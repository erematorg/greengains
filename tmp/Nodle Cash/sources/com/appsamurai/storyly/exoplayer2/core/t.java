package com.appsamurai.storyly.exoplayer2.core;

import com.appsamurai.storyly.exoplayer2.common.MediaItem;
import com.appsamurai.storyly.exoplayer2.common.Player;
import com.appsamurai.storyly.exoplayer2.common.util.ListenerSet;

public final /* synthetic */ class t implements ListenerSet.Event {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4579a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f4580b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int f4581c;

    public /* synthetic */ t(Object obj, int i3, int i4) {
        this.f4579a = i4;
        this.f4580b = obj;
        this.f4581c = i3;
    }

    public final void invoke(Object obj) {
        Player.Listener listener = (Player.Listener) obj;
        switch (this.f4579a) {
            case 0:
                listener.onTimelineChanged(((PlaybackInfo) this.f4580b).timeline, this.f4581c);
                return;
            case 1:
                listener.onPlayWhenReadyChanged(((PlaybackInfo) this.f4580b).playWhenReady, this.f4581c);
                return;
            default:
                listener.onMediaItemTransition((MediaItem) this.f4580b, this.f4581c);
                return;
        }
    }
}
