package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.Nullable;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.auth.internal.zzal;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaae  reason: invalid package */
final class zzaae implements zzadm<zzafm> {
    private final /* synthetic */ UserProfileChangeRequest zza;
    private final /* synthetic */ zzacf zzb;
    private final /* synthetic */ zzyl zzc;

    public zzaae(zzyl zzyl, UserProfileChangeRequest userProfileChangeRequest, zzacf zzacf) {
        this.zza = userProfileChangeRequest;
        this.zzb = zzacf;
        this.zzc = zzyl;
    }

    public final void zza(@Nullable String str) {
        this.zzb.zza(zzal.zza(str));
    }

    public final /* synthetic */ void zza(Object obj) {
        zzafm zzafm = (zzafm) obj;
        zzagf zzagf = new zzagf();
        zzagf.zzd(zzafm.zzc());
        if (this.zza.zzb() || this.zza.getDisplayName() != null) {
            zzagf.zzb(this.zza.getDisplayName());
        }
        if (this.zza.zzc() || this.zza.getPhotoUri() != null) {
            zzagf.zzg(this.zza.zza());
        }
        zzyl.zza(this.zzc, this.zzb, zzafm, zzagf, (zzadn) this);
    }
}
