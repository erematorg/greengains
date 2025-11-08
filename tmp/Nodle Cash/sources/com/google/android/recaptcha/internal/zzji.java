package com.google.android.recaptcha.internal;

import java.util.Iterator;
import java.util.Map;

final class zzji implements Iterator {
    private final Iterator zza;

    public zzji(Iterator it) {
        this.zza = it;
    }

    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    public final /* bridge */ /* synthetic */ Object next() {
        Map.Entry entry = (Map.Entry) this.zza.next();
        return entry.getValue() instanceof zzjj ? new zzjh(entry, (zzjg) null) : entry;
    }

    public final void remove() {
        this.zza.remove();
    }
}
