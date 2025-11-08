package com.appsamurai.storyly.exoplayer2.core.source;

import com.appsamurai.storyly.exoplayer2.core.source.MediaSourceEventListener;
import java.io.IOException;

public final /* synthetic */ class h implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MediaSourceEventListener.EventDispatcher f4567a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ MediaSourceEventListener f4568b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ LoadEventInfo f4569c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ MediaLoadData f4570d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ IOException f4571e;

    /* renamed from: f  reason: collision with root package name */
    public final /* synthetic */ boolean f4572f;

    public /* synthetic */ h(MediaSourceEventListener.EventDispatcher eventDispatcher, MediaSourceEventListener mediaSourceEventListener, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z2) {
        this.f4567a = eventDispatcher;
        this.f4568b = mediaSourceEventListener;
        this.f4569c = loadEventInfo;
        this.f4570d = mediaLoadData;
        this.f4571e = iOException;
        this.f4572f = z2;
    }

    public final void run() {
        this.f4567a.lambda$loadError$3(this.f4568b, this.f4569c, this.f4570d, this.f4571e, this.f4572f);
    }
}
