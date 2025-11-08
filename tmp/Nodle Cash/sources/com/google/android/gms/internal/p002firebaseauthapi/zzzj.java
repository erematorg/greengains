package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.Nullable;
import com.google.firebase.auth.internal.zzal;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzj  reason: invalid package */
final class zzzj implements zzadm<zzafc> {
    private final /* synthetic */ zzadm zza;
    private final /* synthetic */ zzafm zzb;
    private final /* synthetic */ zzzk zzc;

    public zzzj(zzzk zzzk, zzadm zzadm, zzafm zzafm) {
        this.zza = zzadm;
        this.zzb = zzafm;
        this.zzc = zzzk;
    }

    public final void zza(@Nullable String str) {
        this.zzc.zzb.zza(zzal.zza(str));
    }

    public final /* synthetic */ void zza(Object obj) {
        List<zzaff> zza2 = ((zzafc) obj).zza();
        if (zza2 == null || zza2.isEmpty()) {
            this.zza.zza("No users.");
            return;
        }
        zzagf zzagf = new zzagf();
        zzagf.zzd(this.zzb.zzc()).zza(this.zzc.zza);
        zzzk zzzk = this.zzc;
        zzyl.zza(zzzk.zzc, zzzk.zzb, this.zzb, zza2.get(0), zzagf, (zzadn) this.zza);
    }
}
