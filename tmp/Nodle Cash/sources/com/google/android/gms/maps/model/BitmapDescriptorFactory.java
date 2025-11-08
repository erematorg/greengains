package com.google.android.gms.maps.model;

import android.graphics.Bitmap;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.maps.zzi;

public final class BitmapDescriptorFactory {
    public static final float HUE_AZURE = 210.0f;
    public static final float HUE_BLUE = 240.0f;
    public static final float HUE_CYAN = 180.0f;
    public static final float HUE_GREEN = 120.0f;
    public static final float HUE_MAGENTA = 300.0f;
    public static final float HUE_ORANGE = 30.0f;
    public static final float HUE_RED = 0.0f;
    public static final float HUE_ROSE = 330.0f;
    public static final float HUE_VIOLET = 270.0f;
    public static final float HUE_YELLOW = 60.0f;
    private static zzi zza;

    private BitmapDescriptorFactory() {
    }

    @NonNull
    public static BitmapDescriptor defaultMarker() {
        try {
            return new BitmapDescriptor(zzb().zzd());
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    @NonNull
    public static BitmapDescriptor fromAsset(@NonNull String str) {
        Preconditions.checkNotNull(str, "assetName must not be null");
        try {
            return new BitmapDescriptor(zzb().zzf(str));
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    @NonNull
    public static BitmapDescriptor fromBitmap(@NonNull Bitmap bitmap) {
        Preconditions.checkNotNull(bitmap, "image must not be null");
        try {
            return new BitmapDescriptor(zzb().zzg(bitmap));
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    @NonNull
    public static BitmapDescriptor fromFile(@NonNull String str) {
        Preconditions.checkNotNull(str, "fileName must not be null");
        try {
            return new BitmapDescriptor(zzb().zzh(str));
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    @NonNull
    public static BitmapDescriptor fromPath(@NonNull String str) {
        Preconditions.checkNotNull(str, "absolutePath must not be null");
        try {
            return new BitmapDescriptor(zzb().zzi(str));
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    @NonNull
    public static BitmapDescriptor fromPinConfig(@NonNull PinConfig pinConfig) {
        try {
            return new BitmapDescriptor(zzb().zzj(pinConfig));
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    @NonNull
    public static BitmapDescriptor fromResource(int i3) {
        try {
            return new BitmapDescriptor(zzb().zzk(i3));
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    public static void zza(zzi zzi) {
        if (zza == null) {
            zza = (zzi) Preconditions.checkNotNull(zzi, "delegate must not be null");
        }
    }

    private static zzi zzb() {
        return (zzi) Preconditions.checkNotNull(zza, "IBitmapDescriptorFactory is not initialized");
    }

    @NonNull
    public static BitmapDescriptor defaultMarker(float f2) {
        try {
            return new BitmapDescriptor(zzb().zze(f2));
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }
}
