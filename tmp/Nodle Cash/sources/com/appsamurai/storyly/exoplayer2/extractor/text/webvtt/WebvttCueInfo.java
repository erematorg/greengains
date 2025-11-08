package com.appsamurai.storyly.exoplayer2.extractor.text.webvtt;

import com.appsamurai.storyly.exoplayer2.common.text.Cue;

public final class WebvttCueInfo {
    public final Cue cue;
    public final long endTimeUs;
    public final long startTimeUs;

    public WebvttCueInfo(Cue cue2, long j2, long j3) {
        this.cue = cue2;
        this.startTimeUs = j2;
        this.endTimeUs = j3;
    }
}
