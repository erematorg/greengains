"""Lightweight rate limiting middleware."""

import asyncio
import time
from collections import defaultdict
from typing import Dict, Tuple

from fastapi import Request, Response
from starlette.middleware.base import BaseHTTPMiddleware
from starlette.responses import JSONResponse
from starlette.status import HTTP_429_TOO_MANY_REQUESTS

from api.config import get_settings


class RateLimitMiddleware(BaseHTTPMiddleware):
    """In-memory sliding window rate limiter suited for the MVP."""

    def __init__(self, app):
        super().__init__(app)
        self._lock = asyncio.Lock()
        self._requests: Dict[Tuple[str, str], list[float]] = defaultdict(list)

    async def dispatch(self, request: Request, call_next):
        settings = get_settings()
        identifier = self._build_identifier(request)
        now = time.monotonic()
        window_start = now - 60

        async with self._lock:
            timestamps = self._requests[identifier]
            while timestamps and timestamps[0] < window_start:
                timestamps.pop(0)

            if len(timestamps) >= settings.rate_limit_per_minute:
                retry_after = max(0, timestamps[0] + 60 - now)
                return JSONResponse(
                    status_code=HTTP_429_TOO_MANY_REQUESTS,
                    content={"detail": "Rate limit exceeded."},
                    headers={"Retry-After": f"{retry_after:.0f}"},
                )

            timestamps.append(now)

        response: Response = await call_next(request)
        return response

    @staticmethod
    def _build_identifier(request: Request) -> Tuple[str, str]:
        """Build a key combining API key and client IP where possible."""

        api_key = request.headers.get("X-API-Key", "anonymous")
        client_host = request.client.host if request.client else "unknown"
        return api_key, client_host
