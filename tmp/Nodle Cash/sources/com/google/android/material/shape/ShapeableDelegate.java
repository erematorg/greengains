package com.google.android.material.shape;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.google.android.material.canvas.CanvasCompat;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public abstract class ShapeableDelegate {
    boolean forceCompatClippingEnabled = false;
    RectF maskBounds = new RectF();
    boolean offsetZeroCornerEdgeBoundsEnabled = false;
    @Nullable
    ShapeAppearanceModel shapeAppearanceModel;
    final Path shapePath = new Path();

    @NonNull
    public static ShapeableDelegate create(@NonNull View view) {
        return Build.VERSION.SDK_INT >= 33 ? new ShapeableDelegateV33(view) : new ShapeableDelegateV22(view);
    }

    private boolean isMaskBoundsValid() {
        RectF rectF = this.maskBounds;
        return rectF.left <= rectF.right && rectF.top <= rectF.bottom;
    }

    private void updateShapePath() {
        if (isMaskBoundsValid() && this.shapeAppearanceModel != null) {
            ShapeAppearancePathProvider.getInstance().calculatePath(this.shapeAppearanceModel, 1.0f, this.maskBounds, this.shapePath);
        }
    }

    public abstract void invalidateClippingMethod(@NonNull View view);

    public boolean isForceCompatClippingEnabled() {
        return this.forceCompatClippingEnabled;
    }

    public void maybeClip(@NonNull Canvas canvas, @NonNull CanvasCompat.CanvasOperation canvasOperation) {
        if (!shouldUseCompatClipping() || this.shapePath.isEmpty()) {
            canvasOperation.run(canvas);
            return;
        }
        canvas.save();
        canvas.clipPath(this.shapePath);
        canvasOperation.run(canvas);
        canvas.restore();
    }

    public void onMaskChanged(@NonNull View view, @NonNull RectF rectF) {
        this.maskBounds = rectF;
        updateShapePath();
        invalidateClippingMethod(view);
    }

    public void onShapeAppearanceChanged(@NonNull View view, @NonNull ShapeAppearanceModel shapeAppearanceModel2) {
        this.shapeAppearanceModel = shapeAppearanceModel2;
        updateShapePath();
        invalidateClippingMethod(view);
    }

    public void setForceCompatClippingEnabled(@NonNull View view, boolean z2) {
        if (z2 != this.forceCompatClippingEnabled) {
            this.forceCompatClippingEnabled = z2;
            invalidateClippingMethod(view);
        }
    }

    public void setOffsetZeroCornerEdgeBoundsEnabled(@NonNull View view, boolean z2) {
        this.offsetZeroCornerEdgeBoundsEnabled = z2;
        invalidateClippingMethod(view);
    }

    public abstract boolean shouldUseCompatClipping();
}
