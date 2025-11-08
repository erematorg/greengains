package com.bumptech.glide.util;

import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Queue;

public final class ExceptionPassthroughInputStream extends InputStream {
    @GuardedBy("POOL")
    private static final Queue<ExceptionPassthroughInputStream> POOL = Util.createQueue(0);
    private IOException exception;
    private InputStream wrapped;

    public static void clearQueue() {
        synchronized (POOL) {
            while (true) {
                try {
                    Queue<ExceptionPassthroughInputStream> queue = POOL;
                    if (!queue.isEmpty()) {
                        queue.remove();
                    }
                } finally {
                }
            }
        }
    }

    @NonNull
    public static ExceptionPassthroughInputStream obtain(@NonNull InputStream inputStream) {
        ExceptionPassthroughInputStream poll;
        Queue<ExceptionPassthroughInputStream> queue = POOL;
        synchronized (queue) {
            poll = queue.poll();
        }
        if (poll == null) {
            poll = new ExceptionPassthroughInputStream();
        }
        poll.setInputStream(inputStream);
        return poll;
    }

    public int available() throws IOException {
        return this.wrapped.available();
    }

    public void close() throws IOException {
        this.wrapped.close();
    }

    @Nullable
    public IOException getException() {
        return this.exception;
    }

    public void mark(int i3) {
        this.wrapped.mark(i3);
    }

    public boolean markSupported() {
        return this.wrapped.markSupported();
    }

    public int read() throws IOException {
        try {
            return this.wrapped.read();
        } catch (IOException e3) {
            this.exception = e3;
            throw e3;
        }
    }

    public void release() {
        this.exception = null;
        this.wrapped = null;
        Queue<ExceptionPassthroughInputStream> queue = POOL;
        synchronized (queue) {
            queue.offer(this);
        }
    }

    public synchronized void reset() throws IOException {
        this.wrapped.reset();
    }

    public void setInputStream(@NonNull InputStream inputStream) {
        this.wrapped = inputStream;
    }

    public long skip(long j2) throws IOException {
        try {
            return this.wrapped.skip(j2);
        } catch (IOException e3) {
            this.exception = e3;
            throw e3;
        }
    }

    public int read(byte[] bArr) throws IOException {
        try {
            return this.wrapped.read(bArr);
        } catch (IOException e3) {
            this.exception = e3;
            throw e3;
        }
    }

    public int read(byte[] bArr, int i3, int i4) throws IOException {
        try {
            return this.wrapped.read(bArr, i3, i4);
        } catch (IOException e3) {
            this.exception = e3;
            throw e3;
        }
    }
}
