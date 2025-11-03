import { FastifyInstance, FastifyRequest, FastifyReply } from 'fastify';
import { decompressPayload, UnsupportedEncodingError } from '../utils/compression';
import { verifyApiKey } from '../utils/security';
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

  const accelMagnitudes = readings.map(r => vectorMagnitude(r.accel));
  const gyroMagnitudes = readings.map(r => vectorMagnitude(r.gyro));

  const periodStart = new Date(Math.min(...readings.map(r => r.t.getTime())));
  const periodEnd = new Date(Math.max(...readings.map(r => r.t.getTime())));

  return {
    count: readings.length,
    period_start: periodStart,
    period_end: periodEnd,
    light: lightSummary,
    accel_rms: accelMagnitudes.reduce((a, b) => a + b, 0) / accelMagnitudes.length,
    gyro_rms: gyroMagnitudes.reduce((a, b) => a + b, 0) / gyroMagnitudes.length,
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

export async function uploadRoutes(fastify: FastifyInstance) {
  fastify.post(
    '/upload',
    {
      preHandler: verifyApiKey,
    },
    async (request: FastifyRequest, reply: FastifyReply) => {
      console.log('Upload request received');
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
          'INSERT INTO sensor_batches (device_hash, timestamp_utc, batch_json) VALUES ($1, $2, $3::jsonb)',
          [deviceHash, batch.timestamp, payloadJson]
        );

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
