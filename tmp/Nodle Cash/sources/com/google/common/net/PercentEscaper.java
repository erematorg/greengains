package com.google.common.net;

import A.a;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.escape.UnicodeEscaper;
import javax.annotation.CheckForNull;
import org.apache.commons.lang3.StringUtils;
import org.objectweb.asm.signature.SignatureVisitor;

@GwtCompatible
@ElementTypesAreNonnullByDefault
public final class PercentEscaper extends UnicodeEscaper {
    private static final char[] PLUS_SIGN = {SignatureVisitor.EXTENDS};
    private static final char[] UPPER_HEX_DIGITS = "0123456789ABCDEF".toCharArray();
    private final boolean plusForSpace;
    private final boolean[] safeOctets;

    public PercentEscaper(String str, boolean z2) {
        Preconditions.checkNotNull(str);
        if (!str.matches(".*[0-9A-Za-z].*")) {
            String concat = str.concat("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
            if (!z2 || !concat.contains(StringUtils.SPACE)) {
                this.plusForSpace = z2;
                this.safeOctets = createSafeOctets(concat);
                return;
            }
            throw new IllegalArgumentException("plusForSpace cannot be specified when space is a 'safe' character");
        }
        throw new IllegalArgumentException("Alphanumeric characters are always 'safe' and should not be explicitly specified");
    }

    private static boolean[] createSafeOctets(String str) {
        char[] charArray = str.toCharArray();
        int i3 = -1;
        for (char max : charArray) {
            i3 = Math.max(max, i3);
        }
        boolean[] zArr = new boolean[(i3 + 1)];
        for (char c3 : charArray) {
            zArr[c3] = true;
        }
        return zArr;
    }

    public String escape(String str) {
        Preconditions.checkNotNull(str);
        int length = str.length();
        for (int i3 = 0; i3 < length; i3++) {
            char charAt = str.charAt(i3);
            boolean[] zArr = this.safeOctets;
            if (charAt >= zArr.length || !zArr[charAt]) {
                return escapeSlow(str, i3);
            }
        }
        return str;
    }

    public int nextEscapeIndex(CharSequence charSequence, int i3, int i4) {
        Preconditions.checkNotNull(charSequence);
        while (i3 < i4) {
            char charAt = charSequence.charAt(i3);
            boolean[] zArr = this.safeOctets;
            if (charAt >= zArr.length || !zArr[charAt]) {
                break;
            }
            i3++;
        }
        return i3;
    }

    @CheckForNull
    public char[] escape(int i3) {
        boolean[] zArr = this.safeOctets;
        if (i3 < zArr.length && zArr[i3]) {
            return null;
        }
        if (i3 == 32 && this.plusForSpace) {
            return PLUS_SIGN;
        }
        if (i3 <= 127) {
            char[] cArr = new char[3];
            cArr[0] = '%';
            char[] cArr2 = UPPER_HEX_DIGITS;
            cArr[2] = cArr2[i3 & 15];
            cArr[1] = cArr2[i3 >>> 4];
            return cArr;
        } else if (i3 <= 2047) {
            char[] cArr3 = new char[6];
            cArr3[0] = '%';
            cArr3[3] = '%';
            char[] cArr4 = UPPER_HEX_DIGITS;
            cArr3[5] = cArr4[i3 & 15];
            cArr3[4] = cArr4[((i3 >>> 4) & 3) | 8];
            cArr3[2] = cArr4[(i3 >>> 6) & 15];
            cArr3[1] = cArr4[(i3 >>> 10) | 12];
            return cArr3;
        } else if (i3 <= 65535) {
            char[] cArr5 = new char[9];
            cArr5[0] = '%';
            cArr5[1] = 'E';
            cArr5[3] = '%';
            cArr5[6] = '%';
            char[] cArr6 = UPPER_HEX_DIGITS;
            cArr5[8] = cArr6[i3 & 15];
            cArr5[7] = cArr6[((i3 >>> 4) & 3) | 8];
            cArr5[5] = cArr6[(i3 >>> 6) & 15];
            cArr5[4] = cArr6[((i3 >>> 10) & 3) | 8];
            cArr5[2] = cArr6[i3 >>> 12];
            return cArr5;
        } else if (i3 <= 1114111) {
            char[] cArr7 = new char[12];
            cArr7[0] = '%';
            cArr7[1] = 'F';
            cArr7[3] = '%';
            cArr7[6] = '%';
            cArr7[9] = '%';
            char[] cArr8 = UPPER_HEX_DIGITS;
            cArr7[11] = cArr8[i3 & 15];
            cArr7[10] = cArr8[((i3 >>> 4) & 3) | 8];
            cArr7[8] = cArr8[(i3 >>> 6) & 15];
            cArr7[7] = cArr8[((i3 >>> 10) & 3) | 8];
            cArr7[5] = cArr8[(i3 >>> 12) & 15];
            cArr7[4] = cArr8[((i3 >>> 16) & 3) | 8];
            cArr7[2] = cArr8[(i3 >>> 18) & 7];
            return cArr7;
        } else {
            throw new IllegalArgumentException(a.k("Invalid unicode character value ", i3));
        }
    }
}
