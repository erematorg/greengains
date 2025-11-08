package com.google.android.play.integrity.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public class a implements IInterface {

    /* renamed from: a  reason: collision with root package name */
    private final IBinder f6810a;

    /* renamed from: b  reason: collision with root package name */
    private final String f6811b;

    public a(IBinder iBinder, String str) {
        this.f6810a = iBinder;
        this.f6811b = str;
    }

    public final Parcel a() {
        Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken(this.f6811b);
        return obtain;
    }

    public final IBinder asBinder() {
        return this.f6810a;
    }

    public final void b(int i3, Parcel parcel) throws RemoteException {
        try {
            this.f6810a.transact(i3, parcel, (Parcel) null, 1);
        } finally {
            parcel.recycle();
        }
    }
}
