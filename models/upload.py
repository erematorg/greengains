"""Pydantic models describing upload payloads."""

from datetime import datetime
from typing import List, Optional

from pydantic import BaseModel, Field, field_validator, model_validator


class LocationData(BaseModel):
    """Optional location data for smart city environmental analysis (like Nodle Cash)."""

    lat: float = Field(description="Latitude (FINE: ~10-50m GPS accuracy, full precision).")
    lon: float = Field(description="Longitude (FINE: ~10-50m GPS accuracy, full precision).")
    altitude: float = Field(description="Altitude in meters above sea level (for elevation correlation).")
    accuracy_m: float = Field(description="GPS accuracy estimate in meters.")

    @field_validator("lat")
    @classmethod
    def _validate_latitude(cls, value: float) -> float:
        """Ensure latitude is in valid range."""
        if not -90 <= value <= 90:
            raise ValueError("Latitude must be between -90 and 90 degrees.")
        return value

    @field_validator("lon")
    @classmethod
    def _validate_longitude(cls, value: float) -> float:
        """Ensure longitude is in valid range."""
        if not -180 <= value <= 180:
            raise ValueError("Longitude must be between -180 and 180 degrees.")
        return value


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
    location: Optional[LocationData] = Field(
        default=None,
        description="Optional GPS location for environmental heatmap generation."
    )
    geohash: Optional[str] = Field(
        default=None,
        max_length=12,
        description="Privacy-preserving geohash (6-char = ~1.2km grid, like Nodle Cash)."
    )
    battery_level: Optional[int] = Field(
        default=None,
        ge=-1,
        le=100,
        description="Battery level 0-100%, or -1 if charging (Nodle pattern)."
    )
    is_charging: Optional[bool] = Field(
        default=None,
        description="True if device is currently charging."
    )
