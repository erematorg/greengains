package com.appsamurai.storyly.exoplayer2.core.util;

import A.a;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.camera.camera2.internal.C0118y;
import androidx.constraintlayout.core.state.b;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.MediaItem;
import com.appsamurai.storyly.exoplayer2.common.PlaybackException;
import com.appsamurai.storyly.exoplayer2.common.PlaybackParameters;
import com.appsamurai.storyly.exoplayer2.common.Player;
import com.appsamurai.storyly.exoplayer2.common.Timeline;
import com.appsamurai.storyly.exoplayer2.common.Tracks;
import com.appsamurai.storyly.exoplayer2.common.audio.AudioAttributes;
import com.appsamurai.storyly.exoplayer2.common.metadata.Metadata;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.common.video.VideoSize;
import com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsListener;
import com.appsamurai.storyly.exoplayer2.core.decoder.DecoderCounters;
import com.appsamurai.storyly.exoplayer2.core.decoder.DecoderReuseEvaluation;
import com.appsamurai.storyly.exoplayer2.core.source.LoadEventInfo;
import com.appsamurai.storyly.exoplayer2.core.source.MediaLoadData;
import com.appsamurai.storyly.exoplayer2.core.trackselection.MappingTrackSelector;
import com.google.common.collect.ImmutableList;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

public class EventLogger implements AnalyticsListener {
    private static final String DEFAULT_TAG = "EventLogger";
    private static final int MAX_TIMELINE_ITEM_LINES = 3;
    private static final NumberFormat TIME_FORMAT;
    private final Timeline.Period period;
    private final long startTimeMs;
    private final String tag;
    private final Timeline.Window window;

    static {
        NumberFormat instance = NumberFormat.getInstance(Locale.US);
        TIME_FORMAT = instance;
        instance.setMinimumFractionDigits(2);
        instance.setMaximumFractionDigits(2);
        instance.setGroupingUsed(false);
    }

    public EventLogger() {
        this(DEFAULT_TAG);
    }

    private static String getDiscontinuityReasonString(int i3) {
        return i3 != 0 ? i3 != 1 ? i3 != 2 ? i3 != 3 ? i3 != 4 ? i3 != 5 ? "?" : "INTERNAL" : "REMOVE" : "SKIP" : "SEEK_ADJUSTMENT" : "SEEK" : "AUTO_TRANSITION";
    }

    private String getEventString(AnalyticsListener.EventTime eventTime, String str, @Nullable String str2, @Nullable Throwable th) {
        StringBuilder q2 = a.q(str, " [");
        q2.append(getEventTimeString(eventTime));
        String sb = q2.toString();
        if (th instanceof PlaybackException) {
            StringBuilder q3 = a.q(sb, ", errorCode=");
            q3.append(((PlaybackException) th).getErrorCodeName());
            sb = q3.toString();
        }
        if (str2 != null) {
            sb = android.support.v4.media.session.a.n(sb, ", ", str2);
        }
        String throwableString = Log.getThrowableString(th);
        if (!TextUtils.isEmpty(throwableString)) {
            StringBuilder q4 = a.q(sb, "\n  ");
            q4.append(throwableString.replace("\n", "\n  "));
            q4.append(10);
            sb = q4.toString();
        }
        return android.support.v4.media.session.a.m(sb, "]");
    }

    private String getEventTimeString(AnalyticsListener.EventTime eventTime) {
        String str = "window=" + eventTime.windowIndex;
        if (eventTime.mediaPeriodId != null) {
            StringBuilder q2 = a.q(str, ", period=");
            q2.append(eventTime.timeline.getIndexOfPeriod(eventTime.mediaPeriodId.periodUid));
            str = q2.toString();
            if (eventTime.mediaPeriodId.isAd()) {
                StringBuilder q3 = a.q(str, ", adGroup=");
                q3.append(eventTime.mediaPeriodId.adGroupIndex);
                StringBuilder q4 = a.q(q3.toString(), ", ad=");
                q4.append(eventTime.mediaPeriodId.adIndexInAdGroup);
                str = q4.toString();
            }
        }
        StringBuilder sb = new StringBuilder("eventTime=");
        sb.append(getTimeString(eventTime.realtimeMs - this.startTimeMs));
        sb.append(", mediaPos=");
        return C0118y.j(sb, getTimeString(eventTime.eventPlaybackPositionMs), ", ", str);
    }

