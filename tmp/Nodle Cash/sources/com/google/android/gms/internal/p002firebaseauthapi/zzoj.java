package com.google.android.gms.internal.p002firebaseauthapi;

import android.support.v4.media.session.a;
import java.util.Objects;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzoj  reason: invalid package */
final class zzoj {
    private final Class<?> zza;
    private final Class<?> zzb;

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzoj)) {
            return false;
        }
        zzoj zzoj = (zzoj) obj;
        return zzoj.zza.equals(this.zza) && zzoj.zzb.equals(this.zzb);
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.zza, this.zzb});
    }

    public final String toString() {
        return a.n(this.zza.getSimpleName(), " with primitive type: ", this.zzb.getSimpleName());
    }

    private zzoj(Class<?> cls, Class<?> cls2) {
        this.zza = cls;
        this.zzb = cls2;
    }
}
