package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.tasks.TaskCompletionSource;

final /* synthetic */ class zzbu implements RemoteCall {
    private final /* synthetic */ zzbh zza;
    private final /* synthetic */ LocationRequest zzb;

    public /* synthetic */ zzbu(zzbh zzbh, LocationRequest locationRequest) {
        this.zza = zzbh;
        this.zzb = locationRequest;
    }

    public final /* synthetic */ void accept(Object obj, Object obj2) {
        Api api = zzbi.zzb;
        ((zzdz) obj).zzt(this.zza, this.zzb, (TaskCompletionSource) obj2);
    }
}
