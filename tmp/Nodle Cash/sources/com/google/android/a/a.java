package com.google.android.a;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public class a implements IInterface {

    /* renamed from: a  reason: collision with root package name */
    private final IBinder f6576a;

    /* renamed from: b  reason: collision with root package name */
    private final String f6577b = "com.google.android.finsky.externalreferrer.IGetInstallReferrerService";

    public a(IBinder iBinder) {
        this.f6576a = iBinder;
    }

    public final Parcel a() {
        Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken(this.f6577b);
        return obtain;
    }

    public final IBinder asBinder() {
        return this.f6576a;
    }

    public final Parcel b(Parcel parcel) throws RemoteException {
        parcel = Parcel.obtain();
        try {
            this.f6576a.transact(1, parcel, parcel, 0);
            parcel.readException();
            return parcel;
        } catch (RuntimeException e3) {
            throw e3;
        } finally {
            parcel.recycle();
        }
    }
}
