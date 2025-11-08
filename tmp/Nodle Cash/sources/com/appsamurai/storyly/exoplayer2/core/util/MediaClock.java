package com.appsamurai.storyly.exoplayer2.core.util;

import com.appsamurai.storyly.exoplayer2.common.PlaybackParameters;

public interface MediaClock {
    PlaybackParameters getPlaybackParameters();

    long getPositionUs();

    void setPlaybackParameters(PlaybackParameters playbackParameters);
}
