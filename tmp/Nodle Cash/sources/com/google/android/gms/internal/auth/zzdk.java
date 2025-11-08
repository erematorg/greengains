package com.google.android.gms.internal.auth;

import A.a;
import java.io.Serializable;
import javax.annotation.CheckForNull;

final class zzdk implements Serializable, zzdj {
    final zzdj zza;
    volatile transient boolean zzb;
    @CheckForNull
    transient Object zzc;

    public zzdk(zzdj zzdj) {
        this.zza = zzdj;
    }

    public final String toString() {
        return a.l("Suppliers.memoize(", (this.zzb ? a.l("<supplier that returned ", String.valueOf(this.zzc), ">") : this.zza).toString(), ")");
    }

    public final Object zza() {
        if (!this.zzb) {
            synchronized (this) {
                try {
                    if (!this.zzb) {
                        Object zza2 = this.zza.zza();
                        this.zzc = zza2;
                        this.zzb = true;
                        return zza2;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return this.zzc;
    }
}
