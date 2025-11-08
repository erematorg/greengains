package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.StatusCallback;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.location.ActivityTransitionRequest;
import com.google.android.gms.tasks.TaskCompletionSource;

final /* synthetic */ class zzal implements RemoteCall {
    private final /* synthetic */ ActivityTransitionRequest zza;
    private final /* synthetic */ PendingIntent zzb;

    public /* synthetic */ zzal(ActivityTransitionRequest activityTransitionRequest, PendingIntent pendingIntent) {
        this.zza = activityTransitionRequest;
        this.zzb = pendingIntent;
    }

    public final /* synthetic */ void accept(Object obj, Object obj2) {
        Api api = zzaj.zzb;
        zzai zzai = new zzai((TaskCompletionSource) obj2);
        ActivityTransitionRequest activityTransitionRequest = this.zza;
        Preconditions.checkNotNull(activityTransitionRequest, "activityTransitionRequest must be specified.");
        PendingIntent pendingIntent = this.zzb;
        Preconditions.checkNotNull(pendingIntent, "PendingIntent must be specified.");
        Preconditions.checkNotNull(zzai, "ResultHolder not provided.");
        ((zzv) ((zzg) obj).getService()).zzj(activityTransitionRequest, pendingIntent, new StatusCallback(zzai));
    }
}
