import { z } from 'zod';
import * as dotenv from 'dotenv';

dotenv.config();

const configSchema = z.object({
  port: z.coerce.number().default(8000),
  nodeEnv: z.enum(['development', 'production', 'test']).default('development'),

  // Database
  databaseUrl: z.string().min(1, 'DATABASE_URL is required'),

  // API Security
  apiKey: z.string().min(1, 'API_KEY is required'),
  hashSecret: z.string().min(1, 'HASH_SECRET is required'),

  // CORS
  allowedOrigins: z.string().default('*'),

  // Rate Limiting
  rateLimitPerMinute: z.coerce.number().min(1).default(120),
  rateLimitWindowSeconds: z.coerce.number().min(1).default(60),
  rateLimitCacheSize: z.coerce.number().min(10).default(2048),

  // Firebase Admin SDK
  googleApplicationCredentials: z.string().optional(),
});

export type Config = z.infer<typeof configSchema>;

function loadConfig(): Config {
  const rawConfig = {
    port: process.env.PORT,
    nodeEnv: process.env.NODE_ENV,
    databaseUrl: process.env.DATABASE_URL || process.env.GREENGAINS_DATABASE_URL,
    apiKey: process.env.API_KEY || process.env.GREENGAINS_API_KEY,
    hashSecret: process.env.HASH_SECRET || process.env.GREENGAINS_HASH_SECRET,
    allowedOrigins: process.env.ALLOWED_ORIGINS || process.env.GREENGAINS_ALLOWED_ORIGINS,
    rateLimitPerMinute: process.env.RATE_LIMIT_PER_MINUTE || process.env.GREENGAINS_RATE_LIMIT_PER_MINUTE,
    rateLimitWindowSeconds: process.env.GREENGAINS_RATE_LIMIT_WINDOW_SECONDS,
    rateLimitCacheSize: process.env.GREENGAINS_RATE_LIMIT_IDENTIFIER_CACHE_SIZE,
    googleApplicationCredentials: process.env.GOOGLE_APPLICATION_CREDENTIALS,
  };

  const result = configSchema.safeParse(rawConfig);

  if (!result.success) {
    console.error('Configuration validation failed:');
    console.error(result.error.format());
    process.exit(1);
  }

  return result.data;
}

export const config = loadConfig();

export function getAllowedOrigins(): string[] {
  if (config.allowedOrigins === '*') {
    return ['*'];
  }
  return config.allowedOrigins
    .split(',')
    .map(origin => origin.trim())
    .filter(origin => origin.length > 0);
}
