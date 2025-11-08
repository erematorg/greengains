package com.google.android.material.internal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class ClippableRoundedCornerLayout extends FrameLayout {
    private float cornerRadius;
    @Nullable
    private Path path;

    public ClippableRoundedCornerLayout(@NonNull Context context) {
        super(context);
    }

    public void dispatchDraw(Canvas canvas) {
        if (this.path == null) {
            super.dispatchDraw(canvas);
            return;
        }
        int save = canvas.save();
        canvas.clipPath(this.path);
        super.dispatchDraw(canvas);
        canvas.restoreToCount(save);
    }

    public float getCornerRadius() {
        return this.cornerRadius;
    }

    public void resetClipBoundsAndCornerRadius() {
        this.path = null;
        this.cornerRadius = 0.0f;
        invalidate();
    }

    public void updateClipBoundsAndCornerRadius(@NonNull Rect rect, float f2) {
        updateClipBoundsAndCornerRadius((float) rect.left, (float) rect.top, (float) rect.right, (float) rect.bottom, f2);
    }

    public void updateCornerRadius(float f2) {
        updateClipBoundsAndCornerRadius((float) getLeft(), (float) getTop(), (float) getRight(), (float) getBottom(), f2);
    }

    public ClippableRoundedCornerLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void updateClipBoundsAndCornerRadius(float f2, float f3, float f4, float f5, float f6) {
        updateClipBoundsAndCornerRadius(new RectF(f2, f3, f4, f5), f6);
    }

    public ClippableRoundedCornerLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i3) {
        super(context, attributeSet, i3);
    }

    public void updateClipBoundsAndCornerRadius(@NonNull RectF rectF, float f2) {
        if (this.path == null) {
            this.path = new Path();
        }
        this.cornerRadius = f2;
        this.path.reset();
        this.path.addRoundRect(rectF, f2, f2, Path.Direction.CW);
        this.path.close();
        invalidate();
    }
}
