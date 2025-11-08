package com.airbnb.lottie;

import com.airbnb.lottie.LottieDrawable;

public final /* synthetic */ class n implements LottieDrawable.LazyCompositionTask {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3381a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ LottieDrawable f3382b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int f3383c;

    public /* synthetic */ n(LottieDrawable lottieDrawable, int i3, int i4) {
        this.f3381a = i4;
        this.f3382b = lottieDrawable;
        this.f3383c = i3;
    }

    public final void run(LottieComposition lottieComposition) {
        switch (this.f3381a) {
            case 0:
                this.f3382b.lambda$setFrame$15(this.f3383c, lottieComposition);
                return;
            case 1:
                this.f3382b.lambda$setMaxFrame$7(this.f3383c, lottieComposition);
                return;
            default:
                this.f3382b.lambda$setMinFrame$5(this.f3383c, lottieComposition);
                return;
        }
    }
}
