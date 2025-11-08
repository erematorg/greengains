package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzjv;
import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzlf  reason: invalid package */
public final class zzlf implements zzbs {
    private static final byte[] zza = new byte[0];
    private final byte[] zzb;
    private final zzlh zzc;
    private final zzli zzd;
    private final zzle zze;
    private final byte[] zzf;

    private zzlf(zzxv zzxv, zzlh zzlh, zzli zzli, zzle zzle, zzxv zzxv2) {
        this.zzb = zzxv.zzb();
        this.zzc = zzlh;
        this.zzd = zzli;
        this.zze = zzle;
        this.zzf = zzxv2.zzb();
    }

    public static zzbs zza(zzki zzki) throws GeneralSecurityException {
        zzjv zzb2 = zzki.zzb();
        return new zzlf(zzki.zzd(), zza(zzb2.zze()), zza(zzb2.zzd()), zza(zzb2.zzb()), zzki.zzc());
    }

    public static zzle zza(zzjv.zza zza2) throws GeneralSecurityException {
        if (zza2.equals(zzjv.zza.zza)) {
            return new zzku(16);
        }
        if (zza2.equals(zzjv.zza.zzb)) {
            return new zzku(32);
        }
        if (zza2.equals(zzjv.zza.zzc)) {
            return new zzkt();
        }
        throw new GeneralSecurityException("Unrecognized HPKE AEAD identifier");
    }

    public static zzli zza(zzjv.zze zze2) throws GeneralSecurityException {
        if (zze2.equals(zzjv.zze.zza)) {
            return new zzlb("HmacSha256");
        }
        if (zze2.equals(zzjv.zze.zzb)) {
            return new zzlb("HmacSha384");
        }
        if (zze2.equals(zzjv.zze.zzc)) {
            return new zzlb("HmacSha512");
        }
        throw new GeneralSecurityException("Unrecognized HPKE KDF identifier");
    }

    public static zzlh zza(zzjv.zzd zzd2) throws GeneralSecurityException {
        if (zzd2.equals(zzjv.zzd.zzd)) {
            return new zzlv(new zzlb("HmacSha256"));
        }
        if (zzd2.equals(zzjv.zzd.zza)) {
            return zzlu.zza(zzwq.NIST_P256);
        }
        if (zzd2.equals(zzjv.zzd.zzb)) {
            return zzlu.zza(zzwq.NIST_P384);
        }
        if (zzd2.equals(zzjv.zzd.zzc)) {
            return zzlu.zza(zzwq.NIST_P521);
        }
        throw new GeneralSecurityException("Unrecognized HPKE KEM identifier");
    }
}
