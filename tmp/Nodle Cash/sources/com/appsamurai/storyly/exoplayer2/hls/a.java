package com.appsamurai.storyly.exoplayer2.hls;

import com.appsamurai.storyly.exoplayer2.hls.HlsSampleStreamWrapper;

public final /* synthetic */ class a implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4624a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f4625b;

    public /* synthetic */ a(Object obj, int i3) {
        this.f4624a = i3;
        this.f4625b = obj;
    }

    public final void run() {
        int i3 = this.f4624a;
        Object obj = this.f4625b;
        switch (i3) {
            case 0:
                ((HlsSampleStreamWrapper) obj).maybeFinishPrepare();
                return;
            case 1:
                ((HlsSampleStreamWrapper) obj).onTracksEnded();
                return;
            default:
                ((HlsSampleStreamWrapper.Callback) obj).onPrepared();
                return;
        }
    }
}
