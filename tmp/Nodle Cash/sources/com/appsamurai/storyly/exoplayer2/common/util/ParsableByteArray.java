package com.appsamurai.storyly.exoplayer2.common.util;

import A.a;
import androidx.annotation.Nullable;
import com.google.common.base.Ascii;
import com.google.common.base.Charsets;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import okio.Utf8;
import org.msgpack.core.MessagePack;

public final class ParsableByteArray {
    private byte[] data;
    private int limit;
    private int position;

    public ParsableByteArray() {
        this.data = Util.EMPTY_BYTE_ARRAY;
    }

    public int bytesLeft() {
        return this.limit - this.position;
    }

    public int capacity() {
        return this.data.length;
    }

    public void ensureCapacity(int i3) {
        if (i3 > capacity()) {
            this.data = Arrays.copyOf(this.data, i3);
        }
    }

    public byte[] getData() {
        return this.data;
    }

    public int getPosition() {
        return this.position;
    }

    public int limit() {
        return this.limit;
    }

    public char peekChar() {
        byte[] bArr = this.data;
        int i3 = this.position;
        return (char) ((bArr[i3 + 1] & 255) | ((bArr[i3] & 255) << 8));
    }

    public int peekUnsignedByte() {
        return this.data[this.position] & 255;
    }

    public void readBytes(ParsableBitArray parsableBitArray, int i3) {
        readBytes(parsableBitArray.data, 0, i3);
        parsableBitArray.setPosition(0);
    }

    @Nullable
    public String readDelimiterTerminatedString(char c3) {
        if (bytesLeft() == 0) {
            return null;
        }
        int i3 = this.position;
        while (i3 < this.limit && this.data[i3] != c3) {
            i3++;
        }
        byte[] bArr = this.data;
        int i4 = this.position;
        String fromUtf8Bytes = Util.fromUtf8Bytes(bArr, i4, i3 - i4);
        this.position = i3;
        if (i3 < this.limit) {
            this.position = i3 + 1;
        }
        return fromUtf8Bytes;
    }

    public double readDouble() {
        return Double.longBitsToDouble(readLong());
    }

    public float readFloat() {
        return Float.intBitsToFloat(readInt());
    }

    public int readInt() {
        byte[] bArr = this.data;
        int i3 = this.position;
        int i4 = i3 + 1;
        this.position = i4;
        int i5 = (bArr[i3] & 255) << Ascii.CAN;
        int i6 = i3 + 2;
        this.position = i6;
        byte b3 = ((bArr[i4] & 255) << 16) | i5;
        int i7 = i3 + 3;
        this.position = i7;
        this.position = i3 + 4;
        return (bArr[i7] & 255) | b3 | ((bArr[i6] & 255) << 8);
    }

    public int readInt24() {
        byte[] bArr = this.data;
        int i3 = this.position;
        int i4 = i3 + 1;
        this.position = i4;
        int i5 = i3 + 2;
        this.position = i5;
        int i6 = (bArr[i4] & 255) << 8;
        this.position = i3 + 3;
        return (bArr[i5] & 255) | i6 | (((bArr[i3] & 255) << Ascii.CAN) >> 8);
    }

    @Nullable
    public String readLine() {
        if (bytesLeft() == 0) {
            return null;
        }
        int i3 = this.position;
        while (i3 < this.limit && !Util.isLinebreak(this.data[i3])) {
            i3++;
        }
        int i4 = this.position;
        if (i3 - i4 >= 3) {
            byte[] bArr = this.data;
            if (bArr[i4] == -17 && bArr[i4 + 1] == -69 && bArr[i4 + 2] == -65) {
                this.position = i4 + 3;
            }
        }
        byte[] bArr2 = this.data;
        int i5 = this.position;
        String fromUtf8Bytes = Util.fromUtf8Bytes(bArr2, i5, i3 - i5);
        this.position = i3;
        int i6 = this.limit;
        if (i3 == i6) {
            return fromUtf8Bytes;
        }
        byte[] bArr3 = this.data;
        if (bArr3[i3] == 13) {
            int i7 = i3 + 1;
            this.position = i7;
            if (i7 == i6) {
                return fromUtf8Bytes;
            }
        }
        int i8 = this.position;
        if (bArr3[i8] == 10) {
            this.position = i8 + 1;
        }
        return fromUtf8Bytes;
    }

    public int readLittleEndianInt() {
        byte[] bArr = this.data;
        int i3 = this.position;
        int i4 = i3 + 1;
        this.position = i4;
        int i5 = i3 + 2;
        this.position = i5;
        byte b3 = ((bArr[i4] & 255) << 8) | (bArr[i3] & 255);
        int i6 = i3 + 3;
        this.position = i6;
        this.position = i3 + 4;
        return ((bArr[i6] & 255) << Ascii.CAN) | b3 | ((bArr[i5] & 255) << 16);
    }

