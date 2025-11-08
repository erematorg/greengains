package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzalk  reason: invalid package */
final class zzalk implements Iterator {
    private int zza;
    private Iterator zzb;
    private final /* synthetic */ zzali zzc;

    private final Iterator zza() {
        if (this.zzb == null) {
            this.zzb = this.zzc.zzf.entrySet().iterator();
        }
        return this.zzb;
    }

    public final boolean hasNext() {
        int i3 = this.zza;
        return (i3 > 0 && i3 <= this.zzc.zzb.size()) || zza().hasNext();
    }

    public final /* synthetic */ Object next() {
        if (zza().hasNext()) {
            return (Map.Entry) zza().next();
        }
        List zza2 = this.zzc.zzb;
        int i3 = this.zza - 1;
        this.zza = i3;
        return (Map.Entry) zza2.get(i3);
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    private zzalk(zzali zzali) {
        this.zzc = zzali;
        this.zza = zzali.zzb.size();
    }
}
