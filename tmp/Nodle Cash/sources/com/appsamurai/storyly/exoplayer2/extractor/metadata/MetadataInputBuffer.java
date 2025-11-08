package com.appsamurai.storyly.exoplayer2.extractor.metadata;

import com.appsamurai.storyly.exoplayer2.decoder.DecoderInputBuffer;

public final class MetadataInputBuffer extends DecoderInputBuffer {
    public long subsampleOffsetUs;

    public MetadataInputBuffer() {
        super(1);
    }
}
