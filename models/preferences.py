"""Pydantic models for user preferences."""
from typing import Literal

from pydantic import BaseModel


class UserPreferences(BaseModel):
    """User preferences model matching the database schema."""

    theme_mode: Literal["light", "dark", "system"] = "system"
    use_mobile_data: bool = False
    share_location: bool = False


class UserPreferencesResponse(UserPreferences):
    """Response model with Firebase UID."""

    firebase_uid: str
