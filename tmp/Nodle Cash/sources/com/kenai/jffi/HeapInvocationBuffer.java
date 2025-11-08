package com.kenai.jffi;

import java.math.BigDecimal;
import java.nio.Buffer;
import java.nio.ByteOrder;

public final class HeapInvocationBuffer extends InvocationBuffer {
    private static final int PARAM_SIZE = 8;
    private final byte[] buffer;
    private final CallContext callContext;
    private ObjectBuffer objectBuffer;
    private int paramIndex = 0;
    private int paramOffset = 0;

    public static abstract class ArrayIO {

        public static final class SingletonHolder {
            /* access modifiers changed from: private */
            public static final ArrayIO DEFAULT;

            static {
                ArrayIO arrayIO;
                try {
                    int addressSize = Platform.getPlatform().addressSize();
                    if (addressSize == 32) {
                        arrayIO = ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN) ? ArrayIO.getBE32IO() : ArrayIO.getLE32IO();
                    } else if (addressSize == 64) {
                        arrayIO = ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN) ? ArrayIO.getBE64IO() : ArrayIO.getLE64IO();
                    } else {
                        throw new IllegalArgumentException("unsupported address size: " + Platform.getPlatform().addressSize());
                    }
                } catch (Throwable th) {
                    arrayIO = ArrayIO.newInvalidArrayIO(th);
                }
                DEFAULT = arrayIO;
            }

