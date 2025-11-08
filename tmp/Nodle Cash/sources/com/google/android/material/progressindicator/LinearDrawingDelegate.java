package com.google.android.material.progressindicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.core.math.MathUtils;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.progressindicator.DrawingDelegate;

final class LinearDrawingDelegate extends DrawingDelegate<LinearProgressIndicatorSpec> {
    private float displayedCornerRadius;
    private float displayedTrackThickness;
    @FloatRange(from = 0.0d, to = 1.0d)
    private float totalTrackLengthFraction;
    private float trackLength = 300.0f;
    private boolean useStrokeCap;

    public LinearDrawingDelegate(@NonNull LinearProgressIndicatorSpec linearProgressIndicatorSpec) {
        super(linearProgressIndicatorSpec);
    }

    private void drawLine(@NonNull Canvas canvas, @NonNull Paint paint, float f2, float f3, @ColorInt int i3, @Px int i4, @Px int i5) {
        Paint paint2 = paint;
        float clamp = MathUtils.clamp(f2, 0.0f, 1.0f);
        float clamp2 = MathUtils.clamp(f3, 0.0f, 1.0f);
        float lerp = com.google.android.material.math.MathUtils.lerp(1.0f - this.totalTrackLengthFraction, 1.0f, clamp);
        float lerp2 = com.google.android.material.math.MathUtils.lerp(1.0f - this.totalTrackLengthFraction, 1.0f, clamp2);
        float clamp3 = 1.0f - MathUtils.clamp(lerp2, 0.99f, 1.0f);
        float f4 = this.trackLength;
        int clamp4 = (int) ((lerp * f4) + ((float) ((int) ((MathUtils.clamp(lerp, 0.0f, 0.01f) * ((float) i4)) / 0.01f))));
        int i6 = (int) ((lerp2 * f4) - ((float) ((int) ((clamp3 * ((float) i5)) / 0.01f))));
        float f5 = (-f4) / 2.0f;
        if (clamp4 <= i6) {
            float f6 = this.displayedCornerRadius;
            float f7 = ((float) clamp4) + f6;
            float f8 = ((float) i6) - f6;
            float f9 = f6 * 2.0f;
            paint2.setColor(i3);
            paint2.setAntiAlias(true);
            paint2.setStrokeWidth(this.displayedTrackThickness);
            if (f7 >= f8) {
                drawRoundedBlock(canvas, paint, new PointF(f7 + f5, 0.0f), new PointF(f8 + f5, 0.0f), f9, this.displayedTrackThickness);
                return;
            }
            paint2.setStyle(Paint.Style.STROKE);
            paint2.setStrokeCap(this.useStrokeCap ? Paint.Cap.ROUND : Paint.Cap.BUTT);
            float f10 = f7 + f5;
            float f11 = f8 + f5;
            canvas.drawLine(f10, 0.0f, f11, 0.0f, paint);
            if (!this.useStrokeCap && this.displayedCornerRadius > 0.0f) {
                paint2.setStyle(Paint.Style.FILL);
                if (f7 > 0.0f) {
                    drawRoundedBlock(canvas, paint, new PointF(f10, 0.0f), f9, this.displayedTrackThickness);
                }
                if (f8 < this.trackLength) {
                    drawRoundedBlock(canvas, paint, new PointF(f11, 0.0f), f9, this.displayedTrackThickness);
                }
            }
        }
    }

    private void drawRoundedBlock(@NonNull Canvas canvas, @NonNull Paint paint, @NonNull PointF pointF, float f2, float f3) {
        drawRoundedBlock(canvas, paint, pointF, (PointF) null, f2, f3);
    }

