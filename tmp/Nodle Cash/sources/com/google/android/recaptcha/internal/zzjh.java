package com.google.android.recaptcha.internal;

import java.util.Map;

final class zzjh implements Map.Entry {
    private final Map.Entry zza;

    public /* synthetic */ zzjh(Map.Entry entry, zzjg zzjg) {
        this.zza = entry;
    }

    public final Object getKey() {
        return this.zza.getKey();
    }

    public final Object getValue() {
        if (((zzjj) this.zza.getValue()) == null) {
            return null;
        }
        throw null;
    }

    public final Object setValue(Object obj) {
        if (obj instanceof zzke) {
            return ((zzjj) this.zza.getValue()).zzc((zzke) obj);
        }
        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }

    public final zzjj zza() {
        return (zzjj) this.zza.getValue();
    }
}
