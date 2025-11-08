package com.google.android.gms.location;

import android.location.Location;
import androidx.annotation.NonNull;

public interface LocationListener {
    void onLocationChanged(@NonNull Location location);
}
