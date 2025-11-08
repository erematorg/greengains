package com.google.firebase.auth.internal;

import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.recaptcha.RecaptchaAction;
import com.google.android.recaptcha.RecaptchaTasksClient;

final class zzbw implements Continuation<RecaptchaTasksClient, Task<String>> {
    private final /* synthetic */ RecaptchaAction zza;

    public zzbw(zzbu zzbu, RecaptchaAction recaptchaAction) {
        this.zza = recaptchaAction;
    }

    public final /* synthetic */ Object then(Task task) throws Exception {
        if (task.isSuccessful()) {
            return ((RecaptchaTasksClient) task.getResult()).executeTask(this.zza);
        }
        Exception exc = (Exception) Preconditions.checkNotNull(task.getException());
        if (!(exc instanceof zzbr)) {
            return Tasks.forException(exc);
        }
        if (Log.isLoggable("RecaptchaHandler", 4)) {
            String message = exc.getMessage();
            Log.i("RecaptchaHandler", "Ignoring error related to fetching recaptcha config - " + message);
        }
        return Tasks.forResult("");
    }
}
