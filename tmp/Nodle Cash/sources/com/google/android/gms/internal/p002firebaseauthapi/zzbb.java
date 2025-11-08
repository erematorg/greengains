package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Objects;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbb  reason: invalid package */
final class zzbb extends zzaq<Object> {
    private final transient Object[] zza;
    private final transient int zzb;
    private final transient int zzc;

    public zzbb(Object[] objArr, int i3, int i4) {
        this.zza = objArr;
        this.zzb = i3;
        this.zzc = i4;
    }

    public final Object get(int i3) {
        zzz.zza(i3, this.zzc);
        Object obj = this.zza[(i3 * 2) + this.zzb];
        Objects.requireNonNull(obj);
        return obj;
    }

    public final int size() {
        return this.zzc;
    }

    public final boolean zze() {
        return true;
    }
}
