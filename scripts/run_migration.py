"""Simple migration runner for user_preferences table."""
import asyncio
import asyncpg
from pathlib import Path
import sys

# Add parent directory to path to import settings
sys.path.insert(0, str(Path(__file__).parent.parent))
from api.config import get_settings


async def run_migration():
    """Run the user_preferences migration."""
    settings = get_settings()

    # Read migration file
    migration_path = Path(__file__).parent.parent / "db" / "migrations" / "001_create_user_preferences.sql"
    migration_sql = migration_path.read_text()

    print(f"Connecting to database...")
    conn = await asyncpg.connect(settings.database_url)

    try:
        print(f"Running migration: 001_create_user_preferences.sql")
        await conn.execute(migration_sql)
        print("✅ Migration completed successfully!")
    except Exception as e:
        print(f"❌ Migration failed: {e}")
        raise
    finally:
        await conn.close()


if __name__ == "__main__":
    asyncio.run(run_migration())
