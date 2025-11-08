package com.google.android.gms.internal.mlkit_common;

import A.a;
import com.google.firebase.analytics.FirebaseAnalytics;
import javax.annotation.CheckForNull;

public final class zzt {
    public static int zza(int i3, int i4, String str) {
        String str2;
        if (i3 >= 0 && i3 < i4) {
            return i3;
        }
        if (i3 < 0) {
            str2 = zzu.zza("%s (%s) must not be negative", FirebaseAnalytics.Param.INDEX, Integer.valueOf(i3));
        } else if (i4 < 0) {
            throw new IllegalArgumentException(a.k("negative size: ", i4));
        } else {
            str2 = zzu.zza("%s (%s) must be less than size (%s)", FirebaseAnalytics.Param.INDEX, Integer.valueOf(i3), Integer.valueOf(i4));
        }
        throw new IndexOutOfBoundsException(str2);
    }

    public static int zzb(int i3, int i4, String str) {
        if (i3 >= 0 && i3 <= i4) {
            return i3;
        }
        throw new IndexOutOfBoundsException(zzf(i3, i4, FirebaseAnalytics.Param.INDEX));
    }

    public static Object zzc(@CheckForNull Object obj, @CheckForNull Object obj2) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException((String) obj2);
    }

    public static void zzd(int i3, int i4, int i5) {
        if (i3 < 0 || i4 < i3 || i4 > i5) {
            throw new IndexOutOfBoundsException((i3 < 0 || i3 > i5) ? zzf(i3, i5, "start index") : (i4 < 0 || i4 > i5) ? zzf(i4, i5, "end index") : zzu.zza("end index (%s) must not be less than start index (%s)", Integer.valueOf(i4), Integer.valueOf(i3)));
        }
    }

    public static void zze(boolean z2, @CheckForNull Object obj) {
        if (!z2) {
            throw new IllegalStateException("A SourcePolicy can only set internal() or external() once.");
        }
    }

    private static String zzf(int i3, int i4, String str) {
        if (i3 < 0) {
            return zzu.zza("%s (%s) must not be negative", str, Integer.valueOf(i3));
        }
        if (i4 >= 0) {
            return zzu.zza("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i3), Integer.valueOf(i4));
        }
        throw new IllegalArgumentException(a.k("negative size: ", i4));
    }
}
