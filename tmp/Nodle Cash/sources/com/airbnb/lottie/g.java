package com.airbnb.lottie;

import android.content.Context;
import java.util.concurrent.Callable;
import java.util.zip.ZipInputStream;

public final /* synthetic */ class g implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3360a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Context f3361b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ ZipInputStream f3362c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ String f3363d;

    public /* synthetic */ g(Context context, ZipInputStream zipInputStream, String str, int i3) {
        this.f3360a = i3;
        this.f3361b = context;
        this.f3362c = zipInputStream;
        this.f3363d = str;
    }

    public final Object call() {
        switch (this.f3360a) {
            case 0:
                return LottieCompositionFactory.fromZipStreamSync(this.f3361b, this.f3362c, this.f3363d);
            default:
                return LottieCompositionFactory.fromZipStreamSync(this.f3361b, this.f3362c, this.f3363d);
        }
    }
}
