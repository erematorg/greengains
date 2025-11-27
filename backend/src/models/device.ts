import { z } from 'zod';

export const DeviceRegistrationSchema = z.object({
    device_id: z.string().min(1).max(128),
    firebase_token: z.string().min(1),
});

export type DeviceRegistration = z.infer<typeof DeviceRegistrationSchema>;

export const DeviceSecretResponseSchema = z.object({
    device_secret: z.string().min(1),
});

export type DeviceSecretResponse = z.infer<typeof DeviceSecretResponseSchema>;
