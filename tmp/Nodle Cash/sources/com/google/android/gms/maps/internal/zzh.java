package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zzag;
import com.google.android.gms.internal.maps.zzah;
import com.google.android.gms.internal.maps.zzb;
import com.google.android.gms.internal.maps.zzc;

public abstract class zzh extends zzb implements zzi {
    public zzh() {
        super("com.google.android.gms.maps.internal.IInfoWindowAdapter");
    }

    public final boolean zza(int i3, Parcel parcel, Parcel parcel2, int i4) throws RemoteException {
        if (i3 == 1) {
            zzah zzb = zzag.zzb(parcel.readStrongBinder());
            zzc.zzd(parcel);
            IObjectWrapper zzc = zzc(zzb);
            parcel2.writeNoException();
            zzc.zzg(parcel2, zzc);
        } else if (i3 != 2) {
            return false;
        } else {
            zzah zzb2 = zzag.zzb(parcel.readStrongBinder());
            zzc.zzd(parcel);
            IObjectWrapper zzb3 = zzb(zzb2);
            parcel2.writeNoException();
            zzc.zzg(parcel2, zzb3);
        }
        return true;
    }
}
