package com.airbnb.lottie;

import android.content.Context;
import com.airbnb.lottie.network.LottieNetworkCacheProvider;
import java.io.File;

public final /* synthetic */ class a implements LottieNetworkCacheProvider {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Context f3348a;

    public /* synthetic */ a(Context context) {
        this.f3348a = context;
    }

    public final File getCacheDir() {
        return L.lambda$networkCache$0(this.f3348a);
    }
}
