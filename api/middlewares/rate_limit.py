"""Lightweight rate limiting middleware."""

import asyncio
import time
from collections import OrderedDict, deque
from typing import Deque, Dict, Tuple

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
        self._requests: "OrderedDict[Tuple[str, str], Deque[float]]" = OrderedDict()

    async def dispatch(self, request: Request, call_next):
        settings = get_settings()
        identifier = self._build_identifier(request)
        now = time.monotonic()
        window_seconds = settings.rate_limit_window_seconds
        limit = settings.rate_limit_per_minute
        window_start = now - window_seconds

        async with self._lock:
            timestamps = self._requests.get(identifier)
            if timestamps is None:
                timestamps = deque()
                self._requests[identifier] = timestamps
            else:
                self._requests.move_to_end(identifier)

            while timestamps and timestamps[0] < window_start:
                timestamps.popleft()

            if len(timestamps) >= limit:
                retry_after = max(0, timestamps[0] + window_seconds - now)
                return JSONResponse(
                    status_code=HTTP_429_TOO_MANY_REQUESTS,
                    content={"detail": "Rate limit exceeded."},
                    headers={"Retry-After": f"{retry_after:.0f}"},
                )

            timestamps.append(now)
            self._requests.move_to_end(identifier)

            # Drop stale buckets to keep memory bounded under DoS attempts.
            max_buckets = settings.rate_limit_identifier_cache_size
            while len(self._requests) > max_buckets:
                self._requests.popitem(last=False)

        response: Response = await call_next(request)
        return response

    @staticmethod
    def _build_identifier(request: Request) -> Tuple[str, str]:
        """Build a key combining API key and client IP where possible."""

        api_key = request.headers.get("X-API-Key", "anonymous")
        client_host = request.client.host if request.client else "unknown"
        return api_key, client_host
