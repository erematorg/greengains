package com.appsamurai.storyly.exoplayer2.extractor.extractor;

public final class DummyExtractorOutput implements ExtractorOutput {
    public void endTracks() {
    }

    public void seekMap(SeekMap seekMap) {
    }

    public TrackOutput track(int i3, int i4) {
        return new DummyTrackOutput();
    }
}
