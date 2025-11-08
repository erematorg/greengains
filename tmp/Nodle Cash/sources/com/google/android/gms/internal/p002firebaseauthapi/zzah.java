package com.google.android.gms.internal.p002firebaseauthapi;

import android.support.v4.media.session.a;
import androidx.browser.trusted.c;
import androidx.camera.camera2.internal.C0118y;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckForNull;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzah  reason: invalid package */
public final class zzah {
    @CheckForNull
    public static String zza(@CheckForNull String str) {
        return zzy.zzb(str);
    }

    public static String zzb(@CheckForNull String str) {
        return zzy.zzc(str);
    }

    public static boolean zzc(@CheckForNull String str) {
        return zzy.zzd(str);
    }

    public static String zza(@CheckForNull String str, @CheckForNull Object... objArr) {
        int indexOf;
        String valueOf = String.valueOf(str);
        int i3 = 0;
        for (int i4 = 0; i4 < objArr.length; i4++) {
            objArr[i4] = zza(objArr[i4]);
        }
        StringBuilder sb = new StringBuilder((objArr.length * 16) + valueOf.length());
        int i5 = 0;
        while (i3 < objArr.length && (indexOf = valueOf.indexOf("%s", i5)) != -1) {
            sb.append(valueOf, i5, indexOf);
            sb.append(objArr[i3]);
            i5 = indexOf + 2;
            i3++;
        }
        sb.append(valueOf, i5, valueOf.length());
        if (i3 < objArr.length) {
            sb.append(" [");
            sb.append(objArr[i3]);
            for (int i6 = i3 + 1; i6 < objArr.length; i6++) {
                sb.append(", ");
                sb.append(objArr[i6]);
            }
            sb.append(AbstractJsonLexerKt.END_LIST);
        }
        return sb.toString();
    }

    private static String zza(@CheckForNull Object obj) {
        if (obj == null) {
            return AbstractJsonLexerKt.NULL;
        }
        try {
            return obj.toString();
        } catch (Exception e3) {
            String n2 = a.n(obj.getClass().getName(), "@", Integer.toHexString(System.identityHashCode(obj)));
            Logger.getLogger("com.google.common.base.Strings").logp(Level.WARNING, "com.google.common.base.Strings", "lenientToString", c.a("Exception during lenientFormat for ", n2), e3);
            return C0118y.g("<", n2, " threw ", e3.getClass().getName(), ">");
        }
    }
}
