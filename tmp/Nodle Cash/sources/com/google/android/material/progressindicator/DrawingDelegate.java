package com.google.android.material.progressindicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Px;
import com.google.android.material.progressindicator.BaseProgressIndicatorSpec;

abstract class DrawingDelegate<S extends BaseProgressIndicatorSpec> {
    S spec;

    public static class ActiveIndicator {
        @ColorInt
        int color;
        @FloatRange(from = 0.0d, to = 1.0d)
        float endFraction;
        @Px
        int gapSize;
        @FloatRange(from = 0.0d, to = 1.0d)
        float startFraction;
    }

    public DrawingDelegate(S s3) {
        this.spec = s3;
    }

    public abstract void adjustCanvas(@NonNull Canvas canvas, @NonNull Rect rect, @FloatRange(from = -1.0d, to = 1.0d) float f2, boolean z2, boolean z3);

    public abstract void drawStopIndicator(@NonNull Canvas canvas, @NonNull Paint paint, @ColorInt int i3, @IntRange(from = 0, to = 255) int i4);

    public abstract void fillIndicator(@NonNull Canvas canvas, @NonNull Paint paint, @NonNull ActiveIndicator activeIndicator, @IntRange(from = 0, to = 255) int i3);

    public abstract void fillTrack(@NonNull Canvas canvas, @NonNull Paint paint, @FloatRange(from = 0.0d, to = 1.0d) float f2, @FloatRange(from = 0.0d, to = 1.0d) float f3, @ColorInt int i3, @IntRange(from = 0, to = 255) int i4, @Px int i5);

    public abstract int getPreferredHeight();

    public abstract int getPreferredWidth();

    public void validateSpecAndAdjustCanvas(@NonNull Canvas canvas, @NonNull Rect rect, @FloatRange(from = 0.0d, to = 1.0d) float f2, boolean z2, boolean z3) {
        this.spec.validateSpec();
        adjustCanvas(canvas, rect, f2, z2, z3);
    }
}
