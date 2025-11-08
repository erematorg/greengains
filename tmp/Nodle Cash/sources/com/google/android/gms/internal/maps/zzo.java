package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzo extends zzb implements zzp {
    public static zzp zzb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.model.internal.IFeatureDelegate");
        return queryLocalInterface instanceof zzp ? (zzp) queryLocalInterface : new zzn(iBinder);
    }
}
