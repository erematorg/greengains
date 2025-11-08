package com.appsamurai.storyly.exoplayer2.core.analytics;

import android.support.v4.media.session.a;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsListener;
import java.util.Collections;
import java.util.List;

public final class PlaybackStats {
    public static final PlaybackStats EMPTY = merge(new PlaybackStats[0]);
    public static final int PLAYBACK_STATE_ABANDONED = 15;
    public static final int PLAYBACK_STATE_BUFFERING = 6;
    static final int PLAYBACK_STATE_COUNT = 16;
    public static final int PLAYBACK_STATE_ENDED = 11;
    public static final int PLAYBACK_STATE_FAILED = 13;
    public static final int PLAYBACK_STATE_INTERRUPTED_BY_AD = 14;
    public static final int PLAYBACK_STATE_JOINING_BACKGROUND = 1;
    public static final int PLAYBACK_STATE_JOINING_FOREGROUND = 2;
    public static final int PLAYBACK_STATE_NOT_STARTED = 0;
    public static final int PLAYBACK_STATE_PAUSED = 4;
    public static final int PLAYBACK_STATE_PAUSED_BUFFERING = 7;
    public static final int PLAYBACK_STATE_PLAYING = 3;
    public static final int PLAYBACK_STATE_SEEKING = 5;
    public static final int PLAYBACK_STATE_STOPPED = 12;
    public static final int PLAYBACK_STATE_SUPPRESSED = 9;
    public static final int PLAYBACK_STATE_SUPPRESSED_BUFFERING = 10;
    public final int abandonedBeforeReadyCount;
    public final int adPlaybackCount;
    public final List<EventTimeAndFormat> audioFormatHistory;
    public final int backgroundJoiningCount;
    public final int endedCount;
    public final int fatalErrorCount;
    public final List<EventTimeAndException> fatalErrorHistory;
    public final int fatalErrorPlaybackCount;
    public final long firstReportedTimeMs;
    public final int foregroundPlaybackCount;
    public final int initialAudioFormatBitrateCount;
    public final int initialVideoFormatBitrateCount;
    public final int initialVideoFormatHeightCount;
    public final long maxRebufferTimeMs;
    public final List<long[]> mediaTimeHistory;
    public final int nonFatalErrorCount;
    public final List<EventTimeAndException> nonFatalErrorHistory;
    public final int playbackCount;
    private final long[] playbackStateDurationsMs;
    public final List<EventTimeAndPlaybackState> playbackStateHistory;
    public final long totalAudioFormatBitrateTimeProduct;
    public final long totalAudioFormatTimeMs;
    public final long totalAudioUnderruns;
    public final long totalBandwidthBytes;
    public final long totalBandwidthTimeMs;
    public final long totalDroppedFrames;
    public final long totalInitialAudioFormatBitrate;
    public final long totalInitialVideoFormatBitrate;
    public final int totalInitialVideoFormatHeight;
    public final int totalPauseBufferCount;
    public final int totalPauseCount;
    public final int totalRebufferCount;
    public final int totalSeekCount;
    public final long totalValidJoinTimeMs;
    public final long totalVideoFormatBitrateTimeMs;
    public final long totalVideoFormatBitrateTimeProduct;
    public final long totalVideoFormatHeightTimeMs;
    public final long totalVideoFormatHeightTimeProduct;
    public final int validJoinTimeCount;
    public final List<EventTimeAndFormat> videoFormatHistory;

    public static final class EventTimeAndException {
        public final AnalyticsListener.EventTime eventTime;
        public final Exception exception;

        public EventTimeAndException(AnalyticsListener.EventTime eventTime2, Exception exc) {
            this.eventTime = eventTime2;
            this.exception = exc;
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || EventTimeAndException.class != obj.getClass()) {
                return false;
            }
            EventTimeAndException eventTimeAndException = (EventTimeAndException) obj;
            if (!this.eventTime.equals(eventTimeAndException.eventTime)) {
                return false;
            }
            return this.exception.equals(eventTimeAndException.exception);
        }

