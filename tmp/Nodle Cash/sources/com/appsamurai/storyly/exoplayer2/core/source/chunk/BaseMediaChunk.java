package com.appsamurai.storyly.exoplayer2.core.source.chunk;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSource;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSpec;

public abstract class BaseMediaChunk extends MediaChunk {
    public final long clippedEndTimeUs;
    public final long clippedStartTimeUs;
    private int[] firstSampleIndices;
    private BaseMediaChunkOutput output;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BaseMediaChunk(DataSource dataSource, DataSpec dataSpec, Format format, int i3, @Nullable Object obj, long j2, long j3, long j4, long j5, long j6) {
        super(dataSource, dataSpec, format, i3, obj, j2, j3, j6);
        this.clippedStartTimeUs = j4;
        this.clippedEndTimeUs = j5;
    }

    public final int getFirstSampleIndex(int i3) {
        return ((int[]) Assertions.checkStateNotNull(this.firstSampleIndices))[i3];
    }

    public final BaseMediaChunkOutput getOutput() {
        return (BaseMediaChunkOutput) Assertions.checkStateNotNull(this.output);
    }

    public void init(BaseMediaChunkOutput baseMediaChunkOutput) {
        this.output = baseMediaChunkOutput;
        this.firstSampleIndices = baseMediaChunkOutput.getWriteIndices();
    }
}
