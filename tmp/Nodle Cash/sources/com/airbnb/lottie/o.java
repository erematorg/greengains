package com.airbnb.lottie;

import com.airbnb.lottie.LottieDrawable;

public final /* synthetic */ class o implements LottieDrawable.LazyCompositionTask {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ LottieDrawable f3384a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ float f3385b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ float f3386c;

    public /* synthetic */ o(LottieDrawable lottieDrawable, float f2, float f3) {
        this.f3384a = lottieDrawable;
        this.f3385b = f2;
        this.f3386c = f3;
    }

    public final void run(LottieComposition lottieComposition) {
        this.f3384a.lambda$setMinAndMaxProgress$14(this.f3385b, this.f3386c, lottieComposition);
    }
}
