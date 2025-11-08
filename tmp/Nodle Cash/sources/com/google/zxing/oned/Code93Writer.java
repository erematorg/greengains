package com.google.zxing.oned;

import A.a;
import com.google.zxing.BarcodeFormat;
import java.util.Collection;
import java.util.Collections;
import kotlin.text.Typography;

public class Code93Writer extends OneDimensionalCodeWriter {
    @Deprecated
    public static int appendPattern(boolean[] zArr, int i3, int[] iArr, boolean z2) {
        int length = iArr.length;
        int i4 = 0;
        while (i4 < length) {
            int i5 = i3 + 1;
            zArr[i3] = iArr[i4] != 0;
            i4++;
            i3 = i5;
        }
        return 9;
    }

    private static int computeChecksumIndex(String str, int i3) {
        int i4 = 0;
        int i5 = 1;
        for (int length = str.length() - 1; length >= 0; length--) {
            i4 += "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".indexOf(str.charAt(length)) * i5;
            i5++;
            if (i5 > i3) {
                i5 = 1;
            }
        }
        return i4 % 47;
    }

    public static String convertToExtended(String str) {
        int length = str.length();
        StringBuilder sb = new StringBuilder(length * 2);
        for (int i3 = 0; i3 < length; i3++) {
            char charAt = str.charAt(i3);
            if (charAt == 0) {
                sb.append("bU");
            } else if (charAt <= 26) {
                sb.append('a');
                sb.append((char) (charAt + '@'));
            } else if (charAt <= 31) {
                sb.append('b');
                sb.append((char) (charAt + Typography.amp));
            } else if (charAt == ' ' || charAt == '$' || charAt == '%' || charAt == '+') {
                sb.append(charAt);
            } else if (charAt <= ',') {
                sb.append('c');
                sb.append((char) (charAt + ' '));
            } else if (charAt <= '9') {
                sb.append(charAt);
            } else if (charAt == ':') {
                sb.append("cZ");
            } else if (charAt <= '?') {
                sb.append('b');
                sb.append((char) (charAt + 11));
            } else if (charAt == '@') {
                sb.append("bV");
            } else if (charAt <= 'Z') {
                sb.append(charAt);
            } else if (charAt <= '_') {
                sb.append('b');
                sb.append((char) (charAt - 16));
            } else if (charAt == '`') {
                sb.append("bW");
            } else if (charAt <= 'z') {
                sb.append('d');
                sb.append((char) (charAt - ' '));
            } else if (charAt <= 127) {
                sb.append('b');
                sb.append((char) (charAt - '+'));
            } else {
                throw new IllegalArgumentException("Requested content contains a non-encodable character: '" + charAt + "'");
            }
        }
        return sb.toString();
    }

    public boolean[] encode(String str) {
        String convertToExtended = convertToExtended(str);
        int length = convertToExtended.length();
        if (length <= 80) {
            boolean[] zArr = new boolean[(((convertToExtended.length() + 4) * 9) + 1)];
            int appendPattern = appendPattern(zArr, 0, Code93Reader.ASTERISK_ENCODING);
            for (int i3 = 0; i3 < length; i3++) {
                appendPattern += appendPattern(zArr, appendPattern, Code93Reader.CHARACTER_ENCODINGS["0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".indexOf(convertToExtended.charAt(i3))]);
            }
            int computeChecksumIndex = computeChecksumIndex(convertToExtended, 20);
            int[] iArr = Code93Reader.CHARACTER_ENCODINGS;
            int appendPattern2 = appendPattern + appendPattern(zArr, appendPattern, iArr[computeChecksumIndex]);
            StringBuilder p2 = a.p(convertToExtended);
            p2.append("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".charAt(computeChecksumIndex));
            int appendPattern3 = appendPattern2 + appendPattern(zArr, appendPattern2, iArr[computeChecksumIndex(p2.toString(), 15)]);
            zArr[appendPattern3 + appendPattern(zArr, appendPattern3, Code93Reader.ASTERISK_ENCODING)] = true;
            return zArr;
        }
        throw new IllegalArgumentException(a.k("Requested contents should be less than 80 digits long after converting to extended encoding, but got ", length));
    }

    public Collection<BarcodeFormat> getSupportedWriteFormats() {
        return Collections.singleton(BarcodeFormat.CODE_93);
    }

    private static int appendPattern(boolean[] zArr, int i3, int i4) {
        for (int i5 = 0; i5 < 9; i5++) {
            boolean z2 = true;
            int i6 = i3 + i5;
            if (((1 << (8 - i5)) & i4) == 0) {
                z2 = false;
            }
            zArr[i6] = z2;
        }
        return 9;
    }
}
