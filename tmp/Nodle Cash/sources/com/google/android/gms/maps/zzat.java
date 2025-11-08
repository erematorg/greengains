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
import com.google.android.gms.maps.internal.zzcc;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.List;

@VisibleForTesting
final class zzat extends DeferredLifecycleHelper {
    protected OnDelegateCreatedListener zza;
    private final ViewGroup zzb;
    private final Context zzc;
    @Nullable
    private final StreetViewPanoramaOptions zzd;
    private final List zze = new ArrayList();

    @VisibleForTesting
    public zzat(ViewGroup viewGroup, Context context, @Nullable StreetViewPanoramaOptions streetViewPanoramaOptions) {
        this.zzb = viewGroup;
        this.zzc = context;
        this.zzd = streetViewPanoramaOptions;
    }

    public final void createDelegate(OnDelegateCreatedListener onDelegateCreatedListener) {
        this.zza = onDelegateCreatedListener;
        zzb();
    }

    public final void zza(OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
        if (getDelegate() != null) {
            ((zzas) getDelegate()).getStreetViewPanoramaAsync(onStreetViewPanoramaReadyCallback);
        } else {
            this.zze.add(onStreetViewPanoramaReadyCallback);
        }
    }

    public final void zzb() {
        if (this.zza != null && getDelegate() == null) {
            try {
                MapsInitializer.initialize(this.zzc);
                this.zza.onDelegateCreated(new zzas(this.zzb, zzcc.zza(this.zzc, (MapsInitializer.Renderer) null).zzi(ObjectWrapper.wrap(this.zzc), this.zzd)));
                for (OnStreetViewPanoramaReadyCallback streetViewPanoramaAsync : this.zze) {
                    ((zzas) getDelegate()).getStreetViewPanoramaAsync(streetViewPanoramaAsync);
                }
                this.zze.clear();
            } catch (RemoteException e3) {
                throw new RuntimeRemoteException(e3);
            } catch (GooglePlayServicesNotAvailableException unused) {
            }
        }
    }
}
