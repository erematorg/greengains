package com.google.android.gms.internal.location;

import androidx.camera.camera2.internal.C0118y;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckForNull;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public final class zzes {
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
                    String name = obj.getClass().getName();
                    String hexString = Integer.toHexString(System.identityHashCode(obj));
                    String j2 = C0118y.j(new StringBuilder(name.length() + 1 + String.valueOf(hexString).length()), name, "@", hexString);
                    Logger.getLogger("com.google.common.base.Strings").logp(Level.WARNING, "com.google.common.base.Strings", "lenientToString", "Exception during lenientFormat for ".concat(j2), e3);
                    String name2 = e3.getClass().getName();
                    StringBuilder sb = new StringBuilder(name2.length() + j2.length() + 8 + 1);
                    sb.append("<");
                    sb.append(j2);
                    sb.append(" threw ");
                    sb.append(name2);
                    sb.append(">");
                    str2 = sb.toString();
                }
            }
            objArr[i4] = str2;
            i4++;
        }
        StringBuilder sb2 = new StringBuilder(str.length() + (length * 16));
        int i5 = 0;
        while (true) {
            length2 = objArr.length;
            if (i3 >= length2 || (indexOf = str.indexOf("%s", i5)) == -1) {
                sb2.append(str, i5, str.length());
            } else {
                sb2.append(str, i5, indexOf);
                sb2.append(objArr[i3]);
                i5 = indexOf + 2;
                i3++;
            }
        }
        sb2.append(str, i5, str.length());
        if (i3 < length2) {
            sb2.append(" [");
            sb2.append(objArr[i3]);
            for (int i6 = i3 + 1; i6 < objArr.length; i6++) {
                sb2.append(", ");
                sb2.append(objArr[i6]);
            }
            sb2.append(AbstractJsonLexerKt.END_LIST);
        }
        return sb2.toString();
    }
}
