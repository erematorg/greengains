package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.annotation.CheckForNull;

final class zzbg extends zzdb {
    final /* synthetic */ zzbi zza;

    public zzbg(zzbi zzbi) {
        this.zza = zzbi;
    }

    public final boolean contains(@CheckForNull Object obj) {
        Set entrySet = this.zza.zza.entrySet();
        entrySet.getClass();
        try {
            return entrySet.contains(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    public final Iterator iterator() {
        return new zzbh(this.zza);
    }

    public final boolean remove(@CheckForNull Object obj) {
        if (!contains(obj)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Objects.requireNonNull(entry);
        zzbi zzbi = this.zza;
        zzbr.zzr(zzbi.zzb, entry.getKey());
        return true;
    }

    public final Map zza() {
        return this.zza;
    }
}
