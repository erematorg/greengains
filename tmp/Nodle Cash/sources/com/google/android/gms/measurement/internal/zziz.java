package com.google.android.gms.measurement.internal;

import A.a;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.camera.camera2.internal.C0118y;

public final class zziz {
    public static <T> T zza(@NonNull Bundle bundle, String str, Class<T> cls, T t2) {
        T t3 = bundle.get(str);
        if (t3 == null) {
            return t2;
        }
        if (cls.isAssignableFrom(t3.getClass())) {
            return t3;
        }
        String canonicalName = cls.getCanonicalName();
        throw new IllegalStateException(a.n(C0118y.l("Invalid conditional user property field type. '", str, "' expected [", canonicalName, "] but was ["), t3.getClass().getCanonicalName(), "]"));
    }

    public static void zza(@NonNull Bundle bundle, @NonNull Object obj) {
        if (obj instanceof Double) {
            bundle.putDouble("value", ((Double) obj).doubleValue());
        } else if (obj instanceof Long) {
            bundle.putLong("value", ((Long) obj).longValue());
        } else {
            bundle.putString("value", obj.toString());
        }
    }
}
