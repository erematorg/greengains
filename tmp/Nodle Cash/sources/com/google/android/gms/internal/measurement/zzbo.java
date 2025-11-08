package com.google.android.gms.internal.measurement;

import android.support.v4.media.session.a;
import java.util.List;

public final class zzbo extends zzay {
    public zzbo() {
        this.zza.add(zzbv.ADD);
        this.zza.add(zzbv.DIVIDE);
        this.zza.add(zzbv.MODULUS);
        this.zza.add(zzbv.MULTIPLY);
        this.zza.add(zzbv.NEGATE);
        this.zza.add(zzbv.POST_DECREMENT);
        this.zza.add(zzbv.POST_INCREMENT);
        this.zza.add(zzbv.PRE_DECREMENT);
        this.zza.add(zzbv.PRE_INCREMENT);
        this.zza.add(zzbv.SUBTRACT);
    }

    public final zzaq zza(String str, zzh zzh, List<zzaq> list) {
        switch (zzbr.zza[zzg.zza(str).ordinal()]) {
            case 1:
                zzg.zza(zzbv.ADD, 2, list);
                zzaq zza = zzh.zza(list.get(0));
                zzaq zza2 = zzh.zza(list.get(1));
                if ((zza instanceof zzak) || (zza instanceof zzas) || (zza2 instanceof zzak) || (zza2 instanceof zzas)) {
                    return new zzas(a.m(zza.zzf(), zza2.zzf()));
                }
                return new zzai(Double.valueOf(zza2.zze().doubleValue() + zza.zze().doubleValue()));
            case 2:
                zzg.zza(zzbv.DIVIDE, 2, list);
                return new zzai(Double.valueOf(zzh.zza(list.get(0)).zze().doubleValue() / zzh.zza(list.get(1)).zze().doubleValue()));
            case 3:
                zzg.zza(zzbv.MODULUS, 2, list);
                return new zzai(Double.valueOf(zzh.zza(list.get(0)).zze().doubleValue() % zzh.zza(list.get(1)).zze().doubleValue()));
            case 4:
                zzg.zza(zzbv.MULTIPLY, 2, list);
                return new zzai(Double.valueOf(zzh.zza(list.get(0)).zze().doubleValue() * zzh.zza(list.get(1)).zze().doubleValue()));
            case 5:
                zzg.zza(zzbv.NEGATE, 1, list);
                return new zzai(Double.valueOf(zzh.zza(list.get(0)).zze().doubleValue() * -1.0d));
            case 6:
            case 7:
                zzg.zza(str, 2, list);
                zzaq zza3 = zzh.zza(list.get(0));
                zzh.zza(list.get(1));
                return zza3;
            case 8:
            case 9:
                zzg.zza(str, 1, list);
                return zzh.zza(list.get(0));
            case 10:
                zzg.zza(zzbv.SUBTRACT, 2, list);
                zzaq zza4 = zzh.zza(list.get(0));
                zzai zzai = new zzai(Double.valueOf(zzh.zza(list.get(1)).zze().doubleValue() * -1.0d));
                return new zzai(Double.valueOf(zzai.zze().doubleValue() + zza4.zze().doubleValue()));
            default:
                return zza(str);
        }
    }
}
