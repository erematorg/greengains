package com.google.android.gms.internal.measurement;

import java.util.List;

public final class zzba extends zzay {
    public zzba() {
        this.zza.add(zzbv.EQUALS);
        this.zza.add(zzbv.GREATER_THAN);
        this.zza.add(zzbv.GREATER_THAN_EQUALS);
        this.zza.add(zzbv.IDENTITY_EQUALS);
        this.zza.add(zzbv.IDENTITY_NOT_EQUALS);
        this.zza.add(zzbv.LESS_THAN);
        this.zza.add(zzbv.LESS_THAN_EQUALS);
        this.zza.add(zzbv.NOT_EQUALS);
    }

    private static boolean zzb(zzaq zzaq, zzaq zzaq2) {
        if (zzaq instanceof zzak) {
            zzaq = new zzas(zzaq.zzf());
        }
        if (zzaq2 instanceof zzak) {
            zzaq2 = new zzas(zzaq2.zzf());
        }
        if ((zzaq instanceof zzas) && (zzaq2 instanceof zzas)) {
            return zzaq.zzf().compareTo(zzaq2.zzf()) < 0;
        }
        double doubleValue = zzaq.zze().doubleValue();
        double doubleValue2 = zzaq2.zze().doubleValue();
        return !Double.isNaN(doubleValue) && !Double.isNaN(doubleValue2) && !(doubleValue == 0.0d && doubleValue2 == -0.0d) && (!(doubleValue == -0.0d && doubleValue2 == 0.0d) && Double.compare(doubleValue, doubleValue2) < 0);
    }

    private static boolean zzc(zzaq zzaq, zzaq zzaq2) {
        if (zzaq instanceof zzak) {
            zzaq = new zzas(zzaq.zzf());
        }
        if (zzaq2 instanceof zzak) {
            zzaq2 = new zzas(zzaq2.zzf());
        }
        return (((zzaq instanceof zzas) && (zzaq2 instanceof zzas)) || (!Double.isNaN(zzaq.zze().doubleValue()) && !Double.isNaN(zzaq2.zze().doubleValue()))) && !zzb(zzaq2, zzaq);
    }

    public final zzaq zza(String str, zzh zzh, List<zzaq> list) {
        boolean z2;
        boolean zza;
        zzg.zza(zzg.zza(str), 2, list);
        zzaq zza2 = zzh.zza(list.get(0));
        zzaq zza3 = zzh.zza(list.get(1));
        switch (zzbd.zza[zzg.zza(str).ordinal()]) {
            case 1:
                z2 = zza(zza2, zza3);
                break;
            case 2:
                z2 = zzb(zza3, zza2);
                break;
            case 3:
                z2 = zzc(zza3, zza2);
                break;
            case 4:
                z2 = zzg.zza(zza2, zza3);
                break;
            case 5:
                zza = zzg.zza(zza2, zza3);
                break;
            case 6:
                z2 = zzb(zza2, zza3);
                break;
            case 7:
                z2 = zzc(zza2, zza3);
                break;
            case 8:
                zza = zza(zza2, zza3);
                break;
            default:
                return zza(str);
        }
        z2 = !zza;
        return z2 ? zzaq.zzh : zzaq.zzi;
    }

    private static boolean zza(zzaq zzaq, zzaq zzaq2) {
        zzaq zzas;
        zzaq zzas2;
        while (!zzaq.getClass().equals(zzaq2.getClass())) {
            if (((zzaq instanceof zzax) || (zzaq instanceof zzao)) && ((zzaq2 instanceof zzax) || (zzaq2 instanceof zzao))) {
                return true;
            }
            boolean z2 = zzaq instanceof zzai;
            if (!z2 || !(zzaq2 instanceof zzas)) {
                boolean z3 = zzaq instanceof zzas;
                if (z3 && (zzaq2 instanceof zzai)) {
                    zzas = new zzai(zzaq.zze());
                } else if (zzaq instanceof zzag) {
                    zzas = new zzai(zzaq.zze());
                } else if (zzaq2 instanceof zzag) {
                    zzas2 = new zzai(zzaq2.zze());
                } else if ((z3 || z2) && (zzaq2 instanceof zzak)) {
                    zzas2 = new zzas(zzaq2.zzf());
                } else if (!(zzaq instanceof zzak) || (!(zzaq2 instanceof zzas) && !(zzaq2 instanceof zzai))) {
                    return false;
                } else {
                    zzas = new zzas(zzaq.zzf());
                }
                zzaq = zzas;
            } else {
                zzas2 = new zzai(zzaq2.zze());
            }
            zzaq2 = zzas2;
        }
        if ((zzaq instanceof zzax) || (zzaq instanceof zzao)) {
            return true;
        }
        if (zzaq instanceof zzai) {
            return !Double.isNaN(zzaq.zze().doubleValue()) && !Double.isNaN(zzaq2.zze().doubleValue()) && zzaq.zze().doubleValue() == zzaq2.zze().doubleValue();
        }
        if (zzaq instanceof zzas) {
            return zzaq.zzf().equals(zzaq2.zzf());
        }
        if (zzaq instanceof zzag) {
            return zzaq.zzd().equals(zzaq2.zzd());
        }
        return zzaq == zzaq2;
    }
}
