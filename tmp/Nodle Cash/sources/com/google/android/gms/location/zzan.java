package com.google.android.gms.location;

import com.google.android.gms.common.internal.Preconditions;

public final class zzan {
    public static int zza(int i3) {
        boolean z2 = true;
        if (!(i3 == 100 || i3 == 102 || i3 == 104)) {
            if (i3 == 105) {
                i3 = 105;
            } else {
                z2 = false;
            }
        }
        Preconditions.checkArgument(z2, "priority %d must be a Priority.PRIORITY_* constant", Integer.valueOf(i3));
        return i3;
    }

    public static String zzb(int i3) {
        if (i3 == 100) {
            return "HIGH_ACCURACY";
        }
        if (i3 == 102) {
            return "BALANCED_POWER_ACCURACY";
        }
        if (i3 == 104) {
            return "LOW_POWER";
        }
        if (i3 == 105) {
            return "PASSIVE";
        }
        throw new IllegalArgumentException();
    }
}
