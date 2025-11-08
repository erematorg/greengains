package com.google.crypto.tink.subtle;

import androidx.annotation.RequiresApi;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.NonWritableChannelException;
import java.nio.channels.SeekableByteChannel;
import java.security.GeneralSecurityException;
import java.util.Arrays;

@RequiresApi(24)
class StreamingAeadSeekableDecryptingChannel implements SeekableByteChannel {
    private static final int PLAINTEXT_SEGMENT_EXTRA_SIZE = 16;
    private final byte[] aad;
    private final SeekableByteChannel ciphertextChannel;
    private final long ciphertextChannelSize;
    private final int ciphertextOffset;
    private final ByteBuffer ciphertextSegment;
    private final int ciphertextSegmentSize;
    private int currentSegmentNr = -1;
    private final StreamSegmentDecrypter decrypter;
    private final int firstSegmentOffset;
    private final ByteBuffer header;
    private boolean headerRead = false;
    private boolean isCurrentSegmentDecrypted = false;
    private boolean isopen;
    private final int lastCiphertextSegmentSize;
    private final int numberOfSegments;
    private long plaintextPosition = 0;
    private final ByteBuffer plaintextSegment;
    private final int plaintextSegmentSize;
    private long plaintextSize;

    public StreamingAeadSeekableDecryptingChannel(NonceBasedStreamingAead nonceBasedStreamingAead, SeekableByteChannel seekableByteChannel, byte[] bArr) throws IOException, GeneralSecurityException {
        this.decrypter = nonceBasedStreamingAead.newStreamSegmentDecrypter();
        this.ciphertextChannel = seekableByteChannel;
        this.header = ByteBuffer.allocate(nonceBasedStreamingAead.getHeaderLength());
        int ciphertextSegmentSize2 = nonceBasedStreamingAead.getCiphertextSegmentSize();
        this.ciphertextSegmentSize = ciphertextSegmentSize2;
        this.ciphertextSegment = ByteBuffer.allocate(ciphertextSegmentSize2);
        int plaintextSegmentSize2 = nonceBasedStreamingAead.getPlaintextSegmentSize();
        this.plaintextSegmentSize = plaintextSegmentSize2;
        this.plaintextSegment = ByteBuffer.allocate(plaintextSegmentSize2 + 16);
        long size = seekableByteChannel.size();
        this.ciphertextChannelSize = size;
        this.aad = Arrays.copyOf(bArr, bArr.length);
        this.isopen = seekableByteChannel.isOpen();
        int i3 = (int) (size / ((long) ciphertextSegmentSize2));
        int i4 = (int) (size % ((long) ciphertextSegmentSize2));
        int ciphertextOverhead = nonceBasedStreamingAead.getCiphertextOverhead();
        if (i4 > 0) {
            this.numberOfSegments = i3 + 1;
            if (i4 >= ciphertextOverhead) {
                this.lastCiphertextSegmentSize = i4;
            } else {
                throw new IOException("Invalid ciphertext size");
            }
        } else {
            this.numberOfSegments = i3;
            this.lastCiphertextSegmentSize = ciphertextSegmentSize2;
        }
        int ciphertextOffset2 = nonceBasedStreamingAead.getCiphertextOffset();
        this.ciphertextOffset = ciphertextOffset2;
        int headerLength = ciphertextOffset2 - nonceBasedStreamingAead.getHeaderLength();
        this.firstSegmentOffset = headerLength;
        if (headerLength >= 0) {
            long j2 = (((long) this.numberOfSegments) * ((long) ciphertextOverhead)) + ((long) ciphertextOffset2);
            if (j2 <= size) {
                this.plaintextSize = size - j2;
                return;
            }
            throw new IOException("Ciphertext is too short");
        }
        throw new IOException("Invalid ciphertext offset or header length");
    }

    private int getSegmentNr(long j2) {
        return (int) ((j2 + ((long) this.ciphertextOffset)) / ((long) this.plaintextSegmentSize));
    }

    private boolean reachedEnd() {
        return this.isCurrentSegmentDecrypted && this.currentSegmentNr == this.numberOfSegments - 1 && this.plaintextSegment.remaining() == 0;
    }

