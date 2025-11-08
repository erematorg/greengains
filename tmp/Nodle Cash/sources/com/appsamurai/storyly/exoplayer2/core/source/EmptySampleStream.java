package com.appsamurai.storyly.exoplayer2.core.source;

import com.appsamurai.storyly.exoplayer2.core.FormatHolder;
import com.appsamurai.storyly.exoplayer2.decoder.DecoderInputBuffer;

public final class EmptySampleStream implements SampleStream {
    public boolean isReady() {
        return true;
    }

    public void maybeThrowError() {
    }

    public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i3) {
        decoderInputBuffer.setFlags(4);
        return -4;
    }

    public int skipData(long j2) {
        return 0;
    }
}
