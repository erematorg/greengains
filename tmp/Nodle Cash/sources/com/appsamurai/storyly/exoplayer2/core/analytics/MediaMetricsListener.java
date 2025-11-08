package com.appsamurai.storyly.exoplayer2.core.analytics;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.DeniedByServerException;
import android.media.MediaCodec;
import android.media.MediaDrm;
import android.media.MediaDrmResetException;
import android.media.NotProvisionedException;
import android.media.metrics.LogSessionId;
import android.media.metrics.MediaMetricsManager;
import android.media.metrics.PlaybackMetrics;
import android.media.metrics.PlaybackSession;
import android.media.metrics.TrackChangeEvent;
import android.os.SystemClock;
import android.system.ErrnoException;
import android.system.OsConstants;
import android.util.Pair;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.b;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.ExoPlayerLibraryInfo;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.MediaItem;
import com.appsamurai.storyly.exoplayer2.common.ParserException;
import com.appsamurai.storyly.exoplayer2.common.PlaybackException;
import com.appsamurai.storyly.exoplayer2.common.Player;
import com.appsamurai.storyly.exoplayer2.common.Timeline;
import com.appsamurai.storyly.exoplayer2.common.Tracks;
import com.appsamurai.storyly.exoplayer2.common.drm.DrmInitData;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.NetworkTypeObserver;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.common.video.VideoSize;
import com.appsamurai.storyly.exoplayer2.core.ExoPlaybackException;
import com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsListener;
import com.appsamurai.storyly.exoplayer2.core.analytics.PlaybackSessionManager;
import com.appsamurai.storyly.exoplayer2.core.audio.AudioSink;
import com.appsamurai.storyly.exoplayer2.core.decoder.DecoderCounters;
import com.appsamurai.storyly.exoplayer2.core.drm.DefaultDrmSessionManager;
import com.appsamurai.storyly.exoplayer2.core.drm.DrmSession;
import com.appsamurai.storyly.exoplayer2.core.drm.UnsupportedDrmException;
import com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecDecoderException;
import com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecRenderer;
import com.appsamurai.storyly.exoplayer2.core.source.LoadEventInfo;
import com.appsamurai.storyly.exoplayer2.core.source.MediaLoadData;
import com.appsamurai.storyly.exoplayer2.core.source.MediaSource;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.FileDataSource;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.HttpDataSource;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.UdpDataSource;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.UUID;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

@RequiresApi(31)
public final class MediaMetricsListener implements AnalyticsListener, PlaybackSessionManager.Listener {
    @Nullable
    private String activeSessionId;
    private int audioUnderruns;
    private final HashMap<String, Long> bandwidthBytes = new HashMap<>();
    private final HashMap<String, Long> bandwidthTimeMs = new HashMap<>();
    private final Context context;
    @Nullable
    private Format currentAudioFormat;
    private int currentNetworkType = 0;
    private int currentPlaybackState = 0;
    @Nullable
    private Format currentTextFormat;
    @Nullable
    private Format currentVideoFormat;
    private int discontinuityReason;
    private int droppedFrames;
    private boolean hasFatalError;
    private int ioErrorType;
    private boolean isSeeking;
    @Nullable
    private PlaybackMetrics.Builder metricsBuilder;
    @Nullable
    private PendingFormatUpdate pendingAudioFormat;
    @Nullable
    private PlaybackException pendingPlayerError;
    @Nullable
    private PendingFormatUpdate pendingTextFormat;
    @Nullable
    private PendingFormatUpdate pendingVideoFormat;
    private final Timeline.Period period = new Timeline.Period();
    private final PlaybackSession playbackSession;
    private int playedFrames;
    private boolean reportedEventsForCurrentSession;
    private final PlaybackSessionManager sessionManager;
    private final long startTimeMs = SystemClock.elapsedRealtime();
    private final Timeline.Window window = new Timeline.Window();

    public static final class ErrorInfo {
        public final int errorCode;
        public final int subErrorCode;

        public ErrorInfo(int i3, int i4) {
            this.errorCode = i3;
            this.subErrorCode = i4;
        }
    }

    public static final class PendingFormatUpdate {
        public final Format format;
        public final int selectionReason;
        public final String sessionId;

