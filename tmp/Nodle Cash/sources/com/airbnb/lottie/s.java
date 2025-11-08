package com.airbnb.lottie;

public final /* synthetic */ class s implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3397a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ LottieDrawable f3398b;

    public /* synthetic */ s(LottieDrawable lottieDrawable, int i3) {
        this.f3397a = i3;
        this.f3398b = lottieDrawable;
    }

    public final void run() {
        int i3 = this.f3397a;
        LottieDrawable lottieDrawable = this.f3398b;
        switch (i3) {
            case 0:
                lottieDrawable.lambda$new$1();
                return;
            default:
                lottieDrawable.lambda$new$2();
                return;
        }
    }
}
