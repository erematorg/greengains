package com.airbnb.lottie;

import com.airbnb.lottie.LottieDrawable;

public final /* synthetic */ class m implements LottieDrawable.LazyCompositionTask {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3377a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ LottieDrawable f3378b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ String f3379c;

    public /* synthetic */ m(LottieDrawable lottieDrawable, String str, int i3) {
        this.f3377a = i3;
        this.f3378b = lottieDrawable;
        this.f3379c = str;
    }

    public final void run(LottieComposition lottieComposition) {
        switch (this.f3377a) {
            case 0:
                this.f3378b.lambda$setMinAndMaxFrame$11(this.f3379c, lottieComposition);
                return;
            case 1:
                this.f3378b.lambda$setMaxFrame$10(this.f3379c, lottieComposition);
                return;
            default:
                this.f3378b.lambda$setMinFrame$9(this.f3379c, lottieComposition);
                return;
        }
    }
}
