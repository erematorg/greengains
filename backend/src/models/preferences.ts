import { z } from 'zod';

export const UserPreferencesSchema = z.object({
  theme_mode: z.enum(['light', 'dark', 'system']).default('system'),
  use_mobile_data: z.boolean().default(false),
  share_location: z.boolean().default(false),
});

export const UserPreferencesResponseSchema = UserPreferencesSchema.extend({
  firebase_uid: z.string(),
});

export type UserPreferences = z.infer<typeof UserPreferencesSchema>;
export type UserPreferencesResponse = z.infer<typeof UserPreferencesResponseSchema>;
