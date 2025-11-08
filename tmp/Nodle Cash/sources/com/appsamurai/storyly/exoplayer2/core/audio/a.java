package com.appsamurai.storyly.exoplayer2.core.audio;

import com.appsamurai.storyly.exoplayer2.core.audio.AudioRendererEventListener;

public final /* synthetic */ class a implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AudioRendererEventListener.EventDispatcher f4461a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ int f4462b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ long f4463c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ long f4464d;

    public /* synthetic */ a(AudioRendererEventListener.EventDispatcher eventDispatcher, int i3, long j2, long j3) {
        this.f4461a = eventDispatcher;
        this.f4462b = i3;
        this.f4463c = j2;
        this.f4464d = j3;
    }

    public final void run() {
        this.f4461a.lambda$underrun$4(this.f4462b, this.f4463c, this.f4464d);
    }
}
