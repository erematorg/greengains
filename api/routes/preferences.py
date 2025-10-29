"""User preferences endpoints."""
import logging
from typing import Optional

from asyncpg import Connection
from fastapi import APIRouter, HTTPException, status

from api.utils.firebase_auth import FirebaseUidDependency
from db.session import DatabaseConnection
from models.preferences import UserPreferences, UserPreferencesResponse

logger = logging.getLogger(__name__)

router = APIRouter(prefix="/user", tags=["preferences"])


@router.get("/preferences", response_model=UserPreferencesResponse)
async def get_user_preferences(
    firebase_uid: Optional[str] = FirebaseUidDependency,
    connection: Connection = DatabaseConnection,
) -> UserPreferencesResponse:
    """
    Get user preferences from the database.

    Requires Firebase authentication token in Authorization header.
    Returns default preferences if user has never set any.
    """
    if not firebase_uid:
        raise HTTPException(
            status_code=status.HTTP_401_UNAUTHORIZED,
            detail="Authentication required",
        )

    row = await connection.fetchrow(
        """
        SELECT firebase_uid, theme_mode, use_mobile_data, share_location
        FROM user_preferences
        WHERE firebase_uid = $1
        """,
        firebase_uid,
    )

    if row:
        return UserPreferencesResponse(**dict(row))

    # Return defaults if user hasn't set preferences yet
    return UserPreferencesResponse(
        firebase_uid=firebase_uid,
        theme_mode="system",
        use_mobile_data=False,
        share_location=False,
    )


@router.put("/preferences", response_model=UserPreferencesResponse)
async def update_user_preferences(
    preferences: UserPreferences,
    firebase_uid: Optional[str] = FirebaseUidDependency,
    connection: Connection = DatabaseConnection,
) -> UserPreferencesResponse:
    """
    Update user preferences in the database.

    Requires Firebase authentication token in Authorization header.
    Creates a new row if user doesn't exist, updates if they do (upsert).
    """
    if not firebase_uid:
        raise HTTPException(
            status_code=status.HTTP_401_UNAUTHORIZED,
            detail="Authentication required",
        )

    await connection.execute(
        """
        INSERT INTO user_preferences (firebase_uid, theme_mode, use_mobile_data, share_location)
        VALUES ($1, $2, $3, $4)
        ON CONFLICT (firebase_uid)
        DO UPDATE SET
            theme_mode = EXCLUDED.theme_mode,
            use_mobile_data = EXCLUDED.use_mobile_data,
            share_location = EXCLUDED.share_location,
            updated_at = NOW()
        """,
        firebase_uid,
        preferences.theme_mode,
        preferences.use_mobile_data,
        preferences.share_location,
    )

    logger.info(f"Updated preferences for user {firebase_uid}")

    return UserPreferencesResponse(
        firebase_uid=firebase_uid,
        **preferences.model_dump(),
    )
