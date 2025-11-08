package com.google.android.gms.internal.mlkit_common;

import java.util.Set;
import javax.annotation.CheckForNull;

public abstract class zzaj extends zzab implements Set {
    @CheckForNull
    private transient zzaf zza;

    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this || obj == this) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            try {
                if (size() == set.size() && containsAll(set)) {
                    return true;
                }
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    public final int hashCode() {
        return zzar.zza(this);
    }

    /* renamed from: zzd */
    public abstract zzas iterator();

    public final zzaf zzf() {
        zzaf zzaf = this.zza;
        if (zzaf != null) {
            return zzaf;
        }
        zzaf zzg = zzg();
        this.zza = zzg;
        return zzg;
    }

    public zzaf zzg() {
        Object[] array = toArray();
        int i3 = zzaf.zzd;
        return zzaf.zzg(array, array.length);
    }
}
