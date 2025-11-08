package com.airbnb.lottie;

import android.content.Context;
import java.lang.ref.WeakReference;
import java.util.concurrent.Callable;

public final /* synthetic */ class k implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ WeakReference f3370a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Context f3371b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int f3372c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ String f3373d;

    public /* synthetic */ k(WeakReference weakReference, Context context, int i3, String str) {
        this.f3370a = weakReference;
        this.f3371b = context;
        this.f3372c = i3;
        this.f3373d = str;
    }

    public final Object call() {
        return LottieCompositionFactory.lambda$fromRawRes$2(this.f3370a, this.f3371b, this.f3372c, this.f3373d);
    }
}
