package com.google.android.gms.internal.p002firebaseauthapi;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzalj  reason: invalid package */
final class zzalj {
    private static final Class<?> zza = zzd();
    private static final zzamg<?, ?> zzb = zzc();
    private static final zzamg<?, ?> zzc = new zzami();

    public static int zza(int i3, List<?> list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzaim.zza(i3, true) * size;
    }

    public static int zzb(int i3, List<Integer> list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (zzaim.zzi(i3) * size) + zzb(list);
    }

    public static int zzc(int i3, List<?> list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzaim.zzc(i3, 0) * size;
    }

    public static int zzd(int i3, List<?> list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzaim.zza(i3, 0) * size;
    }

    public static int zze(int i3, List<Integer> list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (zzaim.zzi(i3) * size) + zze(list);
    }

    public static int zzf(int i3, List<Long> list, boolean z2) {
        if (list.size() == 0) {
            return 0;
        }
        return (zzaim.zzi(i3) * list.size()) + zzf(list);
    }

    public static int zzg(int i3, List<Integer> list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (zzaim.zzi(i3) * size) + zzg(list);
    }

    public static int zzh(int i3, List<Long> list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (zzaim.zzi(i3) * size) + zzh(list);
    }

    public static int zzi(int i3, List<Integer> list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (zzaim.zzi(i3) * size) + zzi(list);
    }

    public static int zzj(int i3, List<Long> list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (zzaim.zzi(i3) * size) + zzj(list);
    }

