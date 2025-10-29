import { FastifyInstance, FastifyRequest, FastifyReply } from 'fastify';
import { requireAuth } from '../utils/firebase-auth';
import { getPool } from '../database';
import { UserPreferencesSchema, UserPreferences, UserPreferencesResponse } from '../models/preferences';

interface PreferencesRequestBody {
  theme_mode?: 'light' | 'dark' | 'system';
  use_mobile_data?: boolean;
  share_location?: boolean;
}

export async function preferencesRoutes(fastify: FastifyInstance) {
  // GET /user/preferences
  fastify.get(
    '/user/preferences',
    async (request: FastifyRequest, reply: FastifyReply): Promise<UserPreferencesResponse> => {
      const firebaseUid = await requireAuth(request, reply);

      const pool = getPool();
      const result = await pool.query(
        'SELECT firebase_uid, theme_mode, use_mobile_data, share_location FROM user_preferences WHERE firebase_uid = $1',
        [firebaseUid]
      );

      if (result.rows.length > 0) {
        const row = result.rows[0];
        return {
          firebase_uid: row.firebase_uid,
          theme_mode: row.theme_mode,
          use_mobile_data: row.use_mobile_data,
          share_location: row.share_location,
        };
      }

      // Return defaults if user hasn't set preferences yet
      return {
        firebase_uid: firebaseUid,
        theme_mode: 'system',
        use_mobile_data: false,
        share_location: false,
      };
    }
  );

  // PUT /user/preferences
  fastify.put<{ Body: PreferencesRequestBody }>(
    '/user/preferences',
    async (request: FastifyRequest<{ Body: PreferencesRequestBody }>, reply: FastifyReply): Promise<UserPreferencesResponse> => {
      const firebaseUid = await requireAuth(request, reply);

      // Validate request body
      let preferences: UserPreferences;
      try {
        preferences = UserPreferencesSchema.parse(request.body);
      } catch (error: any) {
        return reply.code(422).send({
          error: 'Unprocessable Entity',
          message: 'Validation failed',
          details: error.issues,
        });
      }

      const pool = getPool();
      await pool.query(
        `INSERT INTO user_preferences (firebase_uid, theme_mode, use_mobile_data, share_location)
         VALUES ($1, $2, $3, $4)
         ON CONFLICT (firebase_uid)
         DO UPDATE SET
           theme_mode = EXCLUDED.theme_mode,
           use_mobile_data = EXCLUDED.use_mobile_data,
           share_location = EXCLUDED.share_location,
           updated_at = NOW()`,
        [firebaseUid, preferences.theme_mode, preferences.use_mobile_data, preferences.share_location]
      );

      console.log(`Updated preferences for user ${firebaseUid}`);

      return {
        firebase_uid: firebaseUid,
        ...preferences,
      };
    }
  );
}
