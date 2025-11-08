package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.maps.zzae;

public final class MapCapabilities {
    private final zzae zza;

    public MapCapabilities(zzae zzae) {
        this.zza = (zzae) Preconditions.checkNotNull(zzae);
    }

    public boolean isAdvancedMarkersAvailable() {
        try {
            return this.zza.zzd();
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    public boolean isDataDrivenStylingAvailable() {
        try {
            return this.zza.zze();
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }
}
