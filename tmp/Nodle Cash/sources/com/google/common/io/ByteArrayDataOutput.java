package com.google.common.io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import java.io.DataOutput;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
@J2ktIncompatible
public interface ByteArrayDataOutput extends DataOutput {
    byte[] toByteArray();

    void write(int i3);

    void write(byte[] bArr);

    void write(byte[] bArr, int i3, int i4);

    void writeBoolean(boolean z2);

    void writeByte(int i3);

    @Deprecated
    void writeBytes(String str);

    void writeChar(int i3);

    void writeChars(String str);

    void writeDouble(double d2);

    void writeFloat(float f2);

    void writeInt(int i3);

    void writeLong(long j2);

    void writeShort(int i3);

    void writeUTF(String str);
}
