package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

final /* synthetic */ class zzcu implements RemoteCall {
    private final /* synthetic */ PendingIntent zza;

    public /* synthetic */ zzcu(PendingIntent pendingIntent) {
        this.zza = pendingIntent;
    }

    public final /* synthetic */ void accept(Object obj, Object obj2) {
        ((zzdz) obj).zzF(zzem.zzb(this.zza), (TaskCompletionSource) obj2);
    }
}
