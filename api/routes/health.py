"""Operational health endpoint."""

from datetime import datetime, timezone

from asyncpg import Connection
from fastapi import APIRouter

from db.session import DatabaseConnection

router = APIRouter(tags=["ops"])
_service_started = datetime.now(timezone.utc)


@router.get("/health")
async def health_check(connection: Connection = DatabaseConnection) -> dict[str, object]:
    """Return minimal operational status information."""

    batch_count = await connection.fetchval("SELECT COUNT(*) FROM sensor_batches")
    return {
        "status": "ok",
        "service_started": _service_started.isoformat(),
        "uptime_seconds": (datetime.now(timezone.utc) - _service_started).total_seconds(),
        "batches_total": int(batch_count or 0),
    }
