package com.google.firebase.auth;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

final class zzt implements Continuation<Void, Task<Void>> {
    public zzt(FirebaseAuth firebaseAuth) {
    }

    public final /* synthetic */ Object then(Task task) throws Exception {
        return (task.isSuccessful() || !(task.getException() instanceof FirebaseAuthException) || !((FirebaseAuthException) task.getException()).getErrorCode().equals("ERROR_INTERNAL_SUCCESS_SIGN_OUT")) ? task : Tasks.forResult(null);
    }
}
