package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.io.IOException;
import java.util.List;

final class zzdo implements zzhh {
    private final zzdn zza;

    private zzdo(zzdn zzdn) {
        byte[] bArr = zzep.zzb;
        this.zza = zzdn;
        zzdn.zza = this;
    }

    public static zzdo zza(zzdn zzdn) {
        zzdo zzdo = zzdn.zza;
        return zzdo != null ? zzdo : new zzdo(zzdn);
    }

    public final void zzA(int i3, List list, boolean z2) throws IOException {
        int i4 = 0;
        if (list instanceof zzfb) {
            zzfb zzfb = (zzfb) list;
            if (z2) {
                this.zza.zzr(i3, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < zzfb.size(); i6++) {
                    zzfb.zze(i6);
                    i5 += 8;
                }
                this.zza.zzt(i5);
                while (i4 < zzfb.size()) {
                    this.zza.zzi(zzfb.zze(i4));
                    i4++;
                }
                return;
            }
            while (i4 < zzfb.size()) {
                this.zza.zzh(i3, zzfb.zze(i4));
                i4++;
            }
        } else if (z2) {
            this.zza.zzr(i3, 2);
            int i7 = 0;
            for (int i8 = 0; i8 < list.size(); i8++) {
                ((Long) list.get(i8)).getClass();
                i7 += 8;
            }
            this.zza.zzt(i7);
            while (i4 < list.size()) {
                this.zza.zzi(((Long) list.get(i4)).longValue());
                i4++;
            }
        } else {
            while (i4 < list.size()) {
                this.zza.zzh(i3, ((Long) list.get(i4)).longValue());
                i4++;
            }
        }
    }

    public final void zzB(int i3, int i4) throws IOException {
        zzdn zzdn = this.zza;
        zzdn.zzs(i3, (i4 >> 31) ^ (i4 + i4));
    }

    public final void zzC(int i3, List list, boolean z2) throws IOException {
        int i4 = 0;
        if (list instanceof zzei) {
            zzei zzei = (zzei) list;
            if (z2) {
                this.zza.zzr(i3, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < zzei.size(); i6++) {
                    int zze = zzei.zze(i6);
                    i5 += zzdn.zzA((zze >> 31) ^ (zze + zze));
                }
                this.zza.zzt(i5);
                while (i4 < zzei.size()) {
                    zzdn zzdn = this.zza;
                    int zze2 = zzei.zze(i4);
                    zzdn.zzt((zze2 >> 31) ^ (zze2 + zze2));
                    i4++;
                }
                return;
            }
            while (i4 < zzei.size()) {
                zzdn zzdn2 = this.zza;
                int zze3 = zzei.zze(i4);
                zzdn2.zzs(i3, (zze3 >> 31) ^ (zze3 + zze3));
                i4++;
            }
        } else if (z2) {
            this.zza.zzr(i3, 2);
            int i7 = 0;
            for (int i8 = 0; i8 < list.size(); i8++) {
                int intValue = ((Integer) list.get(i8)).intValue();
                i7 += zzdn.zzA((intValue >> 31) ^ (intValue + intValue));
            }
            this.zza.zzt(i7);
            while (i4 < list.size()) {
                zzdn zzdn3 = this.zza;
                int intValue2 = ((Integer) list.get(i4)).intValue();
                zzdn3.zzt((intValue2 >> 31) ^ (intValue2 + intValue2));
                i4++;
            }
        } else {
            while (i4 < list.size()) {
                zzdn zzdn4 = this.zza;
                int intValue3 = ((Integer) list.get(i4)).intValue();
                zzdn4.zzs(i3, (intValue3 >> 31) ^ (intValue3 + intValue3));
                i4++;
            }
        }
    }

    public final void zzD(int i3, long j2) throws IOException {
        zzdn zzdn = this.zza;
        zzdn.zzu(i3, (j2 >> 63) ^ (j2 + j2));
    }

