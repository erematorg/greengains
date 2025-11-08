package com.appsamurai.storyly.exoplayer2.core.analytics;

import android.os.Looper;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.Player;
import com.appsamurai.storyly.exoplayer2.core.decoder.DecoderCounters;
import com.appsamurai.storyly.exoplayer2.core.decoder.DecoderReuseEvaluation;
import com.appsamurai.storyly.exoplayer2.core.drm.DrmSessionEventListener;
import com.appsamurai.storyly.exoplayer2.core.source.MediaSource;
import com.appsamurai.storyly.exoplayer2.core.source.MediaSourceEventListener;
import com.appsamurai.storyly.exoplayer2.core.upstream.BandwidthMeter;
import java.util.List;

public interface AnalyticsCollector extends Player.Listener, MediaSourceEventListener, BandwidthMeter.EventListener, DrmSessionEventListener {
    void addListener(AnalyticsListener analyticsListener);

    void notifySeekStarted();

    void onAudioCodecError(Exception exc);

    void onAudioDecoderInitialized(String str, long j2, long j3);

    void onAudioDecoderReleased(String str);

    void onAudioDisabled(DecoderCounters decoderCounters);

    void onAudioEnabled(DecoderCounters decoderCounters);

    void onAudioInputFormatChanged(Format format, @Nullable DecoderReuseEvaluation decoderReuseEvaluation);

    void onAudioPositionAdvancing(long j2);

    void onAudioSinkError(Exception exc);

    void onAudioUnderrun(int i3, long j2, long j3);

    void onDroppedFrames(int i3, long j2);

    void onRenderedFirstFrame(Object obj, long j2);

    void onVideoCodecError(Exception exc);

    void onVideoDecoderInitialized(String str, long j2, long j3);

    void onVideoDecoderReleased(String str);

    void onVideoDisabled(DecoderCounters decoderCounters);

    void onVideoEnabled(DecoderCounters decoderCounters);

    void onVideoFrameProcessingOffset(long j2, int i3);

    void onVideoInputFormatChanged(Format format, @Nullable DecoderReuseEvaluation decoderReuseEvaluation);

    void release();

    void removeListener(AnalyticsListener analyticsListener);

    void setPlayer(Player player, Looper looper);

    void updateMediaPeriodQueueInfo(List<MediaSource.MediaPeriodId> list, @Nullable MediaSource.MediaPeriodId mediaPeriodId);
}
