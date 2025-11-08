package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzas extends zza implements zzau {
    public zzas(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.ITileOverlayDelegate");
    }

    public final float zzd() throws RemoteException {
        Parcel zzJ = zzJ(13, zza());
        float readFloat = zzJ.readFloat();
        zzJ.recycle();
        return readFloat;
    }

    public final float zze() throws RemoteException {
        Parcel zzJ = zzJ(5, zza());
        float readFloat = zzJ.readFloat();
        zzJ.recycle();
        return readFloat;
    }

    public final int zzf() throws RemoteException {
        Parcel zzJ = zzJ(9, zza());
        int readInt = zzJ.readInt();
        zzJ.recycle();
        return readInt;
    }

    public final String zzg() throws RemoteException {
        Parcel zzJ = zzJ(3, zza());
        String readString = zzJ.readString();
        zzJ.recycle();
        return readString;
    }

    public final void zzh() throws RemoteException {
        zzc(2, zza());
    }

    public final void zzi() throws RemoteException {
        zzc(1, zza());
    }

    public final void zzj(boolean z2) throws RemoteException {
        Parcel zza = zza();
        int i3 = zzc.zza;
        zza.writeInt(z2 ? 1 : 0);
        zzc(10, zza);
    }

    public final void zzk(float f2) throws RemoteException {
        Parcel zza = zza();
        zza.writeFloat(f2);
        zzc(12, zza);
    }

    public final void zzl(boolean z2) throws RemoteException {
        Parcel zza = zza();
        int i3 = zzc.zza;
        zza.writeInt(z2 ? 1 : 0);
        zzc(6, zza);
    }

    public final void zzm(float f2) throws RemoteException {
        Parcel zza = zza();
        zza.writeFloat(f2);
        zzc(4, zza);
    }

    public final boolean zzn(zzau zzau) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzau);
        Parcel zzJ = zzJ(8, zza);
        boolean zzh = zzc.zzh(zzJ);
        zzJ.recycle();
        return zzh;
    }

    public final boolean zzo() throws RemoteException {
        Parcel zzJ = zzJ(11, zza());
        boolean zzh = zzc.zzh(zzJ);
        zzJ.recycle();
        return zzh;
    }

    public final boolean zzp() throws RemoteException {
        Parcel zzJ = zzJ(7, zza());
        boolean zzh = zzc.zzh(zzJ);
        zzJ.recycle();
        return zzh;
    }
}
