package com.appsamurai.storyly.util;

import android.content.res.Resources;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.math.MathKt;
import org.jetbrains.annotations.NotNull;

public final class m {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final Lazy f6342a = LazyKt.lazy(a.f6343a);

    public static final class a extends Lambda implements Function0<DisplayMetrics> {

        /* renamed from: a  reason: collision with root package name */
        public static final a f6343a = new a();

        public a() {
            super(0);
        }

        public Object invoke() {
            return Resources.getSystem().getDisplayMetrics();
        }
    }

    public static final DisplayMetrics a() {
        Object value = f6342a.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-displayMetrics>(...)");
        return (DisplayMetrics) value;
    }

    @NotNull
    public static final Rect b() {
        DisplayMetrics a2 = a();
        return new Rect(0, 0, a2.widthPixels, a2.heightPixels);
    }

    @NotNull
    public static final Rect c() {
        return ((float) b().height()) / ((float) b().width()) >= 1.7777778f ? new Rect(0, 0, b().width(), (int) (((float) b().width()) * 1.7777778f)) : new Rect(0, 0, (int) (((float) b().height()) / 1.7777778f), b().height());
    }

    public static int a(float f2, float f3, int i3) {
        if ((i3 & 2) != 0) {
            f3 = 592.0f;
        }
        return (int) ((((float) a().heightPixels) * f2) / f3);
    }

    public static int b(float f2, float f3, int i3) {
        if ((i3 & 2) != 0) {
            f3 = 361.0f;
        }
        return (int) ((((float) a().widthPixels) * f2) / f3);
    }

    public static final float a(int i3) {
        return (((float) Resources.getSystem().getDisplayMetrics().densityDpi) / ((float) 160)) * ((float) i3);
    }

    public static final int a(@NotNull Number number) {
        Intrinsics.checkNotNullParameter(number, "<this>");
        return MathKt.roundToInt(number.floatValue() * a().density);
    }
}
