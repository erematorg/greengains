import Fastify from 'fastify';
import cors from '@fastify/cors';
import rateLimit from '@fastify/rate-limit';
import { config, getAllowedOrigins } from './config';
import { initDatabase, closeDatabase } from './database';
import { initFirebase, isFirebaseInitialized } from './utils/firebase-auth';
import { deviceRoutes } from './routes/device';
import { uploadRoutes } from './routes/upload';
import { preferencesRoutes } from './routes/preferences';
import { analyticsRoutes } from './routes/analytics';
import { startAggregationJob, stopAggregationJob } from './jobs/aggregator';

const fastify = Fastify({
  logger: {
    level: config.nodeEnv === 'production' ? 'info' : 'debug',
  },
  bodyLimit: 1048576 * 10, // 10MB limit for uploads
});

// Configure raw body parser for upload endpoint - MOVED to upload.ts
// fastify.addContentTypeParser... removed

// Register CORS
fastify.register(cors, {
  origin: (() => {
    const origins = getAllowedOrigins();
    return origins.length > 0 ? origins : false; // false = disable CORS (same-origin only)
  })(),
  credentials: true,
});

// Register rate limiting
fastify.register(rateLimit, {
  max: config.rateLimitPerMinute,
  timeWindow: config.rateLimitWindowSeconds * 1000, // Convert to milliseconds
  cache: config.rateLimitCacheSize,
});

// Health check endpoint
fastify.get('/health', async () => {
  return { status: 'ok', timestamp: new Date().toISOString() };
});

// Register routes
fastify.register(deviceRoutes);
fastify.register(uploadRoutes);
fastify.register(preferencesRoutes);
fastify.register(analyticsRoutes);

// Graceful shutdown
const shutdown = async (signal: string) => {
  console.log(`\nReceived ${signal}, shutting down gracefully...`);
  try {
    await fastify.close();
    await stopAggregationJob();
    await closeDatabase();
    console.log('Server shut down successfully');
    process.exit(0);
  } catch (error) {
    console.error('Error during shutdown:', error);
    process.exit(1);
  }
};

process.on('SIGTERM', () => shutdown('SIGTERM'));
process.on('SIGINT', () => shutdown('SIGINT'));

// Start server
const start = async () => {
  try {
    // Initialize database
    await initDatabase();

    // Initialize Firebase
    initFirebase();

    // Start aggregation job
    startAggregationJob().catch((error) =>
      fastify.log.error({ err: error }, 'Failed to start aggregation job'),
    );

    // Start listening
    await fastify.listen({
      port: config.port,
      host: '0.0.0.0',
    });

    console.log('--------------------------------------------');
    console.log('GreenGains backend is running');
    console.log(`Server:      http://0.0.0.0:${config.port}`);
    console.log('Database:    Connected');
    console.log(`Firebase:    ${isFirebaseInitialized() ? 'Enabled' : 'Disabled'}`);
    console.log(`Environment: ${config.nodeEnv}`);
    console.log('--------------------------------------------');
  } catch (error) {
    console.error('Failed to start server:', error);
    process.exit(1);
  }
};

start();
