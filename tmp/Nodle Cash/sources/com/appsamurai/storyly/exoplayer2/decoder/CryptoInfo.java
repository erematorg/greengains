package com.appsamurai.storyly.exoplayer2.decoder;

import android.media.MediaCodec;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Util;

public final class CryptoInfo {
    public int clearBlocks;
    public int encryptedBlocks;
    private final MediaCodec.CryptoInfo frameworkCryptoInfo;
    @Nullable
    public byte[] iv;
    @Nullable
    public byte[] key;
    public int mode;
    @Nullable
    public int[] numBytesOfClearData;
    @Nullable
    public int[] numBytesOfEncryptedData;
    public int numSubSamples;
    @Nullable
    private final PatternHolderV24 patternHolder;

    @RequiresApi(24)
    public static final class PatternHolderV24 {
        private final MediaCodec.CryptoInfo frameworkCryptoInfo;
        private final MediaCodec.CryptoInfo.Pattern pattern;

        /* access modifiers changed from: private */
        public void set(int i3, int i4) {
            this.pattern.set(i3, i4);
            this.frameworkCryptoInfo.setPattern(this.pattern);
        }

        private PatternHolderV24(MediaCodec.CryptoInfo cryptoInfo) {
            this.frameworkCryptoInfo = cryptoInfo;
            this.pattern = new MediaCodec.CryptoInfo.Pattern(0, 0);
        }
    }

    public CryptoInfo() {
        MediaCodec.CryptoInfo cryptoInfo = new MediaCodec.CryptoInfo();
        this.frameworkCryptoInfo = cryptoInfo;
        this.patternHolder = Util.SDK_INT >= 24 ? new PatternHolderV24(cryptoInfo) : null;
    }

    public MediaCodec.CryptoInfo getFrameworkCryptoInfo() {
        return this.frameworkCryptoInfo;
    }

    public void increaseClearDataFirstSubSampleBy(int i3) {
        if (i3 != 0) {
            if (this.numBytesOfClearData == null) {
                int[] iArr = new int[1];
                this.numBytesOfClearData = iArr;
                this.frameworkCryptoInfo.numBytesOfClearData = iArr;
            }
            int[] iArr2 = this.numBytesOfClearData;
            iArr2[0] = iArr2[0] + i3;
        }
    }

    public void set(int i3, int[] iArr, int[] iArr2, byte[] bArr, byte[] bArr2, int i4, int i5, int i6) {
        this.numSubSamples = i3;
        this.numBytesOfClearData = iArr;
        this.numBytesOfEncryptedData = iArr2;
        this.key = bArr;
        this.iv = bArr2;
        this.mode = i4;
        this.encryptedBlocks = i5;
        this.clearBlocks = i6;
        MediaCodec.CryptoInfo cryptoInfo = this.frameworkCryptoInfo;
        cryptoInfo.numSubSamples = i3;
        cryptoInfo.numBytesOfClearData = iArr;
        cryptoInfo.numBytesOfEncryptedData = iArr2;
        cryptoInfo.key = bArr;
        cryptoInfo.iv = bArr2;
        cryptoInfo.mode = i4;
        if (Util.SDK_INT >= 24) {
            ((PatternHolderV24) Assertions.checkNotNull(this.patternHolder)).set(i5, i6);
        }
    }
}
