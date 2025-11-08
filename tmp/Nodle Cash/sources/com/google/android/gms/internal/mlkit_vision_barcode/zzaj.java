package com.google.android.gms.internal.mlkit_vision_barcode;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzaj extends zza implements IInterface {
    public zzaj(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetector");
    }

    public final void zzd() throws RemoteException {
        zzc(3, zza());
    }

    public final zzu[] zze(IObjectWrapper iObjectWrapper, zzan zzan) throws RemoteException {
        Parcel zza = zza();
        zzc.zzb(zza, iObjectWrapper);
        zzc.zza(zza, zzan);
        Parcel zzb = zzb(1, zza);
        zzu[] zzuArr = (zzu[]) zzb.createTypedArray(zzu.CREATOR);
        zzb.recycle();
        return zzuArr;
    }

    public final zzu[] zzf(IObjectWrapper iObjectWrapper, zzan zzan) throws RemoteException {
        Parcel zza = zza();
        zzc.zzb(zza, iObjectWrapper);
        zzc.zza(zza, zzan);
        Parcel zzb = zzb(2, zza);
        zzu[] zzuArr = (zzu[]) zzb.createTypedArray(zzu.CREATOR);
        zzb.recycle();
        return zzuArr;
    }
}
