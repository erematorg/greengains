package com.bumptech.glide.load.data;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import java.io.IOException;
import java.io.OutputStream;

public final class BufferedOutputStream extends OutputStream {
    private ArrayPool arrayPool;
    private byte[] buffer;
    private int index;
    @NonNull
    private final OutputStream out;

    public BufferedOutputStream(@NonNull OutputStream outputStream, @NonNull ArrayPool arrayPool2) {
        this(outputStream, arrayPool2, 65536);
    }

    private void flushBuffer() throws IOException {
        int i3 = this.index;
        if (i3 > 0) {
            this.out.write(this.buffer, 0, i3);
            this.index = 0;
        }
    }

    private void maybeFlushBuffer() throws IOException {
        if (this.index == this.buffer.length) {
            flushBuffer();
        }
    }

    private void release() {
        byte[] bArr = this.buffer;
        if (bArr != null) {
            this.arrayPool.put(bArr);
            this.buffer = null;
        }
    }

    /* JADX INFO: finally extract failed */
    public void close() throws IOException {
        try {
            flush();
            this.out.close();
            release();
        } catch (Throwable th) {
            this.out.close();
            throw th;
        }
    }

    public void flush() throws IOException {
        flushBuffer();
        this.out.flush();
    }

    public void write(int i3) throws IOException {
        byte[] bArr = this.buffer;
        int i4 = this.index;
        this.index = i4 + 1;
        bArr[i4] = (byte) i3;
        maybeFlushBuffer();
    }

    @VisibleForTesting
    public BufferedOutputStream(@NonNull OutputStream outputStream, ArrayPool arrayPool2, int i3) {
        this.out = outputStream;
        this.arrayPool = arrayPool2;
        this.buffer = (byte[]) arrayPool2.get(i3, byte[].class);
    }

    public void write(@NonNull byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    public void write(@NonNull byte[] bArr, int i3, int i4) throws IOException {
        int i5 = 0;
        do {
            int i6 = i4 - i5;
            int i7 = i3 + i5;
            int i8 = this.index;
            if (i8 != 0 || i6 < this.buffer.length) {
                int min = Math.min(i6, this.buffer.length - i8);
                System.arraycopy(bArr, i7, this.buffer, this.index, min);
                this.index += min;
                i5 += min;
                maybeFlushBuffer();
            } else {
                this.out.write(bArr, i7, i6);
                return;
            }
        } while (i5 < i4);
    }
}
