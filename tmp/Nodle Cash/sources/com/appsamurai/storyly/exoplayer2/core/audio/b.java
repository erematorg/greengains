package com.appsamurai.storyly.exoplayer2.core.audio;

import com.appsamurai.storyly.exoplayer2.core.audio.AudioRendererEventListener;

public final /* synthetic */ class b implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4465a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ AudioRendererEventListener.EventDispatcher f4466b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Exception f4467c;

    public /* synthetic */ b(AudioRendererEventListener.EventDispatcher eventDispatcher, Exception exc, int i3) {
        this.f4465a = i3;
        this.f4466b = eventDispatcher;
        this.f4467c = exc;
    }

    public final void run() {
        switch (this.f4465a) {
            case 0:
                this.f4466b.lambda$audioCodecError$9(this.f4467c);
                return;
            default:
                this.f4466b.lambda$audioSinkError$8(this.f4467c);
                return;
        }
    }
}
