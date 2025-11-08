package com.google.common.util.concurrent;

import com.google.common.base.Supplier;
import java.util.concurrent.Callable;

public final /* synthetic */ class i implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Supplier f6953a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Callable f6954b;

    public /* synthetic */ i(Supplier supplier, Callable callable) {
        this.f6953a = supplier;
        this.f6954b = callable;
    }

    public final Object call() {
        return Callables.lambda$threadRenaming$2(this.f6953a, this.f6954b);
    }
}
