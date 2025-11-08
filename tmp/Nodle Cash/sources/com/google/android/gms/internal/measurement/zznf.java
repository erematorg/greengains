package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

final class zznf {
    private static final zznx<?, ?> zza = new zznz();

    public static int zza(int i3, List<?> list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzkl.zzb(i3, true) * size;
    }

    public static int zzb(int i3, List<Integer> list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (zzkl.zzi(i3) * size) + zzb(list);
    }

    public static int zzc(int i3, List<?> list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzkl.zzf(i3, 0) * size;
    }

    public static int zzd(int i3, List<?> list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzkl.zzc(i3, 0) * size;
    }

    public static int zze(int i3, List<Integer> list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (zzkl.zzi(i3) * size) + zze(list);
    }

    public static int zzf(int i3, List<Long> list, boolean z2) {
        if (list.size() == 0) {
            return 0;
        }
        return (zzkl.zzi(i3) * list.size()) + zzf(list);
    }

    public static int zzg(int i3, List<Integer> list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (zzkl.zzi(i3) * size) + zzg(list);
    }

    public static int zzh(int i3, List<Long> list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (zzkl.zzi(i3) * size) + zzh(list);
    }

    public static int zzi(int i3, List<Integer> list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (zzkl.zzi(i3) * size) + zzi(list);
    }

    public static int zzj(int i3, List<Long> list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (zzkl.zzi(i3) * size) + zzj(list);
    }

