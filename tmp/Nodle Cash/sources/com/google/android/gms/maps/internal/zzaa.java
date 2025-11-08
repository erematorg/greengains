package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.maps.zzb;
import com.google.android.gms.internal.maps.zzc;
import com.google.android.gms.internal.maps.zzx;
import com.google.android.gms.internal.maps.zzy;

public abstract class zzaa extends zzb implements zzab {
    public zzaa() {
        super("com.google.android.gms.maps.internal.IOnIndoorStateChangeListener");
    }

    public final boolean zza(int i3, Parcel parcel, Parcel parcel2, int i4) throws RemoteException {
        if (i3 == 1) {
            zzb();
        } else if (i3 != 2) {
            return false;
        } else {
            zzy zzb = zzx.zzb(parcel.readStrongBinder());
            zzc.zzd(parcel);
            zzc(zzb);
        }
        parcel2.writeNoException();
        return true;
    }
}
