package com.google.android.gms.internal.mlkit_vision_common;

import java.util.Iterator;
import javax.annotation.CheckForNull;

final class zzx extends zzs {
    private final transient zzr zza;
    private final transient zzp zzb;

    public zzx(zzr zzr, zzp zzp) {
        this.zza = zzr;
        this.zzb = zzp;
    }

    public final boolean contains(@CheckForNull Object obj) {
        return this.zza.get(obj) != null;
    }

    public final /* synthetic */ Iterator iterator() {
        return this.zzb.listIterator(0);
    }

    public final int size() {
        return this.zza.size();
    }

    public final int zza(Object[] objArr, int i3) {
        return this.zzb.zza(objArr, 0);
    }

    public final zzab zzd() {
        return this.zzb.listIterator(0);
    }
}
