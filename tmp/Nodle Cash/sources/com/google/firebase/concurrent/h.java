package com.google.firebase.concurrent;

import java.util.concurrent.Callable;

public final /* synthetic */ class h implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7082a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Runnable f7083b;

    public /* synthetic */ h(Runnable runnable, int i3) {
        this.f7082a = i3;
        this.f7083b = runnable;
    }

    public final Object call() {
        int i3 = this.f7082a;
        Runnable runnable = this.f7083b;
        switch (i3) {
            case 0:
                return runnable.run();
            default:
                return runnable.run();
        }
    }
}
