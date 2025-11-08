package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Iterator;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzamj  reason: invalid package */
final class zzamj implements Iterator<String> {
    private Iterator<String> zza;
    private final /* synthetic */ zzamh zzb;

    public zzamj(zzamh zzamh) {
        this.zzb = zzamh;
        this.zza = zzamh.zza.iterator();
    }

    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    public final /* synthetic */ Object next() {
        return this.zza.next();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
