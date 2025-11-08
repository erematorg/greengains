package com.google.android.gms.internal.p002firebaseauthapi;

import A.a;
import com.google.firebase.analytics.FirebaseAnalytics;
import javax.annotation.CheckForNull;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzz  reason: invalid package */
public final class zzz {
    public static int zza(int i3, int i4) {
        String str;
        if (i3 >= 0 && i3 < i4) {
            return i3;
        }
        if (i3 < 0) {
            str = zzah.zza("%s (%s) must not be negative", FirebaseAnalytics.Param.INDEX, Integer.valueOf(i3));
        } else if (i4 < 0) {
            throw new IllegalArgumentException(a.k("negative size: ", i4));
        } else {
            str = zzah.zza("%s (%s) must be less than size (%s)", FirebaseAnalytics.Param.INDEX, Integer.valueOf(i3), Integer.valueOf(i4));
        }
        throw new IndexOutOfBoundsException(str);
    }

    public static int zzb(int i3, int i4) {
        if (i3 >= 0 && i3 <= i4) {
            return i3;
        }
        throw new IndexOutOfBoundsException(zzb(i3, i4, FirebaseAnalytics.Param.INDEX));
    }

    private static String zzb(int i3, int i4, String str) {
        if (i3 < 0) {
            return zzah.zza("%s (%s) must not be negative", str, Integer.valueOf(i3));
        }
        if (i4 >= 0) {
            return zzah.zza("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i3), Integer.valueOf(i4));
        }
        throw new IllegalArgumentException(a.k("negative size: ", i4));
    }

    public static int zza(int i3, int i4, String str) {
        if (i3 >= 0 && i3 <= i4) {
            return i3;
        }
        throw new IndexOutOfBoundsException(zzb(i3, i4, str));
    }

    public static <T> T zza(@CheckForNull T t2) {
        t2.getClass();
        return t2;
    }

    public static void zza(int i3, int i4, int i5) {
        String str;
        if (i3 < 0 || i4 < i3 || i4 > i5) {
            if (i3 < 0 || i3 > i5) {
                str = zzb(i3, i5, "start index");
            } else if (i4 < 0 || i4 > i5) {
                str = zzb(i4, i5, "end index");
            } else {
                str = zzah.zza("end index (%s) must not be less than start index (%s)", Integer.valueOf(i4), Integer.valueOf(i3));
            }
            throw new IndexOutOfBoundsException(str);
        }
    }
}
