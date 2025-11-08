package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.common.base.Ascii;
import okio.Utf8;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzamp  reason: invalid package */
final class zzamp {
    private static boolean zza(byte b3) {
        return b3 > -65;
    }

    public static /* synthetic */ void zza(byte b3, byte b4, byte b5, byte b6, char[] cArr, int i3) {
        if (!zza(b4)) {
            if ((((b4 + 112) + (b3 << Ascii.FS)) >> 30) == 0 && !zza(b5) && !zza(b6)) {
                byte b7 = ((b3 & 7) << Ascii.DC2) | ((b4 & Utf8.REPLACEMENT_BYTE) << Ascii.FF) | ((b5 & Utf8.REPLACEMENT_BYTE) << 6) | (b6 & Utf8.REPLACEMENT_BYTE);
                cArr[i3] = (char) ((b7 >>> 10) + Utf8.HIGH_SURROGATE_HEADER);
                cArr[i3 + 1] = (char) ((b7 & 1023) + 56320);
                return;
            }
        }
        throw zzajk.zzd();
    }

    public static /* synthetic */ void zza(byte b3, char[] cArr, int i3) {
        cArr[i3] = (char) b3;
    }

    public static /* synthetic */ void zza(byte b3, byte b4, byte b5, char[] cArr, int i3) {
        if (zza(b4) || ((b3 == -32 && b4 < -96) || ((b3 == -19 && b4 >= -96) || zza(b5)))) {
            throw zzajk.zzd();
        }
        cArr[i3] = (char) (((b3 & Ascii.SI) << Ascii.FF) | ((b4 & Utf8.REPLACEMENT_BYTE) << 6) | (b5 & Utf8.REPLACEMENT_BYTE));
    }

    public static /* synthetic */ void zza(byte b3, byte b4, char[] cArr, int i3) {
        if (b3 < -62 || zza(b4)) {
            throw zzajk.zzd();
        }
        cArr[i3] = (char) (((b3 & 31) << 6) | (b4 & Utf8.REPLACEMENT_BYTE));
    }
}
