package com.google.android.gms.internal.location;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.tasks.Task;
import java.util.List;

public final class zzct extends GoogleApi implements GeofencingClient {
    public static final /* synthetic */ int zza = 0;

    public zzct(@NonNull Activity activity) {
        super(activity, zzbi.zzb, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public final Task<Void> addGeofences(GeofencingRequest geofencingRequest, PendingIntent pendingIntent) {
        return doWrite(TaskApiCall.builder().run(new zzcw(geofencingRequest, pendingIntent)).setMethodKey(2424).build());
    }

    public final Task<Void> removeGeofences(PendingIntent pendingIntent) {
        return doWrite(TaskApiCall.builder().run(new zzcu(pendingIntent)).setMethodKey(2425).build());
    }

    public zzct(@NonNull Context context) {
        super(context, zzbi.zzb, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public final Task<Void> removeGeofences(List<String> list) {
        return doWrite(TaskApiCall.builder().run(new zzcv(list)).setMethodKey(2425).build());
    }
}
