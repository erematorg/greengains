package com.appsamurai.storyly.exoplayer2.core.source;

import android.net.Uri;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.MediaItem;
import com.appsamurai.storyly.exoplayer2.common.Timeline;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;

public final class SinglePeriodTimeline extends Timeline {
    private static final MediaItem MEDIA_ITEM = new MediaItem.Builder().setMediaId("SinglePeriodTimeline").setUri(Uri.EMPTY).build();
    private static final Object UID = new Object();
    private final long elapsedRealtimeEpochOffsetMs;
    private final boolean isDynamic;
    private final boolean isSeekable;
    @Nullable
    private final MediaItem.LiveConfiguration liveConfiguration;
    @Nullable
    private final Object manifest;
    @Nullable
    private final MediaItem mediaItem;
    private final long periodDurationUs;
    private final long presentationStartTimeMs;
    private final boolean suppressPositionProjection;
    private final long windowDefaultStartPositionUs;
    private final long windowDurationUs;
    private final long windowPositionInPeriodUs;
    private final long windowStartTimeMs;

    @Deprecated
    public SinglePeriodTimeline(long j2, boolean z2, boolean z3, boolean z4, @Nullable Object obj, @Nullable Object obj2) {
        this(j2, j2, 0, 0, z2, z3, z4, obj, obj2);
    }

    public int getIndexOfPeriod(Object obj) {
        return UID.equals(obj) ? 0 : -1;
    }

    public Timeline.Period getPeriod(int i3, Timeline.Period period, boolean z2) {
        Assertions.checkIndex(i3, 0, 1);
        return period.set((Object) null, z2 ? UID : null, 0, this.periodDurationUs, -this.windowPositionInPeriodUs);
    }

    public int getPeriodCount() {
        return 1;
    }

