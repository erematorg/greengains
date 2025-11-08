package com.google.firebase.auth.internal;

import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.p002firebaseauthapi.zzach;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.recaptcha.RecaptchaAction;
import com.google.firebase.auth.FirebaseAuth;

public abstract class zzbl<T> {
    private static <T> Task<T> zza(zzbu zzbu, RecaptchaAction recaptchaAction, @Nullable String str, Continuation<String, Task<T>> continuation) {
        Task<String> zza = zzbu.zza(str, Boolean.FALSE, recaptchaAction);
        return zza.continueWithTask(continuation).continueWithTask(new zzbq(str, zzbu, recaptchaAction, continuation));
    }

    public abstract Task<T> zza(@Nullable String str);

    public final Task<T> zza(FirebaseAuth firebaseAuth, @Nullable String str, RecaptchaAction recaptchaAction, String str2) {
        zzbn zzbn = new zzbn(this);
        zzbu zzb = firebaseAuth.zzb();
        if (zzb == null || !zzb.zza(str2)) {
            return zza((String) null).continueWithTask(new zzbo(recaptchaAction, firebaseAuth, str, zzbn));
        }
        return zza(zzb, recaptchaAction, str, zzbn);
    }

    public static /* synthetic */ Task zza(RecaptchaAction recaptchaAction, FirebaseAuth firebaseAuth, String str, Continuation continuation, Task task) throws Exception {
        if (task.isSuccessful()) {
            return Tasks.forResult(task.getResult());
        }
        Exception exc = (Exception) Preconditions.checkNotNull(task.getException());
        if (zzach.zzc(exc)) {
            if (Log.isLoggable("RecaptchaCallWrapper", 4)) {
                Log.i("RecaptchaCallWrapper", "Falling back to recaptcha enterprise flow for action ".concat(String.valueOf(recaptchaAction)));
            }
            if (firebaseAuth.zzb() == null) {
                firebaseAuth.zza(new zzbu(firebaseAuth.getApp(), firebaseAuth));
            }
            return zza(firebaseAuth.zzb(), recaptchaAction, str, continuation);
        }
        String valueOf = String.valueOf(recaptchaAction);
        String message = exc.getMessage();
        Log.e("RecaptchaCallWrapper", "Initial task failed for action " + valueOf + "with exception - " + message);
        return Tasks.forException(exc);
    }
}
