package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.HashMap;
import java.util.Map;

public final class zzn extends zza implements zzp {
    public zzn(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IFeatureDelegate");
    }

    public final int zzd() throws RemoteException {
        Parcel zzJ = zzJ(1, zza());
        int readInt = zzJ.readInt();
        zzJ.recycle();
        return readInt;
    }

    public final String zze() throws RemoteException {
        Parcel zzJ = zzJ(4, zza());
        String readString = zzJ.readString();
        zzJ.recycle();
        return readString;
    }

    public final String zzf() throws RemoteException {
        Parcel zzJ = zzJ(2, zza());
        String readString = zzJ.readString();
        zzJ.recycle();
        return readString;
    }

    public final String zzg() throws RemoteException {
        Parcel zzJ = zzJ(3, zza());
        String readString = zzJ.readString();
        zzJ.recycle();
        return readString;
    }

    public final Map zzh() throws RemoteException {
        Parcel zzJ = zzJ(5, zza());
        HashMap zzc = zzc.zzc(zzJ);
        zzJ.recycle();
        return zzc;
    }
}
