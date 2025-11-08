package com.appsamurai.storyly.storylypresenter.product.productdetail;

import A0.C0203d;
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
import com.appsamurai.storyly.data.managers.product.STRProductItem;
import com.appsamurai.storyly.data.managers.product.STRProductVariant;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressLint({"ViewConstructor"})
public final class h extends FrameLayout {

    /* renamed from: C  reason: collision with root package name */
    public static final /* synthetic */ int f5208C = 0;
    @NotNull

    /* renamed from: A  reason: collision with root package name */
    public final Lazy f5209A;
    @NotNull

    /* renamed from: B  reason: collision with root package name */
    public final Lazy f5210B;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public List<STRProductItem> f5211a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public StorylyConfig f5212b;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public Function0<Unit> f5213c;
    @NotNull

    /* renamed from: d  reason: collision with root package name */
    public g0 f5214d;
    @Nullable

    /* renamed from: e  reason: collision with root package name */
    public BottomSheetBehavior<FrameLayout> f5215e;
    @NotNull

    /* renamed from: f  reason: collision with root package name */
    public com.appsamurai.storyly.databinding.a f5216f;
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public Function4<? super STRProductItem, ? super Integer, ? super Function1<? super STRCart, Unit>, ? super Function1<? super STRCartEventResult, Unit>, Unit> f5217g = k.f5249a;
    @NotNull

    /* renamed from: h  reason: collision with root package name */
    public Function1<? super STRProductItem, Unit> f5218h = m.f5251a;
    @NotNull

    /* renamed from: i  reason: collision with root package name */
    public Function0<Unit> f5219i = l.f5250a;
    @Nullable

    /* renamed from: j  reason: collision with root package name */
    public STRProductItem f5220j;
    @NotNull

    /* renamed from: k  reason: collision with root package name */
    public List<? extends List<STRProductVariant>> f5221k = CollectionsKt.listOf(CollectionsKt.emptyList());
    @Nullable

    /* renamed from: l  reason: collision with root package name */
    public ObjectAnimator f5222l;

    /* renamed from: m  reason: collision with root package name */
    public int f5223m;
    @NotNull

    /* renamed from: n  reason: collision with root package name */
    public a f5224n = a.Default;
    @NotNull

    /* renamed from: o  reason: collision with root package name */
    public final Lazy f5225o;
    @NotNull

    /* renamed from: p  reason: collision with root package name */
    public final Lazy f5226p;
    @NotNull

    /* renamed from: q  reason: collision with root package name */
    public final Lazy f5227q;
    @NotNull

    /* renamed from: r  reason: collision with root package name */
    public final Lazy f5228r;
    @NotNull

    /* renamed from: s  reason: collision with root package name */
    public final Lazy f5229s;
    @NotNull

    /* renamed from: t  reason: collision with root package name */
    public final Lazy f5230t;
    @NotNull

    /* renamed from: u  reason: collision with root package name */
    public final Lazy f5231u;
    @NotNull

    /* renamed from: v  reason: collision with root package name */
    public final Lazy f5232v;
    @NotNull

    /* renamed from: w  reason: collision with root package name */
    public final Lazy f5233w;
    @NotNull

    /* renamed from: x  reason: collision with root package name */
    public final Lazy f5234x;
    @NotNull

    /* renamed from: y  reason: collision with root package name */
    public final Lazy f5235y;
    @NotNull

    /* renamed from: z  reason: collision with root package name */
    public final Lazy f5236z;

    public static final class a extends Lambda implements Function0<b> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5237a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ h f5238b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(Context context, h hVar) {
            super(0);
            this.f5237a = context;
            this.f5238b = hVar;
        }

