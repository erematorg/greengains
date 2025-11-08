package com.google.android.gms.internal.p001authapi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* renamed from: com.google.android.gms.internal.auth-api.zbb  reason: invalid package */
public class zbb extends Binder implements IInterface {
    public zbb(String str) {
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
        return zba(i3, parcel, parcel2, i4);
    }

    public boolean zba(int i3, Parcel parcel, Parcel parcel2, int i4) throws RemoteException {
        throw null;
    }
}
