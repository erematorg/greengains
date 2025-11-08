package com.appsamurai.storyly.exoplayer2.core;

import com.appsamurai.storyly.exoplayer2.common.Player;
import com.appsamurai.storyly.exoplayer2.common.util.ListenerSet;

public final /* synthetic */ class u implements ListenerSet.Event {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4585a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Player.PositionInfo f4586b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Player.PositionInfo f4587c;

    public /* synthetic */ u(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i3) {
        this.f4585a = i3;
        this.f4586b = positionInfo;
        this.f4587c = positionInfo2;
    }

    public final void invoke(Object obj) {
        ExoPlayerImpl.lambda$updatePlaybackInfo$13(this.f4585a, this.f4586b, this.f4587c, (Player.Listener) obj);
    }
}
