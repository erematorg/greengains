package com.appsamurai.storyly.util.ui.slider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.core.app.NotificationCompat;
import com.appsamurai.storyly.R;
import com.appsamurai.storyly.util.ui.slider.c;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressLint({"ViewConstructor"})
public final class a extends View {

    /* renamed from: a  reason: collision with root package name */
    public final float f6459a;

    /* renamed from: b  reason: collision with root package name */
    public final int f6460b;

    /* renamed from: c  reason: collision with root package name */
    public final int f6461c;

    /* renamed from: d  reason: collision with root package name */
    public int f6462d;

    /* renamed from: e  reason: collision with root package name */
    public boolean f6463e;

    /* renamed from: f  reason: collision with root package name */
    public final int f6464f;

    /* renamed from: g  reason: collision with root package name */
    public float f6465g;

    /* renamed from: h  reason: collision with root package name */
    public float f6466h;

    /* renamed from: i  reason: collision with root package name */
    public boolean f6467i = true;

    /* renamed from: j  reason: collision with root package name */
    public float f6468j = 0.5f;
    @NotNull

    /* renamed from: k  reason: collision with root package name */
    public c f6469k;
    @NotNull

    /* renamed from: l  reason: collision with root package name */
    public String f6470l;
    @Nullable

    /* renamed from: m  reason: collision with root package name */
    public View f6471m;

    /* renamed from: n  reason: collision with root package name */
    public float f6472n;
    @NotNull

    /* renamed from: o  reason: collision with root package name */
    public final e f6473o;
    @Nullable

    /* renamed from: p  reason: collision with root package name */
    public Function0<Unit> f6474p;
    @Nullable

    /* renamed from: q  reason: collision with root package name */
    public Function0<Unit> f6475q;

    /* renamed from: r  reason: collision with root package name */
    public Drawable f6476r;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public a(@NotNull Context context, float f2) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.f6459a = f2;
        this.f6469k = new c(context);
        this.f6470l = "😍";
        this.f6472n = 0.25f;
        e eVar = new e();
        this.f6473o = eVar;
        float f3 = context.getResources().getDisplayMetrics().density;
        Resources resources = context.getResources();
        this.f6460b = (int) (((float) 56) * f3 * ((float) 4));
        int roundToInt = MathKt.roundToInt((((float) resources.getDimensionPixelSize(R.dimen.st_emoji_slider_thumb_scale_size_step)) * f2) + ((float) resources.getDimensionPixelSize(R.dimen.st_emoji_slider_thumb_initial_size)) + (f3 * ((float) 8)));
        this.f6461c = roundToInt;
        this.f6464f = roundToInt / 2;
        eVar.setCallback(this);
        eVar.a((((float) resources.getDimensionPixelSize(R.dimen.st_emoji_slider_scale_height_step)) * f2) + ((float) resources.getDimensionPixelSize(R.dimen.st_emoji_slider_initial_height)));
        eVar.b((f2 * ((float) resources.getDimensionPixelSize(R.dimen.st_emoji_slider_track_scale_height_step))) + context.getResources().getDimension(R.dimen.st_emoji_slider_track_initial_height));
        eVar.invalidateSelf();
        eVar.b(com.appsamurai.storyly.config.styling.a.COLOR_DBDBDB.a());
        eVar.a(com.appsamurai.storyly.config.styling.a.COLOR_F50000.a());
        eVar.a().setColor(com.appsamurai.storyly.config.styling.a.COLOR_B900B4.a());
        setEmoji(this.f6470l);
        this.f6462d = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    private final Pair<Float, Float> getPaddingForFloatingEmoji() {
        int[] iArr = new int[2];
        getLocationOnScreen(iArr);
        int[] iArr2 = new int[2];
        View view = this.f6471m;
        Intrinsics.checkNotNull(view);
        view.getLocationOnScreen(iArr2);
        float width = this.f6472n * ((float) this.f6473o.getBounds().width());
        float f2 = this.f6466h;
        if (f2 >= -30.0f && f2 <= 30.0f) {
            return new Pair<>(Float.valueOf((((float) iArr[0]) + width) - ((float) iArr2[0])), Float.valueOf(((((float) iArr[1]) + ((float) this.f6473o.getBounds().top)) + ((float) ((int) (getContext().getResources().getDisplayMetrics().density * 32.0f)))) - ((float) iArr2[1])));
        }
        if (f2 >= 0.0f || f2 <= -360.0f) {
            this.f6466h = Math.abs(f2 % ((float) 360));
        } else {
            this.f6466h = f2 + ((float) 360);
        }
        float f3 = this.f6466h;
        if (f3 > 270.0f && f3 < 330.0f) {
            return new Pair<>(Float.valueOf((((float) iArr[0]) + ((float) this.f6473o.getBounds().left)) - ((float) iArr2[0])), Float.valueOf(((((float) iArr[1]) + ((float) this.f6473o.getBounds().top)) - width) - ((float) iArr2[1])));
        }
        if (f3 <= 30.0f || f3 > 90.0f) {
            return new Pair<>(Float.valueOf(((((float) iArr[0]) + ((float) this.f6473o.getBounds().left)) + width) - ((float) iArr2[0])), Float.valueOf(((((float) iArr[1]) + ((float) this.f6473o.getBounds().top)) + ((float) ((int) (getContext().getResources().getDisplayMetrics().density * 32.0f)))) - ((float) iArr2[1])));
        }
        return new Pair<>(Float.valueOf((((this.f6466h / ((float) 360)) * (this.f6472n * width)) + (((float) iArr[0]) + ((float) this.f6473o.getBounds().left))) - ((float) iArr2[0])), Float.valueOf(((float) iArr[1]) + ((float) this.f6473o.getBounds().top) + width + ((float) iArr2[1])));
    }

