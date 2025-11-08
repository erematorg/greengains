package com.google.android.recaptcha.internal;

import javax.annotation.CheckForNull;

public final class zzff {
    public static void zza(boolean z2) {
        if (!z2) {
            throw new IllegalArgumentException();
        }
    }

    public static void zzb(boolean z2, @CheckForNull Object obj) {
        if (!z2) {
            throw new IllegalArgumentException((String) obj);
        }
    }

    public static void zzc(boolean z2, String str, char c3) {
        if (!z2) {
            throw new IllegalArgumentException(zzfi.zza(str, Character.valueOf(c3)));
        }
    }

    public static void zzd(int i3, int i4, int i5) {
        if (i3 < 0 || i4 < i3 || i4 > i5) {
            throw new IndexOutOfBoundsException((i3 < 0 || i3 > i5) ? zzf(i3, i5, "start index") : (i4 < 0 || i4 > i5) ? zzf(i4, i5, "end index") : zzfi.zza("end index (%s) must not be less than start index (%s)", Integer.valueOf(i4), Integer.valueOf(i3)));
        }
    }

    public static void zze(boolean z2, @CheckForNull Object obj) {
        if (!z2) {
            throw new IllegalStateException((String) obj);
        }
    }

    private static String zzf(int i3, int i4, String str) {
        return i3 < 0 ? zzfi.zza("%s (%s) must not be negative", str, Integer.valueOf(i3)) : zzfi.zza("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i3), Integer.valueOf(i4));
    }
}
