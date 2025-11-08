package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.List;
import java.util.Map;

final class zzko implements zzos {
    private final zzkl zza;

    private zzko(zzkl zzkl) {
        zzkl zzkl2 = (zzkl) zzle.zza(zzkl, "output");
        this.zza = zzkl2;
        zzkl2.zza = this;
    }

    public final int zza() {
        return 1;
    }

    public final void zzb(int i3, List<Double> list, boolean z2) throws IOException {
        int i4 = 0;
        if (list instanceof zzkn) {
            zzkn zzkn = (zzkn) list;
            if (z2) {
                this.zza.zzc(i3, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < zzkn.size(); i6++) {
                    i5 += zzkl.zza(zzkn.zzb(i6));
                }
                this.zza.zzc(i5);
                while (i4 < zzkn.size()) {
                    this.zza.zzb(zzkn.zzb(i4));
                    i4++;
                }
                return;
            }
            while (i4 < zzkn.size()) {
                this.zza.zzb(i3, zzkn.zzb(i4));
                i4++;
            }
        } else if (z2) {
            this.zza.zzc(i3, 2);
            int i7 = 0;
            for (int i8 = 0; i8 < list.size(); i8++) {
                i7 += zzkl.zza(list.get(i8).doubleValue());
            }
            this.zza.zzc(i7);
            while (i4 < list.size()) {
                this.zza.zzb(list.get(i4).doubleValue());
                i4++;
            }
        } else {
            while (i4 < list.size()) {
                this.zza.zzb(i3, list.get(i4).doubleValue());
                i4++;
            }
        }
    }

    public final void zzc(int i3, List<Integer> list, boolean z2) throws IOException {
        int i4 = 0;
        if (list instanceof zzlf) {
            zzlf zzlf = (zzlf) list;
            if (z2) {
                this.zza.zzc(i3, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < zzlf.size(); i6++) {
                    i5 += zzkl.zzd(zzlf.zzb(i6));
                }
                this.zza.zzc(i5);
                while (i4 < zzlf.size()) {
                    this.zza.zzb(zzlf.zzb(i4));
                    i4++;
                }
                return;
            }
            while (i4 < zzlf.size()) {
                this.zza.zzb(i3, zzlf.zzb(i4));
                i4++;
            }
        } else if (z2) {
            this.zza.zzc(i3, 2);
            int i7 = 0;
            for (int i8 = 0; i8 < list.size(); i8++) {
                i7 += zzkl.zzd(list.get(i8).intValue());
            }
            this.zza.zzc(i7);
            while (i4 < list.size()) {
                this.zza.zzb(list.get(i4).intValue());
                i4++;
            }
        } else {
            while (i4 < list.size()) {
                this.zza.zzb(i3, list.get(i4).intValue());
                i4++;
            }
        }
    }

    public final void zzd(int i3, List<Integer> list, boolean z2) throws IOException {
        int i4 = 0;
        if (list instanceof zzlf) {
            zzlf zzlf = (zzlf) list;
            if (z2) {
                this.zza.zzc(i3, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < zzlf.size(); i6++) {
                    i5 += zzkl.zze(zzlf.zzb(i6));
                }
                this.zza.zzc(i5);
                while (i4 < zzlf.size()) {
                    this.zza.zza(zzlf.zzb(i4));
                    i4++;
                }
                return;
            }
            while (i4 < zzlf.size()) {
                this.zza.zza(i3, zzlf.zzb(i4));
                i4++;
            }
        } else if (z2) {
            this.zza.zzc(i3, 2);
            int i7 = 0;
            for (int i8 = 0; i8 < list.size(); i8++) {
                i7 += zzkl.zze(list.get(i8).intValue());
            }
            this.zza.zzc(i7);
            while (i4 < list.size()) {
                this.zza.zza(list.get(i4).intValue());
                i4++;
            }
        } else {
            while (i4 < list.size()) {
                this.zza.zza(i3, list.get(i4).intValue());
                i4++;
            }
        }
    }

