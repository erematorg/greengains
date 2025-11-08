package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzr extends zzb implements zzs {
    public static zzs zzb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.model.internal.IFeatureLayerDelegate");
        return queryLocalInterface instanceof zzs ? (zzs) queryLocalInterface : new zzq(iBinder);
    }
}
