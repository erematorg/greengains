package com.google.crypto.tink.subtle;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.security.GeneralSecurityException;
import java.util.Arrays;

class StreamingAeadDecryptingChannel implements ReadableByteChannel {
    private static final int PLAINTEXT_SEGMENT_EXTRA_SIZE = 16;
    private final byte[] associatedData;
    private ReadableByteChannel ciphertextChannel;
    private ByteBuffer ciphertextSegment;
    private final int ciphertextSegmentSize;
    private final StreamSegmentDecrypter decrypter;
    private boolean definedState = true;
    private boolean endOfCiphertext = false;
    private boolean endOfPlaintext = false;
    private final int firstCiphertextSegmentSize;
    private ByteBuffer header;
    private boolean headerRead = false;
    private ByteBuffer plaintextSegment;
    private int segmentNr = 0;

    public StreamingAeadDecryptingChannel(NonceBasedStreamingAead nonceBasedStreamingAead, ReadableByteChannel readableByteChannel, byte[] bArr) throws GeneralSecurityException, IOException {
        this.decrypter = nonceBasedStreamingAead.newStreamSegmentDecrypter();
        this.ciphertextChannel = readableByteChannel;
        this.header = ByteBuffer.allocate(nonceBasedStreamingAead.getHeaderLength());
        this.associatedData = Arrays.copyOf(bArr, bArr.length);
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

    /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    private void readSomeCiphertext(java.nio.ByteBuffer r3) throws java.io.IOException {
        /*
            r2 = this;
        L_0x0000:
            java.nio.channels.ReadableByteChannel r0 = r2.ciphertextChannel
            int r0 = r0.read(r3)
            if (r0 <= 0) goto L_0x000e
            int r1 = r3.remaining()
            if (r1 > 0) goto L_0x0000
        L_0x000e:
            r3 = -1
            if (r0 != r3) goto L_0x0014
            r3 = 1
            r2.endOfCiphertext = r3
        L_0x0014:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.subtle.StreamingAeadDecryptingChannel.readSomeCiphertext(java.nio.ByteBuffer):void");
    }

    private void setUndefinedState() {
        this.definedState = false;
        this.plaintextSegment.limit(0);
    }

    private boolean tryLoadSegment() throws IOException {
        if (!this.endOfCiphertext) {
            readSomeCiphertext(this.ciphertextSegment);
        }
        byte b3 = 0;
        if (this.ciphertextSegment.remaining() > 0 && !this.endOfCiphertext) {
            return false;
        }
        if (!this.endOfCiphertext) {
            ByteBuffer byteBuffer = this.ciphertextSegment;
            b3 = byteBuffer.get(byteBuffer.position() - 1);
            ByteBuffer byteBuffer2 = this.ciphertextSegment;
            byteBuffer2.position(byteBuffer2.position() - 1);
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
            return true;
        } catch (GeneralSecurityException e3) {
            setUndefinedState();
            throw new IOException(e3.getMessage() + "\n" + toString() + "\nsegmentNr:" + this.segmentNr + " endOfCiphertext:" + this.endOfCiphertext, e3);
        }
    }

    private boolean tryReadHeader() throws IOException {
        if (!this.endOfCiphertext) {
            readSomeCiphertext(this.header);
            if (this.header.remaining() > 0) {
                return false;
            }
            this.header.flip();
            try {
                this.decrypter.init(this.header, this.associatedData);
                this.headerRead = true;
                return true;
            } catch (GeneralSecurityException e3) {
                setUndefinedState();
                throw new IOException(e3);
            }
        } else {
            throw new IOException("Ciphertext is too short");
        }
    }

    public synchronized void close() throws IOException {
        this.ciphertextChannel.close();
    }

    public synchronized boolean isOpen() {
        return this.ciphertextChannel.isOpen();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:42:0x008b, code lost:
        return r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int read(java.nio.ByteBuffer r7) throws java.io.IOException {
        /*
            r6 = this;
            monitor-enter(r6)
            boolean r0 = r6.definedState     // Catch:{ all -> 0x0021 }
            if (r0 == 0) goto L_0x008c
            boolean r0 = r6.headerRead     // Catch:{ all -> 0x0021 }
            r1 = 1
            if (r0 != 0) goto L_0x0024
            boolean r0 = r6.tryReadHeader()     // Catch:{ all -> 0x0021 }
            if (r0 != 0) goto L_0x0013
            monitor-exit(r6)
            r6 = 0
            return r6
        L_0x0013:
            java.nio.ByteBuffer r0 = r6.ciphertextSegment     // Catch:{ all -> 0x0021 }
            r0.clear()     // Catch:{ all -> 0x0021 }
            java.nio.ByteBuffer r0 = r6.ciphertextSegment     // Catch:{ all -> 0x0021 }
            int r2 = r6.firstCiphertextSegmentSize     // Catch:{ all -> 0x0021 }
            int r2 = r2 + r1
            r0.limit(r2)     // Catch:{ all -> 0x0021 }
            goto L_0x0024
        L_0x0021:
            r7 = move-exception
            goto L_0x0094
        L_0x0024:
            boolean r0 = r6.endOfPlaintext     // Catch:{ all -> 0x0021 }
            r2 = -1
            if (r0 == 0) goto L_0x002b
            monitor-exit(r6)
            return r2
        L_0x002b:
            int r0 = r7.position()     // Catch:{ all -> 0x0021 }
        L_0x002f:
            int r3 = r7.remaining()     // Catch:{ all -> 0x0021 }
            if (r3 <= 0) goto L_0x007d
            java.nio.ByteBuffer r3 = r6.plaintextSegment     // Catch:{ all -> 0x0021 }
            int r3 = r3.remaining()     // Catch:{ all -> 0x0021 }
            if (r3 != 0) goto L_0x004b
            boolean r3 = r6.endOfCiphertext     // Catch:{ all -> 0x0021 }
            if (r3 == 0) goto L_0x0044
            r6.endOfPlaintext = r1     // Catch:{ all -> 0x0021 }
            goto L_0x007d
        L_0x0044:
            boolean r3 = r6.tryLoadSegment()     // Catch:{ all -> 0x0021 }
            if (r3 != 0) goto L_0x004b
            goto L_0x007d
        L_0x004b:
            java.nio.ByteBuffer r3 = r6.plaintextSegment     // Catch:{ all -> 0x0021 }
            int r3 = r3.remaining()     // Catch:{ all -> 0x0021 }
            int r4 = r7.remaining()     // Catch:{ all -> 0x0021 }
            if (r3 > r4) goto L_0x005d
            java.nio.ByteBuffer r3 = r6.plaintextSegment     // Catch:{ all -> 0x0021 }
            r7.put(r3)     // Catch:{ all -> 0x0021 }
            goto L_0x002f
        L_0x005d:
            int r3 = r7.remaining()     // Catch:{ all -> 0x0021 }
            java.nio.ByteBuffer r4 = r6.plaintextSegment     // Catch:{ all -> 0x0021 }
            java.nio.ByteBuffer r4 = r4.duplicate()     // Catch:{ all -> 0x0021 }
            int r5 = r4.position()     // Catch:{ all -> 0x0021 }
            int r5 = r5 + r3
            r4.limit(r5)     // Catch:{ all -> 0x0021 }
            r7.put(r4)     // Catch:{ all -> 0x0021 }
            java.nio.ByteBuffer r4 = r6.plaintextSegment     // Catch:{ all -> 0x0021 }
            int r5 = r4.position()     // Catch:{ all -> 0x0021 }
            int r5 = r5 + r3
            r4.position(r5)     // Catch:{ all -> 0x0021 }
            goto L_0x002f
        L_0x007d:
            int r7 = r7.position()     // Catch:{ all -> 0x0021 }
            int r7 = r7 - r0
            if (r7 != 0) goto L_0x008a
            boolean r0 = r6.endOfPlaintext     // Catch:{ all -> 0x0021 }
            if (r0 == 0) goto L_0x008a
            monitor-exit(r6)
            return r2
        L_0x008a:
            monitor-exit(r6)
            return r7
        L_0x008c:
            java.io.IOException r7 = new java.io.IOException     // Catch:{ all -> 0x0021 }
            java.lang.String r0 = "This StreamingAeadDecryptingChannel is in an undefined state"
            r7.<init>(r0)     // Catch:{ all -> 0x0021 }
            throw r7     // Catch:{ all -> 0x0021 }
        L_0x0094:
            monitor-exit(r6)     // Catch:{ all -> 0x0021 }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.subtle.StreamingAeadDecryptingChannel.read(java.nio.ByteBuffer):int");
    }

    public synchronized String toString() {
        return "StreamingAeadDecryptingChannel\nsegmentNr:" + this.segmentNr + "\nciphertextSegmentSize:" + this.ciphertextSegmentSize + "\nheaderRead:" + this.headerRead + "\nendOfCiphertext:" + this.endOfCiphertext + "\nendOfPlaintext:" + this.endOfPlaintext + "\ndefinedState:" + this.definedState + "\nHeader position:" + this.header.position() + " limit:" + this.header.position() + "\nciphertextSgement position:" + this.ciphertextSegment.position() + " limit:" + this.ciphertextSegment.limit() + "\nplaintextSegment position:" + this.plaintextSegment.position() + " limit:" + this.plaintextSegment.limit();
    }
}
