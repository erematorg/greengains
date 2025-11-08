package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;

final class zzlq<K> implements Iterator<Map.Entry<K, Object>> {
    private Iterator<Map.Entry<K, Object>> zza;

    public zzlq(Iterator<Map.Entry<K, Object>> it) {
        this.zza = it;
    }

    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    public final /* synthetic */ Object next() {
        Map.Entry next = this.zza.next();
        return next.getValue() instanceof zzlp ? new zzlo(next) : next;
    }

    public final void remove() {
        this.zza.remove();
    }
}
