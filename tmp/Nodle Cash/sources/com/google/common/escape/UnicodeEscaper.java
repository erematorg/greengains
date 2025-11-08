package com.google.common.escape;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import javax.annotation.CheckForNull;

@GwtCompatible
@ElementTypesAreNonnullByDefault
public abstract class UnicodeEscaper extends Escaper {
    private static final int DEST_PAD = 32;

    public static int codePointAt(CharSequence charSequence, int i3, int i4) {
        Preconditions.checkNotNull(charSequence);
        if (i3 < i4) {
            int i5 = i3 + 1;
            char charAt = charSequence.charAt(i3);
            if (charAt < 55296 || charAt > 57343) {
                return charAt;
            }
            if (charAt > 56319) {
                throw new IllegalArgumentException("Unexpected low surrogate character '" + charAt + "' with value " + charAt + " at index " + i3 + " in '" + charSequence + "'");
            } else if (i5 == i4) {
                return -charAt;
            } else {
                char charAt2 = charSequence.charAt(i5);
                if (Character.isLowSurrogate(charAt2)) {
                    return Character.toCodePoint(charAt, charAt2);
                }
                throw new IllegalArgumentException("Expected low surrogate but got char '" + charAt2 + "' with value " + charAt2 + " at index " + i5 + " in '" + charSequence + "'");
            }
        } else {
            throw new IndexOutOfBoundsException("Index exceeds specified range");
        }
    }

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
        int nextEscapeIndex = nextEscapeIndex(str, 0, length);
        return nextEscapeIndex == length ? str : escapeSlow(str, nextEscapeIndex);
    }

    @CheckForNull
    public abstract char[] escape(int i3);

    public final String escapeSlow(String str, int i3) {
        int length = str.length();
        char[] charBufferFromThreadLocal = Platform.charBufferFromThreadLocal();
        int i4 = 0;
        int i5 = 0;
        while (i3 < length) {
            int codePointAt = codePointAt(str, i3, length);
            if (codePointAt >= 0) {
                char[] escape = escape(codePointAt);
                int i6 = (Character.isSupplementaryCodePoint(codePointAt) ? 2 : 1) + i3;
                if (escape != null) {
                    int i7 = i3 - i4;
                    int i8 = i5 + i7;
                    int length2 = escape.length + i8;
                    if (charBufferFromThreadLocal.length < length2) {
                        charBufferFromThreadLocal = growBuffer(charBufferFromThreadLocal, i5, (length - i3) + length2 + 32);
                    }
                    if (i7 > 0) {
                        str.getChars(i4, i3, charBufferFromThreadLocal, i5);
                        i5 = i8;
                    }
                    if (escape.length > 0) {
                        System.arraycopy(escape, 0, charBufferFromThreadLocal, i5, escape.length);
                        i5 += escape.length;
                    }
                    i4 = i6;
                }
                i3 = nextEscapeIndex(str, i6, length);
            } else {
                throw new IllegalArgumentException("Trailing high surrogate at end of input");
            }
        }
        int i9 = length - i4;
        if (i9 > 0) {
            int i10 = i9 + i5;
            if (charBufferFromThreadLocal.length < i10) {
                charBufferFromThreadLocal = growBuffer(charBufferFromThreadLocal, i5, i10);
            }
            str.getChars(i4, length, charBufferFromThreadLocal, i5);
            i5 = i10;
        }
        return new String(charBufferFromThreadLocal, 0, i5);
    }

    public int nextEscapeIndex(CharSequence charSequence, int i3, int i4) {
        while (i3 < i4) {
            int codePointAt = codePointAt(charSequence, i3, i4);
            if (codePointAt < 0 || escape(codePointAt) != null) {
                break;
            }
            i3 += Character.isSupplementaryCodePoint(codePointAt) ? 2 : 1;
        }
        return i3;
    }
}
