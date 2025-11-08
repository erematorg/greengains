package com.appsamurai.storyly.exoplayer2.core;

import com.appsamurai.storyly.exoplayer2.common.Player;
import com.appsamurai.storyly.exoplayer2.common.util.ListenerSet;

public final /* synthetic */ class o implements ListenerSet.Event {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ float f4523a;

    public /* synthetic */ o(float f2) {
        this.f4523a = f2;
    }

    public final void invoke(Object obj) {
        ((Player.Listener) obj).onVolumeChanged(this.f4523a);
    }
}
