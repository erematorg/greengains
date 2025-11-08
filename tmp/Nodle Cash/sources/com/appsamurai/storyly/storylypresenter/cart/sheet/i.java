package com.appsamurai.storyly.storylypresenter.cart.sheet;

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
import androidx.core.view.ViewCompat;
import com.appsamurai.storyly.R;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.data.g0;
import com.appsamurai.storyly.data.managers.product.STRCart;
import com.appsamurai.storyly.util.m;
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
public final class i extends RelativeLayout {

    /* renamed from: o  reason: collision with root package name */
    public static final /* synthetic */ KProperty<Object>[] f4911o = {androidx.compose.ui.autofill.a.m(i.class, RemoteConfigConstants.ResponseFieldKey.STATE, "getState$storyly_release()Lcom/appsamurai/storyly/storylypresenter/cart/sheet/CartIndicatorState;", 0)};
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final StorylyConfig f4912a;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    public final g0 f4913b;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public STRCart f4914c;
    @NotNull

    /* renamed from: d  reason: collision with root package name */
    public final ReadWriteProperty f4915d;
    @NotNull

    /* renamed from: e  reason: collision with root package name */
    public Function0<Unit> f4916e = e.f4933a;
    @Nullable

    /* renamed from: f  reason: collision with root package name */
    public AnimatorSet f4917f;
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public final Lazy f4918g;
    @NotNull

    /* renamed from: h  reason: collision with root package name */
    public final Lazy f4919h;
    @NotNull

    /* renamed from: i  reason: collision with root package name */
    public final Lazy f4920i;
    @NotNull

    /* renamed from: j  reason: collision with root package name */
    public final Lazy f4921j;
    @NotNull

    /* renamed from: k  reason: collision with root package name */
    public final Lazy f4922k;
    @NotNull

    /* renamed from: l  reason: collision with root package name */
    public final Lazy f4923l;
    @NotNull

    /* renamed from: m  reason: collision with root package name */
    public final Lazy f4924m;
    @NotNull

    /* renamed from: n  reason: collision with root package name */
    public final Lazy f4925n;

    public static final class b extends Lambda implements Function0<AppCompatImageView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f4928a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public b(Context context) {
            super(0);
            this.f4928a = context;
        }

