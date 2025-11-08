package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.StatusCallback;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.location.zzb;
import com.google.android.gms.tasks.TaskCompletionSource;

final /* synthetic */ class zzap implements RemoteCall {
    private final /* synthetic */ zzb zza;
    private final /* synthetic */ PendingIntent zzb;

    public /* synthetic */ zzap(zzb zzb2, PendingIntent pendingIntent) {
        this.zza = zzb2;
        this.zzb = pendingIntent;
    }

    public final /* synthetic */ void accept(Object obj, Object obj2) {
        Api api = zzaj.zzb;
        zzai zzai = new zzai((TaskCompletionSource) obj2);
        zzb zzb2 = this.zza;
        Preconditions.checkNotNull(zzb2, "ActivityRecognitionRequest can't be null.");
        PendingIntent pendingIntent = this.zzb;
        Preconditions.checkNotNull(pendingIntent, "PendingIntent must be specified.");
        Preconditions.checkNotNull(zzai, "ResultHolder not provided.");
        ((zzv) ((zzg) obj).getService()).zzi(zzb2, pendingIntent, new StatusCallback(zzai));
    }
}
