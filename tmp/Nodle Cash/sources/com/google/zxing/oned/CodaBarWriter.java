package com.google.zxing.oned;

import com.fasterxml.jackson.core.JsonPointer;
import com.google.zxing.BarcodeFormat;
import java.util.Collection;
import java.util.Collections;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.lang3.ClassUtils;
import org.objectweb.asm.signature.SignatureVisitor;

public final class CodaBarWriter extends OneDimensionalCodeWriter {
    private static final char[] ALT_START_END_CHARS = {'T', 'N', '*', 'E'};
    private static final char[] CHARS_WHICH_ARE_TEN_LENGTH_EACH_AFTER_DECODED = {JsonPointer.SEPARATOR, AbstractJsonLexerKt.COLON, SignatureVisitor.EXTENDS, ClassUtils.PACKAGE_SEPARATOR_CHAR};
    private static final char DEFAULT_GUARD;
    private static final char[] START_END_CHARS;

    static {
        char[] cArr = {'A', 'B', 'C', 'D'};
        START_END_CHARS = cArr;
        DEFAULT_GUARD = cArr[0];
    }

    public boolean[] encode(String str) {
        int i3;
        if (str.length() < 2) {
            StringBuilder sb = new StringBuilder();
            char c3 = DEFAULT_GUARD;
            sb.append(c3);
            sb.append(str);
            sb.append(c3);
            str = sb.toString();
        } else {
            char upperCase = Character.toUpperCase(str.charAt(0));
            char upperCase2 = Character.toUpperCase(str.charAt(str.length() - 1));
            char[] cArr = START_END_CHARS;
            boolean arrayContains = CodaBarReader.arrayContains(cArr, upperCase);
            boolean arrayContains2 = CodaBarReader.arrayContains(cArr, upperCase2);
            char[] cArr2 = ALT_START_END_CHARS;
            boolean arrayContains3 = CodaBarReader.arrayContains(cArr2, upperCase);
            boolean arrayContains4 = CodaBarReader.arrayContains(cArr2, upperCase2);
            if (arrayContains) {
                if (!arrayContains2) {
                    throw new IllegalArgumentException("Invalid start/end guards: ".concat(str));
                }
            } else if (arrayContains3) {
                if (!arrayContains4) {
                    throw new IllegalArgumentException("Invalid start/end guards: ".concat(str));
                }
            } else if (arrayContains2 || arrayContains4) {
                throw new IllegalArgumentException("Invalid start/end guards: ".concat(str));
            } else {
                StringBuilder sb2 = new StringBuilder();
                char c4 = DEFAULT_GUARD;
                sb2.append(c4);
                sb2.append(str);
                sb2.append(c4);
                str = sb2.toString();
            }
        }
        int i4 = 20;
        for (int i5 = 1; i5 < str.length() - 1; i5++) {
            if (Character.isDigit(str.charAt(i5)) || str.charAt(i5) == '-' || str.charAt(i5) == '$') {
                i4 += 9;
            } else if (CodaBarReader.arrayContains(CHARS_WHICH_ARE_TEN_LENGTH_EACH_AFTER_DECODED, str.charAt(i5))) {
                i4 += 10;
            } else {
                throw new IllegalArgumentException("Cannot encode : '" + str.charAt(i5) + '\'');
            }
        }
        boolean[] zArr = new boolean[((str.length() - 1) + i4)];
        int i6 = 0;
        for (int i7 = 0; i7 < str.length(); i7++) {
            char upperCase3 = Character.toUpperCase(str.charAt(i7));
            if (i7 == 0 || i7 == str.length() - 1) {
                if (upperCase3 == '*') {
                    upperCase3 = 'C';
                } else if (upperCase3 == 'E') {
                    upperCase3 = 'D';
                } else if (upperCase3 == 'N') {
                    upperCase3 = 'B';
                } else if (upperCase3 == 'T') {
                    upperCase3 = 'A';
                }
            }
            int i8 = 0;
            while (true) {
                char[] cArr3 = CodaBarReader.ALPHABET;
                if (i8 >= cArr3.length) {
                    i3 = 0;
                    break;
                } else if (upperCase3 == cArr3[i8]) {
                    i3 = CodaBarReader.CHARACTER_ENCODINGS[i8];
                    break;
                } else {
                    i8++;
                }
            }
            int i9 = 0;
            int i10 = 0;
            boolean z2 = true;
            while (i9 < 7) {
                zArr[i6] = z2;
                i6++;
                if (((i3 >> (6 - i9)) & 1) == 0 || i10 == 1) {
                    z2 = !z2;
                    i9++;
                    i10 = 0;
                } else {
                    i10++;
                }
            }
            if (i7 < str.length() - 1) {
                zArr[i6] = false;
                i6++;
            }
        }
        return zArr;
    }

    public Collection<BarcodeFormat> getSupportedWriteFormats() {
        return Collections.singleton(BarcodeFormat.CODABAR);
    }
}
