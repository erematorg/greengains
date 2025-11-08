package com.google.android.recaptcha.internal;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

final class zzkt {
    public static final /* synthetic */ int zza = 0;
    private static final Class zzb;
    private static final zzll zzc;
    private static final zzll zzd = new zzln();

    static {
        Class<?> cls;
        Class<?> cls2;
        zzll zzll = null;
        try {
            cls = Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            cls = null;
        }
        zzb = cls;
        try {
            cls2 = Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused2) {
            cls2 = null;
        }
        if (cls2 != null) {
            try {
                zzll = (zzll) cls2.getConstructor((Class[]) null).newInstance((Object[]) null);
            } catch (Throwable unused3) {
            }
        }
        zzc = zzll;
    }

    public static void zzA(int i3, List list, zzmd zzmd, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzmd.zzu(i3, list, z2);
        }
    }

    public static void zzB(int i3, List list, zzmd zzmd, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzmd.zzy(i3, list, z2);
        }
    }

    public static void zzC(int i3, List list, zzmd zzmd, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzmd.zzA(i3, list, z2);
        }
    }

    public static void zzD(int i3, List list, zzmd zzmd, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzmd.zzC(i3, list, z2);
        }
    }

    public static void zzE(int i3, List list, zzmd zzmd, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzmd.zzE(i3, list, z2);
        }
    }

    public static void zzF(int i3, List list, zzmd zzmd, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzmd.zzJ(i3, list, z2);
        }
    }

    public static void zzG(int i3, List list, zzmd zzmd, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzmd.zzL(i3, list, z2);
        }
    }

    public static boolean zzH(Object obj, Object obj2) {
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
        if (list instanceof zziu) {
            zziu zziu = (zziu) list;
            i3 = 0;
            while (i4 < size) {
                i3 += zzhh.zzu(zziu.zze(i4));
                i4++;
            }
        } else {
            int i5 = 0;
            while (i4 < size) {
                i5 = i3 + zzhh.zzu(((Integer) list.get(i4)).intValue());
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
        return (zzhh.zzy(i3 << 3) + 4) * size;
    }

    public static int zzc(List list) {
        return list.size() * 4;
    }

    public static int zzd(int i3, List list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (zzhh.zzy(i3 << 3) + 8) * size;
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
        if (list instanceof zziu) {
            zziu zziu = (zziu) list;
            i3 = 0;
            while (i4 < size) {
                i3 += zzhh.zzu(zziu.zze(i4));
                i4++;
            }
        } else {
            int i5 = 0;
            while (i4 < size) {
                i5 = i3 + zzhh.zzu(((Integer) list.get(i4)).intValue());
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
        if (list instanceof zzjt) {
            zzjt zzjt = (zzjt) list;
            i3 = 0;
            while (i4 < size) {
                i3 += zzhh.zzz(zzjt.zze(i4));
                i4++;
            }
        } else {
            int i5 = 0;
            while (i4 < size) {
                i5 = i3 + zzhh.zzz(((Long) list.get(i4)).longValue());
                i4++;
            }
        }
        return i3;
    }

    public static int zzh(int i3, Object obj, zzkr zzkr) {
        int i4 = i3 << 3;
        if (obj instanceof zzjk) {
            int i5 = zzhh.zzb;
            int zza2 = ((zzjk) obj).zza();
            return zzhh.zzy(i4) + zzhh.zzy(zza2) + zza2;
        }
        return zzhh.zzy(i4) + zzhh.zzw((zzke) obj, zzkr);
    }

    public static int zzi(List list) {
        int i3;
        int size = list.size();
        int i4 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zziu) {
            zziu zziu = (zziu) list;
            i3 = 0;
            while (i4 < size) {
                int zze = zziu.zze(i4);
                i3 += zzhh.zzy((zze >> 31) ^ (zze + zze));
                i4++;
            }
        } else {
            int i5 = 0;
            while (i4 < size) {
                int intValue = ((Integer) list.get(i4)).intValue();
                i5 = i3 + zzhh.zzy((intValue >> 31) ^ (intValue + intValue));
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
        if (list instanceof zzjt) {
            zzjt zzjt = (zzjt) list;
            i3 = 0;
            while (i4 < size) {
                long zze = zzjt.zze(i4);
                i3 += zzhh.zzz((zze >> 63) ^ (zze + zze));
                i4++;
            }
        } else {
            int i5 = 0;
            while (i4 < size) {
                long longValue = ((Long) list.get(i4)).longValue();
                i5 = i3 + zzhh.zzz((longValue >> 63) ^ (longValue + longValue));
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
        if (list instanceof zziu) {
            zziu zziu = (zziu) list;
            i3 = 0;
            while (i4 < size) {
                i3 += zzhh.zzy(zziu.zze(i4));
                i4++;
            }
        } else {
            int i5 = 0;
            while (i4 < size) {
                i5 = i3 + zzhh.zzy(((Integer) list.get(i4)).intValue());
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
        if (list instanceof zzjt) {
            zzjt zzjt = (zzjt) list;
            i3 = 0;
            while (i4 < size) {
                i3 += zzhh.zzz(zzjt.zze(i4));
                i4++;
            }
        } else {
            int i5 = 0;
            while (i4 < size) {
                i5 = i3 + zzhh.zzz(((Long) list.get(i4)).longValue());
                i4++;
            }
        }
        return i3;
    }

    public static zzll zzm() {
        return zzc;
    }

    public static zzll zzn() {
        return zzd;
    }

    public static Object zzo(Object obj, int i3, List list, zzix zzix, Object obj2, zzll zzll) {
        if (zzix == null) {
            return obj2;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i4 = 0;
            for (int i5 = 0; i5 < size; i5++) {
                Integer num = (Integer) list.get(i5);
                int intValue = num.intValue();
                if (zzix.zza(intValue)) {
                    if (i5 != i4) {
                        list.set(i4, num);
                    }
                    i4++;
                } else {
                    obj2 = zzp(obj, i3, intValue, obj2, zzll);
                }
            }
            if (i4 != size) {
                list.subList(i4, size).clear();
                return obj2;
            }
        } else {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = ((Integer) it.next()).intValue();
                if (!zzix.zza(intValue2)) {
                    obj2 = zzp(obj, i3, intValue2, obj2, zzll);
                    it.remove();
                }
            }
        }
        return obj2;
    }

    public static Object zzp(Object obj, int i3, int i4, Object obj2, zzll zzll) {
        if (obj2 == null) {
            obj2 = zzll.zzc(obj);
        }
        zzll.zzl(obj2, i3, (long) i4);
        return obj2;
    }

    public static void zzq(zzif zzif, Object obj, Object obj2) {
        zzij zzb2 = zzif.zzb(obj2);
        if (!zzb2.zza.isEmpty()) {
            zzif.zzc(obj).zzh(zzb2);
        }
    }

    public static void zzr(zzll zzll, Object obj, Object obj2) {
        zzll.zzo(obj, zzll.zze(zzll.zzd(obj), zzll.zzd(obj2)));
    }

    public static void zzs(Class cls) {
        Class cls2;
        if (!zzit.class.isAssignableFrom(cls) && (cls2 = zzb) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zzt(int i3, List list, zzmd zzmd, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzmd.zzc(i3, list, z2);
        }
    }

    public static void zzu(int i3, List list, zzmd zzmd, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzmd.zzg(i3, list, z2);
        }
    }

    public static void zzv(int i3, List list, zzmd zzmd, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzmd.zzj(i3, list, z2);
        }
    }

    public static void zzw(int i3, List list, zzmd zzmd, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzmd.zzl(i3, list, z2);
        }
    }

    public static void zzx(int i3, List list, zzmd zzmd, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzmd.zzn(i3, list, z2);
        }
    }

    public static void zzy(int i3, List list, zzmd zzmd, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzmd.zzp(i3, list, z2);
        }
    }

    public static void zzz(int i3, List list, zzmd zzmd, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzmd.zzs(i3, list, z2);
        }
    }
}
