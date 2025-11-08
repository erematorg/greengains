package com.appsamurai.storyly.exoplayer2.core;

import android.os.Handler;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Timeline;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsCollector;
import com.appsamurai.storyly.exoplayer2.core.source.MediaPeriod;
import com.appsamurai.storyly.exoplayer2.core.source.MediaSource;
import com.appsamurai.storyly.exoplayer2.core.trackselection.TrackSelector;
import com.appsamurai.storyly.exoplayer2.core.trackselection.TrackSelectorResult;
import com.appsamurai.storyly.exoplayer2.core.upstream.Allocator;
import com.google.common.collect.ImmutableList;

final class MediaPeriodQueue {
    public static final long INITIAL_RENDERER_POSITION_OFFSET_US = 1000000000000L;
    private static final int MAXIMUM_BUFFER_AHEAD_PERIODS = 100;
    private final AnalyticsCollector analyticsCollector;
    private final Handler analyticsCollectorHandler;
    private int length;
    @Nullable
    private MediaPeriodHolder loading;
    private long nextWindowSequenceNumber;
    @Nullable
    private Object oldFrontPeriodUid;
    private long oldFrontPeriodWindowSequenceNumber;
    private final Timeline.Period period = new Timeline.Period();
    @Nullable
    private MediaPeriodHolder playing;
    @Nullable
    private MediaPeriodHolder reading;
    private int repeatMode;
    private boolean shuffleModeEnabled;
    private final Timeline.Window window = new Timeline.Window();

    public MediaPeriodQueue(AnalyticsCollector analyticsCollector2, Handler handler) {
        this.analyticsCollector = analyticsCollector2;
        this.analyticsCollectorHandler = handler;
    }

    private boolean areDurationsCompatible(long j2, long j3) {
        return j2 == C.TIME_UNSET || j2 == j3;
    }

    private boolean canKeepMediaPeriodHolder(MediaPeriodInfo mediaPeriodInfo, MediaPeriodInfo mediaPeriodInfo2) {
        return mediaPeriodInfo.startPositionUs == mediaPeriodInfo2.startPositionUs && mediaPeriodInfo.id.equals(mediaPeriodInfo2.id);
    }

