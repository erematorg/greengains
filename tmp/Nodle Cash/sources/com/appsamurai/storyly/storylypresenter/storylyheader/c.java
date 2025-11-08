package com.appsamurai.storyly.storylypresenter.storylyheader;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;
import com.appsamurai.storyly.R;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.data.m0;
import com.appsamurai.storyly.util.o;
import kotlin.Unit;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressLint({"ViewConstructor"})
public final class c extends ProgressBar {

    /* renamed from: j  reason: collision with root package name */
    public static final /* synthetic */ int f5550j = 0;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final StorylyConfig f5551a;

    /* renamed from: b  reason: collision with root package name */
    public Function2<? super Long, ? super Long, Unit> f5552b;

    /* renamed from: c  reason: collision with root package name */
    public Function0<Unit> f5553c;
    @Nullable

    /* renamed from: d  reason: collision with root package name */
    public o f5554d;
    @Nullable

    /* renamed from: e  reason: collision with root package name */
    public ObjectAnimator f5555e;
    @Nullable

    /* renamed from: f  reason: collision with root package name */
    public Long f5556f = 7000L;

    /* renamed from: g  reason: collision with root package name */
    public long f5557g;

    /* renamed from: h  reason: collision with root package name */
    public long f5558h;

    /* renamed from: i  reason: collision with root package name */
    public boolean f5559i;

    public static final class a extends Lambda implements Function0<Unit> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ c f5560a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(c cVar) {
            super(0);
            this.f5560a = cVar;
        }

        public Object invoke() {
            this.f5560a.getOnTimeCompleted().invoke();
            return Unit.INSTANCE;
        }
    }

    public static final class b extends Lambda implements Function1<Long, Unit> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ c f5561a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ long f5562b;

        /* renamed from: c  reason: collision with root package name */
        public final /* synthetic */ long f5563c;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public b(c cVar, long j2, long j3) {
            super(1);
            this.f5561a = cVar;
            this.f5562b = j2;
            this.f5563c = j3;
        }

        public Object invoke(Object obj) {
            this.f5561a.getOnTimeUpdated().invoke(Long.valueOf(this.f5562b + ((Number) obj).longValue()), Long.valueOf(this.f5563c));
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public c(@NotNull Context context, @Nullable AttributeSet attributeSet, int i3, @NotNull StorylyConfig storylyConfig) {
        super(context, (AttributeSet) null, i3);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        this.f5551a = storylyConfig;
        setProgressDrawable(AppCompatResources.getDrawable(context, R.drawable.st_progress_bar));
        setImportantForAccessibility(4);
        b();
        c();
    }

    private final LayerDrawable getProgressLayerDrawable() {
        Drawable progressDrawable = getProgressDrawable();
        if (progressDrawable != null) {
            return (LayerDrawable) progressDrawable;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.graphics.drawable.LayerDrawable");
    }

    public final void a(@Nullable Long l2, long j2) {
        long longValue;
        this.f5558h = j2;
        this.f5556f = l2;
        if (l2 == null) {
            longValue = 7000;
        } else {
            longValue = l2.longValue();
        }
        long j3 = longValue;
        long j4 = j3 - j2;
        long ceil = (long) Math.ceil(((double) j4) / ((double) getMax()));
        setProgress((int) ((((long) getMax()) * j2) / j3));
        ObjectAnimator ofInt = ObjectAnimator.ofInt(this, "progress", new int[]{getProgress(), getMax()});
        ofInt.setInterpolator(new LinearInterpolator());
        ofInt.setDuration(j4);
        ofInt.start();
        Unit unit = Unit.INSTANCE;
        this.f5555e = ofInt;
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        o oVar = new o(context, j4, ceil);
        oVar.f6350e = new a(this);
        oVar.f6349d = new b(this, j2, j3);
        synchronized (oVar) {
            if (oVar.f6347b <= 0) {
                Function0<Unit> function0 = oVar.f6350e;
                if (function0 != null) {
                    function0.invoke();
                }
            } else {
                oVar.f6351f = SystemClock.elapsedRealtime() + oVar.f6347b;
                oVar.a().sendMessage(oVar.a().obtainMessage(1));
            }
        }
        this.f5554d = oVar;
        this.f5559i = false;
    }

    public final void b() {
        Drawable findDrawableByLayerId = getProgressLayerDrawable().findDrawableByLayerId(16908288);
        m0 storylyStyle$storyly_release = this.f5551a.getStorylyStyle$storyly_release();
        Integer num = null;
        Integer num2 = storylyStyle$storyly_release == null ? null : storylyStyle$storyly_release.f3868g;
        DrawableCompat.setTint(findDrawableByLayerId, num2 == null ? this.f5551a.getStory$storyly_release().getProgressBarColor$storyly_release().get(1).intValue() : num2.intValue());
        Drawable findDrawableByLayerId2 = getProgressLayerDrawable().findDrawableByLayerId(16908301);
        m0 storylyStyle$storyly_release2 = this.f5551a.getStorylyStyle$storyly_release();
        if (storylyStyle$storyly_release2 != null) {
            num = storylyStyle$storyly_release2.f3869h;
        }
        DrawableCompat.setTint(findDrawableByLayerId2, num == null ? this.f5551a.getStory$storyly_release().getProgressBarColor$storyly_release().get(0).intValue() : num.intValue());
    }

    public final void c() {
        ObjectAnimator objectAnimator = this.f5555e;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        this.f5555e = null;
        o oVar = this.f5554d;
        if (oVar != null) {
            synchronized (oVar) {
                oVar.a().removeMessages(1);
                oVar.f6353h = true;
            }
        }
        this.f5554d = null;
        setProgress(0);
        setMax(1000);
        this.f5557g = 0;
        this.f5559i = false;
    }

    public final long getCurrentPlayTime$storyly_release() {
        ObjectAnimator objectAnimator = this.f5555e;
        return this.f5558h + (objectAnimator == null ? 0 : objectAnimator.getCurrentPlayTime());
    }

    @NotNull
    public final Function0<Unit> getOnTimeCompleted() {
        Function0<Unit> function0 = this.f5553c;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onTimeCompleted");
        return null;
    }

    @NotNull
    public final Function2<Long, Long, Unit> getOnTimeUpdated() {
        Function2<? super Long, ? super Long, Unit> function2 = this.f5552b;
        if (function2 != null) {
            return function2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onTimeUpdated");
        return null;
    }

    public final void setOnTimeCompleted(@NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.f5553c = function0;
    }

    public final void setOnTimeUpdated(@NotNull Function2<? super Long, ? super Long, Unit> function2) {
        Intrinsics.checkNotNullParameter(function2, "<set-?>");
        this.f5552b = function2;
    }

    public final void a() {
        ObjectAnimator objectAnimator = this.f5555e;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        o oVar = this.f5554d;
        if (oVar != null) {
            synchronized (oVar) {
                oVar.a().removeMessages(1);
                oVar.f6353h = true;
            }
        }
        setProgress(getMax());
    }
}
