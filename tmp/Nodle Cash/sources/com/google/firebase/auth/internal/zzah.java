package com.google.firebase.auth.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

final class zzah implements Continuation<AuthResult, Task<AuthResult>> {
    private final /* synthetic */ zzai zza;

    public zzah(zzai zzai) {
        this.zza = zzai;
    }

    public final /* synthetic */ Object then(@NonNull Task task) throws Exception {
        if (this.zza.zzd == null) {
            return task;
        }
        if (task.isSuccessful()) {
            AuthResult authResult = (AuthResult) task.getResult();
            return Tasks.forResult(new zzw((zzac) authResult.getUser(), (zzu) authResult.getAdditionalUserInfo(), this.zza.zzd));
        }
        Exception exception = task.getException();
        if (exception instanceof FirebaseAuthUserCollisionException) {
            ((FirebaseAuthUserCollisionException) exception).zza((AuthCredential) this.zza.zzd);
        }
        return Tasks.forException(exception);
    }
}
