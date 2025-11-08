package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.tasks.TaskCompletionSource;

final /* synthetic */ class zzbs implements RemoteCall {
    private final /* synthetic */ PendingIntent zza;
    private final /* synthetic */ LocationRequest zzb;

    public /* synthetic */ zzbs(PendingIntent pendingIntent, LocationRequest locationRequest) {
        this.zza = pendingIntent;
        this.zzb = locationRequest;
    }

    public final /* synthetic */ void accept(Object obj, Object obj2) {
        Api api = zzbi.zzb;
        ((zzdz) obj).zzu(this.zza, this.zzb, (TaskCompletionSource) obj2);
    }
}
