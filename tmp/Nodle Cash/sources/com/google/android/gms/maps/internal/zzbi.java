package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.maps.zzao;
import com.google.android.gms.internal.maps.zzap;
import com.google.android.gms.internal.maps.zzb;
import com.google.android.gms.internal.maps.zzc;

public abstract class zzbi extends zzb implements zzbj {
    public zzbi() {
        super("com.google.android.gms.maps.internal.IOnPolylineClickListener");
    }

    public final boolean zza(int i3, Parcel parcel, Parcel parcel2, int i4) throws RemoteException {
        if (i3 != 1) {
            return false;
        }
        zzap zzb = zzao.zzb(parcel.readStrongBinder());
        zzc.zzd(parcel);
        zzb(zzb);
        parcel2.writeNoException();
        return true;
    }
}
