package com.neovisionaries.ws.client;

import java.nio.ByteBuffer;

class ByteArray {
    private static final int ADDITIONAL_BUFFER_SIZE = 1024;
    private ByteBuffer mBuffer;
    private int mLength;

    public ByteArray(int i3) {
        this.mBuffer = ByteBuffer.allocate(i3);
        this.mLength = 0;
    }

    private void expandBuffer(int i3) {
        ByteBuffer allocate = ByteBuffer.allocate(i3);
        int position = this.mBuffer.position();
        this.mBuffer.position(0);
        allocate.put(this.mBuffer);
        allocate.position(position);
        this.mBuffer = allocate;
    }

    public void clear() {
        this.mBuffer.clear();
        this.mBuffer.position(0);
        this.mLength = 0;
    }

    public void clearBit(int i3) {
        setBit(i3, false);
    }

    public byte get(int i3) throws IndexOutOfBoundsException {
        if (i3 >= 0 && this.mLength > i3) {
            return this.mBuffer.get(i3);
        }
        throw new IndexOutOfBoundsException(String.format("Bad index: index=%d, length=%d", new Object[]{Integer.valueOf(i3), Integer.valueOf(this.mLength)}));
    }

    public boolean getBit(int i3) {
        return (get(i3 / 8) & (1 << (i3 % 8))) != 0;
    }

    public int getBits(int i3, int i4) {
        int i5 = 0;
        int i6 = 1;
        int i7 = 0;
        while (i5 < i4) {
            if (getBit(i3 + i5)) {
                i7 += i6;
            }
            i5++;
            i6 *= 2;
        }
        return i7;
    }

    public int getHuffmanBits(int i3, int i4) {
        int i5 = 1;
        int i6 = i4 - 1;
        int i7 = 0;
        while (i6 >= 0) {
            if (getBit(i3 + i6)) {
                i7 += i5;
            }
            i6--;
            i5 *= 2;
        }
        return i7;
    }

    public int length() {
        return this.mLength;
    }

    public void put(int i3) {
        int capacity = this.mBuffer.capacity();
        int i4 = this.mLength;
        if (capacity < i4 + 1) {
            expandBuffer(i4 + 1024);
        }
        this.mBuffer.put((byte) i3);
        this.mLength++;
    }

    public boolean readBit(int[] iArr) {
        boolean bit = getBit(iArr[0]);
        iArr[0] = iArr[0] + 1;
        return bit;
    }

    public int readBits(int[] iArr, int i3) {
        int bits = getBits(iArr[0], i3);
        iArr[0] = iArr[0] + i3;
        return bits;
    }

    public void setBit(int i3, boolean z2) {
        int i4 = i3 / 8;
        int i5 = i3 % 8;
        byte b3 = get(i4);
        this.mBuffer.put(i4, (byte) (z2 ? (1 << i5) | b3 : (~(1 << i5)) & b3));
    }

    public void shrink(int i3) {
        if (this.mBuffer.capacity() > i3) {
            int i4 = this.mLength;
            byte[] bytes = toBytes(i4 - i3, i4);
            ByteBuffer wrap = ByteBuffer.wrap(bytes);
            this.mBuffer = wrap;
            wrap.position(bytes.length);
            this.mLength = bytes.length;
        }
    }

    public byte[] toBytes() {
        return toBytes(0);
    }

    public byte[] toBytes(int i3) {
        return toBytes(i3, length());
    }

    public byte[] toBytes(int i3, int i4) {
        int i5 = i4 - i3;
        if (i5 < 0 || i3 < 0 || this.mLength < i4) {
            throw new IllegalArgumentException(String.format("Bad range: beginIndex=%d, endIndex=%d, length=%d", new Object[]{Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(this.mLength)}));
        }
        byte[] bArr = new byte[i5];
        if (i5 != 0) {
            System.arraycopy(this.mBuffer.array(), i3, bArr, 0, i5);
        }
        return bArr;
    }

    public ByteArray(byte[] bArr) {
        this.mBuffer = ByteBuffer.wrap(bArr);
        this.mLength = bArr.length;
    }

    public void put(byte[] bArr) {
        int capacity = this.mBuffer.capacity();
        int i3 = this.mLength;
        if (capacity < bArr.length + i3) {
            expandBuffer(i3 + bArr.length + 1024);
        }
        this.mBuffer.put(bArr);
        this.mLength += bArr.length;
    }

    public void put(byte[] bArr, int i3, int i4) {
        int capacity = this.mBuffer.capacity();
        int i5 = this.mLength;
        if (capacity < i5 + i4) {
            expandBuffer(i5 + i4 + 1024);
        }
        this.mBuffer.put(bArr, i3, i4);
        this.mLength += i4;
    }

    public void put(ByteArray byteArray, int i3, int i4) {
        put(byteArray.mBuffer.array(), i3, i4);
    }
}
