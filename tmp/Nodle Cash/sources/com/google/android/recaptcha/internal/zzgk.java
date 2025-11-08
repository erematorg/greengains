package com.google.android.recaptcha.internal;

import com.google.common.base.Ascii;
import java.io.IOException;

final class zzgk {
    public static int zza(byte[] bArr, int i3, zzgj zzgj) throws zzje {
        int zzi = zzi(bArr, i3, zzgj);
        int i4 = zzgj.zza;
        if (i4 < 0) {
            throw zzje.zzf();
        } else if (i4 > bArr.length - zzi) {
            throw zzje.zzj();
        } else if (i4 == 0) {
            zzgj.zzc = zzgw.zzb;
            return zzi;
        } else {
            zzgj.zzc = zzgw.zzm(bArr, zzi, i4);
            return zzi + i4;
        }
    }

    public static int zzb(byte[] bArr, int i3) {
        int i4 = (bArr[i3 + 1] & 255) << 8;
        return ((bArr[i3 + 3] & 255) << Ascii.CAN) | i4 | (bArr[i3] & 255) | ((bArr[i3 + 2] & 255) << 16);
    }

    public static int zzc(zzkr zzkr, byte[] bArr, int i3, int i4, int i5, zzgj zzgj) throws IOException {
        Object zze = zzkr.zze();
        int zzm = zzm(zze, zzkr, bArr, i3, i4, i5, zzgj);
        zzkr.zzf(zze);
        zzgj.zzc = zze;
        return zzm;
    }

    public static int zzd(zzkr zzkr, byte[] bArr, int i3, int i4, zzgj zzgj) throws IOException {
        Object zze = zzkr.zze();
        int zzn = zzn(zze, zzkr, bArr, i3, i4, zzgj);
        zzkr.zzf(zze);
        zzgj.zzc = zze;
        return zzn;
    }

    public static int zze(zzkr zzkr, int i3, byte[] bArr, int i4, int i5, zzjb zzjb, zzgj zzgj) throws IOException {
        int zzd = zzd(zzkr, bArr, i4, i5, zzgj);
        zzjb.add(zzgj.zzc);
        while (zzd < i5) {
            int zzi = zzi(bArr, zzd, zzgj);
            if (i3 != zzgj.zza) {
                break;
            }
            zzd = zzd(zzkr, bArr, zzi, i5, zzgj);
            zzjb.add(zzgj.zzc);
        }
        return zzd;
    }

    public static int zzf(byte[] bArr, int i3, zzjb zzjb, zzgj zzgj) throws IOException {
        zziu zziu = (zziu) zzjb;
        int zzi = zzi(bArr, i3, zzgj);
        int i4 = zzgj.zza + zzi;
        while (zzi < i4) {
            zzi = zzi(bArr, zzi, zzgj);
            zziu.zzg(zzgj.zza);
        }
        if (zzi == i4) {
            return zzi;
        }
        throw zzje.zzj();
    }

    public static int zzg(byte[] bArr, int i3, zzgj zzgj) throws zzje {
        int zzi = zzi(bArr, i3, zzgj);
        int i4 = zzgj.zza;
        if (i4 < 0) {
            throw zzje.zzf();
        } else if (i4 == 0) {
            zzgj.zzc = "";
            return zzi;
        } else {
            zzgj.zzc = new String(bArr, zzi, i4, zzjc.zzb);
            return zzi + i4;
        }
    }

    public static int zzh(int i3, byte[] bArr, int i4, int i5, zzlm zzlm, zzgj zzgj) throws zzje {
        if ((i3 >>> 3) != 0) {
            int i6 = i3 & 7;
            if (i6 == 0) {
                int zzl = zzl(bArr, i4, zzgj);
                zzlm.zzj(i3, Long.valueOf(zzgj.zzb));
                return zzl;
            } else if (i6 == 1) {
                zzlm.zzj(i3, Long.valueOf(zzp(bArr, i4)));
                return i4 + 8;
            } else if (i6 == 2) {
                int zzi = zzi(bArr, i4, zzgj);
                int i7 = zzgj.zza;
                if (i7 < 0) {
                    throw zzje.zzf();
                } else if (i7 <= bArr.length - zzi) {
                    if (i7 == 0) {
                        zzlm.zzj(i3, zzgw.zzb);
                    } else {
                        zzlm.zzj(i3, zzgw.zzm(bArr, zzi, i7));
                    }
                    return zzi + i7;
                } else {
                    throw zzje.zzj();
                }
            } else if (i6 == 3) {
                int i8 = (i3 & -8) | 4;
                zzlm zzf = zzlm.zzf();
                int i9 = 0;
                while (true) {
                    if (i4 >= i5) {
                        break;
                    }
                    int zzi2 = zzi(bArr, i4, zzgj);
                    int i10 = zzgj.zza;
                    i9 = i10;
                    if (i10 == i8) {
                        i4 = zzi2;
                        break;
                    }
                    int zzh = zzh(i9, bArr, zzi2, i5, zzf, zzgj);
                    i9 = i10;
                    i4 = zzh;
                }
                if (i4 > i5 || i9 != i8) {
                    throw zzje.zzg();
                }
                zzlm.zzj(i3, zzf);
                return i4;
            } else if (i6 == 5) {
                zzlm.zzj(i3, Integer.valueOf(zzb(bArr, i4)));
                return i4 + 4;
            } else {
                throw zzje.zzc();
            }
        } else {
            throw zzje.zzc();
        }
    }

