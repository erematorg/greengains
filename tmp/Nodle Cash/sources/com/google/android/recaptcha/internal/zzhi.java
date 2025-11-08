package com.google.android.recaptcha.internal;

import java.io.IOException;
import java.util.List;

final class zzhi implements zzmd {
    private final zzhh zza;

    private zzhi(zzhh zzhh) {
        byte[] bArr = zzjc.zzd;
        this.zza = zzhh;
        zzhh.zza = this;
    }

    public static zzhi zza(zzhh zzhh) {
        zzhi zzhi = zzhh.zza;
        return zzhi != null ? zzhi : new zzhi(zzhh);
    }

    public final void zzA(int i3, List list, boolean z2) throws IOException {
        int i4 = 0;
        if (z2) {
            this.zza.zzo(i3, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                ((Long) list.get(i6)).getClass();
                i5 += 8;
            }
            this.zza.zzq(i5);
            while (i4 < list.size()) {
                this.zza.zzi(((Long) list.get(i4)).longValue());
                i4++;
            }
            return;
        }
        while (i4 < list.size()) {
            this.zza.zzh(i3, ((Long) list.get(i4)).longValue());
            i4++;
        }
    }

    public final void zzB(int i3, int i4) throws IOException {
        zzhh zzhh = this.zza;
        zzhh.zzp(i3, (i4 >> 31) ^ (i4 + i4));
    }

    public final void zzC(int i3, List list, boolean z2) throws IOException {
        int i4 = 0;
        if (z2) {
            this.zza.zzo(i3, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                int intValue = ((Integer) list.get(i6)).intValue();
                i5 += zzhh.zzy((intValue >> 31) ^ (intValue + intValue));
            }
            this.zza.zzq(i5);
            while (i4 < list.size()) {
                zzhh zzhh = this.zza;
                int intValue2 = ((Integer) list.get(i4)).intValue();
                zzhh.zzq((intValue2 >> 31) ^ (intValue2 + intValue2));
                i4++;
            }
            return;
        }
        while (i4 < list.size()) {
            zzhh zzhh2 = this.zza;
            int intValue3 = ((Integer) list.get(i4)).intValue();
            zzhh2.zzp(i3, (intValue3 >> 31) ^ (intValue3 + intValue3));
            i4++;
        }
    }

    public final void zzD(int i3, long j2) throws IOException {
        zzhh zzhh = this.zza;
        zzhh.zzr(i3, (j2 >> 63) ^ (j2 + j2));
    }

    public final void zzE(int i3, List list, boolean z2) throws IOException {
        int i4 = 0;
        if (z2) {
            this.zza.zzo(i3, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                long longValue = ((Long) list.get(i6)).longValue();
                i5 += zzhh.zzz((longValue >> 63) ^ (longValue + longValue));
            }
            this.zza.zzq(i5);
            while (i4 < list.size()) {
                zzhh zzhh = this.zza;
                long longValue2 = ((Long) list.get(i4)).longValue();
                zzhh.zzs((longValue2 >> 63) ^ (longValue2 + longValue2));
                i4++;
            }
            return;
        }
        while (i4 < list.size()) {
            zzhh zzhh2 = this.zza;
            long longValue3 = ((Long) list.get(i4)).longValue();
            zzhh2.zzr(i3, (longValue3 >> 63) ^ (longValue3 + longValue3));
            i4++;
        }
    }

    @Deprecated
    public final void zzF(int i3) throws IOException {
        this.zza.zzo(i3, 3);
    }

    public final void zzG(int i3, String str) throws IOException {
        this.zza.zzm(i3, str);
    }

    public final void zzH(int i3, List list) throws IOException {
        int i4 = 0;
        if (list instanceof zzjm) {
            zzjm zzjm = (zzjm) list;
            while (i4 < list.size()) {
                Object zzf = zzjm.zzf(i4);
                if (zzf instanceof String) {
                    this.zza.zzm(i3, (String) zzf);
                } else {
                    this.zza.zze(i3, (zzgw) zzf);
                }
                i4++;
            }
            return;
        }
        while (i4 < list.size()) {
            this.zza.zzm(i3, (String) list.get(i4));
            i4++;
        }
    }

    public final void zzI(int i3, int i4) throws IOException {
        this.zza.zzp(i3, i4);
    }

