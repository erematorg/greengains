package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;

final class zzac extends zzae {
    final /* synthetic */ long zza;
    final /* synthetic */ PendingIntent zzb;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzac(zzaf zzaf, GoogleApiClient googleApiClient, long j2, PendingIntent pendingIntent) {
        super(googleApiClient);
        this.zza = j2;
        this.zzb = pendingIntent;
    }

    public final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        PendingIntent pendingIntent = this.zzb;
        zzg zzg = (zzg) anyClient;
        Preconditions.checkNotNull(pendingIntent);
        long j2 = this.zza;
        Preconditions.checkArgument(j2 >= 0, "detectionIntervalMillis must be >= 0");
        ((zzv) zzg.getService()).zzh(j2, true, pendingIntent);
        setResult(Status.RESULT_SUCCESS);
    }
}
