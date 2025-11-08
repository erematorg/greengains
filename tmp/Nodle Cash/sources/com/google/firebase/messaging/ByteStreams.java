package com.google.firebase.messaging;

import androidx.collection.SieveCacheKt;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

final class ByteStreams {
    private static final int BUFFER_SIZE = 8192;
    private static final int MAX_ARRAY_LEN = 2147483639;
    private static final int TO_BYTE_ARRAY_DEQUE_SIZE = 20;

    private ByteStreams() {
    }

    private static byte[] combineBuffers(Queue<byte[]> queue, int i3) {
        if (queue.isEmpty()) {
            return new byte[0];
        }
        byte[] remove = queue.remove();
        if (remove.length == i3) {
            return remove;
        }
        int length = i3 - remove.length;
        byte[] copyOf = Arrays.copyOf(remove, i3);
        while (length > 0) {
            byte[] remove2 = queue.remove();
            int min = Math.min(length, remove2.length);
            System.arraycopy(remove2, 0, copyOf, i3 - length, min);
            length -= min;
        }
        return copyOf;
    }

    public static byte[] createBuffer() {
        return new byte[8192];
    }

    public static InputStream limit(InputStream inputStream, long j2) {
        return new LimitedInputStream(inputStream, j2);
    }

    private static int saturatedCast(long j2) {
        if (j2 > SieveCacheKt.NodeLinkMask) {
            return Integer.MAX_VALUE;
        }
        if (j2 < SieveCacheKt.NodeMetaAndPreviousMask) {
            return Integer.MIN_VALUE;
        }
        return (int) j2;
    }

    public static byte[] toByteArray(InputStream inputStream) throws IOException {
        return toByteArrayInternal(inputStream, new ArrayDeque(20), 0);
    }

    private static byte[] toByteArrayInternal(InputStream inputStream, Queue<byte[]> queue, int i3) throws IOException {
        int min = Math.min(8192, Math.max(128, Integer.highestOneBit(i3) * 2));
        while (i3 < MAX_ARRAY_LEN) {
            int min2 = Math.min(min, MAX_ARRAY_LEN - i3);
            byte[] bArr = new byte[min2];
            queue.add(bArr);
            int i4 = 0;
            while (i4 < min2) {
                int read = inputStream.read(bArr, i4, min2 - i4);
                if (read == -1) {
                    return combineBuffers(queue, i3);
                }
                i4 += read;
                i3 += read;
            }
            min = saturatedCast(((long) min) * ((long) (min < 4096 ? 4 : 2)));
        }
        if (inputStream.read() == -1) {
            return combineBuffers(queue, MAX_ARRAY_LEN);
        }
        throw new OutOfMemoryError("input is too large to fit in a byte array");
    }

    public static final class LimitedInputStream extends FilterInputStream {
        private long left;
        private long mark = -1;

        public LimitedInputStream(InputStream inputStream, long j2) {
            super(inputStream);
            this.left = j2;
        }

        public int available() throws IOException {
            return (int) Math.min((long) this.in.available(), this.left);
        }

        public synchronized void mark(int i3) {
            this.in.mark(i3);
            this.mark = this.left;
        }

        public int read() throws IOException {
            if (this.left == 0) {
                return -1;
            }
            int read = this.in.read();
            if (read != -1) {
                this.left--;
            }
            return read;
        }

        public synchronized void reset() throws IOException {
            if (!this.in.markSupported()) {
                throw new IOException("Mark not supported");
            } else if (this.mark != -1) {
                this.in.reset();
                this.left = this.mark;
            } else {
                throw new IOException("Mark not set");
            }
        }

        public long skip(long j2) throws IOException {
            long skip = this.in.skip(Math.min(j2, this.left));
            this.left -= skip;
            return skip;
        }

        public int read(byte[] bArr, int i3, int i4) throws IOException {
            long j2 = this.left;
            if (j2 == 0) {
                return -1;
            }
            int read = this.in.read(bArr, i3, (int) Math.min((long) i4, j2));
            if (read != -1) {
                this.left -= (long) read;
            }
            return read;
        }
    }
}
