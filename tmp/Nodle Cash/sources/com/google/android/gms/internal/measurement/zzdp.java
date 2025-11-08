package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzdp extends zzbx implements zzdq {
    public zzdp() {
        super("com.google.android.gms.measurement.api.internal.IEventHandlerProxy");
    }

    public final boolean zza(int i3, Parcel parcel, Parcel parcel2, int i4) throws RemoteException {
        if (i3 == 1) {
            long readLong = parcel.readLong();
            zzbw.zzb(parcel);
            zza(parcel.readString(), parcel.readString(), (Bundle) zzbw.zza(parcel, Bundle.CREATOR), readLong);
            parcel2.writeNoException();
        } else if (i3 != 2) {
            return false;
        } else {
            int zza = zza();
            parcel2.writeNoException();
            parcel2.writeInt(zza);
        }
        return true;
    }
}
