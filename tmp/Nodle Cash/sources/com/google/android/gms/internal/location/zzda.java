package com.google.android.gms.internal.location;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.location.zzo;
import com.google.android.gms.tasks.Task;

public final class zzda extends GoogleApi implements SettingsClient {
    public static final /* synthetic */ int zza = 0;

    public zzda(@NonNull Activity activity) {
        super(activity, zzbi.zzb, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public final Task<LocationSettingsResponse> checkLocationSettings(LocationSettingsRequest locationSettingsRequest) {
        return doRead(TaskApiCall.builder().run(new zzdb(locationSettingsRequest)).setMethodKey(2426).build());
    }

    public final Task<Boolean> isGoogleLocationAccuracyEnabled() {
        return doRead(TaskApiCall.builder().run(zzdc.zza).setMethodKey(2444).setFeatures(zzo.zzm).build());
    }

    public zzda(@NonNull Context context) {
        super(context, zzbi.zzb, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }
}
