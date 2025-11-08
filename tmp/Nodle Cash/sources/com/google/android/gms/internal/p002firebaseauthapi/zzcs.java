package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzuw;
import com.google.android.gms.internal.p002firebaseauthapi.zzvg;
import com.google.android.gms.internal.p002firebaseauthapi.zzvh;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcs  reason: invalid package */
final class zzcs {
    private static final Charset zza = Charset.forName("UTF-8");

    public static zzvh zza(zzvg zzvg) {
        zzvh.zza zza2 = zzvh.zza().zza(zzvg.zzb());
        for (zzvg.zza next : zzvg.zze()) {
            zza2.zza((zzvh.zzb) ((zzaje) zzvh.zzb.zzb().zza(next.zzb().zzf()).zza(next.zzc()).zza(next.zzf()).zza(next.zza()).zzf()));
        }
        return (zzvh) ((zzaje) zza2.zzf());
    }

    public static void zzb(zzvg zzvg) throws GeneralSecurityException {
        int zzb = zzvg.zzb();
        int i3 = 0;
        boolean z2 = false;
        boolean z3 = true;
        for (zzvg.zza next : zzvg.zze()) {
            if (next.zzc() == zzva.ENABLED) {
                if (!next.zzg()) {
                    throw new GeneralSecurityException(String.format("key %d has no key data", new Object[]{Integer.valueOf(next.zza())}));
                } else if (next.zzf() == zzvs.UNKNOWN_PREFIX) {
                    throw new GeneralSecurityException(String.format("key %d has unknown prefix", new Object[]{Integer.valueOf(next.zza())}));
                } else if (next.zzc() != zzva.UNKNOWN_STATUS) {
                    if (next.zza() == zzb) {
                        if (!z2) {
                            z2 = true;
                        } else {
                            throw new GeneralSecurityException("keyset contains multiple primary keys");
                        }
                    }
                    if (next.zzb().zzb() != zzuw.zzb.ASYMMETRIC_PUBLIC) {
                        z3 = false;
                    }
                    i3++;
                } else {
                    throw new GeneralSecurityException(String.format("key %d has unknown status", new Object[]{Integer.valueOf(next.zza())}));
                }
            }
        }
        if (i3 == 0) {
            throw new GeneralSecurityException("keyset must contain at least one ENABLED key");
        } else if (!z2 && !z3) {
            throw new GeneralSecurityException("keyset doesn't contain a valid primary key");
        }
    }
}
