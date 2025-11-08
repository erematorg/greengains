package com.google.android.gms.internal.common;

import A.a;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

final class zzl extends zzk {
    private final char zza;

    public zzl(char c3) {
        this.zza = c3;
    }

    public final String toString() {
        char[] cArr = {AbstractJsonLexerKt.STRING_ESC, AbstractJsonLexerKt.UNICODE_ESC, 0, 0, 0, 0};
        int i3 = this.zza;
        for (int i4 = 0; i4 < 4; i4++) {
            cArr[5 - i4] = "0123456789ABCDEF".charAt(i3 & 15);
            i3 >>= 4;
        }
        return a.l("CharMatcher.is('", String.copyValueOf(cArr), "')");
    }

    public final boolean zza(char c3) {
        return c3 == this.zza;
    }
}
