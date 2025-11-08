package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.Nullable;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzyr  reason: invalid package */
final class zzyr implements zzadm<zzafc> {
    private final /* synthetic */ zzadn zza;
    private final /* synthetic */ zzacf zzb;
    private final /* synthetic */ zzafm zzc;
    private final /* synthetic */ zzagf zzd;
    private final /* synthetic */ zzyl zze;

    public zzyr(zzyl zzyl, zzadn zzadn, zzacf zzacf, zzafm zzafm, zzagf zzagf) {
        this.zza = zzadn;
        this.zzb = zzacf;
        this.zzc = zzafm;
        this.zzd = zzagf;
        this.zze = zzyl;
    }

    public final void zza(@Nullable String str) {
        this.zza.zza(str);
    }

    public final /* synthetic */ void zza(Object obj) {
        List<zzaff> zza2 = ((zzafc) obj).zza();
        if (zza2 == null || zza2.isEmpty()) {
            this.zza.zza("No users");
        } else {
            zzyl.zza(this.zze, this.zzb, this.zzc, zza2.get(0), this.zzd, this.zza);
        }
    }
}
