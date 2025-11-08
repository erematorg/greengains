package com.appsamurai.storyly.exoplayer2.core.upstream;

import com.appsamurai.storyly.exoplayer2.core.upstream.BandwidthMeter;

public final /* synthetic */ class a implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ BandwidthMeter.EventListener.EventDispatcher.HandlerAndListener f4588a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ int f4589b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ long f4590c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ long f4591d;

    public /* synthetic */ a(BandwidthMeter.EventListener.EventDispatcher.HandlerAndListener handlerAndListener, int i3, long j2, long j3) {
        this.f4588a = handlerAndListener;
        this.f4589b = i3;
        this.f4590c = j2;
        this.f4591d = j3;
    }

    public final void run() {
        this.f4588a.listener.onBandwidthSample(this.f4589b, this.f4590c, this.f4591d);
    }
}
