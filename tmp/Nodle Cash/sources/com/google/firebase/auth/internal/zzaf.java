package com.google.firebase.auth.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.MultiFactorSession;

final class zzaf implements Continuation<GetTokenResult, Task<MultiFactorSession>> {
    private final /* synthetic */ zzag zza;

    public zzaf(zzag zzag) {
        this.zza = zzag;
    }

    public final /* synthetic */ Object then(Task task) throws Exception {
        return !task.isSuccessful() ? Tasks.forException(task.getException()) : Tasks.forResult(zzaj.zza(((GetTokenResult) task.getResult()).getToken(), this.zza.zza));
    }
}
