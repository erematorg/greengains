package com.google.android.gms.internal.p002firebaseauthapi;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbi  reason: invalid package */
final class zzbi {
    public static void zza(boolean z2, String str, long j2, long j3) {
        if (!z2) {
            StringBuilder i3 = a.i(j2, "overflow: ", str, "(");
            i3.append(", ");
            i3.append(j3);
            i3.append(")");
            throw new ArithmeticException(i3.toString());
        }
    }
}
