package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.tasks.TaskCompletionSource;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzadi  reason: invalid package */
public final /* synthetic */ class zzadi implements Runnable {
    private /* synthetic */ zzadj zza;
    private /* synthetic */ zzadl zzb;
    private /* synthetic */ TaskCompletionSource zzc;

    public /* synthetic */ zzadi(zzadj zzadj, zzadl zzadl, TaskCompletionSource taskCompletionSource) {
        this.zza = zzadj;
        this.zzb = zzadl;
        this.zzc = taskCompletionSource;
    }

    public final void run() {
        this.zzb.zza(this.zzc, this.zza.zza);
    }
}
