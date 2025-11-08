package com.appsamurai.storyly.exoplayer2.core;

import com.appsamurai.storyly.exoplayer2.common.Player;
import com.appsamurai.storyly.exoplayer2.common.util.ListenerSet;

public final /* synthetic */ class k implements ListenerSet.Event {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4506a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ int f4507b;

    public /* synthetic */ k(int i3, int i4) {
        this.f4506a = i4;
        this.f4507b = i3;
    }

    public final void invoke(Object obj) {
        int i3 = this.f4506a;
        int i4 = this.f4507b;
        Player.Listener listener = (Player.Listener) obj;
        switch (i3) {
            case 0:
                listener.onRepeatModeChanged(i4);
                return;
            default:
                listener.onAudioSessionIdChanged(i4);
                return;
        }
    }
}
