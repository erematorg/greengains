package com.google.firebase.auth;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.internal.zzbl;

final class zzr extends zzbl<Void> {
    private final /* synthetic */ String zza;
    private final /* synthetic */ ActionCodeSettings zzb;
    private final /* synthetic */ FirebaseAuth zzc;

    public zzr(FirebaseAuth firebaseAuth, String str, ActionCodeSettings actionCodeSettings) {
        this.zza = str;
        this.zzb = actionCodeSettings;
        this.zzc = firebaseAuth;
    }

    public final Task<Void> zza(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            String str2 = this.zza;
            Log.i("FirebaseAuth", "Password reset request " + str2 + " with empty reCAPTCHA token");
        } else {
            String str3 = this.zza;
            Log.i("FirebaseAuth", "Got reCAPTCHA token for password reset of email " + str3);
        }
        return this.zzc.zze.zza(this.zzc.zza, this.zza, this.zzb, this.zzc.zzk, str);
    }
}
