package com.google.firebase.concurrent;

import com.google.firebase.concurrent.DelegatingScheduledFuture;
import java.util.concurrent.Callable;

public final /* synthetic */ class f implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ DelegatingScheduledExecutorService f7078a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Callable f7079b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ DelegatingScheduledFuture.Completer f7080c;

    public /* synthetic */ f(DelegatingScheduledExecutorService delegatingScheduledExecutorService, Callable callable, DelegatingScheduledFuture.Completer completer) {
        this.f7078a = delegatingScheduledExecutorService;
        this.f7079b = callable;
        this.f7080c = completer;
    }

    public final Object call() {
        return this.f7078a.lambda$schedule$4(this.f7079b, this.f7080c);
    }
}
