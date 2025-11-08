package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.StreamingAead;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.concurrent.GuardedBy;

final class InputStreamDecrypter extends InputStream {
    byte[] associatedData;
    @GuardedBy("this")
    boolean attemptedMatching = false;
    @GuardedBy("this")
    InputStream ciphertextStream;
    @GuardedBy("this")
    InputStream matchingStream = null;
    PrimitiveSet<StreamingAead> primitives;

    public InputStreamDecrypter(PrimitiveSet<StreamingAead> primitiveSet, InputStream inputStream, byte[] bArr) {
        this.primitives = primitiveSet;
        if (inputStream.markSupported()) {
            this.ciphertextStream = inputStream;
        } else {
            this.ciphertextStream = new BufferedInputStream(inputStream);
        }
        this.ciphertextStream.mark(Integer.MAX_VALUE);
        this.associatedData = (byte[]) bArr.clone();
    }

    @GuardedBy("this")
    private void disableRewinding() throws IOException {
        this.ciphertextStream.mark(0);
    }

    @GuardedBy("this")
    private void rewind() throws IOException {
        this.ciphertextStream.reset();
    }

    @GuardedBy("this")
    public synchronized int available() throws IOException {
        InputStream inputStream = this.matchingStream;
        if (inputStream == null) {
            return 0;
        }
        return inputStream.available();
    }

    @GuardedBy("this")
    public synchronized void close() throws IOException {
        this.ciphertextStream.close();
    }

    public boolean markSupported() {
        return false;
    }

    @GuardedBy("this")
    public synchronized int read() throws IOException {
        byte[] bArr = new byte[1];
        if (read(bArr) != 1) {
            return -1;
        }
        return bArr[0];
    }

    @GuardedBy("this")
    public synchronized int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0052 */
    @javax.annotation.concurrent.GuardedBy("this")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int read(byte[] r5, int r6, int r7) throws java.io.IOException {
        /*
            r4 = this;
            monitor-enter(r4)
            if (r7 != 0) goto L_0x0006
            monitor-exit(r4)
            r4 = 0
            return r4
        L_0x0006:
            java.io.InputStream r0 = r4.matchingStream     // Catch:{ all -> 0x0010 }
            if (r0 == 0) goto L_0x0012
            int r5 = r0.read(r5, r6, r7)     // Catch:{ all -> 0x0010 }
            monitor-exit(r4)
            return r5
        L_0x0010:
            r5 = move-exception
            goto L_0x006a
        L_0x0012:
            boolean r0 = r4.attemptedMatching     // Catch:{ all -> 0x0010 }
            if (r0 != 0) goto L_0x0062
            r0 = 1
            r4.attemptedMatching = r0     // Catch:{ all -> 0x0010 }
            com.google.crypto.tink.PrimitiveSet<com.google.crypto.tink.StreamingAead> r0 = r4.primitives     // Catch:{ all -> 0x0010 }
            java.util.List r0 = r0.getRawPrimitives()     // Catch:{ all -> 0x0010 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0010 }
        L_0x0023:
            boolean r1 = r0.hasNext()     // Catch:{ all -> 0x0010 }
            if (r1 == 0) goto L_0x005a
            java.lang.Object r1 = r0.next()     // Catch:{ all -> 0x0010 }
            com.google.crypto.tink.PrimitiveSet$Entry r1 = (com.google.crypto.tink.PrimitiveSet.Entry) r1     // Catch:{ all -> 0x0010 }
            java.lang.Object r1 = r1.getPrimitive()     // Catch:{ IOException -> 0x0056, GeneralSecurityException -> 0x0052 }
            com.google.crypto.tink.StreamingAead r1 = (com.google.crypto.tink.StreamingAead) r1     // Catch:{ IOException -> 0x0056, GeneralSecurityException -> 0x0052 }
            java.io.InputStream r2 = r4.ciphertextStream     // Catch:{ IOException -> 0x0056, GeneralSecurityException -> 0x0052 }
            byte[] r3 = r4.associatedData     // Catch:{ IOException -> 0x0056, GeneralSecurityException -> 0x0052 }
            java.io.InputStream r1 = r1.newDecryptingStream(r2, r3)     // Catch:{ IOException -> 0x0056, GeneralSecurityException -> 0x0052 }
            int r2 = r1.read(r5, r6, r7)     // Catch:{ IOException -> 0x0056, GeneralSecurityException -> 0x0052 }
            if (r2 == 0) goto L_0x004a
            r4.matchingStream = r1     // Catch:{ IOException -> 0x0056, GeneralSecurityException -> 0x0052 }
            r4.disableRewinding()     // Catch:{ IOException -> 0x0056, GeneralSecurityException -> 0x0052 }
            monitor-exit(r4)
            return r2
        L_0x004a:
            java.io.IOException r1 = new java.io.IOException     // Catch:{ IOException -> 0x0056, GeneralSecurityException -> 0x0052 }
            java.lang.String r2 = "Could not read bytes from the ciphertext stream"
            r1.<init>(r2)     // Catch:{ IOException -> 0x0056, GeneralSecurityException -> 0x0052 }
            throw r1     // Catch:{ IOException -> 0x0056, GeneralSecurityException -> 0x0052 }
        L_0x0052:
            r4.rewind()     // Catch:{ all -> 0x0010 }
            goto L_0x0023
        L_0x0056:
            r4.rewind()     // Catch:{ all -> 0x0010 }
            goto L_0x0023
        L_0x005a:
            java.io.IOException r5 = new java.io.IOException     // Catch:{ all -> 0x0010 }
            java.lang.String r6 = "No matching key found for the ciphertext in the stream."
            r5.<init>(r6)     // Catch:{ all -> 0x0010 }
            throw r5     // Catch:{ all -> 0x0010 }
        L_0x0062:
            java.io.IOException r5 = new java.io.IOException     // Catch:{ all -> 0x0010 }
            java.lang.String r6 = "No matching key found for the ciphertext in the stream."
            r5.<init>(r6)     // Catch:{ all -> 0x0010 }
            throw r5     // Catch:{ all -> 0x0010 }
        L_0x006a:
            monitor-exit(r4)     // Catch:{ all -> 0x0010 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.streamingaead.InputStreamDecrypter.read(byte[], int, int):int");
    }
}
