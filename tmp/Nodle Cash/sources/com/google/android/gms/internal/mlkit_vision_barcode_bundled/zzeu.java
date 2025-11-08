package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.Map;

final class zzeu implements Map.Entry {
    private final Map.Entry zza;

    public /* synthetic */ zzeu(Map.Entry entry, zzet zzet) {
        this.zza = entry;
    }

    public final Object getKey() {
        return this.zza.getKey();
    }

    public final Object getValue() {
        if (((zzew) this.zza.getValue()) == null) {
            return null;
        }
        throw null;
    }

    public final Object setValue(Object obj) {
        if (obj instanceof zzfm) {
            return ((zzew) this.zza.getValue()).zzc((zzfm) obj);
        }
        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }

    public final zzew zza() {
        return (zzew) this.zza.getValue();
    }
}
