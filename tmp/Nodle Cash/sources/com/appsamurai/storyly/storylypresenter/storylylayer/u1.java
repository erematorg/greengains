package com.appsamurai.storyly.storylypresenter.storylylayer;

import G0.t;
import G0.u;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewParent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.view.GravityCompat;
import com.appsamurai.storyly.R;
import com.appsamurai.storyly.StoryComponent;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.data.b0;
import com.appsamurai.storyly.data.i0;
import com.appsamurai.storyly.util.m;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.json.JsonObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressLint({"ViewConstructor"})
public final class u1 extends o1 {

    /* renamed from: A  reason: collision with root package name */
    public Function0<Unit> f6147A;

    /* renamed from: B  reason: collision with root package name */
    public Function0<Unit> f6148B;

    /* renamed from: C  reason: collision with root package name */
    public Function1<? super b0, Unit> f6149C;
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public final StorylyConfig f6150g;
    @NotNull

    /* renamed from: h  reason: collision with root package name */
    public final Lazy f6151h;
    @NotNull

    /* renamed from: i  reason: collision with root package name */
    public final Lazy f6152i;
    @NotNull

    /* renamed from: j  reason: collision with root package name */
    public final Lazy f6153j;
    @NotNull

    /* renamed from: k  reason: collision with root package name */
    public final Lazy f6154k;
    @NotNull

    /* renamed from: l  reason: collision with root package name */
    public final Lazy f6155l;
    @NotNull

    /* renamed from: m  reason: collision with root package name */
    public final Lazy f6156m;
    @NotNull

    /* renamed from: n  reason: collision with root package name */
    public final Lazy f6157n;
    @NotNull

    /* renamed from: o  reason: collision with root package name */
    public final Lazy f6158o;
    @NotNull

    /* renamed from: p  reason: collision with root package name */
    public final Lazy f6159p;
    @NotNull

    /* renamed from: q  reason: collision with root package name */
    public final Lazy f6160q;
    @NotNull

    /* renamed from: r  reason: collision with root package name */
    public final Lazy f6161r;

    /* renamed from: s  reason: collision with root package name */
    public i0 f6162s;

    /* renamed from: t  reason: collision with root package name */
    public final double f6163t = 13.0d;

    /* renamed from: u  reason: collision with root package name */
    public final double f6164u = 0.6d;
    @Nullable

    /* renamed from: v  reason: collision with root package name */
    public AnimatorSet f6165v;
    @Nullable

    /* renamed from: w  reason: collision with root package name */
    public AnimatorSet f6166w;

    /* renamed from: x  reason: collision with root package name */
    public boolean f6167x = true;

    /* renamed from: y  reason: collision with root package name */
    public Function5<? super com.appsamurai.storyly.analytics.a, ? super b0, ? super StoryComponent, ? super JsonObject, ? super Function1<? super Boolean, Unit>, Unit> f6168y;

    /* renamed from: z  reason: collision with root package name */
    public Function0<Unit> f6169z;

    public static final class a extends Lambda implements Function0<Button> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f6170a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(Context context) {
            super(0);
            this.f6170a = context;
        }

