package com.google.android.gms.internal.p002firebaseauthapi;

import javax.annotation.CheckForNull;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbc  reason: invalid package */
final class zzbc<K> extends zzav<K> {
    private final transient zzau<K, ?> zza;
    private final transient zzaq<K> zzb;

    public zzbc(zzau<K, ?> zzau, zzaq<K> zzaq) {
        this.zza = zzau;
        this.zzb = zzaq;
    }

    public final boolean contains(@CheckForNull Object obj) {
        return this.zza.get(obj) != null;
    }

    public final int size() {
        return this.zza.size();
    }

    public final int zza(Object[] objArr, int i3) {
        return zzc().zza(objArr, i3);
    }

    public final zzaq<K> zzc() {
        return this.zzb;
    }

    /* renamed from: zzd */
    public final zzbd<K> iterator() {
        return (zzbd) zzc().iterator();
    }

    public final boolean zze() {
        return true;
    }
}
