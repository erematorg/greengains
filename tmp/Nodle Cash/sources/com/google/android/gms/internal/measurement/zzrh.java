package com.google.android.gms.internal.measurement;

public final class zzrh implements zzri {
    private static final zzir<Boolean> zza;
    private static final zzir<Long> zzb;
    private static final zzir<Double> zzc;
    private static final zzir<Long> zzd;
    private static final zzir<Long> zze;
    private static final zzir<String> zzf;

    static {
        zziz zza2 = new zziz(zzio.zza("com.google.android.gms.measurement")).zzb().zza();
        zza = zza2.zza("measurement.test.boolean_flag", false);
        zzb = zza2.zza("measurement.test.cached_long_flag", -1);
        zzc = zza2.zza("measurement.test.double_flag", -3.0d);
        zzd = zza2.zza("measurement.test.int_flag", -2);
        zze = zza2.zza("measurement.test.long_flag", -1);
        zzf = zza2.zza("measurement.test.string_flag", "---");
    }

    public final double zza() {
        return zzc.zza().doubleValue();
    }

    public final long zzb() {
        return zzb.zza().longValue();
    }

    public final long zzc() {
        return zzd.zza().longValue();
    }

    public final long zzd() {
        return zze.zza().longValue();
    }

    public final String zze() {
        return zzf.zza();
    }

    public final boolean zzf() {
        return zza.zza().booleanValue();
    }
}
