package com.fasterxml.jackson.core.io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

public final class UTF8Writer extends Writer {
    static final int SURR1_FIRST = 55296;
    static final int SURR1_LAST = 56319;
    static final int SURR2_FIRST = 56320;
    static final int SURR2_LAST = 57343;
    private final IOContext _context;
    private OutputStream _out;
    private byte[] _outBuffer;
    private final int _outBufferEnd;
    private int _outPtr = 0;
    private int _surrogate;

    public UTF8Writer(IOContext iOContext, OutputStream outputStream) {
        this._context = iOContext;
        this._out = outputStream;
        byte[] allocWriteEncodingBuffer = iOContext.allocWriteEncodingBuffer();
        this._outBuffer = allocWriteEncodingBuffer;
        this._outBufferEnd = allocWriteEncodingBuffer.length - 4;
    }

    public static void illegalSurrogate(int i3) throws IOException {
        throw new IOException(illegalSurrogateDesc(i3));
    }

    public static String illegalSurrogateDesc(int i3) {
        if (i3 > 1114111) {
            return "Illegal character point (0x" + Integer.toHexString(i3) + ") to output; max is 0x10FFFF as per RFC 4627";
        } else if (i3 < 55296) {
            return "Illegal character point (0x" + Integer.toHexString(i3) + ") to output";
        } else if (i3 <= 56319) {
            return "Unmatched first part of surrogate pair (0x" + Integer.toHexString(i3) + ")";
        } else {
            return "Unmatched second part of surrogate pair (0x" + Integer.toHexString(i3) + ")";
        }
    }

    public void close() throws IOException {
        OutputStream outputStream = this._out;
        if (outputStream != null) {
            int i3 = this._outPtr;
            if (i3 > 0) {
                outputStream.write(this._outBuffer, 0, i3);
                this._outPtr = 0;
            }
            OutputStream outputStream2 = this._out;
            this._out = null;
            byte[] bArr = this._outBuffer;
            if (bArr != null) {
                this._outBuffer = null;
                this._context.releaseWriteEncodingBuffer(bArr);
            }
            outputStream2.close();
            int i4 = this._surrogate;
            this._surrogate = 0;
            if (i4 > 0) {
                illegalSurrogate(i4);
            }
        }
    }

    public int convertSurrogate(int i3) throws IOException {
        int i4 = this._surrogate;
        this._surrogate = 0;
        if (i3 < 56320 || i3 > 57343) {
            throw new IOException("Broken surrogate pair: first char 0x" + Integer.toHexString(i4) + ", second 0x" + Integer.toHexString(i3) + "; illegal combination");
        }
        return (i3 - 56320) + ((i4 - 55296) << 10) + 65536;
    }

    public void flush() throws IOException {
        OutputStream outputStream = this._out;
        if (outputStream != null) {
            int i3 = this._outPtr;
            if (i3 > 0) {
                outputStream.write(this._outBuffer, 0, i3);
                this._outPtr = 0;
            }
            this._out.flush();
        }
    }

    public void write(char[] cArr) throws IOException {
        write(cArr, 0, cArr.length);
    }

    public Writer append(char c3) throws IOException {
        write((int) c3);
        return this;
    }

    public void write(char[] cArr, int i3, int i4) throws IOException {
        if (i4 >= 2) {
            if (this._surrogate > 0) {
                i4--;
                write(convertSurrogate(cArr[i3]));
                i3++;
            }
            int i5 = this._outPtr;
            byte[] bArr = this._outBuffer;
            int i6 = this._outBufferEnd;
            int i7 = i4 + r10;
            while (r10 < i7) {
                if (i5 >= i6) {
                    this._out.write(bArr, 0, i5);
                    i5 = 0;
                }
                int i8 = r10 + 1;
                char c3 = cArr[r10];
                if (c3 < 128) {
                    int i9 = i5 + 1;
                    bArr[i5] = (byte) c3;
                    int i10 = i7 - i8;
                    int i11 = i6 - i9;
                    if (i10 > i11) {
                        i10 = i11;
                    }
                    int i12 = i10 + i8;
                    while (true) {
                        r10 = i8;
                        i5 = i9;
                        if (r10 >= i12) {
                            continue;
                            break;
                        }
                        i8 = r10 + 1;
                        c3 = cArr[r10];
                        if (c3 >= 128) {
                            break;
                        }
                        i9 = i5 + 1;
                        bArr[i5] = (byte) c3;
                    }
                }
                if (c3 < 2048) {
                    int i13 = i5 + 1;
                    bArr[i5] = (byte) ((c3 >> 6) | 192);
                    i5 += 2;
                    bArr[i13] = (byte) ((c3 & '?') | 128);
                } else if (c3 < 55296 || c3 > 57343) {
                    bArr[i5] = (byte) ((c3 >> 12) | 224);
                    int i14 = i5 + 2;
                    bArr[i5 + 1] = (byte) (((c3 >> 6) & 63) | 128);
                    i5 += 3;
                    bArr[i14] = (byte) ((c3 & '?') | 128);
                } else {
                    if (c3 > 56319) {
                        this._outPtr = i5;
                        illegalSurrogate(c3);
                    }
                    this._surrogate = c3;
                    if (i8 >= i7) {
                        break;
                    }
                    r10 = i8 + 1;
                    int convertSurrogate = convertSurrogate(cArr[i8]);
                    if (convertSurrogate > 1114111) {
                        this._outPtr = i5;
                        illegalSurrogate(convertSurrogate);
                    }
                    bArr[i5] = (byte) ((convertSurrogate >> 18) | 240);
                    bArr[i5 + 1] = (byte) (((convertSurrogate >> 12) & 63) | 128);
                    int i15 = i5 + 3;
                    bArr[i5 + 2] = (byte) (((convertSurrogate >> 6) & 63) | 128);
                    i5 += 4;
                    bArr[i15] = (byte) ((convertSurrogate & 63) | 128);
                }
                r10 = i8;
            }
            this._outPtr = i5;
        } else if (i4 == 1) {
            write((int) cArr[i3]);
        }
    }

