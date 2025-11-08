package com.google.android.gms.internal.mlkit_vision_common;

import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;

final class zzw extends zzs {
    private final transient zzr zza;
    /* access modifiers changed from: private */
    public final transient Object[] zzb;
    /* access modifiers changed from: private */
    public final transient int zzc;

    public zzw(zzr zzr, Object[] objArr, int i3, int i4) {
        this.zza = zzr;
        this.zzb = objArr;
        this.zzc = i4;
    }

    public final boolean contains(@CheckForNull Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            return value != null && value.equals(this.zza.get(key));
        }
    }

    public final /* synthetic */ Iterator iterator() {
        return zzf().listIterator(0);
    }

    public final int size() {
        return this.zzc;
    }

    public final int zza(Object[] objArr, int i3) {
        return zzf().zza(objArr, 0);
    }

    public final zzab zzd() {
        return zzf().listIterator(0);
    }

    public final zzp zzg() {
        return new zzv(this);
    }
}
