package com.appsamurai.storyly.exoplayer2.extractor.extractor.jpeg;

import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorOutput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.SeekMap;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.SeekPoint;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.TrackOutput;

public final class StartOffsetExtractorOutput implements ExtractorOutput {
    private final ExtractorOutput extractorOutput;
    /* access modifiers changed from: private */
    public final long startOffset;

    public StartOffsetExtractorOutput(long j2, ExtractorOutput extractorOutput2) {
        this.startOffset = j2;
        this.extractorOutput = extractorOutput2;
    }

    public void endTracks() {
        this.extractorOutput.endTracks();
    }

    public void seekMap(final SeekMap seekMap) {
        this.extractorOutput.seekMap(new SeekMap() {
            public long getDurationUs() {
                return seekMap.getDurationUs();
            }

            public SeekMap.SeekPoints getSeekPoints(long j2) {
                SeekMap.SeekPoints seekPoints = seekMap.getSeekPoints(j2);
                SeekPoint seekPoint = seekPoints.first;
                SeekPoint seekPoint2 = new SeekPoint(seekPoint.timeUs, StartOffsetExtractorOutput.this.startOffset + seekPoint.position);
                SeekPoint seekPoint3 = seekPoints.second;
                return new SeekMap.SeekPoints(seekPoint2, new SeekPoint(seekPoint3.timeUs, StartOffsetExtractorOutput.this.startOffset + seekPoint3.position));
            }

            public boolean isSeekable() {
                return seekMap.isSeekable();
            }
        });
    }

    public TrackOutput track(int i3, int i4) {
        return this.extractorOutput.track(i3, i4);
    }
}
