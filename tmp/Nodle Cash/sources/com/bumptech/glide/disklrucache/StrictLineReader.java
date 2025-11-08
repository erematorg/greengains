package com.bumptech.glide.disklrucache;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

class StrictLineReader implements Closeable {
    private static final byte CR = 13;
    private static final byte LF = 10;
    private byte[] buf;
    /* access modifiers changed from: private */
    public final Charset charset;
    private int end;
    private final InputStream in;
    private int pos;

    public StrictLineReader(InputStream inputStream, Charset charset2) {
        this(inputStream, 8192, charset2);
    }

    private void fillBuf() throws IOException {
        InputStream inputStream = this.in;
        byte[] bArr = this.buf;
        int read = inputStream.read(bArr, 0, bArr.length);
        if (read != -1) {
            this.pos = 0;
            this.end = read;
            return;
        }
        throw new EOFException();
    }

    public void close() throws IOException {
        synchronized (this.in) {
            try {
                if (this.buf != null) {
                    this.buf = null;
                    this.in.close();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public boolean hasUnterminatedLine() {
        return this.end == -1;
    }

    public String readLine() throws IOException {
        int i3;
        byte[] bArr;
        int i4;
        synchronized (this.in) {
            try {
                if (this.buf != null) {
                    if (this.pos >= this.end) {
                        fillBuf();
                    }
                    for (int i5 = this.pos; i5 != this.end; i5++) {
                        byte[] bArr2 = this.buf;
                        if (bArr2[i5] == 10) {
                            int i6 = this.pos;
                            if (i5 != i6) {
                                i4 = i5 - 1;
                                if (bArr2[i4] == 13) {
                                    String str = new String(bArr2, i6, i4 - i6, this.charset.name());
                                    this.pos = i5 + 1;
                                    return str;
                                }
                            }
                            i4 = i5;
                            String str2 = new String(bArr2, i6, i4 - i6, this.charset.name());
                            this.pos = i5 + 1;
                            return str2;
                        }
                    }
                    AnonymousClass1 r12 = new ByteArrayOutputStream((this.end - this.pos) + 80) {
                        public String toString() {
                            int i3 = this.count;
                            if (i3 > 0 && this.buf[i3 - 1] == 13) {
                                i3--;
                            }
                            try {
                                return new String(this.buf, 0, i3, StrictLineReader.this.charset.name());
                            } catch (UnsupportedEncodingException e3) {
                                throw new AssertionError(e3);
                            }
                        }
                    };
                    loop1:
                    while (true) {
                        byte[] bArr3 = this.buf;
                        int i7 = this.pos;
                        r12.write(bArr3, i7, this.end - i7);
                        this.end = -1;
                        fillBuf();
                        i3 = this.pos;
                        while (true) {
                            if (i3 != this.end) {
                                bArr = this.buf;
                                if (bArr[i3] == 10) {
                                    break loop1;
                                }
                                i3++;
                            }
                        }
                    }
                    int i8 = this.pos;
                    if (i3 != i8) {
                        r12.write(bArr, i8, i3 - i8);
                    }
                    this.pos = i3 + 1;
                    String r7 = r12.toString();
                    return r7;
                }
                throw new IOException("LineReader is closed");
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public StrictLineReader(InputStream inputStream, int i3, Charset charset2) {
        if (inputStream == null || charset2 == null) {
            throw null;
        } else if (i3 < 0) {
            throw new IllegalArgumentException("capacity <= 0");
        } else if (charset2.equals(Util.US_ASCII)) {
            this.in = inputStream;
            this.charset = charset2;
            this.buf = new byte[i3];
        } else {
            throw new IllegalArgumentException("Unsupported encoding");
        }
    }
}
