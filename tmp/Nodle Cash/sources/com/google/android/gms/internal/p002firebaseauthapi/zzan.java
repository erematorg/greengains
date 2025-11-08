package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzan  reason: invalid package */
public class zzan<E> {
    public static int zza(int i3, int i4) {
        if (i4 >= 0) {
            int i5 = i3 + (i3 >> 1) + 1;
            if (i5 < i4) {
                i5 = Integer.highestOneBit(i4 - 1) << 1;
            }
            if (i5 < 0) {
                return Integer.MAX_VALUE;
            }
            return i5;
        }
        throw new AssertionError("cannot store more than MAX_VALUE elements");
    }
}
