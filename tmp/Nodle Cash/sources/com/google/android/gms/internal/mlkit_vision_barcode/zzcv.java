package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Set;
import javax.annotation.CheckForNull;

public abstract class zzcv extends zzcn implements Set {
    @CheckForNull
    private transient zzcs zza;

    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        return zzds.zzb(this, obj);
    }

    public final int hashCode() {
        return zzds.zza(this);
    }

    /* renamed from: zzd */
    public abstract zzdu iterator();

    public final zzcs zzf() {
        zzcs zzcs = this.zza;
        if (zzcs != null) {
            return zzcs;
        }
        zzcs zzg = zzg();
        this.zza = zzg;
        return zzg;
    }

    public zzcs zzg() {
        Object[] array = toArray();
        int i3 = zzcs.zzd;
        return zzcs.zzg(array, array.length);
    }
}
