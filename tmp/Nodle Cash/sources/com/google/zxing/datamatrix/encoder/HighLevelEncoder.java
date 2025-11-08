package com.google.zxing.datamatrix.encoder;

import com.google.zxing.Dimension;
import java.util.Arrays;

public final class HighLevelEncoder {
    static final int ASCII_ENCODATION = 0;
    static final int BASE256_ENCODATION = 5;
    static final int C40_ENCODATION = 1;
    static final char C40_UNLATCH = 'þ';
    static final int EDIFACT_ENCODATION = 4;
    static final char LATCH_TO_ANSIX12 = 'î';
    static final char LATCH_TO_BASE256 = 'ç';
    static final char LATCH_TO_C40 = 'æ';
    static final char LATCH_TO_EDIFACT = 'ð';
    static final char LATCH_TO_TEXT = 'ï';
    private static final char MACRO_05 = 'ì';
    static final String MACRO_05_HEADER = "[)>\u001e05\u001d";
    private static final char MACRO_06 = 'í';
    static final String MACRO_06_HEADER = "[)>\u001e06\u001d";
    static final String MACRO_TRAILER = "\u001e\u0004";
    private static final char PAD = '';
    static final int TEXT_ENCODATION = 2;
    static final char UPPER_SHIFT = 'ë';
    static final int X12_ENCODATION = 3;
    static final char X12_UNLATCH = 'þ';

    private HighLevelEncoder() {
    }

    public static int determineConsecutiveDigitCount(CharSequence charSequence, int i3) {
        int length = charSequence.length();
        int i4 = i3;
        while (i4 < length && isDigit(charSequence.charAt(i4))) {
            i4++;
        }
        return i4 - i3;
    }

    public static String encodeHighLevel(String str) {
        return encodeHighLevel(str, SymbolShapeHint.FORCE_NONE, (Dimension) null, (Dimension) null, false);
    }

    private static int findMinimums(float[] fArr, int[] iArr, int i3, byte[] bArr) {
        for (int i4 = 0; i4 < 6; i4++) {
            int ceil = (int) Math.ceil((double) fArr[i4]);
            iArr[i4] = ceil;
            if (i3 > ceil) {
                Arrays.fill(bArr, (byte) 0);
                i3 = ceil;
            }
            if (i3 == ceil) {
                bArr[i4] = (byte) (bArr[i4] + 1);
            }
        }
        return i3;
    }

    private static int getMinimumCount(byte[] bArr) {
        int i3 = 0;
        for (int i4 = 0; i4 < 6; i4++) {
            i3 += bArr[i4];
        }
        return i3;
    }

    public static void illegalCharacter(char c3) {
        String hexString = Integer.toHexString(c3);
        throw new IllegalArgumentException("Illegal character: " + c3 + " (0x" + ("0000".substring(0, 4 - hexString.length()) + hexString) + ')');
    }

    public static boolean isDigit(char c3) {
        return c3 >= '0' && c3 <= '9';
    }

    public static boolean isExtendedASCII(char c3) {
        return c3 >= 128 && c3 <= 255;
    }

    public static boolean isNativeC40(char c3) {
        return c3 == ' ' || (c3 >= '0' && c3 <= '9') || (c3 >= 'A' && c3 <= 'Z');
    }

    public static boolean isNativeEDIFACT(char c3) {
        return c3 >= ' ' && c3 <= '^';
    }

    public static boolean isNativeText(char c3) {
        return c3 == ' ' || (c3 >= '0' && c3 <= '9') || (c3 >= 'a' && c3 <= 'z');
    }

    public static boolean isNativeX12(char c3) {
        return isX12TermSep(c3) || c3 == ' ' || (c3 >= '0' && c3 <= '9') || (c3 >= 'A' && c3 <= 'Z');
    }

    private static boolean isSpecialB256(char c3) {
        return false;
    }

    private static boolean isX12TermSep(char c3) {
        return c3 == 13 || c3 == '*' || c3 == '>';
    }

    public static int lookAheadTest(CharSequence charSequence, int i3, int i4) {
        int lookAheadTestIntern = lookAheadTestIntern(charSequence, i3, i4);
        if (i4 == 3 && lookAheadTestIntern == 3) {
            int min = Math.min(i3 + 3, charSequence.length());
            while (i3 < min) {
                if (!isNativeX12(charSequence.charAt(i3))) {
                    return 0;
                }
                i3++;
            }
        } else if (i4 == 4 && lookAheadTestIntern == 4) {
            int min2 = Math.min(i3 + 4, charSequence.length());
            while (i3 < min2) {
                if (!isNativeEDIFACT(charSequence.charAt(i3))) {
                    return 0;
                }
                i3++;
            }
        }
        return lookAheadTestIntern;
    }

