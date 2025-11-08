package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.NoSuchElementException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzahr  reason: invalid package */
final class zzahr extends zzaht {
    private int zza = 0;
    private final int zzb;
    private final /* synthetic */ zzaho zzc;

    public zzahr(zzaho zzaho) {
        this.zzc = zzaho;
        this.zzb = zzaho.zzb();
    }

    public final boolean hasNext() {
        return this.zza < this.zzb;
    }

    public final byte zza() {
        int i3 = this.zza;
        if (i3 < this.zzb) {
            this.zza = i3 + 1;
            return this.zzc.zzb(i3);
        }
        throw new NoSuchElementException();
    }
}
