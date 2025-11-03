import { PoolClient } from 'pg';
import { getPool } from '../database';

export const AGGREGATION_WINDOW_MINUTES = 5;
const WINDOW_MS = AGGREGATION_WINDOW_MINUTES * 60 * 1000;
const JOB_INTERVAL_MS = 60 * 1000; // run once a minute

type WindowKey = string;
type DayKey = string;

interface WindowAccumulator {
  windowStart: Date;
  windowEnd: Date;
  geohash: string;
  samples: number;
  deviceIds: Set<string>;
  lightSum: number;
  lightMin: number;
  lightMax: number;
  accelRmsSum: number;
  gyroRmsSum: number;
  batterySum: number;
  batterySamples: number;
  locationSamples: number;
}

interface DayAccumulator {
  day: Date;
  geohash: string;
  samples: number;
  deviceIds: Set<string>;
  lightSum: number;
  lightMin: number;
  lightMax: number;
  accelRmsSum: number;
  gyroRmsSum: number;
  batterySum: number;
  batterySamples: number;
  locationSamples: number;
  deviceActiveMinutes: number;
}

let aggregationTimer: NodeJS.Timeout | null = null;

export async function startAggregationJob(): Promise<void> {
  // Run once immediately, then schedule interval
  await runAggregationJob().catch((error) =>
    console.error('[aggregation] initial run failed:', error),
  );
  aggregationTimer = setInterval(() => {
    runAggregationJob().catch((error) =>
      console.error('[aggregation] scheduled run failed:', error),
    );
  }, JOB_INTERVAL_MS);
}

export async function stopAggregationJob(): Promise<void> {
  if (aggregationTimer) {
    clearInterval(aggregationTimer);
    aggregationTimer = null;
  }
}

function truncateToWindow(date: Date): Date {
  const ms = date.getTime();
  return new Date(Math.floor(ms / WINDOW_MS) * WINDOW_MS);
}

function nextWindowStart(date: Date): Date {
  return new Date(date.getTime() + WINDOW_MS);
}

function dayStartUtc(date: Date): Date {
  return new Date(Date.UTC(date.getUTCFullYear(), date.getUTCMonth(), date.getUTCDate()));
}

