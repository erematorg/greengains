package com.google.android.recaptcha.internal;

import com.google.common.base.Ascii;
import okio.Utf8;

final class zzlw {
    public static /* bridge */ /* synthetic */ void zza(byte b3, byte b4, byte b5, byte b6, char[] cArr, int i3) {
        if (!zze(b4)) {
            if ((((b4 + 112) + (b3 << Ascii.FS)) >> 30) == 0 && !zze(b5) && !zze(b6)) {
                byte b7 = b4 & Utf8.REPLACEMENT_BYTE;
                byte b8 = b5 & Utf8.REPLACEMENT_BYTE;
                byte b9 = ((b3 & 7) << Ascii.DC2) | (b7 << Ascii.FF) | (b8 << 6) | (b6 & Utf8.REPLACEMENT_BYTE);
                cArr[i3] = (char) ((b9 >>> 10) + Utf8.HIGH_SURROGATE_HEADER);
                cArr[i3 + 1] = (char) ((b9 & 1023) + 56320);
                return;
            }
        }
        throw zzje.zzd();
    }

    public static /* bridge */ /* synthetic */ void zzb(byte b3, byte b4, byte b5, char[] cArr, int i3) {
        if (!zze(b4)) {
            if (b3 == -32) {
                if (b4 >= -96) {
                    b3 = -32;
                }
            }
            if (b3 == -19) {
                if (b4 < -96) {
                    b3 = -19;
                }
            }
            if (!zze(b5)) {
                cArr[i3] = (char) (((b3 & Ascii.SI) << Ascii.FF) | ((b4 & Utf8.REPLACEMENT_BYTE) << 6) | (b5 & Utf8.REPLACEMENT_BYTE));
                return;
            }
        }
        throw zzje.zzd();
    }

    public static /* bridge */ /* synthetic */ void zzc(byte b3, byte b4, char[] cArr, int i3) {
        if (b3 < -62 || zze(b4)) {
            throw zzje.zzd();
        }
        cArr[i3] = (char) (((b3 & 31) << 6) | (b4 & Utf8.REPLACEMENT_BYTE));
    }

    public static /* bridge */ /* synthetic */ boolean zzd(byte b3) {
        return b3 >= 0;
    }

    private static boolean zze(byte b3) {
        return b3 > -65;
    }
}