    public final void zzE(int i3, List list, boolean z2) throws IOException {
        int i4 = 0;
        if (list instanceof zzfb) {
            zzfb zzfb = (zzfb) list;
            if (z2) {
                this.zza.zzr(i3, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < zzfb.size(); i6++) {
                    long zze = zzfb.zze(i6);
                    i5 += zzdn.zzB((zze >> 63) ^ (zze + zze));
                }
                this.zza.zzt(i5);
                while (i4 < zzfb.size()) {
                    zzdn zzdn = this.zza;
                    long zze2 = zzfb.zze(i4);
                    zzdn.zzv((zze2 >> 63) ^ (zze2 + zze2));
                    i4++;
                }
                return;
            }
            while (i4 < zzfb.size()) {
                zzdn zzdn2 = this.zza;
                long zze3 = zzfb.zze(i4);
                zzdn2.zzu(i3, (zze3 >> 63) ^ (zze3 + zze3));
                i4++;
            }
        } else if (z2) {
            this.zza.zzr(i3, 2);
            int i7 = 0;
            for (int i8 = 0; i8 < list.size(); i8++) {
                long longValue = ((Long) list.get(i8)).longValue();
                i7 += zzdn.zzB((longValue >> 63) ^ (longValue + longValue));
            }
            this.zza.zzt(i7);
            while (i4 < list.size()) {
                zzdn zzdn3 = this.zza;
                long longValue2 = ((Long) list.get(i4)).longValue();
                zzdn3.zzv((longValue2 >> 63) ^ (longValue2 + longValue2));
                i4++;
            }
        } else {
            while (i4 < list.size()) {
                zzdn zzdn4 = this.zza;
                long longValue3 = ((Long) list.get(i4)).longValue();
                zzdn4.zzu(i3, (longValue3 >> 63) ^ (longValue3 + longValue3));
                i4++;
            }
        }
    }

    @Deprecated
    public final void zzF(int i3) throws IOException {
        this.zza.zzr(i3, 3);
    }

    public final void zzG(int i3, String str) throws IOException {
        this.zza.zzp(i3, str);
    }

    public final void zzH(int i3, List list) throws IOException {
        int i4 = 0;
        if (list instanceof zzey) {
            zzey zzey = (zzey) list;
            while (i4 < list.size()) {
                Object zza2 = zzey.zza();
                if (zza2 instanceof String) {
                    this.zza.zzp(i3, (String) zza2);
                } else {
                    this.zza.zze(i3, (zzdf) zza2);
                }
                i4++;
            }
            return;
        }
        while (i4 < list.size()) {
            this.zza.zzp(i3, (String) list.get(i4));
            i4++;
        }
    }

    public final void zzI(int i3, int i4) throws IOException {
        this.zza.zzs(i3, i4);
    }

    public final void zzJ(int i3, List list, boolean z2) throws IOException {
        int i4 = 0;
        if (list instanceof zzei) {
            zzei zzei = (zzei) list;
            if (z2) {
                this.zza.zzr(i3, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < zzei.size(); i6++) {
                    i5 += zzdn.zzA(zzei.zze(i6));
                }
                this.zza.zzt(i5);
                while (i4 < zzei.size()) {
                    this.zza.zzt(zzei.zze(i4));
                    i4++;
                }
                return;
            }
            while (i4 < zzei.size()) {
                this.zza.zzs(i3, zzei.zze(i4));
                i4++;
            }
        } else if (z2) {
            this.zza.zzr(i3, 2);
            int i7 = 0;
            for (int i8 = 0; i8 < list.size(); i8++) {
                i7 += zzdn.zzA(((Integer) list.get(i8)).intValue());
            }
            this.zza.zzt(i7);
            while (i4 < list.size()) {
                this.zza.zzt(((Integer) list.get(i4)).intValue());
                i4++;
            }
        } else {
            while (i4 < list.size()) {
                this.zza.zzs(i3, ((Integer) list.get(i4)).intValue());
                i4++;
            }
        }
    }

    public final void zzK(int i3, long j2) throws IOException {
        this.zza.zzu(i3, j2);
    }

    public final void zzL(int i3, List list, boolean z2) throws IOException {
        int i4 = 0;
        if (list instanceof zzfb) {
            zzfb zzfb = (zzfb) list;
            if (z2) {
                this.zza.zzr(i3, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < zzfb.size(); i6++) {
                    i5 += zzdn.zzB(zzfb.zze(i6));
                }
                this.zza.zzt(i5);
                while (i4 < zzfb.size()) {
                    this.zza.zzv(zzfb.zze(i4));
                    i4++;
                }
                return;
            }
            while (i4 < zzfb.size()) {
                this.zza.zzu(i3, zzfb.zze(i4));
                i4++;
            }
        } else if (z2) {
            this.zza.zzr(i3, 2);
            int i7 = 0;
            for (int i8 = 0; i8 < list.size(); i8++) {
                i7 += zzdn.zzB(((Long) list.get(i8)).longValue());
            }
            this.zza.zzt(i7);
            while (i4 < list.size()) {
                this.zza.zzv(((Long) list.get(i4)).longValue());
                i4++;
            }
        } else {
            while (i4 < list.size()) {
                this.zza.zzu(i3, ((Long) list.get(i4)).longValue());
                i4++;
            }
        }
    }

