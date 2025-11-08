package com.google.android.gms.internal.p002firebaseauthapi;

import A.a;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzl  reason: invalid package */
final class zzl extends zzm {
    private final char zza;

    public zzl(char c3) {
        this.zza = c3;
    }

    public final String toString() {
        char c3 = this.zza;
        char[] cArr = {AbstractJsonLexerKt.STRING_ESC, AbstractJsonLexerKt.UNICODE_ESC, 0, 0, 0, 0};
        for (int i3 = 0; i3 < 4; i3++) {
            cArr[5 - i3] = "0123456789ABCDEF".charAt(c3 & 15);
            c3 = (char) (c3 >> 4);
        }
        return a.l("CharMatcher.is('", String.copyValueOf(cArr), "')");
    }

    public final boolean zza(char c3) {
        return c3 == this.zza;
    }
}
