package com.google.zxing.common.reedsolomon;

import org.apache.xerces.impl.xs.SchemaSymbols;

final class GenericGFPoly {
    private final int[] coefficients;
    private final GenericGF field;

    public GenericGFPoly(GenericGF genericGF, int[] iArr) {
        if (iArr.length != 0) {
            this.field = genericGF;
            int length = iArr.length;
            int i3 = 1;
            if (length <= 1 || iArr[0] != 0) {
                this.coefficients = iArr;
                return;
            }
            while (i3 < length && iArr[i3] == 0) {
                i3++;
            }
            if (i3 == length) {
                this.coefficients = new int[]{0};
                return;
            }
            int[] iArr2 = new int[(length - i3)];
            this.coefficients = iArr2;
            System.arraycopy(iArr, i3, iArr2, 0, iArr2.length);
            return;
        }
        throw new IllegalArgumentException();
    }

    public GenericGFPoly addOrSubtract(GenericGFPoly genericGFPoly) {
        if (!this.field.equals(genericGFPoly.field)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        } else if (isZero()) {
            return genericGFPoly;
        } else {
            if (genericGFPoly.isZero()) {
                return this;
            }
            int[] iArr = this.coefficients;
            int[] iArr2 = genericGFPoly.coefficients;
            if (iArr.length <= iArr2.length) {
                int[] iArr3 = iArr;
                iArr = iArr2;
                iArr2 = iArr3;
            }
            int[] iArr4 = new int[iArr.length];
            int length = iArr.length - iArr2.length;
            System.arraycopy(iArr, 0, iArr4, 0, length);
            for (int i3 = length; i3 < iArr.length; i3++) {
                iArr4[i3] = GenericGF.addOrSubtract(iArr2[i3 - length], iArr[i3]);
            }
            return new GenericGFPoly(this.field, iArr4);
        }
    }

    public GenericGFPoly[] divide(GenericGFPoly genericGFPoly) {
        if (!this.field.equals(genericGFPoly.field)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        } else if (!genericGFPoly.isZero()) {
            GenericGFPoly zero = this.field.getZero();
            int inverse = this.field.inverse(genericGFPoly.getCoefficient(genericGFPoly.getDegree()));
            GenericGFPoly genericGFPoly2 = this;
            while (genericGFPoly2.getDegree() >= genericGFPoly.getDegree() && !genericGFPoly2.isZero()) {
                int degree = genericGFPoly2.getDegree() - genericGFPoly.getDegree();
                int multiply = this.field.multiply(genericGFPoly2.getCoefficient(genericGFPoly2.getDegree()), inverse);
                GenericGFPoly multiplyByMonomial = genericGFPoly.multiplyByMonomial(degree, multiply);
                zero = zero.addOrSubtract(this.field.buildMonomial(degree, multiply));
                genericGFPoly2 = genericGFPoly2.addOrSubtract(multiplyByMonomial);
            }
            return new GenericGFPoly[]{zero, genericGFPoly2};
        } else {
            throw new IllegalArgumentException("Divide by 0");
        }
    }

    public int evaluateAt(int i3) {
        if (i3 == 0) {
            return getCoefficient(0);
        }
        if (i3 == 1) {
            int i4 = 0;
            for (int addOrSubtract : this.coefficients) {
                i4 = GenericGF.addOrSubtract(i4, addOrSubtract);
            }
            return i4;
        }
        int[] iArr = this.coefficients;
        int i5 = iArr[0];
        int length = iArr.length;
        for (int i6 = 1; i6 < length; i6++) {
            i5 = GenericGF.addOrSubtract(this.field.multiply(i3, i5), this.coefficients[i6]);
        }
        return i5;
    }

    public int getCoefficient(int i3) {
        int[] iArr = this.coefficients;
        return iArr[(iArr.length - 1) - i3];
    }

    public int[] getCoefficients() {
        return this.coefficients;
    }

    public int getDegree() {
        return this.coefficients.length - 1;
    }

    public boolean isZero() {
        return this.coefficients[0] == 0;
    }

    public GenericGFPoly multiply(GenericGFPoly genericGFPoly) {
        if (!this.field.equals(genericGFPoly.field)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        } else if (isZero() || genericGFPoly.isZero()) {
            return this.field.getZero();
        } else {
            int[] iArr = this.coefficients;
            int length = iArr.length;
            int[] iArr2 = genericGFPoly.coefficients;
            int length2 = iArr2.length;
            int[] iArr3 = new int[((length + length2) - 1)];
            for (int i3 = 0; i3 < length; i3++) {
                int i4 = iArr[i3];
                for (int i5 = 0; i5 < length2; i5++) {
                    int i6 = i3 + i5;
                    iArr3[i6] = GenericGF.addOrSubtract(iArr3[i6], this.field.multiply(i4, iArr2[i5]));
                }
            }
            return new GenericGFPoly(this.field, iArr3);
        }
    }

    public GenericGFPoly multiplyByMonomial(int i3, int i4) {
        if (i3 < 0) {
            throw new IllegalArgumentException();
        } else if (i4 == 0) {
            return this.field.getZero();
        } else {
            int length = this.coefficients.length;
            int[] iArr = new int[(i3 + length)];
            for (int i5 = 0; i5 < length; i5++) {
                iArr[i5] = this.field.multiply(this.coefficients[i5], i4);
            }
            return new GenericGFPoly(this.field, iArr);
        }
    }

    public String toString() {
        if (isZero()) {
            return SchemaSymbols.ATTVAL_FALSE_0;
        }
        StringBuilder sb = new StringBuilder(getDegree() * 8);
        for (int degree = getDegree(); degree >= 0; degree--) {
            int coefficient = getCoefficient(degree);
            if (coefficient != 0) {
                if (coefficient < 0) {
                    if (degree == getDegree()) {
                        sb.append("-");
                    } else {
                        sb.append(" - ");
                    }
                    coefficient = -coefficient;
                } else if (sb.length() > 0) {
                    sb.append(" + ");
                }
                if (degree == 0 || coefficient != 1) {
                    int log = this.field.log(coefficient);
                    if (log == 0) {
                        sb.append('1');
                    } else if (log == 1) {
                        sb.append('a');
                    } else {
                        sb.append("a^");
                        sb.append(log);
                    }
                }
                if (degree != 0) {
                    if (degree == 1) {
                        sb.append('x');
                    } else {
                        sb.append("x^");
                        sb.append(degree);
                    }
                }
            }
        }
        return sb.toString();
    }

    public GenericGFPoly multiply(int i3) {
        if (i3 == 0) {
            return this.field.getZero();
        }
        if (i3 == 1) {
            return this;
        }
        int length = this.coefficients.length;
        int[] iArr = new int[length];
        for (int i4 = 0; i4 < length; i4++) {
            iArr[i4] = this.field.multiply(this.coefficients[i4], i3);
        }
        return new GenericGFPoly(this.field, iArr);
    }
}
