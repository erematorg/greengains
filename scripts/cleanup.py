"""Cleanup script removing sensor batches older than the retention window."""

import asyncio
import logging
from typing import Optional

import asyncpg

from api.config import get_settings

RETENTION_DAYS = 30


async def purge_expired_batches(retention_days: Optional[int] = None) -> int:
    """Delete records older than the configured retention window."""

    settings = get_settings()
    days = retention_days or RETENTION_DAYS

    connection = await asyncpg.connect(settings.database_url)
    try:
        command_tag = await connection.execute(
            """
            DELETE FROM sensor_batches
            WHERE timestamp_utc < timezone('utc', now()) - ($1 * INTERVAL '1 day')
            """,
            days,
        )
    finally:
        await connection.close()

    # asyncpg returns strings of the form "DELETE <count>"
    deleted_count = int(command_tag.split()[-1])
    return deleted_count


async def main() -> None:
    """Entrypoint for direct script execution."""

    logging.basicConfig(
        level=logging.INFO,
        format="%(asctime)s %(levelname)s %(message)s",
    )

    removed = await purge_expired_batches()
    logging.info("Cleanup complete. Removed %d expired batches.", removed)


if __name__ == "__main__":
    asyncio.run(main())
