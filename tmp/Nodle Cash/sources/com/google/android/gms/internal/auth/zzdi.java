package com.google.android.gms.internal.auth;

import A.a;
import javax.annotation.CheckForNull;

final class zzdi extends zzdh {
    private final Object zza;

    public zzdi(Object obj) {
        this.zza = obj;
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj instanceof zzdi) {
            return this.zza.equals(((zzdi) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode() + 1502476572;
    }

    public final String toString() {
        return a.l("Optional.of(", this.zza.toString(), ")");
    }

    public final Object zza() {
        return this.zza;
    }

    public final boolean zzb() {
        return true;
    }
}
