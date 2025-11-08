package com.airbnb.lottie;

import com.airbnb.lottie.LottieDrawable;

public final /* synthetic */ class u implements LottieDrawable.LazyCompositionTask {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ LottieDrawable f3401a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ String f3402b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ String f3403c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ boolean f3404d;

    public /* synthetic */ u(LottieDrawable lottieDrawable, String str, String str2, boolean z2) {
        this.f3401a = lottieDrawable;
        this.f3402b = str;
        this.f3403c = str2;
        this.f3404d = z2;
    }

    public final void run(LottieComposition lottieComposition) {
        this.f3401a.lambda$setMinAndMaxFrame$12(this.f3402b, this.f3403c, this.f3404d, lottieComposition);
    }
}
