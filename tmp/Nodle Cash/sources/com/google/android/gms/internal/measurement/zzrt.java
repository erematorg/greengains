package com.google.android.gms.internal.measurement;

public final class zzrt implements zzru {
    private static final zzir<Boolean> zza;
    private static final zzir<Boolean> zzb;

    static {
        zziz zza2 = new zziz(zzio.zza("com.google.android.gms.measurement")).zzb().zza();
        zza2.zza("measurement.client.sessions.background_sessions_enabled", true);
        zza = zza2.zza("measurement.client.sessions.enable_fix_background_engagement", false);
        zza2.zza("measurement.client.sessions.immediate_start_enabled_foreground", true);
        zzb = zza2.zza("measurement.client.sessions.enable_pause_engagement_in_background", true);
        zza2.zza("measurement.client.sessions.remove_expired_session_properties_enabled", true);
        zza2.zza("measurement.client.sessions.session_id_enabled", true);
        zza2.zza("measurement.id.client.sessions.enable_fix_background_engagement", 0);
    }

    public final boolean zza() {
        return zza.zza().booleanValue();
    }

    public final boolean zzb() {
        return zzb.zza().booleanValue();
    }
}
