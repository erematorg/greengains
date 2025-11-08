package com.google.android.gms.internal.measurement;

import androidx.camera.camera2.internal.C0118y;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.common.collect.ImmutableSet;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.text.StringSubstitutor;

public final class zzad {
    private static final ImmutableSet<String> zza = ImmutableSet.of("_syn", "_err", "_el");
    private String zzb;
    private long zzc;
    private Map<String, Object> zzd;

    public zzad(String str, long j2, Map<String, Object> map) {
        this.zzb = str;
        this.zzc = j2;
        HashMap hashMap = new HashMap();
        this.zzd = hashMap;
        if (map != null) {
            hashMap.putAll(map);
        }
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return new zzad(this.zzb, this.zzc, new HashMap(this.zzd));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzad)) {
            return false;
        }
        zzad zzad = (zzad) obj;
        if (this.zzc == zzad.zzc && this.zzb.equals(zzad.zzb)) {
            return this.zzd.equals(zzad.zzd);
        }
        return false;
    }

    public final int hashCode() {
        long j2 = this.zzc;
        return this.zzd.hashCode() + (((this.zzb.hashCode() * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31);
    }

    public final String toString() {
        String str = this.zzb;
        long j2 = this.zzc;
        return C0118y.j(a.i(j2, "Event{name='", str, "', timestamp="), ", params=", String.valueOf(this.zzd), StringSubstitutor.DEFAULT_VAR_END);
    }

    public final long zza() {
        return this.zzc;
    }

    public final String zzb() {
        return this.zzb;
    }

    public final Map<String, Object> zzc() {
        return this.zzd;
    }

    public final Object zza(String str) {
        if (this.zzd.containsKey(str)) {
            return this.zzd.get(str);
        }
        return null;
    }

    public final void zzb(String str) {
        this.zzb = str;
    }

    public static Object zza(String str, Object obj, Object obj2) {
        if (zza.contains(str) && (obj2 instanceof Double)) {
            return Long.valueOf(Math.round(((Double) obj2).doubleValue()));
        }
        if (str.startsWith("_")) {
            return (!(obj instanceof String) && obj != null) ? obj : obj2;
        }
        if (obj instanceof Double) {
            return obj2;
        }
        if (obj instanceof Long) {
            return Long.valueOf(Math.round(((Double) obj2).doubleValue()));
        }
        return obj instanceof String ? obj2.toString() : obj2;
    }

    public final void zza(String str, Object obj) {
        if (obj == null) {
            this.zzd.remove(str);
            return;
        }
        this.zzd.put(str, zza(str, this.zzd.get(str), obj));
    }
}
