package com.google.common.cache;

import com.google.common.base.Function;
import com.google.common.cache.LocalCache;

public final /* synthetic */ class b implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ LocalCache.LoadingValueReference f6862a;

    public /* synthetic */ b(LocalCache.LoadingValueReference loadingValueReference) {
        this.f6862a = loadingValueReference;
    }

    public final Object apply(Object obj) {
        return this.f6862a.lambda$loadFuture$0(obj);
    }
}
