package com.appsamurai.storyly.exoplayer2.core.analytics;

import android.os.SystemClock;
import android.support.v4.media.session.a;
import android.util.Pair;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.PlaybackException;
import com.appsamurai.storyly.exoplayer2.common.Player;
import com.appsamurai.storyly.exoplayer2.common.Timeline;
import com.appsamurai.storyly.exoplayer2.common.Tracks;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.common.video.VideoSize;
import com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsListener;
import com.appsamurai.storyly.exoplayer2.core.analytics.PlaybackSessionManager;
import com.appsamurai.storyly.exoplayer2.core.analytics.PlaybackStats;
import com.appsamurai.storyly.exoplayer2.core.source.LoadEventInfo;
import com.appsamurai.storyly.exoplayer2.core.source.MediaLoadData;
import com.appsamurai.storyly.exoplayer2.core.source.MediaSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class PlaybackStatsListener implements AnalyticsListener, PlaybackSessionManager.Listener {
    @Nullable
    private Format audioFormat;
    private long bandwidthBytes;
    private long bandwidthTimeMs;
    @Nullable
    private final Callback callback;
    private long discontinuityFromPositionMs;
    @Nullable
    private String discontinuityFromSession;
    private int discontinuityReason;
    private int droppedFrames;
    private PlaybackStats finishedPlaybackStats = PlaybackStats.EMPTY;
    private final boolean keepHistory;
    @Nullable
    private Exception nonFatalException;
    private final Timeline.Period period = new Timeline.Period();
    private final Map<String, PlaybackStatsTracker> playbackStatsTrackers = new HashMap();
    private final PlaybackSessionManager sessionManager;
    private final Map<String, AnalyticsListener.EventTime> sessionStartEventTimes = new HashMap();
    @Nullable
    private Format videoFormat;
    private VideoSize videoSize = VideoSize.UNKNOWN;

    public interface Callback {
        void onPlaybackStatsReady(AnalyticsListener.EventTime eventTime, PlaybackStats playbackStats);
    }

    public static final class PlaybackStatsTracker {
        private long audioFormatBitrateTimeProduct;
        private final List<PlaybackStats.EventTimeAndFormat> audioFormatHistory;
        private long audioFormatTimeMs;
        private long audioUnderruns;
        private long bandwidthBytes;
        private long bandwidthTimeMs;
        @Nullable
        private Format currentAudioFormat;
        private float currentPlaybackSpeed;
        private int currentPlaybackState;
        private long currentPlaybackStateStartTimeMs;
        @Nullable
        private Format currentVideoFormat;
        private long droppedFrames;
        private int fatalErrorCount;
        private final List<PlaybackStats.EventTimeAndException> fatalErrorHistory;
        private long firstReportedTimeMs;
        private boolean hasBeenReady;
        private boolean hasEnded;
        private boolean hasFatalError;
        private long initialAudioFormatBitrate;
        private long initialVideoFormatBitrate;
        private int initialVideoFormatHeight;
        private final boolean isAd;
        private boolean isForeground;
        private boolean isInterruptedByAd;
        private boolean isJoinTimeInvalid;
        private boolean isSeeking;
        private final boolean keepHistory;
        private long lastAudioFormatStartTimeMs;
        private long lastRebufferStartTimeMs;
        private long lastVideoFormatStartTimeMs;
        private long maxRebufferTimeMs;
        private final List<long[]> mediaTimeHistory;
        private int nonFatalErrorCount;
        private final List<PlaybackStats.EventTimeAndException> nonFatalErrorHistory;
        private int pauseBufferCount;
        private int pauseCount;
        private final long[] playbackStateDurationsMs = new long[16];
        private final List<PlaybackStats.EventTimeAndPlaybackState> playbackStateHistory;
        private int rebufferCount;
        private int seekCount;
        private boolean startedLoading;
        private long videoFormatBitrateTimeMs;
        private long videoFormatBitrateTimeProduct;
        private long videoFormatHeightTimeMs;
        private long videoFormatHeightTimeProduct;
        private final List<PlaybackStats.EventTimeAndFormat> videoFormatHistory;

        public PlaybackStatsTracker(boolean z2, AnalyticsListener.EventTime eventTime) {
            this.keepHistory = z2;
            this.playbackStateHistory = z2 ? new ArrayList<>() : Collections.emptyList();
            this.mediaTimeHistory = z2 ? new ArrayList<>() : Collections.emptyList();
            this.videoFormatHistory = z2 ? new ArrayList<>() : Collections.emptyList();
            this.audioFormatHistory = z2 ? new ArrayList<>() : Collections.emptyList();
            this.fatalErrorHistory = z2 ? new ArrayList<>() : Collections.emptyList();
            this.nonFatalErrorHistory = z2 ? new ArrayList<>() : Collections.emptyList();
            boolean z3 = false;
            this.currentPlaybackState = 0;
            this.currentPlaybackStateStartTimeMs = eventTime.realtimeMs;
            this.firstReportedTimeMs = C.TIME_UNSET;
            this.maxRebufferTimeMs = C.TIME_UNSET;
            MediaSource.MediaPeriodId mediaPeriodId = eventTime.mediaPeriodId;
            if (mediaPeriodId != null && mediaPeriodId.isAd()) {
                z3 = true;
            }
            this.isAd = z3;
            this.initialAudioFormatBitrate = -1;
            this.initialVideoFormatBitrate = -1;
            this.initialVideoFormatHeight = -1;
            this.currentPlaybackSpeed = 1.0f;
        }

        private long[] guessMediaTimeBasedOnElapsedRealtime(long j2) {
            long[] jArr = (long[]) a.h(this.mediaTimeHistory, 1);
            return new long[]{j2, jArr[1] + ((long) (((float) (j2 - jArr[0])) * this.currentPlaybackSpeed))};
        }

        private static boolean isInvalidJoinTransition(int i3, int i4) {
            return ((i3 != 1 && i3 != 2 && i3 != 14) || i4 == 1 || i4 == 2 || i4 == 14 || i4 == 3 || i4 == 4 || i4 == 9 || i4 == 11) ? false : true;
        }

        private static boolean isPausedState(int i3) {
            return i3 == 4 || i3 == 7;
        }

        private static boolean isReadyState(int i3) {
            return i3 == 3 || i3 == 4 || i3 == 9;
        }

        private static boolean isRebufferingState(int i3) {
            return i3 == 6 || i3 == 7 || i3 == 10;
        }

        private void maybeRecordAudioFormatTime(long j2) {
            Format format;
            int i3;
            if (!(this.currentPlaybackState != 3 || (format = this.currentAudioFormat) == null || (i3 = format.bitrate) == -1)) {
                long j3 = (long) (((float) (j2 - this.lastAudioFormatStartTimeMs)) * this.currentPlaybackSpeed);
                this.audioFormatTimeMs += j3;
                this.audioFormatBitrateTimeProduct = (j3 * ((long) i3)) + this.audioFormatBitrateTimeProduct;
            }
            this.lastAudioFormatStartTimeMs = j2;
        }

        private void maybeRecordVideoFormatTime(long j2) {
            Format format;
            if (this.currentPlaybackState == 3 && (format = this.currentVideoFormat) != null) {
                long j3 = (long) (((float) (j2 - this.lastVideoFormatStartTimeMs)) * this.currentPlaybackSpeed);
                int i3 = format.height;
                if (i3 != -1) {
                    this.videoFormatHeightTimeMs += j3;
                    this.videoFormatHeightTimeProduct = (((long) i3) * j3) + this.videoFormatHeightTimeProduct;
                }
                int i4 = format.bitrate;
                if (i4 != -1) {
                    this.videoFormatBitrateTimeMs += j3;
                    this.videoFormatBitrateTimeProduct = (j3 * ((long) i4)) + this.videoFormatBitrateTimeProduct;
                }
            }
            this.lastVideoFormatStartTimeMs = j2;
        }

        private void maybeUpdateAudioFormat(AnalyticsListener.EventTime eventTime, @Nullable Format format) {
            int i3;
            if (!Util.areEqual(this.currentAudioFormat, format)) {
                maybeRecordAudioFormatTime(eventTime.realtimeMs);
                if (!(format == null || this.initialAudioFormatBitrate != -1 || (i3 = format.bitrate) == -1)) {
                    this.initialAudioFormatBitrate = (long) i3;
                }
                this.currentAudioFormat = format;
                if (this.keepHistory) {
                    this.audioFormatHistory.add(new PlaybackStats.EventTimeAndFormat(eventTime, format));
                }
            }
        }

        private void maybeUpdateMaxRebufferTimeMs(long j2) {
            if (isRebufferingState(this.currentPlaybackState)) {
                long j3 = j2 - this.lastRebufferStartTimeMs;
                long j4 = this.maxRebufferTimeMs;
                if (j4 == C.TIME_UNSET || j3 > j4) {
                    this.maxRebufferTimeMs = j3;
                }
            }
        }

        private void maybeUpdateMediaTimeHistory(long j2, long j3) {
            if (this.keepHistory) {
                if (this.currentPlaybackState != 3) {
                    if (j3 != C.TIME_UNSET) {
                        if (!this.mediaTimeHistory.isEmpty()) {
                            long j4 = ((long[]) a.h(this.mediaTimeHistory, 1))[1];
                            if (j4 != j3) {
                                this.mediaTimeHistory.add(new long[]{j2, j4});
                            }
                        }
                    } else {
                        return;
                    }
                }
                if (j3 != C.TIME_UNSET) {
                    this.mediaTimeHistory.add(new long[]{j2, j3});
                } else if (!this.mediaTimeHistory.isEmpty()) {
                    this.mediaTimeHistory.add(guessMediaTimeBasedOnElapsedRealtime(j2));
                }
            }
        }

        private void maybeUpdateVideoFormat(AnalyticsListener.EventTime eventTime, @Nullable Format format) {
            int i3;
            int i4;
            if (!Util.areEqual(this.currentVideoFormat, format)) {
                maybeRecordVideoFormatTime(eventTime.realtimeMs);
                if (format != null) {
                    if (this.initialVideoFormatHeight == -1 && (i4 = format.height) != -1) {
                        this.initialVideoFormatHeight = i4;
                    }
                    if (this.initialVideoFormatBitrate == -1 && (i3 = format.bitrate) != -1) {
                        this.initialVideoFormatBitrate = (long) i3;
                    }
                }
                this.currentVideoFormat = format;
                if (this.keepHistory) {
                    this.videoFormatHistory.add(new PlaybackStats.EventTimeAndFormat(eventTime, format));
                }
            }
        }

        private int resolveNewPlaybackState(Player player) {
            int playbackState = player.getPlaybackState();
            if (this.isSeeking && this.isForeground) {
                return 5;
            }
            if (this.hasFatalError) {
                return 13;
            }
            if (!this.isForeground) {
                return this.startedLoading ? 1 : 0;
            }
            if (this.isInterruptedByAd) {
                return 14;
            }
            if (playbackState == 4) {
                return 11;
            }
            if (playbackState == 2) {
                int i3 = this.currentPlaybackState;
                if (i3 == 0 || i3 == 1 || i3 == 2 || i3 == 14) {
                    return 2;
                }
                if (!player.getPlayWhenReady()) {
                    return 7;
                }
                return player.getPlaybackSuppressionReason() != 0 ? 10 : 6;
            } else if (playbackState == 3) {
                if (!player.getPlayWhenReady()) {
                    return 4;
                }
                return player.getPlaybackSuppressionReason() != 0 ? 9 : 3;
            } else if (playbackState != 1 || this.currentPlaybackState == 0) {
                return this.currentPlaybackState;
            } else {
                return 12;
            }
        }

        private void updatePlaybackState(int i3, AnalyticsListener.EventTime eventTime) {
            boolean z2 = false;
            Assertions.checkArgument(eventTime.realtimeMs >= this.currentPlaybackStateStartTimeMs);
            long j2 = eventTime.realtimeMs;
            long[] jArr = this.playbackStateDurationsMs;
            int i4 = this.currentPlaybackState;
            jArr[i4] = jArr[i4] + (j2 - this.currentPlaybackStateStartTimeMs);
            if (this.firstReportedTimeMs == C.TIME_UNSET) {
                this.firstReportedTimeMs = j2;
            }
            this.isJoinTimeInvalid |= isInvalidJoinTransition(i4, i3);
            this.hasBeenReady |= isReadyState(i3);
            boolean z3 = this.hasEnded;
            if (i3 == 11) {
                z2 = true;
            }
            this.hasEnded = z3 | z2;
            if (!isPausedState(this.currentPlaybackState) && isPausedState(i3)) {
                this.pauseCount++;
            }
            if (i3 == 5) {
                this.seekCount++;
            }
            if (!isRebufferingState(this.currentPlaybackState) && isRebufferingState(i3)) {
                this.rebufferCount++;
                this.lastRebufferStartTimeMs = eventTime.realtimeMs;
            }
            if (isRebufferingState(this.currentPlaybackState) && this.currentPlaybackState != 7 && i3 == 7) {
                this.pauseBufferCount++;
            }
            maybeUpdateMaxRebufferTimeMs(eventTime.realtimeMs);
            this.currentPlaybackState = i3;
            this.currentPlaybackStateStartTimeMs = eventTime.realtimeMs;
            if (this.keepHistory) {
                this.playbackStateHistory.add(new PlaybackStats.EventTimeAndPlaybackState(eventTime, i3));
            }
        }

        public PlaybackStats build(boolean z2) {
            ArrayList arrayList;
            long[] jArr;
            int i3;
            long j2;
            long[] jArr2 = this.playbackStateDurationsMs;
            List<long[]> list = this.mediaTimeHistory;
            if (!z2) {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                long[] copyOf = Arrays.copyOf(this.playbackStateDurationsMs, 16);
                long max = Math.max(0, elapsedRealtime - this.currentPlaybackStateStartTimeMs);
                int i4 = this.currentPlaybackState;
                copyOf[i4] = copyOf[i4] + max;
                maybeUpdateMaxRebufferTimeMs(elapsedRealtime);
                maybeRecordVideoFormatTime(elapsedRealtime);
                maybeRecordAudioFormatTime(elapsedRealtime);
                ArrayList arrayList2 = new ArrayList(this.mediaTimeHistory);
                if (this.keepHistory && this.currentPlaybackState == 3) {
                    arrayList2.add(guessMediaTimeBasedOnElapsedRealtime(elapsedRealtime));
                }
                jArr = copyOf;
                arrayList = arrayList2;
            } else {
                jArr = jArr2;
                arrayList = list;
            }
            int i5 = (this.isJoinTimeInvalid || !this.hasBeenReady) ? 1 : 0;
            long j3 = i5 != 0 ? C.TIME_UNSET : jArr[2];
            int i6 = jArr[1] > 0 ? 1 : 0;
            List arrayList3 = z2 ? this.videoFormatHistory : new ArrayList(this.videoFormatHistory);
            List arrayList4 = z2 ? this.audioFormatHistory : new ArrayList(this.audioFormatHistory);
            List arrayList5 = z2 ? this.playbackStateHistory : new ArrayList(this.playbackStateHistory);
            long j4 = this.firstReportedTimeMs;
            boolean z3 = this.isForeground;
            boolean z4 = !this.hasBeenReady;
            boolean z5 = this.hasEnded;
            int i7 = i5 ^ 1;
            int i8 = this.pauseCount;
            int i9 = this.pauseBufferCount;
            int i10 = this.seekCount;
            int i11 = this.rebufferCount;
            int i12 = i9;
            long j5 = this.maxRebufferTimeMs;
            boolean z6 = this.isAd;
            int i13 = i11;
            long[] jArr3 = jArr;
            long j6 = this.videoFormatHeightTimeMs;
            long j7 = this.videoFormatHeightTimeProduct;
            long j8 = this.videoFormatBitrateTimeMs;
            long j9 = this.videoFormatBitrateTimeProduct;
            long j10 = this.audioFormatTimeMs;
            long j11 = this.audioFormatBitrateTimeProduct;
            int i14 = this.initialVideoFormatHeight;
            int i15 = i14;
            int i16 = i14 == -1 ? 0 : 1;
            long j12 = this.initialVideoFormatBitrate;
            long j13 = j12;
            int i17 = j12 == -1 ? 0 : 1;
            long j14 = this.initialAudioFormatBitrate;
            if (j14 == -1) {
                j2 = j14;
                i3 = 0;
            } else {
                j2 = j14;
                i3 = 1;
            }
            long j15 = this.bandwidthTimeMs;
            long j16 = this.bandwidthBytes;
            long j17 = this.droppedFrames;
            long j18 = this.audioUnderruns;
            int i18 = this.fatalErrorCount;
            long j19 = j18;
            long j20 = j17;
            return new PlaybackStats(1, jArr3, arrayList5, arrayList, j4, z3 ? 1 : 0, z4 ? 1 : 0, z5 ? 1 : 0, i6, j3, i7, i8, i12, i10, i13, j5, z6 ? 1 : 0, arrayList3, arrayList4, j6, j7, j8, j9, j10, j11, i16, i17, i15, j13, i3, j2, j15, j16, j20, j19, i18 > 0 ? 1 : 0, i18, this.nonFatalErrorCount, this.fatalErrorHistory, this.nonFatalErrorHistory);
        }

        public void onEvents(Player player, AnalyticsListener.EventTime eventTime, boolean z2, long j2, boolean z3, int i3, boolean z4, boolean z5, @Nullable PlaybackException playbackException, @Nullable Exception exc, long j3, long j4, @Nullable Format format, @Nullable Format format2, @Nullable VideoSize videoSize) {
            AnalyticsListener.EventTime eventTime2 = eventTime;
            long j5 = j2;
            PlaybackException playbackException2 = playbackException;
            Exception exc2 = exc;
            Format format3 = format;
            Format format4 = format2;
            VideoSize videoSize2 = videoSize;
            long j6 = C.TIME_UNSET;
            if (j5 != C.TIME_UNSET) {
                maybeUpdateMediaTimeHistory(eventTime2.realtimeMs, j5);
                this.isSeeking = true;
            }
            if (player.getPlaybackState() != 2) {
                this.isSeeking = false;
            }
            int playbackState = player.getPlaybackState();
            if (playbackState == 1 || playbackState == 4 || z3) {
                this.isInterruptedByAd = false;
            }
            if (playbackException2 != null) {
                this.hasFatalError = true;
                this.fatalErrorCount++;
                if (this.keepHistory) {
                    this.fatalErrorHistory.add(new PlaybackStats.EventTimeAndException(eventTime2, playbackException2));
                }
            } else if (player.getPlayerError() == null) {
                this.hasFatalError = false;
            }
            if (this.isForeground && !this.isInterruptedByAd) {
                Tracks currentTracks = player.getCurrentTracks();
                if (!currentTracks.isTypeSelected(2)) {
                    maybeUpdateVideoFormat(eventTime2, (Format) null);
                }
                if (!currentTracks.isTypeSelected(1)) {
                    maybeUpdateAudioFormat(eventTime2, (Format) null);
                }
            }
            if (format3 != null) {
                maybeUpdateVideoFormat(eventTime2, format3);
            }
            if (format4 != null) {
                maybeUpdateAudioFormat(eventTime2, format4);
            }
            Format format5 = this.currentVideoFormat;
            if (!(format5 == null || format5.height != -1 || videoSize2 == null)) {
                maybeUpdateVideoFormat(eventTime2, format5.buildUpon().setWidth(videoSize2.width).setHeight(videoSize2.height).build());
            }
            if (z5) {
                this.startedLoading = true;
            }
            if (z4) {
                this.audioUnderruns++;
            }
            this.droppedFrames += (long) i3;
            this.bandwidthTimeMs += j3;
            this.bandwidthBytes += j4;
            if (exc2 != null) {
                this.nonFatalErrorCount++;
                if (this.keepHistory) {
                    this.nonFatalErrorHistory.add(new PlaybackStats.EventTimeAndException(eventTime2, exc2));
                }
            }
            int resolveNewPlaybackState = resolveNewPlaybackState(player);
            float f2 = player.getPlaybackParameters().speed;
            if (!(this.currentPlaybackState == resolveNewPlaybackState && this.currentPlaybackSpeed == f2)) {
                long j7 = eventTime2.realtimeMs;
                if (z2) {
                    j6 = eventTime2.eventPlaybackPositionMs;
                }
                maybeUpdateMediaTimeHistory(j7, j6);
                maybeRecordVideoFormatTime(eventTime2.realtimeMs);
                maybeRecordAudioFormatTime(eventTime2.realtimeMs);
            }
            this.currentPlaybackSpeed = f2;
            if (this.currentPlaybackState != resolveNewPlaybackState) {
                updatePlaybackState(resolveNewPlaybackState, eventTime2);
            }
        }

        public void onFinished(AnalyticsListener.EventTime eventTime, boolean z2, long j2) {
            int i3 = 11;
            if (this.currentPlaybackState != 11 && !z2) {
                i3 = 15;
            }
            maybeUpdateMediaTimeHistory(eventTime.realtimeMs, j2);
            maybeRecordVideoFormatTime(eventTime.realtimeMs);
            maybeRecordAudioFormatTime(eventTime.realtimeMs);
            updatePlaybackState(i3, eventTime);
        }

        public void onForeground() {
            this.isForeground = true;
        }

        public void onInterruptedByAd() {
            this.isInterruptedByAd = true;
            this.isSeeking = false;
        }
    }

    public PlaybackStatsListener(boolean z2, @Nullable Callback callback2) {
        this.callback = callback2;
        this.keepHistory = z2;
        DefaultPlaybackSessionManager defaultPlaybackSessionManager = new DefaultPlaybackSessionManager();
        this.sessionManager = defaultPlaybackSessionManager;
        defaultPlaybackSessionManager.setListener(this);
    }

    private Pair<AnalyticsListener.EventTime, Boolean> findBestEventTime(AnalyticsListener.Events events, String str) {
        MediaSource.MediaPeriodId mediaPeriodId;
        AnalyticsListener.Events events2 = events;
        String str2 = str;
        AnalyticsListener.EventTime eventTime = null;
        boolean z2 = false;
        for (int i3 = 0; i3 < events.size(); i3++) {
            AnalyticsListener.EventTime eventTime2 = events2.getEventTime(events2.get(i3));
            boolean belongsToSession = this.sessionManager.belongsToSession(eventTime2, str2);
            if (eventTime == null || ((belongsToSession && !z2) || (belongsToSession == z2 && eventTime2.realtimeMs > eventTime.realtimeMs))) {
                eventTime = eventTime2;
                z2 = belongsToSession;
            }
        }
        Assertions.checkNotNull(eventTime);
        if (!z2 && (mediaPeriodId = eventTime.mediaPeriodId) != null && mediaPeriodId.isAd()) {
            long adGroupTimeUs = eventTime.timeline.getPeriodByUid(eventTime.mediaPeriodId.periodUid, this.period).getAdGroupTimeUs(eventTime.mediaPeriodId.adGroupIndex);
            if (adGroupTimeUs == Long.MIN_VALUE) {
                adGroupTimeUs = this.period.durationUs;
            }
            long positionInWindowUs = this.period.getPositionInWindowUs() + adGroupTimeUs;
            long j2 = eventTime.realtimeMs;
            Timeline timeline = eventTime.timeline;
            int i4 = eventTime.windowIndex;
            MediaSource.MediaPeriodId mediaPeriodId2 = eventTime.mediaPeriodId;
            MediaSource.MediaPeriodId mediaPeriodId3 = new MediaSource.MediaPeriodId(mediaPeriodId2.periodUid, mediaPeriodId2.windowSequenceNumber, mediaPeriodId2.adGroupIndex);
            long usToMs = Util.usToMs(positionInWindowUs);
            Timeline timeline2 = eventTime.timeline;
            int i5 = eventTime.currentWindowIndex;
            AnalyticsListener.EventTime eventTime3 = new AnalyticsListener.EventTime(j2, timeline, i4, mediaPeriodId3, usToMs, timeline2, i5, eventTime.currentMediaPeriodId, eventTime.currentPlaybackPositionMs, eventTime.totalBufferedDurationMs);
            z2 = this.sessionManager.belongsToSession(eventTime3, str);
            eventTime = eventTime3;
        }
        return Pair.create(eventTime, Boolean.valueOf(z2));
    }

    private boolean hasEvent(AnalyticsListener.Events events, String str, int i3) {
        return events.contains(i3) && this.sessionManager.belongsToSession(events.getEventTime(i3), str);
    }

    private void maybeAddSessions(AnalyticsListener.Events events) {
        for (int i3 = 0; i3 < events.size(); i3++) {
            int i4 = events.get(i3);
            AnalyticsListener.EventTime eventTime = events.getEventTime(i4);
            if (i4 == 0) {
                this.sessionManager.updateSessionsWithTimelineChange(eventTime);
            } else if (i4 == 11) {
                this.sessionManager.updateSessionsWithDiscontinuity(eventTime, this.discontinuityReason);
            } else {
                this.sessionManager.updateSessions(eventTime);
            }
        }
    }

    public PlaybackStats getCombinedPlaybackStats() {
        int i3 = 1;
        PlaybackStats[] playbackStatsArr = new PlaybackStats[(this.playbackStatsTrackers.size() + 1)];
        playbackStatsArr[0] = this.finishedPlaybackStats;
        for (PlaybackStatsTracker build : this.playbackStatsTrackers.values()) {
            playbackStatsArr[i3] = build.build(false);
            i3++;
        }
        return PlaybackStats.merge(playbackStatsArr);
    }

    @Nullable
    public PlaybackStats getPlaybackStats() {
        String activeSessionId = this.sessionManager.getActiveSessionId();
        PlaybackStatsTracker playbackStatsTracker = activeSessionId == null ? null : this.playbackStatsTrackers.get(activeSessionId);
        if (playbackStatsTracker == null) {
            return null;
        }
        return playbackStatsTracker.build(false);
    }

    public void onAdPlaybackStarted(AnalyticsListener.EventTime eventTime, String str, String str2) {
        ((PlaybackStatsTracker) Assertions.checkNotNull(this.playbackStatsTrackers.get(str))).onInterruptedByAd();
    }

    public void onBandwidthEstimate(AnalyticsListener.EventTime eventTime, int i3, long j2, long j3) {
        this.bandwidthTimeMs = (long) i3;
        this.bandwidthBytes = j2;
    }

    public void onDownstreamFormatChanged(AnalyticsListener.EventTime eventTime, MediaLoadData mediaLoadData) {
        int i3 = mediaLoadData.trackType;
        if (i3 == 2 || i3 == 0) {
            this.videoFormat = mediaLoadData.trackFormat;
        } else if (i3 == 1) {
            this.audioFormat = mediaLoadData.trackFormat;
        }
    }

    public void onDrmSessionManagerError(AnalyticsListener.EventTime eventTime, Exception exc) {
        this.nonFatalException = exc;
    }

    public void onDroppedVideoFrames(AnalyticsListener.EventTime eventTime, int i3, long j2) {
        this.droppedFrames = i3;
    }

    public void onEvents(Player player, AnalyticsListener.Events events) {
        AnalyticsListener.Events events2 = events;
        if (events.size() != 0) {
            maybeAddSessions(events2);
            for (String next : this.playbackStatsTrackers.keySet()) {
                Pair<AnalyticsListener.EventTime, Boolean> findBestEventTime = findBestEventTime(events2, next);
                PlaybackStatsTracker playbackStatsTracker = this.playbackStatsTrackers.get(next);
                boolean hasEvent = hasEvent(events2, next, 11);
                boolean hasEvent2 = hasEvent(events2, next, 1018);
                boolean hasEvent3 = hasEvent(events2, next, 1011);
                boolean hasEvent4 = hasEvent(events2, next, 1000);
                boolean hasEvent5 = hasEvent(events2, next, 10);
                boolean z2 = hasEvent(events2, next, 1003) || hasEvent(events2, next, 1024);
                boolean hasEvent6 = hasEvent(events2, next, 1006);
                boolean hasEvent7 = hasEvent(events2, next, 1004);
                playbackStatsTracker.onEvents(player, (AnalyticsListener.EventTime) findBestEventTime.first, ((Boolean) findBestEventTime.second).booleanValue(), next.equals(this.discontinuityFromSession) ? this.discontinuityFromPositionMs : C.TIME_UNSET, hasEvent, hasEvent2 ? this.droppedFrames : 0, hasEvent3, hasEvent4, hasEvent5 ? player.getPlayerError() : null, z2 ? this.nonFatalException : null, hasEvent6 ? this.bandwidthTimeMs : 0, hasEvent6 ? this.bandwidthBytes : 0, hasEvent7 ? this.videoFormat : null, hasEvent7 ? this.audioFormat : null, hasEvent(events2, next, 25) ? this.videoSize : null);
            }
            this.videoFormat = null;
            this.audioFormat = null;
            this.discontinuityFromSession = null;
            if (events2.contains(AnalyticsListener.EVENT_PLAYER_RELEASED)) {
                this.sessionManager.finishAllSessions(events2.getEventTime(AnalyticsListener.EVENT_PLAYER_RELEASED));
            }
        }
    }

    public void onLoadError(AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z2) {
        this.nonFatalException = iOException;
    }

    public void onPositionDiscontinuity(AnalyticsListener.EventTime eventTime, Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i3) {
        if (this.discontinuityFromSession == null) {
            this.discontinuityFromSession = this.sessionManager.getActiveSessionId();
            this.discontinuityFromPositionMs = positionInfo.positionMs;
        }
        this.discontinuityReason = i3;
    }

    public void onSessionActive(AnalyticsListener.EventTime eventTime, String str) {
        ((PlaybackStatsTracker) Assertions.checkNotNull(this.playbackStatsTrackers.get(str))).onForeground();
    }

    public void onSessionCreated(AnalyticsListener.EventTime eventTime, String str) {
        this.playbackStatsTrackers.put(str, new PlaybackStatsTracker(this.keepHistory, eventTime));
        this.sessionStartEventTimes.put(str, eventTime);
    }

    public void onSessionFinished(AnalyticsListener.EventTime eventTime, String str, boolean z2) {
        PlaybackStatsTracker playbackStatsTracker = (PlaybackStatsTracker) Assertions.checkNotNull(this.playbackStatsTrackers.remove(str));
        AnalyticsListener.EventTime eventTime2 = (AnalyticsListener.EventTime) Assertions.checkNotNull(this.sessionStartEventTimes.remove(str));
        playbackStatsTracker.onFinished(eventTime, z2, str.equals(this.discontinuityFromSession) ? this.discontinuityFromPositionMs : C.TIME_UNSET);
        PlaybackStats build = playbackStatsTracker.build(true);
        this.finishedPlaybackStats = PlaybackStats.merge(this.finishedPlaybackStats, build);
        Callback callback2 = this.callback;
        if (callback2 != null) {
            callback2.onPlaybackStatsReady(eventTime2, build);
        }
    }

    public void onVideoSizeChanged(AnalyticsListener.EventTime eventTime, VideoSize videoSize2) {
        this.videoSize = videoSize2;
    }
}
