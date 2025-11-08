package com.google.firebase.auth.internal;

import android.app.Activity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.FirebaseAuth;

public final /* synthetic */ class zzc implements OnCompleteListener {
    private /* synthetic */ zza zza;
    private /* synthetic */ TaskCompletionSource zzb;
    private /* synthetic */ FirebaseAuth zzc;
    private /* synthetic */ zzcb zzd;
    private /* synthetic */ Activity zze;

    public /* synthetic */ zzc(zza zza2, TaskCompletionSource taskCompletionSource, FirebaseAuth firebaseAuth, zzcb zzcb, Activity activity) {
        this.zza = zza2;
        this.zzb = taskCompletionSource;
        this.zzc = firebaseAuth;
        this.zzd = zzcb;
        this.zze = activity;
    }

    public final void onComplete(Task task) {
        this.zza.zza(this.zzb, this.zzc, this.zzd, this.zze, task);
    }
}
