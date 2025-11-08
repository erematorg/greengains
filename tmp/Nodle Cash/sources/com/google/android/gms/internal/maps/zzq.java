package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzq extends zza implements zzs {
    public zzq(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IFeatureLayerDelegate");
    }

    public final String zzd() throws RemoteException {
        Parcel zzJ = zzJ(6, zza());
        String readString = zzJ.readString();
        zzJ.recycle();
        return readString;
    }

    public final String zze() throws RemoteException {
        Parcel zzJ = zzJ(1, zza());
        String readString = zzJ.readString();
        zzJ.recycle();
        return readString;
    }

    public final void zzf(zzaj zzaj) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzaj);
        zzc(4, zza);
    }

    public final void zzg(zzaj zzaj) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzaj);
        zzc(5, zza);
    }

    public final void zzh(zzar zzar) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzar);
        zzc(3, zza);
    }

    public final boolean zzi() throws RemoteException {
        Parcel zzJ = zzJ(2, zza());
        boolean zzh = zzc.zzh(zzJ);
        zzJ.recycle();
        return zzh;
    }
}
