package com.google.android.gms.internal.measurement;

import java.util.List;
import java.util.Map;

public final class zzn extends zzap {
    private final zzac zza;

    public zzn(zzac zzac) {
        this.zza = zzac;
    }

    public final zzaq zza(String str, zzh zzh, List<zzaq> list) {
        str.getClass();
        char c3 = 65535;
        switch (str.hashCode()) {
            case 21624207:
                if (str.equals("getEventName")) {
                    c3 = 0;
                    break;
                }
                break;
            case 45521504:
                if (str.equals("getTimestamp")) {
                    c3 = 1;
                    break;
                }
                break;
            case 146575578:
                if (str.equals("getParamValue")) {
                    c3 = 2;
                    break;
                }
                break;
            case 700587132:
                if (str.equals("getParams")) {
                    c3 = 3;
                    break;
                }
                break;
            case 920706790:
                if (str.equals("setParamValue")) {
                    c3 = 4;
                    break;
                }
                break;
            case 1570616835:
                if (str.equals("setEventName")) {
                    c3 = 5;
                    break;
                }
                break;
        }
        switch (c3) {
            case 0:
                zzg.zza("getEventName", 0, list);
                return new zzas(this.zza.zzb().zzb());
            case 1:
                zzg.zza("getTimestamp", 0, list);
                return new zzai(Double.valueOf((double) this.zza.zzb().zza()));
            case 2:
                zzg.zza("getParamValue", 1, list);
                return zzj.zza(this.zza.zzb().zza(zzh.zza(list.get(0)).zzf()));
            case 3:
                zzg.zza("getParams", 0, list);
                Map<String, Object> zzc = this.zza.zzb().zzc();
                zzap zzap = new zzap();
                for (String next : zzc.keySet()) {
                    zzap.zza(next, zzj.zza(zzc.get(next)));
                }
                return zzap;
            case 4:
                zzg.zza("setParamValue", 2, list);
                String zzf = zzh.zza(list.get(0)).zzf();
                zzaq zza2 = zzh.zza(list.get(1));
                this.zza.zzb().zza(zzf, zzg.zza(zza2));
                return zza2;
            case 5:
                zzg.zza("setEventName", 1, list);
                zzaq zza3 = zzh.zza(list.get(0));
                if (zzaq.zzc.equals(zza3) || zzaq.zzd.equals(zza3)) {
                    throw new IllegalArgumentException("Illegal event name");
                }
                this.zza.zzb().zzb(zza3.zzf());
                return new zzas(zza3.zzf());
            default:
                return super.zza(str, zzh, list);
        }
    }
}
