package com.fasterxml.jackson.core.io;

import java.io.IOException;
import java.io.InputStream;

public final class MergedStream extends InputStream {
    private byte[] _b;
    private final IOContext _ctxt;
    private final int _end;
    private final InputStream _in;
    private int _ptr;

    public MergedStream(IOContext iOContext, InputStream inputStream, byte[] bArr, int i3, int i4) {
        this._ctxt = iOContext;
        this._in = inputStream;
        this._b = bArr;
        this._ptr = i3;
        this._end = i4;
    }

    private void _free() {
        byte[] bArr = this._b;
        if (bArr != null) {
            this._b = null;
            IOContext iOContext = this._ctxt;
            if (iOContext != null) {
                iOContext.releaseReadIOBuffer(bArr);
            }
        }
    }

    public int available() throws IOException {
        return this._b != null ? this._end - this._ptr : this._in.available();
    }

    public void close() throws IOException {
        _free();
        this._in.close();
    }

    public synchronized void mark(int i3) {
        if (this._b == null) {
            this._in.mark(i3);
        }
    }

    public boolean markSupported() {
        return this._b == null && this._in.markSupported();
    }

    public int read() throws IOException {
        byte[] bArr = this._b;
        if (bArr == null) {
            return this._in.read();
        }
        int i3 = this._ptr;
        int i4 = i3 + 1;
        this._ptr = i4;
        byte b3 = bArr[i3] & 255;
        if (i4 >= this._end) {
            _free();
        }
        return b3;
    }

    public synchronized void reset() throws IOException {
        if (this._b == null) {
            this._in.reset();
        }
    }

    public long skip(long j2) throws IOException {
        long j3;
        if (this._b != null) {
            int i3 = this._end;
            int i4 = this._ptr;
            j3 = (long) (i3 - i4);
            if (j3 > j2) {
                this._ptr = i4 + ((int) j2);
                return j2;
            }
            _free();
            j2 -= j3;
        } else {
            j3 = 0;
        }
        return j2 > 0 ? j3 + this._in.skip(j2) : j3;
    }

    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    public int read(byte[] bArr, int i3, int i4) throws IOException {
        byte[] bArr2 = this._b;
        if (bArr2 == null) {
            return this._in.read(bArr, i3, i4);
        }
        int i5 = this._end;
        int i6 = this._ptr;
        int i7 = i5 - i6;
        if (i4 > i7) {
            i4 = i7;
        }
        System.arraycopy(bArr2, i6, bArr, i3, i4);
        int i8 = this._ptr + i4;
        this._ptr = i8;
        if (i8 >= this._end) {
            _free();
        }
        return i4;
    }
}
