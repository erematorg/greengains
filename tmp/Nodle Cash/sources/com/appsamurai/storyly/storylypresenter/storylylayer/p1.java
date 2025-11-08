package com.appsamurai.storyly.storylypresenter.storylylayer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatTextView;
import com.appsamurai.storyly.R;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.data.b0;
import com.appsamurai.storyly.data.c0;
import com.appsamurai.storyly.data.v;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressLint({"ViewConstructor"})
public final class p1 extends o1 {
    @Nullable

    /* renamed from: g  reason: collision with root package name */
    public final v f5923g;
    @NotNull

    /* renamed from: h  reason: collision with root package name */
    public final StorylyConfig f5924h;

    /* renamed from: i  reason: collision with root package name */
    public c0 f5925i;
    @Nullable

    /* renamed from: j  reason: collision with root package name */
    public Function0<Unit> f5926j;
    @Nullable

    /* renamed from: k  reason: collision with root package name */
    public Function0<Unit> f5927k;
    @Nullable

    /* renamed from: l  reason: collision with root package name */
    public Function1<? super b0, Unit> f5928l;

    /* renamed from: m  reason: collision with root package name */
    public final double f5929m = 0.1d;

    /* renamed from: n  reason: collision with root package name */
    public final double f5930n = 0.4d;

    /* renamed from: o  reason: collision with root package name */
    public final double f5931o = 0.3d;

    /* renamed from: p  reason: collision with root package name */
    public final double f5932p = 0.15d;

    /* renamed from: q  reason: collision with root package name */
    public final double f5933q = 0.33d;

    /* renamed from: r  reason: collision with root package name */
    public final int f5934r = 20;
    @NotNull

    /* renamed from: s  reason: collision with root package name */
    public final Lazy f5935s;
    @NotNull

    /* renamed from: t  reason: collision with root package name */
    public final Lazy f5936t;
    @NotNull

    /* renamed from: u  reason: collision with root package name */
    public final Lazy f5937u;
    @NotNull

    /* renamed from: v  reason: collision with root package name */
    public final Lazy f5938v;
    @NotNull

    /* renamed from: w  reason: collision with root package name */
    public final Lazy f5939w;
    @NotNull

    /* renamed from: x  reason: collision with root package name */
    public final Lazy f5940x;

    public enum a {
        ALL,
        NONE,
        TOP,
        BOTTOM
    }

    public /* synthetic */ class b {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[a.values().length];
            iArr[2] = 1;
            iArr[3] = 2;
            iArr[0] = 3;
            iArr[1] = 4;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final class c extends Lambda implements Function0<ImageView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5946a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public c(Context context) {
            super(0);
            this.f5946a = context;
        }

        public Object invoke() {
            ImageView imageView = new ImageView(this.f5946a);
            imageView.setId(View.generateViewId());
            imageView.setAdjustViewBounds(true);
            imageView.setImageResource(R.drawable.st_link);
            return imageView;
        }
    }

    public static final class d extends Lambda implements Function0<RelativeLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5947a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public d(Context context) {
            super(0);
            this.f5947a = context;
        }

