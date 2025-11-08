package com.google.android.gms.internal.measurement;

public final class zzpe implements zzpb {
    private static final zzir<Long> zza;

    static {
        zziz zza2 = new zziz(zzio.zza("com.google.android.gms.measurement")).zzb().zza();
        zza2.zza("measurement.client.consent_state_v1", true);
        zza2.zza("measurement.client.3p_consent_state_v1", true);
        zza2.zza("measurement.service.consent_state_v1_W36", true);
        zza = zza2.zza("measurement.service.storage_consent_support_version", 203600);
    }

    public final long zza() {
        return zza.zza().longValue();
    }
}
