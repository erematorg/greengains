package com.appsamurai.storyly.exoplayer2.core.video;

import com.appsamurai.storyly.exoplayer2.core.decoder.DecoderCounters;
import com.appsamurai.storyly.exoplayer2.core.video.VideoRendererEventListener;

public final /* synthetic */ class a implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4594a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ VideoRendererEventListener.EventDispatcher f4595b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ DecoderCounters f4596c;

    public /* synthetic */ a(VideoRendererEventListener.EventDispatcher eventDispatcher, DecoderCounters decoderCounters, int i3) {
        this.f4594a = i3;
        this.f4595b = eventDispatcher;
        this.f4596c = decoderCounters;
    }

    public final void run() {
        switch (this.f4594a) {
            case 0:
                this.f4595b.lambda$enabled$0(this.f4596c);
                return;
            default:
                this.f4595b.lambda$disabled$8(this.f4596c);
                return;
        }
    }
}
