package com.google.android.gms.maps.internal;

import android.os.IInterface;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.VisibleRegion;

public interface IProjectionDelegate extends IInterface {
    @NonNull
    LatLng fromScreenLocation(@NonNull IObjectWrapper iObjectWrapper) throws RemoteException;

    @NonNull
    VisibleRegion getVisibleRegion() throws RemoteException;

    @NonNull
    IObjectWrapper toScreenLocation(@NonNull LatLng latLng) throws RemoteException;

    @NonNull
    IObjectWrapper toScreenLocationWithAltitude(@NonNull LatLng latLng, float f2) throws RemoteException;
}
