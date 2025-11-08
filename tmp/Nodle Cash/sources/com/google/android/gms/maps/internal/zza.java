package com.google.android.gms.maps.internal;

import androidx.annotation.Nullable;

public final class zza {
    public static byte zza(@Nullable Boolean bool) {
        if (bool != null) {
            return !bool.booleanValue() ? (byte) 0 : 1;
        }
        return -1;
    }

    @Nullable
    public static Boolean zzb(byte b3) {
        if (b3 == 0) {
            return Boolean.FALSE;
        }
        if (b3 != 1) {
            return null;
        }
        return Boolean.TRUE;
    }
}
