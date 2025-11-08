package com.appsamurai.storyly.exoplayer2.core;

import com.appsamurai.storyly.exoplayer2.common.Player;
import com.appsamurai.storyly.exoplayer2.common.util.ListenerSet;

public final /* synthetic */ class n implements ListenerSet.Event {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4521a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ boolean f4522b;

    public /* synthetic */ n(boolean z2, int i3) {
        this.f4521a = i3;
        this.f4522b = z2;
    }

    public final void invoke(Object obj) {
        int i3 = this.f4521a;
        boolean z2 = this.f4522b;
        Player.Listener listener = (Player.Listener) obj;
        switch (i3) {
            case 0:
                listener.onSkipSilenceEnabledChanged(z2);
                return;
            case 1:
                listener.onShuffleModeEnabledChanged(z2);
                return;
            default:
                listener.onSkipSilenceEnabledChanged(z2);
                return;
        }
    }
}
