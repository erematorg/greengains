package com.appsamurai.storyly.storylypresenter.product.productdetail;

import A0.C0200a;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.motion.widget.Key;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import com.appsamurai.storyly.R;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.data.g0;
import com.appsamurai.storyly.data.managers.product.STRProductItem;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Unit;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.properties.Delegates;
import kotlin.properties.ObservableProperty;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressLint({"ViewConstructor"})
public final class b extends RelativeLayout {

    /* renamed from: r  reason: collision with root package name */
    public static final /* synthetic */ KProperty<Object>[] f5160r;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final StorylyConfig f5161a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final g0 f5162b;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public final ReadWriteProperty f5163c;
    @NotNull

    /* renamed from: d  reason: collision with root package name */
    public final ReadWriteProperty f5164d = new m(1, 1, this);
    @Nullable

    /* renamed from: e  reason: collision with root package name */
    public AnimatorSet f5165e;
    @Nullable

    /* renamed from: f  reason: collision with root package name */
    public STRProductItem f5166f;
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public Function0<Unit> f5167g = i.f5191a;
    @NotNull

    /* renamed from: h  reason: collision with root package name */
    public final Lazy f5168h;
    @NotNull

    /* renamed from: i  reason: collision with root package name */
    public final Lazy f5169i;
    @NotNull

    /* renamed from: j  reason: collision with root package name */
    public final Lazy f5170j;
    @NotNull

    /* renamed from: k  reason: collision with root package name */
    public final Lazy f5171k;
    @NotNull

    /* renamed from: l  reason: collision with root package name */
    public final Lazy f5172l;
    @NotNull

    /* renamed from: m  reason: collision with root package name */
    public final Lazy f5173m;
    @NotNull

    /* renamed from: n  reason: collision with root package name */
    public final Lazy f5174n;
    @NotNull

    /* renamed from: o  reason: collision with root package name */
    public final Lazy f5175o;
    @NotNull

    /* renamed from: p  reason: collision with root package name */
    public final Lazy f5176p;
    @NotNull

    /* renamed from: q  reason: collision with root package name */
    public final Lazy f5177q;

    /* renamed from: com.appsamurai.storyly.storylypresenter.product.productdetail.b$b  reason: collision with other inner class name */
    public static final class C0027b extends Lambda implements Function0<AppCompatImageView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5180a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public C0027b(Context context) {
            super(0);
            this.f5180a = context;
        }

