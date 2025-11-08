package com.google.firebase.auth.internal;

import android.app.Application;
import com.google.android.gms.tasks.Task;
import com.google.android.recaptcha.Recaptcha;
import com.google.android.recaptcha.RecaptchaTasksClient;

final class zzbs implements zzbp {
    public final Task<RecaptchaTasksClient> zza(Application application, String str) {
        return Recaptcha.getTasksClient(application, str);
    }
}
