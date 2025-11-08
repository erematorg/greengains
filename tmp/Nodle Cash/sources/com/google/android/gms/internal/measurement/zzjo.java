package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzlc;
import com.google.common.base.Ascii;
import java.io.IOException;

final class zzjo {
    private static volatile int zza = 100;

    public static double zza(byte[] bArr, int i3) {
        return Double.longBitsToDouble(zzd(bArr, i3));
    }

    public static float zzb(byte[] bArr, int i3) {
        return Float.intBitsToFloat(zzc(bArr, i3));
    }

    public static int zzc(byte[] bArr, int i3) {
        return ((bArr[i3 + 3] & 255) << Ascii.CAN) | (bArr[i3] & 255) | ((bArr[i3 + 1] & 255) << 8) | ((bArr[i3 + 2] & 255) << 16);
    }

    public static int zzd(byte[] bArr, int i3, zzjn zzjn) {
        int i4 = i3 + 1;
        long j2 = (long) bArr[i3];
        if (j2 >= 0) {
            zzjn.zzb = j2;
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
        zzjn.zzb = j3;
        return i5;
    }

    public static int zza(byte[] bArr, int i3, zzjn zzjn) throws zzlk {
        int zzc = zzc(bArr, i3, zzjn);
        int i4 = zzjn.zza;
        if (i4 < 0) {
            throw zzlk.zzf();
        } else if (i4 > bArr.length - zzc) {
            throw zzlk.zzi();
        } else if (i4 == 0) {
            zzjn.zzc = zzjs.zza;
            return zzc;
        } else {
            zzjn.zzc = zzjs.zza(bArr, zzc, i4);
            return zzc + i4;
        }
    }

    public static int zzb(zznd<?> zznd, int i3, byte[] bArr, int i4, int i5, zzll<?> zzll, zzjn zzjn) throws IOException {
        int zza2 = zza((zznd) zznd, bArr, i4, i5, zzjn);
        zzll.add(zzjn.zzc);
        while (zza2 < i5) {
            int zzc = zzc(bArr, zza2, zzjn);
            if (i3 != zzjn.zza) {
                break;
            }
            zza2 = zza((zznd) zznd, bArr, zzc, i5, zzjn);
            zzll.add(zzjn.zzc);
        }
        return zza2;
    }

    public static int zzc(byte[] bArr, int i3, zzjn zzjn) {
        int i4 = i3 + 1;
        byte b3 = bArr[i3];
        if (b3 < 0) {
            return zza((int) b3, bArr, i4, zzjn);
        }
        zzjn.zza = b3;
        return i4;
    }

    public static long zzd(byte[] bArr, int i3) {
        return ((((long) bArr[i3 + 7]) & 255) << 56) | (((long) bArr[i3]) & 255) | ((((long) bArr[i3 + 1]) & 255) << 8) | ((((long) bArr[i3 + 2]) & 255) << 16) | ((((long) bArr[i3 + 3]) & 255) << 24) | ((((long) bArr[i3 + 4]) & 255) << 32) | ((((long) bArr[i3 + 5]) & 255) << 40) | ((((long) bArr[i3 + 6]) & 255) << 48);
    }

    public static int zzb(byte[] bArr, int i3, zzjn zzjn) throws zzlk {
        int zzc = zzc(bArr, i3, zzjn);
        int i4 = zzjn.zza;
        if (i4 < 0) {
            throw zzlk.zzf();
        } else if (i4 == 0) {
            zzjn.zzc = "";
            return zzc;
        } else {
            zzjn.zzc = zzoc.zzb(bArr, zzc, i4);
            return zzc + i4;
        }
    }

    public static int zza(int i3, byte[] bArr, int i4, int i5, Object obj, zzml zzml, zznx<zznw, zznw> zznx, zzjn zzjn) throws IOException {
        if (zzjn.zzd.zza(zzml, i3 >>> 3) == null) {
            return zza(i3, bArr, i4, i5, zzmp.zzc(obj), zzjn);
        }
        zzlc.zzd zzd = (zzlc.zzd) obj;
        zzd.zza();
        zzkv<zzlc.zzc> zzkv = zzd.zzc;
        throw new NoSuchMethodError();
    }

    private static int zza(zznd zznd, byte[] bArr, int i3, int i4, int i5, zzjn zzjn) throws IOException {
        Object zza2 = zznd.zza();
        int zza3 = zza(zza2, zznd, bArr, i3, i4, i5, zzjn);
        zznd.zzd(zza2);
        zzjn.zzc = zza2;
        return zza3;
    }

    public static int zza(zznd zznd, int i3, byte[] bArr, int i4, int i5, zzll<?> zzll, zzjn zzjn) throws IOException {
        int i6 = (i3 & -8) | 4;
        int zza2 = zza(zznd, bArr, i4, i5, i6, zzjn);
        zzll.add(zzjn.zzc);
        while (zza2 < i5) {
            int zzc = zzc(bArr, zza2, zzjn);
            if (i3 != zzjn.zza) {
                break;
            }
            zza2 = zza(zznd, bArr, zzc, i5, i6, zzjn);
            zzll.add(zzjn.zzc);
        }
        return zza2;
    }

    public static int zza(zznd zznd, byte[] bArr, int i3, int i4, zzjn zzjn) throws IOException {
        Object zza2 = zznd.zza();
        int zza3 = zza(zza2, zznd, bArr, i3, i4, zzjn);
        zznd.zzd(zza2);
        zzjn.zzc = zza2;
        return zza3;
    }

    public static int zza(byte[] bArr, int i3, zzll<?> zzll, zzjn zzjn) throws IOException {
        zzlf zzlf = (zzlf) zzll;
        int zzc = zzc(bArr, i3, zzjn);
        int i4 = zzjn.zza + zzc;
        while (zzc < i4) {
            zzc = zzc(bArr, zzc, zzjn);
            zzlf.zzd(zzjn.zza);
        }
        if (zzc == i4) {
            return zzc;
        }
        throw zzlk.zzi();
    }

    public static int zza(int i3, byte[] bArr, int i4, int i5, zznw zznw, zzjn zzjn) throws zzlk {
        if ((i3 >>> 3) != 0) {
            int i6 = i3 & 7;
            if (i6 == 0) {
                int zzd = zzd(bArr, i4, zzjn);
                zznw.zza(i3, (Object) Long.valueOf(zzjn.zzb));
                return zzd;
            } else if (i6 == 1) {
                zznw.zza(i3, (Object) Long.valueOf(zzd(bArr, i4)));
                return i4 + 8;
            } else if (i6 == 2) {
                int zzc = zzc(bArr, i4, zzjn);
                int i7 = zzjn.zza;
                if (i7 < 0) {
                    throw zzlk.zzf();
                } else if (i7 <= bArr.length - zzc) {
                    if (i7 == 0) {
                        zznw.zza(i3, (Object) zzjs.zza);
                    } else {
                        zznw.zza(i3, (Object) zzjs.zza(bArr, zzc, i7));
                    }
                    return zzc + i7;
                } else {
                    throw zzlk.zzi();
                }
            } else if (i6 == 3) {
                zznw zzd2 = zznw.zzd();
                int i8 = (i3 & -8) | 4;
                int i9 = zzjn.zze + 1;
                zzjn.zze = i9;
                zza(i9);
                int i10 = 0;
                while (true) {
                    if (i4 >= i5) {
                        break;
                    }
                    int zzc2 = zzc(bArr, i4, zzjn);
                    int i11 = zzjn.zza;
                    i10 = i11;
                    if (i11 == i8) {
                        i4 = zzc2;
                        break;
                    }
                    int zza2 = zza(i10, bArr, zzc2, i5, zzd2, zzjn);
                    i10 = i11;
                    i4 = zza2;
                }
                zzjn.zze--;
                if (i4 > i5 || i10 != i8) {
                    throw zzlk.zzg();
                }
                zznw.zza(i3, (Object) zzd2);
                return i4;
            } else if (i6 == 5) {
                zznw.zza(i3, (Object) Integer.valueOf(zzc(bArr, i4)));
                return i4 + 4;
            } else {
                throw zzlk.zzc();
            }
        } else {
            throw zzlk.zzc();
        }
    }

    public static int zza(int i3, byte[] bArr, int i4, zzjn zzjn) {
        int i5 = i3 & 127;
        int i6 = i4 + 1;
        byte b3 = bArr[i4];
        if (b3 >= 0) {
            zzjn.zza = i5 | (b3 << 7);
            return i6;
        }
        int i7 = i5 | ((b3 & Byte.MAX_VALUE) << 7);
        int i8 = i4 + 2;
        byte b4 = bArr[i6];
        if (b4 >= 0) {
            zzjn.zza = i7 | (b4 << Ascii.SO);
            return i8;
        }
        int i9 = i7 | ((b4 & Byte.MAX_VALUE) << Ascii.SO);
        int i10 = i4 + 3;
        byte b5 = bArr[i8];
        if (b5 >= 0) {
            zzjn.zza = i9 | (b5 << Ascii.NAK);
            return i10;
        }
        int i11 = i9 | ((b5 & Byte.MAX_VALUE) << Ascii.NAK);
        int i12 = i4 + 4;
        byte b6 = bArr[i10];
        if (b6 >= 0) {
            zzjn.zza = i11 | (b6 << Ascii.FS);
            return i12;
        }
        int i13 = i11 | ((b6 & Byte.MAX_VALUE) << Ascii.FS);
        while (true) {
            int i14 = i12 + 1;
            if (bArr[i12] >= 0) {
                zzjn.zza = i13;
                return i14;
            }
            i12 = i14;
        }
    }

    public static int zza(int i3, byte[] bArr, int i4, int i5, zzll<?> zzll, zzjn zzjn) {
        zzlf zzlf = (zzlf) zzll;
        int zzc = zzc(bArr, i4, zzjn);
        zzlf.zzd(zzjn.zza);
        while (zzc < i5) {
            int zzc2 = zzc(bArr, zzc, zzjn);
            if (i3 != zzjn.zza) {
                break;
            }
            zzc = zzc(bArr, zzc2, zzjn);
            zzlf.zzd(zzjn.zza);
        }
        return zzc;
    }

    public static int zza(Object obj, zznd zznd, byte[] bArr, int i3, int i4, int i5, zzjn zzjn) throws IOException {
        int i6 = zzjn.zze + 1;
        zzjn.zze = i6;
        zza(i6);
        int zza2 = ((zzmp) zznd).zza(obj, bArr, i3, i4, i5, zzjn);
        zzjn.zze--;
        zzjn.zzc = obj;
        return zza2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v6, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int zza(java.lang.Object r6, com.google.android.gms.internal.measurement.zznd r7, byte[] r8, int r9, int r10, com.google.android.gms.internal.measurement.zzjn r11) throws java.io.IOException {
        /*
            int r0 = r9 + 1
            byte r9 = r8[r9]
            if (r9 >= 0) goto L_0x000c
            int r0 = zza((int) r9, (byte[]) r8, (int) r0, (com.google.android.gms.internal.measurement.zzjn) r11)
            int r9 = r11.zza
        L_0x000c:
            r3 = r0
            if (r9 < 0) goto L_0x002d
            int r10 = r10 - r3
            if (r9 > r10) goto L_0x002d
            int r10 = r11.zze
            int r10 = r10 + 1
            r11.zze = r10
            zza(r10)
            int r9 = r9 + r3
            r0 = r7
            r1 = r6
            r2 = r8
            r4 = r9
            r5 = r11
            r0.zza(r1, r2, r3, r4, r5)
            int r7 = r11.zze
            int r7 = r7 + -1
            r11.zze = r7
            r11.zzc = r6
            return r9
        L_0x002d:
            com.google.android.gms.internal.measurement.zzlk r6 = com.google.android.gms.internal.measurement.zzlk.zzi()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzjo.zza(java.lang.Object, com.google.android.gms.internal.measurement.zznd, byte[], int, int, com.google.android.gms.internal.measurement.zzjn):int");
    }

    public static int zza(int i3, byte[] bArr, int i4, int i5, zzjn zzjn) throws zzlk {
        if ((i3 >>> 3) != 0) {
            int i6 = i3 & 7;
            if (i6 == 0) {
                return zzd(bArr, i4, zzjn);
            }
            if (i6 == 1) {
                return i4 + 8;
            }
            if (i6 == 2) {
                return zzc(bArr, i4, zzjn) + zzjn.zza;
            }
            if (i6 == 3) {
                int i7 = (i3 & -8) | 4;
                int i8 = 0;
                while (i4 < i5) {
                    i4 = zzc(bArr, i4, zzjn);
                    i8 = zzjn.zza;
                    if (i8 == i7) {
                        break;
                    }
                    i4 = zza(i8, bArr, i4, i5, zzjn);
                }
                if (i4 <= i5 && i8 == i7) {
                    return i4;
                }
                throw zzlk.zzg();
            } else if (i6 == 5) {
                return i4 + 4;
            } else {
                throw zzlk.zzc();
            }
        } else {
            throw zzlk.zzc();
        }
    }

    private static void zza(int i3) throws zzlk {
        if (i3 >= zza) {
            throw zzlk.zzh();
        }
    }
}
