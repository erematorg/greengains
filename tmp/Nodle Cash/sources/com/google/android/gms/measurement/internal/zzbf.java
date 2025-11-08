package com.google.android.gms.measurement.internal;

import java.util.Iterator;

final class zzbf implements Iterator<String> {
    private Iterator<String> zza;
    private final /* synthetic */ zzbc zzb;

    public zzbf(zzbc zzbc) {
        this.zzb = zzbc;
        this.zza = zzbc.zza.keySet().iterator();
    }

    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    public final /* synthetic */ Object next() {
        return this.zza.next();
    }

    public final void remove() {
        throw new UnsupportedOperationException("Remove not supported");
    }
}
