import * as fs from 'fs';
import * as path from 'path';
import * as dotenv from 'dotenv';

// Load env vars from root .env
const envPath = path.resolve(__dirname, '../../.env');
console.log('Loading .env from:', envPath);
console.log('File exists:', fs.existsSync(envPath));

const result = dotenv.config({ path: envPath });
if (result.error) {
    console.error('Error loading .env:', result.error);
} else {
    console.log('Dotenv loaded successfully');
}

// Check if vars are loaded
console.log('DATABASE_URL present:', !!process.env.DATABASE_URL);

async function runMigration() {
    // Dynamically import database to ensure config is loaded AFTER dotenv
    const { getPool } = await import('../src/database');

    console.log('Starting migration...');
    const pool = getPool();

    try {
        const sqlPath = path.join(__dirname, '../db/20251127_add_device_secrets.sql');
        const sql = fs.readFileSync(sqlPath, 'utf-8');

        console.log('Executing SQL:', sql);
        await pool.query(sql);
        console.log('Migration completed successfully.');
    } catch (error) {
        console.error('Migration failed:', error);
    } finally {
        await pool.end();
    }
}

runMigration();
