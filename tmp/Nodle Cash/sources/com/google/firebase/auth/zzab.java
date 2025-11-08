package com.google.firebase.auth;

import com.google.android.gms.internal.p002firebaseauthapi.zzah;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.recaptcha.RecaptchaTasksClient;

final class zzab implements Continuation<RecaptchaTasksClient, Task<Void>> {
    public zzab(FirebaseAuth firebaseAuth) {
    }

    public final /* synthetic */ Object then(Task task) throws Exception {
        if (task.isSuccessful()) {
            return Tasks.forResult(null);
        }
        Exception exception = task.getException();
        return Tasks.forException(new FirebaseAuthException("INTERNAL_ERROR", zzah.zzb(exception != null ? exception.getMessage() : "")));
    }
}
