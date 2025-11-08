package com.google.android.gms.internal.measurement;

public final class zzpk implements zzph {
    private static final zzir<Boolean> zza;
    private static final zzir<Boolean> zzb;

    static {
        zziz zza2 = new zziz(zzio.zza("com.google.android.gms.measurement")).zzb().zza();
        zza = zza2.zza("measurement.set_default_event_parameters_with_backfill.client.dev", false);
        zzb = zza2.zza("measurement.set_default_event_parameters_with_backfill.service", false);
    }

    public final boolean zza() {
        return true;
    }

    public final boolean zzb() {
        return zza.zza().booleanValue();
    }

    public final boolean zzc() {
        return zzb.zza().booleanValue();
    }
}
