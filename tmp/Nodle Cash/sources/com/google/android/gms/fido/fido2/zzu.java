package com.google.android.gms.fido.fido2;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.fido.zzd;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzu extends zzd {
    final /* synthetic */ TaskCompletionSource zza;

    public zzu(Fido2PrivilegedApiClient fido2PrivilegedApiClient, TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    public final void zzb(boolean z2) {
        this.zza.setResult(Boolean.valueOf(z2));
    }

    public final void zzc(Status status) {
        this.zza.trySetException(new ApiException(status));
    }
}