    public static int lookAheadTestIntern(CharSequence charSequence, int i3, int i4) {
        float[] fArr;
        int i5;
        int i6;
        int i7;
        int i8;
        CharSequence charSequence2 = charSequence;
        int i9 = i3;
        if (i9 >= charSequence.length()) {
            return i4;
        }
        float f2 = 2.0f;
        int i10 = 5;
        int i11 = 2;
        int i12 = 4;
        int i13 = 3;
        if (i4 == 0) {
            fArr = new float[]{0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.25f};
        } else {
            fArr = new float[]{1.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.25f};
            fArr[i4] = 0.0f;
        }
        byte[] bArr = new byte[6];
        int[] iArr = new int[6];
        int i14 = 0;
        while (true) {
            int i15 = i9 + i14;
            if (i15 == charSequence.length()) {
                Arrays.fill(bArr, (byte) 0);
                Arrays.fill(iArr, 0);
                int findMinimums = findMinimums(fArr, iArr, Integer.MAX_VALUE, bArr);
                int minimumCount = getMinimumCount(bArr);
                if (iArr[0] == findMinimums) {
                    return 0;
                }
                if (minimumCount == 1) {
                    if (bArr[i10] > 0) {
                        return i10;
                    }
                    if (bArr[i12] > 0) {
                        return i12;
                    }
                    if (bArr[i11] > 0) {
                        return i11;
                    }
                    if (bArr[i13] > 0) {
                        return i13;
                    }
                }
                return 1;
            }
            char charAt = charSequence2.charAt(i15);
            i14++;
            if (isDigit(charAt)) {
                fArr[0] = fArr[0] + 0.5f;
            } else if (isExtendedASCII(charAt)) {
                float ceil = (float) Math.ceil((double) fArr[0]);
                fArr[0] = ceil;
                fArr[0] = ceil + f2;
            } else {
                float ceil2 = (float) Math.ceil((double) fArr[0]);
                fArr[0] = ceil2;
                fArr[0] = ceil2 + 1.0f;
            }
            if (isNativeC40(charAt)) {
                fArr[1] = fArr[1] + 0.6666667f;
            } else if (isExtendedASCII(charAt)) {
                fArr[1] = fArr[1] + 2.6666667f;
            } else {
                fArr[1] = fArr[1] + 1.3333334f;
            }
            if (isNativeText(charAt)) {
                fArr[i11] = fArr[i11] + 0.6666667f;
            } else if (isExtendedASCII(charAt)) {
                fArr[i11] = fArr[i11] + 2.6666667f;
            } else {
                fArr[i11] = fArr[i11] + 1.3333334f;
            }
            if (isNativeX12(charAt)) {
                fArr[i13] = fArr[i13] + 0.6666667f;
            } else if (isExtendedASCII(charAt)) {
                fArr[i13] = fArr[i13] + 4.3333335f;
            } else {
                fArr[i13] = fArr[i13] + 3.3333333f;
            }
            if (isNativeEDIFACT(charAt)) {
                fArr[i12] = fArr[i12] + 0.75f;
            } else if (isExtendedASCII(charAt)) {
                fArr[i12] = fArr[i12] + 4.25f;
            } else {
                fArr[i12] = fArr[i12] + 3.25f;
            }
            if (isSpecialB256(charAt)) {
                i5 = 5;
                fArr[5] = fArr[5] + 4.0f;
            } else {
                i5 = 5;
                fArr[5] = fArr[5] + 1.0f;
            }
            if (i14 >= i12) {
                Arrays.fill(bArr, (byte) 0);
                Arrays.fill(iArr, 0);
                findMinimums(fArr, iArr, Integer.MAX_VALUE, bArr);
                if (iArr[0] < min(iArr[i5], iArr[1], iArr[i11], iArr[i13], iArr[i12])) {
                    return 0;
                }
                int i16 = iArr[i5];
                if (i16 < iArr[0] || i16 + 1 < min(iArr[1], iArr[2], iArr[3], iArr[i12])) {
                    return 5;
                }
                if (iArr[i12] + 1 < min(iArr[5], iArr[1], iArr[2], iArr[3], iArr[0])) {
                    return i12;
                }
                if (iArr[2] + 1 < min(iArr[5], iArr[1], iArr[i12], iArr[3], iArr[0])) {
                    return 2;
                }
                if (iArr[3] + 1 < min(iArr[5], iArr[1], iArr[4], iArr[2], iArr[0])) {
                    return 3;
                }
                i7 = 4;
                i6 = 2;
                if (iArr[1] + 1 < min(iArr[0], iArr[5], iArr[4], iArr[2])) {
                    int i17 = iArr[1];
                    int i18 = iArr[3];
                    if (i17 < i18) {
                        return 1;
                    }
                    if (i17 == i18) {
                        for (int i19 = i9 + i14 + 1; i19 < charSequence.length(); i19++) {
                            char charAt2 = charSequence2.charAt(i19);
                            if (isX12TermSep(charAt2)) {
                                return 3;
                            }
                            if (!isNativeX12(charAt2)) {
                                break;
                            }
                        }
                        return 1;
                    }
                    i8 = 3;
                } else {
                    i8 = 3;
                }
                i10 = 5;
            } else {
                i10 = i5;
                i7 = i12;
                i8 = i13;
                i6 = i11;
            }
            i12 = i7;
            i11 = i6;
            i13 = i8;
            f2 = 2.0f;
        }
    }

