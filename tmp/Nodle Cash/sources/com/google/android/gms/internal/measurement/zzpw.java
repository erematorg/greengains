package com.google.android.gms.internal.measurement;

public final class zzpw implements zzpt {
    private static final zzir<Boolean> zza;
    private static final zzir<Boolean> zzb;

    static {
        zziz zza2 = new zziz(zzio.zza("com.google.android.gms.measurement")).zzb().zza();
        zza2.zza("measurement.dma_consent.client", true);
        zza2.zza("measurement.dma_consent.client_bow_check2", true);
        zza2.zza("measurement.dma_consent.separate_service_calls_fix", true);
        zza2.zza("measurement.dma_consent.service", true);
        zza = zza2.zza("measurement.dma_consent.service_database_update_fix", true);
        zza2.zza("measurement.dma_consent.service_dcu_event", true);
        zzb = zza2.zza("measurement.dma_consent.service_dcu_event2", true);
        zza2.zza("measurement.dma_consent.service_npa_remote_default", true);
        zza2.zza("measurement.dma_consent.service_split_batch_on_consent", true);
        zza2.zza("measurement.dma_consent.set_consent_inline_on_worker", true);
    }

    public final boolean zza() {
        return zza.zza().booleanValue();
    }

    public final boolean zzb() {
        return zzb.zza().booleanValue();
    }
}
