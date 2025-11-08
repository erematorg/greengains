package com.google.firebase.auth;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.internal.zzbl;
import com.google.firebase.auth.internal.zzbz;
import com.google.firebase.auth.internal.zzi;

final class zzad extends zzbl<AuthResult> {
    private final /* synthetic */ boolean zza;
    private final /* synthetic */ FirebaseUser zzb;
    private final /* synthetic */ EmailAuthCredential zzc;
    private final /* synthetic */ FirebaseAuth zzd;

    public zzad(FirebaseAuth firebaseAuth, boolean z2, FirebaseUser firebaseUser, EmailAuthCredential emailAuthCredential) {
        this.zza = z2;
        this.zzb = firebaseUser;
        this.zzc = emailAuthCredential;
        this.zzd = firebaseAuth;
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [com.google.firebase.auth.FirebaseAuth$zzb, com.google.firebase.auth.internal.zzbz] */
    public final Task<AuthResult> zza(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            Log.i("FirebaseAuth", "Email link login/reauth with empty reCAPTCHA token");
        } else {
            Log.i("FirebaseAuth", "Got reCAPTCHA token for login/reauth with email link");
        }
        if (!this.zza) {
            return this.zzd.zze.zza(this.zzd.zza, this.zzc, str, (zzi) new FirebaseAuth.zza());
        }
        return this.zzd.zze.zzb(this.zzd.zza, (FirebaseUser) Preconditions.checkNotNull(this.zzb), this.zzc, str, (zzbz) new FirebaseAuth.zzb());
    }
}
