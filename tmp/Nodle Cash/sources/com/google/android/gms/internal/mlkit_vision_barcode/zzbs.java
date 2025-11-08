package com.google.android.gms.internal.mlkit_vision_barcode;

import android.support.v4.media.session.a;
import androidx.emoji2.emojipicker.StickyVariantProvider;
import java.util.Map;
import javax.annotation.CheckForNull;

abstract class zzbs implements Map.Entry {
    public final boolean equals(@CheckForNull Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            return zzax.zza(getKey(), entry.getKey()) && zzax.zza(getValue(), entry.getValue());
        }
    }

    public abstract Object getKey();

    public abstract Object getValue();

    public final int hashCode() {
        Object key = getKey();
        Object value = getValue();
        int i3 = 0;
        int hashCode = key == null ? 0 : key.hashCode();
        if (value != null) {
            i3 = value.hashCode();
        }
        return hashCode ^ i3;
    }

    public Object setValue(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final String toString() {
        return a.n(String.valueOf(getKey()), StickyVariantProvider.KEY_VALUE_DELIMITER, String.valueOf(getValue()));
    }
}
