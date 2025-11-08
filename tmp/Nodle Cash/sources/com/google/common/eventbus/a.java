package com.google.common.eventbus;

public final /* synthetic */ class a implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Subscriber f6898a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f6899b;

    public /* synthetic */ a(Object obj, Subscriber subscriber) {
        this.f6898a = subscriber;
        this.f6899b = obj;
    }

    public final void run() {
        this.f6898a.lambda$dispatchEvent$0(this.f6899b);
    }
}
