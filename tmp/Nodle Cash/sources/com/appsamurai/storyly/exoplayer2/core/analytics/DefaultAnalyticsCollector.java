package com.appsamurai.storyly.exoplayer2.core.analytics;

import android.os.Handler;
import android.os.Looper;
import android.util.SparseArray;
import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import androidx.camera.camera2.interop.d;
import androidx.camera.core.processing.c;
import com.appsamurai.storyly.exoplayer2.common.DeviceInfo;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.MediaItem;
import com.appsamurai.storyly.exoplayer2.common.MediaMetadata;
import com.appsamurai.storyly.exoplayer2.common.PlaybackException;
import com.appsamurai.storyly.exoplayer2.common.PlaybackParameters;
import com.appsamurai.storyly.exoplayer2.common.Player;
import com.appsamurai.storyly.exoplayer2.common.Timeline;
import com.appsamurai.storyly.exoplayer2.common.Tracks;
import com.appsamurai.storyly.exoplayer2.common.a;
import com.appsamurai.storyly.exoplayer2.common.audio.AudioAttributes;
import com.appsamurai.storyly.exoplayer2.common.metadata.Metadata;
import com.appsamurai.storyly.exoplayer2.common.text.Cue;
import com.appsamurai.storyly.exoplayer2.common.text.CueGroup;
import com.appsamurai.storyly.exoplayer2.common.trackselection.TrackSelectionParameters;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Clock;
import com.appsamurai.storyly.exoplayer2.common.util.FlagSet;
import com.appsamurai.storyly.exoplayer2.common.util.HandlerWrapper;
import com.appsamurai.storyly.exoplayer2.common.util.ListenerSet;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.common.video.VideoSize;
import com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsListener;
import com.appsamurai.storyly.exoplayer2.core.decoder.DecoderCounters;
import com.appsamurai.storyly.exoplayer2.core.decoder.DecoderReuseEvaluation;
import com.appsamurai.storyly.exoplayer2.core.source.LoadEventInfo;
import com.appsamurai.storyly.exoplayer2.core.source.MediaLoadData;
import com.appsamurai.storyly.exoplayer2.core.source.MediaSource;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import java.io.IOException;
import java.util.List;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

public class DefaultAnalyticsCollector implements AnalyticsCollector {
    private final Clock clock;
    private final SparseArray<AnalyticsListener.EventTime> eventTimes;
    private HandlerWrapper handler;
    private boolean isSeeking;
    private ListenerSet<AnalyticsListener> listeners;
    private final MediaPeriodQueueTracker mediaPeriodQueueTracker;
    private final Timeline.Period period;
    private Player player;
    private final Timeline.Window window = new Timeline.Window();

    public static final class MediaPeriodQueueTracker {
        @Nullable
        private MediaSource.MediaPeriodId currentPlayerMediaPeriod;
        /* access modifiers changed from: private */
        public ImmutableList<MediaSource.MediaPeriodId> mediaPeriodQueue = ImmutableList.of();
        private ImmutableMap<MediaSource.MediaPeriodId, Timeline> mediaPeriodTimelines = ImmutableMap.of();
        private final Timeline.Period period;
        private MediaSource.MediaPeriodId playingMediaPeriod;
        private MediaSource.MediaPeriodId readingMediaPeriod;

        public MediaPeriodQueueTracker(Timeline.Period period2) {
            this.period = period2;
        }

        private void addTimelineForMediaPeriodId(ImmutableMap.Builder<MediaSource.MediaPeriodId, Timeline> builder, @Nullable MediaSource.MediaPeriodId mediaPeriodId, Timeline timeline) {
            if (mediaPeriodId != null) {
                if (timeline.getIndexOfPeriod(mediaPeriodId.periodUid) != -1) {
                    builder.put(mediaPeriodId, timeline);
                    return;
                }
                Timeline timeline2 = this.mediaPeriodTimelines.get(mediaPeriodId);
                if (timeline2 != null) {
                    builder.put(mediaPeriodId, timeline2);
                }
            }
        }

        @Nullable
        private static MediaSource.MediaPeriodId findCurrentPlayerMediaPeriodInQueue(Player player, ImmutableList<MediaSource.MediaPeriodId> immutableList, @Nullable MediaSource.MediaPeriodId mediaPeriodId, Timeline.Period period2) {
            Timeline currentTimeline = player.getCurrentTimeline();
            int currentPeriodIndex = player.getCurrentPeriodIndex();
            Object uidOfPeriod = currentTimeline.isEmpty() ? null : currentTimeline.getUidOfPeriod(currentPeriodIndex);
            int adGroupIndexAfterPositionUs = (player.isPlayingAd() || currentTimeline.isEmpty()) ? -1 : currentTimeline.getPeriod(currentPeriodIndex, period2).getAdGroupIndexAfterPositionUs(Util.msToUs(player.getCurrentPosition()) - period2.getPositionInWindowUs());
            for (int i3 = 0; i3 < immutableList.size(); i3++) {
                MediaSource.MediaPeriodId mediaPeriodId2 = immutableList.get(i3);
                if (isMatchingMediaPeriod(mediaPeriodId2, uidOfPeriod, player.isPlayingAd(), player.getCurrentAdGroupIndex(), player.getCurrentAdIndexInAdGroup(), adGroupIndexAfterPositionUs)) {
                    return mediaPeriodId2;
                }
            }
            if (immutableList.isEmpty() && mediaPeriodId != null) {
                if (isMatchingMediaPeriod(mediaPeriodId, uidOfPeriod, player.isPlayingAd(), player.getCurrentAdGroupIndex(), player.getCurrentAdIndexInAdGroup(), adGroupIndexAfterPositionUs)) {
                    return mediaPeriodId;
                }
            }
            return null;
        }

