package com.google.android.play.integrity.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public class b extends Binder implements IInterface {
    public b(String str) {
        attachInterface(this, str);
    }

    public boolean a(int i3, Parcel parcel, Parcel parcel2, int i4) throws RemoteException {
        throw null;
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
        return a(i3, parcel, parcel2, i4);
    }
}
