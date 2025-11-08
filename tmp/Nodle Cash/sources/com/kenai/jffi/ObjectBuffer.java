package com.kenai.jffi;

import java.nio.Buffer;

final class ObjectBuffer {
    static final int ARRAY = 268435456;
    static final int BOOLEAN = 117440512;
    static final int BUFFER = 536870912;
    static final int BYTE = 16777216;
    static final int CHAR = 134217728;
    public static final int CLEAR = 16;
    static final int DOUBLE = 100663296;
    static final int FLAGS_MASK = 255;
    static final int FLAGS_SHIFT = 0;
    static final int FLOAT = 83886080;
    public static final int IN = 1;
    static final int INDEX_MASK = 16711680;
    static final int INDEX_SHIFT = 16;
    static final int INT = 50331648;
    static final int JNI = 1073741824;
    public static final int JNIENV = 16777216;
    public static final int JNIOBJECT = 33554432;
    static final int LONG = 67108864;
    public static final int OUT = 2;
    public static final int PINNED = 8;
    static final int PRIM_MASK = 251658240;
    static final int SHORT = 33554432;
    static final int TYPE_MASK = -16777216;
    static final int TYPE_SHIFT = 24;
    public static final int ZERO_TERMINATE = 4;
    private int[] info;
    private int infoIndex = 0;
    private int objectIndex = 0;
    private Object[] objects;

    public ObjectBuffer() {
        Object[] objArr = new Object[1];
        this.objects = objArr;
        this.info = new int[(objArr.length * 3)];
    }

    private final void ensureSpace() {
        Object[] objArr = this.objects;
        int length = objArr.length;
        int i3 = this.objectIndex;
        if (length <= i3 + 1) {
            Object[] objArr2 = new Object[(objArr.length << 1)];
            System.arraycopy(objArr, 0, objArr2, 0, i3);
            this.objects = objArr2;
            int[] iArr = new int[(objArr2.length * 3)];
            System.arraycopy(this.info, 0, iArr, 0, this.objectIndex * 3);
            this.info = iArr;
        }
    }

    public static final int makeBufferFlags(int i3) {
        return ((i3 << 16) & INDEX_MASK) | 536870912;
    }

    private static final int makeJNIFlags(int i3, int i4) {
        return ((i3 << 16) & INDEX_MASK) | 1073741824 | i4;
    }

    public static final int makeObjectFlags(int i3, int i4, int i5) {
        return (i3 & 255) | ((i5 << 16) & INDEX_MASK) | i4;
    }

    public final int[] info() {
        return this.info;
    }

    public final int objectCount() {
        return this.objectIndex;
    }

    public final Object[] objects() {
        return this.objects;
    }

    public void putArray(int i3, byte[] bArr, int i4, int i5, int i6) {
        putObject(bArr, i4, i5, makeObjectFlags(i6, 285212672, i3));
    }

    public void putDirectBuffer(int i3, Buffer buffer, int i4, int i5) {
        putObject(buffer, i4, i5, makeBufferFlags(i3));
    }

    public void putJNI(int i3, Object obj, int i4) {
        putObject(obj, 0, 0, makeJNIFlags(i3, i4));
    }

    public void putObject(Object obj, int i3, int i4, int i5) {
        ensureSpace();
        Object[] objArr = this.objects;
        int i6 = this.objectIndex;
        this.objectIndex = i6 + 1;
        objArr[i6] = obj;
        int[] iArr = this.info;
        int i7 = this.infoIndex;
        int i8 = i7 + 1;
        this.infoIndex = i8;
        iArr[i7] = i5;
        int i9 = i7 + 2;
        this.infoIndex = i9;
        iArr[i8] = i3;
        this.infoIndex = i7 + 3;
        iArr[i9] = i4;
    }

    public void putArray(int i3, short[] sArr, int i4, int i5, int i6) {
        putObject(sArr, i4, i5, makeObjectFlags(i6, 301989888, i3));
    }

    public void putArray(int i3, int[] iArr, int i4, int i5, int i6) {
        putObject(iArr, i4, i5, makeObjectFlags(i6, 318767104, i3));
    }

    public void putArray(int i3, long[] jArr, int i4, int i5, int i6) {
        putObject(jArr, i4, i5, makeObjectFlags(i6, 335544320, i3));
    }

    public void putArray(int i3, float[] fArr, int i4, int i5, int i6) {
        putObject(fArr, i4, i5, makeObjectFlags(i6, 352321536, i3));
    }

    public ObjectBuffer(int i3) {
        this.objects = new Object[i3];
        this.info = new int[(i3 * 3)];
    }

    public void putArray(int i3, double[] dArr, int i4, int i5, int i6) {
        putObject(dArr, i4, i5, makeObjectFlags(i6, 369098752, i3));
    }

    public void putArray(int i3, boolean[] zArr, int i4, int i5, int i6) {
        putObject(zArr, i4, i5, makeObjectFlags(i6, 385875968, i3));
    }

    public void putArray(int i3, char[] cArr, int i4, int i5, int i6) {
        putObject(cArr, i4, i5, makeObjectFlags(i6, 402653184, i3));
    }
}