    public void write(int i3) throws IOException {
        int i4;
        if (this._surrogate > 0) {
            i3 = convertSurrogate(i3);
        } else if (i3 >= 55296 && i3 <= 57343) {
            if (i3 > 56319) {
                illegalSurrogate(i3);
            }
            this._surrogate = i3;
            return;
        }
        int i5 = this._outPtr;
        if (i5 >= this._outBufferEnd) {
            this._out.write(this._outBuffer, 0, i5);
            this._outPtr = 0;
        }
        if (i3 < 128) {
            byte[] bArr = this._outBuffer;
            int i6 = this._outPtr;
            this._outPtr = i6 + 1;
            bArr[i6] = (byte) i3;
            return;
        }
        int i7 = this._outPtr;
        if (i3 < 2048) {
            byte[] bArr2 = this._outBuffer;
            int i8 = i7 + 1;
            bArr2[i7] = (byte) ((i3 >> 6) | 192);
            i4 = i7 + 2;
            bArr2[i8] = (byte) ((i3 & 63) | 128);
        } else if (i3 <= 65535) {
            byte[] bArr3 = this._outBuffer;
            bArr3[i7] = (byte) ((i3 >> 12) | 224);
            int i9 = i7 + 2;
            bArr3[i7 + 1] = (byte) (((i3 >> 6) & 63) | 128);
            i4 = i7 + 3;
            bArr3[i9] = (byte) ((i3 & 63) | 128);
        } else {
            if (i3 > 1114111) {
                illegalSurrogate(i3);
            }
            byte[] bArr4 = this._outBuffer;
            bArr4[i7] = (byte) ((i3 >> 18) | 240);
            bArr4[i7 + 1] = (byte) (((i3 >> 12) & 63) | 128);
            int i10 = i7 + 3;
            bArr4[i7 + 2] = (byte) (((i3 >> 6) & 63) | 128);
            i4 = i7 + 4;
            bArr4[i10] = (byte) ((i3 & 63) | 128);
        }
        this._outPtr = i4;
    }

    public void write(String str) throws IOException {
        write(str, 0, str.length());
    }

    public void write(String str, int i3, int i4) throws IOException {
        if (i4 >= 2) {
            if (this._surrogate > 0) {
                i4--;
                write(convertSurrogate(str.charAt(i3)));
                i3++;
            }
            int i5 = this._outPtr;
            byte[] bArr = this._outBuffer;
            int i6 = this._outBufferEnd;
            int i7 = i4 + r10;
            while (r10 < i7) {
                if (i5 >= i6) {
                    this._out.write(bArr, 0, i5);
                    i5 = 0;
                }
                int i8 = r10 + 1;
                char charAt = str.charAt(r10);
                if (charAt < 128) {
                    int i9 = i5 + 1;
                    bArr[i5] = (byte) charAt;
                    int i10 = i7 - i8;
                    int i11 = i6 - i9;
                    if (i10 > i11) {
                        i10 = i11;
                    }
                    int i12 = i10 + i8;
                    while (true) {
                        r10 = i8;
                        i5 = i9;
                        if (r10 >= i12) {
                            continue;
                            break;
                        }
                        i8 = r10 + 1;
                        charAt = str.charAt(r10);
                        if (charAt >= 128) {
                            break;
                        }
                        i9 = i5 + 1;
                        bArr[i5] = (byte) charAt;
                    }
                }
                if (charAt < 2048) {
                    int i13 = i5 + 1;
                    bArr[i5] = (byte) ((charAt >> 6) | 192);
                    i5 += 2;
                    bArr[i13] = (byte) ((charAt & '?') | 128);
                } else if (charAt < 55296 || charAt > 57343) {
                    bArr[i5] = (byte) ((charAt >> 12) | 224);
                    int i14 = i5 + 2;
                    bArr[i5 + 1] = (byte) (((charAt >> 6) & 63) | 128);
                    i5 += 3;
                    bArr[i14] = (byte) ((charAt & '?') | 128);
                } else {
                    if (charAt > 56319) {
                        this._outPtr = i5;
                        illegalSurrogate(charAt);
                    }
                    this._surrogate = charAt;
                    if (i8 >= i7) {
                        break;
                    }
                    r10 = i8 + 1;
                    int convertSurrogate = convertSurrogate(str.charAt(i8));
                    if (convertSurrogate > 1114111) {
                        this._outPtr = i5;
                        illegalSurrogate(convertSurrogate);
                    }
                    bArr[i5] = (byte) ((convertSurrogate >> 18) | 240);
                    bArr[i5 + 1] = (byte) (((convertSurrogate >> 12) & 63) | 128);
                    int i15 = i5 + 3;
                    bArr[i5 + 2] = (byte) (((convertSurrogate >> 6) & 63) | 128);
                    i5 += 4;
                    bArr[i15] = (byte) ((convertSurrogate & 63) | 128);
                }
                r10 = i8;
            }
            this._outPtr = i5;
        } else if (i4 == 1) {
            write((int) str.charAt(i3));
        }
    }
}
