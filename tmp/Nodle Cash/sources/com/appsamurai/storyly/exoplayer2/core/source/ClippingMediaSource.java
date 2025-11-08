package com.appsamurai.storyly.exoplayer2.core.source;

import androidx.annotation.Nullable;
import androidx.core.os.EnvironmentCompat;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.MediaItem;
import com.appsamurai.storyly.exoplayer2.common.Timeline;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.source.MediaSource;
import com.appsamurai.storyly.exoplayer2.core.upstream.Allocator;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.TransferListener;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;

public final class ClippingMediaSource extends CompositeMediaSource<Void> {
    private final boolean allowDynamicClippingUpdates;
    @Nullable
    private IllegalClippingException clippingError;
    @Nullable
    private ClippingTimeline clippingTimeline;
    private final boolean enableInitialDiscontinuity;
    private final long endUs;
    private final ArrayList<ClippingMediaPeriod> mediaPeriods;
    private final MediaSource mediaSource;
    private long periodEndUs;
    private long periodStartUs;
    private final boolean relativeToDefaultPosition;
    private final long startUs;
    private final Timeline.Window window;

    public static final class ClippingTimeline extends ForwardingTimeline {
        private final long durationUs;
        private final long endUs;
        private final boolean isDynamic;
        private final long startUs;

        public ClippingTimeline(Timeline timeline, long j2, long j3) throws IllegalClippingException {
            super(timeline);
            boolean z2 = false;
            if (timeline.getPeriodCount() == 1) {
                Timeline.Window window = timeline.getWindow(0, new Timeline.Window());
                long max = Math.max(0, j2);
                if (window.isPlaceholder || max == 0 || window.isSeekable) {
                    long max2 = j3 == Long.MIN_VALUE ? window.durationUs : Math.max(0, j3);
                    long j4 = window.durationUs;
                    if (j4 != C.TIME_UNSET) {
                        max2 = max2 > j4 ? j4 : max2;
                        if (max > max2) {
                            throw new IllegalClippingException(2);
                        }
                    }
                    this.startUs = max;
                    this.endUs = max2;
                    int i3 = (max2 > C.TIME_UNSET ? 1 : (max2 == C.TIME_UNSET ? 0 : -1));
                    this.durationUs = i3 == 0 ? -9223372036854775807L : max2 - max;
                    if (window.isDynamic && (i3 == 0 || (j4 != C.TIME_UNSET && max2 == j4))) {
                        z2 = true;
                    }
                    this.isDynamic = z2;
                    return;
                }
                throw new IllegalClippingException(1);
            }
            throw new IllegalClippingException(0);
        }

        public Timeline.Period getPeriod(int i3, Timeline.Period period, boolean z2) {
            this.timeline.getPeriod(0, period, z2);
            long positionInWindowUs = period.getPositionInWindowUs() - this.startUs;
            long j2 = this.durationUs;
            return period.set(period.id, period.uid, 0, j2 == C.TIME_UNSET ? -9223372036854775807L : j2 - positionInWindowUs, positionInWindowUs);
        }

        public Timeline.Window getWindow(int i3, Timeline.Window window, long j2) {
            this.timeline.getWindow(0, window, 0);
            long j3 = window.positionInFirstPeriodUs;
            long j4 = this.startUs;
            window.positionInFirstPeriodUs = j3 + j4;
            window.durationUs = this.durationUs;
            window.isDynamic = this.isDynamic;
            long j5 = window.defaultPositionUs;
            if (j5 != C.TIME_UNSET) {
                long max = Math.max(j5, j4);
                window.defaultPositionUs = max;
                long j6 = this.endUs;
                if (j6 != C.TIME_UNSET) {
                    max = Math.min(max, j6);
                }
                window.defaultPositionUs = max - this.startUs;
            }
            long usToMs = Util.usToMs(this.startUs);
            long j7 = window.presentationStartTimeMs;
            if (j7 != C.TIME_UNSET) {
                window.presentationStartTimeMs = j7 + usToMs;
            }
            long j8 = window.windowStartTimeMs;
            if (j8 != C.TIME_UNSET) {
                window.windowStartTimeMs = j8 + usToMs;
            }
            return window;
        }
    }

    public static final class IllegalClippingException extends IOException {
        public static final int REASON_INVALID_PERIOD_COUNT = 0;
        public static final int REASON_NOT_SEEKABLE_TO_START = 1;
        public static final int REASON_START_EXCEEDS_END = 2;
        public final int reason;

        @Documented
        @Target({ElementType.TYPE_USE})
        @Retention(RetentionPolicy.SOURCE)
        public @interface Reason {
        }

        public IllegalClippingException(int i3) {
            super("Illegal clipping: " + getReasonDescription(i3));
            this.reason = i3;
        }

