package com.google.android.gms.internal.p002firebaseauthapi;

import android.support.v4.media.session.a;
import androidx.emoji2.emojipicker.StickyVariantProvider;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzalt  reason: invalid package */
final class zzalt implements Comparable, Map.Entry {
    private final Comparable zza;
    private Object zzb;
    private final /* synthetic */ zzali zzc;

    public zzalt(zzali zzali, Map.Entry entry) {
        this(zzali, (Comparable) entry.getKey(), entry.getValue());
    }

    private static boolean zza(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    public final /* synthetic */ int compareTo(Object obj) {
        return ((Comparable) getKey()).compareTo((Comparable) ((zzalt) obj).getKey());
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return zza(this.zza, entry.getKey()) && zza(this.zzb, entry.getValue());
    }

    public final /* synthetic */ Object getKey() {
        return this.zza;
    }

    public final Object getValue() {
        return this.zzb;
    }

    public final int hashCode() {
        Comparable comparable = this.zza;
        int i3 = 0;
        int hashCode = comparable == null ? 0 : comparable.hashCode();
        Object obj = this.zzb;
        if (obj != null) {
            i3 = obj.hashCode();
        }
        return hashCode ^ i3;
    }

    public final Object setValue(Object obj) {
        this.zzc.zzg();
        Object obj2 = this.zzb;
        this.zzb = obj;
        return obj2;
    }

    public final String toString() {
        return a.n(String.valueOf(this.zza), StickyVariantProvider.KEY_VALUE_DELIMITER, String.valueOf(this.zzb));
    }

    public zzalt(zzali zzali, Comparable comparable, Object obj) {
        this.zzc = zzali;
        this.zza = comparable;
        this.zzb = obj;
    }
}
