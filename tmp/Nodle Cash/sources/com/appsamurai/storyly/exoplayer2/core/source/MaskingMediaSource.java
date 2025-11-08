package com.appsamurai.storyly.exoplayer2.core.source;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.MediaItem;
import com.appsamurai.storyly.exoplayer2.common.Timeline;
import com.appsamurai.storyly.exoplayer2.common.source.ads.AdPlaybackState;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.source.MediaSource;
import com.appsamurai.storyly.exoplayer2.core.upstream.Allocator;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.TransferListener;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

public final class MaskingMediaSource extends CompositeMediaSource<Void> {
    private boolean hasRealTimeline;
    private boolean hasStartedPreparing;
    private boolean isPrepared;
    private final MediaSource mediaSource;
    private final Timeline.Period period;
    private MaskingTimeline timeline;
    @Nullable
    private MaskingMediaPeriod unpreparedMaskingMediaPeriod;
    private final boolean useLazyPreparation;
    private final Timeline.Window window;

    public static final class MaskingTimeline extends ForwardingTimeline {
        public static final Object MASKING_EXTERNAL_PERIOD_UID = new Object();
        /* access modifiers changed from: private */
        @Nullable
        public final Object replacedInternalPeriodUid;
        @Nullable
        private final Object replacedInternalWindowUid;

        private MaskingTimeline(Timeline timeline, @Nullable Object obj, @Nullable Object obj2) {
            super(timeline);
            this.replacedInternalWindowUid = obj;
            this.replacedInternalPeriodUid = obj2;
        }

        public static MaskingTimeline createWithPlaceholderTimeline(MediaItem mediaItem) {
            return new MaskingTimeline(new PlaceholderTimeline(mediaItem), Timeline.Window.SINGLE_WINDOW_UID, MASKING_EXTERNAL_PERIOD_UID);
        }

        public static MaskingTimeline createWithRealTimeline(Timeline timeline, @Nullable Object obj, @Nullable Object obj2) {
            return new MaskingTimeline(timeline, obj, obj2);
        }

        public MaskingTimeline cloneWithUpdatedTimeline(Timeline timeline) {
            return new MaskingTimeline(timeline, this.replacedInternalWindowUid, this.replacedInternalPeriodUid);
        }

        public int getIndexOfPeriod(Object obj) {
            Object obj2;
            Timeline timeline = this.timeline;
            if (MASKING_EXTERNAL_PERIOD_UID.equals(obj) && (obj2 = this.replacedInternalPeriodUid) != null) {
                obj = obj2;
            }
            return timeline.getIndexOfPeriod(obj);
        }

        public Timeline.Period getPeriod(int i3, Timeline.Period period, boolean z2) {
            this.timeline.getPeriod(i3, period, z2);
            if (Util.areEqual(period.uid, this.replacedInternalPeriodUid) && z2) {
                period.uid = MASKING_EXTERNAL_PERIOD_UID;
            }
            return period;
        }

        public Timeline getTimeline() {
            return this.timeline;
        }

        public Object getUidOfPeriod(int i3) {
            Object uidOfPeriod = this.timeline.getUidOfPeriod(i3);
            return Util.areEqual(uidOfPeriod, this.replacedInternalPeriodUid) ? MASKING_EXTERNAL_PERIOD_UID : uidOfPeriod;
        }

        public Timeline.Window getWindow(int i3, Timeline.Window window, long j2) {
            this.timeline.getWindow(i3, window, j2);
            if (Util.areEqual(window.uid, this.replacedInternalWindowUid)) {
                window.uid = Timeline.Window.SINGLE_WINDOW_UID;
            }
            return window;
        }
    }

    @VisibleForTesting
    public static final class PlaceholderTimeline extends Timeline {
        private final MediaItem mediaItem;

        public PlaceholderTimeline(MediaItem mediaItem2) {
            this.mediaItem = mediaItem2;
        }

        public int getIndexOfPeriod(Object obj) {
            return obj == MaskingTimeline.MASKING_EXTERNAL_PERIOD_UID ? 0 : -1;
        }

