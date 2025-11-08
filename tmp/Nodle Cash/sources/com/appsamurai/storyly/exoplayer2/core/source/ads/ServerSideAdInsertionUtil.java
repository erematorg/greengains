package com.appsamurai.storyly.exoplayer2.core.source.ads;

import androidx.annotation.CheckResult;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Player;
import com.appsamurai.storyly.exoplayer2.common.Timeline;
import com.appsamurai.storyly.exoplayer2.common.source.MediaPeriodId;
import com.appsamurai.storyly.exoplayer2.common.source.ads.AdPlaybackState;
import com.appsamurai.storyly.exoplayer2.common.util.Util;

public final class ServerSideAdInsertionUtil {
    private ServerSideAdInsertionUtil() {
    }

    @CheckResult
    public static AdPlaybackState addAdGroupToAdPlaybackState(AdPlaybackState adPlaybackState, long j2, long j3, long... jArr) {
        long mediaPeriodPositionUsForContent = getMediaPeriodPositionUsForContent(j2, -1, adPlaybackState);
        int i3 = adPlaybackState.removedAdGroupCount;
        while (i3 < adPlaybackState.adGroupCount && adPlaybackState.getAdGroup(i3).timeUs != Long.MIN_VALUE && adPlaybackState.getAdGroup(i3).timeUs <= mediaPeriodPositionUsForContent) {
            i3++;
        }
        int i4 = 0;
        AdPlaybackState withContentResumeOffsetUs = adPlaybackState.withNewAdGroup(i3, mediaPeriodPositionUsForContent).withIsServerSideInserted(i3, true).withAdCount(i3, jArr.length).withAdDurationsUs(i3, jArr).withContentResumeOffsetUs(i3, j3);
        while (i4 < jArr.length && jArr[i4] == 0) {
            withContentResumeOffsetUs = withContentResumeOffsetUs.withSkippedAd(i3, i4);
            i4++;
        }
        return correctFollowingAdGroupTimes(withContentResumeOffsetUs, i3, Util.sum(jArr), j3);
    }

    private static AdPlaybackState correctFollowingAdGroupTimes(AdPlaybackState adPlaybackState, int i3, long j2, long j3) {
        long j4 = (-j2) + j3;
        while (true) {
            i3++;
            if (i3 >= adPlaybackState.adGroupCount) {
                return adPlaybackState;
            }
            long j5 = adPlaybackState.getAdGroup(i3).timeUs;
            if (j5 != Long.MIN_VALUE) {
                adPlaybackState = adPlaybackState.withAdGroupTimeUs(i3, j5 + j4);
            }
        }
    }

    public static int getAdCountInGroup(AdPlaybackState adPlaybackState, int i3) {
        int i4 = adPlaybackState.getAdGroup(i3).count;
        if (i4 == -1) {
            return 0;
        }
        return i4;
    }

    public static long getMediaPeriodPositionUs(long j2, MediaPeriodId mediaPeriodId, AdPlaybackState adPlaybackState) {
        return mediaPeriodId.isAd() ? getMediaPeriodPositionUsForAd(j2, mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup, adPlaybackState) : getMediaPeriodPositionUsForContent(j2, mediaPeriodId.nextAdGroupIndex, adPlaybackState);
    }

    public static long getMediaPeriodPositionUsForAd(long j2, int i3, int i4, AdPlaybackState adPlaybackState) {
        int i5;
        AdPlaybackState.AdGroup adGroup = adPlaybackState.getAdGroup(i3);
        long j3 = j2 - adGroup.timeUs;
        int i6 = adPlaybackState.removedAdGroupCount;
        while (true) {
            i5 = 0;
            if (i6 >= i3) {
                break;
            }
            AdPlaybackState.AdGroup adGroup2 = adPlaybackState.getAdGroup(i6);
            while (i5 < getAdCountInGroup(adPlaybackState, i6)) {
                j3 -= adGroup2.durationsUs[i5];
                i5++;
            }
            j3 += adGroup2.contentResumeOffsetUs;
            i6++;
        }
        if (i4 < getAdCountInGroup(adPlaybackState, i3)) {
            while (i5 < i4) {
                j3 -= adGroup.durationsUs[i5];
                i5++;
            }
        }
        return j3;
    }

