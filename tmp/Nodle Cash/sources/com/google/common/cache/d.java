package com.google.common.cache;

import com.appsamurai.storyly.exoplayer2.core.video.spherical.c;
import java.util.concurrent.Executor;

public final /* synthetic */ class d implements RemovalListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Executor f6868a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ RemovalListener f6869b;

    public /* synthetic */ d(RemovalListener removalListener, Executor executor) {
        this.f6868a = executor;
        this.f6869b = removalListener;
    }

    public final void onRemoval(RemovalNotification removalNotification) {
        this.f6868a.execute(new c(this.f6869b, removalNotification, 2));
    }
}