    private static String getMediaItemTransitionReasonString(int i3) {
        return i3 != 0 ? i3 != 1 ? i3 != 2 ? i3 != 3 ? "?" : "PLAYLIST_CHANGED" : "SEEK" : "AUTO" : "REPEAT";
    }

    private static String getPlayWhenReadyChangeReasonString(int i3) {
        return i3 != 1 ? i3 != 2 ? i3 != 3 ? i3 != 4 ? i3 != 5 ? "?" : "END_OF_MEDIA_ITEM" : "REMOTE" : "AUDIO_BECOMING_NOISY" : "AUDIO_FOCUS_LOSS" : "USER_REQUEST";
    }

    private static String getPlaybackSuppressionReasonString(int i3) {
        return i3 != 0 ? i3 != 1 ? "?" : "TRANSIENT_AUDIO_FOCUS_LOSS" : "NONE";
    }

    private static String getRepeatModeString(int i3) {
        return i3 != 0 ? i3 != 1 ? i3 != 2 ? "?" : "ALL" : "ONE" : "OFF";
    }

    private static String getStateString(int i3) {
        return i3 != 1 ? i3 != 2 ? i3 != 3 ? i3 != 4 ? "?" : "ENDED" : "READY" : "BUFFERING" : "IDLE";
    }

    private static String getTimeString(long j2) {
        return j2 == C.TIME_UNSET ? "?" : TIME_FORMAT.format((double) (((float) j2) / 1000.0f));
    }

    private static String getTimelineChangeReasonString(int i3) {
        return i3 != 0 ? i3 != 1 ? "?" : "SOURCE_UPDATE" : "PLAYLIST_CHANGED";
    }

    private static String getTrackStatusString(boolean z2) {
        return z2 ? "[X]" : "[ ]";
    }

    private void printInternalError(AnalyticsListener.EventTime eventTime, String str, Exception exc) {
        loge(eventTime, "internalError", str, exc);
    }

    private void printMetadata(Metadata metadata, String str) {
        for (int i3 = 0; i3 < metadata.length(); i3++) {
            StringBuilder p2 = a.p(str);
            p2.append(metadata.get(i3));
            logd(p2.toString());
        }
    }

    public void logd(String str) {
        Log.d(this.tag, str);
    }

    public void loge(String str) {
        Log.e(this.tag, str);
    }

    public void onAudioAttributesChanged(AnalyticsListener.EventTime eventTime, AudioAttributes audioAttributes) {
        logd(eventTime, "audioAttributes", audioAttributes.contentType + "," + audioAttributes.flags + "," + audioAttributes.usage + "," + audioAttributes.allowedCapturePolicy);
    }

    public void onAudioDecoderInitialized(AnalyticsListener.EventTime eventTime, String str, long j2) {
        logd(eventTime, "audioDecoderInitialized", str);
    }

    public void onAudioDecoderReleased(AnalyticsListener.EventTime eventTime, String str) {
        logd(eventTime, "audioDecoderReleased", str);
    }

    public void onAudioDisabled(AnalyticsListener.EventTime eventTime, DecoderCounters decoderCounters) {
        logd(eventTime, "audioDisabled");
    }

    public void onAudioEnabled(AnalyticsListener.EventTime eventTime, DecoderCounters decoderCounters) {
        logd(eventTime, "audioEnabled");
    }

    public void onAudioInputFormatChanged(AnalyticsListener.EventTime eventTime, Format format, @Nullable DecoderReuseEvaluation decoderReuseEvaluation) {
        logd(eventTime, "audioInputFormat", Format.toLogString(format));
    }

