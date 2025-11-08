package com.appsamurai.storyly.exoplayer2.core;

import android.os.Bundle;
import com.appsamurai.storyly.exoplayer2.common.Bundleable;
import com.appsamurai.storyly.exoplayer2.common.Player;
import com.appsamurai.storyly.exoplayer2.common.util.ListenerSet;
import com.appsamurai.storyly.exoplayer2.common.util.Log;

public final /* synthetic */ class r implements ListenerSet.Event, Bundleable.Creator {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4537a;

    public /* synthetic */ r(int i3) {
        this.f4537a = i3;
    }

    public static void a(int i3, String str, String str2) {
        Log.w(str2, str + i3);
    }

    public Bundleable fromBundle(Bundle bundle) {
        return ExoPlaybackException.a(bundle);
    }

    public void invoke(Object obj) {
        Player.Listener listener = (Player.Listener) obj;
        switch (this.f4537a) {
            case 0:
                listener.onPlayerError(ExoPlaybackException.createForUnexpected(new ExoTimeoutException(1), 1003));
                return;
            case 3:
                listener.onSeekProcessed();
                return;
            default:
                listener.onRenderedFirstFrame();
                return;
        }
    }
}
