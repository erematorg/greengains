package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.measurement.zzdj;
import com.google.android.gms.internal.measurement.zzdl;
import com.google.android.gms.internal.measurement.zzdq;
import com.google.android.gms.internal.measurement.zzdr;
import com.google.android.gms.internal.measurement.zzdt;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

@DynamiteApi
public class AppMeasurementDynamiteService extends zzdj {
    @VisibleForTesting
    zzhw zza = null;
    @GuardedBy("listenerMap")
    private final Map<Integer, zzjj> zzb = new ArrayMap();

    public class zza implements zzjj {
        private zzdq zza;

        public zza(zzdq zzdq) {
            this.zza = zzdq;
        }

        public final void onEvent(String str, String str2, Bundle bundle, long j2) {
            try {
                this.zza.zza(str, str2, bundle, j2);
            } catch (RemoteException e3) {
                zzhw zzhw = AppMeasurementDynamiteService.this.zza;
                if (zzhw != null) {
                    zzhw.zzj().zzu().zza("Event listener threw exception", e3);
                }
            }
        }
    }

    public class zzb implements zzjg {
        private zzdq zza;

        public zzb(zzdq zzdq) {
            this.zza = zzdq;
        }

        public final void interceptEvent(String str, String str2, Bundle bundle, long j2) {
            try {
                this.zza.zza(str, str2, bundle, j2);
            } catch (RemoteException e3) {
                zzhw zzhw = AppMeasurementDynamiteService.this.zza;
                if (zzhw != null) {
                    zzhw.zzj().zzu().zza("Event interceptor threw exception", e3);
                }
            }
        }
    }

    @EnsuresNonNull({"scion"})
    private final void zza() {
        if (this.zza == null) {
            throw new IllegalStateException("Attempting to perform action before initialize.");
        }
    }

    public void beginAdUnitExposure(@NonNull String str, long j2) throws RemoteException {
        zza();
        this.zza.zze().zza(str, j2);
    }

    public void clearConditionalUserProperty(@NonNull String str, @NonNull String str2, @NonNull Bundle bundle) throws RemoteException {
        zza();
        this.zza.zzp().zza(str, str2, bundle);
    }

    public void clearMeasurementEnabled(long j2) throws RemoteException {
        zza();
        this.zza.zzp().zza((Boolean) null);
    }

    public void endAdUnitExposure(@NonNull String str, long j2) throws RemoteException {
        zza();
        this.zza.zze().zzb(str, j2);
    }

    public void generateEventId(zzdl zzdl) throws RemoteException {
        zza();
        long zzm = this.zza.zzt().zzm();
        zza();
        this.zza.zzt().zza(zzdl, zzm);
    }

    public void getAppInstanceId(zzdl zzdl) throws RemoteException {
        zza();
        this.zza.zzl().zzb((Runnable) new zzj(this, zzdl));
    }

    public void getCachedAppInstanceId(zzdl zzdl) throws RemoteException {
        zza();
        zza(zzdl, this.zza.zzp().zzag());
    }

    public void getConditionalUserProperties(String str, String str2, zzdl zzdl) throws RemoteException {
        zza();
        this.zza.zzl().zzb((Runnable) new zzn(this, zzdl, str, str2));
    }

    public void getCurrentScreenClass(zzdl zzdl) throws RemoteException {
        zza();
        zza(zzdl, this.zza.zzp().zzah());
    }

    public void getCurrentScreenName(zzdl zzdl) throws RemoteException {
        zza();
        zza(zzdl, this.zza.zzp().zzai());
    }

    public void getGmpAppId(zzdl zzdl) throws RemoteException {
        zza();
        zza(zzdl, this.zza.zzp().zzaj());
    }

    public void getMaxUserProperties(String str, zzdl zzdl) throws RemoteException {
        zza();
        this.zza.zzp();
        zzjk.zza(str);
        zza();
        this.zza.zzt().zza(zzdl, 25);
    }

    public void getSessionId(zzdl zzdl) throws RemoteException {
        zza();
        this.zza.zzp().zza(zzdl);
    }

    public void getTestFlag(zzdl zzdl, int i3) throws RemoteException {
        zza();
        if (i3 == 0) {
            this.zza.zzt().zza(zzdl, this.zza.zzp().zzak());
        } else if (i3 == 1) {
            this.zza.zzt().zza(zzdl, this.zza.zzp().zzaf().longValue());
        } else if (i3 == 2) {
            zzop zzt = this.zza.zzt();
            double doubleValue = this.zza.zzp().zzad().doubleValue();
            Bundle bundle = new Bundle();
            bundle.putDouble("r", doubleValue);
            try {
                zzdl.zza(bundle);
            } catch (RemoteException e3) {
                zzt.zzu.zzj().zzu().zza("Error returning double value to wrapper", e3);
            }
        } else if (i3 == 3) {
            this.zza.zzt().zza(zzdl, this.zza.zzp().zzae().intValue());
        } else if (i3 == 4) {
            this.zza.zzt().zza(zzdl, this.zza.zzp().zzac().booleanValue());
        }
    }

