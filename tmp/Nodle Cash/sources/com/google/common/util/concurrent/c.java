package com.google.common.util.concurrent;

import com.google.common.util.concurrent.AbstractExecutionThreadService;
import com.google.common.util.concurrent.JdkFutureAdapters;
import java.io.Closeable;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledFuture;

public final /* synthetic */ class c implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6940a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f6941b;

    public /* synthetic */ c(Object obj, int i3) {
        this.f6940a = i3;
        this.f6941b = obj;
    }

    public final void run() {
        int i3 = this.f6940a;
        Object obj = this.f6941b;
        switch (i3) {
            case 0:
                ((AbstractExecutionThreadService.AnonymousClass1) obj).lambda$doStart$1();
                return;
            case 1:
                ((JdkFutureAdapters.ListenableFutureAdapter) obj).lambda$addListener$0();
                return;
            case 2:
                WrappingExecutorService.lambda$wrapTask$0((Callable) obj);
                return;
            case 3:
                ClosingFuture.lambda$closeQuietly$0((Closeable) obj);
                return;
            default:
                ((ScheduledFuture) obj).cancel(false);
                return;
        }
    }
}