    public int readLittleEndianInt24() {
        byte[] bArr = this.data;
        int i3 = this.position;
        int i4 = i3 + 1;
        this.position = i4;
        int i5 = i3 + 2;
        this.position = i5;
        int i6 = (bArr[i4] & 255) << 8;
        this.position = i3 + 3;
        return ((bArr[i5] & 255) << 16) | i6 | (bArr[i3] & 255);
    }

    public long readLittleEndianLong() {
        byte[] bArr = this.data;
        int i3 = this.position;
        int i4 = i3 + 1;
        this.position = i4;
        int i5 = i3 + 2;
        this.position = i5;
        long j2 = (((long) bArr[i3]) & 255) | ((((long) bArr[i4]) & 255) << 8);
        int i6 = i3 + 3;
        this.position = i6;
        int i7 = i3 + 4;
        this.position = i7;
        long j3 = j2 | ((((long) bArr[i5]) & 255) << 16) | ((((long) bArr[i6]) & 255) << 24);
        int i8 = i3 + 5;
        this.position = i8;
        int i9 = i3 + 6;
        this.position = i9;
        long j4 = j3 | ((((long) bArr[i7]) & 255) << 32) | ((((long) bArr[i8]) & 255) << 40);
        int i10 = i3 + 7;
        this.position = i10;
        this.position = i3 + 8;
        return ((((long) bArr[i10]) & 255) << 56) | j4 | ((((long) bArr[i9]) & 255) << 48);
    }

    public short readLittleEndianShort() {
        byte[] bArr = this.data;
        int i3 = this.position;
        int i4 = i3 + 1;
        this.position = i4;
        this.position = i3 + 2;
        return (short) (((bArr[i4] & 255) << 8) | (bArr[i3] & 255));
    }

    public long readLittleEndianUnsignedInt() {
        byte[] bArr = this.data;
        int i3 = this.position;
        int i4 = i3 + 1;
        this.position = i4;
        int i5 = i3 + 2;
        this.position = i5;
        int i6 = i3 + 3;
        this.position = i6;
        this.position = i3 + 4;
        return ((((long) bArr[i6]) & 255) << 24) | (((long) bArr[i3]) & 255) | ((((long) bArr[i4]) & 255) << 8) | ((((long) bArr[i5]) & 255) << 16);
    }

    public int readLittleEndianUnsignedInt24() {
        byte[] bArr = this.data;
        int i3 = this.position;
        int i4 = i3 + 1;
        this.position = i4;
        int i5 = i3 + 2;
        this.position = i5;
        int i6 = (bArr[i4] & 255) << 8;
        this.position = i3 + 3;
        return ((bArr[i5] & 255) << 16) | i6 | (bArr[i3] & 255);
    }

    public int readLittleEndianUnsignedIntToInt() {
        int readLittleEndianInt = readLittleEndianInt();
        if (readLittleEndianInt >= 0) {
            return readLittleEndianInt;
        }
        throw new IllegalStateException(a.k("Top bit not zero: ", readLittleEndianInt));
    }

    public int readLittleEndianUnsignedShort() {
        byte[] bArr = this.data;
        int i3 = this.position;
        int i4 = i3 + 1;
        this.position = i4;
        this.position = i3 + 2;
        return ((bArr[i4] & 255) << 8) | (bArr[i3] & 255);
    }

    public long readLong() {
        byte[] bArr = this.data;
        int i3 = this.position;
        int i4 = i3 + 1;
        this.position = i4;
        int i5 = i3 + 2;
        this.position = i5;
        int i6 = i3 + 3;
        this.position = i6;
        long j2 = ((((long) bArr[i3]) & 255) << 56) | ((((long) bArr[i4]) & 255) << 48) | ((((long) bArr[i5]) & 255) << 40);
        int i7 = i3 + 4;
        this.position = i7;
        int i8 = i3 + 5;
        this.position = i8;
        long j3 = j2 | ((((long) bArr[i6]) & 255) << 32) | ((((long) bArr[i7]) & 255) << 24);
        int i9 = i3 + 6;
        this.position = i9;
        int i10 = i3 + 7;
        this.position = i10;
        this.position = i3 + 8;
        return (((long) bArr[i10]) & 255) | j3 | ((((long) bArr[i8]) & 255) << 16) | ((((long) bArr[i9]) & 255) << 8);
    }

    public String readNullTerminatedString(int i3) {
        if (i3 == 0) {
            return "";
        }
        int i4 = this.position;
        int i5 = (i4 + i3) - 1;
        String fromUtf8Bytes = Util.fromUtf8Bytes(this.data, i4, (i5 >= this.limit || this.data[i5] != 0) ? i3 : i3 - 1);
        this.position += i3;
        return fromUtf8Bytes;
    }

    public short readShort() {
        byte[] bArr = this.data;
        int i3 = this.position;
        int i4 = i3 + 1;
        this.position = i4;
        this.position = i3 + 2;
        return (short) ((bArr[i4] & 255) | ((bArr[i3] & 255) << 8));
    }

    public String readString(int i3) {
        return readString(i3, Charsets.UTF_8);
    }

    public int readSynchSafeInt() {
        return readUnsignedByte() | (readUnsignedByte() << 21) | (readUnsignedByte() << 14) | (readUnsignedByte() << 7);
    }

