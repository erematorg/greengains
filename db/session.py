"""Async PostgreSQL connection management using asyncpg."""

from collections.abc import AsyncIterator
from typing import Optional

import asyncpg
from fastapi import Depends

from api.config import get_settings


_pool: Optional[asyncpg.pool.Pool] = None


async def init_db_pool() -> None:
    """Initialise the global asyncpg connection pool."""

    global _pool
    if _pool is None:
        settings = get_settings()
        _pool = await asyncpg.create_pool(settings.database_url, min_size=1, max_size=5)


async def close_db_pool() -> None:
    """Close the pool during application shutdown."""

    global _pool
    if _pool is not None:
        await _pool.close()
        _pool = None


async def get_connection() -> AsyncIterator[asyncpg.Connection]:
    """Yield a pooled connection for request-scoped usage."""

    if _pool is None:
        raise RuntimeError("Database pool has not been initialised.")

    conn = await _pool.acquire()
    try:
        yield conn
    finally:
        await _pool.release(conn)


DatabaseConnection = Depends(get_connection)
