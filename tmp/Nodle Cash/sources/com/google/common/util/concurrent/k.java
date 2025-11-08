package com.google.common.util.concurrent;

import java.util.concurrent.Callable;

public final /* synthetic */ class k implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Object f6957a;

    public /* synthetic */ k(Object obj) {
        this.f6957a = obj;
    }

    public final Object call() {
        return Callables.lambda$returning$0(this.f6957a);
    }
}
