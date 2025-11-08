package com.appsamurai.storyly.exoplayer2.core.source.ads;

import com.appsamurai.storyly.exoplayer2.common.source.ads.AdPlaybackState;
import com.appsamurai.storyly.exoplayer2.core.source.MediaSource;
import com.appsamurai.storyly.exoplayer2.core.source.ads.AdsMediaSource;
import com.google.common.collect.ImmutableMap;

public final /* synthetic */ class c implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4551a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f4552b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f4553c;

    public /* synthetic */ c(Object obj, Object obj2, int i3) {
        this.f4551a = i3;
        this.f4552b = obj;
        this.f4553c = obj2;
    }

    public final void run() {
        switch (this.f4551a) {
            case 0:
                ((AdsMediaSource.AdPrepareListener) this.f4552b).lambda$onPrepareComplete$0((MediaSource.MediaPeriodId) this.f4553c);
                return;
            case 1:
                ((AdsMediaSource.ComponentListener) this.f4552b).lambda$onAdPlaybackState$0((AdPlaybackState) this.f4553c);
                return;
            default:
                ((ServerSideAdInsertionMediaSource) this.f4552b).lambda$setAdPlaybackStates$0((ImmutableMap) this.f4553c);
                return;
        }
    }
}
