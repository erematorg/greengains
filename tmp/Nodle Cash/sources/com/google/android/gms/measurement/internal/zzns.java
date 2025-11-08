package com.google.android.gms.measurement.internal;

import androidx.annotation.NonNull;
import java.util.Collections;
import java.util.Map;

final class zzns {
    private String zza;
    private Map<String, String> zzb;
    @NonNull
    private zznt zzc;

    public zzns(String str, zznt zznt) {
        this.zza = str;
        this.zzc = zznt;
    }

    public final zznt zza() {
        return this.zzc;
    }

    public final String zzb() {
        return this.zza;
    }

    @NonNull
    public final Map<String, String> zzc() {
        Map<String, String> map = this.zzb;
        return map == null ? Collections.emptyMap() : map;
    }

    public zzns(String str, Map<String, String> map, zznt zznt) {
        this.zza = str;
        this.zzb = map;
        this.zzc = zznt;
    }
}
