package com.google.zxing.pdf417.decoder.ec;

import com.google.zxing.ChecksumException;

public final class ErrorCorrection {
    private final ModulusGF field = ModulusGF.PDF417_GF;

    private int[] findErrorLocations(ModulusPoly modulusPoly) throws ChecksumException {
        int degree = modulusPoly.getDegree();
        int[] iArr = new int[degree];
        int i3 = 0;
        for (int i4 = 1; i4 < this.field.getSize() && i3 < degree; i4++) {
            if (modulusPoly.evaluateAt(i4) == 0) {
                iArr[i3] = this.field.inverse(i4);
                i3++;
            }
        }
        if (i3 == degree) {
            return iArr;
        }
        throw ChecksumException.getChecksumInstance();
    }

    private int[] findErrorMagnitudes(ModulusPoly modulusPoly, ModulusPoly modulusPoly2, int[] iArr) {
        int degree = modulusPoly2.getDegree();
        if (degree < 1) {
            return new int[0];
        }
        int[] iArr2 = new int[degree];
        for (int i3 = 1; i3 <= degree; i3++) {
            iArr2[degree - i3] = this.field.multiply(i3, modulusPoly2.getCoefficient(i3));
        }
        ModulusPoly modulusPoly3 = new ModulusPoly(this.field, iArr2);
        int length = iArr.length;
        int[] iArr3 = new int[length];
        for (int i4 = 0; i4 < length; i4++) {
            int inverse = this.field.inverse(iArr[i4]);
            iArr3[i4] = this.field.multiply(this.field.subtract(0, modulusPoly.evaluateAt(inverse)), this.field.inverse(modulusPoly3.evaluateAt(inverse)));
        }
        return iArr3;
    }

    private ModulusPoly[] runEuclideanAlgorithm(ModulusPoly modulusPoly, ModulusPoly modulusPoly2, int i3) throws ChecksumException {
        if (modulusPoly.getDegree() < modulusPoly2.getDegree()) {
            ModulusPoly modulusPoly3 = modulusPoly2;
            modulusPoly2 = modulusPoly;
            modulusPoly = modulusPoly3;
        }
        ModulusPoly zero = this.field.getZero();
        ModulusPoly one = this.field.getOne();
        while (true) {
            ModulusPoly modulusPoly4 = r11;
            r11 = modulusPoly;
            modulusPoly = modulusPoly4;
            ModulusPoly modulusPoly5 = one;
            ModulusPoly modulusPoly6 = zero;
            zero = modulusPoly5;
            if (modulusPoly.getDegree() < i3 / 2) {
                int coefficient = zero.getCoefficient(0);
                if (coefficient != 0) {
                    int inverse = this.field.inverse(coefficient);
                    return new ModulusPoly[]{zero.multiply(inverse), modulusPoly.multiply(inverse)};
                }
                throw ChecksumException.getChecksumInstance();
            } else if (!modulusPoly.isZero()) {
                ModulusPoly zero2 = this.field.getZero();
                int inverse2 = this.field.inverse(modulusPoly.getCoefficient(modulusPoly.getDegree()));
                while (r11.getDegree() >= modulusPoly.getDegree() && !r11.isZero()) {
                    int degree = r11.getDegree() - modulusPoly.getDegree();
                    int multiply = this.field.multiply(r11.getCoefficient(r11.getDegree()), inverse2);
                    zero2 = zero2.add(this.field.buildMonomial(degree, multiply));
                    r11 = r11.subtract(modulusPoly.multiplyByMonomial(degree, multiply));
                }
                one = zero2.multiply(zero).subtract(modulusPoly6).negative();
            } else {
                throw ChecksumException.getChecksumInstance();
            }
        }
    }

    public int decode(int[] iArr, int i3, int[] iArr2) throws ChecksumException {
        ModulusPoly modulusPoly = new ModulusPoly(this.field, iArr);
        int[] iArr3 = new int[i3];
        int i4 = 0;
        boolean z2 = false;
        for (int i5 = i3; i5 > 0; i5--) {
            int evaluateAt = modulusPoly.evaluateAt(this.field.exp(i5));
            iArr3[i3 - i5] = evaluateAt;
            if (evaluateAt != 0) {
                z2 = true;
            }
        }
        if (!z2) {
            return 0;
        }
        ModulusPoly one = this.field.getOne();
        if (iArr2 != null) {
            for (int length : iArr2) {
                int exp = this.field.exp((iArr.length - 1) - length);
                ModulusGF modulusGF = this.field;
                one = one.multiply(new ModulusPoly(modulusGF, new int[]{modulusGF.subtract(0, exp), 1}));
            }
        }
        ModulusPoly[] runEuclideanAlgorithm = runEuclideanAlgorithm(this.field.buildMonomial(i3, 1), new ModulusPoly(this.field, iArr3), i3);
        ModulusPoly modulusPoly2 = runEuclideanAlgorithm[0];
        ModulusPoly modulusPoly3 = runEuclideanAlgorithm[1];
        int[] findErrorLocations = findErrorLocations(modulusPoly2);
        int[] findErrorMagnitudes = findErrorMagnitudes(modulusPoly3, modulusPoly2, findErrorLocations);
        while (i4 < findErrorLocations.length) {
            int length2 = (iArr.length - 1) - this.field.log(findErrorLocations[i4]);
            if (length2 >= 0) {
                iArr[length2] = this.field.subtract(iArr[length2], findErrorMagnitudes[i4]);
                i4++;
            } else {
                throw ChecksumException.getChecksumInstance();
            }
        }
        return findErrorLocations.length;
    }
}
