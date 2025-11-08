package com.google.common.base;

import android.support.v4.media.session.a;
import androidx.constraintlayout.core.state.b;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckForNull;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@GwtCompatible
@ElementTypesAreNonnullByDefault
public final class Strings {
    private Strings() {
    }

    public static String commonPrefix(CharSequence charSequence, CharSequence charSequence2) {
        Preconditions.checkNotNull(charSequence);
        Preconditions.checkNotNull(charSequence2);
        int min = Math.min(charSequence.length(), charSequence2.length());
        int i3 = 0;
        while (i3 < min && charSequence.charAt(i3) == charSequence2.charAt(i3)) {
            i3++;
        }
        int i4 = i3 - 1;
        if (validSurrogatePairAt(charSequence, i4) || validSurrogatePairAt(charSequence2, i4)) {
            i3--;
        }
        return charSequence.subSequence(0, i3).toString();
    }

    public static String commonSuffix(CharSequence charSequence, CharSequence charSequence2) {
        Preconditions.checkNotNull(charSequence);
        Preconditions.checkNotNull(charSequence2);
        int min = Math.min(charSequence.length(), charSequence2.length());
        int i3 = 0;
        while (i3 < min && charSequence.charAt((charSequence.length() - i3) - 1) == charSequence2.charAt((charSequence2.length() - i3) - 1)) {
            i3++;
        }
        if (validSurrogatePairAt(charSequence, (charSequence.length() - i3) - 1) || validSurrogatePairAt(charSequence2, (charSequence2.length() - i3) - 1)) {
            i3--;
        }
        return charSequence.subSequence(charSequence.length() - i3, charSequence.length()).toString();
    }

    @CheckForNull
    public static String emptyToNull(@CheckForNull String str) {
        return Platform.emptyToNull(str);
    }

    public static boolean isNullOrEmpty(@CheckForNull String str) {
        return Platform.stringIsNullOrEmpty(str);
    }

    public static String lenientFormat(@CheckForNull String str, @CheckForNull Object... objArr) {
        int indexOf;
        String valueOf = String.valueOf(str);
        int i3 = 0;
        if (objArr == null) {
            objArr = new Object[]{"(Object[])null"};
        } else {
            for (int i4 = 0; i4 < objArr.length; i4++) {
                objArr[i4] = lenientToString(objArr[i4]);
            }
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

    private static String lenientToString(@CheckForNull Object obj) {
        if (obj == null) {
            return AbstractJsonLexerKt.NULL;
        }
        try {
            return obj.toString();
        } catch (Exception e3) {
            String str = obj.getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(obj));
            Logger.getLogger("com.google.common.base.Strings").log(Level.WARNING, "Exception during lenientFormat for " + str, e3);
            StringBuilder w2 = a.w("<", str, " threw ");
            w2.append(e3.getClass().getName());
            w2.append(">");
            return w2.toString();
        }
    }

    public static String nullToEmpty(@CheckForNull String str) {
        return Platform.nullToEmpty(str);
    }

    public static String padEnd(String str, int i3, char c3) {
        Preconditions.checkNotNull(str);
        if (str.length() >= i3) {
            return str;
        }
        StringBuilder o3 = b.o(i3, str);
        for (int length = str.length(); length < i3; length++) {
            o3.append(c3);
        }
        return o3.toString();
    }

    public static String padStart(String str, int i3, char c3) {
        Preconditions.checkNotNull(str);
        if (str.length() >= i3) {
            return str;
        }
        StringBuilder sb = new StringBuilder(i3);
        for (int length = str.length(); length < i3; length++) {
            sb.append(c3);
        }
        sb.append(str);
        return sb.toString();
    }

    public static String repeat(String str, int i3) {
        Preconditions.checkNotNull(str);
        boolean z2 = false;
        if (i3 <= 1) {
            if (i3 >= 0) {
                z2 = true;
            }
            Preconditions.checkArgument(z2, "invalid count: %s", i3);
            return i3 == 0 ? "" : str;
        }
        int length = str.length();
        long j2 = ((long) length) * ((long) i3);
        int i4 = (int) j2;
        if (((long) i4) == j2) {
            char[] cArr = new char[i4];
            str.getChars(0, length, cArr, 0);
            while (true) {
                int i5 = i4 - length;
                if (length < i5) {
                    System.arraycopy(cArr, 0, cArr, length, length);
                    length <<= 1;
                } else {
                    System.arraycopy(cArr, 0, cArr, length, i5);
                    return new String(cArr);
                }
            }
        } else {
            throw new ArrayIndexOutOfBoundsException(androidx.compose.animation.core.a.s("Required array size too large: ", j2));
        }
    }

    @VisibleForTesting
    public static boolean validSurrogatePairAt(CharSequence charSequence, int i3) {
        return i3 >= 0 && i3 <= charSequence.length() + -2 && Character.isHighSurrogate(charSequence.charAt(i3)) && Character.isLowSurrogate(charSequence.charAt(i3 + 1));
    }
}
