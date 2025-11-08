package com.google.crypto.tink.subtle;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import javax.annotation.concurrent.GuardedBy;

public final class RewindableReadableByteChannel implements ReadableByteChannel {
    @GuardedBy("this")
    final ReadableByteChannel baseChannel;
    @GuardedBy("this")
    ByteBuffer buffer = null;
    @GuardedBy("this")
    boolean canRewind = true;
    @GuardedBy("this")
    boolean directRead = false;

    public RewindableReadableByteChannel(ReadableByteChannel readableByteChannel) {
        this.baseChannel = readableByteChannel;
    }

    private synchronized void setBufferLimit(int i3) {
        try {
            if (this.buffer.capacity() < i3) {
                int position = this.buffer.position();
                ByteBuffer allocate = ByteBuffer.allocate(Math.max(this.buffer.capacity() * 2, i3));
                this.buffer.rewind();
                allocate.put(this.buffer);
                allocate.position(position);
                this.buffer = allocate;
            }
            this.buffer.limit(i3);
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public synchronized void close() throws IOException {
        this.canRewind = false;
        this.directRead = true;
        this.baseChannel.close();
    }

    public synchronized void disableRewinding() {
        this.canRewind = false;
    }

    public synchronized boolean isOpen() {
        return this.baseChannel.isOpen();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0045, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0078, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00cb, code lost:
        return r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int read(java.nio.ByteBuffer r7) throws java.io.IOException {
        /*
            r6 = this;
            monitor-enter(r6)
            boolean r0 = r6.directRead     // Catch:{ all -> 0x000d }
            if (r0 == 0) goto L_0x0010
            java.nio.channels.ReadableByteChannel r0 = r6.baseChannel     // Catch:{ all -> 0x000d }
            int r7 = r0.read(r7)     // Catch:{ all -> 0x000d }
            monitor-exit(r6)
            return r7
        L_0x000d:
            r7 = move-exception
            goto L_0x00cc
        L_0x0010:
            int r0 = r7.remaining()     // Catch:{ all -> 0x000d }
            if (r0 != 0) goto L_0x0019
            monitor-exit(r6)
            r6 = 0
            return r6
        L_0x0019:
            java.nio.ByteBuffer r1 = r6.buffer     // Catch:{ all -> 0x000d }
            r2 = 1
            if (r1 != 0) goto L_0x0046
            boolean r1 = r6.canRewind     // Catch:{ all -> 0x000d }
            if (r1 != 0) goto L_0x002c
            r6.directRead = r2     // Catch:{ all -> 0x000d }
            java.nio.channels.ReadableByteChannel r0 = r6.baseChannel     // Catch:{ all -> 0x000d }
            int r7 = r0.read(r7)     // Catch:{ all -> 0x000d }
            monitor-exit(r6)
            return r7
        L_0x002c:
            java.nio.ByteBuffer r0 = java.nio.ByteBuffer.allocate(r0)     // Catch:{ all -> 0x000d }
            r6.buffer = r0     // Catch:{ all -> 0x000d }
            java.nio.channels.ReadableByteChannel r1 = r6.baseChannel     // Catch:{ all -> 0x000d }
            int r0 = r1.read(r0)     // Catch:{ all -> 0x000d }
            java.nio.ByteBuffer r1 = r6.buffer     // Catch:{ all -> 0x000d }
            r1.flip()     // Catch:{ all -> 0x000d }
            if (r0 <= 0) goto L_0x0044
            java.nio.ByteBuffer r1 = r6.buffer     // Catch:{ all -> 0x000d }
            r7.put(r1)     // Catch:{ all -> 0x000d }
        L_0x0044:
            monitor-exit(r6)
            return r0
        L_0x0046:
            int r1 = r1.remaining()     // Catch:{ all -> 0x000d }
            r3 = 0
            if (r1 < r0) goto L_0x0079
            java.nio.ByteBuffer r1 = r6.buffer     // Catch:{ all -> 0x000d }
            int r1 = r1.limit()     // Catch:{ all -> 0x000d }
            java.nio.ByteBuffer r4 = r6.buffer     // Catch:{ all -> 0x000d }
            int r5 = r4.position()     // Catch:{ all -> 0x000d }
            int r5 = r5 + r0
            r4.limit(r5)     // Catch:{ all -> 0x000d }
            java.nio.ByteBuffer r4 = r6.buffer     // Catch:{ all -> 0x000d }
            r7.put(r4)     // Catch:{ all -> 0x000d }
            java.nio.ByteBuffer r7 = r6.buffer     // Catch:{ all -> 0x000d }
            r7.limit(r1)     // Catch:{ all -> 0x000d }
            boolean r7 = r6.canRewind     // Catch:{ all -> 0x000d }
            if (r7 != 0) goto L_0x0077
            java.nio.ByteBuffer r7 = r6.buffer     // Catch:{ all -> 0x000d }
            boolean r7 = r7.hasRemaining()     // Catch:{ all -> 0x000d }
            if (r7 != 0) goto L_0x0077
            r6.buffer = r3     // Catch:{ all -> 0x000d }
            r6.directRead = r2     // Catch:{ all -> 0x000d }
        L_0x0077:
            monitor-exit(r6)
            return r0
        L_0x0079:
            java.nio.ByteBuffer r1 = r6.buffer     // Catch:{ all -> 0x000d }
            int r1 = r1.remaining()     // Catch:{ all -> 0x000d }
            int r0 = r0 - r1
            java.nio.ByteBuffer r4 = r6.buffer     // Catch:{ all -> 0x000d }
            int r4 = r4.position()     // Catch:{ all -> 0x000d }
            java.nio.ByteBuffer r5 = r6.buffer     // Catch:{ all -> 0x000d }
            int r5 = r5.limit()     // Catch:{ all -> 0x000d }
            int r0 = r0 + r5
            r6.setBufferLimit(r0)     // Catch:{ all -> 0x000d }
            java.nio.ByteBuffer r0 = r6.buffer     // Catch:{ all -> 0x000d }
            r0.position(r5)     // Catch:{ all -> 0x000d }
            java.nio.channels.ReadableByteChannel r0 = r6.baseChannel     // Catch:{ all -> 0x000d }
            java.nio.ByteBuffer r5 = r6.buffer     // Catch:{ all -> 0x000d }
            int r0 = r0.read(r5)     // Catch:{ all -> 0x000d }
            java.nio.ByteBuffer r5 = r6.buffer     // Catch:{ all -> 0x000d }
            r5.flip()     // Catch:{ all -> 0x000d }
            java.nio.ByteBuffer r5 = r6.buffer     // Catch:{ all -> 0x000d }
            r5.position(r4)     // Catch:{ all -> 0x000d }
            java.nio.ByteBuffer r5 = r6.buffer     // Catch:{ all -> 0x000d }
            r7.put(r5)     // Catch:{ all -> 0x000d }
            if (r1 != 0) goto L_0x00b3
            if (r0 >= 0) goto L_0x00b3
            monitor-exit(r6)
            r6 = -1
            return r6
        L_0x00b3:
            java.nio.ByteBuffer r7 = r6.buffer     // Catch:{ all -> 0x000d }
            int r7 = r7.position()     // Catch:{ all -> 0x000d }
            int r7 = r7 - r4
            boolean r0 = r6.canRewind     // Catch:{ all -> 0x000d }
            if (r0 != 0) goto L_0x00ca
            java.nio.ByteBuffer r0 = r6.buffer     // Catch:{ all -> 0x000d }
            boolean r0 = r0.hasRemaining()     // Catch:{ all -> 0x000d }
            if (r0 != 0) goto L_0x00ca
            r6.buffer = r3     // Catch:{ all -> 0x000d }
            r6.directRead = r2     // Catch:{ all -> 0x000d }
        L_0x00ca:
            monitor-exit(r6)
            return r7
        L_0x00cc:
            monitor-exit(r6)     // Catch:{ all -> 0x000d }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.subtle.RewindableReadableByteChannel.read(java.nio.ByteBuffer):int");
    }

    public synchronized void rewind() throws IOException {
        if (this.canRewind) {
            ByteBuffer byteBuffer = this.buffer;
            if (byteBuffer != null) {
                byteBuffer.position(0);
            }
        } else {
            throw new IOException("Cannot rewind anymore.");
        }
    }
}
