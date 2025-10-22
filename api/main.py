"""Application factory for the GreenGains backend."""

import logging
from typing import Optional

from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware
from starlette.middleware.gzip import GZipMiddleware

from api.config import get_settings
from api.middlewares.rate_limit import RateLimitMiddleware
from api.routes.health import router as health_router
from api.routes.upload import router as upload_router
from db.session import close_db_pool, init_db_pool


def create_app(settings_override=None) -> FastAPI:
    """Build and configure the FastAPI application."""

    settings = settings_override or get_settings()

    _configure_logging()

    app = FastAPI(
        title=settings.app_name,
        version="0.1.0",
        docs_url="/docs",
        redoc_url="/redoc",
        openapi_url="/openapi.json",
    )

    _configure_cors(app, settings.allowed_origins)
    app.add_middleware(GZipMiddleware, minimum_size=512)
    app.add_middleware(RateLimitMiddleware)

    app.include_router(health_router)
    app.include_router(upload_router)

    @app.on_event("startup")
    async def _startup() -> None:
        await init_db_pool()

    @app.on_event("shutdown")
    async def _shutdown() -> None:
        await close_db_pool()

    return app


def _configure_logging() -> None:
    """Configure structured logging for the service."""

    logging.basicConfig(
        level=logging.INFO,
        format="%(asctime)s %(levelname)s %(name)s %(message)s",
    )


def _configure_cors(app: FastAPI, allowed_origins: Optional[list[str]]) -> None:
    """Apply permissive defaults with optional strict origins."""

    if allowed_origins:
        origins = allowed_origins
    else:
        origins = ["*"]

    app.add_middleware(
        CORSMiddleware,
        allow_origins=origins,
        allow_credentials=False,
        allow_methods=["POST", "OPTIONS"],
        allow_headers=["*"],
        max_age=86400,
    )


app = create_app()
