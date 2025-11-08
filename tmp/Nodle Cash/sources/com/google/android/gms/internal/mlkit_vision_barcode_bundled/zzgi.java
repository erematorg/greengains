package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import android.support.v4.media.session.a;
import androidx.emoji2.emojipicker.StickyVariantProvider;
import java.util.Map;

final class zzgi implements Map.Entry, Comparable {
    final /* synthetic */ zzgo zza;
    private final Comparable zzb;
    private Object zzc;

    public zzgi(zzgo zzgo, Comparable comparable, Object obj) {
        this.zza = zzgo;
        this.zzb = comparable;
        this.zzc = obj;
    }

    private static final boolean zzb(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return this.zzb.compareTo(((zzgi) obj).zzb);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return zzb(this.zzb, entry.getKey()) && zzb(this.zzc, entry.getValue());
    }

    public final /* synthetic */ Object getKey() {
        return this.zzb;
    }

    public final Object getValue() {
        return this.zzc;
    }

    public final int hashCode() {
        Comparable comparable = this.zzb;
        int i3 = 0;
        int hashCode = comparable == null ? 0 : comparable.hashCode();
        Object obj = this.zzc;
        if (obj != null) {
            i3 = obj.hashCode();
        }
        return hashCode ^ i3;
    }

    public final Object setValue(Object obj) {
        this.zza.zzo();
        Object obj2 = this.zzc;
        this.zzc = obj;
        return obj2;
    }

    public final String toString() {
        return a.n(String.valueOf(this.zzb), StickyVariantProvider.KEY_VALUE_DELIMITER, String.valueOf(this.zzc));
    }

    public final Comparable zza() {
        return this.zzb;
    }
}
