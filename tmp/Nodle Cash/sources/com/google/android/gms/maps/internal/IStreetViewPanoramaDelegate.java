package com.google.android.gms.maps.internal;

import android.os.IInterface;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;
import com.google.android.gms.maps.model.StreetViewSource;

public interface IStreetViewPanoramaDelegate extends IInterface {
    void animateTo(@NonNull StreetViewPanoramaCamera streetViewPanoramaCamera, long j2) throws RemoteException;

    void enablePanning(boolean z2) throws RemoteException;

    void enableStreetNames(boolean z2) throws RemoteException;

    void enableUserNavigation(boolean z2) throws RemoteException;

    void enableZoom(boolean z2) throws RemoteException;

    @NonNull
    StreetViewPanoramaCamera getPanoramaCamera() throws RemoteException;

    @NonNull
    StreetViewPanoramaLocation getStreetViewPanoramaLocation() throws RemoteException;

    boolean isPanningGesturesEnabled() throws RemoteException;

    boolean isStreetNamesEnabled() throws RemoteException;

    boolean isUserNavigationEnabled() throws RemoteException;

    boolean isZoomGesturesEnabled() throws RemoteException;

    @Nullable
    IObjectWrapper orientationToPoint(@NonNull StreetViewPanoramaOrientation streetViewPanoramaOrientation) throws RemoteException;

    @NonNull
    StreetViewPanoramaOrientation pointToOrientation(@NonNull IObjectWrapper iObjectWrapper) throws RemoteException;

    void setOnStreetViewPanoramaCameraChangeListener(@javax.annotation.Nullable zzbl zzbl) throws RemoteException;

    void setOnStreetViewPanoramaChangeListener(@javax.annotation.Nullable zzbn zzbn) throws RemoteException;

    void setOnStreetViewPanoramaClickListener(@javax.annotation.Nullable zzbp zzbp) throws RemoteException;

    void setOnStreetViewPanoramaLongClickListener(@javax.annotation.Nullable zzbr zzbr) throws RemoteException;

    void setPosition(@NonNull LatLng latLng) throws RemoteException;

    void setPositionWithID(@NonNull String str) throws RemoteException;

    void setPositionWithRadius(@NonNull LatLng latLng, int i3) throws RemoteException;

    void setPositionWithRadiusAndSource(@NonNull LatLng latLng, int i3, @javax.annotation.Nullable StreetViewSource streetViewSource) throws RemoteException;

    void setPositionWithSource(@NonNull LatLng latLng, @javax.annotation.Nullable StreetViewSource streetViewSource) throws RemoteException;
}