    public final void a() {
        if (this.f6471m != null) {
            Pair<Float, Float> paddingForFloatingEmoji = getPaddingForFloatingEmoji();
            float floatValue = paddingForFloatingEmoji.component1().floatValue();
            float floatValue2 = paddingForFloatingEmoji.component2().floatValue();
            c cVar = this.f6469k;
            String str = this.f6470l;
            float f2 = this.f6466h;
            cVar.getClass();
            Intrinsics.checkNotNullParameter(str, "emoji");
            c.a aVar = new c.a(str);
            aVar.f6490b = floatValue;
            aVar.f6491c = floatValue2;
            aVar.f6493e = 0.0f;
            aVar.f6495g = f2;
            Unit unit = Unit.INSTANCE;
            cVar.f6488l = aVar;
            if (!cVar.f6487k) {
                cVar.f6487k = true;
                cVar.doFrame(System.currentTimeMillis());
            }
        }
    }

    public final void b(MotionEvent motionEvent) {
        if (this.f6463e) {
            setProgress(((float) (((int) motionEvent.getX()) - this.f6473o.getBounds().left)) / ((float) this.f6473o.getBounds().width()));
            float f2 = this.f6472n;
            if (this.f6471m != null) {
                Pair<Float, Float> paddingForFloatingEmoji = getPaddingForFloatingEmoji();
                float floatValue = paddingForFloatingEmoji.component1().floatValue();
                float floatValue2 = paddingForFloatingEmoji.component2().floatValue();
                c cVar = this.f6469k;
                float f3 = this.f6466h;
                c.a aVar = cVar.f6488l;
                if (aVar != null) {
                    aVar.f6490b = floatValue;
                    aVar.f6491c = floatValue2;
                    int i3 = cVar.f6478b;
                    aVar.f6493e = (f2 * ((float) (cVar.f6479c - i3))) + ((float) i3);
                    aVar.f6495g = f3;
                }
                cVar.invalidateSelf();
            }
        }
    }

    public final float getAverageProgressValue() {
        return this.f6468j;
    }

    public final float getDegree() {
        return this.f6466h;
    }

    @NotNull
    public final String getEmoji() {
        return this.f6470l;
    }

    public final float getProgress() {
        return this.f6472n;
    }

    @Nullable
    public final View getSliderParticleSystem() {
        return this.f6471m;
    }

    @Nullable
    public final Function0<Unit> getStartTrackingListener() {
        return this.f6474p;
    }

    @Nullable
    public final Function0<Unit> getStopTrackingListener() {
        return this.f6475q;
    }

    public void invalidateDrawable(@NotNull Drawable drawable) {
        Intrinsics.checkNotNullParameter(drawable, "drawable");
        invalidate();
    }

    public void onDraw(@NotNull Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        this.f6473o.draw(canvas);
        float width = this.f6472n * ((float) this.f6473o.getBounds().width());
        canvas.save();
        canvas.translate((float) this.f6473o.getBounds().left, (float) this.f6473o.getBounds().top);
        Drawable drawable = this.f6476r;
        Drawable drawable2 = null;
        if (drawable == null) {
            Intrinsics.throwUninitializedPropertyAccessException("thumbDrawable");
            drawable = null;
        }
        int roundToInt = MathKt.roundToInt(width);
        int intrinsicWidth = drawable.getIntrinsicWidth() / 2;
        int intrinsicHeight = drawable.getIntrinsicHeight() / 2;
        int height = this.f6473o.getBounds().height() / 2;
        drawable.setBounds(roundToInt - intrinsicWidth, height - intrinsicHeight, roundToInt + intrinsicWidth, height + intrinsicHeight);
        Drawable drawable3 = this.f6476r;
        if (drawable3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("thumbDrawable");
        } else {
            drawable2 = drawable3;
        }
        drawable2.draw(canvas);
        canvas.restore();
    }

    public void onMeasure(int i3, int i4) {
        setMeasuredDimension(View.resolveSizeAndState(this.f6460b, i3, 0), View.resolveSizeAndState(this.f6461c, i4, 0));
    }

