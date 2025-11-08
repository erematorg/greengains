package com.google.android.gms.maps;

import android.content.Context;
import android.os.RemoteException;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.dynamic.DeferredLifecycleHelper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.OnDelegateCreatedListener;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.internal.IMapViewDelegate;
import com.google.android.gms.maps.internal.zzcc;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.List;

@VisibleForTesting
final class zzai extends DeferredLifecycleHelper {
    protected OnDelegateCreatedListener zza;
    private final ViewGroup zzb;
    private final Context zzc;
    @Nullable
    private final GoogleMapOptions zzd;
    private final List zze = new ArrayList();

    @VisibleForTesting
    public zzai(ViewGroup viewGroup, Context context, @Nullable GoogleMapOptions googleMapOptions) {
        this.zzb = viewGroup;
        this.zzc = context;
        this.zzd = googleMapOptions;
    }

    public final void createDelegate(OnDelegateCreatedListener onDelegateCreatedListener) {
        this.zza = onDelegateCreatedListener;
        zzb();
    }

    public final void zza(OnMapReadyCallback onMapReadyCallback) {
        if (getDelegate() != null) {
            ((zzah) getDelegate()).getMapAsync(onMapReadyCallback);
        } else {
            this.zze.add(onMapReadyCallback);
        }
    }

    public final void zzb() {
        if (this.zza != null && getDelegate() == null) {
            try {
                MapsInitializer.initialize(this.zzc);
                IMapViewDelegate zzg = zzcc.zza(this.zzc, (MapsInitializer.Renderer) null).zzg(ObjectWrapper.wrap(this.zzc), this.zzd);
                if (zzg != null) {
                    this.zza.onDelegateCreated(new zzah(this.zzb, zzg));
                    for (OnMapReadyCallback mapAsync : this.zze) {
                        ((zzah) getDelegate()).getMapAsync(mapAsync);
                    }
                    this.zze.clear();
                }
            } catch (RemoteException e3) {
                throw new RuntimeRemoteException(e3);
            } catch (GooglePlayServicesNotAvailableException unused) {
            }
        }
    }
}
