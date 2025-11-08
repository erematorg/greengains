package com.google.android.gms.location;

import android.os.IInterface;
import android.os.RemoteException;

public interface zzw extends IInterface {
    void zzd(LocationResult locationResult) throws RemoteException;

    void zze(LocationAvailability locationAvailability) throws RemoteException;

    void zzf() throws RemoteException;
}
