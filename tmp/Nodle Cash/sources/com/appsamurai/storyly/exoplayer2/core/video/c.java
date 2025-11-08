package com.appsamurai.storyly.exoplayer2.core.video;

import com.appsamurai.storyly.exoplayer2.core.video.VideoRendererEventListener;

public final /* synthetic */ class c implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ VideoRendererEventListener.EventDispatcher f4601a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f4602b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ long f4603c;

    public /* synthetic */ c(VideoRendererEventListener.EventDispatcher eventDispatcher, Object obj, long j2) {
        this.f4601a = eventDispatcher;
        this.f4602b = obj;
        this.f4603c = j2;
    }

    public final void run() {
        this.f4601a.lambda$renderedFirstFrame$6(this.f4602b, this.f4603c);
    }
}