export async function runAggregationJob(): Promise<void> {
  const pool = getPool();

  const existingMaxRes = await pool.query<{ max: Date | null }>(
    'SELECT MAX(window_end) AS max FROM sensor_aggregates_5m',
  );

  let fromWindowEnd: Date | null = existingMaxRes.rows[0]?.max ?? null;
  if (!fromWindowEnd) {
    // No aggregates yet; start from earliest batch
    const minRes = await pool.query<{ min: Date | null }>(
      'SELECT MIN(timestamp_utc) AS min FROM sensor_batches',
    );
    if (!minRes.rows[0]?.min) {
      return; // nothing to aggregate
    }
    const minDate = truncateToWindow(new Date(minRes.rows[0].min));
    fromWindowEnd = nextWindowStart(minDate);
  }

  // process only fully elapsed windows
  const upToExclusive = truncateToWindow(new Date());
  if (!fromWindowEnd || fromWindowEnd >= upToExclusive) {
    return; // nothing new
  }

  const rows = await pool.query<{
    device_hash: string;
    timestamp_utc: Date;
    batch_json: any;
  }>(
    `SELECT device_hash, timestamp_utc, batch_json
     FROM sensor_batches
     WHERE timestamp_utc > $1 AND timestamp_utc <= $2
     ORDER BY timestamp_utc ASC`,
    [fromWindowEnd.toISOString(), upToExclusive.toISOString()],
  );

  if (rows.rowCount === 0) {
    return;
  }

  const windowBuckets = new Map<WindowKey, WindowAccumulator>();
  const dayBuckets = new Map<DayKey, DayAccumulator>();

  for (const row of rows.rows) {
    const payload = typeof row.batch_json === 'string' ? JSON.parse(row.batch_json) : row.batch_json;
    const geohash: string | undefined = payload?.geohash;
    if (!geohash) {
      continue;
    }

    const summary = payload?.summary;
    const readingsCount: number =
      typeof summary?.count === 'number'
        ? summary.count
        : Array.isArray(payload?.batch)
        ? payload.batch.length
        : 0;
    if (readingsCount <= 0) {
      continue;
    }

    const periodEnd = summary?.period_end ? new Date(summary.period_end) : new Date(row.timestamp_utc);
    const windowStart = truncateToWindow(periodEnd);
    if (windowStart >= upToExclusive) {
      continue; // skip partial current window
    }
    const windowEnd = nextWindowStart(windowStart);
    const windowKey = `${windowStart.toISOString()}|${geohash}`;

    let windowAcc = windowBuckets.get(windowKey);
    if (!windowAcc) {
      windowAcc = {
        windowStart,
        windowEnd,
        geohash,
        samples: 0,
        deviceIds: new Set<string>(),
        lightSum: 0,
        lightMin: Number.POSITIVE_INFINITY,
        lightMax: Number.NEGATIVE_INFINITY,
        accelRmsSum: 0,
        gyroRmsSum: 0,
        batterySum: 0,
        batterySamples: 0,
        locationSamples: 0,
      };
      windowBuckets.set(windowKey, windowAcc);
    }

    windowAcc.samples += readingsCount;
    windowAcc.deviceIds.add(row.device_hash);

    const lightAvg = summary?.light?.avg;
    const lightMin = summary?.light?.min;
    const lightMax = summary?.light?.max;
    if (typeof lightAvg === 'number') {
      windowAcc.lightSum += lightAvg * readingsCount;
    }
    if (typeof lightMin === 'number') {
      windowAcc.lightMin = Math.min(windowAcc.lightMin, lightMin);
    }
    if (typeof lightMax === 'number') {
      windowAcc.lightMax = Math.max(windowAcc.lightMax, lightMax);
    }

    const accelRms = summary?.accel_rms;
    if (typeof accelRms === 'number') {
      windowAcc.accelRmsSum += accelRms * readingsCount;
    }

    const gyroRms = summary?.gyro_rms;
    if (typeof gyroRms === 'number') {
      windowAcc.gyroRmsSum += gyroRms * readingsCount;
    }

    const batteryLevel = payload?.battery_level;
    if (typeof batteryLevel === 'number' && batteryLevel >= 0) {
      windowAcc.batterySum += batteryLevel;
      windowAcc.batterySamples += 1;
    }

    if (payload?.location != null) {
      windowAcc.locationSamples += readingsCount;
    }
  }

  if (windowBuckets.size === 0) {
    return;
  }

  const windowResults = [];
  for (const bucket of windowBuckets.values()) {
    const samples = bucket.samples;
    if (samples === 0) {
      continue;
    }
    const deviceCount = bucket.deviceIds.size;
    const avgLight =
      samples > 0 && isFinite(bucket.lightSum) ? bucket.lightSum / samples : null;
    const lightMin = bucket.lightMin === Number.POSITIVE_INFINITY ? null : bucket.lightMin;
    const lightMax = bucket.lightMax === Number.NEGATIVE_INFINITY ? null : bucket.lightMax;
    const avgAccelRms = bucket.accelRmsSum / samples;
    const avgGyroRms = bucket.gyroRmsSum / samples;
    const movementScore = Math.min(1, Math.max(0, avgAccelRms / 9.81));
    const batteryAvg =
      bucket.batterySamples > 0 ? bucket.batterySum / bucket.batterySamples : null;
    const locationShare = samples > 0 ? bucket.locationSamples / samples : 0;

    windowResults.push({
      windowStart: bucket.windowStart,
      windowEnd: bucket.windowEnd,
      geohash: bucket.geohash,
      samplesCount: samples,
      deviceCount,
      avgLight,
      lightMin,
      lightMax,
      avgAccelRms,
      avgGyroRms,
      movementScore,
      batteryAvg,
      locationShare,
    });

    const dayKey = buildDayKey(bucket.windowStart, bucket.geohash);
    let dayAcc = dayBuckets.get(dayKey);
    if (!dayAcc) {
      const dayStart = dayStartUtc(bucket.windowStart);
      dayAcc = {
        day: dayStart,
        geohash: bucket.geohash,
        samples: 0,
        deviceIds: new Set<string>(),
        lightSum: 0,
        lightMin: Number.POSITIVE_INFINITY,
        lightMax: Number.NEGATIVE_INFINITY,
        accelRmsSum: 0,
        gyroRmsSum: 0,
        batterySum: 0,
        batterySamples: 0,
        locationSamples: 0,
        deviceActiveMinutes: 0,
      };
      dayBuckets.set(dayKey, dayAcc);
    }

    dayAcc.samples += samples;
    dayAcc.deviceIds = mergeSets(dayAcc.deviceIds, bucket.deviceIds);
    dayAcc.lightSum += bucket.lightSum;
    if (bucket.lightMin !== Number.POSITIVE_INFINITY) {
      dayAcc.lightMin = Math.min(dayAcc.lightMin, bucket.lightMin);
    }
    if (bucket.lightMax !== Number.NEGATIVE_INFINITY) {
      dayAcc.lightMax = Math.max(dayAcc.lightMax, bucket.lightMax);
    }
    dayAcc.accelRmsSum += bucket.accelRmsSum;
    dayAcc.gyroRmsSum += bucket.gyroRmsSum;
    dayAcc.batterySum += bucket.batterySum;
    dayAcc.batterySamples += bucket.batterySamples;
    dayAcc.locationSamples += bucket.locationSamples;
    dayAcc.deviceActiveMinutes += deviceCount * AGGREGATION_WINDOW_MINUTES;
  }

  const client = await pool.connect();
  try {
    await client.query('BEGIN');
    await upsertWindowResults(client, windowResults);
    await upsertDailyResults(client, dayBuckets);
    await client.query('COMMIT');
  } catch (error) {
    await client.query('ROLLBACK');
    throw error;
  } finally {
    client.release();
  }
}

