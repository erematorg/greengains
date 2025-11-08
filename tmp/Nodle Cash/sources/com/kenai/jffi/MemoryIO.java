package com.kenai.jffi;

import androidx.browser.trusted.c;
import androidx.collection.SieveCacheKt;
import com.kenai.jffi.UnsafeMemoryIO;
import java.lang.reflect.Method;
import java.nio.Buffer;
import java.nio.ByteBuffer;

public abstract class MemoryIO {
    static final long ADDRESS_MASK = Platform.getPlatform().addressMask();
    final Foreign foreign = Foreign.getInstance();

    public static final class CheckedMemorySingletonHolder {
        /* access modifiers changed from: private */
        public static final MemoryIO INSTANCE = MemoryIO.newNativeCheckedImpl();

        private CheckedMemorySingletonHolder() {
        }
    }

    public static final class CheckedNativeImpl extends MemoryIO {
        private CheckedNativeImpl() {
        }

        public final void _copyMemory(long j2, long j3, long j4) {
            Foreign.copyMemoryChecked(j2, j3, j4);
        }

        public final long getAddress(long j2) {
            return Foreign.getAddressChecked(j2) & MemoryIO.ADDRESS_MASK;
        }

        public final byte getByte(long j2) {
            return Foreign.getByteChecked(j2);
        }

        public final void getByteArray(long j2, byte[] bArr, int i3, int i4) {
            Foreign.getByteArrayChecked(j2, bArr, i3, i4);
        }

        public final void getCharArray(long j2, char[] cArr, int i3, int i4) {
            Foreign.getCharArrayChecked(j2, cArr, i3, i4);
        }

        public final double getDouble(long j2) {
            return Foreign.getDoubleChecked(j2);
        }

        public final void getDoubleArray(long j2, double[] dArr, int i3, int i4) {
            Foreign.getDoubleArrayChecked(j2, dArr, i3, i4);
        }

        public final float getFloat(long j2) {
            return Foreign.getFloatChecked(j2);
        }

        public final void getFloatArray(long j2, float[] fArr, int i3, int i4) {
            Foreign.getFloatArrayChecked(j2, fArr, i3, i4);
        }

        public final int getInt(long j2) {
            return Foreign.getIntChecked(j2);
        }

        public final void getIntArray(long j2, int[] iArr, int i3, int i4) {
            Foreign.getIntArrayChecked(j2, iArr, i3, i4);
        }

        public final long getLong(long j2) {
            return Foreign.getLongChecked(j2);
        }

        public final void getLongArray(long j2, long[] jArr, int i3, int i4) {
            Foreign.getLongArrayChecked(j2, jArr, i3, i4);
        }

        public final short getShort(long j2) {
            return Foreign.getShortChecked(j2);
        }

        public final void getShortArray(long j2, short[] sArr, int i3, int i4) {
            Foreign.getShortArrayChecked(j2, sArr, i3, i4);
        }

        public final long getStringLength(long j2) {
            return Foreign.strlenChecked(j2);
        }

        public final byte[] getZeroTerminatedByteArray(long j2) {
            return Foreign.getZeroTerminatedByteArrayChecked(j2);
        }

        public final long memchr(long j2, int i3, long j3) {
            return Foreign.memchrChecked(j2, i3, j3);
        }

        public final void memcpy(long j2, long j3, long j4) {
            Foreign.memcpyChecked(j2, j3, j4);
        }

        public final void memmove(long j2, long j3, long j4) {
            Foreign.memmoveChecked(j2, j3, j4);
        }

        public final void putAddress(long j2, long j3) {
            Foreign.putAddressChecked(j2, j3);
        }

        public final void putByte(long j2, byte b3) {
            Foreign.putByteChecked(j2, b3);
        }

        public final void putByteArray(long j2, byte[] bArr, int i3, int i4) {
            Foreign.putByteArrayChecked(j2, bArr, i3, i4);
        }

        public final void putCharArray(long j2, char[] cArr, int i3, int i4) {
            Foreign.putCharArrayChecked(j2, cArr, i3, i4);
        }

        public final void putDouble(long j2, double d2) {
            Foreign.putDoubleChecked(j2, d2);
        }

        public final void putDoubleArray(long j2, double[] dArr, int i3, int i4) {
            Foreign.putDoubleArrayChecked(j2, dArr, i3, i4);
        }

        public final void putFloat(long j2, float f2) {
            Foreign.putFloatChecked(j2, f2);
        }

