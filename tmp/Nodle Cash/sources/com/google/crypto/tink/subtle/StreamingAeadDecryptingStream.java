package com.google.crypto.tink.subtle;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Arrays;

class StreamingAeadDecryptingStream extends FilterInputStream {
    private static final int PLAINTEXT_SEGMENT_EXTRA_SIZE = 16;
    private final byte[] aad;
    private final ByteBuffer ciphertextSegment;
    private final int ciphertextSegmentSize;
    private final StreamSegmentDecrypter decrypter;
    private boolean decryptionErrorOccured = false;
    private boolean endOfCiphertext = false;
    private boolean endOfPlaintext = false;
    private final int firstCiphertextSegmentSize;
    private final int headerLength;
    private boolean headerRead = false;
    private final ByteBuffer plaintextSegment;
    private int segmentNr = 0;

    public StreamingAeadDecryptingStream(NonceBasedStreamingAead nonceBasedStreamingAead, InputStream inputStream, byte[] bArr) throws GeneralSecurityException, IOException {
        super(inputStream);
        this.decrypter = nonceBasedStreamingAead.newStreamSegmentDecrypter();
        this.headerLength = nonceBasedStreamingAead.getHeaderLength();
        this.aad = Arrays.copyOf(bArr, bArr.length);
        int ciphertextSegmentSize2 = nonceBasedStreamingAead.getCiphertextSegmentSize();
        this.ciphertextSegmentSize = ciphertextSegmentSize2;
        ByteBuffer allocate = ByteBuffer.allocate(ciphertextSegmentSize2 + 1);
        this.ciphertextSegment = allocate;
        allocate.limit(0);
        this.firstCiphertextSegmentSize = ciphertextSegmentSize2 - nonceBasedStreamingAead.getCiphertextOffset();
        ByteBuffer allocate2 = ByteBuffer.allocate(nonceBasedStreamingAead.getPlaintextSegmentSize() + 16);
        this.plaintextSegment = allocate2;
        allocate2.limit(0);
    }

    private void loadSegment() throws IOException {
        byte b3;
        while (!this.endOfCiphertext && this.ciphertextSegment.remaining() > 0) {
            int read = this.in.read(this.ciphertextSegment.array(), this.ciphertextSegment.position(), this.ciphertextSegment.remaining());
            if (read > 0) {
                ByteBuffer byteBuffer = this.ciphertextSegment;
                byteBuffer.position(byteBuffer.position() + read);
            } else if (read == -1) {
                this.endOfCiphertext = true;
            } else if (read == 0) {
                throw new IOException("Could not read bytes from the ciphertext stream");
            }
        }
        if (!this.endOfCiphertext) {
            ByteBuffer byteBuffer2 = this.ciphertextSegment;
            b3 = byteBuffer2.get(byteBuffer2.position() - 1);
            ByteBuffer byteBuffer3 = this.ciphertextSegment;
            byteBuffer3.position(byteBuffer3.position() - 1);
        } else {
            b3 = 0;
        }
        this.ciphertextSegment.flip();
        this.plaintextSegment.clear();
        try {
            this.decrypter.decryptSegment(this.ciphertextSegment, this.segmentNr, this.endOfCiphertext, this.plaintextSegment);
            this.segmentNr++;
            this.plaintextSegment.flip();
            this.ciphertextSegment.clear();
            if (!this.endOfCiphertext) {
                this.ciphertextSegment.clear();
                this.ciphertextSegment.limit(this.ciphertextSegmentSize + 1);
                this.ciphertextSegment.put(b3);
            }
        } catch (GeneralSecurityException e3) {
            setDecryptionErrorOccured();
            throw new IOException(e3.getMessage() + "\n" + toString() + "\nsegmentNr:" + this.segmentNr + " endOfCiphertext:" + this.endOfCiphertext, e3);
        }
    }

    private void readHeader() throws IOException {
        if (!this.headerRead) {
            ByteBuffer allocate = ByteBuffer.allocate(this.headerLength);
            while (allocate.remaining() > 0) {
                int read = this.in.read(allocate.array(), allocate.position(), allocate.remaining());
                if (read == -1) {
                    setDecryptionErrorOccured();
                    throw new IOException("Ciphertext is too short");
                } else if (read != 0) {
                    allocate.position(allocate.position() + read);
                } else {
                    throw new IOException("Could not read bytes from the ciphertext stream");
                }
            }
            allocate.flip();
            try {
                this.decrypter.init(allocate, this.aad);
                this.headerRead = true;
            } catch (GeneralSecurityException e3) {
                throw new IOException(e3);
            }
        } else {
            setDecryptionErrorOccured();
            throw new IOException("Decryption failed.");
        }
    }

    private void setDecryptionErrorOccured() {
        this.decryptionErrorOccured = true;
        this.plaintextSegment.limit(0);
    }

    public synchronized int available() {
        return this.plaintextSegment.remaining();
    }

    public synchronized void close() throws IOException {
        super.close();
    }

