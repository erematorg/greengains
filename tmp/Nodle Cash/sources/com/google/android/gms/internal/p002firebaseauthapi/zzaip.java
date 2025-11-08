package com.google.android.gms.internal.p002firebaseauthapi;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaip  reason: invalid package */
final class zzaip implements zzanc {
    private final zzaim zza;

    private zzaip(zzaim zzaim) {
        zzaim zzaim2 = (zzaim) zzajh.zza(zzaim, "output");
        this.zza = zzaim2;
        zzaim2.zze = this;
    }

    public static zzaip zza(zzaim zzaim) {
        zzaip zzaip = zzaim.zze;
        if (zzaip != null) {
            return zzaip;
        }
        return new zzaip(zzaim);
    }

    public final void zzb(int i3, List<Double> list, boolean z2) throws IOException {
        int i4 = 0;
        if (list instanceof zzaio) {
            zzaio zzaio = (zzaio) list;
            if (z2) {
                this.zza.zzk(i3, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < zzaio.size(); i6++) {
                    i5 += zzaim.zza(zzaio.zzb(i6));
                }
                this.zza.zzn(i5);
                while (i4 < zzaio.size()) {
                    this.zza.zzb(zzaio.zzb(i4));
                    i4++;
                }
                return;
            }
            while (i4 < zzaio.size()) {
                this.zza.zzb(i3, zzaio.zzb(i4));
                i4++;
            }
        } else if (z2) {
            this.zza.zzk(i3, 2);
            int i7 = 0;
            for (int i8 = 0; i8 < list.size(); i8++) {
                i7 += zzaim.zza(list.get(i8).doubleValue());
            }
            this.zza.zzn(i7);
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
        if (z2) {
            this.zza.zzk(i3, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzaim.zzc(list.get(i6).intValue());
            }
            this.zza.zzn(i5);
            while (i4 < list.size()) {
                this.zza.zzl(list.get(i4).intValue());
                i4++;
            }
            return;
        }
        while (i4 < list.size()) {
            this.zza.zzi(i3, list.get(i4).intValue());
            i4++;
        }
    }

    public final void zzd(int i3, List<Integer> list, boolean z2) throws IOException {
        int i4 = 0;
        if (list instanceof zzajf) {
            zzajf zzajf = (zzajf) list;
            if (z2) {
                this.zza.zzk(i3, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < zzajf.size(); i6++) {
                    i5 += zzaim.zzd(zzajf.zzb(i6));
                }
                this.zza.zzn(i5);
                while (i4 < zzajf.size()) {
                    this.zza.zzk(zzajf.zzb(i4));
                    i4++;
                }
                return;
            }
            while (i4 < zzajf.size()) {
                this.zza.zzh(i3, zzajf.zzb(i4));
                i4++;
            }
        } else if (z2) {
            this.zza.zzk(i3, 2);
            int i7 = 0;
            for (int i8 = 0; i8 < list.size(); i8++) {
                i7 += zzaim.zzd(list.get(i8).intValue());
            }
            this.zza.zzn(i7);
            while (i4 < list.size()) {
                this.zza.zzk(list.get(i4).intValue());
                i4++;
            }
        } else {
            while (i4 < list.size()) {
                this.zza.zzh(i3, list.get(i4).intValue());
                i4++;
            }
        }
    }

    public final void zze(int i3, List<Long> list, boolean z2) throws IOException {
        int i4 = 0;
        if (list instanceof zzaka) {
            zzaka zzaka = (zzaka) list;
            if (z2) {
                this.zza.zzk(i3, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < zzaka.size(); i6++) {
                    i5 += zzaim.zzc(zzaka.zzb(i6));
                }
                this.zza.zzn(i5);
                while (i4 < zzaka.size()) {
                    this.zza.zzh(zzaka.zzb(i4));
                    i4++;
                }
                return;
            }
            while (i4 < zzaka.size()) {
                this.zza.zzf(i3, zzaka.zzb(i4));
                i4++;
            }
        } else if (z2) {
            this.zza.zzk(i3, 2);
            int i7 = 0;
            for (int i8 = 0; i8 < list.size(); i8++) {
                i7 += zzaim.zzc(list.get(i8).longValue());
            }
            this.zza.zzn(i7);
            while (i4 < list.size()) {
                this.zza.zzh(list.get(i4).longValue());
                i4++;
            }
        } else {
            while (i4 < list.size()) {
                this.zza.zzf(i3, list.get(i4).longValue());
                i4++;
            }
        }
    }

    public final void zzf(int i3, List<Float> list, boolean z2) throws IOException {
        int i4 = 0;
        if (list instanceof zzajd) {
            zzajd zzajd = (zzajd) list;
            if (z2) {
                this.zza.zzk(i3, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < zzajd.size(); i6++) {
                    i5 += zzaim.zza(zzajd.zzb(i6));
                }
                this.zza.zzn(i5);
                while (i4 < zzajd.size()) {
                    this.zza.zzb(zzajd.zzb(i4));
                    i4++;
                }
                return;
            }
            while (i4 < zzajd.size()) {
                this.zza.zzb(i3, zzajd.zzb(i4));
                i4++;
            }
        } else if (z2) {
            this.zza.zzk(i3, 2);
            int i7 = 0;
            for (int i8 = 0; i8 < list.size(); i8++) {
                i7 += zzaim.zza(list.get(i8).floatValue());
            }
            this.zza.zzn(i7);
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
        if (list instanceof zzajf) {
            zzajf zzajf = (zzajf) list;
            if (z2) {
                this.zza.zzk(i3, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < zzajf.size(); i6++) {
                    i5 += zzaim.zze(zzajf.zzb(i6));
                }
                this.zza.zzn(i5);
                while (i4 < zzajf.size()) {
                    this.zza.zzl(zzajf.zzb(i4));
                    i4++;
                }
                return;
            }
            while (i4 < zzajf.size()) {
                this.zza.zzi(i3, zzajf.zzb(i4));
                i4++;
            }
        } else if (z2) {
            this.zza.zzk(i3, 2);
            int i7 = 0;
            for (int i8 = 0; i8 < list.size(); i8++) {
                i7 += zzaim.zze(list.get(i8).intValue());
            }
            this.zza.zzn(i7);
            while (i4 < list.size()) {
                this.zza.zzl(list.get(i4).intValue());
                i4++;
            }
        } else {
            while (i4 < list.size()) {
                this.zza.zzi(i3, list.get(i4).intValue());
                i4++;
            }
        }
    }

    public final void zzh(int i3, List<Long> list, boolean z2) throws IOException {
        int i4 = 0;
        if (list instanceof zzaka) {
            zzaka zzaka = (zzaka) list;
            if (z2) {
                this.zza.zzk(i3, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < zzaka.size(); i6++) {
                    i5 += zzaim.zzd(zzaka.zzb(i6));
                }
                this.zza.zzn(i5);
                while (i4 < zzaka.size()) {
                    this.zza.zzj(zzaka.zzb(i4));
                    i4++;
                }
                return;
            }
            while (i4 < zzaka.size()) {
                this.zza.zzh(i3, zzaka.zzb(i4));
                i4++;
            }
        } else if (z2) {
            this.zza.zzk(i3, 2);
            int i7 = 0;
            for (int i8 = 0; i8 < list.size(); i8++) {
                i7 += zzaim.zzd(list.get(i8).longValue());
            }
            this.zza.zzn(i7);
            while (i4 < list.size()) {
                this.zza.zzj(list.get(i4).longValue());
                i4++;
            }
        } else {
            while (i4 < list.size()) {
                this.zza.zzh(i3, list.get(i4).longValue());
                i4++;
            }
        }
    }

    public final void zzi(int i3, List<Integer> list, boolean z2) throws IOException {
        int i4 = 0;
        if (list instanceof zzajf) {
            zzajf zzajf = (zzajf) list;
            if (z2) {
                this.zza.zzk(i3, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < zzajf.size(); i6++) {
                    i5 += zzaim.zzg(zzajf.zzb(i6));
                }
                this.zza.zzn(i5);
                while (i4 < zzajf.size()) {
                    this.zza.zzk(zzajf.zzb(i4));
                    i4++;
                }
                return;
            }
            while (i4 < zzajf.size()) {
                this.zza.zzh(i3, zzajf.zzb(i4));
                i4++;
            }
        } else if (z2) {
            this.zza.zzk(i3, 2);
            int i7 = 0;
            for (int i8 = 0; i8 < list.size(); i8++) {
                i7 += zzaim.zzg(list.get(i8).intValue());
            }
            this.zza.zzn(i7);
            while (i4 < list.size()) {
                this.zza.zzk(list.get(i4).intValue());
                i4++;
            }
        } else {
            while (i4 < list.size()) {
                this.zza.zzh(i3, list.get(i4).intValue());
                i4++;
            }
        }
    }

    public final void zzj(int i3, List<Long> list, boolean z2) throws IOException {
        int i4 = 0;
        if (list instanceof zzaka) {
            zzaka zzaka = (zzaka) list;
            if (z2) {
                this.zza.zzk(i3, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < zzaka.size(); i6++) {
                    i5 += zzaim.zze(zzaka.zzb(i6));
                }
                this.zza.zzn(i5);
                while (i4 < zzaka.size()) {
                    this.zza.zzh(zzaka.zzb(i4));
                    i4++;
                }
                return;
            }
            while (i4 < zzaka.size()) {
                this.zza.zzf(i3, zzaka.zzb(i4));
                i4++;
            }
        } else if (z2) {
            this.zza.zzk(i3, 2);
            int i7 = 0;
            for (int i8 = 0; i8 < list.size(); i8++) {
                i7 += zzaim.zze(list.get(i8).longValue());
            }
            this.zza.zzn(i7);
            while (i4 < list.size()) {
                this.zza.zzh(list.get(i4).longValue());
                i4++;
            }
        } else {
            while (i4 < list.size()) {
                this.zza.zzf(i3, list.get(i4).longValue());
                i4++;
            }
        }
    }

    public final void zzk(int i3, List<Integer> list, boolean z2) throws IOException {
        int i4 = 0;
        if (list instanceof zzajf) {
            zzajf zzajf = (zzajf) list;
            if (z2) {
                this.zza.zzk(i3, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < zzajf.size(); i6++) {
                    i5 += zzaim.zzh(zzajf.zzb(i6));
                }
                this.zza.zzn(i5);
                while (i4 < zzajf.size()) {
                    this.zza.zzm(zzajf.zzb(i4));
                    i4++;
                }
                return;
            }
            while (i4 < zzajf.size()) {
                this.zza.zzj(i3, zzajf.zzb(i4));
                i4++;
            }
        } else if (z2) {
            this.zza.zzk(i3, 2);
            int i7 = 0;
            for (int i8 = 0; i8 < list.size(); i8++) {
                i7 += zzaim.zzh(list.get(i8).intValue());
            }
            this.zza.zzn(i7);
            while (i4 < list.size()) {
                this.zza.zzm(list.get(i4).intValue());
                i4++;
            }
        } else {
            while (i4 < list.size()) {
                this.zza.zzj(i3, list.get(i4).intValue());
                i4++;
            }
        }
    }

    public final void zzl(int i3, List<Long> list, boolean z2) throws IOException {
        int i4 = 0;
        if (list instanceof zzaka) {
            zzaka zzaka = (zzaka) list;
            if (z2) {
                this.zza.zzk(i3, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < zzaka.size(); i6++) {
                    i5 += zzaim.zzf(zzaka.zzb(i6));
                }
                this.zza.zzn(i5);
                while (i4 < zzaka.size()) {
                    this.zza.zzi(zzaka.zzb(i4));
                    i4++;
                }
                return;
            }
            while (i4 < zzaka.size()) {
                this.zza.zzg(i3, zzaka.zzb(i4));
                i4++;
            }
        } else if (z2) {
            this.zza.zzk(i3, 2);
            int i7 = 0;
            for (int i8 = 0; i8 < list.size(); i8++) {
                i7 += zzaim.zzf(list.get(i8).longValue());
            }
            this.zza.zzn(i7);
            while (i4 < list.size()) {
                this.zza.zzi(list.get(i4).longValue());
                i4++;
            }
        } else {
            while (i4 < list.size()) {
                this.zza.zzg(i3, list.get(i4).longValue());
                i4++;
            }
        }
    }

    public final void zzm(int i3, List<Integer> list, boolean z2) throws IOException {
        int i4 = 0;
        if (list instanceof zzajf) {
            zzajf zzajf = (zzajf) list;
            if (z2) {
                this.zza.zzk(i3, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < zzajf.size(); i6++) {
                    i5 += zzaim.zzj(zzajf.zzb(i6));
                }
                this.zza.zzn(i5);
                while (i4 < zzajf.size()) {
                    this.zza.zzn(zzajf.zzb(i4));
                    i4++;
                }
                return;
            }
            while (i4 < zzajf.size()) {
                this.zza.zzl(i3, zzajf.zzb(i4));
                i4++;
            }
        } else if (z2) {
            this.zza.zzk(i3, 2);
            int i7 = 0;
            for (int i8 = 0; i8 < list.size(); i8++) {
                i7 += zzaim.zzj(list.get(i8).intValue());
            }
            this.zza.zzn(i7);
            while (i4 < list.size()) {
                this.zza.zzn(list.get(i4).intValue());
                i4++;
            }
        } else {
            while (i4 < list.size()) {
                this.zza.zzl(i3, list.get(i4).intValue());
                i4++;
            }
        }
    }

    public final void zzn(int i3, List<Long> list, boolean z2) throws IOException {
        int i4 = 0;
        if (list instanceof zzaka) {
            zzaka zzaka = (zzaka) list;
            if (z2) {
                this.zza.zzk(i3, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < zzaka.size(); i6++) {
                    i5 += zzaim.zzg(zzaka.zzb(i6));
                }
                this.zza.zzn(i5);
                while (i4 < zzaka.size()) {
                    this.zza.zzj(zzaka.zzb(i4));
                    i4++;
                }
                return;
            }
            while (i4 < zzaka.size()) {
                this.zza.zzh(i3, zzaka.zzb(i4));
                i4++;
            }
        } else if (z2) {
            this.zza.zzk(i3, 2);
            int i7 = 0;
            for (int i8 = 0; i8 < list.size(); i8++) {
                i7 += zzaim.zzg(list.get(i8).longValue());
            }
            this.zza.zzn(i7);
            while (i4 < list.size()) {
                this.zza.zzj(list.get(i4).longValue());
                i4++;
            }
        } else {
            while (i4 < list.size()) {
                this.zza.zzh(i3, list.get(i4).longValue());
                i4++;
            }
        }
    }

    public final int zza() {
        return zzanf.zza;
    }

    public final void zza(int i3, boolean z2) throws IOException {
        this.zza.zzb(i3, z2);
    }

    public final void zza(int i3, List<Boolean> list, boolean z2) throws IOException {
        int i4 = 0;
        if (list instanceof zzahm) {
            zzahm zzahm = (zzahm) list;
            if (z2) {
                this.zza.zzk(i3, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < zzahm.size(); i6++) {
                    i5 += zzaim.zza(zzahm.zzb(i6));
                }
                this.zza.zzn(i5);
                while (i4 < zzahm.size()) {
                    this.zza.zzb(zzahm.zzb(i4));
                    i4++;
                }
                return;
            }
            while (i4 < zzahm.size()) {
                this.zza.zzb(i3, zzahm.zzb(i4));
                i4++;
            }
        } else if (z2) {
            this.zza.zzk(i3, 2);
            int i7 = 0;
            for (int i8 = 0; i8 < list.size(); i8++) {
                i7 += zzaim.zza(list.get(i8).booleanValue());
            }
            this.zza.zzn(i7);
            while (i4 < list.size()) {
                this.zza.zzb(list.get(i4).booleanValue());
                i4++;
            }
        } else {
            while (i4 < list.size()) {
                this.zza.zzb(i3, list.get(i4).booleanValue());
                i4++;
            }
        }
    }

    public final void zzc(int i3, int i4) throws IOException {
        this.zza.zzi(i3, i4);
    }

    public final void zzc(int i3, long j2) throws IOException {
        this.zza.zzf(i3, j2);
    }

    public final void zzb(int i3, int i4) throws IOException {
        this.zza.zzh(i3, i4);
    }

    public final void zzd(int i3, int i4) throws IOException {
        this.zza.zzh(i3, i4);
    }

    public final void zze(int i3, int i4) throws IOException {
        this.zza.zzj(i3, i4);
    }

    public final void zzf(int i3, int i4) throws IOException {
        this.zza.zzl(i3, i4);
    }

    public final void zzb(int i3, long j2) throws IOException {
        this.zza.zzh(i3, j2);
    }

    public final void zze(int i3, long j2) throws IOException {
        this.zza.zzh(i3, j2);
    }

    public final void zzd(int i3, long j2) throws IOException {
        this.zza.zzg(i3, j2);
    }

    public final void zzb(int i3, Object obj, zzalh zzalh) throws IOException {
        this.zza.zzc(i3, (zzakp) obj, zzalh);
    }

    public final void zza(int i3, zzaho zzaho) throws IOException {
        this.zza.zzc(i3, zzaho);
    }

    public final void zzb(int i3, List<?> list, zzalh zzalh) throws IOException {
        for (int i4 = 0; i4 < list.size(); i4++) {
            zzb(i3, (Object) list.get(i4), zzalh);
        }
    }

    public final void zza(int i3, List<zzaho> list) throws IOException {
        for (int i4 = 0; i4 < list.size(); i4++) {
            this.zza.zzc(i3, list.get(i4));
        }
    }

    @Deprecated
    public final void zzb(int i3) throws IOException {
        this.zza.zzk(i3, 3);
    }

    public final void zza(int i3, double d2) throws IOException {
        this.zza.zzb(i3, d2);
    }

    public final void zzb(int i3, List<String> list) throws IOException {
        int i4 = 0;
        if (list instanceof zzajv) {
            zzajv zzajv = (zzajv) list;
            while (i4 < list.size()) {
                Object zzb = zzajv.zzb(i4);
                if (zzb instanceof String) {
                    this.zza.zzb(i3, (String) zzb);
                } else {
                    this.zza.zzc(i3, (zzaho) zzb);
                }
                i4++;
            }
            return;
        }
        while (i4 < list.size()) {
            this.zza.zzb(i3, list.get(i4));
            i4++;
        }
    }

    @Deprecated
    public final void zza(int i3) throws IOException {
        this.zza.zzk(i3, 4);
    }

    public final void zza(int i3, int i4) throws IOException {
        this.zza.zzi(i3, i4);
    }

    public final void zza(int i3, long j2) throws IOException {
        this.zza.zzf(i3, j2);
    }

    public final void zza(int i3, float f2) throws IOException {
        this.zza.zzb(i3, f2);
    }

    public final void zza(int i3, Object obj, zzalh zzalh) throws IOException {
        zzaim zzaim = this.zza;
        zzaim.zzk(i3, 3);
        zzalh.zza((zzakp) obj, (zzanc) zzaim.zze);
        zzaim.zzk(i3, 4);
    }

    public final void zza(int i3, List<?> list, zzalh zzalh) throws IOException {
        for (int i4 = 0; i4 < list.size(); i4++) {
            zza(i3, (Object) list.get(i4), zzalh);
        }
    }

    public final <K, V> void zza(int i3, zzakg<K, V> zzakg, Map<K, V> map) throws IOException {
        for (Map.Entry next : map.entrySet()) {
            this.zza.zzk(i3, 2);
            this.zza.zzn(zzakh.zza(zzakg, next.getKey(), next.getValue()));
            zzakh.zza(this.zza, zzakg, next.getKey(), next.getValue());
        }
    }

    public final void zza(int i3, Object obj) throws IOException {
        if (obj instanceof zzaho) {
            this.zza.zzd(i3, (zzaho) obj);
        } else {
            this.zza.zzb(i3, (zzakp) obj);
        }
    }

    public final void zza(int i3, String str) throws IOException {
        this.zza.zzb(i3, str);
    }
}