        public final void putFloatArray(long j2, float[] fArr, int i3, int i4) {
            Foreign.putFloatArrayChecked(j2, fArr, i3, i4);
        }

        public final void putInt(long j2, int i3) {
            Foreign.putIntChecked(j2, i3);
        }

        public final void putIntArray(long j2, int[] iArr, int i3, int i4) {
            Foreign.putIntArrayChecked(j2, iArr, i3, i4);
        }

        public final void putLong(long j2, long j3) {
            Foreign.putLongChecked(j2, j3);
        }

        public final void putLongArray(long j2, long[] jArr, int i3, int i4) {
            Foreign.putLongArrayChecked(j2, jArr, i3, i4);
        }

        public final void putShort(long j2, short s3) {
            Foreign.putShortChecked(j2, s3);
        }

        public final void putShortArray(long j2, short[] sArr, int i3, int i4) {
            Foreign.putShortArrayChecked(j2, sArr, i3, i4);
        }

        public final void putZeroTerminatedByteArray(long j2, byte[] bArr, int i3, int i4) {
            Foreign.putZeroTerminatedByteArrayChecked(j2, bArr, i3, i4);
        }

        public final void setMemory(long j2, long j3, byte b3) {
            Foreign.setMemoryChecked(j2, j3, b3);
        }

        public final byte[] getZeroTerminatedByteArray(long j2, int i3) {
            return Foreign.getZeroTerminatedByteArrayChecked(j2, i3);
        }
    }

    public static abstract class NativeImpl extends MemoryIO {
        private NativeImpl() {
        }

        public final void _copyMemory(long j2, long j3, long j4) {
            Foreign.copyMemory(j2, j3, j4);
        }

        public final byte getByte(long j2) {
            return Foreign.getByte(j2);
        }

        public final void getByteArray(long j2, byte[] bArr, int i3, int i4) {
            Foreign.getByteArray(j2, bArr, i3, i4);
        }

        public final void getCharArray(long j2, char[] cArr, int i3, int i4) {
            Foreign.getCharArray(j2, cArr, i3, i4);
        }

        public final double getDouble(long j2) {
            return Foreign.getDouble(j2);
        }

        public final void getDoubleArray(long j2, double[] dArr, int i3, int i4) {
            Foreign.getDoubleArray(j2, dArr, i3, i4);
        }

        public final float getFloat(long j2) {
            return Foreign.getFloat(j2);
        }

        public final void getFloatArray(long j2, float[] fArr, int i3, int i4) {
            Foreign.getFloatArray(j2, fArr, i3, i4);
        }

        public final int getInt(long j2) {
            return Foreign.getInt(j2);
        }

        public final void getIntArray(long j2, int[] iArr, int i3, int i4) {
            Foreign.getIntArray(j2, iArr, i3, i4);
        }

        public final long getLong(long j2) {
            return Foreign.getLong(j2);
        }

        public final void getLongArray(long j2, long[] jArr, int i3, int i4) {
            Foreign.getLongArray(j2, jArr, i3, i4);
        }

        public final short getShort(long j2) {
            return Foreign.getShort(j2);
        }

        public final void getShortArray(long j2, short[] sArr, int i3, int i4) {
            Foreign.getShortArray(j2, sArr, i3, i4);
        }

        public final long getStringLength(long j2) {
            return Foreign.strlen(j2);
        }

        public final byte[] getZeroTerminatedByteArray(long j2) {
            return Foreign.getZeroTerminatedByteArray(j2);
        }

        public final long memchr(long j2, int i3, long j3) {
            return Foreign.memchr(j2, i3, j3);
        }

        public final void memcpy(long j2, long j3, long j4) {
            Foreign.memcpy(j2, j3, j4);
        }

        public final void memmove(long j2, long j3, long j4) {
            Foreign.memmove(j2, j3, j4);
        }

        public final void putByte(long j2, byte b3) {
            Foreign.putByte(j2, b3);
        }

        public final void putByteArray(long j2, byte[] bArr, int i3, int i4) {
            Foreign.putByteArray(j2, bArr, i3, i4);
        }

        public final void putCharArray(long j2, char[] cArr, int i3, int i4) {
            Foreign.putCharArray(j2, cArr, i3, i4);
        }

        public final void putDouble(long j2, double d2) {
            Foreign.putDouble(j2, d2);
        }

        public final void putDoubleArray(long j2, double[] dArr, int i3, int i4) {
            Foreign.putDoubleArray(j2, dArr, i3, i4);
        }

        public final void putFloat(long j2, float f2) {
            Foreign.putFloat(j2, f2);
        }

