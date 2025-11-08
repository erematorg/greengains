package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzq extends zzb implements zzr {
    public zzq() {
        super("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
    }

    public final boolean zza(int i3, Parcel parcel, Parcel parcel2, int i4) throws RemoteException {
        if (i3 == 1) {
            zzc.zzd(parcel);
            zzd((zzl) zzc.zza(parcel, zzl.CREATOR));
        } else if (i3 != 2) {
            return false;
        } else {
            zze();
        }
        return true;
    }
}
