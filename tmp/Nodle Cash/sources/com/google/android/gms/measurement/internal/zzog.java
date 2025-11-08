package com.google.android.gms.measurement.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.internal.measurement.zzgn;
import java.util.Map;

public final class zzog {
    private long zza;
    private zzgn.zzj zzb;
    private String zzc;
    private Map<String, String> zzd;

    public final long zza() {
        return this.zza;
    }

    @Nullable
    public final zzgn.zzj zzb() {
        return this.zzb;
    }

    public final String zzc() {
        return this.zzc;
    }

    public final Map<String, String> zzd() {
        return this.zzd;
    }

    private zzog(long j2, zzgn.zzj zzj, String str, Map<String, String> map, zznt zznt) {
        this.zza = j2;
        this.zzb = zzj;
        this.zzc = str;
        this.zzd = map;
    }
}
