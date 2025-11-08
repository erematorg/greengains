package com.google.android.gms.maps;

import android.location.Location;
import android.os.RemoteException;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.internal.zzaj;
import com.google.android.gms.maps.model.RuntimeRemoteException;

final class zzl implements LocationSource.OnLocationChangedListener {
    final /* synthetic */ zzaj zza;

    public zzl(zzt zzt, zzaj zzaj) {
        this.zza = zzaj;
    }

    public final void onLocationChanged(Location location) {
        try {
            this.zza.zzd(location);
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }
}
