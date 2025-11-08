package com.appsamurai.storyly.exoplayer2.datasource.upstream.crypto;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class AesFlushingCipher {
    private final int blockSize;
    private final Cipher cipher;
    private final byte[] flushedBlock;
    private int pendingXorBytes;
    private final byte[] zerosBlock;

    public AesFlushingCipher(int i3, byte[] bArr, @Nullable String str, long j2) {
        this(i3, bArr, getFNV64Hash(str), j2);
    }

    private static long getFNV64Hash(@Nullable String str) {
        long j2 = 0;
        if (str == null) {
            return 0;
        }
        for (int i3 = 0; i3 < str.length(); i3++) {
            long charAt = j2 ^ ((long) str.charAt(i3));
            j2 = charAt + (charAt << 1) + (charAt << 4) + (charAt << 5) + (charAt << 7) + (charAt << 8) + (charAt << 40);
        }
        return j2;
    }

    private byte[] getInitializationVector(long j2, long j3) {
        return ByteBuffer.allocate(16).putLong(j2).putLong(j3).array();
    }

    private int nonFlushingUpdate(byte[] bArr, int i3, int i4, byte[] bArr2, int i5) {
        try {
            return this.cipher.update(bArr, i3, i4, bArr2, i5);
        } catch (ShortBufferException e3) {
            throw new RuntimeException(e3);
        }
    }

    public void update(byte[] bArr, int i3, int i4, byte[] bArr2, int i5) {
        int i6 = i3;
        do {
            int i7 = this.pendingXorBytes;
            if (i7 > 0) {
                bArr2[i5] = (byte) (bArr[i6] ^ this.flushedBlock[this.blockSize - i7]);
                i5++;
                i6++;
                this.pendingXorBytes = i7 - 1;
                i4--;
            } else {
                int nonFlushingUpdate = nonFlushingUpdate(bArr, i6, i4, bArr2, i5);
                if (i4 != nonFlushingUpdate) {
                    int i8 = i4 - nonFlushingUpdate;
                    int i9 = 0;
                    boolean z2 = true;
                    Assertions.checkState(i8 < this.blockSize);
                    int i10 = i5 + nonFlushingUpdate;
                    int i11 = this.blockSize - i8;
                    this.pendingXorBytes = i11;
                    if (nonFlushingUpdate(this.zerosBlock, 0, i11, this.flushedBlock, 0) != this.blockSize) {
                        z2 = false;
                    }
                    Assertions.checkState(z2);
                    while (i9 < i8) {
                        bArr2[i10] = this.flushedBlock[i9];
                        i9++;
                        i10++;
                    }
                    return;
                }
                return;
            }
        } while (i4 != 0);
    }

    public void updateInPlace(byte[] bArr, int i3, int i4) {
        update(bArr, i3, i4, bArr, i3);
    }

    public AesFlushingCipher(int i3, byte[] bArr, long j2, long j3) {
        try {
            Cipher instance = Cipher.getInstance("AES/CTR/NoPadding");
            this.cipher = instance;
            int blockSize2 = instance.getBlockSize();
            this.blockSize = blockSize2;
            this.zerosBlock = new byte[blockSize2];
            this.flushedBlock = new byte[blockSize2];
            long j4 = j3 / ((long) blockSize2);
            int i4 = (int) (j3 % ((long) blockSize2));
            instance.init(i3, new SecretKeySpec(bArr, Util.splitAtFirst(instance.getAlgorithm(), "/")[0]), new IvParameterSpec(getInitializationVector(j2, j4)));
            if (i4 != 0) {
                updateInPlace(new byte[i4], 0, i4);
            }
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException e3) {
            throw new RuntimeException(e3);
        }
    }
}