        public int hashCode() {
            return this.exception.hashCode() + (this.eventTime.hashCode() * 31);
        }
    }

    public static final class EventTimeAndFormat {
        public final AnalyticsListener.EventTime eventTime;
        @Nullable
        public final Format format;

        public EventTimeAndFormat(AnalyticsListener.EventTime eventTime2, @Nullable Format format2) {
            this.eventTime = eventTime2;
            this.format = format2;
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || EventTimeAndFormat.class != obj.getClass()) {
                return false;
            }
            EventTimeAndFormat eventTimeAndFormat = (EventTimeAndFormat) obj;
            if (!this.eventTime.equals(eventTimeAndFormat.eventTime)) {
                return false;
            }
            Format format2 = this.format;
            return format2 != null ? format2.equals(eventTimeAndFormat.format) : eventTimeAndFormat.format == null;
        }

        public int hashCode() {
            int hashCode = this.eventTime.hashCode() * 31;
            Format format2 = this.format;
            return hashCode + (format2 != null ? format2.hashCode() : 0);
        }
    }

    public static final class EventTimeAndPlaybackState {
        public final AnalyticsListener.EventTime eventTime;
        public final int playbackState;

        public EventTimeAndPlaybackState(AnalyticsListener.EventTime eventTime2, int i3) {
            this.eventTime = eventTime2;
            this.playbackState = i3;
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || EventTimeAndPlaybackState.class != obj.getClass()) {
                return false;
            }
            EventTimeAndPlaybackState eventTimeAndPlaybackState = (EventTimeAndPlaybackState) obj;
            if (this.playbackState != eventTimeAndPlaybackState.playbackState) {
                return false;
            }
            return this.eventTime.equals(eventTimeAndPlaybackState.eventTime);
        }

        public int hashCode() {
            return (this.eventTime.hashCode() * 31) + this.playbackState;
        }
    }

    public PlaybackStats(int i3, long[] jArr, List<EventTimeAndPlaybackState> list, List<long[]> list2, long j2, int i4, int i5, int i6, int i7, long j3, int i8, int i9, int i10, int i11, int i12, long j4, int i13, List<EventTimeAndFormat> list3, List<EventTimeAndFormat> list4, long j5, long j6, long j7, long j8, long j9, long j10, int i14, int i15, int i16, long j11, int i17, long j12, long j13, long j14, long j15, long j16, int i18, int i19, int i20, List<EventTimeAndException> list5, List<EventTimeAndException> list6) {
        this.playbackCount = i3;
        this.playbackStateDurationsMs = jArr;
        this.playbackStateHistory = Collections.unmodifiableList(list);
        this.mediaTimeHistory = Collections.unmodifiableList(list2);
        this.firstReportedTimeMs = j2;
        this.foregroundPlaybackCount = i4;
        this.abandonedBeforeReadyCount = i5;
        this.endedCount = i6;
        this.backgroundJoiningCount = i7;
        this.totalValidJoinTimeMs = j3;
        this.validJoinTimeCount = i8;
        this.totalPauseCount = i9;
        this.totalPauseBufferCount = i10;
        this.totalSeekCount = i11;
        this.totalRebufferCount = i12;
        this.maxRebufferTimeMs = j4;
        this.adPlaybackCount = i13;
        this.videoFormatHistory = Collections.unmodifiableList(list3);
        this.audioFormatHistory = Collections.unmodifiableList(list4);
        this.totalVideoFormatHeightTimeMs = j5;
        this.totalVideoFormatHeightTimeProduct = j6;
        this.totalVideoFormatBitrateTimeMs = j7;
        this.totalVideoFormatBitrateTimeProduct = j8;
        this.totalAudioFormatTimeMs = j9;
        this.totalAudioFormatBitrateTimeProduct = j10;
        this.initialVideoFormatHeightCount = i14;
        this.initialVideoFormatBitrateCount = i15;
        this.totalInitialVideoFormatHeight = i16;
        this.totalInitialVideoFormatBitrate = j11;
        this.initialAudioFormatBitrateCount = i17;
        this.totalInitialAudioFormatBitrate = j12;
        this.totalBandwidthTimeMs = j13;
        this.totalBandwidthBytes = j14;
        this.totalDroppedFrames = j15;
        this.totalAudioUnderruns = j16;
        this.fatalErrorPlaybackCount = i18;
        this.fatalErrorCount = i19;
        this.nonFatalErrorCount = i20;
        this.fatalErrorHistory = Collections.unmodifiableList(list5);
        this.nonFatalErrorHistory = Collections.unmodifiableList(list6);
    }

    public static PlaybackStats merge(PlaybackStats... playbackStatsArr) {
        int i3;
        PlaybackStats[] playbackStatsArr2 = playbackStatsArr;
        int i4 = 16;
        long[] jArr = new long[16];
        int length = playbackStatsArr2.length;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        long j5 = 0;
        long j6 = 0;
        long j7 = 0;
        long j8 = 0;
        long j9 = 0;
        long j10 = 0;
        long j11 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = -1;
        long j12 = C.TIME_UNSET;
        long j13 = C.TIME_UNSET;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        long j14 = C.TIME_UNSET;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        int i17 = 0;
        int i18 = 0;
        int i19 = 0;
        long j15 = -1;
        int i20 = 0;
        long j16 = -1;
        int i21 = 0;
        int i22 = 0;
        int i23 = 0;
        while (i5 < length) {
            PlaybackStats playbackStats = playbackStatsArr2[i5];
            i6 += playbackStats.playbackCount;
            for (int i24 = 0; i24 < i4; i24++) {
                jArr[i24] = jArr[i24] + playbackStats.playbackStateDurationsMs[i24];
            }
            if (j13 == C.TIME_UNSET) {
                j13 = playbackStats.firstReportedTimeMs;
                i3 = length;
            } else {
                i3 = length;
                long j17 = playbackStats.firstReportedTimeMs;
                if (j17 != C.TIME_UNSET) {
                    j13 = Math.min(j13, j17);
                }
            }
            i8 += playbackStats.foregroundPlaybackCount;
            i9 += playbackStats.abandonedBeforeReadyCount;
            i10 += playbackStats.endedCount;
            i11 += playbackStats.backgroundJoiningCount;
            if (j14 == C.TIME_UNSET) {
                j14 = playbackStats.totalValidJoinTimeMs;
            } else {
                long j18 = playbackStats.totalValidJoinTimeMs;
                if (j18 != C.TIME_UNSET) {
                    j14 += j18;
                }
            }
            i12 += playbackStats.validJoinTimeCount;
            i13 += playbackStats.totalPauseCount;
            i14 += playbackStats.totalPauseBufferCount;
            i15 += playbackStats.totalSeekCount;
            i16 += playbackStats.totalRebufferCount;
            if (j12 == C.TIME_UNSET) {
                j12 = playbackStats.maxRebufferTimeMs;
            } else {
                long j19 = playbackStats.maxRebufferTimeMs;
                if (j19 != C.TIME_UNSET) {
                    j12 = Math.max(j12, j19);
                }
            }
            i17 += playbackStats.adPlaybackCount;
            j2 += playbackStats.totalVideoFormatHeightTimeMs;
            j3 += playbackStats.totalVideoFormatHeightTimeProduct;
            j4 += playbackStats.totalVideoFormatBitrateTimeMs;
            j5 += playbackStats.totalVideoFormatBitrateTimeProduct;
            j6 += playbackStats.totalAudioFormatTimeMs;
            j7 += playbackStats.totalAudioFormatBitrateTimeProduct;
            i18 += playbackStats.initialVideoFormatHeightCount;
            i19 += playbackStats.initialVideoFormatBitrateCount;
            if (i7 == -1) {
                i7 = playbackStats.totalInitialVideoFormatHeight;
            } else {
                int i25 = playbackStats.totalInitialVideoFormatHeight;
                if (i25 != -1) {
                    i7 += i25;
                }
            }
            if (j15 == -1) {
                j15 = playbackStats.totalInitialVideoFormatBitrate;
            } else {
                long j20 = playbackStats.totalInitialVideoFormatBitrate;
                if (j20 != -1) {
                    j15 += j20;
                }
            }
            i20 += playbackStats.initialAudioFormatBitrateCount;
            if (j16 == -1) {
                j16 = playbackStats.totalInitialAudioFormatBitrate;
            } else {
                long j21 = playbackStats.totalInitialAudioFormatBitrate;
                if (j21 != -1) {
                    j16 += j21;
                }
            }
            j8 += playbackStats.totalBandwidthTimeMs;
            j9 += playbackStats.totalBandwidthBytes;
            j10 += playbackStats.totalDroppedFrames;
            j11 += playbackStats.totalAudioUnderruns;
            i21 += playbackStats.fatalErrorPlaybackCount;
            i22 += playbackStats.fatalErrorCount;
            i23 += playbackStats.nonFatalErrorCount;
            i5++;
            length = i3;
            i4 = 16;
        }
        return new PlaybackStats(i6, jArr, Collections.emptyList(), Collections.emptyList(), j13, i8, i9, i10, i11, j14, i12, i13, i14, i15, i16, j12, i17, Collections.emptyList(), Collections.emptyList(), j2, j3, j4, j5, j6, j7, i18, i19, i7, j15, i20, j16, j8, j9, j10, j11, i21, i22, i23, Collections.emptyList(), Collections.emptyList());
    }

    public float getAbandonedBeforeReadyRatio() {
        int i3 = this.abandonedBeforeReadyCount;
        int i4 = this.playbackCount;
        int i5 = this.foregroundPlaybackCount;
        int i6 = i3 - (i4 - i5);
        if (i5 == 0) {
            return 0.0f;
        }
        return ((float) i6) / ((float) i5);
    }

    public float getAudioUnderrunRate() {
        long totalPlayTimeMs = getTotalPlayTimeMs();
        if (totalPlayTimeMs == 0) {
            return 0.0f;
        }
        return (((float) this.totalAudioUnderruns) * 1000.0f) / ((float) totalPlayTimeMs);
    }

    public float getDroppedFramesRate() {
        long totalPlayTimeMs = getTotalPlayTimeMs();
        if (totalPlayTimeMs == 0) {
            return 0.0f;
        }
        return (((float) this.totalDroppedFrames) * 1000.0f) / ((float) totalPlayTimeMs);
    }

    public float getEndedRatio() {
        int i3 = this.foregroundPlaybackCount;
        if (i3 == 0) {
            return 0.0f;
        }
        return ((float) this.endedCount) / ((float) i3);
    }

    public float getFatalErrorRate() {
        long totalPlayTimeMs = getTotalPlayTimeMs();
        if (totalPlayTimeMs == 0) {
            return 0.0f;
        }
        return (((float) this.fatalErrorCount) * 1000.0f) / ((float) totalPlayTimeMs);
    }

    public float getFatalErrorRatio() {
        int i3 = this.foregroundPlaybackCount;
        if (i3 == 0) {
            return 0.0f;
        }
        return ((float) this.fatalErrorPlaybackCount) / ((float) i3);
    }

    public float getJoinTimeRatio() {
        long totalPlayAndWaitTimeMs = getTotalPlayAndWaitTimeMs();
        if (totalPlayAndWaitTimeMs == 0) {
            return 0.0f;
        }
        return ((float) getTotalJoinTimeMs()) / ((float) totalPlayAndWaitTimeMs);
    }

    public int getMeanAudioFormatBitrate() {
        long j2 = this.totalAudioFormatTimeMs;
        if (j2 == 0) {
            return -1;
        }
        return (int) (this.totalAudioFormatBitrateTimeProduct / j2);
    }

    public int getMeanBandwidth() {
        long j2 = this.totalBandwidthTimeMs;
        if (j2 == 0) {
            return -1;
        }
        return (int) ((this.totalBandwidthBytes * 8000) / j2);
    }

    public long getMeanElapsedTimeMs() {
        return this.playbackCount == 0 ? C.TIME_UNSET : getTotalElapsedTimeMs() / ((long) this.playbackCount);
    }

    public int getMeanInitialAudioFormatBitrate() {
        int i3 = this.initialAudioFormatBitrateCount;
        if (i3 == 0) {
            return -1;
        }
        return (int) (this.totalInitialAudioFormatBitrate / ((long) i3));
    }

    public int getMeanInitialVideoFormatBitrate() {
        int i3 = this.initialVideoFormatBitrateCount;
        if (i3 == 0) {
            return -1;
        }
        return (int) (this.totalInitialVideoFormatBitrate / ((long) i3));
    }

    public int getMeanInitialVideoFormatHeight() {
        int i3 = this.initialVideoFormatHeightCount;
        if (i3 == 0) {
            return -1;
        }
        return this.totalInitialVideoFormatHeight / i3;
    }

    public long getMeanJoinTimeMs() {
        int i3 = this.validJoinTimeCount;
        return i3 == 0 ? C.TIME_UNSET : this.totalValidJoinTimeMs / ((long) i3);
    }

    public float getMeanNonFatalErrorCount() {
        int i3 = this.foregroundPlaybackCount;
        if (i3 == 0) {
            return 0.0f;
        }
        return ((float) this.nonFatalErrorCount) / ((float) i3);
    }

    public float getMeanPauseBufferCount() {
        int i3 = this.foregroundPlaybackCount;
        if (i3 == 0) {
            return 0.0f;
        }
        return ((float) this.totalPauseBufferCount) / ((float) i3);
    }

    public float getMeanPauseCount() {
        int i3 = this.foregroundPlaybackCount;
        if (i3 == 0) {
            return 0.0f;
        }
        return ((float) this.totalPauseCount) / ((float) i3);
    }

    public long getMeanPausedTimeMs() {
        return this.foregroundPlaybackCount == 0 ? C.TIME_UNSET : getTotalPausedTimeMs() / ((long) this.foregroundPlaybackCount);
    }

    public long getMeanPlayAndWaitTimeMs() {
        return this.foregroundPlaybackCount == 0 ? C.TIME_UNSET : getTotalPlayAndWaitTimeMs() / ((long) this.foregroundPlaybackCount);
    }

    public long getMeanPlayTimeMs() {
        return this.foregroundPlaybackCount == 0 ? C.TIME_UNSET : getTotalPlayTimeMs() / ((long) this.foregroundPlaybackCount);
    }

    public float getMeanRebufferCount() {
        int i3 = this.foregroundPlaybackCount;
        if (i3 == 0) {
            return 0.0f;
        }
        return ((float) this.totalRebufferCount) / ((float) i3);
    }

    public long getMeanRebufferTimeMs() {
        return this.foregroundPlaybackCount == 0 ? C.TIME_UNSET : getTotalRebufferTimeMs() / ((long) this.foregroundPlaybackCount);
    }

    public float getMeanSeekCount() {
        int i3 = this.foregroundPlaybackCount;
        if (i3 == 0) {
            return 0.0f;
        }
        return ((float) this.totalSeekCount) / ((float) i3);
    }

    public long getMeanSeekTimeMs() {
        return this.foregroundPlaybackCount == 0 ? C.TIME_UNSET : getTotalSeekTimeMs() / ((long) this.foregroundPlaybackCount);
    }

    public long getMeanSingleRebufferTimeMs() {
        if (this.totalRebufferCount == 0) {
            return C.TIME_UNSET;
        }
        return (getPlaybackStateDurationMs(7) + getPlaybackStateDurationMs(6)) / ((long) this.totalRebufferCount);
    }

    public long getMeanSingleSeekTimeMs() {
        return this.totalSeekCount == 0 ? C.TIME_UNSET : getTotalSeekTimeMs() / ((long) this.totalSeekCount);
    }

    public float getMeanTimeBetweenFatalErrors() {
        return 1.0f / getFatalErrorRate();
    }

    public float getMeanTimeBetweenNonFatalErrors() {
        return 1.0f / getNonFatalErrorRate();
    }

    public float getMeanTimeBetweenRebuffers() {
        return 1.0f / getRebufferRate();
    }

    public int getMeanVideoFormatBitrate() {
        long j2 = this.totalVideoFormatBitrateTimeMs;
        if (j2 == 0) {
            return -1;
        }
        return (int) (this.totalVideoFormatBitrateTimeProduct / j2);
    }

    public int getMeanVideoFormatHeight() {
        long j2 = this.totalVideoFormatHeightTimeMs;
        if (j2 == 0) {
            return -1;
        }
        return (int) (this.totalVideoFormatHeightTimeProduct / j2);
    }

    public long getMeanWaitTimeMs() {
        return this.foregroundPlaybackCount == 0 ? C.TIME_UNSET : getTotalWaitTimeMs() / ((long) this.foregroundPlaybackCount);
    }

    public long getMediaTimeMsAtRealtimeMs(long j2) {
        if (this.mediaTimeHistory.isEmpty()) {
            return C.TIME_UNSET;
        }
        int i3 = 0;
        while (i3 < this.mediaTimeHistory.size() && this.mediaTimeHistory.get(i3)[0] <= j2) {
            i3++;
        }
        if (i3 == 0) {
            return this.mediaTimeHistory.get(0)[1];
        }
        if (i3 == this.mediaTimeHistory.size()) {
            return ((long[]) a.h(this.mediaTimeHistory, 1))[1];
        }
        int i4 = i3 - 1;
        long j3 = this.mediaTimeHistory.get(i4)[0];
        long j4 = this.mediaTimeHistory.get(i4)[1];
        long j5 = this.mediaTimeHistory.get(i3)[0];
        long j6 = this.mediaTimeHistory.get(i3)[1];
        long j7 = j5 - j3;
        if (j7 == 0) {
            return j4;
        }
        return j4 + ((long) (((float) (j6 - j4)) * (((float) (j2 - j3)) / ((float) j7))));
    }

    public float getNonFatalErrorRate() {
        long totalPlayTimeMs = getTotalPlayTimeMs();
        if (totalPlayTimeMs == 0) {
            return 0.0f;
        }
        return (((float) this.nonFatalErrorCount) * 1000.0f) / ((float) totalPlayTimeMs);
    }

    public int getPlaybackStateAtTime(long j2) {
        int i3 = 0;
        for (EventTimeAndPlaybackState next : this.playbackStateHistory) {
            if (next.eventTime.realtimeMs > j2) {
                break;
            }
            i3 = next.playbackState;
        }
        return i3;
    }

    public long getPlaybackStateDurationMs(int i3) {
        return this.playbackStateDurationsMs[i3];
    }

    public float getRebufferRate() {
        long totalPlayTimeMs = getTotalPlayTimeMs();
        if (totalPlayTimeMs == 0) {
            return 0.0f;
        }
        return (((float) this.totalRebufferCount) * 1000.0f) / ((float) totalPlayTimeMs);
    }

    public float getRebufferTimeRatio() {
        long totalPlayAndWaitTimeMs = getTotalPlayAndWaitTimeMs();
        if (totalPlayAndWaitTimeMs == 0) {
            return 0.0f;
        }
        return ((float) getTotalRebufferTimeMs()) / ((float) totalPlayAndWaitTimeMs);
    }

    public float getSeekTimeRatio() {
        long totalPlayAndWaitTimeMs = getTotalPlayAndWaitTimeMs();
        if (totalPlayAndWaitTimeMs == 0) {
            return 0.0f;
        }
        return ((float) getTotalSeekTimeMs()) / ((float) totalPlayAndWaitTimeMs);
    }

    public long getTotalElapsedTimeMs() {
        long j2 = 0;
        for (int i3 = 0; i3 < 16; i3++) {
            j2 += this.playbackStateDurationsMs[i3];
        }
        return j2;
    }

    public long getTotalJoinTimeMs() {
        return getPlaybackStateDurationMs(2);
    }

    public long getTotalPausedTimeMs() {
        return getPlaybackStateDurationMs(7) + getPlaybackStateDurationMs(4);
    }

    public long getTotalPlayAndWaitTimeMs() {
        return getTotalWaitTimeMs() + getTotalPlayTimeMs();
    }

    public long getTotalPlayTimeMs() {
        return getPlaybackStateDurationMs(3);
    }

    public long getTotalRebufferTimeMs() {
        return getPlaybackStateDurationMs(6);
    }

    public long getTotalSeekTimeMs() {
        return getPlaybackStateDurationMs(5);
    }

    public long getTotalWaitTimeMs() {
        return getPlaybackStateDurationMs(5) + getPlaybackStateDurationMs(6) + getPlaybackStateDurationMs(2);
    }

    public float getWaitTimeRatio() {
        long totalPlayAndWaitTimeMs = getTotalPlayAndWaitTimeMs();
        if (totalPlayAndWaitTimeMs == 0) {
            return 0.0f;
        }
        return ((float) getTotalWaitTimeMs()) / ((float) totalPlayAndWaitTimeMs);
    }
}
