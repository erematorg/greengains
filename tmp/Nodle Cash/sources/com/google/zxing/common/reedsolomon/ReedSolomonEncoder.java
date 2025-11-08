package com.google.zxing.common.reedsolomon;

import android.support.v4.media.session.a;
import java.util.ArrayList;
import java.util.List;

public final class ReedSolomonEncoder {
    private final List<GenericGFPoly> cachedGenerators;
    private final GenericGF field;

    public ReedSolomonEncoder(GenericGF genericGF) {
        this.field = genericGF;
        ArrayList arrayList = new ArrayList();
        this.cachedGenerators = arrayList;
        arrayList.add(new GenericGFPoly(genericGF, new int[]{1}));
    }

    private GenericGFPoly buildGenerator(int i3) {
        if (i3 >= this.cachedGenerators.size()) {
            GenericGFPoly genericGFPoly = (GenericGFPoly) a.h(this.cachedGenerators, 1);
            for (int size = this.cachedGenerators.size(); size <= i3; size++) {
                GenericGF genericGF = this.field;
                genericGFPoly = genericGFPoly.multiply(new GenericGFPoly(genericGF, new int[]{1, genericGF.exp(genericGF.getGeneratorBase() + (size - 1))}));
                this.cachedGenerators.add(genericGFPoly);
            }
        }
        return this.cachedGenerators.get(i3);
    }

    public void encode(int[] iArr, int i3) {
        if (i3 != 0) {
            int length = iArr.length - i3;
            if (length > 0) {
                GenericGFPoly buildGenerator = buildGenerator(i3);
                int[] iArr2 = new int[length];
                System.arraycopy(iArr, 0, iArr2, 0, length);
                int[] coefficients = new GenericGFPoly(this.field, iArr2).multiplyByMonomial(i3, 1).divide(buildGenerator)[1].getCoefficients();
                int length2 = i3 - coefficients.length;
                for (int i4 = 0; i4 < length2; i4++) {
                    iArr[length + i4] = 0;
                }
                System.arraycopy(coefficients, 0, iArr, length + length2, coefficients.length);
                return;
            }
            throw new IllegalArgumentException("No data bytes provided");
        }
        throw new IllegalArgumentException("No error correction bytes");
    }
}
