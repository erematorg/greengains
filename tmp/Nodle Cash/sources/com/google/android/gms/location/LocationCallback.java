package com.google.android.gms.location;

import androidx.annotation.NonNull;

public abstract class LocationCallback {
    public void onLocationAvailability(@NonNull LocationAvailability locationAvailability) {
    }

    public void onLocationResult(@NonNull LocationResult locationResult) {
    }
}
