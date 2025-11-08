package com.appsamurai.storyly.storylypresenter.storylylayer;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import com.appsamurai.storyly.R;
import com.appsamurai.storyly.StoryComponent;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.data.a0;
import com.appsamurai.storyly.data.b0;
import com.appsamurai.storyly.data.l0;
import com.appsamurai.storyly.util.ui.l;
import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.math.MathKt;
import kotlinx.serialization.json.JsonElementBuildersKt;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonObjectBuilder;
import org.jetbrains.annotations.NotNull;

public final class c2 extends o1 {
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public final StorylyConfig f5671g;
    @NotNull

    /* renamed from: h  reason: collision with root package name */
    public final Lazy f5672h;
    @NotNull

    /* renamed from: i  reason: collision with root package name */
    public final Lazy f5673i;
    @NotNull

    /* renamed from: j  reason: collision with root package name */
    public final Lazy f5674j;
    @NotNull

    /* renamed from: k  reason: collision with root package name */
    public final Lazy f5675k;
    @NotNull

    /* renamed from: l  reason: collision with root package name */
    public final Lazy f5676l;
    @NotNull

    /* renamed from: m  reason: collision with root package name */
    public final Lazy f5677m;

    /* renamed from: n  reason: collision with root package name */
    public l0 f5678n;

    /* renamed from: o  reason: collision with root package name */
    public Function5<? super com.appsamurai.storyly.analytics.a, ? super b0, ? super StoryComponent, ? super JsonObject, ? super Function1<? super Boolean, Unit>, Unit> f5679o;

    /* renamed from: p  reason: collision with root package name */
    public Function0<Unit> f5680p;

    /* renamed from: q  reason: collision with root package name */
    public Function0<Unit> f5681q;
    @NotNull

    /* renamed from: r  reason: collision with root package name */
    public final Lazy f5682r;

    public static final class a extends Lambda implements Function0<RelativeLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5683a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(Context context) {
            super(0);
            this.f5683a = context;
        }

