package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Objects;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzay  reason: invalid package */
final class zzay<E> extends zzaq<E> {
    static final zzaq<Object> zza = new zzay(new Object[0], 0);
    private final transient Object[] zzb;
    private final transient int zzc;

    public zzay(Object[] objArr, int i3) {
        this.zzb = objArr;
        this.zzc = i3;
    }

    public final E get(int i3) {
        zzz.zza(i3, this.zzc);
        E e3 = this.zzb[i3];
        Objects.requireNonNull(e3);
        return e3;
    }

    public final int size() {
        return this.zzc;
    }

    public final int zza(Object[] objArr, int i3) {
        System.arraycopy(this.zzb, 0, objArr, i3, this.zzc);
        return i3 + this.zzc;
    }

    public final int zzb() {
        return 0;
    }

    public final boolean zze() {
        return false;
    }

    public final Object[] zzf() {
        return this.zzb;
    }

    public final int zza() {
        return this.zzc;
    }
}
