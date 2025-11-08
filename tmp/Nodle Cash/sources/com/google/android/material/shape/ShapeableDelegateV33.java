package com.google.android.material.shape;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

@RequiresApi(33)
class ShapeableDelegateV33 extends ShapeableDelegate {
    public ShapeableDelegateV33(@NonNull View view) {
        initMaskOutlineProvider(view);
    }

    @DoNotInline
    private void initMaskOutlineProvider(View view) {
        view.setOutlineProvider(new ViewOutlineProvider() {
            public void getOutline(View view, Outline outline) {
                if (!ShapeableDelegateV33.this.shapePath.isEmpty()) {
                    outline.setPath(ShapeableDelegateV33.this.shapePath);
                }
            }
        });
    }

    public void invalidateClippingMethod(@NonNull View view) {
        view.setClipToOutline(!shouldUseCompatClipping());
        if (shouldUseCompatClipping()) {
            view.invalidate();
        } else {
            view.invalidateOutline();
        }
    }

    public boolean shouldUseCompatClipping() {
        return this.forceCompatClippingEnabled;
    }
}
