package com.google.android.play.core.integrity;

import android.content.Context;
import com.google.android.play.integrity.internal.ae;

final class v {

    /* renamed from: a  reason: collision with root package name */
    private static o f6806a;

    public static synchronized o a(Context context) {
        o oVar;
        synchronized (v.class) {
            try {
                if (f6806a == null) {
                    m mVar = new m((l) null);
                    mVar.a(ae.a(context));
                    f6806a = mVar.b();
                }
                oVar = f6806a;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return oVar;
    }
}