    public synchronized void mark(int i3) {
    }

    public boolean markSupported() {
        return false;
    }

    public int read() throws IOException {
        byte[] bArr = new byte[1];
        int read = read(bArr, 0, 1);
        if (read == 1) {
            return bArr[0] & 255;
        }
        if (read == -1) {
            return read;
        }
        throw new IOException("Reading failed");
    }

    public long skip(long j2) throws IOException {
        int read;
        long j3 = (long) this.ciphertextSegmentSize;
        if (j2 <= 0) {
            return 0;
        }
        int min = (int) Math.min(j3, j2);
        byte[] bArr = new byte[min];
        long j4 = j2;
        while (j4 > 0 && (read = read(bArr, 0, (int) Math.min((long) min, j4))) > 0) {
            j4 -= (long) read;
        }
        return j2 - j4;
    }

    public synchronized String toString() {
        return "StreamingAeadDecryptingStream\nsegmentNr:" + this.segmentNr + "\nciphertextSegmentSize:" + this.ciphertextSegmentSize + "\nheaderRead:" + this.headerRead + "\nendOfCiphertext:" + this.endOfCiphertext + "\nendOfPlaintext:" + this.endOfPlaintext + "\ndecryptionErrorOccured:" + this.decryptionErrorOccured + "\nciphertextSgement position:" + this.ciphertextSegment.position() + " limit:" + this.ciphertextSegment.limit() + "\nplaintextSegment position:" + this.plaintextSegment.position() + " limit:" + this.plaintextSegment.limit();
    }

    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0057, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int read(byte[] r7, int r8, int r9) throws java.io.IOException {
        /*
            r6 = this;
            monitor-enter(r6)
            boolean r0 = r6.decryptionErrorOccured     // Catch:{ all -> 0x001b }
            if (r0 != 0) goto L_0x0058
            boolean r0 = r6.headerRead     // Catch:{ all -> 0x001b }
            r1 = 1
            if (r0 != 0) goto L_0x001d
            r6.readHeader()     // Catch:{ all -> 0x001b }
            java.nio.ByteBuffer r0 = r6.ciphertextSegment     // Catch:{ all -> 0x001b }
            r0.clear()     // Catch:{ all -> 0x001b }
            java.nio.ByteBuffer r0 = r6.ciphertextSegment     // Catch:{ all -> 0x001b }
            int r2 = r6.firstCiphertextSegmentSize     // Catch:{ all -> 0x001b }
            int r2 = r2 + r1
            r0.limit(r2)     // Catch:{ all -> 0x001b }
            goto L_0x001d
        L_0x001b:
            r7 = move-exception
            goto L_0x0060
        L_0x001d:
            boolean r0 = r6.endOfPlaintext     // Catch:{ all -> 0x001b }
            r2 = -1
            if (r0 == 0) goto L_0x0024
            monitor-exit(r6)
            return r2
        L_0x0024:
            r0 = 0
        L_0x0025:
            if (r0 >= r9) goto L_0x004e
            java.nio.ByteBuffer r3 = r6.plaintextSegment     // Catch:{ all -> 0x001b }
            int r3 = r3.remaining()     // Catch:{ all -> 0x001b }
            if (r3 != 0) goto L_0x0039
            boolean r3 = r6.endOfCiphertext     // Catch:{ all -> 0x001b }
            if (r3 == 0) goto L_0x0036
            r6.endOfPlaintext = r1     // Catch:{ all -> 0x001b }
            goto L_0x004e
        L_0x0036:
            r6.loadSegment()     // Catch:{ all -> 0x001b }
        L_0x0039:
            java.nio.ByteBuffer r3 = r6.plaintextSegment     // Catch:{ all -> 0x001b }
            int r3 = r3.remaining()     // Catch:{ all -> 0x001b }
            int r4 = r9 - r0
            int r3 = java.lang.Math.min(r3, r4)     // Catch:{ all -> 0x001b }
            java.nio.ByteBuffer r4 = r6.plaintextSegment     // Catch:{ all -> 0x001b }
            int r5 = r0 + r8
            r4.get(r7, r5, r3)     // Catch:{ all -> 0x001b }
            int r0 = r0 + r3
            goto L_0x0025
        L_0x004e:
            if (r0 != 0) goto L_0x0056
            boolean r7 = r6.endOfPlaintext     // Catch:{ all -> 0x001b }
            if (r7 == 0) goto L_0x0056
            monitor-exit(r6)
            return r2
        L_0x0056:
            monitor-exit(r6)
            return r0
        L_0x0058:
            java.io.IOException r7 = new java.io.IOException     // Catch:{ all -> 0x001b }
            java.lang.String r8 = "Decryption failed."
            r7.<init>(r8)     // Catch:{ all -> 0x001b }
            throw r7     // Catch:{ all -> 0x001b }
        L_0x0060:
            monitor-exit(r6)     // Catch:{ all -> 0x001b }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.subtle.StreamingAeadDecryptingStream.read(byte[], int, int):int");
    }
}