    public final void zze(int i3, List<Long> list, boolean z2) throws IOException {
        int i4 = 0;
        if (list instanceof zzlw) {
            zzlw zzlw = (zzlw) list;
            if (z2) {
                this.zza.zzc(i3, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < zzlw.size(); i6++) {
                    i5 += zzkl.zzc(zzlw.zzb(i6));
                }
                this.zza.zzc(i5);
                while (i4 < zzlw.size()) {
                    this.zza.zza(zzlw.zzb(i4));
                    i4++;
                }
                return;
            }
            while (i4 < zzlw.size()) {
                this.zza.zza(i3, zzlw.zzb(i4));
                i4++;
            }
        } else if (z2) {
            this.zza.zzc(i3, 2);
            int i7 = 0;
            for (int i8 = 0; i8 < list.size(); i8++) {
                i7 += zzkl.zzc(list.get(i8).longValue());
            }
            this.zza.zzc(i7);
            while (i4 < list.size()) {
                this.zza.zza(list.get(i4).longValue());
                i4++;
            }
        } else {
            while (i4 < list.size()) {
                this.zza.zza(i3, list.get(i4).longValue());
                i4++;
            }
        }
    }

    public final void zzf(int i3, List<Float> list, boolean z2) throws IOException {
        int i4 = 0;
        if (list instanceof zzlb) {
            zzlb zzlb = (zzlb) list;
            if (z2) {
                this.zza.zzc(i3, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < zzlb.size(); i6++) {
                    i5 += zzkl.zza(zzlb.zzb(i6));
                }
                this.zza.zzc(i5);
                while (i4 < zzlb.size()) {
                    this.zza.zzb(zzlb.zzb(i4));
                    i4++;
                }
                return;
            }
            while (i4 < zzlb.size()) {
                this.zza.zzb(i3, zzlb.zzb(i4));
                i4++;
            }
        } else if (z2) {
            this.zza.zzc(i3, 2);
            int i7 = 0;
            for (int i8 = 0; i8 < list.size(); i8++) {
                i7 += zzkl.zza(list.get(i8).floatValue());
            }
            this.zza.zzc(i7);
            while (i4 < list.size()) {
                this.zza.zzb(list.get(i4).floatValue());
                i4++;
            }
        } else {
            while (i4 < list.size()) {
                this.zza.zzb(i3, list.get(i4).floatValue());
                i4++;
            }
        }
    }

    public final void zzg(int i3, List<Integer> list, boolean z2) throws IOException {
        int i4 = 0;
        if (list instanceof zzlf) {
            zzlf zzlf = (zzlf) list;
            if (z2) {
                this.zza.zzc(i3, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < zzlf.size(); i6++) {
                    i5 += zzkl.zzf(zzlf.zzb(i6));
                }
                this.zza.zzc(i5);
                while (i4 < zzlf.size()) {
                    this.zza.zzb(zzlf.zzb(i4));
                    i4++;
                }
                return;
            }
            while (i4 < zzlf.size()) {
                this.zza.zzb(i3, zzlf.zzb(i4));
                i4++;
            }
        } else if (z2) {
            this.zza.zzc(i3, 2);
            int i7 = 0;
            for (int i8 = 0; i8 < list.size(); i8++) {
                i7 += zzkl.zzf(list.get(i8).intValue());
            }
            this.zza.zzc(i7);
            while (i4 < list.size()) {
                this.zza.zzb(list.get(i4).intValue());
                i4++;
            }
        } else {
            while (i4 < list.size()) {
                this.zza.zzb(i3, list.get(i4).intValue());
                i4++;
            }
        }
    }

    public final void zzh(int i3, List<Long> list, boolean z2) throws IOException {
        int i4 = 0;
        if (list instanceof zzlw) {
            zzlw zzlw = (zzlw) list;
            if (z2) {
                this.zza.zzc(i3, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < zzlw.size(); i6++) {
                    i5 += zzkl.zzd(zzlw.zzb(i6));
                }
                this.zza.zzc(i5);
                while (i4 < zzlw.size()) {
                    this.zza.zzb(zzlw.zzb(i4));
                    i4++;
                }
                return;
            }
            while (i4 < zzlw.size()) {
                this.zza.zzb(i3, zzlw.zzb(i4));
                i4++;
            }
        } else if (z2) {
            this.zza.zzc(i3, 2);
            int i7 = 0;
            for (int i8 = 0; i8 < list.size(); i8++) {
                i7 += zzkl.zzd(list.get(i8).longValue());
            }
            this.zza.zzc(i7);
            while (i4 < list.size()) {
                this.zza.zzb(list.get(i4).longValue());
                i4++;
            }
        } else {
            while (i4 < list.size()) {
                this.zza.zzb(i3, list.get(i4).longValue());
                i4++;
            }
        }
    }

