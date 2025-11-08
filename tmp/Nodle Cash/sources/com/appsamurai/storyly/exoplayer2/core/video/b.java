package com.appsamurai.storyly.exoplayer2.core.video;

import com.appsamurai.storyly.exoplayer2.core.video.VideoRendererEventListener;

public final /* synthetic */ class b implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4597a = 1;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ VideoRendererEventListener.EventDispatcher f4598b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int f4599c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ long f4600d;

    public /* synthetic */ b(VideoRendererEventListener.EventDispatcher eventDispatcher, int i3, long j2) {
        this.f4598b = eventDispatcher;
        this.f4599c = i3;
        this.f4600d = j2;
    }

    public final void run() {
        switch (this.f4597a) {
            case 0:
                this.f4598b.lambda$reportVideoFrameProcessingOffset$4(this.f4600d, this.f4599c);
                return;
            default:
                this.f4598b.lambda$droppedFrames$3(this.f4599c, this.f4600d);
                return;
        }
    }

    public /* synthetic */ b(VideoRendererEventListener.EventDispatcher eventDispatcher, long j2, int i3) {
        this.f4598b = eventDispatcher;
        this.f4600d = j2;
        this.f4599c = i3;
    }
}
