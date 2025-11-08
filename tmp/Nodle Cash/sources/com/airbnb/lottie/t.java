package com.airbnb.lottie;

import com.airbnb.lottie.LottieDrawable;

public final /* synthetic */ class t implements LottieDrawable.LazyCompositionTask {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3399a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ LottieDrawable f3400b;

    public /* synthetic */ t(LottieDrawable lottieDrawable, int i3) {
        this.f3399a = i3;
        this.f3400b = lottieDrawable;
    }

    public final void run(LottieComposition lottieComposition) {
        int i3 = this.f3399a;
        LottieDrawable lottieDrawable = this.f3400b;
        switch (i3) {
            case 0:
                lottieDrawable.lambda$resumeAnimation$4(lottieComposition);
                return;
            default:
                lottieDrawable.lambda$playAnimation$3(lottieComposition);
                return;
        }
    }
}