    public final void zzi(int i3, List<Integer> list, boolean z2) throws IOException {
        int i4 = 0;
        if (list instanceof zzlf) {
            zzlf zzlf = (zzlf) list;
            if (z2) {
                this.zza.zzc(i3, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < zzlf.size(); i6++) {
                    i5 += zzkl.zzg(zzlf.zzb(i6));
                }
                this.zza.zzc(i5);
                while (i4 < zzlf.size()) {
                    this.zza.zza(zzlf.zzb(i4));
                    i4++;
                }
                return;
            }
            while (i4 < zzlf.size()) {
                this.zza.zza(i3, zzlf.zzb(i4));
                i4++;
            }
        } else if (z2) {
            this.zza.zzc(i3, 2);
            int i7 = 0;
            for (int i8 = 0; i8 < list.size(); i8++) {
                i7 += zzkl.zzg(list.get(i8).intValue());
            }
            this.zza.zzc(i7);
            while (i4 < list.size()) {
                this.zza.zza(list.get(i4).intValue());
                i4++;
            }
        } else {
            while (i4 < list.size()) {
                this.zza.zza(i3, list.get(i4).intValue());
                i4++;
            }
        }
    }

    public final void zzj(int i3, List<Long> list, boolean z2) throws IOException {
        int i4 = 0;
        if (list instanceof zzlw) {
            zzlw zzlw = (zzlw) list;
            if (z2) {
                this.zza.zzc(i3, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < zzlw.size(); i6++) {
                    i5 += zzkl.zze(zzlw.zzb(i6));
                }
                this.zza.zzc(i5);
                while (i4 < zzlw.size()) {
                    this.zza.zza(zzlw.zzb(i4));
                    i4++;
                }
                return;
            }
            while (i4 < zzlw.size()) {
                this.zza.zza(i3, zzlw.zzb(i4));
                i4++;
            }
        } else if (z2) {
            this.zza.zzc(i3, 2);
            int i7 = 0;
            for (int i8 = 0; i8 < list.size(); i8++) {
                i7 += zzkl.zze(list.get(i8).longValue());
            }
            this.zza.zzc(i7);
            while (i4 < list.size()) {
                this.zza.zza(list.get(i4).longValue());
                i4++;
            }
        } else {
            while (i4 < list.size()) {
                this.zza.zza(i3, list.get(i4).longValue());
                i4++;
            }
        }
    }

    public final void zzk(int i3, List<Integer> list, boolean z2) throws IOException {
        int i4 = 0;
        if (list instanceof zzlf) {
            zzlf zzlf = (zzlf) list;
            if (z2) {
                this.zza.zzc(i3, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < zzlf.size(); i6++) {
                    i5 += zzkl.zzh(zzlf.zzb(i6));
                }
                this.zza.zzc(i5);
                while (i4 < zzlf.size()) {
                    this.zza.zzk(zzlf.zzb(i4));
                    i4++;
                }
                return;
            }
            while (i4 < zzlf.size()) {
                this.zza.zzk(i3, zzlf.zzb(i4));
                i4++;
            }
        } else if (z2) {
            this.zza.zzc(i3, 2);
            int i7 = 0;
            for (int i8 = 0; i8 < list.size(); i8++) {
                i7 += zzkl.zzh(list.get(i8).intValue());
            }
            this.zza.zzc(i7);
            while (i4 < list.size()) {
                this.zza.zzk(list.get(i4).intValue());
                i4++;
            }
        } else {
            while (i4 < list.size()) {
                this.zza.zzk(i3, list.get(i4).intValue());
                i4++;
            }
        }
    }

