package com.google.mlkit.common.internal.model;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public final /* synthetic */ class zzf implements OnCompleteListener {
    public final /* synthetic */ zzg zza;

    public /* synthetic */ zzf(zzg zzg) {
        this.zza = zzg;
    }

    public final void onComplete(Task task) {
        this.zza.zzd(task);
    }
}
