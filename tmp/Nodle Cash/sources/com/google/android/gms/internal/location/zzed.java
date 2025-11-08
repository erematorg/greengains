package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.internal.ICancelToken;
import com.google.android.gms.tasks.OnTokenCanceledListener;

final /* synthetic */ class zzed implements OnTokenCanceledListener {
    private final /* synthetic */ ICancelToken zza;

    public /* synthetic */ zzed(ICancelToken iCancelToken) {
        this.zza = iCancelToken;
    }

    public final /* synthetic */ void onCanceled() {
        try {
            this.zza.cancel();
        } catch (RemoteException unused) {
        }
    }
}
