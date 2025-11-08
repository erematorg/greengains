package com.airbnb.lottie;

import java.util.concurrent.atomic.AtomicBoolean;

public final /* synthetic */ class j implements LottieListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3367a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ String f3368b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ AtomicBoolean f3369c;

    public /* synthetic */ j(String str, AtomicBoolean atomicBoolean, int i3) {
        this.f3367a = i3;
        this.f3368b = str;
        this.f3369c = atomicBoolean;
    }

    public final void onResult(Object obj) {
        switch (this.f3367a) {
            case 0:
                LottieCompositionFactory.lambda$cache$16(this.f3368b, this.f3369c, (LottieComposition) obj);
                return;
            default:
                LottieCompositionFactory.lambda$cache$17(this.f3368b, this.f3369c, (Throwable) obj);
                return;
        }
    }
}
