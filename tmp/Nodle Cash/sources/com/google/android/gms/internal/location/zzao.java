package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.location.SleepSegmentRequest;
import com.google.android.gms.tasks.TaskCompletionSource;

final /* synthetic */ class zzao implements RemoteCall {
    private final /* synthetic */ zzaj zza;
    private final /* synthetic */ PendingIntent zzb;
    private final /* synthetic */ SleepSegmentRequest zzc;

    public /* synthetic */ zzao(zzaj zzaj, PendingIntent pendingIntent, SleepSegmentRequest sleepSegmentRequest) {
        this.zza = zzaj;
        this.zzb = pendingIntent;
        this.zzc = sleepSegmentRequest;
    }

    public final /* synthetic */ void accept(Object obj, Object obj2) {
        ((zzv) ((zzg) obj).getService()).zzm(this.zzb, this.zzc, new zzah(this.zza, (TaskCompletionSource) obj2));
    }
}
