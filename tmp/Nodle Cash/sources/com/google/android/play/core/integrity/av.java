package com.google.android.play.core.integrity;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.play.integrity.internal.q;
import com.google.firebase.messaging.Constants;

final class av extends at {

    /* renamed from: c  reason: collision with root package name */
    private final q f6759c = new q("OnWarmUpIntegrityTokenCallback");

    public av(ax axVar, TaskCompletionSource taskCompletionSource) {
        super(axVar, taskCompletionSource);
    }

    public final void e(Bundle bundle) throws RemoteException {
        super.e(bundle);
        this.f6759c.c("onWarmUpExpressIntegrityToken", new Object[0]);
        int i3 = bundle.getInt(Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        if (i3 != 0) {
            this.f6756a.trySetException(new StandardIntegrityException(i3, (Throwable) null));
        } else {
            this.f6756a.trySetResult(Long.valueOf(bundle.getLong("warm.up.sid")));
        }
    }
}
