package com.google.android.gms.maps.model;

import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.maps.zzaj;
import com.google.android.gms.internal.maps.zzar;
import com.google.android.gms.internal.maps.zzs;
import java.util.HashMap;
import java.util.Map;

public final class FeatureLayer {
    private final zzs zza;
    @Nullable
    private StyleFactory zzb;
    private final Map zzc = new HashMap();

    public interface OnFeatureClickListener {
        void onFeatureClick(@NonNull FeatureClickEvent featureClickEvent);
    }

    public interface StyleFactory {
        @Nullable
        FeatureStyle getStyle(@NonNull Feature feature);
    }

    public FeatureLayer(zzs zzs) {
        this.zza = (zzs) Preconditions.checkNotNull(zzs);
    }

    public final void addOnFeatureClickListener(@NonNull OnFeatureClickListener onFeatureClickListener) {
        try {
            zze zze = new zze(this, onFeatureClickListener);
            this.zzc.put(onFeatureClickListener, zze);
            this.zza.zzf(zze);
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    @Nullable
    public String getDatasetId() {
        try {
            return this.zza.zzd();
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    @Nullable
    public StyleFactory getFeatureStyle() {
        return this.zzb;
    }

    @NonNull
    @FeatureType
    public String getFeatureType() {
        try {
            return this.zza.zze();
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    public boolean isAvailable() {
        try {
            return this.zza.zzi();
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    public final void removeOnFeatureClickListener(@NonNull OnFeatureClickListener onFeatureClickListener) {
        try {
            if (this.zzc.containsKey(onFeatureClickListener)) {
                this.zza.zzg((zzaj) this.zzc.get(onFeatureClickListener));
                this.zzc.remove(onFeatureClickListener);
            }
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    public void setFeatureStyle(@Nullable StyleFactory styleFactory) {
        this.zzb = styleFactory;
        if (styleFactory == null) {
            try {
                this.zza.zzh((zzar) null);
            } catch (RemoteException e3) {
                throw new RuntimeRemoteException(e3);
            }
        } else {
            try {
                this.zza.zzh(new zzd(this, styleFactory));
            } catch (RemoteException e4) {
                throw new RuntimeRemoteException(e4);
            }
        }
    }
}
