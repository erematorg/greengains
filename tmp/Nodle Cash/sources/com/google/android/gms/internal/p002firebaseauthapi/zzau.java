package com.google.android.gms.internal.p002firebaseauthapi;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import javax.annotation.CheckForNull;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.objectweb.asm.signature.SignatureVisitor;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzau  reason: invalid package */
public abstract class zzau<K, V> implements Serializable, Map<K, V> {
    private static final Map.Entry<?, ?>[] zza = new Map.Entry[0];
    @CheckForNull
    private transient zzav<Map.Entry<K, V>> zzb;
    @CheckForNull
    private transient zzav<K> zzc;
    @CheckForNull
    private transient zzal<V> zzd;

    public static <K, V> zzau<K, V> zza(Map<? extends K, ? extends V> map) {
        if (!(map instanceof zzau) || (map instanceof SortedMap)) {
            Set<Map.Entry<? extends K, ? extends V>> entrySet = map.entrySet();
            zzat zzat = new zzat(entrySet instanceof Collection ? entrySet.size() : 4);
            zzat.zza(entrySet);
            return zzat.zza();
        }
        zzau<K, V> zzau = (zzau) map;
        zzau.zzd();
        return zzau;
    }

    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public boolean containsKey(@CheckForNull Object obj) {
        return get(obj) != null;
    }

    public boolean containsValue(@CheckForNull Object obj) {
        return ((zzal) values()).contains(obj);
    }

    public /* synthetic */ Set entrySet() {
        zzav<Map.Entry<K, V>> zzav = this.zzb;
        if (zzav != null) {
            return zzav;
        }
        zzav<Map.Entry<K, V>> zzb2 = zzb();
        this.zzb = zzb2;
        return zzb2;
    }

    public boolean equals(@CheckForNull Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Map) {
            return entrySet().equals(((Map) obj).entrySet());
        }
        return false;
    }

    @CheckForNull
    public abstract V get(@CheckForNull Object obj);

    @CheckForNull
    public final V getOrDefault(@CheckForNull Object obj, @CheckForNull V v2) {
        V v3 = get(obj);
        return v3 != null ? v3 : v2;
    }

    public int hashCode() {
        return zzbe.zza((zzav) entrySet());
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public /* synthetic */ Set keySet() {
        zzav<K> zzav = this.zzc;
        if (zzav != null) {
            return zzav;
        }
        zzav<K> zzc2 = zzc();
        this.zzc = zzc2;
        return zzc2;
    }

    @CheckForNull
    @Deprecated
    public final V put(K k2, V v2) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException();
    }

    @CheckForNull
    @Deprecated
    public final V remove(@CheckForNull Object obj) {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        int size = size();
        zzaj.zza(size, "size");
        StringBuilder sb = new StringBuilder((int) Math.min(((long) size) << 3, 1073741824));
        sb.append(AbstractJsonLexerKt.BEGIN_OBJ);
        boolean z2 = true;
        for (Map.Entry entry : entrySet()) {
            if (!z2) {
                sb.append(", ");
            }
            sb.append(entry.getKey());
            sb.append(SignatureVisitor.INSTANCEOF);
            sb.append(entry.getValue());
            z2 = false;
        }
        sb.append(AbstractJsonLexerKt.END_OBJ);
        return sb.toString();
    }

    public /* synthetic */ Collection values() {
        zzal<V> zzal = this.zzd;
        if (zzal != null) {
            return zzal;
        }
        zzal<V> zza2 = zza();
        this.zzd = zza2;
        return zza2;
    }

    public abstract zzal<V> zza();

    public abstract zzav<Map.Entry<K, V>> zzb();

    public abstract zzav<K> zzc();

    public abstract boolean zzd();
}
