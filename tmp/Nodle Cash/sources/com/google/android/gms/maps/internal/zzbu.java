package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzc;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.VisibleRegion;

public final class zzbu extends zza implements IProjectionDelegate {
    public zzbu(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IProjectionDelegate");
    }

    public final LatLng fromScreenLocation(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, iObjectWrapper);
        Parcel zzJ = zzJ(1, zza);
        LatLng latLng = (LatLng) zzc.zza(zzJ, LatLng.CREATOR);
        zzJ.recycle();
        return latLng;
    }

    public final VisibleRegion getVisibleRegion() throws RemoteException {
        Parcel zzJ = zzJ(3, zza());
        VisibleRegion visibleRegion = (VisibleRegion) zzc.zza(zzJ, VisibleRegion.CREATOR);
        zzJ.recycle();
        return visibleRegion;
    }

    public final IObjectWrapper toScreenLocation(LatLng latLng) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, latLng);
        return a.e(zzJ(2, zza));
    }

    public final IObjectWrapper toScreenLocationWithAltitude(LatLng latLng, float f2) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, latLng);
        zza.writeFloat(f2);
        return a.e(zzJ(4, zza));
    }
}
