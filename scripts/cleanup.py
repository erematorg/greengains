"""Cleanup script removing sensor batches older than the retention window."""

from __future__ import annotations

import argparse
import asyncio
import logging
from typing import Optional

import asyncpg

from api.config import get_settings

RETENTION_DAYS = 30


async def purge_expired_batches(
    retention_days: Optional[int] = None, *, dry_run: bool = False
) -> tuple[int, int]:
    """Delete records older than the configured retention window.

    Returns a tuple of (deleted_count, candidate_count). When dry_run is True the
    first element will always be zero and the second will contain the number of
    rows that would be removed.
    """

    settings = get_settings()
    days = retention_days or RETENTION_DAYS

    connection = await asyncpg.connect(settings.database_url)
    try:
        if dry_run:
            would_remove = await connection.fetchval(
                """
                SELECT COUNT(*) FROM sensor_batches
                WHERE timestamp_utc < timezone('utc', now()) - ($1 * INTERVAL '1 day')
                """,
                days,
            )
            return 0, int(would_remove or 0)

        command_tag = await connection.execute(
            """
            DELETE FROM sensor_batches
            WHERE timestamp_utc < timezone('utc', now()) - ($1 * INTERVAL '1 day')
            """,
            days,
        )
    finally:
        await connection.close()

    deleted_count = int(command_tag.split()[-1])
    return deleted_count, deleted_count


async def main() -> None:
    """Entrypoint for direct script execution."""

    parser = argparse.ArgumentParser(description="Purge expired sensor batches.")
    parser.add_argument(
        "--days",
        type=int,
        default=RETENTION_DAYS,
        help="Retention window in days (default: 30).",
    )
    parser.add_argument(
        "--dry-run",
        action="store_true",
        help="Only report how many rows would be removed without deleting them.",
    )
    args = parser.parse_args()

    logging.basicConfig(
        level=logging.INFO,
        format="%(asctime)s %(levelname)s %(message)s",
    )

    deleted, candidates = await purge_expired_batches(args.days, dry_run=args.dry_run)
    if args.dry_run:
        logging.info(
            "Dry-run complete. %d batches older than %d days would be removed.",
            candidates,
            args.days,
        )
    else:
        logging.info(
            "Cleanup complete. Removed %d batches older than %d days.",
            deleted,
            args.days,
        )


if __name__ == "__main__":
    asyncio.run(main())
