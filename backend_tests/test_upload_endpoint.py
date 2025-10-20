"""Tests for the /upload endpoint behaviour."""

import gzip
import json
from datetime import datetime, timezone

import pytest
from httpx import AsyncClient

import api.main as main_module
from api.main import create_app
from api.utils.security import verify_api_key
from db.session import get_connection


class FakeTransaction:
    """Minimal async transaction context manager."""

    def __init__(self, connection):
        self.connection = connection

    async def __aenter__(self):
        return self

    async def __aexit__(self, exc_type, exc, tb):
        return False


class FakeConnection:
    """In-memory stand-in for an asyncpg connection."""

    def __init__(self):
        self.queries: list[tuple[str, tuple]] = []

    def transaction(self):
        return FakeTransaction(self)

    async def execute(self, query: str, *params):
        self.queries.append((query, params))


@pytest.fixture
def fake_connection() -> FakeConnection:
    return FakeConnection()


@pytest.fixture
def test_app(monkeypatch: pytest.MonkeyPatch, fake_connection: FakeConnection):
    async def noop() -> None:
        return None

    monkeypatch.setattr(main_module, "init_db_pool", noop)
    monkeypatch.setattr(main_module, "close_db_pool", noop)

    app = create_app()

    async def _override_connection():
        yield fake_connection

    async def _override_api_key():
        return None

    app.dependency_overrides[get_connection] = _override_connection
    app.dependency_overrides[verify_api_key] = _override_api_key

    return app


def _build_payload():
    base_timestamp = datetime(2025, 10, 17, 12, 5, tzinfo=timezone.utc)
    reading_time = datetime(2025, 10, 17, 12, 0, tzinfo=timezone.utc)
    return {
        "device_id": "device-123",
        "timestamp": base_timestamp.isoformat(),
        "batch": [
            {
                "t": reading_time.isoformat(),
                "light": 240.4,
                "accel": [0.0, 0.1, 9.8],
                "gyro": [0.01, 0.0, 0.03],
                "magnet": [10, 3, -2],
            }
        ],
    }


@pytest.mark.asyncio
async def test_upload_persists_payload(test_app, fake_connection):
    async with AsyncClient(app=test_app, base_url="http://testserver") as client:
        response = await client.post("/upload", json=_build_payload())

    assert response.status_code == 202
    assert response.json() == {"accepted_records": 1}
    assert len(fake_connection.queries) == 1

    query, params = fake_connection.queries[0]
    assert "INSERT INTO sensor_batches" in query
    _, _, payload_json = params
    stored_payload = json.loads(payload_json)
    assert stored_payload["summary"]["count"] == 1
    assert stored_payload["batch"][0]["light"] == 240.4


@pytest.mark.asyncio
async def test_upload_accepts_gzip(test_app):
    payload = json.dumps(_build_payload()).encode("utf-8")
    compressed = gzip.compress(payload)

    async with AsyncClient(app=test_app, base_url="http://testserver") as client:
        response = await client.post(
            "/upload",
            content=compressed,
            headers={"Content-Encoding": "gzip", "Content-Type": "application/json"},
        )

    assert response.status_code == 202
    assert response.json() == {"accepted_records": 1}


@pytest.mark.asyncio
async def test_upload_rejects_invalid_payload(test_app):
    invalid_payload = {"device_id": "bad", "timestamp": "2025-10-17T12:05:00Z", "batch": []}

    async with AsyncClient(app=test_app, base_url="http://testserver") as client:
        response = await client.post("/upload", json=invalid_payload)

    assert response.status_code == 422


@pytest.mark.asyncio
async def test_upload_rejects_unsupported_encoding(test_app):
    async with AsyncClient(app=test_app, base_url="http://testserver") as client:
        response = await client.post(
            "/upload",
            content=b"{}",
            headers={"Content-Encoding": "compress", "Content-Type": "application/json"},
        )

    assert response.status_code == 415
