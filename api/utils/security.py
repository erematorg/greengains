"""Security helpers for authentication and anonymisation."""

import hashlib
import hmac
from typing import Annotated

from fastapi import Depends, Header, HTTPException, status

from api.config import get_settings


def hash_device_id(device_id: str, secret: str) -> str:
    """Return a stable SHA-256 HMAC digest of the device identifier."""

    digest = hmac.new(secret.encode("utf-8"), device_id.encode("utf-8"), hashlib.sha256)
    return digest.hexdigest()


async def verify_api_key(
    x_api_key: Annotated[str | None, Header(alias="X-API-Key")] = None,
) -> None:
    """Validate the X-API-Key header against the configured API key."""

    settings = get_settings()
    if x_api_key is None or not hmac.compare_digest(x_api_key, settings.api_key):
        raise HTTPException(
            status_code=status.HTTP_401_UNAUTHORIZED,
            detail="Invalid or missing API key.",
        )


ApiKeyDependency = Depends(verify_api_key)
