package com.google.android.gms.location;

import androidx.annotation.NonNull;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;

@Deprecated
public interface SettingsApi {
    @NonNull
    PendingResult<LocationSettingsResult> checkLocationSettings(@NonNull GoogleApiClient googleApiClient, @NonNull LocationSettingsRequest locationSettingsRequest);
}
