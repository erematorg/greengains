package com.google.android.gms.internal.measurement;

public final class zzpf implements zzpg {
    private static final zzir<Boolean> zza;
    private static final zzir<Boolean> zzb;
    private static final zzir<Boolean> zzc;
    private static final zzir<Boolean> zzd;

    static {
        zziz zza2 = new zziz(zzio.zza("com.google.android.gms.measurement")).zzb().zza();
        zza = zza2.zza("measurement.consent.stop_reset_on_storage_denied.client", true);
        zzb = zza2.zza("measurement.consent.stop_reset_on_storage_denied.service", true);
        zzc = zza2.zza("measurement.consent.scrub_audience_data_analytics_consent", true);
        zzd = zza2.zza("measurement.consent.fix_first_open_count_from_snapshot", true);
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

    public final boolean zzd() {
        return zzc.zza().booleanValue();
    }

    public final boolean zze() {
        return zzd.zza().booleanValue();
    }
}
