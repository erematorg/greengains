package com.appsamurai.storyly.exoplayer2.extractor.extractor.jpeg;

import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ForwardingExtractorInput;

final class StartOffsetExtractorInput extends ForwardingExtractorInput {
    private final long startOffset;

    public StartOffsetExtractorInput(ExtractorInput extractorInput, long j2) {
        super(extractorInput);
        Assertions.checkArgument(extractorInput.getPosition() >= j2);
        this.startOffset = j2;
    }

    public long getLength() {
        return super.getLength() - this.startOffset;
    }

    public long getPeekPosition() {
        return super.getPeekPosition() - this.startOffset;
    }

    public long getPosition() {
        return super.getPosition() - this.startOffset;
    }

    public <E extends Throwable> void setRetryPosition(long j2, E e3) throws Throwable {
        super.setRetryPosition(j2 + this.startOffset, e3);
    }
}
