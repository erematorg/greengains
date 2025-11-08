package com.fasterxml.jackson.databind.util;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class ByteBufferBackedOutputStream extends OutputStream {
    protected final ByteBuffer _b;

    public ByteBufferBackedOutputStream(ByteBuffer byteBuffer) {
        this._b = byteBuffer;
    }

    public void write(int i3) throws IOException {
        this._b.put((byte) i3);
    }

    public void write(byte[] bArr, int i3, int i4) throws IOException {
        this._b.put(bArr, i3, i4);
    }
}
