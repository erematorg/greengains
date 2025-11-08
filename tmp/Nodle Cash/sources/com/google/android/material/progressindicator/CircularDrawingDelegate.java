package com.google.android.material.progressindicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Px;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.math.MathUtils;
import com.google.android.material.progressindicator.DrawingDelegate;

final class CircularDrawingDelegate extends DrawingDelegate<CircularProgressIndicatorSpec> {
    private static final float ROUND_CAP_RAMP_DOWN_THRESHHOLD = 0.01f;
    private float adjustedRadius;
    private float displayedCornerRadius;
    private float displayedTrackThickness;
    @FloatRange(from = 0.0d, to = 1.0d)
    private float totalTrackLengthFraction;
    private boolean useStrokeCap;

    public CircularDrawingDelegate(@NonNull CircularProgressIndicatorSpec circularProgressIndicatorSpec) {
        super(circularProgressIndicatorSpec);
    }

    private void drawArc(@NonNull Canvas canvas, @NonNull Paint paint, float f2, float f3, @ColorInt int i3, @Px int i4, @Px int i5) {
        Paint paint2 = paint;
        float f4 = f3 >= f2 ? f3 - f2 : (f3 + 1.0f) - f2;
        float f5 = f2 % 1.0f;
        if (this.totalTrackLengthFraction < 1.0f) {
            float f6 = f5 + f4;
            if (f6 > 1.0f) {
                Canvas canvas2 = canvas;
                Paint paint3 = paint;
                int i6 = i3;
                drawArc(canvas2, paint3, f5, 1.0f, i6, i4, 0);
                drawArc(canvas2, paint3, 1.0f, f6, i6, 0, i5);
                return;
            }
        }
        float degrees = (float) Math.toDegrees((double) (this.displayedCornerRadius / this.adjustedRadius));
        if (f5 == 0.0f && f4 >= 0.99f) {
            f4 += (((degrees * 2.0f) / 360.0f) * (f4 - 0.99f)) / 0.01f;
        }
        float lerp = MathUtils.lerp(1.0f - this.totalTrackLengthFraction, 1.0f, f5);
        float lerp2 = MathUtils.lerp(0.0f, this.totalTrackLengthFraction, f4);
        float degrees2 = (float) Math.toDegrees((double) (((float) i4) / this.adjustedRadius));
        float degrees3 = ((lerp2 * 360.0f) - degrees2) - ((float) Math.toDegrees((double) (((float) i5) / this.adjustedRadius)));
        float f7 = (lerp * 360.0f) + degrees2;
        if (degrees3 > 0.0f) {
            paint2.setAntiAlias(true);
            paint2.setColor(i3);
            paint2.setStrokeWidth(this.displayedTrackThickness);
            float f8 = degrees * 2.0f;
            if (degrees3 < f8) {
                float f9 = degrees3 / f8;
                paint2.setStyle(Paint.Style.FILL);
                drawRoundedBlock(canvas, paint, (degrees * f9) + f7, this.displayedCornerRadius * 2.0f, this.displayedTrackThickness, f9);
                return;
            }
            float f10 = this.adjustedRadius;
            RectF rectF = new RectF(-f10, -f10, f10, f10);
            paint2.setStyle(Paint.Style.STROKE);
            paint2.setStrokeCap(this.useStrokeCap ? Paint.Cap.ROUND : Paint.Cap.BUTT);
            float f11 = f7 + degrees;
            canvas.drawArc(rectF, f11, degrees3 - f8, false, paint);
            if (!this.useStrokeCap && this.displayedCornerRadius > 0.0f) {
                paint2.setStyle(Paint.Style.FILL);
                Canvas canvas3 = canvas;
                Paint paint4 = paint;
                drawRoundedBlock(canvas3, paint4, f11, this.displayedCornerRadius * 2.0f, this.displayedTrackThickness);
                drawRoundedBlock(canvas3, paint4, (f7 + degrees3) - degrees, this.displayedCornerRadius * 2.0f, this.displayedTrackThickness);
            }
        }
    }

    private void drawRoundedBlock(@NonNull Canvas canvas, @NonNull Paint paint, float f2, float f3, float f4) {
        drawRoundedBlock(canvas, paint, f2, f3, f4, 1.0f);
    }

    private int getSize() {
        S s3 = this.spec;
        return (((CircularProgressIndicatorSpec) s3).indicatorInset * 2) + ((CircularProgressIndicatorSpec) s3).indicatorSize;
    }

