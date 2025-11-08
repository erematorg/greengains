package com.fasterxml.jackson.core.io;

import androidx.camera.camera2.internal.C0118y;
import com.fasterxml.jackson.core.base.GeneratorBase;
import java.io.CharConversionException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class UTF32Reader extends Reader {
    protected static final int LAST_VALID_UNICODE_CHAR = 1114111;
    protected static final char NC = 0;
    protected final boolean _bigEndian;
    protected byte[] _buffer;
    protected int _byteCount;
    protected int _charCount;
    protected final IOContext _context;
    protected InputStream _in;
    protected int _length;
    protected final boolean _managedBuffers;
    protected int _ptr;
    protected char _surrogate = 0;
    protected char[] _tmpBuf;

    public UTF32Reader(IOContext iOContext, InputStream inputStream, byte[] bArr, int i3, int i4, boolean z2) {
        boolean z3 = false;
        this._context = iOContext;
        this._in = inputStream;
        this._buffer = bArr;
        this._ptr = i3;
        this._length = i4;
        this._bigEndian = z2;
        this._managedBuffers = inputStream != null ? true : z3;
    }

    private void freeBuffers() {
        byte[] bArr = this._buffer;
        if (bArr != null) {
            this._buffer = null;
            IOContext iOContext = this._context;
            if (iOContext != null) {
                iOContext.releaseReadIOBuffer(bArr);
            }
        }
    }

    private boolean loadMore(int i3) throws IOException {
        byte[] bArr;
        InputStream inputStream = this._in;
        if (inputStream == null || (bArr = this._buffer) == null) {
            return false;
        }
        this._byteCount = (this._length - i3) + this._byteCount;
        if (i3 > 0) {
            int i4 = this._ptr;
            if (i4 > 0) {
                System.arraycopy(bArr, i4, bArr, 0, i3);
                this._ptr = 0;
            }
            this._length = i3;
        } else {
            this._ptr = 0;
            int read = inputStream.read(bArr);
            if (read < 1) {
                this._length = 0;
                if (read < 0) {
                    if (this._managedBuffers) {
                        freeBuffers();
                    }
                    return false;
                }
                reportStrangeStream();
            }
            this._length = read;
        }
        while (true) {
            int i5 = this._length;
            if (i5 >= 4) {
                return true;
            }
            InputStream inputStream2 = this._in;
            byte[] bArr2 = this._buffer;
            int read2 = inputStream2.read(bArr2, i5, bArr2.length - i5);
            if (read2 < 1) {
                if (read2 < 0) {
                    if (this._managedBuffers) {
                        freeBuffers();
                    }
                    reportUnexpectedEOF(this._length, 4);
                }
                reportStrangeStream();
            }
            this._length += read2;
        }
    }

    private void reportBounds(char[] cArr, int i3, int i4) throws IOException {
        throw new ArrayIndexOutOfBoundsException(String.format("read(buf,%d,%d), cbuf[%d]", new Object[]{Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(cArr.length)}));
    }

    private void reportInvalid(int i3, int i4, String str) throws IOException {
        StringBuilder sb = new StringBuilder("Invalid UTF-32 character 0x");
        sb.append(Integer.toHexString(i3));
        sb.append(str);
        sb.append(" at char #");
        sb.append(this._charCount + i4);
        sb.append(", byte #");
        sb.append((this._byteCount + this._ptr) - 1);
        sb.append(")");
        throw new CharConversionException(sb.toString());
    }

    private void reportStrangeStream() throws IOException {
        throw new IOException("Strange I/O stream, returned 0 bytes on read");
    }

    private void reportUnexpectedEOF(int i3, int i4) throws IOException {
        int i5 = this._byteCount + i3;
        int i6 = this._charCount;
        StringBuilder k2 = C0118y.k(i3, i4, "Unexpected EOF in the middle of a 4-byte UTF-32 char: got ", ", needed ", ", at char #");
        k2.append(i6);
        k2.append(", byte #");
        k2.append(i5);
        k2.append(")");
        throw new CharConversionException(k2.toString());
    }

    public void close() throws IOException {
        InputStream inputStream = this._in;
        if (inputStream != null) {
            this._in = null;
            freeBuffers();
            inputStream.close();
        }
    }

    public int read() throws IOException {
        if (this._tmpBuf == null) {
            this._tmpBuf = new char[1];
        }
        if (read(this._tmpBuf, 0, 1) < 1) {
            return -1;
        }
        return this._tmpBuf[0];
    }

    public int read(char[] cArr, int i3, int i4) throws IOException {
        int i5;
        byte b3;
        byte b4;
        if (this._buffer == null) {
            return -1;
        }
        if (i4 < 1) {
            return i4;
        }
        if (i3 < 0 || i3 + i4 > cArr.length) {
            reportBounds(cArr, i3, i4);
        }
        int i6 = i4 + i3;
        char c3 = this._surrogate;
        if (c3 != 0) {
            i5 = i3 + 1;
            cArr[i3] = c3;
            this._surrogate = 0;
        } else {
            int i7 = this._length - this._ptr;
            if (i7 < 4 && !loadMore(i7)) {
                if (i7 == 0) {
                    return -1;
                }
                reportUnexpectedEOF(this._length - this._ptr, 4);
            }
            i5 = i3;
        }
        int i8 = this._length - 4;
        while (true) {
            if (i5 >= i6) {
                break;
            }
            int i9 = this._ptr;
            if (i9 > i8) {
                break;
            }
            if (this._bigEndian) {
                byte[] bArr = this._buffer;
                b3 = (bArr[i9] << 8) | (bArr[i9 + 1] & 255);
                b4 = (bArr[i9 + 3] & 255) | ((bArr[i9 + 2] & 255) << 8);
            } else {
                byte[] bArr2 = this._buffer;
                byte b5 = (bArr2[i9] & 255) | ((bArr2[i9 + 1] & 255) << 8);
                b3 = (bArr2[i9 + 3] << 8) | (bArr2[i9 + 2] & 255);
                b4 = b5;
            }
            this._ptr = i9 + 4;
            if (b3 != 0) {
                byte b6 = 65535 & b3;
                byte b7 = b4 | ((b6 - 1) << 16);
                if (b6 > 16) {
                    reportInvalid(b7, i5 - i3, String.format(" (above 0x%08x)", new Object[]{1114111}));
                }
                int i10 = i5 + 1;
                cArr[i5] = (char) ((b7 >> 10) + GeneratorBase.SURR1_FIRST);
                byte b8 = (b7 & 1023) | 56320;
                if (i10 >= i6) {
                    this._surrogate = (char) b7;
                    i5 = i10;
                    break;
                }
                b4 = b8;
                i5 = i10;
            }
            cArr[i5] = (char) b4;
            i5++;
        }
        int i11 = i5 - i3;
        this._charCount += i11;
        return i11;
    }
}
