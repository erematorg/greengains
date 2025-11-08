package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Arrays;
import javax.annotation.CheckForNull;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public final class zzav {
    private final String zza;
    private final zzat zzb;
    private zzat zzc;

    public /* synthetic */ zzav(String str, zzau zzau) {
        zzat zzat = new zzat();
        this.zzb = zzat;
        this.zzc = zzat;
        str.getClass();
        this.zza = str;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(32);
        sb.append(this.zza);
        sb.append(AbstractJsonLexerKt.BEGIN_OBJ);
        zzat zzat = this.zzb.zzb;
        String str = "";
        while (zzat != null) {
            Object obj = zzat.zza;
            sb.append(str);
            if (obj == null || !obj.getClass().isArray()) {
                sb.append(obj);
            } else {
                String deepToString = Arrays.deepToString(new Object[]{obj});
                sb.append(deepToString, 1, deepToString.length() - 1);
            }
            zzat = zzat.zzb;
            str = ", ";
        }
        sb.append(AbstractJsonLexerKt.END_OBJ);
        return sb.toString();
    }

    public final zzav zza(@CheckForNull Object obj) {
        zzat zzat = new zzat();
        this.zzc.zzb = zzat;
        this.zzc = zzat;
        zzat.zza = obj;
        return this;
    }
}
