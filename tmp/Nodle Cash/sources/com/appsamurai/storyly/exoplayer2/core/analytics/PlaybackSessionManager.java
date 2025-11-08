package com.appsamurai.storyly.exoplayer2.core.analytics;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.Timeline;
import com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsListener;
import com.appsamurai.storyly.exoplayer2.core.source.MediaSource;

public interface PlaybackSessionManager {

    public interface Listener {
        void onAdPlaybackStarted(AnalyticsListener.EventTime eventTime, String str, String str2);

        void onSessionActive(AnalyticsListener.EventTime eventTime, String str);

        void onSessionCreated(AnalyticsListener.EventTime eventTime, String str);

        void onSessionFinished(AnalyticsListener.EventTime eventTime, String str, boolean z2);
    }

    boolean belongsToSession(AnalyticsListener.EventTime eventTime, String str);

    void finishAllSessions(AnalyticsListener.EventTime eventTime);

    @Nullable
    String getActiveSessionId();

    String getSessionForMediaPeriodId(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId);

    void setListener(Listener listener);

    void updateSessions(AnalyticsListener.EventTime eventTime);

    void updateSessionsWithDiscontinuity(AnalyticsListener.EventTime eventTime, int i3);

    void updateSessionsWithTimelineChange(AnalyticsListener.EventTime eventTime);
}
