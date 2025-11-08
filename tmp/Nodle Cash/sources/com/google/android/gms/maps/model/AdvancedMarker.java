package com.google.android.gms.maps.model;

import android.os.RemoteException;
import android.view.View;
import androidx.annotation.Nullable;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.maps.zzah;

public class AdvancedMarker extends Marker {
    public AdvancedMarker(zzah zzah) {
        super(zzah);
    }

    @Nullable
    public View getIconView() {
        try {
            return (View) ObjectWrapper.unwrap(this.zza.zzh());
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    public void setIconView(@Nullable View view) {
        if (view == null || view.getParent() == null) {
            try {
                this.zza.zzu(ObjectWrapper.wrap(view));
            } catch (RemoteException e3) {
                throw new RuntimeRemoteException(e3);
            }
        } else {
            throw new IllegalArgumentException("View already has a parent, can not be used as Marker");
        }
    }
}
