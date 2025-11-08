package com.google.android.gms.internal.maps;

import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.PinConfig;

public final class zzg extends zza implements zzi {
    public zzg(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
    }

    public final IObjectWrapper zzd() throws RemoteException {
        return a.e(zzJ(4, zza()));
    }

    public final IObjectWrapper zze(float f2) throws RemoteException {
        Parcel zza = zza();
        zza.writeFloat(f2);
        return a.e(zzJ(5, zza));
    }

    public final IObjectWrapper zzf(String str) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        return a.e(zzJ(2, zza));
    }

    public final IObjectWrapper zzg(Bitmap bitmap) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, bitmap);
        return a.e(zzJ(6, zza));
    }

    public final IObjectWrapper zzh(String str) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        return a.e(zzJ(3, zza));
    }

    public final IObjectWrapper zzi(String str) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        return a.e(zzJ(7, zza));
    }

    public final IObjectWrapper zzj(PinConfig pinConfig) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, pinConfig);
        return a.e(zzJ(8, zza));
    }

    public final IObjectWrapper zzk(int i3) throws RemoteException {
        Parcel zza = zza();
        zza.writeInt(i3);
        return a.e(zzJ(1, zza));
    }
}
