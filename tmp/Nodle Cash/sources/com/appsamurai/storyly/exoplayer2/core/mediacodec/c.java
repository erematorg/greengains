package com.appsamurai.storyly.exoplayer2.core.mediacodec;

public final /* synthetic */ class c implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AsynchronousMediaCodecCallback f4517a;

    public /* synthetic */ c(AsynchronousMediaCodecCallback asynchronousMediaCodecCallback) {
        this.f4517a = asynchronousMediaCodecCallback;
    }

    public final void run() {
        this.f4517a.onFlushCompleted();
    }
}
