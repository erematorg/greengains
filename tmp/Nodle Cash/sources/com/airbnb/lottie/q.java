package com.airbnb.lottie;

import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieValueCallback;

public final /* synthetic */ class q implements LottieDrawable.LazyCompositionTask {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ LottieDrawable f3390a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ KeyPath f3391b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f3392c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ LottieValueCallback f3393d;

    public /* synthetic */ q(LottieDrawable lottieDrawable, KeyPath keyPath, Object obj, LottieValueCallback lottieValueCallback) {
        this.f3390a = lottieDrawable;
        this.f3391b = keyPath;
        this.f3392c = obj;
        this.f3393d = lottieValueCallback;
    }

    public final void run(LottieComposition lottieComposition) {
        this.f3390a.lambda$addValueCallback$17(this.f3391b, this.f3392c, this.f3393d, lottieComposition);
    }
}
