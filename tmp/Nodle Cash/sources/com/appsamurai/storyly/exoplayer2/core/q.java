package com.appsamurai.storyly.exoplayer2.core;

import com.appsamurai.storyly.exoplayer2.common.Player;
import com.appsamurai.storyly.exoplayer2.common.util.FlagSet;
import com.appsamurai.storyly.exoplayer2.common.util.ListenerSet;
import com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal;

public final /* synthetic */ class q implements ListenerSet.Event, ListenerSet.IterationFinishedEvent, ExoPlayerImplInternal.PlaybackInfoUpdateListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4535a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ ExoPlayerImpl f4536b;

    public /* synthetic */ q(ExoPlayerImpl exoPlayerImpl, int i3) {
        this.f4535a = i3;
        this.f4536b = exoPlayerImpl;
    }

    public void invoke(Object obj) {
        int i3 = this.f4535a;
        ExoPlayerImpl exoPlayerImpl = this.f4536b;
        Player.Listener listener = (Player.Listener) obj;
        switch (i3) {
            case 0:
                exoPlayerImpl.lambda$setPlaylistMetadata$7(listener);
                return;
            default:
                exoPlayerImpl.lambda$updateAvailableCommands$26(listener);
                return;
        }
    }

    public void onPlaybackInfoUpdate(ExoPlayerImplInternal.PlaybackInfoUpdate playbackInfoUpdate) {
        this.f4536b.lambda$new$2(playbackInfoUpdate);
    }

    public void invoke(Object obj, FlagSet flagSet) {
        this.f4536b.lambda$new$0((Player.Listener) obj, flagSet);
    }
}
