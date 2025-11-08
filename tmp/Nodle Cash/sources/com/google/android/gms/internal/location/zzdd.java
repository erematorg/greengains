package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.location.zzw;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzdd extends zzq {
    final /* synthetic */ TaskCompletionSource zza;
    final /* synthetic */ zzw zzb;

    public zzdd(TaskCompletionSource taskCompletionSource, zzw zzw) {
        this.zza = taskCompletionSource;
        this.zzb = zzw;
    }

    public final void zzd(zzl zzl) {
        TaskUtil.setResultOrApiException(zzl.getStatus(), this.zza);
    }

    public final void zze() throws RemoteException {
        this.zzb.zzf();
    }
}
