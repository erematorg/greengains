package com.airbnb.lottie;

import android.content.Context;
import java.util.concurrent.Callable;

public final /* synthetic */ class f implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3356a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Context f3357b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ String f3358c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ String f3359d;

    public /* synthetic */ f(Context context, int i3, String str, String str2) {
        this.f3356a = i3;
        this.f3357b = context;
        this.f3358c = str;
        this.f3359d = str2;
    }

    public final Object call() {
        switch (this.f3356a) {
            case 0:
                return LottieCompositionFactory.fromAssetSync(this.f3357b, this.f3358c, this.f3359d);
            default:
                return LottieCompositionFactory.lambda$fromUrl$0(this.f3357b, this.f3358c, this.f3359d);
        }
    }
}