        private static boolean isMatchingMediaPeriod(MediaSource.MediaPeriodId mediaPeriodId, @Nullable Object obj, boolean z2, int i3, int i4, int i5) {
            if (!mediaPeriodId.periodUid.equals(obj)) {
                return false;
            }
            return (z2 && mediaPeriodId.adGroupIndex == i3 && mediaPeriodId.adIndexInAdGroup == i4) || (!z2 && mediaPeriodId.adGroupIndex == -1 && mediaPeriodId.nextAdGroupIndex == i5);
        }

        private void updateMediaPeriodTimelines(Timeline timeline) {
            ImmutableMap.Builder builder = ImmutableMap.builder();
            if (this.mediaPeriodQueue.isEmpty()) {
                addTimelineForMediaPeriodId(builder, this.playingMediaPeriod, timeline);
                if (!Objects.equal(this.readingMediaPeriod, this.playingMediaPeriod)) {
                    addTimelineForMediaPeriodId(builder, this.readingMediaPeriod, timeline);
                }
                if (!Objects.equal(this.currentPlayerMediaPeriod, this.playingMediaPeriod) && !Objects.equal(this.currentPlayerMediaPeriod, this.readingMediaPeriod)) {
                    addTimelineForMediaPeriodId(builder, this.currentPlayerMediaPeriod, timeline);
                }
            } else {
                for (int i3 = 0; i3 < this.mediaPeriodQueue.size(); i3++) {
                    addTimelineForMediaPeriodId(builder, this.mediaPeriodQueue.get(i3), timeline);
                }
                if (!this.mediaPeriodQueue.contains(this.currentPlayerMediaPeriod)) {
                    addTimelineForMediaPeriodId(builder, this.currentPlayerMediaPeriod, timeline);
                }
            }
            this.mediaPeriodTimelines = builder.build();
        }

        @Nullable
        public MediaSource.MediaPeriodId getCurrentPlayerMediaPeriod() {
            return this.currentPlayerMediaPeriod;
        }

        @Nullable
        public MediaSource.MediaPeriodId getLoadingMediaPeriod() {
            if (this.mediaPeriodQueue.isEmpty()) {
                return null;
            }
            return (MediaSource.MediaPeriodId) Iterables.getLast(this.mediaPeriodQueue);
        }

        @Nullable
        public Timeline getMediaPeriodIdTimeline(MediaSource.MediaPeriodId mediaPeriodId) {
            return this.mediaPeriodTimelines.get(mediaPeriodId);
        }

        @Nullable
        public MediaSource.MediaPeriodId getPlayingMediaPeriod() {
            return this.playingMediaPeriod;
        }

        @Nullable
        public MediaSource.MediaPeriodId getReadingMediaPeriod() {
            return this.readingMediaPeriod;
        }

        public void onPositionDiscontinuity(Player player) {
            this.currentPlayerMediaPeriod = findCurrentPlayerMediaPeriodInQueue(player, this.mediaPeriodQueue, this.playingMediaPeriod, this.period);
        }

        public void onQueueUpdated(List<MediaSource.MediaPeriodId> list, @Nullable MediaSource.MediaPeriodId mediaPeriodId, Player player) {
            this.mediaPeriodQueue = ImmutableList.copyOf(list);
            if (!list.isEmpty()) {
                this.playingMediaPeriod = list.get(0);
                this.readingMediaPeriod = (MediaSource.MediaPeriodId) Assertions.checkNotNull(mediaPeriodId);
            }
            if (this.currentPlayerMediaPeriod == null) {
                this.currentPlayerMediaPeriod = findCurrentPlayerMediaPeriodInQueue(player, this.mediaPeriodQueue, this.playingMediaPeriod, this.period);
            }
            updateMediaPeriodTimelines(player.getCurrentTimeline());
        }

        public void onTimelineChanged(Player player) {
            this.currentPlayerMediaPeriod = findCurrentPlayerMediaPeriodInQueue(player, this.mediaPeriodQueue, this.playingMediaPeriod, this.period);
            updateMediaPeriodTimelines(player.getCurrentTimeline());
        }
    }

    public DefaultAnalyticsCollector(Clock clock2) {
        this.clock = (Clock) Assertions.checkNotNull(clock2);
        this.listeners = new ListenerSet<>(Util.getCurrentOrMainLooper(), clock2, new a(29));
        Timeline.Period period2 = new Timeline.Period();
        this.period = period2;
        this.mediaPeriodQueueTracker = new MediaPeriodQueueTracker(period2);
        this.eventTimes = new SparseArray<>();
    }

    private AnalyticsListener.EventTime generateLoadingMediaPeriodEventTime() {
        return generateEventTime(this.mediaPeriodQueueTracker.getLoadingMediaPeriod());
    }

    private AnalyticsListener.EventTime generateMediaPeriodEventTime(int i3, @Nullable MediaSource.MediaPeriodId mediaPeriodId) {
        Assertions.checkNotNull(this.player);
        if (mediaPeriodId != null) {
            return this.mediaPeriodQueueTracker.getMediaPeriodIdTimeline(mediaPeriodId) != null ? generateEventTime(mediaPeriodId) : generateEventTime(Timeline.EMPTY, i3, mediaPeriodId);
        }
        Timeline currentTimeline = this.player.getCurrentTimeline();
        if (i3 >= currentTimeline.getWindowCount()) {
            currentTimeline = Timeline.EMPTY;
        }
        return generateEventTime(currentTimeline, i3, (MediaSource.MediaPeriodId) null);
    }

