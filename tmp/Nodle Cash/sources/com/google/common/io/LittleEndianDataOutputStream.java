package com.google.common.io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Longs;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
@J2ktIncompatible
public final class LittleEndianDataOutputStream extends FilterOutputStream implements DataOutput {
    public LittleEndianDataOutputStream(OutputStream outputStream) {
        super(new DataOutputStream((OutputStream) Preconditions.checkNotNull(outputStream)));
    }

    public void close() throws IOException {
        this.out.close();
    }

    public void write(byte[] bArr, int i3, int i4) throws IOException {
        this.out.write(bArr, i3, i4);
    }

    public void writeBoolean(boolean z2) throws IOException {
        ((DataOutputStream) this.out).writeBoolean(z2);
    }

    public void writeByte(int i3) throws IOException {
        ((DataOutputStream) this.out).writeByte(i3);
    }

    @Deprecated
    public void writeBytes(String str) throws IOException {
        ((DataOutputStream) this.out).writeBytes(str);
    }

    public void writeChar(int i3) throws IOException {
        writeShort(i3);
    }

    public void writeChars(String str) throws IOException {
        for (int i3 = 0; i3 < str.length(); i3++) {
            writeChar(str.charAt(i3));
        }
    }

    public void writeDouble(double d2) throws IOException {
        writeLong(Double.doubleToLongBits(d2));
    }

    public void writeFloat(float f2) throws IOException {
        writeInt(Float.floatToIntBits(f2));
    }

    public void writeInt(int i3) throws IOException {
        this.out.write(i3 & 255);
        this.out.write((i3 >> 8) & 255);
        this.out.write((i3 >> 16) & 255);
        this.out.write((i3 >> 24) & 255);
    }

    public void writeLong(long j2) throws IOException {
        byte[] byteArray = Longs.toByteArray(Long.reverseBytes(j2));
        write(byteArray, 0, byteArray.length);
    }

    public void writeShort(int i3) throws IOException {
        this.out.write(i3 & 255);
        this.out.write((i3 >> 8) & 255);
    }

    public void writeUTF(String str) throws IOException {
        ((DataOutputStream) this.out).writeUTF(str);
    }
}
