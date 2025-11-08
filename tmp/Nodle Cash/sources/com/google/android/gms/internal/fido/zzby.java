package com.google.android.gms.internal.fido;

import A.a;
import java.util.Iterator;
import javax.annotation.CheckForNull;

final class zzby extends zzbc {
    final transient Object zza;

    public zzby(Object obj) {
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
        return new zzbl(this.zza);
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

    public final zzcb zzd() {
        return new zzbl(this.zza);
    }

    public final zzaz zzi() {
        return zzaz.zzj(this.zza);
    }
}