    public final void zzl(int i3, List<Long> list, boolean z2) throws IOException {
        int i4 = 0;
        if (list instanceof zzlw) {
            zzlw zzlw = (zzlw) list;
            if (z2) {
                this.zza.zzc(i3, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < zzlw.size(); i6++) {
                    i5 += zzkl.zzf(zzlw.zzb(i6));
                }
                this.zza.zzc(i5);
                while (i4 < zzlw.size()) {
                    this.zza.zzh(zzlw.zzb(i4));
                    i4++;
                }
                return;
            }
            while (i4 < zzlw.size()) {
                this.zza.zzh(i3, zzlw.zzb(i4));
                i4++;
            }
        } else if (z2) {
            this.zza.zzc(i3, 2);
            int i7 = 0;
            for (int i8 = 0; i8 < list.size(); i8++) {
                i7 += zzkl.zzf(list.get(i8).longValue());
            }
            this.zza.zzc(i7);
            while (i4 < list.size()) {
                this.zza.zzh(list.get(i4).longValue());
                i4++;
            }
        } else {
            while (i4 < list.size()) {
                this.zza.zzh(i3, list.get(i4).longValue());
                i4++;
            }
        }
    }

    public final void zzm(int i3, List<Integer> list, boolean z2) throws IOException {
        int i4 = 0;
        if (list instanceof zzlf) {
            zzlf zzlf = (zzlf) list;
            if (z2) {
                this.zza.zzc(i3, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < zzlf.size(); i6++) {
                    i5 += zzkl.zzj(zzlf.zzb(i6));
                }
                this.zza.zzc(i5);
                while (i4 < zzlf.size()) {
                    this.zza.zzc(zzlf.zzb(i4));
                    i4++;
                }
                return;
            }
            while (i4 < zzlf.size()) {
                this.zza.zzd(i3, zzlf.zzb(i4));
                i4++;
            }
        } else if (z2) {
            this.zza.zzc(i3, 2);
            int i7 = 0;
            for (int i8 = 0; i8 < list.size(); i8++) {
                i7 += zzkl.zzj(list.get(i8).intValue());
            }
            this.zza.zzc(i7);
            while (i4 < list.size()) {
                this.zza.zzc(list.get(i4).intValue());
                i4++;
            }
        } else {
            while (i4 < list.size()) {
                this.zza.zzd(i3, list.get(i4).intValue());
                i4++;
            }
        }
    }

    public final void zzn(int i3, List<Long> list, boolean z2) throws IOException {
        int i4 = 0;
        if (list instanceof zzlw) {
            zzlw zzlw = (zzlw) list;
            if (z2) {
                this.zza.zzc(i3, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < zzlw.size(); i6++) {
                    i5 += zzkl.zzg(zzlw.zzb(i6));
                }
                this.zza.zzc(i5);
                while (i4 < zzlw.size()) {
                    this.zza.zzb(zzlw.zzb(i4));
                    i4++;
                }
                return;
            }
            while (i4 < zzlw.size()) {
                this.zza.zzb(i3, zzlw.zzb(i4));
                i4++;
            }
        } else if (z2) {
            this.zza.zzc(i3, 2);
            int i7 = 0;
            for (int i8 = 0; i8 < list.size(); i8++) {
                i7 += zzkl.zzg(list.get(i8).longValue());
            }
            this.zza.zzc(i7);
            while (i4 < list.size()) {
                this.zza.zzb(list.get(i4).longValue());
                i4++;
            }
        } else {
            while (i4 < list.size()) {
                this.zza.zzb(i3, list.get(i4).longValue());
                i4++;
            }
        }
    }

    public static zzko zza(zzkl zzkl) {
        zzko zzko = zzkl.zza;
        if (zzko != null) {
            return zzko;
        }
        return new zzko(zzkl);
    }

    public final void zza(int i3, boolean z2) throws IOException {
        this.zza.zza(i3, z2);
    }

    public final void zza(int i3, List<Boolean> list, boolean z2) throws IOException {
        int i4 = 0;
        if (list instanceof zzjq) {
            zzjq zzjq = (zzjq) list;
            if (z2) {
                this.zza.zzc(i3, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < zzjq.size(); i6++) {
                    i5 += zzkl.zza(zzjq.zzb(i6));
                }
                this.zza.zzc(i5);
                while (i4 < zzjq.size()) {
                    this.zza.zzb(zzjq.zzb(i4));
                    i4++;
                }
                return;
            }
            while (i4 < zzjq.size()) {
                this.zza.zza(i3, zzjq.zzb(i4));
                i4++;
            }
        } else if (z2) {
            this.zza.zzc(i3, 2);
            int i7 = 0;
            for (int i8 = 0; i8 < list.size(); i8++) {
                i7 += zzkl.zza(list.get(i8).booleanValue());
            }
            this.zza.zzc(i7);
            while (i4 < list.size()) {
                this.zza.zzb(list.get(i4).booleanValue());
                i4++;
            }
        } else {
            while (i4 < list.size()) {
                this.zza.zza(i3, list.get(i4).booleanValue());
                i4++;
            }
        }
    }

