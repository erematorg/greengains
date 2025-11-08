package com.google.common.escape;

import androidx.compose.ui.autofill.a;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import javax.annotation.CheckForNull;

@GwtCompatible
@ElementTypesAreNonnullByDefault
public abstract class CharEscaper extends Escaper {
    private static final int DEST_PAD_MULTIPLIER = 2;

    private static char[] growBuffer(char[] cArr, int i3, int i4) {
        if (i4 >= 0) {
            char[] cArr2 = new char[i4];
            if (i3 > 0) {
                System.arraycopy(cArr, 0, cArr2, 0, i3);
            }
            return cArr2;
        }
        throw new AssertionError("Cannot increase internal buffer any further");
    }

    public String escape(String str) {
        Preconditions.checkNotNull(str);
        int length = str.length();
        for (int i3 = 0; i3 < length; i3++) {
            if (escape(str.charAt(i3)) != null) {
                return escapeSlow(str, i3);
            }
        }
        return str;
    }

    @CheckForNull
    public abstract char[] escape(char c3);

    public final String escapeSlow(String str, int i3) {
        int length = str.length();
        char[] charBufferFromThreadLocal = Platform.charBufferFromThreadLocal();
        int length2 = charBufferFromThreadLocal.length;
        int i4 = 0;
        int i5 = 0;
        while (i3 < length) {
            char[] escape = escape(str.charAt(i3));
            if (escape != null) {
                int length3 = escape.length;
                int i6 = i3 - i4;
                int i7 = i5 + i6;
                int i8 = i7 + length3;
                if (length2 < i8) {
                    length2 = a.c(length, i3, 2, i8);
                    charBufferFromThreadLocal = growBuffer(charBufferFromThreadLocal, i5, length2);
                }
                if (i6 > 0) {
                    str.getChars(i4, i3, charBufferFromThreadLocal, i5);
                    i5 = i7;
                }
                if (length3 > 0) {
                    System.arraycopy(escape, 0, charBufferFromThreadLocal, i5, length3);
                    i5 += length3;
                }
                i4 = i3 + 1;
            }
            i3++;
        }
        int i9 = length - i4;
        if (i9 > 0) {
            int i10 = i9 + i5;
            if (length2 < i10) {
                charBufferFromThreadLocal = growBuffer(charBufferFromThreadLocal, i5, i10);
            }
            str.getChars(i4, length, charBufferFromThreadLocal, i5);
            i5 = i10;
        }
        return new String(charBufferFromThreadLocal, 0, i5);
    }
}
