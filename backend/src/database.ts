import { Pool, PoolClient } from 'pg';
import { config } from './config';

let pool: Pool | null = null;

export async function initDatabase(): Promise<void> {
  if (pool) return;

  pool = new Pool({
    connectionString: config.databaseUrl,
    min: 1,
    max: 5,
    idleTimeoutMillis: 30000,
    connectionTimeoutMillis: 10000,
    keepAlive: true,
    keepAliveInitialDelayMillis: 10000,
    ssl: {
      rejectUnauthorized: false, // Supabase pooler requires SSL
    },
  });

  // Handle pool errors
  pool.on('error', (err) => {
    console.error('Unexpected pool error:', err);
  });

  // Test connection
  try {
    const client = await pool.connect();
    console.log('✅ Database connected successfully');
    client.release();
  } catch (error) {
    console.error('❌ Database connection failed:', error);
    throw error;
  }
}

export async function closeDatabase(): Promise<void> {
  if (pool) {
    await pool.end();
    pool = null;
    console.log('Database pool closed');
  }
}

export function getPool(): Pool {
  if (!pool) {
    throw new Error('Database pool not initialized. Call initDatabase() first.');
  }
  return pool;
}

export async function getClient(): Promise<PoolClient> {
  const pool = getPool();
  return await pool.connect();
}

// Helper for running queries
export async function query<T = any>(text: string, params?: any[]): Promise<T[]> {
  const pool = getPool();
  const result = await pool.query(text, params);
  return result.rows;
}
