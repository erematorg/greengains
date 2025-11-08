package com.google.mlkit.common.sdkinternal;

import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.HashMap;
import java.util.Map;

@KeepForSdk
public abstract class LazyInstanceMap<K, V> {
    @GuardedBy("instances")
    private final Map zza = new HashMap();

    @NonNull
    @KeepForSdk
    public abstract V create(@NonNull K k2);

    @NonNull
    @KeepForSdk
    public V get(@NonNull K k2) {
        synchronized (this.zza) {
            try {
                if (this.zza.containsKey(k2)) {
                    V v2 = this.zza.get(k2);
                    return v2;
                }
                V create = create(k2);
                this.zza.put(k2, create);
                return create;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
