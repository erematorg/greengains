"""Convenience script to run backend regression checks locally."""

from __future__ import annotations

import subprocess
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent


def run(cmd: list[str], description: str) -> None:
    print(f"→ {description}")
    completed = subprocess.run(cmd, cwd=ROOT, check=False)
    if completed.returncode != 0:
        raise SystemExit(f"Command failed ({description}): {' '.join(cmd)}")


def main() -> None:
    run([sys.executable, "-m", "pytest", "backend_tests"], "pytest backend_tests")
    run([sys.executable, "scripts/send_sample.py"], "send sample batch")
    run(
        [sys.executable, "scripts/cleanup.py", "--dry-run"],
        "cleanup dry-run (no deletions)",
    )
    print("✅ All checks passed.")


if __name__ == "__main__":
    main()
