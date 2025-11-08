package com.google.android.gms.common;

import android.content.Intent;
import androidx.annotation.NonNull;

public class GooglePlayServicesRepairableException extends UserRecoverableException {
    private final int zza;

    public GooglePlayServicesRepairableException(int i3, @NonNull String str, @NonNull Intent intent) {
        super(str, intent);
        this.zza = i3;
    }

    public int getConnectionStatusCode() {
        return this.zza;
    }
}
