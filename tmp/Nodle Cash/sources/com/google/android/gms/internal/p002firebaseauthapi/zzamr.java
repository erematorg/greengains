package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.common.base.Ascii;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzamr  reason: invalid package */
final class zzamr extends zzamo {
    public final int zza(String str, byte[] bArr, int i3, int i4) {
        int i5;
        int i6;
        char charAt;
        int length = str.length();
        int i7 = i4 + i3;
        int i8 = 0;
        while (i8 < length && (i6 = i8 + i3) < i7 && (charAt = str.charAt(i8)) < 128) {
            bArr[i6] = (byte) charAt;
            i8++;
        }
        if (i8 == length) {
            return i3 + length;
        }
        int i9 = i3 + i8;
        while (i8 < length) {
            char charAt2 = str.charAt(i8);
            if (charAt2 < 128 && i9 < i7) {
                bArr[i9] = (byte) charAt2;
                i9++;
            } else if (charAt2 < 2048 && i9 <= i7 - 2) {
                int i10 = i9 + 1;
                bArr[i9] = (byte) ((charAt2 >>> 6) | 960);
                i9 += 2;
                bArr[i10] = (byte) ((charAt2 & '?') | 128);
            } else if ((charAt2 < 55296 || 57343 < charAt2) && i9 <= i7 - 3) {
                bArr[i9] = (byte) ((charAt2 >>> 12) | 480);
                int i11 = i9 + 2;
                bArr[i9 + 1] = (byte) (((charAt2 >>> 6) & 63) | 128);
                i9 += 3;
                bArr[i11] = (byte) ((charAt2 & '?') | 128);
            } else if (i9 <= i7 - 4) {
                int i12 = i8 + 1;
                if (i12 != str.length()) {
                    char charAt3 = str.charAt(i12);
                    if (Character.isSurrogatePair(charAt2, charAt3)) {
                        int codePoint = Character.toCodePoint(charAt2, charAt3);
                        bArr[i9] = (byte) ((codePoint >>> 18) | 240);
                        bArr[i9 + 1] = (byte) (((codePoint >>> 12) & 63) | 128);
                        int i13 = i9 + 3;
                        bArr[i9 + 2] = (byte) (((codePoint >>> 6) & 63) | 128);
                        i9 += 4;
                        bArr[i13] = (byte) ((codePoint & 63) | 128);
                        i8 = i12;
                    } else {
                        i8 = i12;
                    }
                }
                throw new zzamq(i8 - 1, length);
            } else if (55296 > charAt2 || charAt2 > 57343 || ((i5 = i8 + 1) != str.length() && Character.isSurrogatePair(charAt2, str.charAt(i5)))) {
                throw new ArrayIndexOutOfBoundsException("Failed writing " + charAt2 + " at index " + i9);
            } else {
                throw new zzamq(i8, length);
            }
            i8++;
        }
        return i9;
    }

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
            if (b3 >= 0) {
                r9 = i6;
            } else if (b3 < -32) {
                if (i6 >= i5) {
                    return b3;
                }
                if (b3 >= -62) {
                    r9 += 2;
                    if (bArr[i6] > -65) {
                    }
                }
                return -1;
            } else if (b3 < -16) {
                if (i6 >= i5 - 1) {
                    return zzamn.zza(bArr, i6, i5);
                }
                int i7 = r9 + 2;
                byte b4 = bArr[i6];
                if (b4 <= -65 && ((b3 != -32 || b4 >= -96) && (b3 != -19 || b4 < -96))) {
                    r9 += 3;
                    if (bArr[i7] > -65) {
                    }
                }
                return -1;
            } else if (i6 >= i5 - 2) {
                return zzamn.zza(bArr, i6, i5);
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
                return -1;
            }
        }
        return 0;
    }

    public final String zza(byte[] bArr, int i3, int i4) throws zzajk {
        if ((i3 | i4 | ((bArr.length - i3) - i4)) >= 0) {
            int i5 = i3 + i4;
            char[] cArr = new char[i4];
            int i6 = 0;
            while (r10 < i5) {
                byte b3 = bArr[r10];
                if (b3 < 0) {
                    break;
                }
                i3 = r10 + 1;
                zzamp.zza(b3, cArr, i6);
                i6++;
            }
            int i7 = i6;
            while (r10 < i5) {
                int i8 = r10 + 1;
                byte b4 = bArr[r10];
                if (b4 >= 0) {
                    int i9 = i7 + 1;
                    zzamp.zza(b4, cArr, i7);
                    while (i8 < i5) {
                        byte b5 = bArr[i8];
                        if (b5 < 0) {
                            break;
                        }
                        i8++;
                        zzamp.zza(b5, cArr, i9);
                        i9++;
                    }
                    i7 = i9;
                    r10 = i8;
                } else if (b4 < -32) {
                    if (i8 < i5) {
                        r10 += 2;
                        zzamp.zza(b4, bArr[i8], cArr, i7);
                        i7++;
                    } else {
                        throw zzajk.zzd();
                    }
                } else if (b4 < -16) {
                    if (i8 < i5 - 1) {
                        int i10 = r10 + 2;
                        r10 += 3;
                        zzamp.zza(b4, bArr[i8], bArr[i10], cArr, i7);
                        i7++;
                    } else {
                        throw zzajk.zzd();
                    }
                } else if (i8 < i5 - 2) {
                    byte b6 = bArr[i8];
                    int i11 = r10 + 3;
                    byte b7 = bArr[r10 + 2];
                    r10 += 4;
                    zzamp.zza(b4, b6, b7, bArr[i11], cArr, i7);
                    i7 += 2;
                } else {
                    throw zzajk.zzd();
                }
            }
            return new String(cArr, 0, i7);
        }
        throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(i3), Integer.valueOf(i4)}));
    }
}
