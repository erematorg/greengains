package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import com.google.common.base.Ascii;
import java.io.IOException;

final class zzcv {
    public static final /* synthetic */ int zza = 0;
    private static volatile int zzb = 100;

    public static int zza(byte[] bArr, int i3, zzcu zzcu) throws zzer {
        int zzj = zzj(bArr, i3, zzcu);
        int i4 = zzcu.zza;
        if (i4 < 0) {
            throw new zzer("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        } else if (i4 > bArr.length - zzj) {
            throw new zzer("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        } else if (i4 == 0) {
            zzcu.zzc = zzdf.zzb;
            return zzj;
        } else {
            zzcu.zzc = zzdf.zzr(bArr, zzj, i4);
            return zzj + i4;
        }
    }

    public static int zzb(int i3, byte[] bArr, int i4, int i5, zzed zzed, zzef zzef, zzgs zzgs, zzcu zzcu) throws IOException {
        int i6;
        zzdx zzdx = zzed.zzb;
        zzhf zzhf = zzef.zzb.zzb;
        Object obj = null;
        if (zzhf != zzhf.ENUM) {
            switch (zzhf.ordinal()) {
                case 0:
                    i6 = i4 + 8;
                    obj = Double.valueOf(Double.longBitsToDouble(zzq(bArr, i4)));
                    break;
                case 1:
                    i6 = i4 + 4;
                    obj = Float.valueOf(Float.intBitsToFloat(zzc(bArr, i4)));
                    break;
                case 2:
                case 3:
                    i4 = zzm(bArr, i4, zzcu);
                    obj = Long.valueOf(zzcu.zzb);
                    break;
                case 4:
                case 12:
                    i4 = zzj(bArr, i4, zzcu);
                    obj = Integer.valueOf(zzcu.zza);
                    break;
                case 5:
                case 15:
                    i6 = i4 + 8;
                    obj = Long.valueOf(zzq(bArr, i4));
                    break;
                case 6:
                case 14:
                    i6 = i4 + 4;
                    obj = Integer.valueOf(zzc(bArr, i4));
                    break;
                case 7:
                    i4 = zzm(bArr, i4, zzcu);
                    obj = Boolean.valueOf(zzcu.zzb != 0);
                    break;
                case 8:
                    i4 = zzh(bArr, i4, zzcu);
                    obj = zzcu.zzc;
                    break;
                case 9:
                    int i7 = ((i3 >>> 3) << 3) | 4;
                    zzge zzb2 = zzfu.zza().zzb(zzef.zza.getClass());
                    Object zze = zzdx.zze(zzef.zzb);
                    if (zze == null) {
                        zze = zzb2.zze();
                        zzdx.zzi(zzef.zzb, zze);
                    }
                    return zzn(zze, zzb2, bArr, i4, i5, i7, zzcu);
                case 10:
                    zzge zzb3 = zzfu.zza().zzb(zzef.zza.getClass());
                    Object zze2 = zzdx.zze(zzef.zzb);
                    if (zze2 == null) {
                        zze2 = zzb3.zze();
                        zzdx.zzi(zzef.zzb, zze2);
                    }
                    return zzo(zze2, zzb3, bArr, i4, i5, zzcu);
                case 11:
                    i4 = zza(bArr, i4, zzcu);
                    obj = zzcu.zzc;
                    break;
                case 13:
                    throw new IllegalStateException("Shouldn't reach here.");
                case 16:
                    i4 = zzj(bArr, i4, zzcu);
                    obj = Integer.valueOf(zzdj.zzb(zzcu.zza));
                    break;
                case 17:
                    i4 = zzm(bArr, i4, zzcu);
                    obj = Long.valueOf(zzdj.zzc(zzcu.zzb));
                    break;
            }
            i4 = i6;
            zzdx.zzi(zzef.zzb, obj);
            return i4;
        }
        zzj(bArr, i4, zzcu);
        throw null;
    }

    public static int zzc(byte[] bArr, int i3) {
        int i4 = (bArr[i3 + 1] & 255) << 8;
        return ((bArr[i3 + 3] & 255) << Ascii.CAN) | i4 | (bArr[i3] & 255) | ((bArr[i3 + 2] & 255) << 16);
    }

    public static int zzd(zzge zzge, byte[] bArr, int i3, int i4, int i5, zzcu zzcu) throws IOException {
        Object zze = zzge.zze();
        int zzn = zzn(zze, zzge, bArr, i3, i4, i5, zzcu);
        zzge.zzf(zze);
        zzcu.zzc = zze;
        return zzn;
    }

    public static int zze(zzge zzge, byte[] bArr, int i3, int i4, zzcu zzcu) throws IOException {
        Object zze = zzge.zze();
        int zzo = zzo(zze, zzge, bArr, i3, i4, zzcu);
        zzge.zzf(zze);
        zzcu.zzc = zze;
        return zzo;
    }

    public static int zzf(zzge zzge, int i3, byte[] bArr, int i4, int i5, zzeo zzeo, zzcu zzcu) throws IOException {
        int zze = zze(zzge, bArr, i4, i5, zzcu);
        zzeo.add(zzcu.zzc);
        while (zze < i5) {
            int zzj = zzj(bArr, zze, zzcu);
            if (i3 != zzcu.zza) {
                break;
            }
            zze = zze(zzge, bArr, zzj, i5, zzcu);
            zzeo.add(zzcu.zzc);
        }
        return zze;
    }

    public static int zzg(byte[] bArr, int i3, zzeo zzeo, zzcu zzcu) throws IOException {
        zzei zzei = (zzei) zzeo;
        int zzj = zzj(bArr, i3, zzcu);
        int i4 = zzcu.zza + zzj;
        while (zzj < i4) {
            zzj = zzj(bArr, zzj, zzcu);
            zzei.zzg(zzcu.zza);
        }
        if (zzj == i4) {
            return zzj;
        }
        throw new zzer("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    public static int zzh(byte[] bArr, int i3, zzcu zzcu) throws zzer {
        int zzj = zzj(bArr, i3, zzcu);
        int i4 = zzcu.zza;
        if (i4 < 0) {
            throw new zzer("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        } else if (i4 == 0) {
            zzcu.zzc = "";
            return zzj;
        } else {
            zzcu.zzc = new String(bArr, zzj, i4, zzep.zza);
            return zzj + i4;
        }
    }

    public static int zzi(int i3, byte[] bArr, int i4, int i5, zzgt zzgt, zzcu zzcu) throws zzer {
        if ((i3 >>> 3) != 0) {
            int i6 = i3 & 7;
            if (i6 == 0) {
                int zzm = zzm(bArr, i4, zzcu);
                zzgt.zzj(i3, Long.valueOf(zzcu.zzb));
                return zzm;
            } else if (i6 == 1) {
                zzgt.zzj(i3, Long.valueOf(zzq(bArr, i4)));
                return i4 + 8;
            } else if (i6 == 2) {
                int zzj = zzj(bArr, i4, zzcu);
                int i7 = zzcu.zza;
                if (i7 < 0) {
                    throw new zzer("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
                } else if (i7 <= bArr.length - zzj) {
                    if (i7 == 0) {
                        zzgt.zzj(i3, zzdf.zzb);
                    } else {
                        zzgt.zzj(i3, zzdf.zzr(bArr, zzj, i7));
                    }
                    return zzj + i7;
                } else {
                    throw new zzer("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                }
            } else if (i6 == 3) {
                int i8 = (i3 & -8) | 4;
                zzgt zzf = zzgt.zzf();
                int i9 = zzcu.zze + 1;
                zzcu.zze = i9;
                zzr(i9);
                int i10 = 0;
                while (true) {
                    if (i4 >= i5) {
                        break;
                    }
                    int zzj2 = zzj(bArr, i4, zzcu);
                    i10 = zzcu.zza;
                    if (i10 == i8) {
                        i4 = zzj2;
                        break;
                    }
                    i4 = zzi(i10, bArr, zzj2, i5, zzf, zzcu);
                }
                zzcu.zze--;
                if (i4 > i5 || i10 != i8) {
                    throw new zzer("Failed to parse the message.");
                }
                zzgt.zzj(i3, zzf);
                return i4;
            } else if (i6 == 5) {
                zzgt.zzj(i3, Integer.valueOf(zzc(bArr, i4)));
                return i4 + 4;
            } else {
                throw new zzer("Protocol message contained an invalid tag (zero).");
            }
        } else {
            throw new zzer("Protocol message contained an invalid tag (zero).");
        }
    }

    public static int zzj(byte[] bArr, int i3, zzcu zzcu) {
        int i4 = i3 + 1;
        byte b3 = bArr[i3];
        if (b3 < 0) {
            return zzk(b3, bArr, i4, zzcu);
        }
        zzcu.zza = b3;
        return i4;
    }

    public static int zzk(int i3, byte[] bArr, int i4, zzcu zzcu) {
        byte b3 = bArr[i4];
        int i5 = i4 + 1;
        int i6 = i3 & 127;
        if (b3 >= 0) {
            zzcu.zza = i6 | (b3 << 7);
            return i5;
        }
        int i7 = i6 | ((b3 & Byte.MAX_VALUE) << 7);
        int i8 = i4 + 2;
        byte b4 = bArr[i5];
        if (b4 >= 0) {
            zzcu.zza = i7 | (b4 << Ascii.SO);
            return i8;
        }
        int i9 = i7 | ((b4 & Byte.MAX_VALUE) << Ascii.SO);
        int i10 = i4 + 3;
        byte b5 = bArr[i8];
        if (b5 >= 0) {
            zzcu.zza = i9 | (b5 << Ascii.NAK);
            return i10;
        }
        int i11 = i9 | ((b5 & Byte.MAX_VALUE) << Ascii.NAK);
        int i12 = i4 + 4;
        byte b6 = bArr[i10];
        if (b6 >= 0) {
            zzcu.zza = i11 | (b6 << Ascii.FS);
            return i12;
        }
        int i13 = i11 | ((b6 & Byte.MAX_VALUE) << Ascii.FS);
        while (true) {
            int i14 = i12 + 1;
            if (bArr[i12] < 0) {
                i12 = i14;
            } else {
                zzcu.zza = i13;
                return i14;
            }
        }
    }

    public static int zzl(int i3, byte[] bArr, int i4, int i5, zzeo zzeo, zzcu zzcu) {
        zzei zzei = (zzei) zzeo;
        int zzj = zzj(bArr, i4, zzcu);
        zzei.zzg(zzcu.zza);
        while (zzj < i5) {
            int zzj2 = zzj(bArr, zzj, zzcu);
            if (i3 != zzcu.zza) {
                break;
            }
            zzj = zzj(bArr, zzj2, zzcu);
            zzei.zzg(zzcu.zza);
        }
        return zzj;
    }

    public static int zzm(byte[] bArr, int i3, zzcu zzcu) {
        long j2 = (long) bArr[i3];
        int i4 = i3 + 1;
        if (j2 >= 0) {
            zzcu.zzb = j2;
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
        zzcu.zzb = j3;
        return i5;
    }

    public static int zzn(Object obj, zzge zzge, byte[] bArr, int i3, int i4, int i5, zzcu zzcu) throws IOException {
        int i6 = zzcu.zze + 1;
        zzcu.zze = i6;
        zzr(i6);
        int zzc = ((zzfp) zzge).zzc(obj, bArr, i3, i4, i5, zzcu);
        zzcu.zze--;
        zzcu.zzc = obj;
        return zzc;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v6, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int zzo(java.lang.Object r6, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzge r7, byte[] r8, int r9, int r10, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcu r11) throws java.io.IOException {
        /*
            int r0 = r9 + 1
            byte r9 = r8[r9]
            if (r9 >= 0) goto L_0x000c
            int r0 = zzk(r9, r8, r0, r11)
            int r9 = r11.zza
        L_0x000c:
            r3 = r0
            if (r9 < 0) goto L_0x002d
            int r10 = r10 - r3
            if (r9 > r10) goto L_0x002d
            int r10 = r11.zze
            int r10 = r10 + 1
            r11.zze = r10
            zzr(r10)
            int r9 = r9 + r3
            r0 = r7
            r1 = r6
            r2 = r8
            r4 = r9
            r5 = r11
            r0.zzh(r1, r2, r3, r4, r5)
            int r7 = r11.zze
            int r7 = r7 + -1
            r11.zze = r7
            r11.zzc = r6
            return r9
        L_0x002d:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzer r6 = new com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzer
            java.lang.String r7 = "While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length."
            r6.<init>((java.lang.String) r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcv.zzo(java.lang.Object, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzge, byte[], int, int, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcu):int");
    }

    public static int zzp(int i3, byte[] bArr, int i4, int i5, zzcu zzcu) throws zzer {
        if ((i3 >>> 3) != 0) {
            int i6 = i3 & 7;
            if (i6 == 0) {
                return zzm(bArr, i4, zzcu);
            }
            if (i6 == 1) {
                return i4 + 8;
            }
            if (i6 == 2) {
                return zzj(bArr, i4, zzcu) + zzcu.zza;
            }
            if (i6 == 3) {
                int i7 = (i3 & -8) | 4;
                int i8 = 0;
                while (i4 < i5) {
                    i4 = zzj(bArr, i4, zzcu);
                    i8 = zzcu.zza;
                    if (i8 == i7) {
                        break;
                    }
                    i4 = zzp(i8, bArr, i4, i5, zzcu);
                }
                if (i4 <= i5 && i8 == i7) {
                    return i4;
                }
                throw new zzer("Failed to parse the message.");
            } else if (i6 == 5) {
                return i4 + 4;
            } else {
                throw new zzer("Protocol message contained an invalid tag (zero).");
            }
        } else {
            throw new zzer("Protocol message contained an invalid tag (zero).");
        }
    }

    public static long zzq(byte[] bArr, int i3) {
        return (((long) bArr[i3]) & 255) | ((((long) bArr[i3 + 1]) & 255) << 8) | ((((long) bArr[i3 + 2]) & 255) << 16) | ((((long) bArr[i3 + 3]) & 255) << 24) | ((((long) bArr[i3 + 4]) & 255) << 32) | ((((long) bArr[i3 + 5]) & 255) << 40) | ((((long) bArr[i3 + 6]) & 255) << 48) | ((((long) bArr[i3 + 7]) & 255) << 56);
    }

    private static void zzr(int i3) throws zzer {
        if (i3 >= zzb) {
            throw new zzer("Protocol message had too many levels of nesting.  May be malicious.  Use setRecursionLimit() to increase the recursion depth limit.");
        }
    }
}
