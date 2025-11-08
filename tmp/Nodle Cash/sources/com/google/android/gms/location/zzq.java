package com.google.android.gms.location;

import com.google.android.gms.common.internal.Preconditions;

public final class zzq {
    public static int zza(int i3) {
        boolean z2 = true;
        if (!(i3 == 0 || i3 == 1)) {
            if (i3 == 2) {
                i3 = 2;
            } else {
                z2 = false;
            }
        }
        Preconditions.checkArgument(z2, "granularity %d must be a Granularity.GRANULARITY_* constant", Integer.valueOf(i3));
        return i3;
    }

    public static String zzb(int i3) {
        if (i3 == 0) {
            return "GRANULARITY_PERMISSION_LEVEL";
        }
        if (i3 == 1) {
            return "GRANULARITY_COARSE";
        }
        if (i3 == 2) {
            return "GRANULARITY_FINE";
        }
        throw new IllegalArgumentException();
    }
}
