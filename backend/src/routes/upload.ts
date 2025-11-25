import { FastifyInstance, FastifyRequest, FastifyReply } from 'fastify';
import type { Pool } from 'pg';
import { decompressPayload, UnsupportedEncodingError } from '../utils/compression';
import { verifyApiKey } from '../utils/security';
import { verifyFirebaseToken } from '../utils/firebase-auth';
import { hashDeviceId } from '../utils/security';
import { getPool } from '../database';
import { UploadBatchSchema, UploadBatch, SensorReading } from '../models/upload';

interface Summary {
  count: number;
  period_start: Date;
  period_end: Date;
  light: { avg: number; min: number; max: number };
  accel_rms: number;
  gyro_rms: number;
}

function vectorMagnitude(vector: number[]): number {
  return Math.sqrt(vector.reduce((sum, component) => sum + component ** 2, 0));
}

function summarizeBatch(readings: SensorReading[]): Summary {
  const lights = readings.map(r => r.light);
  const lightSummary = {
    avg: lights.reduce((a, b) => a + b, 0) / lights.length,
    min: Math.min(...lights),
    max: Math.max(...lights),
  };

  // Handle optional accel data
  const accelReadings = readings.filter(r => r.accel !== undefined);
  const accelMagnitudes = accelReadings.length > 0
    ? accelReadings.map(r => vectorMagnitude(r.accel!))
    : [];

  // Handle optional gyro data
  const gyroReadings = readings.filter(r => r.gyro !== undefined);
  const gyroMagnitudes = gyroReadings.length > 0
    ? gyroReadings.map(r => vectorMagnitude(r.gyro!))
    : [];

  const periodStart = new Date(Math.min(...readings.map(r => r.t.getTime())));
  const periodEnd = new Date(Math.max(...readings.map(r => r.t.getTime())));

  return {
    count: readings.length,
    period_start: periodStart,
    period_end: periodEnd,
    light: lightSummary,
    accel_rms: accelMagnitudes.length > 0
      ? accelMagnitudes.reduce((a, b) => a + b, 0) / accelMagnitudes.length
      : 0,
    gyro_rms: gyroMagnitudes.length > 0
      ? gyroMagnitudes.reduce((a, b) => a + b, 0) / gyroMagnitudes.length
      : 0,
  };
}

function buildStoragePayload(batch: UploadBatch): any {
  const summary = summarizeBatch(batch.batch);

  const payload: any = {
    timestamp: batch.timestamp,
    summary,
    batch: batch.batch,
  };

  if (batch.location) {
    payload.location = batch.location;
  }

  if (batch.geohash) {
    payload.geohash = batch.geohash;
  }

  if (batch.battery_level !== undefined) {
    payload.battery_level = batch.battery_level;
  }

  if (batch.is_charging !== undefined) {
    payload.is_charging = batch.is_charging;
  }

  return payload;
}

type QualityCounters = {
  total: number;
  valid: number;
  pocketLikely: number;
};

function analyzeQuality(readings: SensorReading[]): QualityCounters {
  const counters: QualityCounters = {
    total: 0,
    valid: 0,
    pocketLikely: 0,
  };

  for (const reading of readings) {
    const quality = (reading as any)?.quality;
    if (!quality) continue;
    counters.total += 1;

    const pocket = String(quality.pocket ?? '').toLowerCase();
    if (pocket === 'likely') {
      counters.pocketLikely += 1;
      continue;
    }

    const locationQuality = String(quality.location_quality ?? '').toLowerCase();
    const motionState = String(quality.motion_state ?? '').toLowerCase();
    const motionConfidence =
      typeof quality.motion_confidence === 'number' ? quality.motion_confidence : 0;

    const locationOk = ['high', 'medium', 'low'].includes(locationQuality);
    const motionOk = motionState !== 'unknown' && motionConfidence >= 0.2;

    if (locationOk || motionOk || pocket === 'unlikely') {
      counters.valid += 1;
    }
  }

  return counters;
}

function calculateUptimeSeconds(summary: Summary): number {
  const start = summary?.period_start ? summary.period_start.getTime() : 0;
  const end = summary?.period_end ? summary.period_end.getTime() : start;
  if (!start || !end) return 0;
  return Math.max(0, Math.floor((end - start) / 1000));
}

