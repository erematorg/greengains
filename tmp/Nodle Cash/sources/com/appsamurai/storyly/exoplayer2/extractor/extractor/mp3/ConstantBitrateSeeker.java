package com.appsamurai.storyly.exoplayer2.extractor.extractor.mp3;

import com.appsamurai.storyly.exoplayer2.extractor.audio.MpegAudioUtil;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ConstantBitrateSeekMap;

final class ConstantBitrateSeeker extends ConstantBitrateSeekMap implements Seeker {
    public ConstantBitrateSeeker(long j2, long j3, MpegAudioUtil.Header header, boolean z2) {
        super(j2, j3, header.bitrate, header.frameSize, z2);
    }

    public long getDataEndPosition() {
        return -1;
    }

    public long getTimeUs(long j2) {
        return getTimeUsAtPosition(j2);
    }
}
