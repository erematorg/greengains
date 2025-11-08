package com.google.zxing.pdf417.decoder.ec;

final class ModulusPoly {
    private final int[] coefficients;
    private final ModulusGF field;

    public ModulusPoly(ModulusGF modulusGF, int[] iArr) {
        if (iArr.length != 0) {
            this.field = modulusGF;
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

    public ModulusPoly add(ModulusPoly modulusPoly) {
        if (!this.field.equals(modulusPoly.field)) {
            throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
        } else if (isZero()) {
            return modulusPoly;
        } else {
            if (modulusPoly.isZero()) {
                return this;
            }
            int[] iArr = this.coefficients;
            int[] iArr2 = modulusPoly.coefficients;
            if (iArr.length <= iArr2.length) {
                int[] iArr3 = iArr;
                iArr = iArr2;
                iArr2 = iArr3;
            }
            int[] iArr4 = new int[iArr.length];
            int length = iArr.length - iArr2.length;
            System.arraycopy(iArr, 0, iArr4, 0, length);
            for (int i3 = length; i3 < iArr.length; i3++) {
                iArr4[i3] = this.field.add(iArr2[i3 - length], iArr[i3]);
            }
            return new ModulusPoly(this.field, iArr4);
        }
    }

    public int evaluateAt(int i3) {
        if (i3 == 0) {
            return getCoefficient(0);
        }
        if (i3 == 1) {
            int i4 = 0;
            for (int add : this.coefficients) {
                i4 = this.field.add(i4, add);
            }
            return i4;
        }
        int[] iArr = this.coefficients;
        int i5 = iArr[0];
        int length = iArr.length;
        for (int i6 = 1; i6 < length; i6++) {
            ModulusGF modulusGF = this.field;
            i5 = modulusGF.add(modulusGF.multiply(i3, i5), this.coefficients[i6]);
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

    public ModulusPoly multiply(ModulusPoly modulusPoly) {
        if (!this.field.equals(modulusPoly.field)) {
            throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
        } else if (isZero() || modulusPoly.isZero()) {
            return this.field.getZero();
        } else {
            int[] iArr = this.coefficients;
            int length = iArr.length;
            int[] iArr2 = modulusPoly.coefficients;
            int length2 = iArr2.length;
            int[] iArr3 = new int[((length + length2) - 1)];
            for (int i3 = 0; i3 < length; i3++) {
                int i4 = iArr[i3];
                for (int i5 = 0; i5 < length2; i5++) {
                    int i6 = i3 + i5;
                    ModulusGF modulusGF = this.field;
                    iArr3[i6] = modulusGF.add(iArr3[i6], modulusGF.multiply(i4, iArr2[i5]));
                }
            }
            return new ModulusPoly(this.field, iArr3);
        }
    }

    public ModulusPoly multiplyByMonomial(int i3, int i4) {
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
            return new ModulusPoly(this.field, iArr);
        }
    }

    public ModulusPoly negative() {
        int length = this.coefficients.length;
        int[] iArr = new int[length];
        for (int i3 = 0; i3 < length; i3++) {
            iArr[i3] = this.field.subtract(0, this.coefficients[i3]);
        }
        return new ModulusPoly(this.field, iArr);
    }

    public ModulusPoly subtract(ModulusPoly modulusPoly) {
        if (this.field.equals(modulusPoly.field)) {
            return modulusPoly.isZero() ? this : add(modulusPoly.negative());
        }
        throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(getDegree() * 8);
        for (int degree = getDegree(); degree >= 0; degree--) {
            int coefficient = getCoefficient(degree);
            if (coefficient != 0) {
                if (coefficient < 0) {
                    sb.append(" - ");
                    coefficient = -coefficient;
                } else if (sb.length() > 0) {
                    sb.append(" + ");
                }
                if (degree == 0 || coefficient != 1) {
                    sb.append(coefficient);
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

    public ModulusPoly multiply(int i3) {
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
        return new ModulusPoly(this.field, iArr);
    }
}
