package com.google.android.gms.internal.measurement;

public final class zzpl implements zzpm {
    private static final zzir<Boolean> zza;
    private static final zzir<Boolean> zzb;

    static {
        zziz zza2 = new zziz(zzio.zza("com.google.android.gms.measurement")).zzb().zza();
        zza = zza2.zza("measurement.set_default_event_parameters_propagate_clear.client.dev", false);
        zzb = zza2.zza("measurement.set_default_event_parameters_propagate_clear.service", false);
    }

    public final boolean zza() {
        return zza.zza().booleanValue();
    }

    public final boolean zzb() {
        return zzb.zza().booleanValue();
    }
}
