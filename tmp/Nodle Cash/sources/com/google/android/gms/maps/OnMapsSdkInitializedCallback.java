package com.google.android.gms.maps;

import androidx.annotation.NonNull;
import com.google.android.gms.maps.MapsInitializer;

public interface OnMapsSdkInitializedCallback {
    void onMapsSdkInitialized(@NonNull MapsInitializer.Renderer renderer);
}
