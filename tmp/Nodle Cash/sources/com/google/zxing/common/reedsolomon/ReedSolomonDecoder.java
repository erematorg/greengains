package com.google.zxing.common.reedsolomon;

public final class ReedSolomonDecoder {
    private final GenericGF field;

    public ReedSolomonDecoder(GenericGF genericGF) {
        this.field = genericGF;
    }

    private int[] findErrorLocations(GenericGFPoly genericGFPoly) throws ReedSolomonException {
        int degree = genericGFPoly.getDegree();
        if (degree == 1) {
            return new int[]{genericGFPoly.getCoefficient(1)};
        }
        int[] iArr = new int[degree];
        int i3 = 0;
        for (int i4 = 1; i4 < this.field.getSize() && i3 < degree; i4++) {
            if (genericGFPoly.evaluateAt(i4) == 0) {
                iArr[i3] = this.field.inverse(i4);
                i3++;
            }
        }
        if (i3 == degree) {
            return iArr;
        }
        throw new ReedSolomonException("Error locator degree does not match number of roots");
    }

    private int[] findErrorMagnitudes(GenericGFPoly genericGFPoly, int[] iArr) {
        int length = iArr.length;
        int[] iArr2 = new int[length];
        for (int i3 = 0; i3 < length; i3++) {
            int inverse = this.field.inverse(iArr[i3]);
            int i4 = 1;
            for (int i5 = 0; i5 < length; i5++) {
                if (i3 != i5) {
                    int multiply = this.field.multiply(iArr[i5], inverse);
                    i4 = this.field.multiply(i4, (multiply & 1) == 0 ? multiply | 1 : multiply & -2);
                }
            }
            iArr2[i3] = this.field.multiply(genericGFPoly.evaluateAt(inverse), this.field.inverse(i4));
            if (this.field.getGeneratorBase() != 0) {
                iArr2[i3] = this.field.multiply(iArr2[i3], inverse);
            }
        }
        return iArr2;
    }

    private GenericGFPoly[] runEuclideanAlgorithm(GenericGFPoly genericGFPoly, GenericGFPoly genericGFPoly2, int i3) throws ReedSolomonException {
        if (genericGFPoly.getDegree() < genericGFPoly2.getDegree()) {
            GenericGFPoly genericGFPoly3 = genericGFPoly2;
            genericGFPoly2 = genericGFPoly;
            genericGFPoly = genericGFPoly3;
        }
        GenericGFPoly zero = this.field.getZero();
        GenericGFPoly one = this.field.getOne();
        do {
            GenericGFPoly genericGFPoly4 = r11;
            r11 = genericGFPoly;
            genericGFPoly = genericGFPoly4;
            GenericGFPoly genericGFPoly5 = one;
            GenericGFPoly genericGFPoly6 = zero;
            zero = genericGFPoly5;
            if (genericGFPoly.getDegree() * 2 < i3) {
                int coefficient = zero.getCoefficient(0);
                if (coefficient != 0) {
                    int inverse = this.field.inverse(coefficient);
                    return new GenericGFPoly[]{zero.multiply(inverse), genericGFPoly.multiply(inverse)};
                }
                throw new ReedSolomonException("sigmaTilde(0) was zero");
            } else if (!genericGFPoly.isZero()) {
                GenericGFPoly zero2 = this.field.getZero();
                int inverse2 = this.field.inverse(genericGFPoly.getCoefficient(genericGFPoly.getDegree()));
                while (r11.getDegree() >= genericGFPoly.getDegree() && !r11.isZero()) {
                    int degree = r11.getDegree() - genericGFPoly.getDegree();
                    int multiply = this.field.multiply(r11.getCoefficient(r11.getDegree()), inverse2);
                    zero2 = zero2.addOrSubtract(this.field.buildMonomial(degree, multiply));
                    r11 = r11.addOrSubtract(genericGFPoly.multiplyByMonomial(degree, multiply));
                }
                one = zero2.multiply(zero).addOrSubtract(genericGFPoly6);
            } else {
                throw new ReedSolomonException("r_{i-1} was zero");
            }
        } while (r11.getDegree() < genericGFPoly.getDegree());
        throw new IllegalStateException("Division algorithm failed to reduce polynomial? r: " + r11 + ", rLast: " + genericGFPoly);
    }

    public void decode(int[] iArr, int i3) throws ReedSolomonException {
        decodeWithECCount(iArr, i3);
    }

    public int decodeWithECCount(int[] iArr, int i3) throws ReedSolomonException {
        GenericGFPoly genericGFPoly = new GenericGFPoly(this.field, iArr);
        int[] iArr2 = new int[i3];
        int i4 = 0;
        boolean z2 = true;
        for (int i5 = 0; i5 < i3; i5++) {
            GenericGF genericGF = this.field;
            int evaluateAt = genericGFPoly.evaluateAt(genericGF.exp(genericGF.getGeneratorBase() + i5));
            iArr2[(i3 - 1) - i5] = evaluateAt;
            if (evaluateAt != 0) {
                z2 = false;
            }
        }
        if (z2) {
            return 0;
        }
        GenericGFPoly[] runEuclideanAlgorithm = runEuclideanAlgorithm(this.field.buildMonomial(i3, 1), new GenericGFPoly(this.field, iArr2), i3);
        GenericGFPoly genericGFPoly2 = runEuclideanAlgorithm[0];
        GenericGFPoly genericGFPoly3 = runEuclideanAlgorithm[1];
        int[] findErrorLocations = findErrorLocations(genericGFPoly2);
        int[] findErrorMagnitudes = findErrorMagnitudes(genericGFPoly3, findErrorLocations);
        while (i4 < findErrorLocations.length) {
            int length = (iArr.length - 1) - this.field.log(findErrorLocations[i4]);
            if (length >= 0) {
                iArr[length] = GenericGF.addOrSubtract(iArr[length], findErrorMagnitudes[i4]);
                i4++;
            } else {
                throw new ReedSolomonException("Bad error location");
            }
        }
        return findErrorLocations.length;
    }
}
