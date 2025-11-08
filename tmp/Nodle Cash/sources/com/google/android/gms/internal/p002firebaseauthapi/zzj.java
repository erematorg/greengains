package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.firebase.analytics.FirebaseAnalytics;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzj  reason: invalid package */
public abstract class zzj {
    public int zza(CharSequence charSequence, int i3) {
        int length = charSequence.length();
        zzz.zza(i3, length, FirebaseAnalytics.Param.INDEX);
        while (i3 < length) {
            if (zza(charSequence.charAt(i3))) {
                return i3;
            }
            i3++;
        }
        return -1;
    }

    public abstract boolean zza(char c3);
}