        public final void putFloatArray(long j2, float[] fArr, int i3, int i4) {
            Foreign.putFloatArray(j2, fArr, i3, i4);
        }

        public final void putInt(long j2, int i3) {
            Foreign.putInt(j2, i3);
        }

        public final void putIntArray(long j2, int[] iArr, int i3, int i4) {
            Foreign.putIntArray(j2, iArr, i3, i4);
        }

        public final void putLong(long j2, long j3) {
            Foreign.putLong(j2, j3);
        }

        public final void putLongArray(long j2, long[] jArr, int i3, int i4) {
            Foreign.putLongArray(j2, jArr, i3, i4);
        }

        public final void putShort(long j2, short s3) {
            Foreign.putShort(j2, s3);
        }

        public final void putShortArray(long j2, short[] sArr, int i3, int i4) {
            Foreign.putShortArray(j2, sArr, i3, i4);
        }

        public final void putZeroTerminatedByteArray(long j2, byte[] bArr, int i3, int i4) {
            Foreign.putZeroTerminatedByteArray(j2, bArr, i3, i4);
        }

        public final void setMemory(long j2, long j3, byte b3) {
            Foreign.setMemory(j2, j3, b3);
        }

        public final byte[] getZeroTerminatedByteArray(long j2, int i3) {
            return Foreign.getZeroTerminatedByteArray(j2, i3);
        }
    }

    public static final class NativeImpl32 extends NativeImpl {
        private NativeImpl32() {
            super();
        }

        public final long getAddress(long j2) {
            return ((long) Foreign.getInt(j2)) & MemoryIO.ADDRESS_MASK;
        }

        public final void putAddress(long j2, long j3) {
            Foreign.putInt(j2, (int) j3);
        }
    }

    public static final class NativeImpl64 extends NativeImpl {
        private NativeImpl64() {
            super();
        }

        public final long getAddress(long j2) {
            return Foreign.getLong(j2);
        }

        public final void putAddress(long j2, long j3) {
            Foreign.putLong(j2, j3);
        }
    }

    public static final class SingletonHolder {
        /* access modifiers changed from: private */
        public static final MemoryIO INSTANCE = MemoryIO.newMemoryIO();

        private SingletonHolder() {
        }
    }

    public static MemoryIO getCheckedInstance() {
        return CheckedMemorySingletonHolder.INSTANCE;
    }

