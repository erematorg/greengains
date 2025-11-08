package com.google.android.gms.internal.measurement;

public final class zzrn implements zzro {
    private static final zzir<Boolean> zza;
    private static final zzir<Boolean> zzb;
    private static final zzir<Boolean> zzc;
    private static final zzir<Boolean> zzd;
    private static final zzir<Boolean> zze;
    private static final zzir<Boolean> zzf;
    private static final zzir<Boolean> zzg;
    private static final zzir<Boolean> zzh;
    private static final zzir<Boolean> zzi;
    private static final zzir<Boolean> zzj;

    static {
        zziz zza2 = new zziz(zzio.zza("com.google.android.gms.measurement")).zzb().zza();
        zza2.zza("measurement.id.rb.attribution.bundle_on_backgrounded", 0);
        zza = zza2.zza("measurement.rb.attribution.ad_campaign_info", false);
        zzb = zza2.zza("measurement.rb.attribution.client.bundle_on_backgrounded", false);
        zzc = zza2.zza("measurement.rb.attribution.service.bundle_on_backgrounded", false);
        zzd = zza2.zza("measurement.rb.attribution.client2", true);
        zza2.zza("measurement.rb.attribution.dma_fix", true);
        zze = zza2.zza("measurement.rb.attribution.followup1.service", false);
        zza2.zza("measurement.rb.attribution.index_out_of_bounds_fix", true);
        zzf = zza2.zza("measurement.rb.attribution.retry_disposition", false);
        zzg = zza2.zza("measurement.rb.attribution.service", true);
        zzh = zza2.zza("measurement.rb.attribution.enable_trigger_redaction", true);
        zzi = zza2.zza("measurement.rb.attribution.uuid_generation", true);
        zza2.zza("measurement.id.rb.attribution.retry_disposition", 0);
        zzj = zza2.zza("measurement.rb.attribution.improved_retry", true);
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

    public final boolean zzf() {
        return zze.zza().booleanValue();
    }

    public final boolean zzg() {
        return zzf.zza().booleanValue();
    }

    public final boolean zzh() {
        return zzg.zza().booleanValue();
    }

    public final boolean zzi() {
        return zzh.zza().booleanValue();
    }

    public final boolean zzj() {
        return zzi.zza().booleanValue();
    }

    public final boolean zzk() {
        return zzj.zza().booleanValue();
    }
}
