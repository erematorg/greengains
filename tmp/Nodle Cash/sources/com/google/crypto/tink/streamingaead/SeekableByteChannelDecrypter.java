package com.google.crypto.tink.streamingaead;

import androidx.annotation.RequiresApi;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.StreamingAead;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.NonWritableChannelException;
import java.nio.channels.SeekableByteChannel;
import java.util.ArrayDeque;
import java.util.Deque;
import javax.annotation.concurrent.GuardedBy;

@RequiresApi(24)
final class SeekableByteChannelDecrypter implements SeekableByteChannel {
    byte[] associatedData;
    @GuardedBy("this")
    SeekableByteChannel attemptingChannel = null;
    @GuardedBy("this")
    long cachedPosition;
    @GuardedBy("this")
    SeekableByteChannel ciphertextChannel;
    @GuardedBy("this")
    SeekableByteChannel matchingChannel = null;
    Deque<StreamingAead> remainingPrimitives = new ArrayDeque();
    @GuardedBy("this")
    long startingPosition;

    public SeekableByteChannelDecrypter(PrimitiveSet<StreamingAead> primitiveSet, SeekableByteChannel seekableByteChannel, byte[] bArr) throws IOException {
        for (PrimitiveSet.Entry<StreamingAead> primitive : primitiveSet.getRawPrimitives()) {
            this.remainingPrimitives.add((StreamingAead) primitive.getPrimitive());
        }
        this.ciphertextChannel = seekableByteChannel;
        this.cachedPosition = -1;
        this.startingPosition = seekableByteChannel.position();
        this.associatedData = (byte[]) bArr.clone();
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:1:0x0001 */
    /* JADX WARNING: Removed duplicated region for block: B:1:0x0001 A[LOOP:0: B:1:0x0001->B:5:0x0018, LOOP_START, SYNTHETIC] */
    @javax.annotation.concurrent.GuardedBy("this")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized java.nio.channels.SeekableByteChannel nextAttemptingChannel() throws java.io.IOException {
        /*
            r5 = this;
            monitor-enter(r5)
        L_0x0001:
            java.util.Deque<com.google.crypto.tink.StreamingAead> r0 = r5.remainingPrimitives     // Catch:{ all -> 0x002c }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x002c }
            if (r0 != 0) goto L_0x0030
            java.nio.channels.SeekableByteChannel r0 = r5.ciphertextChannel     // Catch:{ all -> 0x002c }
            long r1 = r5.startingPosition     // Catch:{ all -> 0x002c }
            r0.position(r1)     // Catch:{ all -> 0x002c }
            java.util.Deque<com.google.crypto.tink.StreamingAead> r0 = r5.remainingPrimitives     // Catch:{ all -> 0x002c }
            java.lang.Object r0 = r0.removeFirst()     // Catch:{ all -> 0x002c }
            com.google.crypto.tink.StreamingAead r0 = (com.google.crypto.tink.StreamingAead) r0     // Catch:{ all -> 0x002c }
            java.nio.channels.SeekableByteChannel r1 = r5.ciphertextChannel     // Catch:{ GeneralSecurityException -> 0x0001 }
            byte[] r2 = r5.associatedData     // Catch:{ GeneralSecurityException -> 0x0001 }
            java.nio.channels.SeekableByteChannel r0 = r0.newSeekableDecryptingChannel(r1, r2)     // Catch:{ GeneralSecurityException -> 0x0001 }
            long r1 = r5.cachedPosition     // Catch:{ GeneralSecurityException -> 0x0001 }
            r3 = 0
            int r3 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r3 < 0) goto L_0x002e
            r0.position(r1)     // Catch:{ GeneralSecurityException -> 0x0001 }
            goto L_0x002e
        L_0x002c:
            r0 = move-exception
            goto L_0x0038
        L_0x002e:
            monitor-exit(r5)
            return r0
        L_0x0030:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x002c }
            java.lang.String r1 = "No matching key found for the ciphertext in the stream."
            r0.<init>(r1)     // Catch:{ all -> 0x002c }
            throw r0     // Catch:{ all -> 0x002c }
        L_0x0038:
            monitor-exit(r5)     // Catch:{ all -> 0x002c }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.streamingaead.SeekableByteChannelDecrypter.nextAttemptingChannel():java.nio.channels.SeekableByteChannel");
    }

    @GuardedBy("this")
    public synchronized void close() throws IOException {
        this.ciphertextChannel.close();
    }

    @GuardedBy("this")
    public synchronized boolean isOpen() {
        return this.ciphertextChannel.isOpen();
    }

    @GuardedBy("this")
    @CanIgnoreReturnValue
    public synchronized SeekableByteChannel position(long j2) throws IOException {
        try {
            SeekableByteChannel seekableByteChannel = this.matchingChannel;
            if (seekableByteChannel != null) {
                seekableByteChannel.position(j2);
            } else if (j2 >= 0) {
                this.cachedPosition = j2;
                SeekableByteChannel seekableByteChannel2 = this.attemptingChannel;
                if (seekableByteChannel2 != null) {
                    seekableByteChannel2.position(j2);
                }
            } else {
                throw new IllegalArgumentException("Position must be non-negative");
            }
        } catch (Throwable th) {
            throw th;
        }
        return this;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:23|24|27|28) */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0032, code lost:
        return r0;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0033 */
    @javax.annotation.concurrent.GuardedBy("this")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int read(java.nio.ByteBuffer r4) throws java.io.IOException {
        /*
            r3 = this;
            monitor-enter(r3)
            int r0 = r4.remaining()     // Catch:{ all -> 0x0014 }
            r1 = 0
            if (r0 != 0) goto L_0x000a
            monitor-exit(r3)
            return r1
        L_0x000a:
            java.nio.channels.SeekableByteChannel r0 = r3.matchingChannel     // Catch:{ all -> 0x0014 }
            if (r0 == 0) goto L_0x0016
            int r4 = r0.read(r4)     // Catch:{ all -> 0x0014 }
            monitor-exit(r3)
            return r4
        L_0x0014:
            r4 = move-exception
            goto L_0x003a
        L_0x0016:
            java.nio.channels.SeekableByteChannel r0 = r3.attemptingChannel     // Catch:{ all -> 0x0014 }
            if (r0 != 0) goto L_0x0020
            java.nio.channels.SeekableByteChannel r0 = r3.nextAttemptingChannel()     // Catch:{ all -> 0x0014 }
            r3.attemptingChannel = r0     // Catch:{ all -> 0x0014 }
        L_0x0020:
            java.nio.channels.SeekableByteChannel r0 = r3.attemptingChannel     // Catch:{ IOException -> 0x0033 }
            int r0 = r0.read(r4)     // Catch:{ IOException -> 0x0033 }
            if (r0 != 0) goto L_0x002a
            monitor-exit(r3)
            return r1
        L_0x002a:
            java.nio.channels.SeekableByteChannel r2 = r3.attemptingChannel     // Catch:{ IOException -> 0x0033 }
            r3.matchingChannel = r2     // Catch:{ IOException -> 0x0033 }
            r2 = 0
            r3.attemptingChannel = r2     // Catch:{ IOException -> 0x0033 }
            monitor-exit(r3)
            return r0
        L_0x0033:
            java.nio.channels.SeekableByteChannel r0 = r3.nextAttemptingChannel()     // Catch:{ all -> 0x0014 }
            r3.attemptingChannel = r0     // Catch:{ all -> 0x0014 }
            goto L_0x0020
        L_0x003a:
            monitor-exit(r3)     // Catch:{ all -> 0x0014 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.streamingaead.SeekableByteChannelDecrypter.read(java.nio.ByteBuffer):int");
    }

    @GuardedBy("this")
    public synchronized long size() throws IOException {
        SeekableByteChannel seekableByteChannel;
        seekableByteChannel = this.matchingChannel;
        if (seekableByteChannel != null) {
        } else {
            throw new IOException("Cannot determine size before first read()-call.");
        }
        return seekableByteChannel.size();
    }

    public SeekableByteChannel truncate(long j2) throws IOException {
        throw new NonWritableChannelException();
    }

    public int write(ByteBuffer byteBuffer) throws IOException {
        throw new NonWritableChannelException();
    }

    @GuardedBy("this")
    public synchronized long position() throws IOException {
        SeekableByteChannel seekableByteChannel = this.matchingChannel;
        if (seekableByteChannel != null) {
            return seekableByteChannel.position();
        }
        return this.cachedPosition;
    }
}
