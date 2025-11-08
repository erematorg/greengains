package com.airbnb.lottie;

import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.InputStream;
import java.util.concurrent.Callable;
import org.json.JSONObject;

public final /* synthetic */ class b implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3349a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ String f3350b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f3351c;

    public /* synthetic */ b(int i3, String str, Object obj) {
        this.f3349a = i3;
        this.f3351c = obj;
        this.f3350b = str;
    }

    public final Object call() {
        switch (this.f3349a) {
            case 0:
                return ((LottieAnimationView) this.f3351c).lambda$fromAssets$2(this.f3350b);
            case 1:
                return LottieCompositionFactory.fromJsonReaderSync((JsonReader) this.f3351c, this.f3350b);
            case 2:
                return LottieCompositionFactory.fromJsonInputStreamSync((InputStream) this.f3351c, this.f3350b);
            case 3:
                return LottieCompositionFactory.fromJsonSync((JSONObject) this.f3351c, this.f3350b);
            default:
                return LottieCompositionFactory.fromJsonStringSync(this.f3350b, (String) this.f3351c);
        }
    }

    public /* synthetic */ b(String str, String str2) {
        this.f3349a = 4;
        this.f3350b = str;
        this.f3351c = str2;
    }
}
