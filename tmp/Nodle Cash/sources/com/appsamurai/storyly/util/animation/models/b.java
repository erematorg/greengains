package com.appsamurai.storyly.util.animation.models;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public interface b {

    public static final class a implements b {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public static final a f6296a = new a();
        @NotNull

        /* renamed from: b  reason: collision with root package name */
        public static final RectF f6297b = new RectF();

        public void a(@NotNull Canvas canvas, @NotNull Paint paint, float f2) {
            Intrinsics.checkNotNullParameter(canvas, "canvas");
            Intrinsics.checkNotNullParameter(paint, "paint");
            RectF rectF = f6297b;
            rectF.set(0.0f, 0.0f, f2, f2);
            canvas.drawOval(rectF, paint);
        }
    }

    /* renamed from: com.appsamurai.storyly.util.animation.models.b$b  reason: collision with other inner class name */
    public static final class C0056b implements b {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public static final C0056b f6298a = new C0056b();

        public void a(@NotNull Canvas canvas, @NotNull Paint paint, float f2) {
            Intrinsics.checkNotNullParameter(canvas, "canvas");
            Intrinsics.checkNotNullParameter(paint, "paint");
            canvas.drawRect(0.0f, 0.0f, f2, f2, paint);
        }
    }

    void a(@NotNull Canvas canvas, @NotNull Paint paint, float f2);
}