function mergeSets<T>(target: Set<T>, source: Set<T>): Set<T> {
  for (const value of source) {
    target.add(value);
  }
  return target;
}

function buildDayKey(windowStart: Date, geohash: string): DayKey {
  const day = dayStartUtc(windowStart);
  return `${day.toISOString()}|${geohash}`;
}

async function upsertWindowResults(
  client: PoolClient,
  results: Array<{
    windowStart: Date;
    windowEnd: Date;
    geohash: string;
    samplesCount: number;
    deviceCount: number;
    avgLight: number | null;
    lightMin: number | null;
    lightMax: number | null;
    avgAccelRms: number;
    avgGyroRms: number;
    movementScore: number;
    batteryAvg: number | null;
    locationShare: number;
  }>,
): Promise<void> {
  for (const row of results) {
    await client.query(
      `INSERT INTO sensor_aggregates_5m (
          window_start,
          window_end,
          geohash,
          samples_count,
          device_count,
          avg_light,
          avg_light_min,
          avg_light_max,
          avg_accel_rms,
          avg_gyro_rms,
          movement_score,
          battery_avg,
          location_share,
          created_at,
          updated_at
        )
        VALUES ($1,$2,$3,$4,$5,$6,$7,$8,$9,$10,$11,$12,$13,NOW(),NOW())
        ON CONFLICT (window_start, geohash)
        DO UPDATE SET
          samples_count = EXCLUDED.samples_count,
          device_count = EXCLUDED.device_count,
          avg_light = EXCLUDED.avg_light,
          avg_light_min = EXCLUDED.avg_light_min,
          avg_light_max = EXCLUDED.avg_light_max,
          avg_accel_rms = EXCLUDED.avg_accel_rms,
          avg_gyro_rms = EXCLUDED.avg_gyro_rms,
          movement_score = EXCLUDED.movement_score,
          battery_avg = EXCLUDED.battery_avg,
          location_share = EXCLUDED.location_share,
          updated_at = NOW()`,
      [
        row.windowStart.toISOString(),
        row.windowEnd.toISOString(),
        row.geohash,
        row.samplesCount,
        row.deviceCount,
        row.avgLight,
        row.lightMin,
        row.lightMax,
        row.avgAccelRms,
        row.avgGyroRms,
        row.movementScore,
        row.batteryAvg,
        row.locationShare,
      ],
    );
  }
}

