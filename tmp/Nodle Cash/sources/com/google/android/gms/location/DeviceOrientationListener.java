package com.google.android.gms.location;

import androidx.annotation.NonNull;

public interface DeviceOrientationListener {
    void onDeviceOrientationChanged(@NonNull DeviceOrientation deviceOrientation);
}
