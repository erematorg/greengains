package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.compose.animation.core.a;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzamn  reason: invalid package */
final class zzamn {
    private static final zzamo zza = new zzamr();

    static {
        if (zzamm.zzc()) {
            boolean zzd = zzamm.zzd();
        }
    }

    public static /* synthetic */ int zza(byte[] bArr, int i3, int i4) {
        byte b3 = bArr[i3 - 1];
        int i5 = i4 - i3;
        if (i5 != 0) {
            if (i5 == 1) {
                byte b4 = bArr[i3];
                if (b3 > -12 || b4 > -65) {
                    return -1;
                }
                return (b4 << 8) ^ b3;
            } else if (i5 == 2) {
                byte b5 = bArr[i3];
                byte b6 = bArr[i3 + 1];
                if (b3 > -12 || b5 > -65 || b6 > -65) {
                    return -1;
                }
                return (b6 << 16) ^ ((b5 << 8) ^ b3);
            } else {
                throw new AssertionError();
            }
        } else if (b3 > -12) {
            return -1;
        } else {
            return b3;
        }
    }

    public static String zzb(byte[] bArr, int i3, int i4) throws zzajk {
        return zza.zza(bArr, i3, i4);
    }

    public static boolean zzc(byte[] bArr, int i3, int i4) {
        return zza.zzb(bArr, i3, i4);
    }

    public static int zza(String str, byte[] bArr, int i3, int i4) {
        return zza.zza(str, bArr, i3, i4);
    }

    public static int zza(String str) {
        int length = str.length();
        int i3 = 0;
        int i4 = 0;
        while (i4 < length && str.charAt(i4) < 128) {
            i4++;
        }
        int i5 = length;
        while (true) {
            if (i4 >= length) {
                break;
            }
            char charAt = str.charAt(i4);
            if (charAt < 2048) {
                i5 += (127 - charAt) >>> 31;
                i4++;
            } else {
                int length2 = str.length();
                while (i4 < length2) {
                    char charAt2 = str.charAt(i4);
                    if (charAt2 < 2048) {
                        i3 += (127 - charAt2) >>> 31;
                    } else {
                        i3 += 2;
                        if (55296 <= charAt2 && charAt2 <= 57343) {
                            if (Character.codePointAt(str, i4) >= 65536) {
                                i4++;
                            } else {
                                throw new zzamq(i4, length2);
                            }
                        }
                    }
                    i4++;
                }
                i5 += i3;
            }
        }
        if (i5 >= length) {
            return i5;
        }
        throw new IllegalArgumentException(a.s("UTF-8 length does not fit in int: ", ((long) i5) + 4294967296L));
    }

    public static boolean zza(byte[] bArr) {
        return zza.zzb(bArr, 0, bArr.length);
    }
}
