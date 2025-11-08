package com.bumptech.glide.load.resource.bitmap;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class RecyclableBufferedInputStream extends FilterInputStream {
    private volatile byte[] buf;
    private final ArrayPool byteArrayPool;
    private int count;
    private int marklimit;
    private int markpos;
    private int pos;

    public static class InvalidMarkException extends IOException {
        private static final long serialVersionUID = -4338378848813561757L;

        public InvalidMarkException(String str) {
            super(str);
        }
    }

    public RecyclableBufferedInputStream(@NonNull InputStream inputStream, @NonNull ArrayPool arrayPool) {
        this(inputStream, arrayPool, 65536);
    }

    private int fillbuf(InputStream inputStream, byte[] bArr) throws IOException {
        int i3;
        int i4 = this.markpos;
        if (i4 == -1 || this.pos - i4 >= (i3 = this.marklimit)) {
            int read = inputStream.read(bArr);
            if (read > 0) {
                this.markpos = -1;
                this.pos = 0;
                this.count = read;
            }
            return read;
        }
        if (i4 == 0 && i3 > bArr.length && this.count == bArr.length) {
            int length = bArr.length * 2;
            if (length <= i3) {
                i3 = length;
            }
            byte[] bArr2 = (byte[]) this.byteArrayPool.get(i3, byte[].class);
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            this.buf = bArr2;
            this.byteArrayPool.put(bArr);
            bArr = bArr2;
        } else if (i4 > 0) {
            System.arraycopy(bArr, i4, bArr, 0, bArr.length - i4);
        }
        int i5 = this.pos - this.markpos;
        this.pos = i5;
        this.markpos = 0;
        this.count = 0;
        int read2 = inputStream.read(bArr, i5, bArr.length - i5);
        int i6 = this.pos;
        if (read2 > 0) {
            i6 += read2;
        }
        this.count = i6;
        return read2;
    }

    private static IOException streamClosed() throws IOException {
        throw new IOException("BufferedInputStream is closed");
    }

    public synchronized int available() throws IOException {
        InputStream inputStream;
        inputStream = this.in;
        if (this.buf == null || inputStream == null) {
            throw streamClosed();
        }
        return (this.count - this.pos) + inputStream.available();
    }

    public void close() throws IOException {
        if (this.buf != null) {
            this.byteArrayPool.put(this.buf);
            this.buf = null;
        }
        InputStream inputStream = this.in;
        this.in = null;
        if (inputStream != null) {
            inputStream.close();
        }
    }

    public synchronized void fixMarkLimit() {
        this.marklimit = this.buf.length;
    }

    public synchronized void mark(int i3) {
        this.marklimit = Math.max(this.marklimit, i3);
        this.markpos = this.pos;
    }

    public boolean markSupported() {
        return true;
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:12:0x001a=Splitter:B:12:0x001a, B:28:0x003b=Splitter:B:28:0x003b} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int read() throws java.io.IOException {
        /*
            r5 = this;
            monitor-enter(r5)
            byte[] r0 = r5.buf     // Catch:{ all -> 0x0018 }
            java.io.InputStream r1 = r5.in     // Catch:{ all -> 0x0018 }
            if (r0 == 0) goto L_0x003b
            if (r1 == 0) goto L_0x003b
            int r2 = r5.pos     // Catch:{ all -> 0x0018 }
            int r3 = r5.count     // Catch:{ all -> 0x0018 }
            r4 = -1
            if (r2 < r3) goto L_0x001a
            int r1 = r5.fillbuf(r1, r0)     // Catch:{ all -> 0x0018 }
            if (r1 != r4) goto L_0x001a
            monitor-exit(r5)
            return r4
        L_0x0018:
            r0 = move-exception
            goto L_0x0040
        L_0x001a:
            byte[] r1 = r5.buf     // Catch:{ all -> 0x0018 }
            if (r0 == r1) goto L_0x0028
            byte[] r0 = r5.buf     // Catch:{ all -> 0x0018 }
            if (r0 == 0) goto L_0x0023
            goto L_0x0028
        L_0x0023:
            java.io.IOException r0 = streamClosed()     // Catch:{ all -> 0x0018 }
            throw r0     // Catch:{ all -> 0x0018 }
        L_0x0028:
            int r1 = r5.count     // Catch:{ all -> 0x0018 }
            int r2 = r5.pos     // Catch:{ all -> 0x0018 }
            int r1 = r1 - r2
            if (r1 <= 0) goto L_0x0039
            int r1 = r2 + 1
            r5.pos = r1     // Catch:{ all -> 0x0018 }
            byte r0 = r0[r2]     // Catch:{ all -> 0x0018 }
            r0 = r0 & 255(0xff, float:3.57E-43)
            monitor-exit(r5)
            return r0
        L_0x0039:
            monitor-exit(r5)
            return r4
        L_0x003b:
            java.io.IOException r0 = streamClosed()     // Catch:{ all -> 0x0018 }
            throw r0     // Catch:{ all -> 0x0018 }
        L_0x0040:
            monitor-exit(r5)     // Catch:{ all -> 0x0018 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream.read():int");
    }

    public synchronized void release() {
        if (this.buf != null) {
            this.byteArrayPool.put(this.buf);
            this.buf = null;
        }
    }

    public synchronized void reset() throws IOException {
        if (this.buf != null) {
            int i3 = this.markpos;
            if (-1 != i3) {
                this.pos = i3;
            } else {
                throw new InvalidMarkException("Mark has been invalidated, pos: " + this.pos + " markLimit: " + this.marklimit);
            }
        } else {
            throw new IOException("Stream is closed");
        }
    }

    public synchronized long skip(long j2) throws IOException {
        if (j2 < 1) {
            return 0;
        }
        byte[] bArr = this.buf;
        if (bArr != null) {
            InputStream inputStream = this.in;
            if (inputStream != null) {
                int i3 = this.count;
                int i4 = this.pos;
                if (((long) (i3 - i4)) >= j2) {
                    this.pos = (int) (((long) i4) + j2);
                    return j2;
                }
                long j3 = ((long) i3) - ((long) i4);
                this.pos = i3;
                if (this.markpos == -1 || j2 > ((long) this.marklimit)) {
                    long skip = inputStream.skip(j2 - j3);
                    if (skip > 0) {
                        this.markpos = -1;
                    }
                    return j3 + skip;
                } else if (fillbuf(inputStream, bArr) == -1) {
                    return j3;
                } else {
                    int i5 = this.count;
                    int i6 = this.pos;
                    if (((long) (i5 - i6)) >= j2 - j3) {
                        this.pos = (int) ((((long) i6) + j2) - j3);
                        return j2;
                    }
                    long j4 = (j3 + ((long) i5)) - ((long) i6);
                    this.pos = i5;
                    return j4;
                }
            } else {
                throw streamClosed();
            }
        } else {
            throw streamClosed();
        }
    }

    @VisibleForTesting
    public RecyclableBufferedInputStream(@NonNull InputStream inputStream, @NonNull ArrayPool arrayPool, int i3) {
        super(inputStream);
        this.markpos = -1;
        this.byteArrayPool = arrayPool;
        this.buf = (byte[]) arrayPool.get(i3, byte[].class);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0033, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0049, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0056, code lost:
        return r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int read(@androidx.annotation.NonNull byte[] r7, int r8, int r9) throws java.io.IOException {
        /*
            r6 = this;
            monitor-enter(r6)
            byte[] r0 = r6.buf     // Catch:{ all -> 0x0030 }
            if (r0 == 0) goto L_0x008d
            if (r9 != 0) goto L_0x000a
            monitor-exit(r6)
            r6 = 0
            return r6
        L_0x000a:
            java.io.InputStream r1 = r6.in     // Catch:{ all -> 0x0030 }
            if (r1 == 0) goto L_0x0088
            int r2 = r6.pos     // Catch:{ all -> 0x0030 }
            int r3 = r6.count     // Catch:{ all -> 0x0030 }
            if (r2 >= r3) goto L_0x0034
            int r4 = r3 - r2
            if (r4 < r9) goto L_0x001a
            r3 = r9
            goto L_0x001b
        L_0x001a:
            int r3 = r3 - r2
        L_0x001b:
            java.lang.System.arraycopy(r0, r2, r7, r8, r3)     // Catch:{ all -> 0x0030 }
            int r2 = r6.pos     // Catch:{ all -> 0x0030 }
            int r2 = r2 + r3
            r6.pos = r2     // Catch:{ all -> 0x0030 }
            if (r3 == r9) goto L_0x0032
            int r2 = r1.available()     // Catch:{ all -> 0x0030 }
            if (r2 != 0) goto L_0x002c
            goto L_0x0032
        L_0x002c:
            int r8 = r8 + r3
            int r2 = r9 - r3
            goto L_0x0035
        L_0x0030:
            r7 = move-exception
            goto L_0x0092
        L_0x0032:
            monitor-exit(r6)
            return r3
        L_0x0034:
            r2 = r9
        L_0x0035:
            int r3 = r6.markpos     // Catch:{ all -> 0x0030 }
            r4 = -1
            if (r3 != r4) goto L_0x004a
            int r3 = r0.length     // Catch:{ all -> 0x0030 }
            if (r2 < r3) goto L_0x004a
            int r3 = r1.read(r7, r8, r2)     // Catch:{ all -> 0x0030 }
            if (r3 != r4) goto L_0x0078
            if (r2 != r9) goto L_0x0046
            goto L_0x0048
        L_0x0046:
            int r4 = r9 - r2
        L_0x0048:
            monitor-exit(r6)
            return r4
        L_0x004a:
            int r3 = r6.fillbuf(r1, r0)     // Catch:{ all -> 0x0030 }
            if (r3 != r4) goto L_0x0057
            if (r2 != r9) goto L_0x0053
            goto L_0x0055
        L_0x0053:
            int r4 = r9 - r2
        L_0x0055:
            monitor-exit(r6)
            return r4
        L_0x0057:
            byte[] r3 = r6.buf     // Catch:{ all -> 0x0030 }
            if (r0 == r3) goto L_0x0065
            byte[] r0 = r6.buf     // Catch:{ all -> 0x0030 }
            if (r0 == 0) goto L_0x0060
            goto L_0x0065
        L_0x0060:
            java.io.IOException r7 = streamClosed()     // Catch:{ all -> 0x0030 }
            throw r7     // Catch:{ all -> 0x0030 }
        L_0x0065:
            int r3 = r6.count     // Catch:{ all -> 0x0030 }
            int r4 = r6.pos     // Catch:{ all -> 0x0030 }
            int r5 = r3 - r4
            if (r5 < r2) goto L_0x006f
            r3 = r2
            goto L_0x0070
        L_0x006f:
            int r3 = r3 - r4
        L_0x0070:
            java.lang.System.arraycopy(r0, r4, r7, r8, r3)     // Catch:{ all -> 0x0030 }
            int r4 = r6.pos     // Catch:{ all -> 0x0030 }
            int r4 = r4 + r3
            r6.pos = r4     // Catch:{ all -> 0x0030 }
        L_0x0078:
            int r2 = r2 - r3
            if (r2 != 0) goto L_0x007d
            monitor-exit(r6)
            return r9
        L_0x007d:
            int r4 = r1.available()     // Catch:{ all -> 0x0030 }
            if (r4 != 0) goto L_0x0086
            int r9 = r9 - r2
            monitor-exit(r6)
            return r9
        L_0x0086:
            int r8 = r8 + r3
            goto L_0x0035
        L_0x0088:
            java.io.IOException r7 = streamClosed()     // Catch:{ all -> 0x0030 }
            throw r7     // Catch:{ all -> 0x0030 }
        L_0x008d:
            java.io.IOException r7 = streamClosed()     // Catch:{ all -> 0x0030 }
            throw r7     // Catch:{ all -> 0x0030 }
        L_0x0092:
            monitor-exit(r6)     // Catch:{ all -> 0x0030 }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream.read(byte[], int, int):int");
    }
}
