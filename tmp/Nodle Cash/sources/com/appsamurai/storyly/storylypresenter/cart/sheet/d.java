package com.appsamurai.storyly.storylypresenter.cart.sheet;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.GravityCompat;
import androidx.core.widget.NestedScrollView;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;
import com.appsamurai.storyly.R;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.data.g0;
import com.appsamurai.storyly.data.managers.product.STRCart;
import com.appsamurai.storyly.data.managers.product.STRCartEventResult;
import com.appsamurai.storyly.data.managers.product.STRCartItem;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressLint({"ViewConstructor"})
public final class d extends FrameLayout {

    /* renamed from: w  reason: collision with root package name */
    public static final /* synthetic */ int f4859w = 0;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final StorylyConfig f4860a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final STRCart f4861b;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    public final g0 f4862c;
    @NotNull

    /* renamed from: d  reason: collision with root package name */
    public final Function0<Unit> f4863d;
    @NotNull

    /* renamed from: e  reason: collision with root package name */
    public com.appsamurai.storyly.databinding.a f4864e;
    @Nullable

    /* renamed from: f  reason: collision with root package name */
    public BottomSheetBehavior<FrameLayout> f4865f;
    @Nullable

    /* renamed from: g  reason: collision with root package name */
    public ObjectAnimator f4866g;

    /* renamed from: h  reason: collision with root package name */
    public int f4867h;
    @NotNull

    /* renamed from: i  reason: collision with root package name */
    public Function0<Unit> f4868i = j.f4894a;
    @Nullable

    /* renamed from: j  reason: collision with root package name */
    public Function4<? super STRCartItem, ? super Integer, ? super Function1<? super STRCart, Unit>, ? super Function1<? super STRCartEventResult, Unit>, Unit> f4869j = k.f4895a;
    @NotNull

    /* renamed from: k  reason: collision with root package name */
    public b f4870k = b.Default;
    @NotNull

    /* renamed from: l  reason: collision with root package name */
    public final Lazy f4871l;
    @NotNull

    /* renamed from: m  reason: collision with root package name */
    public final Lazy f4872m;
    @NotNull

    /* renamed from: n  reason: collision with root package name */
    public final Lazy f4873n;
    @NotNull

    /* renamed from: o  reason: collision with root package name */
    public final Lazy f4874o;
    @NotNull

    /* renamed from: p  reason: collision with root package name */
    public final Lazy f4875p;
    @NotNull

    /* renamed from: q  reason: collision with root package name */
    public final Lazy f4876q;
    @NotNull

    /* renamed from: r  reason: collision with root package name */
    public final Lazy f4877r;
    @NotNull

    /* renamed from: s  reason: collision with root package name */
    public final Lazy f4878s;
    @NotNull

    /* renamed from: t  reason: collision with root package name */
    public final Lazy f4879t;
    @NotNull

    /* renamed from: u  reason: collision with root package name */
    public final Lazy f4880u;
    @NotNull

    /* renamed from: v  reason: collision with root package name */
    public final Lazy f4881v;

    public static final class a extends Lambda implements Function0<i> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f4882a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ d f4883b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(Context context, d dVar) {
            super(0);
            this.f4882a = context;
            this.f4883b = dVar;
        }