        public PendingFormatUpdate(Format format2, int i3, String str) {
            this.format = format2;
            this.selectionReason = i3;
            this.sessionId = str;
        }
    }

    private MediaMetricsListener(Context context2, PlaybackSession playbackSession2) {
        this.context = context2.getApplicationContext();
        this.playbackSession = playbackSession2;
        DefaultPlaybackSessionManager defaultPlaybackSessionManager = new DefaultPlaybackSessionManager();
        this.sessionManager = defaultPlaybackSessionManager;
        defaultPlaybackSessionManager.setListener(this);
    }

    @EnsuresNonNullIf(expression = {"#1"}, result = true)
    private boolean canReportPendingFormatUpdate(@Nullable PendingFormatUpdate pendingFormatUpdate) {
        return pendingFormatUpdate != null && pendingFormatUpdate.sessionId.equals(this.sessionManager.getActiveSessionId());
    }

    @Nullable
    public static MediaMetricsListener create(Context context2) {
        MediaMetricsManager c3 = w.c(context2.getSystemService("media_metrics"));
        if (c3 == null) {
            return null;
        }
        return new MediaMetricsListener(context2, c3.createPlaybackSession());
    }

    private void finishCurrentSession() {
        PlaybackMetrics.Builder builder = this.metricsBuilder;
        if (builder != null && this.reportedEventsForCurrentSession) {
            builder.setAudioUnderrunCount(this.audioUnderruns);
            this.metricsBuilder.setVideoFramesDropped(this.droppedFrames);
            this.metricsBuilder.setVideoFramesPlayed(this.playedFrames);
            Long l2 = this.bandwidthTimeMs.get(this.activeSessionId);
            this.metricsBuilder.setNetworkTransferDurationMillis(l2 == null ? 0 : l2.longValue());
            Long l3 = this.bandwidthBytes.get(this.activeSessionId);
            this.metricsBuilder.setNetworkBytesRead(l3 == null ? 0 : l3.longValue());
            this.metricsBuilder.setStreamSource((l3 == null || l3.longValue() <= 0) ? 0 : 1);
            this.playbackSession.reportPlaybackMetrics(this.metricsBuilder.build());
        }
        this.metricsBuilder = null;
        this.activeSessionId = null;
        this.audioUnderruns = 0;
        this.droppedFrames = 0;
        this.playedFrames = 0;
        this.currentVideoFormat = null;
        this.currentAudioFormat = null;
        this.currentTextFormat = null;
        this.reportedEventsForCurrentSession = false;
    }

    @SuppressLint({"SwitchIntDef"})
    private static int getDrmErrorCode(int i3) {
        switch (Util.getErrorCodeForMediaDrmErrorCode(i3)) {
            case PlaybackException.ERROR_CODE_DRM_PROVISIONING_FAILED:
                return 24;
            case PlaybackException.ERROR_CODE_DRM_CONTENT_ERROR:
                return 28;
            case PlaybackException.ERROR_CODE_DRM_LICENSE_ACQUISITION_FAILED:
                return 25;
            case PlaybackException.ERROR_CODE_DRM_DISALLOWED_OPERATION:
                return 26;
            default:
                return 27;
        }
    }

    @Nullable
    private static DrmInitData getDrmInitData(ImmutableList<Tracks.Group> immutableList) {
        DrmInitData drmInitData;
        UnmodifiableIterator<Tracks.Group> it = immutableList.iterator();
        while (it.hasNext()) {
            Tracks.Group next = it.next();
            int i3 = 0;
            while (true) {
                if (i3 < next.length) {
                    if (next.isTrackSelected(i3) && (drmInitData = next.getTrackFormat(i3).drmInitData) != null) {
                        return drmInitData;
                    }
                    i3++;
                }
            }
        }
        return null;
    }

    private static int getDrmType(DrmInitData drmInitData) {
        for (int i3 = 0; i3 < drmInitData.schemeDataCount; i3++) {
            UUID uuid = drmInitData.get(i3).uuid;
            if (uuid.equals(C.WIDEVINE_UUID)) {
                return 3;
            }
            if (uuid.equals(C.PLAYREADY_UUID)) {
                return 2;
            }
            if (uuid.equals(C.CLEARKEY_UUID)) {
                return 6;
            }
        }
        return 1;
    }

