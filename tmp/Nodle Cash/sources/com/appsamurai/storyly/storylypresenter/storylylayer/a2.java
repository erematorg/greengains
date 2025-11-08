package com.appsamurai.storyly.storylypresenter.storylylayer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.AppCompatTextView;
import com.appsamurai.storyly.R;
import com.appsamurai.storyly.StoryComponent;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.data.a0;
import com.appsamurai.storyly.data.b0;
import com.appsamurai.storyly.data.j0;
import com.appsamurai.storyly.exoplayer2.core.ExoPlayer;
import com.appsamurai.storyly.util.ui.l;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.math.MathKt;
import kotlinx.serialization.json.JsonObject;
import org.jetbrains.annotations.NotNull;

@SuppressLint({"ViewConstructor"})
public final class a2 extends o1 {

    /* renamed from: s  reason: collision with root package name */
    public static final /* synthetic */ int f5576s = 0;
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public final StorylyConfig f5577g;
    @SuppressLint({"RtlHardcoded"})

    /* renamed from: h  reason: collision with root package name */
    public j0 f5578h;

    /* renamed from: i  reason: collision with root package name */
    public Function5<? super com.appsamurai.storyly.analytics.a, ? super b0, ? super StoryComponent, ? super JsonObject, ? super Function1<? super Boolean, Unit>, Unit> f5579i;

    /* renamed from: j  reason: collision with root package name */
    public final long f5580j = ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS;
    @NotNull

    /* renamed from: k  reason: collision with root package name */
    public final Lazy f5581k = LazyKt.lazy(f.f5595a);
    @NotNull

    /* renamed from: l  reason: collision with root package name */
    public final Lazy f5582l;
    @NotNull

    /* renamed from: m  reason: collision with root package name */
    public final Lazy f5583m;
    @NotNull

    /* renamed from: n  reason: collision with root package name */
    public final Lazy f5584n;
    @NotNull

    /* renamed from: o  reason: collision with root package name */
    public final Lazy f5585o;
    @NotNull

    /* renamed from: p  reason: collision with root package name */
    public final Lazy f5586p;
    @NotNull

    /* renamed from: q  reason: collision with root package name */
    public final Lazy f5587q;
    @NotNull

    /* renamed from: r  reason: collision with root package name */
    public final Lazy f5588r;

    public static final class a extends Lambda implements Function0<AppCompatTextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5589a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(Context context) {
            super(0);
            this.f5589a = context;
        }

