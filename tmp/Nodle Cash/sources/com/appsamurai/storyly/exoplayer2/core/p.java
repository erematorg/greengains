package com.appsamurai.storyly.exoplayer2.core;

import com.appsamurai.storyly.exoplayer2.common.Player;
import com.appsamurai.storyly.exoplayer2.common.util.ListenerSet;

public final /* synthetic */ class p implements ListenerSet.Event {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4533a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ int f4534b;

    public /* synthetic */ p(int i3, int i4) {
        this.f4533a = i3;
        this.f4534b = i4;
    }

    public final void invoke(Object obj) {
        ((Player.Listener) obj).onSurfaceSizeChanged(this.f4533a, this.f4534b);
    }
}
