package com.google.crypto.tink.subtle;

import com.reown.android.internal.common.crypto.kmr.BouncyCastleKeyManagementRepository;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class AesGcmHkdfStreaming extends NonceBasedStreamingAead {
    private static final int NONCE_PREFIX_IN_BYTES = 7;
    private static final int NONCE_SIZE_IN_BYTES = 12;
    private static final int TAG_SIZE_IN_BYTES = 16;
    private final int ciphertextSegmentSize;
    private final int firstSegmentOffset;
    private final String hkdfAlg;
    private final byte[] ikm;
    /* access modifiers changed from: private */
    public final int keySizeInBytes;
    private final int plaintextSegmentSize;

    public class AesGcmHkdfStreamDecrypter implements StreamSegmentDecrypter {
        private Cipher cipher;
        private SecretKeySpec keySpec;
        private byte[] noncePrefix;

        public AesGcmHkdfStreamDecrypter() {
        }

        public synchronized void decryptSegment(ByteBuffer byteBuffer, int i3, boolean z2, ByteBuffer byteBuffer2) throws GeneralSecurityException {
            this.cipher.init(2, this.keySpec, AesGcmHkdfStreaming.paramsForSegment(this.noncePrefix, (long) i3, z2));
            this.cipher.doFinal(byteBuffer, byteBuffer2);
        }

        public synchronized void init(ByteBuffer byteBuffer, byte[] bArr) throws GeneralSecurityException {
            if (byteBuffer.remaining() != AesGcmHkdfStreaming.this.getHeaderLength()) {
                throw new InvalidAlgorithmParameterException("Invalid header length");
            } else if (byteBuffer.get() == AesGcmHkdfStreaming.this.getHeaderLength()) {
                this.noncePrefix = new byte[7];
                byte[] bArr2 = new byte[AesGcmHkdfStreaming.this.keySizeInBytes];
                byteBuffer.get(bArr2);
                byteBuffer.get(this.noncePrefix);
                this.keySpec = AesGcmHkdfStreaming.this.deriveKeySpec(bArr2, bArr);
                this.cipher = AesGcmHkdfStreaming.cipherInstance();
            } else {
                throw new GeneralSecurityException("Invalid ciphertext");
            }
        }
    }

    public AesGcmHkdfStreaming(byte[] bArr, String str, int i3, int i4, int i5) throws InvalidAlgorithmParameterException {
        if (bArr.length < 16 || bArr.length < i3) {
            throw new InvalidAlgorithmParameterException("ikm too short, must be >= " + Math.max(16, i3));
        }
        Validators.validateAesKeySize(i3);
        if (i4 > getHeaderLength() + i5 + 16) {
            this.ikm = Arrays.copyOf(bArr, bArr.length);
            this.hkdfAlg = str;
            this.keySizeInBytes = i3;
            this.ciphertextSegmentSize = i4;
            this.firstSegmentOffset = i5;
            this.plaintextSegmentSize = i4 - 16;
            return;
        }
        throw new InvalidAlgorithmParameterException("ciphertextSegmentSize too small");
    }

    /* access modifiers changed from: private */
    public static Cipher cipherInstance() throws GeneralSecurityException {
        return EngineFactory.CIPHER.getInstance("AES/GCM/NoPadding");
    }

    /* access modifiers changed from: private */
    public SecretKeySpec deriveKeySpec(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        return new SecretKeySpec(Hkdf.computeHkdf(this.hkdfAlg, this.ikm, bArr, bArr2, this.keySizeInBytes), BouncyCastleKeyManagementRepository.AES);
    }

    /* access modifiers changed from: private */
    public static GCMParameterSpec paramsForSegment(byte[] bArr, long j2, boolean z2) throws GeneralSecurityException {
        ByteBuffer allocate = ByteBuffer.allocate(12);
        allocate.order(ByteOrder.BIG_ENDIAN);
        allocate.put(bArr);
        SubtleUtil.putAsUnsigedInt(allocate, j2);
        allocate.put(z2 ? (byte) 1 : 0);
        return new GCMParameterSpec(128, allocate.array());
    }

    /* access modifiers changed from: private */
    public static byte[] randomNonce() {
        return Random.randBytes(7);
    }

    /* access modifiers changed from: private */
    public byte[] randomSalt() {
        return Random.randBytes(this.keySizeInBytes);
    }

    public long expectedCiphertextSize(long j2) {
        long ciphertextOffset = j2 + ((long) getCiphertextOffset());
        int i3 = this.plaintextSegmentSize;
        long j3 = (ciphertextOffset / ((long) i3)) * ((long) this.ciphertextSegmentSize);
        long j4 = ciphertextOffset % ((long) i3);
        return j4 > 0 ? j3 + j4 + 16 : j3;
    }

    public int getCiphertextOffset() {
        return getHeaderLength() + this.firstSegmentOffset;
    }

    public int getCiphertextOverhead() {
        return 16;
    }

    public int getCiphertextSegmentSize() {
        return this.ciphertextSegmentSize;
    }

    public int getFirstSegmentOffset() {
        return this.firstSegmentOffset;
    }

    public int getHeaderLength() {
        return this.keySizeInBytes + 8;
    }

    public int getPlaintextSegmentSize() {
        return this.plaintextSegmentSize;
    }

    public /* bridge */ /* synthetic */ ReadableByteChannel newDecryptingChannel(ReadableByteChannel readableByteChannel, byte[] bArr) throws GeneralSecurityException, IOException {
        return super.newDecryptingChannel(readableByteChannel, bArr);
    }

    public /* bridge */ /* synthetic */ InputStream newDecryptingStream(InputStream inputStream, byte[] bArr) throws GeneralSecurityException, IOException {
        return super.newDecryptingStream(inputStream, bArr);
    }

    public /* bridge */ /* synthetic */ WritableByteChannel newEncryptingChannel(WritableByteChannel writableByteChannel, byte[] bArr) throws GeneralSecurityException, IOException {
        return super.newEncryptingChannel(writableByteChannel, bArr);
    }

    public /* bridge */ /* synthetic */ OutputStream newEncryptingStream(OutputStream outputStream, byte[] bArr) throws GeneralSecurityException, IOException {
        return super.newEncryptingStream(outputStream, bArr);
    }

    public /* bridge */ /* synthetic */ SeekableByteChannel newSeekableDecryptingChannel(SeekableByteChannel seekableByteChannel, byte[] bArr) throws GeneralSecurityException, IOException {
        return super.newSeekableDecryptingChannel(seekableByteChannel, bArr);
    }

    public AesGcmHkdfStreamDecrypter newStreamSegmentDecrypter() throws GeneralSecurityException {
        return new AesGcmHkdfStreamDecrypter();
    }

    public AesGcmHkdfStreamEncrypter newStreamSegmentEncrypter(byte[] bArr) throws GeneralSecurityException {
        return new AesGcmHkdfStreamEncrypter(bArr);
    }

    public class AesGcmHkdfStreamEncrypter implements StreamSegmentEncrypter {
        private final Cipher cipher;
        private long encryptedSegments;
        private final ByteBuffer header;
        private final SecretKeySpec keySpec;
        private final byte[] noncePrefix;

        public AesGcmHkdfStreamEncrypter(byte[] bArr) throws GeneralSecurityException {
            this.encryptedSegments = 0;
            this.cipher = AesGcmHkdfStreaming.cipherInstance();
            this.encryptedSegments = 0;
            byte[] access$100 = AesGcmHkdfStreaming.this.randomSalt();
            byte[] access$200 = AesGcmHkdfStreaming.randomNonce();
            this.noncePrefix = access$200;
            ByteBuffer allocate = ByteBuffer.allocate(AesGcmHkdfStreaming.this.getHeaderLength());
            this.header = allocate;
            allocate.put((byte) AesGcmHkdfStreaming.this.getHeaderLength());
            allocate.put(access$100);
            allocate.put(access$200);
            allocate.flip();
            this.keySpec = AesGcmHkdfStreaming.this.deriveKeySpec(access$100, bArr);
        }

        public synchronized void encryptSegment(ByteBuffer byteBuffer, boolean z2, ByteBuffer byteBuffer2) throws GeneralSecurityException {
            this.cipher.init(1, this.keySpec, AesGcmHkdfStreaming.paramsForSegment(this.noncePrefix, this.encryptedSegments, z2));
            this.encryptedSegments++;
            this.cipher.doFinal(byteBuffer, byteBuffer2);
        }

        public ByteBuffer getHeader() {
            return this.header.asReadOnlyBuffer();
        }

        public synchronized void encryptSegment(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, boolean z2, ByteBuffer byteBuffer3) throws GeneralSecurityException {
            try {
                this.cipher.init(1, this.keySpec, AesGcmHkdfStreaming.paramsForSegment(this.noncePrefix, this.encryptedSegments, z2));
                this.encryptedSegments++;
                if (byteBuffer2.hasRemaining()) {
                    this.cipher.update(byteBuffer, byteBuffer3);
                    this.cipher.doFinal(byteBuffer2, byteBuffer3);
                } else {
                    this.cipher.doFinal(byteBuffer, byteBuffer3);
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }
}
