package com.google.android.gms.internal.mlkit_common;

import javax.annotation.CheckForNull;

public final class zzs extends zzl {
    public static boolean zza(@CheckForNull Object obj, @CheckForNull Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj != null) {
            return obj.equals(obj2);
        }
        return false;
    }
}
