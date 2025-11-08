package com.google.firebase.concurrent;

import com.google.firebase.concurrent.DelegatingScheduledFuture;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public final /* synthetic */ class b implements DelegatingScheduledFuture.Resolver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7060a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ DelegatingScheduledExecutorService f7061b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ long f7062c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ TimeUnit f7063d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ Object f7064e;

    public /* synthetic */ b(DelegatingScheduledExecutorService delegatingScheduledExecutorService, Object obj, long j2, TimeUnit timeUnit, int i3) {
        this.f7060a = i3;
        this.f7061b = delegatingScheduledExecutorService;
        this.f7064e = obj;
        this.f7062c = j2;
        this.f7063d = timeUnit;
    }

    public final ScheduledFuture addCompleter(DelegatingScheduledFuture.Completer completer) {
        switch (this.f7060a) {
            case 0:
                return this.f7061b.lambda$schedule$2((Runnable) this.f7064e, this.f7062c, this.f7063d, completer);
            default:
                return this.f7061b.lambda$schedule$5((Callable) this.f7064e, this.f7062c, this.f7063d, completer);
        }
    }
}
