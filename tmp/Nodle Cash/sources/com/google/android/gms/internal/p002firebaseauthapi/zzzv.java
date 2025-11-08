package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.Nullable;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzv  reason: invalid package */
final class zzzv implements zzadm<zzafc> {
    private final /* synthetic */ zzadm zza;
    private final /* synthetic */ zzafm zzb;
    private final /* synthetic */ zzzw zzc;

    public zzzv(zzzw zzzw, zzadm zzadm, zzafm zzafm) {
        this.zza = zzadm;
        this.zzb = zzafm;
        this.zzc = zzzw;
    }

    public final void zza(@Nullable String str) {
        this.zza.zza(str);
    }

    public final /* synthetic */ void zza(Object obj) {
        List<zzaff> zza2 = ((zzafc) obj).zza();
        if (zza2 == null || zza2.isEmpty()) {
            this.zza.zza("No users");
        } else {
            this.zzc.zza.zza(this.zzb, zza2.get(0));
        }
    }
}
