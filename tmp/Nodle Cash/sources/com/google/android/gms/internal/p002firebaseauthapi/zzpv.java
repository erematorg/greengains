package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpv  reason: invalid package */
public final class zzpv implements zzch<zzps, zzps> {
    private static final zzpv zza = new zzpv();

    private zzpv() {
    }

    public static void zzc() throws GeneralSecurityException {
        zznp.zza().zza(zza);
    }

    public final Class<zzps> zza() {
        return zzps.class;
    }

    public final Class<zzps> zzb() {
        return zzps.class;
    }

    public final /* synthetic */ Object zza(zzoo zzoo) throws GeneralSecurityException {
        if (zzoo == null) {
            throw new GeneralSecurityException("primitive set must be non-null");
        } else if (zzoo.zza() != null) {
            for (List<zzop> it : zzoo.zzd()) {
                for (zzop zzd : it) {
                    zzps zzps = (zzps) zzd.zzd();
                }
            }
            return new zzpu(zzoo);
        } else {
            throw new GeneralSecurityException("no primary in primitive set");
        }
    }
}
