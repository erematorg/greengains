package com.google.android.gms.auth;

import android.content.Intent;
import androidx.annotation.Nullable;

public class GooglePlayServicesAvailabilityException extends UserRecoverableAuthException {
    private final int zza;

    public GooglePlayServicesAvailabilityException(int i3, @Nullable String str, @Nullable Intent intent) {
        super(str, intent);
        this.zza = i3;
    }

    public int getConnectionStatusCode() {
        return this.zza;
    }
}
