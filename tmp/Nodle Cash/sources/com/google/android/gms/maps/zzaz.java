package com.google.android.gms.maps;

import android.app.Activity;
import android.os.RemoteException;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;
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
final class zzaz extends DeferredLifecycleHelper {
    protected OnDelegateCreatedListener zza;
    private final Fragment zzb;
    private Activity zzc;
    private final List zzd = new ArrayList();

    @VisibleForTesting
    public zzaz(Fragment fragment) {
        this.zzb = fragment;
    }

    public static /* synthetic */ void zza(zzaz zzaz, Activity activity) {
        zzaz.zzc = activity;
        zzaz.zzc();
    }

    public final void createDelegate(OnDelegateCreatedListener onDelegateCreatedListener) {
        this.zza = onDelegateCreatedListener;
        zzc();
    }

    public final void zzb(OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
        if (getDelegate() != null) {
            ((zzay) getDelegate()).getStreetViewPanoramaAsync(onStreetViewPanoramaReadyCallback);
        } else {
            this.zzd.add(onStreetViewPanoramaReadyCallback);
        }
    }

    public final void zzc() {
        if (this.zzc != null && this.zza != null && getDelegate() == null) {
            try {
                MapsInitializer.initialize(this.zzc);
                this.zza.onDelegateCreated(new zzay(this.zzb, zzcc.zza(this.zzc, (MapsInitializer.Renderer) null).zzh(ObjectWrapper.wrap(this.zzc))));
                for (OnStreetViewPanoramaReadyCallback streetViewPanoramaAsync : this.zzd) {
                    ((zzay) getDelegate()).getStreetViewPanoramaAsync(streetViewPanoramaAsync);
                }
                this.zzd.clear();
            } catch (RemoteException e3) {
                throw new RuntimeRemoteException(e3);
            } catch (GooglePlayServicesNotAvailableException unused) {
            }
        }
    }
}
