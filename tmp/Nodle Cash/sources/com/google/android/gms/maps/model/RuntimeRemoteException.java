package com.google.android.gms.maps.model;

import android.os.RemoteException;
import androidx.annotation.NonNull;

public final class RuntimeRemoteException extends RuntimeException {
    public RuntimeRemoteException(@NonNull RemoteException remoteException) {
        super(remoteException);
    }
}