    public final void zzb(int i3, boolean z2) throws IOException {
        this.zza.zzd(i3, z2);
    }

    public final void zzc(int i3, List list, boolean z2) throws IOException {
        int i4 = 0;
        if (list instanceof zzcw) {
            zzcw zzcw = (zzcw) list;
            if (z2) {
                this.zza.zzr(i3, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < zzcw.size(); i6++) {
                    zzcw.zzf(i6);
                    i5++;
                }
                this.zza.zzt(i5);
                while (i4 < zzcw.size()) {
                    this.zza.zzb(zzcw.zzf(i4) ? (byte) 1 : 0);
                    i4++;
                }
                return;
            }
            while (i4 < zzcw.size()) {
                this.zza.zzd(i3, zzcw.zzf(i4));
                i4++;
            }
        } else if (z2) {
            this.zza.zzr(i3, 2);
            int i7 = 0;
            for (int i8 = 0; i8 < list.size(); i8++) {
                ((Boolean) list.get(i8)).getClass();
                i7++;
            }
            this.zza.zzt(i7);
            while (i4 < list.size()) {
                this.zza.zzb(((Boolean) list.get(i4)).booleanValue() ? (byte) 1 : 0);
                i4++;
            }
        } else {
            while (i4 < list.size()) {
                this.zza.zzd(i3, ((Boolean) list.get(i4)).booleanValue());
                i4++;
            }
        }
    }

    public final void zzd(int i3, zzdf zzdf) throws IOException {
        this.zza.zze(i3, zzdf);
    }

    public final void zze(int i3, List list) throws IOException {
        for (int i4 = 0; i4 < list.size(); i4++) {
            this.zza.zze(i3, (zzdf) list.get(i4));
        }
    }

    public final void zzf(int i3, double d2) throws IOException {
        this.zza.zzh(i3, Double.doubleToRawLongBits(d2));
    }

    public final void zzg(int i3, List list, boolean z2) throws IOException {
        int i4 = 0;
        if (list instanceof zzdp) {
            zzdp zzdp = (zzdp) list;
            if (z2) {
                this.zza.zzr(i3, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < zzdp.size(); i6++) {
                    zzdp.zze(i6);
                    i5 += 8;
                }
                this.zza.zzt(i5);
                while (i4 < zzdp.size()) {
                    this.zza.zzi(Double.doubleToRawLongBits(zzdp.zze(i4)));
                    i4++;
                }
                return;
            }
            while (i4 < zzdp.size()) {
                this.zza.zzh(i3, Double.doubleToRawLongBits(zzdp.zze(i4)));
                i4++;
            }
        } else if (z2) {
            this.zza.zzr(i3, 2);
            int i7 = 0;
            for (int i8 = 0; i8 < list.size(); i8++) {
                ((Double) list.get(i8)).getClass();
                i7 += 8;
            }
            this.zza.zzt(i7);
            while (i4 < list.size()) {
                this.zza.zzi(Double.doubleToRawLongBits(((Double) list.get(i4)).doubleValue()));
                i4++;
            }
        } else {
            while (i4 < list.size()) {
                this.zza.zzh(i3, Double.doubleToRawLongBits(((Double) list.get(i4)).doubleValue()));
                i4++;
            }
        }
    }

    @Deprecated
    public final void zzh(int i3) throws IOException {
        this.zza.zzr(i3, 4);
    }

    public final void zzi(int i3, int i4) throws IOException {
        this.zza.zzj(i3, i4);
    }

    public final void zzj(int i3, List list, boolean z2) throws IOException {
        int i4 = 0;
        if (list instanceof zzei) {
            zzei zzei = (zzei) list;
            if (z2) {
                this.zza.zzr(i3, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < zzei.size(); i6++) {
                    i5 += zzdn.zzB((long) zzei.zze(i6));
                }
                this.zza.zzt(i5);
                while (i4 < zzei.size()) {
                    this.zza.zzk(zzei.zze(i4));
                    i4++;
                }
                return;
            }
            while (i4 < zzei.size()) {
                this.zza.zzj(i3, zzei.zze(i4));
                i4++;
            }
        } else if (z2) {
            this.zza.zzr(i3, 2);
            int i7 = 0;
            for (int i8 = 0; i8 < list.size(); i8++) {
                i7 += zzdn.zzB((long) ((Integer) list.get(i8)).intValue());
            }
            this.zza.zzt(i7);
            while (i4 < list.size()) {
                this.zza.zzk(((Integer) list.get(i4)).intValue());
                i4++;
            }
        } else {
            while (i4 < list.size()) {
                this.zza.zzj(i3, ((Integer) list.get(i4)).intValue());
                i4++;
            }
        }
    }

