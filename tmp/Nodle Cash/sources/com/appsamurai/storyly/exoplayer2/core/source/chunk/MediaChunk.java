package com.appsamurai.storyly.exoplayer2.core.source.chunk;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSource;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSpec;

public abstract class MediaChunk extends Chunk {
    public final long chunkIndex;

    public MediaChunk(DataSource dataSource, DataSpec dataSpec, Format format, int i3, @Nullable Object obj, long j2, long j3, long j4) {
        super(dataSource, dataSpec, 1, format, i3, obj, j2, j3);
        Assertions.checkNotNull(format);
        this.chunkIndex = j4;
    }

    public long getNextChunkIndex() {
        long j2 = this.chunkIndex;
        if (j2 != -1) {
            return 1 + j2;
        }
        return -1;
    }

    public abstract boolean isLoadCompleted();
}
