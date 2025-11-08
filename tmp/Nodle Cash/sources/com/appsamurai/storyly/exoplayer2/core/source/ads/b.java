package com.appsamurai.storyly.exoplayer2.core.source.ads;

import com.appsamurai.storyly.exoplayer2.core.source.MediaSource;
import com.appsamurai.storyly.exoplayer2.core.source.ads.AdsMediaSource;
import java.io.IOException;

public final /* synthetic */ class b implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AdsMediaSource.AdPrepareListener f4548a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ MediaSource.MediaPeriodId f4549b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ IOException f4550c;

    public /* synthetic */ b(AdsMediaSource.AdPrepareListener adPrepareListener, MediaSource.MediaPeriodId mediaPeriodId, IOException iOException) {
        this.f4548a = adPrepareListener;
        this.f4549b = mediaPeriodId;
        this.f4550c = iOException;
    }

    public final void run() {
        this.f4548a.lambda$onPrepareError$1(this.f4549b, this.f4550c);
    }
}
