package com.appsamurai.storyly.exoplayer2.extractor.extractor;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.extractor.audio.Ac3Util;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.TrackOutput;
import java.io.IOException;

public final class TrueHdSampleRechunker {
    private int chunkFlags;
    private int chunkOffset;
    private int chunkSampleCount;
    private int chunkSize;
    private long chunkTimeUs;
    private boolean foundSyncframe;
    private final byte[] syncframePrefix = new byte[10];

    public void outputPendingSampleMetadata(TrackOutput trackOutput, @Nullable TrackOutput.CryptoData cryptoData) {
        if (this.chunkSampleCount > 0) {
            trackOutput.sampleMetadata(this.chunkTimeUs, this.chunkFlags, this.chunkSize, this.chunkOffset, cryptoData);
            this.chunkSampleCount = 0;
        }
    }

    public void reset() {
        this.foundSyncframe = false;
        this.chunkSampleCount = 0;
    }

    public void sampleMetadata(TrackOutput trackOutput, long j2, int i3, int i4, int i5, @Nullable TrackOutput.CryptoData cryptoData) {
        Assertions.checkState(this.chunkOffset <= i4 + i5, "TrueHD chunk samples must be contiguous in the sample queue.");
        if (this.foundSyncframe) {
            int i6 = this.chunkSampleCount;
            int i7 = i6 + 1;
            this.chunkSampleCount = i7;
            if (i6 == 0) {
                this.chunkTimeUs = j2;
                this.chunkFlags = i3;
                this.chunkSize = 0;
            }
            this.chunkSize += i4;
            this.chunkOffset = i5;
            if (i7 >= 16) {
                outputPendingSampleMetadata(trackOutput, cryptoData);
            }
        }
    }

    public void startSample(ExtractorInput extractorInput) throws IOException {
        if (!this.foundSyncframe) {
            extractorInput.peekFully(this.syncframePrefix, 0, 10);
            extractorInput.resetPeekPosition();
            if (Ac3Util.parseTrueHdSyncframeAudioSampleCount(this.syncframePrefix) != 0) {
                this.foundSyncframe = true;
            }
        }
    }
}
