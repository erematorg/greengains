package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;

public final class zzaf extends zza implements zzah {
    public zzaf(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IMarkerDelegate");
    }

    public final void zzA(String str) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzc(5, zza);
    }

    public final void zzB(boolean z2) throws RemoteException {
        Parcel zza = zza();
        int i3 = zzc.zza;
        zza.writeInt(z2 ? 1 : 0);
        zzc(14, zza);
    }

    public final void zzC(float f2) throws RemoteException {
        Parcel zza = zza();
        zza.writeFloat(f2);
        zzc(27, zza);
    }

    public final void zzD() throws RemoteException {
        zzc(11, zza());
    }

    public final boolean zzE(zzah zzah) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzah);
        Parcel zzJ = zzJ(16, zza);
        boolean zzh = zzc.zzh(zzJ);
        zzJ.recycle();
        return zzh;
    }

    public final boolean zzF() throws RemoteException {
        Parcel zzJ = zzJ(10, zza());
        boolean zzh = zzc.zzh(zzJ);
        zzJ.recycle();
        return zzh;
    }

    public final boolean zzG() throws RemoteException {
        Parcel zzJ = zzJ(21, zza());
        boolean zzh = zzc.zzh(zzJ);
        zzJ.recycle();
        return zzh;
    }

    public final boolean zzH() throws RemoteException {
        Parcel zzJ = zzJ(13, zza());
        boolean zzh = zzc.zzh(zzJ);
        zzJ.recycle();
        return zzh;
    }

    public final boolean zzI() throws RemoteException {
        Parcel zzJ = zzJ(15, zza());
        boolean zzh = zzc.zzh(zzJ);
        zzJ.recycle();
        return zzh;
    }

    public final float zzd() throws RemoteException {
        Parcel zzJ = zzJ(26, zza());
        float readFloat = zzJ.readFloat();
        zzJ.recycle();
        return readFloat;
    }

    public final float zze() throws RemoteException {
        Parcel zzJ = zzJ(23, zza());
        float readFloat = zzJ.readFloat();
        zzJ.recycle();
        return readFloat;
    }

    public final float zzf() throws RemoteException {
        Parcel zzJ = zzJ(28, zza());
        float readFloat = zzJ.readFloat();
        zzJ.recycle();
        return readFloat;
    }

    public final int zzg() throws RemoteException {
        Parcel zzJ = zzJ(17, zza());
        int readInt = zzJ.readInt();
        zzJ.recycle();
        return readInt;
    }

    public final IObjectWrapper zzh() throws RemoteException {
        return a.e(zzJ(34, zza()));
    }

    public final IObjectWrapper zzi() throws RemoteException {
        return a.e(zzJ(30, zza()));
    }

    public final LatLng zzj() throws RemoteException {
        Parcel zzJ = zzJ(4, zza());
        LatLng latLng = (LatLng) zzc.zza(zzJ, LatLng.CREATOR);
        zzJ.recycle();
        return latLng;
    }

    public final String zzk() throws RemoteException {
        Parcel zzJ = zzJ(2, zza());
        String readString = zzJ.readString();
        zzJ.recycle();
        return readString;
    }

    public final String zzl() throws RemoteException {
        Parcel zzJ = zzJ(8, zza());
        String readString = zzJ.readString();
        zzJ.recycle();
        return readString;
    }

    public final String zzm() throws RemoteException {
        Parcel zzJ = zzJ(6, zza());
        String readString = zzJ.readString();
        zzJ.recycle();
        return readString;
    }

    public final void zzn() throws RemoteException {
        zzc(12, zza());
    }

    public final void zzo() throws RemoteException {
        zzc(1, zza());
    }

    public final void zzp(float f2) throws RemoteException {
        Parcel zza = zza();
        zza.writeFloat(f2);
        zzc(25, zza);
    }

    public final void zzq(float f2, float f3) throws RemoteException {
        Parcel zza = zza();
        zza.writeFloat(f2);
        zza.writeFloat(f3);
        zzc(19, zza);
    }

    public final void zzr(boolean z2) throws RemoteException {
        Parcel zza = zza();
        int i3 = zzc.zza;
        zza.writeInt(z2 ? 1 : 0);
        zzc(9, zza);
    }

    public final void zzs(boolean z2) throws RemoteException {
        Parcel zza = zza();
        int i3 = zzc.zza;
        zza.writeInt(z2 ? 1 : 0);
        zzc(20, zza);
    }

    public final void zzt(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, iObjectWrapper);
        zzc(18, zza);
    }

    public final void zzu(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, iObjectWrapper);
        zzc(33, zza);
    }

    public final void zzv(float f2, float f3) throws RemoteException {
        Parcel zza = zza();
        zza.writeFloat(f2);
        zza.writeFloat(f3);
        zzc(24, zza);
    }

    public final void zzw(LatLng latLng) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, latLng);
        zzc(3, zza);
    }

    public final void zzx(float f2) throws RemoteException {
        Parcel zza = zza();
        zza.writeFloat(f2);
        zzc(22, zza);
    }

    public final void zzy(String str) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzc(7, zza);
    }

    public final void zzz(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, iObjectWrapper);
        zzc(29, zza);
    }
}