        public Object invoke() {
            Button button = new Button(this.f6170a);
            button.setId(View.generateViewId());
            button.setBackgroundColor(0);
            return button;
        }
    }

    public static final class b extends Lambda implements Function0<ImageView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f6171a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public b(Context context) {
            super(0);
            this.f6171a = context;
        }

        public Object invoke() {
            ImageView imageView = new ImageView(this.f6171a);
            imageView.setId(View.generateViewId());
            imageView.setBackgroundColor(0);
            imageView.setEnabled(false);
            imageView.setPadding(0, 0, 0, 0);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            return imageView;
        }
    }

    public static final class c extends Lambda implements Function0<ImageView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f6172a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public c(Context context) {
            super(0);
            this.f6172a = context;
        }

        public Object invoke() {
            ImageView imageView = new ImageView(this.f6172a);
            imageView.setId(View.generateViewId());
            return imageView;
        }
    }

    public static final class d extends Lambda implements Function0<TextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f6173a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public d(Context context) {
            super(0);
            this.f6173a = context;
        }

        public Object invoke() {
            TextView textView = new TextView(this.f6173a);
            textView.setId(View.generateViewId());
            textView.setMaxLines(1);
            textView.setMinLines(1);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setMaxWidth((int) m.a(60));
            textView.setIncludeFontPadding(false);
            textView.setTextAlignment(1);
            textView.setLineSpacing(0.0f, 0.0f);
            textView.setPaintFlags(textView.getPaintFlags() | 16);
            textView.setHorizontallyScrolling(false);
            return textView;
        }
    }

    public static final class e extends Lambda implements Function0<RelativeLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f6174a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public e(Context context) {
            super(0);
            this.f6174a = context;
        }

        public Object invoke() {
            RelativeLayout relativeLayout = new RelativeLayout(this.f6174a);
            relativeLayout.setId(View.generateViewId());
            return relativeLayout;
        }
    }

    public static final class f extends Lambda implements Function0<Button> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f6175a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public f(Context context) {
            super(0);
            this.f6175a = context;
        }

        public Object invoke() {
            Button button = new Button(this.f6175a);
            button.setId(View.generateViewId());
            button.setBackgroundColor(0);
            return button;
        }
    }

    public static final class g extends Lambda implements Function0<TextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f6176a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public g(Context context) {
            super(0);
            this.f6176a = context;
        }

        public Object invoke() {
            TextView textView = new TextView(this.f6176a);
            textView.setId(View.generateViewId());
            textView.setMaxLines(1);
            textView.setMinLines(1);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setMaxWidth((int) m.a(75));
            textView.setTextAlignment(1);
            textView.setIncludeFontPadding(false);
            textView.setLineSpacing(0.0f, 0.0f);
            textView.setHorizontallyScrolling(false);
            return textView;
        }
    }

    public static final class h extends Lambda implements Function0<RelativeLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f6177a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public h(Context context) {
            super(0);
            this.f6177a = context;
        }

        public Object invoke() {
            RelativeLayout relativeLayout = new RelativeLayout(this.f6177a);
            relativeLayout.setClipChildren(false);
            relativeLayout.setClipToPadding(false);
            relativeLayout.setBackgroundColor(0);
            return relativeLayout;
        }
    }

    public static final class i implements Animator.AnimatorListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ u1 f6178a;

        public i(u1 u1Var) {
            this.f6178a = u1Var;
        }

        public void onAnimationCancel(@NotNull Animator animator) {
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.f6178a.getPoint(), "scaleY", new float[]{this.f6178a.getPoint().getScaleY(), 1.0f}), ObjectAnimator.ofFloat(this.f6178a.getPoint(), "scaleX", new float[]{this.f6178a.getPoint().getScaleX(), 1.0f})});
            animatorSet.setInterpolator(new DecelerateInterpolator());
            animatorSet.setDuration((long) (((double) ((this.f6178a.getPoint().getScaleX() - ((float) 1)) * ((float) 1000))) / 0.5d));
            animatorSet.start();
        }

        public void onAnimationEnd(@NotNull Animator animator) {
        }

        public void onAnimationRepeat(@NotNull Animator animator) {
        }

        public void onAnimationStart(@NotNull Animator animator) {
        }
    }

    public static final class j extends Lambda implements Function0<TextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f6179a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public j(Context context) {
            super(0);
            this.f6179a = context;
        }

        public Object invoke() {
            TextView textView = new TextView(this.f6179a);
            textView.setId(View.generateViewId());
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setMaxLines(2);
            textView.setMinLines(1);
            textView.setMinHeight(20);
            textView.setMinWidth((int) m.a(30));
            textView.setMaxWidth((int) m.a(145));
            textView.setHorizontallyScrolling(false);
            textView.setGravity(GravityCompat.START);
            textView.setTextAlignment(1);
            com.appsamurai.storyly.util.d.a(textView);
            return textView;
        }
    }

    public static final class k extends Lambda implements Function0<FrameLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f6180a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public k(Context context) {
            super(0);
            this.f6180a = context;
        }

        public Object invoke() {
            FrameLayout frameLayout = new FrameLayout(this.f6180a);
            frameLayout.setId(View.generateViewId());
            frameLayout.setAlpha(0.0f);
            frameLayout.setVisibility(4);
            com.appsamurai.storyly.util.ui.l.b(frameLayout);
            return frameLayout;
        }
    }

    public static final class l extends Lambda implements Function0<RelativeLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f6181a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public l(Context context) {
            super(0);
            this.f6181a = context;
        }

        public Object invoke() {
            RelativeLayout relativeLayout = new RelativeLayout(this.f6181a);
            relativeLayout.setId(View.generateViewId());
            relativeLayout.setBackgroundColor(0);
            return relativeLayout;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public u1(@NotNull Context context, @NotNull StorylyConfig storylyConfig) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        this.f6150g = storylyConfig;
        this.f6151h = LazyKt.lazy(new h(context));
        this.f6152i = LazyKt.lazy(new e(context));
        this.f6153j = LazyKt.lazy(new c(context));
        this.f6154k = LazyKt.lazy(new f(context));
        this.f6155l = LazyKt.lazy(new k(context));
        this.f6156m = LazyKt.lazy(new l(context));
        this.f6157n = LazyKt.lazy(new b(context));
        this.f6158o = LazyKt.lazy(new j(context));
        this.f6159p = LazyKt.lazy(new g(context));
        this.f6160q = LazyKt.lazy(new d(context));
        this.f6161r = LazyKt.lazy(new a(context));
        com.appsamurai.storyly.util.ui.l.b(this);
    }

    public static final void e(u1 u1Var) {
        Intrinsics.checkNotNullParameter(u1Var, "this$0");
        u1Var.getPointButton().setEnabled(false);
    }

    public static final void f(u1 u1Var) {
        Intrinsics.checkNotNullParameter(u1Var, "this$0");
        u1Var.getPointButton().setEnabled(false);
    }

    /* access modifiers changed from: private */
    public final Button getActionButton() {
        return (Button) this.f6161r.getValue();
    }

    private final ImageView getChevronImageView() {
        return (ImageView) this.f6157n.getValue();
    }

    private final ImageView getIconView() {
        return (ImageView) this.f6153j.getValue();
    }

    private final TextView getOldPriceTextView() {
        return (TextView) this.f6160q.getValue();
    }

    /* access modifiers changed from: private */
    public final RelativeLayout getPoint() {
        return (RelativeLayout) this.f6152i.getValue();
    }

    /* access modifiers changed from: private */
    public final Button getPointButton() {
        return (Button) this.f6154k.getValue();
    }

    private final TextView getPriceTextView() {
        return (TextView) this.f6159p.getValue();
    }

    private final RelativeLayout getProductTagView() {
        return (RelativeLayout) this.f6151h.getValue();
    }

    private final TextView getTitleTextView() {
        return (TextView) this.f6158o.getValue();
    }

    /* access modifiers changed from: private */
    public final FrameLayout getToolTip() {
        return (FrameLayout) this.f6155l.getValue();
    }

    private final RelativeLayout getToolTipContainer() {
        return (RelativeLayout) this.f6156m.getValue();
    }

    public final void g() {
        if (!this.f6167x) {
            AnimatorSet animatorSet = this.f6166w;
            if (animatorSet != null) {
                animatorSet.cancel();
            }
            d(0);
        } else if (getToolTip().getVisibility() == 0) {
            c(400);
        } else {
            getOnUserReaction$storyly_release().invoke(com.appsamurai.storyly.analytics.a.ProductTagExpanded, getStorylyLayerItem$storyly_release(), null, null, null);
            d(400);
        }
    }

    @NotNull
    public final Function1<b0, Unit> getOnUserActionClick$storyly_release() {
        Function1<? super b0, Unit> function1 = this.f6149C;
        if (function1 != null) {
            return function1;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onUserActionClick");
        return null;
    }

    @NotNull
    public final Function0<Unit> getOnUserInteractionEnded$storyly_release() {
        Function0<Unit> function0 = this.f6147A;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onUserInteractionEnded");
        return null;
    }

    @NotNull
    public final Function0<Unit> getOnUserInteractionStarted$storyly_release() {
        Function0<Unit> function0 = this.f6169z;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onUserInteractionStarted");
        return null;
    }

    @NotNull
    public final Function5<com.appsamurai.storyly.analytics.a, b0, StoryComponent, JsonObject, Function1<? super Boolean, Unit>, Unit> getOnUserReaction$storyly_release() {
        Function5<? super com.appsamurai.storyly.analytics.a, ? super b0, ? super StoryComponent, ? super JsonObject, ? super Function1<? super Boolean, Unit>, Unit> function5 = this.f6168y;
        if (function5 != null) {
            return function5;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onUserReaction");
        return null;
    }

    @NotNull
    public final Function0<Unit> getOnUserTapPoint$storyly_release() {
        Function0<Unit> function0 = this.f6148B;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onUserTapPoint");
        return null;
    }

    public final void setOnUserActionClick$storyly_release(@NotNull Function1<? super b0, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.f6149C = function1;
    }

    public final void setOnUserInteractionEnded$storyly_release(@NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.f6147A = function0;
    }

    public final void setOnUserInteractionStarted$storyly_release(@NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.f6169z = function0;
    }

    public final void setOnUserReaction$storyly_release(@NotNull Function5<? super com.appsamurai.storyly.analytics.a, ? super b0, ? super StoryComponent, ? super JsonObject, ? super Function1<? super Boolean, Unit>, Unit> function5) {
        Intrinsics.checkNotNullParameter(function5, "<set-?>");
        this.f6168y = function5;
    }

    public final void setOnUserTapPoint$storyly_release(@NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.f6148B = function0;
    }

    public static final void b(u1 u1Var, View view) {
        Intrinsics.checkNotNullParameter(u1Var, "this$0");
        u1Var.getOnUserInteractionStarted$storyly_release().invoke();
        u1Var.getOnUserTapPoint$storyly_release().invoke();
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x05d9  */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x05e2  */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x065a  */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x0667  */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x0674  */
    /* JADX WARNING: Removed duplicated region for block: B:167:0x068a  */
    /* JADX WARNING: Removed duplicated region for block: B:172:0x069a  */
    /* JADX WARNING: Removed duplicated region for block: B:177:0x06a5  */
    /* JADX WARNING: Removed duplicated region for block: B:180:0x06c9  */
    /* JADX WARNING: Removed duplicated region for block: B:183:0x06d2  */
    /* JADX WARNING: Removed duplicated region for block: B:186:0x06f3  */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x06f6  */
    /* JADX WARNING: Removed duplicated region for block: B:189:0x06fb  */
    /* JADX WARNING: Removed duplicated region for block: B:192:0x072d  */
    /* JADX WARNING: Removed duplicated region for block: B:195:0x073a  */
    /* JADX WARNING: Removed duplicated region for block: B:196:0x073c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(@org.jetbrains.annotations.NotNull com.appsamurai.storyly.storylypresenter.storylylayer.d r31) {
        /*
            r30 = this;
            r6 = r30
            r8 = 0
            java.lang.String r0 = "safeFrame"
            r1 = r31
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
            r30.c()
            float r0 = r31.b()
            r31.a()
            android.widget.RelativeLayout r2 = r30.getProductTagView()
            android.widget.FrameLayout$LayoutParams r3 = new android.widget.FrameLayout$LayoutParams
            r9 = -1
            r3.<init>(r9, r9)
            r6.addView(r2, r3)
            double r2 = r6.f6163t
            r10 = 2
            double r4 = (double) r10
            double r4 = r2 / r4
            double r11 = (double) r0
            double r2 = r2 * r11
            r0 = 100
            double r13 = (double) r0
            double r2 = r2 / r13
            int r0 = kotlin.math.MathKt.roundToInt((double) r2)
            double r11 = r11 * r4
            double r11 = r11 / r13
            int r11 = kotlin.math.MathKt.roundToInt((double) r11)
            android.widget.FrameLayout$LayoutParams r2 = new android.widget.FrameLayout$LayoutParams
            r2.<init>(r0, r0)
            com.appsamurai.storyly.data.b0 r3 = r30.getStorylyLayerItem$storyly_release()
            android.graphics.Point r3 = r3.b()
            int r3 = r3.x
            com.appsamurai.storyly.data.b0 r4 = r30.getStorylyLayerItem$storyly_release()
            android.graphics.Point r4 = r4.b()
            int r4 = r4.y
            int r0 = -r0
            int r0 = r0 / r10
            float r0 = (float) r0
            float r5 = r31.c()
            float r5 = r5 + r0
            float r1 = r31.d()
            float r12 = r1 + r0
            r0 = r30
            r1 = r2
            r2 = r3
            r3 = r4
            r4 = r5
            r5 = r12
            android.widget.FrameLayout$LayoutParams r12 = r0.a(r1, r2, r3, r4, r5)
            r6.setLayoutParams(r12)
            android.widget.RelativeLayout$LayoutParams r13 = new android.widget.RelativeLayout$LayoutParams
            r13.<init>(r11, r11)
            r14 = 13
            r13.addRule(r14)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            android.widget.RelativeLayout r15 = r30.getPoint()
            com.appsamurai.storyly.data.i0 r0 = r6.f6162s
            java.lang.String r16 = "storylyLayer"
            r17 = 0
            if (r0 != 0) goto L_0x008a
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r16)
            r0 = r17
        L_0x008a:
            com.appsamurai.storyly.data.c r1 = r0.f3783x
            if (r1 != 0) goto L_0x0092
            com.appsamurai.storyly.data.c r1 = r0.l()
        L_0x0092:
            int r1 = r1.f3624a
            int r5 = r11 / 2
            float r4 = (float) r5
            r0 = r30
            r2 = r4
            r3 = r4
            r18 = r4
            r19 = r5
            r5 = r18
            android.graphics.drawable.Drawable r0 = r0.a(r1, r2, r3, r4, r5)
            android.graphics.drawable.GradientDrawable r0 = (android.graphics.drawable.GradientDrawable) r0
            android.content.Context r1 = r30.getContext()
            android.content.res.Resources r1 = r1.getResources()
            int r2 = com.appsamurai.storyly.R.dimen.st_product_tag_point_border_width
            int r1 = r1.getDimensionPixelSize(r2)
            com.appsamurai.storyly.data.i0 r2 = r6.f6162s
            if (r2 != 0) goto L_0x00be
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r16)
            r2 = r17
        L_0x00be:
            com.appsamurai.storyly.data.c r3 = r2.f3784y
            if (r3 != 0) goto L_0x00c6
            com.appsamurai.storyly.data.c r3 = r2.k()
        L_0x00c6:
            int r2 = r3.f3624a
            r0.setStroke(r1, r2)
            r15.setBackground(r0)
            android.widget.RelativeLayout r0 = r30.getProductTagView()
            android.widget.RelativeLayout r1 = r30.getPoint()
            r0.addView(r1, r13)
            double r0 = (double) r11
            double r2 = r6.f6164u
            double r0 = r0 * r2
            int r0 = kotlin.math.MathKt.roundToInt((double) r0)
            android.widget.RelativeLayout$LayoutParams r1 = new android.widget.RelativeLayout$LayoutParams
            r1.<init>(r0, r0)
            r1.addRule(r14)
            android.widget.ImageView r0 = r30.getIconView()
            android.content.Context r2 = r0.getContext()
            com.appsamurai.storyly.data.i0 r3 = r6.f6162s
            if (r3 != 0) goto L_0x00fa
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r16)
            r3 = r17
        L_0x00fa:
            java.lang.String r3 = r3.f3781v
            if (r3 == 0) goto L_0x0142
            int r4 = r3.hashCode()
            switch(r4) {
                case -2125409372: goto L_0x0136;
                case -1648893033: goto L_0x012a;
                case -1577211727: goto L_0x011e;
                case -678927291: goto L_0x0112;
                case 99657: goto L_0x0106;
                default: goto L_0x0105;
            }
        L_0x0105:
            goto L_0x0142
        L_0x0106:
            java.lang.String r4 = "dot"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x010f
            goto L_0x0142
        L_0x010f:
            int r3 = com.appsamurai.storyly.R.drawable.st_product_tag_icon_dot
            goto L_0x0144
        L_0x0112:
            java.lang.String r4 = "percent"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x011b
            goto L_0x0142
        L_0x011b:
            int r3 = com.appsamurai.storyly.R.drawable.st_product_tag_icon_percent
            goto L_0x0144
        L_0x011e:
            java.lang.String r4 = "shopping_bag"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0127
            goto L_0x0142
        L_0x0127:
            int r3 = com.appsamurai.storyly.R.drawable.st_product_tag_icon_shopping_bag
            goto L_0x0144
        L_0x012a:
            java.lang.String r4 = "shopping_cart"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0133
            goto L_0x0142
        L_0x0133:
            int r3 = com.appsamurai.storyly.R.drawable.st_product_tag_icon_shopping_cart
            goto L_0x0144
        L_0x0136:
            java.lang.String r4 = "price_tag"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x013f
            goto L_0x0142
        L_0x013f:
            int r3 = com.appsamurai.storyly.R.drawable.st_product_tag_icon_price_tag
            goto L_0x0144
        L_0x0142:
            int r3 = com.appsamurai.storyly.R.drawable.st_product_tag_icon_dot
        L_0x0144:
            android.graphics.drawable.Drawable r2 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r2, r3)
            r0.setImageDrawable(r2)
            android.graphics.PorterDuffColorFilter r2 = new android.graphics.PorterDuffColorFilter
            com.appsamurai.storyly.data.i0 r3 = r6.f6162s
            if (r3 != 0) goto L_0x0156
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r16)
            r3 = r17
        L_0x0156:
            com.appsamurai.storyly.data.c r4 = r3.f3782w
            if (r4 != 0) goto L_0x015e
            com.appsamurai.storyly.data.c r4 = r3.k()
        L_0x015e:
            int r3 = r4.f3624a
            android.graphics.PorterDuff$Mode r4 = android.graphics.PorterDuff.Mode.SRC_ATOP
            r2.<init>(r3, r4)
            r0.setColorFilter(r2)
            android.widget.RelativeLayout r0 = r30.getPoint()
            android.widget.ImageView r2 = r30.getIconView()
            r0.addView(r2, r1)
            android.widget.RelativeLayout$LayoutParams r0 = new android.widget.RelativeLayout$LayoutParams
            r0.<init>(r9, r9)
            android.widget.Button r1 = r30.getPointButton()
            G0.v r2 = new G0.v
            r2.<init>(r6, r8)
            r1.setOnClickListener(r2)
            android.widget.RelativeLayout r1 = r30.getPoint()
            android.widget.Button r2 = r30.getPointButton()
            r1.addView(r2, r0)
            android.content.Context r0 = r30.getContext()
            android.content.res.Resources r0 = r0.getResources()
            int r1 = com.appsamurai.storyly.R.dimen.st_product_tag_title_text_size
            int r0 = r0.getDimensionPixelSize(r1)
            android.content.Context r2 = r30.getContext()
            android.content.res.Resources r2 = r2.getResources()
            int r3 = com.appsamurai.storyly.R.dimen.st_product_tag_chevron_margin_end
            int r13 = r2.getDimensionPixelSize(r3)
            android.content.Context r2 = r30.getContext()
            android.content.res.Resources r2 = r2.getResources()
            int r3 = com.appsamurai.storyly.R.dimen.st_product_tag_chevron_padding_end
            int r2 = r2.getDimensionPixelSize(r3)
            android.widget.RelativeLayout$LayoutParams r3 = new android.widget.RelativeLayout$LayoutParams
            r3.<init>(r0, r0)
            r0 = 10
            r3.addRule(r0)
            r14 = 21
            r3.addRule(r14)
            android.content.Context r4 = r30.getContext()
            android.content.res.Resources r4 = r4.getResources()
            int r5 = com.appsamurai.storyly.R.dimen.st_product_tag_chevron_margin_top
            int r4 = r4.getDimensionPixelSize(r5)
            r3.topMargin = r4
            r3.setMarginEnd(r13)
            android.widget.ImageView r4 = r30.getChevronImageView()
            com.appsamurai.storyly.data.i0 r5 = r6.f6162s
            if (r5 != 0) goto L_0x01e8
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r16)
            r5 = r17
        L_0x01e8:
            r5.getClass()
            int r5 = com.appsamurai.storyly.R.drawable.st_product_tag_chevron
            r4.setImageResource(r5)
            android.graphics.PorterDuffColorFilter r5 = new android.graphics.PorterDuffColorFilter
            com.appsamurai.storyly.data.i0 r15 = r6.f6162s
            if (r15 != 0) goto L_0x01fb
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r16)
            r15 = r17
        L_0x01fb:
            com.appsamurai.storyly.data.c r9 = r15.f3780u
            if (r9 != 0) goto L_0x0203
            com.appsamurai.storyly.data.c r9 = r15.k()
        L_0x0203:
            int r9 = r9.f3624a
            android.graphics.PorterDuff$Mode r15 = android.graphics.PorterDuff.Mode.MULTIPLY
            r5.<init>(r9, r15)
            r4.setColorFilter(r5)
            boolean r5 = r30.a()
            java.lang.String r9 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r9)
            if (r5 == 0) goto L_0x021a
            r15 = r8
            goto L_0x021b
        L_0x021a:
            r15 = r2
        L_0x021b:
            if (r5 == 0) goto L_0x021e
            goto L_0x021f
        L_0x021e:
            r2 = r8
        L_0x021f:
            r4.setPadding(r15, r8, r2, r8)
            android.widget.RelativeLayout r2 = r30.getToolTipContainer()
            android.widget.ImageView r4 = r30.getChevronImageView()
            r2.addView(r4, r3)
            android.widget.ImageView r2 = r30.getChevronImageView()
            r2.measure(r8, r8)
            android.content.Context r2 = r30.getContext()
            android.content.res.Resources r2 = r2.getResources()
            int r3 = com.appsamurai.storyly.R.dimen.st_product_tag_title_text_margin_start
            int r15 = r2.getDimensionPixelSize(r3)
            android.content.Context r2 = r30.getContext()
            android.content.res.Resources r2 = r2.getResources()
            int r3 = com.appsamurai.storyly.R.dimen.st_product_tag_title_text_margin_top
            int r2 = r2.getDimensionPixelSize(r3)
            android.content.Context r3 = r30.getContext()
            android.content.res.Resources r3 = r3.getResources()
            int r4 = com.appsamurai.storyly.R.dimen.st_product_tag_title_text_margin_bottom
            int r3 = r3.getDimensionPixelSize(r4)
            android.content.Context r4 = r30.getContext()
            android.content.res.Resources r4 = r4.getResources()
            int r5 = com.appsamurai.storyly.R.dimen.st_product_tag_title_text_margin_end
            int r4 = r4.getDimensionPixelSize(r5)
            android.widget.RelativeLayout$LayoutParams r5 = new android.widget.RelativeLayout$LayoutParams
            r10 = -2
            r5.<init>(r10, r10)
            r5.addRule(r0)
            r0 = 20
            r5.addRule(r0)
            r5.setMarginStart(r15)
            r5.topMargin = r2
            r5.bottomMargin = r3
            android.widget.ImageView r0 = r30.getChevronImageView()
            int r0 = r0.getMeasuredWidth()
            int r0 = r0 + r4
            int r0 = r0 + r13
            r5.setMarginEnd(r0)
            android.widget.TextView r0 = r30.getTitleTextView()
            com.appsamurai.storyly.data.i0 r2 = r6.f6162s
            if (r2 != 0) goto L_0x029b
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r16)
            r2 = r17
        L_0x029b:
            com.appsamurai.storyly.data.i0 r3 = r6.f6162s
            if (r3 != 0) goto L_0x02a4
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r16)
            r3 = r17
        L_0x02a4:
            com.appsamurai.storyly.data.managers.product.STRProductItem r3 = r3.b()
            com.appsamurai.storyly.data.i0 r4 = r6.f6162s
            if (r4 != 0) goto L_0x02b1
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r16)
            r4 = r17
        L_0x02b1:
            java.lang.String r4 = r4.f3760a
            java.lang.String r2 = r2.a(r3, r4)
            r0.setText(r2)
            android.content.Context r2 = r0.getContext()
            android.content.res.Resources r2 = r2.getResources()
            float r1 = r2.getDimension(r1)
            r0.setTextSize(r8, r1)
            com.appsamurai.storyly.config.StorylyConfig r1 = r6.f6150g
            com.appsamurai.storyly.config.styling.story.StorylyStoryStyling r1 = r1.getStory$storyly_release()
            android.graphics.Typeface r1 = r1.getInteractiveTypeface$storyly_release()
            r0.setTypeface(r1)
            com.appsamurai.storyly.data.i0 r1 = r6.f6162s
            if (r1 != 0) goto L_0x02df
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r16)
            r1 = r17
        L_0x02df:
            boolean r1 = r1.f3771l
            com.appsamurai.storyly.data.i0 r2 = r6.f6162s
            if (r2 != 0) goto L_0x02ea
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r16)
            r2 = r17
        L_0x02ea:
            boolean r2 = r2.f3772m
            com.appsamurai.storyly.util.c.a(r0, r1, r2)
            com.appsamurai.storyly.data.i0 r1 = r6.f6162s
            if (r1 != 0) goto L_0x02f8
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r16)
            r1 = r17
        L_0x02f8:
            com.appsamurai.storyly.data.c r2 = r1.f3768i
            if (r2 != 0) goto L_0x0300
            com.appsamurai.storyly.data.c r2 = r1.k()
        L_0x0300:
            int r1 = r2.f3624a
            r0.setTextColor(r1)
            android.widget.RelativeLayout r0 = r30.getToolTipContainer()
            android.widget.TextView r1 = r30.getTitleTextView()
            r0.addView(r1, r5)
            android.content.Context r0 = r30.getContext()
            android.content.res.Resources r0 = r0.getResources()
            int r1 = com.appsamurai.storyly.R.dimen.st_product_tag_price_text_padding_end
            int r20 = r0.getDimensionPixelSize(r1)
            android.content.Context r0 = r30.getContext()
            android.content.res.Resources r0 = r0.getResources()
            int r1 = com.appsamurai.storyly.R.dimen.st_product_tag_price_text_padding_start
            int r21 = r0.getDimensionPixelSize(r1)
            android.content.Context r0 = r30.getContext()
            android.content.res.Resources r0 = r0.getResources()
            int r1 = com.appsamurai.storyly.R.dimen.st_product_tag_price_text_padding_top
            int r5 = r0.getDimensionPixelSize(r1)
            android.content.Context r0 = r30.getContext()
            android.content.res.Resources r0 = r0.getResources()
            int r1 = com.appsamurai.storyly.R.dimen.st_product_tag_price_text_padding_bottom
            int r4 = r0.getDimensionPixelSize(r1)
            android.content.Context r0 = r30.getContext()
            android.content.res.Resources r0 = r0.getResources()
            int r3 = com.appsamurai.storyly.R.dimen.st_product_tag_price_text_margin_bottom
            int r0 = r0.getDimensionPixelSize(r3)
            com.appsamurai.storyly.data.i0 r1 = r6.f6162s
            if (r1 != 0) goto L_0x035f
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r16)
            r1 = r17
        L_0x035f:
            com.appsamurai.storyly.data.i0 r2 = r6.f6162s
            if (r2 != 0) goto L_0x0368
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r16)
            r2 = r17
        L_0x0368:
            com.appsamurai.storyly.data.managers.product.STRProductItem r2 = r2.b()
            com.appsamurai.storyly.config.StorylyConfig r7 = r6.f6150g
            com.appsamurai.storyly.config.StorylyProductConfig r7 = r7.getProduct$storyly_release()
            com.appsamurai.storyly.util.formatter.b r7 = r7.getPriceFormatter$storyly_release()
            com.appsamurai.storyly.data.i0 r8 = r6.f6162s
            if (r8 != 0) goto L_0x037f
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r16)
            r8 = r17
        L_0x037f:
            java.lang.String r8 = r8.f3770k
            java.lang.String r1 = r1.b(r2, r7, r8)
            com.appsamurai.storyly.data.i0 r2 = r6.f6162s
            if (r2 != 0) goto L_0x038e
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r16)
            r2 = r17
        L_0x038e:
            com.appsamurai.storyly.data.i0 r7 = r6.f6162s
            if (r7 != 0) goto L_0x0397
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r16)
            r7 = r17
        L_0x0397:
            com.appsamurai.storyly.data.managers.product.STRProductItem r7 = r7.b()
            com.appsamurai.storyly.config.StorylyConfig r8 = r6.f6150g
            com.appsamurai.storyly.config.StorylyProductConfig r8 = r8.getProduct$storyly_release()
            com.appsamurai.storyly.util.formatter.b r8 = r8.getPriceFormatter$storyly_release()
            com.appsamurai.storyly.data.i0 r14 = r6.f6162s
            if (r14 != 0) goto L_0x03ae
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r16)
            r14 = r17
        L_0x03ae:
            java.lang.String r14 = r14.f3778s
            java.lang.String r7 = r2.a(r7, r8, r14)
            int r2 = r1.length()
            r8 = 17
            r14 = 3
            if (r2 <= 0) goto L_0x0585
            android.widget.RelativeLayout$LayoutParams r2 = new android.widget.RelativeLayout$LayoutParams
            r2.<init>(r10, r10)
            r10 = 21
            r2.addRule(r10)
            android.widget.TextView r10 = r30.getTitleTextView()
            int r10 = r10.getId()
            r2.addRule(r14, r10)
            r2.setMarginEnd(r13)
            r2.bottomMargin = r0
            int r0 = r7.length()
            if (r0 != 0) goto L_0x03e0
            r2.setMarginStart(r15)
        L_0x03e0:
            android.widget.TextView r10 = r30.getPriceTextView()
            r10.setText(r1)
            android.content.Context r0 = r10.getContext()
            android.content.res.Resources r0 = r0.getResources()
            int r1 = com.appsamurai.storyly.R.dimen.st_product_tag_price_text_size
            float r0 = r0.getDimension(r1)
            r14 = 0
            r10.setTextSize(r14, r0)
            com.appsamurai.storyly.data.i0 r0 = r6.f6162s
            if (r0 != 0) goto L_0x0402
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r16)
            r0 = r17
        L_0x0402:
            com.appsamurai.storyly.data.c r14 = r0.f3769j
            if (r14 != 0) goto L_0x040a
            com.appsamurai.storyly.data.c r14 = r0.l()
        L_0x040a:
            int r0 = r14.f3624a
            r10.setTextColor(r0)
            com.appsamurai.storyly.config.StorylyConfig r0 = r6.f6150g
            com.appsamurai.storyly.config.styling.story.StorylyStoryStyling r0 = r0.getStory$storyly_release()
            android.graphics.Typeface r0 = r0.getInteractiveTypeface$storyly_release()
            r10.setTypeface(r0)
            com.appsamurai.storyly.data.i0 r0 = r6.f6162s
            if (r0 != 0) goto L_0x0425
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r16)
            r0 = r17
        L_0x0425:
            boolean r0 = r0.f3773n
            com.appsamurai.storyly.data.i0 r14 = r6.f6162s
            if (r14 != 0) goto L_0x0430
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r16)
            r14 = r17
        L_0x0430:
            boolean r14 = r14.f3774o
            com.appsamurai.storyly.util.c.a(r10, r0, r14)
            boolean r0 = r30.a()
            if (r0 == 0) goto L_0x046f
            com.appsamurai.storyly.data.i0 r0 = r6.f6162s
            if (r0 != 0) goto L_0x0444
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r16)
            r0 = r17
        L_0x0444:
            com.appsamurai.storyly.data.c r14 = r0.f3777r
            if (r14 != 0) goto L_0x044c
            com.appsamurai.storyly.data.c r14 = r0.k()
        L_0x044c:
            int r14 = r14.f3624a
            r22 = 1092616192(0x41200000, float:10.0)
            r23 = 1073741824(0x40000000, float:2.0)
            r24 = 1092616192(0x41200000, float:10.0)
            r25 = 1073741824(0x40000000, float:2.0)
            r0 = r30
            r26 = r1
            r1 = r14
            r14 = r2
            r2 = r22
            r27 = r3
            r3 = r23
            r28 = r4
            r4 = r24
            r29 = r5
            r5 = r25
            android.graphics.drawable.Drawable r0 = r0.a(r1, r2, r3, r4, r5)
            goto L_0x0499
        L_0x046f:
            r26 = r1
            r14 = r2
            r27 = r3
            r28 = r4
            r29 = r5
            com.appsamurai.storyly.data.i0 r0 = r6.f6162s
            if (r0 != 0) goto L_0x0481
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r16)
            r0 = r17
        L_0x0481:
            com.appsamurai.storyly.data.c r1 = r0.f3777r
            if (r1 != 0) goto L_0x0489
            com.appsamurai.storyly.data.c r1 = r0.k()
        L_0x0489:
            int r1 = r1.f3624a
            r2 = 1073741824(0x40000000, float:2.0)
            r3 = 1092616192(0x41200000, float:10.0)
            r4 = 1073741824(0x40000000, float:2.0)
            r5 = 1092616192(0x41200000, float:10.0)
            r0 = r30
            android.graphics.drawable.Drawable r0 = r0.a(r1, r2, r3, r4, r5)
        L_0x0499:
            r10.setBackground(r0)
            r10.setGravity(r8)
            boolean r0 = r30.a()
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r9)
            if (r0 == 0) goto L_0x04ab
            r1 = r21
            goto L_0x04ad
        L_0x04ab:
            r1 = r20
        L_0x04ad:
            if (r0 == 0) goto L_0x04b6
            r0 = r20
        L_0x04b1:
            r3 = r28
            r2 = r29
            goto L_0x04b9
        L_0x04b6:
            r0 = r21
            goto L_0x04b1
        L_0x04b9:
            r10.setPadding(r1, r2, r0, r3)
            android.widget.RelativeLayout r0 = r30.getToolTipContainer()
            android.widget.TextView r1 = r30.getPriceTextView()
            r0.addView(r1, r14)
            int r0 = r7.length()
            if (r0 <= 0) goto L_0x0585
            android.widget.TextView r0 = r30.getPriceTextView()
            r1 = 0
            r0.measure(r1, r1)
            android.widget.RelativeLayout$LayoutParams r0 = new android.widget.RelativeLayout$LayoutParams
            r1 = -2
            r0.<init>(r1, r1)
            r1 = 21
            r0.addRule(r1)
            android.widget.TextView r1 = r30.getTitleTextView()
            int r1 = r1.getId()
            r4 = 3
            r0.addRule(r4, r1)
            r0.setMarginStart(r15)
            android.content.Context r1 = r30.getContext()
            android.content.res.Resources r1 = r1.getResources()
            int r4 = com.appsamurai.storyly.R.dimen.st_product_tag_old_price_text_margin_end
            int r1 = r1.getDimensionPixelSize(r4)
            android.widget.TextView r4 = r30.getPriceTextView()
            int r4 = r4.getMeasuredWidth()
            int r4 = r4 + r1
            int r4 = r4 + r13
            r0.setMarginEnd(r4)
            android.content.Context r1 = r30.getContext()
            android.content.res.Resources r1 = r1.getResources()
            r4 = r27
            int r1 = r1.getDimensionPixelSize(r4)
            r0.bottomMargin = r1
            android.widget.TextView r1 = r30.getOldPriceTextView()
            r1.setText(r7)
            android.content.Context r4 = r1.getContext()
            android.content.res.Resources r4 = r4.getResources()
            r5 = r26
            float r4 = r4.getDimension(r5)
            r5 = 0
            r1.setTextSize(r5, r4)
            com.appsamurai.storyly.data.i0 r4 = r6.f6162s
            if (r4 != 0) goto L_0x053c
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r16)
            r4 = r17
        L_0x053c:
            com.appsamurai.storyly.data.c r5 = r4.f3779t
            if (r5 != 0) goto L_0x0544
            com.appsamurai.storyly.data.c r5 = r4.k()
        L_0x0544:
            int r4 = r5.f3624a
            r1.setTextColor(r4)
            com.appsamurai.storyly.config.StorylyConfig r4 = r6.f6150g
            com.appsamurai.storyly.config.styling.story.StorylyStoryStyling r4 = r4.getStory$storyly_release()
            android.graphics.Typeface r4 = r4.getInteractiveTypeface$storyly_release()
            r1.setTypeface(r4)
            com.appsamurai.storyly.data.i0 r4 = r6.f6162s
            if (r4 != 0) goto L_0x055f
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r16)
            r4 = r17
        L_0x055f:
            boolean r4 = r4.f3775p
            com.appsamurai.storyly.data.i0 r5 = r6.f6162s
            if (r5 != 0) goto L_0x056a
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r16)
            r5 = r17
        L_0x056a:
            boolean r5 = r5.f3776q
            com.appsamurai.storyly.util.c.a(r1, r4, r5)
            r4 = 0
            r1.setPadding(r4, r2, r4, r3)
            r2 = 8388613(0x800005, float:1.175495E-38)
            r1.setGravity(r2)
            android.widget.RelativeLayout r1 = r30.getToolTipContainer()
            android.widget.TextView r2 = r30.getOldPriceTextView()
            r1.addView(r2, r0)
            goto L_0x0586
        L_0x0585:
            r4 = 0
        L_0x0586:
            android.widget.RelativeLayout r0 = r30.getToolTipContainer()
            r0.measure(r4, r4)
            android.widget.RelativeLayout$LayoutParams r0 = new android.widget.RelativeLayout$LayoutParams
            android.widget.RelativeLayout r1 = r30.getToolTipContainer()
            int r1 = r1.getMeasuredWidth()
            android.widget.RelativeLayout r2 = r30.getToolTipContainer()
            int r2 = r2.getMeasuredHeight()
            r0.<init>(r1, r2)
            android.widget.Button r1 = r30.getActionButton()
            G0.v r2 = new G0.v
            r3 = 1
            r2.<init>(r6, r3)
            r1.setOnClickListener(r2)
            android.widget.RelativeLayout r1 = r30.getToolTipContainer()
            android.widget.Button r2 = r30.getActionButton()
            r1.addView(r2, r0)
            android.widget.FrameLayout$LayoutParams r7 = new android.widget.FrameLayout$LayoutParams
            android.widget.RelativeLayout r0 = r30.getToolTipContainer()
            int r0 = r0.getMeasuredWidth()
            android.widget.RelativeLayout r1 = r30.getToolTipContainer()
            int r1 = r1.getMeasuredHeight()
            r7.<init>(r0, r1)
            r7.gravity = r8
            android.widget.RelativeLayout r8 = r30.getToolTipContainer()
            com.appsamurai.storyly.data.i0 r0 = r6.f6162s
            if (r0 != 0) goto L_0x05de
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r16)
            r0 = r17
        L_0x05de:
            com.appsamurai.storyly.data.c r1 = r0.f3766g
            if (r1 != 0) goto L_0x05e6
            com.appsamurai.storyly.data.c r1 = r0.l()
        L_0x05e6:
            int r1 = r1.f3624a
            r2 = 1094713344(0x41400000, float:12.0)
            r3 = 1094713344(0x41400000, float:12.0)
            r4 = 1094713344(0x41400000, float:12.0)
            r5 = 1094713344(0x41400000, float:12.0)
            r0 = r30
            android.graphics.drawable.Drawable r0 = r0.a(r1, r2, r3, r4, r5)
            android.graphics.drawable.GradientDrawable r0 = (android.graphics.drawable.GradientDrawable) r0
            r8.setBackground(r0)
            android.widget.FrameLayout r0 = r30.getToolTip()
            android.widget.RelativeLayout r1 = r30.getToolTipContainer()
            r0.addView(r1, r7)
            android.content.Context r0 = r30.getContext()
            android.content.res.Resources r0 = r0.getResources()
            int r1 = com.appsamurai.storyly.R.dimen.st_product_tag_point_tooltip_gap
            int r0 = r0.getDimensionPixelSize(r1)
            android.content.Context r1 = r30.getContext()
            android.content.res.Resources r1 = r1.getResources()
            int r2 = com.appsamurai.storyly.R.dimen.st_product_tag_background_border_width
            float r1 = r1.getDimension(r2)
            android.content.Context r3 = r30.getContext()
            android.content.res.Resources r3 = r3.getResources()
            int r7 = r3.getDimensionPixelSize(r2)
            android.widget.RelativeLayout r2 = r30.getToolTipContainer()
            int r2 = r2.getMeasuredWidth()
            r3 = 2
            int r2 = r2 / r3
            int r4 = r12.leftMargin
            int r5 = r12.width
            int r5 = r5 / r3
            int r5 = r5 + r4
            int r5 = r5 - r2
            android.widget.FrameLayout$LayoutParams r8 = new android.widget.FrameLayout$LayoutParams
            android.widget.RelativeLayout r3 = r30.getToolTipContainer()
            int r3 = r3.getMeasuredWidth()
            int r3 = r3 + r7
            android.widget.RelativeLayout r4 = r30.getToolTipContainer()
            int r4 = r4.getMeasuredHeight()
            int r4 = r4 + r7
            r8.<init>(r3, r4)
            com.appsamurai.storyly.data.i0 r3 = r6.f6162s
            if (r3 != 0) goto L_0x065f
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r16)
            r3 = r17
        L_0x065f:
            com.appsamurai.storyly.data.r0 r3 = r3.f3763d
            boolean r3 = r3.a()
            if (r3 == 0) goto L_0x0674
            int r3 = r12.topMargin
            float r4 = (float) r11
            r9 = 1069547520(0x3fc00000, float:1.5)
            float r4 = r4 * r9
            int r4 = kotlin.math.MathKt.roundToInt((float) r4)
            int r4 = r4 + r3
            int r4 = r4 + r0
            goto L_0x0684
        L_0x0674:
            int r3 = r12.topMargin
            int r3 = r3 + r19
            android.widget.RelativeLayout r4 = r30.getToolTipContainer()
            int r4 = r4.getMeasuredHeight()
            int r3 = r3 - r4
            int r3 = r3 - r7
            int r4 = r3 - r0
        L_0x0684:
            r8.topMargin = r4
            com.appsamurai.storyly.data.i0 r0 = r6.f6162s
            if (r0 != 0) goto L_0x068f
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r16)
            r0 = r17
        L_0x068f:
            com.appsamurai.storyly.data.r0 r0 = r0.f3763d
            int r0 = r0.ordinal()
            if (r0 == 0) goto L_0x06a5
            r3 = 5
            if (r0 == r3) goto L_0x06a5
            r3 = 2
            if (r0 == r3) goto L_0x06a1
            r3 = 3
            if (r0 == r3) goto L_0x06a1
            goto L_0x06a9
        L_0x06a1:
            int r5 = r5 - r19
            int r5 = r5 + r2
            goto L_0x06a9
        L_0x06a5:
            int r5 = r5 + r19
            int r5 = r5 - r2
            int r5 = r5 - r7
        L_0x06a9:
            r8.leftMargin = r5
            r0 = 0
            r8.gravity = r0
            android.widget.FrameLayout r9 = r30.getToolTip()
            r0 = 1048576000(0x3e800000, float:0.25)
            float r1 = r1 * r0
            r0 = 1094713344(0x41400000, float:12.0)
            float r5 = r1 + r0
            r1 = 0
            r0 = r30
            r2 = r5
            r3 = r5
            r4 = r5
            android.graphics.drawable.Drawable r0 = r0.a(r1, r2, r3, r4, r5)
            android.graphics.drawable.GradientDrawable r0 = (android.graphics.drawable.GradientDrawable) r0
            com.appsamurai.storyly.data.i0 r1 = r6.f6162s
            if (r1 != 0) goto L_0x06ce
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r16)
            r1 = r17
        L_0x06ce:
            com.appsamurai.storyly.data.c r2 = r1.f3767h
            if (r2 != 0) goto L_0x06e3
            com.appsamurai.storyly.data.c r2 = new com.appsamurai.storyly.data.c
            com.appsamurai.storyly.data.c r1 = r1.k()
            int r1 = r1.f3624a
            r3 = 76
            int r1 = com.appsamurai.storyly.util.f.a((int) r1, (int) r3)
            r2.<init>(r1)
        L_0x06e3:
            int r1 = r2.f3624a
            r0.setStroke(r7, r1)
            r9.setBackground(r0)
            android.view.ViewParent r0 = r30.getParent()
            boolean r1 = r0 instanceof android.widget.FrameLayout
            if (r1 == 0) goto L_0x06f6
            android.widget.FrameLayout r0 = (android.widget.FrameLayout) r0
            goto L_0x06f8
        L_0x06f6:
            r0 = r17
        L_0x06f8:
            if (r0 != 0) goto L_0x06fb
            goto L_0x0706
        L_0x06fb:
            int r1 = r0.indexOfChild(r6)
            android.widget.FrameLayout r2 = r30.getToolTip()
            r0.addView(r2, r1, r8)
        L_0x0706:
            android.widget.RelativeLayout r0 = r30.getPoint()
            r1 = 0
            r0.measure(r1, r1)
            android.widget.FrameLayout r0 = r30.getToolTip()
            r0.setVisibility(r1)
            android.widget.FrameLayout r0 = r30.getToolTip()
            r1 = 0
            r0.setAlpha(r1)
            android.widget.RelativeLayout r0 = r30.getPoint()
            int r0 = r0.getMeasuredHeight()
            float r0 = (float) r0
            r2 = 1056964608(0x3f000000, float:0.5)
            float r0 = r0 * r2
            com.appsamurai.storyly.data.i0 r2 = r6.f6162s
            if (r2 != 0) goto L_0x0732
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r16)
            r2 = r17
        L_0x0732:
            com.appsamurai.storyly.data.r0 r2 = r2.f3763d
            boolean r2 = r2.a()
            if (r2 == 0) goto L_0x073c
            r9 = 1
            goto L_0x073d
        L_0x073c:
            r9 = -1
        L_0x073d:
            float r2 = (float) r9
            float r0 = r0 * r2
            android.animation.AnimatorSet r2 = new android.animation.AnimatorSet
            r2.<init>()
            android.animation.AnimatorSet r3 = new android.animation.AnimatorSet
            r3.<init>()
            android.widget.FrameLayout r4 = r30.getToolTip()
            float r5 = -r0
            r7 = 2
            float[] r8 = new float[r7]
            r9 = 0
            r8[r9] = r5
            r10 = 1
            r8[r10] = r1
            java.lang.String r1 = "translationY"
            android.animation.ObjectAnimator r4 = android.animation.ObjectAnimator.ofFloat(r4, r1, r8)
            android.widget.FrameLayout r8 = r30.getToolTip()
            float[] r11 = new float[r7]
            r11 = {0, 1065353216} // fill-array
            java.lang.String r12 = "alpha"
            android.animation.ObjectAnimator r8 = android.animation.ObjectAnimator.ofFloat(r8, r12, r11)
            android.animation.Animator[] r11 = new android.animation.Animator[r7]
            r11[r9] = r4
            r11[r10] = r8
            r3.playTogether(r11)
            android.view.animation.DecelerateInterpolator r4 = new android.view.animation.DecelerateInterpolator
            r4.<init>()
            r3.setInterpolator(r4)
            r7 = 300(0x12c, double:1.48E-321)
            r2.setStartDelay(r7)
            r7 = 400(0x190, double:1.976E-321)
            r3.setDuration(r7)
            com.appsamurai.storyly.storylypresenter.storylylayer.y1 r4 = new com.appsamurai.storyly.storylypresenter.storylylayer.y1
            r4.<init>(r6)
            r3.addListener(r4)
            android.animation.AnimatorSet r4 = new android.animation.AnimatorSet
            r4.<init>()
            android.widget.FrameLayout r9 = r30.getToolTip()
            r10 = 1
            float[] r11 = new float[r10]
            r13 = 0
            r11[r13] = r5
            android.animation.ObjectAnimator r1 = android.animation.ObjectAnimator.ofFloat(r9, r1, r11)
            android.widget.FrameLayout r5 = r30.getToolTip()
            r9 = 2
            float[] r11 = new float[r9]
            r11 = {1065353216, 0} // fill-array
            android.animation.ObjectAnimator r5 = android.animation.ObjectAnimator.ofFloat(r5, r12, r11)
            android.animation.Animator[] r9 = new android.animation.Animator[r9]
            r9[r13] = r1
            r9[r10] = r5
            r4.playTogether(r9)
            android.view.animation.DecelerateInterpolator r1 = new android.view.animation.DecelerateInterpolator
            r1.<init>()
            r4.setInterpolator(r1)
            r4.setDuration(r7)
            com.appsamurai.storyly.storylypresenter.storylylayer.z1 r1 = new com.appsamurai.storyly.storylypresenter.storylylayer.z1
            r1.<init>(r6)
            r4.addListener(r1)
            com.appsamurai.storyly.storylypresenter.storylylayer.x1 r1 = new com.appsamurai.storyly.storylypresenter.storylylayer.x1
            r1.<init>(r6)
            r2.addListener(r1)
            com.appsamurai.storyly.storylypresenter.storylylayer.v1 r1 = new com.appsamurai.storyly.storylypresenter.storylylayer.v1
            r1.<init>(r6)
            r2.addListener(r1)
            com.appsamurai.storyly.storylypresenter.storylylayer.w1 r1 = new com.appsamurai.storyly.storylypresenter.storylylayer.w1
            r1.<init>(r6, r0)
            r2.addListener(r1)
            android.animation.AnimatorSet$Builder r0 = r2.play(r3)
            r0.before(r4)
            android.animation.AnimatorSet$Builder r0 = r2.play(r4)
            r3 = 2000(0x7d0, double:9.88E-321)
            r0.after(r3)
            r2.start()
            r6.f6166w = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.storylypresenter.storylylayer.u1.a(com.appsamurai.storyly.storylypresenter.storylylayer.d):void");
    }

    public void c() {
        ViewParent parent = getParent();
        FrameLayout frameLayout = parent instanceof FrameLayout ? (FrameLayout) parent : null;
        if (frameLayout != null) {
            frameLayout.removeView(getToolTip());
        }
        AnimatorSet animatorSet = this.f6165v;
        if (animatorSet != null) {
            animatorSet.removeAllListeners();
        }
        AnimatorSet animatorSet2 = this.f6165v;
        if (animatorSet2 != null) {
            animatorSet2.cancel();
        }
        AnimatorSet animatorSet3 = this.f6166w;
        if (animatorSet3 != null) {
            animatorSet3.removeAllListeners();
        }
        AnimatorSet animatorSet4 = this.f6166w;
        if (animatorSet4 != null) {
            animatorSet4.cancel();
        }
        getToolTip().removeAllViews();
        getProductTagView().removeAllViews();
        removeAllViews();
    }

    public void d() {
        if (getToolTip().getVisibility() == 0) {
            c(400);
        }
    }

    public final void f() {
        AnimatorSet animatorSet = this.f6165v;
        if (animatorSet != null) {
            animatorSet.removeAllListeners();
            animatorSet.cancel();
        }
        AnimatorSet animatorSet2 = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(getPoint(), "scaleY", new float[]{1.0f, 1.5f});
        ofFloat.setRepeatMode(2);
        ofFloat.setRepeatCount(-1);
        Unit unit = Unit.INSTANCE;
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(getPoint(), "scaleX", new float[]{1.0f, 1.5f});
        ofFloat2.setRepeatMode(2);
        ofFloat2.setRepeatCount(-1);
        animatorSet2.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet2.setDuration(1000);
        animatorSet2.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet2.addListener(new i(this));
        animatorSet2.start();
        this.f6165v = animatorSet2;
    }

    public final void d(long j2) {
        AnimatorSet animatorSet = this.f6165v;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        getToolTip().setVisibility(0);
        getToolTip().setAlpha(0.0f);
        float measuredHeight = ((float) getPoint().getMeasuredHeight()) * 0.5f;
        i0 i0Var = this.f6162s;
        if (i0Var == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            i0Var = null;
        }
        float f2 = measuredHeight * (i0Var.f3763d.a() ? 1.0f : -1.0f);
        getToolTip().setTranslationY(getToolTip().getTranslationY() - f2);
        getToolTip().animate().cancel();
        getToolTip().animate().setStartDelay(0).setDuration(j2).setInterpolator(new DecelerateInterpolator()).alpha(1.0f).translationYBy(f2).withStartAction(new t(this, 0)).withEndAction(new t(this, 1));
    }

    public static final void g(u1 u1Var) {
        Intrinsics.checkNotNullParameter(u1Var, "this$0");
        u1Var.getPointButton().setEnabled(true);
    }

    public final void c(long j2) {
        f();
        float measuredHeight = ((float) getPoint().getMeasuredHeight()) * 0.5f;
        i0 i0Var = this.f6162s;
        if (i0Var == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            i0Var = null;
        }
        float f2 = measuredHeight * (i0Var.f3763d.a() ? 1.0f : -1.0f);
        getToolTip().animate().cancel();
        getToolTip().animate().setStartDelay(0).setDuration(j2).setInterpolator(new DecelerateInterpolator()).alpha(0.0f).translationYBy(-f2).withStartAction(new t(this, 2)).withEndAction(new u(this, f2));
    }

    public static final void a(u1 u1Var, View view) {
        Intrinsics.checkNotNullParameter(u1Var, "this$0");
        if (!u1Var.f6167x) {
            u1Var.getOnUserInteractionStarted$storyly_release().invoke();
            u1Var.getOnUserTapPoint$storyly_release().invoke();
        }
        u1Var.getOnUserReaction$storyly_release().invoke(com.appsamurai.storyly.analytics.a.ProductTagClicked, u1Var.getStorylyLayerItem$storyly_release(), null, null, null);
        u1Var.getOnUserActionClick$storyly_release().invoke(u1Var.getStorylyLayerItem$storyly_release());
    }

    public static final void a(u1 u1Var, float f2) {
        Intrinsics.checkNotNullParameter(u1Var, "this$0");
        u1Var.getPointButton().setEnabled(true);
        u1Var.getToolTip().setVisibility(8);
        u1Var.getToolTip().setTranslationY(u1Var.getToolTip().getTranslationY() + f2);
        u1Var.getOnUserInteractionEnded$storyly_release().invoke();
    }

    public final Drawable a(int i3, float f2, float f3, float f4, float f5) {
        Drawable drawable = AppCompatResources.getDrawable(getContext(), R.drawable.st_rectangle_shape_drawable);
        if (drawable != null) {
            GradientDrawable gradientDrawable = (GradientDrawable) ((GradientDrawable) drawable).mutate();
            gradientDrawable.setColor(i3);
            float applyDimension = TypedValue.applyDimension(1, f2, getContext().getResources().getDisplayMetrics());
            float applyDimension2 = TypedValue.applyDimension(1, f3, getContext().getResources().getDisplayMetrics());
            float applyDimension3 = TypedValue.applyDimension(1, f4, getContext().getResources().getDisplayMetrics());
            float applyDimension4 = TypedValue.applyDimension(1, f5, getContext().getResources().getDisplayMetrics());
            gradientDrawable.setCornerRadii(new float[]{applyDimension, applyDimension, applyDimension2, applyDimension2, applyDimension3, applyDimension3, applyDimension4, applyDimension4});
            return gradientDrawable;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.graphics.drawable.GradientDrawable");
    }
}
