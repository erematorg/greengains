## GreenGains Backend Playbook

### Local Development
- Install deps: `python -m venv venv && venv\Scripts\activate && pip install -r requirements.txt`.
- Run API: `uvicorn api.main:app --reload`.
- Environment vars live in `.env`; Supabase session-pooler URL keeps local IPv4 machines happy.
- Tests: `pytest backend_tests` (stubs the DB connection, so no network dependency).
- Manual POST sanity check: `python scripts/send_sample.py` (adds a gzip batch); alternatively `Invoke-WebRequest … scripts\sample_payload.json`.

### Database & Retention
- Schema defined in `scripts/init_db.sql`; apply via Supabase SQL Editor (already done).
- Daily retention: schedule `python scripts/cleanup.py` (Supabase Scheduler, cron, or GH Actions) to delete records older than 30 days.

### API Contract
- `POST /upload` (requires header `X-API-Key`).
- Payload mirrors mobile batch format; gzip/Brotli accepted.
- Server stores batch+summary in `sensor_batches.batch_json`.
- Failures: `422` on validation errors, `415` on unknown encodings, `400` on corrupt compression.

### Upcoming Backend Tasks
- Add `/summary` and `/partners/data` endpoints once analytics needs are clearer.
- Monitor Supabase storage (30-day retention keeps cost low).
- Convert to JWT auth when real users exist.
- Add deployment config (Render/Railway/Fly) when ready to publish publicly.
