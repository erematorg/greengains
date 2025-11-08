package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.Executor;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzadj  reason: invalid package */
public class zzadj {
    zzaci zza;
    Executor zzb;

    public final <ResultT> Task<ResultT> zza(zzadl<ResultT> zzadl) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zzb.execute(new zzadi(this, zzadl, taskCompletionSource));
        return taskCompletionSource.getTask();
    }
}
