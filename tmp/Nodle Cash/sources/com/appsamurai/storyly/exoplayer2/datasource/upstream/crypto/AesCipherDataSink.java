package com.appsamurai.storyly.exoplayer2.datasource.upstream.crypto;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSink;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSpec;
import java.io.IOException;

public final class AesCipherDataSink implements DataSink {
    @Nullable
    private AesFlushingCipher cipher;
    @Nullable
    private final byte[] scratch;
    private final byte[] secretKey;
    private final DataSink wrappedDataSink;

    public AesCipherDataSink(byte[] bArr, DataSink dataSink) {
        this(bArr, dataSink, (byte[]) null);
    }

    public void close() throws IOException {
        this.cipher = null;
        this.wrappedDataSink.close();
    }

    public void open(DataSpec dataSpec) throws IOException {
        this.wrappedDataSink.open(dataSpec);
        this.cipher = new AesFlushingCipher(1, this.secretKey, dataSpec.key, dataSpec.position + dataSpec.uriPositionOffset);
    }

    public void write(byte[] bArr, int i3, int i4) throws IOException {
        if (this.scratch == null) {
            ((AesFlushingCipher) Util.castNonNull(this.cipher)).updateInPlace(bArr, i3, i4);
            this.wrappedDataSink.write(bArr, i3, i4);
            return;
        }
        int i5 = 0;
        while (i5 < i4) {
            int min = Math.min(i4 - i5, this.scratch.length);
            ((AesFlushingCipher) Util.castNonNull(this.cipher)).update(bArr, i3 + i5, min, this.scratch, 0);
            this.wrappedDataSink.write(this.scratch, 0, min);
            i5 += min;
        }
    }

    public AesCipherDataSink(byte[] bArr, DataSink dataSink, @Nullable byte[] bArr2) {
        this.wrappedDataSink = dataSink;
        this.secretKey = bArr;
        this.scratch = bArr2;
    }
}
