package com.google.common.util.concurrent;

import com.google.common.util.concurrent.SimpleTimeLimiter;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public final /* synthetic */ class n implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Method f6964a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f6965b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object[] f6966c;

    public /* synthetic */ n(Method method, Object obj, Object[] objArr) {
        this.f6964a = method;
        this.f6965b = obj;
        this.f6966c = objArr;
    }

    public final Object call() {
        return SimpleTimeLimiter.AnonymousClass1.lambda$invoke$0(this.f6964a, this.f6965b, this.f6966c);
    }
}
