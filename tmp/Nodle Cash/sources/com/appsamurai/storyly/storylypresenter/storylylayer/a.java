package com.appsamurai.storyly.storylypresenter.storylylayer;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class a extends Drawable {

    /* renamed from: a  reason: collision with root package name */
    public final float f5564a;

    /* renamed from: b  reason: collision with root package name */
    public final int f5565b;

    /* renamed from: c  reason: collision with root package name */
    public final int f5566c;

    /* renamed from: d  reason: collision with root package name */
    public final int f5567d;

    /* renamed from: e  reason: collision with root package name */
    public final int f5568e;
    @NotNull

    /* renamed from: f  reason: collision with root package name */
    public final Lazy f5569f = LazyKt.lazy(new b(this));
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public final Lazy f5570g = LazyKt.lazy(new C0048a(this));
    @NotNull

    /* renamed from: h  reason: collision with root package name */
    public final Path f5571h = new Path();

    /* renamed from: com.appsamurai.storyly.storylypresenter.storylylayer.a$a  reason: collision with other inner class name */
    public static final class C0048a extends Lambda implements Function0<Paint> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ a f5572a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public C0048a(a aVar) {
            super(0);
            this.f5572a = aVar;
        }

        public Object invoke() {
            Paint paint = new Paint(1);
            a aVar = this.f5572a;
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(aVar.f5568e);
            paint.setDither(true);
            return paint;
        }
    }

    public static final class b extends Lambda implements Function0<Paint> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ a f5573a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public b(a aVar) {
            super(0);
            this.f5573a = aVar;
        }

        public Object invoke() {
            Paint paint = new Paint(1);
            a aVar = this.f5573a;
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(aVar.f5567d);
            paint.setStrokeWidth((float) aVar.f5566c);
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setDither(true);
            return paint;
        }
    }

    public a(float f2, int i3, int i4, int i5, int i6) {
        this.f5564a = f2;
        this.f5565b = i3;
        this.f5566c = i4;
        this.f5567d = i5;
        this.f5568e = i6;
    }

    public void draw(@NotNull Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        int width = getBounds().width();
        int height = getBounds().height();
        float f2 = ((float) this.f5566c) / 2.0f;
        canvas.save();
        Path path = this.f5571h;
        float f3 = f2 + 0.0f;
        path.moveTo(this.f5564a + 0.0f + f2, f3);
        float f4 = (float) width;
        path.lineTo((f4 - this.f5564a) - f2, f3);
        float f5 = this.f5564a * 2.0f;
        float f6 = f4 - f2;
        path.arcTo(new RectF((f4 - f5) - f2, f3, f6, f5 + 0.0f), 270.0f, 90.0f);
        path.lineTo(f6, (float) (height - this.f5565b));
        float f7 = (float) height;
        float f8 = f7 - f2;
        path.lineTo(f6, f8 - ((float) (this.f5565b / 11)));
        float f9 = (float) this.f5565b;
        path.arcTo(new RectF(f6 - (f9 / 2.0f), f7 - (f9 / 11.0f), f6, f8), 0.0f, 45.0f);
        int i3 = this.f5565b;
        path.lineTo(f6 - ((float) i3), (float) (height - i3));
        float f10 = (float) 2;
        path.lineTo((this.f5564a * f10) + 0.0f + f2, (float) (height - this.f5565b));
        float f11 = this.f5564a;
        int i4 = this.f5565b;
        path.arcTo(new RectF(f3, (f7 - (2.0f * f11)) - ((float) i4), (f11 * f10) + 0.0f + f2, (float) (height - i4)), 90.0f, 90.0f);
        path.lineTo(f3, this.f5564a + 0.0f + f2);
        float f12 = (f10 * this.f5564a) + 0.0f + f2;
        path.arcTo(new RectF(f3, f3, f12, f12), 180.0f, 90.0f);
        canvas.drawPath(this.f5571h, (Paint) this.f5569f.getValue());
        canvas.drawPath(this.f5571h, (Paint) this.f5570g.getValue());
    }

    public int getOpacity() {
        return -1;
    }

    public void setAlpha(int i3) {
    }

    public void setColorFilter(@Nullable ColorFilter colorFilter) {
    }
}
