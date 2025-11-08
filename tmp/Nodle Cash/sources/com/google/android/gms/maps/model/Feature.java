package com.google.android.gms.maps.model;

import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.maps.zzp;

public abstract class Feature {
    private final zzp zza;

    public Feature(zzp zzp) {
        this.zza = zzp;
    }

    @Nullable
    public static Feature zza(zzp zzp) {
        Preconditions.checkNotNull(zzp);
        try {
            int zzd = zzp.zzd();
            if (zzd == 1) {
                return new PlaceFeature(zzp);
            }
            if (zzd == 2) {
                return new DatasetFeature(zzp);
            }
            return null;
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    @NonNull
    @FeatureType
    public String getFeatureType() {
        try {
            return this.zza.zzf();
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }
}
