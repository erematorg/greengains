package com.airbnb.lottie.model;

import android.graphics.PointF;
import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.compose.animation.core.a;

@RestrictTo({RestrictTo.Scope.LIBRARY})
public class DocumentData {
    public float baselineShift;
    @Nullable
    public PointF boxPosition;
    @Nullable
    public PointF boxSize;
    @ColorInt
    public int color;
    public String fontName;
    public Justification justification;
    public float lineHeight;
    public float size;
    @ColorInt
    public int strokeColor;
    public boolean strokeOverFill;
    public float strokeWidth;
    public String text;
    public int tracking;

    public enum Justification {
        LEFT_ALIGN,
        RIGHT_ALIGN,
        CENTER
    }

    public DocumentData(String str, String str2, float f2, Justification justification2, int i3, float f3, float f4, @ColorInt int i4, @ColorInt int i5, float f5, boolean z2, PointF pointF, PointF pointF2) {
        set(str, str2, f2, justification2, i3, f3, f4, i4, i5, f5, z2, pointF, pointF2);
    }

    public int hashCode() {
        String str = this.fontName;
        int ordinal = ((this.justification.ordinal() + (((int) (((float) a.i(str, this.text.hashCode() * 31, 31)) + this.size)) * 31)) * 31) + this.tracking;
        long floatToRawIntBits = (long) Float.floatToRawIntBits(this.lineHeight);
        return (((ordinal * 31) + ((int) (floatToRawIntBits ^ (floatToRawIntBits >>> 32)))) * 31) + this.color;
    }

    public void set(String str, String str2, float f2, Justification justification2, int i3, float f3, float f4, @ColorInt int i4, @ColorInt int i5, float f5, boolean z2, PointF pointF, PointF pointF2) {
        this.text = str;
        this.fontName = str2;
        this.size = f2;
        this.justification = justification2;
        this.tracking = i3;
        this.lineHeight = f3;
        this.baselineShift = f4;
        this.color = i4;
        this.strokeColor = i5;
        this.strokeWidth = f5;
        this.strokeOverFill = z2;
        this.boxPosition = pointF;
        this.boxSize = pointF2;
    }

    public DocumentData() {
    }
}
