package com.google.android.gms.internal.p002firebaseauthapi;

import android.content.Context;
import androidx.annotation.Nullable;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesUtilLight;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaco  reason: invalid package */
public final class zzaco {
    @Nullable
    private static Boolean zza;

    public static boolean zza(Context context) {
        if (zza == null) {
            int isGooglePlayServicesAvailable = GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context, GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
            zza = Boolean.valueOf(isGooglePlayServicesAvailable == 0 || isGooglePlayServicesAvailable == 2);
        }
        return zza.booleanValue();
    }
}
