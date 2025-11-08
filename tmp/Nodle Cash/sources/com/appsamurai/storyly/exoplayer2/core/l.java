package com.appsamurai.storyly.exoplayer2.core;

import com.appsamurai.storyly.exoplayer2.common.Player;
import com.appsamurai.storyly.exoplayer2.common.util.ListenerSet;

public final /* synthetic */ class l implements ListenerSet.Event {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4508a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ PlaybackInfo f4509b;

    public /* synthetic */ l(PlaybackInfo playbackInfo, int i3) {
        this.f4508a = i3;
        this.f4509b = playbackInfo;
    }

    public final void invoke(Object obj) {
        int i3 = this.f4508a;
        PlaybackInfo playbackInfo = this.f4509b;
        Player.Listener listener = (Player.Listener) obj;
        switch (i3) {
            case 0:
                listener.onPlayerErrorChanged(playbackInfo.playbackError);
                return;
            case 1:
                listener.onPlayerError(playbackInfo.playbackError);
                return;
            case 2:
                listener.onTracksChanged(playbackInfo.trackSelectorResult.tracks);
                return;
            case 3:
                ExoPlayerImpl.lambda$updatePlaybackInfo$19(playbackInfo, listener);
                return;
            case 4:
                listener.onPlayerStateChanged(playbackInfo.playWhenReady, playbackInfo.playbackState);
                return;
            case 5:
                listener.onPlaybackStateChanged(playbackInfo.playbackState);
                return;
            case 6:
                listener.onPlaybackSuppressionReasonChanged(playbackInfo.playbackSuppressionReason);
                return;
            case 7:
                listener.onIsPlayingChanged(ExoPlayerImpl.isPlaying(playbackInfo));
                return;
            default:
                listener.onPlaybackParametersChanged(playbackInfo.playbackParameters);
                return;
        }
    }
}
