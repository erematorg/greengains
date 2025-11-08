package com.google.android.gms.internal.measurement;

public final class zzrs implements zzrp {
    private static final zzir<Boolean> zza;

    static {
        zziz zza2 = new zziz(zzio.zza("com.google.android.gms.measurement")).zzb().zza();
        zza2.zza("measurement.collection.enable_session_stitching_token.client.dev", true);
        zza2.zza("measurement.collection.enable_session_stitching_token.first_open_fix", true);
        zza = zza2.zza("measurement.session_stitching_token_enabled", false);
        zza2.zza("measurement.link_sst_to_sid", true);
    }

    public final boolean zza() {
        return true;
    }

    public final boolean zzb() {
        return zza.zza().booleanValue();
    }
}
