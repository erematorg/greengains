package com.airbnb.lottie;

import java.io.InputStream;
import java.util.concurrent.Callable;

/* renamed from: com.airbnb.lottie.l  reason: case insensitive filesystem */
public final /* synthetic */ class C0214l implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ InputStream f3374a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ String f3375b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ boolean f3376c;

    public /* synthetic */ C0214l(InputStream inputStream, String str, boolean z2) {
        this.f3374a = inputStream;
        this.f3375b = str;
        this.f3376c = z2;
    }

    public final Object call() {
        return LottieCompositionFactory.fromJsonInputStreamSync(this.f3374a, this.f3375b, this.f3376c);
    }
}
