package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.Nullable;
import com.google.firebase.auth.internal.zzal;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzo  reason: invalid package */
final class zzzo implements zzadm<zzagu> {
    private final /* synthetic */ zzacf zza;
    private final /* synthetic */ zzyl zzb;

    public zzzo(zzyl zzyl, zzacf zzacf) {
        this.zza = zzacf;
        this.zzb = zzyl;
    }

    public final void zza(@Nullable String str) {
        this.zza.zza(zzal.zza(str));
    }

    public final /* synthetic */ void zza(Object obj) {
        zzagu zzagu = (zzagu) obj;
        if (!zzagu.zzl()) {
            zzyl.zza(this.zzb, zzagu, this.zza, (zzadn) this);
        } else {
            this.zza.zza(new zzym(zzagu.zzf(), zzagu.zzk(), zzagu.zzb()));
        }
    }
}
