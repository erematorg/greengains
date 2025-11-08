package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.errorprone.annotations.concurrent.GuardedBy;

final class zaan extends zabg {
    final /* synthetic */ BaseGmsClient.ConnectionProgressReportCallbacks zaa;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zaan(zaao zaao, zabf zabf, BaseGmsClient.ConnectionProgressReportCallbacks connectionProgressReportCallbacks) {
        super(zabf);
        this.zaa = connectionProgressReportCallbacks;
    }

    @GuardedBy("lock")
    public final void zaa() {
        this.zaa.onReportServiceBinding(new ConnectionResult(16, (PendingIntent) null));
    }
}
