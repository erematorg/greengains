package com.appsamurai.storyly.util.ui;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.style.LineBackgroundSpan;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import org.jetbrains.annotations.NotNull;

public final class i implements LineBackgroundSpan {

    /* renamed from: a  reason: collision with root package name */
    public final int f6390a;

    /* renamed from: b  reason: collision with root package name */
    public final int f6391b;

    /* renamed from: c  reason: collision with root package name */
    public final int f6392c;
    @NotNull

    /* renamed from: d  reason: collision with root package name */
    public final RectF f6393d = new RectF();

    public i(int i3, int i4, int i5, float f2) {
        this.f6390a = i3;
        this.f6391b = i4;
        this.f6392c = i5;
    }

    @SuppressLint({"RtlHardcoded"})
    public void drawBackground(@NotNull Canvas canvas, @NotNull Paint paint, int i3, int i4, int i5, int i6, int i7, @NotNull CharSequence charSequence, int i8, int i9, int i10) {
        Pair pair;
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        Intrinsics.checkNotNullParameter(paint, "paint");
        Intrinsics.checkNotNullParameter(charSequence, "text");
        int roundToInt = MathKt.roundToInt(paint.measureText(charSequence, i8, i9));
        int color = paint.getColor();
        int i11 = this.f6391b;
        if (i11 != 1) {
            pair = i11 != 3 ? i11 != 5 ? new Pair(Integer.valueOf(i3 - this.f6392c), Integer.valueOf(i3 + roundToInt + this.f6392c)) : new Pair(Integer.valueOf((i4 - roundToInt) - this.f6392c), Integer.valueOf(i4 + this.f6392c)) : new Pair(Integer.valueOf(i3 - this.f6392c), Integer.valueOf(i3 + roundToInt + this.f6392c));
        } else {
            int i12 = i4 - i3;
            pair = new Pair(Integer.valueOf(((i12 - roundToInt) / 2) - this.f6392c), Integer.valueOf(((i12 + roundToInt) / 2) + this.f6392c));
        }
        this.f6393d.set(((float) ((Number) pair.component1()).intValue()) - 0.0f, (float) (i5 - (i10 == 0 ? this.f6392c / 2 : this.f6392c / -2)), ((float) ((Number) pair.component2()).intValue()) + 0.0f, (float) ((this.f6392c / 2) + i7));
        paint.setColor(this.f6390a);
        canvas.drawRoundRect(this.f6393d, 0.0f, 0.0f, paint);
        paint.setColor(color);
    }
}
