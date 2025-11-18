import { FastifyInstance } from 'fastify';
import { z } from 'zod';
import { getPool } from '../database';
import { AGGREGATION_WINDOW_MINUTES } from '../jobs/aggregator';

const aggregatesQuerySchema = z.object({
  from: z.string().datetime().optional(),
  to: z.string().datetime().optional(),
  geohash: z.string().max(12).optional(),
  bucket: z.enum(['5m', 'day']).default('5m'),
  limit: z.coerce.number().int().min(1).max(500).default(200),
  cursor: z.string().optional(),
});

const coverageQuerySchema = z.object({
  hours: z.coerce.number().int().min(1).max(168).default(24),
  min_devices: z.coerce.number().int().min(0).default(0),
  precision: z.coerce.number().int().min(1).max(12).default(6),
});

const deviceStatsQuerySchema = z.object({
  limit: z.coerce.number().int().min(1).max(500).default(100),
  cursor: z.string().optional(),
});

export async function analyticsRoutes(fastify: FastifyInstance) {
  const pool = getPool();

  fastify.get('/analytics/aggregates', async (request) => {
    const query = aggregatesQuerySchema.parse(request.query);
    const table = query.bucket === 'day' ? 'sensor_aggregates_daily' : 'sensor_aggregates_5m';

    const whereClauses: string[] = [];
    const params: any[] = [];
    let paramIndex = 1;

    if (query.from) {
      const column = query.bucket === 'day' ? 'day' : 'window_start';
      whereClauses.push(`${column} >= $${paramIndex++}`);
      params.push(query.from);
    }

    if (query.to) {
      const column = query.bucket === 'day' ? 'day' : 'window_start';
      whereClauses.push(`${column} <= $${paramIndex++}`);
      params.push(query.to);
    }

    if (query.geohash) {
      whereClauses.push(`geohash LIKE $${paramIndex++}`);
      params.push(`${query.geohash}%`);
    }

    if (query.cursor) {
      const [cursorTime, cursorGeohash] = query.cursor.split('|');
      if (cursorTime && cursorGeohash) {
        params.push(cursorTime);
        params.push(cursorGeohash);
        const column = query.bucket === 'day' ? 'day' : 'window_start';
        whereClauses.push(`(${column}, geohash) > ($${paramIndex++ - 1}, $${paramIndex++ - 1})`);
      }
    }

    const whereSql = whereClauses.length > 0 ? `WHERE ${whereClauses.join(' AND ')}` : '';
    const orderColumn = query.bucket === 'day' ? 'day' : 'window_start';

    const sql = `
      SELECT *
      FROM ${table}
      ${whereSql}
      ORDER BY ${orderColumn} ASC, geohash ASC
      LIMIT $${paramIndex}
    `;
    params.push(query.limit);

    const result = await pool.query(sql, params);

    const items = result.rows.map((row) => ({
      ...row,
      samples_count: Number(row.samples_count),
      device_count: Number(row.device_count),
      avg_light: row.avg_light !== null ? Number(row.avg_light) : null,
      avg_light_min: row.avg_light_min !== null ? Number(row.avg_light_min) : null,
      avg_light_max: row.avg_light_max !== null ? Number(row.avg_light_max) : null,
      avg_accel_rms: row.avg_accel_rms !== null ? Number(row.avg_accel_rms) : null,
      avg_gyro_rms: row.avg_gyro_rms !== null ? Number(row.avg_gyro_rms) : null,
      movement_score: row.movement_score !== null ? Number(row.movement_score) : null,
      battery_avg: row.battery_avg !== null ? Number(row.battery_avg) : null,
      location_share: row.location_share !== null ? Number(row.location_share) : null,
      device_hours: row.device_hours !== undefined && row.device_hours !== null ? Number(row.device_hours) : undefined,
      quality_samples: row.quality_samples !== undefined && row.quality_samples !== null ? Number(row.quality_samples) : undefined,
      quality_valid_ratio: row.quality_valid_ratio !== null ? Number(row.quality_valid_ratio) : null,
      quality_pocket_ratio: row.quality_pocket_ratio !== null ? Number(row.quality_pocket_ratio) : null,
    }));

    const lastItem = items[items.length - 1];
    let next_cursor: string | null = null;
    if (lastItem) {
      const cursorTime =
        query.bucket === 'day'
          ? lastItem.day
          : lastItem.window_start ?? lastItem.window_end;
      if (cursorTime) {
        next_cursor = `${cursorTime}|${lastItem.geohash}`;
      }
    }

    return {
      bucket: query.bucket,
      items,
      next_cursor,
    };
  });

  fastify.get('/analytics/coverage', async (request) => {
    const query = coverageQuerySchema.parse(request.query);
    const now = new Date();
    const from = new Date(now.getTime() - query.hours * 60 * 60 * 1000);

    const result = await pool.query(
      `
        SELECT
          geohash,
          SUM(samples_count) AS samples_count,
          SUM(device_count) AS device_events,
          SUM(device_count * ${AGGREGATION_WINDOW_MINUTES}) / 60.0 AS device_hours,
          AVG(avg_light) AS avg_light,
          AVG(avg_accel_rms) AS avg_accel_rms,
          AVG(avg_gyro_rms) AS avg_gyro_rms,
          AVG(movement_score) AS movement_score,
          AVG(location_share) AS location_share,
          SUM(quality_samples) AS quality_samples,
          AVG(quality_valid_ratio) AS quality_valid_ratio,
          AVG(quality_pocket_ratio) AS quality_pocket_ratio
        FROM sensor_aggregates_5m
        WHERE window_start >= $1
        GROUP BY geohash
      `,
      [from.toISOString()],
    );

    const items = result.rows
      .map((row) => ({
        geohash: row.geohash,
        samples_count: Number(row.samples_count),
        device_events: Number(row.device_events),
        device_hours: Number(row.device_hours),
        avg_light: row.avg_light !== null ? Number(row.avg_light) : null,
        avg_accel_rms: row.avg_accel_rms !== null ? Number(row.avg_accel_rms) : null,
        avg_gyro_rms: row.avg_gyro_rms !== null ? Number(row.avg_gyro_rms) : null,
        movement_score: row.movement_score !== null ? Number(row.movement_score) : null,
        location_share: row.location_share !== null ? Number(row.location_share) : null,
        quality_samples: row.quality_samples !== null ? Number(row.quality_samples) : null,
        quality_valid_ratio: row.quality_valid_ratio !== null ? Number(row.quality_valid_ratio) : null,
        quality_pocket_ratio: row.quality_pocket_ratio !== null ? Number(row.quality_pocket_ratio) : null,
      }))
      .filter((item) => item.device_events >= query.min_devices)
      .sort((a, b) => b.device_events - a.device_events);

    return {
      from: from.toISOString(),
      to: now.toISOString(),
      items,
    };
  });

  fastify.get('/analytics/device-stats', async (request) => {
    const query = deviceStatsQuerySchema.parse(request.query);

    const clauses: string[] = [];
    const params: any[] = [];
    let paramIndex = 1;

    if (query.cursor) {
      const [cursorTime, cursorHash] = query.cursor.split('|');
      if (cursorTime && cursorHash) {
        clauses.push(`(COALESCE(last_upload_at, '1970-01-01'), device_hash) < ($${paramIndex++}, $${paramIndex++})`);
        params.push(cursorTime);
        params.push(cursorHash);
      }
    }

    const whereSql = clauses.length ? `WHERE ${clauses.join(' AND ')}` : '';

    const sql = `
      SELECT device_hash, samples_count, valid_samples, pocket_samples, uptime_seconds, last_upload_at
      FROM user_stats
      ${whereSql}
      ORDER BY COALESCE(last_upload_at, '1970-01-01') DESC, device_hash DESC
      LIMIT $${paramIndex}
    `;
    params.push(query.limit);

    const result = await pool.query(sql, params);

    const items = result.rows.map((row) => ({
      device_hash: row.device_hash,
      samples_count: Number(row.samples_count),
      valid_samples: Number(row.valid_samples),
      pocket_samples: Number(row.pocket_samples),
      uptime_seconds: Number(row.uptime_seconds),
      last_upload_at: row.last_upload_at ? row.last_upload_at.toISOString() : null,
    }));

    const last = items[items.length - 1];
    let next_cursor: string | null = null;
    if (last && last.last_upload_at) {
      next_cursor = `${last.last_upload_at}|${last.device_hash}`;
    }

    return {
      items,
      next_cursor,
    };
  });
}
