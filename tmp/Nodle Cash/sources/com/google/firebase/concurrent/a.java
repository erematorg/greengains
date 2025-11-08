package com.google.firebase.concurrent;

import com.google.firebase.concurrent.DelegatingScheduledFuture;
import java.util.concurrent.Callable;

public final /* synthetic */ class a implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7057a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f7058b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f7059c;

    public /* synthetic */ a(Object obj, Object obj2, int i3) {
        this.f7057a = i3;
        this.f7058b = obj;
        this.f7059c = obj2;
    }

    public final void run() {
        switch (this.f7057a) {
            case 0:
                ((CustomThreadFactory) this.f7058b).lambda$newThread$0((Runnable) this.f7059c);
                return;
            case 1:
                DelegatingScheduledExecutorService.lambda$schedule$3((Callable) this.f7058b, (DelegatingScheduledFuture.Completer) this.f7059c);
                return;
            default:
                ((LimitedConcurrencyExecutor) this.f7058b).lambda$decorate$0((Runnable) this.f7059c);
                return;
        }
    }
}
