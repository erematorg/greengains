import * as admin from 'firebase-admin';
import { FastifyRequest, FastifyReply } from 'fastify';
import { config } from '../config';

let app: admin.app.App | null = null;

export function initFirebase(): void {
  if (app) return;

  try {
    // Read Firebase credentials from environment variable (JSON string)
    if (config.firebaseServiceAccount) {
      const serviceAccount = JSON.parse(config.firebaseServiceAccount);

      // Fix newlines in private_key if they're escaped
      if (serviceAccount.private_key && typeof serviceAccount.private_key === 'string') {
        serviceAccount.private_key = serviceAccount.private_key.replace(/\\n/g, '\n');
      }

      app = admin.initializeApp({
        credential: admin.credential.cert(serviceAccount),
      });
    } else {
      // Fallback to GOOGLE_APPLICATION_CREDENTIALS file path if available
      app = admin.initializeApp({
        credential: admin.credential.applicationDefault(),
      });
    }
    console.log('✅ Firebase Admin SDK initialized successfully');
  } catch (error) {
    console.warn('⚠️  Firebase Admin SDK initialization failed:', error);
    console.warn('⚠️  User authentication will not be available');
  }
}

export async function verifyFirebaseToken(
  request: FastifyRequest,
  reply: FastifyReply
): Promise<string | null> {
  const authorization = request.headers.authorization;

  if (!authorization) {
    return null;
  }

  if (!authorization.startsWith('Bearer ')) {
    reply.code(401).send({
      error: 'Unauthorized',
      message: "Invalid authorization header format. Expected 'Bearer <token>'",
    });
    throw new Error('Invalid authorization header');
  }

  const token = authorization.substring(7); // Remove "Bearer " prefix

  try {
    const decodedToken = await admin.auth().verifyIdToken(token);
    return decodedToken.uid;
  } catch (error: any) {
    if (error.code === 'auth/id-token-expired') {
      reply.code(401).send({
        error: 'Unauthorized',
        message: 'Firebase token expired',
      });
    } else if (error.code === 'auth/argument-error') {
      reply.code(401).send({
        error: 'Unauthorized',
        message: 'Invalid Firebase token',
      });
    } else {
      console.error('Firebase token verification failed:', error);
      reply.code(401).send({
        error: 'Unauthorized',
        message: 'Token verification failed',
      });
    }
    throw error;
  }
}

export async function requireAuth(
  request: FastifyRequest,
  reply: FastifyReply
): Promise<string> {
  const uid = await verifyFirebaseToken(request, reply);

  if (!uid) {
    reply.code(401).send({
      error: 'Unauthorized',
      message: 'Authentication required',
    });
    throw new Error('Authentication required');
  }

  return uid;
}