    public int readUnsignedByte() {
        byte[] bArr = this.data;
        int i3 = this.position;
        this.position = i3 + 1;
        return bArr[i3] & 255;
    }

    public int readUnsignedFixedPoint1616() {
        byte[] bArr = this.data;
        int i3 = this.position;
        int i4 = i3 + 1;
        this.position = i4;
        this.position = i3 + 2;
        byte b3 = (bArr[i4] & 255) | ((bArr[i3] & 255) << 8);
        this.position = i3 + 4;
        return b3;
    }

    public long readUnsignedInt() {
        byte[] bArr = this.data;
        int i3 = this.position;
        int i4 = i3 + 1;
        this.position = i4;
        int i5 = i3 + 2;
        this.position = i5;
        int i6 = i3 + 3;
        this.position = i6;
        this.position = i3 + 4;
        return (((long) bArr[i6]) & 255) | ((((long) bArr[i3]) & 255) << 24) | ((((long) bArr[i4]) & 255) << 16) | ((((long) bArr[i5]) & 255) << 8);
    }

    public int readUnsignedInt24() {
        byte[] bArr = this.data;
        int i3 = this.position;
        int i4 = i3 + 1;
        this.position = i4;
        int i5 = i3 + 2;
        this.position = i5;
        int i6 = (bArr[i4] & 255) << 8;
        this.position = i3 + 3;
        return (bArr[i5] & 255) | i6 | ((bArr[i3] & 255) << 16);
    }

    public int readUnsignedIntToInt() {
        int readInt = readInt();
        if (readInt >= 0) {
            return readInt;
        }
        throw new IllegalStateException(a.k("Top bit not zero: ", readInt));
    }

    public long readUnsignedLongToLong() {
        long readLong = readLong();
        if (readLong >= 0) {
            return readLong;
        }
        throw new IllegalStateException(androidx.compose.animation.core.a.s("Top bit not zero: ", readLong));
    }

    public int readUnsignedShort() {
        byte[] bArr = this.data;
        int i3 = this.position;
        int i4 = i3 + 1;
        this.position = i4;
        this.position = i3 + 2;
        return (bArr[i4] & 255) | ((bArr[i3] & 255) << 8);
    }

    public long readUtf8EncodedLong() {
        int i3;
        int i4;
        long j2 = (long) this.data[this.position];
        int i5 = 7;
        while (true) {
            i3 = 1;
            if (i5 < 0) {
                break;
            }
            int i6 = 1 << i5;
            if ((((long) i6) & j2) != 0) {
                i5--;
            } else if (i5 < 6) {
                j2 &= (long) (i6 - 1);
                i4 = 7 - i5;
            } else if (i5 == 7) {
                i4 = 1;
            }
        }
        i4 = 0;
        if (i4 != 0) {
            while (i3 < i4) {
                byte b3 = this.data[this.position + i3];
                if ((b3 & MessagePack.Code.NIL) == 128) {
                    j2 = (j2 << 6) | ((long) (b3 & Utf8.REPLACEMENT_BYTE));
                    i3++;
                } else {
                    throw new NumberFormatException(androidx.compose.animation.core.a.s("Invalid UTF-8 sequence continuation byte: ", j2));
                }
            }
            this.position += i4;
            return j2;
        }
        throw new NumberFormatException(androidx.compose.animation.core.a.s("Invalid UTF-8 sequence first byte: ", j2));
    }

    public void reset(int i3) {
        reset(capacity() < i3 ? new byte[i3] : this.data, i3);
    }

    public void setLimit(int i3) {
        Assertions.checkArgument(i3 >= 0 && i3 <= this.data.length);
        this.limit = i3;
    }

    public void setPosition(int i3) {
        Assertions.checkArgument(i3 >= 0 && i3 <= this.limit);
        this.position = i3;
    }

    public void skipBytes(int i3) {
        setPosition(this.position + i3);
    }

    public String readString(int i3, Charset charset) {
        String str = new String(this.data, this.position, i3, charset);
        this.position += i3;
        return str;
    }

    public void reset(byte[] bArr) {
        reset(bArr, bArr.length);
    }

    public ParsableByteArray(int i3) {
        this.data = new byte[i3];
        this.limit = i3;
    }

    public void readBytes(byte[] bArr, int i3, int i4) {
        System.arraycopy(this.data, this.position, bArr, i3, i4);
        this.position += i4;
    }

    public void reset(byte[] bArr, int i3) {
        this.data = bArr;
        this.limit = i3;
        this.position = 0;
    }

    public void readBytes(ByteBuffer byteBuffer, int i3) {
        byteBuffer.put(this.data, this.position, i3);
        this.position += i3;
    }

    public ParsableByteArray(byte[] bArr) {
        this.data = bArr;
        this.limit = bArr.length;
    }

    @Nullable
    public String readNullTerminatedString() {
        return readDelimiterTerminatedString(0);
    }

    public ParsableByteArray(byte[] bArr, int i3) {
        this.data = bArr;
        this.limit = i3;
    }
}