    public final void zzk(int i3, int i4) throws IOException {
        this.zza.zzf(i3, i4);
    }

    public final void zzl(int i3, List list, boolean z2) throws IOException {
        int i4 = 0;
        if (list instanceof zzei) {
            zzei zzei = (zzei) list;
            if (z2) {
                this.zza.zzr(i3, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < zzei.size(); i6++) {
                    zzei.zze(i6);
                    i5 += 4;
                }
                this.zza.zzt(i5);
                while (i4 < zzei.size()) {
                    this.zza.zzg(zzei.zze(i4));
                    i4++;
                }
                return;
            }
            while (i4 < zzei.size()) {
                this.zza.zzf(i3, zzei.zze(i4));
                i4++;
            }
        } else if (z2) {
            this.zza.zzr(i3, 2);
            int i7 = 0;
            for (int i8 = 0; i8 < list.size(); i8++) {
                ((Integer) list.get(i8)).getClass();
                i7 += 4;
            }
            this.zza.zzt(i7);
            while (i4 < list.size()) {
                this.zza.zzg(((Integer) list.get(i4)).intValue());
                i4++;
            }
        } else {
            while (i4 < list.size()) {
                this.zza.zzf(i3, ((Integer) list.get(i4)).intValue());
                i4++;
            }
        }
    }

    public final void zzm(int i3, long j2) throws IOException {
        this.zza.zzh(i3, j2);
    }

    public final void zzn(int i3, List list, boolean z2) throws IOException {
        int i4 = 0;
        if (list instanceof zzfb) {
            zzfb zzfb = (zzfb) list;
            if (z2) {
                this.zza.zzr(i3, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < zzfb.size(); i6++) {
                    zzfb.zze(i6);
                    i5 += 8;
                }
                this.zza.zzt(i5);
                while (i4 < zzfb.size()) {
                    this.zza.zzi(zzfb.zze(i4));
                    i4++;
                }
                return;
            }
            while (i4 < zzfb.size()) {
                this.zza.zzh(i3, zzfb.zze(i4));
                i4++;
            }
        } else if (z2) {
            this.zza.zzr(i3, 2);
            int i7 = 0;
            for (int i8 = 0; i8 < list.size(); i8++) {
                ((Long) list.get(i8)).getClass();
                i7 += 8;
            }
            this.zza.zzt(i7);
            while (i4 < list.size()) {
                this.zza.zzi(((Long) list.get(i4)).longValue());
                i4++;
            }
        } else {
            while (i4 < list.size()) {
                this.zza.zzh(i3, ((Long) list.get(i4)).longValue());
                i4++;
            }
        }
    }

    public final void zzo(int i3, float f2) throws IOException {
        this.zza.zzf(i3, Float.floatToRawIntBits(f2));
    }

    public final void zzp(int i3, List list, boolean z2) throws IOException {
        int i4 = 0;
        if (list instanceof zzdz) {
            zzdz zzdz = (zzdz) list;
            if (z2) {
                this.zza.zzr(i3, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < zzdz.size(); i6++) {
                    zzdz.zze(i6);
                    i5 += 4;
                }
                this.zza.zzt(i5);
                while (i4 < zzdz.size()) {
                    this.zza.zzg(Float.floatToRawIntBits(zzdz.zze(i4)));
                    i4++;
                }
                return;
            }
            while (i4 < zzdz.size()) {
                this.zza.zzf(i3, Float.floatToRawIntBits(zzdz.zze(i4)));
                i4++;
            }
        } else if (z2) {
            this.zza.zzr(i3, 2);
            int i7 = 0;
            for (int i8 = 0; i8 < list.size(); i8++) {
                ((Float) list.get(i8)).getClass();
                i7 += 4;
            }
            this.zza.zzt(i7);
            while (i4 < list.size()) {
                this.zza.zzg(Float.floatToRawIntBits(((Float) list.get(i4)).floatValue()));
                i4++;
            }
        } else {
            while (i4 < list.size()) {
                this.zza.zzf(i3, Float.floatToRawIntBits(((Float) list.get(i4)).floatValue()));
                i4++;
            }
        }
    }

    public final void zzq(int i3, Object obj, zzge zzge) throws IOException {
        zzdn zzdn = this.zza;
        zzdn.zzr(i3, 3);
        zzge.zzi((zzfm) obj, zzdn.zza);
        zzdn.zzr(i3, 4);
    }