        public Object invoke() {
            RelativeLayout relativeLayout = new RelativeLayout(this.f5947a);
            relativeLayout.setBackgroundColor(0);
            return relativeLayout;
        }
    }

    public static final class e extends Lambda implements Function0<TextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5948a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ p1 f5949b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public e(Context context, p1 p1Var) {
            super(0);
            this.f5948a = context;
            this.f5949b = p1Var;
        }

        public Object invoke() {
            TextView textView = new TextView(this.f5948a);
            p1 p1Var = this.f5949b;
            textView.setId(View.generateViewId());
            textView.setTextAlignment(4);
            textView.setAllCaps(false);
            textView.setMaxLines(1);
            textView.setMinLines(1);
            textView.setTextColor(p1Var.getConfig().getMoments$storyly_release().getLinkCTAStyling$storyly_release().getLinkTextColor$storyly_release());
            textView.setEllipsize(TextUtils.TruncateAt.MIDDLE);
            textView.setIncludeFontPadding(false);
            textView.setBackgroundColor(0);
            return textView;
        }
    }

    public static final class f extends Lambda implements Function0<ImageView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5950a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public f(Context context) {
            super(0);
            this.f5950a = context;
        }

        public Object invoke() {
            ImageView imageView = new ImageView(this.f5950a);
            imageView.setId(View.generateViewId());
            return imageView;
        }
    }

    public static final class g extends Lambda implements Function0<AppCompatTextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5951a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public g(Context context) {
            super(0);
            this.f5951a = context;
        }

        public Object invoke() {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f5951a);
            Context context = this.f5951a;
            appCompatTextView.setId(View.generateViewId());
            appCompatTextView.setTextAlignment(1);
            appCompatTextView.setText(context.getString(R.string.stm_cta_tool_tip_text));
            appCompatTextView.setCompoundDrawablePadding((int) (Resources.getSystem().getDisplayMetrics().density * 10.0f));
            appCompatTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.st_tooltip_right_arrow, 0);
            appCompatTextView.setTextSize(1, 12.0f);
            appCompatTextView.setTextColor(-1);
            appCompatTextView.setTextIsSelectable(false);
            return appCompatTextView;
        }
    }

    public static final class h extends Lambda implements Function0<RelativeLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5952a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public h(Context context) {
            super(0);
            this.f5952a = context;
        }

        public Object invoke() {
            RelativeLayout relativeLayout = new RelativeLayout(this.f5952a);
            relativeLayout.setId(View.generateViewId());
            relativeLayout.setClipChildren(false);
            relativeLayout.setClickable(true);
            relativeLayout.setFocusable(true);
            relativeLayout.setAlpha(0.0f);
            relativeLayout.setVisibility(4);
            relativeLayout.setClipToPadding(false);
            return relativeLayout;
        }
    }

    public static final class i extends Lambda implements Function1<Point, Unit> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ p1 f5953a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public i(p1 p1Var) {
            super(1);
            this.f5953a = p1Var;
        }

        public Object invoke(Object obj) {
            Point point = (Point) obj;
            Intrinsics.checkNotNullParameter(point, "it");
            Function0<Unit> onUserInteractionStarted$storyly_release = this.f5953a.getOnUserInteractionStarted$storyly_release();
            if (onUserInteractionStarted$storyly_release != null) {
                onUserInteractionStarted$storyly_release.invoke();
            }
            p1.a(this.f5953a, point);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public p1(@NotNull Context context, @Nullable v vVar, @NotNull StorylyConfig storylyConfig) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        this.f5923g = vVar;
        this.f5924h = storylyConfig;
        this.f5935s = LazyKt.lazy(new d(context));
        this.f5936t = LazyKt.lazy(new e(context, this));
        this.f5937u = LazyKt.lazy(new c(context));
        this.f5938v = LazyKt.lazy(new h(context));
        this.f5939w = LazyKt.lazy(new g(context));
        this.f5940x = LazyKt.lazy(new f(context));
    }

    private final ImageView getImageView() {
        return (ImageView) this.f5937u.getValue();
    }

    private final RelativeLayout getLinkCtaView() {
        return (RelativeLayout) this.f5935s.getValue();
    }

    private final TextView getTextView() {
        return (TextView) this.f5936t.getValue();
    }

    private final ImageView getToolTipArrowImageView() {
        return (ImageView) this.f5940x.getValue();
    }

    private final AppCompatTextView getToolTipTextView() {
        return (AppCompatTextView) this.f5939w.getValue();
    }

    private final RelativeLayout getToolTipView() {
        return (RelativeLayout) this.f5938v.getValue();
    }

    public void a(@NotNull b0 b0Var) {
        Intrinsics.checkNotNullParameter(b0Var, "storylyLayerItem");
        this.f5925i = (c0) b0Var.f3615j;
        setStorylyLayerItem$storyly_release(b0Var);
        Typeface momentsCustomFont$storyly_release = this.f5924h.getMoments$storyly_release().getTextStyling$storyly_release().getMomentsCustomFont$storyly_release("nunito");
        if (momentsCustomFont$storyly_release != null) {
            getTextView().setTypeface(momentsCustomFont$storyly_release);
        } else {
            getTextView().setTypeface(Typeface.DEFAULT);
        }
        setRotation(b0Var.f3613h);
        getOnLayerLoad$storyly_release().invoke();
    }

    public final void f() {
        RelativeLayout toolTipView = getToolTipView();
        toolTipView.animate().cancel();
        toolTipView.animate().setDuration(400).alpha(0.0f).withEndAction(new G0.a(toolTipView, 1));
    }

    @NotNull
    public final StorylyConfig getConfig() {
        return this.f5924h;
    }

    public final double getIconLeadingPaddingRatio$storyly_release() {
        return this.f5931o;
    }

    public final double getIconSizeRatio$storyly_release() {
        return this.f5930n;
    }

    @Nullable
    public final Function1<b0, Unit> getOnUserActionClick$storyly_release() {
        return this.f5928l;
    }

    @Nullable
    public final Function0<Unit> getOnUserInteractionEnded$storyly_release() {
        return this.f5927k;
    }

    @Nullable
    public final Function0<Unit> getOnUserInteractionStarted$storyly_release() {
        return this.f5926j;
    }

    @Nullable
    public final v getStorylyGroupItem() {
        return this.f5923g;
    }

    public final double getTextHorizontalPaddingRatio$storyly_release() {
        return this.f5932p;
    }

    public final double getTextSizeRatio$storyly_release() {
        return this.f5933q;
    }

    public final int getTooltipBottomPadding$storyly_release() {
        return this.f5934r;
    }

    public final void setOnUserActionClick$storyly_release(@Nullable Function1<? super b0, Unit> function1) {
        this.f5928l = function1;
    }

    public final void setOnUserInteractionEnded$storyly_release(@Nullable Function0<Unit> function0) {
        this.f5927k = function0;
    }

    public final void setOnUserInteractionStarted$storyly_release(@Nullable Function0<Unit> function0) {
        this.f5926j = function0;
    }

    /* JADX WARNING: type inference failed for: r12v7, types: [android.view.ViewParent] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(@org.jetbrains.annotations.NotNull com.appsamurai.storyly.storylypresenter.storylylayer.d r12) {
        /*
            r11 = this;
            java.lang.String r0 = "safeFrame"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r0)
            float r0 = r12.b()
            float r1 = r12.a()
            com.appsamurai.storyly.data.b0 r2 = r11.getStorylyLayerItem$storyly_release()
            float r2 = r2.f3610e
            float r1 = r1 * r2
            r2 = 100
            float r2 = (float) r2
            float r1 = r1 / r2
            int r1 = (int) r1
            com.appsamurai.storyly.data.b0 r3 = r11.getStorylyLayerItem$storyly_release()
            float r3 = r3.f3609d
            float r0 = r0 * r3
            float r0 = r0 / r2
            int r0 = (int) r0
            android.widget.FrameLayout$LayoutParams r3 = new android.widget.FrameLayout$LayoutParams
            r3.<init>(r0, r1)
            com.appsamurai.storyly.data.b0 r0 = r11.getStorylyLayerItem$storyly_release()
            android.graphics.Point r0 = r0.b()
            int r4 = r0.x
            com.appsamurai.storyly.data.b0 r0 = r11.getStorylyLayerItem$storyly_release()
            android.graphics.Point r0 = r0.b()
            int r5 = r0.y
            float r6 = r12.c()
            float r7 = r12.d()
            r2 = r11
            android.widget.FrameLayout$LayoutParams r12 = r2.a(r3, r4, r5, r6, r7)
            r11.setLayoutParams(r12)
            double r0 = (double) r1
            double r2 = r11.f5930n
            double r2 = r2 * r0
            int r12 = (int) r2
            double r2 = r11.f5931o
            double r2 = r2 * r0
            int r2 = (int) r2
            double r3 = r11.f5933q
            double r3 = r3 * r0
            android.widget.TextView r5 = r11.getTextView()
            com.appsamurai.storyly.data.c0 r6 = r11.f5925i
            r7 = 0
            if (r6 != 0) goto L_0x0067
            java.lang.String r6 = "storylyLayer"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)
            r6 = r7
        L_0x0067:
            java.lang.String r6 = r6.f3625a
            r5.setText(r6)
            float r3 = (float) r3
            r4 = 0
            r5.setTextSize(r4, r3)
            double r5 = r11.f5932p
            double r5 = r5 * r0
            int r3 = (int) r5
            double r5 = r11.f5929m
            double r0 = r0 * r5
            float r0 = (float) r0
            com.appsamurai.storyly.storylypresenter.storylylayer.p1$a r1 = com.appsamurai.storyly.storylypresenter.storylylayer.p1.a.ALL
            r5 = -1
            android.graphics.drawable.Drawable r0 = r11.a(r1, r0, r5)
            r11.setBackground(r0)
            android.widget.FrameLayout$LayoutParams r0 = new android.widget.FrameLayout$LayoutParams
            r0.<init>(r5, r5)
            r6 = 16
            r0.gravity = r6
            android.widget.RelativeLayout$LayoutParams r6 = new android.widget.RelativeLayout$LayoutParams
            r6.<init>(r12, r12)
            r12 = 15
            r6.addRule(r12)
            r6.setMarginStart(r2)
            android.widget.RelativeLayout$LayoutParams r2 = new android.widget.RelativeLayout$LayoutParams
            r8 = -2
            r2.<init>(r8, r8)
            r2.addRule(r12)
            r12 = 21
            r2.addRule(r12)
            r12 = 14
            r2.addRule(r12)
            android.widget.ImageView r9 = r11.getImageView()
            int r9 = r9.getId()
            r10 = 1
            r2.addRule(r10, r9)
            r2.setMarginEnd(r3)
            r2.setMarginStart(r3)
            android.widget.RelativeLayout r3 = r11.getLinkCtaView()
            r11.addView(r3, r0)
            android.widget.RelativeLayout r0 = r11.getLinkCtaView()
            android.widget.ImageView r3 = r11.getImageView()
            r0.addView(r3, r6)
            android.widget.RelativeLayout r0 = r11.getLinkCtaView()
            android.widget.TextView r3 = r11.getTextView()
            r0.addView(r3, r2)
            androidx.appcompat.widget.AppCompatTextView r0 = r11.getToolTipTextView()
            java.lang.String r2 = "#99212121"
            int r2 = android.graphics.Color.parseColor(r2)
            r3 = 1092616192(0x41200000, float:10.0)
            android.graphics.drawable.Drawable r1 = r11.a(r1, r3, r2)
            r0.setBackground(r1)
            r1 = 12
            float r2 = com.appsamurai.storyly.util.m.a((int) r1)
            int r2 = (int) r2
            r3 = 10
            float r6 = com.appsamurai.storyly.util.m.a((int) r3)
            int r6 = (int) r6
            float r1 = com.appsamurai.storyly.util.m.a((int) r1)
            int r1 = (int) r1
            float r3 = com.appsamurai.storyly.util.m.a((int) r3)
            int r3 = (int) r3
            r0.setPadding(r2, r6, r1, r3)
            android.widget.ImageView r0 = r11.getToolTipArrowImageView()
            r0.setPadding(r4, r4, r4, r4)
            int r1 = com.appsamurai.storyly.R.drawable.st_link_cta_tooltip_arrow
            r0.setImageResource(r1)
            android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_CENTER
            r0.setScaleType(r1)
            android.widget.RelativeLayout r0 = r11.getToolTipView()
            androidx.appcompat.widget.AppCompatTextView r1 = r11.getToolTipTextView()
            android.widget.RelativeLayout$LayoutParams r2 = new android.widget.RelativeLayout$LayoutParams
            r2.<init>(r8, r8)
            r0.addView(r1, r2)
            android.widget.RelativeLayout r0 = r11.getToolTipView()
            android.widget.ImageView r1 = r11.getToolTipArrowImageView()
            android.widget.RelativeLayout$LayoutParams r2 = new android.widget.RelativeLayout$LayoutParams
            r3 = 5
            float r6 = com.appsamurai.storyly.util.m.a((int) r3)
            int r6 = (int) r6
            float r3 = com.appsamurai.storyly.util.m.a((int) r3)
            int r3 = (int) r3
            r2.<init>(r6, r3)
            androidx.appcompat.widget.AppCompatTextView r3 = r11.getToolTipTextView()
            int r3 = r3.getId()
            r6 = 3
            r2.addRule(r6, r3)
            r2.addRule(r12)
            r2.topMargin = r5
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            r0.addView(r1, r2)
            android.view.ViewParent r12 = r11.getParent()
            boolean r0 = r12 instanceof android.widget.FrameLayout
            if (r0 == 0) goto L_0x0163
            r7 = r12
            android.widget.FrameLayout r7 = (android.widget.FrameLayout) r7
        L_0x0163:
            if (r7 != 0) goto L_0x0166
            goto L_0x0174
        L_0x0166:
            android.widget.RelativeLayout r12 = r11.getToolTipView()
            android.widget.FrameLayout$LayoutParams r0 = new android.widget.FrameLayout$LayoutParams
            r0.<init>(r8, r8)
            r0.gravity = r4
            r7.addView(r12, r0)
        L_0x0174:
            com.appsamurai.storyly.storylypresenter.storylylayer.p1$i r12 = new com.appsamurai.storyly.storylypresenter.storylylayer.p1$i
            r12.<init>(r11)
            com.appsamurai.storyly.storylypresenter.storylylayer.q1.a(r11, r12)
            android.widget.RelativeLayout r12 = r11.getToolTipView()
            E0.c r0 = new E0.c
            r1 = 4
            r0.<init>(r11, r1)
            r12.setOnClickListener(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.storylypresenter.storylylayer.p1.a(com.appsamurai.storyly.storylypresenter.storylylayer.d):void");
    }

    public static final void a(p1 p1Var, View view) {
        Intrinsics.checkNotNullParameter(p1Var, "this$0");
        Function1<b0, Unit> onUserActionClick$storyly_release = p1Var.getOnUserActionClick$storyly_release();
        if (onUserActionClick$storyly_release != null) {
            onUserActionClick$storyly_release.invoke(p1Var.getStorylyLayerItem$storyly_release());
        }
    }

    public static final void a(p1 p1Var, Point point) {
        if (p1Var.getToolTipView().getVisibility() == 0) {
            Function0<Unit> function0 = p1Var.f5927k;
            if (function0 != null) {
                function0.invoke();
            }
            p1Var.f();
            return;
        }
        if (point.x < p1Var.getToolTipView().getWidth() / 2) {
            point.x = p1Var.getToolTipView().getWidth() / 2;
        }
        if (((float) point.x) > p1Var.getSafeFrame$storyly_release().b() - ((float) (p1Var.getToolTipView().getWidth() / 2))) {
            point.x = (int) (p1Var.getSafeFrame$storyly_release().b() - ((float) (p1Var.getToolTipView().getWidth() / 2)));
        }
        RelativeLayout toolTipView = p1Var.getToolTipView();
        int width = point.x - (p1Var.getToolTipView().getWidth() / 2);
        int height = point.y - p1Var.getToolTipView().getHeight();
        if (toolTipView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.LayoutParams layoutParams = toolTipView.getLayoutParams();
            if (layoutParams != null) {
                ((ViewGroup.MarginLayoutParams) layoutParams).setMargins(width, height, 0, 0);
                toolTipView.requestLayout();
            } else {
                throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
            }
        }
        RelativeLayout toolTipView2 = p1Var.getToolTipView();
        toolTipView2.setVisibility(0);
        toolTipView2.setAlpha(0.0f);
        toolTipView2.animate().cancel();
        toolTipView2.animate().setDuration(300).alpha(1.0f);
    }

    public static final void a(RelativeLayout relativeLayout) {
        Intrinsics.checkNotNullParameter(relativeLayout, "$this_apply");
        relativeLayout.setVisibility(8);
    }

    public final Drawable a(a aVar, float f2, int i3) {
        Drawable drawable = AppCompatResources.getDrawable(getContext(), R.drawable.st_rectangle_shape_drawable);
        if (drawable != null) {
            GradientDrawable gradientDrawable = (GradientDrawable) ((GradientDrawable) drawable).mutate();
            gradientDrawable.setColor(i3);
            gradientDrawable.setStroke(2, Color.parseColor("#0D000000"));
            float applyDimension = TypedValue.applyDimension(1, f2, getContext().getResources().getDisplayMetrics());
            int i4 = b.$EnumSwitchMapping$0[aVar.ordinal()];
            if (i4 == 1) {
                gradientDrawable.setCornerRadii(new float[]{applyDimension, applyDimension, applyDimension, applyDimension, 0.0f, 0.0f, 0.0f, 0.0f});
            } else if (i4 == 2) {
                gradientDrawable.setCornerRadii(new float[]{0.0f, 0.0f, 0.0f, 0.0f, applyDimension, applyDimension, applyDimension, applyDimension});
            } else if (i4 == 3) {
                gradientDrawable.setCornerRadii(new float[]{applyDimension, applyDimension, applyDimension, applyDimension, applyDimension, applyDimension, applyDimension, applyDimension});
            }
            return gradientDrawable;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.graphics.drawable.GradientDrawable");
    }
}
