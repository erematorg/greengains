package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzo;
import java.util.Map;

final class zzho implements zzo {
    private final /* synthetic */ String zza;
    private final /* synthetic */ zzhg zzb;

    public zzho(zzhg zzhg, String str) {
        this.zza = str;
        this.zzb = zzhg;
    }

    public final String zza(String str) {
        Map map = (Map) this.zzb.zzc.get(this.zza);
        if (map == null || !map.containsKey(str)) {
            return null;
        }
        return (String) map.get(str);
    }
}