    public void adjustCanvas(@NonNull Canvas canvas, @NonNull Rect rect, @FloatRange(from = 0.0d, to = 1.0d) float f2, boolean z2, boolean z3) {
        this.trackLength = (float) rect.width();
        float f3 = (float) ((LinearProgressIndicatorSpec) this.spec).trackThickness;
        canvas.translate((((float) rect.width()) / 2.0f) + ((float) rect.left), Math.max(0.0f, (((float) rect.height()) - f3) / 2.0f) + (((float) rect.height()) / 2.0f) + ((float) rect.top));
        if (((LinearProgressIndicatorSpec) this.spec).drawHorizontallyInverse) {
            canvas.scale(-1.0f, 1.0f);
        }
        float f4 = this.trackLength / 2.0f;
        float f5 = f3 / 2.0f;
        canvas.clipRect(-f4, -f5, f4, f5);
        S s3 = this.spec;
        this.useStrokeCap = ((LinearProgressIndicatorSpec) s3).trackThickness / 2 == ((LinearProgressIndicatorSpec) s3).trackCornerRadius;
        this.displayedTrackThickness = ((float) ((LinearProgressIndicatorSpec) s3).trackThickness) * f2;
        this.displayedCornerRadius = ((float) Math.min(((LinearProgressIndicatorSpec) s3).trackThickness / 2, ((LinearProgressIndicatorSpec) s3).trackCornerRadius)) * f2;
        if (z2 || z3) {
            if ((z2 && ((LinearProgressIndicatorSpec) this.spec).showAnimationBehavior == 2) || (z3 && ((LinearProgressIndicatorSpec) this.spec).hideAnimationBehavior == 1)) {
                canvas.scale(1.0f, -1.0f);
            }
            if (z2 || (z3 && ((LinearProgressIndicatorSpec) this.spec).hideAnimationBehavior != 3)) {
                canvas.translate(0.0f, ((1.0f - f2) * ((float) ((LinearProgressIndicatorSpec) this.spec).trackThickness)) / 2.0f);
            }
        }
        if (!z3 || ((LinearProgressIndicatorSpec) this.spec).hideAnimationBehavior != 3) {
            this.totalTrackLengthFraction = 1.0f;
        } else {
            this.totalTrackLengthFraction = f2;
        }
    }

    public void drawStopIndicator(@NonNull Canvas canvas, @NonNull Paint paint, @ColorInt int i3, @IntRange(from = 0, to = 255) int i4) {
        int compositeARGBWithAlpha = MaterialColors.compositeARGBWithAlpha(i3, i4);
        if (((LinearProgressIndicatorSpec) this.spec).trackStopIndicatorSize > 0 && compositeARGBWithAlpha != 0) {
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(compositeARGBWithAlpha);
            PointF pointF = new PointF((this.trackLength / 2.0f) - (this.displayedTrackThickness / 2.0f), 0.0f);
            S s3 = this.spec;
            drawRoundedBlock(canvas, paint, pointF, (float) ((LinearProgressIndicatorSpec) s3).trackStopIndicatorSize, (float) ((LinearProgressIndicatorSpec) s3).trackStopIndicatorSize);
        }
    }

    public void fillIndicator(@NonNull Canvas canvas, @NonNull Paint paint, @NonNull DrawingDelegate.ActiveIndicator activeIndicator, int i3) {
        int compositeARGBWithAlpha = MaterialColors.compositeARGBWithAlpha(activeIndicator.color, i3);
        float f2 = activeIndicator.startFraction;
        float f3 = activeIndicator.endFraction;
        int i4 = activeIndicator.gapSize;
        drawLine(canvas, paint, f2, f3, compositeARGBWithAlpha, i4, i4);
    }

    public void fillTrack(@NonNull Canvas canvas, @NonNull Paint paint, float f2, float f3, int i3, int i4, @Px int i5) {
        drawLine(canvas, paint, f2, f3, MaterialColors.compositeARGBWithAlpha(i3, i4), i5, i5);
    }

    public int getPreferredHeight() {
        return ((LinearProgressIndicatorSpec) this.spec).trackThickness;
    }

    public int getPreferredWidth() {
        return -1;
    }

    private void drawRoundedBlock(@NonNull Canvas canvas, @NonNull Paint paint, @NonNull PointF pointF, @Nullable PointF pointF2, float f2, float f3) {
        float min = Math.min(f3, this.displayedTrackThickness);
        float f4 = f2 / 2.0f;
        float min2 = Math.min(f4, (this.displayedCornerRadius * min) / this.displayedTrackThickness);
        RectF rectF = new RectF((-f2) / 2.0f, (-min) / 2.0f, f4, min / 2.0f);
        paint.setStyle(Paint.Style.FILL);
        canvas.save();
        if (pointF2 != null) {
            canvas.translate(pointF2.x, pointF2.y);
            Path path = new Path();
            path.addRoundRect(rectF, min2, min2, Path.Direction.CCW);
            canvas.clipPath(path);
            canvas.translate(-pointF2.x, -pointF2.y);
        }
        canvas.translate(pointF.x, pointF.y);
        canvas.drawRoundRect(rectF, min2, min2, paint);
        canvas.restore();
    }
}