    public final void zzJ(int i3, List list, boolean z2) throws IOException {
        int i4 = 0;
        if (z2) {
            this.zza.zzo(i3, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzhh.zzy(((Integer) list.get(i6)).intValue());
            }
            this.zza.zzq(i5);
            while (i4 < list.size()) {
                this.zza.zzq(((Integer) list.get(i4)).intValue());
                i4++;
            }
            return;
        }
        while (i4 < list.size()) {
            this.zza.zzp(i3, ((Integer) list.get(i4)).intValue());
            i4++;
        }
    }

    public final void zzK(int i3, long j2) throws IOException {
        this.zza.zzr(i3, j2);
    }

    public final void zzL(int i3, List list, boolean z2) throws IOException {
        int i4 = 0;
        if (z2) {
            this.zza.zzo(i3, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzhh.zzz(((Long) list.get(i6)).longValue());
            }
            this.zza.zzq(i5);
            while (i4 < list.size()) {
                this.zza.zzs(((Long) list.get(i4)).longValue());
                i4++;
            }
            return;
        }
        while (i4 < list.size()) {
            this.zza.zzr(i3, ((Long) list.get(i4)).longValue());
            i4++;
        }
    }

    public final void zzb(int i3, boolean z2) throws IOException {
        this.zza.zzd(i3, z2);
    }

    public final void zzc(int i3, List list, boolean z2) throws IOException {
        int i4 = 0;
        if (z2) {
            this.zza.zzo(i3, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                ((Boolean) list.get(i6)).getClass();
                i5++;
            }
            this.zza.zzq(i5);
            while (i4 < list.size()) {
                this.zza.zzb(((Boolean) list.get(i4)).booleanValue() ? (byte) 1 : 0);
                i4++;
            }
            return;
        }
        while (i4 < list.size()) {
            this.zza.zzd(i3, ((Boolean) list.get(i4)).booleanValue());
            i4++;
        }
    }

    public final void zzd(int i3, zzgw zzgw) throws IOException {
        this.zza.zze(i3, zzgw);
    }

    public final void zze(int i3, List list) throws IOException {
        for (int i4 = 0; i4 < list.size(); i4++) {
            this.zza.zze(i3, (zzgw) list.get(i4));
        }
    }

    public final void zzf(int i3, double d2) throws IOException {
        this.zza.zzh(i3, Double.doubleToRawLongBits(d2));
    }

    public final void zzg(int i3, List list, boolean z2) throws IOException {
        int i4 = 0;
        if (z2) {
            this.zza.zzo(i3, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                ((Double) list.get(i6)).getClass();
                i5 += 8;
            }
            this.zza.zzq(i5);
            while (i4 < list.size()) {
                this.zza.zzi(Double.doubleToRawLongBits(((Double) list.get(i4)).doubleValue()));
                i4++;
            }
            return;
        }
        while (i4 < list.size()) {
            this.zza.zzh(i3, Double.doubleToRawLongBits(((Double) list.get(i4)).doubleValue()));
            i4++;
        }
    }

    @Deprecated
    public final void zzh(int i3) throws IOException {
        this.zza.zzo(i3, 4);
    }

    public final void zzi(int i3, int i4) throws IOException {
        this.zza.zzj(i3, i4);
    }

    public final void zzj(int i3, List list, boolean z2) throws IOException {
        int i4 = 0;
        if (z2) {
            this.zza.zzo(i3, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzhh.zzu(((Integer) list.get(i6)).intValue());
            }
            this.zza.zzq(i5);
            while (i4 < list.size()) {
                this.zza.zzk(((Integer) list.get(i4)).intValue());
                i4++;
            }
            return;
        }
        while (i4 < list.size()) {
            this.zza.zzj(i3, ((Integer) list.get(i4)).intValue());
            i4++;
        }
    }

    public final void zzk(int i3, int i4) throws IOException {
        this.zza.zzf(i3, i4);
    }

    public final void zzl(int i3, List list, boolean z2) throws IOException {
        int i4 = 0;
        if (z2) {
            this.zza.zzo(i3, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                ((Integer) list.get(i6)).getClass();
                i5 += 4;
            }
            this.zza.zzq(i5);
            while (i4 < list.size()) {
                this.zza.zzg(((Integer) list.get(i4)).intValue());
                i4++;
            }
            return;
        }
        while (i4 < list.size()) {
            this.zza.zzf(i3, ((Integer) list.get(i4)).intValue());
            i4++;
        }
    }

    public final void zzm(int i3, long j2) throws IOException {
        this.zza.zzh(i3, j2);
    }

