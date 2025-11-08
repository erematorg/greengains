package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Iterator;
import java.util.NoSuchElementException;
import javax.annotation.CheckForNull;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzi  reason: invalid package */
abstract class zzi<T> implements Iterator<T> {
    private int zza = zzh.zzb;
    @CheckForNull
    private T zzb;

    public final boolean hasNext() {
        int i3 = this.zza;
        int i4 = zzh.zzd;
        if (i3 != i4) {
            int i5 = i3 - 1;
            if (i5 == 0) {
                return true;
            }
            if (i5 != 2) {
                this.zza = i4;
                this.zzb = zza();
                if (this.zza != zzh.zzc) {
                    this.zza = zzh.zza;
                    return true;
                }
            }
            return false;
        }
        throw new IllegalStateException();
    }

    public final T next() {
        if (hasNext()) {
            this.zza = zzh.zzb;
            T t2 = this.zzb;
            this.zzb = null;
            return t2;
        }
        throw new NoSuchElementException();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    @CheckForNull
    public abstract T zza();

    @CheckForNull
    public final T zzb() {
        this.zza = zzh.zzc;
        return null;
    }
}
