package com.appsamurai.storyly.util.ui.slider;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.Spannable;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.ViewTreeObserver;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class d extends Drawable {

    /* renamed from: a  reason: collision with root package name */
    public int f6496a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final TextPaint f6497b;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    public Spannable f6498c;
    @NotNull

    /* renamed from: d  reason: collision with root package name */
    public Layout.Alignment f6499d = Layout.Alignment.ALIGN_CENTER;

    /* renamed from: e  reason: collision with root package name */
    public int f6500e;

    /* renamed from: f  reason: collision with root package name */
    public int f6501f;

    /* renamed from: g  reason: collision with root package name */
    public int f6502g;

    /* renamed from: h  reason: collision with root package name */
    public int f6503h;

    /* renamed from: i  reason: collision with root package name */
    public final float f6504i = 1.0f;

    /* renamed from: j  reason: collision with root package name */
    public float f6505j;

    /* renamed from: k  reason: collision with root package name */
    public StaticLayout f6506k;

    public d(@NotNull Context context, int i3) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f6496a = i3;
        TextPaint textPaint = new TextPaint();
        this.f6497b = textPaint;
        textPaint.density = context.getResources().getDisplayMetrics().density;
        textPaint.setAntiAlias(true);
        textPaint.setDither(true);
        textPaint.setFilterBitmap(true);
        textPaint.setColor(-1);
    }

    public final void a() {
        int lineCount;
        Spannable spannable = this.f6498c;
        if (spannable != null) {
            int i3 = 0;
            StaticLayout build = StaticLayout.Builder.obtain(spannable.toString(), 0, spannable.length(), this.f6497b, this.f6496a).setAlignment(this.f6499d).setLineSpacing(0.0f, this.f6504i).setIncludePad(false).build();
            Intrinsics.checkNotNullExpressionValue(build, "{\n            StaticLayo…(false).build()\n        }");
            this.f6506k = build;
            StaticLayout staticLayout = null;
            if (build == null) {
                Intrinsics.throwUninitializedPropertyAccessException("staticlayout");
                build = null;
            }
            if (build != null && (lineCount = build.getLineCount()) >= 0) {
                int i4 = 0;
                while (true) {
                    int i5 = i3 + 1;
                    i4 = Math.max(i4, MathKt.roundToInt(build.getLineRight(i3) - build.getLineLeft(i3)));
                    if (i3 == lineCount) {
                        break;
                    }
                    i3 = i5;
                }
                i3 = i4;
            }
            this.f6502g = Math.round(0.0f) + i3;
            StaticLayout staticLayout2 = this.f6506k;
            if (staticLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("staticlayout");
            } else {
                staticLayout = staticLayout2;
            }
            this.f6503h = Math.round(0.0f) + staticLayout.getHeight();
        }
    }

    public void draw(@NotNull Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        canvas.save();
        canvas.translate((float) this.f6500e, (float) this.f6501f);
        int i3 = 0;
        if (!TextUtils.isEmpty(this.f6498c)) {
            Spannable spannable = this.f6498c;
            Intrinsics.checkNotNull(spannable);
            Spannable spannable2 = this.f6498c;
            Intrinsics.checkNotNull(spannable2);
            ViewTreeObserver.OnPreDrawListener[] onPreDrawListenerArr = (ViewTreeObserver.OnPreDrawListener[]) spannable.getSpans(0, spannable2.length(), ViewTreeObserver.OnPreDrawListener.class);
            int length = onPreDrawListenerArr.length - 1;
            if (length >= 0) {
                int i4 = 0;
                while (true) {
                    int i5 = i4 + 1;
                    onPreDrawListenerArr[i4].onPreDraw();
                    if (i5 > length) {
                        break;
                    }
                    i4 = i5;
                }
            }
        }
        canvas.translate(0.0f, 0.0f);
        StaticLayout staticLayout = null;
        if (this.f6499d != Layout.Alignment.ALIGN_NORMAL) {
            StaticLayout staticLayout2 = this.f6506k;
            if (staticLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("staticlayout");
                staticLayout2 = null;
            }
            if (!(staticLayout2 == null || staticLayout2.getLineCount() == 0)) {
                int lineCount = staticLayout2.getLineCount();
                int i6 = Integer.MAX_VALUE;
                if (lineCount >= 0) {
                    while (true) {
                        int i7 = i3 + 1;
                        i6 = Math.min(i6, (int) staticLayout2.getLineLeft(i3));
                        if (i3 == lineCount) {
                            break;
                        }
                        i3 = i7;
                    }
                }
                i3 = i6;
            }
            canvas.rotate(this.f6505j * ((float) -1));
            canvas.save();
            canvas.translate(-((float) i3), 0.0f);
            StaticLayout staticLayout3 = this.f6506k;
            if (staticLayout3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("staticlayout");
            } else {
                staticLayout = staticLayout3;
            }
            staticLayout.draw(canvas);
            canvas.restore();
        } else {
            StaticLayout staticLayout4 = this.f6506k;
            if (staticLayout4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("staticlayout");
            } else {
                staticLayout = staticLayout4;
            }
            staticLayout.draw(canvas);
        }
        canvas.restore();
    }

    public int getIntrinsicHeight() {
        return this.f6503h;
    }

    public int getIntrinsicWidth() {
        return this.f6502g;
    }

    public int getOpacity() {
        return -3;
    }

    public void onBoundsChange(@NotNull Rect rect) {
        Intrinsics.checkNotNullParameter(rect, "rect");
        this.f6500e = rect.left;
        this.f6501f = rect.top;
    }

    public void setAlpha(int i3) {
        this.f6497b.setAlpha(i3);
        a();
        invalidateSelf();
    }

    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        this.f6497b.setColorFilter(colorFilter);
        a();
        invalidateSelf();
    }
}
