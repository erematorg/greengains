package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.location.LastLocationRequest;
import com.google.android.gms.tasks.TaskCompletionSource;

final /* synthetic */ class zzbj implements RemoteCall {
    private final /* synthetic */ LastLocationRequest zza;

    public /* synthetic */ zzbj(LastLocationRequest lastLocationRequest) {
        this.zza = lastLocationRequest;
    }

    public final /* synthetic */ void accept(Object obj, Object obj2) {
        Api api = zzbi.zzb;
        ((zzdz) obj).zzq(this.zza, (TaskCompletionSource) obj2);
    }
}
