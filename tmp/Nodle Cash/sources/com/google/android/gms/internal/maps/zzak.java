package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import java.util.ArrayList;
import java.util.List;

public final class zzak extends zza implements zzam {
    public zzak(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IPolygonDelegate");
    }

    public final void zzA(float f2) throws RemoteException {
        Parcel zza = zza();
        zza.writeFloat(f2);
        zzc(13, zza);
    }

    public final boolean zzB(zzam zzam) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzam);
        Parcel zzJ = zzJ(19, zza);
        boolean zzh = zzc.zzh(zzJ);
        zzJ.recycle();
        return zzh;
    }

    public final boolean zzC() throws RemoteException {
        Parcel zzJ = zzJ(22, zza());
        boolean zzh = zzc.zzh(zzJ);
        zzJ.recycle();
        return zzh;
    }

    public final boolean zzD() throws RemoteException {
        Parcel zzJ = zzJ(18, zza());
        boolean zzh = zzc.zzh(zzJ);
        zzJ.recycle();
        return zzh;
    }

    public final boolean zzE() throws RemoteException {
        Parcel zzJ = zzJ(16, zza());
        boolean zzh = zzc.zzh(zzJ);
        zzJ.recycle();
        return zzh;
    }

    public final float zzd() throws RemoteException {
        Parcel zzJ = zzJ(8, zza());
        float readFloat = zzJ.readFloat();
        zzJ.recycle();
        return readFloat;
    }

    public final float zze() throws RemoteException {
        Parcel zzJ = zzJ(14, zza());
        float readFloat = zzJ.readFloat();
        zzJ.recycle();
        return readFloat;
    }

    public final int zzf() throws RemoteException {
        Parcel zzJ = zzJ(12, zza());
        int readInt = zzJ.readInt();
        zzJ.recycle();
        return readInt;
    }

    public final int zzg() throws RemoteException {
        Parcel zzJ = zzJ(10, zza());
        int readInt = zzJ.readInt();
        zzJ.recycle();
        return readInt;
    }

    public final int zzh() throws RemoteException {
        Parcel zzJ = zzJ(24, zza());
        int readInt = zzJ.readInt();
        zzJ.recycle();
        return readInt;
    }

    public final int zzi() throws RemoteException {
        Parcel zzJ = zzJ(20, zza());
        int readInt = zzJ.readInt();
        zzJ.recycle();
        return readInt;
    }

    public final IObjectWrapper zzj() throws RemoteException {
        return a.e(zzJ(28, zza()));
    }

    public final String zzk() throws RemoteException {
        Parcel zzJ = zzJ(2, zza());
        String readString = zzJ.readString();
        zzJ.recycle();
        return readString;
    }

    public final List zzl() throws RemoteException {
        Parcel zzJ = zzJ(6, zza());
        ArrayList zzb = zzc.zzb(zzJ);
        zzJ.recycle();
        return zzb;
    }

    public final List zzm() throws RemoteException {
        Parcel zzJ = zzJ(4, zza());
        ArrayList<LatLng> createTypedArrayList = zzJ.createTypedArrayList(LatLng.CREATOR);
        zzJ.recycle();
        return createTypedArrayList;
    }

    public final List zzn() throws RemoteException {
        Parcel zzJ = zzJ(26, zza());
        ArrayList<PatternItem> createTypedArrayList = zzJ.createTypedArrayList(PatternItem.CREATOR);
        zzJ.recycle();
        return createTypedArrayList;
    }

    public final void zzo() throws RemoteException {
        zzc(1, zza());
    }

    public final void zzp(boolean z2) throws RemoteException {
        Parcel zza = zza();
        int i3 = zzc.zza;
        zza.writeInt(z2 ? 1 : 0);
        zzc(21, zza);
    }

    public final void zzq(int i3) throws RemoteException {
        Parcel zza = zza();
        zza.writeInt(i3);
        zzc(11, zza);
    }

    public final void zzr(boolean z2) throws RemoteException {
        Parcel zza = zza();
        int i3 = zzc.zza;
        zza.writeInt(z2 ? 1 : 0);
        zzc(17, zza);
    }

    public final void zzs(List list) throws RemoteException {
        Parcel zza = zza();
        zza.writeList(list);
        zzc(5, zza);
    }

    public final void zzt(List list) throws RemoteException {
        Parcel zza = zza();
        zza.writeTypedList(list);
        zzc(3, zza);
    }

    public final void zzu(int i3) throws RemoteException {
        Parcel zza = zza();
        zza.writeInt(i3);
        zzc(9, zza);
    }

    public final void zzv(int i3) throws RemoteException {
        Parcel zza = zza();
        zza.writeInt(i3);
        zzc(23, zza);
    }

    public final void zzw(List list) throws RemoteException {
        Parcel zza = zza();
        zza.writeTypedList(list);
        zzc(25, zza);
    }

    public final void zzx(float f2) throws RemoteException {
        Parcel zza = zza();
        zza.writeFloat(f2);
        zzc(7, zza);
    }

    public final void zzy(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, iObjectWrapper);
        zzc(27, zza);
    }

    public final void zzz(boolean z2) throws RemoteException {
        Parcel zza = zza();
        int i3 = zzc.zza;
        zza.writeInt(z2 ? 1 : 0);
        zzc(15, zza);
    }
}
