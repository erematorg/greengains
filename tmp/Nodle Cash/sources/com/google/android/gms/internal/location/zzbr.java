package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.location.zzac;
import com.google.android.gms.tasks.TaskCompletionSource;

final /* synthetic */ class zzbr implements RemoteCall {
    static final /* synthetic */ zzbr zza = new zzbr();

    private /* synthetic */ zzbr() {
    }

    public final /* synthetic */ void accept(Object obj, Object obj2) {
        Api api = zzbi.zzb;
        ((zzdz) obj).zzp(zzac.zza(), (TaskCompletionSource) obj2);
    }
}
