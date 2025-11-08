package com.google.android.gms.internal.measurement;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public class zzbx extends Binder implements IInterface {
    public zzbx(String str) {
        attachInterface(this, str);
    }

    public IBinder asBinder() {
        return this;
    }

    public boolean onTransact(int i3, Parcel parcel, Parcel parcel2, int i4) throws RemoteException {
        boolean z2;
        if (i3 > 16777215) {
            z2 = super.onTransact(i3, parcel, parcel2, i4);
        } else {
            parcel.enforceInterface(getInterfaceDescriptor());
            z2 = false;
        }
        if (z2) {
            return true;
        }
        return zza(i3, parcel, parcel2, i4);
    }

    public boolean zza(int i3, Parcel parcel, Parcel parcel2, int i4) throws RemoteException {
        return false;
    }
}
