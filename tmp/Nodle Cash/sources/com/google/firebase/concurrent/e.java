package com.google.firebase.concurrent;

import com.google.firebase.concurrent.DelegatingScheduledFuture;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public final /* synthetic */ class e implements DelegatingScheduledFuture.Resolver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7072a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ DelegatingScheduledExecutorService f7073b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Runnable f7074c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ long f7075d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ long f7076e;

    /* renamed from: f  reason: collision with root package name */
    public final /* synthetic */ TimeUnit f7077f;

    public /* synthetic */ e(DelegatingScheduledExecutorService delegatingScheduledExecutorService, Runnable runnable, long j2, long j3, TimeUnit timeUnit, int i3) {
        this.f7072a = i3;
        this.f7073b = delegatingScheduledExecutorService;
        this.f7074c = runnable;
        this.f7075d = j2;
        this.f7076e = j3;
        this.f7077f = timeUnit;
    }

    public final ScheduledFuture addCompleter(DelegatingScheduledFuture.Completer completer) {
        switch (this.f7072a) {
            case 0:
                return this.f7073b.lambda$scheduleAtFixedRate$8(this.f7074c, this.f7075d, this.f7076e, this.f7077f, completer);
            default:
                return this.f7073b.lambda$scheduleWithFixedDelay$11(this.f7074c, this.f7075d, this.f7076e, this.f7077f, completer);
        }
    }
}
