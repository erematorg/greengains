package com.google.android.gms.maps.model;

import android.os.RemoteException;
import androidx.annotation.NonNull;
import com.google.android.gms.internal.maps.zzp;

public final class PlaceFeature extends Feature {
    private final zzp zza;

    public PlaceFeature(zzp zzp) {
        super(zzp);
        this.zza = zzp;
    }

    @NonNull
    public String getPlaceId() {
        try {
            return this.zza.zzg();
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }
}
