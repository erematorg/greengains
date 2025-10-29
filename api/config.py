"""Configuration management for the GreenGains backend."""

from functools import lru_cache
from typing import List

from pydantic import Field
from pydantic_settings import BaseSettings, SettingsConfigDict


class Settings(BaseSettings):
    """Centralised application settings loaded from environment variables."""

    app_name: str = Field(default="GreenGains Backend", description="Service name.")
    api_key: str = Field(..., alias="GREENGAINS_API_KEY", description="Inbound API key.")
    hash_secret: str = Field(
        ..., alias="GREENGAINS_HASH_SECRET", description="Secret used to hash device IDs."
    )
    database_url: str = Field(
        ..., alias="GREENGAINS_DATABASE_URL", description="PostgreSQL connection string."
    )
    allowed_origins_raw: str | None = Field(
        default=None,
        alias="GREENGAINS_ALLOWED_ORIGINS",
        description="Comma separated list of allowed CORS origins.",
    )
    rate_limit_per_minute: int = Field(
        default=60,
        alias="GREENGAINS_RATE_LIMIT_PER_MINUTE",
        ge=1,
        description="Maximum requests allowed per identifier inside the sliding window.",
    )
    rate_limit_window_seconds: int = Field(
        default=60,
        alias="GREENGAINS_RATE_LIMIT_WINDOW_SECONDS",
        ge=1,
        description="Sliding window size in seconds for rate limiting.",
    )
    rate_limit_identifier_cache_size: int = Field(
        default=2048,
        alias="GREENGAINS_RATE_LIMIT_IDENTIFIER_CACHE_SIZE",
        ge=10,
        description="Maximum number of unique identifier buckets kept in memory.",
    )

    model_config = SettingsConfigDict(env_file=".env", env_file_encoding="utf-8", populate_by_name=True)

    @property
    def allowed_origins(self) -> List[str]:
        """Split comma separated origins while keeping empty lists simple."""

        if not self.allowed_origins_raw:
            return []
        return [origin.strip() for origin in self.allowed_origins_raw.split(",") if origin.strip()]


@lru_cache
def get_settings() -> Settings:
    """Return a cached Settings instance for consistent configuration usage."""

    return Settings()
