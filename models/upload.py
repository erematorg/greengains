"""Pydantic models describing upload payloads."""

from datetime import datetime
from typing import List

from pydantic import BaseModel, Field, field_validator, model_validator


class SensorReading(BaseModel):
    """Single sensor reading emitted by a device."""

    t: datetime = Field(description="UTC timestamp for the reading.")
    light: float = Field(description="Ambient light level in lux.")
    accel: List[float] = Field(
        description="Acceleration vector in m/s^2.", min_length=3, max_length=3
    )
    gyro: List[float] = Field(description="Gyroscope vector in rad/s.", min_length=3, max_length=3)
    magnet: List[float] = Field(
        description="Magnetometer vector in microtesla.", min_length=3, max_length=3
    )

    @field_validator("accel", "gyro", "magnet")
    @classmethod
    def _validate_vectors(cls, value: List[float]) -> List[float]:
        """Ensure vectors always contain numeric values."""

        if len(value) != 3:
            raise ValueError("Vector fields must contain exactly 3 elements.")
        return [float(component) for component in value]


class UploadBatch(BaseModel):
    """Batch upload schema used by the /upload endpoint."""

    device_id: str = Field(min_length=1, max_length=128, description="Anonymous device identifier.")
    timestamp: datetime = Field(description="Submission time recorded on device.")
    batch: List[SensorReading] = Field(
        description="Collection of sensor readings.", min_length=1
    )