    private static ErrorInfo getErrorInfo(PlaybackException playbackException, Context context2, boolean z2) {
        boolean z3;
        int i3;
        if (playbackException.errorCode == 1001) {
            return new ErrorInfo(20, 0);
        }
        if (playbackException instanceof ExoPlaybackException) {
            ExoPlaybackException exoPlaybackException = (ExoPlaybackException) playbackException;
            z3 = exoPlaybackException.type == 1;
            i3 = exoPlaybackException.rendererFormatSupport;
        } else {
            i3 = 0;
            z3 = false;
        }
        Throwable th = (Throwable) Assertions.checkNotNull(playbackException.getCause());
        if (th instanceof IOException) {
            if (th instanceof HttpDataSource.InvalidResponseCodeException) {
                return new ErrorInfo(5, ((HttpDataSource.InvalidResponseCodeException) th).responseCode);
            }
            if ((th instanceof HttpDataSource.InvalidContentTypeException) || (th instanceof ParserException)) {
                return new ErrorInfo(z2 ? 10 : 11, 0);
            }
            boolean z4 = th instanceof HttpDataSource.HttpDataSourceException;
            if (z4 || (th instanceof UdpDataSource.UdpDataSourceException)) {
                if (NetworkTypeObserver.getInstance(context2).getNetworkType() == 1) {
                    return new ErrorInfo(3, 0);
                }
                Throwable cause = th.getCause();
                return cause instanceof UnknownHostException ? new ErrorInfo(6, 0) : cause instanceof SocketTimeoutException ? new ErrorInfo(7, 0) : (!z4 || ((HttpDataSource.HttpDataSourceException) th).type != 1) ? new ErrorInfo(8, 0) : new ErrorInfo(4, 0);
            } else if (playbackException.errorCode == 1002) {
                return new ErrorInfo(21, 0);
            } else {
                if (th instanceof DrmSession.DrmSessionException) {
                    Throwable th2 = (Throwable) Assertions.checkNotNull(th.getCause());
                    int i4 = Util.SDK_INT;
                    if (i4 < 21 || !(th2 instanceof MediaDrm.MediaDrmStateException)) {
                        return (i4 < 23 || !(th2 instanceof MediaDrmResetException)) ? (i4 < 18 || !(th2 instanceof NotProvisionedException)) ? (i4 < 18 || !(th2 instanceof DeniedByServerException)) ? th2 instanceof UnsupportedDrmException ? new ErrorInfo(23, 0) : th2 instanceof DefaultDrmSessionManager.MissingSchemeDataException ? new ErrorInfo(28, 0) : new ErrorInfo(30, 0) : new ErrorInfo(29, 0) : new ErrorInfo(24, 0) : new ErrorInfo(27, 0);
                    }
                    int errorCodeFromPlatformDiagnosticsInfo = Util.getErrorCodeFromPlatformDiagnosticsInfo(((MediaDrm.MediaDrmStateException) th2).getDiagnosticInfo());
                    return new ErrorInfo(getDrmErrorCode(errorCodeFromPlatformDiagnosticsInfo), errorCodeFromPlatformDiagnosticsInfo);
                } else if (!(th instanceof FileDataSource.FileDataSourceException) || !(th.getCause() instanceof FileNotFoundException)) {
                    return new ErrorInfo(9, 0);
                } else {
                    Throwable cause2 = ((Throwable) Assertions.checkNotNull(th.getCause())).getCause();
                    return (Util.SDK_INT < 21 || !(cause2 instanceof ErrnoException) || ((ErrnoException) cause2).errno != OsConstants.EACCES) ? new ErrorInfo(31, 0) : new ErrorInfo(32, 0);
                }
            }
        } else if (z3 && (i3 == 0 || i3 == 1)) {
            return new ErrorInfo(35, 0);
        } else {
            if (z3 && i3 == 3) {
                return new ErrorInfo(15, 0);
            }
            if (z3 && i3 == 2) {
                return new ErrorInfo(23, 0);
            }
            if (th instanceof MediaCodecRenderer.DecoderInitializationException) {
                return new ErrorInfo(13, Util.getErrorCodeFromPlatformDiagnosticsInfo(((MediaCodecRenderer.DecoderInitializationException) th).diagnosticInfo));
            }
            if (th instanceof MediaCodecDecoderException) {
                return new ErrorInfo(14, Util.getErrorCodeFromPlatformDiagnosticsInfo(((MediaCodecDecoderException) th).diagnosticInfo));
            }
            if (th instanceof OutOfMemoryError) {
                return new ErrorInfo(14, 0);
            }
            if (th instanceof AudioSink.InitializationException) {
                return new ErrorInfo(17, ((AudioSink.InitializationException) th).audioTrackState);
            }
            if (th instanceof AudioSink.WriteException) {
                return new ErrorInfo(18, ((AudioSink.WriteException) th).errorCode);
            }
            if (Util.SDK_INT < 16 || !(th instanceof MediaCodec.CryptoException)) {
                return new ErrorInfo(22, 0);
            }
            int errorCode = ((MediaCodec.CryptoException) th).getErrorCode();
            return new ErrorInfo(getDrmErrorCode(errorCode), errorCode);
        }
    }

