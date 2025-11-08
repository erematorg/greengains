package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzvg;
import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbo  reason: invalid package */
public final class zzbo {
    public static final byte[] zza = new byte[0];

    public static byte[] zza(zzvg.zza zza2) throws GeneralSecurityException {
        int i3 = zzbn.zza[zza2.zzf().ordinal()];
        if (i3 == 1 || i3 == 2) {
            return zznt.zza(zza2.zza()).zzb();
        }
        if (i3 == 3) {
            return zznt.zzb(zza2.zza()).zzb();
        }
        if (i3 == 4) {
            return zza;
        }
        throw new GeneralSecurityException("unknown output prefix type");
    }
}
