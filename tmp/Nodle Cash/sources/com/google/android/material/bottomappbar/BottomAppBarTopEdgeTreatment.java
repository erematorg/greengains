package com.google.android.material.bottomappbar;

import android.support.v4.media.session.a;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import com.google.android.material.shape.EdgeTreatment;
import com.google.android.material.shape.ShapePath;

public class BottomAppBarTopEdgeTreatment extends EdgeTreatment implements Cloneable {
    private static final int ANGLE_LEFT = 180;
    private static final int ANGLE_UP = 270;
    private static final int ARC_HALF = 180;
    private static final int ARC_QUARTER = 90;
    private static final float ROUNDED_CORNER_FAB_OFFSET = 1.75f;
    private float cradleVerticalOffset;
    private float fabCornerSize = -1.0f;
    private float fabDiameter;
    private float fabMargin;
    private float horizontalOffset;
    private float roundedCornerRadius;

    public BottomAppBarTopEdgeTreatment(float f2, float f3, float f4) {
        this.fabMargin = f2;
        this.roundedCornerRadius = f3;
        setCradleVerticalOffset(f4);
        this.horizontalOffset = 0.0f;
    }

    public float getCradleVerticalOffset() {
        return this.cradleVerticalOffset;
    }

    public void getEdgePath(float f2, float f3, float f4, @NonNull ShapePath shapePath) {
        float f5;
        float f6;
        float f7 = f2;
        float f8 = f4;
        ShapePath shapePath2 = shapePath;
        float f9 = this.fabDiameter;
        if (f9 == 0.0f) {
            shapePath2.lineTo(f7, 0.0f);
            return;
        }
        float f10 = ((this.fabMargin * 2.0f) + f9) / 2.0f;
        float f11 = f8 * this.roundedCornerRadius;
        float f12 = f3 + this.horizontalOffset;
        float b3 = a.b(1.0f, f8, f10, this.cradleVerticalOffset * f8);
        if (b3 / f10 >= 1.0f) {
            shapePath2.lineTo(f7, 0.0f);
            return;
        }
        float f13 = this.fabCornerSize;
        float f14 = f13 * f8;
        boolean z2 = f13 == -1.0f || Math.abs((f13 * 2.0f) - f9) < 0.1f;
        if (!z2) {
            f6 = ROUNDED_CORNER_FAB_OFFSET;
            f5 = 0.0f;
        } else {
            f5 = b3;
            f6 = 0.0f;
        }
        float f15 = f10 + f11;
        float f16 = f5 + f11;
        float sqrt = (float) Math.sqrt((double) ((f15 * f15) - (f16 * f16)));
        float f17 = f12 - sqrt;
        float f18 = f12 + sqrt;
        float degrees = (float) Math.toDegrees(Math.atan((double) (sqrt / f16)));
        float f19 = (90.0f - degrees) + f6;
        shapePath2.lineTo(f17, 0.0f);
        float f20 = f11 * 2.0f;
        float f21 = degrees;
        shapePath.addArc(f17 - f11, 0.0f, f17 + f11, f20, 270.0f, degrees);
        if (z2) {
            shapePath.addArc(f12 - f10, (-f10) - f5, f12 + f10, f10 - f5, 180.0f - f19, (f19 * 2.0f) - 180.0f);
        } else {
            float f22 = this.fabMargin;
            float f23 = f14 * 2.0f;
            float f24 = f12 - f10;
            shapePath.addArc(f24, -(f14 + f22), f24 + f22 + f23, f22 + f14, 180.0f - f19, ((f19 * 2.0f) - 180.0f) / 2.0f);
            float f25 = f12 + f10;
            float f26 = this.fabMargin;
            shapePath2.lineTo(f25 - ((f26 / 2.0f) + f14), f26 + f14);
            float f27 = this.fabMargin;
            shapePath.addArc(f25 - (f23 + f27), -(f14 + f27), f25, f27 + f14, 90.0f, f19 - 0.049804688f);
        }
        shapePath.addArc(f18 - f11, 0.0f, f18 + f11, f20, 270.0f - f21, f21);
        shapePath2.lineTo(f7, 0.0f);
    }

    public float getFabCornerRadius() {
        return this.fabCornerSize;
    }

    public float getFabCradleMargin() {
        return this.fabMargin;
    }

    public float getFabCradleRoundedCornerRadius() {
        return this.roundedCornerRadius;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public float getFabDiameter() {
        return this.fabDiameter;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public float getHorizontalOffset() {
        return this.horizontalOffset;
    }

    public void setCradleVerticalOffset(@FloatRange(from = 0.0d) float f2) {
        if (f2 >= 0.0f) {
            this.cradleVerticalOffset = f2;
            return;
        }
        throw new IllegalArgumentException("cradleVerticalOffset must be positive.");
    }

    public void setFabCornerSize(float f2) {
        this.fabCornerSize = f2;
    }

    public void setFabCradleMargin(float f2) {
        this.fabMargin = f2;
    }

    public void setFabCradleRoundedCornerRadius(float f2) {
        this.roundedCornerRadius = f2;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setFabDiameter(float f2) {
        this.fabDiameter = f2;
    }

    public void setHorizontalOffset(float f2) {
        this.horizontalOffset = f2;
    }
}