    public static MemoryIO getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static boolean isUnsafeAvailable() {
        try {
            Class<?> cls = Class.forName("sun.misc.Unsafe");
            Class[] clsArr = {Byte.TYPE, Short.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE};
            for (int i3 = 0; i3 < 6; i3++) {
                verifyAccessor(cls, clsArr[i3]);
            }
            Class cls2 = Long.TYPE;
            cls.getDeclaredMethod("getAddress", new Class[]{cls2});
            cls.getDeclaredMethod("putAddress", new Class[]{cls2, cls2});
            cls.getDeclaredMethod("allocateMemory", new Class[]{cls2});
            cls.getDeclaredMethod("freeMemory", new Class[]{cls2});
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* access modifiers changed from: private */
    public static MemoryIO newMemoryIO() {
        try {
            return Boolean.getBoolean("jffi.memory.checked") ? newNativeCheckedImpl() : (Boolean.getBoolean("jffi.unsafe.disabled") || !isUnsafeAvailable()) ? newNativeImpl() : newUnsafeImpl();
        } catch (Throwable unused) {
            return newNativeImpl();
        }
    }

    /* access modifiers changed from: private */
    public static MemoryIO newNativeCheckedImpl() {
        return Foreign.isMemoryProtectionEnabled() ? new CheckedNativeImpl() : newNativeImpl();
    }

    private static MemoryIO newNativeImpl() {
        return Platform.getPlatform().addressSize() == 32 ? newNativeImpl32() : newNativeImpl64();
    }

    private static MemoryIO newNativeImpl32() {
        return new NativeImpl32();
    }

    private static MemoryIO newNativeImpl64() {
        return new NativeImpl64();
    }

    private static MemoryIO newUnsafeImpl() {
        return Platform.getPlatform().addressSize() == 32 ? newUnsafeImpl32() : newUnsafeImpl64();
    }

    private static MemoryIO newUnsafeImpl32() {
        return new UnsafeMemoryIO.UnsafeMemoryIO32();
    }

    private static MemoryIO newUnsafeImpl64() {
        return new UnsafeMemoryIO.UnsafeMemoryIO64();
    }

    private static void verifyAccessor(Class cls, Class cls2) throws NoSuchMethodException {
        String simpleName = cls2.getSimpleName();
        String str = simpleName.substring(0, 1).toUpperCase() + simpleName.substring(1);
        String a2 = c.a("get", str);
        Class cls3 = Long.TYPE;
        Method declaredMethod = cls.getDeclaredMethod(a2, new Class[]{cls3});
        if (declaredMethod.getReturnType().equals(cls2)) {
            cls.getDeclaredMethod(c.a("put", str), new Class[]{cls3, cls2});
            return;
        }
        throw new NoSuchMethodException("Incorrect return type for " + declaredMethod.getName());
    }

    public abstract void _copyMemory(long j2, long j3, long j4);

    public final long allocateMemory(long j2, boolean z2) {
        return Foreign.allocateMemory(j2, z2) & ADDRESS_MASK;
    }

    public final void copyMemory(long j2, long j3, long j4) {
        if (j3 + j4 <= j2 || j2 + j4 <= j3) {
            _copyMemory(j2, j3, j4);
        } else {
            memmove(j3, j2, j4);
        }
    }

    public final void freeMemory(long j2) {
        Foreign.freeMemory(j2);
    }

    public abstract long getAddress(long j2);

    public abstract byte getByte(long j2);

    public abstract void getByteArray(long j2, byte[] bArr, int i3, int i4);

    public abstract void getCharArray(long j2, char[] cArr, int i3, int i4);

    public final long getDirectBufferAddress(Buffer buffer) {
        return this.foreign.getDirectBufferAddress(buffer);
    }

    public abstract double getDouble(long j2);

    public abstract void getDoubleArray(long j2, double[] dArr, int i3, int i4);

    public abstract float getFloat(long j2);

    public abstract void getFloatArray(long j2, float[] fArr, int i3, int i4);

    public abstract int getInt(long j2);

    public abstract void getIntArray(long j2, int[] iArr, int i3, int i4);

    public abstract long getLong(long j2);

    public abstract void getLongArray(long j2, long[] jArr, int i3, int i4);

    public abstract short getShort(long j2);

    public abstract void getShortArray(long j2, short[] sArr, int i3, int i4);

    public abstract long getStringLength(long j2);

    public abstract byte[] getZeroTerminatedByteArray(long j2);

    public abstract byte[] getZeroTerminatedByteArray(long j2, int i3);

    @Deprecated
    public final byte[] getZeroTerminatedByteArray(long j2, long j3) {
        return getZeroTerminatedByteArray(j2, (int) j3);
    }

    public final long indexOf(long j2, byte b3) {
        long memchr = memchr(j2, b3, SieveCacheKt.NodeLinkMask);
        if (memchr != 0) {
            return memchr - j2;
        }
        return -1;
    }

    public abstract long memchr(long j2, int i3, long j3);

    public abstract void memcpy(long j2, long j3, long j4);

    public abstract void memmove(long j2, long j3, long j4);

    public final void memset(long j2, int i3, long j3) {
        setMemory(j2, j3, (byte) i3);
    }

    public final ByteBuffer newDirectByteBuffer(long j2, int i3) {
        return this.foreign.newDirectByteBuffer(j2, i3);
    }

    public abstract void putAddress(long j2, long j3);

    public abstract void putByte(long j2, byte b3);

    public abstract void putByteArray(long j2, byte[] bArr, int i3, int i4);

    public abstract void putCharArray(long j2, char[] cArr, int i3, int i4);

    public abstract void putDouble(long j2, double d2);

    public abstract void putDoubleArray(long j2, double[] dArr, int i3, int i4);

    public abstract void putFloat(long j2, float f2);

    public abstract void putFloatArray(long j2, float[] fArr, int i3, int i4);

    public abstract void putInt(long j2, int i3);

    public abstract void putIntArray(long j2, int[] iArr, int i3, int i4);

    public abstract void putLong(long j2, long j3);

    public abstract void putLongArray(long j2, long[] jArr, int i3, int i4);

    public abstract void putShort(long j2, short s3);

    public abstract void putShortArray(long j2, short[] sArr, int i3, int i4);

    public abstract void putZeroTerminatedByteArray(long j2, byte[] bArr, int i3, int i4);

    public abstract void setMemory(long j2, long j3, byte b3);

    public final long indexOf(long j2, byte b3, int i3) {
        long memchr = memchr(j2, b3, (long) i3);
        if (memchr != 0) {
            return memchr - j2;
        }
        return -1;
    }
}
