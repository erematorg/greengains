package com.fasterxml.jackson.core.io.doubleparser;

class FloatBitsFromCharSequence extends AbstractFloatingPointBitsFromCharSequence {
    public long nan() {
        return (long) Float.floatToRawIntBits(Float.NaN);
    }

    public long negativeInfinity() {
        return (long) Float.floatToRawIntBits(Float.NEGATIVE_INFINITY);
    }

    public long positiveInfinity() {
        return (long) Float.floatToRawIntBits(Float.POSITIVE_INFINITY);
    }

    public long valueOfFloatLiteral(CharSequence charSequence, int i3, int i4, boolean z2, long j2, int i5, boolean z3, int i6) {
        float decFloatLiteralToFloat = FastFloatMath.decFloatLiteralToFloat(z2, j2, i5, z3, i6);
        if (Float.isNaN(decFloatLiteralToFloat)) {
            decFloatLiteralToFloat = Float.parseFloat(charSequence.subSequence(i3, i4).toString());
        }
        return (long) Float.floatToRawIntBits(decFloatLiteralToFloat);
    }

    public long valueOfHexLiteral(CharSequence charSequence, int i3, int i4, boolean z2, long j2, int i5, boolean z3, int i6) {
        float hexFloatLiteralToFloat = FastFloatMath.hexFloatLiteralToFloat(z2, j2, i5, z3, i6);
        if (Float.isNaN(hexFloatLiteralToFloat)) {
            hexFloatLiteralToFloat = Float.parseFloat(charSequence.subSequence(i3, i4).toString());
        }
        return (long) Float.floatToRawIntBits(hexFloatLiteralToFloat);
    }
}
