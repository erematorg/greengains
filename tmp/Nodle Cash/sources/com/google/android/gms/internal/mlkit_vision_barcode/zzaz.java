package com.google.android.gms.internal.mlkit_vision_barcode;

import A.a;
import com.google.firebase.analytics.FirebaseAnalytics;
import javax.annotation.CheckForNull;

public final class zzaz {
    public static int zza(int i3, int i4, String str) {
        String str2;
        if (i3 >= 0 && i3 < i4) {
            return i3;
        }
        if (i3 < 0) {
            str2 = zzba.zzb("%s (%s) must not be negative", FirebaseAnalytics.Param.INDEX, Integer.valueOf(i3));
        } else if (i4 < 0) {
            throw new IllegalArgumentException(a.k("negative size: ", i4));
        } else {
            str2 = zzba.zzb("%s (%s) must be less than size (%s)", FirebaseAnalytics.Param.INDEX, Integer.valueOf(i3), Integer.valueOf(i4));
        }
        throw new IndexOutOfBoundsException(str2);
    }

    public static int zzb(int i3, int i4, String str) {
        if (i3 >= 0 && i3 <= i4) {
            return i3;
        }
        throw new IndexOutOfBoundsException(zzg(i3, i4, FirebaseAnalytics.Param.INDEX));
    }

    public static Object zzc(@CheckForNull Object obj, @CheckForNull Object obj2) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException("Executor was null.");
    }

    public static void zzd(boolean z2) {
        if (!z2) {
            throw new IllegalArgumentException();
        }
    }

    public static void zze(int i3, int i4, int i5) {
        if (i3 < 0 || i4 < i3 || i4 > i5) {
            throw new IndexOutOfBoundsException((i3 < 0 || i3 > i5) ? zzg(i3, i5, "start index") : (i4 < 0 || i4 > i5) ? zzg(i4, i5, "end index") : zzba.zzb("end index (%s) must not be less than start index (%s)", Integer.valueOf(i4), Integer.valueOf(i3)));
        }
    }

    public static void zzf(boolean z2, @CheckForNull Object obj) {
        if (!z2) {
            throw new IllegalStateException((String) obj);
        }
    }

    private static String zzg(int i3, int i4, String str) {
        if (i3 < 0) {
            return zzba.zzb("%s (%s) must not be negative", str, Integer.valueOf(i3));
        }
        if (i4 >= 0) {
            return zzba.zzb("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i3), Integer.valueOf(i4));
        }
        throw new IllegalArgumentException(a.k("negative size: ", i4));
    }
}
