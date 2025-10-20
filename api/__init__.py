"""Application package exposing the FastAPI entrypoint for GreenGains."""

# Re-export the factory for convenience when used by ASGI servers.
from .main import create_app

__all__ = ["create_app"]
