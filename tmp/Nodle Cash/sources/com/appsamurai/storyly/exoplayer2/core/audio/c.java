package com.appsamurai.storyly.exoplayer2.core.audio;

import com.appsamurai.storyly.exoplayer2.core.audio.AudioRendererEventListener;
import com.appsamurai.storyly.exoplayer2.core.video.VideoRendererEventListener;

public final /* synthetic */ class c implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4468a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ String f4469b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ long f4470c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ long f4471d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ Object f4472e;

    public /* synthetic */ c(Object obj, String str, long j2, long j3, int i3) {
        this.f4468a = i3;
        this.f4472e = obj;
        this.f4469b = str;
        this.f4470c = j2;
        this.f4471d = j3;
    }

    public final void run() {
        switch (this.f4468a) {
            case 0:
                ((AudioRendererEventListener.EventDispatcher) this.f4472e).lambda$decoderInitialized$1(this.f4469b, this.f4470c, this.f4471d);
                return;
            default:
                ((VideoRendererEventListener.EventDispatcher) this.f4472e).lambda$decoderInitialized$1(this.f4469b, this.f4470c, this.f4471d);
                return;
        }
    }
}
