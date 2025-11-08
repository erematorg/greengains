package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.AbstractMap;
import java.util.Objects;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaz  reason: invalid package */
final class zzaz extends zzaq {
    private final /* synthetic */ zzba zza;

    public zzaz(zzba zzba) {
        this.zza = zzba;
    }

    public final /* synthetic */ Object get(int i3) {
        zzz.zza(i3, this.zza.zzd);
        int i4 = i3 * 2;
        Object obj = this.zza.zzb[i4];
        Objects.requireNonNull(obj);
        Object obj2 = this.zza.zzb[i4 + 1];
        Objects.requireNonNull(obj2);
        return new AbstractMap.SimpleImmutableEntry(obj, obj2);
    }

    public final int size() {
        return this.zza.zzd;
    }

    public final boolean zze() {
        return true;
    }
}
