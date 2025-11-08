package com.google.android.gms.internal.maps;

import A.a;
import java.util.Iterator;
import javax.annotation.CheckForNull;

final class zzbw extends zzbm {
    final transient Object zza;

    public zzbw(Object obj) {
        obj.getClass();
        this.zza = obj;
    }

    public final boolean contains(@CheckForNull Object obj) {
        return this.zza.equals(obj);
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final /* synthetic */ Iterator iterator() {
        return new zzbn(this.zza);
    }

    public final int size() {
        return 1;
    }

    public final String toString() {
        return a.l("[", this.zza.toString(), "]");
    }

    public final int zza(Object[] objArr, int i3) {
        objArr[0] = this.zza;
        return 1;
    }

    public final zzbx zzd() {
        return new zzbn(this.zza);
    }
}
