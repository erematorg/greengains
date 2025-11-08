package com.appsamurai.storyly.exoplayer2.extractor.text;

import com.appsamurai.storyly.exoplayer2.decoder.DecoderInputBuffer;

public class SubtitleInputBuffer extends DecoderInputBuffer {
    public long subsampleOffsetUs;

    public SubtitleInputBuffer() {
        super(1);
    }
}
