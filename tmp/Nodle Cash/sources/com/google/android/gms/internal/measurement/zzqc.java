package com.google.android.gms.internal.measurement;

public final class zzqc implements zzpz {
    private static final zzir<Boolean> zza;
    private static final zzir<Boolean> zzb;
    private static final zzir<Boolean> zzc;

    static {
        zziz zza2 = new zziz(zzio.zza("com.google.android.gms.measurement")).zzb().zza();
        zza2.zza("measurement.client.ad_id_consent_fix", true);
        zza2.zza("measurement.service.consent.aiid_reset_fix", false);
        zza2.zza("measurement.service.consent.aiid_reset_fix2", true);
        zza = zza2.zza("measurement.service.consent.app_start_fix", true);
        zzb = zza2.zza("measurement.service.consent.params_on_fx", true);
        zzc = zza2.zza("measurement.service.consent.pfo_on_fx", true);
    }

    public final boolean zza() {
        return zza.zza().booleanValue();
    }

    public final boolean zzb() {
        return zzb.zza().booleanValue();
    }

    public final boolean zzc() {
        return zzc.zza().booleanValue();
    }
}
