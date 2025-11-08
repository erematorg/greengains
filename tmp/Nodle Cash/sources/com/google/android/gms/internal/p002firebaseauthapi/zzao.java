package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzao  reason: invalid package */
class zzao<E> extends zzan<E> {
    Object[] zza = new Object[4];
    int zzb = 0;
    boolean zzc;

    public zzao(int i3) {
        zzaj.zza(4, "initialCapacity");
    }

    public zzao<E> zza(E e3) {
        zzz.zza(e3);
        int i3 = this.zzb + 1;
        Object[] objArr = this.zza;
        if (objArr.length < i3) {
            this.zza = Arrays.copyOf(objArr, zzan.zza(objArr.length, i3));
            this.zzc = false;
        } else if (this.zzc) {
            this.zza = (Object[]) objArr.clone();
            this.zzc = false;
        }
        Object[] objArr2 = this.zza;
        int i4 = this.zzb;
        this.zzb = i4 + 1;
        objArr2[i4] = e3;
        return this;
    }
}
