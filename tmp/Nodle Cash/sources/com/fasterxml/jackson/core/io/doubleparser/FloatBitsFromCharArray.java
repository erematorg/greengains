package com.fasterxml.jackson.core.io.doubleparser;

class FloatBitsFromCharArray extends AbstractFloatingPointBitsFromCharArray {
    public long nan() {
        return (long) Float.floatToRawIntBits(Float.NaN);
    }

    public long negativeInfinity() {
        return (long) Float.floatToRawIntBits(Float.NEGATIVE_INFINITY);
    }

    public long positiveInfinity() {
        return (long) Float.floatToRawIntBits(Float.POSITIVE_INFINITY);
    }

    public long valueOfFloatLiteral(char[] cArr, int i3, int i4, boolean z2, long j2, int i5, boolean z3, int i6) {
        float decFloatLiteralToFloat = FastFloatMath.decFloatLiteralToFloat(z2, j2, i5, z3, i6);
        if (Float.isNaN(decFloatLiteralToFloat)) {
            decFloatLiteralToFloat = Float.parseFloat(new String(cArr, i3, i4 - i3));
        }
        return (long) Float.floatToRawIntBits(decFloatLiteralToFloat);
    }

    public long valueOfHexLiteral(char[] cArr, int i3, int i4, boolean z2, long j2, int i5, boolean z3, int i6) {
        float hexFloatLiteralToFloat = FastFloatMath.hexFloatLiteralToFloat(z2, j2, i5, z3, i6);
        if (Float.isNaN(hexFloatLiteralToFloat)) {
            hexFloatLiteralToFloat = Float.parseFloat(new String(cArr, i3, i4 - i3));
        }
        return (long) Float.floatToRawIntBits(hexFloatLiteralToFloat);
    }
}
