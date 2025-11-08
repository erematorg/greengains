package com.google.crypto.tink.subtle;

import A.a;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
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
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class AesCtrHmacStreaming extends NonceBasedStreamingAead {
    public static final TinkFipsUtil.AlgorithmFipsCompatibility FIPS = TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_NOT_FIPS;
    private static final int HMAC_KEY_SIZE_IN_BYTES = 32;
    private static final int NONCE_PREFIX_IN_BYTES = 7;
    private static final int NONCE_SIZE_IN_BYTES = 16;
    private final int ciphertextSegmentSize;
    private final int firstSegmentOffset;
    private final String hkdfAlgo;
    private final byte[] ikm;
    /* access modifiers changed from: private */
    public final int keySizeInBytes;
    private final int plaintextSegmentSize;
    private final String tagAlgo;
    /* access modifiers changed from: private */
    public final int tagSizeInBytes;

    public class AesCtrHmacStreamDecrypter implements StreamSegmentDecrypter {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private Cipher cipher;
        private SecretKeySpec hmacKeySpec;
        private SecretKeySpec keySpec;
        private Mac mac;
        private byte[] noncePrefix;

        public AesCtrHmacStreamDecrypter() {
        }

        public synchronized void decryptSegment(ByteBuffer byteBuffer, int i3, boolean z2, ByteBuffer byteBuffer2) throws GeneralSecurityException {
            int position = byteBuffer.position();
            byte[] access$700 = AesCtrHmacStreaming.this.nonceForSegment(this.noncePrefix, (long) i3, z2);
            int remaining = byteBuffer.remaining();
            if (remaining >= AesCtrHmacStreaming.this.tagSizeInBytes) {
                int access$800 = (remaining - AesCtrHmacStreaming.this.tagSizeInBytes) + position;
                ByteBuffer duplicate = byteBuffer.duplicate();
                duplicate.limit(access$800);
                ByteBuffer duplicate2 = byteBuffer.duplicate();
                duplicate2.position(access$800);
                this.mac.init(this.hmacKeySpec);
                this.mac.update(access$700);
                this.mac.update(duplicate);
                byte[] copyOf = Arrays.copyOf(this.mac.doFinal(), AesCtrHmacStreaming.this.tagSizeInBytes);
                byte[] bArr = new byte[AesCtrHmacStreaming.this.tagSizeInBytes];
                duplicate2.get(bArr);
                if (Bytes.equal(bArr, copyOf)) {
                    byteBuffer.limit(access$800);
                    this.cipher.init(1, this.keySpec, new IvParameterSpec(access$700));
                    this.cipher.doFinal(byteBuffer, byteBuffer2);
                } else {
                    throw new GeneralSecurityException("Tag mismatch");
                }
            } else {
                throw new GeneralSecurityException("Ciphertext too short");
            }
        }

        public synchronized void init(ByteBuffer byteBuffer, byte[] bArr) throws GeneralSecurityException {
            if (byteBuffer.remaining() != AesCtrHmacStreaming.this.getHeaderLength()) {
                throw new InvalidAlgorithmParameterException("Invalid header length");
            } else if (byteBuffer.get() == AesCtrHmacStreaming.this.getHeaderLength()) {
                this.noncePrefix = new byte[7];
                byte[] bArr2 = new byte[AesCtrHmacStreaming.this.keySizeInBytes];
                byteBuffer.get(bArr2);
                byteBuffer.get(this.noncePrefix);
                byte[] access$400 = AesCtrHmacStreaming.this.deriveKeyMaterial(bArr2, bArr);
                this.keySpec = AesCtrHmacStreaming.this.deriveKeySpec(access$400);
                this.hmacKeySpec = AesCtrHmacStreaming.this.deriveHmacKeySpec(access$400);
                this.cipher = AesCtrHmacStreaming.cipherInstance();
                this.mac = AesCtrHmacStreaming.this.macInstance();
            } else {
                throw new GeneralSecurityException("Invalid ciphertext");
            }
        }
    }

    public AesCtrHmacStreaming(byte[] bArr, String str, int i3, String str2, int i4, int i5, int i6) throws GeneralSecurityException {
        if (FIPS.isCompatible()) {
            validateParameters(bArr.length, i3, str2, i4, i5, i6);
            this.ikm = Arrays.copyOf(bArr, bArr.length);
            this.hkdfAlgo = str;
            this.keySizeInBytes = i3;
            this.tagAlgo = str2;
            this.tagSizeInBytes = i4;
            this.ciphertextSegmentSize = i5;
            this.firstSegmentOffset = i6;
            this.plaintextSegmentSize = i5 - i4;
            return;
        }
        throw new GeneralSecurityException("Can not use AES-CTR-HMAC streaming in FIPS-mode.");
    }

    /* access modifiers changed from: private */
    public static Cipher cipherInstance() throws GeneralSecurityException {
        return EngineFactory.CIPHER.getInstance("AES/CTR/NoPadding");
    }

    /* access modifiers changed from: private */
    public SecretKeySpec deriveHmacKeySpec(byte[] bArr) throws GeneralSecurityException {
        return new SecretKeySpec(bArr, this.keySizeInBytes, 32, this.tagAlgo);
    }

    /* access modifiers changed from: private */
    public byte[] deriveKeyMaterial(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        return Hkdf.computeHkdf(this.hkdfAlgo, this.ikm, bArr, bArr2, this.keySizeInBytes + 32);
    }

    /* access modifiers changed from: private */
    public SecretKeySpec deriveKeySpec(byte[] bArr) throws GeneralSecurityException {
        return new SecretKeySpec(bArr, 0, this.keySizeInBytes, BouncyCastleKeyManagementRepository.AES);
    }

    /* access modifiers changed from: private */
    public Mac macInstance() throws GeneralSecurityException {
        return EngineFactory.MAC.getInstance(this.tagAlgo);
    }

    /* access modifiers changed from: private */
    public byte[] nonceForSegment(byte[] bArr, long j2, boolean z2) throws GeneralSecurityException {
        ByteBuffer allocate = ByteBuffer.allocate(16);
        allocate.order(ByteOrder.BIG_ENDIAN);
        allocate.put(bArr);
        SubtleUtil.putAsUnsigedInt(allocate, j2);
        allocate.put(z2 ? (byte) 1 : 0);
        allocate.putInt(0);
        return allocate.array();
    }

    /* access modifiers changed from: private */
    public byte[] randomNonce() {
        return Random.randBytes(7);
    }

    /* access modifiers changed from: private */
    public byte[] randomSalt() {
        return Random.randBytes(this.keySizeInBytes);
    }

    private static void validateParameters(int i3, int i4, String str, int i5, int i6, int i7) throws InvalidAlgorithmParameterException {
        if (i3 < 16 || i3 < i4) {
            throw new InvalidAlgorithmParameterException("ikm too short, must be >= " + Math.max(16, i4));
        }
        Validators.validateAesKeySize(i4);
        if (i5 < 10) {
            throw new InvalidAlgorithmParameterException(a.k("tag size too small ", i5));
        } else if ((str.equals("HmacSha1") && i5 > 20) || ((str.equals("HmacSha256") && i5 > 32) || (str.equals("HmacSha512") && i5 > 64))) {
            throw new InvalidAlgorithmParameterException("tag size too big");
        } else if ((((i6 - i7) - i5) - i4) - 8 <= 0) {
            throw new InvalidAlgorithmParameterException("ciphertextSegmentSize too small");
        }
    }

    public long expectedCiphertextSize(long j2) {
        long ciphertextOffset = j2 + ((long) getCiphertextOffset());
        int i3 = this.plaintextSegmentSize;
        long j3 = (ciphertextOffset / ((long) i3)) * ((long) this.ciphertextSegmentSize);
        long j4 = ciphertextOffset % ((long) i3);
        return j4 > 0 ? j3 + j4 + ((long) this.tagSizeInBytes) : j3;
    }

    public int getCiphertextOffset() {
        return getHeaderLength() + this.firstSegmentOffset;
    }

    public int getCiphertextOverhead() {
        return this.tagSizeInBytes;
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

    public AesCtrHmacStreamDecrypter newStreamSegmentDecrypter() throws GeneralSecurityException {
        return new AesCtrHmacStreamDecrypter();
    }

    public AesCtrHmacStreamEncrypter newStreamSegmentEncrypter(byte[] bArr) throws GeneralSecurityException {
        return new AesCtrHmacStreamEncrypter(bArr);
    }

    public class AesCtrHmacStreamEncrypter implements StreamSegmentEncrypter {
        private final Cipher cipher = AesCtrHmacStreaming.cipherInstance();
        private long encryptedSegments = 0;
        private ByteBuffer header;
        private final SecretKeySpec hmacKeySpec;
        private final SecretKeySpec keySpec;
        private final Mac mac;
        private final byte[] noncePrefix;

        public AesCtrHmacStreamEncrypter(byte[] bArr) throws GeneralSecurityException {
            this.mac = AesCtrHmacStreaming.this.macInstance();
            this.encryptedSegments = 0;
            byte[] access$200 = AesCtrHmacStreaming.this.randomSalt();
            byte[] access$300 = AesCtrHmacStreaming.this.randomNonce();
            this.noncePrefix = access$300;
            ByteBuffer allocate = ByteBuffer.allocate(AesCtrHmacStreaming.this.getHeaderLength());
            this.header = allocate;
            allocate.put((byte) AesCtrHmacStreaming.this.getHeaderLength());
            this.header.put(access$200);
            this.header.put(access$300);
            this.header.flip();
            byte[] access$400 = AesCtrHmacStreaming.this.deriveKeyMaterial(access$200, bArr);
            this.keySpec = AesCtrHmacStreaming.this.deriveKeySpec(access$400);
            this.hmacKeySpec = AesCtrHmacStreaming.this.deriveHmacKeySpec(access$400);
        }

        public synchronized void encryptSegment(ByteBuffer byteBuffer, boolean z2, ByteBuffer byteBuffer2) throws GeneralSecurityException {
            int position = byteBuffer2.position();
            byte[] access$700 = AesCtrHmacStreaming.this.nonceForSegment(this.noncePrefix, this.encryptedSegments, z2);
            this.cipher.init(1, this.keySpec, new IvParameterSpec(access$700));
            this.encryptedSegments++;
            this.cipher.doFinal(byteBuffer, byteBuffer2);
            ByteBuffer duplicate = byteBuffer2.duplicate();
            duplicate.flip();
            duplicate.position(position);
            this.mac.init(this.hmacKeySpec);
            this.mac.update(access$700);
            this.mac.update(duplicate);
            byteBuffer2.put(this.mac.doFinal(), 0, AesCtrHmacStreaming.this.tagSizeInBytes);
        }

        public ByteBuffer getHeader() {
            return this.header.asReadOnlyBuffer();
        }

        public synchronized void encryptSegment(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, boolean z2, ByteBuffer byteBuffer3) throws GeneralSecurityException {
            int position = byteBuffer3.position();
            byte[] access$700 = AesCtrHmacStreaming.this.nonceForSegment(this.noncePrefix, this.encryptedSegments, z2);
            this.cipher.init(1, this.keySpec, new IvParameterSpec(access$700));
            this.encryptedSegments++;
            this.cipher.update(byteBuffer, byteBuffer3);
            this.cipher.doFinal(byteBuffer2, byteBuffer3);
            ByteBuffer duplicate = byteBuffer3.duplicate();
            duplicate.flip();
            duplicate.position(position);
            this.mac.init(this.hmacKeySpec);
            this.mac.update(access$700);
            this.mac.update(duplicate);
            byteBuffer3.put(this.mac.doFinal(), 0, AesCtrHmacStreaming.this.tagSizeInBytes);
        }
    }
}
