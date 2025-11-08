package com.google.android.gms.internal.auth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public class zzb extends Binder implements IInterface {
    public zzb(String str) {
        attachInterface(this, str);
    }

    public final IBinder asBinder() {
        return this;
    }

    public final boolean onTransact(int i3, Parcel parcel, Parcel parcel2, int i4) throws RemoteException {
        if (i3 <= 16777215) {
            parcel.enforceInterface(getInterfaceDescriptor());
        } else if (super.onTransact(i3, parcel, parcel2, i4)) {
            return true;
        }
        return zza(i3, parcel, parcel2, i4);
    }

    public boolean zza(int i3, Parcel parcel, Parcel parcel2, int i4) throws RemoteException {
        throw null;
    }
}
