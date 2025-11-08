package com.google.android.recaptcha.internal;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Queue;

public final class zzfl extends zzfp implements Serializable {
    final int zza;
    private final Queue zzb;

    private zzfl(int i3) {
        if (i3 >= 0) {
            this.zzb = new ArrayDeque(i3);
            this.zza = i3;
            return;
        }
        throw new IllegalArgumentException(zzfi.zza("maxSize (%s) must >= 0", Integer.valueOf(i3)));
    }

    public static zzfl zza(int i3) {
        return new zzfl(i3);
    }

    public final boolean add(Object obj) {
        obj.getClass();
        if (this.zza == 0) {
            return true;
        }
        if (size() == this.zza) {
            this.zzb.remove();
        }
        this.zzb.add(obj);
        return true;
    }

    public final boolean addAll(Collection collection) {
        int size = collection.size();
        if (size < this.zza) {
            return zzfs.zza(this, collection.iterator());
        }
        clear();
        int i3 = size - this.zza;
        zzff.zzb(i3 >= 0, "number to skip cannot be negative");
        return zzfs.zza(this, new zzfr(collection, i3).iterator());
    }

    public final boolean offer(Object obj) {
        add(obj);
        return true;
    }

    public final /* synthetic */ Object zzb() {
        return this.zzb;
    }

    public final /* synthetic */ Collection zzc() {
        return this.zzb;
    }

    public final Queue zzd() {
        return this.zzb;
    }
}
