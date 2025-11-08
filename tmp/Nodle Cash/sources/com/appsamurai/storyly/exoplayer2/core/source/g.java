package com.appsamurai.storyly.exoplayer2.core.source;

import com.appsamurai.storyly.exoplayer2.core.source.MediaSourceEventListener;

public final /* synthetic */ class g implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4562a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ MediaSourceEventListener.EventDispatcher f4563b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ MediaSourceEventListener f4564c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ LoadEventInfo f4565d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ MediaLoadData f4566e;

    public /* synthetic */ g(MediaSourceEventListener.EventDispatcher eventDispatcher, MediaSourceEventListener mediaSourceEventListener, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, int i3) {
        this.f4562a = i3;
        this.f4563b = eventDispatcher;
        this.f4564c = mediaSourceEventListener;
        this.f4565d = loadEventInfo;
        this.f4566e = mediaLoadData;
    }

    public final void run() {
        switch (this.f4562a) {
            case 0:
                this.f4563b.lambda$loadCompleted$1(this.f4564c, this.f4565d, this.f4566e);
                return;
            case 1:
                this.f4563b.lambda$loadStarted$0(this.f4564c, this.f4565d, this.f4566e);
                return;
            default:
                this.f4563b.lambda$loadCanceled$2(this.f4564c, this.f4565d, this.f4566e);
                return;
        }
    }
}