    public static void zzk(int i3, List<Integer> list, zzos zzos, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzos.zzk(i3, list, z2);
        }
    }

    public static void zzl(int i3, List<Long> list, zzos zzos, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzos.zzl(i3, list, z2);
        }
    }

    public static void zzm(int i3, List<Integer> list, zzos zzos, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzos.zzm(i3, list, z2);
        }
    }

    public static void zzn(int i3, List<Long> list, zzos zzos, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzos.zzn(i3, list, z2);
        }
    }

    public static int zza(List<?> list) {
        return list.size();
    }

    public static int zzc(List<?> list) {
        return list.size() << 2;
    }

    public static int zzd(List<?> list) {
        return list.size() << 3;
    }

    public static int zza(int i3, List<zzjs> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzi = zzkl.zzi(i3) * size;
        for (int i4 = 0; i4 < list.size(); i4++) {
            zzi += zzkl.zzb(list.get(i4));
        }
        return zzi;
    }

    public static int zzb(List<Integer> list) {
        int i3;
        int size = list.size();
        int i4 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzlf) {
            zzlf zzlf = (zzlf) list;
            i3 = 0;
            while (i4 < size) {
                i3 += zzkl.zzd(zzlf.zzb(i4));
                i4++;
            }
        } else {
            int i5 = 0;
            while (i4 < size) {
                i5 = i3 + zzkl.zzd(list.get(i4).intValue());
                i4++;
            }
        }
        return i3;
    }

    public static void zzc(int i3, List<Integer> list, zzos zzos, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzos.zzc(i3, list, z2);
        }
    }

    public static void zzd(int i3, List<Integer> list, zzos zzos, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzos.zzd(i3, list, z2);
        }
    }

    public static int zze(List<Integer> list) {
        int i3;
        int size = list.size();
        int i4 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzlf) {
            zzlf zzlf = (zzlf) list;
            i3 = 0;
            while (i4 < size) {
                i3 += zzkl.zzf(zzlf.zzb(i4));
                i4++;
            }
        } else {
            int i5 = 0;
            while (i4 < size) {
                i5 = i3 + zzkl.zzf(list.get(i4).intValue());
                i4++;
            }
        }
        return i3;
    }

    public static int zzf(List<Long> list) {
        int i3;
        int size = list.size();
        int i4 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzlw) {
            zzlw zzlw = (zzlw) list;
            i3 = 0;
            while (i4 < size) {
                i3 += zzkl.zzd(zzlw.zzb(i4));
                i4++;
            }
        } else {
            int i5 = 0;
            while (i4 < size) {
                i5 = i3 + zzkl.zzd(list.get(i4).longValue());
                i4++;
            }
        }
        return i3;
    }

    public static int zzg(List<Integer> list) {
        int i3;
        int size = list.size();
        int i4 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzlf) {
            zzlf zzlf = (zzlf) list;
            i3 = 0;
            while (i4 < size) {
                i3 += zzkl.zzh(zzlf.zzb(i4));
                i4++;
            }
        } else {
            int i5 = 0;
            while (i4 < size) {
                i5 = i3 + zzkl.zzh(list.get(i4).intValue());
                i4++;
            }
        }
        return i3;
    }

    public static int zzh(List<Long> list) {
        int i3;
        int size = list.size();
        int i4 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzlw) {
            zzlw zzlw = (zzlw) list;
            i3 = 0;
            while (i4 < size) {
                i3 += zzkl.zzf(zzlw.zzb(i4));
                i4++;
            }
        } else {
            int i5 = 0;
            while (i4 < size) {
                i5 = i3 + zzkl.zzf(list.get(i4).longValue());
                i4++;
            }
        }
        return i3;
    }

    public static int zzi(List<Integer> list) {
        int i3;
        int size = list.size();
        int i4 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzlf) {
            zzlf zzlf = (zzlf) list;
            i3 = 0;
            while (i4 < size) {
                i3 += zzkl.zzj(zzlf.zzb(i4));
                i4++;
            }
        } else {
            int i5 = 0;
            while (i4 < size) {
                i5 = i3 + zzkl.zzj(list.get(i4).intValue());
                i4++;
            }
        }
        return i3;
    }

    public static int zzj(List<Long> list) {
        int i3;
        int size = list.size();
        int i4 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzlw) {
            zzlw zzlw = (zzlw) list;
            i3 = 0;
            while (i4 < size) {
                i3 += zzkl.zzg(zzlw.zzb(i4));
                i4++;
            }
        } else {
            int i5 = 0;
            while (i4 < size) {
                i5 = i3 + zzkl.zzg(list.get(i4).longValue());
                i4++;
            }
        }
        return i3;
    }

    public static int zza(int i3, List<zzml> list, zznd<?> zznd) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i4 = 0;
        for (int i5 = 0; i5 < size; i5++) {
            i4 += zzkl.zzb(i3, list.get(i5), (zznd) zznd);
        }
        return i4;
    }

    public static int zzb(int i3, List<?> list, zznd<?> zznd) {
        int zza2;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzi = zzkl.zzi(i3) * size;
        for (int i4 = 0; i4 < size; i4++) {
            Object obj = list.get(i4);
            if (obj instanceof zzlt) {
                zza2 = zzkl.zza((zzlt) obj);
            } else {
                zza2 = zzkl.zza((zzml) obj, (zznd) zznd);
            }
            zzi = zza2 + zzi;
        }
        return zzi;
    }

    public static void zze(int i3, List<Long> list, zzos zzos, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzos.zze(i3, list, z2);
        }
    }

    public static void zzf(int i3, List<Float> list, zzos zzos, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzos.zzf(i3, list, z2);
        }
    }

    public static void zzg(int i3, List<Integer> list, zzos zzos, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzos.zzg(i3, list, z2);
        }
    }

    public static void zzh(int i3, List<Long> list, zzos zzos, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzos.zzh(i3, list, z2);
        }
    }

    public static void zzi(int i3, List<Integer> list, zzos zzos, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzos.zzi(i3, list, z2);
        }
    }

    public static void zzj(int i3, List<Long> list, zzos zzos, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzos.zzj(i3, list, z2);
        }
    }

    public static int zza(int i3, Object obj, zznd<?> zznd) {
        if (obj instanceof zzlt) {
            return zzkl.zzb(i3, (zzlt) obj);
        }
        return zzkl.zzc(i3, (zzml) obj, zznd);
    }

    public static zznx<?, ?> zza() {
        return zza;
    }

    public static <UT, UB> UB zza(Object obj, int i3, List<Integer> list, zzlg zzlg, UB ub, zznx<UT, UB> zznx) {
        if (zzlg == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i4 = 0;
            for (int i5 = 0; i5 < size; i5++) {
                Integer num = list.get(i5);
                int intValue = num.intValue();
                if (zzlg.zza(intValue)) {
                    if (i5 != i4) {
                        list.set(i4, num);
                    }
                    i4++;
                } else {
                    ub = zza(obj, i3, intValue, ub, zznx);
                }
            }
            if (i4 != size) {
                list.subList(i4, size).clear();
            }
        } else {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = it.next().intValue();
                if (!zzlg.zza(intValue2)) {
                    ub = zza(obj, i3, intValue2, ub, zznx);
                    it.remove();
                }
            }
        }
        return ub;
    }

    public static int zzb(int i3, List<?> list) {
        int zzb;
        int zzb2;
        int size = list.size();
        int i4 = 0;
        if (size == 0) {
            return 0;
        }
        int zzi = zzkl.zzi(i3) * size;
        if (list instanceof zzls) {
            zzls zzls = (zzls) list;
            while (i4 < size) {
                Object zza2 = zzls.zza(i4);
                if (zza2 instanceof zzjs) {
                    zzb2 = zzkl.zzb((zzjs) zza2);
                } else {
                    zzb2 = zzkl.zzb((String) zza2);
                }
                zzi = zzb2 + zzi;
                i4++;
            }
        } else {
            while (i4 < size) {
                Object obj = list.get(i4);
                if (obj instanceof zzjs) {
                    zzb = zzkl.zzb((zzjs) obj);
                } else {
                    zzb = zzkl.zzb((String) obj);
                }
                zzi = zzb + zzi;
                i4++;
            }
        }
        return zzi;
    }

    public static <UT, UB> UB zza(Object obj, int i3, int i4, UB ub, zznx<UT, UB> zznx) {
        if (ub == null) {
            ub = zznx.zzc(obj);
        }
        zznx.zzb(ub, i3, (long) i4);
        return ub;
    }

    public static void zzb(int i3, List<Double> list, zzos zzos, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzos.zzb(i3, list, z2);
        }
    }

    public static <T, FT extends zzkx<FT>> void zza(zzkr<FT> zzkr, T t2, T t3) {
        zzkv<FT> zza2 = zzkr.zza((Object) t3);
        if (!zza2.zza.isEmpty()) {
            zzkr.zzb(t2).zza(zza2);
        }
    }

    public static void zzb(int i3, List<?> list, zzos zzos, zznd<?> zznd) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzos.zzb(i3, list, (zznd) zznd);
        }
    }

    public static void zzb(int i3, List<String> list, zzos zzos) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzos.zzb(i3, list);
        }
    }

    public static <T> void zza(zzme zzme, T t2, T t3, long j2) {
        zzny.zza((Object) t2, j2, zzme.zza(zzny.zze(t2, j2), zzny.zze(t3, j2)));
    }

    public static <T, UT, UB> void zza(zznx<UT, UB> zznx, T t2, T t3) {
        zznx.zzc(t2, zznx.zza(zznx.zzd(t2), zznx.zzd(t3)));
    }

    public static void zza(Class<?> cls) {
        zzlc.class.isAssignableFrom(cls);
    }

    public static void zza(int i3, List<Boolean> list, zzos zzos, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzos.zza(i3, list, z2);
        }
    }

    public static void zza(int i3, List<zzjs> list, zzos zzos) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzos.zza(i3, list);
        }
    }

    public static void zza(int i3, List<?> list, zzos zzos, zznd<?> zznd) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzos.zza(i3, list, (zznd) zznd);
        }
    }

    public static boolean zza(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }
}
