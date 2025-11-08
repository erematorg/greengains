package com.google.firebase.auth;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.internal.zzbl;

final class zzaa extends zzbl<AuthResult> {
    private final /* synthetic */ String zza;
    private final /* synthetic */ boolean zzb;
    private final /* synthetic */ FirebaseUser zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ String zze;
    private final /* synthetic */ FirebaseAuth zzf;

    public zzaa(FirebaseAuth firebaseAuth, String str, boolean z2, FirebaseUser firebaseUser, String str2, String str3) {
        this.zza = str;
        this.zzb = z2;
        this.zzc = firebaseUser;
        this.zzd = str2;
        this.zze = str3;
        this.zzf = firebaseAuth;
    }

    /* JADX WARNING: type inference failed for: r8v0, types: [com.google.firebase.auth.FirebaseAuth$zzb, com.google.firebase.auth.internal.zzbz] */
    public final Task<AuthResult> zza(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            String str2 = this.zza;
            Log.i("FirebaseAuth", "Logging in as " + str2 + " with empty reCAPTCHA token");
        } else {
            String str3 = this.zza;
            Log.i("FirebaseAuth", "Got reCAPTCHA token for login with email " + str3);
        }
        if (this.zzb) {
            return this.zzf.zze.zzb(this.zzf.zza, (FirebaseUser) Preconditions.checkNotNull(this.zzc), this.zza, this.zzd, this.zze, str, new FirebaseAuth.zzb());
        }
        return this.zzf.zze.zzb(this.zzf.zza, this.zza, this.zzd, this.zze, str, new FirebaseAuth.zza());
    }
}
