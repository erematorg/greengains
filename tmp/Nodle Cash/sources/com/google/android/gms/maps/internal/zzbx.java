package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzc;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;
import com.google.android.gms.maps.model.StreetViewSource;

public final class zzbx extends zza implements IStreetViewPanoramaDelegate {
    public zzbx(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
    }

    public final void animateTo(StreetViewPanoramaCamera streetViewPanoramaCamera, long j2) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, streetViewPanoramaCamera);
        zza.writeLong(j2);
        zzc(9, zza);
    }

    public final void enablePanning(boolean z2) throws RemoteException {
        Parcel zza = zza();
        int i3 = zzc.zza;
        zza.writeInt(z2 ? 1 : 0);
        zzc(2, zza);
    }

    public final void enableStreetNames(boolean z2) throws RemoteException {
        Parcel zza = zza();
        int i3 = zzc.zza;
        zza.writeInt(z2 ? 1 : 0);
        zzc(4, zza);
    }

    public final void enableUserNavigation(boolean z2) throws RemoteException {
        Parcel zza = zza();
        int i3 = zzc.zza;
        zza.writeInt(z2 ? 1 : 0);
        zzc(3, zza);
    }

    public final void enableZoom(boolean z2) throws RemoteException {
        Parcel zza = zza();
        int i3 = zzc.zza;
        zza.writeInt(z2 ? 1 : 0);
        zzc(1, zza);
    }

    public final StreetViewPanoramaCamera getPanoramaCamera() throws RemoteException {
        Parcel zzJ = zzJ(10, zza());
        StreetViewPanoramaCamera streetViewPanoramaCamera = (StreetViewPanoramaCamera) zzc.zza(zzJ, StreetViewPanoramaCamera.CREATOR);
        zzJ.recycle();
        return streetViewPanoramaCamera;
    }

    public final StreetViewPanoramaLocation getStreetViewPanoramaLocation() throws RemoteException {
        Parcel zzJ = zzJ(14, zza());
        StreetViewPanoramaLocation streetViewPanoramaLocation = (StreetViewPanoramaLocation) zzc.zza(zzJ, StreetViewPanoramaLocation.CREATOR);
        zzJ.recycle();
        return streetViewPanoramaLocation;
    }

    public final boolean isPanningGesturesEnabled() throws RemoteException {
        Parcel zzJ = zzJ(6, zza());
        boolean zzh = zzc.zzh(zzJ);
        zzJ.recycle();
        return zzh;
    }

    public final boolean isStreetNamesEnabled() throws RemoteException {
        Parcel zzJ = zzJ(8, zza());
        boolean zzh = zzc.zzh(zzJ);
        zzJ.recycle();
        return zzh;
    }

    public final boolean isUserNavigationEnabled() throws RemoteException {
        Parcel zzJ = zzJ(7, zza());
        boolean zzh = zzc.zzh(zzJ);
        zzJ.recycle();
        return zzh;
    }

    public final boolean isZoomGesturesEnabled() throws RemoteException {
        Parcel zzJ = zzJ(5, zza());
        boolean zzh = zzc.zzh(zzJ);
        zzJ.recycle();
        return zzh;
    }

    public final IObjectWrapper orientationToPoint(StreetViewPanoramaOrientation streetViewPanoramaOrientation) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, streetViewPanoramaOrientation);
        return a.e(zzJ(19, zza));
    }

    public final StreetViewPanoramaOrientation pointToOrientation(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, iObjectWrapper);
        Parcel zzJ = zzJ(18, zza);
        StreetViewPanoramaOrientation streetViewPanoramaOrientation = (StreetViewPanoramaOrientation) zzc.zza(zzJ, StreetViewPanoramaOrientation.CREATOR);
        zzJ.recycle();
        return streetViewPanoramaOrientation;
    }

    public final void setOnStreetViewPanoramaCameraChangeListener(zzbl zzbl) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzbl);
        zzc(16, zza);
    }

    public final void setOnStreetViewPanoramaChangeListener(zzbn zzbn) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzbn);
        zzc(15, zza);
    }

    public final void setOnStreetViewPanoramaClickListener(zzbp zzbp) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzbp);
        zzc(17, zza);
    }

    public final void setOnStreetViewPanoramaLongClickListener(zzbr zzbr) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzbr);
        zzc(20, zza);
    }

    public final void setPosition(LatLng latLng) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, latLng);
        zzc(12, zza);
    }

    public final void setPositionWithID(String str) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzc(11, zza);
    }

    public final void setPositionWithRadius(LatLng latLng, int i3) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, latLng);
        zza.writeInt(i3);
        zzc(13, zza);
    }

    public final void setPositionWithRadiusAndSource(LatLng latLng, int i3, StreetViewSource streetViewSource) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, latLng);
        zza.writeInt(i3);
        zzc.zze(zza, streetViewSource);
        zzc(22, zza);
    }

    public final void setPositionWithSource(LatLng latLng, StreetViewSource streetViewSource) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, latLng);
        zzc.zze(zza, streetViewSource);
        zzc(21, zza);
    }
}
