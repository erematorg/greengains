package com.appsamurai.storyly.exoplayer2.extractor.extractor;

public interface ExtractorOutput {
    public static final ExtractorOutput PLACEHOLDER = new ExtractorOutput() {
        public void endTracks() {
            throw new UnsupportedOperationException();
        }

        public void seekMap(SeekMap seekMap) {
            throw new UnsupportedOperationException();
        }

        public TrackOutput track(int i3, int i4) {
            throw new UnsupportedOperationException();
        }
    };

    void endTracks();

    void seekMap(SeekMap seekMap);

    TrackOutput track(int i3, int i4);
}
