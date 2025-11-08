package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzne  reason: invalid package */
public final class zzne {
    public static final zzro zza = new zzng();

    public static <P> zzrr zza(zzoo<P> zzoo) {
        zzbw zzbw;
        zzrq zzrq = new zzrq();
        zzrq.zza(zzoo.zzb());
        for (List<zzop<P>> it : zzoo.zzd()) {
            Iterator it2 = it.iterator();
            while (true) {
                if (it2.hasNext()) {
                    zzop zzop = (zzop) it2.next();
                    int i3 = zznd.zza[zzop.zzb().ordinal()];
                    if (i3 == 1) {
                        zzbw = zzbw.zza;
                    } else if (i3 == 2) {
                        zzbw = zzbw.zzb;
                    } else if (i3 == 3) {
                        zzbw = zzbw.zzc;
                    } else {
                        throw new IllegalStateException("Unknown key status");
                    }
                    int zza2 = zzop.zza();
                    String zze = zzop.zze();
                    if (zze.startsWith("type.googleapis.com/google.crypto.")) {
                        zze = zze.substring(34);
                    }
                    zzrq.zza(zzbw, zza2, zze, zzop.zzc().name());
                }
            }
        }
        if (zzoo.zza() != null) {
            zzrq.zza(zzoo.zza().zza());
        }
        try {
            return zzrq.zza();
        } catch (GeneralSecurityException e3) {
            throw new IllegalStateException(e3);
        }
    }
}
