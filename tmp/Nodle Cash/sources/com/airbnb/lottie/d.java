package com.airbnb.lottie;

import java.util.concurrent.Callable;

public final /* synthetic */ class d implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ LottieAnimationView f3352a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ int f3353b;

    public /* synthetic */ d(LottieAnimationView lottieAnimationView, int i3) {
        this.f3352a = lottieAnimationView;
        this.f3353b = i3;
    }

    public final Object call() {
        return this.f3352a.lambda$fromRawRes$1(this.f3353b);
    }
}
