package com.appsamurai.storyly.storylypresenter.product;

import android.animation.StateListAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.ViewCompat;
import com.appsamurai.storyly.R;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.util.m;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class b extends FrameLayout {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final StorylyConfig f5110a;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    public final String f5111b;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    public final String f5112c;
    @Nullable

    /* renamed from: d  reason: collision with root package name */
    public final String f5113d;
    @NotNull

    /* renamed from: e  reason: collision with root package name */
    public Function0<Unit> f5114e;
    @NotNull

    /* renamed from: f  reason: collision with root package name */
    public Function0<Unit> f5115f;
    @Nullable

    /* renamed from: g  reason: collision with root package name */
    public BottomSheetBehavior<FrameLayout> f5116g;
    @NotNull

    /* renamed from: h  reason: collision with root package name */
    public com.appsamurai.storyly.databinding.a f5117h;
    @NotNull

    /* renamed from: i  reason: collision with root package name */
    public a f5118i = a.Default;
    @NotNull

    /* renamed from: j  reason: collision with root package name */
    public final Lazy f5119j;
    @NotNull

    /* renamed from: k  reason: collision with root package name */
    public final Lazy f5120k;
    @NotNull

    /* renamed from: l  reason: collision with root package name */
    public final Lazy f5121l;
    @NotNull

    /* renamed from: m  reason: collision with root package name */
    public final Lazy f5122m;
    @NotNull

    /* renamed from: n  reason: collision with root package name */
    public final Lazy f5123n;
    @NotNull

    /* renamed from: o  reason: collision with root package name */
    public final Lazy f5124o;
    @NotNull

    /* renamed from: p  reason: collision with root package name */
    public final Lazy f5125p;
    @NotNull

    /* renamed from: q  reason: collision with root package name */
    public final Lazy f5126q;

    /* renamed from: com.appsamurai.storyly.storylypresenter.product.b$b  reason: collision with other inner class name */
    public static final class C0025b extends Lambda implements Function0<AppCompatImageView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5129a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public C0025b(Context context) {
            super(0);
            this.f5129a = context;
        }

        public Object invoke() {
            AppCompatImageView appCompatImageView = new AppCompatImageView(this.f5129a);
            appCompatImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            appCompatImageView.setImageResource(R.drawable.st_basket);
            return appCompatImageView;
        }
    }

    public static final class c extends Lambda implements Function0<AppCompatTextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5130a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public c(Context context) {
            super(0);
            this.f5130a = context;
        }

        public Object invoke() {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f5130a);
            appCompatTextView.setEllipsize(TextUtils.TruncateAt.END);
            appCompatTextView.setMaxLines(1);
            appCompatTextView.setMinLines(1);
            appCompatTextView.setIncludeFontPadding(false);
            appCompatTextView.setHorizontallyScrolling(false);
            appCompatTextView.setGravity(17);
            appCompatTextView.setTextColor(-1);
            appCompatTextView.setTextAlignment(1);
            com.appsamurai.storyly.util.d.a(appCompatTextView);
            return appCompatTextView;
        }
    }

    public static final class e extends Lambda implements Function0<LinearLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5133a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public e(Context context) {
            super(0);
            this.f5133a = context;
        }

        public Object invoke() {
            LinearLayout linearLayout = new LinearLayout(this.f5133a);
            linearLayout.setOrientation(1);
            return linearLayout;
        }
    }

    public static final class f extends BottomSheetBehavior.BottomSheetCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ BottomSheetBehavior<FrameLayout> f5134a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ b f5135b;

        public f(BottomSheetBehavior<FrameLayout> bottomSheetBehavior, b bVar) {
            this.f5134a = bottomSheetBehavior;
            this.f5135b = bVar;
        }

        public void onSlide(@NotNull View view, float f2) {
            Intrinsics.checkNotNullParameter(view, "bottomSheet");
        }

        public void onStateChanged(@NotNull View view, int i3) {
            Intrinsics.checkNotNullParameter(view, "bottomSheet");
            if (this.f5134a.getState() == 5) {
                ViewParent parent = this.f5135b.getParent();
                ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
                if (viewGroup != null) {
                    viewGroup.removeView(this.f5135b);
                }
                b bVar = this.f5135b;
                if (bVar.f5118i == a.WithGoToCart) {
                    bVar.getOnBasketButtonClicked().invoke();
                } else {
                    bVar.getResume().invoke();
                }
            }
        }
    }

    public static final class g extends Lambda implements Function0<LinearLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5136a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public g(Context context) {
            super(0);
            this.f5136a = context;
        }

        public Object invoke() {
            LinearLayout linearLayout = new LinearLayout(this.f5136a);
            linearLayout.setOrientation(1);
            linearLayout.setGravity(17);
            return linearLayout;
        }
    }

    public static final class h extends Lambda implements Function0<AppCompatImageView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5137a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public h(Context context) {
            super(0);
            this.f5137a = context;
        }

        public Object invoke() {
            AppCompatImageView appCompatImageView = new AppCompatImageView(this.f5137a);
            appCompatImageView.setImageResource(R.drawable.st_success);
            appCompatImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return appCompatImageView;
        }
    }

    public static final class i extends Lambda implements Function0<AppCompatTextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5138a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public i(Context context) {
            super(0);
            this.f5138a = context;
        }

        public Object invoke() {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f5138a);
            appCompatTextView.setEllipsize(TextUtils.TruncateAt.END);
            appCompatTextView.setMaxLines(3);
            appCompatTextView.setMinLines(1);
            appCompatTextView.setIncludeFontPadding(false);
            appCompatTextView.setHorizontallyScrolling(false);
            appCompatTextView.setGravity(17);
            appCompatTextView.setTextColor(Color.parseColor("#212121"));
            appCompatTextView.setTextAlignment(1);
            com.appsamurai.storyly.util.d.a(appCompatTextView);
            return appCompatTextView;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public b(@NotNull Context context, @NotNull StorylyConfig storylyConfig, @Nullable String str, @Nullable String str2, @Nullable String str3, @NotNull Function0<Unit> function0, @NotNull Function0<Unit> function02) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        Intrinsics.checkNotNullParameter(function0, "onBasketButtonClicked");
        Intrinsics.checkNotNullParameter(function02, "resume");
        this.f5110a = storylyConfig;
        this.f5111b = str;
        this.f5112c = str2;
        this.f5113d = str3;
        this.f5114e = function0;
        this.f5115f = function02;
        com.appsamurai.storyly.databinding.a a2 = com.appsamurai.storyly.databinding.a.a(LayoutInflater.from(context));
        Intrinsics.checkNotNullExpressionValue(a2, "inflate(\n        LayoutI…later.from(context)\n    )");
        this.f5117h = a2;
        this.f5119j = LazyKt.lazy(new e(context));
        this.f5120k = LazyKt.lazy(new g(context));
        this.f5121l = LazyKt.lazy(new h(context));
        this.f5122m = LazyKt.lazy(new i(context));
        this.f5123n = LazyKt.lazy(new a(context, this));
        this.f5124o = LazyKt.lazy(new c(context));
        this.f5125p = LazyKt.lazy(new C0025b(context));
        this.f5126q = LazyKt.lazy(new d(context, this));
        a();
        b();
    }

    public static final void a(b bVar, View view) {
        Intrinsics.checkNotNullParameter(bVar, "this$0");
        a(bVar, (a) null, 1);
    }

    private final LinearLayout getBasketButtonContainer() {
        return (LinearLayout) this.f5123n.getValue();
    }

    private final AppCompatImageView getBasketButtonIcon() {
        return (AppCompatImageView) this.f5125p.getValue();
    }

    private final AppCompatTextView getBasketButtonText() {
        return (AppCompatTextView) this.f5124o.getValue();
    }

    private final AppCompatButton getCloseButton() {
        return (AppCompatButton) this.f5126q.getValue();
    }

    private final LinearLayout getContainer() {
        return (LinearLayout) this.f5119j.getValue();
    }

    private final LinearLayout getSuccessMessageContainer() {
        return (LinearLayout) this.f5120k.getValue();
    }

    private final AppCompatImageView getSuccessMessageIcon() {
        return (AppCompatImageView) this.f5121l.getValue();
    }

    private final AppCompatTextView getSuccessMessageText() {
        return (AppCompatTextView) this.f5122m.getValue();
    }

    public final void b() {
        int width = (int) (((double) m.b().width()) * 0.388d);
        int width2 = (int) (((double) m.b().width()) * 0.0445d);
        int width3 = (int) (((double) m.b().width()) * 0.07d);
        double d2 = (double) width;
        float f2 = (float) (0.1d * d2);
        float width4 = (float) (((double) m.b().width()) * 0.033d);
        double d3 = d2 * 0.135d;
        int i3 = (int) (d2 * 0.28d);
        int width5 = (int) (((double) m.b().width()) * 0.07d);
        int width6 = (int) (((double) m.b().width()) * 0.0445d);
        int width7 = (int) (((double) m.b().width()) * 0.11388888888d);
        double d4 = (double) width7;
        int i4 = width7;
        int i5 = width6;
        int i6 = (int) (d4 * 0.4d);
        int width8 = (int) (((double) m.b().width()) * 0.027265d);
        double d5 = d4;
        FrameLayout frameLayout = this.f5117h.f4323b;
        int i7 = (int) (d4 * 0.024d);
        int i8 = width8;
        int i9 = (int) (d4 * 0.2d);
        int i10 = (int) (((double) i3) * 0.3d);
        int i11 = i3;
        FrameLayout frameLayout2 = frameLayout;
        frameLayout2.setBackground(com.appsamurai.storyly.util.ui.b.a(this, -1, width4, width4, 0.0f, 0.0f, (Integer) null, 0, 96));
        RelativeLayout relativeLayout = this.f5117h.f4322a;
        Class cls = Integer.TYPE;
        Class<ViewGroup.LayoutParams> cls2 = ViewGroup.LayoutParams.class;
        ViewGroup.LayoutParams newInstance = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-1, -1});
        Intrinsics.checkNotNullExpressionValue(newInstance, "layoutParams");
        Unit unit = Unit.INSTANCE;
        addView(relativeLayout, newInstance);
        FrameLayout frameLayout3 = this.f5117h.f4323b;
        LinearLayout container = getContainer();
        ViewGroup.LayoutParams newInstance2 = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-1, -2});
        Intrinsics.checkNotNullExpressionValue(newInstance2, "layoutParams");
        frameLayout3.addView(container, newInstance2);
        LinearLayout container2 = getContainer();
        LinearLayout successMessageContainer = getSuccessMessageContainer();
        Class<FrameLayout.LayoutParams> cls3 = FrameLayout.LayoutParams.class;
        ViewGroup.LayoutParams newInstance3 = cls3.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-1, Integer.valueOf(width)});
        Intrinsics.checkNotNullExpressionValue(newInstance3, "layoutParams");
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) newInstance3;
        layoutParams.topMargin = width3;
        layoutParams.bottomMargin = width3;
        layoutParams.leftMargin = width2;
        layoutParams.rightMargin = width2;
        container2.addView(successMessageContainer, newInstance3);
        LinearLayout container3 = getContainer();
        LinearLayout basketButtonContainer = getBasketButtonContainer();
        ViewGroup.LayoutParams newInstance4 = cls3.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-1, Integer.valueOf(i4)});
        Intrinsics.checkNotNullExpressionValue(newInstance4, "layoutParams");
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) newInstance4;
        int i12 = i5;
        layoutParams2.leftMargin = i12;
        layoutParams2.rightMargin = i12;
        container3.addView(basketButtonContainer, newInstance4);
        LinearLayout basketButtonContainer2 = getBasketButtonContainer();
        AppCompatImageView basketButtonIcon = getBasketButtonIcon();
        ViewGroup.LayoutParams newInstance5 = cls3.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{Integer.valueOf(i6), Integer.valueOf(i6)});
        Intrinsics.checkNotNullExpressionValue(newInstance5, "layoutParams");
        ((FrameLayout.LayoutParams) newInstance5).setMarginEnd(i9);
        basketButtonContainer2.addView(basketButtonIcon, newInstance5);
        AppCompatTextView basketButtonText = getBasketButtonText();
        ViewGroup.LayoutParams newInstance6 = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-2, -2});
        Intrinsics.checkNotNullExpressionValue(newInstance6, "layoutParams");
        basketButtonContainer2.addView(basketButtonText, newInstance6);
        LinearLayout container4 = getContainer();
        AppCompatButton closeButton = getCloseButton();
        ViewGroup.LayoutParams newInstance7 = cls3.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-1, Integer.valueOf(i4)});
        Intrinsics.checkNotNullExpressionValue(newInstance7, "layoutParams");
        FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) newInstance7;
        layoutParams3.bottomMargin = width5;
        layoutParams3.topMargin = i8;
        layoutParams3.leftMargin = i12;
        layoutParams3.rightMargin = i12;
        container4.addView(closeButton, newInstance7);
        LinearLayout successMessageContainer2 = getSuccessMessageContainer();
        AppCompatImageView successMessageIcon = getSuccessMessageIcon();
        ViewGroup.LayoutParams newInstance8 = cls3.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{Integer.valueOf(i11), Integer.valueOf(i11)});
        Intrinsics.checkNotNullExpressionValue(newInstance8, "layoutParams");
        ((FrameLayout.LayoutParams) newInstance8).topMargin = i10;
        successMessageContainer2.addView(successMessageIcon, newInstance8);
        AppCompatTextView successMessageText = getSuccessMessageText();
        ViewGroup.LayoutParams newInstance9 = LinearLayout.LayoutParams.class.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-1, -2});
        Intrinsics.checkNotNullExpressionValue(newInstance9, "layoutParams");
        LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) newInstance9;
        layoutParams4.weight = 1.0f;
        int i13 = width2 * 2;
        layoutParams4.leftMargin = i13;
        layoutParams4.rightMargin = i13;
        successMessageContainer2.addView(successMessageText, newInstance9);
        LinearLayout successMessageContainer3 = getSuccessMessageContainer();
        successMessageContainer3.setBackground(com.appsamurai.storyly.util.ui.b.a(successMessageContainer3, Color.parseColor("#F5F5F5"), f2, Integer.valueOf(Color.parseColor("#F5F5F5")), 0));
        AppCompatTextView successMessageText2 = getSuccessMessageText();
        successMessageText2.setText(getMessage());
        successMessageText2.setTypeface(this.f5110a.getStory$storyly_release().getInteractiveTypeface$storyly_release());
        double d6 = d3;
        successMessageText2.setLineHeight((int) d6);
        successMessageText2.setTextSize(0, (float) (d6 * 0.85d));
        LinearLayout basketButtonContainer3 = getBasketButtonContainer();
        float f3 = (float) (i4 / 2);
        basketButtonContainer3.setBackground(com.appsamurai.storyly.util.ui.b.a(basketButtonContainer3, ViewCompat.MEASURED_STATE_MASK, f3, Integer.valueOf(ViewCompat.MEASURED_STATE_MASK), 0));
        AppCompatTextView basketButtonText2 = getBasketButtonText();
        basketButtonText2.setForeground((Drawable) null);
        basketButtonText2.setText(this.f5111b);
        basketButtonText2.setTypeface(this.f5110a.getStory$storyly_release().getInteractiveTypeface$storyly_release());
        float f4 = (float) (0.414d * d5 * 0.85d);
        basketButtonText2.setTextSize(0, f4);
        AppCompatButton closeButton2 = getCloseButton();
        closeButton2.setText(this.f5112c);
        closeButton2.setTypeface(this.f5110a.getStory$storyly_release().getInteractiveTypeface$storyly_release());
        closeButton2.setTextSize(0, f4);
        closeButton2.setBackground(com.appsamurai.storyly.util.ui.b.a(closeButton2, -1, f3, Integer.valueOf(Color.parseColor("#E0E0E0")), i7));
        this.f5117h.f4322a.setOnClickListener(new Z.a(this, 0));
    }

    @Nullable
    public final String getMessage() {
        return this.f5113d;
    }

    @NotNull
    public final Function0<Unit> getOnBasketButtonClicked() {
        return this.f5114e;
    }

    @NotNull
    public final Function0<Unit> getResume() {
        return this.f5115f;
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getSuccessMessageContainer().removeAllViews();
        getContainer().removeAllViews();
    }

    public final void setOnBasketButtonClicked(@NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.f5114e = function0;
    }

    public final void setResume(@NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.f5115f = function0;
    }

    public final void a() {
        BottomSheetBehavior<FrameLayout> from = BottomSheetBehavior.from(this.f5117h.f4323b);
        from.setPeekHeight((int) (((double) m.b().height()) * 0.9d));
        from.setHideable(true);
        from.setState(5);
        from.addBottomSheetCallback(new f(from, this));
        Unit unit = Unit.INSTANCE;
        this.f5116g = from;
    }

    public static final class a extends Lambda implements Function0<LinearLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5127a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ b f5128b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(Context context, b bVar) {
            super(0);
            this.f5127a = context;
            this.f5128b = bVar;
        }

        @NotNull
        /* renamed from: a */
        public final LinearLayout invoke() {
            LinearLayout linearLayout = new LinearLayout(this.f5127a);
            b bVar = this.f5128b;
            linearLayout.setOrientation(0);
            linearLayout.setGravity(17);
            linearLayout.setOnClickListener(new Z.a(bVar, 1));
            return linearLayout;
        }

        public static final void a(b bVar, View view) {
            Intrinsics.checkNotNullParameter(bVar, "this$0");
            bVar.f5118i = a.WithGoToCart;
            BottomSheetBehavior<FrameLayout> bottomSheetBehavior = bVar.f5116g;
            if (bottomSheetBehavior != null) {
                bottomSheetBehavior.setState(5);
            }
        }
    }

    public static void a(b bVar, a aVar, int i3) {
        bVar.f5118i = (i3 & 1) != 0 ? a.Default : null;
        BottomSheetBehavior<FrameLayout> bottomSheetBehavior = bVar.f5116g;
        if (bottomSheetBehavior != null) {
            bottomSheetBehavior.setState(5);
        }
    }

    public static final class d extends Lambda implements Function0<AppCompatButton> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5131a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ b f5132b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public d(Context context, b bVar) {
            super(0);
            this.f5131a = context;
            this.f5132b = bVar;
        }

        @NotNull
        /* renamed from: a */
        public final AppCompatButton invoke() {
            AppCompatButton appCompatButton = new AppCompatButton(this.f5131a);
            b bVar = this.f5132b;
            appCompatButton.setAllCaps(false);
            appCompatButton.setMaxLines(1);
            appCompatButton.setMinLines(1);
            appCompatButton.setEllipsize(TextUtils.TruncateAt.END);
            appCompatButton.setIncludeFontPadding(false);
            appCompatButton.setHorizontallyScrolling(false);
            appCompatButton.setGravity(17);
            appCompatButton.setTextColor(Color.parseColor("#424242"));
            appCompatButton.setTextAlignment(1);
            com.appsamurai.storyly.util.d.a(appCompatButton);
            appCompatButton.setStateListAnimator((StateListAnimator) null);
            appCompatButton.setOnClickListener(new Z.a(bVar, 2));
            return appCompatButton;
        }

        public static final void a(b bVar, View view) {
            Intrinsics.checkNotNullParameter(bVar, "this$0");
            b.a(bVar, (a) null, 1);
        }
    }
}
