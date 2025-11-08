package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.Nullable;
import com.google.firebase.auth.internal.zzal;
import com.google.firebase.auth.zzf;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzys  reason: invalid package */
final class zzys implements zzadm<zzaer> {
    private final /* synthetic */ zzacf zza;
    private final /* synthetic */ zzyl zzb;

    public zzys(zzyl zzyl, zzacf zzacf) {
        this.zza = zzacf;
        this.zzb = zzyl;
    }

    public final void zza(@Nullable String str) {
        this.zza.zza(zzal.zza(str));
    }

    public final /* synthetic */ void zza(Object obj) {
        zzaer zzaer = (zzaer) obj;
        if (zzaer.zzf()) {
            this.zza.zza(new zzym(zzaer.zzc(), zzaer.zze(), (zzf) null));
            return;
        }
        this.zzb.zza(new zzafm(zzaer.zzd(), zzaer.zzb(), Long.valueOf(zzaer.zza()), "Bearer"), (String) null, (String) null, Boolean.valueOf(zzaer.zzg()), (zzf) null, this.zza, this);
    }
}