    public void onAudioSessionIdChanged(AnalyticsListener.EventTime eventTime, int i3) {
        logd(eventTime, "audioSessionId", Integer.toString(i3));
    }

    public void onAudioUnderrun(AnalyticsListener.EventTime eventTime, int i3, long j2, long j3) {
        loge(eventTime, "audioTrackUnderrun", i3 + ", " + j2 + ", " + j3, (Throwable) null);
    }

    public void onBandwidthEstimate(AnalyticsListener.EventTime eventTime, int i3, long j2, long j3) {
    }

    public void onDownstreamFormatChanged(AnalyticsListener.EventTime eventTime, MediaLoadData mediaLoadData) {
        logd(eventTime, "downstreamFormat", Format.toLogString(mediaLoadData.trackFormat));
    }

    public void onDrmKeysLoaded(AnalyticsListener.EventTime eventTime) {
        logd(eventTime, "drmKeysLoaded");
    }

    public void onDrmKeysRemoved(AnalyticsListener.EventTime eventTime) {
        logd(eventTime, "drmKeysRemoved");
    }

    public void onDrmKeysRestored(AnalyticsListener.EventTime eventTime) {
        logd(eventTime, "drmKeysRestored");
    }

    public void onDrmSessionAcquired(AnalyticsListener.EventTime eventTime, int i3) {
        logd(eventTime, "drmSessionAcquired", a.k("state=", i3));
    }

    public void onDrmSessionManagerError(AnalyticsListener.EventTime eventTime, Exception exc) {
        printInternalError(eventTime, "drmSessionManagerError", exc);
    }

    public void onDrmSessionReleased(AnalyticsListener.EventTime eventTime) {
        logd(eventTime, "drmSessionReleased");
    }

    public void onDroppedVideoFrames(AnalyticsListener.EventTime eventTime, int i3, long j2) {
        logd(eventTime, "droppedFrames", Integer.toString(i3));
    }

    public void onIsLoadingChanged(AnalyticsListener.EventTime eventTime, boolean z2) {
        logd(eventTime, "loading", Boolean.toString(z2));
    }

    public void onIsPlayingChanged(AnalyticsListener.EventTime eventTime, boolean z2) {
        logd(eventTime, "isPlaying", Boolean.toString(z2));
    }

    public void onLoadCanceled(AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
    }

    public void onLoadCompleted(AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
    }

    public void onLoadError(AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z2) {
        printInternalError(eventTime, "loadError", iOException);
    }

    public void onLoadStarted(AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
    }

    public void onMediaItemTransition(AnalyticsListener.EventTime eventTime, @Nullable MediaItem mediaItem, int i3) {
        logd("mediaItem [" + getEventTimeString(eventTime) + ", reason=" + getMediaItemTransitionReasonString(i3) + "]");
    }

    public void onMetadata(AnalyticsListener.EventTime eventTime, Metadata metadata) {
        logd("metadata [" + getEventTimeString(eventTime));
        printMetadata(metadata, "  ");
        logd("]");
    }

    public void onPlayWhenReadyChanged(AnalyticsListener.EventTime eventTime, boolean z2, int i3) {
        logd(eventTime, "playWhenReady", z2 + ", " + getPlayWhenReadyChangeReasonString(i3));
    }

    public void onPlaybackParametersChanged(AnalyticsListener.EventTime eventTime, PlaybackParameters playbackParameters) {
        logd(eventTime, "playbackParameters", playbackParameters.toString());
    }

    public void onPlaybackStateChanged(AnalyticsListener.EventTime eventTime, int i3) {
        logd(eventTime, RemoteConfigConstants.ResponseFieldKey.STATE, getStateString(i3));
    }

    public void onPlaybackSuppressionReasonChanged(AnalyticsListener.EventTime eventTime, int i3) {
        logd(eventTime, "playbackSuppressionReason", getPlaybackSuppressionReasonString(i3));
    }

    public void onPlayerError(AnalyticsListener.EventTime eventTime, PlaybackException playbackException) {
        loge(eventTime, "playerFailed", playbackException);
    }

