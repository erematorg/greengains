package com.google.android.gms.internal.mlkit_common;

import java.util.Arrays;
import javax.annotation.CheckForNull;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.objectweb.asm.signature.SignatureVisitor;

public final class zzq {
    private final String zza;
    private final zzo zzb;
    private zzo zzc;

    public /* synthetic */ zzq(String str, zzp zzp) {
        zzo zzo = new zzo();
        this.zzb = zzo;
        this.zzc = zzo;
        str.getClass();
        this.zza = str;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(32);
        sb.append(this.zza);
        sb.append(AbstractJsonLexerKt.BEGIN_OBJ);
        zzo zzo = this.zzb.zzc;
        String str = "";
        while (zzo != null) {
            Object obj = zzo.zzb;
            sb.append(str);
            String str2 = zzo.zza;
            if (str2 != null) {
                sb.append(str2);
                sb.append(SignatureVisitor.INSTANCEOF);
            }
            if (obj == null || !obj.getClass().isArray()) {
                sb.append(obj);
            } else {
                String deepToString = Arrays.deepToString(new Object[]{obj});
                sb.append(deepToString, 1, deepToString.length() - 1);
            }
            zzo = zzo.zzc;
            str = ", ";
        }
        sb.append(AbstractJsonLexerKt.END_OBJ);
        return sb.toString();
    }

    public final zzq zza(String str, @CheckForNull Object obj) {
        zzo zzo = new zzo();
        this.zzc.zzc = zzo;
        this.zzc = zzo;
        zzo.zzb = obj;
        zzo.zza = str;
        return this;
    }

    public final zzq zzb(String str, boolean z2) {
        String valueOf = String.valueOf(z2);
        zzn zzn = new zzn((zzm) null);
        this.zzc.zzc = zzn;
        this.zzc = zzn;
        zzn.zzb = valueOf;
        zzn.zza = "isManifestFile";
        return this;
    }
}
