package com.kenai.jffi;

import java.nio.Buffer;

public abstract class InvocationBuffer {
    public abstract void putAddress(long j2);

    public abstract void putArray(byte[] bArr, int i3, int i4, int i5);

    public abstract void putArray(double[] dArr, int i3, int i4, int i5);

    public abstract void putArray(float[] fArr, int i3, int i4, int i5);

    public abstract void putArray(int[] iArr, int i3, int i4, int i5);

    public abstract void putArray(long[] jArr, int i3, int i4, int i5);

    public abstract void putArray(short[] sArr, int i3, int i4, int i5);

    public abstract void putByte(int i3);

    public abstract void putDirectBuffer(Buffer buffer, int i3, int i4);

    public abstract void putDouble(double d2);

    public abstract void putFloat(float f2);

    public abstract void putInt(int i3);

    public abstract void putLong(long j2);

    public abstract void putShort(int i3);

    public abstract void putStruct(long j2);

    public abstract void putStruct(byte[] bArr, int i3);
}