        public Object invoke() {
            RelativeLayout relativeLayout = new RelativeLayout(this.f5683a);
            relativeLayout.setId(View.generateViewId());
            return relativeLayout;
        }
    }

    public static final class b extends Lambda implements Function0<Unit> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ c2 f5684a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public b(c2 c2Var) {
            super(0);
            this.f5684a = c2Var;
        }

        public Object invoke() {
            this.f5684a.getOnUserInteractionStarted$storyly_release().invoke();
            c2.a(this.f5684a);
            return Unit.INSTANCE;
        }
    }

    public static final class c extends Lambda implements Function0<Unit> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ c2 f5685a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public c(c2 c2Var) {
            super(0);
            this.f5685a = c2Var;
        }

        public Object invoke() {
            this.f5685a.getOnUserInteractionEnded$storyly_release().invoke();
            c2.b(this.f5685a);
            return Unit.INSTANCE;
        }
    }

    public static final class d extends Lambda implements Function0<View> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5686a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public d(Context context) {
            super(0);
            this.f5686a = context;
        }

        public Object invoke() {
            View view = new View(this.f5686a);
            view.setId(View.generateViewId());
            return view;
        }
    }

    public static final class e extends Lambda implements Function0<RelativeLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5687a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public e(Context context) {
            super(0);
            this.f5687a = context;
        }

        public Object invoke() {
            RelativeLayout relativeLayout = new RelativeLayout(this.f5687a);
            relativeLayout.setId(View.generateViewId());
            return relativeLayout;
        }
    }

    public static final class f extends Lambda implements Function0<SharedPreferences> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5688a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public f(Context context) {
            super(0);
            this.f5688a = context;
        }

        public Object invoke() {
            return this.f5688a.getSharedPreferences("stryly-rating-results", 0);
        }
    }

    public static final class g extends Lambda implements Function0<com.appsamurai.storyly.util.ui.slider.a> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5689a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ c2 f5690b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public g(Context context, c2 c2Var) {
            super(0);
            this.f5689a = context;
            this.f5690b = c2Var;
        }

        public Object invoke() {
            Context context = this.f5689a;
            l0 l0Var = this.f5690b.f5678n;
            if (l0Var == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                l0Var = null;
            }
            return new com.appsamurai.storyly.util.ui.slider.a(context, l0Var.f3844f);
        }
    }

    public static final class h extends Lambda implements Function0<TextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5691a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public h(Context context) {
            super(0);
            this.f5691a = context;
        }

        public Object invoke() {
            TextView textView = new TextView(this.f5691a);
            textView.setId(View.generateViewId());
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setMaxLines(2);
            textView.setMinLines(2);
            textView.setHorizontallyScrolling(false);
            textView.setGravity(17);
            textView.setTextAlignment(1);
            com.appsamurai.storyly.util.d.a(textView);
            return textView;
        }
    }

    public static final class i extends Lambda implements Function0<RelativeLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5692a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public i(Context context) {
            super(0);
            this.f5692a = context;
        }

        public Object invoke() {
            RelativeLayout relativeLayout = new RelativeLayout(this.f5692a);
            relativeLayout.setClipChildren(false);
            relativeLayout.setClipToPadding(false);
            relativeLayout.setBackgroundColor(0);
            relativeLayout.setLayoutDirection(0);
            return relativeLayout;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public c2(@NotNull Context context, @NotNull StorylyConfig storylyConfig) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        this.f5671g = storylyConfig;
        this.f5672h = LazyKt.lazy(new f(context));
        this.f5673i = LazyKt.lazy(new a(context));
        this.f5674j = LazyKt.lazy(new h(context));
        this.f5675k = LazyKt.lazy(new g(context, this));
        this.f5676l = LazyKt.lazy(new d(context));
        this.f5677m = LazyKt.lazy(new e(context));
        this.f5682r = LazyKt.lazy(new i(context));
        l.b(this);
    }

    public static final void b(c2 c2Var, ValueAnimator valueAnimator) {
        Intrinsics.checkNotNullParameter(c2Var, "this$0");
        com.appsamurai.storyly.util.ui.slider.a ratingSlider = c2Var.getRatingSlider();
        Object animatedValue = valueAnimator.getAnimatedValue();
        if (animatedValue != null) {
            ratingSlider.setProgress(((Float) animatedValue).floatValue());
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Float");
    }

    private final int getAverage() {
        int i3 = getRatingSharedPreferences().getInt(getStorylyLayerItem$storyly_release().f3614i, -1);
        l0 l0Var = null;
        Integer valueOf = i3 == -1 ? null : Integer.valueOf(i3);
        if (valueOf == null) {
            l0 l0Var2 = this.f5678n;
            if (l0Var2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            } else {
                l0Var = l0Var2;
            }
            return l0Var.f3842d;
        }
        int intValue = valueOf.intValue();
        l0 l0Var3 = this.f5678n;
        if (l0Var3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            l0Var3 = null;
        }
        int i4 = l0Var3.f3842d;
        l0 l0Var4 = this.f5678n;
        if (l0Var4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            l0Var4 = null;
        }
        double d2 = (double) ((i4 * l0Var4.f3843e) + intValue);
        l0 l0Var5 = this.f5678n;
        if (l0Var5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
        } else {
            l0Var = l0Var5;
        }
        return MathKt.roundToInt(d2 / (((double) l0Var.f3843e) + 1.0d));
    }

    private final RelativeLayout getContainer() {
        return (RelativeLayout) this.f5673i.getValue();
    }

    private final View getRatingAnimationView() {
        return (View) this.f5676l.getValue();
    }

    private final RelativeLayout getRatingAverageView() {
        return (RelativeLayout) this.f5677m.getValue();
    }

    private final SharedPreferences getRatingSharedPreferences() {
        return (SharedPreferences) this.f5672h.getValue();
    }

    private final com.appsamurai.storyly.util.ui.slider.a getRatingSlider() {
        return (com.appsamurai.storyly.util.ui.slider.a) this.f5675k.getValue();
    }

    private final TextView getRatingTitle() {
        return (TextView) this.f5674j.getValue();
    }

    private final RelativeLayout getRatingView() {
        return (RelativeLayout) this.f5682r.getValue();
    }

    public void a(@NotNull b0 b0Var) {
        Intrinsics.checkNotNullParameter(b0Var, "storylyLayerItem");
        a0 a0Var = b0Var.f3615j;
        l0 l0Var = null;
        l0 l0Var2 = a0Var instanceof l0 ? (l0) a0Var : null;
        if (l0Var2 != null) {
            this.f5678n = l0Var2;
            setStorylyLayerItem$storyly_release(b0Var);
            TextView ratingTitle = getRatingTitle();
            l0 l0Var3 = this.f5678n;
            if (l0Var3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                l0Var3 = null;
            }
            com.appsamurai.storyly.data.c cVar = l0Var3.f3847i;
            if (cVar == null) {
                cVar = Intrinsics.areEqual((Object) l0Var3.f3840b, (Object) "Dark") ? new com.appsamurai.storyly.data.c(-1) : com.appsamurai.storyly.config.styling.a.COLOR_262626.b();
            }
            ratingTitle.setTextColor(cVar.f3624a);
            TextView ratingTitle2 = getRatingTitle();
            l0 l0Var4 = this.f5678n;
            if (l0Var4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                l0Var4 = null;
            }
            ratingTitle2.setText(l0Var4.f3839a);
            TextView ratingTitle3 = getRatingTitle();
            float dimension = getContext().getResources().getDimension(R.dimen.st_rating_title_initial_text_size);
            l0 l0Var5 = this.f5678n;
            if (l0Var5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                l0Var5 = null;
            }
            ratingTitle3.setTextSize(0, (getContext().getResources().getDimension(R.dimen.st_rating_title_scale_text_size_step) * l0Var5.f3844f) + dimension);
            getRatingTitle().setTypeface(this.f5671g.getStory$storyly_release().getInteractiveTypeface$storyly_release());
            TextView ratingTitle4 = getRatingTitle();
            l0 l0Var6 = this.f5678n;
            if (l0Var6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                l0Var6 = null;
            }
            boolean z2 = l0Var6.f3852n;
            l0 l0Var7 = this.f5678n;
            if (l0Var7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                l0Var7 = null;
            }
            com.appsamurai.storyly.util.c.a(ratingTitle4, z2, l0Var7.f3853o);
            getRatingSlider().setDegree(b0Var.f3613h);
            com.appsamurai.storyly.util.ui.slider.a ratingSlider = getRatingSlider();
            l0 l0Var8 = this.f5678n;
            if (l0Var8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            } else {
                l0Var = l0Var8;
            }
            ratingSlider.setEmoji(l0Var.f3841c);
            getRatingSlider().setProgress(0.0f);
            getRatingSlider().setAverageProgressValue((float) getAverage());
            getRatingSlider().setStartTrackingListener(new b(this));
            getRatingSlider().setStopTrackingListener(new c(this));
            setRotation(b0Var.f3613h);
            getOnLayerLoad$storyly_release().invoke();
        }
    }

    public void c() {
        getRatingSlider().clearAnimation();
        com.appsamurai.storyly.util.ui.slider.a ratingSlider = getRatingSlider();
        ratingSlider.f6467i = true;
        ratingSlider.invalidate();
        getRatingAverageView().removeAllViews();
        getRatingAnimationView().setVisibility(8);
        removeAllViews();
        getRatingView().removeAllViews();
        getContainer().removeAllViews();
    }

    public final void f() {
        AnimatorSet animatorSet = new AnimatorSet();
        ArrayList arrayList = new ArrayList();
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setFloatValues(new float[]{0.0f, 0.25f});
        valueAnimator.addUpdateListener(new G0.g(this, 0));
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.setDuration(300);
        valueAnimator.setStartDelay(300);
        ValueAnimator valueAnimator2 = new ValueAnimator();
        valueAnimator2.setFloatValues(new float[]{0.25f, 0.0f});
        valueAnimator2.addUpdateListener(new G0.g(this, 1));
        valueAnimator2.setInterpolator(new DecelerateInterpolator());
        valueAnimator2.setDuration(300);
        valueAnimator2.setStartDelay(600);
        arrayList.add(valueAnimator);
        arrayList.add(valueAnimator2);
        animatorSet.playTogether(arrayList);
        animatorSet.start();
    }

    @NotNull
    public final Function0<Unit> getOnUserInteractionEnded$storyly_release() {
        Function0<Unit> function0 = this.f5681q;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onUserInteractionEnded");
        return null;
    }

    @NotNull
    public final Function0<Unit> getOnUserInteractionStarted$storyly_release() {
        Function0<Unit> function0 = this.f5680p;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onUserInteractionStarted");
        return null;
    }

    @NotNull
    public final Function5<com.appsamurai.storyly.analytics.a, b0, StoryComponent, JsonObject, Function1<? super Boolean, Unit>, Unit> getOnUserReaction$storyly_release() {
        Function5<? super com.appsamurai.storyly.analytics.a, ? super b0, ? super StoryComponent, ? super JsonObject, ? super Function1<? super Boolean, Unit>, Unit> function5 = this.f5679o;
        if (function5 != null) {
            return function5;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onUserReaction");
        return null;
    }

    public final void setOnUserInteractionEnded$storyly_release(@NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.f5681q = function0;
    }

    public final void setOnUserInteractionStarted$storyly_release(@NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.f5680p = function0;
    }

    public final void setOnUserReaction$storyly_release(@NotNull Function5<? super com.appsamurai.storyly.analytics.a, ? super b0, ? super StoryComponent, ? super JsonObject, ? super Function1<? super Boolean, Unit>, Unit> function5) {
        Intrinsics.checkNotNullParameter(function5, "<set-?>");
        this.f5679o = function5;
    }

    public static final void b(c2 c2Var) {
        c2Var.getRatingSlider().setUserSeekable(false);
        int roundToInt = MathKt.roundToInt((float) Math.ceil((double) (c2Var.getRatingSlider().getProgress() * ((float) 100))));
        String str = c2Var.getStorylyLayerItem$storyly_release().f3614i;
        SharedPreferences ratingSharedPreferences = c2Var.getRatingSharedPreferences();
        Intrinsics.checkNotNullExpressionValue(ratingSharedPreferences, "ratingSharedPreferences");
        SharedPreferences.Editor edit = ratingSharedPreferences.edit();
        Intrinsics.checkExpressionValueIsNotNull(edit, "editor");
        edit.putInt(str, roundToInt);
        edit.apply();
        c2Var.a(c2Var.getAverage());
        Function5<com.appsamurai.storyly.analytics.a, b0, StoryComponent, JsonObject, Function1<? super Boolean, Unit>, Unit> onUserReaction$storyly_release = c2Var.getOnUserReaction$storyly_release();
        com.appsamurai.storyly.analytics.a aVar = com.appsamurai.storyly.analytics.a.Rated;
        b0 storylyLayerItem$storyly_release = c2Var.getStorylyLayerItem$storyly_release();
        b0 storylyLayerItem$storyly_release2 = c2Var.getStorylyLayerItem$storyly_release();
        StoryComponent a2 = storylyLayerItem$storyly_release2.f3615j.a(storylyLayerItem$storyly_release2, roundToInt);
        JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
        JsonElementBuildersKt.put(jsonObjectBuilder, "activity", String.valueOf(roundToInt));
        Unit unit = Unit.INSTANCE;
        onUserReaction$storyly_release.invoke(aVar, storylyLayerItem$storyly_release, a2, jsonObjectBuilder.build(), null);
    }

    public void a(@NotNull d dVar) {
        Intrinsics.checkNotNullParameter(dVar, "safeFrame");
        c();
        float b3 = dVar.b();
        dVar.a();
        addView(getRatingView(), new FrameLayout.LayoutParams(-1, -2));
        l0 l0Var = this.f5678n;
        l0 l0Var2 = null;
        if (l0Var == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            l0Var = null;
        }
        float f2 = (((l0Var.f3844f * 4.0f) + 55.0f) / ((float) 100)) * b3;
        RelativeLayout container = getContainer();
        l0 l0Var3 = this.f5678n;
        if (l0Var3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            l0Var3 = null;
        }
        int i3 = (Intrinsics.areEqual((Object) l0Var3.f3840b, (Object) "Dark") ? com.appsamurai.storyly.config.styling.a.COLOR_141414.b() : new com.appsamurai.storyly.data.c(-1)).f3624a;
        Drawable drawable = AppCompatResources.getDrawable(getContext(), R.drawable.st_rectangle_shape_drawable);
        if (drawable != null) {
            GradientDrawable gradientDrawable = (GradientDrawable) ((GradientDrawable) drawable).mutate();
            gradientDrawable.setColor(i3);
            gradientDrawable.setCornerRadius(TypedValue.applyDimension(1, 15.0f, getContext().getResources().getDisplayMetrics()));
            int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.st_rating_background_border_initial_thickness);
            l0 l0Var4 = this.f5678n;
            if (l0Var4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                l0Var4 = null;
            }
            com.appsamurai.storyly.data.c cVar = l0Var4.f3850l;
            if (cVar == null) {
                cVar = (Intrinsics.areEqual((Object) l0Var4.f3840b, (Object) "Dark") ? com.appsamurai.storyly.config.styling.a.COLOR_3D3D3D : com.appsamurai.storyly.config.styling.a.COLOR_E0E0E0).b();
            }
            gradientDrawable.setStroke(dimensionPixelSize, cVar.f3624a);
            Unit unit = Unit.INSTANCE;
            container.setBackground(gradientDrawable);
            Resources resources = getContext().getResources();
            int i4 = R.dimen.st_rating_tooltip_height;
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) getContext().getResources().getDimension(R.dimen.st_rating_tooltip_width), (int) resources.getDimension(i4));
            layoutParams.addRule(5, getRatingView().getId());
            layoutParams.addRule(3, getContainer().getId());
            layoutParams.topMargin = (int) (((double) getContext().getResources().getDimension(i4)) * -0.33d);
            getRatingView().addView(getRatingAverageView(), layoutParams);
            getRatingView().addView(getContainer(), new FrameLayout.LayoutParams(MathKt.roundToInt(f2), -2));
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, -2);
            Resources resources2 = getContext().getResources();
            int i5 = R.dimen.st_rating_child_horizontal_margin;
            layoutParams2.leftMargin = (int) resources2.getDimension(i5);
            layoutParams2.rightMargin = (int) getContext().getResources().getDimension(i5);
            Resources resources3 = getContext().getResources();
            int i6 = R.dimen.st_rating_child_vertical_margin;
            layoutParams2.topMargin = (int) resources3.getDimension(i6);
            getContainer().addView(getRatingTitle(), layoutParams2);
            l0 l0Var5 = this.f5678n;
            if (l0Var5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                l0Var5 = null;
            }
            if (!l0Var5.f3845g) {
                getRatingTitle().setVisibility(8);
            }
            FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(-1, -1);
            layoutParams3.gravity = 0;
            ViewParent parent = getParent();
            FrameLayout frameLayout = parent instanceof FrameLayout ? (FrameLayout) parent : null;
            if (frameLayout != null) {
                frameLayout.addView(getRatingAnimationView(), layoutParams3);
            }
            getRatingAnimationView().setVisibility(8);
            getRatingSlider().setSliderParticleSystem(getRatingAnimationView());
            RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams4.addRule(9);
            layoutParams4.addRule(11);
            layoutParams4.addRule(3, getRatingTitle().getId());
            layoutParams4.topMargin = (int) getContext().getResources().getDimension(i6);
            layoutParams4.bottomMargin = (int) getContext().getResources().getDimension(i6);
            getContainer().addView(getRatingSlider(), layoutParams4);
            FrameLayout.LayoutParams layoutParams5 = new FrameLayout.LayoutParams(MathKt.roundToInt(f2), -2);
            setLayoutParams(layoutParams5);
            measure(0, 0);
            setLayoutParams(o1.a(this, layoutParams5, getStorylyLayerItem$storyly_release().b().x, getStorylyLayerItem$storyly_release().b().y, 0.0f, 0.0f, 24, (Object) null));
            getRatingAverageView().setVisibility(8);
            int i7 = getRatingSharedPreferences().getInt(getStorylyLayerItem$storyly_release().f3614i, -1);
            Integer valueOf = i7 == -1 ? null : Integer.valueOf(i7);
            l0 l0Var6 = this.f5678n;
            if (l0Var6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                l0Var6 = null;
            }
            if (l0Var6.f3854p || valueOf != null) {
                getRatingAnimationView().setVisibility(8);
                ViewParent parent2 = getParent();
                FrameLayout frameLayout2 = parent2 instanceof FrameLayout ? (FrameLayout) parent2 : null;
                if (frameLayout2 != null) {
                    frameLayout2.removeView(getRatingAnimationView());
                }
                getRatingSlider().setUserSeekable(false);
                com.appsamurai.storyly.util.ui.slider.a ratingSlider = getRatingSlider();
                l0 l0Var7 = this.f5678n;
                if (l0Var7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                } else {
                    l0Var2 = l0Var7;
                }
                if (l0Var2.f3854p) {
                    valueOf = Integer.valueOf(getAverage());
                }
                ratingSlider.setProgress(valueOf == null ? 0.0f : ((float) valueOf.intValue()) / 100.0f);
                a(getAverage());
                return;
            }
            getRatingSlider().setUserSeekable(true);
            f();
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.graphics.drawable.GradientDrawable");
    }

    public final void a(int i3) {
        int i4;
        getRatingAverageView().removeAllViews();
        getRatingAverageView().setVisibility(0);
        getRatingAverageView().bringToFront();
        ViewGroup.LayoutParams layoutParams = getRatingAverageView().getLayoutParams();
        RelativeLayout.LayoutParams layoutParams2 = layoutParams instanceof RelativeLayout.LayoutParams ? (RelativeLayout.LayoutParams) layoutParams : null;
        if (layoutParams2 != null) {
            layoutParams2.leftMargin = (int) ((float) (((double) (((((float) getLayoutParams().width) - (getContext().getResources().getDimension(R.dimen.st_rating_child_horizontal_margin) * ((float) 2))) * ((float) i3)) / ((float) 100))) + (((double) getContext().getResources().getDimension(R.dimen.st_rating_tooltip_width)) * (i3 <= 25 ? 0.1d : i3 >= 75 ? -0.9d : -0.5d))));
        }
        RelativeLayout ratingAverageView = getRatingAverageView();
        if (i3 <= 25) {
            i4 = R.drawable.st_tooltip_left;
        } else if (i3 >= 75) {
            i4 = R.drawable.st_tooltip_right;
        } else {
            i4 = R.drawable.st_tooltip;
        }
        ratingAverageView.setBackgroundResource(i4);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams3.addRule(9);
        layoutParams3.addRule(11);
        layoutParams3.addRule(12);
        layoutParams3.bottomMargin = (int) getContext().getResources().getDimension(R.dimen.st_rating_average_text_bottom_margin);
        TextView textView = new TextView(getContext());
        textView.setText(textView.getContext().getString(R.string.average_answer_text));
        textView.setMaxLines(1);
        textView.setGravity(17);
        textView.setTextAlignment(1);
        textView.setTextColor(Color.parseColor("#262626"));
        textView.setTextSize(0, textView.getContext().getResources().getDimension(R.dimen.st_rating_average_text_font_size));
        textView.setTypeface(this.f5671g.getStory$storyly_release().getInteractiveTypeface$storyly_release());
        getRatingAverageView().addView(textView, layoutParams3);
    }

    public static final void a(c2 c2Var, ValueAnimator valueAnimator) {
        Intrinsics.checkNotNullParameter(c2Var, "this$0");
        com.appsamurai.storyly.util.ui.slider.a ratingSlider = c2Var.getRatingSlider();
        Object animatedValue = valueAnimator.getAnimatedValue();
        if (animatedValue != null) {
            ratingSlider.setProgress(((Float) animatedValue).floatValue());
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Float");
    }

    public static final void a(c2 c2Var) {
        c2Var.getRatingAnimationView().setVisibility(0);
        c2Var.getRatingAnimationView().bringToFront();
    }
}
