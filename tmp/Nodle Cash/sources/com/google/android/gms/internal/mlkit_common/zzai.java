package com.google.android.gms.internal.mlkit_common;

import A.a;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.objectweb.asm.signature.SignatureVisitor;

public abstract class zzai implements Map, Serializable {
    @CheckForNull
    private transient zzaj zza;
    @CheckForNull
    private transient zzaj zzb;
    @CheckForNull
    private transient zzab zzc;

    public static zzai zzc(Object obj, Object obj2) {
        zzw.zza("optional-module-barcode", OptionalModuleUtils.BARCODE_MODULE_ID);
        return zzaq.zzg(1, new Object[]{"optional-module-barcode", OptionalModuleUtils.BARCODE_MODULE_ID}, (zzah) null);
    }

    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public final boolean containsKey(@CheckForNull Object obj) {
        return get(obj) != null;
    }

    public final boolean containsValue(@CheckForNull Object obj) {
        return values().contains(obj);
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        return entrySet().equals(((Map) obj).entrySet());
    }

    @CheckForNull
    public abstract Object get(@CheckForNull Object obj);

    @CheckForNull
    public final Object getOrDefault(@CheckForNull Object obj, @CheckForNull Object obj2) {
        Object obj3 = get(obj);
        return obj3 != null ? obj3 : obj2;
    }

    public final int hashCode() {
        return zzar.zza(entrySet());
    }

    public final boolean isEmpty() {
        return size() == 0;
    }

    public final /* bridge */ /* synthetic */ Set keySet() {
        zzaj zzaj = this.zzb;
        if (zzaj != null) {
            return zzaj;
        }
        zzaj zze = zze();
        this.zzb = zze;
        return zze;
    }

    @CheckForNull
    @Deprecated
    public final Object put(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void putAll(Map map) {
        throw new UnsupportedOperationException();
    }

    @CheckForNull
    @Deprecated
    public final Object remove(@CheckForNull Object obj) {
        throw new UnsupportedOperationException();
    }

    public final String toString() {
        int size = size();
        if (size >= 0) {
            StringBuilder sb = new StringBuilder((int) Math.min(((long) size) * 8, 1073741824));
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
        throw new IllegalArgumentException(a.k("size cannot be negative but was: ", size));
    }

    public abstract zzab zza();

    /* renamed from: zzb */
    public final zzab values() {
        zzab zzab = this.zzc;
        if (zzab != null) {
            return zzab;
        }
        zzab zza2 = zza();
        this.zzc = zza2;
        return zza2;
    }

    public abstract zzaj zzd();

    public abstract zzaj zze();

    /* renamed from: zzf */
    public final zzaj entrySet() {
        zzaj zzaj = this.zza;
        if (zzaj != null) {
            return zzaj;
        }
        zzaj zzd = zzd();
        this.zza = zzd;
        return zzd;
    }
}
