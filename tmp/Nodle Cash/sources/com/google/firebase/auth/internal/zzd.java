package com.google.firebase.auth.internal;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzd implements OnSuccessListener<String> {
    private final /* synthetic */ TaskCompletionSource zza;

    public zzd(zza zza2, TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        this.zza.setResult(new zzj().zzb((String) obj).zza());
    }
}
