package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.maps.zzag;
import com.google.android.gms.internal.maps.zzah;
import com.google.android.gms.internal.maps.zzb;
import com.google.android.gms.internal.maps.zzc;

public abstract class zzaw extends zzb implements zzax {
    public zzaw() {
        super("com.google.android.gms.maps.internal.IOnMarkerDragListener");
    }

    public final boolean zza(int i3, Parcel parcel, Parcel parcel2, int i4) throws RemoteException {
        if (i3 == 1) {
            zzah zzb = zzag.zzb(parcel.readStrongBinder());
            zzc.zzd(parcel);
            zzd(zzb);
        } else if (i3 == 2) {
            zzah zzb2 = zzag.zzb(parcel.readStrongBinder());
            zzc.zzd(parcel);
            zzb(zzb2);
        } else if (i3 != 3) {
            return false;
        } else {
            zzah zzb3 = zzag.zzb(parcel.readStrongBinder());
            zzc.zzd(parcel);
            zzc(zzb3);
        }
        parcel2.writeNoException();
        return true;
    }
}
