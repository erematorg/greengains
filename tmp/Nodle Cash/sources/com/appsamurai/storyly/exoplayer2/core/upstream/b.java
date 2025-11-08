package com.appsamurai.storyly.exoplayer2.core.upstream;

import com.appsamurai.storyly.exoplayer2.common.util.NetworkTypeObserver;

public final /* synthetic */ class b implements NetworkTypeObserver.Listener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ DefaultBandwidthMeter f4592a;

    public /* synthetic */ b(DefaultBandwidthMeter defaultBandwidthMeter) {
        this.f4592a = defaultBandwidthMeter;
    }

    public final void onNetworkTypeChanged(int i3) {
        this.f4592a.onNetworkTypeChanged(i3);
    }
}
