package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzvc;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzct  reason: invalid package */
public final class zzct {
    public static final zzvc zza = zza(16);
    public static final zzvc zzb;
    private static final zzvc zzc = zza(32);
    private static final zzvc zzd = zza(16, 16);
    private static final zzvc zze = zza(32, 16);
    private static final zzvc zzf;
    private static final zzvc zzg;
    private static final zzvc zzh;

    static {
        zzub zzub = zzub.SHA256;
        zzb = zza(16, 16, 32, 16, zzub);
        zzf = zza(32, 16, 32, 32, zzub);
        zzvc.zza zza2 = zzvc.zza().zza(zzep.zza());
        zzvs zzvs = zzvs.TINK;
        zzg = (zzvc) ((zzaje) zza2.zza(zzvs).zzf());
        zzh = (zzvc) ((zzaje) zzvc.zza().zza(zzga.zza()).zza(zzvs).zzf());
    }

    private static zzvc zza(int i3, int i4, int i5, int i6, zzub zzub) {
        return (zzvc) ((zzaje) zzvc.zza().zza(((zzsf) ((zzaje) zzsf.zza().zza((zzsj) ((zzaje) zzsj.zzb().zza((zzsk) ((zzaje) zzsk.zzb().zza(16).zzf())).zza(i3).zzf())).zza((zzue) ((zzaje) zzue.zzc().zza((zzuh) ((zzaje) zzuh.zzc().zza(zzub).zza(i6).zzf())).zza(32).zzf())).zzf())).zzj()).zza(zzdc.zza()).zza(zzvs.TINK).zzf());
    }

    private static zzvc zza(int i3, int i4) {
        return (zzvc) ((zzaje) zzvc.zza().zza(((zzso) ((zzaje) zzso.zzb().zza(i3).zza((zzsr) ((zzaje) zzsr.zzb().zza(16).zzf())).zzf())).zzj()).zza(zzdm.zza()).zza(zzvs.TINK).zzf());
    }

    private static zzvc zza(int i3) {
        return (zzvc) ((zzaje) zzvc.zza().zza(((zzsv) ((zzaje) zzsv.zzc().zza(i3).zzf())).zzj()).zza(zzdv.zza()).zza(zzvs.TINK).zzf());
    }
}
