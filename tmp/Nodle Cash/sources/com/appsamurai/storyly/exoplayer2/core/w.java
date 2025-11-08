package com.appsamurai.storyly.exoplayer2.core;

import com.appsamurai.storyly.exoplayer2.common.Player;
import com.appsamurai.storyly.exoplayer2.common.util.ListenerSet;

public final /* synthetic */ class w implements ListenerSet.Event {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4609a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ boolean f4610b;

    public /* synthetic */ w(int i3, boolean z2) {
        this.f4609a = i3;
        this.f4610b = z2;
    }

    public final void invoke(Object obj) {
        ((Player.Listener) obj).onDeviceVolumeChanged(this.f4609a, this.f4610b);
    }
}
