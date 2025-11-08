package com.appsamurai.storyly.util.ui.slider;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.view.Choreographer;
import com.appsamurai.storyly.R;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class c extends Drawable implements Choreographer.FrameCallback {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6477a;

    /* renamed from: b  reason: collision with root package name */
    public final int f6478b;

    /* renamed from: c  reason: collision with root package name */
    public final int f6479c;

    /* renamed from: d  reason: collision with root package name */
    public final int f6480d;
    @NotNull

    /* renamed from: e  reason: collision with root package name */
    public final List<a> f6481e = new ArrayList();
    @NotNull

    /* renamed from: f  reason: collision with root package name */
    public final List<a> f6482f = new ArrayList();
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public final Rect f6483g = new Rect();
    @NotNull

    /* renamed from: h  reason: collision with root package name */
    public final TextPaint f6484h = new TextPaint(1);
    @Nullable

    /* renamed from: i  reason: collision with root package name */
    public Drawable f6485i;

    /* renamed from: j  reason: collision with root package name */
    public long f6486j;

    /* renamed from: k  reason: collision with root package name */
    public boolean f6487k;
    @Nullable

    /* renamed from: l  reason: collision with root package name */
    public a f6488l;

    public static final class a {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final String f6489a;

        /* renamed from: b  reason: collision with root package name */
        public float f6490b;

        /* renamed from: c  reason: collision with root package name */
        public float f6491c;

        /* renamed from: d  reason: collision with root package name */
        public float f6492d;

        /* renamed from: e  reason: collision with root package name */
        public float f6493e;

        /* renamed from: f  reason: collision with root package name */
        public float f6494f;

        /* renamed from: g  reason: collision with root package name */
        public float f6495g;

        public a(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "mainEmoji");
            this.f6489a = str;
        }
    }

    public c(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f6477a = context;
        this.f6478b = context.getResources().getDimensionPixelSize(R.dimen.slider_particle_system_particle_min_size);
        this.f6479c = context.getResources().getDimensionPixelSize(R.dimen.slider_particle_system_particle_max_size);
        this.f6480d = context.getResources().getDimensionPixelSize(R.dimen.slider_particle_system_anchor_offset);
    }

    public final void a(Canvas canvas, a aVar) {
        this.f6485i = b.a(this.f6477a, aVar.f6489a, aVar.f6493e, Float.valueOf(aVar.f6495g));
        float f2 = aVar.f6495g;
        int i3 = 150;
        if (f2 < -30.0f || f2 > 30.0f) {
            if (f2 > 30.0f && f2 <= 90.0f) {
                i3 = -150;
            }
            float width = aVar.f6490b - (((float) this.f6483g.width()) / 2.0f);
            float f3 = ((aVar.f6491c + aVar.f6492d) - ((float) i3)) - (aVar.f6493e / 2.0f);
            if (width < 100.0f) {
                width = 100.0f;
            }
            canvas.translate(width, f3);
            Drawable drawable = this.f6485i;
            if (drawable != null) {
                drawable.draw(canvas);
            }
            canvas.rotate(aVar.f6495g, this.f6483g.exactCenterX(), aVar.f6491c);
            return;
        }
        float width2 = aVar.f6490b - (((float) this.f6483g.width()) / 2.0f);
        if (width2 < 50.0f) {
            width2 = 50.0f;
        }
        float f4 = aVar.f6495g;
        if (f4 < 0.0f) {
            width2 += (float) 100;
        }
        canvas.rotate(f4, (float) this.f6483g.centerX(), aVar.f6491c);
        canvas.translate(width2, ((aVar.f6491c + aVar.f6492d) - ((float) 150)) - (aVar.f6493e / 2.0f));
        Drawable drawable2 = this.f6485i;
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
    }

    public void doFrame(long j2) {
        a aVar = this.f6488l;
        if (aVar != null) {
            aVar.f6492d = (float) ((Math.sin(Math.toRadians((double) (System.currentTimeMillis() / ((long) 8)))) * 16.0d) - ((double) this.f6480d));
        }
        long currentTimeMillis = System.currentTimeMillis();
        long j3 = this.f6486j;
        if (j3 != 0) {
            float f2 = ((float) (currentTimeMillis - j3)) / 1000.0f;
            for (a aVar2 : this.f6481e) {
                float f3 = (((float) 1000) * f2) + aVar2.f6494f;
                aVar2.f6494f = f3;
                float f4 = aVar2.f6491c - (f3 * f2);
                aVar2.f6491c = f4;
                float f5 = aVar2.f6493e;
                if (f4 < ((float) getBounds().top) - (((float) 2) * f5) || f5 < 0.0f) {
                    this.f6482f.add(aVar2);
                }
            }
            if (!this.f6482f.isEmpty()) {
                this.f6481e.removeAll(this.f6482f);
                this.f6482f.clear();
            }
        }
        this.f6486j = currentTimeMillis;
        if (this.f6488l != null || !this.f6481e.isEmpty()) {
            Choreographer.getInstance().postFrameCallback(this);
        } else {
            this.f6487k = false;
        }
        invalidateSelf();
    }

    public void draw(@NotNull Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        a aVar = this.f6488l;
        if (aVar != null) {
            a(canvas, aVar);
        }
        int size = this.f6481e.size() - 1;
        if (size >= 0) {
            int i3 = 0;
            while (true) {
                int i4 = i3 + 1;
                a(canvas, this.f6481e.get(i3));
                if (i4 <= size) {
                    i3 = i4;
                } else {
                    return;
                }
            }
        }
    }

    public int getOpacity() {
        return -3;
    }

    public void setAlpha(int i3) {
        this.f6484h.setAlpha(i3);
        invalidateSelf();
    }

    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        this.f6484h.setColorFilter(colorFilter);
        invalidateSelf();
    }
}