    private static Pair<String, String> getLanguageAndRegion(String str) {
        String[] split = Util.split(str, "-");
        return Pair.create(split[0], split.length >= 2 ? split[1] : null);
    }

    private static int getNetworkType(Context context2) {
        switch (NetworkTypeObserver.getInstance(context2).getNetworkType()) {
            case 0:
                return 0;
            case 1:
                return 9;
            case 2:
                return 2;
            case 3:
                return 4;
            case 4:
                return 5;
            case 5:
                return 6;
            case 7:
                return 3;
            case 9:
                return 8;
            case 10:
                return 7;
            default:
                return 1;
        }
    }

    private static int getStreamType(MediaItem mediaItem) {
        MediaItem.LocalConfiguration localConfiguration = mediaItem.localConfiguration;
        if (localConfiguration == null) {
            return 0;
        }
        int inferContentTypeForUriAndMimeType = Util.inferContentTypeForUriAndMimeType(localConfiguration.uri, localConfiguration.mimeType);
        if (inferContentTypeForUriAndMimeType == 0) {
            return 3;
        }
        if (inferContentTypeForUriAndMimeType != 1) {
            return inferContentTypeForUriAndMimeType != 2 ? 1 : 4;
        }
        return 5;
    }

    private static int getTrackChangeReason(int i3) {
        if (i3 == 1) {
            return 2;
        }
        if (i3 != 2) {
            return i3 != 3 ? 1 : 4;
        }
        return 3;
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

    private void maybeReportNetworkChange(long j2) {
        int networkType = getNetworkType(this.context);
        if (networkType != this.currentNetworkType) {
            this.currentNetworkType = networkType;
            this.playbackSession.reportNetworkEvent(v.a().setNetworkType(networkType).setTimeSinceCreatedMillis(j2 - this.startTimeMs).build());
        }
    }

    private void maybeReportPlaybackError(long j2) {
        PlaybackException playbackException = this.pendingPlayerError;
        if (playbackException != null) {
            ErrorInfo errorInfo = getErrorInfo(playbackException, this.context, this.ioErrorType == 4);
            this.playbackSession.reportPlaybackErrorEvent(v.e().setTimeSinceCreatedMillis(j2 - this.startTimeMs).setErrorCode(errorInfo.errorCode).setSubErrorCode(errorInfo.subErrorCode).setException(playbackException).build());
            this.reportedEventsForCurrentSession = true;
            this.pendingPlayerError = null;
        }
    }

    private void maybeReportPlaybackStateChange(Player player, AnalyticsListener.Events events, long j2) {
        if (player.getPlaybackState() != 2) {
            this.isSeeking = false;
        }
        if (player.getPlayerError() == null) {
            this.hasFatalError = false;
        } else if (events.contains(10)) {
            this.hasFatalError = true;
        }
        int resolveNewPlaybackState = resolveNewPlaybackState(player);
        if (this.currentPlaybackState != resolveNewPlaybackState) {
            this.currentPlaybackState = resolveNewPlaybackState;
            this.reportedEventsForCurrentSession = true;
            this.playbackSession.reportPlaybackStateEvent(v.k().setState(this.currentPlaybackState).setTimeSinceCreatedMillis(j2 - this.startTimeMs).build());
        }
    }

    private void maybeReportTrackChanges(Player player, AnalyticsListener.Events events, long j2) {
        if (events.contains(2)) {
            Tracks currentTracks = player.getCurrentTracks();
            boolean isTypeSelected = currentTracks.isTypeSelected(2);
            boolean isTypeSelected2 = currentTracks.isTypeSelected(1);
            boolean isTypeSelected3 = currentTracks.isTypeSelected(3);
            if (isTypeSelected || isTypeSelected2 || isTypeSelected3) {
                if (!isTypeSelected) {
                    maybeUpdateVideoFormat(j2, (Format) null, 0);
                }
                if (!isTypeSelected2) {
                    maybeUpdateAudioFormat(j2, (Format) null, 0);
                }
                if (!isTypeSelected3) {
                    maybeUpdateTextFormat(j2, (Format) null, 0);
                }
            }
        }
        if (canReportPendingFormatUpdate(this.pendingVideoFormat)) {
            PendingFormatUpdate pendingFormatUpdate = this.pendingVideoFormat;
            Format format = pendingFormatUpdate.format;
            if (format.height != -1) {
                maybeUpdateVideoFormat(j2, format, pendingFormatUpdate.selectionReason);
                this.pendingVideoFormat = null;
            }
        }
        if (canReportPendingFormatUpdate(this.pendingAudioFormat)) {
            PendingFormatUpdate pendingFormatUpdate2 = this.pendingAudioFormat;
            maybeUpdateAudioFormat(j2, pendingFormatUpdate2.format, pendingFormatUpdate2.selectionReason);
            this.pendingAudioFormat = null;
        }
        if (canReportPendingFormatUpdate(this.pendingTextFormat)) {
            PendingFormatUpdate pendingFormatUpdate3 = this.pendingTextFormat;
            maybeUpdateTextFormat(j2, pendingFormatUpdate3.format, pendingFormatUpdate3.selectionReason);
            this.pendingTextFormat = null;
        }
    }

    private void maybeUpdateAudioFormat(long j2, @Nullable Format format, int i3) {
        if (!Util.areEqual(this.currentAudioFormat, format)) {
            if (this.currentAudioFormat == null && i3 == 0) {
                i3 = 1;
            }
            this.currentAudioFormat = format;
            reportTrackChangeEvent(0, j2, format, i3);
        }
    }

    private void maybeUpdateMetricsBuilderValues(Player player, AnalyticsListener.Events events) {
        DrmInitData drmInitData;
        if (events.contains(0)) {
            AnalyticsListener.EventTime eventTime = events.getEventTime(0);
            if (this.metricsBuilder != null) {
                maybeUpdateTimelineMetadata(eventTime.timeline, eventTime.mediaPeriodId);
            }
        }
        if (!(!events.contains(2) || this.metricsBuilder == null || (drmInitData = getDrmInitData(player.getCurrentTracks().getGroups())) == null)) {
            b.d(Util.castNonNull(this.metricsBuilder)).setDrmType(getDrmType(drmInitData));
        }
        if (events.contains(1011)) {
            this.audioUnderruns++;
        }
    }

    private void maybeUpdateTextFormat(long j2, @Nullable Format format, int i3) {
        if (!Util.areEqual(this.currentTextFormat, format)) {
            if (this.currentTextFormat == null && i3 == 0) {
                i3 = 1;
            }
            this.currentTextFormat = format;
            reportTrackChangeEvent(2, j2, format, i3);
        }
    }

    @RequiresNonNull({"metricsBuilder"})
    private void maybeUpdateTimelineMetadata(Timeline timeline, @Nullable MediaSource.MediaPeriodId mediaPeriodId) {
        int indexOfPeriod;
        PlaybackMetrics.Builder builder = this.metricsBuilder;
        if (mediaPeriodId != null && (indexOfPeriod = timeline.getIndexOfPeriod(mediaPeriodId.periodUid)) != -1) {
            timeline.getPeriod(indexOfPeriod, this.period);
            timeline.getWindow(this.period.windowIndex, this.window);
            builder.setStreamType(getStreamType(this.window.mediaItem));
            Timeline.Window window2 = this.window;
            if (window2.durationUs != C.TIME_UNSET && !window2.isPlaceholder && !window2.isDynamic && !window2.isLive()) {
                builder.setMediaDurationMillis(this.window.getDurationMs());
            }
            builder.setPlaybackType(this.window.isLive() ? 2 : 1);
            this.reportedEventsForCurrentSession = true;
        }
    }

    private void maybeUpdateVideoFormat(long j2, @Nullable Format format, int i3) {
        if (!Util.areEqual(this.currentVideoFormat, format)) {
            if (this.currentVideoFormat == null && i3 == 0) {
                i3 = 1;
            }
            this.currentVideoFormat = format;
            reportTrackChangeEvent(1, j2, format, i3);
        }
    }

    private void reportTrackChangeEvent(int i3, long j2, @Nullable Format format, int i4) {
        TrackChangeEvent.Builder f2 = v.o(i3).setTimeSinceCreatedMillis(j2 - this.startTimeMs);
        if (format != null) {
            f2.setTrackState(1);
            f2.setTrackChangeReason(getTrackChangeReason(i4));
            String str = format.containerMimeType;
            if (str != null) {
                f2.setContainerMimeType(str);
            }
            String str2 = format.sampleMimeType;
            if (str2 != null) {
                f2.setSampleMimeType(str2);
            }
            String str3 = format.codecs;
            if (str3 != null) {
                f2.setCodecName(str3);
            }
            int i5 = format.bitrate;
            if (i5 != -1) {
                f2.setBitrate(i5);
            }
            int i6 = format.width;
            if (i6 != -1) {
                f2.setWidth(i6);
            }
            int i7 = format.height;
            if (i7 != -1) {
                f2.setHeight(i7);
            }
            int i8 = format.channelCount;
            if (i8 != -1) {
                f2.setChannelCount(i8);
            }
            int i9 = format.sampleRate;
            if (i9 != -1) {
                f2.setAudioSampleRate(i9);
            }
            String str4 = format.language;
            if (str4 != null) {
                Pair<String, String> languageAndRegion = getLanguageAndRegion(str4);
                f2.setLanguage((String) languageAndRegion.first);
                Object obj = languageAndRegion.second;
                if (obj != null) {
                    f2.setLanguageRegion((String) obj);
                }
            }
            float f3 = format.frameRate;
            if (f3 != -1.0f) {
                f2.setVideoFrameRate(f3);
            }
        } else {
            f2.setTrackState(0);
        }
        this.reportedEventsForCurrentSession = true;
        this.playbackSession.reportTrackChangeEvent(f2.build());
    }

    private int resolveNewPlaybackState(Player player) {
        int playbackState = player.getPlaybackState();
        if (this.isSeeking) {
            return 5;
        }
        if (this.hasFatalError) {
            return 13;
        }
        if (playbackState == 4) {
            return 11;
        }
        if (playbackState == 2) {
            int i3 = this.currentPlaybackState;
            if (i3 == 0 || i3 == 2) {
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

    public LogSessionId getLogSessionId() {
        return this.playbackSession.getSessionId();
    }

    public void onAdPlaybackStarted(AnalyticsListener.EventTime eventTime, String str, String str2) {
    }

    public void onBandwidthEstimate(AnalyticsListener.EventTime eventTime, int i3, long j2, long j3) {
        MediaSource.MediaPeriodId mediaPeriodId = eventTime.mediaPeriodId;
        if (mediaPeriodId != null) {
            String sessionForMediaPeriodId = this.sessionManager.getSessionForMediaPeriodId(eventTime.timeline, (MediaSource.MediaPeriodId) Assertions.checkNotNull(mediaPeriodId));
            Long l2 = this.bandwidthBytes.get(sessionForMediaPeriodId);
            Long l3 = this.bandwidthTimeMs.get(sessionForMediaPeriodId);
            long j4 = 0;
            this.bandwidthBytes.put(sessionForMediaPeriodId, Long.valueOf((l2 == null ? 0 : l2.longValue()) + j2));
            HashMap<String, Long> hashMap = this.bandwidthTimeMs;
            if (l3 != null) {
                j4 = l3.longValue();
            }
            hashMap.put(sessionForMediaPeriodId, Long.valueOf(j4 + ((long) i3)));
        }
    }

    public void onDownstreamFormatChanged(AnalyticsListener.EventTime eventTime, MediaLoadData mediaLoadData) {
        if (eventTime.mediaPeriodId != null) {
            PendingFormatUpdate pendingFormatUpdate = new PendingFormatUpdate((Format) Assertions.checkNotNull(mediaLoadData.trackFormat), mediaLoadData.trackSelectionReason, this.sessionManager.getSessionForMediaPeriodId(eventTime.timeline, (MediaSource.MediaPeriodId) Assertions.checkNotNull(eventTime.mediaPeriodId)));
            int i3 = mediaLoadData.trackType;
            if (i3 != 0) {
                if (i3 == 1) {
                    this.pendingAudioFormat = pendingFormatUpdate;
                    return;
                } else if (i3 != 2) {
                    if (i3 == 3) {
                        this.pendingTextFormat = pendingFormatUpdate;
                        return;
                    }
                    return;
                }
            }
            this.pendingVideoFormat = pendingFormatUpdate;
        }
    }

    public void onEvents(Player player, AnalyticsListener.Events events) {
        if (events.size() != 0) {
            maybeAddSessions(events);
            long elapsedRealtime = SystemClock.elapsedRealtime();
            maybeUpdateMetricsBuilderValues(player, events);
            maybeReportPlaybackError(elapsedRealtime);
            maybeReportTrackChanges(player, events, elapsedRealtime);
            maybeReportNetworkChange(elapsedRealtime);
            maybeReportPlaybackStateChange(player, events, elapsedRealtime);
            if (events.contains(AnalyticsListener.EVENT_PLAYER_RELEASED)) {
                this.sessionManager.finishAllSessions(events.getEventTime(AnalyticsListener.EVENT_PLAYER_RELEASED));
            }
        }
    }

    public void onLoadError(AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z2) {
        this.ioErrorType = mediaLoadData.dataType;
    }

    public void onPlayerError(AnalyticsListener.EventTime eventTime, PlaybackException playbackException) {
        this.pendingPlayerError = playbackException;
    }

    public void onPositionDiscontinuity(AnalyticsListener.EventTime eventTime, Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i3) {
        if (i3 == 1) {
            this.isSeeking = true;
        }
        this.discontinuityReason = i3;
    }

    public void onSessionActive(AnalyticsListener.EventTime eventTime, String str) {
        MediaSource.MediaPeriodId mediaPeriodId = eventTime.mediaPeriodId;
        if (mediaPeriodId == null || !mediaPeriodId.isAd()) {
            finishCurrentSession();
            this.activeSessionId = str;
            this.metricsBuilder = v.j().setPlayerName(ExoPlayerLibraryInfo.TAG).setPlayerVersion(ExoPlayerLibraryInfo.VERSION);
            maybeUpdateTimelineMetadata(eventTime.timeline, eventTime.mediaPeriodId);
        }
    }

    public void onSessionCreated(AnalyticsListener.EventTime eventTime, String str) {
    }

    public void onSessionFinished(AnalyticsListener.EventTime eventTime, String str, boolean z2) {
        MediaSource.MediaPeriodId mediaPeriodId = eventTime.mediaPeriodId;
        if ((mediaPeriodId == null || !mediaPeriodId.isAd()) && str.equals(this.activeSessionId)) {
            finishCurrentSession();
        }
        this.bandwidthTimeMs.remove(str);
        this.bandwidthBytes.remove(str);
    }

    public void onVideoDisabled(AnalyticsListener.EventTime eventTime, DecoderCounters decoderCounters) {
        this.droppedFrames += decoderCounters.droppedBufferCount;
        this.playedFrames += decoderCounters.renderedOutputBufferCount;
    }

    public void onVideoSizeChanged(AnalyticsListener.EventTime eventTime, VideoSize videoSize) {
        PendingFormatUpdate pendingFormatUpdate = this.pendingVideoFormat;
        if (pendingFormatUpdate != null) {
            Format format = pendingFormatUpdate.format;
            if (format.height == -1) {
                this.pendingVideoFormat = new PendingFormatUpdate(format.buildUpon().setWidth(videoSize.width).setHeight(videoSize.height).build(), pendingFormatUpdate.selectionReason, pendingFormatUpdate.sessionId);
            }
        }
    }
}
