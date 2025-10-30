import * as admin from 'firebase-admin';
import { FastifyRequest, FastifyReply } from 'fastify';
import { config } from '../config';

let app: admin.app.App | null = null;
let isInitialized = false;

export function isFirebaseInitialized(): boolean {
  return isInitialized;
}

export function initFirebase(): void {
  if (app) return;

  try {
    // Read Firebase credentials from environment variable (JSON string)
    if (config.firebaseServiceAccount) {
      const serviceAccount = JSON.parse(config.firebaseServiceAccount);

      // Normalize private_key format - ensure proper line breaks
      if (serviceAccount.private_key && typeof serviceAccount.private_key === 'string') {
        let key = serviceAccount.private_key;

        // If the key has literal \n strings, replace them with actual newlines
        if (key.includes('\\n')) {
          key = key.replace(/\\n/g, '\n');
        }

        // Ensure the key starts and ends correctly
        key = key.trim();
        if (!key.startsWith('-----BEGIN PRIVATE KEY-----')) {
          throw new Error('Invalid private key format: missing BEGIN marker');
        }
        if (!key.endsWith('-----END PRIVATE KEY-----')) {
          throw new Error('Invalid private key format: missing END marker');
        }

        serviceAccount.private_key = key;
      }

      app = admin.initializeApp({
        credential: admin.credential.cert(serviceAccount),
        projectId: serviceAccount.project_id,
      });
    } else {
      // Fallback to GOOGLE_APPLICATION_CREDENTIALS file path if available
      app = admin.initializeApp({
        credential: admin.credential.applicationDefault(),
      });
    }
    console.log('✅ Firebase Admin SDK initialized successfully');
    isInitialized = true;
  } catch (error) {
    console.warn('⚠️  Firebase Admin SDK initialization failed:', error);
    console.warn('⚠️  User authentication will not be available');
    isInitialized = false;
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
