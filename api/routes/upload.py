"""Upload endpoint implementation."""

import json
import logging
from math import sqrt
from statistics import mean
from typing import Dict, List

from asyncpg import Connection
from fastapi import APIRouter, HTTPException, Request, status
from fastapi.encoders import jsonable_encoder
from pydantic import ValidationError

from api.config import get_settings
from api.utils.compression import UnsupportedEncodingError, decompress_payload
from api.utils.security import ApiKeyDependency, hash_device_id
from db.session import DatabaseConnection
from models.upload import SensorReading, UploadBatch

logger = logging.getLogger(__name__)

router = APIRouter(tags=["ingest"])


@router.post("/upload", status_code=status.HTTP_202_ACCEPTED, dependencies=[ApiKeyDependency])
async def upload_sensor_batch(
    request: Request,
    connection: Connection = DatabaseConnection,
) -> Dict[str, int]:
    """Ingest a compressed or plain JSON batch of sensor readings."""

    settings = get_settings()

    raw_payload = await request.body()
    try:
        payload = decompress_payload(request.headers.get("Content-Encoding"), raw_payload)
    except UnsupportedEncodingError as exc:
        raise HTTPException(status_code=status.HTTP_415_UNSUPPORTED_MEDIA_TYPE, detail=str(exc)) from exc
    except OSError as exc:  # Invalid compressed bytes
        raise HTTPException(status_code=status.HTTP_400_BAD_REQUEST, detail="Invalid compressed payload.") from exc

    try:
        batch = UploadBatch.model_validate_json(payload)
    except ValidationError as exc:
        raise HTTPException(status_code=status.HTTP_422_UNPROCESSABLE_ENTITY, detail=exc.errors()) from exc

    device_hash = hash_device_id(batch.device_id, settings.hash_secret)
    readings_count = len(batch.batch)

    sanitized_payload = _build_storage_payload(batch)
    payload_json = json.dumps(jsonable_encoder(sanitized_payload), separators=(",", ":"))

    async with connection.transaction():
        await connection.execute(
            """
            INSERT INTO sensor_batches (device_hash, timestamp_utc, batch_json)
            VALUES ($1, $2, $3::jsonb)
            """,
            device_hash,
            batch.timestamp,
            payload_json,
        )

    stats = sanitized_payload["summary"]
    log_extra = {
        "device_hash": device_hash,
        "batch_size": readings_count,
        "period_start": stats["period_start"],
        "period_end": stats["period_end"],
        "avg_light": stats["light"]["avg"],
    }

    # Include location in logs if present
    if batch.location is not None:
        log_extra["location"] = f"({batch.location.lat}, {batch.location.lon})"
        log_extra["location_accuracy_m"] = batch.location.accuracy_m

    logger.info("Stored sensor batch", extra=log_extra)

    return {"accepted_records": readings_count}


def _build_storage_payload(batch: UploadBatch) -> Dict[str, object]:
    """Construct an anonymised storage payload with aggregate metrics."""

    summary = _summarise_batch(batch.batch)
    readings = [reading.model_dump() for reading in batch.batch]

    payload = {
        "timestamp": batch.timestamp,
        "summary": summary,
        "batch": readings,
    }

    # Include optional location data for environmental heatmap generation
    if batch.location is not None:
        payload["location"] = batch.location.model_dump()

    return payload


def _summarise_batch(readings: List[SensorReading]) -> Dict[str, object]:
    """Create simple aggregate metrics for dashboard backfills."""

    lights = [reading.light for reading in readings]
    light_summary = {
        "avg": mean(lights),
        "min": min(lights),
        "max": max(lights),
    }

    accel_magnitudes = [_vector_magnitude(reading.accel) for reading in readings]
    gyro_magnitudes = [_vector_magnitude(reading.gyro) for reading in readings]

    period_start = min(reading.t for reading in readings)
    period_end = max(reading.t for reading in readings)

    return {
        "count": len(readings),
        "period_start": period_start,
        "period_end": period_end,
        "light": light_summary,
        "accel_rms": mean(accel_magnitudes),
        "gyro_rms": mean(gyro_magnitudes),
    }


def _vector_magnitude(vector: List[float]) -> float:
    """Return Euclidean magnitude for a 3D vector."""

    return sqrt(sum(component**2 for component in vector))
