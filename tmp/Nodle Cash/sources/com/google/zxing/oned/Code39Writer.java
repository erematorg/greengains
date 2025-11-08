package com.google.zxing.oned;

import A.a;
import androidx.camera.camera2.internal.C0118y;
import com.fasterxml.jackson.core.JsonPointer;
import com.google.zxing.BarcodeFormat;
import java.util.Collection;
import java.util.Collections;
import kotlin.text.Typography;
import org.objectweb.asm.signature.SignatureVisitor;

public final class Code39Writer extends OneDimensionalCodeWriter {
    private static void toIntArray(int i3, int[] iArr) {
        for (int i4 = 0; i4 < 9; i4++) {
            int i5 = 1;
            if (((1 << (8 - i4)) & i3) != 0) {
                i5 = 2;
            }
            iArr[i4] = i5;
        }
    }

    private static String tryToConvertToExtendedMode(String str) {
        int length = str.length();
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < length; i3++) {
            char charAt = str.charAt(i3);
            if (charAt != 0) {
                if (charAt != ' ') {
                    if (charAt == '@') {
                        sb.append("%V");
                    } else if (charAt == '`') {
                        sb.append("%W");
                    } else if (!(charAt == '-' || charAt == '.')) {
                        if (charAt <= 26) {
                            sb.append('$');
                            sb.append((char) (charAt + '@'));
                        } else if (charAt < ' ') {
                            sb.append('%');
                            sb.append((char) (charAt + Typography.amp));
                        } else if (charAt <= ',' || charAt == '/' || charAt == ':') {
                            sb.append(JsonPointer.SEPARATOR);
                            sb.append((char) (charAt + ' '));
                        } else if (charAt <= '9') {
                            sb.append((char) charAt);
                        } else if (charAt <= '?') {
                            sb.append('%');
                            sb.append((char) (charAt + 11));
                        } else if (charAt <= 'Z') {
                            sb.append((char) charAt);
                        } else if (charAt <= '_') {
                            sb.append('%');
                            sb.append((char) (charAt - 16));
                        } else if (charAt <= 'z') {
                            sb.append(SignatureVisitor.EXTENDS);
                            sb.append((char) (charAt - ' '));
                        } else if (charAt <= 127) {
                            sb.append('%');
                            sb.append((char) (charAt - '+'));
                        } else {
                            throw new IllegalArgumentException("Requested content contains a non-encodable character: '" + str.charAt(i3) + "'");
                        }
                    }
                }
                sb.append(charAt);
            } else {
                sb.append("%U");
            }
        }
        return sb.toString();
    }

    public boolean[] encode(String str) {
        int length = str.length();
        if (length <= 80) {
            int i3 = 0;
            while (true) {
                if (i3 >= length) {
                    break;
                } else if ("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%".indexOf(str.charAt(i3)) < 0) {
                    str = tryToConvertToExtendedMode(str);
                    length = str.length();
                    if (length > 80) {
                        throw new IllegalArgumentException(C0118y.c(length, "Requested contents should be less than 80 digits long, but got ", " (extended full ASCII mode)"));
                    }
                } else {
                    i3++;
                }
            }
            int[] iArr = new int[9];
            boolean[] zArr = new boolean[((length * 13) + 25)];
            toIntArray(148, iArr);
            int appendPattern = OneDimensionalCodeWriter.appendPattern(zArr, 0, iArr, true);
            int[] iArr2 = {1};
            int appendPattern2 = OneDimensionalCodeWriter.appendPattern(zArr, appendPattern, iArr2, false) + appendPattern;
            for (int i4 = 0; i4 < length; i4++) {
                toIntArray(Code39Reader.CHARACTER_ENCODINGS["0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%".indexOf(str.charAt(i4))], iArr);
                int appendPattern3 = OneDimensionalCodeWriter.appendPattern(zArr, appendPattern2, iArr, true) + appendPattern2;
                appendPattern2 = OneDimensionalCodeWriter.appendPattern(zArr, appendPattern3, iArr2, false) + appendPattern3;
            }
            toIntArray(148, iArr);
            OneDimensionalCodeWriter.appendPattern(zArr, appendPattern2, iArr, true);
            return zArr;
        }
        throw new IllegalArgumentException(a.k("Requested contents should be less than 80 digits long, but got ", length));
    }

    public Collection<BarcodeFormat> getSupportedWriteFormats() {
        return Collections.singleton(BarcodeFormat.CODE_39);
    }
}
