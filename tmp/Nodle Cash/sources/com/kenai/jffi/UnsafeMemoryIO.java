package com.kenai.jffi;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

public abstract class UnsafeMemoryIO extends MemoryIO {
    protected static Unsafe unsafe = Unsafe.class.cast(getUnsafe());

    public static class UnsafeMemoryIO32 extends UnsafeMemoryIO {
        public final long getAddress(long j2) {
            return ((long) UnsafeMemoryIO.unsafe.getInt(j2)) & MemoryIO.ADDRESS_MASK;
        }

        public final void putAddress(long j2, long j3) {
            UnsafeMemoryIO.unsafe.putInt(j2, (int) j3);
        }
    }

    public static class UnsafeMemoryIO64 extends UnsafeMemoryIO {
        public final long getAddress(long j2) {
            return UnsafeMemoryIO.unsafe.getLong(j2);
        }

        public final void putAddress(long j2, long j3) {
            UnsafeMemoryIO.unsafe.putLong(j2, j3);
        }
    }

    private static Object getUnsafe() {
        try {
            Class<?> cls = Class.forName("sun.misc.Unsafe");
            Field declaredField = cls.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            return declaredField.get(cls);
        } catch (Exception e3) {
            throw new RuntimeException(e3);
        }
    }

    public final void _copyMemory(long j2, long j3, long j4) {
        unsafe.copyMemory(j2, j3, j4);
    }

    public final byte getByte(long j2) {
        return unsafe.getByte(j2);
    }

    public final void getByteArray(long j2, byte[] bArr, int i3, int i4) {
        Foreign.getByteArray(j2, bArr, i3, i4);
    }

    public final void getCharArray(long j2, char[] cArr, int i3, int i4) {
        Foreign.getCharArray(j2, cArr, i3, i4);
    }

    public final double getDouble(long j2) {
        return unsafe.getDouble(j2);
    }

    public final void getDoubleArray(long j2, double[] dArr, int i3, int i4) {
        Foreign.getDoubleArray(j2, dArr, i3, i4);
    }

    public final float getFloat(long j2) {
        return unsafe.getFloat(j2);
    }

    public final void getFloatArray(long j2, float[] fArr, int i3, int i4) {
        Foreign.getFloatArray(j2, fArr, i3, i4);
    }

    public final int getInt(long j2) {
        return unsafe.getInt(j2);
    }

    public final void getIntArray(long j2, int[] iArr, int i3, int i4) {
        Foreign.getIntArray(j2, iArr, i3, i4);
    }

    public final long getLong(long j2) {
        return unsafe.getLong(j2);
    }

    public final void getLongArray(long j2, long[] jArr, int i3, int i4) {
        Foreign.getLongArray(j2, jArr, i3, i4);
    }

    public final short getShort(long j2) {
        return unsafe.getShort(j2);
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
        unsafe.putByte(j2, b3);
    }

    public final void putByteArray(long j2, byte[] bArr, int i3, int i4) {
        Foreign.putByteArray(j2, bArr, i3, i4);
    }

    public final void putCharArray(long j2, char[] cArr, int i3, int i4) {
        Foreign.putCharArray(j2, cArr, i3, i4);
    }

    public final void putDouble(long j2, double d2) {
        unsafe.putDouble(j2, d2);
    }

    public final void putDoubleArray(long j2, double[] dArr, int i3, int i4) {
        Foreign.putDoubleArray(j2, dArr, i3, i4);
    }

    public final void putFloat(long j2, float f2) {
        unsafe.putFloat(j2, f2);
    }

    public final void putFloatArray(long j2, float[] fArr, int i3, int i4) {
        Foreign.putFloatArray(j2, fArr, i3, i4);
    }

    public final void putInt(long j2, int i3) {
        unsafe.putInt(j2, i3);
    }

    public final void putIntArray(long j2, int[] iArr, int i3, int i4) {
        Foreign.putIntArray(j2, iArr, i3, i4);
    }

    public final void putLong(long j2, long j3) {
        unsafe.putLong(j2, j3);
    }

    public final void putLongArray(long j2, long[] jArr, int i3, int i4) {
        Foreign.putLongArray(j2, jArr, i3, i4);
    }

    public final void putShort(long j2, short s3) {
        unsafe.putShort(j2, s3);
    }

    public final void putShortArray(long j2, short[] sArr, int i3, int i4) {
        Foreign.putShortArray(j2, sArr, i3, i4);
    }

    public final void putZeroTerminatedByteArray(long j2, byte[] bArr, int i3, int i4) {
        Foreign.putZeroTerminatedByteArray(j2, bArr, i3, i4);
    }

    public final void setMemory(long j2, long j3, byte b3) {
        unsafe.setMemory(j2, j3, b3);
    }

    public final byte[] getZeroTerminatedByteArray(long j2, int i3) {
        return Foreign.getZeroTerminatedByteArray(j2, i3);
    }
}
