package com.google.android.recaptcha.internal;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;

final class zzgc {
    public static void zza(boolean z2, String str, long j2, long j3) {
        if (!z2) {
            StringBuilder i3 = a.i(j2, "overflow: ", str, "(");
            i3.append(", ");
            i3.append(j3);
            i3.append(")");
            throw new ArithmeticException(i3.toString());
        }
    }

    public static void zzb(boolean z2) {
        if (!z2) {
            throw new ArithmeticException("mode was UNNECESSARY, but rounding was necessary");
        }
    }
}
