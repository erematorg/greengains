package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.maps.zzm;
import com.google.android.gms.internal.maps.zzo;
import java.util.ArrayList;
import java.util.List;

public final class FeatureClickEvent {
    private final zzm zza;

    public FeatureClickEvent(zzm zzm) {
        this.zza = (zzm) Preconditions.checkNotNull(zzm);
    }

    @NonNull
    public List<Feature> getFeatures() {
        try {
            List<IBinder> zze = this.zza.zze();
            ArrayList arrayList = new ArrayList(zze.size());
            for (IBinder zzb : zze) {
                Feature zza2 = Feature.zza(zzo.zzb(zzb));
                if (zza2 != null) {
                    arrayList.add(zza2);
                }
            }
            return arrayList;
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    @NonNull
    public LatLng getLatLng() {
        try {
            return this.zza.zzd();
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }
}
