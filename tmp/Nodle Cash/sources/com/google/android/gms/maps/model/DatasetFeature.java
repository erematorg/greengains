package com.google.android.gms.maps.model;

import android.os.RemoteException;
import androidx.annotation.NonNull;
import com.google.android.gms.internal.maps.zzbl;
import com.google.android.gms.internal.maps.zzp;
import java.util.Map;

public final class DatasetFeature extends Feature {
    private final zzp zza;

    public DatasetFeature(zzp zzp) {
        super(zzp);
        this.zza = zzp;
    }

    @NonNull
    public Map<String, String> getDatasetAttributes() {
        try {
            return zzbl.zzc(this.zza.zzh().entrySet());
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    @NonNull
    public String getDatasetId() {
        try {
            return this.zza.zze();
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }
}
