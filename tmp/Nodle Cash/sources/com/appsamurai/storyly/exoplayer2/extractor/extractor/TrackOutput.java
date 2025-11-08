package com.appsamurai.storyly.exoplayer2.extractor.extractor;

import A.a;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.upstream.DataReader;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;

public interface TrackOutput {
    public static final int SAMPLE_DATA_PART_ENCRYPTION = 1;
    public static final int SAMPLE_DATA_PART_MAIN = 0;
    public static final int SAMPLE_DATA_PART_SUPPLEMENTAL = 2;

    public static final class CryptoData {
        public final int clearBlocks;
        public final int cryptoMode;
        public final int encryptedBlocks;
        public final byte[] encryptionKey;

        public CryptoData(int i3, byte[] bArr, int i4, int i5) {
            this.cryptoMode = i3;
            this.encryptionKey = bArr;
            this.encryptedBlocks = i4;
            this.clearBlocks = i5;
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || CryptoData.class != obj.getClass()) {
                return false;
            }
            CryptoData cryptoData = (CryptoData) obj;
            return this.cryptoMode == cryptoData.cryptoMode && this.encryptedBlocks == cryptoData.encryptedBlocks && this.clearBlocks == cryptoData.clearBlocks && Arrays.equals(this.encryptionKey, cryptoData.encryptionKey);
        }

        public int hashCode() {
            return ((a.d(this.encryptionKey, this.cryptoMode * 31, 31) + this.encryptedBlocks) * 31) + this.clearBlocks;
        }
    }

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface SampleDataPart {
    }

    void format(Format format);

    int sampleData(DataReader dataReader, int i3, boolean z2) throws IOException {
        return sampleData(dataReader, i3, z2, 0);
    }

    int sampleData(DataReader dataReader, int i3, boolean z2, int i4) throws IOException;

    void sampleData(ParsableByteArray parsableByteArray, int i3, int i4);

    void sampleMetadata(long j2, int i3, int i4, int i5, @Nullable CryptoData cryptoData);

    void sampleData(ParsableByteArray parsableByteArray, int i3) {
        sampleData(parsableByteArray, i3, 0);
    }
}
