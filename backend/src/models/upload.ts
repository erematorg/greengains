import { z } from 'zod';

export const LocationDataSchema = z.object({
  lat: z.number().min(-90).max(90),
  lon: z.number().min(-180).max(180),
  altitude: z.number().optional(),
  accuracy_m: z.number(),
  speed_mps: z.number().optional(),
  bearing_deg: z.number().optional(),
});

export const SensorReadingSchema = z.object({
  t: z.coerce.date(),
  light: z.number(),
  accel: z.array(z.number()).length(3).optional(),
  gyro: z.array(z.number()).length(3).optional(),
  magnet: z.array(z.number()).length(3).optional(),
});

export const UploadBatchSchema = z.object({
  device_id: z.string().min(1).max(128),
  timestamp: z.coerce.date(),
  batch: z.array(SensorReadingSchema).min(1),
  location: LocationDataSchema.optional(),
  geohash: z.string().max(12).optional(),
  battery_level: z.number().min(-1).max(100).optional(),
  is_charging: z.boolean().optional(),
});

export type LocationData = z.infer<typeof LocationDataSchema>;
export type SensorReading = z.infer<typeof SensorReadingSchema>;
export type UploadBatch = z.infer<typeof UploadBatchSchema>;
