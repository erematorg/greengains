package com.google.android.recaptcha.internal;

import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public abstract class zzfm implements Iterable {
    private final zzfe zza = zzfe.zza();

    public final String toString() {
        StringBuilder sb = new StringBuilder("[");
        boolean z2 = true;
        for (Object append : this) {
            if (!z2) {
                sb.append(", ");
            }
            sb.append(append);
            z2 = false;
        }
        sb.append(AbstractJsonLexerKt.END_LIST);
        return sb.toString();
    }
}
