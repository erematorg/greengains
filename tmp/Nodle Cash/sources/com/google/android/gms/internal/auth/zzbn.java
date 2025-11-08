package com.google.android.gms.internal.auth;

import android.os.RemoteException;
import com.google.android.gms.auth.api.proxy.AuthApiStatusCodes;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbn extends zzbd {
    final /* synthetic */ TaskCompletionSource zza;

    public zzbn(zzbo zzbo, TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    public final void zzc(String str) throws RemoteException {
        TaskUtil.setResultOrApiException(str != null ? Status.RESULT_SUCCESS : new Status(AuthApiStatusCodes.AUTH_APP_CERT_ERROR), str, this.zza);
    }
}