    public final void zzn(int i3, List list, boolean z2) throws IOException {
        int i4 = 0;
        if (z2) {
            this.zza.zzo(i3, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                ((Long) list.get(i6)).getClass();
                i5 += 8;
            }
            this.zza.zzq(i5);
            while (i4 < list.size()) {
                this.zza.zzi(((Long) list.get(i4)).longValue());
                i4++;
            }
            return;
        }
        while (i4 < list.size()) {
            this.zza.zzh(i3, ((Long) list.get(i4)).longValue());
            i4++;
        }
    }

    public final void zzo(int i3, float f2) throws IOException {
        this.zza.zzf(i3, Float.floatToRawIntBits(f2));
    }

    public final void zzp(int i3, List list, boolean z2) throws IOException {
        int i4 = 0;
        if (z2) {
            this.zza.zzo(i3, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                ((Float) list.get(i6)).getClass();
                i5 += 4;
            }
            this.zza.zzq(i5);
            while (i4 < list.size()) {
                this.zza.zzg(Float.floatToRawIntBits(((Float) list.get(i4)).floatValue()));
                i4++;
            }
            return;
        }
        while (i4 < list.size()) {
            this.zza.zzf(i3, Float.floatToRawIntBits(((Float) list.get(i4)).floatValue()));
            i4++;
        }
    }

    public final void zzq(int i3, Object obj, zzkr zzkr) throws IOException {
        zzhh zzhh = this.zza;
        zzhh.zzo(i3, 3);
        zzkr.zzj((zzke) obj, zzhh.zza);
        zzhh.zzo(i3, 4);
    }

    public final void zzr(int i3, int i4) throws IOException {
        this.zza.zzj(i3, i4);
    }

    public final void zzs(int i3, List list, boolean z2) throws IOException {
        int i4 = 0;
        if (z2) {
            this.zza.zzo(i3, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzhh.zzu(((Integer) list.get(i6)).intValue());
            }
            this.zza.zzq(i5);
            while (i4 < list.size()) {
                this.zza.zzk(((Integer) list.get(i4)).intValue());
                i4++;
            }
            return;
        }
        while (i4 < list.size()) {
            this.zza.zzj(i3, ((Integer) list.get(i4)).intValue());
            i4++;
        }
    }

    public final void zzt(int i3, long j2) throws IOException {
        this.zza.zzr(i3, j2);
    }

    public final void zzu(int i3, List list, boolean z2) throws IOException {
        int i4 = 0;
        if (z2) {
            this.zza.zzo(i3, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzhh.zzz(((Long) list.get(i6)).longValue());
            }
            this.zza.zzq(i5);
            while (i4 < list.size()) {
                this.zza.zzs(((Long) list.get(i4)).longValue());
                i4++;
            }
            return;
        }
        while (i4 < list.size()) {
            this.zza.zzr(i3, ((Long) list.get(i4)).longValue());
            i4++;
        }
    }

    public final void zzv(int i3, Object obj, zzkr zzkr) throws IOException {
        zzke zzke = (zzke) obj;
        zzhe zzhe = (zzhe) this.zza;
        zzhe.zzq((i3 << 3) | 2);
        zzhe.zzq(((zzgf) zzke).zza(zzkr));
        zzkr.zzj(zzke, zzhe.zza);
    }

    public final void zzw(int i3, Object obj) throws IOException {
        if (obj instanceof zzgw) {
            zzhe zzhe = (zzhe) this.zza;
            zzhe.zzq(11);
            zzhe.zzp(2, i3);
            zzhe.zze(3, (zzgw) obj);
            zzhe.zzq(12);
            return;
        }
        zzhh zzhh = this.zza;
        zzke zzke = (zzke) obj;
        zzhe zzhe2 = (zzhe) zzhh;
        zzhe2.zzq(11);
        zzhe2.zzp(2, i3);
        zzhe2.zzq(26);
        zzhe2.zzq(zzke.zzn());
        zzke.zze(zzhh);
        zzhe2.zzq(12);
    }

    public final void zzx(int i3, int i4) throws IOException {
        this.zza.zzf(i3, i4);
    }

    public final void zzy(int i3, List list, boolean z2) throws IOException {
        int i4 = 0;
        if (z2) {
            this.zza.zzo(i3, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                ((Integer) list.get(i6)).getClass();
                i5 += 4;
            }
            this.zza.zzq(i5);
            while (i4 < list.size()) {
                this.zza.zzg(((Integer) list.get(i4)).intValue());
                i4++;
            }
            return;
        }
        while (i4 < list.size()) {
            this.zza.zzf(i3, ((Integer) list.get(i4)).intValue());
            i4++;
        }
    }

    public final void zzz(int i3, long j2) throws IOException {
        this.zza.zzh(i3, j2);
    }
}
