package com.google.android.material.animation;

import android.animation.TypeEvaluator;
import android.graphics.Matrix;
import android.support.v4.media.session.a;
import androidx.annotation.NonNull;

public class MatrixEvaluator implements TypeEvaluator<Matrix> {
    private final float[] tempEndValues = new float[9];
    private final Matrix tempMatrix = new Matrix();
    private final float[] tempStartValues = new float[9];

    @NonNull
    public Matrix evaluate(float f2, @NonNull Matrix matrix, @NonNull Matrix matrix2) {
        matrix.getValues(this.tempStartValues);
        matrix2.getValues(this.tempEndValues);
        for (int i3 = 0; i3 < 9; i3++) {
            float[] fArr = this.tempEndValues;
            float f3 = fArr[i3];
            float f4 = this.tempStartValues[i3];
            fArr[i3] = a.b(f3, f4, f2, f4);
        }
        this.tempMatrix.setValues(this.tempEndValues);
        return this.tempMatrix;
    }
}
