package com.google.firebase.concurrent;

import java.util.concurrent.Callable;

public final /* synthetic */ class i implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7084a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Runnable f7085b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f7086c;

    public /* synthetic */ i(Object obj, int i3, Runnable runnable) {
        this.f7084a = i3;
        this.f7085b = runnable;
        this.f7086c = obj;
    }

    public final Object call() {
        switch (this.f7084a) {
            case 0:
                return this.f7085b.run();
            default:
                return this.f7085b.run();
        }
    }
}
