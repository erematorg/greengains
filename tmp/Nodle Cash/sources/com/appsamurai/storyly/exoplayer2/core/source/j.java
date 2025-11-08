package com.appsamurai.storyly.exoplayer2.core.source;

public final /* synthetic */ class j implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4575a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ ProgressiveMediaPeriod f4576b;

    public /* synthetic */ j(ProgressiveMediaPeriod progressiveMediaPeriod, int i3) {
        this.f4575a = i3;
        this.f4576b = progressiveMediaPeriod;
    }

    public final void run() {
        int i3 = this.f4575a;
        ProgressiveMediaPeriod progressiveMediaPeriod = this.f4576b;
        switch (i3) {
            case 0:
                progressiveMediaPeriod.maybeFinishPrepare();
                return;
            default:
                progressiveMediaPeriod.lambda$new$0();
                return;
        }
    }
}
