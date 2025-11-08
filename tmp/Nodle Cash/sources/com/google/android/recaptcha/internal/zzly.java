package com.google.android.recaptcha.internal;

import com.google.common.base.Ascii;

final class zzly extends zzlx {
    public final int zza(int i3, byte[] bArr, int i4, int i5) {
        while (r9 < i5 && bArr[r9] >= 0) {
            i4 = r9 + 1;
        }
        if (r9 >= i5) {
            return 0;
        }
        while (r9 < i5) {
            int i6 = r9 + 1;
            byte b3 = bArr[r9];
            if (b3 < 0) {
                if (b3 < -32) {
                    if (i6 >= i5) {
                        return b3;
                    }
                    if (b3 >= -62) {
                        r9 += 2;
                        if (bArr[i6] > -65) {
                        }
                    }
                } else if (b3 < -16) {
                    if (i6 >= i5 - 1) {
                        return zzma.zza(bArr, i6, i5);
                    }
                    int i7 = r9 + 2;
                    byte b4 = bArr[i6];
                    if (b4 <= -65 && ((b3 != -32 || b4 >= -96) && (b3 != -19 || b4 < -96))) {
                        r9 += 3;
                        if (bArr[i7] > -65) {
                        }
                    }
                } else if (i6 >= i5 - 2) {
                    return zzma.zza(bArr, i6, i5);
                } else {
                    int i8 = r9 + 2;
                    byte b5 = bArr[i6];
                    if (b5 <= -65) {
                        if ((((b5 + 112) + (b3 << Ascii.FS)) >> 30) == 0) {
                            int i9 = r9 + 3;
                            if (bArr[i8] <= -65) {
                                r9 += 4;
                                if (bArr[i9] > -65) {
                                }
                            }
                        }
                    }
                }
                return -1;
            }
            r9 = i6;
        }
        return 0;
    }
}