    public final void zzb(int i3, int i4) throws IOException {
        this.zza.zza(i3, i4);
    }

    public final void zzd(int i3, int i4) throws IOException {
        this.zza.zza(i3, i4);
    }

    public final void zze(int i3, int i4) throws IOException {
        this.zza.zzk(i3, i4);
    }

    public final void zzf(int i3, int i4) throws IOException {
        this.zza.zzd(i3, i4);
    }

    public final void zzb(int i3, long j2) throws IOException {
        this.zza.zzb(i3, j2);
    }

    public final void zze(int i3, long j2) throws IOException {
        this.zza.zzb(i3, j2);
    }

    public final void zzd(int i3, long j2) throws IOException {
        this.zza.zzh(i3, j2);
    }

    public final void zzb(int i3, Object obj, zznd zznd) throws IOException {
        this.zza.zza(i3, (zzml) obj, zznd);
    }

    public final void zza(int i3, zzjs zzjs) throws IOException {
        this.zza.zza(i3, zzjs);
    }

    public final void zzb(int i3, List<?> list, zznd zznd) throws IOException {
        for (int i4 = 0; i4 < list.size(); i4++) {
            zzb(i3, (Object) list.get(i4), zznd);
        }
    }

    public final void zzc(int i3, int i4) throws IOException {
        this.zza.zzb(i3, i4);
    }

    public final void zza(int i3, List<zzjs> list) throws IOException {
        for (int i4 = 0; i4 < list.size(); i4++) {
            this.zza.zza(i3, list.get(i4));
        }
    }

    public final void zzc(int i3, long j2) throws IOException {
        this.zza.zza(i3, j2);
    }

    @Deprecated
    public final void zzb(int i3) throws IOException {
        this.zza.zzc(i3, 3);
    }

    public final void zza(int i3, double d2) throws IOException {
        this.zza.zzb(i3, d2);
    }

    public final void zzb(int i3, List<String> list) throws IOException {
        int i4 = 0;
        if (list instanceof zzls) {
            zzls zzls = (zzls) list;
            while (i4 < list.size()) {
                Object zza2 = zzls.zza(i4);
                if (zza2 instanceof String) {
                    this.zza.zza(i3, (String) zza2);
                } else {
                    this.zza.zza(i3, (zzjs) zza2);
                }
                i4++;
            }
            return;
        }
        while (i4 < list.size()) {
            this.zza.zza(i3, list.get(i4));
            i4++;
        }
    }

    @Deprecated
    public final void zza(int i3) throws IOException {
        this.zza.zzc(i3, 4);
    }

    public final void zza(int i3, int i4) throws IOException {
        this.zza.zzb(i3, i4);
    }

    public final void zza(int i3, long j2) throws IOException {
        this.zza.zza(i3, j2);
    }

    public final void zza(int i3, float f2) throws IOException {
        this.zza.zzb(i3, f2);
    }

    public final void zza(int i3, Object obj, zznd zznd) throws IOException {
        zzkl zzkl = this.zza;
        zzkl.zzc(i3, 3);
        zznd.zza((zzml) obj, (zzos) zzkl.zza);
        zzkl.zzc(i3, 4);
    }

    public final void zza(int i3, List<?> list, zznd zznd) throws IOException {
        for (int i4 = 0; i4 < list.size(); i4++) {
            zza(i3, (Object) list.get(i4), zznd);
        }
    }

    public final <K, V> void zza(int i3, zzmc<K, V> zzmc, Map<K, V> map) throws IOException {
        for (Map.Entry next : map.entrySet()) {
            this.zza.zzc(i3, 2);
            this.zza.zzc(zzmd.zza(zzmc, next.getKey(), next.getValue()));
            zzmd.zza(this.zza, zzmc, next.getKey(), next.getValue());
        }
    }

    public final void zza(int i3, Object obj) throws IOException {
        if (obj instanceof zzjs) {
            this.zza.zzb(i3, (zzjs) obj);
        } else {
            this.zza.zza(i3, (zzml) obj);
        }
    }

    public final void zza(int i3, String str) throws IOException {
        this.zza.zza(i3, str);
    }
}
