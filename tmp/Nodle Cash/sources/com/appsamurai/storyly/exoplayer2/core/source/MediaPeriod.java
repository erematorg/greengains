package com.appsamurai.storyly.exoplayer2.core.source;

import com.appsamurai.storyly.exoplayer2.common.offline.StreamKey;
import com.appsamurai.storyly.exoplayer2.core.SeekParameters;
import com.appsamurai.storyly.exoplayer2.core.source.SequenceableLoader;
import com.appsamurai.storyly.exoplayer2.core.trackselection.ExoTrackSelection;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public interface MediaPeriod extends SequenceableLoader {

    public interface Callback extends SequenceableLoader.Callback<MediaPeriod> {
        void onPrepared(MediaPeriod mediaPeriod);
    }

    boolean continueLoading(long j2);

    void discardBuffer(long j2, boolean z2);

    long getAdjustedSeekPositionUs(long j2, SeekParameters seekParameters);

    long getBufferedPositionUs();

    long getNextLoadPositionUs();

    List<StreamKey> getStreamKeys(List<ExoTrackSelection> list) {
        return Collections.emptyList();
    }

    TrackGroupArray getTrackGroups();

    boolean isLoading();

    void maybeThrowPrepareError() throws IOException;

    void prepare(Callback callback, long j2);

    long readDiscontinuity();

    void reevaluateBuffer(long j2);

    long seekToUs(long j2);

    long selectTracks(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j2);
}
