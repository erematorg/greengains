package com.google.android.gms.location;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.internal.location.zza;

public final class zzu extends zza implements zzw {
    public zzu(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.location.ILocationCallback");
    }

    public final void zzd(LocationResult locationResult) throws RemoteException {
        throw null;
    }

    public final void zze(LocationAvailability locationAvailability) throws RemoteException {
        throw null;
    }

    public final void zzf() throws RemoteException {
        throw null;
    }
}
