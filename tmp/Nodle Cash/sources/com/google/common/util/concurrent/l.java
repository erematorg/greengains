package com.google.common.util.concurrent;

import com.google.common.util.concurrent.ExecutionSequencer;

public final /* synthetic */ class l implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ TrustedListenableFutureTask f6958a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ SettableFuture f6959b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ ListenableFuture f6960c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ ListenableFuture f6961d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ ExecutionSequencer.TaskNonReentrantExecutor f6962e;

    public /* synthetic */ l(TrustedListenableFutureTask trustedListenableFutureTask, SettableFuture settableFuture, ListenableFuture listenableFuture, ListenableFuture listenableFuture2, ExecutionSequencer.TaskNonReentrantExecutor taskNonReentrantExecutor) {
        this.f6958a = trustedListenableFutureTask;
        this.f6959b = settableFuture;
        this.f6960c = listenableFuture;
        this.f6961d = listenableFuture2;
        this.f6962e = taskNonReentrantExecutor;
    }

    public final void run() {
        ExecutionSequencer.TaskNonReentrantExecutor taskNonReentrantExecutor = this.f6962e;
        ExecutionSequencer.lambda$submitAsync$0(this.f6958a, this.f6959b, this.f6960c, this.f6961d, taskNonReentrantExecutor);
    }
}