        public Object invoke() {
            AppCompatImageView appCompatImageView = new AppCompatImageView(this.f4928a);
            appCompatImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            appCompatImageView.setImageResource(R.drawable.st_right_arrow_icon);
            appCompatImageView.setColorFilter(-1, PorterDuff.Mode.SRC_IN);
            return appCompatImageView;
        }
    }

    public static final class c extends Lambda implements Function0<AppCompatTextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f4929a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ i f4930b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public c(Context context, i iVar) {
            super(0);
            this.f4929a = context;
            this.f4930b = iVar;
        }

        public Object invoke() {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f4929a);
            i iVar = this.f4930b;
            appCompatTextView.setEllipsize(TextUtils.TruncateAt.END);
            appCompatTextView.setSingleLine(true);
            appCompatTextView.setIncludeFontPadding(false);
            g0 g0Var = iVar.f4913b;
            appCompatTextView.setText(g0Var == null ? null : g0Var.a());
            appCompatTextView.setHorizontallyScrolling(false);
            appCompatTextView.setGravity(17);
            appCompatTextView.setTextColor(-1);
            appCompatTextView.setTextAlignment(1);
            com.appsamurai.storyly.util.d.a(appCompatTextView);
            return appCompatTextView;
        }
    }

    public static final class d extends Lambda implements Function0<AppCompatTextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f4931a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ i f4932b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public d(Context context, i iVar) {
            super(0);
            this.f4931a = context;
            this.f4932b = iVar;
        }

        public Object invoke() {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f4931a);
            i iVar = this.f4932b;
            com.appsamurai.storyly.util.formatter.b priceFormatter$storyly_release = iVar.f4912a.getProduct$storyly_release().getPriceFormatter$storyly_release();
            String a2 = priceFormatter$storyly_release == null ? null : priceFormatter$storyly_release.a(iVar.f4914c.getOldTotalPrice(), iVar.f4914c.getCurrency());
            appCompatTextView.setId(View.generateViewId());
            appCompatTextView.setSingleLine(true);
            appCompatTextView.setEllipsize(TextUtils.TruncateAt.END);
            appCompatTextView.setIncludeFontPadding(false);
            appCompatTextView.setText(a2);
            appCompatTextView.setVisibility((Intrinsics.areEqual(iVar.f4914c.getTotalPrice(), iVar.f4914c.getOldTotalPrice()) || a2 == null) ? 8 : 0);
            appCompatTextView.setLineSpacing(0.0f, 0.0f);
            appCompatTextView.setPaintFlags(appCompatTextView.getPaintFlags() | 16);
            appCompatTextView.setHorizontallyScrolling(false);
            appCompatTextView.setTextColor(Color.parseColor("#757575"));
            return appCompatTextView;
        }
    }

    public static final class e extends Lambda implements Function0<Unit> {

        /* renamed from: a  reason: collision with root package name */
        public static final e f4933a = new e();

        public e() {
            super(0);
        }

        public /* bridge */ /* synthetic */ Object invoke() {
            return Unit.INSTANCE;
        }
    }

    public static final class f extends Lambda implements Function0<LinearLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f4934a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public f(Context context) {
            super(0);
            this.f4934a = context;
        }

        public Object invoke() {
            LinearLayout linearLayout = new LinearLayout(this.f4934a);
            linearLayout.setId(View.generateViewId());
            linearLayout.setOrientation(0);
            linearLayout.setGravity(15);
            return linearLayout;
        }
    }

    public static final class g extends ObservableProperty<a> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ i f4935a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public g(Object obj, Object obj2, i iVar) {
            super(obj2);
            this.f4935a = iVar;
        }

        public void afterChange(@NotNull KProperty<?> kProperty, a aVar, a aVar2) {
            Intrinsics.checkNotNullParameter(kProperty, "property");
            a aVar3 = aVar;
            i.a(this.f4935a, aVar2);
        }
    }

    public static final class h extends Lambda implements Function0<LinearLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f4936a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public h(Context context) {
            super(0);
            this.f4936a = context;
        }

        public Object invoke() {
            LinearLayout linearLayout = new LinearLayout(this.f4936a);
            linearLayout.setId(View.generateViewId());
            linearLayout.setOrientation(1);
            return linearLayout;
        }
    }

    /* renamed from: com.appsamurai.storyly.storylypresenter.cart.sheet.i$i  reason: collision with other inner class name */
    public static final class C0021i extends Lambda implements Function0<AppCompatTextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f4937a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ i f4938b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public C0021i(Context context, i iVar) {
            super(0);
            this.f4937a = context;
            this.f4938b = iVar;
        }

        public Object invoke() {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f4937a);
            i iVar = this.f4938b;
            appCompatTextView.setId(View.generateViewId());
            appCompatTextView.setSingleLine(true);
            appCompatTextView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            com.appsamurai.storyly.util.formatter.b priceFormatter$storyly_release = iVar.f4912a.getProduct$storyly_release().getPriceFormatter$storyly_release();
            appCompatTextView.setText(priceFormatter$storyly_release == null ? null : priceFormatter$storyly_release.a(Float.valueOf(iVar.f4914c.getTotalPrice()), iVar.f4914c.getCurrency()));
            appCompatTextView.setEllipsize(TextUtils.TruncateAt.END);
            appCompatTextView.setIncludeFontPadding(false);
            appCompatTextView.setHorizontallyScrolling(false);
            return appCompatTextView;
        }
    }

    public static final class j extends Lambda implements Function0<AppCompatTextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f4939a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ i f4940b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public j(Context context, i iVar) {
            super(0);
            this.f4939a = context;
            this.f4940b = iVar;
        }

        public Object invoke() {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f4939a);
            i iVar = this.f4940b;
            appCompatTextView.setId(View.generateViewId());
            appCompatTextView.setSingleLine(true);
            appCompatTextView.setEllipsize(TextUtils.TruncateAt.END);
            appCompatTextView.setIncludeFontPadding(false);
            appCompatTextView.setLineSpacing(0.0f, 0.0f);
            g0 g0Var = iVar.f4913b;
            appCompatTextView.setText(g0Var == null ? null : g0Var.h());
            appCompatTextView.setTextColor(Color.parseColor("#757575"));
            appCompatTextView.setHorizontallyScrolling(false);
            return appCompatTextView;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public i(@NotNull Context context, @Nullable AttributeSet attributeSet, int i3, @NotNull StorylyConfig storylyConfig, @Nullable g0 g0Var, @NotNull STRCart sTRCart) {
        super(context, attributeSet, i3);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        Intrinsics.checkNotNullParameter(sTRCart, "cart");
        this.f4912a = storylyConfig;
        this.f4913b = g0Var;
        this.f4914c = sTRCart;
        Delegates delegates = Delegates.INSTANCE;
        a aVar = a.Default;
        this.f4915d = new g(aVar, aVar, this);
        this.f4918g = LazyKt.lazy(new h(context));
        this.f4919h = LazyKt.lazy(new j(context, this));
        this.f4920i = LazyKt.lazy(new f(context));
        this.f4921j = LazyKt.lazy(new C0021i(context, this));
        this.f4922k = LazyKt.lazy(new d(context, this));
        this.f4923l = LazyKt.lazy(new a(context, this));
        this.f4924m = LazyKt.lazy(new c(context, this));
        this.f4925n = LazyKt.lazy(new b(context));
        setId(View.generateViewId());
        setBackgroundColor(-1);
        b();
    }

    public static final void a(i iVar, a aVar) {
        boolean z2 = aVar == a.Default;
        int i3 = z2 ? R.drawable.st_right_arrow_icon : R.drawable.st_load_icon;
        iVar.getButtonContainer().setClickable(z2);
        iVar.getButtonContainer().setEnabled(z2);
        iVar.getButtonImage().setImageResource(i3);
        if (!z2) {
            AnimatorSet animatorSet = iVar.f4917f;
            if (animatorSet != null) {
                animatorSet.start();
                return;
            }
            return;
        }
        iVar.a();
    }

    private final LinearLayout getButtonContainer() {
        return (LinearLayout) this.f4923l.getValue();
    }

    private final AppCompatImageView getButtonImage() {
        return (AppCompatImageView) this.f4925n.getValue();
    }

    private final AppCompatTextView getButtonText() {
        return (AppCompatTextView) this.f4924m.getValue();
    }

    private final AppCompatTextView getOldTotalPriceTextView() {
        return (AppCompatTextView) this.f4922k.getValue();
    }

    private final LinearLayout getPriceContainer() {
        return (LinearLayout) this.f4920i.getValue();
    }

    private final LinearLayout getTotalContainer() {
        return (LinearLayout) this.f4918g.getValue();
    }

    private final AppCompatTextView getTotalPriceTextView() {
        return (AppCompatTextView) this.f4921j.getValue();
    }

    private final AppCompatTextView getTotalTextView() {
        return (AppCompatTextView) this.f4919h.getValue();
    }

    public final void b() {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(getButtonImage(), Key.ROTATION, new float[]{360.0f});
        ofFloat.setRepeatCount(-1);
        ofFloat.setInterpolator(new LinearInterpolator());
        Unit unit = Unit.INSTANCE;
        animatorSet.play(ofFloat);
        animatorSet.setDuration(1000);
        this.f4917f = animatorSet;
    }

    public final void c() {
        double height = (double) ((float) (((double) m.b().height()) * 0.137d));
        double d2 = 0.136d * height;
        int i3 = (int) d2;
        int i4 = (int) (0.465d * height);
        double d3 = 0.181d * height;
        int i5 = (int) d3;
        float f2 = (float) d3;
        LinearLayout buttonContainer = getButtonContainer();
        Class cls = Integer.TYPE;
        Class<RelativeLayout.LayoutParams> cls2 = RelativeLayout.LayoutParams.class;
        float f3 = (float) (0.1d * height);
        ViewGroup.LayoutParams newInstance = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-2, Integer.valueOf(i4)});
        Intrinsics.checkNotNullExpressionValue(newInstance, "layoutParams");
        float f4 = f2;
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) newInstance;
        float f5 = (float) d2;
        layoutParams.addRule(15);
        layoutParams.addRule(21);
        layoutParams.rightMargin = i3;
        Unit unit = Unit.INSTANCE;
        addView(buttonContainer, newInstance);
        LinearLayout totalContainer = getTotalContainer();
        ViewGroup.LayoutParams newInstance2 = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-2, -2});
        Intrinsics.checkNotNullExpressionValue(newInstance2, "layoutParams");
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) newInstance2;
        float f6 = (float) (0.159d * height);
        layoutParams2.addRule(16, getButtonContainer().getId());
        layoutParams2.addRule(15);
        layoutParams2.addRule(20);
        addView(totalContainer, newInstance2);
        LinearLayout totalContainer2 = getTotalContainer();
        AppCompatTextView totalTextView = getTotalTextView();
        ViewGroup.LayoutParams newInstance3 = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-2, -2});
        Intrinsics.checkNotNullExpressionValue(newInstance3, "layoutParams");
        RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) newInstance3;
        layoutParams3.setMarginStart(i3);
        layoutParams3.bottomMargin = (int) (0.045d * height);
        totalContainer2.addView(totalTextView, newInstance3);
        LinearLayout priceContainer = getPriceContainer();
        ViewGroup.LayoutParams newInstance4 = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-2, -2});
        Intrinsics.checkNotNullExpressionValue(newInstance4, "layoutParams");
        totalContainer2.addView(priceContainer, newInstance4);
        LinearLayout priceContainer2 = getPriceContainer();
        AppCompatTextView totalPriceTextView = getTotalPriceTextView();
        ViewGroup.LayoutParams newInstance5 = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-2, -2});
        Intrinsics.checkNotNullExpressionValue(newInstance5, "layoutParams");
        RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) newInstance5;
        layoutParams4.addRule(15);
        layoutParams4.addRule(20);
        layoutParams4.setMarginEnd((int) (height * 0.09d));
        layoutParams4.setMarginStart(i3);
        priceContainer2.addView(totalPriceTextView, newInstance5);
        AppCompatTextView oldTotalPriceTextView = getOldTotalPriceTextView();
        ViewGroup.LayoutParams newInstance6 = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-2, -2});
        Intrinsics.checkNotNullExpressionValue(newInstance6, "layoutParams");
        RelativeLayout.LayoutParams layoutParams5 = (RelativeLayout.LayoutParams) newInstance6;
        layoutParams5.addRule(15);
        layoutParams5.addRule(20);
        priceContainer2.addView(oldTotalPriceTextView, newInstance6);
        LinearLayout buttonContainer2 = getButtonContainer();
        AppCompatTextView buttonText = getButtonText();
        Class<LinearLayout.LayoutParams> cls3 = LinearLayout.LayoutParams.class;
        ViewGroup.LayoutParams newInstance7 = cls3.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-2, -2});
        Intrinsics.checkNotNullExpressionValue(newInstance7, "layoutParams");
        LinearLayout.LayoutParams layoutParams6 = (LinearLayout.LayoutParams) newInstance7;
        layoutParams6.setMarginEnd((int) (0.091d * height));
        layoutParams6.setMarginStart(i5);
        buttonContainer2.addView(buttonText, newInstance7);
        AppCompatImageView buttonImage = getButtonImage();
        ViewGroup.LayoutParams newInstance8 = cls3.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{Integer.valueOf(i5), Integer.valueOf(i5)});
        Intrinsics.checkNotNullExpressionValue(newInstance8, "layoutParams");
        ((LinearLayout.LayoutParams) newInstance8).setMarginEnd(i5);
        buttonContainer2.addView(buttonImage, newInstance8);
        int i6 = 0;
        getButtonContainer().setBackground(com.appsamurai.storyly.util.ui.b.a(this, ViewCompat.MEASURED_STATE_MASK, (float) (i4 / 2), Integer.valueOf(ViewCompat.MEASURED_STATE_MASK), 0));
        AppCompatTextView buttonText2 = getButtonText();
        buttonText2.setTypeface(this.f4912a.getStory$storyly_release().getInteractiveTypeface$storyly_release());
        buttonText2.setTextSize(0, f6);
        AppCompatTextView totalTextView2 = getTotalTextView();
        totalTextView2.setTypeface(this.f4912a.getStory$storyly_release().getInteractiveTypeface$storyly_release());
        float f7 = f5;
        totalTextView2.setTextSize(0, f7);
        AppCompatTextView totalPriceTextView2 = getTotalPriceTextView();
        totalPriceTextView2.setTypeface(this.f4912a.getStory$storyly_release().getInteractiveTypeface$storyly_release());
        totalPriceTextView2.setTextSize(0, f4);
        AppCompatTextView oldTotalPriceTextView2 = getOldTotalPriceTextView();
        oldTotalPriceTextView2.setTypeface(this.f4912a.getStory$storyly_release().getInteractiveTypeface$storyly_release());
        oldTotalPriceTextView2.setTextSize(0, f7);
        if (this.f4914c.getOldTotalPrice() == null || Intrinsics.areEqual(this.f4914c.getOldTotalPrice(), 0.0f)) {
            i6 = 4;
        }
        oldTotalPriceTextView2.setVisibility(i6);
        setElevation(f3);
    }

    @NotNull
    public final Function0<Unit> getOnGoToCheckout$storyly_release() {
        return this.f4916e;
    }

    @NotNull
    public final a getState$storyly_release() {
        return (a) this.f4915d.getValue(this, f4911o[0]);
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a();
    }

    public final void setOnGoToCheckout$storyly_release(@NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.f4916e = function0;
    }

    public final void setState$storyly_release(@NotNull a aVar) {
        Intrinsics.checkNotNullParameter(aVar, "<set-?>");
        this.f4915d.setValue(this, f4911o[0], aVar);
    }

    public static final class a extends Lambda implements Function0<LinearLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f4926a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ i f4927b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(Context context, i iVar) {
            super(0);
            this.f4926a = context;
            this.f4927b = iVar;
        }

        @NotNull
        /* renamed from: a */
        public final LinearLayout invoke() {
            LinearLayout linearLayout = new LinearLayout(this.f4926a);
            i iVar = this.f4927b;
            linearLayout.setId(View.generateViewId());
            linearLayout.setOrientation(0);
            linearLayout.setGravity(17);
            linearLayout.setOnClickListener(new E0.c(iVar, 8));
            return linearLayout;
        }

        public static final void a(i iVar, View view) {
            Intrinsics.checkNotNullParameter(iVar, "this$0");
            iVar.getOnGoToCheckout$storyly_release().invoke();
        }
    }

    public final void a() {
        ArrayList<Animator> childAnimations;
        AnimatorSet animatorSet = this.f4917f;
        if (!(animatorSet == null || (childAnimations = animatorSet.getChildAnimations()) == null)) {
            for (Animator removeAllListeners : childAnimations) {
                removeAllListeners.removeAllListeners();
            }
        }
        AnimatorSet animatorSet2 = this.f4917f;
        if (animatorSet2 != null) {
            animatorSet2.removeAllListeners();
        }
        AnimatorSet animatorSet3 = this.f4917f;
        if (animatorSet3 != null) {
            animatorSet3.end();
        }
        AnimatorSet animatorSet4 = this.f4917f;
        if (animatorSet4 != null) {
            animatorSet4.cancel();
        }
        getButtonImage().clearAnimation();
    }

    public final void a(@Nullable STRCart sTRCart) {
        if (sTRCart != null) {
            this.f4914c = sTRCart;
            AppCompatTextView totalPriceTextView = getTotalPriceTextView();
            com.appsamurai.storyly.util.formatter.b priceFormatter$storyly_release = this.f4912a.getProduct$storyly_release().getPriceFormatter$storyly_release();
            String str = null;
            totalPriceTextView.setText(priceFormatter$storyly_release == null ? null : priceFormatter$storyly_release.a(Float.valueOf(sTRCart.getTotalPrice()), sTRCart.getCurrency()));
            AppCompatTextView oldTotalPriceTextView = getOldTotalPriceTextView();
            com.appsamurai.storyly.util.formatter.b priceFormatter$storyly_release2 = this.f4912a.getProduct$storyly_release().getPriceFormatter$storyly_release();
            if (priceFormatter$storyly_release2 != null) {
                str = priceFormatter$storyly_release2.a(sTRCart.getOldTotalPrice(), sTRCart.getCurrency());
            }
            oldTotalPriceTextView.setText(str);
            getOldTotalPriceTextView().setVisibility(!Intrinsics.areEqual(sTRCart.getOldTotalPrice(), sTRCart.getTotalPrice()) ? 0 : 8);
        }
    }
}