    public static void zzk(int i3, List<Integer> list, zzanc zzanc, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanc.zzk(i3, list, z2);
        }
    }

    public static void zzl(int i3, List<Long> list, zzanc zzanc, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanc.zzl(i3, list, z2);
        }
    }

    public static void zzm(int i3, List<Integer> list, zzanc zzanc, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanc.zzm(i3, list, z2);
        }
    }

    public static void zzn(int i3, List<Long> list, zzanc zzanc, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanc.zzn(i3, list, z2);
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

    public static int zza(int i3, List<zzaho> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzi = zzaim.zzi(i3) * size;
        for (int i4 = 0; i4 < list.size(); i4++) {
            zzi += zzaim.zza(list.get(i4));
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
        if (list instanceof zzajf) {
            zzajf zzajf = (zzajf) list;
            i3 = 0;
            while (i4 < size) {
                i3 += zzaim.zzc(zzajf.zzb(i4));
                i4++;
            }
        } else {
            int i5 = 0;
            while (i4 < size) {
                i5 = i3 + zzaim.zzc(list.get(i4).intValue());
                i4++;
            }
        }
        return i3;
    }

    private static zzamg<?, ?> zzc() {
        try {
            Class<?> zze = zze();
            if (zze == null) {
                return null;
            }
            return (zzamg) zze.getConstructor((Class[]) null).newInstance((Object[]) null);
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zzd() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            return null;
        }
    }

    public static int zze(List<Integer> list) {
        int i3;
        int size = list.size();
        int i4 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzajf) {
            zzajf zzajf = (zzajf) list;
            i3 = 0;
            while (i4 < size) {
                i3 += zzaim.zze(zzajf.zzb(i4));
                i4++;
            }
        } else {
            int i5 = 0;
            while (i4 < size) {
                i5 = i3 + zzaim.zze(list.get(i4).intValue());
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
        if (list instanceof zzaka) {
            zzaka zzaka = (zzaka) list;
            i3 = 0;
            while (i4 < size) {
                i3 += zzaim.zzd(zzaka.zzb(i4));
                i4++;
            }
        } else {
            int i5 = 0;
            while (i4 < size) {
                i5 = i3 + zzaim.zzd(list.get(i4).longValue());
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
        if (list instanceof zzajf) {
            zzajf zzajf = (zzajf) list;
            i3 = 0;
            while (i4 < size) {
                i3 += zzaim.zzh(zzajf.zzb(i4));
                i4++;
            }
        } else {
            int i5 = 0;
            while (i4 < size) {
                i5 = i3 + zzaim.zzh(list.get(i4).intValue());
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
        if (list instanceof zzaka) {
            zzaka zzaka = (zzaka) list;
            i3 = 0;
            while (i4 < size) {
                i3 += zzaim.zzf(zzaka.zzb(i4));
                i4++;
            }
        } else {
            int i5 = 0;
            while (i4 < size) {
                i5 = i3 + zzaim.zzf(list.get(i4).longValue());
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
        if (list instanceof zzajf) {
            zzajf zzajf = (zzajf) list;
            i3 = 0;
            while (i4 < size) {
                i3 += zzaim.zzj(zzajf.zzb(i4));
                i4++;
            }
        } else {
            int i5 = 0;
            while (i4 < size) {
                i5 = i3 + zzaim.zzj(list.get(i4).intValue());
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
        if (list instanceof zzaka) {
            zzaka zzaka = (zzaka) list;
            i3 = 0;
            while (i4 < size) {
                i3 += zzaim.zzg(zzaka.zzb(i4));
                i4++;
            }
        } else {
            int i5 = 0;
            while (i4 < size) {
                i5 = i3 + zzaim.zzg(list.get(i4).longValue());
                i4++;
            }
        }
        return i3;
    }

    public static void zzd(int i3, List<Integer> list, zzanc zzanc, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanc.zzd(i3, list, z2);
        }
    }

    public static void zzc(int i3, List<Integer> list, zzanc zzanc, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanc.zzc(i3, list, z2);
        }
    }

    public static int zza(int i3, List<zzakp> list, zzalh zzalh) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i4 = 0;
        for (int i5 = 0; i5 < size; i5++) {
            i4 += zzaim.zza(i3, list.get(i5), zzalh);
        }
        return i4;
    }

    public static int zzb(int i3, List<?> list, zzalh zzalh) {
        int zza2;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzi = zzaim.zzi(i3) * size;
        for (int i4 = 0; i4 < size; i4++) {
            Object obj = list.get(i4);
            if (obj instanceof zzajt) {
                zza2 = zzaim.zza((zzajt) obj);
            } else {
                zza2 = zzaim.zza((zzakp) obj, zzalh);
            }
            zzi = zza2 + zzi;
        }
        return zzi;
    }

    private static Class<?> zze() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void zzf(int i3, List<Float> list, zzanc zzanc, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanc.zzf(i3, list, z2);
        }
    }

    public static void zzg(int i3, List<Integer> list, zzanc zzanc, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanc.zzg(i3, list, z2);
        }
    }

    public static void zzh(int i3, List<Long> list, zzanc zzanc, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanc.zzh(i3, list, z2);
        }
    }

    public static void zzi(int i3, List<Integer> list, zzanc zzanc, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanc.zzi(i3, list, z2);
        }
    }

    public static void zzj(int i3, List<Long> list, zzanc zzanc, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanc.zzj(i3, list, z2);
        }
    }

    public static int zza(int i3, Object obj, zzalh zzalh) {
        if (obj instanceof zzajt) {
            return zzaim.zzb(i3, (zzajt) obj);
        }
        return zzaim.zzb(i3, (zzakp) obj, zzalh);
    }

    public static void zze(int i3, List<Long> list, zzanc zzanc, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanc.zze(i3, list, z2);
        }
    }

    public static zzamg<?, ?> zza() {
        return zzb;
    }

    public static <UT, UB> UB zza(Object obj, int i3, List<Integer> list, zzaji zzaji, UB ub, zzamg<UT, UB> zzamg) {
        if (zzaji == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i4 = 0;
            for (int i5 = 0; i5 < size; i5++) {
                Integer num = list.get(i5);
                int intValue = num.intValue();
                if (zzaji.zza(intValue)) {
                    if (i5 != i4) {
                        list.set(i4, num);
                    }
                    i4++;
                } else {
                    ub = zza(obj, i3, intValue, ub, zzamg);
                }
            }
            if (i4 != size) {
                list.subList(i4, size).clear();
            }
        } else {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = it.next().intValue();
                if (!zzaji.zza(intValue2)) {
                    ub = zza(obj, i3, intValue2, ub, zzamg);
                    it.remove();
                }
            }
        }
        return ub;
    }

    public static int zzb(int i3, List<?> list) {
        int zza2;
        int zza3;
        int size = list.size();
        int i4 = 0;
        if (size == 0) {
            return 0;
        }
        int zzi = zzaim.zzi(i3) * size;
        if (list instanceof zzajv) {
            zzajv zzajv = (zzajv) list;
            while (i4 < size) {
                Object zzb2 = zzajv.zzb(i4);
                if (zzb2 instanceof zzaho) {
                    zza3 = zzaim.zza((zzaho) zzb2);
                } else {
                    zza3 = zzaim.zza((String) zzb2);
                }
                zzi = zza3 + zzi;
                i4++;
            }
        } else {
            while (i4 < size) {
                Object obj = list.get(i4);
                if (obj instanceof zzaho) {
                    zza2 = zzaim.zza((zzaho) obj);
                } else {
                    zza2 = zzaim.zza((String) obj);
                }
                zzi = zza2 + zzi;
                i4++;
            }
        }
        return zzi;
    }

    public static <UT, UB> UB zza(Object obj, int i3, int i4, UB ub, zzamg<UT, UB> zzamg) {
        if (ub == null) {
            ub = zzamg.zzc(obj);
        }
        zzamg.zzb(ub, i3, (long) i4);
        return ub;
    }

    public static zzamg<?, ?> zzb() {
        return zzc;
    }

    public static <T, FT extends zzaiz<FT>> void zza(zzais<FT> zzais, T t2, T t3) {
        zzaix<FT> zza2 = zzais.zza((Object) t3);
        if (!zza2.zza.isEmpty()) {
            zzais.zzb(t2).zza(zza2);
        }
    }

    public static void zzb(int i3, List<Double> list, zzanc zzanc, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanc.zzb(i3, list, z2);
        }
    }

    public static void zzb(int i3, List<?> list, zzanc zzanc, zzalh zzalh) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanc.zzb(i3, list, zzalh);
        }
    }

    public static <T> void zza(zzaki zzaki, T t2, T t3, long j2) {
        zzamm.zza((Object) t2, j2, zzaki.zza(zzamm.zze(t2, j2), zzamm.zze(t3, j2)));
    }

    public static void zzb(int i3, List<String> list, zzanc zzanc) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanc.zzb(i3, list);
        }
    }

    public static <T, UT, UB> void zza(zzamg<UT, UB> zzamg, T t2, T t3) {
        zzamg.zzc(t2, zzamg.zza(zzamg.zzd(t2), zzamg.zzd(t3)));
    }

    public static void zza(Class<?> cls) {
        Class<?> cls2;
        if (!zzaje.class.isAssignableFrom(cls) && (cls2 = zza) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zza(int i3, List<Boolean> list, zzanc zzanc, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanc.zza(i3, list, z2);
        }
    }

    public static void zza(int i3, List<zzaho> list, zzanc zzanc) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanc.zza(i3, list);
        }
    }

    public static void zza(int i3, List<?> list, zzanc zzanc, zzalh zzalh) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanc.zza(i3, list, zzalh);
        }
    }

    public static boolean zza(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }
}
