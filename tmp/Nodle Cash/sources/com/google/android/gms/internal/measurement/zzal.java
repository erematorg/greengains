package com.google.android.gms.internal.measurement;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class zzal implements zzak, zzaq {
    protected final String zza;
    protected final Map<String, zzaq> zzb = new HashMap();

    public zzal(String str) {
        this.zza = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzal)) {
            return false;
        }
        zzal zzal = (zzal) obj;
        String str = this.zza;
        if (str != null) {
            return str.equals(zzal.zza);
        }
        return false;
    }

    public int hashCode() {
        String str = this.zza;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public abstract zzaq zza(zzh zzh, List<zzaq> list);

    public final zzaq zza(String str, zzh zzh, List<zzaq> list) {
        if ("toString".equals(str)) {
            return new zzas(this.zza);
        }
        return zzan.zza(this, new zzas(str), zzh, list);
    }

    public zzaq zzc() {
        return this;
    }

    public final Boolean zzd() {
        return Boolean.TRUE;
    }

    public final Double zze() {
        return Double.valueOf(Double.NaN);
    }

    public final String zzf() {
        return this.zza;
    }

    public final Iterator<zzaq> zzh() {
        return zzan.zza(this.zzb);
    }

    public final boolean zzc(String str) {
        return this.zzb.containsKey(str);
    }

    public final zzaq zza(String str) {
        if (this.zzb.containsKey(str)) {
            return this.zzb.get(str);
        }
        return zzaq.zzc;
    }

    public final String zza() {
        return this.zza;
    }

    public final void zza(String str, zzaq zzaq) {
        if (zzaq == null) {
            this.zzb.remove(str);
        } else {
            this.zzb.put(str, zzaq);
        }
    }
}
