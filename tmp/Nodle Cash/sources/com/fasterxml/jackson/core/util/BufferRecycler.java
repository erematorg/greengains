package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.databind.ser.SerializerCache;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class BufferRecycler {
    public static final int BYTE_BASE64_CODEC_BUFFER = 3;
    private static final int[] BYTE_BUFFER_LENGTHS = {8000, 8000, 2000, 2000};
    public static final int BYTE_READ_IO_BUFFER = 0;
    public static final int BYTE_WRITE_CONCAT_BUFFER = 2;
    public static final int BYTE_WRITE_ENCODING_BUFFER = 1;
    private static final int[] CHAR_BUFFER_LENGTHS = {SerializerCache.DEFAULT_MAX_CACHED, SerializerCache.DEFAULT_MAX_CACHED, 200, 200};
    public static final int CHAR_CONCAT_BUFFER = 1;
    public static final int CHAR_NAME_COPY_BUFFER = 3;
    public static final int CHAR_TEXT_BUFFER = 2;
    public static final int CHAR_TOKEN_BUFFER = 0;
    protected final AtomicReferenceArray<byte[]> _byteBuffers;
    protected final AtomicReferenceArray<char[]> _charBuffers;

    public BufferRecycler() {
        this(4, 4);
    }

    public final byte[] allocByteBuffer(int i3) {
        return allocByteBuffer(i3, 0);
    }

    public final char[] allocCharBuffer(int i3) {
        return allocCharBuffer(i3, 0);
    }

    public byte[] balloc(int i3) {
        return new byte[i3];
    }

    public int byteBufferLength(int i3) {
        return BYTE_BUFFER_LENGTHS[i3];
    }

    public char[] calloc(int i3) {
        return new char[i3];
    }

    public int charBufferLength(int i3) {
        return CHAR_BUFFER_LENGTHS[i3];
    }

    public void releaseByteBuffer(int i3, byte[] bArr) {
        this._byteBuffers.set(i3, bArr);
    }

    public void releaseCharBuffer(int i3, char[] cArr) {
        this._charBuffers.set(i3, cArr);
    }

    public BufferRecycler(int i3, int i4) {
        this._byteBuffers = new AtomicReferenceArray<>(i3);
        this._charBuffers = new AtomicReferenceArray<>(i4);
    }

    public byte[] allocByteBuffer(int i3, int i4) {
        int byteBufferLength = byteBufferLength(i3);
        if (i4 < byteBufferLength) {
            i4 = byteBufferLength;
        }
        byte[] andSet = this._byteBuffers.getAndSet(i3, (Object) null);
        return (andSet == null || andSet.length < i4) ? balloc(i4) : andSet;
    }

    public char[] allocCharBuffer(int i3, int i4) {
        int charBufferLength = charBufferLength(i3);
        if (i4 < charBufferLength) {
            i4 = charBufferLength;
        }
        char[] andSet = this._charBuffers.getAndSet(i3, (Object) null);
        return (andSet == null || andSet.length < i4) ? calloc(i4) : andSet;
    }
}
