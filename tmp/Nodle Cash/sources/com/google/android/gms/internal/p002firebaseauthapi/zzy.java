package com.google.android.gms.internal.p002firebaseauthapi;

import javax.annotation.CheckForNull;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzy  reason: invalid package */
final class zzy {
    private static final zzv zza = new zzx();

    public static zzs zza(String str) {
        zzz.zza(str);
        return zza.zza(str);
    }

    @CheckForNull
    public static String zzb(@CheckForNull String str) {
        if (zzd(str)) {
            return null;
        }
        return str;
    }

    public static String zzc(@CheckForNull String str) {
        return str == null ? "" : str;
    }

    public static boolean zzd(@CheckForNull String str) {
        return str == null || str.isEmpty();
    }
}
