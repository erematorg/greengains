package com.appsamurai.storyly.storylypresenter.storylylayer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;
import androidx.core.view.ViewCompat;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class h2 extends View {

    /* renamed from: a  reason: collision with root package name */
    public float f5759a;

    /* renamed from: b  reason: collision with root package name */
    public int f5760b = ViewCompat.MEASURED_STATE_MASK;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public final Lazy f5761c = LazyKt.lazy(new a(this));
    @NotNull

    /* renamed from: d  reason: collision with root package name */
    public final Path f5762d = new Path();
    @NotNull

    /* renamed from: e  reason: collision with root package name */
    public final Lazy f5763e = LazyKt.lazy(new b(this));

    public static final class a extends Lambda implements Function0<Float> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ h2 f5764a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(h2 h2Var) {
            super(0);
            this.f5764a = h2Var;
        }

        public Object invoke() {
            return Float.valueOf(this.f5764a.f5759a * 4.0f);
        }
    }

    public static final class b extends Lambda implements Function0<Paint> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ h2 f5765a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public b(h2 h2Var) {
            super(0);
            this.f5765a = h2Var;
        }

        public Object invoke() {
            Paint paint = new Paint();
            h2 h2Var = this.f5765a;
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(h2Var.f5759a);
            paint.setColor(h2Var.f5760b);
            paint.setPathEffect(new DashPathEffect(new float[]{((Number) h2Var.f5761c.getValue()).floatValue(), ((Number) h2Var.f5761c.getValue()).floatValue() * 0.8f}, 0.0f));
            return paint;
        }
    }

    public h2(@Nullable Context context) {
        super(context);
    }

    public void onDraw(@NotNull Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        this.f5762d.moveTo(0.0f, 0.0f);
        this.f5762d.lineTo(0.0f, (float) getMeasuredHeight());
        canvas.drawPath(this.f5762d, (Paint) this.f5763e.getValue());
    }
}
