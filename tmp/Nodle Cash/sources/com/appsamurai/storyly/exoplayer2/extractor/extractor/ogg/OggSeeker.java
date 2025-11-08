package com.appsamurai.storyly.exoplayer2.extractor.extractor.ogg;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.SeekMap;
import java.io.IOException;

interface OggSeeker {
    @Nullable
    SeekMap createSeekMap();

    long read(ExtractorInput extractorInput) throws IOException;

    void startSeek(long j2);
}
