package com.airbnb.lottie;

import com.airbnb.lottie.LottieDrawable;

/* renamed from: com.airbnb.lottie.r  reason: case insensitive filesystem */
public final /* synthetic */ class C0215r implements LottieDrawable.LazyCompositionTask {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3394a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ LottieDrawable f3395b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ float f3396c;

    public /* synthetic */ C0215r(LottieDrawable lottieDrawable, float f2, int i3) {
        this.f3394a = i3;
        this.f3395b = lottieDrawable;
        this.f3396c = f2;
    }

    public final void run(LottieComposition lottieComposition) {
        switch (this.f3394a) {
            case 0:
                this.f3395b.lambda$setMaxProgress$8(this.f3396c, lottieComposition);
                return;
            case 1:
                this.f3395b.lambda$setMinProgress$6(this.f3396c, lottieComposition);
                return;
            default:
                this.f3395b.lambda$setProgress$16(this.f3396c, lottieComposition);
                return;
        }
    }
}
