package com.fasterxml.jackson.core.io.doubleparser;

final class DoubleBitsFromCharArray extends AbstractFloatingPointBitsFromCharArray {
    public long nan() {
        return Double.doubleToRawLongBits(Double.NaN);
    }

    public long negativeInfinity() {
        return Double.doubleToRawLongBits(Double.NEGATIVE_INFINITY);
    }

    public long positiveInfinity() {
        return Double.doubleToRawLongBits(Double.POSITIVE_INFINITY);
    }

    public long valueOfFloatLiteral(char[] cArr, int i3, int i4, boolean z2, long j2, int i5, boolean z3, int i6) {
        double tryDecFloatToDoubleTruncated = FastDoubleMath.tryDecFloatToDoubleTruncated(z2, j2, i5, z3, i6);
        if (Double.isNaN(tryDecFloatToDoubleTruncated)) {
            tryDecFloatToDoubleTruncated = Double.parseDouble(new String(cArr, i3, i4 - i3));
        }
        return Double.doubleToRawLongBits(tryDecFloatToDoubleTruncated);
    }

    public long valueOfHexLiteral(char[] cArr, int i3, int i4, boolean z2, long j2, int i5, boolean z3, int i6) {
        int i7 = i3;
        double tryHexFloatToDoubleTruncated = FastDoubleMath.tryHexFloatToDoubleTruncated(z2, j2, (long) i5, z3, (long) i6);
        if (Double.isNaN(tryHexFloatToDoubleTruncated)) {
            char[] cArr2 = cArr;
            tryHexFloatToDoubleTruncated = Double.parseDouble(new String(cArr, i3, i4 - i7));
        }
        return Double.doubleToRawLongBits(tryHexFloatToDoubleTruncated);
    }
}
