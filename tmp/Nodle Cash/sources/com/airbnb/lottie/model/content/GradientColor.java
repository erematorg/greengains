package com.airbnb.lottie.model.content;

import A.a;
import com.airbnb.lottie.utils.GammaEvaluator;
import com.airbnb.lottie.utils.MiscUtils;
import java.util.Arrays;

public class GradientColor {
    private final int[] colors;
    private final float[] positions;

    public GradientColor(float[] fArr, int[] iArr) {
        this.positions = fArr;
        this.colors = iArr;
    }

    private int getColorForPosition(float f2) {
        int binarySearch = Arrays.binarySearch(this.positions, f2);
        if (binarySearch >= 0) {
            return this.colors[binarySearch];
        }
        int i3 = -(binarySearch + 1);
        if (i3 == 0) {
            return this.colors[0];
        }
        int[] iArr = this.colors;
        if (i3 == iArr.length - 1) {
            return iArr[iArr.length - 1];
        }
        float[] fArr = this.positions;
        int i4 = i3 - 1;
        float f3 = fArr[i4];
        return GammaEvaluator.evaluate((f2 - f3) / (fArr[i3] - f3), iArr[i4], iArr[i3]);
    }

    public GradientColor copyWithPositions(float[] fArr) {
        int[] iArr = new int[fArr.length];
        for (int i3 = 0; i3 < fArr.length; i3++) {
            iArr[i3] = getColorForPosition(fArr[i3]);
        }
        return new GradientColor(fArr, iArr);
    }

    public int[] getColors() {
        return this.colors;
    }

    public float[] getPositions() {
        return this.positions;
    }

    public int getSize() {
        return this.colors.length;
    }

    public void lerp(GradientColor gradientColor, GradientColor gradientColor2, float f2) {
        int[] iArr;
        if (gradientColor.colors.length == gradientColor2.colors.length) {
            int i3 = 0;
            while (true) {
                iArr = gradientColor.colors;
                if (i3 >= iArr.length) {
                    break;
                }
                this.positions[i3] = MiscUtils.lerp(gradientColor.positions[i3], gradientColor2.positions[i3], f2);
                this.colors[i3] = GammaEvaluator.evaluate(f2, gradientColor.colors[i3], gradientColor2.colors[i3]);
                i3++;
            }
            int length = iArr.length;
            while (true) {
                float[] fArr = this.positions;
                if (length < fArr.length) {
                    int[] iArr2 = gradientColor.colors;
                    fArr[length] = fArr[iArr2.length - 1];
                    int[] iArr3 = this.colors;
                    iArr3[length] = iArr3[iArr2.length - 1];
                    length++;
                } else {
                    return;
                }
            }
        } else {
            StringBuilder sb = new StringBuilder("Cannot interpolate between gradients. Lengths vary (");
            sb.append(gradientColor.colors.length);
            sb.append(" vs ");
            throw new IllegalArgumentException(a.m(sb, ")", gradientColor2.colors.length));
        }
    }
}
