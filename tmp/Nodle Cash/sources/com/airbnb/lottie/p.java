package com.airbnb.lottie;

import com.airbnb.lottie.LottieDrawable;

public final /* synthetic */ class p implements LottieDrawable.LazyCompositionTask {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ LottieDrawable f3387a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ int f3388b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int f3389c;

    public /* synthetic */ p(LottieDrawable lottieDrawable, int i3, int i4) {
        this.f3387a = lottieDrawable;
        this.f3388b = i3;
        this.f3389c = i4;
    }

    public final void run(LottieComposition lottieComposition) {
        this.f3387a.lambda$setMinAndMaxFrame$13(this.f3388b, this.f3389c, lottieComposition);
    }
}
