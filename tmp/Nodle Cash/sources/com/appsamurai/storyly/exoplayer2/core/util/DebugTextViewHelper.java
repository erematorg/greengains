package com.appsamurai.storyly.exoplayer2.core.util;

import A.a;
import android.annotation.SuppressLint;
import android.os.Looper;
import android.widget.TextView;
import androidx.core.os.EnvironmentCompat;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.Player;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.core.ExoPlayer;
import com.appsamurai.storyly.exoplayer2.core.decoder.DecoderCounters;
import java.util.Locale;
import org.web3j.utils.RevertReasonExtractor;

public class DebugTextViewHelper {
    private static final int REFRESH_INTERVAL_MS = 1000;
    private final ExoPlayer player;
    private boolean started;
    private final TextView textView;
    private final Updater updater;

    public final class Updater implements Player.Listener, Runnable {
        private Updater() {
        }

        public void onPlayWhenReadyChanged(boolean z2, int i3) {
            DebugTextViewHelper.this.updateAndPost();
        }

        public void onPlaybackStateChanged(int i3) {
            DebugTextViewHelper.this.updateAndPost();
        }

        public void onPositionDiscontinuity(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i3) {
            DebugTextViewHelper.this.updateAndPost();
        }

        public void run() {
            DebugTextViewHelper.this.updateAndPost();
        }
    }

    public DebugTextViewHelper(ExoPlayer exoPlayer, TextView textView2) {
        Assertions.checkArgument(exoPlayer.getApplicationLooper() == Looper.getMainLooper());
        this.player = exoPlayer;
        this.textView = textView2;
        this.updater = new Updater();
    }

    private static String getDecoderCountersBufferCountString(DecoderCounters decoderCounters) {
        if (decoderCounters == null) {
            return "";
        }
        decoderCounters.ensureUpdated();
        return " sib:" + decoderCounters.skippedInputBufferCount + " sb:" + decoderCounters.skippedOutputBufferCount + " rb:" + decoderCounters.renderedOutputBufferCount + " db:" + decoderCounters.droppedBufferCount + " mcdb:" + decoderCounters.maxConsecutiveDroppedBufferCount + " dk:" + decoderCounters.droppedToKeyframeCount;
    }

    private static String getPixelAspectRatioString(float f2) {
        return (f2 == -1.0f || f2 == 1.0f) ? "" : " par:".concat(String.format(Locale.US, "%.02f", new Object[]{Float.valueOf(f2)}));
    }

    private static String getVideoFrameProcessingOffsetAverageString(long j2, int i3) {
        return i3 == 0 ? RevertReasonExtractor.MISSING_REASON : String.valueOf((long) (((double) j2) / ((double) i3)));
    }

    public String getAudioString() {
        Format audioFormat = this.player.getAudioFormat();
        DecoderCounters audioDecoderCounters = this.player.getAudioDecoderCounters();
        if (audioFormat == null || audioDecoderCounters == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder("\n");
        sb.append(audioFormat.sampleMimeType);
        sb.append("(id:");
        sb.append(audioFormat.id);
        sb.append(" hz:");
        sb.append(audioFormat.sampleRate);
        sb.append(" ch:");
        sb.append(audioFormat.channelCount);
        return a.n(sb, getDecoderCountersBufferCountString(audioDecoderCounters), ")");
    }

    public String getDebugString() {
        return getPlayerStateString() + getVideoString() + getAudioString();
    }

    public String getPlayerStateString() {
        int playbackState = this.player.getPlaybackState();
        String str = playbackState != 1 ? playbackState != 2 ? playbackState != 3 ? playbackState != 4 ? EnvironmentCompat.MEDIA_UNKNOWN : "ended" : "ready" : "buffering" : "idle";
        boolean playWhenReady = this.player.getPlayWhenReady();
        int currentMediaItemIndex = this.player.getCurrentMediaItemIndex();
        return "playWhenReady:" + playWhenReady + " playbackState:" + str + " item:" + currentMediaItemIndex;
    }

    public String getVideoString() {
        Format videoFormat = this.player.getVideoFormat();
        DecoderCounters videoDecoderCounters = this.player.getVideoDecoderCounters();
        if (videoFormat == null || videoDecoderCounters == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder("\n");
        sb.append(videoFormat.sampleMimeType);
        sb.append("(id:");
        sb.append(videoFormat.id);
        sb.append(" r:");
        sb.append(videoFormat.width);
        sb.append("x");
        sb.append(videoFormat.height);
        sb.append(getPixelAspectRatioString(videoFormat.pixelWidthHeightRatio));
        sb.append(getDecoderCountersBufferCountString(videoDecoderCounters));
        sb.append(" vfpo: ");
        return a.n(sb, getVideoFrameProcessingOffsetAverageString(videoDecoderCounters.totalVideoFrameProcessingOffsetUs, videoDecoderCounters.videoFrameProcessingOffsetCount), ")");
    }

    public final void start() {
        if (!this.started) {
            this.started = true;
            this.player.addListener(this.updater);
            updateAndPost();
        }
    }

    public final void stop() {
        if (this.started) {
            this.started = false;
            this.player.removeListener(this.updater);
            this.textView.removeCallbacks(this.updater);
        }
    }

    @SuppressLint({"SetTextI18n"})
    public final void updateAndPost() {
        this.textView.setText(getDebugString());
        this.textView.removeCallbacks(this.updater);
        this.textView.postDelayed(this.updater, 1000);
    }
}
