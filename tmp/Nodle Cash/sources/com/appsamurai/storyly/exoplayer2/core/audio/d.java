package com.appsamurai.storyly.exoplayer2.core.audio;

import com.appsamurai.storyly.exoplayer2.core.audio.AudioRendererEventListener;
import com.appsamurai.storyly.exoplayer2.core.decoder.DecoderCounters;

public final /* synthetic */ class d implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4473a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ AudioRendererEventListener.EventDispatcher f4474b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ DecoderCounters f4475c;

    public /* synthetic */ d(AudioRendererEventListener.EventDispatcher eventDispatcher, DecoderCounters decoderCounters, int i3) {
        this.f4473a = i3;
        this.f4474b = eventDispatcher;
        this.f4475c = decoderCounters;
    }

    public final void run() {
        switch (this.f4473a) {
            case 0:
                this.f4474b.lambda$disabled$6(this.f4475c);
                return;
            default:
                this.f4474b.lambda$enabled$0(this.f4475c);
                return;
        }
    }
}