            private SingletonHolder() {
            }
        }

        private ArrayIO() {
        }

        public static ArrayIO getBE32IO() {
            return BE32ArrayIO.INSTANCE;
        }

        public static ArrayIO getBE64IO() {
            return BE64ArrayIO.INSTANCE;
        }

        public static ArrayIO getInstance() {
            return SingletonHolder.DEFAULT;
        }

        public static ArrayIO getLE32IO() {
            return LE32ArrayIO.INSTANCE;
        }

        public static ArrayIO getLE64IO() {
            return LE64ArrayIO.INSTANCE;
        }

        public static ArrayIO newInvalidArrayIO(Throwable th) {
            return new InvalidArrayIO(th);
        }

        public abstract void putAddress(byte[] bArr, int i3, long j2);

        public abstract void putByte(byte[] bArr, int i3, int i4);

        public final void putDouble(byte[] bArr, int i3, double d2) {
            putLong(bArr, i3, Double.doubleToRawLongBits(d2));
        }

        public final void putFloat(byte[] bArr, int i3, float f2) {
            putInt(bArr, i3, Float.floatToRawIntBits(f2));
        }

        public abstract void putInt(byte[] bArr, int i3, int i4);

        public abstract void putLong(byte[] bArr, int i3, long j2);

        public abstract void putShort(byte[] bArr, int i3, int i4);
    }

    public static final class BE32ArrayIO extends BigEndianArrayIO {
        static final ArrayIO INSTANCE = new BE32ArrayIO();

        private BE32ArrayIO() {
            super();
        }

        public void putAddress(byte[] bArr, int i3, long j2) {
            bArr[i3] = (byte) ((int) (j2 >> 24));
            bArr[i3 + 1] = (byte) ((int) (j2 >> 16));
            bArr[i3 + 2] = (byte) ((int) (j2 >> 8));
            bArr[i3 + 3] = (byte) ((int) j2);
        }
    }

    public static final class BE64ArrayIO extends BigEndianArrayIO {
        static final ArrayIO INSTANCE = new BE64ArrayIO();

        private BE64ArrayIO() {
            super();
        }

        public void putAddress(byte[] bArr, int i3, long j2) {
            putLong(bArr, i3, j2);
        }
    }

    public static abstract class BigEndianArrayIO extends ArrayIO {
        private BigEndianArrayIO() {
            super();
        }

        public final void putByte(byte[] bArr, int i3, int i4) {
            bArr[i3] = (byte) i4;
        }

        public final void putInt(byte[] bArr, int i3, int i4) {
            bArr[i3] = (byte) (i4 >> 24);
            bArr[i3 + 1] = (byte) (i4 >> 16);
            bArr[i3 + 2] = (byte) (i4 >> 8);
            bArr[i3 + 3] = (byte) i4;
        }

        public final void putLong(byte[] bArr, int i3, long j2) {
            bArr[i3] = (byte) ((int) (j2 >> 56));
            bArr[i3 + 1] = (byte) ((int) (j2 >> 48));
            bArr[i3 + 2] = (byte) ((int) (j2 >> 40));
            bArr[i3 + 3] = (byte) ((int) (j2 >> 32));
            bArr[i3 + 4] = (byte) ((int) (j2 >> 24));
            bArr[i3 + 5] = (byte) ((int) (j2 >> 16));
            bArr[i3 + 6] = (byte) ((int) (j2 >> 8));
            bArr[i3 + 7] = (byte) ((int) j2);
        }

        public final void putShort(byte[] bArr, int i3, int i4) {
            bArr[i3] = (byte) (i4 >> 8);
            bArr[i3 + 1] = (byte) i4;
        }
    }

    public static final class DefaultEncoder extends Encoder {

        /* renamed from: io  reason: collision with root package name */
        private final ArrayIO f7227io;

        public DefaultEncoder(ArrayIO arrayIO) {
            this.f7227io = arrayIO;
        }

        public final int getBufferSize(CallContext callContext) {
            return callContext.getParameterCount() * 8;
        }

        public final int putAddress(byte[] bArr, int i3, long j2) {
            this.f7227io.putAddress(bArr, i3, j2);
            return i3 + 8;
        }

        public final int putByte(byte[] bArr, int i3, int i4) {
            this.f7227io.putByte(bArr, i3, i4);
            return i3 + 8;
        }

        public final int putDouble(byte[] bArr, int i3, double d2) {
            this.f7227io.putDouble(bArr, i3, d2);
            return i3 + 8;
        }

        public final int putFloat(byte[] bArr, int i3, float f2) {
            this.f7227io.putFloat(bArr, i3, f2);
            return i3 + 8;
        }

        public final int putInt(byte[] bArr, int i3, int i4) {
            this.f7227io.putInt(bArr, i3, i4);
            return i3 + 8;
        }

        public final int putLong(byte[] bArr, int i3, long j2) {
            this.f7227io.putLong(bArr, i3, j2);
            return i3 + 8;
        }

        public final int putShort(byte[] bArr, int i3, int i4) {
            this.f7227io.putShort(bArr, i3, i4);
            return i3 + 8;
        }

        public int skipAddress(int i3) {
            return i3 + 8;
        }
    }

    public static abstract class Encoder {

        public static class SingletonHolder {
            static final Encoder INSTANCE = new DefaultEncoder(ArrayIO.getInstance());

            private SingletonHolder() {
            }
        }

        public static Encoder getInstance() {
            return SingletonHolder.INSTANCE;
        }

        public abstract int getBufferSize(CallContext callContext);

        public abstract int putAddress(byte[] bArr, int i3, long j2);

        public abstract int putByte(byte[] bArr, int i3, int i4);

        public abstract int putDouble(byte[] bArr, int i3, double d2);

        public abstract int putFloat(byte[] bArr, int i3, float f2);

        public abstract int putInt(byte[] bArr, int i3, int i4);

        public abstract int putLong(byte[] bArr, int i3, long j2);

        public abstract int putShort(byte[] bArr, int i3, int i4);

        public abstract int skipAddress(int i3);
    }

    public static final class InvalidArrayIO extends ArrayIO {
        private final Throwable error;

        public InvalidArrayIO(Throwable th) {
            super();
            this.error = th;
        }

        private RuntimeException ex() {
            RuntimeException runtimeException = new RuntimeException("could not determine native data encoding");
            runtimeException.initCause(this.error);
            return runtimeException;
        }

        public void putAddress(byte[] bArr, int i3, long j2) {
            throw ex();
        }

        public void putByte(byte[] bArr, int i3, int i4) {
            throw ex();
        }

        public void putInt(byte[] bArr, int i3, int i4) {
            throw ex();
        }

        public void putLong(byte[] bArr, int i3, long j2) {
            throw ex();
        }

        public void putShort(byte[] bArr, int i3, int i4) {
            throw ex();
        }
    }

    public static final class LE32ArrayIO extends LittleEndianArrayIO {
        static final ArrayIO INSTANCE = new LE32ArrayIO();

        private LE32ArrayIO() {
            super();
        }

        public final void putAddress(byte[] bArr, int i3, long j2) {
            bArr[i3] = (byte) ((int) j2);
            bArr[i3 + 1] = (byte) ((int) (j2 >> 8));
            bArr[i3 + 2] = (byte) ((int) (j2 >> 16));
            bArr[i3 + 3] = (byte) ((int) (j2 >> 24));
        }
    }

    public static final class LE64ArrayIO extends LittleEndianArrayIO {
        static final ArrayIO INSTANCE = new LE64ArrayIO();

        private LE64ArrayIO() {
            super();
        }

        public final void putAddress(byte[] bArr, int i3, long j2) {
            putLong(bArr, i3, j2);
        }
    }

    public static abstract class LittleEndianArrayIO extends ArrayIO {
        private LittleEndianArrayIO() {
            super();
        }

        public final void putByte(byte[] bArr, int i3, int i4) {
            bArr[i3] = (byte) i4;
        }

        public final void putInt(byte[] bArr, int i3, int i4) {
            bArr[i3] = (byte) i4;
            bArr[i3 + 1] = (byte) (i4 >> 8);
            bArr[i3 + 2] = (byte) (i4 >> 16);
            bArr[i3 + 3] = (byte) (i4 >> 24);
        }

        public final void putLong(byte[] bArr, int i3, long j2) {
            bArr[i3] = (byte) ((int) j2);
            bArr[i3 + 1] = (byte) ((int) (j2 >> 8));
            bArr[i3 + 2] = (byte) ((int) (j2 >> 16));
            bArr[i3 + 3] = (byte) ((int) (j2 >> 24));
            bArr[i3 + 4] = (byte) ((int) (j2 >> 32));
            bArr[i3 + 5] = (byte) ((int) (j2 >> 40));
            bArr[i3 + 6] = (byte) ((int) (j2 >> 48));
            bArr[i3 + 7] = (byte) ((int) (j2 >> 56));
        }

        public final void putShort(byte[] bArr, int i3, int i4) {
            bArr[i3] = (byte) i4;
            bArr[i3 + 1] = (byte) (i4 >> 8);
        }
    }

    public HeapInvocationBuffer(Function function) {
        CallContext callContext2 = function.getCallContext();
        this.callContext = callContext2;
        this.buffer = new byte[Encoder.getInstance().getBufferSize(callContext2)];
    }

    private final ObjectBuffer getObjectBuffer() {
        if (this.objectBuffer == null) {
            this.objectBuffer = new ObjectBuffer();
        }
        return this.objectBuffer;
    }

    public byte[] array() {
        return this.buffer;
    }

    public ObjectBuffer objectBuffer() {
        return this.objectBuffer;
    }

    public final void putAddress(long j2) {
        this.paramOffset = Encoder.getInstance().putAddress(this.buffer, this.paramOffset, j2);
        this.paramIndex++;
    }

    public final void putArray(byte[] bArr, int i3, int i4, int i5) {
        this.paramOffset = Encoder.getInstance().skipAddress(this.paramOffset);
        ObjectBuffer objectBuffer2 = getObjectBuffer();
        int i6 = this.paramIndex;
        this.paramIndex = i6 + 1;
        objectBuffer2.putArray(i6, bArr, i3, i4, i5);
    }

    public final void putByte(int i3) {
        this.paramOffset = Encoder.getInstance().putByte(this.buffer, this.paramOffset, i3);
        this.paramIndex++;
    }

    public final void putDirectBuffer(Buffer buffer2, int i3, int i4) {
        this.paramOffset = Encoder.getInstance().skipAddress(this.paramOffset);
        ObjectBuffer objectBuffer2 = getObjectBuffer();
        int i5 = this.paramIndex;
        this.paramIndex = i5 + 1;
        objectBuffer2.putDirectBuffer(i5, buffer2, i3, i4);
    }

    public final void putDouble(double d2) {
        this.paramOffset = Encoder.getInstance().putDouble(this.buffer, this.paramOffset, d2);
        this.paramIndex++;
    }

    public final void putFloat(float f2) {
        this.paramOffset = Encoder.getInstance().putFloat(this.buffer, this.paramOffset, f2);
        this.paramIndex++;
    }

    public final void putInt(int i3) {
        this.paramOffset = Encoder.getInstance().putInt(this.buffer, this.paramOffset, i3);
        this.paramIndex++;
    }

    public final void putJNIEnvironment() {
        this.paramOffset = Encoder.getInstance().putAddress(this.buffer, this.paramOffset, 0);
        ObjectBuffer objectBuffer2 = getObjectBuffer();
        int i3 = this.paramIndex;
        this.paramIndex = i3 + 1;
        objectBuffer2.putJNI(i3, (Object) null, 16777216);
    }

    public final void putJNIObject(Object obj) {
        this.paramOffset = Encoder.getInstance().putAddress(this.buffer, this.paramOffset, 0);
        ObjectBuffer objectBuffer2 = getObjectBuffer();
        int i3 = this.paramIndex;
        this.paramIndex = i3 + 1;
        objectBuffer2.putJNI(i3, obj, 33554432);
    }

    public final void putLong(long j2) {
        this.paramOffset = Encoder.getInstance().putLong(this.buffer, this.paramOffset, j2);
        this.paramIndex++;
    }

    public final void putLongDouble(double d2) {
        Type type = Type.LONGDOUBLE;
        int size = type.size();
        byte[] bArr = new byte[size];
        Foreign.getInstance().longDoubleFromDouble(d2, bArr, 0, type.size());
        getObjectBuffer().putArray(this.paramIndex, bArr, 0, size, 1);
        this.paramOffset += 8;
        this.paramIndex++;
    }

    public final void putObject(Object obj, ObjectParameterStrategy objectParameterStrategy, ObjectParameterInfo objectParameterInfo) {
        if (objectParameterStrategy.isDirect()) {
            this.paramOffset = Encoder.getInstance().putAddress(this.buffer, this.paramOffset, objectParameterStrategy.address(obj));
        } else {
            this.paramOffset = Encoder.getInstance().skipAddress(this.paramOffset);
            getObjectBuffer().putObject(objectParameterStrategy.object(obj), objectParameterStrategy.offset(obj), objectParameterStrategy.length(obj), ObjectBuffer.makeObjectFlags(objectParameterInfo.ioflags(), objectParameterStrategy.typeInfo, this.paramIndex));
        }
        this.paramIndex++;
    }

    public final void putShort(int i3) {
        this.paramOffset = Encoder.getInstance().putShort(this.buffer, this.paramOffset, i3);
        this.paramIndex++;
    }

    public final void putStruct(byte[] bArr, int i3) {
        Type parameterType = this.callContext.getParameterType(this.paramIndex);
        this.paramOffset = Encoder.getInstance().skipAddress(this.paramOffset);
        getObjectBuffer().putArray(this.paramIndex, bArr, i3, parameterType.size(), 1);
        this.paramIndex++;
    }

    public final void putArray(short[] sArr, int i3, int i4, int i5) {
        this.paramOffset = Encoder.getInstance().skipAddress(this.paramOffset);
        ObjectBuffer objectBuffer2 = getObjectBuffer();
        int i6 = this.paramIndex;
        this.paramIndex = i6 + 1;
        objectBuffer2.putArray(i6, sArr, i3, i4, i5);
    }

    public final void putArray(int[] iArr, int i3, int i4, int i5) {
        this.paramOffset = Encoder.getInstance().skipAddress(this.paramOffset);
        ObjectBuffer objectBuffer2 = getObjectBuffer();
        int i6 = this.paramIndex;
        this.paramIndex = i6 + 1;
        objectBuffer2.putArray(i6, iArr, i3, i4, i5);
    }

    public final void putStruct(long j2) {
        this.callContext.getParameterType(this.paramIndex);
        this.paramOffset = Encoder.getInstance().putAddress(this.buffer, this.paramOffset, j2);
        this.paramIndex++;
    }

    public HeapInvocationBuffer(CallContext callContext2) {
        this.callContext = callContext2;
        this.buffer = new byte[Encoder.getInstance().getBufferSize(callContext2)];
    }

    public final void putLongDouble(BigDecimal bigDecimal) {
        Type type = Type.LONGDOUBLE;
        int size = type.size();
        byte[] bArr = new byte[size];
        Foreign.getInstance().longDoubleFromString(bigDecimal.toEngineeringString(), bArr, 0, type.size());
        getObjectBuffer().putArray(this.paramIndex, bArr, 0, size, 1);
        this.paramOffset += 8;
        this.paramIndex++;
    }

    public final void putArray(long[] jArr, int i3, int i4, int i5) {
        this.paramOffset = Encoder.getInstance().skipAddress(this.paramOffset);
        ObjectBuffer objectBuffer2 = getObjectBuffer();
        int i6 = this.paramIndex;
        this.paramIndex = i6 + 1;
        objectBuffer2.putArray(i6, jArr, i3, i4, i5);
    }

    public final void putObject(Object obj, ObjectParameterStrategy objectParameterStrategy, int i3) {
        if (objectParameterStrategy.isDirect()) {
            this.paramOffset = Encoder.getInstance().putAddress(this.buffer, this.paramOffset, objectParameterStrategy.address(obj));
        } else {
            this.paramOffset = Encoder.getInstance().skipAddress(this.paramOffset);
            getObjectBuffer().putObject(objectParameterStrategy.object(obj), objectParameterStrategy.offset(obj), objectParameterStrategy.length(obj), ObjectBuffer.makeObjectFlags(i3, objectParameterStrategy.typeInfo, this.paramIndex));
        }
        this.paramIndex++;
    }

    public final void putArray(float[] fArr, int i3, int i4, int i5) {
        this.paramOffset = Encoder.getInstance().skipAddress(this.paramOffset);
        ObjectBuffer objectBuffer2 = getObjectBuffer();
        int i6 = this.paramIndex;
        this.paramIndex = i6 + 1;
        objectBuffer2.putArray(i6, fArr, i3, i4, i5);
    }

    public HeapInvocationBuffer(CallContext callContext2, int i3) {
        this.callContext = callContext2;
        this.buffer = new byte[Encoder.getInstance().getBufferSize(callContext2)];
        this.objectBuffer = new ObjectBuffer(i3);
    }

    public final void putArray(double[] dArr, int i3, int i4, int i5) {
        this.paramOffset = Encoder.getInstance().skipAddress(this.paramOffset);
        ObjectBuffer objectBuffer2 = getObjectBuffer();
        int i6 = this.paramIndex;
        this.paramIndex = i6 + 1;
        objectBuffer2.putArray(i6, dArr, i3, i4, i5);
    }
}