        public Object invoke() {
            Context context = this.f4882a;
            d dVar = this.f4883b;
            i iVar = new i(context, (AttributeSet) null, 0, dVar.f4860a, dVar.f4862c, dVar.f4861b);
            iVar.setOnGoToCheckout$storyly_release(new c(this.f4883b));
            return iVar;
        }
    }

    public static final class b extends Lambda implements Function0<com.appsamurai.storyly.storylypresenter.cart.list.f> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f4884a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ d f4885b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public b(Context context, d dVar) {
            super(0);
            this.f4884a = context;
            this.f4885b = dVar;
        }

        public Object invoke() {
            com.appsamurai.storyly.storylypresenter.cart.list.f fVar = new com.appsamurai.storyly.storylypresenter.cart.list.f(this.f4884a, (AttributeSet) null, 0, this.f4885b.f4860a);
            fVar.setOnUpdateCart$storyly_release(new g(this.f4885b, fVar));
            return fVar;
        }
    }

    /* renamed from: com.appsamurai.storyly.storylypresenter.cart.sheet.d$d  reason: collision with other inner class name */
    public static final class C0020d extends Lambda implements Function0<LinearLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f4888a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public C0020d(Context context) {
            super(0);
            this.f4888a = context;
        }

        public Object invoke() {
            LinearLayout linearLayout = new LinearLayout(this.f4888a);
            linearLayout.setOrientation(1);
            return linearLayout;
        }
    }

    public static final class e extends Lambda implements Function0<LinearLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f4889a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public e(Context context) {
            super(0);
            this.f4889a = context;
        }

        public Object invoke() {
            LinearLayout linearLayout = new LinearLayout(this.f4889a);
            linearLayout.setOrientation(1);
            linearLayout.setGravity(GravityCompat.END);
            return linearLayout;
        }
    }

    public static final class f extends Lambda implements Function0<FrameLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f4890a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public f(Context context) {
            super(0);
            this.f4890a = context;
        }

        public Object invoke() {
            FrameLayout frameLayout = new FrameLayout(this.f4890a);
            frameLayout.setBackgroundColor(Color.parseColor("#EEEEEE"));
            return frameLayout;
        }
    }

    public static final class g extends Lambda implements Function0<LinearLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f4891a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public g(Context context) {
            super(0);
            this.f4891a = context;
        }

        public Object invoke() {
            LinearLayout linearLayout = new LinearLayout(this.f4891a);
            linearLayout.setOrientation(0);
            linearLayout.setGravity(16);
            linearLayout.setVisibility(8);
            return linearLayout;
        }
    }

    public static final class h extends Lambda implements Function0<AppCompatImageView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f4892a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public h(Context context) {
            super(0);
            this.f4892a = context;
        }

        public Object invoke() {
            AppCompatImageView appCompatImageView = new AppCompatImageView(this.f4892a);
            appCompatImageView.setImageResource(R.drawable.st_round_error);
            appCompatImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return appCompatImageView;
        }
    }

    public static final class i extends Lambda implements Function0<AppCompatTextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f4893a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public i(Context context) {
            super(0);
            this.f4893a = context;
        }

        public Object invoke() {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f4893a);
            appCompatTextView.setEllipsize(TextUtils.TruncateAt.END);
            appCompatTextView.setMaxLines(3);
            appCompatTextView.setIncludeFontPadding(false);
            appCompatTextView.setHorizontallyScrolling(false);
            appCompatTextView.setGravity(GravityCompat.START);
            appCompatTextView.setTextColor(Color.parseColor("#212121"));
            appCompatTextView.setTextAlignment(1);
            com.appsamurai.storyly.util.d.a(appCompatTextView);
            return appCompatTextView;
        }
    }

    public static final class j extends Lambda implements Function0<Unit> {

        /* renamed from: a  reason: collision with root package name */
        public static final j f4894a = new j();

        public j() {
            super(0);
        }

        public /* bridge */ /* synthetic */ Object invoke() {
            return Unit.INSTANCE;
        }
    }

    public static final class k extends Lambda implements Function4<STRCartItem, Integer, Function1<? super STRCart, ? extends Unit>, Function1<? super STRCartEventResult, ? extends Unit>, Unit> {

        /* renamed from: a  reason: collision with root package name */
        public static final k f4895a = new k();

        public k() {
            super(4);
        }

        public Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            STRCartItem sTRCartItem = (STRCartItem) obj;
            ((Number) obj2).intValue();
            Intrinsics.checkNotNullParameter((Function1) obj3, "$noName_2");
            Intrinsics.checkNotNullParameter((Function1) obj4, "$noName_3");
            return Unit.INSTANCE;
        }
    }

    public static final class l extends Lambda implements Function0<NestedScrollView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f4896a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public l(Context context) {
            super(0);
            this.f4896a = context;
        }

        public Object invoke() {
            return new NestedScrollView(this.f4896a);
        }
    }

    public static final class m extends Lambda implements Function0<LinearLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f4897a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public m(Context context) {
            super(0);
            this.f4897a = context;
        }

        public Object invoke() {
            LinearLayout linearLayout = new LinearLayout(this.f4897a);
            linearLayout.setOrientation(1);
            return linearLayout;
        }
    }

    public static final class n extends BottomSheetBehavior.BottomSheetCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ BottomSheetBehavior<FrameLayout> f4898a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ d f4899b;

        public n(BottomSheetBehavior<FrameLayout> bottomSheetBehavior, d dVar) {
            this.f4898a = bottomSheetBehavior;
            this.f4899b = dVar;
        }

        public void onSlide(@NotNull View view, float f2) {
            Intrinsics.checkNotNullParameter(view, "bottomSheet");
        }

        public void onStateChanged(@NotNull View view, int i3) {
            Intrinsics.checkNotNullParameter(view, "bottomSheet");
            if (this.f4898a.getState() == 5) {
                ViewParent parent = this.f4899b.getParent();
                ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
                if (viewGroup != null) {
                    viewGroup.removeView(this.f4899b);
                }
                d dVar = this.f4899b;
                if (dVar.f4870k == b.WithGoToCheckout) {
                    dVar.getOnGoToCheckout$storyly_release().invoke();
                } else {
                    dVar.f4863d.invoke();
                }
            }
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public d(@NotNull Context context, @NotNull StorylyConfig storylyConfig, @NotNull STRCart sTRCart, @Nullable g0 g0Var, @NotNull Function0<Unit> function0) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        Intrinsics.checkNotNullParameter(sTRCart, "cart");
        Intrinsics.checkNotNullParameter(function0, "onStateChanged");
        this.f4860a = storylyConfig;
        this.f4861b = sTRCart;
        this.f4862c = g0Var;
        this.f4863d = function0;
        com.appsamurai.storyly.databinding.a a2 = com.appsamurai.storyly.databinding.a.a(LayoutInflater.from(context));
        Intrinsics.checkNotNullExpressionValue(a2, "inflate(LayoutInflater.from(context))");
        this.f4864e = a2;
        this.f4871l = LazyKt.lazy(new C0020d(context));
        this.f4872m = LazyKt.lazy(new e(context));
        this.f4873n = LazyKt.lazy(new c(context, this));
        this.f4874o = LazyKt.lazy(new f(context));
        this.f4875p = LazyKt.lazy(new l(context));
        this.f4876q = LazyKt.lazy(new m(context));
        this.f4877r = LazyKt.lazy(new b(context, this));
        this.f4878s = LazyKt.lazy(new a(context, this));
        this.f4879t = LazyKt.lazy(new g(context));
        this.f4880u = LazyKt.lazy(new h(context));
        this.f4881v = LazyKt.lazy(new i(context));
        b();
        a();
    }

    /* access modifiers changed from: private */
    public final i getBottomIndicator() {
        return (i) this.f4878s.getValue();
    }

    /* access modifiers changed from: private */
    public final com.appsamurai.storyly.storylypresenter.cart.list.f getCartRecyclerView() {
        return (com.appsamurai.storyly.storylypresenter.cart.list.f) this.f4877r.getValue();
    }

    private final AppCompatImageView getCloseIcon() {
        return (AppCompatImageView) this.f4873n.getValue();
    }

    private final LinearLayout getContentContainer() {
        return (LinearLayout) this.f4871l.getValue();
    }

    private final LinearLayout getHeaderContainer() {
        return (LinearLayout) this.f4872m.getValue();
    }

    private final FrameLayout getHeaderSeperator() {
        return (FrameLayout) this.f4874o.getValue();
    }

    /* access modifiers changed from: private */
    public final LinearLayout getMessageContainer() {
        return (LinearLayout) this.f4879t.getValue();
    }

    private final AppCompatImageView getMessageIcon() {
        return (AppCompatImageView) this.f4880u.getValue();
    }

    /* access modifiers changed from: private */
    public final AppCompatTextView getMessageText() {
        return (AppCompatTextView) this.f4881v.getValue();
    }

    /* access modifiers changed from: private */
    public final NestedScrollView getScrollView() {
        return (NestedScrollView) this.f4875p.getValue();
    }

    private final LinearLayout getScrollViewContainer() {
        return (LinearLayout) this.f4876q.getValue();
    }

    @NotNull
    public final Function0<Unit> getOnGoToCheckout$storyly_release() {
        return this.f4868i;
    }

    @Nullable
    public final Function4<STRCartItem, Integer, Function1<? super STRCart, Unit>, Function1<? super STRCartEventResult, Unit>, Unit> getOnUpdateCart$storyly_release() {
        return this.f4869j;
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ObjectAnimator objectAnimator = this.f4866g;
        if (objectAnimator != null) {
            objectAnimator.removeAllListeners();
        }
        ObjectAnimator objectAnimator2 = this.f4866g;
        if (objectAnimator2 != null) {
            objectAnimator2.end();
        }
        ObjectAnimator objectAnimator3 = this.f4866g;
        if (objectAnimator3 != null) {
            objectAnimator3.cancel();
        }
        this.f4864e.f4325d.clearAnimation();
    }

    public final void setOnGoToCheckout$storyly_release(@NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.f4868i = function0;
    }

    public final void setOnUpdateCart$storyly_release(@Nullable Function4<? super STRCartItem, ? super Integer, ? super Function1<? super STRCart, Unit>, ? super Function1<? super STRCartEventResult, Unit>, Unit> function4) {
        this.f4869j = function4;
    }

    public final void a() {
        BottomSheetBehavior<FrameLayout> from = BottomSheetBehavior.from(this.f4864e.f4323b);
        from.setPeekHeight(((int) (((double) com.appsamurai.storyly.util.m.b().height()) * 0.9d)) - this.f4867h);
        from.setHideable(true);
        from.setState(5);
        from.addBottomSheetCallback(new n(from, this));
        Unit unit = Unit.INSTANCE;
        this.f4865f = from;
    }

    public final void b() {
        this.f4867h = (int) (((double) com.appsamurai.storyly.util.m.b().height()) * 0.137d);
        float width = (float) (((double) com.appsamurai.storyly.util.m.b().width()) * 0.033d);
        int width2 = (int) (((double) com.appsamurai.storyly.util.m.b().width()) * 0.066d);
        int width3 = (int) (((double) com.appsamurai.storyly.util.m.b().width()) * 0.033d);
        int width4 = (int) (((double) com.appsamurai.storyly.util.m.b().width()) * 0.136d);
        int width5 = (int) (((double) com.appsamurai.storyly.util.m.b().width()) * 0.033d);
        int width6 = (int) (((double) com.appsamurai.storyly.util.m.b().width()) * 0.0027d);
        int width7 = (int) (((double) com.appsamurai.storyly.util.m.b().width()) * 0.027d);
        RelativeLayout relativeLayout = this.f4864e.f4322a;
        Class cls = Integer.TYPE;
        float f2 = width;
        int i3 = width6;
        Class<ViewGroup.LayoutParams> cls2 = ViewGroup.LayoutParams.class;
        int width8 = (int) (((double) com.appsamurai.storyly.util.m.b().width()) * 0.038d);
        ViewGroup.LayoutParams newInstance = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-1, -1});
        Intrinsics.checkNotNullExpressionValue(newInstance, "layoutParams");
        Unit unit = Unit.INSTANCE;
        addView(relativeLayout, newInstance);
        FrameLayout frameLayout = this.f4864e.f4323b;
        LinearLayout contentContainer = getContentContainer();
        int width9 = (int) (((double) com.appsamurai.storyly.util.m.b().width()) * 0.061d);
        Class<FrameLayout.LayoutParams> cls3 = FrameLayout.LayoutParams.class;
        int width10 = (int) (((double) com.appsamurai.storyly.util.m.b().width()) * 0.05d);
        ViewGroup.LayoutParams newInstance2 = cls3.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-1, -2});
        Intrinsics.checkNotNullExpressionValue(newInstance2, "layoutParams");
        frameLayout.addView(contentContainer, newInstance2);
        LinearLayout contentContainer2 = getContentContainer();
        LinearLayout headerContainer = getHeaderContainer();
        ViewGroup.LayoutParams newInstance3 = cls3.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-1, -2});
        Intrinsics.checkNotNullExpressionValue(newInstance3, "layoutParams");
        contentContainer2.addView(headerContainer, newInstance3);
        LinearLayout headerContainer2 = getHeaderContainer();
        AppCompatImageView closeIcon = getCloseIcon();
        int width11 = (int) (((double) com.appsamurai.storyly.util.m.b().width()) * 0.055d);
        ViewGroup.LayoutParams newInstance4 = cls3.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{Integer.valueOf(width2), Integer.valueOf(width2)});
        Intrinsics.checkNotNullExpressionValue(newInstance4, "layoutParams");
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) newInstance4;
        layoutParams.setMarginEnd(width3);
        layoutParams.topMargin = width3;
        headerContainer2.addView(closeIcon, newInstance4);
        FrameLayout headerSeperator = getHeaderSeperator();
        ViewGroup.LayoutParams newInstance5 = cls3.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-1, 1});
        Intrinsics.checkNotNullExpressionValue(newInstance5, "layoutParams");
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) newInstance5;
        layoutParams2.gravity = GravityCompat.END;
        layoutParams2.topMargin = (int) (((double) com.appsamurai.storyly.util.m.b().width()) * 0.033d);
        headerContainer2.addView(headerSeperator, newInstance5);
        NestedScrollView scrollView = getScrollView();
        ViewGroup.LayoutParams newInstance6 = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-1, -2});
        Intrinsics.checkNotNullExpressionValue(newInstance6, "layoutParams");
        contentContainer2.addView(scrollView, newInstance6);
        NestedScrollView scrollView2 = getScrollView();
        LinearLayout scrollViewContainer = getScrollViewContainer();
        ViewGroup.LayoutParams newInstance7 = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-1, -2});
        Intrinsics.checkNotNullExpressionValue(newInstance7, "layoutParams");
        scrollView2.addView((View) scrollViewContainer, newInstance7);
        LinearLayout scrollViewContainer2 = getScrollViewContainer();
        LinearLayout messageContainer = getMessageContainer();
        ViewGroup.LayoutParams newInstance8 = cls3.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-1, -2});
        Intrinsics.checkNotNullExpressionValue(newInstance8, "layoutParams");
        FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) newInstance8;
        layoutParams3.topMargin = width5;
        layoutParams3.leftMargin = width4;
        layoutParams3.rightMargin = width4;
        scrollViewContainer2.addView(messageContainer, newInstance8);
        LinearLayout messageContainer2 = getMessageContainer();
        AppCompatImageView messageIcon = getMessageIcon();
        ViewGroup.LayoutParams newInstance9 = cls3.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{Integer.valueOf(width11), Integer.valueOf(width11)});
        Intrinsics.checkNotNullExpressionValue(newInstance9, "layoutParams");
        ((FrameLayout.LayoutParams) newInstance9).setMarginStart(width10);
        messageContainer2.addView(messageIcon, newInstance9);
        AppCompatTextView messageText = getMessageText();
        ViewGroup.LayoutParams newInstance10 = cls3.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-2, -2});
        Intrinsics.checkNotNullExpressionValue(newInstance10, "layoutParams");
        FrameLayout.LayoutParams layoutParams4 = (FrameLayout.LayoutParams) newInstance10;
        int i4 = width7;
        layoutParams4.setMarginStart(i4);
        layoutParams4.setMarginEnd(i4);
        int i5 = width9;
        layoutParams4.topMargin = i5;
        layoutParams4.bottomMargin = i5;
        messageContainer2.addView(messageText, newInstance10);
        com.appsamurai.storyly.storylypresenter.cart.list.f cartRecyclerView = getCartRecyclerView();
        ViewGroup.LayoutParams newInstance11 = LinearLayout.LayoutParams.class.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-1, -2});
        Intrinsics.checkNotNullExpressionValue(newInstance11, "layoutParams");
        scrollViewContainer2.addView(cartRecyclerView, newInstance11);
        AppCompatTextView messageText2 = getMessageText();
        messageText2.setTypeface(this.f4860a.getStory$storyly_release().getInteractiveTypeface$storyly_release());
        messageText2.setTextSize(0, (float) width8);
        getMessageContainer().setBackground(com.appsamurai.storyly.util.ui.b.a(this, Color.parseColor("#E0E0E0"), (float) (((double) com.appsamurai.storyly.util.m.b().width()) * 0.044d), Integer.valueOf(Color.parseColor("#E0E0E0")), i3));
        this.f4864e.f4323b.setBackground(com.appsamurai.storyly.util.ui.b.a(this, -1, f2, f2, 0.0f, 0.0f, (Integer) null, 0, 96));
        FrameLayout frameLayout2 = this.f4864e.f4325d;
        i bottomIndicator = getBottomIndicator();
        ViewGroup.LayoutParams newInstance12 = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-1, Integer.valueOf(this.f4867h)});
        Intrinsics.checkNotNullExpressionValue(newInstance12, "layoutParams");
        frameLayout2.addView(bottomIndicator, newInstance12);
        this.f4864e.f4324c.setOnClickListener(new Y.a(this, 0));
        getCartRecyclerView().setup(this.f4861b.getItems());
        getBottomIndicator().c();
    }

    public static final class c extends Lambda implements Function0<AppCompatImageView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f4886a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ d f4887b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public c(Context context, d dVar) {
            super(0);
            this.f4886a = context;
            this.f4887b = dVar;
        }

        @NotNull
        /* renamed from: a */
        public final AppCompatImageView invoke() {
            AppCompatImageView appCompatImageView = new AppCompatImageView(this.f4886a);
            d dVar = this.f4887b;
            appCompatImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            appCompatImageView.setImageResource(R.drawable.st_dismiss);
            appCompatImageView.setOnClickListener(new Y.a(dVar, 1));
            return appCompatImageView;
        }

        public static final void a(d dVar, View view) {
            Intrinsics.checkNotNullParameter(dVar, "this$0");
            int i3 = d.f4859w;
            dVar.a(b.Default);
        }
    }

    public final void a(Function0<Unit> function0) {
        AutoTransition autoTransition = new AutoTransition();
        autoTransition.addTarget((View) this.f4864e.f4323b);
        autoTransition.setInterpolator((TimeInterpolator) new FastOutSlowInInterpolator());
        autoTransition.setDuration(600);
        TransitionManager.beginDelayedTransition(this.f4864e.f4324c, autoTransition);
        function0.invoke();
        TransitionManager.endTransitions(this.f4864e.f4323b);
    }

    public static final void a(d dVar, View view) {
        Intrinsics.checkNotNullParameter(dVar, "this$0");
        dVar.a(b.Default);
    }

    public final void a(b bVar) {
        this.f4870k = bVar;
        BottomSheetBehavior<FrameLayout> bottomSheetBehavior = this.f4865f;
        if (bottomSheetBehavior != null) {
            bottomSheetBehavior.setState(5);
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.f4864e.f4325d, "alpha", new float[]{1.0f, 0.0f});
        this.f4866g = ofFloat;
        if (ofFloat != null) {
            ofFloat.setDuration(300);
        }
        ObjectAnimator objectAnimator = this.f4866g;
        if (objectAnimator != null) {
            objectAnimator.start();
        }
    }
}
