package com.kenai.jffi;

import com.kenai.jffi.Closure;

final class DirectClosureBuffer implements Closure.Buffer {
    private static final MemoryIO IO = MemoryIO.getInstance();
    private static final long PARAM_SIZE = ((long) (Platform.getPlatform().addressSize() / 8));
    private static final NativeWordIO WordIO = NativeWordIO.getInstance();
    private final CallContext callContext;
    private final long parameters;
    private final long retval;

    public static abstract class NativeWordIO {
        private NativeWordIO() {
        }

        public static final NativeWordIO getInstance() {
            return Platform.getPlatform().addressSize() == 32 ? NativeWordIO32.INSTANCE : NativeWordIO64.INSTANCE;
        }

        public abstract int get(long j2);

        public abstract void put(long j2, int i3);
    }

    public static final class NativeWordIO32 extends NativeWordIO {
        static final NativeWordIO INSTANCE = new NativeWordIO32();
        private static final MemoryIO IO = MemoryIO.getInstance();

        private NativeWordIO32() {
            super();
        }

        public int get(long j2) {
            return IO.getInt(j2);
        }

        public void put(long j2, int i3) {
            IO.putInt(j2, i3);
        }
    }

    public static final class NativeWordIO64 extends NativeWordIO {
        static final NativeWordIO INSTANCE = new NativeWordIO64();
        private static final MemoryIO IO = MemoryIO.getInstance();

        private NativeWordIO64() {
            super();
        }

        public int get(long j2) {
            return (int) IO.getLong(j2);
        }

        public void put(long j2, int i3) {
            IO.putLong(j2, (long) i3);
        }
    }

    public DirectClosureBuffer(CallContext callContext2, long j2, long j3) {
        this.callContext = callContext2;
        this.retval = j2;
        this.parameters = j3;
    }

    public final long getAddress(int i3) {
        MemoryIO memoryIO = IO;
        return memoryIO.getAddress(memoryIO.getAddress((((long) i3) * PARAM_SIZE) + this.parameters));
    }

    public final byte getByte(int i3) {
        MemoryIO memoryIO = IO;
        return memoryIO.getByte(memoryIO.getAddress((((long) i3) * PARAM_SIZE) + this.parameters));
    }

    public final double getDouble(int i3) {
        MemoryIO memoryIO = IO;
        return memoryIO.getDouble(memoryIO.getAddress((((long) i3) * PARAM_SIZE) + this.parameters));
    }

    public final float getFloat(int i3) {
        MemoryIO memoryIO = IO;
        return memoryIO.getFloat(memoryIO.getAddress((((long) i3) * PARAM_SIZE) + this.parameters));
    }

    public final int getInt(int i3) {
        MemoryIO memoryIO = IO;
        return memoryIO.getInt(memoryIO.getAddress((((long) i3) * PARAM_SIZE) + this.parameters));
    }

    public final long getLong(int i3) {
        MemoryIO memoryIO = IO;
        return memoryIO.getLong(memoryIO.getAddress((((long) i3) * PARAM_SIZE) + this.parameters));
    }

    public final short getShort(int i3) {
        MemoryIO memoryIO = IO;
        return memoryIO.getShort(memoryIO.getAddress((((long) i3) * PARAM_SIZE) + this.parameters));
    }

    public final long getStruct(int i3) {
        return IO.getAddress((((long) i3) * PARAM_SIZE) + this.parameters);
    }

    public final void setAddressReturn(long j2) {
        IO.putAddress(this.retval, j2);
    }

    public final void setByteReturn(byte b3) {
        WordIO.put(this.retval, b3);
    }

    public final void setDoubleReturn(double d2) {
        IO.putDouble(this.retval, d2);
    }

    public final void setFloatReturn(float f2) {
        IO.putFloat(this.retval, f2);
    }

    public final void setIntReturn(int i3) {
        WordIO.put(this.retval, i3);
    }

    public final void setLongReturn(long j2) {
        IO.putLong(this.retval, j2);
    }

    public final void setShortReturn(short s3) {
        WordIO.put(this.retval, s3);
    }

    public void setStructReturn(long j2) {
        IO.copyMemory(j2, this.retval, (long) this.callContext.getReturnType().size());
    }

    public void setStructReturn(byte[] bArr, int i3) {
        IO.putByteArray(this.retval, bArr, i3, this.callContext.getReturnType().size());
    }
}
