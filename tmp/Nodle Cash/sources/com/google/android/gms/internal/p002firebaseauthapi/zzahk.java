package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.common.base.Ascii;
import java.io.IOException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzahk  reason: invalid package */
final class zzahk {
    public static double zza(byte[] bArr, int i3) {
        return Double.longBitsToDouble(zzd(bArr, i3));
    }

    public static float zzb(byte[] bArr, int i3) {
        return Float.intBitsToFloat(zzc(bArr, i3));
    }

    public static int zzc(byte[] bArr, int i3) {
        return ((bArr[i3 + 3] & 255) << Ascii.CAN) | (bArr[i3] & 255) | ((bArr[i3 + 1] & 255) << 8) | ((bArr[i3 + 2] & 255) << 16);
    }

    public static int zzd(byte[] bArr, int i3, zzahn zzahn) {
        int i4 = i3 + 1;
        long j2 = (long) bArr[i3];
        if (j2 >= 0) {
            zzahn.zzb = j2;
            return i4;
        }
        int i5 = i3 + 2;
        byte b3 = bArr[i4];
        long j3 = (j2 & 127) | (((long) (b3 & Byte.MAX_VALUE)) << 7);
        int i6 = 7;
        while (b3 < 0) {
            int i7 = i5 + 1;
            byte b4 = bArr[i5];
            i6 += 7;
            j3 |= ((long) (b4 & Byte.MAX_VALUE)) << i6;
            int i8 = i7;
            b3 = b4;
            i5 = i8;
        }
        zzahn.zzb = j3;
        return i5;
    }

    public static int zza(byte[] bArr, int i3, zzahn zzahn) throws zzajk {
        int zzc = zzc(bArr, i3, zzahn);
        int i4 = zzahn.zza;
        if (i4 < 0) {
            throw zzajk.zzf();
        } else if (i4 > bArr.length - zzc) {
            throw zzajk.zzi();
        } else if (i4 == 0) {
            zzahn.zzc = zzaho.zza;
            return zzc;
        } else {
            zzahn.zzc = zzaho.zza(bArr, zzc, i4);
            return zzc + i4;
        }
    }

    public static int zzb(byte[] bArr, int i3, zzahn zzahn) throws zzajk {
        int zzc = zzc(bArr, i3, zzahn);
        int i4 = zzahn.zza;
        if (i4 < 0) {
            throw zzajk.zzf();
        } else if (i4 == 0) {
            zzahn.zzc = "";
            return zzc;
        } else {
            zzahn.zzc = zzamn.zzb(bArr, zzc, i4);
            return zzc + i4;
        }
    }

    public static int zzc(byte[] bArr, int i3, zzahn zzahn) {
        int i4 = i3 + 1;
        byte b3 = bArr[i3];
        if (b3 < 0) {
            return zza((int) b3, bArr, i4, zzahn);
        }
        zzahn.zza = b3;
        return i4;
    }

    public static long zzd(byte[] bArr, int i3) {
        return ((((long) bArr[i3 + 7]) & 255) << 56) | (((long) bArr[i3]) & 255) | ((((long) bArr[i3 + 1]) & 255) << 8) | ((((long) bArr[i3 + 2]) & 255) << 16) | ((((long) bArr[i3 + 3]) & 255) << 24) | ((((long) bArr[i3 + 4]) & 255) << 32) | ((((long) bArr[i3 + 5]) & 255) << 40) | ((((long) bArr[i3 + 6]) & 255) << 48);
    }

    public static int zza(zzalh zzalh, byte[] bArr, int i3, int i4, int i5, zzahn zzahn) throws IOException {
        Object zza = zzalh.zza();
        int zza2 = zza(zza, zzalh, bArr, i3, i4, i5, zzahn);
        zzalh.zzc(zza);
        zzahn.zzc = zza;
        return zza2;
    }

    public static int zza(zzalh zzalh, byte[] bArr, int i3, int i4, zzahn zzahn) throws IOException {
        Object zza = zzalh.zza();
        int zza2 = zza(zza, zzalh, bArr, i3, i4, zzahn);
        zzalh.zzc(zza);
        zzahn.zzc = zza;
        return zza2;
    }