    public final void zzr(int i3, int i4) throws IOException {
        this.zza.zzj(i3, i4);
    }

    public final void zzs(int i3, List list, boolean z2) throws IOException {
        int i4 = 0;
        if (list instanceof zzei) {
            zzei zzei = (zzei) list;
            if (z2) {
                this.zza.zzr(i3, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < zzei.size(); i6++) {
                    i5 += zzdn.zzB((long) zzei.zze(i6));
                }
                this.zza.zzt(i5);
                while (i4 < zzei.size()) {
                    this.zza.zzk(zzei.zze(i4));
                    i4++;
                }
                return;
            }
            while (i4 < zzei.size()) {
                this.zza.zzj(i3, zzei.zze(i4));
                i4++;
            }
        } else if (z2) {
            this.zza.zzr(i3, 2);
            int i7 = 0;
            for (int i8 = 0; i8 < list.size(); i8++) {
                i7 += zzdn.zzB((long) ((Integer) list.get(i8)).intValue());
            }
            this.zza.zzt(i7);
            while (i4 < list.size()) {
                this.zza.zzk(((Integer) list.get(i4)).intValue());
                i4++;
            }
        } else {
            while (i4 < list.size()) {
                this.zza.zzj(i3, ((Integer) list.get(i4)).intValue());
                i4++;
            }
        }
    }

    public final void zzt(int i3, long j2) throws IOException {
        this.zza.zzu(i3, j2);
    }

    public final void zzu(int i3, List list, boolean z2) throws IOException {
        int i4 = 0;
        if (list instanceof zzfb) {
            zzfb zzfb = (zzfb) list;
            if (z2) {
                this.zza.zzr(i3, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < zzfb.size(); i6++) {
                    i5 += zzdn.zzB(zzfb.zze(i6));
                }
                this.zza.zzt(i5);
                while (i4 < zzfb.size()) {
                    this.zza.zzv(zzfb.zze(i4));
                    i4++;
                }
                return;
            }
            while (i4 < zzfb.size()) {
                this.zza.zzu(i3, zzfb.zze(i4));
                i4++;
            }
        } else if (z2) {
            this.zza.zzr(i3, 2);
            int i7 = 0;
            for (int i8 = 0; i8 < list.size(); i8++) {
                i7 += zzdn.zzB(((Long) list.get(i8)).longValue());
            }
            this.zza.zzt(i7);
            while (i4 < list.size()) {
                this.zza.zzv(((Long) list.get(i4)).longValue());
                i4++;
            }
        } else {
            while (i4 < list.size()) {
                this.zza.zzu(i3, ((Long) list.get(i4)).longValue());
                i4++;
            }
        }
    }

    public final void zzv(int i3, Object obj, zzge zzge) throws IOException {
        this.zza.zzm(i3, (zzfm) obj, zzge);
    }

    public final void zzw(int i3, Object obj) throws IOException {
        boolean z2 = obj instanceof zzdf;
        zzdn zzdn = this.zza;
        if (z2) {
            zzdn.zzo(i3, (zzdf) obj);
        } else {
            zzdn.zzn(i3, (zzfm) obj);
        }
    }

    public final void zzx(int i3, int i4) throws IOException {
        this.zza.zzf(i3, i4);
    }

    public final void zzy(int i3, List list, boolean z2) throws IOException {
        int i4 = 0;
        if (list instanceof zzei) {
            zzei zzei = (zzei) list;
            if (z2) {
                this.zza.zzr(i3, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < zzei.size(); i6++) {
                    zzei.zze(i6);
                    i5 += 4;
                }
                this.zza.zzt(i5);
                while (i4 < zzei.size()) {
                    this.zza.zzg(zzei.zze(i4));
                    i4++;
                }
                return;
            }
            while (i4 < zzei.size()) {
                this.zza.zzf(i3, zzei.zze(i4));
                i4++;
            }
        } else if (z2) {
            this.zza.zzr(i3, 2);
            int i7 = 0;
            for (int i8 = 0; i8 < list.size(); i8++) {
                ((Integer) list.get(i8)).getClass();
                i7 += 4;
            }
            this.zza.zzt(i7);
            while (i4 < list.size()) {
                this.zza.zzg(((Integer) list.get(i4)).intValue());
                i4++;
            }
        } else {
            while (i4 < list.size()) {
                this.zza.zzf(i3, ((Integer) list.get(i4)).intValue());
                i4++;
            }
        }
    }

    public final void zzz(int i3, long j2) throws IOException {
        this.zza.zzh(i3, j2);
    }
}
