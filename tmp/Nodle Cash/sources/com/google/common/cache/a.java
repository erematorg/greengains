package com.google.common.cache;

import java.util.concurrent.Callable;

public final /* synthetic */ class a implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ CacheLoader f6859a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f6860b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f6861c;

    public /* synthetic */ a(CacheLoader cacheLoader, Object obj, Object obj2) {
        this.f6859a = cacheLoader;
        this.f6860b = obj;
        this.f6861c = obj2;
    }

    public final Object call() {
        return this.f6859a.reload(this.f6860b, this.f6861c).get();
    }
}