    public static int zza(zzalh<?> zzalh, int i3, byte[] bArr, int i4, int i5, zzajl<?> zzajl, zzahn zzahn) throws IOException {
        int zza = zza((zzalh) zzalh, bArr, i4, i5, zzahn);
        zzajl.add(zzahn.zzc);
        while (zza < i5) {
            int zzc = zzc(bArr, zza, zzahn);
            if (i3 != zzahn.zza) {
                break;
            }
            zza = zza((zzalh) zzalh, bArr, zzc, i5, zzahn);
            zzajl.add(zzahn.zzc);
        }
        return zza;
    }

    public static int zza(byte[] bArr, int i3, zzajl<?> zzajl, zzahn zzahn) throws IOException {
        zzajf zzajf = (zzajf) zzajl;
        int zzc = zzc(bArr, i3, zzahn);
        int i4 = zzahn.zza + zzc;
        while (zzc < i4) {
            zzc = zzc(bArr, zzc, zzahn);
            zzajf.zzc(zzahn.zza);
        }
        if (zzc == i4) {
            return zzc;
        }
        throw zzajk.zzi();
    }

    public static int zza(int i3, byte[] bArr, int i4, int i5, zzamf zzamf, zzahn zzahn) throws zzajk {
        if ((i3 >>> 3) != 0) {
            int i6 = i3 & 7;
            if (i6 == 0) {
                int zzd = zzd(bArr, i4, zzahn);
                zzamf.zza(i3, (Object) Long.valueOf(zzahn.zzb));
                return zzd;
            } else if (i6 == 1) {
                zzamf.zza(i3, (Object) Long.valueOf(zzd(bArr, i4)));
                return i4 + 8;
            } else if (i6 == 2) {
                int zzc = zzc(bArr, i4, zzahn);
                int i7 = zzahn.zza;
                if (i7 < 0) {
                    throw zzajk.zzf();
                } else if (i7 <= bArr.length - zzc) {
                    if (i7 == 0) {
                        zzamf.zza(i3, (Object) zzaho.zza);
                    } else {
                        zzamf.zza(i3, (Object) zzaho.zza(bArr, zzc, i7));
                    }
                    return zzc + i7;
                } else {
                    throw zzajk.zzi();
                }
            } else if (i6 == 3) {
                zzamf zzd2 = zzamf.zzd();
                int i8 = (i3 & -8) | 4;
                int i9 = 0;
                while (true) {
                    if (i4 >= i5) {
                        break;
                    }
                    int zzc2 = zzc(bArr, i4, zzahn);
                    int i10 = zzahn.zza;
                    i9 = i10;
                    if (i10 == i8) {
                        i4 = zzc2;
                        break;
                    }
                    int zza = zza(i9, bArr, zzc2, i5, zzd2, zzahn);
                    i9 = i10;
                    i4 = zza;
                }
                if (i4 > i5 || i9 != i8) {
                    throw zzajk.zzg();
                }
                zzamf.zza(i3, (Object) zzd2);
                return i4;
            } else if (i6 == 5) {
                zzamf.zza(i3, (Object) Integer.valueOf(zzc(bArr, i4)));
                return i4 + 4;
            } else {
                throw zzajk.zzc();
            }
        } else {
            throw zzajk.zzc();
        }
    }

