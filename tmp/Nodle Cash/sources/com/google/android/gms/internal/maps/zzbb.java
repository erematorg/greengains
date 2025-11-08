package com.google.android.gms.internal.maps;

import android.support.v4.media.session.a;
import androidx.camera.camera2.internal.C0118y;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckForNull;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public final class zzbb {
    public static String zza(@CheckForNull String str, @CheckForNull Object... objArr) {
        int length;
        int length2;
        int indexOf;
        String str2;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            length = objArr.length;
            if (i4 >= length) {
                break;
            }
            Object obj = objArr[i4];
            if (obj == null) {
                str2 = AbstractJsonLexerKt.NULL;
            } else {
                try {
                    str2 = obj.toString();
                } catch (Exception e3) {
                    String n2 = a.n(obj.getClass().getName(), "@", Integer.toHexString(System.identityHashCode(obj)));
                    Logger.getLogger("com.google.common.base.Strings").logp(Level.WARNING, "com.google.common.base.Strings", "lenientToString", "Exception during lenientFormat for ".concat(n2), e3);
                    str2 = C0118y.g("<", n2, " threw ", e3.getClass().getName(), ">");
                }
            }
            objArr[i4] = str2;
            i4++;
        }
        StringBuilder sb = new StringBuilder(str.length() + (length * 16));
        int i5 = 0;
        while (true) {
            length2 = objArr.length;
            if (i3 >= length2 || (indexOf = str.indexOf("%s", i5)) == -1) {
                sb.append(str, i5, str.length());
            } else {
                sb.append(str, i5, indexOf);
                sb.append(objArr[i3]);
                i5 = indexOf + 2;
                i3++;
            }
        }
        sb.append(str, i5, str.length());
        if (i3 < length2) {
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
}