    private AnalyticsListener.EventTime generatePlayingMediaPeriodEventTime() {
        return generateEventTime(this.mediaPeriodQueueTracker.getPlayingMediaPeriod());
    }

    private AnalyticsListener.EventTime generateReadingMediaPeriodEventTime() {
        return generateEventTime(this.mediaPeriodQueueTracker.getReadingMediaPeriod());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r2 = ((com.appsamurai.storyly.exoplayer2.core.ExoPlaybackException) r2).mediaPeriodId;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsListener.EventTime getEventTimeForErrorEvent(@androidx.annotation.Nullable com.appsamurai.storyly.exoplayer2.common.PlaybackException r2) {
        /*
            r1 = this;
            boolean r0 = r2 instanceof com.appsamurai.storyly.exoplayer2.core.ExoPlaybackException
            if (r0 == 0) goto L_0x0014
            com.appsamurai.storyly.exoplayer2.core.ExoPlaybackException r2 = (com.appsamurai.storyly.exoplayer2.core.ExoPlaybackException) r2
            com.appsamurai.storyly.exoplayer2.common.source.MediaPeriodId r2 = r2.mediaPeriodId
            if (r2 == 0) goto L_0x0014
            com.appsamurai.storyly.exoplayer2.core.source.MediaSource$MediaPeriodId r0 = new com.appsamurai.storyly.exoplayer2.core.source.MediaSource$MediaPeriodId
            r0.<init>((com.appsamurai.storyly.exoplayer2.common.source.MediaPeriodId) r2)
            com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsListener$EventTime r1 = r1.generateEventTime(r0)
            return r1
        L_0x0014:
            com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsListener$EventTime r1 = r1.generateCurrentPlayerMediaPeriodEventTime()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.analytics.DefaultAnalyticsCollector.getEventTimeForErrorEvent(com.appsamurai.storyly.exoplayer2.common.PlaybackException):com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsListener$EventTime");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$new$0(AnalyticsListener analyticsListener, FlagSet flagSet) {
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onAudioDecoderInitialized$4(AnalyticsListener.EventTime eventTime, String str, long j2, long j3, AnalyticsListener analyticsListener) {
        analyticsListener.onAudioDecoderInitialized(eventTime, str, j2);
        long j4 = j2;
        analyticsListener.onAudioDecoderInitialized(eventTime, str, j3, j4);
        analyticsListener.onDecoderInitialized(eventTime, 1, str, j4);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onAudioDisabled$9(AnalyticsListener.EventTime eventTime, DecoderCounters decoderCounters, AnalyticsListener analyticsListener) {
        analyticsListener.onAudioDisabled(eventTime, decoderCounters);
        analyticsListener.onDecoderDisabled(eventTime, 1, decoderCounters);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onAudioEnabled$3(AnalyticsListener.EventTime eventTime, DecoderCounters decoderCounters, AnalyticsListener analyticsListener) {
        analyticsListener.onAudioEnabled(eventTime, decoderCounters);
        analyticsListener.onDecoderEnabled(eventTime, 1, decoderCounters);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onAudioInputFormatChanged$5(AnalyticsListener.EventTime eventTime, Format format, DecoderReuseEvaluation decoderReuseEvaluation, AnalyticsListener analyticsListener) {
        analyticsListener.onAudioInputFormatChanged(eventTime, format);
        analyticsListener.onAudioInputFormatChanged(eventTime, format, decoderReuseEvaluation);
        analyticsListener.onDecoderInputFormatChanged(eventTime, 1, format);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onDrmSessionAcquired$62(AnalyticsListener.EventTime eventTime, int i3, AnalyticsListener analyticsListener) {
        analyticsListener.onDrmSessionAcquired(eventTime);
        analyticsListener.onDrmSessionAcquired(eventTime, i3);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onIsLoadingChanged$32(AnalyticsListener.EventTime eventTime, boolean z2, AnalyticsListener analyticsListener) {
        analyticsListener.onLoadingChanged(eventTime, z2);
        analyticsListener.onIsLoadingChanged(eventTime, z2);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onPositionDiscontinuity$43(AnalyticsListener.EventTime eventTime, int i3, Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, AnalyticsListener analyticsListener) {
        analyticsListener.onPositionDiscontinuity(eventTime, i3);
        analyticsListener.onPositionDiscontinuity(eventTime, positionInfo, positionInfo2, i3);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onVideoDecoderInitialized$14(AnalyticsListener.EventTime eventTime, String str, long j2, long j3, AnalyticsListener analyticsListener) {
        analyticsListener.onVideoDecoderInitialized(eventTime, str, j2);
        long j4 = j2;
        analyticsListener.onVideoDecoderInitialized(eventTime, str, j3, j4);
        analyticsListener.onDecoderInitialized(eventTime, 2, str, j4);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onVideoDisabled$18(AnalyticsListener.EventTime eventTime, DecoderCounters decoderCounters, AnalyticsListener analyticsListener) {
        analyticsListener.onVideoDisabled(eventTime, decoderCounters);
        analyticsListener.onDecoderDisabled(eventTime, 2, decoderCounters);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onVideoEnabled$13(AnalyticsListener.EventTime eventTime, DecoderCounters decoderCounters, AnalyticsListener analyticsListener) {
        analyticsListener.onVideoEnabled(eventTime, decoderCounters);
        analyticsListener.onDecoderEnabled(eventTime, 2, decoderCounters);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onVideoInputFormatChanged$15(AnalyticsListener.EventTime eventTime, Format format, DecoderReuseEvaluation decoderReuseEvaluation, AnalyticsListener analyticsListener) {
        analyticsListener.onVideoInputFormatChanged(eventTime, format);
        analyticsListener.onVideoInputFormatChanged(eventTime, format, decoderReuseEvaluation);
        analyticsListener.onDecoderInputFormatChanged(eventTime, 2, format);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onVideoSizeChanged$57(AnalyticsListener.EventTime eventTime, VideoSize videoSize, AnalyticsListener analyticsListener) {
        analyticsListener.onVideoSizeChanged(eventTime, videoSize);
        analyticsListener.onVideoSizeChanged(eventTime, videoSize.width, videoSize.height, videoSize.unappliedRotationDegrees, videoSize.pixelWidthHeightRatio);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setPlayer$1(Player player2, AnalyticsListener analyticsListener, FlagSet flagSet) {
        analyticsListener.onEvents(player2, new AnalyticsListener.Events(flagSet, this.eventTimes));
    }

    /* access modifiers changed from: private */
    public void releaseInternal() {
        AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(generateCurrentPlayerMediaPeriodEventTime, AnalyticsListener.EVENT_PLAYER_RELEASED, new e(generateCurrentPlayerMediaPeriodEventTime, 5));
        this.listeners.release();
    }

    @CallSuper
    public void addListener(AnalyticsListener analyticsListener) {
        Assertions.checkNotNull(analyticsListener);
        this.listeners.add(analyticsListener);
    }

    public final AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime() {
        return generateEventTime(this.mediaPeriodQueueTracker.getCurrentPlayerMediaPeriod());
    }

    @RequiresNonNull({"player"})
    public final AnalyticsListener.EventTime generateEventTime(Timeline timeline, int i3, @Nullable MediaSource.MediaPeriodId mediaPeriodId) {
        long j2;
        Timeline timeline2 = timeline;
        int i4 = i3;
        MediaSource.MediaPeriodId mediaPeriodId2 = timeline.isEmpty() ? null : mediaPeriodId;
        long elapsedRealtime = this.clock.elapsedRealtime();
        boolean z2 = timeline2.equals(this.player.getCurrentTimeline()) && i4 == this.player.getCurrentMediaItemIndex();
        long j3 = 0;
        if (mediaPeriodId2 == null || !mediaPeriodId2.isAd()) {
            if (z2) {
                j2 = this.player.getContentPosition();
                return new AnalyticsListener.EventTime(elapsedRealtime, timeline, i3, mediaPeriodId2, j2, this.player.getCurrentTimeline(), this.player.getCurrentMediaItemIndex(), this.mediaPeriodQueueTracker.getCurrentPlayerMediaPeriod(), this.player.getCurrentPosition(), this.player.getTotalBufferedDuration());
            } else if (!timeline.isEmpty()) {
                j3 = timeline2.getWindow(i4, this.window).getDefaultPositionMs();
            }
        } else if (z2 && this.player.getCurrentAdGroupIndex() == mediaPeriodId2.adGroupIndex && this.player.getCurrentAdIndexInAdGroup() == mediaPeriodId2.adIndexInAdGroup) {
            j3 = this.player.getCurrentPosition();
        }
        j2 = j3;
        return new AnalyticsListener.EventTime(elapsedRealtime, timeline, i3, mediaPeriodId2, j2, this.player.getCurrentTimeline(), this.player.getCurrentMediaItemIndex(), this.mediaPeriodQueueTracker.getCurrentPlayerMediaPeriod(), this.player.getCurrentPosition(), this.player.getTotalBufferedDuration());
    }

    public final void notifySeekStarted() {
        if (!this.isSeeking) {
            AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime = generateCurrentPlayerMediaPeriodEventTime();
            this.isSeeking = true;
            sendEvent(generateCurrentPlayerMediaPeriodEventTime, -1, new e(generateCurrentPlayerMediaPeriodEventTime, 2));
        }
    }

    public final void onAudioAttributesChanged(AudioAttributes audioAttributes) {
        AnalyticsListener.EventTime generateReadingMediaPeriodEventTime = generateReadingMediaPeriodEventTime();
        sendEvent(generateReadingMediaPeriodEventTime, 20, new d(generateReadingMediaPeriodEventTime, audioAttributes, 15));
    }

    public final void onAudioCodecError(Exception exc) {
        AnalyticsListener.EventTime generateReadingMediaPeriodEventTime = generateReadingMediaPeriodEventTime();
        sendEvent(generateReadingMediaPeriodEventTime, AnalyticsListener.EVENT_AUDIO_CODEC_ERROR, new g(generateReadingMediaPeriodEventTime, exc, 3));
    }

    public final void onAudioDecoderInitialized(String str, long j2, long j3) {
        AnalyticsListener.EventTime generateReadingMediaPeriodEventTime = generateReadingMediaPeriodEventTime();
        sendEvent(generateReadingMediaPeriodEventTime, 1008, new s(generateReadingMediaPeriodEventTime, str, j3, j2, 0));
    }

    public final void onAudioDecoderReleased(String str) {
        AnalyticsListener.EventTime generateReadingMediaPeriodEventTime = generateReadingMediaPeriodEventTime();
        sendEvent(generateReadingMediaPeriodEventTime, 1012, new m(generateReadingMediaPeriodEventTime, str, 0));
    }

    public final void onAudioDisabled(DecoderCounters decoderCounters) {
        AnalyticsListener.EventTime generatePlayingMediaPeriodEventTime = generatePlayingMediaPeriodEventTime();
        sendEvent(generatePlayingMediaPeriodEventTime, 1013, new a(generatePlayingMediaPeriodEventTime, 3, decoderCounters));
    }

    public final void onAudioEnabled(DecoderCounters decoderCounters) {
        AnalyticsListener.EventTime generateReadingMediaPeriodEventTime = generateReadingMediaPeriodEventTime();
        sendEvent(generateReadingMediaPeriodEventTime, 1007, new a(generateReadingMediaPeriodEventTime, 0, decoderCounters));
    }

    public final void onAudioInputFormatChanged(Format format, @Nullable DecoderReuseEvaluation decoderReuseEvaluation) {
        AnalyticsListener.EventTime generateReadingMediaPeriodEventTime = generateReadingMediaPeriodEventTime();
        sendEvent(generateReadingMediaPeriodEventTime, 1009, new b(generateReadingMediaPeriodEventTime, format, decoderReuseEvaluation, 1));
    }

    public final void onAudioPositionAdvancing(long j2) {
        AnalyticsListener.EventTime generateReadingMediaPeriodEventTime = generateReadingMediaPeriodEventTime();
        sendEvent(generateReadingMediaPeriodEventTime, 1010, new j(generateReadingMediaPeriodEventTime, j2, 2));
    }

    public final void onAudioSessionIdChanged(int i3) {
        AnalyticsListener.EventTime generateReadingMediaPeriodEventTime = generateReadingMediaPeriodEventTime();
        sendEvent(generateReadingMediaPeriodEventTime, 21, new h(generateReadingMediaPeriodEventTime, i3, 5));
    }

    public final void onAudioSinkError(Exception exc) {
        AnalyticsListener.EventTime generateReadingMediaPeriodEventTime = generateReadingMediaPeriodEventTime();
        sendEvent(generateReadingMediaPeriodEventTime, 1014, new g(generateReadingMediaPeriodEventTime, exc, 2));
    }

    public final void onAudioUnderrun(int i3, long j2, long j3) {
        AnalyticsListener.EventTime generateReadingMediaPeriodEventTime = generateReadingMediaPeriodEventTime();
        sendEvent(generateReadingMediaPeriodEventTime, 1011, new k(generateReadingMediaPeriodEventTime, i3, j2, j3, 1));
    }

    public void onAvailableCommandsChanged(Player.Commands commands) {
        AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(generateCurrentPlayerMediaPeriodEventTime, 13, new d(generateCurrentPlayerMediaPeriodEventTime, commands, 11));
    }

    public final void onBandwidthSample(int i3, long j2, long j3) {
        AnalyticsListener.EventTime generateLoadingMediaPeriodEventTime = generateLoadingMediaPeriodEventTime();
        sendEvent(generateLoadingMediaPeriodEventTime, 1006, new k(generateLoadingMediaPeriodEventTime, i3, j2, j3, 0));
    }

    public void onCues(List<Cue> list) {
        AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(generateCurrentPlayerMediaPeriodEventTime, 27, new d(generateCurrentPlayerMediaPeriodEventTime, list, 10));
    }

    public void onDeviceInfoChanged(DeviceInfo deviceInfo) {
        AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(generateCurrentPlayerMediaPeriodEventTime, 29, new d(generateCurrentPlayerMediaPeriodEventTime, deviceInfo, 20));
    }

    public void onDeviceVolumeChanged(int i3, boolean z2) {
        AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(generateCurrentPlayerMediaPeriodEventTime, 30, new f(generateCurrentPlayerMediaPeriodEventTime, i3, z2));
    }

    public final void onDownstreamFormatChanged(int i3, @Nullable MediaSource.MediaPeriodId mediaPeriodId, MediaLoadData mediaLoadData) {
        AnalyticsListener.EventTime generateMediaPeriodEventTime = generateMediaPeriodEventTime(i3, mediaPeriodId);
        sendEvent(generateMediaPeriodEventTime, 1004, new d(generateMediaPeriodEventTime, mediaLoadData, 0));
    }

    public final void onDrmKeysLoaded(int i3, @Nullable MediaSource.MediaPeriodId mediaPeriodId) {
        AnalyticsListener.EventTime generateMediaPeriodEventTime = generateMediaPeriodEventTime(i3, mediaPeriodId);
        sendEvent(generateMediaPeriodEventTime, 1023, new e(generateMediaPeriodEventTime, 6));
    }

    public final void onDrmKeysRemoved(int i3, @Nullable MediaSource.MediaPeriodId mediaPeriodId) {
        AnalyticsListener.EventTime generateMediaPeriodEventTime = generateMediaPeriodEventTime(i3, mediaPeriodId);
        sendEvent(generateMediaPeriodEventTime, AnalyticsListener.EVENT_DRM_KEYS_REMOVED, new e(generateMediaPeriodEventTime, 3));
    }

    public final void onDrmKeysRestored(int i3, @Nullable MediaSource.MediaPeriodId mediaPeriodId) {
        AnalyticsListener.EventTime generateMediaPeriodEventTime = generateMediaPeriodEventTime(i3, mediaPeriodId);
        sendEvent(generateMediaPeriodEventTime, 1025, new e(generateMediaPeriodEventTime, 4));
    }

    public final void onDrmSessionAcquired(int i3, @Nullable MediaSource.MediaPeriodId mediaPeriodId, int i4) {
        AnalyticsListener.EventTime generateMediaPeriodEventTime = generateMediaPeriodEventTime(i3, mediaPeriodId);
        sendEvent(generateMediaPeriodEventTime, AnalyticsListener.EVENT_DRM_SESSION_ACQUIRED, new h(generateMediaPeriodEventTime, i4, 0));
    }

    public final void onDrmSessionManagerError(int i3, @Nullable MediaSource.MediaPeriodId mediaPeriodId, Exception exc) {
        AnalyticsListener.EventTime generateMediaPeriodEventTime = generateMediaPeriodEventTime(i3, mediaPeriodId);
        sendEvent(generateMediaPeriodEventTime, 1024, new g(generateMediaPeriodEventTime, exc, 0));
    }

    public final void onDrmSessionReleased(int i3, @Nullable MediaSource.MediaPeriodId mediaPeriodId) {
        AnalyticsListener.EventTime generateMediaPeriodEventTime = generateMediaPeriodEventTime(i3, mediaPeriodId);
        sendEvent(generateMediaPeriodEventTime, AnalyticsListener.EVENT_DRM_SESSION_RELEASED, new e(generateMediaPeriodEventTime, 0));
    }

    public final void onDroppedFrames(int i3, long j2) {
        AnalyticsListener.EventTime generatePlayingMediaPeriodEventTime = generatePlayingMediaPeriodEventTime();
        sendEvent(generatePlayingMediaPeriodEventTime, 1018, new i(generatePlayingMediaPeriodEventTime, i3, j2));
    }

    public void onEvents(Player player2, Player.Events events) {
    }

    public final void onIsLoadingChanged(boolean z2) {
        AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(generateCurrentPlayerMediaPeriodEventTime, 3, new n(generateCurrentPlayerMediaPeriodEventTime, z2, 1));
    }

    public void onIsPlayingChanged(boolean z2) {
        AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(generateCurrentPlayerMediaPeriodEventTime, 7, new n(generateCurrentPlayerMediaPeriodEventTime, z2, 0));
    }

    public final void onLoadCanceled(int i3, @Nullable MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
        AnalyticsListener.EventTime generateMediaPeriodEventTime = generateMediaPeriodEventTime(i3, mediaPeriodId);
        sendEvent(generateMediaPeriodEventTime, 1002, new q(generateMediaPeriodEventTime, loadEventInfo, mediaLoadData, 0));
    }

    public final void onLoadCompleted(int i3, @Nullable MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
        AnalyticsListener.EventTime generateMediaPeriodEventTime = generateMediaPeriodEventTime(i3, mediaPeriodId);
        sendEvent(generateMediaPeriodEventTime, 1001, new q(generateMediaPeriodEventTime, loadEventInfo, mediaLoadData, 2));
    }

    public final void onLoadError(int i3, @Nullable MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z2) {
        AnalyticsListener.EventTime generateMediaPeriodEventTime = generateMediaPeriodEventTime(i3, mediaPeriodId);
        sendEvent(generateMediaPeriodEventTime, 1003, new p(generateMediaPeriodEventTime, loadEventInfo, mediaLoadData, iOException, z2));
    }

    public final void onLoadStarted(int i3, @Nullable MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
        AnalyticsListener.EventTime generateMediaPeriodEventTime = generateMediaPeriodEventTime(i3, mediaPeriodId);
        sendEvent(generateMediaPeriodEventTime, 1000, new q(generateMediaPeriodEventTime, loadEventInfo, mediaLoadData, 1));
    }

    public void onLoadingChanged(boolean z2) {
    }

    public void onMaxSeekToPreviousPositionChanged(long j2) {
        AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(generateCurrentPlayerMediaPeriodEventTime, 18, new j(generateCurrentPlayerMediaPeriodEventTime, j2, 1));
    }

    public final void onMediaItemTransition(@Nullable MediaItem mediaItem, int i3) {
        AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(generateCurrentPlayerMediaPeriodEventTime, 1, new t(generateCurrentPlayerMediaPeriodEventTime, mediaItem, i3));
    }

    public void onMediaMetadataChanged(MediaMetadata mediaMetadata) {
        AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(generateCurrentPlayerMediaPeriodEventTime, 14, new r(generateCurrentPlayerMediaPeriodEventTime, mediaMetadata, 1));
    }

    public final void onMetadata(Metadata metadata) {
        AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(generateCurrentPlayerMediaPeriodEventTime, 28, new d(generateCurrentPlayerMediaPeriodEventTime, metadata, 19));
    }

    public final void onPlayWhenReadyChanged(boolean z2, int i3) {
        AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(generateCurrentPlayerMediaPeriodEventTime, 5, new f(generateCurrentPlayerMediaPeriodEventTime, z2, i3, 2));
    }

    public final void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
        AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(generateCurrentPlayerMediaPeriodEventTime, 12, new d(generateCurrentPlayerMediaPeriodEventTime, playbackParameters, 16));
    }

    public final void onPlaybackStateChanged(int i3) {
        AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(generateCurrentPlayerMediaPeriodEventTime, 4, new h(generateCurrentPlayerMediaPeriodEventTime, i3, 4));
    }

    public final void onPlaybackSuppressionReasonChanged(int i3) {
        AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(generateCurrentPlayerMediaPeriodEventTime, 6, new h(generateCurrentPlayerMediaPeriodEventTime, i3, 1));
    }

    public final void onPlayerError(PlaybackException playbackException) {
        AnalyticsListener.EventTime eventTimeForErrorEvent = getEventTimeForErrorEvent(playbackException);
        sendEvent(eventTimeForErrorEvent, 10, new c(eventTimeForErrorEvent, playbackException, 1));
    }

    public void onPlayerErrorChanged(@Nullable PlaybackException playbackException) {
        AnalyticsListener.EventTime eventTimeForErrorEvent = getEventTimeForErrorEvent(playbackException);
        sendEvent(eventTimeForErrorEvent, 10, new c(eventTimeForErrorEvent, playbackException, 0));
    }

    public final void onPlayerStateChanged(boolean z2, int i3) {
        AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(generateCurrentPlayerMediaPeriodEventTime, -1, new f(generateCurrentPlayerMediaPeriodEventTime, z2, i3, 1));
    }

    public void onPlaylistMetadataChanged(MediaMetadata mediaMetadata) {
        AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(generateCurrentPlayerMediaPeriodEventTime, 15, new r(generateCurrentPlayerMediaPeriodEventTime, mediaMetadata, 0));
    }

    public void onPositionDiscontinuity(int i3) {
    }

    public void onRenderedFirstFrame() {
    }

    public final void onRepeatModeChanged(int i3) {
        AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(generateCurrentPlayerMediaPeriodEventTime, 8, new h(generateCurrentPlayerMediaPeriodEventTime, i3, 3));
    }

    public void onSeekBackIncrementChanged(long j2) {
        AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(generateCurrentPlayerMediaPeriodEventTime, 16, new j(generateCurrentPlayerMediaPeriodEventTime, j2, 3));
    }

    public void onSeekForwardIncrementChanged(long j2) {
        AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(generateCurrentPlayerMediaPeriodEventTime, 17, new j(generateCurrentPlayerMediaPeriodEventTime, j2, 0));
    }

    public final void onSeekProcessed() {
        AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(generateCurrentPlayerMediaPeriodEventTime, -1, new e(generateCurrentPlayerMediaPeriodEventTime, 1));
    }

    public final void onShuffleModeEnabledChanged(boolean z2) {
        AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(generateCurrentPlayerMediaPeriodEventTime, 9, new n(generateCurrentPlayerMediaPeriodEventTime, z2, 3));
    }

    public final void onSkipSilenceEnabledChanged(boolean z2) {
        AnalyticsListener.EventTime generateReadingMediaPeriodEventTime = generateReadingMediaPeriodEventTime();
        sendEvent(generateReadingMediaPeriodEventTime, 23, new n(generateReadingMediaPeriodEventTime, z2, 2));
    }

    public final void onSurfaceSizeChanged(int i3, int i4) {
        AnalyticsListener.EventTime generateReadingMediaPeriodEventTime = generateReadingMediaPeriodEventTime();
        sendEvent(generateReadingMediaPeriodEventTime, 24, new c(generateReadingMediaPeriodEventTime, i3, i4));
    }

    public final void onTimelineChanged(Timeline timeline, int i3) {
        this.mediaPeriodQueueTracker.onTimelineChanged((Player) Assertions.checkNotNull(this.player));
        AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(generateCurrentPlayerMediaPeriodEventTime, 0, new h(generateCurrentPlayerMediaPeriodEventTime, i3, 2));
    }

    public void onTrackSelectionParametersChanged(TrackSelectionParameters trackSelectionParameters) {
        AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(generateCurrentPlayerMediaPeriodEventTime, 19, new d(generateCurrentPlayerMediaPeriodEventTime, trackSelectionParameters, 13));
    }

    public void onTracksChanged(Tracks tracks) {
        AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(generateCurrentPlayerMediaPeriodEventTime, 2, new d(generateCurrentPlayerMediaPeriodEventTime, tracks, 12));
    }

    public final void onUpstreamDiscarded(int i3, @Nullable MediaSource.MediaPeriodId mediaPeriodId, MediaLoadData mediaLoadData) {
        AnalyticsListener.EventTime generateMediaPeriodEventTime = generateMediaPeriodEventTime(i3, mediaPeriodId);
        sendEvent(generateMediaPeriodEventTime, 1005, new d(generateMediaPeriodEventTime, mediaLoadData, 1));
    }

    public final void onVideoCodecError(Exception exc) {
        AnalyticsListener.EventTime generateReadingMediaPeriodEventTime = generateReadingMediaPeriodEventTime();
        sendEvent(generateReadingMediaPeriodEventTime, AnalyticsListener.EVENT_VIDEO_CODEC_ERROR, new g(generateReadingMediaPeriodEventTime, exc, 1));
    }

    public final void onVideoDecoderInitialized(String str, long j2, long j3) {
        AnalyticsListener.EventTime generateReadingMediaPeriodEventTime = generateReadingMediaPeriodEventTime();
        sendEvent(generateReadingMediaPeriodEventTime, 1016, new s(generateReadingMediaPeriodEventTime, str, j3, j2, 1));
    }

    public final void onVideoDecoderReleased(String str) {
        AnalyticsListener.EventTime generateReadingMediaPeriodEventTime = generateReadingMediaPeriodEventTime();
        sendEvent(generateReadingMediaPeriodEventTime, 1019, new m(generateReadingMediaPeriodEventTime, str, 1));
    }

    public final void onVideoDisabled(DecoderCounters decoderCounters) {
        AnalyticsListener.EventTime generatePlayingMediaPeriodEventTime = generatePlayingMediaPeriodEventTime();
        sendEvent(generatePlayingMediaPeriodEventTime, 1020, new a(generatePlayingMediaPeriodEventTime, 1, decoderCounters));
    }

    public final void onVideoEnabled(DecoderCounters decoderCounters) {
        AnalyticsListener.EventTime generateReadingMediaPeriodEventTime = generateReadingMediaPeriodEventTime();
        sendEvent(generateReadingMediaPeriodEventTime, 1015, new a(generateReadingMediaPeriodEventTime, 2, decoderCounters));
    }

    public final void onVideoFrameProcessingOffset(long j2, int i3) {
        AnalyticsListener.EventTime generatePlayingMediaPeriodEventTime = generatePlayingMediaPeriodEventTime();
        sendEvent(generatePlayingMediaPeriodEventTime, 1021, new i(generatePlayingMediaPeriodEventTime, j2, i3));
    }

    public final void onVideoInputFormatChanged(Format format, @Nullable DecoderReuseEvaluation decoderReuseEvaluation) {
        AnalyticsListener.EventTime generateReadingMediaPeriodEventTime = generateReadingMediaPeriodEventTime();
        sendEvent(generateReadingMediaPeriodEventTime, 1017, new b(generateReadingMediaPeriodEventTime, format, decoderReuseEvaluation, 0));
    }

    public final void onVideoSizeChanged(VideoSize videoSize) {
        AnalyticsListener.EventTime generateReadingMediaPeriodEventTime = generateReadingMediaPeriodEventTime();
        sendEvent(generateReadingMediaPeriodEventTime, 25, new d(generateReadingMediaPeriodEventTime, videoSize, 18));
    }

    public final void onVolumeChanged(float f2) {
        AnalyticsListener.EventTime generateReadingMediaPeriodEventTime = generateReadingMediaPeriodEventTime();
        sendEvent(generateReadingMediaPeriodEventTime, 22, new o(generateReadingMediaPeriodEventTime, f2));
    }

    @CallSuper
    public void release() {
        ((HandlerWrapper) Assertions.checkStateNotNull(this.handler)).post(new C0.d(this, 26));
    }

    @CallSuper
    public void removeListener(AnalyticsListener analyticsListener) {
        this.listeners.remove(analyticsListener);
    }

    public final void sendEvent(AnalyticsListener.EventTime eventTime, int i3, ListenerSet.Event<AnalyticsListener> event) {
        this.eventTimes.put(i3, eventTime);
        this.listeners.sendEvent(i3, event);
    }

    @CallSuper
    public void setPlayer(Player player2, Looper looper) {
        Assertions.checkState(this.player == null || this.mediaPeriodQueueTracker.mediaPeriodQueue.isEmpty());
        this.player = (Player) Assertions.checkNotNull(player2);
        this.handler = this.clock.createHandler(looper, (Handler.Callback) null);
        this.listeners = this.listeners.copy(looper, new d(this, player2, 17));
    }

    public final void updateMediaPeriodQueueInfo(List<MediaSource.MediaPeriodId> list, @Nullable MediaSource.MediaPeriodId mediaPeriodId) {
        this.mediaPeriodQueueTracker.onQueueUpdated(list, mediaPeriodId, (Player) Assertions.checkNotNull(this.player));
    }

    public final void onPositionDiscontinuity(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i3) {
        if (i3 == 1) {
            this.isSeeking = false;
        }
        this.mediaPeriodQueueTracker.onPositionDiscontinuity((Player) Assertions.checkNotNull(this.player));
        AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(generateCurrentPlayerMediaPeriodEventTime, 11, new l(generateCurrentPlayerMediaPeriodEventTime, positionInfo, positionInfo2, i3));
    }

    public final void onRenderedFirstFrame(Object obj, long j2) {
        AnalyticsListener.EventTime generateReadingMediaPeriodEventTime = generateReadingMediaPeriodEventTime();
        sendEvent(generateReadingMediaPeriodEventTime, 26, new androidx.camera.core.impl.utils.futures.a((Object) generateReadingMediaPeriodEventTime, obj, j2));
    }

    public void onCues(CueGroup cueGroup) {
        AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(generateCurrentPlayerMediaPeriodEventTime, 27, new d(generateCurrentPlayerMediaPeriodEventTime, cueGroup, 14));
    }

    private AnalyticsListener.EventTime generateEventTime(@Nullable MediaSource.MediaPeriodId mediaPeriodId) {
        Timeline timeline;
        Assertions.checkNotNull(this.player);
        if (mediaPeriodId == null) {
            timeline = null;
        } else {
            timeline = this.mediaPeriodQueueTracker.getMediaPeriodIdTimeline(mediaPeriodId);
        }
        if (mediaPeriodId != null && timeline != null) {
            return generateEventTime(timeline, timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period).windowIndex, mediaPeriodId);
        }
        int currentMediaItemIndex = this.player.getCurrentMediaItemIndex();
        Timeline currentTimeline = this.player.getCurrentTimeline();
        if (currentMediaItemIndex >= currentTimeline.getWindowCount()) {
            currentTimeline = Timeline.EMPTY;
        }
        return generateEventTime(currentTimeline, currentMediaItemIndex, (MediaSource.MediaPeriodId) null);
    }
}
