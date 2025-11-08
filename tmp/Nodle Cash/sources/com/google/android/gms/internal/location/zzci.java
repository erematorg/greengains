package com.google.android.gms.internal.location;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.RegistrationMethods;
import com.google.android.gms.location.DeviceOrientationListener;
import com.google.android.gms.location.DeviceOrientationRequest;
import com.google.android.gms.location.FusedOrientationProviderClient;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.Executor;

public final class zzci extends GoogleApi implements FusedOrientationProviderClient {
    public static final /* synthetic */ int zza = 0;

    public zzci(Activity activity) {
        super(activity, zzbi.zzb, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public final String getApiFallbackAttributionTag(Context context) {
        return null;
    }

    public final Task<Void> removeOrientationUpdates(DeviceOrientationListener deviceOrientationListener) {
        return doUnregisterEventListener(ListenerHolders.createListenerKey(deviceOrientationListener, "DeviceOrientationListener"), 2440).continueWith(zzcm.zza, zzck.zza);
    }

    public final Task<Void> requestOrientationUpdates(DeviceOrientationRequest deviceOrientationRequest, Executor executor, DeviceOrientationListener deviceOrientationListener) {
        ListenerHolder createListenerHolder = ListenerHolders.createListenerHolder(deviceOrientationListener, executor, "DeviceOrientationListener");
        zzcl zzcl = new zzcl(createListenerHolder, deviceOrientationRequest);
        return doRegisterEventListener(RegistrationMethods.builder().register(zzcl).unregister(new zzcj(createListenerHolder)).withHolder(createListenerHolder).setMethodKey(2434).build());
    }

    public zzci(Context context) {
        super(context, zzbi.zzb, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }
}