        public Timeline.Period getPeriod(int i3, Timeline.Period period, boolean z2) {
            Object obj = null;
            Integer num = z2 ? 0 : null;
            if (z2) {
                obj = MaskingTimeline.MASKING_EXTERNAL_PERIOD_UID;
            }
            period.set(num, obj, 0, C.TIME_UNSET, 0, AdPlaybackState.NONE, true);
            return period;
        }

        public int getPeriodCount() {
            return 1;
        }

        public Object getUidOfPeriod(int i3) {
            return MaskingTimeline.MASKING_EXTERNAL_PERIOD_UID;
        }

        public Timeline.Window getWindow(int i3, Timeline.Window window, long j2) {
            Timeline.Window window2 = window;
            window.set(Timeline.Window.SINGLE_WINDOW_UID, this.mediaItem, (Object) null, C.TIME_UNSET, C.TIME_UNSET, C.TIME_UNSET, false, true, (MediaItem.LiveConfiguration) null, 0, C.TIME_UNSET, 0, 0, 0);
            Timeline.Window window3 = window;
            window3.isPlaceholder = true;
            return window3;
        }

        public int getWindowCount() {
            return 1;
        }
    }

    public MaskingMediaSource(MediaSource mediaSource2, boolean z2) {
        this.mediaSource = mediaSource2;
        this.useLazyPreparation = z2 && mediaSource2.isSingleWindow();
        this.window = new Timeline.Window();
        this.period = new Timeline.Period();
        Timeline initialTimeline = mediaSource2.getInitialTimeline();
        if (initialTimeline != null) {
            this.timeline = MaskingTimeline.createWithRealTimeline(initialTimeline, (Object) null, (Object) null);
            this.hasRealTimeline = true;
            return;
        }
        this.timeline = MaskingTimeline.createWithPlaceholderTimeline(mediaSource2.getMediaItem());
    }

    private Object getExternalPeriodUid(Object obj) {
        return (this.timeline.replacedInternalPeriodUid == null || !this.timeline.replacedInternalPeriodUid.equals(obj)) ? obj : MaskingTimeline.MASKING_EXTERNAL_PERIOD_UID;
    }

    private Object getInternalPeriodUid(Object obj) {
        return (this.timeline.replacedInternalPeriodUid == null || !obj.equals(MaskingTimeline.MASKING_EXTERNAL_PERIOD_UID)) ? obj : this.timeline.replacedInternalPeriodUid;
    }

    @RequiresNonNull({"unpreparedMaskingMediaPeriod"})
    private void setPreparePositionOverrideToUnpreparedMaskingPeriod(long j2) {
        MaskingMediaPeriod maskingMediaPeriod = this.unpreparedMaskingMediaPeriod;
        int indexOfPeriod = this.timeline.getIndexOfPeriod(maskingMediaPeriod.id.periodUid);
        if (indexOfPeriod != -1) {
            long j3 = this.timeline.getPeriod(indexOfPeriod, this.period).durationUs;
            if (j3 != C.TIME_UNSET && j2 >= j3) {
                j2 = Math.max(0, j3 - 1);
            }
            maskingMediaPeriod.overridePreparePositionUs(j2);
        }
    }

    public MediaItem getMediaItem() {
        return this.mediaSource.getMediaItem();
    }

    public Timeline getTimeline() {
        return this.timeline;
    }

    public void maybeThrowSourceInfoRefreshError() {
    }

    public void prepareSourceInternal(@Nullable TransferListener transferListener) {
        super.prepareSourceInternal(transferListener);
        if (!this.useLazyPreparation) {
            this.hasStartedPreparing = true;
            prepareChildSource(null, this.mediaSource);
        }
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
        ((MaskingMediaPeriod) mediaPeriod).releasePeriod();
        if (mediaPeriod == this.unpreparedMaskingMediaPeriod) {
            this.unpreparedMaskingMediaPeriod = null;
        }
    }

    public void releaseSourceInternal() {
        this.isPrepared = false;
        this.hasStartedPreparing = false;
        super.releaseSourceInternal();
    }