    private boolean tryLoadSegment(int i3) throws IOException {
        int i4;
        if (i3 < 0 || i3 >= (i4 = this.numberOfSegments)) {
            throw new IOException("Invalid position");
        }
        boolean z2 = i3 == i4 - 1;
        if (i3 != this.currentSegmentNr) {
            int i5 = this.ciphertextSegmentSize;
            long j2 = ((long) i3) * ((long) i5);
            if (z2) {
                i5 = this.lastCiphertextSegmentSize;
            }
            if (i3 == 0) {
                int i6 = this.ciphertextOffset;
                i5 -= i6;
                j2 = (long) i6;
            }
            this.ciphertextChannel.position(j2);
            this.ciphertextSegment.clear();
            this.ciphertextSegment.limit(i5);
            this.currentSegmentNr = i3;
            this.isCurrentSegmentDecrypted = false;
        } else if (this.isCurrentSegmentDecrypted) {
            return true;
        }
        if (this.ciphertextSegment.remaining() > 0) {
            this.ciphertextChannel.read(this.ciphertextSegment);
        }
        if (this.ciphertextSegment.remaining() > 0) {
            return false;
        }
        this.ciphertextSegment.flip();
        this.plaintextSegment.clear();
        try {
            this.decrypter.decryptSegment(this.ciphertextSegment, i3, z2, this.plaintextSegment);
            this.plaintextSegment.flip();
            this.isCurrentSegmentDecrypted = true;
            return true;
        } catch (GeneralSecurityException e3) {
            this.currentSegmentNr = -1;
            throw new IOException("Failed to decrypt", e3);
        }
    }

    private boolean tryReadHeader() throws IOException {
        this.ciphertextChannel.position((long) (this.header.position() + this.firstSegmentOffset));
        this.ciphertextChannel.read(this.header);
        if (this.header.remaining() > 0) {
            return false;
        }
        this.header.flip();
        try {
            this.decrypter.init(this.header, this.aad);
            this.headerRead = true;
            return true;
        } catch (GeneralSecurityException e3) {
            throw new IOException(e3);
        }
    }

    public synchronized void close() throws IOException {
        this.ciphertextChannel.close();
        this.isopen = false;
    }

    public synchronized boolean isOpen() {
        return this.isopen;
    }

    public synchronized long position() {
        return this.plaintextPosition;
    }

    public synchronized int read(ByteBuffer byteBuffer, long j2) throws IOException {
        long position = position();
        try {
            position(j2);
        } finally {
            position(position);
        }
        return read(byteBuffer);
    }

    public long size() {
        return this.plaintextSize;
    }

    public synchronized String toString() {
        StringBuilder sb;
        String str;
        sb = new StringBuilder("StreamingAeadSeekableDecryptingChannel\nciphertextChannel");
        try {
            str = "position:" + this.ciphertextChannel.position();
        } catch (IOException unused) {
            str = "position: n/a";
        }
        sb.append(str);
        sb.append("\nciphertextChannelSize:");
        sb.append(this.ciphertextChannelSize);
        sb.append("\nplaintextSize:");
        sb.append(this.plaintextSize);
        sb.append("\nciphertextSegmentSize:");
        sb.append(this.ciphertextSegmentSize);
        sb.append("\nnumberOfSegments:");
        sb.append(this.numberOfSegments);
        sb.append("\nheaderRead:");
        sb.append(this.headerRead);
        sb.append("\nplaintextPosition:");
        sb.append(this.plaintextPosition);
        sb.append("\nHeader position:");
        sb.append(this.header.position());
        sb.append(" limit:");
        sb.append(this.header.position());
        sb.append("\ncurrentSegmentNr:");
        sb.append(this.currentSegmentNr);
        sb.append("\nciphertextSgement position:");
        sb.append(this.ciphertextSegment.position());
        sb.append(" limit:");
        sb.append(this.ciphertextSegment.limit());
        sb.append("\nisCurrentSegmentDecrypted:");
        sb.append(this.isCurrentSegmentDecrypted);
        sb.append("\nplaintextSegment position:");
        sb.append(this.plaintextSegment.position());
        sb.append(" limit:");
        sb.append(this.plaintextSegment.limit());
        return sb.toString();
    }

    public SeekableByteChannel truncate(long j2) throws NonWritableChannelException {
        throw new NonWritableChannelException();
    }

    public synchronized long verifiedSize() throws IOException {
        if (tryLoadSegment(this.numberOfSegments - 1)) {
        } else {
            throw new IOException("could not verify the size");
        }
        return this.plaintextSize;
    }

    public int write(ByteBuffer byteBuffer) throws NonWritableChannelException {
        throw new NonWritableChannelException();
    }

