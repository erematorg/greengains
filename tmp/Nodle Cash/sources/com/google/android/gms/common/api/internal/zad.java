package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

abstract class zad extends zac {
    protected final TaskCompletionSource zaa;

    public zad(int i3, TaskCompletionSource taskCompletionSource) {
        super(i3);
        this.zaa = taskCompletionSource;
    }

    public abstract void zac(zabq zabq) throws RemoteException;

    public final void zad(@NonNull Status status) {
        this.zaa.trySetException(new ApiException(status));
    }

    public final void zae(@NonNull Exception exc) {
        this.zaa.trySetException(exc);
    }

    public final void zaf(zabq zabq) throws DeadObjectException {
        try {
            zac(zabq);
        } catch (DeadObjectException e3) {
            zad(zai.zah(e3));
            throw e3;
        } catch (RemoteException e4) {
            zad(zai.zah(e4));
        } catch (RuntimeException e5) {
            this.zaa.trySetException(e5);
        }
    }

    public void zag(@NonNull zaad zaad, boolean z2) {
    }
}
