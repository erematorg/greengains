package com.google.android.gms.location;

import com.google.android.gms.common.internal.Preconditions;

public final class zzar {
    public static int zza(int i3) {
        boolean z2 = true;
        if (!(i3 == 0 || i3 == 1)) {
            if (i3 == 2) {
                i3 = 2;
            } else {
                z2 = false;
            }
        }
        Preconditions.checkArgument(z2, "throttle behavior %d must be a ThrottleBehavior.THROTTLE_* constant", Integer.valueOf(i3));
        return i3;
    }

    public static String zzb(int i3) {
        if (i3 == 0) {
            return "THROTTLE_BACKGROUND";
        }
        if (i3 == 1) {
            return "THROTTLE_ALWAYS";
        }
        if (i3 == 2) {
            return "THROTTLE_NEVER";
        }
        throw new IllegalArgumentException();
    }
}