    public void onPositionDiscontinuity(AnalyticsListener.EventTime eventTime, Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i3) {
        StringBuilder sb = new StringBuilder("reason=");
        sb.append(getDiscontinuityReasonString(i3));
        sb.append(", PositionInfo:old [mediaItem=");
        sb.append(positionInfo.mediaItemIndex);
        sb.append(", period=");
        sb.append(positionInfo.periodIndex);
        sb.append(", pos=");
        sb.append(positionInfo.positionMs);
        if (positionInfo.adGroupIndex != -1) {
            sb.append(", contentPos=");
            sb.append(positionInfo.contentPositionMs);
            sb.append(", adGroup=");
            sb.append(positionInfo.adGroupIndex);
            sb.append(", ad=");
            sb.append(positionInfo.adIndexInAdGroup);
        }
        sb.append("], PositionInfo:new [mediaItem=");
        sb.append(positionInfo2.mediaItemIndex);
        sb.append(", period=");
        sb.append(positionInfo2.periodIndex);
        sb.append(", pos=");
        sb.append(positionInfo2.positionMs);
        if (positionInfo2.adGroupIndex != -1) {
            sb.append(", contentPos=");
            sb.append(positionInfo2.contentPositionMs);
            sb.append(", adGroup=");
            sb.append(positionInfo2.adGroupIndex);
            sb.append(", ad=");
            sb.append(positionInfo2.adIndexInAdGroup);
        }
        sb.append("]");
        logd(eventTime, "positionDiscontinuity", sb.toString());
    }

    public void onRenderedFirstFrame(AnalyticsListener.EventTime eventTime, Object obj, long j2) {
        logd(eventTime, "renderedFirstFrame", String.valueOf(obj));
    }

    public void onRepeatModeChanged(AnalyticsListener.EventTime eventTime, int i3) {
        logd(eventTime, "repeatMode", getRepeatModeString(i3));
    }

    public void onShuffleModeChanged(AnalyticsListener.EventTime eventTime, boolean z2) {
        logd(eventTime, "shuffleModeEnabled", Boolean.toString(z2));
    }

    public void onSkipSilenceEnabledChanged(AnalyticsListener.EventTime eventTime, boolean z2) {
        logd(eventTime, "skipSilenceEnabled", Boolean.toString(z2));
    }

    public void onSurfaceSizeChanged(AnalyticsListener.EventTime eventTime, int i3, int i4) {
        logd(eventTime, "surfaceSize", i3 + ", " + i4);
    }

    public void onTimelineChanged(AnalyticsListener.EventTime eventTime, int i3) {
        int periodCount = eventTime.timeline.getPeriodCount();
        int windowCount = eventTime.timeline.getWindowCount();
        StringBuilder sb = new StringBuilder("timeline [");
        b.v(sb, getEventTimeString(eventTime), ", periodCount=", periodCount, ", windowCount=");
        sb.append(windowCount);
        sb.append(", reason=");
        sb.append(getTimelineChangeReasonString(i3));
        logd(sb.toString());
        for (int i4 = 0; i4 < Math.min(periodCount, 3); i4++) {
            eventTime.timeline.getPeriod(i4, this.period);
            logd("  period [" + getTimeString(this.period.getDurationMs()) + "]");
        }
        if (periodCount > 3) {
            logd("  ...");
        }
        for (int i5 = 0; i5 < Math.min(windowCount, 3); i5++) {
            eventTime.timeline.getWindow(i5, this.window);
            logd("  window [" + getTimeString(this.window.getDurationMs()) + ", seekable=" + this.window.isSeekable + ", dynamic=" + this.window.isDynamic + "]");
        }
        if (windowCount > 3) {
            logd("  ...");
        }
        logd("]");
    }

