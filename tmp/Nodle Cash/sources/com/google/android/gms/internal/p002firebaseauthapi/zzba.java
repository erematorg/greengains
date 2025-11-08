package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Map;
import javax.annotation.CheckForNull;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzba  reason: invalid package */
final class zzba<K, V> extends zzav<Map.Entry<K, V>> {
    private final transient zzau<K, V> zza;
    /* access modifiers changed from: private */
    public final transient Object[] zzb;
    private final transient int zzc = 0;
    /* access modifiers changed from: private */
    public final transient int zzd;

    public zzba(zzau<K, V> zzau, Object[] objArr, int i3, int i4) {
        this.zza = zzau;
        this.zzb = objArr;
        this.zzd = i4;
    }

    public final boolean contains(@CheckForNull Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            return value != null && value.equals(this.zza.get(key));
        }
    }

    public final int size() {
        return this.zzd;
    }

    /* renamed from: zzd */
    public final zzbd<Map.Entry<K, V>> iterator() {
        return (zzbd) zzc().iterator();
    }

    public final boolean zze() {
        return true;
    }

    public final zzaq<Map.Entry<K, V>> zzg() {
        return new zzaz(this);
    }

    public final int zza(Object[] objArr, int i3) {
        return zzc().zza(objArr, i3);
    }
}