        private static String getReasonDescription(int i3) {
            return i3 != 0 ? i3 != 1 ? i3 != 2 ? EnvironmentCompat.MEDIA_UNKNOWN : "start exceeds end" : "not seekable to start" : "invalid period count";
        }
    }

    public ClippingMediaSource(MediaSource mediaSource2, long j2, long j3) {
        this(mediaSource2, j2, j3, true, false, false);
    }

    private void refreshClippedTimeline(Timeline timeline) {
        long j2;
        timeline.getWindow(0, this.window);
        long positionInFirstPeriodUs = this.window.getPositionInFirstPeriodUs();
        long j3 = Long.MIN_VALUE;
        if (this.clippingTimeline == null || this.mediaPeriods.isEmpty() || this.allowDynamicClippingUpdates) {
            long j4 = this.startUs;
            long j5 = this.endUs;
            if (this.relativeToDefaultPosition) {
                long defaultPositionUs = this.window.getDefaultPositionUs();
                j4 += defaultPositionUs;
                j5 += defaultPositionUs;
            }
            this.periodStartUs = positionInFirstPeriodUs + j4;
            if (this.endUs != Long.MIN_VALUE) {
                j3 = positionInFirstPeriodUs + j5;
            }
            this.periodEndUs = j3;
            int size = this.mediaPeriods.size();
            for (int i3 = 0; i3 < size; i3++) {
                this.mediaPeriods.get(i3).updateClipping(this.periodStartUs, this.periodEndUs);
            }
            j2 = j4;
            j3 = j5;
        } else {
            long j6 = this.periodStartUs - positionInFirstPeriodUs;
            if (this.endUs != Long.MIN_VALUE) {
                j3 = this.periodEndUs - positionInFirstPeriodUs;
            }
            j2 = j6;
        }
        try {
            ClippingTimeline clippingTimeline2 = new ClippingTimeline(timeline, j2, j3);
            this.clippingTimeline = clippingTimeline2;
            refreshSourceInfo(clippingTimeline2);
        } catch (IllegalClippingException e3) {
            this.clippingError = e3;
            for (int i4 = 0; i4 < this.mediaPeriods.size(); i4++) {
                this.mediaPeriods.get(i4).setClippingError(this.clippingError);
            }
        }
    }

    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j2) {
        ClippingMediaPeriod clippingMediaPeriod = new ClippingMediaPeriod(this.mediaSource.createPeriod(mediaPeriodId, allocator, j2), this.enableInitialDiscontinuity, this.periodStartUs, this.periodEndUs);
        this.mediaPeriods.add(clippingMediaPeriod);
        return clippingMediaPeriod;
    }

    public MediaItem getMediaItem() {
        return this.mediaSource.getMediaItem();
    }

    public void maybeThrowSourceInfoRefreshError() throws IOException {
        IllegalClippingException illegalClippingException = this.clippingError;
        if (illegalClippingException == null) {
            super.maybeThrowSourceInfoRefreshError();
            return;
        }
        throw illegalClippingException;
    }

    public void prepareSourceInternal(@Nullable TransferListener transferListener) {
        super.prepareSourceInternal(transferListener);
        prepareChildSource(null, this.mediaSource);
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
        Assertions.checkState(this.mediaPeriods.remove(mediaPeriod));
        this.mediaSource.releasePeriod(((ClippingMediaPeriod) mediaPeriod).mediaPeriod);
        if (this.mediaPeriods.isEmpty() && !this.allowDynamicClippingUpdates) {
            refreshClippedTimeline(((ClippingTimeline) Assertions.checkNotNull(this.clippingTimeline)).timeline);
        }
    }

    public void releaseSourceInternal() {
        super.releaseSourceInternal();
        this.clippingError = null;
        this.clippingTimeline = null;
    }

    public ClippingMediaSource(MediaSource mediaSource2, long j2) {
        this(mediaSource2, 0, j2, true, false, true);
    }

    public void onChildSourceInfoRefreshed(Void voidR, MediaSource mediaSource2, Timeline timeline) {
        if (this.clippingError == null) {
            refreshClippedTimeline(timeline);
        }
    }

    public ClippingMediaSource(MediaSource mediaSource2, long j2, long j3, boolean z2, boolean z3, boolean z4) {
        Assertions.checkArgument(j2 >= 0);
        this.mediaSource = (MediaSource) Assertions.checkNotNull(mediaSource2);
        this.startUs = j2;
        this.endUs = j3;
        this.enableInitialDiscontinuity = z2;
        this.allowDynamicClippingUpdates = z3;
        this.relativeToDefaultPosition = z4;
        this.mediaPeriods = new ArrayList<>();
        this.window = new Timeline.Window();
    }
}
