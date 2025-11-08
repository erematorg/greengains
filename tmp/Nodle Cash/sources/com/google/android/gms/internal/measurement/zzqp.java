package com.google.android.gms.internal.measurement;

public final class zzqp implements zzqq {
    private static final zzir<Boolean> zza;
    private static final zzir<Boolean> zzb;

    static {
        zziz zza2 = new zziz(zzio.zza("com.google.android.gms.measurement")).zzb().zza();
        zza = zza2.zza("measurement.gbraid_campaign.gbraid.client", false);
        zzb = zza2.zza("measurement.gbraid_campaign.gbraid.service", false);
        zza2.zza("measurement.id.gbraid_campaign.service", 0);
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
