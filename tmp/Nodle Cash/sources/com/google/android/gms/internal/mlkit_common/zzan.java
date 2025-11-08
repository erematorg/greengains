package com.google.android.gms.internal.mlkit_common;

import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;

final class zzan extends zzaj {
    private final transient zzai zza;
    /* access modifiers changed from: private */
    public final transient Object[] zzb;
    /* access modifiers changed from: private */
    public final transient int zzc;

    public zzan(zzai zzai, Object[] objArr, int i3, int i4) {
        this.zza = zzai;
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

    public final zzas zzd() {
        return zzf().listIterator(0);
    }

    public final zzaf zzg() {
        return new zzam(this);
    }
}
