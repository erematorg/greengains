package com.appsamurai.storyly.exoplayer2.core.source.chunk;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSource;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSourceUtil;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSpec;
import java.io.IOException;
import java.util.Arrays;

public abstract class DataChunk extends Chunk {
    private static final int READ_GRANULARITY = 16384;
    private byte[] data;
    private volatile boolean loadCanceled;

    public DataChunk(DataSource dataSource, DataSpec dataSpec, int i3, Format format, int i4, @Nullable Object obj, @Nullable byte[] bArr) {
        super(dataSource, dataSpec, i3, format, i4, obj, C.TIME_UNSET, C.TIME_UNSET);
        DataChunk dataChunk;
        byte[] bArr2;
        if (bArr == null) {
            bArr2 = Util.EMPTY_BYTE_ARRAY;
            dataChunk = this;
        } else {
            dataChunk = this;
            bArr2 = bArr;
        }
        dataChunk.data = bArr2;
    }

    private void maybeExpandData(int i3) {
        byte[] bArr = this.data;
        if (bArr.length < i3 + 16384) {
            this.data = Arrays.copyOf(bArr, bArr.length + 16384);
        }
    }

    public final void cancelLoad() {
        this.loadCanceled = true;
    }

    public abstract void consume(byte[] bArr, int i3) throws IOException;

    public byte[] getDataHolder() {
        return this.data;
    }

    public final void load() throws IOException {
        try {
            this.dataSource.open(this.dataSpec);
            int i3 = 0;
            int i4 = 0;
            while (i3 != -1 && !this.loadCanceled) {
                maybeExpandData(i4);
                i3 = this.dataSource.read(this.data, i4, 16384);
                if (i3 != -1) {
                    i4 += i3;
                }
            }
            if (!this.loadCanceled) {
                consume(this.data, i4);
            }
            DataSourceUtil.closeQuietly(this.dataSource);
        } catch (Throwable th) {
            DataSourceUtil.closeQuietly(this.dataSource);
            throw th;
        }
    }
}