    public static long getMediaPeriodPositionUsForContent(long j2, int i3, AdPlaybackState adPlaybackState) {
        if (i3 == -1) {
            i3 = adPlaybackState.adGroupCount;
        }
        long j3 = 0;
        for (int i4 = adPlaybackState.removedAdGroupCount; i4 < i3; i4++) {
            AdPlaybackState.AdGroup adGroup = adPlaybackState.getAdGroup(i4);
            long j4 = adGroup.timeUs;
            if (j4 == Long.MIN_VALUE || j4 > j2 - j3) {
                break;
            }
            for (int i5 = 0; i5 < getAdCountInGroup(adPlaybackState, i4); i5++) {
                j3 += adGroup.durationsUs[i5];
            }
            long j5 = adGroup.contentResumeOffsetUs;
            j3 -= j5;
            long j6 = adGroup.timeUs;
            long j7 = j2 - j3;
            if (j5 + j6 > j7) {
                return Math.max(j6, j7);
            }
        }
        return j2 - j3;
    }

    public static long getStreamPositionUs(Player player, AdPlaybackState adPlaybackState) {
        Timeline currentTimeline = player.getCurrentTimeline();
        if (currentTimeline.isEmpty()) {
            return C.TIME_UNSET;
        }
        Timeline.Period period = currentTimeline.getPeriod(player.getCurrentPeriodIndex(), new Timeline.Period());
        if (!Util.areEqual(period.getAdsId(), adPlaybackState.adsId)) {
            return C.TIME_UNSET;
        }
        if (!player.isPlayingAd()) {
            return getStreamPositionUsForContent(Util.msToUs(player.getCurrentPosition()) - period.getPositionInWindowUs(), -1, adPlaybackState);
        }
        return getStreamPositionUsForAd(Util.msToUs(player.getCurrentPosition()), player.getCurrentAdGroupIndex(), player.getCurrentAdIndexInAdGroup(), adPlaybackState);
    }

    public static long getStreamPositionUsForAd(long j2, int i3, int i4, AdPlaybackState adPlaybackState) {
        int i5;
        AdPlaybackState.AdGroup adGroup = adPlaybackState.getAdGroup(i3);
        long j3 = j2 + adGroup.timeUs;
        int i6 = adPlaybackState.removedAdGroupCount;
        while (true) {
            i5 = 0;
            if (i6 >= i3) {
                break;
            }
            AdPlaybackState.AdGroup adGroup2 = adPlaybackState.getAdGroup(i6);
            while (i5 < getAdCountInGroup(adPlaybackState, i6)) {
                j3 += adGroup2.durationsUs[i5];
                i5++;
            }
            j3 -= adGroup2.contentResumeOffsetUs;
            i6++;
        }
        if (i4 < getAdCountInGroup(adPlaybackState, i3)) {
            while (i5 < i4) {
                j3 += adGroup.durationsUs[i5];
                i5++;
            }
        }
        return j3;
    }

    public static long getStreamPositionUsForContent(long j2, int i3, AdPlaybackState adPlaybackState) {
        if (i3 == -1) {
            i3 = adPlaybackState.adGroupCount;
        }
        long j3 = 0;
        for (int i4 = adPlaybackState.removedAdGroupCount; i4 < i3; i4++) {
            AdPlaybackState.AdGroup adGroup = adPlaybackState.getAdGroup(i4);
            long j4 = adGroup.timeUs;
            if (j4 == Long.MIN_VALUE || j4 > j2) {
                break;
            }
            long j5 = j4 + j3;
            for (int i5 = 0; i5 < getAdCountInGroup(adPlaybackState, i4); i5++) {
                j3 += adGroup.durationsUs[i5];
            }
            long j6 = adGroup.contentResumeOffsetUs;
            j3 -= j6;
            if (adGroup.timeUs + j6 > j2) {
                return Math.max(j5, j2 + j3);
            }
        }
        return j2 + j3;
    }

    public static long getStreamPositionUs(long j2, MediaPeriodId mediaPeriodId, AdPlaybackState adPlaybackState) {
        if (mediaPeriodId.isAd()) {
            return getStreamPositionUsForAd(j2, mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup, adPlaybackState);
        }
        return getStreamPositionUsForContent(j2, mediaPeriodId.nextAdGroupIndex, adPlaybackState);
    }
}
