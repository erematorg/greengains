package com.google.android.play.core.integrity;

import android.content.Context;
import com.google.android.play.integrity.internal.ae;

final class aj {

    /* renamed from: a  reason: collision with root package name */
    private static s f6737a;

    public static synchronized s a(Context context) {
        s sVar;
        synchronized (aj.class) {
            try {
                if (f6737a == null) {
                    q qVar = new q((p) null);
                    qVar.a(ae.a(context));
                    f6737a = qVar.b();
                }
                sVar = f6737a;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return sVar;
    }
}
