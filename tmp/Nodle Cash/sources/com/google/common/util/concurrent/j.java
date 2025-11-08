package com.google.common.util.concurrent;

import java.util.concurrent.Callable;

public final /* synthetic */ class j implements AsyncCallable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ListeningExecutorService f6955a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Callable f6956b;

    public /* synthetic */ j(ListeningExecutorService listeningExecutorService, Callable callable) {
        this.f6955a = listeningExecutorService;
        this.f6956b = callable;
    }

    public final ListenableFuture call() {
        return this.f6955a.submit(this.f6956b);
    }
}
