package com.appsamurai.storyly.exoplayer2.core;

import com.appsamurai.storyly.exoplayer2.core.ExoPlayer;
import com.appsamurai.storyly.exoplayer2.core.upstream.BandwidthMeter;
import com.google.common.base.Supplier;

public final /* synthetic */ class g implements Supplier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4498a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ BandwidthMeter f4499b;

    public /* synthetic */ g(BandwidthMeter bandwidthMeter, int i3) {
        this.f4498a = i3;
        this.f4499b = bandwidthMeter;
    }

    public final Object get() {
        int i3 = this.f4498a;
        BandwidthMeter bandwidthMeter = this.f4499b;
        switch (i3) {
            case 0:
                return ExoPlayer.Builder.lambda$setBandwidthMeter$20(bandwidthMeter);
            default:
                return ExoPlayer.Builder.lambda$new$12(bandwidthMeter);
        }
    }
}
