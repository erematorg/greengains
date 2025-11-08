package com.airbnb.lottie;

import com.airbnb.lottie.utils.Utils;
import java.util.zip.ZipInputStream;

public final /* synthetic */ class h implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3364a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ ZipInputStream f3365b;

    public /* synthetic */ h(ZipInputStream zipInputStream, int i3) {
        this.f3364a = i3;
        this.f3365b = zipInputStream;
    }

    public final void run() {
        int i3 = this.f3364a;
        ZipInputStream zipInputStream = this.f3365b;
        switch (i3) {
            case 0:
                Utils.closeQuietly(zipInputStream);
                return;
            default:
                Utils.closeQuietly(zipInputStream);
                return;
        }
    }
}
