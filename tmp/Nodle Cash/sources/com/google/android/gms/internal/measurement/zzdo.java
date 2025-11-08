package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzdo extends zzbx implements zzdl {
    public zzdo() {
        super("com.google.android.gms.measurement.api.internal.IBundleReceiver");
    }

    public final boolean zza(int i3, Parcel parcel, Parcel parcel2, int i4) throws RemoteException {
        if (i3 != 1) {
            return false;
        }
        zzbw.zzb(parcel);
        zza((Bundle) zzbw.zza(parcel, Bundle.CREATOR));
        parcel2.writeNoException();
        return true;
    }
}
