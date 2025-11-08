package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzkn  reason: invalid package */
final class zzkn implements zzbs {
    private final zzoo<zzbs> zza;
    private final zzro zzb;

    public zzkn(zzoo<zzbs> zzoo) {
        this.zza = zzoo;
        if (zzoo.zzf()) {
            this.zzb = zznl.zza().zzb().zza(zzne.zza(zzoo), "hybrid_encrypt", "encrypt");
        } else {
            this.zzb = zzne.zza;
        }
    }
}
