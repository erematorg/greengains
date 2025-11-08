package com.google.android.gms.internal.p002firebaseauthapi;

import android.support.v4.media.session.a;
import java.util.Objects;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpe  reason: invalid package */
final class zzpe {
    private final Class<?> zza;
    private final Class<? extends zzox> zzb;

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzpe)) {
            return false;
        }
        zzpe zzpe = (zzpe) obj;
        return zzpe.zza.equals(this.zza) && zzpe.zzb.equals(this.zzb);
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.zza, this.zzb});
    }

    public final String toString() {
        return a.n(this.zza.getSimpleName(), " with serialization type: ", this.zzb.getSimpleName());
    }

    private zzpe(Class<?> cls, Class<? extends zzox> cls2) {
        this.zza = cls;
        this.zzb = cls2;
    }
}
