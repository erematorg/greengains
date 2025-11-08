package com.appsamurai.storyly.exoplayer2.common;

public final class IllegalSeekPositionException extends IllegalStateException {
    public final long positionMs;
    public final Timeline timeline;
    public final int windowIndex;

    public IllegalSeekPositionException(Timeline timeline2, int i3, long j2) {
        this.timeline = timeline2;
        this.windowIndex = i3;
        this.positionMs = j2;
    }
}
