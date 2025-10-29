import { createHmac, timingSafeEqual } from 'crypto';
import { FastifyRequest, FastifyReply } from 'fastify';
import { config } from '../config';

export function hashDeviceId(deviceId: string): string {
  const hmac = createHmac('sha256', config.hashSecret);
  hmac.update(deviceId);
  return hmac.digest('hex');
}

export async function verifyApiKey(
  request: FastifyRequest,
  reply: FastifyReply
): Promise<void> {
  const apiKey = request.headers['x-api-key'] as string | undefined;

  if (!apiKey) {
    reply.code(401).send({
      error: 'Unauthorized',
      message: 'Missing API key',
    });
    throw new Error('Missing API key');
  }

  // Timing-safe comparison to prevent timing attacks
  const expectedKey = Buffer.from(config.apiKey);
  const providedKey = Buffer.from(apiKey);

  if (expectedKey.length !== providedKey.length || !timingSafeEqual(expectedKey, providedKey)) {
    reply.code(401).send({
      error: 'Unauthorized',
      message: 'Invalid API key',
    });
    throw new Error('Invalid API key');
  }
}
