package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzc;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public final class zzb extends zza implements ICameraUpdateFactoryDelegate {
    public zzb(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
    }

    public final IObjectWrapper newCameraPosition(CameraPosition cameraPosition) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, cameraPosition);
        return a.e(zzJ(7, zza));
    }

    public final IObjectWrapper newLatLng(LatLng latLng) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, latLng);
        return a.e(zzJ(8, zza));
    }

    public final IObjectWrapper newLatLngBounds(LatLngBounds latLngBounds, int i3) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, latLngBounds);
        zza.writeInt(i3);
        return a.e(zzJ(10, zza));
    }

    public final IObjectWrapper newLatLngBoundsWithSize(LatLngBounds latLngBounds, int i3, int i4, int i5) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, latLngBounds);
        zza.writeInt(i3);
        zza.writeInt(i4);
        zza.writeInt(i5);
        return a.e(zzJ(11, zza));
    }

    public final IObjectWrapper newLatLngZoom(LatLng latLng, float f2) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, latLng);
        zza.writeFloat(f2);
        return a.e(zzJ(9, zza));
    }

    public final IObjectWrapper scrollBy(float f2, float f3) throws RemoteException {
        Parcel zza = zza();
        zza.writeFloat(f2);
        zza.writeFloat(f3);
        return a.e(zzJ(3, zza));
    }

    public final IObjectWrapper zoomBy(float f2) throws RemoteException {
        Parcel zza = zza();
        zza.writeFloat(f2);
        return a.e(zzJ(5, zza));
    }

    public final IObjectWrapper zoomByWithFocus(float f2, int i3, int i4) throws RemoteException {
        Parcel zza = zza();
        zza.writeFloat(f2);
        zza.writeInt(i3);
        zza.writeInt(i4);
        return a.e(zzJ(6, zza));
    }

    public final IObjectWrapper zoomIn() throws RemoteException {
        return a.e(zzJ(1, zza()));
    }

    public final IObjectWrapper zoomOut() throws RemoteException {
        return a.e(zzJ(2, zza()));
    }

    public final IObjectWrapper zoomTo(float f2) throws RemoteException {
        Parcel zza = zza();
        zza.writeFloat(f2);
        return a.e(zzJ(4, zza));
    }
}