    @Nullable
    private MediaPeriodInfo getFirstMediaPeriodInfo(PlaybackInfo playbackInfo) {
        return getMediaPeriodInfo(playbackInfo.timeline, playbackInfo.periodId, playbackInfo.requestedContentPositionUs, playbackInfo.positionUs);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00cc, code lost:
        if (r1.isServerSideInsertedAdGroup(r1.getRemovedAdGroupCount()) != false) goto L_0x00d0;
     */
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.appsamurai.storyly.exoplayer2.core.MediaPeriodInfo getFollowingMediaPeriodInfo(com.appsamurai.storyly.exoplayer2.common.Timeline r20, com.appsamurai.storyly.exoplayer2.core.MediaPeriodHolder r21, long r22) {
        /*
            r19 = this;
            r0 = r19
            r9 = r20
            r10 = r21
            com.appsamurai.storyly.exoplayer2.core.MediaPeriodInfo r11 = r10.info
            long r1 = r21.getRendererOffset()
            long r3 = r11.durationUs
            long r1 = r1 + r3
            long r7 = r1 - r22
            boolean r1 = r11.isLastInTimelinePeriod
            r13 = -1
            r14 = 1
            r15 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r17 = 0
            if (r1 == 0) goto L_0x00ef
            com.appsamurai.storyly.exoplayer2.core.source.MediaSource$MediaPeriodId r1 = r11.id
            java.lang.Object r1 = r1.periodUid
            int r2 = r9.getIndexOfPeriod(r1)
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r3 = r0.period
            com.appsamurai.storyly.exoplayer2.common.Timeline$Window r4 = r0.window
            int r1 = r0.repeatMode
            boolean r6 = r0.shuffleModeEnabled
            r5 = r1
            r1 = r20
            int r1 = r1.getNextPeriodIndex(r2, r3, r4, r5, r6)
            if (r1 != r13) goto L_0x0038
            return r17
        L_0x0038:
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r2 = r0.period
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r2 = r9.getPeriod(r1, r2, r14)
            int r4 = r2.windowIndex
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r2 = r0.period
            java.lang.Object r2 = r2.uid
            java.lang.Object r2 = com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkNotNull(r2)
            com.appsamurai.storyly.exoplayer2.core.source.MediaSource$MediaPeriodId r3 = r11.id
            long r5 = r3.windowSequenceNumber
            com.appsamurai.storyly.exoplayer2.common.Timeline$Window r3 = r0.window
            com.appsamurai.storyly.exoplayer2.common.Timeline$Window r3 = r9.getWindow(r4, r3)
            int r3 = r3.firstPeriodIndex
            if (r3 != r1) goto L_0x0099
            com.appsamurai.storyly.exoplayer2.common.Timeline$Window r2 = r0.window
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r3 = r0.period
            r5 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r12 = 0
            long r7 = java.lang.Math.max(r12, r7)
            r1 = r20
            android.util.Pair r1 = r1.getPeriodPositionUs(r2, r3, r4, r5, r7)
            if (r1 != 0) goto L_0x006e
            return r17
        L_0x006e:
            java.lang.Object r2 = r1.first
            java.lang.Object r1 = r1.second
            java.lang.Long r1 = (java.lang.Long) r1
            long r5 = r1.longValue()
            com.appsamurai.storyly.exoplayer2.core.MediaPeriodHolder r1 = r21.getNext()
            if (r1 == 0) goto L_0x0091
            java.lang.Object r3 = r1.uid
            boolean r3 = r3.equals(r2)
            if (r3 == 0) goto L_0x0091
            com.appsamurai.storyly.exoplayer2.core.MediaPeriodInfo r1 = r1.info
            com.appsamurai.storyly.exoplayer2.core.source.MediaSource$MediaPeriodId r1 = r1.id
            long r3 = r1.windowSequenceNumber
        L_0x008c:
            r12 = r5
            r17 = r15
            r5 = r3
            goto L_0x009d
        L_0x0091:
            long r3 = r0.nextWindowSequenceNumber
            r7 = 1
            long r7 = r7 + r3
            r0.nextWindowSequenceNumber = r7
            goto L_0x008c
        L_0x0099:
            r12 = 0
            r17 = r12
        L_0x009d:
            com.appsamurai.storyly.exoplayer2.common.Timeline$Window r7 = r0.window
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r8 = r0.period
            r1 = r20
            r3 = r12
            com.appsamurai.storyly.exoplayer2.core.source.MediaSource$MediaPeriodId r2 = resolveMediaPeriodIdForAds(r1, r2, r3, r5, r7, r8)
            int r1 = (r17 > r15 ? 1 : (r17 == r15 ? 0 : -1))
            if (r1 == 0) goto L_0x00e4
            long r3 = r11.requestedContentPositionUs
            int r1 = (r3 > r15 ? 1 : (r3 == r15 ? 0 : -1))
            if (r1 == 0) goto L_0x00e4
            com.appsamurai.storyly.exoplayer2.core.source.MediaSource$MediaPeriodId r1 = r11.id
            java.lang.Object r1 = r1.periodUid
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r3 = r0.period
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r1 = r9.getPeriodByUid(r1, r3)
            int r1 = r1.getAdGroupCount()
            if (r1 <= 0) goto L_0x00cf
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r1 = r0.period
            int r3 = r1.getRemovedAdGroupCount()
            boolean r1 = r1.isServerSideInsertedAdGroup(r3)
            if (r1 == 0) goto L_0x00cf
            goto L_0x00d0
        L_0x00cf:
            r14 = 0
        L_0x00d0:
            boolean r1 = r2.isAd()
            if (r1 == 0) goto L_0x00dc
            if (r14 == 0) goto L_0x00dc
            long r3 = r11.requestedContentPositionUs
            r5 = r12
            goto L_0x00e6
        L_0x00dc:
            if (r14 == 0) goto L_0x00e4
            long r3 = r11.requestedContentPositionUs
            r5 = r3
        L_0x00e1:
            r3 = r17
            goto L_0x00e6
        L_0x00e4:
            r5 = r12
            goto L_0x00e1
        L_0x00e6:
            r0 = r19
            r1 = r20
            com.appsamurai.storyly.exoplayer2.core.MediaPeriodInfo r0 = r0.getMediaPeriodInfo(r1, r2, r3, r5)
            return r0
        L_0x00ef:
            r1 = r13
            r12 = 0
            com.appsamurai.storyly.exoplayer2.core.source.MediaSource$MediaPeriodId r10 = r11.id
            java.lang.Object r2 = r10.periodUid
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r3 = r0.period
            r9.getPeriodByUid(r2, r3)
            boolean r2 = r10.isAd()
            if (r2 == 0) goto L_0x0169
            int r3 = r10.adGroupIndex
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r2 = r0.period
            int r2 = r2.getAdCountInAdGroup(r3)
            if (r2 != r1) goto L_0x010c
            return r17
        L_0x010c:
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r1 = r0.period
            int r4 = r10.adIndexInAdGroup
            int r4 = r1.getNextAdIndexToPlay(r3, r4)
            if (r4 >= r2) goto L_0x0125
            java.lang.Object r2 = r10.periodUid
            long r5 = r11.requestedContentPositionUs
            long r7 = r10.windowSequenceNumber
            r0 = r19
            r1 = r20
            com.appsamurai.storyly.exoplayer2.core.MediaPeriodInfo r0 = r0.getMediaPeriodInfoForAd(r1, r2, r3, r4, r5, r7)
            return r0
        L_0x0125:
            long r1 = r11.requestedContentPositionUs
            int r3 = (r1 > r15 ? 1 : (r1 == r15 ? 0 : -1))
            if (r3 != 0) goto L_0x014b
            com.appsamurai.storyly.exoplayer2.common.Timeline$Window r2 = r0.window
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r3 = r0.period
            int r4 = r3.windowIndex
            r5 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            long r7 = java.lang.Math.max(r12, r7)
            r1 = r20
            android.util.Pair r1 = r1.getPeriodPositionUs(r2, r3, r4, r5, r7)
            if (r1 != 0) goto L_0x0143
            return r17
        L_0x0143:
            java.lang.Object r1 = r1.second
            java.lang.Long r1 = (java.lang.Long) r1
            long r1 = r1.longValue()
        L_0x014b:
            java.lang.Object r3 = r10.periodUid
            int r4 = r10.adGroupIndex
            long r3 = r0.getMinStartPositionAfterAdGroupUs(r9, r3, r4)
            java.lang.Object r5 = r10.periodUid
            long r3 = java.lang.Math.max(r3, r1)
            long r6 = r11.requestedContentPositionUs
            long r10 = r10.windowSequenceNumber
            r0 = r19
            r1 = r20
            r2 = r5
            r5 = r6
            r7 = r10
            com.appsamurai.storyly.exoplayer2.core.MediaPeriodInfo r0 = r0.getMediaPeriodInfoForContent(r1, r2, r3, r5, r7)
            return r0
        L_0x0169:
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r1 = r0.period
            int r2 = r10.nextAdGroupIndex
            int r4 = r1.getFirstAdIndexToPlay(r2)
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r1 = r0.period
            int r2 = r10.nextAdGroupIndex
            boolean r1 = r1.isServerSideInsertedAdGroup(r2)
            if (r1 == 0) goto L_0x0188
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r1 = r0.period
            int r2 = r10.nextAdGroupIndex
            int r1 = r1.getAdState(r2, r4)
            r2 = 3
            if (r1 != r2) goto L_0x0188
            r12 = r14
            goto L_0x0189
        L_0x0188:
            r12 = 0
        L_0x0189:
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r1 = r0.period
            int r2 = r10.nextAdGroupIndex
            int r1 = r1.getAdCountInAdGroup(r2)
            if (r4 == r1) goto L_0x01a7
            if (r12 == 0) goto L_0x0196
            goto L_0x01a7
        L_0x0196:
            java.lang.Object r2 = r10.periodUid
            int r3 = r10.nextAdGroupIndex
            long r5 = r11.durationUs
            long r7 = r10.windowSequenceNumber
            r0 = r19
            r1 = r20
            com.appsamurai.storyly.exoplayer2.core.MediaPeriodInfo r0 = r0.getMediaPeriodInfoForAd(r1, r2, r3, r4, r5, r7)
            return r0
        L_0x01a7:
            java.lang.Object r1 = r10.periodUid
            int r2 = r10.nextAdGroupIndex
            long r3 = r0.getMinStartPositionAfterAdGroupUs(r9, r1, r2)
            java.lang.Object r2 = r10.periodUid
            long r5 = r11.durationUs
            long r7 = r10.windowSequenceNumber
            r0 = r19
            r1 = r20
            com.appsamurai.storyly.exoplayer2.core.MediaPeriodInfo r0 = r0.getMediaPeriodInfoForContent(r1, r2, r3, r5, r7)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.MediaPeriodQueue.getFollowingMediaPeriodInfo(com.appsamurai.storyly.exoplayer2.common.Timeline, com.appsamurai.storyly.exoplayer2.core.MediaPeriodHolder, long):com.appsamurai.storyly.exoplayer2.core.MediaPeriodInfo");
    }

    @Nullable
    private MediaPeriodInfo getMediaPeriodInfo(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId, long j2, long j3) {
        MediaSource.MediaPeriodId mediaPeriodId2 = mediaPeriodId;
        Timeline timeline2 = timeline;
        timeline.getPeriodByUid(mediaPeriodId2.periodUid, this.period);
        if (mediaPeriodId.isAd()) {
            return getMediaPeriodInfoForAd(timeline, mediaPeriodId2.periodUid, mediaPeriodId2.adGroupIndex, mediaPeriodId2.adIndexInAdGroup, j2, mediaPeriodId2.windowSequenceNumber);
        }
        return getMediaPeriodInfoForContent(timeline, mediaPeriodId2.periodUid, j3, j2, mediaPeriodId2.windowSequenceNumber);
    }

    private MediaPeriodInfo getMediaPeriodInfoForAd(Timeline timeline, Object obj, int i3, int i4, long j2, long j3) {
        int i5 = i4;
        MediaSource.MediaPeriodId mediaPeriodId = new MediaSource.MediaPeriodId(obj, i3, i5, j3);
        long adDurationUs = timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period).getAdDurationUs(mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup);
        long adResumePositionUs = i5 == this.period.getFirstAdIndexToPlay(i3) ? this.period.getAdResumePositionUs() : 0;
        return new MediaPeriodInfo(mediaPeriodId, (adDurationUs == C.TIME_UNSET || adResumePositionUs < adDurationUs) ? adResumePositionUs : Math.max(0, adDurationUs - 1), j2, C.TIME_UNSET, adDurationUs, this.period.isServerSideInsertedAdGroup(mediaPeriodId.adGroupIndex), false, false, false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00a5 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00b6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.appsamurai.storyly.exoplayer2.core.MediaPeriodInfo getMediaPeriodInfoForContent(com.appsamurai.storyly.exoplayer2.common.Timeline r25, java.lang.Object r26, long r27, long r29, long r31) {
        /*
            r24 = this;
            r0 = r24
            r1 = r25
            r2 = r26
            r3 = r27
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r5 = r0.period
            r1.getPeriodByUid(r2, r5)
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r5 = r0.period
            int r5 = r5.getAdGroupIndexAfterPositionUs(r3)
            r6 = 1
            r7 = 0
            r8 = -1
            if (r5 != r8) goto L_0x002e
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r9 = r0.period
            int r9 = r9.getAdGroupCount()
            if (r9 <= 0) goto L_0x004d
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r9 = r0.period
            int r10 = r9.getRemovedAdGroupCount()
            boolean r9 = r9.isServerSideInsertedAdGroup(r10)
            if (r9 == 0) goto L_0x004d
            r9 = r6
            goto L_0x004e
        L_0x002e:
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r9 = r0.period
            boolean r9 = r9.isServerSideInsertedAdGroup(r5)
            if (r9 == 0) goto L_0x004d
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r9 = r0.period
            long r9 = r9.getAdGroupTimeUs(r5)
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r11 = r0.period
            long r12 = r11.durationUs
            int r9 = (r9 > r12 ? 1 : (r9 == r12 ? 0 : -1))
            if (r9 != 0) goto L_0x004d
            boolean r9 = r11.hasPlayedAdGroup(r5)
            if (r9 == 0) goto L_0x004d
            r9 = r6
            r5 = r8
            goto L_0x004e
        L_0x004d:
            r9 = r7
        L_0x004e:
            com.appsamurai.storyly.exoplayer2.core.source.MediaSource$MediaPeriodId r11 = new com.appsamurai.storyly.exoplayer2.core.source.MediaSource$MediaPeriodId
            r12 = r31
            r11.<init>(r2, r12, r5)
            boolean r2 = r0.isLastInPeriod(r11)
            boolean r22 = r0.isLastInWindow(r1, r11)
            boolean r23 = r0.isLastInTimeline(r1, r11, r2)
            if (r5 == r8) goto L_0x006e
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r1 = r0.period
            boolean r1 = r1.isServerSideInsertedAdGroup(r5)
            if (r1 == 0) goto L_0x006e
            r20 = r6
            goto L_0x0070
        L_0x006e:
            r20 = r7
        L_0x0070:
            r12 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r5 == r8) goto L_0x0080
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r1 = r0.period
            long r14 = r1.getAdGroupTimeUs(r5)
        L_0x007d:
            r16 = r14
            goto L_0x0089
        L_0x0080:
            if (r9 == 0) goto L_0x0087
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r1 = r0.period
            long r14 = r1.durationUs
            goto L_0x007d
        L_0x0087:
            r16 = r12
        L_0x0089:
            int r1 = (r16 > r12 ? 1 : (r16 == r12 ? 0 : -1))
            if (r1 == 0) goto L_0x0097
            r14 = -9223372036854775808
            int r1 = (r16 > r14 ? 1 : (r16 == r14 ? 0 : -1))
            if (r1 != 0) goto L_0x0094
            goto L_0x0097
        L_0x0094:
            r18 = r16
            goto L_0x009d
        L_0x0097:
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r0 = r0.period
            long r0 = r0.durationUs
            r18 = r0
        L_0x009d:
            int r0 = (r18 > r12 ? 1 : (r18 == r12 ? 0 : -1))
            if (r0 == 0) goto L_0x00b6
            int r0 = (r3 > r18 ? 1 : (r3 == r18 ? 0 : -1))
            if (r0 < 0) goto L_0x00b6
            if (r23 != 0) goto L_0x00ab
            if (r9 != 0) goto L_0x00aa
            goto L_0x00ab
        L_0x00aa:
            r6 = r7
        L_0x00ab:
            long r0 = (long) r6
            long r0 = r18 - r0
            r3 = 0
            long r0 = java.lang.Math.max(r3, r0)
            r12 = r0
            goto L_0x00b7
        L_0x00b6:
            r12 = r3
        L_0x00b7:
            com.appsamurai.storyly.exoplayer2.core.MediaPeriodInfo r0 = new com.appsamurai.storyly.exoplayer2.core.MediaPeriodInfo
            r10 = r0
            r14 = r29
            r21 = r2
            r10.<init>(r11, r12, r14, r16, r18, r20, r21, r22, r23)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.MediaPeriodQueue.getMediaPeriodInfoForContent(com.appsamurai.storyly.exoplayer2.common.Timeline, java.lang.Object, long, long, long):com.appsamurai.storyly.exoplayer2.core.MediaPeriodInfo");
    }

    private long getMinStartPositionAfterAdGroupUs(Timeline timeline, Object obj, int i3) {
        timeline.getPeriodByUid(obj, this.period);
        long adGroupTimeUs = this.period.getAdGroupTimeUs(i3);
        return adGroupTimeUs == Long.MIN_VALUE ? this.period.durationUs : this.period.getContentResumeOffsetUs(i3) + adGroupTimeUs;
    }

    private boolean isLastInPeriod(MediaSource.MediaPeriodId mediaPeriodId) {
        return !mediaPeriodId.isAd() && mediaPeriodId.nextAdGroupIndex == -1;
    }

    private boolean isLastInTimeline(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId, boolean z2) {
        int indexOfPeriod = timeline.getIndexOfPeriod(mediaPeriodId.periodUid);
        if (!timeline.getWindow(timeline.getPeriod(indexOfPeriod, this.period).windowIndex, this.window).isDynamic) {
            return timeline.isLastPeriod(indexOfPeriod, this.period, this.window, this.repeatMode, this.shuffleModeEnabled) && z2;
        }
    }

    private boolean isLastInWindow(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId) {
        if (!isLastInPeriod(mediaPeriodId)) {
            return false;
        }
        return timeline.getWindow(timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period).windowIndex, this.window).lastPeriodIndex == timeline.getIndexOfPeriod(mediaPeriodId.periodUid);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$notifyQueueUpdate$0(ImmutableList.Builder builder, MediaSource.MediaPeriodId mediaPeriodId) {
        this.analyticsCollector.updateMediaPeriodQueueInfo(builder.build(), mediaPeriodId);
    }

    private void notifyQueueUpdate() {
        ImmutableList.Builder builder = ImmutableList.builder();
        for (MediaPeriodHolder mediaPeriodHolder = this.playing; mediaPeriodHolder != null; mediaPeriodHolder = mediaPeriodHolder.getNext()) {
            builder.add((Object) mediaPeriodHolder.info.id);
        }
        MediaPeriodHolder mediaPeriodHolder2 = this.reading;
        this.analyticsCollectorHandler.post(new y(this, builder, mediaPeriodHolder2 == null ? null : mediaPeriodHolder2.info.id));
    }

    private long resolvePeriodIndexToWindowSequenceNumber(Timeline timeline, Object obj) {
        int indexOfPeriod;
        int i3 = timeline.getPeriodByUid(obj, this.period).windowIndex;
        Object obj2 = this.oldFrontPeriodUid;
        if (obj2 != null && (indexOfPeriod = timeline.getIndexOfPeriod(obj2)) != -1 && timeline.getPeriod(indexOfPeriod, this.period).windowIndex == i3) {
            return this.oldFrontPeriodWindowSequenceNumber;
        }
        for (MediaPeriodHolder mediaPeriodHolder = this.playing; mediaPeriodHolder != null; mediaPeriodHolder = mediaPeriodHolder.getNext()) {
            if (mediaPeriodHolder.uid.equals(obj)) {
                return mediaPeriodHolder.info.id.windowSequenceNumber;
            }
        }
        for (MediaPeriodHolder mediaPeriodHolder2 = this.playing; mediaPeriodHolder2 != null; mediaPeriodHolder2 = mediaPeriodHolder2.getNext()) {
            int indexOfPeriod2 = timeline.getIndexOfPeriod(mediaPeriodHolder2.uid);
            if (indexOfPeriod2 != -1 && timeline.getPeriod(indexOfPeriod2, this.period).windowIndex == i3) {
                return mediaPeriodHolder2.info.id.windowSequenceNumber;
            }
        }
        long j2 = this.nextWindowSequenceNumber;
        this.nextWindowSequenceNumber = 1 + j2;
        if (this.playing == null) {
            this.oldFrontPeriodUid = obj;
            this.oldFrontPeriodWindowSequenceNumber = j2;
        }
        return j2;
    }

    private boolean updateForPlaybackModeChange(Timeline timeline) {
        MediaPeriodHolder mediaPeriodHolder = this.playing;
        if (mediaPeriodHolder == null) {
            return true;
        }
        int indexOfPeriod = timeline.getIndexOfPeriod(mediaPeriodHolder.uid);
        while (true) {
            indexOfPeriod = timeline.getNextPeriodIndex(indexOfPeriod, this.period, this.window, this.repeatMode, this.shuffleModeEnabled);
            while (mediaPeriodHolder.getNext() != null && !mediaPeriodHolder.info.isLastInTimelinePeriod) {
                mediaPeriodHolder = mediaPeriodHolder.getNext();
            }
            MediaPeriodHolder next = mediaPeriodHolder.getNext();
            if (indexOfPeriod == -1 || next == null || timeline.getIndexOfPeriod(next.uid) != indexOfPeriod) {
                boolean removeAfter = removeAfter(mediaPeriodHolder);
                mediaPeriodHolder.info = getUpdatedMediaPeriodInfo(timeline, mediaPeriodHolder.info);
            } else {
                mediaPeriodHolder = next;
            }
        }
        boolean removeAfter2 = removeAfter(mediaPeriodHolder);
        mediaPeriodHolder.info = getUpdatedMediaPeriodInfo(timeline, mediaPeriodHolder.info);
        return !removeAfter2;
    }

    @Nullable
    public MediaPeriodHolder advancePlayingPeriod() {
        MediaPeriodHolder mediaPeriodHolder = this.playing;
        if (mediaPeriodHolder == null) {
            return null;
        }
        if (mediaPeriodHolder == this.reading) {
            this.reading = mediaPeriodHolder.getNext();
        }
        this.playing.release();
        int i3 = this.length - 1;
        this.length = i3;
        if (i3 == 0) {
            this.loading = null;
            MediaPeriodHolder mediaPeriodHolder2 = this.playing;
            this.oldFrontPeriodUid = mediaPeriodHolder2.uid;
            this.oldFrontPeriodWindowSequenceNumber = mediaPeriodHolder2.info.id.windowSequenceNumber;
        }
        this.playing = this.playing.getNext();
        notifyQueueUpdate();
        return this.playing;
    }

    public MediaPeriodHolder advanceReadingPeriod() {
        MediaPeriodHolder mediaPeriodHolder = this.reading;
        Assertions.checkState((mediaPeriodHolder == null || mediaPeriodHolder.getNext() == null) ? false : true);
        this.reading = this.reading.getNext();
        notifyQueueUpdate();
        return this.reading;
    }

    public void clear() {
        if (this.length != 0) {
            MediaPeriodHolder mediaPeriodHolder = (MediaPeriodHolder) Assertions.checkStateNotNull(this.playing);
            this.oldFrontPeriodUid = mediaPeriodHolder.uid;
            this.oldFrontPeriodWindowSequenceNumber = mediaPeriodHolder.info.id.windowSequenceNumber;
            while (mediaPeriodHolder != null) {
                mediaPeriodHolder.release();
                mediaPeriodHolder = mediaPeriodHolder.getNext();
            }
            this.playing = null;
            this.loading = null;
            this.reading = null;
            this.length = 0;
            notifyQueueUpdate();
        }
    }

    public MediaPeriodHolder enqueueNextMediaPeriodHolder(RendererCapabilities[] rendererCapabilitiesArr, TrackSelector trackSelector, Allocator allocator, MediaSourceList mediaSourceList, MediaPeriodInfo mediaPeriodInfo, TrackSelectorResult trackSelectorResult) {
        long rendererOffset;
        MediaPeriodHolder mediaPeriodHolder = this.loading;
        if (mediaPeriodHolder == null) {
            rendererOffset = INITIAL_RENDERER_POSITION_OFFSET_US;
            MediaPeriodInfo mediaPeriodInfo2 = mediaPeriodInfo;
        } else {
            rendererOffset = (mediaPeriodHolder.getRendererOffset() + this.loading.info.durationUs) - mediaPeriodInfo.startPositionUs;
        }
        MediaPeriodHolder mediaPeriodHolder2 = new MediaPeriodHolder(rendererCapabilitiesArr, rendererOffset, trackSelector, allocator, mediaSourceList, mediaPeriodInfo, trackSelectorResult);
        MediaPeriodHolder mediaPeriodHolder3 = this.loading;
        if (mediaPeriodHolder3 != null) {
            mediaPeriodHolder3.setNext(mediaPeriodHolder2);
        } else {
            this.playing = mediaPeriodHolder2;
            this.reading = mediaPeriodHolder2;
        }
        this.oldFrontPeriodUid = null;
        this.loading = mediaPeriodHolder2;
        this.length++;
        notifyQueueUpdate();
        return mediaPeriodHolder2;
    }

    @Nullable
    public MediaPeriodHolder getLoadingPeriod() {
        return this.loading;
    }

    @Nullable
    public MediaPeriodInfo getNextMediaPeriodInfo(long j2, PlaybackInfo playbackInfo) {
        MediaPeriodHolder mediaPeriodHolder = this.loading;
        return mediaPeriodHolder == null ? getFirstMediaPeriodInfo(playbackInfo) : getFollowingMediaPeriodInfo(playbackInfo.timeline, mediaPeriodHolder, j2);
    }

    @Nullable
    public MediaPeriodHolder getPlayingPeriod() {
        return this.playing;
    }

    @Nullable
    public MediaPeriodHolder getReadingPeriod() {
        return this.reading;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0029, code lost:
        r1 = r3.nextAdGroupIndex;
     */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x006c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.appsamurai.storyly.exoplayer2.core.MediaPeriodInfo getUpdatedMediaPeriodInfo(com.appsamurai.storyly.exoplayer2.common.Timeline r19, com.appsamurai.storyly.exoplayer2.core.MediaPeriodInfo r20) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = r20
            com.appsamurai.storyly.exoplayer2.core.source.MediaSource$MediaPeriodId r3 = r2.id
            boolean r11 = r0.isLastInPeriod(r3)
            boolean r12 = r0.isLastInWindow(r1, r3)
            boolean r13 = r0.isLastInTimeline(r1, r3, r11)
            com.appsamurai.storyly.exoplayer2.core.source.MediaSource$MediaPeriodId r4 = r2.id
            java.lang.Object r4 = r4.periodUid
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r5 = r0.period
            r1.getPeriodByUid(r4, r5)
            boolean r1 = r3.isAd()
            r4 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r6 = -1
            if (r1 != 0) goto L_0x0035
            int r1 = r3.nextAdGroupIndex
            if (r1 != r6) goto L_0x002e
            goto L_0x0035
        L_0x002e:
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r7 = r0.period
            long r7 = r7.getAdGroupTimeUs(r1)
            goto L_0x0036
        L_0x0035:
            r7 = r4
        L_0x0036:
            boolean r1 = r3.isAd()
            if (r1 == 0) goto L_0x0048
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r1 = r0.period
            int r4 = r3.adGroupIndex
            int r5 = r3.adIndexInAdGroup
            long r4 = r1.getAdDurationUs(r4, r5)
        L_0x0046:
            r9 = r4
            goto L_0x005c
        L_0x0048:
            int r1 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r1 == 0) goto L_0x0055
            r4 = -9223372036854775808
            int r1 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r1 != 0) goto L_0x0053
            goto L_0x0055
        L_0x0053:
            r9 = r7
            goto L_0x005c
        L_0x0055:
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r1 = r0.period
            long r4 = r1.getDurationUs()
            goto L_0x0046
        L_0x005c:
            boolean r1 = r3.isAd()
            if (r1 == 0) goto L_0x006c
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r0 = r0.period
            int r1 = r3.adGroupIndex
            boolean r0 = r0.isServerSideInsertedAdGroup(r1)
        L_0x006a:
            r14 = r0
            goto L_0x007c
        L_0x006c:
            int r1 = r3.nextAdGroupIndex
            if (r1 == r6) goto L_0x007a
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r0 = r0.period
            boolean r0 = r0.isServerSideInsertedAdGroup(r1)
            if (r0 == 0) goto L_0x007a
            r0 = 1
            goto L_0x006a
        L_0x007a:
            r0 = 0
            goto L_0x006a
        L_0x007c:
            com.appsamurai.storyly.exoplayer2.core.MediaPeriodInfo r15 = new com.appsamurai.storyly.exoplayer2.core.MediaPeriodInfo
            long r4 = r2.startPositionUs
            long r1 = r2.requestedContentPositionUs
            r0 = r15
            r16 = r1
            r1 = r3
            r2 = r4
            r4 = r16
            r6 = r7
            r8 = r9
            r10 = r14
            r0.<init>(r1, r2, r4, r6, r8, r10, r11, r12, r13)
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.MediaPeriodQueue.getUpdatedMediaPeriodInfo(com.appsamurai.storyly.exoplayer2.common.Timeline, com.appsamurai.storyly.exoplayer2.core.MediaPeriodInfo):com.appsamurai.storyly.exoplayer2.core.MediaPeriodInfo");
    }

    public boolean isLoading(MediaPeriod mediaPeriod) {
        MediaPeriodHolder mediaPeriodHolder = this.loading;
        return mediaPeriodHolder != null && mediaPeriodHolder.mediaPeriod == mediaPeriod;
    }

    public void reevaluateBuffer(long j2) {
        MediaPeriodHolder mediaPeriodHolder = this.loading;
        if (mediaPeriodHolder != null) {
            mediaPeriodHolder.reevaluateBuffer(j2);
        }
    }

    public boolean removeAfter(MediaPeriodHolder mediaPeriodHolder) {
        boolean z2 = false;
        Assertions.checkState(mediaPeriodHolder != null);
        if (mediaPeriodHolder.equals(this.loading)) {
            return false;
        }
        this.loading = mediaPeriodHolder;
        while (mediaPeriodHolder.getNext() != null) {
            mediaPeriodHolder = mediaPeriodHolder.getNext();
            if (mediaPeriodHolder == this.reading) {
                this.reading = this.playing;
                z2 = true;
            }
            mediaPeriodHolder.release();
            this.length--;
        }
        this.loading.setNext((MediaPeriodHolder) null);
        notifyQueueUpdate();
        return z2;
    }

    public MediaSource.MediaPeriodId resolveMediaPeriodIdForAds(Timeline timeline, Object obj, long j2) {
        return resolveMediaPeriodIdForAds(timeline, obj, j2, resolvePeriodIndexToWindowSequenceNumber(timeline, obj), this.window, this.period);
    }

    public MediaSource.MediaPeriodId resolveMediaPeriodIdForAdsAfterPeriodPositionChange(Timeline timeline, Object obj, long j2) {
        long resolvePeriodIndexToWindowSequenceNumber = resolvePeriodIndexToWindowSequenceNumber(timeline, obj);
        timeline.getPeriodByUid(obj, this.period);
        timeline.getWindow(this.period.windowIndex, this.window);
        boolean z2 = false;
        for (int indexOfPeriod = timeline.getIndexOfPeriod(obj); indexOfPeriod >= this.window.firstPeriodIndex; indexOfPeriod--) {
            boolean z3 = true;
            timeline.getPeriod(indexOfPeriod, this.period, true);
            if (this.period.getAdGroupCount() <= 0) {
                z3 = false;
            }
            z2 |= z3;
            Timeline.Period period2 = this.period;
            if (period2.getAdGroupIndexForPositionUs(period2.durationUs) != -1) {
                obj = Assertions.checkNotNull(this.period.uid);
            }
            if (z2 && (!z3 || this.period.durationUs != 0)) {
                break;
            }
        }
        return resolveMediaPeriodIdForAds(timeline, obj, j2, resolvePeriodIndexToWindowSequenceNumber, this.window, this.period);
    }

    public boolean shouldLoadNextMediaPeriod() {
        MediaPeriodHolder mediaPeriodHolder = this.loading;
        return mediaPeriodHolder == null || (!mediaPeriodHolder.info.isFinal && mediaPeriodHolder.isFullyBuffered() && this.loading.info.durationUs != C.TIME_UNSET && this.length < 100);
    }

    public boolean updateQueuedPeriods(Timeline timeline, long j2, long j3) {
        MediaPeriodInfo mediaPeriodInfo;
        MediaPeriodHolder mediaPeriodHolder = this.playing;
        MediaPeriodHolder mediaPeriodHolder2 = null;
        while (mediaPeriodHolder != null) {
            MediaPeriodInfo mediaPeriodInfo2 = mediaPeriodHolder.info;
            if (mediaPeriodHolder2 == null) {
                mediaPeriodInfo = getUpdatedMediaPeriodInfo(timeline, mediaPeriodInfo2);
            } else {
                MediaPeriodInfo followingMediaPeriodInfo = getFollowingMediaPeriodInfo(timeline, mediaPeriodHolder2, j2);
                if (followingMediaPeriodInfo == null) {
                    return !removeAfter(mediaPeriodHolder2);
                }
                if (!canKeepMediaPeriodHolder(mediaPeriodInfo2, followingMediaPeriodInfo)) {
                    return !removeAfter(mediaPeriodHolder2);
                }
                mediaPeriodInfo = followingMediaPeriodInfo;
            }
            mediaPeriodHolder.info = mediaPeriodInfo.copyWithRequestedContentPositionUs(mediaPeriodInfo2.requestedContentPositionUs);
            if (!areDurationsCompatible(mediaPeriodInfo2.durationUs, mediaPeriodInfo.durationUs)) {
                mediaPeriodHolder.updateClipping();
                long j4 = mediaPeriodInfo.durationUs;
                return !removeAfter(mediaPeriodHolder) && !(mediaPeriodHolder == this.reading && !mediaPeriodHolder.info.isFollowedByTransitionToSameStream && ((j3 > Long.MIN_VALUE ? 1 : (j3 == Long.MIN_VALUE ? 0 : -1)) == 0 || (j3 > ((j4 > C.TIME_UNSET ? 1 : (j4 == C.TIME_UNSET ? 0 : -1)) == 0 ? Long.MAX_VALUE : mediaPeriodHolder.toRendererTime(j4)) ? 1 : (j3 == ((j4 > C.TIME_UNSET ? 1 : (j4 == C.TIME_UNSET ? 0 : -1)) == 0 ? Long.MAX_VALUE : mediaPeriodHolder.toRendererTime(j4)) ? 0 : -1)) >= 0));
            }
            mediaPeriodHolder2 = mediaPeriodHolder;
            mediaPeriodHolder = mediaPeriodHolder.getNext();
        }
        return true;
    }

    public boolean updateRepeatMode(Timeline timeline, int i3) {
        this.repeatMode = i3;
        return updateForPlaybackModeChange(timeline);
    }

    public boolean updateShuffleModeEnabled(Timeline timeline, boolean z2) {
        this.shuffleModeEnabled = z2;
        return updateForPlaybackModeChange(timeline);
    }

    private static MediaSource.MediaPeriodId resolveMediaPeriodIdForAds(Timeline timeline, Object obj, long j2, long j3, Timeline.Window window2, Timeline.Period period2) {
        timeline.getPeriodByUid(obj, period2);
        timeline.getWindow(period2.windowIndex, window2);
        int indexOfPeriod = timeline.getIndexOfPeriod(obj);
        Object obj2 = obj;
        while (period2.durationUs == 0 && period2.getAdGroupCount() > 0 && period2.isServerSideInsertedAdGroup(period2.getRemovedAdGroupCount()) && period2.getAdGroupIndexForPositionUs(0) == -1) {
            int i3 = indexOfPeriod + 1;
            if (indexOfPeriod >= window2.lastPeriodIndex) {
                break;
            }
            timeline.getPeriod(i3, period2, true);
            obj2 = Assertions.checkNotNull(period2.uid);
            indexOfPeriod = i3;
        }
        timeline.getPeriodByUid(obj2, period2);
        int adGroupIndexForPositionUs = period2.getAdGroupIndexForPositionUs(j2);
        if (adGroupIndexForPositionUs == -1) {
            return new MediaSource.MediaPeriodId(obj2, j3, period2.getAdGroupIndexAfterPositionUs(j2));
        }
        return new MediaSource.MediaPeriodId(obj2, adGroupIndexForPositionUs, period2.getFirstAdIndexToPlay(adGroupIndexForPositionUs), j3);
    }
}
