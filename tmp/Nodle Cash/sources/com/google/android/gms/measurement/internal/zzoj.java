package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzgn;
import java.util.Map;

final class zzoj {
    private long zza;
    private zzgn.zzj zzb;
    private String zzc;
    private Map<String, String> zzd;
    private zznt zze;

    public final zzoj zza(zzgn.zzj zzj) {
        this.zzb = zzj;
        return this;
    }

    public final zzoj zza(long j2) {
        this.zza = j2;
        return this;
    }

    public final zzoj zza(Map<String, String> map) {
        this.zzd = map;
        return this;
    }

    public final zzoj zza(zznt zznt) {
        this.zze = zznt;
        return this;
    }

    public final zzoj zza(String str) {
        this.zzc = str;
        return this;
    }

    public final zzog zza() {
        return new zzog(this.zza, this.zzb, this.zzc, this.zzd, this.zze);
    }
}
