package com.airbnb.lottie;

import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.Utils;
import java.io.InputStream;

public final /* synthetic */ class e implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3354a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f3355b;

    public /* synthetic */ e(Object obj, int i3) {
        this.f3354a = i3;
        this.f3355b = obj;
    }

    public final void run() {
        int i3 = this.f3354a;
        Object obj = this.f3355b;
        switch (i3) {
            case 0:
                Utils.closeQuietly((JsonReader) obj);
                return;
            case 1:
                Utils.closeQuietly((InputStream) obj);
                return;
            default:
                ((LottieTask) obj).lambda$notifyListeners$0();
                return;
        }
    }
}