    public static int zzi(byte[] bArr, int i3, zzgj zzgj) {
        int i4 = i3 + 1;
        byte b3 = bArr[i3];
        if (b3 < 0) {
            return zzj(b3, bArr, i4, zzgj);
        }
        zzgj.zza = b3;
        return i4;
    }

    public static int zzj(int i3, byte[] bArr, int i4, zzgj zzgj) {
        byte b3 = bArr[i4];
        int i5 = i4 + 1;
        int i6 = i3 & 127;
        if (b3 >= 0) {
            zzgj.zza = i6 | (b3 << 7);
            return i5;
        }
        int i7 = i6 | ((b3 & Byte.MAX_VALUE) << 7);
        int i8 = i4 + 2;
        byte b4 = bArr[i5];
        if (b4 >= 0) {
            zzgj.zza = i7 | (b4 << Ascii.SO);
            return i8;
        }
        int i9 = i7 | ((b4 & Byte.MAX_VALUE) << Ascii.SO);
        int i10 = i4 + 3;
        byte b5 = bArr[i8];
        if (b5 >= 0) {
            zzgj.zza = i9 | (b5 << Ascii.NAK);
            return i10;
        }
        int i11 = i9 | ((b5 & Byte.MAX_VALUE) << Ascii.NAK);
        int i12 = i4 + 4;
        byte b6 = bArr[i10];
        if (b6 >= 0) {
            zzgj.zza = i11 | (b6 << Ascii.FS);
            return i12;
        }
        int i13 = i11 | ((b6 & Byte.MAX_VALUE) << Ascii.FS);
        while (true) {
            int i14 = i12 + 1;
            if (bArr[i12] < 0) {
                i12 = i14;
            } else {
                zzgj.zza = i13;
                return i14;
            }
        }
    }

    public static int zzk(int i3, byte[] bArr, int i4, int i5, zzjb zzjb, zzgj zzgj) {
        zziu zziu = (zziu) zzjb;
        int zzi = zzi(bArr, i4, zzgj);
        zziu.zzg(zzgj.zza);
        while (zzi < i5) {
            int zzi2 = zzi(bArr, zzi, zzgj);
            if (i3 != zzgj.zza) {
                break;
            }
            zzi = zzi(bArr, zzi2, zzgj);
            zziu.zzg(zzgj.zza);
        }
        return zzi;
    }

    public static int zzl(byte[] bArr, int i3, zzgj zzgj) {
        long j2 = (long) bArr[i3];
        int i4 = i3 + 1;
        if (j2 >= 0) {
            zzgj.zzb = j2;
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
        zzgj.zzb = j3;
        return i5;
    }

    public static int zzm(Object obj, zzkr zzkr, byte[] bArr, int i3, int i4, int i5, zzgj zzgj) throws IOException {
        int zzc = ((zzkh) zzkr).zzc(obj, bArr, i3, i4, i5, zzgj);
        zzgj.zzc = obj;
        return zzc;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v6, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int zzn(java.lang.Object r6, com.google.android.recaptcha.internal.zzkr r7, byte[] r8, int r9, int r10, com.google.android.recaptcha.internal.zzgj r11) throws java.io.IOException {
        /*
            int r0 = r9 + 1
            byte r9 = r8[r9]
            if (r9 >= 0) goto L_0x000c
            int r0 = zzj(r9, r8, r0, r11)
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
            r0.zzi(r1, r2, r3, r4, r5)
            r11.zzc = r6
            return r9
        L_0x001e:
            com.google.android.recaptcha.internal.zzje r6 = com.google.android.recaptcha.internal.zzje.zzj()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzgk.zzn(java.lang.Object, com.google.android.recaptcha.internal.zzkr, byte[], int, int, com.google.android.recaptcha.internal.zzgj):int");
    }

    public static int zzo(int i3, byte[] bArr, int i4, int i5, zzgj zzgj) throws zzje {
        if ((i3 >>> 3) != 0) {
            int i6 = i3 & 7;
            if (i6 == 0) {
                return zzl(bArr, i4, zzgj);
            }
            if (i6 == 1) {
                return i4 + 8;
            }
            if (i6 == 2) {
                return zzi(bArr, i4, zzgj) + zzgj.zza;
            }
            if (i6 == 3) {
                int i7 = (i3 & -8) | 4;
                int i8 = 0;
                while (i4 < i5) {
                    i4 = zzi(bArr, i4, zzgj);
                    i8 = zzgj.zza;
                    if (i8 == i7) {
                        break;
                    }
                    i4 = zzo(i8, bArr, i4, i5, zzgj);
                }
                if (i4 <= i5 && i8 == i7) {
                    return i4;
                }
                throw zzje.zzg();
            } else if (i6 == 5) {
                return i4 + 4;
            } else {
                throw zzje.zzc();
            }
        } else {
            throw zzje.zzc();
        }
    }

    public static long zzp(byte[] bArr, int i3) {
        return (((long) bArr[i3]) & 255) | ((((long) bArr[i3 + 1]) & 255) << 8) | ((((long) bArr[i3 + 2]) & 255) << 16) | ((((long) bArr[i3 + 3]) & 255) << 24) | ((((long) bArr[i3 + 4]) & 255) << 32) | ((((long) bArr[i3 + 5]) & 255) << 40) | ((((long) bArr[i3 + 6]) & 255) << 48) | ((((long) bArr[i3 + 7]) & 255) << 56);
    }
}
