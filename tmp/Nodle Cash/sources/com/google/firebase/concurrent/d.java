package com.google.firebase.concurrent;

import com.google.firebase.concurrent.DelegatingScheduledFuture;

public final /* synthetic */ class d implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7068a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ DelegatingScheduledExecutorService f7069b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Runnable f7070c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ DelegatingScheduledFuture.Completer f7071d;

    public /* synthetic */ d(DelegatingScheduledExecutorService delegatingScheduledExecutorService, Runnable runnable, DelegatingScheduledFuture.Completer completer, int i3) {
        this.f7068a = i3;
        this.f7069b = delegatingScheduledExecutorService;
        this.f7070c = runnable;
        this.f7071d = completer;
    }

    public final void run() {
        switch (this.f7068a) {
            case 0:
                this.f7069b.lambda$scheduleWithFixedDelay$10(this.f7070c, this.f7071d);
                return;
            case 1:
                this.f7069b.lambda$scheduleAtFixedRate$7(this.f7070c, this.f7071d);
                return;
            default:
                this.f7069b.lambda$schedule$1(this.f7070c, this.f7071d);
                return;
        }
    }
}