    private static int min(int i3, int i4, int i5, int i6, int i7) {
        return Math.min(min(i3, i4, i5, i6), i7);
    }

    private static char randomize253State(int i3) {
        int i4 = (i3 * 149) % 253;
        int i5 = i4 + 130;
        if (i5 > 254) {
            i5 = i4 - 124;
        }
        return (char) i5;
    }

    public static String encodeHighLevel(String str, SymbolShapeHint symbolShapeHint, Dimension dimension, Dimension dimension2) {
        return encodeHighLevel(str, symbolShapeHint, dimension, dimension2, false);
    }

    private static int min(int i3, int i4, int i5, int i6) {
        return Math.min(i3, Math.min(i4, Math.min(i5, i6)));
    }

    public static String encodeHighLevel(String str, SymbolShapeHint symbolShapeHint, Dimension dimension, Dimension dimension2, boolean z2) {
        C40Encoder c40Encoder = new C40Encoder();
        int i3 = 0;
        Encoder[] encoderArr = {new ASCIIEncoder(), c40Encoder, new TextEncoder(), new X12Encoder(), new EdifactEncoder(), new Base256Encoder()};
        EncoderContext encoderContext = new EncoderContext(str);
        encoderContext.setSymbolShape(symbolShapeHint);
        encoderContext.setSizeConstraints(dimension, dimension2);
        if (str.startsWith(MACRO_05_HEADER) && str.endsWith(MACRO_TRAILER)) {
            encoderContext.writeCodeword(MACRO_05);
            encoderContext.setSkipAtEnd(2);
            encoderContext.pos += 7;
        } else if (str.startsWith(MACRO_06_HEADER) && str.endsWith(MACRO_TRAILER)) {
            encoderContext.writeCodeword(MACRO_06);
            encoderContext.setSkipAtEnd(2);
            encoderContext.pos += 7;
        }
        if (z2) {
            c40Encoder.encodeMaximal(encoderContext);
            i3 = encoderContext.getNewEncoding();
            encoderContext.resetEncoderSignal();
        }
        while (encoderContext.hasMoreCharacters()) {
            encoderArr[i3].encode(encoderContext);
            if (encoderContext.getNewEncoding() >= 0) {
                i3 = encoderContext.getNewEncoding();
                encoderContext.resetEncoderSignal();
            }
        }
        int codewordCount = encoderContext.getCodewordCount();
        encoderContext.updateSymbolInfo();
        int dataCapacity = encoderContext.getSymbolInfo().getDataCapacity();
        if (!(codewordCount >= dataCapacity || i3 == 0 || i3 == 5 || i3 == 4)) {
            encoderContext.writeCodeword(254);
        }
        StringBuilder codewords = encoderContext.getCodewords();
        if (codewords.length() < dataCapacity) {
            codewords.append(PAD);
        }
        while (codewords.length() < dataCapacity) {
            codewords.append(randomize253State(codewords.length() + 1));
        }
        return encoderContext.getCodewords().toString();
    }
}