        public Object invoke() {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f5589a);
            appCompatTextView.setId(View.generateViewId());
            appCompatTextView.setTextAlignment(1);
            appCompatTextView.setTextIsSelectable(false);
            return appCompatTextView;
        }
    }

    public static final class b extends Lambda implements Function0<ImageView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5590a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public b(Context context) {
            super(0);
            this.f5590a = context;
        }

        public Object invoke() {
            ImageView imageView = new ImageView(this.f5590a);
            imageView.setId(View.generateViewId());
            return imageView;
        }
    }

    public static final class d extends Lambda implements Function0<h2> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5593a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public d(Context context) {
            super(0);
            this.f5593a = context;
        }

        public Object invoke() {
            h2 h2Var = new h2(this.f5593a);
            h2Var.setId(View.generateViewId());
            return h2Var;
        }
    }

    public static final class e extends Lambda implements Function0<ImageView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5594a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public e(Context context) {
            super(0);
            this.f5594a = context;
        }

        public Object invoke() {
            ImageView imageView = new ImageView(this.f5594a);
            imageView.setId(View.generateViewId());
            return imageView;
        }
    }

    public static final class f extends Lambda implements Function0<Handler> {

        /* renamed from: a  reason: collision with root package name */
        public static final f f5595a = new f();

        public f() {
            super(0);
        }

        public Object invoke() {
            return new Handler(Looper.getMainLooper());
        }
    }

    public static final class g extends Lambda implements Function0<AppCompatTextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5596a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public g(Context context) {
            super(0);
            this.f5596a = context;
        }

        public Object invoke() {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f5596a);
            appCompatTextView.setId(View.generateViewId());
            appCompatTextView.setTextAlignment(1);
            appCompatTextView.setTextIsSelectable(false);
            return appCompatTextView;
        }
    }

    public static final class h extends Lambda implements Function0<RelativeLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5597a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public h(Context context) {
            super(0);
            this.f5597a = context;
        }

        public Object invoke() {
            RelativeLayout relativeLayout = new RelativeLayout(this.f5597a);
            relativeLayout.setId(View.generateViewId());
            relativeLayout.setClipChildren(false);
            relativeLayout.setClipToPadding(false);
            relativeLayout.setAlpha(0.0f);
            relativeLayout.setVisibility(4);
            l.b(relativeLayout);
            return relativeLayout;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public a2(@NotNull Context context, @NotNull StorylyConfig storylyConfig) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        this.f5577g = storylyConfig;
        this.f5582l = LazyKt.lazy(new c(context, this));
        this.f5583m = LazyKt.lazy(new a(context));
        this.f5584n = LazyKt.lazy(new d(context));
        this.f5585o = LazyKt.lazy(new b(context));
        this.f5586p = LazyKt.lazy(new h(context));
        this.f5587q = LazyKt.lazy(new e(context));
        this.f5588r = LazyKt.lazy(new g(context));
        l.b(this);
    }

    public static final void b(a2 a2Var) {
        Intrinsics.checkNotNullParameter(a2Var, "this$0");
        a2Var.getToolTipView().setPivotX((float) (a2Var.getToolTipView().getWidth() / 2));
    }

    private final AppCompatTextView getCodeTextView() {
        return (AppCompatTextView) this.f5583m.getValue();
    }

    private final ImageView getCopyImageView() {
        return (ImageView) this.f5585o.getValue();
    }

    private final e getPromoCodeView() {
        return (e) this.f5582l.getValue();
    }

    private final h2 getSeparatorLineView() {
        return (h2) this.f5584n.getValue();
    }

    private final ImageView getToolTipArrowImageView() {
        return (ImageView) this.f5587q.getValue();
    }

    private final Handler getToolTipHandler() {
        return (Handler) this.f5581k.getValue();
    }

    private final AppCompatTextView getToolTipTextView() {
        return (AppCompatTextView) this.f5588r.getValue();
    }

    private final RelativeLayout getToolTipView() {
        return (RelativeLayout) this.f5586p.getValue();
    }

    public void a(@NotNull b0 b0Var) {
        Intrinsics.checkNotNullParameter(b0Var, "storylyLayerItem");
        a0 a0Var = b0Var.f3615j;
        j0 j0Var = null;
        j0 j0Var2 = a0Var instanceof j0 ? (j0) a0Var : null;
        if (j0Var2 != null) {
            this.f5578h = j0Var2;
            setStorylyLayerItem$storyly_release(b0Var);
            AppCompatTextView codeTextView = getCodeTextView();
            j0 j0Var3 = this.f5578h;
            if (j0Var3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            } else {
                j0Var = j0Var3;
            }
            codeTextView.setText(j0Var.f3793a);
            getCodeTextView().setGravity(1);
            getToolTipTextView().setText(getContext().getString(R.string.st_promo_code_tooltip_copied_text));
            setRotation(b0Var.f3613h);
            getOnLayerLoad$storyly_release().invoke();
        }
    }

    public void c() {
        ViewParent parent = getParent();
        FrameLayout frameLayout = parent instanceof FrameLayout ? (FrameLayout) parent : null;
        if (frameLayout != null) {
            frameLayout.removeView(getToolTipView());
        }
        getToolTipView().removeAllViews();
        getPromoCodeView().removeAllViews();
        getToolTipHandler().removeCallbacksAndMessages((Object) null);
        removeAllViews();
    }

    public void d() {
        if (getToolTipView().getVisibility() == 0) {
            f();
        }
    }

    public final void f() {
        RelativeLayout toolTipView = getToolTipView();
        toolTipView.animate().cancel();
        toolTipView.animate().setDuration(400).alpha(0.0f).withEndAction(new G0.a(toolTipView, 0));
    }

    public final void g() {
        if (getToolTipView().getVisibility() != 0) {
            getToolTipHandler().removeCallbacksAndMessages((Object) null);
            RelativeLayout toolTipView = getToolTipView();
            toolTipView.setVisibility(0);
            toolTipView.setAlpha(0.0f);
            toolTipView.animate().cancel();
            toolTipView.animate().setDuration(300).alpha(1.0f);
            getToolTipHandler().postDelayed(new G0.b(this, 0), this.f5580j);
        }
    }

    @NotNull
    public final Function5<com.appsamurai.storyly.analytics.a, b0, StoryComponent, JsonObject, Function1<? super Boolean, Unit>, Unit> getOnUserReaction$storyly_release() {
        Function5<? super com.appsamurai.storyly.analytics.a, ? super b0, ? super StoryComponent, ? super JsonObject, ? super Function1<? super Boolean, Unit>, Unit> function5 = this.f5579i;
        if (function5 != null) {
            return function5;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onUserReaction");
        return null;
    }

    public final void setOnUserReaction$storyly_release(@NotNull Function5<? super com.appsamurai.storyly.analytics.a, ? super b0, ? super StoryComponent, ? super JsonObject, ? super Function1<? super Boolean, Unit>, Unit> function5) {
        Intrinsics.checkNotNullParameter(function5, "<set-?>");
        this.f5579i = function5;
    }

    public static final class c extends Lambda implements Function0<e> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5591a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ a2 f5592b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public c(Context context, a2 a2Var) {
            super(0);
            this.f5591a = context;
            this.f5592b = a2Var;
        }

        @NotNull
        /* renamed from: a */
        public final e invoke() {
            e eVar = new e(this.f5591a);
            a2 a2Var = this.f5592b;
            eVar.setId(View.generateViewId());
            eVar.setClipChildren(false);
            eVar.setClipToPadding(false);
            eVar.setOnClickListener(new E0.c(a2Var, 1));
            return eVar;
        }

        public static final void a(a2 a2Var, View view) {
            Intrinsics.checkNotNullParameter(a2Var, "this$0");
            int i3 = a2.f5576s;
            Context context = a2Var.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "context");
            j0 j0Var = a2Var.f5578h;
            if (j0Var == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                j0Var = null;
            }
            com.appsamurai.storyly.util.g.a(context, "promoCode", j0Var.f3793a);
            a2Var.g();
            Function5<com.appsamurai.storyly.analytics.a, b0, StoryComponent, JsonObject, Function1<? super Boolean, Unit>, Unit> onUserReaction$storyly_release = a2Var.getOnUserReaction$storyly_release();
            com.appsamurai.storyly.analytics.a aVar = com.appsamurai.storyly.analytics.a.PromoCodeCopied;
            b0 storylyLayerItem$storyly_release = a2Var.getStorylyLayerItem$storyly_release();
            b0 storylyLayerItem$storyly_release2 = a2Var.getStorylyLayerItem$storyly_release();
            onUserReaction$storyly_release.invoke(aVar, storylyLayerItem$storyly_release, storylyLayerItem$storyly_release2.f3615j.a(storylyLayerItem$storyly_release2, Integer.MIN_VALUE), null, null);
        }
    }

    public void a(@NotNull d dVar) {
        float f2;
        Intrinsics.checkNotNullParameter(dVar, "safeFrame");
        c();
        float b3 = dVar.b();
        float a2 = dVar.a();
        float f3 = (float) 100;
        FrameLayout.LayoutParams a3 = a(new FrameLayout.LayoutParams(A.a.a(getStorylyLayerItem$storyly_release().f3609d, f3, b3), A.a.a(getStorylyLayerItem$storyly_release().f3610e, f3, a2)), getStorylyLayerItem$storyly_release().b().x, getStorylyLayerItem$storyly_release().b().y, dVar.c(), dVar.d());
        setLayoutParams(a3);
        j0 j0Var = this.f5578h;
        if (j0Var == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            j0Var = null;
        }
        Float f4 = j0Var.f3795c;
        if (f4 == null) {
            f2 = getStorylyLayerItem$storyly_release().f3610e / ((float) 2);
        } else {
            f2 = f4.floatValue();
        }
        float f5 = (f2 / f3) * a2;
        int i3 = a3.height;
        float f6 = (float) (((double) (i3 / 2)) * 0.4d);
        float f7 = (float) i3;
        int i4 = (int) ((f7 - f5) / ((float) 8));
        float f8 = 0.03f * f7;
        float f9 = f7 / 6.0f;
        addView(getPromoCodeView(), new FrameLayout.LayoutParams(-1, -1));
        e promoCodeView = getPromoCodeView();
        j0 j0Var2 = this.f5578h;
        if (j0Var2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            j0Var2 = null;
        }
        promoCodeView.f5712a = j0Var2.a().f3624a;
        getPromoCodeView().f5713b = f8;
        e promoCodeView2 = getPromoCodeView();
        j0 j0Var3 = this.f5578h;
        if (j0Var3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            j0Var3 = null;
        }
        promoCodeView2.f5714c = j0Var3.b().f3624a;
        getPromoCodeView().f5715d = f6;
        getPromoCodeView().f5716e = f9;
        int i5 = ((int) f9) + i4;
        getPromoCodeView().setPaddingRelative(i4, 0, i5, 0);
        e promoCodeView3 = getPromoCodeView();
        AppCompatTextView codeTextView = getCodeTextView();
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, MathKt.roundToInt(f5));
        layoutParams.addRule(20);
        layoutParams.addRule(16, getSeparatorLineView().getId());
        layoutParams.addRule(15);
        Unit unit = Unit.INSTANCE;
        promoCodeView3.addView(codeTextView, layoutParams);
        AppCompatTextView codeTextView2 = getCodeTextView();
        codeTextView2.setTypeface(this.f5577g.getStory$storyly_release().getInteractiveTypeface$storyly_release());
        j0 j0Var4 = this.f5578h;
        if (j0Var4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            j0Var4 = null;
        }
        boolean z2 = j0Var4.f3799g;
        j0 j0Var5 = this.f5578h;
        if (j0Var5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            j0Var5 = null;
        }
        com.appsamurai.storyly.util.c.a(codeTextView2, z2, j0Var5.f3800h);
        j0 j0Var6 = this.f5578h;
        if (j0Var6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            j0Var6 = null;
        }
        codeTextView2.setTextColor(j0Var6.c().f3624a);
        codeTextView2.setFirstBaselineToTopHeight(0);
        codeTextView2.setIncludeFontPadding(false);
        codeTextView2.setMaxLines(1);
        TextUtils.TruncateAt truncateAt = TextUtils.TruncateAt.END;
        codeTextView2.setEllipsize(truncateAt);
        codeTextView2.setGravity(17);
        codeTextView2.setTextSize(0, 0.75f * f5);
        int i6 = (int) f5;
        codeTextView2.setLineHeight(i6);
        codeTextView2.setPaddingRelative(i5, 0, i4, 0);
        e promoCodeView4 = getPromoCodeView();
        h2 separatorLineView = getSeparatorLineView();
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams((int) f8, -1);
        layoutParams2.addRule(16, getCopyImageView().getId());
        layoutParams2.setMarginEnd(i4);
        promoCodeView4.addView(separatorLineView, layoutParams2);
        h2 separatorLineView2 = getSeparatorLineView();
        j0 j0Var7 = this.f5578h;
        if (j0Var7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            j0Var7 = null;
        }
        separatorLineView2.f5760b = j0Var7.b().f3624a;
        getSeparatorLineView().f5759a = f8;
        e promoCodeView5 = getPromoCodeView();
        ImageView copyImageView = getCopyImageView();
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(i6, i6);
        layoutParams3.addRule(21);
        layoutParams3.addRule(15);
        promoCodeView5.addView(copyImageView, layoutParams3);
        ImageView copyImageView2 = getCopyImageView();
        copyImageView2.setImageResource(R.drawable.st_promo_code_copy);
        ImageView.ScaleType scaleType = ImageView.ScaleType.FIT_CENTER;
        copyImageView2.setScaleType(scaleType);
        float f10 = 0.018f * a2;
        float f11 = 0.9f * f10;
        float f12 = 0.5f * f10;
        float f13 = 0.1f * f10;
        float f14 = 0.7f * f10;
        float f15 = 0.15f * f10;
        RelativeLayout toolTipView = getToolTipView();
        AppCompatTextView toolTipTextView = getToolTipTextView();
        float f16 = 0.2f * f10;
        FrameLayout.LayoutParams layoutParams4 = a3;
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams5.addRule(14);
        toolTipView.addView(toolTipTextView, layoutParams5);
        AppCompatTextView toolTipTextView2 = getToolTipTextView();
        toolTipTextView2.setTypeface(this.f5577g.getStory$storyly_release().getInteractiveTypeface$storyly_release());
        com.appsamurai.storyly.util.c.a(toolTipTextView2, false, false);
        j0 j0Var8 = this.f5578h;
        if (j0Var8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            j0Var8 = null;
        }
        toolTipTextView2.setTextColor(j0Var8.c().f3624a);
        toolTipTextView2.setFirstBaselineToTopHeight(0);
        toolTipTextView2.setIncludeFontPadding(false);
        toolTipTextView2.setMaxLines(1);
        toolTipTextView2.setEllipsize(truncateAt);
        toolTipTextView2.setTextSize(0, f10);
        int i7 = (int) f11;
        int i8 = (int) f12;
        toolTipTextView2.setPadding(i7, i8, i7, i8);
        GradientDrawable gradientDrawable = new GradientDrawable();
        j0 j0Var9 = this.f5578h;
        if (j0Var9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            j0Var9 = null;
        }
        gradientDrawable.setColor(j0Var9.a().f3624a);
        int i9 = (int) f13;
        j0 j0Var10 = this.f5578h;
        if (j0Var10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            j0Var10 = null;
        }
        gradientDrawable.setStroke(i9, j0Var10.b().f3624a);
        gradientDrawable.setCornerRadii(new float[]{f10, f10, f10, f10, f10, f10, f10, f10});
        toolTipTextView2.setBackground(gradientDrawable);
        getToolTipTextView().measure(0, 0);
        RelativeLayout toolTipView2 = getToolTipView();
        ImageView toolTipArrowImageView = getToolTipArrowImageView();
        int i10 = (int) f14;
        RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(i10, i10);
        layoutParams6.addRule(12);
        layoutParams6.addRule(14);
        toolTipView2.addView(toolTipArrowImageView, layoutParams6);
        ImageView toolTipArrowImageView2 = getToolTipArrowImageView();
        toolTipArrowImageView2.setPadding(0, 0, 0, 0);
        j0 j0Var11 = this.f5578h;
        if (j0Var11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            j0Var11 = null;
        }
        toolTipArrowImageView2.setImageResource(Intrinsics.areEqual((Object) j0Var11.f3794b, (Object) "Dark") ? R.drawable.st_promo_code_arrow_dark : R.drawable.st_promo_code_arrow_light);
        toolTipArrowImageView2.setScaleType(scaleType);
        int measuredHeight = (i10 - ((int) f15)) + getToolTipTextView().getMeasuredHeight();
        post(new G0.b(this, 1));
        getToolTipView().setPivotY((((a2 * getStorylyLayerItem$storyly_release().f3610e) / f3) / ((float) 2)) + ((float) measuredHeight));
        getToolTipView().setRotation(getStorylyLayerItem$storyly_release().f3613h);
        ViewParent parent = getParent();
        FrameLayout frameLayout = parent instanceof FrameLayout ? (FrameLayout) parent : null;
        if (frameLayout != null) {
            RelativeLayout toolTipView3 = getToolTipView();
            FrameLayout.LayoutParams layoutParams7 = layoutParams4;
            FrameLayout.LayoutParams layoutParams8 = new FrameLayout.LayoutParams(layoutParams7.width, measuredHeight);
            layoutParams8.topMargin = (layoutParams7.topMargin - measuredHeight) - ((int) f16);
            layoutParams8.leftMargin = layoutParams7.leftMargin;
            layoutParams8.gravity = 0;
            frameLayout.addView(toolTipView3, layoutParams8);
        }
    }

    public static final void a(a2 a2Var) {
        Intrinsics.checkNotNullParameter(a2Var, "this$0");
        a2Var.f();
    }

    public static final void a(RelativeLayout relativeLayout) {
        Intrinsics.checkNotNullParameter(relativeLayout, "$this_apply");
        relativeLayout.setVisibility(8);
    }
}