    @CanIgnoreReturnValue
    public synchronized SeekableByteChannel position(long j2) {
        this.plaintextPosition = j2;
        return this;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x009c, code lost:
        return r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int read(java.nio.ByteBuffer r7) throws java.io.IOException {
        /*
            r6 = this;
            monitor-enter(r6)
            boolean r0 = r6.isopen     // Catch:{ all -> 0x0012 }
            if (r0 == 0) goto L_0x009d
            boolean r0 = r6.headerRead     // Catch:{ all -> 0x0012 }
            if (r0 != 0) goto L_0x0015
            boolean r0 = r6.tryReadHeader()     // Catch:{ all -> 0x0012 }
            if (r0 != 0) goto L_0x0015
            monitor-exit(r6)
            r6 = 0
            return r6
        L_0x0012:
            r7 = move-exception
            goto L_0x00a3
        L_0x0015:
            int r0 = r7.position()     // Catch:{ all -> 0x0012 }
        L_0x0019:
            int r1 = r7.remaining()     // Catch:{ all -> 0x0012 }
            if (r1 <= 0) goto L_0x008b
            long r1 = r6.plaintextPosition     // Catch:{ all -> 0x0012 }
            long r3 = r6.plaintextSize     // Catch:{ all -> 0x0012 }
            int r3 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r3 >= 0) goto L_0x008b
            int r1 = r6.getSegmentNr(r1)     // Catch:{ all -> 0x0012 }
            if (r1 != 0) goto L_0x0031
            long r2 = r6.plaintextPosition     // Catch:{ all -> 0x0012 }
        L_0x002f:
            int r2 = (int) r2     // Catch:{ all -> 0x0012 }
            goto L_0x003c
        L_0x0031:
            long r2 = r6.plaintextPosition     // Catch:{ all -> 0x0012 }
            int r4 = r6.ciphertextOffset     // Catch:{ all -> 0x0012 }
            long r4 = (long) r4     // Catch:{ all -> 0x0012 }
            long r2 = r2 + r4
            int r4 = r6.plaintextSegmentSize     // Catch:{ all -> 0x0012 }
            long r4 = (long) r4     // Catch:{ all -> 0x0012 }
            long r2 = r2 % r4
            goto L_0x002f
        L_0x003c:
            boolean r1 = r6.tryLoadSegment(r1)     // Catch:{ all -> 0x0012 }
            if (r1 == 0) goto L_0x008b
            java.nio.ByteBuffer r1 = r6.plaintextSegment     // Catch:{ all -> 0x0012 }
            r1.position(r2)     // Catch:{ all -> 0x0012 }
            java.nio.ByteBuffer r1 = r6.plaintextSegment     // Catch:{ all -> 0x0012 }
            int r1 = r1.remaining()     // Catch:{ all -> 0x0012 }
            int r2 = r7.remaining()     // Catch:{ all -> 0x0012 }
            if (r1 > r2) goto L_0x0065
            long r1 = r6.plaintextPosition     // Catch:{ all -> 0x0012 }
            java.nio.ByteBuffer r3 = r6.plaintextSegment     // Catch:{ all -> 0x0012 }
            int r3 = r3.remaining()     // Catch:{ all -> 0x0012 }
            long r3 = (long) r3     // Catch:{ all -> 0x0012 }
            long r1 = r1 + r3
            r6.plaintextPosition = r1     // Catch:{ all -> 0x0012 }
            java.nio.ByteBuffer r1 = r6.plaintextSegment     // Catch:{ all -> 0x0012 }
            r7.put(r1)     // Catch:{ all -> 0x0012 }
            goto L_0x0019
        L_0x0065:
            int r1 = r7.remaining()     // Catch:{ all -> 0x0012 }
            java.nio.ByteBuffer r2 = r6.plaintextSegment     // Catch:{ all -> 0x0012 }
            java.nio.ByteBuffer r2 = r2.duplicate()     // Catch:{ all -> 0x0012 }
            int r3 = r2.position()     // Catch:{ all -> 0x0012 }
            int r3 = r3 + r1
            r2.limit(r3)     // Catch:{ all -> 0x0012 }
            r7.put(r2)     // Catch:{ all -> 0x0012 }
            long r2 = r6.plaintextPosition     // Catch:{ all -> 0x0012 }
            long r4 = (long) r1     // Catch:{ all -> 0x0012 }
            long r2 = r2 + r4
            r6.plaintextPosition = r2     // Catch:{ all -> 0x0012 }
            java.nio.ByteBuffer r2 = r6.plaintextSegment     // Catch:{ all -> 0x0012 }
            int r3 = r2.position()     // Catch:{ all -> 0x0012 }
            int r3 = r3 + r1
            r2.position(r3)     // Catch:{ all -> 0x0012 }
            goto L_0x0019
        L_0x008b:
            int r7 = r7.position()     // Catch:{ all -> 0x0012 }
            int r7 = r7 - r0
            if (r7 != 0) goto L_0x009b
            boolean r0 = r6.reachedEnd()     // Catch:{ all -> 0x0012 }
            if (r0 == 0) goto L_0x009b
            monitor-exit(r6)
            r6 = -1
            return r6
        L_0x009b:
            monitor-exit(r6)
            return r7
        L_0x009d:
            java.nio.channels.ClosedChannelException r7 = new java.nio.channels.ClosedChannelException     // Catch:{ all -> 0x0012 }
            r7.<init>()     // Catch:{ all -> 0x0012 }
            throw r7     // Catch:{ all -> 0x0012 }
        L_0x00a3:
            monitor-exit(r6)     // Catch:{ all -> 0x0012 }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.subtle.StreamingAeadSeekableDecryptingChannel.read(java.nio.ByteBuffer):int");
    }
}
