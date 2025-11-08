package com.google.android.gms.internal.auth;

import com.google.common.base.Ascii;
import java.io.IOException;

final class zzdu {
    public static int zza(byte[] bArr, int i3, zzdt zzdt) throws zzfb {
        int zzh = zzh(bArr, i3, zzdt);
        int i4 = zzdt.zza;
        if (i4 < 0) {
            throw zzfb.zzc();
        } else if (i4 > bArr.length - zzh) {
            throw zzfb.zzf();
        } else if (i4 == 0) {
            zzdt.zzc = zzef.zzb;
            return zzh;
        } else {
            zzdt.zzc = zzef.zzk(bArr, zzh, i4);
            return zzh + i4;
        }
    }

    public static int zzb(byte[] bArr, int i3) {
        int i4 = (bArr[i3 + 1] & 255) << 8;
        return ((bArr[i3 + 3] & 255) << Ascii.CAN) | i4 | (bArr[i3] & 255) | ((bArr[i3 + 2] & 255) << 16);
    }

    public static int zzc(zzgi zzgi, byte[] bArr, int i3, int i4, int i5, zzdt zzdt) throws IOException {
        Object zzd = zzgi.zzd();
        int zzl = zzl(zzd, zzgi, bArr, i3, i4, i5, zzdt);
        zzgi.zze(zzd);
        zzdt.zzc = zzd;
        return zzl;
    }

    public static int zzd(zzgi zzgi, byte[] bArr, int i3, int i4, zzdt zzdt) throws IOException {
        Object zzd = zzgi.zzd();
        int zzm = zzm(zzd, zzgi, bArr, i3, i4, zzdt);
        zzgi.zze(zzd);
        zzdt.zzc = zzd;
        return zzm;
    }

    public static int zze(zzgi zzgi, int i3, byte[] bArr, int i4, int i5, zzez zzez, zzdt zzdt) throws IOException {
        int zzd = zzd(zzgi, bArr, i4, i5, zzdt);
        zzez.add(zzdt.zzc);
        while (zzd < i5) {
            int zzh = zzh(bArr, zzd, zzdt);
            if (i3 != zzdt.zza) {
                break;
            }
            zzd = zzd(zzgi, bArr, zzh, i5, zzdt);
            zzez.add(zzdt.zzc);
        }
        return zzd;
    }

    public static int zzf(byte[] bArr, int i3, zzez zzez, zzdt zzdt) throws IOException {
        zzew zzew = (zzew) zzez;
        int zzh = zzh(bArr, i3, zzdt);
        int i4 = zzdt.zza + zzh;
        while (zzh < i4) {
            zzh = zzh(bArr, zzh, zzdt);
            zzew.zze(zzdt.zza);
        }
        if (zzh == i4) {
            return zzh;
        }
        throw zzfb.zzf();
    }

    public static int zzg(int i3, byte[] bArr, int i4, int i5, zzha zzha, zzdt zzdt) throws zzfb {
        if ((i3 >>> 3) != 0) {
            int i6 = i3 & 7;
            if (i6 == 0) {
                int zzk = zzk(bArr, i4, zzdt);
                zzha.zzh(i3, Long.valueOf(zzdt.zzb));
                return zzk;
            } else if (i6 == 1) {
                zzha.zzh(i3, Long.valueOf(zzn(bArr, i4)));
                return i4 + 8;
            } else if (i6 == 2) {
                int zzh = zzh(bArr, i4, zzdt);
                int i7 = zzdt.zza;
                if (i7 < 0) {
                    throw zzfb.zzc();
                } else if (i7 <= bArr.length - zzh) {
                    if (i7 == 0) {
                        zzha.zzh(i3, zzef.zzb);
                    } else {
                        zzha.zzh(i3, zzef.zzk(bArr, zzh, i7));
                    }
                    return zzh + i7;
                } else {
                    throw zzfb.zzf();
                }
            } else if (i6 == 3) {
                int i8 = (i3 & -8) | 4;
                zzha zzd = zzha.zzd();
                int i9 = 0;
                while (true) {
                    if (i4 >= i5) {
                        break;
                    }
                    int zzh2 = zzh(bArr, i4, zzdt);
                    int i10 = zzdt.zza;
                    i9 = i10;
                    if (i10 == i8) {
                        i4 = zzh2;
                        break;
                    }
                    int zzg = zzg(i9, bArr, zzh2, i5, zzd, zzdt);
                    i9 = i10;
                    i4 = zzg;
                }
                if (i4 > i5 || i9 != i8) {
                    throw zzfb.zzd();
                }
                zzha.zzh(i3, zzd);
                return i4;
            } else if (i6 == 5) {
                zzha.zzh(i3, Integer.valueOf(zzb(bArr, i4)));
                return i4 + 4;
            } else {
                throw zzfb.zza();
            }
        } else {
            throw zzfb.zza();
        }
    }