    public Object getUidOfPeriod(int i3) {
        Assertions.checkIndex(i3, 0, 1);
        return UID;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002b, code lost:
        if (r1 > r3) goto L_0x0024;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.appsamurai.storyly.exoplayer2.common.Timeline.Window getWindow(int r25, com.appsamurai.storyly.exoplayer2.common.Timeline.Window r26, long r27) {
        /*
            r24 = this;
            r0 = r24
            r1 = 0
            r2 = 1
            r3 = r25
            com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkIndex(r3, r1, r2)
            long r1 = r0.windowDefaultStartPositionUs
            boolean r14 = r0.isDynamic
            if (r14 == 0) goto L_0x002e
            boolean r3 = r0.suppressPositionProjection
            if (r3 != 0) goto L_0x002e
            r3 = 0
            int r3 = (r27 > r3 ? 1 : (r27 == r3 ? 0 : -1))
            if (r3 == 0) goto L_0x002e
            long r3 = r0.windowDurationUs
            r5 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 != 0) goto L_0x0027
        L_0x0024:
            r16 = r5
            goto L_0x0030
        L_0x0027:
            long r1 = r1 + r27
            int r3 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r3 <= 0) goto L_0x002e
            goto L_0x0024
        L_0x002e:
            r16 = r1
        L_0x0030:
            java.lang.Object r4 = com.appsamurai.storyly.exoplayer2.common.Timeline.Window.SINGLE_WINDOW_UID
            com.appsamurai.storyly.exoplayer2.common.MediaItem r5 = r0.mediaItem
            java.lang.Object r6 = r0.manifest
            long r7 = r0.presentationStartTimeMs
            long r9 = r0.windowStartTimeMs
            long r11 = r0.elapsedRealtimeEpochOffsetMs
            boolean r13 = r0.isSeekable
            com.appsamurai.storyly.exoplayer2.common.MediaItem$LiveConfiguration r15 = r0.liveConfiguration
            long r1 = r0.windowDurationUs
            r18 = r1
            r21 = 0
            long r0 = r0.windowPositionInPeriodUs
            r22 = r0
            r20 = 0
            r3 = r26
            com.appsamurai.storyly.exoplayer2.common.Timeline$Window r0 = r3.set(r4, r5, r6, r7, r9, r11, r13, r14, r15, r16, r18, r20, r21, r22)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.source.SinglePeriodTimeline.getWindow(int, com.appsamurai.storyly.exoplayer2.common.Timeline$Window, long):com.appsamurai.storyly.exoplayer2.common.Timeline$Window");
    }

    public int getWindowCount() {
        return 1;
    }

    public SinglePeriodTimeline(long j2, boolean z2, boolean z3, boolean z4, @Nullable Object obj, MediaItem mediaItem2) {
        this(j2, j2, 0, 0, z2, z3, z4, obj, mediaItem2);
    }

    @Deprecated
    public SinglePeriodTimeline(long j2, long j3, long j4, long j5, boolean z2, boolean z3, boolean z4, @Nullable Object obj, @Nullable Object obj2) {
        this((long) C.TIME_UNSET, (long) C.TIME_UNSET, (long) C.TIME_UNSET, j2, j3, j4, j5, z2, z3, z4, obj, obj2);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SinglePeriodTimeline(long j2, long j3, long j4, long j5, boolean z2, boolean z3, boolean z4, @Nullable Object obj, MediaItem mediaItem2) {
        this(C.TIME_UNSET, C.TIME_UNSET, C.TIME_UNSET, j2, j3, j4, j5, z2, z3, false, obj, mediaItem2, z4 ? mediaItem2.liveConfiguration : null);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SinglePeriodTimeline(long r24, long r26, long r28, long r30, long r32, long r34, long r36, boolean r38, boolean r39, boolean r40, @androidx.annotation.Nullable java.lang.Object r41, @androidx.annotation.Nullable java.lang.Object r42) {
        /*
            r23 = this;
            com.appsamurai.storyly.exoplayer2.common.MediaItem r0 = MEDIA_ITEM
            com.appsamurai.storyly.exoplayer2.common.MediaItem$Builder r1 = r0.buildUpon()
            r2 = r42
            com.appsamurai.storyly.exoplayer2.common.MediaItem$Builder r1 = r1.setTag(r2)
            com.appsamurai.storyly.exoplayer2.common.MediaItem r21 = r1.build()
            if (r40 == 0) goto L_0x0017
            com.appsamurai.storyly.exoplayer2.common.MediaItem$LiveConfiguration r0 = r0.liveConfiguration
        L_0x0014:
            r22 = r0
            goto L_0x0019
        L_0x0017:
            r0 = 0
            goto L_0x0014
        L_0x0019:
            r19 = 0
            r2 = r23
            r3 = r24
            r5 = r26
            r7 = r28
            r9 = r30
            r11 = r32
            r13 = r34
            r15 = r36
            r17 = r38
            r18 = r39
            r20 = r41
            r2.<init>(r3, r5, r7, r9, r11, r13, r15, r17, r18, r19, r20, r21, r22)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.source.SinglePeriodTimeline.<init>(long, long, long, long, long, long, long, boolean, boolean, boolean, java.lang.Object, java.lang.Object):void");
    }

    @Deprecated
    public SinglePeriodTimeline(long j2, long j3, long j4, long j5, long j6, long j7, long j8, boolean z2, boolean z3, @Nullable Object obj, MediaItem mediaItem2, @Nullable MediaItem.LiveConfiguration liveConfiguration2) {
        this(j2, j3, j4, j5, j6, j7, j8, z2, z3, false, obj, mediaItem2, liveConfiguration2);
    }

    public SinglePeriodTimeline(long j2, long j3, long j4, long j5, long j6, long j7, long j8, boolean z2, boolean z3, boolean z4, @Nullable Object obj, MediaItem mediaItem2, @Nullable MediaItem.LiveConfiguration liveConfiguration2) {
        this.presentationStartTimeMs = j2;
        this.windowStartTimeMs = j3;
        this.elapsedRealtimeEpochOffsetMs = j4;
        this.periodDurationUs = j5;
        this.windowDurationUs = j6;
        this.windowPositionInPeriodUs = j7;
        this.windowDefaultStartPositionUs = j8;
        this.isSeekable = z2;
        this.isDynamic = z3;
        this.suppressPositionProjection = z4;
        this.manifest = obj;
        this.mediaItem = (MediaItem) Assertions.checkNotNull(mediaItem2);
        this.liveConfiguration = liveConfiguration2;
    }
}
