# GreenGains Backend (TypeScript)

Node.js + TypeScript backend for GreenGains sensor data collection platform.

## Stack

- **Runtime:** Node.js 20+
- **Framework:** Fastify (faster than Express)
- **Validation:** Zod (like Pydantic)
- **Database:** PostgreSQL via node-postgres (pg)
- **Auth:** Firebase Admin SDK

## Setup

1. **Install dependencies:**
   ```bash
   npm install
   ```

2. **Configure environment:**
   Copy `.env.example` to `.env` and fill in values:
   ```bash
   cp .env.example .env
   ```

3. **Run development server:**
   ```bash
   npm run dev
   ```

4. **Build for production:**
   ```bash
   npm run build
   npm start
   ```

## API Endpoints

### Health Check
- `GET /health` - Server health status

### Data Upload
- `POST /upload` - Upload sensor batch (requires X-API-Key header)

### User Preferences (requires Firebase auth)
- `GET /user/preferences` - Get user preferences
- `PUT /user/preferences` - Update user preferences

## Environment Variables

See `.env.example` for required configuration.

## Migration from Python

This backend replaces the Python FastAPI implementation with equivalent TypeScript functionality:

- ✅ Same database schema
- ✅ Same API endpoints
- ✅ Same authentication
- ✅ Better performance (Node.js async I/O)
- ✅ Lower resource usage
