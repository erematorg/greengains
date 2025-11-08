package com.kenai.jffi;

public interface Closure {

    public interface Buffer {
        long getAddress(int i3);

        byte getByte(int i3);

        double getDouble(int i3);

        float getFloat(int i3);

        int getInt(int i3);

        long getLong(int i3);

        short getShort(int i3);

        long getStruct(int i3);

        void setAddressReturn(long j2);

        void setByteReturn(byte b3);

        void setDoubleReturn(double d2);

        void setFloatReturn(float f2);

        void setIntReturn(int i3);

        void setLongReturn(long j2);

        void setShortReturn(short s3);

        void setStructReturn(long j2);

        void setStructReturn(byte[] bArr, int i3);
    }

    public interface Handle {
        void dispose();

        @Deprecated
        void free();

        long getAddress();

        void setAutoRelease(boolean z2);
    }

    void invoke(Buffer buffer);
}
