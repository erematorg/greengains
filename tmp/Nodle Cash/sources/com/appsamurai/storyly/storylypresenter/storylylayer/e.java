package com.appsamurai.storyly.storylypresenter.storylylayer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.widget.RelativeLayout;
import androidx.core.view.ViewCompat;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class e extends RelativeLayout {

    /* renamed from: a  reason: collision with root package name */
    public int f5712a = ViewCompat.MEASURED_STATE_MASK;

    /* renamed from: b  reason: collision with root package name */
    public float f5713b;

    /* renamed from: c  reason: collision with root package name */
    public int f5714c = ViewCompat.MEASURED_STATE_MASK;

    /* renamed from: d  reason: collision with root package name */
    public float f5715d;

    /* renamed from: e  reason: collision with root package name */
    public float f5716e;
    @NotNull

    /* renamed from: f  reason: collision with root package name */
    public final Path f5717f = new Path();
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public final Lazy f5718g = LazyKt.lazy(new a(this));
    @NotNull

    /* renamed from: h  reason: collision with root package name */
    public final Lazy f5719h = LazyKt.lazy(new b(this));

    public static final class a extends Lambda implements Function0<Paint> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ e f5720a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(e eVar) {
            super(0);
            this.f5720a = eVar;
        }

        public Object invoke() {
            Paint paint = new Paint(1);
            e eVar = this.f5720a;
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(eVar.f5712a);
            return paint;
        }
    }

    public static final class b extends Lambda implements Function0<Paint> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ e f5721a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public b(e eVar) {
            super(0);
            this.f5721a = eVar;
        }

        public Object invoke() {
            Paint paint = new Paint(1);
            e eVar = this.f5721a;
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(eVar.f5714c);
            paint.setStrokeWidth(eVar.f5713b);
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setDither(true);
            paint.setAntiAlias(true);
            return paint;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public e(@NotNull Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        setWillNotDraw(false);
    }

    public void onDraw(@Nullable Canvas canvas) {
        Canvas canvas2 = canvas;
        super.onDraw(canvas);
        float measuredWidth = (float) getMeasuredWidth();
        float measuredHeight = (float) getMeasuredHeight();
        float f2 = this.f5713b / 2.0f;
        Path path = this.f5717f;
        float f3 = f2 + 0.0f;
        path.moveTo(this.f5715d + 0.0f + f2, f3);
        path.lineTo((measuredWidth - this.f5715d) - f2, f3);
        float f4 = this.f5715d * 2.0f;
        float f5 = measuredWidth - f2;
        path.arcTo(new RectF((measuredWidth - f4) - f2, f3, f5, f4 + 0.0f), 270.0f, 90.0f);
        float f6 = measuredHeight / 2.0f;
        path.lineTo(f5, f6 - this.f5716e);
        float f7 = this.f5716e;
        path.arcTo(new RectF((measuredWidth - f7) - f2, f6 - f7, (measuredWidth + f7) - f2, f7 + f6), 270.0f, -180.0f);
        path.lineTo(f5, (measuredHeight - this.f5715d) - f2);
        float f8 = this.f5715d;
        float f9 = (float) 2;
        float f10 = measuredHeight - f2;
        path.arcTo(new RectF((measuredWidth - (f8 * 2.0f)) - f2, (measuredHeight - (f8 * f9)) - f2, f5, f10), 0.0f, 90.0f);
        path.lineTo(this.f5715d + 0.0f + f2, f10);
        float f11 = this.f5715d;
        path.arcTo(new RectF(f3, measuredHeight - (2.0f * f11), (f11 * f9) + 0.0f + f2, f10), 90.0f, 90.0f);
        path.lineTo(f3, this.f5716e + f6);
        float f12 = this.f5716e;
        path.arcTo(new RectF((((float) -1) * f12) + f2, f6 - f12, f12 + f2, f6 + f12), 90.0f, -180.0f);
        path.lineTo(f3, this.f5715d + 0.0f + f2);
        float f13 = (f9 * this.f5715d) + 0.0f + f2;
        path.arcTo(new RectF(f3, f3, f13, f13), 180.0f, 90.0f);
        if (canvas2 != null) {
            canvas2.drawPath(this.f5717f, (Paint) this.f5719h.getValue());
        }
        if (canvas2 != null) {
            canvas2.drawPath(this.f5717f, (Paint) this.f5718g.getValue());
        }
    }
}
