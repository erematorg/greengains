package com.appsamurai.storyly.exoplayer2.core.util;

import com.appsamurai.storyly.exoplayer2.common.PlaybackParameters;
import com.appsamurai.storyly.exoplayer2.common.util.Clock;
import com.appsamurai.storyly.exoplayer2.common.util.Util;

public final class StandaloneMediaClock implements MediaClock {
    private long baseElapsedMs;
    private long baseUs;
    private final Clock clock;
    private PlaybackParameters playbackParameters = PlaybackParameters.DEFAULT;
    private boolean started;

    public StandaloneMediaClock(Clock clock2) {
        this.clock = clock2;
    }

    public PlaybackParameters getPlaybackParameters() {
        return this.playbackParameters;
    }

    public long getPositionUs() {
        long j2 = this.baseUs;
        if (!this.started) {
            return j2;
        }
        long elapsedRealtime = this.clock.elapsedRealtime() - this.baseElapsedMs;
        PlaybackParameters playbackParameters2 = this.playbackParameters;
        return j2 + (playbackParameters2.speed == 1.0f ? Util.msToUs(elapsedRealtime) : playbackParameters2.getMediaTimeUsForPlayoutTimeMs(elapsedRealtime));
    }

    public void resetPosition(long j2) {
        this.baseUs = j2;
        if (this.started) {
            this.baseElapsedMs = this.clock.elapsedRealtime();
        }
    }

    public void setPlaybackParameters(PlaybackParameters playbackParameters2) {
        if (this.started) {
            resetPosition(getPositionUs());
        }
        this.playbackParameters = playbackParameters2;
    }

    public void start() {
        if (!this.started) {
            this.baseElapsedMs = this.clock.elapsedRealtime();
            this.started = true;
        }
    }

    public void stop() {
        if (this.started) {
            resetPosition(getPositionUs());
            this.started = false;
        }
    }
}
