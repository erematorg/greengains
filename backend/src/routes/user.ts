import { FastifyInstance, FastifyRequest, FastifyReply } from 'fastify';
import { getPool } from '../database';
import { verifyFirebaseToken } from '../utils/firebase-auth';

/**
 * User-specific endpoints (profile, H3 tiles, stats)
 * All endpoints require Firebase authentication
 */
export async function userRoutes(fastify: FastifyInstance) {

  /**
   * GET /api/user/profile
   * Returns user stats aggregated across all their devices
   */
  fastify.get(
    '/api/user/profile',
    async (request: FastifyRequest, reply: FastifyReply) => {
      let userId: string | null = null;

      try {
        userId = await verifyFirebaseToken(request, reply);
        if (reply.sent || !userId) return;
      } catch (e) {
        return reply.code(401).send({ error: 'Unauthorized', message: 'Invalid or missing authentication token' });
      }

      try {
        const pool = getPool();

        // Aggregate stats across all user's devices
        const statsResult = await pool.query(
          `SELECT
            COALESCE(SUM(samples_count), 0)::bigint as total_uploads,
            COALESCE(SUM(valid_samples), 0)::bigint as valid_samples,
            COUNT(DISTINCT device_hash) as device_count,
            MIN(created_at) as first_upload_date,
            MAX(last_upload_at) as last_upload_date
          FROM user_stats
          WHERE user_id = $1`,
          [userId]
        );

        if (statsResult.rows.length === 0 || !statsResult.rows[0].total_uploads) {
          return reply.send({
            uid: userId,
            stats: {
              totalUploads: 0,
              uploadsToday: 0,
              daysActive: 0,
              currentStreak: 0,
              longestStreak: 0,
              areaCovered: 0.0,
              deviceCount: 0,
              firstUploadDate: null,
              lastUploadDate: null,
            },
          });
        }

        const row = statsResult.rows[0];

        // Count uploads today
        const todayResult = await pool.query(
          `SELECT COUNT(*)::int as uploads_today
          FROM sensor_batches
          WHERE user_id = $1
          AND DATE(timestamp_utc) = CURRENT_DATE`,
          [userId]
        );
        const uploadsToday = todayResult.rows[0]?.uploads_today || 0;

        // Calculate days active (distinct upload dates)
        const daysActiveResult = await pool.query(
          `SELECT COUNT(DISTINCT DATE(timestamp_utc))::int as days_active
          FROM sensor_batches
          WHERE user_id = $1`,
          [userId]
        );
        const daysActive = daysActiveResult.rows[0]?.days_active || 0;

        // Calculate streaks (consecutive days with uploads)
        const streakResult = await pool.query(
          `WITH daily_uploads AS (
            SELECT DISTINCT DATE(timestamp_utc) as upload_date
            FROM sensor_batches
            WHERE user_id = $1
            ORDER BY upload_date DESC
          ),
          date_diff AS (
            SELECT
              upload_date,
              upload_date - LAG(upload_date, 1, upload_date) OVER (ORDER BY upload_date DESC) as gap
            FROM daily_uploads
          ),
          streak_groups AS (
            SELECT
              upload_date,
              SUM(CASE WHEN gap < -1 THEN 1 ELSE 0 END) OVER (ORDER BY upload_date DESC) as streak_id
            FROM date_diff
          )
          SELECT
            MAX(streak_length) as longest_streak,
            CASE WHEN MIN(upload_date) = CURRENT_DATE THEN current_streak ELSE 0 END as current_streak
          FROM (
            SELECT
              streak_id,
              COUNT(*) as streak_length,
              MIN(upload_date) as min_date,
              MAX(CASE WHEN upload_date >= CURRENT_DATE THEN COUNT(*) ELSE 0 END) as current_streak
            FROM streak_groups
            GROUP BY streak_id
          ) t`,
          [userId]
        );

        const longestStreak = streakResult.rows[0]?.longest_streak || 0;
        const currentStreak = streakResult.rows[0]?.current_streak || 0;

        // TODO: Calculate area covered from H3 tiles (requires H3 library)
        // For now, return 0.0
        const areaCovered = 0.0;

        return reply.send({
          uid: userId,
          createdAt: row.first_upload_date,
          stats: {
            totalUploads: parseInt(row.total_uploads, 10),
            uploadsToday,
            daysActive,
            currentStreak,
            longestStreak,
            areaCovered,
            deviceCount: parseInt(row.device_count, 10),
            firstUploadDate: row.first_upload_date,
            lastUploadDate: row.last_upload_date,
          },
        });
      } catch (error) {
        console.error('Profile fetch error:', error);
        return reply.code(500).send({ error: 'Internal Server Error' });
      }
    }
  );

  /**
   * GET /api/user/tiles
   * Returns H3 tiles with boundaries for coverage map
   * Query params: ?hours=24 (optional, default 24)
   */
  fastify.get(
    '/api/user/tiles',
    async (request: FastifyRequest, reply: FastifyReply) => {
      let userId: string | null = null;

      try {
        userId = await verifyFirebaseToken(request, reply);
        if (reply.sent || !userId) return;
      } catch (e) {
        return reply.code(401).send({ error: 'Unauthorized', message: 'Invalid or missing authentication token' });
      }

      try {
        const { hours = 24 } = request.query as { hours?: number };
        const pool = getPool();

        // Extract H3 indices and location data from sensor_batches
        // Note: H3 boundary calculation requires h3-js library on backend
        // For now, we'll extract location data and let the client handle H3
        const tilesResult = await pool.query(
          `SELECT
            (batch_json->'location'->>'h3Index') as h3_index,
            (batch_json->'location'->>'lat')::float as lat,
            (batch_json->'location'->>'lon')::float as lon,
            (batch_json->'location'->>'accuracy_m')::float as accuracy_m,
            COUNT(*) as sample_count,
            MAX(timestamp_utc) as last_update,
            AVG((batch_json->'summary'->'light'->>'avg')::float) as avg_light,
            AVG((batch_json->'summary'->>'accel_rms')::float) as avg_accel_rms
          FROM sensor_batches
          WHERE user_id = $1
          AND timestamp_utc > NOW() - ($2 || ' hours')::interval
          AND batch_json->'location'->>'h3Index' IS NOT NULL
          GROUP BY h3_index, lat, lon, accuracy_m
          ORDER BY last_update DESC`,
          [userId, hours]
        );

        // Transform to H3Tile format
        // TODO: Add H3 boundary calculation using h3-js library
        // For now, client will need to compute boundaries or we return centroid only
        const tiles = tilesResult.rows.map(row => ({
          h3Index: row.h3_index,
          centroid: {
            lat: parseFloat(row.lat),
            lng: parseFloat(row.lon),
          },
          confidence: Math.min(1.0, (parseInt(row.sample_count, 10) / 100)), // Simple confidence based on sample count
          sampleCount: parseInt(row.sample_count, 10),
          deviceCount: 1, // Per-user view, so always 1 device per tile
          lastUpdate: row.last_update,
          avgLight: row.avg_light ? parseFloat(row.avg_light) : null,
          avgAccelRms: row.avg_accel_rms ? parseFloat(row.avg_accel_rms) : null,
          // boundary: null, // TODO: Add H3 boundary calculation
        }));

        return reply.send({ tiles });
      } catch (error) {
        console.error('Tiles fetch error:', error);
        return reply.code(500).send({ error: 'Internal Server Error' });
      }
    }
  );
}
