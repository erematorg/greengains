package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;

final class zzdm extends zzcv {
    private final transient zzcu zza;
    /* access modifiers changed from: private */
    public final transient Object[] zzb;
    /* access modifiers changed from: private */
    public final transient int zzc = 1;

    public zzdm(zzcu zzcu, Object[] objArr, int i3, int i4) {
        this.zza = zzcu;
        this.zzb = objArr;
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
        return zzf().zza(objArr, i3);
    }

    public final zzdu zzd() {
        return zzf().listIterator(0);
    }

    public final zzcs zzg() {
        return new zzdl(this);
    }
}
