package com.google.android.material.internal;

import android.animation.TypeEvaluator;
import android.graphics.Rect;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class RectEvaluator implements TypeEvaluator<Rect> {
    private final Rect rect;

    public RectEvaluator(@NonNull Rect rect2) {
        this.rect = rect2;
    }

    public Rect evaluate(float f2, @NonNull Rect rect2, @NonNull Rect rect3) {
        int i3 = rect2.left;
        int i4 = i3 + ((int) (((float) (rect3.left - i3)) * f2));
        int i5 = rect2.top;
        int i6 = i5 + ((int) (((float) (rect3.top - i5)) * f2));
        int i7 = rect2.right;
        int i8 = rect2.bottom;
        int i9 = i8 + ((int) (((float) (rect3.bottom - i8)) * f2));
        this.rect.set(i4, i6, i7 + ((int) (((float) (rect3.right - i7)) * f2)), i9);
        return this.rect;
    }
}
