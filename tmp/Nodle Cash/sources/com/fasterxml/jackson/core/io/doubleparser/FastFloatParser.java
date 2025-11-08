package com.fasterxml.jackson.core.io.doubleparser;

public class FastFloatParser {
    private static final FloatBitsFromCharArray CHAR_ARRAY_PARSER = new FloatBitsFromCharArray();
    private static final FloatBitsFromCharSequence CHAR_SEQ_PARSER = new FloatBitsFromCharSequence();

    private FastFloatParser() {
    }

    public static float parseFloat(CharSequence charSequence) throws NumberFormatException {
        return parseFloat(charSequence, 0, charSequence.length());
    }

    public static long parseFloatBits(CharSequence charSequence, int i3, int i4) {
        return CHAR_SEQ_PARSER.parseFloatingPointLiteral(charSequence, i3, i4);
    }

    public static float parseFloat(CharSequence charSequence, int i3, int i4) throws NumberFormatException {
        long parseFloatingPointLiteral = CHAR_SEQ_PARSER.parseFloatingPointLiteral(charSequence, i3, i4);
        if (parseFloatingPointLiteral != -1) {
            return Float.intBitsToFloat((int) parseFloatingPointLiteral);
        }
        throw new NumberFormatException("Illegal input");
    }

    public static long parseFloatBits(char[] cArr, int i3, int i4) {
        return CHAR_ARRAY_PARSER.parseFloatingPointLiteral(cArr, i3, i4);
    }

    public static float parseFloat(char[] cArr) throws NumberFormatException {
        return parseFloat(cArr, 0, cArr.length);
    }

    public static float parseFloat(char[] cArr, int i3, int i4) throws NumberFormatException {
        long parseFloatingPointLiteral = CHAR_ARRAY_PARSER.parseFloatingPointLiteral(cArr, i3, i4);
        if (parseFloatingPointLiteral != -1) {
            return Float.intBitsToFloat((int) parseFloatingPointLiteral);
        }
        throw new NumberFormatException("Illegal input");
    }
}
