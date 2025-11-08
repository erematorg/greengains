package com.google.android.recaptcha.internal;

import android.content.Context;
import android.os.Build;
import androidx.annotation.ChecksSdkIntAtLeast;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import org.jetbrains.annotations.NotNull;

public final class zzaf {
    @NotNull
    public static final zzaf zza = new zzaf();
    @NotNull
    @ChecksSdkIntAtLeast(extension = 0)
    private static final String zzb = String.valueOf(Build.VERSION.SDK_INT);
    @NotNull
    private static final GoogleApiAvailabilityLight zzc = GoogleApiAvailabilityLight.getInstance();

    private zzaf() {
    }

    @NotNull
    public static final String zza(@NotNull Context context) {
        int isGooglePlayServicesAvailable = zzc.isGooglePlayServicesAvailable(context);
        return (isGooglePlayServicesAvailable == 1 || isGooglePlayServicesAvailable == 3 || isGooglePlayServicesAvailable == 9) ? "ANDROID_OFFPLAY" : "ANDROID_ONPLAY";
    }
}