    public void adjustCanvas(@NonNull Canvas canvas, @NonNull Rect rect, @FloatRange(from = 0.0d, to = 1.0d) float f2, boolean z2, boolean z3) {
        float width = ((float) rect.width()) / ((float) getPreferredWidth());
        float height = ((float) rect.height()) / ((float) getPreferredHeight());
        S s3 = this.spec;
        float f3 = (((float) ((CircularProgressIndicatorSpec) s3).indicatorSize) / 2.0f) + ((float) ((CircularProgressIndicatorSpec) s3).indicatorInset);
        canvas.translate((f3 * width) + ((float) rect.left), (f3 * height) + ((float) rect.top));
        canvas.rotate(-90.0f);
        canvas.scale(width, height);
        if (((CircularProgressIndicatorSpec) this.spec).indicatorDirection != 0) {
            canvas.scale(1.0f, -1.0f);
        }
        float f4 = -f3;
        canvas.clipRect(f4, f4, f3, f3);
        S s4 = this.spec;
        this.useStrokeCap = ((CircularProgressIndicatorSpec) s4).trackThickness / 2 <= ((CircularProgressIndicatorSpec) s4).trackCornerRadius;
        this.displayedTrackThickness = ((float) ((CircularProgressIndicatorSpec) s4).trackThickness) * f2;
        this.displayedCornerRadius = ((float) Math.min(((CircularProgressIndicatorSpec) s4).trackThickness / 2, ((CircularProgressIndicatorSpec) s4).trackCornerRadius)) * f2;
        S s5 = this.spec;
        float f5 = ((float) (((CircularProgressIndicatorSpec) s5).indicatorSize - ((CircularProgressIndicatorSpec) s5).trackThickness)) / 2.0f;
        this.adjustedRadius = f5;
        if (z2 || z3) {
            if ((z2 && ((CircularProgressIndicatorSpec) s5).showAnimationBehavior == 2) || (z3 && ((CircularProgressIndicatorSpec) s5).hideAnimationBehavior == 1)) {
                this.adjustedRadius = (((1.0f - f2) * ((float) ((CircularProgressIndicatorSpec) s5).trackThickness)) / 2.0f) + f5;
            } else if ((z2 && ((CircularProgressIndicatorSpec) s5).showAnimationBehavior == 1) || (z3 && ((CircularProgressIndicatorSpec) s5).hideAnimationBehavior == 2)) {
                this.adjustedRadius = f5 - (((1.0f - f2) * ((float) ((CircularProgressIndicatorSpec) s5).trackThickness)) / 2.0f);
            }
        }
        if (!z3 || ((CircularProgressIndicatorSpec) s5).hideAnimationBehavior != 3) {
            this.totalTrackLengthFraction = 1.0f;
        } else {
            this.totalTrackLengthFraction = f2;
        }
    }

    public void drawStopIndicator(@NonNull Canvas canvas, @NonNull Paint paint, @ColorInt int i3, @IntRange(from = 0, to = 255) int i4) {
    }

    public void fillIndicator(@NonNull Canvas canvas, @NonNull Paint paint, @NonNull DrawingDelegate.ActiveIndicator activeIndicator, @IntRange(from = 0, to = 255) int i3) {
        int compositeARGBWithAlpha = MaterialColors.compositeARGBWithAlpha(activeIndicator.color, i3);
        float f2 = activeIndicator.startFraction;
        float f3 = activeIndicator.endFraction;
        int i4 = activeIndicator.gapSize;
        drawArc(canvas, paint, f2, f3, compositeARGBWithAlpha, i4, i4);
    }

    public void fillTrack(@NonNull Canvas canvas, @NonNull Paint paint, float f2, float f3, @ColorInt int i3, @IntRange(from = 0, to = 255) int i4, int i5) {
        drawArc(canvas, paint, f2, f3, MaterialColors.compositeARGBWithAlpha(i3, i4), i5, i5);
    }

    public int getPreferredHeight() {
        return getSize();
    }

    public int getPreferredWidth() {
        return getSize();
    }

    private void drawRoundedBlock(@NonNull Canvas canvas, @NonNull Paint paint, float f2, float f3, float f4, float f5) {
        float min = (float) ((int) Math.min(f4, this.displayedTrackThickness));
        float f6 = f3 / 2.0f;
        float min2 = Math.min(f6, (this.displayedCornerRadius * min) / this.displayedTrackThickness);
        RectF rectF = new RectF((-min) / 2.0f, (-f3) / 2.0f, min / 2.0f, f6);
        canvas.save();
        double d2 = (double) f2;
        canvas.translate((float) (Math.cos(Math.toRadians(d2)) * ((double) this.adjustedRadius)), (float) (Math.sin(Math.toRadians(d2)) * ((double) this.adjustedRadius)));
        canvas.rotate(f2);
        canvas.scale(f5, f5);
        canvas.drawRoundRect(rectF, min2, min2, paint);
        canvas.restore();
    }
}
