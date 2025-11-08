package com.google.android.gms.internal.ads_identifier;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public class zza implements IInterface {
    private final IBinder zza;
    private final String zzb = "com.google.android.gms.ads.identifier.internal.IAdvertisingIdService";

    public zza(IBinder iBinder, String str) {
        this.zza = iBinder;
    }

    public final IBinder asBinder() {
        return this.zza;
    }

    public final Parcel zza() {
        Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken(this.zzb);
        return obtain;
    }

    public final Parcel zzb(int i3, Parcel parcel) throws RemoteException {
        parcel = Parcel.obtain();
        try {
            this.zza.transact(i3, parcel, parcel, 0);
            parcel.readException();
            return parcel;
        } catch (RuntimeException e3) {
            throw e3;
        } finally {
            parcel.recycle();
        }
    }
}
