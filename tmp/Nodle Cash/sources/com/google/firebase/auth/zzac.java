package com.google.firebase.auth;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.internal.zzbl;
import com.google.firebase.auth.internal.zzbz;

final class zzac extends zzbl<Void> {
    private final /* synthetic */ boolean zza;
    private final /* synthetic */ FirebaseUser zzb;
    private final /* synthetic */ EmailAuthCredential zzc;
    private final /* synthetic */ FirebaseAuth zzd;

    public zzac(FirebaseAuth firebaseAuth, boolean z2, FirebaseUser firebaseUser, EmailAuthCredential emailAuthCredential) {
        this.zza = z2;
        this.zzb = firebaseUser;
        this.zzc = emailAuthCredential;
        this.zzd = firebaseAuth;
    }

    /* JADX WARNING: type inference failed for: r9v0, types: [com.google.firebase.auth.FirebaseAuth$zzb, com.google.firebase.auth.internal.zzbz] */
    /* JADX WARNING: type inference failed for: r6v1, types: [com.google.firebase.auth.FirebaseAuth$zzb, com.google.firebase.auth.internal.zzbz] */
    public final Task<Void> zza(@Nullable String str) {
        if (this.zza) {
            if (TextUtils.isEmpty(str)) {
                Log.i("FirebaseAuth", " Email link reauth with empty reCAPTCHA token");
            } else {
                Log.i("FirebaseAuth", "Got reCAPTCHA token for reauth with email link");
            }
            return this.zzd.zze.zza(this.zzd.zza, this.zzb, this.zzc, str, (zzbz) new FirebaseAuth.zzb());
        }
        String zzc2 = this.zzc.zzc();
        String zzd2 = this.zzc.zzd();
        if (TextUtils.isEmpty(str)) {
            Log.i("FirebaseAuth", "Reauthenticating " + zzc2 + " with empty reCAPTCHA token");
        } else {
            Log.i("FirebaseAuth", "Got reCAPTCHA token for reauth with " + zzc2);
        }
        return this.zzd.zze.zza(this.zzd.zza, this.zzb, zzc2, Preconditions.checkNotEmpty(zzd2), this.zzb.getTenantId(), str, new FirebaseAuth.zzb());
    }
}
