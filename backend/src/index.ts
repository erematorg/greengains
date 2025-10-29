import Fastify from 'fastify';
import cors from '@fastify/cors';
import rateLimit from '@fastify/rate-limit';
import { config, getAllowedOrigins } from './config';
import { initDatabase, closeDatabase } from './database';
import { initFirebase } from './utils/firebase-auth';
import { uploadRoutes } from './routes/upload';
import { preferencesRoutes } from './routes/preferences';

const fastify = Fastify({
  logger: {
    level: config.nodeEnv === 'production' ? 'info' : 'debug',
  },
  bodyLimit: 1048576 * 10, // 10MB limit for uploads
});

// Configure raw body parser for upload endpoint
fastify.addContentTypeParser('application/json', { parseAs: 'buffer' }, (req, body, done) => {
  done(null, body);
});

// Register CORS
fastify.register(cors, {
  origin: getAllowedOrigins(),
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
fastify.register(uploadRoutes);
fastify.register(preferencesRoutes);

// Graceful shutdown
const shutdown = async (signal: string) => {
  console.log(`\nReceived ${signal}, shutting down gracefully...`);
  try {
    await fastify.close();
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

    // Start listening
    await fastify.listen({
      port: config.port,
      host: '0.0.0.0',
    });

    console.log(`
🚀 GreenGains Backend (TypeScript) is running
📍 Server: http://0.0.0.0:${config.port}
🗄️  Database: Connected
🔥 Firebase: ${process.env.GOOGLE_APPLICATION_CREDENTIALS ? 'Enabled' : 'Disabled'}
🌍 Environment: ${config.nodeEnv}
    `);
  } catch (error) {
    console.error('Failed to start server:', error);
    process.exit(1);
  }
};

start();
