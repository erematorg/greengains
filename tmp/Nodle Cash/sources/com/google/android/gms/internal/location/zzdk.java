package com.google.android.gms.internal.location;

import android.location.Location;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzdk extends zzy {
    final /* synthetic */ TaskCompletionSource zza;

    public zzdk(TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    public final void zzb(Status status, Location location) {
        TaskUtil.setResultOrApiException(status, location, this.zza);
    }
}