async function upsertUserStats(
  pool: Pool,
  deviceHash: string,
  summary: Summary,
  readings: SensorReading[],
  timestamp: Date,
  userId: string | null,
): Promise<void> {
  const totalSamples = summary?.count ?? readings.length;
  if (totalSamples <= 0) {
    return;
  }

  const quality = analyzeQuality(readings);
  const uptimeSeconds = calculateUptimeSeconds(summary);

  await pool.query(
    `INSERT INTO user_stats (
      device_hash, samples_count, valid_samples, pocket_samples, uptime_seconds, last_upload_at, user_id
    ) VALUES ($1, $2, $3, $4, $5, $6, $7)
    ON CONFLICT (device_hash)
    DO UPDATE SET
      samples_count = user_stats.samples_count + EXCLUDED.samples_count,
      valid_samples = user_stats.valid_samples + EXCLUDED.valid_samples,
      pocket_samples = user_stats.pocket_samples + EXCLUDED.pocket_samples,
      uptime_seconds = user_stats.uptime_seconds + EXCLUDED.uptime_seconds,
      last_upload_at = GREATEST(user_stats.last_upload_at, EXCLUDED.last_upload_at),
      user_id = COALESCE(EXCLUDED.user_id, user_stats.user_id), -- Update user_id if provided
      updated_at = NOW()`,
    [
      deviceHash,
      totalSamples,
      quality.valid,
      quality.pocketLikely,
      uptimeSeconds,
      timestamp,
      userId
    ],
  );
}

export async function uploadRoutes(fastify: FastifyInstance) {
  fastify.post(
    '/upload',
    {
      preHandler: verifyApiKey,
    },
    async (request: FastifyRequest, reply: FastifyReply) => {
      // 1. Verify Firebase Auth (The "Honeygain" Way)
      let userId: string | null = null;
      try {
        userId = await verifyFirebaseToken(request, reply);
      } catch (e) {
        console.warn('Auth check failed or missing:', e);
      }

      console.log(`Upload request received. User: ${userId || 'Anonymous/Legacy'}`);

      try {
        // Decompress payload if needed
        const rawPayload = request.body as Buffer;
        const contentEncoding = request.headers['content-encoding'];

        let decompressed: Buffer;
        try {
          decompressed = decompressPayload(contentEncoding, rawPayload);
        } catch (error) {
          if (error instanceof UnsupportedEncodingError) {
            return reply.code(415).send({ error: 'Unsupported Media Type', message: error.message });
          }
          return reply.code(400).send({ error: 'Bad Request', message: 'Invalid compressed payload' });
        }

        // Parse and validate JSON
        let batch: UploadBatch;
        try {
          const parsed = JSON.parse(decompressed.toString('utf-8'));
          batch = UploadBatchSchema.parse(parsed);
        } catch (error: any) {
          return reply.code(422).send({
            error: 'Unprocessable Entity',
            message: 'Validation failed',
            details: error.issues || error.message,
          });
        }

        // Hash device ID for anonymization
        const deviceHash = hashDeviceId(batch.device_id);
        const readingsCount = batch.batch.length;

        // Build storage payload
        const sanitizedPayload = buildStoragePayload(batch);
        const payloadJson = JSON.stringify(sanitizedPayload);

        // Store in database
        const pool = getPool();
        await pool.query(
          'INSERT INTO sensor_batches (device_hash, timestamp_utc, batch_json, user_id) VALUES ($1, $2, $3::jsonb, $4)',
          [deviceHash, batch.timestamp, payloadJson, userId]
        );
        await upsertUserStats(pool, deviceHash, sanitizedPayload.summary, batch.batch, batch.timestamp, userId);

        // Log with stats
        const stats = sanitizedPayload.summary;
        const logData: any = {
          device_hash: deviceHash,
          batch_size: readingsCount,
          period_start: stats.period_start,
          period_end: stats.period_end,
          avg_light: stats.light.avg,
        };

        if (batch.location) {
          logData.location = `(${batch.location.lat}, ${batch.location.lon})`;
          logData.location_accuracy_m = batch.location.accuracy_m;
        }

        console.log('Stored sensor batch', logData);

        return reply.code(202).send({ accepted_records: readingsCount });
      } catch (error) {
        console.error('Upload error:', error);
        return reply.code(500).send({ error: 'Internal Server Error' });
      }
    }
  );
}
