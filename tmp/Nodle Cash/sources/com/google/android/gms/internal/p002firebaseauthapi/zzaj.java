package com.google.android.gms.internal.p002firebaseauthapi;

import com.appsamurai.storyly.exoplayer2.common.a;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaj  reason: invalid package */
final class zzaj {
    public static int zza(int i3, String str) {
        if (i3 >= 0) {
            return i3;
        }
        throw new IllegalArgumentException(a.b(i3, str, " cannot be negative but was: "));
    }

    public static void zza(Object obj, Object obj2) {
        if (obj == null) {
            throw new NullPointerException("null key in entry: null=".concat(String.valueOf(obj2)));
        } else if (obj2 == null) {
            throw new NullPointerException(A.a.l("null value in entry: ", String.valueOf(obj), "=null"));
        }
    }
}
