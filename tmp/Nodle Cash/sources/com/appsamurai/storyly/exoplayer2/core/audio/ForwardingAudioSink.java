package com.appsamurai.storyly.exoplayer2.core.audio;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.PlaybackParameters;
import com.appsamurai.storyly.exoplayer2.common.audio.AudioAttributes;
import com.appsamurai.storyly.exoplayer2.common.audio.AuxEffectInfo;
import com.appsamurai.storyly.exoplayer2.core.analytics.PlayerId;
import com.appsamurai.storyly.exoplayer2.core.audio.AudioSink;
import java.nio.ByteBuffer;

public class ForwardingAudioSink implements AudioSink {
    private final AudioSink sink;

    public ForwardingAudioSink(AudioSink audioSink) {
        this.sink = audioSink;
    }

    public void configure(Format format, int i3, @Nullable int[] iArr) throws AudioSink.ConfigurationException {
        this.sink.configure(format, i3, iArr);
    }

    public void disableTunneling() {
        this.sink.disableTunneling();
    }

    public void enableTunnelingV21() {
        this.sink.enableTunnelingV21();
    }

    public void experimentalFlushWithoutAudioTrackRelease() {
        this.sink.experimentalFlushWithoutAudioTrackRelease();
    }

    public void flush() {
        this.sink.flush();
    }

    @Nullable
    public AudioAttributes getAudioAttributes() {
        return this.sink.getAudioAttributes();
    }

    public long getCurrentPositionUs(boolean z2) {
        return this.sink.getCurrentPositionUs(z2);
    }

    public int getFormatSupport(Format format) {
        return this.sink.getFormatSupport(format);
    }

    public PlaybackParameters getPlaybackParameters() {
        return this.sink.getPlaybackParameters();
    }

    public boolean getSkipSilenceEnabled() {
        return this.sink.getSkipSilenceEnabled();
    }

    public boolean handleBuffer(ByteBuffer byteBuffer, long j2, int i3) throws AudioSink.InitializationException, AudioSink.WriteException {
        return this.sink.handleBuffer(byteBuffer, j2, i3);
    }

    public void handleDiscontinuity() {
        this.sink.handleDiscontinuity();
    }

    public boolean hasPendingData() {
        return this.sink.hasPendingData();
    }

    public boolean isEnded() {
        return this.sink.isEnded();
    }

    public void pause() {
        this.sink.pause();
    }

    public void play() {
        this.sink.play();
    }

    public void playToEndOfStream() throws AudioSink.WriteException {
        this.sink.playToEndOfStream();
    }

    public void reset() {
        this.sink.reset();
    }

    public void setAudioAttributes(AudioAttributes audioAttributes) {
        this.sink.setAudioAttributes(audioAttributes);
    }

    public void setAudioSessionId(int i3) {
        this.sink.setAudioSessionId(i3);
    }

    public void setAuxEffectInfo(AuxEffectInfo auxEffectInfo) {
        this.sink.setAuxEffectInfo(auxEffectInfo);
    }

    public void setListener(AudioSink.Listener listener) {
        this.sink.setListener(listener);
    }

    public void setPlaybackParameters(PlaybackParameters playbackParameters) {
        this.sink.setPlaybackParameters(playbackParameters);
    }

    public void setPlayerId(@Nullable PlayerId playerId) {
        this.sink.setPlayerId(playerId);
    }

    public void setSkipSilenceEnabled(boolean z2) {
        this.sink.setSkipSilenceEnabled(z2);
    }

    public void setVolume(float f2) {
        this.sink.setVolume(f2);
    }

    public boolean supportsFormat(Format format) {
        return this.sink.supportsFormat(format);
    }
}
