package com.appsamurai.storyly.util.ui.slider;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class e extends Drawable implements Drawable.Callback {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Paint f6507a = new Paint(1);
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final Paint f6508b = new Paint(1);
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public final RectF f6509c = new RectF();

    /* renamed from: d  reason: collision with root package name */
    public float f6510d = 0.9f;

    /* renamed from: e  reason: collision with root package name */
    public int f6511e;

    /* renamed from: f  reason: collision with root package name */
    public int f6512f;

    /* renamed from: g  reason: collision with root package name */
    public float f6513g;

    /* renamed from: h  reason: collision with root package name */
    public float f6514h;

    /* renamed from: i  reason: collision with root package name */
    public float f6515i;

    @NotNull
    public final Paint a() {
        return this.f6507a;
    }

    public final void b(int i3) {
        if (this.f6511e != i3) {
            this.f6511e = i3;
            Rect bounds = getBounds();
            Intrinsics.checkNotNullExpressionValue(bounds, "bounds");
            a(bounds);
        }
    }

    public void draw(@NotNull Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        canvas.save();
        canvas.translate((float) getBounds().left, (float) getBounds().top);
        float f2 = (float) 2;
        this.f6509c.set(0.0f, (((float) getBounds().height()) / 2.0f) - (this.f6515i / f2), (float) getBounds().width(), (this.f6515i / f2) + (((float) getBounds().height()) / 2.0f));
        RectF rectF = this.f6509c;
        float f3 = this.f6513g;
        canvas.drawRoundRect(rectF, f3, f3, this.f6507a);
        this.f6509c.set(0.0f, (((float) getBounds().height()) / 2.0f) - (this.f6515i / f2), this.f6510d * ((float) getBounds().width()), (this.f6515i / f2) + (((float) getBounds().height()) / 2.0f));
        RectF rectF2 = this.f6509c;
        float f4 = this.f6513g;
        canvas.drawRoundRect(rectF2, f4, f4, this.f6508b);
        canvas.restore();
    }

    public int getIntrinsicHeight() {
        return (int) this.f6514h;
    }

    public int getOpacity() {
        return -3;
    }

    public void invalidateDrawable(@NotNull Drawable drawable) {
        Intrinsics.checkNotNullParameter(drawable, "drawable");
        invalidateSelf();
    }

    public void onBoundsChange(@NotNull Rect rect) {
        Intrinsics.checkNotNullParameter(rect, "rect");
        a(rect);
    }

    public void scheduleDrawable(@NotNull Drawable drawable, @NotNull Runnable runnable, long j2) {
        Intrinsics.checkNotNullParameter(drawable, "drawable");
        Intrinsics.checkNotNullParameter(runnable, "runnable");
    }

    public void setAlpha(int i3) {
        this.f6508b.setAlpha(i3);
        this.f6507a.setAlpha(i3);
    }

    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        this.f6508b.setColorFilter(colorFilter);
        this.f6507a.setColorFilter(colorFilter);
    }

    public void unscheduleDrawable(@NotNull Drawable drawable, @NotNull Runnable runnable) {
        Intrinsics.checkNotNullParameter(drawable, "drawable");
        Intrinsics.checkNotNullParameter(runnable, "runnable");
    }

    public final void a(int i3) {
        if (this.f6512f != i3) {
            this.f6512f = i3;
            Rect bounds = getBounds();
            Intrinsics.checkNotNullExpressionValue(bounds, "bounds");
            a(bounds);
        }
    }

    public final void b(float f2) {
        this.f6513g = f2 / ((float) 2);
        this.f6515i = f2;
        invalidateSelf();
    }

    public final void a(float f2) {
        this.f6514h = f2;
    }

    public final void a(Rect rect) {
        this.f6508b.setShader(new LinearGradient(0.0f, rect.exactCenterY(), (float) rect.width(), rect.exactCenterY(), this.f6511e, this.f6512f, Shader.TileMode.CLAMP));
    }
}