        public Object invoke() {
            b bVar = new b(this.f5237a, (AttributeSet) null, 0, this.f5238b.getConfig(), this.f5238b.getLayer());
            bVar.setOnBuyNowClick$storyly_release(new g(this.f5238b, bVar));
            return bVar;
        }
    }

    public static final class c extends Lambda implements Function0<LinearLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5241a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public c(Context context) {
            super(0);
            this.f5241a = context;
        }

        public Object invoke() {
            LinearLayout linearLayout = new LinearLayout(this.f5241a);
            linearLayout.setOrientation(1);
            return linearLayout;
        }
    }

    public static final class d extends Lambda implements Function0<AppCompatTextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5242a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public d(Context context) {
            super(0);
            this.f5242a = context;
        }

        public Object invoke() {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f5242a);
            appCompatTextView.setGravity(GravityCompat.START);
            appCompatTextView.setTextColor(Color.parseColor("#212121"));
            appCompatTextView.setIncludeFontPadding(false);
            appCompatTextView.setHorizontallyScrolling(false);
            return appCompatTextView;
        }
    }

    public static final class e extends Lambda implements Function0<AppCompatTextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5243a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public e(Context context) {
            super(0);
            this.f5243a = context;
        }

        public Object invoke() {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f5243a);
            Context context = this.f5243a;
            appCompatTextView.setSingleLine(true);
            appCompatTextView.setTextColor(Color.parseColor("#616161"));
            appCompatTextView.setText(context.getString(R.string.st_product_sheet_desc));
            appCompatTextView.setEllipsize(TextUtils.TruncateAt.END);
            appCompatTextView.setIncludeFontPadding(false);
            appCompatTextView.setHorizontallyScrolling(false);
            return appCompatTextView;
        }
    }

    public static final class f extends Lambda implements Function0<LinearLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5244a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public f(Context context) {
            super(0);
            this.f5244a = context;
        }

        public Object invoke() {
            LinearLayout linearLayout = new LinearLayout(this.f5244a);
            linearLayout.setOrientation(1);
            linearLayout.setGravity(GravityCompat.END);
            return linearLayout;
        }
    }

    public static final class g extends Lambda implements Function0<LinearLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5245a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public g(Context context) {
            super(0);
            this.f5245a = context;
        }

        public Object invoke() {
            LinearLayout linearLayout = new LinearLayout(this.f5245a);
            linearLayout.setOrientation(1);
            return linearLayout;
        }
    }

    /* renamed from: com.appsamurai.storyly.storylypresenter.product.productdetail.h$h  reason: collision with other inner class name */
    public static final class C0028h extends Lambda implements Function0<LinearLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5246a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public C0028h(Context context) {
            super(0);
            this.f5246a = context;
        }

        public Object invoke() {
            LinearLayout linearLayout = new LinearLayout(this.f5246a);
            linearLayout.setOrientation(0);
            linearLayout.setGravity(16);
            linearLayout.setVisibility(8);
            return linearLayout;
        }
    }

    public static final class i extends Lambda implements Function0<AppCompatImageView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5247a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public i(Context context) {
            super(0);
            this.f5247a = context;
        }

        public Object invoke() {
            AppCompatImageView appCompatImageView = new AppCompatImageView(this.f5247a);
            appCompatImageView.setImageResource(R.drawable.st_round_error);
            appCompatImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return appCompatImageView;
        }
    }

    public static final class j extends Lambda implements Function0<AppCompatTextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5248a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public j(Context context) {
            super(0);
            this.f5248a = context;
        }

        public Object invoke() {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f5248a);
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

    public static final class k extends Lambda implements Function4<STRProductItem, Integer, Function1<? super STRCart, ? extends Unit>, Function1<? super STRCartEventResult, ? extends Unit>, Unit> {

        /* renamed from: a  reason: collision with root package name */
        public static final k f5249a = new k();

        public k() {
            super(4);
        }

        public Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            ((Number) obj2).intValue();
            Intrinsics.checkNotNullParameter((STRProductItem) obj, "$noName_0");
            Intrinsics.checkNotNullParameter((Function1) obj3, "$noName_2");
            Intrinsics.checkNotNullParameter((Function1) obj4, "$noName_3");
            return Unit.INSTANCE;
        }
    }

    public static final class l extends Lambda implements Function0<Unit> {

        /* renamed from: a  reason: collision with root package name */
        public static final l f5250a = new l();

        public l() {
            super(0);
        }

        public /* bridge */ /* synthetic */ Object invoke() {
            return Unit.INSTANCE;
        }
    }

    public static final class m extends Lambda implements Function1<STRProductItem, Unit> {

        /* renamed from: a  reason: collision with root package name */
        public static final m f5251a = new m();

        public m() {
            super(1);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            STRProductItem sTRProductItem = (STRProductItem) obj;
            return Unit.INSTANCE;
        }
    }

    public static final class n extends Lambda implements Function0<com.appsamurai.storyly.storylypresenter.product.imagelist.c> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5252a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public n(Context context) {
            super(0);
            this.f5252a = context;
        }

        public Object invoke() {
            return new com.appsamurai.storyly.storylypresenter.product.imagelist.c(this.f5252a, (AttributeSet) null, 0);
        }
    }

    public static final class o extends Lambda implements Function0<NestedScrollView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5253a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public o(Context context) {
            super(0);
            this.f5253a = context;
        }

        public Object invoke() {
            return new NestedScrollView(this.f5253a);
        }
    }

    public static final class p extends Lambda implements Function0<FrameLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5254a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public p(Context context) {
            super(0);
            this.f5254a = context;
        }

        public Object invoke() {
            FrameLayout frameLayout = new FrameLayout(this.f5254a);
            frameLayout.setBackgroundColor(Color.parseColor("#EEEEEE"));
            return frameLayout;
        }
    }

    public static final class q extends BottomSheetBehavior.BottomSheetCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ BottomSheetBehavior<FrameLayout> f5255a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ h f5256b;

        public q(BottomSheetBehavior<FrameLayout> bottomSheetBehavior, h hVar) {
            this.f5255a = bottomSheetBehavior;
            this.f5256b = hVar;
        }

        public void onSlide(@NotNull View view, float f2) {
            Intrinsics.checkNotNullParameter(view, "bottomSheet");
        }

        public void onStateChanged(@NotNull View view, int i3) {
            Intrinsics.checkNotNullParameter(view, "bottomSheet");
            if (this.f5255a.getState() == 5) {
                ViewParent parent = this.f5256b.getParent();
                ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
                if (viewGroup != null) {
                    viewGroup.removeView(this.f5256b);
                }
                h hVar = this.f5256b;
                if (hVar.f5224n == a.WithSuccess) {
                    hVar.getOnBuyNowSuccess$storyly_release().invoke();
                } else {
                    hVar.getResume().invoke();
                }
            }
        }
    }

    public static final class r extends Lambda implements Function0<com.appsamurai.storyly.storylypresenter.product.variant.e> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5257a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ h f5258b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public r(Context context, h hVar) {
            super(0);
            this.f5257a = context;
            this.f5258b = hVar;
        }

        public Object invoke() {
            return new com.appsamurai.storyly.storylypresenter.product.variant.e(this.f5257a, this.f5258b.getConfig(), new j(this.f5258b));
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public h(@NotNull Context context, @NotNull List<STRProductItem> list, @NotNull StorylyConfig storylyConfig, @NotNull Function0<Unit> function0, @NotNull g0 g0Var) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(list, FirebaseAnalytics.Param.ITEMS);
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        Intrinsics.checkNotNullParameter(function0, "resume");
        Intrinsics.checkNotNullParameter(g0Var, "layer");
        this.f5211a = list;
        this.f5212b = storylyConfig;
        this.f5213c = function0;
        this.f5214d = g0Var;
        com.appsamurai.storyly.databinding.a a2 = com.appsamurai.storyly.databinding.a.a(LayoutInflater.from(context));
        Intrinsics.checkNotNullExpressionValue(a2, "inflate(\n        LayoutI…later.from(context)\n    )");
        this.f5216f = a2;
        this.f5225o = LazyKt.lazy(new o(context));
        this.f5226p = LazyKt.lazy(new c(context));
        this.f5227q = LazyKt.lazy(new g(context));
        this.f5228r = LazyKt.lazy(new n(context));
        this.f5229s = LazyKt.lazy(new r(context, this));
        this.f5230t = LazyKt.lazy(new f(context));
        this.f5231u = LazyKt.lazy(new b(context, this));
        this.f5232v = LazyKt.lazy(new p(context));
        this.f5233w = LazyKt.lazy(new e(context));
        this.f5234x = LazyKt.lazy(new d(context));
        this.f5235y = LazyKt.lazy(new a(context, this));
        this.f5236z = LazyKt.lazy(new C0028h(context));
        this.f5209A = LazyKt.lazy(new i(context));
        this.f5210B = LazyKt.lazy(new j(context));
        b();
        c();
        a();
    }

    public static final void a(h hVar, Function0 function0) {
        hVar.getClass();
        AutoTransition autoTransition = new AutoTransition();
        autoTransition.addTarget((View) hVar.f5216f.f4323b);
        autoTransition.setInterpolator((TimeInterpolator) new FastOutSlowInInterpolator());
        autoTransition.setDuration(600);
        TransitionManager.beginDelayedTransition(hVar.f5216f.f4324c, autoTransition);
        function0.invoke();
        TransitionManager.endTransitions(hVar.f5216f.f4323b);
    }

    /* access modifiers changed from: private */
    public final b getBottomIndicator() {
        return (b) this.f5235y.getValue();
    }

    private final AppCompatImageView getCloseIcon() {
        return (AppCompatImageView) this.f5231u.getValue();
    }

    private final LinearLayout getContentView() {
        return (LinearLayout) this.f5226p.getValue();
    }

    private final AppCompatTextView getDescLabel() {
        return (AppCompatTextView) this.f5234x.getValue();
    }

    private final AppCompatTextView getDescTitle() {
        return (AppCompatTextView) this.f5233w.getValue();
    }

    private final LinearLayout getHeaderContainer() {
        return (LinearLayout) this.f5230t.getValue();
    }

    private final LinearLayout getLinearLayout() {
        return (LinearLayout) this.f5227q.getValue();
    }

    /* access modifiers changed from: private */
    public final LinearLayout getMessageContainer() {
        return (LinearLayout) this.f5236z.getValue();
    }

    private final AppCompatImageView getMessageIcon() {
        return (AppCompatImageView) this.f5209A.getValue();
    }

    /* access modifiers changed from: private */
    public final AppCompatTextView getMessageText() {
        return (AppCompatTextView) this.f5210B.getValue();
    }

    private final com.appsamurai.storyly.storylypresenter.product.imagelist.c getProductImageRecyclerView() {
        return (com.appsamurai.storyly.storylypresenter.product.imagelist.c) this.f5228r.getValue();
    }

    /* access modifiers changed from: private */
    public final NestedScrollView getScrollView() {
        return (NestedScrollView) this.f5225o.getValue();
    }

    private final FrameLayout getSeperator() {
        return (FrameLayout) this.f5232v.getValue();
    }

    /* access modifiers changed from: private */
    public final com.appsamurai.storyly.storylypresenter.product.variant.e getVariantStackView() {
        return (com.appsamurai.storyly.storylypresenter.product.variant.e) this.f5229s.getValue();
    }

    @NotNull
    public final StorylyConfig getConfig() {
        return this.f5212b;
    }

    @NotNull
    public final List<STRProductItem> getItems() {
        return this.f5211a;
    }

    @NotNull
    public final g0 getLayer() {
        return this.f5214d;
    }

    @NotNull
    public final Function4<STRProductItem, Integer, Function1<? super STRCart, Unit>, Function1<? super STRCartEventResult, Unit>, Unit> getOnBuyNowClick$storyly_release() {
        return this.f5217g;
    }

    @NotNull
    public final Function0<Unit> getOnBuyNowSuccess$storyly_release() {
        return this.f5219i;
    }

    @NotNull
    public final Function1<STRProductItem, Unit> getOnProductSelected$storyly_release() {
        return this.f5218h;
    }

    @NotNull
    public final Function0<Unit> getResume() {
        return this.f5213c;
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ObjectAnimator objectAnimator = this.f5222l;
        if (objectAnimator != null) {
            objectAnimator.removeAllListeners();
        }
        ObjectAnimator objectAnimator2 = this.f5222l;
        if (objectAnimator2 != null) {
            objectAnimator2.end();
        }
        ObjectAnimator objectAnimator3 = this.f5222l;
        if (objectAnimator3 != null) {
            objectAnimator3.cancel();
        }
        this.f5216f.f4325d.clearAnimation();
    }

    public final void setConfig(@NotNull StorylyConfig storylyConfig) {
        Intrinsics.checkNotNullParameter(storylyConfig, "<set-?>");
        this.f5212b = storylyConfig;
    }

    public final void setItems(@NotNull List<STRProductItem> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.f5211a = list;
    }

    public final void setLayer(@NotNull g0 g0Var) {
        Intrinsics.checkNotNullParameter(g0Var, "<set-?>");
        this.f5214d = g0Var;
    }

    public final void setOnBuyNowClick$storyly_release(@NotNull Function4<? super STRProductItem, ? super Integer, ? super Function1<? super STRCart, Unit>, ? super Function1<? super STRCartEventResult, Unit>, Unit> function4) {
        Intrinsics.checkNotNullParameter(function4, "<set-?>");
        this.f5217g = function4;
    }

    public final void setOnBuyNowSuccess$storyly_release(@NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.f5219i = function0;
    }

    public final void setOnProductSelected$storyly_release(@NotNull Function1<? super STRProductItem, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.f5218h = function1;
    }

    public final void setResume(@NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.f5213c = function0;
    }

    public final void b() {
        this.f5220j = (STRProductItem) CollectionsKt.firstOrNull(this.f5211a);
        ArrayList arrayList = new ArrayList();
        for (STRProductItem variants : this.f5211a) {
            arrayList.addAll(variants.getVariants());
        }
        List distinct = CollectionsKt.distinct(arrayList);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object next : distinct) {
            String name = ((STRProductVariant) next).getName();
            Object obj = linkedHashMap.get(name);
            if (obj == null) {
                obj = new ArrayList();
                linkedHashMap.put(name, obj);
            }
            ((List) obj).add(next);
        }
        ArrayList arrayList2 = new ArrayList(linkedHashMap.size());
        for (Map.Entry value : linkedHashMap.entrySet()) {
            arrayList2.add((List) value.getValue());
        }
        this.f5221k = arrayList2;
    }

    public final void c() {
        Integer num;
        h hVar;
        T t2;
        int height = (int) (((double) com.appsamurai.storyly.util.m.b().height()) * 0.05d);
        int width = (int) (((double) com.appsamurai.storyly.util.m.b().width()) * 0.033d);
        int width2 = (int) (((double) com.appsamurai.storyly.util.m.b().width()) * 0.066d);
        int width3 = (int) (((double) com.appsamurai.storyly.util.m.b().width()) * 0.033d);
        int width4 = (int) (((double) com.appsamurai.storyly.util.m.b().width()) * 0.0335d);
        int width5 = (int) (((double) com.appsamurai.storyly.util.m.b().width()) * 0.033d);
        int width6 = (int) (((double) com.appsamurai.storyly.util.m.b().width()) * 0.048d);
        int width7 = (int) (((double) com.appsamurai.storyly.util.m.b().width()) * 0.435d);
        int width8 = (int) (((double) com.appsamurai.storyly.util.m.b().width()) * 0.045d);
        int i3 = height;
        this.f5223m = (int) (((double) com.appsamurai.storyly.util.m.b().height()) * 0.155d);
        float width9 = (float) (((double) com.appsamurai.storyly.util.m.b().width()) * 0.033d);
        int width10 = (int) (((double) com.appsamurai.storyly.util.m.b().width()) * 0.033d);
        int width11 = (int) (((double) com.appsamurai.storyly.util.m.b().width()) * 0.0027d);
        float width12 = (float) (((double) com.appsamurai.storyly.util.m.b().width()) * 0.044d);
        int width13 = (int) (((double) com.appsamurai.storyly.util.m.b().width()) * 0.027d);
        int width14 = (int) (((double) com.appsamurai.storyly.util.m.b().width()) * 0.061d);
        int width15 = (int) (((double) com.appsamurai.storyly.util.m.b().width()) * 0.05d);
        int width16 = (int) (((double) com.appsamurai.storyly.util.m.b().width()) * 0.055d);
        int width17 = (int) (((double) com.appsamurai.storyly.util.m.b().width()) * 0.038d);
        int i4 = width15;
        int i5 = width16;
        int i6 = width8;
        int i7 = width13;
        int i8 = width7;
        int i9 = width6;
        int i10 = width5;
        this.f5216f.f4323b.setBackground(com.appsamurai.storyly.util.ui.b.a(this, -1, width9, width9, 0.0f, 0.0f, (Integer) null, 0, 96));
        getMessageContainer().setBackground(com.appsamurai.storyly.util.ui.b.a(this, Color.parseColor("#E0E0E0"), width12, Integer.valueOf(Color.parseColor("#E0E0E0")), width11));
        getBottomIndicator().setProduct$storyly_release((STRProductItem) CollectionsKt.firstOrNull(this.f5211a));
        getBottomIndicator().c();
        RelativeLayout relativeLayout = this.f5216f.f4322a;
        Class cls = Integer.TYPE;
        Class<ViewGroup.LayoutParams> cls2 = ViewGroup.LayoutParams.class;
        ViewGroup.LayoutParams newInstance = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-1, -1});
        String str = "layoutParams";
        Intrinsics.checkNotNullExpressionValue(newInstance, str);
        Unit unit = Unit.INSTANCE;
        addView(relativeLayout, newInstance);
        FrameLayout frameLayout = this.f5216f.f4323b;
        LinearLayout contentView = getContentView();
        Class<FrameLayout.LayoutParams> cls3 = FrameLayout.LayoutParams.class;
        ViewGroup.LayoutParams newInstance2 = cls3.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-1, -2});
        Intrinsics.checkNotNullExpressionValue(newInstance2, str);
        frameLayout.addView(contentView, newInstance2);
        FrameLayout frameLayout2 = this.f5216f.f4325d;
        b bottomIndicator = getBottomIndicator();
        ViewGroup.LayoutParams newInstance3 = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-1, Integer.valueOf(this.f5223m)});
        Intrinsics.checkNotNullExpressionValue(newInstance3, str);
        frameLayout2.addView(bottomIndicator, newInstance3);
        LinearLayout contentView2 = getContentView();
        LinearLayout headerContainer = getHeaderContainer();
        ViewGroup.LayoutParams newInstance4 = cls3.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-1, -2});
        Intrinsics.checkNotNullExpressionValue(newInstance4, str);
        contentView2.addView(headerContainer, newInstance4);
        LinearLayout headerContainer2 = getHeaderContainer();
        AppCompatImageView closeIcon = getCloseIcon();
        ViewGroup.LayoutParams newInstance5 = cls3.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{Integer.valueOf(width2), Integer.valueOf(width2)});
        Intrinsics.checkNotNullExpressionValue(newInstance5, str);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) newInstance5;
        layoutParams.setMarginEnd(width);
        layoutParams.topMargin = width;
        layoutParams.leftMargin = width4;
        layoutParams.rightMargin = width4;
        headerContainer2.addView(closeIcon, newInstance5);
        FrameLayout seperator = getSeperator();
        ViewGroup.LayoutParams newInstance6 = cls3.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-1, 1});
        Intrinsics.checkNotNullExpressionValue(newInstance6, str);
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) newInstance6;
        layoutParams2.gravity = GravityCompat.END;
        layoutParams2.topMargin = width3;
        headerContainer2.addView(seperator, newInstance6);
        NestedScrollView scrollView = getScrollView();
        ViewGroup.LayoutParams newInstance7 = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-1, -2});
        Intrinsics.checkNotNullExpressionValue(newInstance7, str);
        contentView2.addView(scrollView, newInstance7);
        NestedScrollView scrollView2 = getScrollView();
        LinearLayout linearLayout = getLinearLayout();
        ViewGroup.LayoutParams newInstance8 = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-1, -2});
        Intrinsics.checkNotNullExpressionValue(newInstance8, str);
        scrollView2.addView((View) linearLayout, newInstance8);
        LinearLayout linearLayout2 = getLinearLayout();
        LinearLayout messageContainer = getMessageContainer();
        ViewGroup.LayoutParams newInstance9 = cls3.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-1, -2});
        Intrinsics.checkNotNullExpressionValue(newInstance9, str);
        FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) newInstance9;
        layoutParams3.topMargin = width10;
        layoutParams3.leftMargin = width4;
        layoutParams3.rightMargin = width4;
        linearLayout2.addView(messageContainer, newInstance9);
        LinearLayout messageContainer2 = getMessageContainer();
        AppCompatImageView messageIcon = getMessageIcon();
        ViewGroup.LayoutParams newInstance10 = cls3.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{Integer.valueOf(i5), Integer.valueOf(i5)});
        Intrinsics.checkNotNullExpressionValue(newInstance10, str);
        ((FrameLayout.LayoutParams) newInstance10).setMarginStart(i4);
        messageContainer2.addView(messageIcon, newInstance10);
        AppCompatTextView messageText = getMessageText();
        ViewGroup.LayoutParams newInstance11 = cls3.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-2, -2});
        Intrinsics.checkNotNullExpressionValue(newInstance11, str);
        FrameLayout.LayoutParams layoutParams4 = (FrameLayout.LayoutParams) newInstance11;
        int i11 = i7;
        layoutParams4.setMarginStart(i11);
        layoutParams4.setMarginEnd(i11);
        int i12 = width14;
        layoutParams4.topMargin = i12;
        layoutParams4.bottomMargin = i12;
        messageContainer2.addView(messageText, newInstance11);
        com.appsamurai.storyly.storylypresenter.product.imagelist.c productImageRecyclerView = getProductImageRecyclerView();
        Class<LinearLayout.LayoutParams> cls4 = LinearLayout.LayoutParams.class;
        ViewGroup.LayoutParams newInstance12 = cls4.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-1, Integer.valueOf(i8)});
        Intrinsics.checkNotNullExpressionValue(newInstance12, str);
        LinearLayout.LayoutParams layoutParams5 = (LinearLayout.LayoutParams) newInstance12;
        int i13 = i6;
        layoutParams5.topMargin = i13;
        layoutParams5.bottomMargin = i13;
        linearLayout2.addView(productImageRecyclerView, newInstance12);
        STRProductItem sTRProductItem = this.f5220j;
        List<STRProductVariant> variants = sTRProductItem == null ? null : sTRProductItem.getVariants();
        String str2 = "#EEEEEE";
        if (variants != null && !variants.isEmpty()) {
            View view = new View(linearLayout2.getContext());
            view.setBackgroundColor(Color.parseColor(str2));
            ViewGroup.LayoutParams newInstance13 = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-1, 1});
            Intrinsics.checkNotNullExpressionValue(newInstance13, str);
            linearLayout2.addView(view, newInstance13);
            com.appsamurai.storyly.storylypresenter.product.variant.e variantStackView = getVariantStackView();
            ViewGroup.LayoutParams newInstance14 = cls4.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-1, -2});
            Intrinsics.checkNotNullExpressionValue(newInstance14, str);
            ((LinearLayout.LayoutParams) newInstance14).gravity = 17;
            linearLayout2.addView(variantStackView, newInstance14);
        }
        STRProductItem sTRProductItem2 = this.f5220j;
        String desc = sTRProductItem2 == null ? null : sTRProductItem2.getDesc();
        if (desc == null || StringsKt.isBlank(desc)) {
            num = 1;
        } else {
            AppCompatTextView descTitle = getDescTitle();
            ViewGroup.LayoutParams newInstance15 = cls3.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-2, -2});
            Intrinsics.checkNotNullExpressionValue(newInstance15, str);
            FrameLayout.LayoutParams layoutParams6 = (FrameLayout.LayoutParams) newInstance15;
            layoutParams6.setMarginStart(width4);
            layoutParams6.setMarginEnd(width4);
            num = 1;
            int i14 = i10;
            layoutParams6.topMargin = i14 * 2;
            linearLayout2.addView(descTitle, newInstance15);
            AppCompatTextView descLabel = getDescLabel();
            ViewGroup.LayoutParams newInstance16 = cls3.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-1, -2});
            Intrinsics.checkNotNullExpressionValue(newInstance16, str);
            FrameLayout.LayoutParams layoutParams7 = (FrameLayout.LayoutParams) newInstance16;
            layoutParams7.setMarginStart(width4);
            layoutParams7.setMarginEnd(width4);
            layoutParams7.topMargin = i14;
            linearLayout2.addView(descLabel, newInstance16);
        }
        View view2 = new View(linearLayout2.getContext());
        ViewGroup.LayoutParams newInstance17 = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-1, Integer.valueOf(i3)});
        Intrinsics.checkNotNullExpressionValue(newInstance17, str);
        linearLayout2.addView(view2, newInstance17);
        com.appsamurai.storyly.storylypresenter.product.imagelist.c productImageRecyclerView2 = getProductImageRecyclerView();
        STRProductItem sTRProductItem3 = this.f5220j;
        List<String> imageUrls = sTRProductItem3 == null ? null : sTRProductItem3.getImageUrls();
        if (imageUrls == null) {
            imageUrls = CollectionsKt.emptyList();
        }
        productImageRecyclerView2.setup(imageUrls);
        AppCompatTextView descTitle2 = getDescTitle();
        float f2 = (float) (((double) i9) * 0.85d);
        descTitle2.setTextSize(0, f2);
        descTitle2.setTypeface(getConfig().getStory$storyly_release().getInteractiveTypeface$storyly_release());
        AppCompatTextView descLabel2 = getDescLabel();
        descLabel2.setTypeface(getConfig().getStory$storyly_release().getInteractiveTypeface$storyly_release());
        STRProductItem sTRProductItem4 = this.f5220j;
        descLabel2.setText(sTRProductItem4 == null ? null : sTRProductItem4.getDesc());
        descLabel2.setTextSize(0, f2);
        AppCompatTextView messageText2 = getMessageText();
        messageText2.setTypeface(getConfig().getStory$storyly_release().getInteractiveTypeface$storyly_release());
        messageText2.setTextSize(0, (float) width17);
        STRProductItem sTRProductItem5 = this.f5220j;
        List<STRProductVariant> variants2 = sTRProductItem5 == null ? null : sTRProductItem5.getVariants();
        if (variants2 == null) {
            variants2 = CollectionsKt.emptyList();
        }
        a(variants2);
        com.appsamurai.storyly.storylypresenter.product.variant.e variantStackView2 = getVariantStackView();
        List<? extends List<STRProductVariant>> list = this.f5221k;
        STRProductItem sTRProductItem6 = this.f5220j;
        List<STRProductVariant> variants3 = sTRProductItem6 == null ? null : sTRProductItem6.getVariants();
        if (variants3 == null) {
            variants3 = CollectionsKt.emptyList();
        }
        variantStackView2.getClass();
        Intrinsics.checkNotNullParameter(variants3, "selectedVariants");
        if (list == null) {
            hVar = this;
        } else {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                List<STRProductVariant> list2 = (List) it.next();
                Iterator<T> it2 = variants3.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        t2 = null;
                        break;
                    }
                    t2 = it2.next();
                    String name = ((STRProductVariant) t2).getName();
                    STRProductVariant sTRProductVariant = (STRProductVariant) CollectionsKt.firstOrNull(list2);
                    if (Intrinsics.areEqual((Object) name, (Object) sTRProductVariant == null ? null : sTRProductVariant.getName())) {
                        break;
                    }
                }
                STRProductVariant sTRProductVariant2 = (STRProductVariant) t2;
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
                for (STRProductVariant copy$storyly_release : list2) {
                    arrayList.add(copy$storyly_release.copy$storyly_release());
                }
                double width18 = ((double) ((int) (((double) com.appsamurai.storyly.util.m.b().width()) * 0.435d))) * 0.25d;
                Context context = variantStackView2.getContext();
                Intrinsics.checkNotNullExpressionValue(context, "context");
                Iterator<T> it3 = it;
                List<STRProductVariant> list3 = variants3;
                com.appsamurai.storyly.storylypresenter.product.variant.d dVar = new com.appsamurai.storyly.storylypresenter.product.variant.d(context, (AttributeSet) null, 0, variantStackView2.f5284a);
                View view3 = new View(variantStackView2.getContext());
                view3.setBackgroundColor(Color.parseColor(str2));
                variantStackView2.f5286c.add(dVar);
                dVar.setComponentHeight$storyly_release((int) width18);
                dVar.setOnVariantSelection$storyly_release(variantStackView2.f5285b);
                Class cls5 = Integer.TYPE;
                ViewGroup.LayoutParams newInstance18 = cls4.getConstructor(new Class[]{cls5, cls5}).newInstance(new Object[]{-1, Integer.valueOf((int) (width18 * 1.25d))});
                Intrinsics.checkNotNullExpressionValue(newInstance18, str);
                LinearLayout.LayoutParams layoutParams8 = (LinearLayout.LayoutParams) newInstance18;
                int i15 = (int) (width18 * 0.4d);
                String str3 = str;
                layoutParams8.bottomMargin = (int) (((double) i15) - (width18 * ((double) 0.1f)));
                layoutParams8.topMargin = i15;
                Unit unit2 = Unit.INSTANCE;
                variantStackView2.addView(dVar, newInstance18);
                ViewGroup.LayoutParams newInstance19 = cls2.getConstructor(new Class[]{cls5, cls5}).newInstance(new Object[]{-1, num});
                str = str3;
                Intrinsics.checkNotNullExpressionValue(newInstance19, str);
                variantStackView2.addView(view3, newInstance19);
                dVar.setup(arrayList);
                dVar.setSelectedItem(sTRProductVariant2);
                it = it3;
                variants3 = list3;
                cls4 = cls4;
                str2 = str2;
            }
            hVar = this;
        }
        hVar.f5216f.f4324c.setOnClickListener(new C0203d(hVar, 0));
    }

    public static final class b extends Lambda implements Function0<AppCompatImageView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5239a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ h f5240b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public b(Context context, h hVar) {
            super(0);
            this.f5239a = context;
            this.f5240b = hVar;
        }

        @NotNull
        /* renamed from: a */
        public final AppCompatImageView invoke() {
            AppCompatImageView appCompatImageView = new AppCompatImageView(this.f5239a);
            h hVar = this.f5240b;
            appCompatImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            appCompatImageView.setImageResource(R.drawable.st_dismiss);
            appCompatImageView.setOnClickListener(new C0203d(hVar, 1));
            return appCompatImageView;
        }

        public static final void a(h hVar, View view) {
            Intrinsics.checkNotNullParameter(hVar, "this$0");
            int i3 = h.f5208C;
            hVar.a(a.Default);
        }
    }

    public static final void a(h hVar, STRProductVariant sTRProductVariant) {
        List<STRProductVariant> list;
        List<STRProductVariant> list2;
        T t2;
        T t3;
        String str;
        com.appsamurai.storyly.storylypresenter.product.variant.c headerAdapter$storyly_release;
        List<STRProductVariant> variants;
        hVar.getClass();
        if (sTRProductVariant != null) {
            STRProductItem sTRProductItem = hVar.f5220j;
            String str2 = null;
            if (sTRProductItem == null || (variants = sTRProductItem.getVariants()) == null) {
                list = null;
            } else {
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(variants, 10));
                for (STRProductVariant copy$storyly_release : variants) {
                    arrayList.add(copy$storyly_release.copy$storyly_release());
                }
                list = CollectionsKt.toMutableList(arrayList);
            }
            if (list != null) {
                CollectionsKt__MutableCollectionsKt.removeAll(list, new i(sTRProductVariant));
            }
            List<STRProductItem> list3 = hVar.f5211a;
            ArrayList arrayList2 = new ArrayList();
            for (T next : list3) {
                if (((STRProductItem) next).getVariants().contains(sTRProductVariant)) {
                    arrayList2.add(next);
                }
            }
            if (list != null) {
                for (STRProductVariant sTRProductVariant2 : list) {
                    ArrayList arrayList3 = new ArrayList();
                    for (Object next2 : arrayList2) {
                        if (((STRProductItem) next2).getVariants().contains(sTRProductVariant2)) {
                            arrayList3.add(next2);
                        }
                    }
                    if (!arrayList3.isEmpty()) {
                        arrayList2 = arrayList3;
                    }
                }
            }
            STRProductItem sTRProductItem2 = (STRProductItem) CollectionsKt.firstOrNull(arrayList2);
            hVar.f5220j = sTRProductItem2;
            if (sTRProductItem2 == null) {
                list2 = null;
            } else {
                list2 = sTRProductItem2.getVariants();
            }
            if (list2 == null) {
                list2 = CollectionsKt.emptyList();
            }
            hVar.a(list2);
            com.appsamurai.storyly.storylypresenter.product.imagelist.c productImageRecyclerView = hVar.getProductImageRecyclerView();
            STRProductItem sTRProductItem3 = hVar.f5220j;
            List<String> imageUrls = sTRProductItem3 == null ? null : sTRProductItem3.getImageUrls();
            if (imageUrls == null) {
                imageUrls = CollectionsKt.emptyList();
            }
            productImageRecyclerView.setup(imageUrls);
            com.appsamurai.storyly.storylypresenter.product.variant.e variantStackView = hVar.getVariantStackView();
            List<? extends List<STRProductVariant>> list4 = hVar.f5221k;
            STRProductItem sTRProductItem4 = hVar.f5220j;
            List<STRProductVariant> variants2 = sTRProductItem4 == null ? null : sTRProductItem4.getVariants();
            if (variants2 == null) {
                variants2 = CollectionsKt.emptyList();
            }
            variantStackView.getClass();
            Intrinsics.checkNotNullParameter(variants2, "selectedVariants");
            if (list4 != null) {
                for (List<STRProductVariant> list5 : list4) {
                    ArrayList arrayList4 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list5, 10));
                    for (STRProductVariant copy$storyly_release2 : list5) {
                        arrayList4.add(copy$storyly_release2.copy$storyly_release());
                    }
                    ArrayList arrayList5 = new ArrayList();
                    Iterator it = arrayList4.iterator();
                    while (it.hasNext()) {
                        arrayList5.add((STRProductVariant) it.next());
                    }
                    Iterator<T> it2 = variantStackView.f5286c.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            t2 = null;
                            break;
                        }
                        t2 = it2.next();
                        String str3 = ((com.appsamurai.storyly.storylypresenter.product.variant.d) t2).getHeaderAdapter$storyly_release().f5276b;
                        STRProductVariant sTRProductVariant3 = (STRProductVariant) CollectionsKt.firstOrNull(arrayList4);
                        if (Intrinsics.areEqual((Object) str3, (Object) sTRProductVariant3 == null ? null : sTRProductVariant3.getName())) {
                            break;
                        }
                    }
                    com.appsamurai.storyly.storylypresenter.product.variant.d dVar = (com.appsamurai.storyly.storylypresenter.product.variant.d) t2;
                    Iterator<T> it3 = variants2.iterator();
                    while (true) {
                        if (!it3.hasNext()) {
                            t3 = null;
                            break;
                        }
                        t3 = it3.next();
                        String name = ((STRProductVariant) t3).getName();
                        if (dVar == null || (headerAdapter$storyly_release = dVar.getHeaderAdapter$storyly_release()) == null) {
                            str = null;
                        } else {
                            str = headerAdapter$storyly_release.f5276b;
                        }
                        if (Intrinsics.areEqual((Object) name, (Object) str)) {
                            break;
                        }
                    }
                    STRProductVariant sTRProductVariant4 = (STRProductVariant) t3;
                    if (dVar != null) {
                        dVar.setSelectedItem(sTRProductVariant4);
                    }
                    if (dVar != null) {
                        dVar.setup(arrayList5);
                    }
                }
            }
            hVar.getBottomIndicator().a(hVar.f5220j);
            AppCompatTextView descLabel = hVar.getDescLabel();
            STRProductItem sTRProductItem5 = hVar.f5220j;
            if (sTRProductItem5 != null) {
                str2 = sTRProductItem5.getDesc();
            }
            descLabel.setText(str2);
        }
    }

    public final void a() {
        BottomSheetBehavior<FrameLayout> from = BottomSheetBehavior.from(this.f5216f.f4323b);
        from.setPeekHeight(((int) (((double) com.appsamurai.storyly.util.m.b().height()) * 0.9d)) - this.f5223m);
        from.setHideable(true);
        from.setState(5);
        from.addBottomSheetCallback(new q(from, this));
        Unit unit = Unit.INSTANCE;
        this.f5215e = from;
    }

    public final void a(List<STRProductVariant> list) {
        boolean z2;
        int i3 = 0;
        for (T next : this.f5221k) {
            int i4 = i3 + 1;
            if (i3 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            List list2 = (List) next;
            if (i3 > 0) {
                ArrayList arrayList = new ArrayList();
                for (T next2 : list) {
                    if (CollectionsKt.flatten(CollectionsKt.slice(this.f5221k, RangesKt.until(0, i3))).contains((STRProductVariant) next2)) {
                        arrayList.add(next2);
                    }
                }
                List<STRProductItem> items = getItems();
                ArrayList arrayList2 = new ArrayList();
                for (T next3 : items) {
                    STRProductItem sTRProductItem = (STRProductItem) next3;
                    if (!arrayList.isEmpty()) {
                        Iterator it = arrayList.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            if (!sTRProductItem.getVariants().contains((STRProductVariant) it.next())) {
                                break;
                            }
                        }
                    }
                    arrayList2.add(next3);
                }
                for (STRProductVariant sTRProductVariant : (Iterable) this.f5221k.get(i3)) {
                    if (!arrayList2.isEmpty()) {
                        Iterator it2 = arrayList2.iterator();
                        while (true) {
                            if (it2.hasNext()) {
                                if (((STRProductItem) it2.next()).getVariants().contains(sTRProductVariant)) {
                                    z2 = true;
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                    }
                    z2 = false;
                    sTRProductVariant.setEnabled$storyly_release(z2);
                }
            }
            i3 = i4;
        }
    }

    public static final void a(h hVar, View view) {
        Intrinsics.checkNotNullParameter(hVar, "this$0");
        hVar.a(a.Default);
    }

    public final void a(a aVar) {
        this.f5224n = aVar;
        BottomSheetBehavior<FrameLayout> bottomSheetBehavior = this.f5215e;
        if (bottomSheetBehavior != null) {
            bottomSheetBehavior.setState(5);
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.f5216f.f4325d, "alpha", new float[]{1.0f, 0.0f});
        this.f5222l = ofFloat;
        if (ofFloat != null) {
            ofFloat.setDuration(300);
        }
        ObjectAnimator objectAnimator = this.f5222l;
        if (objectAnimator != null) {
            objectAnimator.start();
        }
    }
}
