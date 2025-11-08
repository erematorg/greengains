package com.google.firebase.auth.internal;

import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.p002firebaseauthapi.zzach;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.recaptcha.RecaptchaAction;

final class zzbq implements Continuation {
    private final /* synthetic */ String zza;
    private final /* synthetic */ zzbu zzb;
    private final /* synthetic */ RecaptchaAction zzc;
    private final /* synthetic */ Continuation zzd;

    public zzbq(String str, zzbu zzbu, RecaptchaAction recaptchaAction, Continuation continuation) {
        this.zza = str;
        this.zzb = zzbu;
        this.zzc = recaptchaAction;
        this.zzd = continuation;
    }

    public final /* synthetic */ Object then(Task task) throws Exception {
        if (task.isSuccessful() || !zzach.zzb((Exception) Preconditions.checkNotNull(task.getException()))) {
            return task;
        }
        if (Log.isLoggable("RecaptchaCallWrapper", 4)) {
            String str = this.zza;
            Log.i("RecaptchaCallWrapper", "Invalid token - Refreshing Recaptcha Enterprise config and fetching new token for tenant " + str);
        }
        return this.zzb.zza(this.zza, Boolean.TRUE, this.zzc).continueWithTask(this.zzd);
    }
}
