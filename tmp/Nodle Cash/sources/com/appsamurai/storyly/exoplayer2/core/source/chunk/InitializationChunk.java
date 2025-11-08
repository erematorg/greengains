package com.appsamurai.storyly.exoplayer2.core.source.chunk;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.ChunkExtractor;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSource;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSourceUtil;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSpec;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.StatsDataSource;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.DefaultExtractorInput;
import java.io.IOException;

public final class InitializationChunk extends Chunk {
    private final ChunkExtractor chunkExtractor;
    private volatile boolean loadCanceled;
    private long nextLoadPosition;
    private ChunkExtractor.TrackOutputProvider trackOutputProvider;

    public InitializationChunk(DataSource dataSource, DataSpec dataSpec, Format format, int i3, @Nullable Object obj, ChunkExtractor chunkExtractor2) {
        super(dataSource, dataSpec, 2, format, i3, obj, C.TIME_UNSET, C.TIME_UNSET);
        this.chunkExtractor = chunkExtractor2;
    }

    public void cancelLoad() {
        this.loadCanceled = true;
    }

    public void init(ChunkExtractor.TrackOutputProvider trackOutputProvider2) {
        this.trackOutputProvider = trackOutputProvider2;
    }

    public void load() throws IOException {
        DefaultExtractorInput defaultExtractorInput;
        if (this.nextLoadPosition == 0) {
            this.chunkExtractor.init(this.trackOutputProvider, C.TIME_UNSET, C.TIME_UNSET);
        }
        try {
            DataSpec subrange = this.dataSpec.subrange(this.nextLoadPosition);
            StatsDataSource statsDataSource = this.dataSource;
            defaultExtractorInput = new DefaultExtractorInput(statsDataSource, subrange.position, statsDataSource.open(subrange));
            do {
                if (this.loadCanceled || !this.chunkExtractor.read(defaultExtractorInput)) {
                    break;
                }
                break;
                break;
            } while (!this.chunkExtractor.read(defaultExtractorInput));
            break;
            this.nextLoadPosition = defaultExtractorInput.getPosition() - this.dataSpec.position;
            DataSourceUtil.closeQuietly(this.dataSource);
        } catch (Throwable th) {
            DataSourceUtil.closeQuietly(this.dataSource);
            throw th;
        }
    }
}