    public void onSizeChanged(int i3, int i4, int i5, int i6) {
        super.onSizeChanged(i3, i4, i5, i6);
        int i7 = i4 / 2;
        this.f6473o.setBounds(Math.max(getPaddingLeft(), this.f6464f), i7 - (((int) this.f6473o.f6514h) / 2), i3 - Math.max(getPaddingRight(), this.f6464f), (((int) this.f6473o.f6514h) / 2) + i7);
    }

    public boolean onTouchEvent(@NotNull MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, NotificationCompat.CATEGORY_EVENT);
        if (!this.f6467i || !isEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action == 1) {
                if (this.f6463e) {
                    super.performClick();
                }
                if (!this.f6463e) {
                    Rect bounds = this.f6473o.getBounds();
                    Intrinsics.checkNotNullExpressionValue(bounds, "trackDrawable.bounds");
                    if (bounds.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                        this.f6463e = true;
                        a();
                        b(motionEvent);
                    }
                }
                if (this.f6463e) {
                    this.f6467i = false;
                    invalidate();
                    c cVar = this.f6469k;
                    c.a aVar = cVar.f6488l;
                    if (aVar != null) {
                        cVar.f6481e.add(0, aVar);
                        cVar.f6488l = null;
                    }
                    Function0<Unit> function0 = this.f6475q;
                    if (function0 != null) {
                        function0.invoke();
                    }
                }
                this.f6463e = false;
                setPressed(false);
                invalidate();
            } else if (action != 2) {
                if (action == 3) {
                    if (this.f6463e) {
                        this.f6463e = false;
                        setPressed(false);
                    }
                    invalidate();
                }
            } else if (this.f6463e) {
                b(motionEvent);
            } else if (Math.abs(motionEvent.getX() - this.f6465g) > ((float) this.f6462d)) {
                a(motionEvent);
            }
        } else if (isScrollContainer()) {
            this.f6465g = motionEvent.getX();
        } else {
            a(motionEvent);
        }
        return true;
    }

    public boolean performClick() {
        super.performClick();
        return true;
    }

    public void scheduleDrawable(@NotNull Drawable drawable, @NotNull Runnable runnable, long j2) {
        Intrinsics.checkNotNullParameter(drawable, "drawable");
        Intrinsics.checkNotNullParameter(runnable, "runnable");
    }

    public final void setAverageProgressValue(float f2) {
        this.f6468j = f2;
    }

    public final void setDegree(float f2) {
        this.f6466h = f2;
    }

    public final void setEmoji(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "value");
        this.f6470l = str;
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "this.context");
        d a2 = b.a(context, str, (this.f6459a * ((float) getContext().getResources().getDimensionPixelSize(R.dimen.st_emoji_slider_thumb_scale_size_step))) + ((float) getContext().getResources().getDimensionPixelSize(R.dimen.st_emoji_slider_thumb_initial_size)), (Float) null);
        this.f6476r = a2;
        a2.setCallback(this);
        invalidate();
    }

    public final void setProgress(float f2) {
        float max = Math.max(Math.min(f2, 1.0f), 0.0f);
        this.f6472n = max;
        e eVar = this.f6473o;
        eVar.f6510d = max;
        eVar.invalidateSelf();
        invalidate();
    }

    public final void setSliderParticleSystem(@Nullable View view) {
        this.f6471m = view;
        if ((view == null ? null : view.getBackground()) instanceof c) {
            Drawable background = view.getBackground();
            if (background != null) {
                this.f6469k = (c) background;
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type com.appsamurai.storyly.util.ui.slider.FloatingEmoji");
        } else if (view != null) {
            view.setBackground(this.f6469k);
        }
    }

    public final void setStartTrackingListener(@Nullable Function0<Unit> function0) {
        this.f6474p = function0;
    }

    public final void setStopTrackingListener(@Nullable Function0<Unit> function0) {
        this.f6475q = function0;
    }

    public final void setUserSeekable(boolean z2) {
        this.f6467i = z2;
    }

    public void unscheduleDrawable(@NotNull Drawable drawable, @NotNull Runnable runnable) {
        Intrinsics.checkNotNullParameter(drawable, "drawable");
        Intrinsics.checkNotNullParameter(runnable, "runnable");
    }

    public final void a(MotionEvent motionEvent) {
        int x2 = ((int) motionEvent.getX()) - this.f6473o.getBounds().left;
        int y2 = ((int) motionEvent.getY()) - this.f6473o.getBounds().top;
        Drawable drawable = this.f6476r;
        if (drawable == null) {
            Intrinsics.throwUninitializedPropertyAccessException("thumbDrawable");
            drawable = null;
        }
        if (!drawable.getBounds().contains(x2, y2)) {
            Rect bounds = this.f6473o.getBounds();
            Intrinsics.checkNotNullExpressionValue(bounds, "trackDrawable.bounds");
            if (!bounds.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                return;
            }
        }
        dispatchSetPressed(true);
        a();
        Function0<Unit> function0 = this.f6474p;
        if (function0 != null) {
            function0.invoke();
        }
        this.f6463e = true;
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
    }
}
