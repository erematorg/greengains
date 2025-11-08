package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import java.io.IOException;
import java.util.List;

final class zzgg {
    public static final /* synthetic */ int zza = 0;
    private static final zzgs zzb = new zzgu();

    static {
        int i3 = zzfu.zza;
    }

    public static void zzA(int i3, List list, zzhh zzhh, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhh.zzC(i3, list, z2);
        }
    }

    public static void zzB(int i3, List list, zzhh zzhh, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhh.zzE(i3, list, z2);
        }
    }

    public static void zzC(int i3, List list, zzhh zzhh, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhh.zzJ(i3, list, z2);
        }
    }

    public static void zzD(int i3, List list, zzhh zzhh, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhh.zzL(i3, list, z2);
        }
    }

    public static boolean zzE(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj != null) {
            return obj.equals(obj2);
        }
        return false;
    }

    public static int zza(List list) {
        int i3;
        int size = list.size();
        int i4 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzei) {
            zzei zzei = (zzei) list;
            i3 = 0;
            while (i4 < size) {
                i3 += zzdn.zzB((long) zzei.zze(i4));
                i4++;
            }
        } else {
            int i5 = 0;
            while (i4 < size) {
                i5 = i3 + zzdn.zzB((long) ((Integer) list.get(i4)).intValue());
                i4++;
            }
        }
        return i3;
    }

    public static int zzb(int i3, List list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (zzdn.zzA(i3 << 3) + 4) * size;
    }

    public static int zzc(List list) {
        return list.size() * 4;
    }

    public static int zzd(int i3, List list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (zzdn.zzA(i3 << 3) + 8) * size;
    }

    public static int zze(List list) {
        return list.size() * 8;
    }

    public static int zzf(List list) {
        int i3;
        int size = list.size();
        int i4 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzei) {
            zzei zzei = (zzei) list;
            i3 = 0;
            while (i4 < size) {
                i3 += zzdn.zzB((long) zzei.zze(i4));
                i4++;
            }
        } else {
            int i5 = 0;
            while (i4 < size) {
                i5 = i3 + zzdn.zzB((long) ((Integer) list.get(i4)).intValue());
                i4++;
            }
        }
        return i3;
    }

    public static int zzg(List list) {
        int i3;
        int size = list.size();
        int i4 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzfb) {
            zzfb zzfb = (zzfb) list;
            i3 = 0;
            while (i4 < size) {
                i3 += zzdn.zzB(zzfb.zze(i4));
                i4++;
            }
        } else {
            int i5 = 0;
            while (i4 < size) {
                i5 = i3 + zzdn.zzB(((Long) list.get(i4)).longValue());
                i4++;
            }
        }
        return i3;
    }

    public static int zzh(int i3, Object obj, zzge zzge) {
        int i4 = i3 << 3;
        if (obj instanceof zzex) {
            int zzA = zzdn.zzA(i4);
            int zza2 = ((zzex) obj).zza();
            return a.b(zza2, zza2, zzA);
        }
        return zzdn.zzy((zzfm) obj, zzge) + zzdn.zzA(i4);
    }

    public static int zzi(List list) {
        int i3;
        int size = list.size();
        int i4 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzei) {
            zzei zzei = (zzei) list;
            i3 = 0;
            while (i4 < size) {
                int zze = zzei.zze(i4);
                i3 += zzdn.zzA((zze >> 31) ^ (zze + zze));
                i4++;
            }
        } else {
            int i5 = 0;
            while (i4 < size) {
                int intValue = ((Integer) list.get(i4)).intValue();
                i5 = i3 + zzdn.zzA((intValue >> 31) ^ (intValue + intValue));
                i4++;
            }
        }
        return i3;
    }

    public static int zzj(List list) {
        int i3;
        int size = list.size();
        int i4 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzfb) {
            zzfb zzfb = (zzfb) list;
            i3 = 0;
            while (i4 < size) {
                long zze = zzfb.zze(i4);
                i3 += zzdn.zzB((zze >> 63) ^ (zze + zze));
                i4++;
            }
        } else {
            int i5 = 0;
            while (i4 < size) {
                long longValue = ((Long) list.get(i4)).longValue();
                i5 = i3 + zzdn.zzB((longValue >> 63) ^ (longValue + longValue));
                i4++;
            }
        }
        return i3;
    }

    public static int zzk(List list) {
        int i3;
        int size = list.size();
        int i4 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzei) {
            zzei zzei = (zzei) list;
            i3 = 0;
            while (i4 < size) {
                i3 += zzdn.zzA(zzei.zze(i4));
                i4++;
            }
        } else {
            int i5 = 0;
            while (i4 < size) {
                i5 = i3 + zzdn.zzA(((Integer) list.get(i4)).intValue());
                i4++;
            }
        }
        return i3;
    }

    public static int zzl(List list) {
        int i3;
        int size = list.size();
        int i4 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzfb) {
            zzfb zzfb = (zzfb) list;
            i3 = 0;
            while (i4 < size) {
                i3 += zzdn.zzB(zzfb.zze(i4));
                i4++;
            }
        } else {
            int i5 = 0;
            while (i4 < size) {
                i5 = i3 + zzdn.zzB(((Long) list.get(i4)).longValue());
                i4++;
            }
        }
        return i3;
    }

    public static zzgs zzm() {
        return zzb;
    }

    /* JADX WARNING: Failed to insert additional move for type inference */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object zzn(java.lang.Object r2, int r3, int r4, java.lang.Object r5, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgs r6) {
        /*
            if (r5 != 0) goto L_0x0012
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeh r2 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeh) r2
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgt r5 = r2.zzc
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgt r6 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgt.zzc()
            if (r5 != r6) goto L_0x0012
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgt r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgt.zzf()
            r2.zzc = r5
        L_0x0012:
            long r0 = (long) r4
            int r2 = r3 << 3
            r3 = r5
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgt r3 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgt) r3
            java.lang.Long r4 = java.lang.Long.valueOf(r0)
            r3.zzj(r2, r4)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgg.zzn(java.lang.Object, int, int, java.lang.Object, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgs):java.lang.Object");
    }

    public static void zzo(zzdt zzdt, Object obj, Object obj2) {
        zzdx zzdx = ((zzed) obj2).zzb;
        if (!zzdx.zza.isEmpty()) {
            ((zzed) obj).zzc().zzh(zzdx);
        }
    }

    public static void zzp(zzgs zzgs, Object obj, Object obj2) {
        zzeh zzeh = (zzeh) obj;
        zzgt zzgt = zzeh.zzc;
        zzgt zzgt2 = ((zzeh) obj2).zzc;
        if (!zzgt.zzc().equals(zzgt2)) {
            if (zzgt.zzc().equals(zzgt)) {
                zzgt = zzgt.zze(zzgt, zzgt2);
            } else {
                zzgt.zzd(zzgt2);
            }
        }
        zzeh.zzc = zzgt;
    }

    public static void zzq(int i3, List list, zzhh zzhh, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhh.zzc(i3, list, z2);
        }
    }

    public static void zzr(int i3, List list, zzhh zzhh, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhh.zzg(i3, list, z2);
        }
    }

    public static void zzs(int i3, List list, zzhh zzhh, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhh.zzj(i3, list, z2);
        }
    }

    public static void zzt(int i3, List list, zzhh zzhh, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhh.zzl(i3, list, z2);
        }
    }

    public static void zzu(int i3, List list, zzhh zzhh, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhh.zzn(i3, list, z2);
        }
    }

    public static void zzv(int i3, List list, zzhh zzhh, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhh.zzp(i3, list, z2);
        }
    }

    public static void zzw(int i3, List list, zzhh zzhh, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhh.zzs(i3, list, z2);
        }
    }

    public static void zzx(int i3, List list, zzhh zzhh, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhh.zzu(i3, list, z2);
        }
    }

    public static void zzy(int i3, List list, zzhh zzhh, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhh.zzy(i3, list, z2);
        }
    }

    public static void zzz(int i3, List list, zzhh zzhh, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhh.zzA(i3, list, z2);
        }
    }
}