    public void getUserProperties(String str, String str2, boolean z2, zzdl zzdl) throws RemoteException {
        zza();
        this.zza.zzl().zzb((Runnable) new zzl(this, zzdl, str, str2, z2));
    }

    public void initForTests(@NonNull Map map) throws RemoteException {
        zza();
    }

    public void initialize(IObjectWrapper iObjectWrapper, zzdt zzdt, long j2) throws RemoteException {
        zzhw zzhw = this.zza;
        if (zzhw == null) {
            this.zza = zzhw.zza((Context) Preconditions.checkNotNull((Context) ObjectWrapper.unwrap(iObjectWrapper)), zzdt, Long.valueOf(j2));
        } else {
            zzhw.zzj().zzu().zza("Attempting to initialize multiple times");
        }
    }

    public void isDataCollectionEnabled(zzdl zzdl) throws RemoteException {
        zza();
        this.zza.zzl().zzb((Runnable) new zzm(this, zzdl));
    }

    public void logEvent(@NonNull String str, @NonNull String str2, @NonNull Bundle bundle, boolean z2, boolean z3, long j2) throws RemoteException {
        zza();
        this.zza.zzp().zza(str, str2, bundle, z2, z3, j2);
    }

    public void logEventAndBundle(String str, String str2, Bundle bundle, zzdl zzdl, long j2) throws RemoteException {
        Bundle bundle2;
        zza();
        Preconditions.checkNotEmpty(str2);
        if (bundle == null) {
            bundle2 = new Bundle();
        }
        bundle2.putString("_o", "app");
        this.zza.zzl().zzb((Runnable) new zzi(this, zzdl, new zzbh(str2, new zzbc(bundle), "app", j2), str));
    }

    public void logHealthData(int i3, @NonNull String str, @NonNull IObjectWrapper iObjectWrapper, @NonNull IObjectWrapper iObjectWrapper2, @NonNull IObjectWrapper iObjectWrapper3) throws RemoteException {
        zza();
        Object obj = null;
        Object unwrap = iObjectWrapper == null ? null : ObjectWrapper.unwrap(iObjectWrapper);
        Object unwrap2 = iObjectWrapper2 == null ? null : ObjectWrapper.unwrap(iObjectWrapper2);
        if (iObjectWrapper3 != null) {
            obj = ObjectWrapper.unwrap(iObjectWrapper3);
        }
        this.zza.zzj().zza(i3, true, false, str, unwrap, unwrap2, obj);
    }

    public void onActivityCreated(@NonNull IObjectWrapper iObjectWrapper, @NonNull Bundle bundle, long j2) throws RemoteException {
        zza();
        Application.ActivityLifecycleCallbacks zzaa = this.zza.zzp().zzaa();
        if (zzaa != null) {
            this.zza.zzp().zzao();
            zzaa.onActivityCreated((Activity) ObjectWrapper.unwrap(iObjectWrapper), bundle);
        }
    }

