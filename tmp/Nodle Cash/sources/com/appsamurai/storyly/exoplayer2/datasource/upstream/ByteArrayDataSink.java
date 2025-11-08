package com.appsamurai.storyly.exoplayer2.datasource.upstream;

import androidx.annotation.Nullable;
import androidx.collection.SieveCacheKt;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public final class ByteArrayDataSink implements DataSink {
    private ByteArrayOutputStream stream;

    public void close() throws IOException {
        ((ByteArrayOutputStream) Util.castNonNull(this.stream)).close();
    }

    @Nullable
    public byte[] getData() {
        ByteArrayOutputStream byteArrayOutputStream = this.stream;
        if (byteArrayOutputStream == null) {
            return null;
        }
        return byteArrayOutputStream.toByteArray();
    }

    public void open(DataSpec dataSpec) {
        long j2 = dataSpec.length;
        if (j2 == -1) {
            this.stream = new ByteArrayOutputStream();
            return;
        }
        Assertions.checkArgument(j2 <= SieveCacheKt.NodeLinkMask);
        this.stream = new ByteArrayOutputStream((int) dataSpec.length);
    }

    public void write(byte[] bArr, int i3, int i4) {
        ((ByteArrayOutputStream) Util.castNonNull(this.stream)).write(bArr, i3, i4);
    }
}