async function upsertDailyResults(
  client: PoolClient,
  dayBuckets: Map<
    DayKey,
    {
      day: Date;
      geohash: string;
      samples: number;
      deviceIds: Set<string>;
      lightSum: number;
      lightMin: number;
      lightMax: number;
      accelRmsSum: number;
      gyroRmsSum: number;
      batterySum: number;
      batterySamples: number;
      locationSamples: number;
      deviceActiveMinutes: number;
    }
  >,
): Promise<void> {
  for (const bucket of dayBuckets.values()) {
    const samples = bucket.samples;
    if (samples === 0) {
      continue;
    }
    const deviceCount = bucket.deviceIds.size;
    const avgLight =
      samples > 0 && isFinite(bucket.lightSum) ? bucket.lightSum / samples : null;
    const lightMin =
      bucket.lightMin === Number.POSITIVE_INFINITY ? null : bucket.lightMin;
    const lightMax =
      bucket.lightMax === Number.NEGATIVE_INFINITY ? null : bucket.lightMax;
    const avgAccelRms = bucket.accelRmsSum / samples;
    const avgGyroRms = bucket.gyroRmsSum / samples;
    const movementScore = Math.min(1, Math.max(0, avgAccelRms / 9.81));
    const batteryAvg =
      bucket.batterySamples > 0 ? bucket.batterySum / bucket.batterySamples : null;
    const locationShare = samples > 0 ? bucket.locationSamples / samples : 0;
    const deviceHours = bucket.deviceActiveMinutes / 60;

    await client.query(
      `INSERT INTO sensor_aggregates_daily (
          day,
          geohash,
          samples_count,
          device_count,
          avg_light,
          avg_light_min,
          avg_light_max,
          avg_accel_rms,
          avg_gyro_rms,
          movement_score,
          battery_avg,
          location_share,
          device_hours,
          created_at,
          updated_at
        )
        VALUES ($1,$2,$3,$4,$5,$6,$7,$8,$9,$10,$11,$12,$13,NOW(),NOW())
        ON CONFLICT (day, geohash)
        DO UPDATE SET
          samples_count = EXCLUDED.samples_count,
          device_count = EXCLUDED.device_count,
          avg_light = EXCLUDED.avg_light,
          avg_light_min = EXCLUDED.avg_light_min,
          avg_light_max = EXCLUDED.avg_light_max,
          avg_accel_rms = EXCLUDED.avg_accel_rms,
          avg_gyro_rms = EXCLUDED.avg_gyro_rms,
          movement_score = EXCLUDED.movement_score,
          battery_avg = EXCLUDED.battery_avg,
          location_share = EXCLUDED.location_share,
          device_hours = EXCLUDED.device_hours,
          updated_at = NOW()`,
      [
        bucket.day.toISOString().slice(0, 10),
        bucket.geohash,
        samples,
        deviceCount,
        avgLight,
        lightMin,
        lightMax,
        avgAccelRms,
        avgGyroRms,
        movementScore,
        batteryAvg,
        locationShare,
        deviceHours,
      ],
    );
  }
}