    public static int zza(int i3, byte[] bArr, int i4, zzahn zzahn) {
        int i5 = i3 & 127;
        int i6 = i4 + 1;
        byte b3 = bArr[i4];
        if (b3 >= 0) {
            zzahn.zza = i5 | (b3 << 7);
            return i6;
        }
        int i7 = i5 | ((b3 & Byte.MAX_VALUE) << 7);
        int i8 = i4 + 2;
        byte b4 = bArr[i6];
        if (b4 >= 0) {
            zzahn.zza = i7 | (b4 << Ascii.SO);
            return i8;
        }
        int i9 = i7 | ((b4 & Byte.MAX_VALUE) << Ascii.SO);
        int i10 = i4 + 3;
        byte b5 = bArr[i8];
        if (b5 >= 0) {
            zzahn.zza = i9 | (b5 << Ascii.NAK);
            return i10;
        }
        int i11 = i9 | ((b5 & Byte.MAX_VALUE) << Ascii.NAK);
        int i12 = i4 + 4;
        byte b6 = bArr[i10];
        if (b6 >= 0) {
            zzahn.zza = i11 | (b6 << Ascii.FS);
            return i12;
        }
        int i13 = i11 | ((b6 & Byte.MAX_VALUE) << Ascii.FS);
        while (true) {
            int i14 = i12 + 1;
            if (bArr[i12] >= 0) {
                zzahn.zza = i13;
                return i14;
            }
            i12 = i14;
        }
    }

    public static int zza(int i3, byte[] bArr, int i4, int i5, zzajl<?> zzajl, zzahn zzahn) {
        zzajf zzajf = (zzajf) zzajl;
        int zzc = zzc(bArr, i4, zzahn);
        zzajf.zzc(zzahn.zza);
        while (zzc < i5) {
            int zzc2 = zzc(bArr, zzc, zzahn);
            if (i3 != zzahn.zza) {
                break;
            }
            zzc = zzc(bArr, zzc2, zzahn);
            zzajf.zzc(zzahn.zza);
        }
        return zzc;
    }

    public static int zza(Object obj, zzalh zzalh, byte[] bArr, int i3, int i4, int i5, zzahn zzahn) throws IOException {
        int zza = ((zzakt) zzalh).zza(obj, bArr, i3, i4, i5, zzahn);
        zzahn.zzc = obj;
        return zza;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v6, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int zza(java.lang.Object r6, com.google.android.gms.internal.p002firebaseauthapi.zzalh r7, byte[] r8, int r9, int r10, com.google.android.gms.internal.p002firebaseauthapi.zzahn r11) throws java.io.IOException {
        /*
            int r0 = r9 + 1
            byte r9 = r8[r9]
            if (r9 >= 0) goto L_0x000c
            int r0 = zza((int) r9, (byte[]) r8, (int) r0, (com.google.android.gms.internal.p002firebaseauthapi.zzahn) r11)
            int r9 = r11.zza
        L_0x000c:
            r3 = r0
            if (r9 < 0) goto L_0x001e
            int r10 = r10 - r3
            if (r9 > r10) goto L_0x001e
            int r9 = r9 + r3
            r0 = r7
            r1 = r6
            r2 = r8
            r4 = r9
            r5 = r11
            r0.zza(r1, r2, r3, r4, r5)
            r11.zzc = r6
            return r9
        L_0x001e:
            com.google.android.gms.internal.firebase-auth-api.zzajk r6 = com.google.android.gms.internal.p002firebaseauthapi.zzajk.zzi()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzahk.zza(java.lang.Object, com.google.android.gms.internal.firebase-auth-api.zzalh, byte[], int, int, com.google.android.gms.internal.firebase-auth-api.zzahn):int");
    }

    public static int zza(int i3, byte[] bArr, int i4, int i5, zzahn zzahn) throws zzajk {
        if ((i3 >>> 3) != 0) {
            int i6 = i3 & 7;
            if (i6 == 0) {
                return zzd(bArr, i4, zzahn);
            }
            if (i6 == 1) {
                return i4 + 8;
            }
            if (i6 == 2) {
                return zzc(bArr, i4, zzahn) + zzahn.zza;
            }
            if (i6 == 3) {
                int i7 = (i3 & -8) | 4;
                int i8 = 0;
                while (i4 < i5) {
                    i4 = zzc(bArr, i4, zzahn);
                    i8 = zzahn.zza;
                    if (i8 == i7) {
                        break;
                    }
                    i4 = zza(i8, bArr, i4, i5, zzahn);
                }
                if (i4 <= i5 && i8 == i7) {
                    return i4;
                }
                throw zzajk.zzg();
            } else if (i6 == 5) {
                return i4 + 4;
            } else {
                throw zzajk.zzc();
            }
        } else {
            throw zzajk.zzc();
        }
    }
}
