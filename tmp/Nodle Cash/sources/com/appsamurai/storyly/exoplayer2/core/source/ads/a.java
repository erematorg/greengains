package com.appsamurai.storyly.exoplayer2.core.source.ads;

import com.appsamurai.storyly.exoplayer2.core.source.ads.AdsMediaSource;

public final /* synthetic */ class a implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4545a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ AdsMediaSource f4546b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ AdsMediaSource.ComponentListener f4547c;

    public /* synthetic */ a(AdsMediaSource adsMediaSource, AdsMediaSource.ComponentListener componentListener, int i3) {
        this.f4545a = i3;
        this.f4546b = adsMediaSource;
        this.f4547c = componentListener;
    }

    public final void run() {
        switch (this.f4545a) {
            case 0:
                this.f4546b.lambda$prepareSourceInternal$0(this.f4547c);
                return;
            default:
                this.f4546b.lambda$releaseSourceInternal$1(this.f4547c);
                return;
        }
    }
}