    public void onTracksChanged(AnalyticsListener.EventTime eventTime, Tracks tracks) {
        Metadata metadata;
        logd("tracks [" + getEventTimeString(eventTime));
        ImmutableList<Tracks.Group> groups = tracks.getGroups();
        for (int i3 = 0; i3 < groups.size(); i3++) {
            Tracks.Group group = groups.get(i3);
            logd("  group [");
            for (int i4 = 0; i4 < group.length; i4++) {
                String trackStatusString = getTrackStatusString(group.isTrackSelected(i4));
                String formatSupportString = Util.getFormatSupportString(group.getTrackSupport(i4));
                StringBuilder x2 = android.support.v4.media.session.a.x("    ", trackStatusString, " Track:", i4, ", ");
                x2.append(Format.toLogString(group.getTrackFormat(i4)));
                x2.append(", supported=");
                x2.append(formatSupportString);
                logd(x2.toString());
            }
            logd("  ]");
        }
        boolean z2 = false;
        int i5 = 0;
        while (!z2 && i5 < groups.size()) {
            Tracks.Group group2 = groups.get(i5);
            int i6 = 0;
            while (!z2 && i6 < group2.length) {
                if (group2.isTrackSelected(i6) && (metadata = group2.getTrackFormat(i6).metadata) != null && metadata.length() > 0) {
                    logd("  Metadata [");
                    printMetadata(metadata, "    ");
                    logd("  ]");
                    z2 = true;
                }
                i6++;
            }
            i5++;
        }
        logd("]");
    }

    public void onUpstreamDiscarded(AnalyticsListener.EventTime eventTime, MediaLoadData mediaLoadData) {
        logd(eventTime, "upstreamDiscarded", Format.toLogString(mediaLoadData.trackFormat));
    }

    public void onVideoDecoderInitialized(AnalyticsListener.EventTime eventTime, String str, long j2) {
        logd(eventTime, "videoDecoderInitialized", str);
    }

    public void onVideoDecoderReleased(AnalyticsListener.EventTime eventTime, String str) {
        logd(eventTime, "videoDecoderReleased", str);
    }

    public void onVideoDisabled(AnalyticsListener.EventTime eventTime, DecoderCounters decoderCounters) {
        logd(eventTime, "videoDisabled");
    }

    public void onVideoEnabled(AnalyticsListener.EventTime eventTime, DecoderCounters decoderCounters) {
        logd(eventTime, "videoEnabled");
    }

    public void onVideoInputFormatChanged(AnalyticsListener.EventTime eventTime, Format format, @Nullable DecoderReuseEvaluation decoderReuseEvaluation) {
        logd(eventTime, "videoInputFormat", Format.toLogString(format));
    }

    public void onVideoSizeChanged(AnalyticsListener.EventTime eventTime, VideoSize videoSize) {
        logd(eventTime, "videoSize", videoSize.width + ", " + videoSize.height);
    }

    public void onVolumeChanged(AnalyticsListener.EventTime eventTime, float f2) {
        logd(eventTime, "volume", Float.toString(f2));
    }

    public EventLogger(String str) {
        this.tag = str;
        this.window = new Timeline.Window();
        this.period = new Timeline.Period();
        this.startTimeMs = SystemClock.elapsedRealtime();
    }

    private void logd(AnalyticsListener.EventTime eventTime, String str) {
        logd(getEventString(eventTime, str, (String) null, (Throwable) null));
    }

    private void loge(AnalyticsListener.EventTime eventTime, String str, @Nullable Throwable th) {
        loge(getEventString(eventTime, str, (String) null, th));
    }

    private void logd(AnalyticsListener.EventTime eventTime, String str, String str2) {
        logd(getEventString(eventTime, str, str2, (Throwable) null));
    }

    private void loge(AnalyticsListener.EventTime eventTime, String str, String str2, @Nullable Throwable th) {
        loge(getEventString(eventTime, str, str2, th));
    }

    @Deprecated
    public EventLogger(@Nullable MappingTrackSelector mappingTrackSelector) {
        this(DEFAULT_TAG);
    }

    @Deprecated
    public EventLogger(@Nullable MappingTrackSelector mappingTrackSelector, String str) {
        this(str);
    }
}
