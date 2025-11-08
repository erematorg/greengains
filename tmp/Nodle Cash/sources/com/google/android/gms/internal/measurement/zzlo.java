package com.google.android.gms.internal.measurement;

import java.util.Map;

final class zzlo<K> implements Map.Entry<K, Object> {
    private Map.Entry<K, zzlp> zza;

    public final K getKey() {
        return this.zza.getKey();
    }

    public final Object getValue() {
        if (this.zza.getValue() == null) {
            return null;
        }
        throw new NoSuchMethodError();
    }

    public final Object setValue(Object obj) {
        if (obj instanceof zzml) {
            return this.zza.getValue().zza((zzml) obj);
        }
        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }

    public final zzlp zza() {
        return this.zza.getValue();
    }

    private zzlo(Map.Entry<K, zzlp> entry) {
        this.zza = entry;
    }
}