    public static int zzh(byte[] bArr, int i3, zzdt zzdt) {
        int i4 = i3 + 1;
        byte b3 = bArr[i3];
        if (b3 < 0) {
            return zzi(b3, bArr, i4, zzdt);
        }
        zzdt.zza = b3;
        return i4;
    }

    public static int zzi(int i3, byte[] bArr, int i4, zzdt zzdt) {
        byte b3 = bArr[i4];
        int i5 = i4 + 1;
        int i6 = i3 & 127;
        if (b3 >= 0) {
            zzdt.zza = i6 | (b3 << 7);
            return i5;
        }
        int i7 = i6 | ((b3 & Byte.MAX_VALUE) << 7);
        int i8 = i4 + 2;
        byte b4 = bArr[i5];
        if (b4 >= 0) {
            zzdt.zza = i7 | (b4 << Ascii.SO);
            return i8;
        }
        int i9 = i7 | ((b4 & Byte.MAX_VALUE) << Ascii.SO);
        int i10 = i4 + 3;
        byte b5 = bArr[i8];
        if (b5 >= 0) {
            zzdt.zza = i9 | (b5 << Ascii.NAK);
            return i10;
        }
        int i11 = i9 | ((b5 & Byte.MAX_VALUE) << Ascii.NAK);
        int i12 = i4 + 4;
        byte b6 = bArr[i10];
        if (b6 >= 0) {
            zzdt.zza = i11 | (b6 << Ascii.FS);
            return i12;
        }
        int i13 = i11 | ((b6 & Byte.MAX_VALUE) << Ascii.FS);
        while (true) {
            int i14 = i12 + 1;
            if (bArr[i12] < 0) {
                i12 = i14;
            } else {
                zzdt.zza = i13;
                return i14;
            }
        }
    }

    public static int zzj(int i3, byte[] bArr, int i4, int i5, zzez zzez, zzdt zzdt) {
        zzew zzew = (zzew) zzez;
        int zzh = zzh(bArr, i4, zzdt);
        zzew.zze(zzdt.zza);
        while (zzh < i5) {
            int zzh2 = zzh(bArr, zzh, zzdt);
            if (i3 != zzdt.zza) {
                break;
            }
            zzh = zzh(bArr, zzh2, zzdt);
            zzew.zze(zzdt.zza);
        }
        return zzh;
    }

    public static int zzk(byte[] bArr, int i3, zzdt zzdt) {
        long j2 = (long) bArr[i3];
        int i4 = i3 + 1;
        if (j2 >= 0) {
            zzdt.zzb = j2;
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
        zzdt.zzb = j3;
        return i5;
    }

    public static int zzl(Object obj, zzgi zzgi, byte[] bArr, int i3, int i4, int i5, zzdt zzdt) throws IOException {
        int zzb = ((zzga) zzgi).zzb(obj, bArr, i3, i4, i5, zzdt);
        zzdt.zzc = obj;
        return zzb;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v6, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int zzm(java.lang.Object r6, com.google.android.gms.internal.auth.zzgi r7, byte[] r8, int r9, int r10, com.google.android.gms.internal.auth.zzdt r11) throws java.io.IOException {
        /*
            int r0 = r9 + 1
            byte r9 = r8[r9]
            if (r9 >= 0) goto L_0x000c
            int r0 = zzi(r9, r8, r0, r11)
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
            r0.zzg(r1, r2, r3, r4, r5)
            r11.zzc = r6
            return r9
        L_0x001e:
            com.google.android.gms.internal.auth.zzfb r6 = com.google.android.gms.internal.auth.zzfb.zzf()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.auth.zzdu.zzm(java.lang.Object, com.google.android.gms.internal.auth.zzgi, byte[], int, int, com.google.android.gms.internal.auth.zzdt):int");
    }

    public static long zzn(byte[] bArr, int i3) {
        return (((long) bArr[i3]) & 255) | ((((long) bArr[i3 + 1]) & 255) << 8) | ((((long) bArr[i3 + 2]) & 255) << 16) | ((((long) bArr[i3 + 3]) & 255) << 24) | ((((long) bArr[i3 + 4]) & 255) << 32) | ((((long) bArr[i3 + 5]) & 255) << 40) | ((((long) bArr[i3 + 6]) & 255) << 48) | ((((long) bArr[i3 + 7]) & 255) << 56);
    }
}