    public MaskingMediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j2) {
        MaskingMediaPeriod maskingMediaPeriod = new MaskingMediaPeriod(mediaPeriodId, allocator, j2);
        maskingMediaPeriod.setMediaSource(this.mediaSource);
        if (this.isPrepared) {
            maskingMediaPeriod.createPeriod(mediaPeriodId.copyWithPeriodUid(getInternalPeriodUid(mediaPeriodId.periodUid)));
        } else {
            this.unpreparedMaskingMediaPeriod = maskingMediaPeriod;
            if (!this.hasStartedPreparing) {
                this.hasStartedPreparing = true;
                prepareChildSource(null, this.mediaSource);
            }
        }
        return maskingMediaPeriod;
    }

    @Nullable
    public MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(Void voidR, MediaSource.MediaPeriodId mediaPeriodId) {
        return mediaPeriodId.copyWithPeriodUid(getExternalPeriodUid(mediaPeriodId.periodUid));
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x009e  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00bb  */
    /* JADX WARNING: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onChildSourceInfoRefreshed(java.lang.Void r13, com.appsamurai.storyly.exoplayer2.core.source.MediaSource r14, com.appsamurai.storyly.exoplayer2.common.Timeline r15) {
        /*
            r12 = this;
            boolean r13 = r12.isPrepared
            if (r13 == 0) goto L_0x0019
            com.appsamurai.storyly.exoplayer2.core.source.MaskingMediaSource$MaskingTimeline r13 = r12.timeline
            com.appsamurai.storyly.exoplayer2.core.source.MaskingMediaSource$MaskingTimeline r13 = r13.cloneWithUpdatedTimeline(r15)
            r12.timeline = r13
            com.appsamurai.storyly.exoplayer2.core.source.MaskingMediaPeriod r13 = r12.unpreparedMaskingMediaPeriod
            if (r13 == 0) goto L_0x00ae
            long r13 = r13.getPreparePositionOverrideUs()
            r12.setPreparePositionOverrideToUnpreparedMaskingPeriod(r13)
            goto L_0x00ae
        L_0x0019:
            boolean r13 = r15.isEmpty()
            if (r13 == 0) goto L_0x0036
            boolean r13 = r12.hasRealTimeline
            if (r13 == 0) goto L_0x002a
            com.appsamurai.storyly.exoplayer2.core.source.MaskingMediaSource$MaskingTimeline r13 = r12.timeline
            com.appsamurai.storyly.exoplayer2.core.source.MaskingMediaSource$MaskingTimeline r13 = r13.cloneWithUpdatedTimeline(r15)
            goto L_0x0032
        L_0x002a:
            java.lang.Object r13 = com.appsamurai.storyly.exoplayer2.common.Timeline.Window.SINGLE_WINDOW_UID
            java.lang.Object r14 = com.appsamurai.storyly.exoplayer2.core.source.MaskingMediaSource.MaskingTimeline.MASKING_EXTERNAL_PERIOD_UID
            com.appsamurai.storyly.exoplayer2.core.source.MaskingMediaSource$MaskingTimeline r13 = com.appsamurai.storyly.exoplayer2.core.source.MaskingMediaSource.MaskingTimeline.createWithRealTimeline(r15, r13, r14)
        L_0x0032:
            r12.timeline = r13
            goto L_0x00ae
        L_0x0036:
            com.appsamurai.storyly.exoplayer2.common.Timeline$Window r13 = r12.window
            r14 = 0
            r15.getWindow(r14, r13)
            com.appsamurai.storyly.exoplayer2.common.Timeline$Window r13 = r12.window
            long r0 = r13.getDefaultPositionUs()
            com.appsamurai.storyly.exoplayer2.common.Timeline$Window r13 = r12.window
            java.lang.Object r13 = r13.uid
            com.appsamurai.storyly.exoplayer2.core.source.MaskingMediaPeriod r2 = r12.unpreparedMaskingMediaPeriod
            if (r2 == 0) goto L_0x0074
            long r2 = r2.getPreparePositionUs()
            com.appsamurai.storyly.exoplayer2.core.source.MaskingMediaSource$MaskingTimeline r4 = r12.timeline
            com.appsamurai.storyly.exoplayer2.core.source.MaskingMediaPeriod r5 = r12.unpreparedMaskingMediaPeriod
            com.appsamurai.storyly.exoplayer2.core.source.MediaSource$MediaPeriodId r5 = r5.id
            java.lang.Object r5 = r5.periodUid
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r6 = r12.period
            r4.getPeriodByUid(r5, r6)
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r4 = r12.period
            long r4 = r4.getPositionInWindowUs()
            long r4 = r4 + r2
            com.appsamurai.storyly.exoplayer2.core.source.MaskingMediaSource$MaskingTimeline r2 = r12.timeline
            com.appsamurai.storyly.exoplayer2.common.Timeline$Window r3 = r12.window
            com.appsamurai.storyly.exoplayer2.common.Timeline$Window r14 = r2.getWindow(r14, r3)
            long r2 = r14.getDefaultPositionUs()
            int r14 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r14 == 0) goto L_0x0074
            r10 = r4
            goto L_0x0075
        L_0x0074:
            r10 = r0
        L_0x0075:
            com.appsamurai.storyly.exoplayer2.common.Timeline$Window r7 = r12.window
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r8 = r12.period
            r9 = 0
            r6 = r15
            android.util.Pair r14 = r6.getPeriodPositionUs(r7, r8, r9, r10)
            java.lang.Object r0 = r14.first
            java.lang.Object r14 = r14.second
            java.lang.Long r14 = (java.lang.Long) r14
            long r1 = r14.longValue()
            boolean r14 = r12.hasRealTimeline
            if (r14 == 0) goto L_0x0094
            com.appsamurai.storyly.exoplayer2.core.source.MaskingMediaSource$MaskingTimeline r13 = r12.timeline
            com.appsamurai.storyly.exoplayer2.core.source.MaskingMediaSource$MaskingTimeline r13 = r13.cloneWithUpdatedTimeline(r15)
            goto L_0x0098
        L_0x0094:
            com.appsamurai.storyly.exoplayer2.core.source.MaskingMediaSource$MaskingTimeline r13 = com.appsamurai.storyly.exoplayer2.core.source.MaskingMediaSource.MaskingTimeline.createWithRealTimeline(r15, r13, r0)
        L_0x0098:
            r12.timeline = r13
            com.appsamurai.storyly.exoplayer2.core.source.MaskingMediaPeriod r13 = r12.unpreparedMaskingMediaPeriod
            if (r13 == 0) goto L_0x00ae
            r12.setPreparePositionOverrideToUnpreparedMaskingPeriod(r1)
            com.appsamurai.storyly.exoplayer2.core.source.MediaSource$MediaPeriodId r13 = r13.id
            java.lang.Object r14 = r13.periodUid
            java.lang.Object r14 = r12.getInternalPeriodUid(r14)
            com.appsamurai.storyly.exoplayer2.core.source.MediaSource$MediaPeriodId r13 = r13.copyWithPeriodUid((java.lang.Object) r14)
            goto L_0x00af
        L_0x00ae:
            r13 = 0
        L_0x00af:
            r14 = 1
            r12.hasRealTimeline = r14
            r12.isPrepared = r14
            com.appsamurai.storyly.exoplayer2.core.source.MaskingMediaSource$MaskingTimeline r14 = r12.timeline
            r12.refreshSourceInfo(r14)
            if (r13 == 0) goto L_0x00c6
            com.appsamurai.storyly.exoplayer2.core.source.MaskingMediaPeriod r12 = r12.unpreparedMaskingMediaPeriod
            java.lang.Object r12 = com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkNotNull(r12)
            com.appsamurai.storyly.exoplayer2.core.source.MaskingMediaPeriod r12 = (com.appsamurai.storyly.exoplayer2.core.source.MaskingMediaPeriod) r12
            r12.createPeriod(r13)
        L_0x00c6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.source.MaskingMediaSource.onChildSourceInfoRefreshed(java.lang.Void, com.appsamurai.storyly.exoplayer2.core.source.MediaSource, com.appsamurai.storyly.exoplayer2.common.Timeline):void");
    }
}
