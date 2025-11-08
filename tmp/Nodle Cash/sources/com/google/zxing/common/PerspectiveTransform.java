package com.google.zxing.common;

import androidx.compose.animation.core.a;

public final class PerspectiveTransform {
    private final float a11;
    private final float a12;
    private final float a13;
    private final float a21;
    private final float a22;
    private final float a23;
    private final float a31;
    private final float a32;
    private final float a33;

    private PerspectiveTransform(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10) {
        this.a11 = f2;
        this.a12 = f5;
        this.a13 = f8;
        this.a21 = f3;
        this.a22 = f6;
        this.a23 = f9;
        this.a31 = f4;
        this.a32 = f7;
        this.a33 = f10;
    }

    public static PerspectiveTransform quadrilateralToQuadrilateral(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16, float f17) {
        return squareToQuadrilateral(f10, f11, f12, f13, f14, f15, f16, f17).times(quadrilateralToSquare(f2, f3, f4, f5, f6, f7, f8, f9));
    }

    public static PerspectiveTransform quadrilateralToSquare(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        return squareToQuadrilateral(f2, f3, f4, f5, f6, f7, f8, f9).buildAdjoint();
    }

    public static PerspectiveTransform squareToQuadrilateral(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        float f10 = ((f2 - f4) + f6) - f8;
        float f11 = ((f3 - f5) + f7) - f9;
        if (f10 == 0.0f && f11 == 0.0f) {
            return new PerspectiveTransform(f4 - f2, f6 - f4, f2, f5 - f3, f7 - f5, f3, 0.0f, 0.0f, 1.0f);
        }
        float f12 = f4 - f6;
        float f13 = f8 - f6;
        float f14 = f5 - f7;
        float f15 = f9 - f7;
        float f16 = (f12 * f15) - (f13 * f14);
        float a2 = a.a(f13, f11, f15 * f10, f16);
        float a3 = a.a(f10, f14, f12 * f11, f16);
        return new PerspectiveTransform((a2 * f4) + (f4 - f2), (a3 * f8) + (f8 - f2), f2, (a2 * f5) + (f5 - f3), (a3 * f9) + (f9 - f3), f3, a2, a3, 1.0f);
    }

    public PerspectiveTransform buildAdjoint() {
        float f2 = this.a22;
        float f3 = this.a33;
        float f4 = this.a23;
        float f5 = this.a32;
        float f6 = (f2 * f3) - (f4 * f5);
        float f7 = this.a31;
        float f8 = this.a21;
        float f9 = (f4 * f7) - (f8 * f3);
        float f10 = (f8 * f5) - (f2 * f7);
        float f11 = this.a13;
        float f12 = this.a12;
        float f13 = (f11 * f5) - (f12 * f3);
        float f14 = this.a11;
        return new PerspectiveTransform(f6, f9, f10, f13, (f3 * f14) - (f11 * f7), (f7 * f12) - (f5 * f14), (f12 * f4) - (f11 * f2), (f11 * f8) - (f4 * f14), (f14 * f2) - (f12 * f8));
    }

    public PerspectiveTransform times(PerspectiveTransform perspectiveTransform) {
        PerspectiveTransform perspectiveTransform2 = perspectiveTransform;
        float f2 = this.a11;
        float f3 = perspectiveTransform2.a11;
        float f4 = this.a21;
        float f5 = perspectiveTransform2.a12;
        float f6 = this.a31;
        float f7 = perspectiveTransform2.a13;
        float f8 = (f4 * f5) + (f2 * f3) + (f6 * f7);
        float f9 = perspectiveTransform2.a21;
        float f10 = perspectiveTransform2.a22;
        float f11 = perspectiveTransform2.a23;
        float f12 = (f4 * f10) + (f2 * f9) + (f6 * f11);
        float f13 = perspectiveTransform2.a31;
        float f14 = perspectiveTransform2.a32;
        float f15 = perspectiveTransform2.a33;
        float f16 = f6 * f15;
        float f17 = f16 + (f4 * f14) + (f2 * f13);
        float f18 = this.a12;
        float f19 = f17;
        float f20 = this.a22;
        float f21 = this.a32;
        float f22 = (f21 * f7) + (f20 * f5) + (f18 * f3);
        float f23 = (f21 * f11) + (f20 * f10) + (f18 * f9);
        float f24 = f21 * f15;
        float f25 = f24 + (f20 * f14) + (f18 * f13);
        float f26 = this.a13;
        float f27 = this.a23;
        float f28 = this.a33;
        float f29 = f7 * f28;
        float f30 = f11 * f28;
        float f31 = f28 * f15;
        return new PerspectiveTransform(f8, f12, f19, f22, f23, f25, f29 + (f5 * f27) + (f3 * f26), f30 + (f10 * f27) + (f9 * f26), f31 + (f27 * f14) + (f26 * f13));
    }

    public void transformPoints(float[] fArr) {
        float[] fArr2 = fArr;
        float f2 = this.a11;
        float f3 = this.a12;
        float f4 = this.a13;
        float f5 = this.a21;
        float f6 = this.a22;
        float f7 = this.a23;
        float f8 = this.a31;
        float f9 = this.a32;
        float f10 = this.a33;
        int length = fArr2.length - 1;
        for (int i3 = 0; i3 < length; i3 += 2) {
            float f11 = fArr2[i3];
            int i4 = i3 + 1;
            float f12 = fArr2[i4];
            float f13 = (f7 * f12) + (f4 * f11) + f10;
            fArr2[i3] = (((f5 * f12) + (f2 * f11)) + f8) / f13;
            fArr2[i4] = (((f12 * f6) + (f11 * f3)) + f9) / f13;
        }
    }

    public void transformPoints(float[] fArr, float[] fArr2) {
        int length = fArr.length;
        for (int i3 = 0; i3 < length; i3++) {
            float f2 = fArr[i3];
            float f3 = fArr2[i3];
            float f4 = (this.a23 * f3) + (this.a13 * f2) + this.a33;
            fArr[i3] = (((this.a21 * f3) + (this.a11 * f2)) + this.a31) / f4;
            fArr2[i3] = (((this.a22 * f3) + (this.a12 * f2)) + this.a32) / f4;
        }
    }
}
