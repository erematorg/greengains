package com.google.android.play.integrity.internal;

import com.google.android.gms.tasks.TaskCompletionSource;

final class u extends r {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ TaskCompletionSource f6842a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ r f6843b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ ac f6844c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public u(ac acVar, TaskCompletionSource taskCompletionSource, TaskCompletionSource taskCompletionSource2, r rVar) {
        super(taskCompletionSource);
        this.f6844c = acVar;
        this.f6842a = taskCompletionSource2;
        this.f6843b = rVar;
    }

    public final void b() {
        synchronized (this.f6844c.f6819g) {
            try {
                ac.o(this.f6844c, this.f6842a);
                if (this.f6844c.f6825m.getAndIncrement() > 0) {
                    this.f6844c.f6815c.c("Already connected to the service.", new Object[0]);
                }
                ac.q(this.f6844c, this.f6843b);
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
