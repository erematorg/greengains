package com.appsamurai.storyly.exoplayer2.core.source;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.MediaItem;
import com.appsamurai.storyly.exoplayer2.common.Timeline;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.core.source.MediaSource;
import com.appsamurai.storyly.exoplayer2.core.upstream.Allocator;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.TransferListener;
import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class MergingMediaSource extends CompositeMediaSource<Integer> {
    private static final MediaItem EMPTY_MEDIA_ITEM = new MediaItem.Builder().setMediaId("MergingMediaSource").build();
    private static final int PERIOD_COUNT_UNSET = -1;
    private final boolean adjustPeriodTimeOffsets;
    private final boolean clipDurations;
    private final Map<Object, Long> clippedDurationsUs;
    private final Multimap<Object, ClippingMediaPeriod> clippedMediaPeriods;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private final MediaSource[] mediaSources;
    @Nullable
    private IllegalMergeException mergeError;
    private final ArrayList<MediaSource> pendingTimelineSources;
    private int periodCount;
    private long[][] periodTimeOffsetsUs;
    private final Timeline[] timelines;

    public static final class ClippedTimeline extends ForwardingTimeline {
        private final long[] periodDurationsUs;
        private final long[] windowDurationsUs;

        public ClippedTimeline(Timeline timeline, Map<Object, Long> map) {
            super(timeline);
            int windowCount = timeline.getWindowCount();
            this.windowDurationsUs = new long[timeline.getWindowCount()];
            Timeline.Window window = new Timeline.Window();
            for (int i3 = 0; i3 < windowCount; i3++) {
                this.windowDurationsUs[i3] = timeline.getWindow(i3, window).durationUs;
            }
            int periodCount = timeline.getPeriodCount();
            this.periodDurationsUs = new long[periodCount];
            Timeline.Period period = new Timeline.Period();
            for (int i4 = 0; i4 < periodCount; i4++) {
                timeline.getPeriod(i4, period, true);
                long longValue = ((Long) Assertions.checkNotNull(map.get(period.uid))).longValue();
                long[] jArr = this.periodDurationsUs;
                longValue = longValue == Long.MIN_VALUE ? period.durationUs : longValue;
                jArr[i4] = longValue;
                long j2 = period.durationUs;
                if (j2 != C.TIME_UNSET) {
                    long[] jArr2 = this.windowDurationsUs;
                    int i5 = period.windowIndex;
                    jArr2[i5] = jArr2[i5] - (j2 - longValue);
                }
            }
        }

        public Timeline.Period getPeriod(int i3, Timeline.Period period, boolean z2) {
            super.getPeriod(i3, period, z2);
            period.durationUs = this.periodDurationsUs[i3];
            return period;
        }

        public Timeline.Window getWindow(int i3, Timeline.Window window, long j2) {
            long j3;
            super.getWindow(i3, window, j2);
            long j4 = this.windowDurationsUs[i3];
            window.durationUs = j4;
            if (j4 != C.TIME_UNSET) {
                long j5 = window.defaultPositionUs;
                if (j5 != C.TIME_UNSET) {
                    j3 = Math.min(j5, j4);
                    window.defaultPositionUs = j3;
                    return window;
                }
            }
            j3 = window.defaultPositionUs;
            window.defaultPositionUs = j3;
            return window;
        }
    }

    public static final class IllegalMergeException extends IOException {
        public static final int REASON_PERIOD_COUNT_MISMATCH = 0;
        public final int reason;

        @Documented
        @Target({ElementType.TYPE_USE})
        @Retention(RetentionPolicy.SOURCE)
        public @interface Reason {
        }

        public IllegalMergeException(int i3) {
            this.reason = i3;
        }
    }

    public MergingMediaSource(MediaSource... mediaSourceArr) {
        this(false, mediaSourceArr);
    }

    private void computePeriodTimeOffsets() {
        Timeline.Period period = new Timeline.Period();
        for (int i3 = 0; i3 < this.periodCount; i3++) {
            long j2 = -this.timelines[0].getPeriod(i3, period).getPositionInWindowUs();
            int i4 = 1;
            while (true) {
                Timeline[] timelineArr = this.timelines;
                if (i4 >= timelineArr.length) {
                    break;
                }
                this.periodTimeOffsetsUs[i3][i4] = j2 - (-timelineArr[i4].getPeriod(i3, period).getPositionInWindowUs());
                i4++;
            }
        }
    }

    private void updateClippedDuration() {
        Timeline[] timelineArr;
        Timeline.Period period = new Timeline.Period();
        for (int i3 = 0; i3 < this.periodCount; i3++) {
            int i4 = 0;
            long j2 = Long.MIN_VALUE;
            while (true) {
                timelineArr = this.timelines;
                if (i4 >= timelineArr.length) {
                    break;
                }
                long durationUs = timelineArr[i4].getPeriod(i3, period).getDurationUs();
                if (durationUs != C.TIME_UNSET) {
                    long j3 = durationUs + this.periodTimeOffsetsUs[i3][i4];
                    if (j2 == Long.MIN_VALUE || j3 < j2) {
                        j2 = j3;
                    }
                }
                i4++;
            }
            Object uidOfPeriod = timelineArr[0].getUidOfPeriod(i3);
            this.clippedDurationsUs.put(uidOfPeriod, Long.valueOf(j2));
            for (ClippingMediaPeriod updateClipping : this.clippedMediaPeriods.get(uidOfPeriod)) {
                updateClipping.updateClipping(0, j2);
            }
        }
    }

    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j2) {
        int length = this.mediaSources.length;
        MediaPeriod[] mediaPeriodArr = new MediaPeriod[length];
        int indexOfPeriod = this.timelines[0].getIndexOfPeriod(mediaPeriodId.periodUid);
        for (int i3 = 0; i3 < length; i3++) {
            mediaPeriodArr[i3] = this.mediaSources[i3].createPeriod(mediaPeriodId.copyWithPeriodUid(this.timelines[i3].getUidOfPeriod(indexOfPeriod)), allocator, j2 - this.periodTimeOffsetsUs[indexOfPeriod][i3]);
        }
        MergingMediaPeriod mergingMediaPeriod = new MergingMediaPeriod(this.compositeSequenceableLoaderFactory, this.periodTimeOffsetsUs[indexOfPeriod], mediaPeriodArr);
        if (!this.clipDurations) {
            return mergingMediaPeriod;
        }
        ClippingMediaPeriod clippingMediaPeriod = new ClippingMediaPeriod(mergingMediaPeriod, true, 0, ((Long) Assertions.checkNotNull(this.clippedDurationsUs.get(mediaPeriodId.periodUid))).longValue());
        this.clippedMediaPeriods.put(mediaPeriodId.periodUid, clippingMediaPeriod);
        return clippingMediaPeriod;
    }

    public MediaItem getMediaItem() {
        MediaSource[] mediaSourceArr = this.mediaSources;
        return mediaSourceArr.length > 0 ? mediaSourceArr[0].getMediaItem() : EMPTY_MEDIA_ITEM;
    }

    public void maybeThrowSourceInfoRefreshError() throws IOException {
        IllegalMergeException illegalMergeException = this.mergeError;
        if (illegalMergeException == null) {
            super.maybeThrowSourceInfoRefreshError();
            return;
        }
        throw illegalMergeException;
    }

    public void prepareSourceInternal(@Nullable TransferListener transferListener) {
        super.prepareSourceInternal(transferListener);
        for (int i3 = 0; i3 < this.mediaSources.length; i3++) {
            prepareChildSource(Integer.valueOf(i3), this.mediaSources[i3]);
        }
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
        if (this.clipDurations) {
            ClippingMediaPeriod clippingMediaPeriod = (ClippingMediaPeriod) mediaPeriod;
            Iterator<Map.Entry<Object, ClippingMediaPeriod>> it = this.clippedMediaPeriods.entries().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry next = it.next();
                if (((ClippingMediaPeriod) next.getValue()).equals(clippingMediaPeriod)) {
                    this.clippedMediaPeriods.remove(next.getKey(), next.getValue());
                    break;
                }
            }
            mediaPeriod = clippingMediaPeriod.mediaPeriod;
        }
        MergingMediaPeriod mergingMediaPeriod = (MergingMediaPeriod) mediaPeriod;
        int i3 = 0;
        while (true) {
            MediaSource[] mediaSourceArr = this.mediaSources;
            if (i3 < mediaSourceArr.length) {
                mediaSourceArr[i3].releasePeriod(mergingMediaPeriod.getChildPeriod(i3));
                i3++;
            } else {
                return;
            }
        }
    }

    public void releaseSourceInternal() {
        super.releaseSourceInternal();
        Arrays.fill(this.timelines, (Object) null);
        this.periodCount = -1;
        this.mergeError = null;
        this.pendingTimelineSources.clear();
        Collections.addAll(this.pendingTimelineSources, this.mediaSources);
    }

    public MergingMediaSource(boolean z2, MediaSource... mediaSourceArr) {
        this(z2, false, mediaSourceArr);
    }

    @Nullable
    public MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(Integer num, MediaSource.MediaPeriodId mediaPeriodId) {
        if (num.intValue() == 0) {
            return mediaPeriodId;
        }
        return null;
    }

    public void onChildSourceInfoRefreshed(Integer num, MediaSource mediaSource, Timeline timeline) {
        if (this.mergeError == null) {
            if (this.periodCount == -1) {
                this.periodCount = timeline.getPeriodCount();
            } else if (timeline.getPeriodCount() != this.periodCount) {
                this.mergeError = new IllegalMergeException(0);
                return;
            }
            if (this.periodTimeOffsetsUs.length == 0) {
                int i3 = this.periodCount;
                int[] iArr = new int[2];
                iArr[1] = this.timelines.length;
                iArr[0] = i3;
                this.periodTimeOffsetsUs = (long[][]) Array.newInstance(Long.TYPE, iArr);
            }
            this.pendingTimelineSources.remove(mediaSource);
            this.timelines[num.intValue()] = timeline;
            if (this.pendingTimelineSources.isEmpty()) {
                if (this.adjustPeriodTimeOffsets) {
                    computePeriodTimeOffsets();
                }
                ClippedTimeline clippedTimeline = this.timelines[0];
                if (this.clipDurations) {
                    updateClippedDuration();
                    clippedTimeline = new ClippedTimeline(clippedTimeline, this.clippedDurationsUs);
                }
                refreshSourceInfo(clippedTimeline);
            }
        }
    }

    public MergingMediaSource(boolean z2, boolean z3, MediaSource... mediaSourceArr) {
        this(z2, z3, new DefaultCompositeSequenceableLoaderFactory(), mediaSourceArr);
    }

    public MergingMediaSource(boolean z2, boolean z3, CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory2, MediaSource... mediaSourceArr) {
        this.adjustPeriodTimeOffsets = z2;
        this.clipDurations = z3;
        this.mediaSources = mediaSourceArr;
        this.compositeSequenceableLoaderFactory = compositeSequenceableLoaderFactory2;
        this.pendingTimelineSources = new ArrayList<>(Arrays.asList(mediaSourceArr));
        this.periodCount = -1;
        this.timelines = new Timeline[mediaSourceArr.length];
        this.periodTimeOffsetsUs = new long[0][];
        this.clippedDurationsUs = new HashMap();
        this.clippedMediaPeriods = MultimapBuilder.hashKeys().arrayListValues().build();
    }
}
