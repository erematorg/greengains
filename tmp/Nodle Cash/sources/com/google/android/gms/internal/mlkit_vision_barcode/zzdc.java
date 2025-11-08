package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;

class zzdc extends zzdr {
    final Map zzb;

    public zzdc(Map map) {
        this.zzb = map;
    }

    public void clear() {
        this.zzb.clear();
    }

    public final boolean contains(@CheckForNull Object obj) {
        return this.zzb.containsKey(obj);
    }

    public final boolean isEmpty() {
        return this.zzb.isEmpty();
    }

    public Iterator iterator() {
        return new zzcz(this.zzb.entrySet().iterator());
    }

    public boolean remove(@CheckForNull Object obj) {
        if (!contains(obj)) {
            return false;
        }
        this.zzb.remove(obj);
        return true;
    }

    public final int size() {
        return this.zzb.size();
    }
}
