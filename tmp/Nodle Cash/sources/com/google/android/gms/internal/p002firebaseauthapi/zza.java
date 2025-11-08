package com.google.android.gms.internal.p002firebaseauthapi;

import android.os.Build;
import androidx.annotation.ChecksSdkIntAtLeast;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zza  reason: invalid package */
public final class zza {
    @ChecksSdkIntAtLeast(api = 33)
    public static boolean zza() {
        return Build.VERSION.SDK_INT >= 33;
    }
}
