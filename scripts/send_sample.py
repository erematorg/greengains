"""Send a sample sensor batch to the local backend for manual testing."""

import gzip
import json
import time

import requests

API_KEY = "Vb9kS3tP0xQ7fY2L"
BACKEND_URL = "http://127.0.0.1:8000/upload"


def main() -> None:
    payload = {
        "device_id": "desktop-test",
        "timestamp": time.strftime("%Y-%m-%dT%H:%M:%SZ", time.gmtime()),
        "batch": [
            {
                "t": time.strftime("%Y-%m-%dT%H:%M:%SZ", time.gmtime()),
                "light": 240.4,
                "accel": [0.0, 0.1, 9.8],
                "gyro": [0.01, 0.0, 0.03],
                "magnet": [10, 3, -2],
            }
        ],
    }

    compressed = gzip.compress(json.dumps(payload).encode("utf-8"))
    response = requests.post(
        BACKEND_URL,
        headers={
            "X-API-Key": API_KEY,
            "Content-Type": "application/json",
            "Content-Encoding": "gzip",
        },
        data=compressed,
        timeout=10,
    )
    print(response.status_code, response.text)


if __name__ == "__main__":
    main()
