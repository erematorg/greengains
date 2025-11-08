package com.google.android.gms.maps.internal;

import android.os.IInterface;
import android.os.RemoteException;

public interface ILocationSourceDelegate extends IInterface {
    void activate(zzaj zzaj) throws RemoteException;

    void deactivate() throws RemoteException;
}