        public Object invoke() {
            AppCompatImageView appCompatImageView = new AppCompatImageView(this.f5180a);
            appCompatImageView.setImageResource(R.drawable.st_basket);
            appCompatImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            appCompatImageView.setColorFilter(-1, PorterDuff.Mode.SRC_IN);
            return appCompatImageView;
        }
    }

    public static final class c extends Lambda implements Function0<AppCompatTextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5181a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ b f5182b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public c(Context context, b bVar) {
            super(0);
            this.f5181a = context;
            this.f5182b = bVar;
        }

        public Object invoke() {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f5181a);
            b bVar = this.f5182b;
            appCompatTextView.setEllipsize(TextUtils.TruncateAt.END);
            appCompatTextView.setSingleLine(true);
            appCompatTextView.setText(bVar.getLayer().d());
            appCompatTextView.setIncludeFontPadding(false);
            appCompatTextView.setHorizontallyScrolling(false);
            appCompatTextView.setGravity(17);
            appCompatTextView.setTextColor(-1);
            appCompatTextView.setTextAlignment(1);
            return appCompatTextView;
        }
    }

    public static final class f extends Lambda implements Function0<LinearLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5187a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public f(Context context) {
            super(0);
            this.f5187a = context;
        }

        public Object invoke() {
            LinearLayout linearLayout = new LinearLayout(this.f5187a);
            linearLayout.setId(View.generateViewId());
            linearLayout.setGravity(17);
            linearLayout.setOrientation(0);
            return linearLayout;
        }
    }

    public static final class g extends Lambda implements Function0<AppCompatTextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5188a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ b f5189b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public g(Context context, b bVar) {
            super(0);
            this.f5188a = context;
            this.f5189b = bVar;
        }

        public Object invoke() {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f5188a);
            b bVar = this.f5189b;
            appCompatTextView.setSingleLine(true);
            appCompatTextView.setText(String.valueOf(bVar.getQuantity$storyly_release()));
            appCompatTextView.setEllipsize(TextUtils.TruncateAt.END);
            appCompatTextView.setGravity(GravityCompat.END);
            appCompatTextView.setIncludeFontPadding(false);
            appCompatTextView.setHorizontallyScrolling(false);
            appCompatTextView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            return appCompatTextView;
        }
    }

    public static final class h extends Lambda implements Function0<AppCompatTextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5190a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public h(Context context) {
            super(0);
            this.f5190a = context;
        }

        public Object invoke() {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f5190a);
            appCompatTextView.setId(View.generateViewId());
            appCompatTextView.setSingleLine(true);
            appCompatTextView.setEllipsize(TextUtils.TruncateAt.END);
            appCompatTextView.setIncludeFontPadding(false);
            appCompatTextView.setGravity(15);
            appCompatTextView.setLineSpacing(0.0f, 0.0f);
            appCompatTextView.setPaintFlags(appCompatTextView.getPaintFlags() | 16);
            appCompatTextView.setHorizontallyScrolling(false);
            return appCompatTextView;
        }
    }

    public static final class i extends Lambda implements Function0<Unit> {

        /* renamed from: a  reason: collision with root package name */
        public static final i f5191a = new i();

        public i() {
            super(0);
        }

        public /* bridge */ /* synthetic */ Object invoke() {
            return Unit.INSTANCE;
        }
    }

    public static final class j extends Lambda implements Function0<LinearLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5192a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public j(Context context) {
            super(0);
            this.f5192a = context;
        }

        public Object invoke() {
            LinearLayout linearLayout = new LinearLayout(this.f5192a);
            linearLayout.setOrientation(0);
            return linearLayout;
        }
    }

    public static final class k extends Lambda implements Function0<AppCompatTextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5193a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public k(Context context) {
            super(0);
            this.f5193a = context;
        }

        public Object invoke() {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f5193a);
            appCompatTextView.setId(View.generateViewId());
            appCompatTextView.setSingleLine(true);
            appCompatTextView.setEllipsize(TextUtils.TruncateAt.END);
            appCompatTextView.setGravity(15);
            appCompatTextView.setIncludeFontPadding(false);
            appCompatTextView.setHorizontallyScrolling(false);
            return appCompatTextView;
        }
    }

    public static final class l extends ObservableProperty<c> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ b f5194a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public l(Object obj, Object obj2, b bVar) {
            super(obj2);
            this.f5194a = bVar;
        }

        public void afterChange(@NotNull KProperty<?> kProperty, c cVar, c cVar2) {
            Intrinsics.checkNotNullParameter(kProperty, "property");
            c cVar3 = cVar;
            b.a(this.f5194a, cVar2);
        }
    }

    public static final class m extends ObservableProperty<Integer> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Object f5195a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ b f5196b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public m(Object obj, Object obj2, b bVar) {
            super(obj2);
            this.f5195a = obj;
            this.f5196b = bVar;
        }

        public void afterChange(@NotNull KProperty<?> kProperty, Integer num, Integer num2) {
            Intrinsics.checkNotNullParameter(kProperty, "property");
            int intValue = num2.intValue();
            num.intValue();
            this.f5196b.getIndicatorLabel().setText(String.valueOf(intValue));
            this.f5196b.a(intValue, true);
        }
    }

    static {
        Class<b> cls = b.class;
        f5160r = new KProperty[]{androidx.compose.ui.autofill.a.m(cls, RemoteConfigConstants.ResponseFieldKey.STATE, "getState$storyly_release()Lcom/appsamurai/storyly/storylypresenter/product/productdetail/ProductDetailIndicatorState;", 0), androidx.compose.ui.autofill.a.m(cls, FirebaseAnalytics.Param.QUANTITY, "getQuantity$storyly_release()I", 0)};
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public b(@NotNull Context context, @Nullable AttributeSet attributeSet, int i3, @NotNull StorylyConfig storylyConfig, @NotNull g0 g0Var) {
        super(context, attributeSet, i3);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        Intrinsics.checkNotNullParameter(g0Var, "layer");
        this.f5161a = storylyConfig;
        this.f5162b = g0Var;
        Delegates delegates = Delegates.INSTANCE;
        c cVar = c.Default;
        this.f5163c = new l(cVar, cVar, this);
        this.f5168h = LazyKt.lazy(new h(context));
        this.f5169i = LazyKt.lazy(new j(context));
        this.f5170j = LazyKt.lazy(new k(context));
        this.f5171k = LazyKt.lazy(new a(context, this));
        this.f5172l = LazyKt.lazy(new C0027b(context));
        this.f5173m = LazyKt.lazy(new c(context, this));
        this.f5174n = LazyKt.lazy(new f(context));
        this.f5175o = LazyKt.lazy(new d(context, this));
        this.f5176p = LazyKt.lazy(new e(context, this));
        this.f5177q = LazyKt.lazy(new g(context, this));
        b();
        setId(View.generateViewId());
        setBackgroundColor(-1);
    }

    private final LinearLayout getActionButtonContainer() {
        return (LinearLayout) this.f5171k.getValue();
    }

    private final AppCompatImageView getActionButtonIcon() {
        return (AppCompatImageView) this.f5172l.getValue();
    }

    private final AppCompatTextView getActionButtonText() {
        return (AppCompatTextView) this.f5173m.getValue();
    }

    private final AppCompatImageView getDecreaseIcon() {
        return (AppCompatImageView) this.f5175o.getValue();
    }

    private final AppCompatImageView getIncreaseIcon() {
        return (AppCompatImageView) this.f5176p.getValue();
    }

    private final LinearLayout getIndicatorContainer() {
        return (LinearLayout) this.f5174n.getValue();
    }

    /* access modifiers changed from: private */
    public final AppCompatTextView getIndicatorLabel() {
        return (AppCompatTextView) this.f5177q.getValue();
    }

    private final AppCompatTextView getOldPriceTextView() {
        return (AppCompatTextView) this.f5168h.getValue();
    }

    private final LinearLayout getPriceContainer() {
        return (LinearLayout) this.f5169i.getValue();
    }

    private final AppCompatTextView getPriceTextView() {
        return (AppCompatTextView) this.f5170j.getValue();
    }

    public final void b() {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(getActionButtonIcon(), Key.ROTATION, new float[]{360.0f});
        ofFloat.setRepeatCount(-1);
        ofFloat.setInterpolator(new LinearInterpolator());
        Unit unit = Unit.INSTANCE;
        animatorSet.play(ofFloat);
        animatorSet.setDuration(1000);
        this.f5165e = animatorSet;
    }

    public final void c() {
        String str;
        String str2;
        int width = (int) (((double) com.appsamurai.storyly.util.m.b().width()) * 0.0335d);
        double height = (double) ((float) (((double) com.appsamurai.storyly.util.m.b().height()) * 0.155d));
        float f2 = (float) (0.1d * height);
        double d2 = 0.175d * height;
        int i3 = (int) (0.155d * height);
        int i4 = (int) (0.077d * height);
        double d3 = 0.4d * height;
        int i5 = (int) (height * 0.0113d);
        int i6 = (int) (0.6d * d3);
        int i7 = (int) (0.2d * d3);
        com.appsamurai.storyly.util.formatter.b priceFormatter$storyly_release = this.f5161a.getProduct$storyly_release().getPriceFormatter$storyly_release();
        if (priceFormatter$storyly_release == null) {
            str = null;
        } else {
            STRProductItem sTRProductItem = this.f5166f;
            Float salesPrice = sTRProductItem == null ? null : sTRProductItem.getSalesPrice();
            if (salesPrice == null) {
                STRProductItem sTRProductItem2 = this.f5166f;
                salesPrice = sTRProductItem2 == null ? null : Float.valueOf(sTRProductItem2.getPrice());
            }
            STRProductItem sTRProductItem3 = this.f5166f;
            str = priceFormatter$storyly_release.a(salesPrice, sTRProductItem3 == null ? null : sTRProductItem3.getCurrency());
        }
        com.appsamurai.storyly.util.formatter.b priceFormatter$storyly_release2 = this.f5161a.getProduct$storyly_release().getPriceFormatter$storyly_release();
        if (priceFormatter$storyly_release2 == null) {
            str2 = null;
        } else {
            STRProductItem sTRProductItem4 = this.f5166f;
            Float valueOf = sTRProductItem4 == null ? null : Float.valueOf(sTRProductItem4.getPrice());
            STRProductItem sTRProductItem5 = this.f5166f;
            str2 = priceFormatter$storyly_release2.a(valueOf, sTRProductItem5 == null ? null : sTRProductItem5.getCurrency());
        }
        setElevation(f2);
        a(this.f5166f);
        LinearLayout indicatorContainer = getIndicatorContainer();
        int i8 = (int) d3;
        Class cls = Integer.TYPE;
        int i9 = i5;
        double d4 = d3;
        Class<RelativeLayout.LayoutParams> cls2 = RelativeLayout.LayoutParams.class;
        String str3 = str2;
        ViewGroup.LayoutParams newInstance = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-2, Integer.valueOf(i8)});
        Intrinsics.checkNotNullExpressionValue(newInstance, "layoutParams");
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) newInstance;
        double d5 = d2;
        layoutParams.addRule(20);
        layoutParams.addRule(12);
        layoutParams.bottomMargin = i3;
        layoutParams.leftMargin = width;
        Unit unit = Unit.INSTANCE;
        addView(indicatorContainer, newInstance);
        LinearLayout priceContainer = getPriceContainer();
        ViewGroup.LayoutParams newInstance2 = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-1, -1});
        Intrinsics.checkNotNullExpressionValue(newInstance2, "layoutParams");
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) newInstance2;
        layoutParams2.addRule(10);
        layoutParams2.addRule(20);
        layoutParams2.addRule(2, getIndicatorContainer().getId());
        layoutParams2.setMarginStart(width);
        layoutParams2.setMarginEnd(width);
        addView(priceContainer, newInstance2);
        LinearLayout priceContainer2 = getPriceContainer();
        AppCompatTextView priceTextView = getPriceTextView();
        String str4 = str;
        ViewGroup.LayoutParams newInstance3 = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-2, -2});
        Intrinsics.checkNotNullExpressionValue(newInstance3, "layoutParams");
        ((RelativeLayout.LayoutParams) newInstance3).setMarginEnd(width);
        priceContainer2.addView(priceTextView, newInstance3);
        AppCompatTextView oldPriceTextView = getOldPriceTextView();
        ViewGroup.LayoutParams newInstance4 = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-2, -2});
        Intrinsics.checkNotNullExpressionValue(newInstance4, "layoutParams");
        priceContainer2.addView(oldPriceTextView, newInstance4);
        LinearLayout indicatorContainer2 = getIndicatorContainer();
        AppCompatImageView decreaseIcon = getDecreaseIcon();
        ViewGroup.LayoutParams newInstance5 = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{Integer.valueOf(i6), Integer.valueOf(i6)});
        Intrinsics.checkNotNullExpressionValue(newInstance5, "layoutParams");
        RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) newInstance5;
        layoutParams3.setMarginStart(i7);
        layoutParams3.setMarginEnd(i7);
        indicatorContainer2.addView(decreaseIcon, newInstance5);
        indicatorContainer2.addView(getIndicatorLabel());
        AppCompatImageView increaseIcon = getIncreaseIcon();
        ViewGroup.LayoutParams newInstance6 = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{Integer.valueOf(i6), Integer.valueOf(i6)});
        Intrinsics.checkNotNullExpressionValue(newInstance6, "layoutParams");
        RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) newInstance6;
        layoutParams4.setMarginStart(i7);
        layoutParams4.setMarginEnd(i7);
        indicatorContainer2.addView(increaseIcon, newInstance6);
        LinearLayout actionButtonContainer = getActionButtonContainer();
        ViewGroup.LayoutParams newInstance7 = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-1, Integer.valueOf(i8)});
        Intrinsics.checkNotNullExpressionValue(newInstance7, "layoutParams");
        RelativeLayout.LayoutParams layoutParams5 = (RelativeLayout.LayoutParams) newInstance7;
        layoutParams5.addRule(21);
        layoutParams5.addRule(6, getIndicatorContainer().getId());
        layoutParams5.addRule(17, getIndicatorContainer().getId());
        layoutParams5.bottomMargin = i3;
        layoutParams5.leftMargin = width;
        layoutParams5.rightMargin = width;
        setPadding(i3, 0, i3, 0);
        addView(actionButtonContainer, newInstance7);
        LinearLayout actionButtonContainer2 = getActionButtonContainer();
        AppCompatImageView actionButtonIcon = getActionButtonIcon();
        ViewGroup.LayoutParams newInstance8 = LinearLayout.LayoutParams.class.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{Integer.valueOf(i3), Integer.valueOf(i3)});
        Intrinsics.checkNotNullExpressionValue(newInstance8, "layoutParams");
        LinearLayout.LayoutParams layoutParams6 = (LinearLayout.LayoutParams) newInstance8;
        layoutParams6.setMarginStart(i4);
        layoutParams6.setMarginEnd(i4);
        actionButtonContainer2.addView(actionButtonIcon, newInstance8);
        AppCompatTextView actionButtonText = getActionButtonText();
        ViewGroup.LayoutParams newInstance9 = ViewGroup.LayoutParams.class.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-2, -2});
        Intrinsics.checkNotNullExpressionValue(newInstance9, "layoutParams");
        actionButtonContainer2.addView(actionButtonText, newInstance9);
        getPriceContainer().setGravity(16);
        AppCompatTextView priceTextView2 = getPriceTextView();
        priceTextView2.setTypeface(getConfig().getStory$storyly_release().getInteractiveTypeface$storyly_release());
        priceTextView2.setText(str4 == null ? str3 : str4);
        priceTextView2.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        priceTextView2.setMaxWidth(com.appsamurai.storyly.util.m.b().width() / 2);
        float f3 = (float) (d5 * 0.83d);
        priceTextView2.setTextSize(0, f3);
        priceTextView2.setVisibility(getLayer().j() ? 0 : 8);
        AppCompatTextView oldPriceTextView2 = getOldPriceTextView();
        oldPriceTextView2.setTypeface(getConfig().getStory$storyly_release().getInteractiveTypeface$storyly_release());
        oldPriceTextView2.setText(str3);
        oldPriceTextView2.setTextColor(Color.parseColor("#757575"));
        oldPriceTextView2.setTextSize(0, f3);
        STRProductItem product$storyly_release = getProduct$storyly_release();
        oldPriceTextView2.setVisibility((product$storyly_release != null && product$storyly_release.hasSpecialPrice$storyly_release() && getLayer().i() && getLayer().j()) ? 0 : 4);
        float f4 = (float) (d4 / ((double) 2));
        getIndicatorContainer().setBackground(com.appsamurai.storyly.util.ui.b.a(this, -1, f4, Integer.valueOf(Color.parseColor("#E0E0E0")), i9));
        LinearLayout actionButtonContainer3 = getActionButtonContainer();
        actionButtonContainer3.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        actionButtonContainer3.setPadding(width, 0, width, 0);
        actionButtonContainer3.setBackground(com.appsamurai.storyly.util.ui.b.a(actionButtonContainer3, ViewCompat.MEASURED_STATE_MASK, f4, Integer.valueOf(ViewCompat.MEASURED_STATE_MASK), 0));
        AppCompatTextView actionButtonText2 = getActionButtonText();
        actionButtonText2.setTypeface(getConfig().getStory$storyly_release().getInteractiveTypeface$storyly_release());
        actionButtonText2.setTextSize(0, f3);
        AppCompatTextView indicatorLabel = getIndicatorLabel();
        indicatorLabel.setTypeface(getConfig().getStory$storyly_release().getInteractiveTypeface$storyly_release());
        indicatorLabel.setTextSize(0, f3);
    }

    @NotNull
    public final StorylyConfig getConfig() {
        return this.f5161a;
    }

    @NotNull
    public final g0 getLayer() {
        return this.f5162b;
    }

    @NotNull
    public final Function0<Unit> getOnBuyNowClick$storyly_release() {
        return this.f5167g;
    }

    @Nullable
    public final STRProductItem getProduct$storyly_release() {
        return this.f5166f;
    }

    public final int getQuantity$storyly_release() {
        return ((Number) this.f5164d.getValue(this, f5160r[1])).intValue();
    }

    @NotNull
    public final c getState$storyly_release() {
        return (c) this.f5163c.getValue(this, f5160r[0]);
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a();
    }

    public final void setOnBuyNowClick$storyly_release(@NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.f5167g = function0;
    }

    public final void setProduct$storyly_release(@Nullable STRProductItem sTRProductItem) {
        this.f5166f = sTRProductItem;
    }

    public final void setQuantity$storyly_release(int i3) {
        this.f5164d.setValue(this, f5160r[1], Integer.valueOf(i3));
    }

    public final void setState$storyly_release(@NotNull c cVar) {
        Intrinsics.checkNotNullParameter(cVar, "<set-?>");
        this.f5163c.setValue(this, f5160r[0], cVar);
    }

    public final void a(@Nullable STRProductItem sTRProductItem) {
        String str;
        if (sTRProductItem != null) {
            this.f5166f = sTRProductItem;
            com.appsamurai.storyly.util.formatter.b priceFormatter$storyly_release = this.f5161a.getProduct$storyly_release().getPriceFormatter$storyly_release();
            String str2 = null;
            if (priceFormatter$storyly_release == null) {
                str = null;
            } else {
                Float salesPrice = sTRProductItem.getSalesPrice();
                str = priceFormatter$storyly_release.a(Float.valueOf(salesPrice == null ? sTRProductItem.getPrice() : salesPrice.floatValue()), sTRProductItem.getCurrency());
            }
            com.appsamurai.storyly.util.formatter.b priceFormatter$storyly_release2 = this.f5161a.getProduct$storyly_release().getPriceFormatter$storyly_release();
            if (priceFormatter$storyly_release2 != null) {
                str2 = priceFormatter$storyly_release2.a(Float.valueOf(sTRProductItem.getPrice()), sTRProductItem.getCurrency());
            }
            AppCompatTextView priceTextView = getPriceTextView();
            if (str == null) {
                str = str2;
            }
            priceTextView.setText(str);
            getOldPriceTextView().setText(str2);
            getOldPriceTextView().setVisibility((!sTRProductItem.hasSpecialPrice$storyly_release() || !this.f5162b.i() || !this.f5162b.j()) ? 4 : 0);
        }
    }

    public static final class e extends Lambda implements Function0<AppCompatImageView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5185a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ b f5186b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public e(Context context, b bVar) {
            super(0);
            this.f5185a = context;
            this.f5186b = bVar;
        }

        @NotNull
        /* renamed from: a */
        public final AppCompatImageView invoke() {
            AppCompatImageView appCompatImageView = new AppCompatImageView(this.f5185a);
            b bVar = this.f5186b;
            appCompatImageView.setImageResource(R.drawable.st_incrase_icon);
            appCompatImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            appCompatImageView.setOnClickListener(new C0200a(bVar, 2));
            return appCompatImageView;
        }

        public static final void a(b bVar, View view) {
            Intrinsics.checkNotNullParameter(bVar, "this$0");
            bVar.setQuantity$storyly_release(bVar.getQuantity$storyly_release() + 1);
        }
    }

    public static final class a extends Lambda implements Function0<LinearLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5178a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ b f5179b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(Context context, b bVar) {
            super(0);
            this.f5178a = context;
            this.f5179b = bVar;
        }

        @NotNull
        /* renamed from: a */
        public final LinearLayout invoke() {
            LinearLayout linearLayout = new LinearLayout(this.f5178a);
            b bVar = this.f5179b;
            linearLayout.setId(View.generateViewId());
            linearLayout.setOrientation(0);
            linearLayout.setGravity(17);
            linearLayout.setOnClickListener(new C0200a(bVar, 0));
            return linearLayout;
        }

        public static final void a(b bVar, View view) {
            Intrinsics.checkNotNullParameter(bVar, "this$0");
            b.a(bVar, c.Loading);
            bVar.getOnBuyNowClick$storyly_release().invoke();
        }
    }

    public static final class d extends Lambda implements Function0<AppCompatImageView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5183a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ b f5184b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public d(Context context, b bVar) {
            super(0);
            this.f5183a = context;
            this.f5184b = bVar;
        }

        @NotNull
        /* renamed from: a */
        public final AppCompatImageView invoke() {
            AppCompatImageView appCompatImageView = new AppCompatImageView(this.f5183a);
            b bVar = this.f5184b;
            appCompatImageView.setImageResource(R.drawable.st_decrease_icon);
            appCompatImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            appCompatImageView.setEnabled(false);
            appCompatImageView.setAlpha(0.3f);
            appCompatImageView.setOnClickListener(new C0200a(bVar, 1));
            return appCompatImageView;
        }

        public static final void a(b bVar, View view) {
            Intrinsics.checkNotNullParameter(bVar, "this$0");
            bVar.setQuantity$storyly_release(bVar.getQuantity$storyly_release() - 1);
        }
    }

    public final void a(int i3, boolean z2) {
        getDecreaseIcon().setEnabled(i3 > 1 && z2);
        float f2 = 0.3f;
        getDecreaseIcon().setAlpha((i3 <= 1 || !z2) ? 0.3f : 1.0f);
        getIncreaseIcon().setEnabled(z2);
        AppCompatImageView increaseIcon = getIncreaseIcon();
        if (z2) {
            f2 = 1.0f;
        }
        increaseIcon.setAlpha(f2);
    }

    public static final void a(b bVar, c cVar) {
        int i3 = 0;
        boolean z2 = cVar == c.Default;
        int i4 = z2 ? R.drawable.st_basket : R.drawable.st_load_icon;
        bVar.a(bVar.getQuantity$storyly_release(), z2);
        bVar.getActionButtonContainer().setClickable(z2);
        bVar.getActionButtonContainer().setEnabled(z2);
        AppCompatTextView actionButtonText = bVar.getActionButtonText();
        if (!z2) {
            i3 = 8;
        }
        actionButtonText.setVisibility(i3);
        bVar.getActionButtonIcon().setImageResource(i4);
        if (!z2) {
            AnimatorSet animatorSet = bVar.f5165e;
            if (animatorSet != null) {
                animatorSet.start();
                return;
            }
            return;
        }
        bVar.a();
    }

    public final void a() {
        ArrayList<Animator> childAnimations;
        AnimatorSet animatorSet = this.f5165e;
        if (!(animatorSet == null || (childAnimations = animatorSet.getChildAnimations()) == null)) {
            for (Animator removeAllListeners : childAnimations) {
                removeAllListeners.removeAllListeners();
            }
        }
        AnimatorSet animatorSet2 = this.f5165e;
        if (animatorSet2 != null) {
            animatorSet2.removeAllListeners();
        }
        AnimatorSet animatorSet3 = this.f5165e;
        if (animatorSet3 != null) {
            animatorSet3.end();
        }
        AnimatorSet animatorSet4 = this.f5165e;
        if (animatorSet4 != null) {
            animatorSet4.cancel();
        }
        getActionButtonIcon().clearAnimation();
    }
}
