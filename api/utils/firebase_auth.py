"""Firebase authentication utilities for verifying user tokens."""
import logging
from typing import Optional

import firebase_admin
from fastapi import Depends, Header, HTTPException, status
from firebase_admin import auth, credentials

logger = logging.getLogger(__name__)

# Initialize Firebase Admin SDK (call once at startup)
_app: Optional[firebase_admin.App] = None


def init_firebase_app():
    """Initialize Firebase Admin SDK with default credentials."""
    global _app
    if _app is None:
        try:
            # Uses GOOGLE_APPLICATION_CREDENTIALS environment variable
            # or default credentials from the environment
            _app = firebase_admin.initialize_app()
            logger.info("Firebase Admin SDK initialized successfully")
        except Exception as e:
            logger.warning(f"Firebase Admin SDK initialization failed: {e}")
            logger.warning("User authentication will not be available")


async def get_firebase_uid(authorization: Optional[str] = Header(None)) -> Optional[str]:
    """
    Extract and verify Firebase ID token from Authorization header.

    Returns:
        Firebase UID if token is valid, None if no token provided

    Raises:
        HTTPException 401 if token is invalid
    """
    if not authorization:
        return None

    if not authorization.startswith("Bearer "):
        raise HTTPException(
            status_code=status.HTTP_401_UNAUTHORIZED,
            detail="Invalid authorization header format. Expected 'Bearer <token>'",
        )

    token = authorization[7:]  # Remove "Bearer " prefix

    try:
        decoded_token = auth.verify_id_token(token)
        return decoded_token["uid"]
    except auth.InvalidIdTokenError:
        raise HTTPException(
            status_code=status.HTTP_401_UNAUTHORIZED,
            detail="Invalid or expired Firebase token",
        )
    except Exception as e:
        logger.error(f"Firebase token verification failed: {e}")
        raise HTTPException(
            status_code=status.HTTP_401_UNAUTHORIZED,
            detail="Token verification failed",
        )


# Dependency for endpoints that require authentication
FirebaseUidDependency = Depends(get_firebase_uid)
