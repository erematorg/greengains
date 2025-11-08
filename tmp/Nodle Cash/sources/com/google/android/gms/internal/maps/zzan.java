package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.Cap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.StyleSpan;
import java.util.ArrayList;
import java.util.List;

public final class zzan extends zza implements zzap {
    public zzan(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IPolylineDelegate");
    }

    public final void zzA(boolean z2) throws RemoteException {
        Parcel zza = zza();
        int i3 = zzc.zza;
        zza.writeInt(z2 ? 1 : 0);
        zzc(11, zza);
    }

    public final void zzB(float f2) throws RemoteException {
        Parcel zza = zza();
        zza.writeFloat(f2);
        zzc(5, zza);
    }

    public final void zzC(float f2) throws RemoteException {
        Parcel zza = zza();
        zza.writeFloat(f2);
        zzc(9, zza);
    }

    public final boolean zzD(zzap zzap) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzap);
        Parcel zzJ = zzJ(15, zza);
        boolean zzh = zzc.zzh(zzJ);
        zzJ.recycle();
        return zzh;
    }

    public final boolean zzE() throws RemoteException {
        Parcel zzJ = zzJ(18, zza());
        boolean zzh = zzc.zzh(zzJ);
        zzJ.recycle();
        return zzh;
    }

    public final boolean zzF() throws RemoteException {
        Parcel zzJ = zzJ(14, zza());
        boolean zzh = zzc.zzh(zzJ);
        zzJ.recycle();
        return zzh;
    }

    public final boolean zzG() throws RemoteException {
        Parcel zzJ = zzJ(12, zza());
        boolean zzh = zzc.zzh(zzJ);
        zzJ.recycle();
        return zzh;
    }

    public final float zzd() throws RemoteException {
        Parcel zzJ = zzJ(6, zza());
        float readFloat = zzJ.readFloat();
        zzJ.recycle();
        return readFloat;
    }

    public final float zze() throws RemoteException {
        Parcel zzJ = zzJ(10, zza());
        float readFloat = zzJ.readFloat();
        zzJ.recycle();
        return readFloat;
    }

    public final int zzf() throws RemoteException {
        Parcel zzJ = zzJ(8, zza());
        int readInt = zzJ.readInt();
        zzJ.recycle();
        return readInt;
    }

    public final int zzg() throws RemoteException {
        Parcel zzJ = zzJ(24, zza());
        int readInt = zzJ.readInt();
        zzJ.recycle();
        return readInt;
    }

    public final int zzh() throws RemoteException {
        Parcel zzJ = zzJ(16, zza());
        int readInt = zzJ.readInt();
        zzJ.recycle();
        return readInt;
    }

    public final IObjectWrapper zzi() throws RemoteException {
        return a.e(zzJ(28, zza()));
    }

    public final Cap zzj() throws RemoteException {
        Parcel zzJ = zzJ(22, zza());
        Cap cap = (Cap) zzc.zza(zzJ, Cap.CREATOR);
        zzJ.recycle();
        return cap;
    }

    public final Cap zzk() throws RemoteException {
        Parcel zzJ = zzJ(20, zza());
        Cap cap = (Cap) zzc.zza(zzJ, Cap.CREATOR);
        zzJ.recycle();
        return cap;
    }

    public final String zzl() throws RemoteException {
        Parcel zzJ = zzJ(2, zza());
        String readString = zzJ.readString();
        zzJ.recycle();
        return readString;
    }

    public final List zzm() throws RemoteException {
        Parcel zzJ = zzJ(26, zza());
        ArrayList<PatternItem> createTypedArrayList = zzJ.createTypedArrayList(PatternItem.CREATOR);
        zzJ.recycle();
        return createTypedArrayList;
    }

    public final List zzn() throws RemoteException {
        Parcel zzJ = zzJ(4, zza());
        ArrayList<LatLng> createTypedArrayList = zzJ.createTypedArrayList(LatLng.CREATOR);
        zzJ.recycle();
        return createTypedArrayList;
    }

    public final List zzo() throws RemoteException {
        Parcel zzJ = zzJ(30, zza());
        ArrayList<StyleSpan> createTypedArrayList = zzJ.createTypedArrayList(StyleSpan.CREATOR);
        zzJ.recycle();
        return createTypedArrayList;
    }

    public final void zzp() throws RemoteException {
        zzc(1, zza());
    }

    public final void zzq(boolean z2) throws RemoteException {
        Parcel zza = zza();
        int i3 = zzc.zza;
        zza.writeInt(z2 ? 1 : 0);
        zzc(17, zza);
    }

    public final void zzr(int i3) throws RemoteException {
        Parcel zza = zza();
        zza.writeInt(i3);
        zzc(7, zza);
    }

    public final void zzs(Cap cap) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, cap);
        zzc(21, zza);
    }

    public final void zzt(boolean z2) throws RemoteException {
        Parcel zza = zza();
        int i3 = zzc.zza;
        zza.writeInt(z2 ? 1 : 0);
        zzc(13, zza);
    }

    public final void zzu(int i3) throws RemoteException {
        Parcel zza = zza();
        zza.writeInt(i3);
        zzc(23, zza);
    }

    public final void zzv(List list) throws RemoteException {
        Parcel zza = zza();
        zza.writeTypedList(list);
        zzc(25, zza);
    }

    public final void zzw(List list) throws RemoteException {
        Parcel zza = zza();
        zza.writeTypedList(list);
        zzc(3, zza);
    }

    public final void zzx(List list) throws RemoteException {
        Parcel zza = zza();
        zza.writeTypedList(list);
        zzc(29, zza);
    }

    public final void zzy(Cap cap) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, cap);
        zzc(19, zza);
    }

    public final void zzz(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, iObjectWrapper);
        zzc(27, zza);
    }
}
