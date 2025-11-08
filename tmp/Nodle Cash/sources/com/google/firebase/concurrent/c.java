package com.google.firebase.concurrent;

import com.google.firebase.concurrent.DelegatingScheduledFuture;

public final /* synthetic */ class c implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7065a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Runnable f7066b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ DelegatingScheduledFuture.Completer f7067c;

    public /* synthetic */ c(Runnable runnable, DelegatingScheduledFuture.Completer completer, int i3) {
        this.f7065a = i3;
        this.f7066b = runnable;
        this.f7067c = completer;
    }

    public final void run() {
        switch (this.f7065a) {
            case 0:
                DelegatingScheduledExecutorService.lambda$scheduleWithFixedDelay$9(this.f7066b, this.f7067c);
                return;
            case 1:
                DelegatingScheduledExecutorService.lambda$schedule$0(this.f7066b, this.f7067c);
                return;
            default:
                DelegatingScheduledExecutorService.lambda$scheduleAtFixedRate$6(this.f7066b, this.f7067c);
                return;
        }
    }
}
