package com.google.android.recaptcha.internal;

import java.util.Iterator;

final class zzlp implements Iterator {
    final Iterator zza;
    final /* synthetic */ zzlq zzb;

    public zzlp(zzlq zzlq) {
        this.zzb = zzlq;
        this.zza = zzlq.zza.iterator();
    }

    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    public final /* bridge */ /* synthetic */ Object next() {
        return (String) this.zza.next();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
