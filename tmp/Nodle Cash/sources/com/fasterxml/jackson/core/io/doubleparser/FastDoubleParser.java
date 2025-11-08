package com.fasterxml.jackson.core.io.doubleparser;

public class FastDoubleParser {
    private static final DoubleBitsFromCharArray CHAR_ARRAY_PARSER = new DoubleBitsFromCharArray();
    private static final DoubleBitsFromCharSequence CHAR_SEQ_PARSER = new DoubleBitsFromCharSequence();

    private FastDoubleParser() {
    }

    public static double parseDouble(CharSequence charSequence) throws NumberFormatException {
        return parseDouble(charSequence, 0, charSequence.length());
    }

    public static long parseDoubleBits(CharSequence charSequence, int i3, int i4) {
        return CHAR_SEQ_PARSER.parseFloatingPointLiteral(charSequence, i3, i4);
    }

    public static double parseDouble(CharSequence charSequence, int i3, int i4) throws NumberFormatException {
        long parseFloatingPointLiteral = CHAR_SEQ_PARSER.parseFloatingPointLiteral(charSequence, i3, i4);
        if (parseFloatingPointLiteral != -1) {
            return Double.longBitsToDouble(parseFloatingPointLiteral);
        }
        throw new NumberFormatException("Illegal input");
    }

    public static long parseDoubleBits(char[] cArr, int i3, int i4) {
        return CHAR_ARRAY_PARSER.parseFloatingPointLiteral(cArr, i3, i4);
    }

    public static double parseDouble(char[] cArr) throws NumberFormatException {
        return parseDouble(cArr, 0, cArr.length);
    }

    public static double parseDouble(char[] cArr, int i3, int i4) throws NumberFormatException {
        long parseFloatingPointLiteral = CHAR_ARRAY_PARSER.parseFloatingPointLiteral(cArr, i3, i4);
        if (parseFloatingPointLiteral != -1) {
            return Double.longBitsToDouble(parseFloatingPointLiteral);
        }
        throw new NumberFormatException("Illegal input");
    }
}
