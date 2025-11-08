package com.google.common.cache;

import com.google.common.cache.LocalCache;
import com.google.common.util.concurrent.ListenableFuture;

public final /* synthetic */ class c implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ LocalCache.Segment f6863a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f6864b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int f6865c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ LocalCache.LoadingValueReference f6866d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ ListenableFuture f6867e;

    public /* synthetic */ c(LocalCache.Segment segment, Object obj, int i3, LocalCache.LoadingValueReference loadingValueReference, ListenableFuture listenableFuture) {
        this.f6863a = segment;
        this.f6864b = obj;
        this.f6865c = i3;
        this.f6866d = loadingValueReference;
        this.f6867e = listenableFuture;
    }

    public final void run() {
        this.f6863a.lambda$loadAsync$0(this.f6864b, this.f6865c, this.f6866d, this.f6867e);
    }
}
