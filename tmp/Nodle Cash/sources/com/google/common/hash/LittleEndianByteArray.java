package com.google.common.hash;

import com.google.common.base.Ascii;
import com.google.common.primitives.Longs;
import java.nio.ByteOrder;
import sun.misc.Unsafe;

@ElementTypesAreNonnullByDefault
final class LittleEndianByteArray {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final LittleEndianBytes byteArray;

    public enum JavaLittleEndianBytes implements LittleEndianBytes {
        INSTANCE {
            public long getLongLittleEndian(byte[] bArr, int i3) {
                return Longs.fromBytes(bArr[i3 + 7], bArr[i3 + 6], bArr[i3 + 5], bArr[i3 + 4], bArr[i3 + 3], bArr[i3 + 2], bArr[i3 + 1], bArr[i3]);
            }

            public void putLongLittleEndian(byte[] bArr, int i3, long j2) {
                long j3 = 255;
                for (int i4 = 0; i4 < 8; i4++) {
                    bArr[i3 + i4] = (byte) ((int) ((j2 & j3) >> (i4 * 8)));
                    j3 <<= 8;
                }
            }
        }
    }

    public interface LittleEndianBytes {
        long getLongLittleEndian(byte[] bArr, int i3);

        void putLongLittleEndian(byte[] bArr, int i3, long j2);
    }

    public enum UnsafeByteArray implements LittleEndianBytes {
        UNSAFE_LITTLE_ENDIAN {
            public long getLongLittleEndian(byte[] bArr, int i3) {
                return UnsafeByteArray.theUnsafe.getLong(bArr, ((long) i3) + ((long) UnsafeByteArray.BYTE_ARRAY_BASE_OFFSET));
            }

            public void putLongLittleEndian(byte[] bArr, int i3, long j2) {
                UnsafeByteArray.theUnsafe.putLong(bArr, ((long) i3) + ((long) UnsafeByteArray.BYTE_ARRAY_BASE_OFFSET), j2);
            }
        },
        UNSAFE_BIG_ENDIAN {
            public long getLongLittleEndian(byte[] bArr, int i3) {
                return Long.reverseBytes(UnsafeByteArray.theUnsafe.getLong(bArr, ((long) i3) + ((long) UnsafeByteArray.BYTE_ARRAY_BASE_OFFSET)));
            }

            public void putLongLittleEndian(byte[] bArr, int i3, long j2) {
                long reverseBytes = Long.reverseBytes(j2);
                UnsafeByteArray.theUnsafe.putLong(bArr, ((long) i3) + ((long) UnsafeByteArray.BYTE_ARRAY_BASE_OFFSET), reverseBytes);
            }
        };
        
        /* access modifiers changed from: private */
        public static final int BYTE_ARRAY_BASE_OFFSET = 0;
        /* access modifiers changed from: private */
        public static final Unsafe theUnsafe = null;

        static {
            Unsafe unsafe = getUnsafe();
            theUnsafe = unsafe;
            Class<byte[]> cls = byte[].class;
            BYTE_ARRAY_BASE_OFFSET = unsafe.arrayBaseOffset(cls);
            if (unsafe.arrayIndexScale(cls) != 1) {
                throw new AssertionError();
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:5:0x0010, code lost:
            return (sun.misc.Unsafe) java.security.AccessController.doPrivileged(new com.google.common.hash.a());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:6:0x0011, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x001d, code lost:
            throw new java.lang.RuntimeException("Could not initialize intrinsics", r0.getCause());
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0005 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static sun.misc.Unsafe getUnsafe() {
            /*
                sun.misc.Unsafe r0 = sun.misc.Unsafe.getUnsafe()     // Catch:{ SecurityException -> 0x0005 }
                return r0
            L_0x0005:
                com.google.common.hash.a r0 = new com.google.common.hash.a     // Catch:{ PrivilegedActionException -> 0x0011 }
                r0.<init>()     // Catch:{ PrivilegedActionException -> 0x0011 }
                java.lang.Object r0 = java.security.AccessController.doPrivileged(r0)     // Catch:{ PrivilegedActionException -> 0x0011 }
                sun.misc.Unsafe r0 = (sun.misc.Unsafe) r0     // Catch:{ PrivilegedActionException -> 0x0011 }
                return r0
            L_0x0011:
                r0 = move-exception
                java.lang.RuntimeException r1 = new java.lang.RuntimeException
                java.lang.String r2 = "Could not initialize intrinsics"
                java.lang.Throwable r0 = r0.getCause()
                r1.<init>(r2, r0)
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.hash.LittleEndianByteArray.UnsafeByteArray.getUnsafe():sun.misc.Unsafe");
        }
    }

    static {
        LittleEndianBytes littleEndianBytes = JavaLittleEndianBytes.INSTANCE;
        try {
            if ("amd64".equals(System.getProperty("os.arch"))) {
                littleEndianBytes = ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN) ? UnsafeByteArray.UNSAFE_LITTLE_ENDIAN : UnsafeByteArray.UNSAFE_BIG_ENDIAN;
            }
        } catch (Throwable unused) {
        }
        byteArray = littleEndianBytes;
    }

    private LittleEndianByteArray() {
    }

    public static int load32(byte[] bArr, int i3) {
        return ((bArr[i3 + 3] & 255) << Ascii.CAN) | (bArr[i3] & 255) | ((bArr[i3 + 1] & 255) << 8) | ((bArr[i3 + 2] & 255) << 16);
    }

    public static long load64(byte[] bArr, int i3) {
        return byteArray.getLongLittleEndian(bArr, i3);
    }

    public static long load64Safely(byte[] bArr, int i3, int i4) {
        int min = Math.min(i4, 8);
        long j2 = 0;
        for (int i5 = 0; i5 < min; i5++) {
            j2 |= (((long) bArr[i3 + i5]) & 255) << (i5 * 8);
        }
        return j2;
    }

    public static void store64(byte[] bArr, int i3, long j2) {
        byteArray.putLongLittleEndian(bArr, i3, j2);
    }

    public static boolean usingUnsafe() {
        return byteArray instanceof UnsafeByteArray;
    }
}
