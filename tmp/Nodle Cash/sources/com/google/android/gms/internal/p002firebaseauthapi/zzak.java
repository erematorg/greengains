package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.NoSuchElementException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzak  reason: invalid package */
abstract class zzak<E> extends zzbg<E> {
    private final int zza;
    private int zzb;

    public zzak(int i3, int i4) {
        zzz.zzb(i4, i3);
        this.zza = i3;
        this.zzb = i4;
    }

    public final boolean hasNext() {
        return this.zzb < this.zza;
    }

    public final boolean hasPrevious() {
        return this.zzb > 0;
    }

    public final E next() {
        if (hasNext()) {
            int i3 = this.zzb;
            this.zzb = i3 + 1;
            return zza(i3);
        }
        throw new NoSuchElementException();
    }

    public final int nextIndex() {
        return this.zzb;
    }

    public final E previous() {
        if (hasPrevious()) {
            int i3 = this.zzb - 1;
            this.zzb = i3;
            return zza(i3);
        }
        throw new NoSuchElementException();
    }

    public final int previousIndex() {
        return this.zzb - 1;
    }

    public abstract E zza(int i3);
}
