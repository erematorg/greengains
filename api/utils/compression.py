"""Helpers for decoding compressed request payloads."""

import gzip
from typing import Optional

try:
    import brotli
except ModuleNotFoundError:  # pragma: no cover - optional dependency
    brotli = None


class UnsupportedEncodingError(ValueError):
    """Raised when the content encoding is not supported."""


def decompress_payload(content_encoding: Optional[str], payload: bytes) -> bytes:
    """Return uncompressed request bytes based on the Content-Encoding header."""

    if not payload:
        return payload

    if not content_encoding:
        return payload

    normalized = content_encoding.lower()
    if normalized == "gzip":
        return gzip.decompress(payload)
    if normalized in {"br", "brotli"}:
        if brotli is None:
            raise UnsupportedEncodingError("Brotli support not installed.")
        return brotli.decompress(payload)

    if normalized not in {"identity", "none"}:
        raise UnsupportedEncodingError(f"Unsupported content encoding: {content_encoding}")
    return payload
