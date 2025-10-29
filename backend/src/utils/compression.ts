import { gunzipSync } from 'zlib';

export class UnsupportedEncodingError extends Error {
  constructor(message: string) {
    super(message);
    this.name = 'UnsupportedEncodingError';
  }
}

export function decompressPayload(contentEncoding: string | undefined, payload: Buffer): Buffer {
  if (!payload || payload.length === 0) {
    return payload;
  }

  if (!contentEncoding) {
    return payload;
  }

  const normalized = contentEncoding.toLowerCase();

  if (normalized === 'gzip') {
    try {
      return gunzipSync(payload);
    } catch (error) {
      throw new Error('Invalid gzip payload');
    }
  }

  if (normalized === 'br' || normalized === 'brotli') {
    throw new UnsupportedEncodingError('Brotli support not implemented');
  }

  if (normalized !== 'identity' && normalized !== 'none') {
    throw new UnsupportedEncodingError(`Unsupported content encoding: ${contentEncoding}`);
  }

  return payload;
}
