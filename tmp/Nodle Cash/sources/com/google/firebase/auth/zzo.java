package com.google.firebase.auth;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.internal.zzbl;
import com.google.firebase.auth.internal.zzi;

final class zzo extends zzbl<AuthResult> {
    private final /* synthetic */ String zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ FirebaseAuth zzc;

    public zzo(FirebaseAuth firebaseAuth, String str, String str2) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = firebaseAuth;
    }

    public final Task<AuthResult> zza(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            String str2 = this.zza;
            Log.i("FirebaseAuth", "Creating user with " + str2 + " with empty reCAPTCHA token");
        } else {
            String str3 = this.zza;
            Log.i("FirebaseAuth", "Got reCAPTCHA token for sign up with email " + str3);
        }
        return this.zzc.zze.zza(this.zzc.zza, this.zza, this.zzb, this.zzc.zzk, str, (zzi) new FirebaseAuth.zza());
    }
}
