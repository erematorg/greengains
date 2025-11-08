package com.appsamurai.storyly.exoplayer2.core.source;

import com.appsamurai.storyly.exoplayer2.extractor.extractor.SeekMap;

public final /* synthetic */ class i implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ProgressiveMediaPeriod f4573a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ SeekMap f4574b;

    public /* synthetic */ i(ProgressiveMediaPeriod progressiveMediaPeriod, SeekMap seekMap) {
        this.f4573a = progressiveMediaPeriod;
        this.f4574b = seekMap;
    }

    public final void run() {
        this.f4573a.lambda$seekMap$1(this.f4574b);
    }
}