    public void onActivityDestroyed(@NonNull IObjectWrapper iObjectWrapper, long j2) throws RemoteException {
        zza();
        Application.ActivityLifecycleCallbacks zzaa = this.zza.zzp().zzaa();
        if (zzaa != null) {
            this.zza.zzp().zzao();
            zzaa.onActivityDestroyed((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    public void onActivityPaused(@NonNull IObjectWrapper iObjectWrapper, long j2) throws RemoteException {
        zza();
        Application.ActivityLifecycleCallbacks zzaa = this.zza.zzp().zzaa();
        if (zzaa != null) {
            this.zza.zzp().zzao();
            zzaa.onActivityPaused((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    public void onActivityResumed(@NonNull IObjectWrapper iObjectWrapper, long j2) throws RemoteException {
        zza();
        Application.ActivityLifecycleCallbacks zzaa = this.zza.zzp().zzaa();
        if (zzaa != null) {
            this.zza.zzp().zzao();
            zzaa.onActivityResumed((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    public void onActivitySaveInstanceState(IObjectWrapper iObjectWrapper, zzdl zzdl, long j2) throws RemoteException {
        zza();
        Application.ActivityLifecycleCallbacks zzaa = this.zza.zzp().zzaa();
        Bundle bundle = new Bundle();
        if (zzaa != null) {
            this.zza.zzp().zzao();
            zzaa.onActivitySaveInstanceState((Activity) ObjectWrapper.unwrap(iObjectWrapper), bundle);
        }
        try {
            zzdl.zza(bundle);
        } catch (RemoteException e3) {
            this.zza.zzj().zzu().zza("Error returning bundle value to wrapper", e3);
        }
    }

    public void onActivityStarted(@NonNull IObjectWrapper iObjectWrapper, long j2) throws RemoteException {
        zza();
        Application.ActivityLifecycleCallbacks zzaa = this.zza.zzp().zzaa();
        if (zzaa != null) {
            this.zza.zzp().zzao();
            zzaa.onActivityStarted((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    public void onActivityStopped(@NonNull IObjectWrapper iObjectWrapper, long j2) throws RemoteException {
        zza();
        Application.ActivityLifecycleCallbacks zzaa = this.zza.zzp().zzaa();
        if (zzaa != null) {
            this.zza.zzp().zzao();
            zzaa.onActivityStopped((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    public void performAction(Bundle bundle, zzdl zzdl, long j2) throws RemoteException {
        zza();
        zzdl.zza((Bundle) null);
    }

    public void registerOnMeasurementEventListener(zzdq zzdq) throws RemoteException {
        zzjj zzjj;
        zza();
        synchronized (this.zzb) {
            try {
                zzjj = this.zzb.get(Integer.valueOf(zzdq.zza()));
                if (zzjj == null) {
                    zzjj = new zza(zzdq);
                    this.zzb.put(Integer.valueOf(zzdq.zza()), zzjj);
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        this.zza.zzp().zza(zzjj);
    }

    public void resetAnalyticsData(long j2) throws RemoteException {
        zza();
        this.zza.zzp().zza(j2);
    }

    public void setConditionalUserProperty(@NonNull Bundle bundle, long j2) throws RemoteException {
        zza();
        if (bundle == null) {
            this.zza.zzj().zzg().zza("Conditional user property must not be null");
        } else {
            this.zza.zzp().zzb(bundle, j2);
        }
    }

    public void setConsent(@NonNull Bundle bundle, long j2) throws RemoteException {
        zza();
        this.zza.zzp().zzc(bundle, j2);
    }

    public void setConsentThirdParty(@NonNull Bundle bundle, long j2) throws RemoteException {
        zza();
        this.zza.zzp().zzd(bundle, j2);
    }

    public void setCurrentScreen(@NonNull IObjectWrapper iObjectWrapper, @NonNull String str, @NonNull String str2, long j2) throws RemoteException {
        zza();
        this.zza.zzq().zza((Activity) ObjectWrapper.unwrap(iObjectWrapper), str, str2);
    }

    public void setDataCollectionEnabled(boolean z2) throws RemoteException {
        zza();
        this.zza.zzp().zzc(z2);
    }

    public void setDefaultEventParameters(@NonNull Bundle bundle) {
        zza();
        this.zza.zzp().zzd(bundle);
    }

    public void setDefaultEventParametersWithBackfill(@NonNull Bundle bundle) {
        zza();
        this.zza.zzp().zze(bundle);
    }

    public void setEventInterceptor(zzdq zzdq) throws RemoteException {
        zza();
        zzb zzb2 = new zzb(zzdq);
        if (this.zza.zzl().zzg()) {
            this.zza.zzp().zza((zzjg) zzb2);
        } else {
            this.zza.zzl().zzb((Runnable) new zzk(this, zzb2));
        }
    }

    public void setInstanceIdProvider(zzdr zzdr) throws RemoteException {
        zza();
    }

    public void setMeasurementEnabled(boolean z2, long j2) throws RemoteException {
        zza();
        this.zza.zzp().zza(Boolean.valueOf(z2));
    }

    public void setMinimumSessionDuration(long j2) throws RemoteException {
        zza();
    }

    public void setSessionTimeoutDuration(long j2) throws RemoteException {
        zza();
        this.zza.zzp().zzc(j2);
    }

    public void setSgtmDebugInfo(@NonNull Intent intent) throws RemoteException {
        zza();
        this.zza.zzp().zza(intent);
    }

    public void setUserId(@NonNull String str, long j2) throws RemoteException {
        zza();
        this.zza.zzp().zza(str, j2);
    }

    public void setUserProperty(@NonNull String str, @NonNull String str2, @NonNull IObjectWrapper iObjectWrapper, boolean z2, long j2) throws RemoteException {
        zza();
        this.zza.zzp().zza(str, str2, ObjectWrapper.unwrap(iObjectWrapper), z2, j2);
    }

    public void unregisterOnMeasurementEventListener(zzdq zzdq) throws RemoteException {
        zzjj remove;
        zza();
        synchronized (this.zzb) {
            remove = this.zzb.remove(Integer.valueOf(zzdq.zza()));
        }
        if (remove == null) {
            remove = new zza(zzdq);
        }
        this.zza.zzp().zzb(remove);
    }

    private final void zza(zzdl zzdl, String str) {
        zza();
        this.zza.zzt().zza(zzdl, str);
    }
}
