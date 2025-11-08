package com.fasterxml.jackson.core.util;

import androidx.camera.core.impl.i;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedList;

public final class ByteArrayBuilder extends OutputStream {
    static final int DEFAULT_BLOCK_ARRAY_SIZE = 40;
    private static final int INITIAL_BLOCK_SIZE = 500;
    private static final int MAX_BLOCK_SIZE = 131072;
    public static final byte[] NO_BYTES = new byte[0];
    private final BufferRecycler _bufferRecycler;
    private byte[] _currBlock;
    private int _currBlockPtr;
    private final LinkedList<byte[]> _pastBlocks;
    private int _pastLen;

    public ByteArrayBuilder() {
        this((BufferRecycler) null);
    }

    private void _allocMore() {
        int length = this._pastLen + this._currBlock.length;
        if (length >= 0) {
            this._pastLen = length;
            int max = Math.max(length >> 1, 1000);
            if (max > 131072) {
                max = 131072;
            }
            this._pastBlocks.add(this._currBlock);
            this._currBlock = new byte[max];
            this._currBlockPtr = 0;
            return;
        }
        throw new IllegalStateException("Maximum Java array size (2GB) exceeded by `ByteArrayBuilder`");
    }

    public static ByteArrayBuilder fromInitial(byte[] bArr, int i3) {
        return new ByteArrayBuilder((BufferRecycler) null, bArr, i3);
    }

    public void append(int i3) {
        if (this._currBlockPtr >= this._currBlock.length) {
            _allocMore();
        }
        byte[] bArr = this._currBlock;
        int i4 = this._currBlockPtr;
        this._currBlockPtr = i4 + 1;
        bArr[i4] = (byte) i3;
    }

    public void appendFourBytes(int i3) {
        int i4 = this._currBlockPtr;
        int i5 = i4 + 3;
        byte[] bArr = this._currBlock;
        if (i5 < bArr.length) {
            int i6 = i4 + 1;
            this._currBlockPtr = i6;
            bArr[i4] = (byte) (i3 >> 24);
            int i7 = i4 + 2;
            this._currBlockPtr = i7;
            bArr[i6] = (byte) (i3 >> 16);
            int i8 = i4 + 3;
            this._currBlockPtr = i8;
            bArr[i7] = (byte) (i3 >> 8);
            this._currBlockPtr = i4 + 4;
            bArr[i8] = (byte) i3;
            return;
        }
        append(i3 >> 24);
        append(i3 >> 16);
        append(i3 >> 8);
        append(i3);
    }

    public void appendThreeBytes(int i3) {
        int i4 = this._currBlockPtr;
        int i5 = i4 + 2;
        byte[] bArr = this._currBlock;
        if (i5 < bArr.length) {
            int i6 = i4 + 1;
            this._currBlockPtr = i6;
            bArr[i4] = (byte) (i3 >> 16);
            int i7 = i4 + 2;
            this._currBlockPtr = i7;
            bArr[i6] = (byte) (i3 >> 8);
            this._currBlockPtr = i4 + 3;
            bArr[i7] = (byte) i3;
            return;
        }
        append(i3 >> 16);
        append(i3 >> 8);
        append(i3);
    }

    public void appendTwoBytes(int i3) {
        int i4 = this._currBlockPtr;
        int i5 = i4 + 1;
        byte[] bArr = this._currBlock;
        if (i5 < bArr.length) {
            int i6 = i4 + 1;
            this._currBlockPtr = i6;
            bArr[i4] = (byte) (i3 >> 8);
            this._currBlockPtr = i4 + 2;
            bArr[i6] = (byte) i3;
            return;
        }
        append(i3 >> 8);
        append(i3);
    }

    public void close() {
    }

    public byte[] completeAndCoalesce(int i3) {
        this._currBlockPtr = i3;
        return toByteArray();
    }

    public byte[] finishCurrentSegment() {
        _allocMore();
        return this._currBlock;
    }

    public void flush() {
    }

    public byte[] getCurrentSegment() {
        return this._currBlock;
    }

    public int getCurrentSegmentLength() {
        return this._currBlockPtr;
    }

    public void release() {
        byte[] bArr;
        reset();
        BufferRecycler bufferRecycler = this._bufferRecycler;
        if (bufferRecycler != null && (bArr = this._currBlock) != null) {
            bufferRecycler.releaseByteBuffer(2, bArr);
            this._currBlock = null;
        }
    }

    public void reset() {
        this._pastLen = 0;
        this._currBlockPtr = 0;
        if (!this._pastBlocks.isEmpty()) {
            this._pastBlocks.clear();
        }
    }

    public byte[] resetAndGetFirstSegment() {
        reset();
        return this._currBlock;
    }

    public void setCurrentSegmentLength(int i3) {
        this._currBlockPtr = i3;
    }

    public int size() {
        return this._pastLen + this._currBlockPtr;
    }

    public byte[] toByteArray() {
        int i3 = this._pastLen + this._currBlockPtr;
        if (i3 == 0) {
            return NO_BYTES;
        }
        byte[] bArr = new byte[i3];
        Iterator<byte[]> it = this._pastBlocks.iterator();
        int i4 = 0;
        while (it.hasNext()) {
            byte[] next = it.next();
            int length = next.length;
            System.arraycopy(next, 0, bArr, i4, length);
            i4 += length;
        }
        System.arraycopy(this._currBlock, 0, bArr, i4, this._currBlockPtr);
        int i5 = i4 + this._currBlockPtr;
        if (i5 == i3) {
            if (!this._pastBlocks.isEmpty()) {
                reset();
            }
            return bArr;
        }
        throw new RuntimeException(i.a(i3, i5, "Internal error: total len assumed to be ", ", copied ", " bytes"));
    }

    public void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    public ByteArrayBuilder(BufferRecycler bufferRecycler) {
        this(bufferRecycler, 500);
    }

    public void write(byte[] bArr, int i3, int i4) {
        while (true) {
            int min = Math.min(this._currBlock.length - this._currBlockPtr, i4);
            if (min > 0) {
                System.arraycopy(bArr, i3, this._currBlock, this._currBlockPtr, min);
                i3 += min;
                this._currBlockPtr += min;
                i4 -= min;
            }
            if (i4 > 0) {
                _allocMore();
            } else {
                return;
            }
        }
    }

    public ByteArrayBuilder(int i3) {
        this((BufferRecycler) null, i3);
    }

    public ByteArrayBuilder(BufferRecycler bufferRecycler, int i3) {
        this._pastBlocks = new LinkedList<>();
        this._bufferRecycler = bufferRecycler;
        this._currBlock = bufferRecycler == null ? new byte[(i3 > 131072 ? 131072 : i3)] : bufferRecycler.allocByteBuffer(2);
    }

    public void write(int i3) {
        append(i3);
    }

    private ByteArrayBuilder(BufferRecycler bufferRecycler, byte[] bArr, int i3) {
        this._pastBlocks = new LinkedList<>();
        this._bufferRecycler = null;
        this._currBlock = bArr;
        this._currBlockPtr = i3;
    }
}
