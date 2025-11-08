package com.google.android.gms.maps;

import android.graphics.Point;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public final class CameraUpdateFactory {
    private static ICameraUpdateFactoryDelegate zza;

    private CameraUpdateFactory() {
    }

    @NonNull
    public static CameraUpdate newCameraPosition(@NonNull CameraPosition cameraPosition) {
        Preconditions.checkNotNull(cameraPosition, "cameraPosition must not be null");
        try {
            return new CameraUpdate(zzb().newCameraPosition(cameraPosition));
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    @NonNull
    public static CameraUpdate newLatLng(@NonNull LatLng latLng) {
        Preconditions.checkNotNull(latLng, "latLng must not be null");
        try {
            return new CameraUpdate(zzb().newLatLng(latLng));
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    @NonNull
    public static CameraUpdate newLatLngBounds(@NonNull LatLngBounds latLngBounds, int i3) {
        Preconditions.checkNotNull(latLngBounds, "bounds must not be null");
        try {
            return new CameraUpdate(zzb().newLatLngBounds(latLngBounds, i3));
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    @NonNull
    public static CameraUpdate newLatLngZoom(@NonNull LatLng latLng, float f2) {
        Preconditions.checkNotNull(latLng, "latLng must not be null");
        try {
            return new CameraUpdate(zzb().newLatLngZoom(latLng, f2));
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    @NonNull
    public static CameraUpdate scrollBy(float f2, float f3) {
        try {
            return new CameraUpdate(zzb().scrollBy(f2, f3));
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    @NonNull
    public static CameraUpdate zoomBy(float f2) {
        try {
            return new CameraUpdate(zzb().zoomBy(f2));
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    @NonNull
    public static CameraUpdate zoomIn() {
        try {
            return new CameraUpdate(zzb().zoomIn());
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    @NonNull
    public static CameraUpdate zoomOut() {
        try {
            return new CameraUpdate(zzb().zoomOut());
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    @NonNull
    public static CameraUpdate zoomTo(float f2) {
        try {
            return new CameraUpdate(zzb().zoomTo(f2));
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    public static void zza(@NonNull ICameraUpdateFactoryDelegate iCameraUpdateFactoryDelegate) {
        zza = (ICameraUpdateFactoryDelegate) Preconditions.checkNotNull(iCameraUpdateFactoryDelegate);
    }

    private static ICameraUpdateFactoryDelegate zzb() {
        return (ICameraUpdateFactoryDelegate) Preconditions.checkNotNull(zza, "CameraUpdateFactory is not initialized");
    }

    @NonNull
    public static CameraUpdate zoomBy(float f2, @NonNull Point point) {
        Preconditions.checkNotNull(point, "focus must not be null");
        try {
            return new CameraUpdate(zzb().zoomByWithFocus(f2, point.x, point.y));
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    @NonNull
    public static CameraUpdate newLatLngBounds(@NonNull LatLngBounds latLngBounds, int i3, int i4, int i5) {
        Preconditions.checkNotNull(latLngBounds, "bounds must not be null");
        try {
            return new CameraUpdate(zzb().newLatLngBoundsWithSize(latLngBounds, i3, i4, i5));
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }
}
