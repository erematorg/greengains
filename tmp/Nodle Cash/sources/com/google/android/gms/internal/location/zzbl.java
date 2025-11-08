package com.google.android.gms.internal.location;

import android.location.Location;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

final /* synthetic */ class zzbl implements RemoteCall {
    private final /* synthetic */ Location zza;

    public /* synthetic */ zzbl(Location location) {
        this.zza = location;
    }

    public final /* synthetic */ void accept(Object obj, Object obj2) {
        Api api = zzbi.zzb;
        ((zzdz) obj).zzA(this.zza, (TaskCompletionSource) obj2);
    }
}
