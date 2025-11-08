package com.appsamurai.storyly.storylypresenter.storylylayer.storylyProductList;

import android.animation.StateListAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.GravityCompat;
import com.appsamurai.storyly.config.StorylyConfig;
import com.bumptech.glide.Glide;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressLint({"ViewConstructor"})
public final class f extends RelativeLayout {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final StorylyConfig f6071a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final Lazy f6072b;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public final Lazy f6073c;
    @NotNull

    /* renamed from: d  reason: collision with root package name */
    public final Lazy f6074d;
    @NotNull

    /* renamed from: e  reason: collision with root package name */
    public final Lazy f6075e;
    @NotNull

    /* renamed from: f  reason: collision with root package name */
    public final Lazy f6076f;
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public final Lazy f6077g;
    @NotNull

    /* renamed from: h  reason: collision with root package name */
    public final Lazy f6078h;
    @NotNull

    /* renamed from: i  reason: collision with root package name */
    public final Lazy f6079i;

    public static final class a extends Lambda implements Function0<AppCompatButton> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f6080a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(Context context) {
            super(0);
            this.f6080a = context;
        }

        public Object invoke() {
            AppCompatButton appCompatButton = new AppCompatButton(this.f6080a);
            appCompatButton.setId(View.generateViewId());
            appCompatButton.setAllCaps(false);
            appCompatButton.setClickable(false);
            appCompatButton.setStateListAnimator((StateListAnimator) null);
            appCompatButton.setEllipsize(TextUtils.TruncateAt.END);
            appCompatButton.setSingleLine(true);
            appCompatButton.setTextAlignment(1);
            return appCompatButton;
        }
    }

    public static final class b extends Lambda implements Function0<View> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f6081a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public b(Context context) {
            super(0);
            this.f6081a = context;
        }

        public Object invoke() {
            return new View(this.f6081a);
        }
    }

    public static final class c extends Lambda implements Function0<FrameLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f6082a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ f f6083b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public c(Context context, f fVar) {
            super(0);
            this.f6082a = context;
            this.f6083b = fVar;
        }

        public Object invoke() {
            FrameLayout frameLayout = new FrameLayout(this.f6082a);
            this.f6083b.setGravity(13);
            frameLayout.setId(View.generateViewId());
            return frameLayout;
        }
    }

    public static final class d extends Lambda implements Function0<AppCompatImageView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f6084a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public d(Context context) {
            super(0);
            this.f6084a = context;
        }

        public Object invoke() {
            AppCompatImageView appCompatImageView = new AppCompatImageView(this.f6084a);
            appCompatImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return appCompatImageView;
        }
    }

    public static final class e extends Lambda implements Function0<AppCompatTextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f6085a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public e(Context context) {
            super(0);
            this.f6085a = context;
        }

        public Object invoke() {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f6085a);
            appCompatTextView.setId(View.generateViewId());
            appCompatTextView.setSingleLine(true);
            appCompatTextView.setEllipsize(TextUtils.TruncateAt.END);
            appCompatTextView.setIncludeFontPadding(false);
            appCompatTextView.setGravity(GravityCompat.END);
            appCompatTextView.setLineSpacing(0.0f, 0.0f);
            appCompatTextView.setPaintFlags(appCompatTextView.getPaintFlags() | 16);
            appCompatTextView.setHorizontallyScrolling(false);
            return appCompatTextView;
        }
    }

    /* renamed from: com.appsamurai.storyly.storylypresenter.storylylayer.storylyProductList.f$f  reason: collision with other inner class name */
    public static final class C0054f extends Lambda implements Function0<LinearLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f6086a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public C0054f(Context context) {
            super(0);
            this.f6086a = context;
        }

        public Object invoke() {
            LinearLayout linearLayout = new LinearLayout(this.f6086a);
            linearLayout.setId(View.generateViewId());
            linearLayout.setOrientation(1);
            linearLayout.setGravity(GravityCompat.END);
            linearLayout.setVerticalGravity(16);
            return linearLayout;
        }
    }

    public static final class g extends Lambda implements Function0<AppCompatTextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f6087a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public g(Context context) {
            super(0);
            this.f6087a = context;
        }

        public Object invoke() {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f6087a);
            appCompatTextView.setId(View.generateViewId());
            appCompatTextView.setSingleLine(true);
            appCompatTextView.setEllipsize(TextUtils.TruncateAt.END);
            appCompatTextView.setGravity(GravityCompat.END);
            appCompatTextView.setIncludeFontPadding(false);
            appCompatTextView.setHorizontallyScrolling(false);
            return appCompatTextView;
        }
    }

    public static final class h extends Lambda implements Function0<AppCompatTextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f6088a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public h(Context context) {
            super(0);
            this.f6088a = context;
        }

        public Object invoke() {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f6088a);
            appCompatTextView.setId(View.generateViewId());
            appCompatTextView.setEllipsize(TextUtils.TruncateAt.END);
            appCompatTextView.setMaxLines(2);
            appCompatTextView.setMinLines(1);
            appCompatTextView.setIncludeFontPadding(false);
            appCompatTextView.setHorizontallyScrolling(false);
            appCompatTextView.setTextAlignment(5);
            com.appsamurai.storyly.util.d.a(appCompatTextView);
            return appCompatTextView;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public f(@NotNull Context context, @Nullable AttributeSet attributeSet, int i3, @NotNull StorylyConfig storylyConfig) {
        super(context, attributeSet, i3);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        this.f6071a = storylyConfig;
        this.f6072b = LazyKt.lazy(new c(context, this));
        this.f6073c = LazyKt.lazy(new d(context));
        this.f6074d = LazyKt.lazy(new b(context));
        this.f6075e = LazyKt.lazy(new h(context));
        this.f6076f = LazyKt.lazy(new C0054f(context));
        this.f6077g = LazyKt.lazy(new e(context));
        this.f6078h = LazyKt.lazy(new g(context));
        this.f6079i = LazyKt.lazy(new a(context));
    }

    private final AppCompatButton getActionButton() {
        return (AppCompatButton) this.f6079i.getValue();
    }

    private final View getImageBorder() {
        return (View) this.f6074d.getValue();
    }

    private final FrameLayout getImageContainer() {
        return (FrameLayout) this.f6072b.getValue();
    }

    private final AppCompatImageView getImageView() {
        return (AppCompatImageView) this.f6073c.getValue();
    }

    private final AppCompatTextView getOldPriceTextView() {
        return (AppCompatTextView) this.f6077g.getValue();
    }

    private final LinearLayout getPriceContainer() {
        return (LinearLayout) this.f6076f.getValue();
    }

    private final AppCompatTextView getPriceTextView() {
        return (AppCompatTextView) this.f6078h.getValue();
    }

    private final AppCompatTextView getTitleTextView() {
        return (AppCompatTextView) this.f6075e.getValue();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v20, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v4, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(int r34, @org.jetbrains.annotations.Nullable com.appsamurai.storyly.data.managers.product.STRProductItem r35, @org.jetbrains.annotations.NotNull com.appsamurai.storyly.storylypresenter.storylylayer.storylyProductList.a r36) {
        /*
            r33 = this;
            r6 = r33
            r7 = r36
            java.lang.String r0 = "entity"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            if (r35 != 0) goto L_0x000c
            return
        L_0x000c:
            r0 = r34
            float r11 = (float) r0
            r0 = 1043878380(0x3e3851ec, float:0.18)
            float r0 = r0 * r11
            r1 = 1010055512(0x3c343958, float:0.011)
            float r1 = r1 * r11
            int r1 = (int) r1
            r2 = 1035489772(0x3db851ec, float:0.09)
            float r2 = r2 * r11
            int r12 = (int) r2
            r2 = 1052266988(0x3eb851ec, float:0.36)
            float r2 = r2 * r11
            int r13 = (int) r2
            r2 = 1043542835(0x3e333333, float:0.175)
            float r2 = r2 * r11
            int r14 = (int) r2
            double r2 = (double) r14
            r4 = 4605831338911806259(0x3feb333333333333, double:0.85)
            double r9 = r2 * r4
            r16 = 1062333317(0x3f51eb85, float:0.82)
            float r15 = r11 * r16
            int r15 = (int) r15
            r16 = 1040187392(0x3e000000, float:0.125)
            float r8 = r11 * r16
            float r4 = (float) r15
            r5 = 1017370378(0x3ca3d70a, float:0.02)
            float r4 = r4 * r5
            int r4 = (int) r4
            r5 = 1051595899(0x3eae147b, float:0.34)
            float r5 = r5 * r11
            int r5 = (int) r5
            r16 = 1042871747(0x3e28f5c3, float:0.165)
            r19 = r5
            float r5 = r11 * r16
            r20 = r9
            double r9 = (double) r5
            r17 = 4605831338911806259(0x3feb333333333333, double:0.85)
            double r9 = r9 * r17
            r5 = 1040858481(0x3e0a3d71, float:0.135)
            float r5 = r5 * r11
            int r5 = (int) r5
            r22 = 4606506878855911834(0x3fed99999999999a, double:0.925)
            double r2 = r2 * r22
            int r3 = (int) r2
            r22 = r9
            double r9 = (double) r3
            double r9 = r9 * r17
            com.appsamurai.storyly.config.StorylyConfig r2 = r6.f6071a
            com.appsamurai.storyly.config.StorylyProductConfig r2 = r2.getProduct$storyly_release()
            com.appsamurai.storyly.util.formatter.b r2 = r2.getPriceFormatter$storyly_release()
            r16 = 0
            if (r2 != 0) goto L_0x007c
            r18 = r3
            r17 = r5
            r24 = r16
            goto L_0x009e
        L_0x007c:
            java.lang.Float r17 = r35.getSalesPrice()
            if (r17 != 0) goto L_0x0089
            float r17 = r35.getPrice()
        L_0x0086:
            r18 = r3
            goto L_0x008e
        L_0x0089:
            float r17 = r17.floatValue()
            goto L_0x0086
        L_0x008e:
            java.lang.Float r3 = java.lang.Float.valueOf(r17)
            r17 = r5
            java.lang.String r5 = r35.getCurrency()
            java.lang.String r2 = r2.a(r3, r5)
            r24 = r2
        L_0x009e:
            com.appsamurai.storyly.config.StorylyConfig r2 = r6.f6071a
            com.appsamurai.storyly.config.StorylyProductConfig r2 = r2.getProduct$storyly_release()
            com.appsamurai.storyly.util.formatter.b r2 = r2.getPriceFormatter$storyly_release()
            if (r2 != 0) goto L_0x00ad
            r5 = r16
            goto L_0x00be
        L_0x00ad:
            float r3 = r35.getPrice()
            java.lang.Float r3 = java.lang.Float.valueOf(r3)
            java.lang.String r5 = r35.getCurrency()
            java.lang.String r2 = r2.a(r3, r5)
            r5 = r2
        L_0x00be:
            java.util.List r2 = r35.getImageUrls()
            if (r2 != 0) goto L_0x00c5
            goto L_0x00d4
        L_0x00c5:
            java.lang.Object r2 = kotlin.collections.CollectionsKt.firstOrNull(r2)
            java.lang.String r2 = (java.lang.String) r2
            if (r2 != 0) goto L_0x00ce
            goto L_0x00d4
        L_0x00ce:
            int r2 = r2.length()
            if (r2 != 0) goto L_0x00d7
        L_0x00d4:
            r25 = 0
            goto L_0x00d9
        L_0x00d7:
            r25 = 1
        L_0x00d9:
            java.lang.Integer r2 = r7.f6048b
            if (r2 != 0) goto L_0x00df
            r2 = -1
            goto L_0x00e3
        L_0x00df:
            int r2 = r2.intValue()
        L_0x00e3:
            java.lang.Integer r3 = r7.f6047a
            java.lang.String r26 = "#EEEEEE"
            if (r3 != 0) goto L_0x00ee
            int r3 = android.graphics.Color.parseColor(r26)
            goto L_0x00f2
        L_0x00ee:
            int r3 = r3.intValue()
        L_0x00f2:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            android.graphics.drawable.GradientDrawable r0 = com.appsamurai.storyly.util.ui.b.a(r6, r2, r0, r3, r1)
            r6.setBackground(r0)
            java.lang.Class<android.widget.RelativeLayout$LayoutParams> r2 = android.widget.RelativeLayout.LayoutParams.class
            java.lang.String r1 = "layoutParams"
            if (r25 == 0) goto L_0x0221
            android.widget.FrameLayout r0 = r33.getImageContainer()
            java.lang.Class r3 = java.lang.Integer.TYPE
            r27 = r5
            java.lang.Class[] r5 = new java.lang.Class[]{r3, r3}
            java.lang.reflect.Constructor r5 = r2.getConstructor(r5)
            r28 = r9
            java.lang.Integer r9 = java.lang.Integer.valueOf(r15)
            java.lang.Integer r10 = java.lang.Integer.valueOf(r15)
            java.lang.Object[] r9 = new java.lang.Object[]{r9, r10}
            java.lang.Object r5 = r5.newInstance(r9)
            android.view.ViewGroup$LayoutParams r5 = (android.view.ViewGroup.LayoutParams) r5
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r1)
            r9 = r5
            android.widget.RelativeLayout$LayoutParams r9 = (android.widget.RelativeLayout.LayoutParams) r9
            r9.setMarginStart(r12)
            r10 = 13
            r9.addRule(r10)
            r10 = 20
            r9.addRule(r10)
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            r6.addView(r0, r5)
            android.widget.FrameLayout r0 = r33.getImageContainer()
            androidx.appcompat.widget.AppCompatImageView r5 = r33.getImageView()
            java.lang.Class[] r9 = new java.lang.Class[]{r3, r3}
            java.lang.reflect.Constructor r9 = r2.getConstructor(r9)
            java.lang.Integer r10 = java.lang.Integer.valueOf(r15)
            r30 = r11
            java.lang.Integer r11 = java.lang.Integer.valueOf(r15)
            java.lang.Object[] r10 = new java.lang.Object[]{r10, r11}
            java.lang.Object r9 = r9.newInstance(r10)
            android.view.ViewGroup$LayoutParams r9 = (android.view.ViewGroup.LayoutParams) r9
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r1)
            r0.addView(r5, r9)
            android.widget.FrameLayout r0 = r33.getImageContainer()
            android.view.View r5 = r33.getImageBorder()
            java.lang.Class[] r3 = new java.lang.Class[]{r3, r3}
            java.lang.reflect.Constructor r3 = r2.getConstructor(r3)
            java.lang.Integer r9 = java.lang.Integer.valueOf(r15)
            java.lang.Integer r10 = java.lang.Integer.valueOf(r15)
            java.lang.Object[] r9 = new java.lang.Object[]{r9, r10}
            java.lang.Object r3 = r3.newInstance(r9)
            android.view.ViewGroup$LayoutParams r3 = (android.view.ViewGroup.LayoutParams) r3
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r1)
            r0.addView(r5, r3)
            android.view.View r9 = r33.getImageBorder()
            int r0 = android.graphics.Color.parseColor(r26)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r0)
            r5 = 0
            r10 = 1
            r0 = r33
            r11 = r1
            r1 = r5
            r15 = r2
            r2 = r8
            r5 = r18
            r31 = r5
            r18 = r14
            r14 = r17
            r17 = r19
            r32 = r27
            r5 = r10
            android.graphics.drawable.GradientDrawable r0 = com.appsamurai.storyly.util.ui.b.a(r0, r1, r2, r3, r4, r5)
            r9.setBackground(r0)
            java.util.List r0 = r35.getImageUrls()
            if (r0 != 0) goto L_0x01c2
        L_0x01bf:
            r0 = r16
            goto L_0x01cb
        L_0x01c2:
            java.lang.Object r0 = kotlin.collections.CollectionsKt.firstOrNull(r0)
            r16 = r0
            java.lang.String r16 = (java.lang.String) r16
            goto L_0x01bf
        L_0x01cb:
            r1 = 0
            int r1 = (r8 > r1 ? 1 : (r8 == r1 ? 0 : -1))
            if (r1 <= 0) goto L_0x01f0
            com.bumptech.glide.request.RequestOptions r1 = new com.bumptech.glide.request.RequestOptions
            r1.<init>()
            com.bumptech.glide.load.resource.bitmap.CenterCrop r2 = new com.bumptech.glide.load.resource.bitmap.CenterCrop
            r2.<init>()
            com.bumptech.glide.load.resource.bitmap.RoundedCorners r3 = new com.bumptech.glide.load.resource.bitmap.RoundedCorners
            int r4 = (int) r8
            r3.<init>(r4)
            r4 = 2
            com.bumptech.glide.load.Transformation[] r5 = new com.bumptech.glide.load.Transformation[r4]
            r4 = 0
            r5[r4] = r2
            r2 = 1
            r5[r2] = r3
            com.bumptech.glide.request.BaseRequestOptions r1 = r1.transform((com.bumptech.glide.load.Transformation<android.graphics.Bitmap>[]) r5)
            com.bumptech.glide.request.RequestOptions r1 = (com.bumptech.glide.request.RequestOptions) r1
            goto L_0x0200
        L_0x01f0:
            com.bumptech.glide.request.RequestOptions r1 = new com.bumptech.glide.request.RequestOptions
            r1.<init>()
            com.bumptech.glide.load.resource.bitmap.CenterCrop r2 = new com.bumptech.glide.load.resource.bitmap.CenterCrop
            r2.<init>()
            com.bumptech.glide.request.BaseRequestOptions r1 = r1.transform((com.bumptech.glide.load.Transformation<android.graphics.Bitmap>) r2)
            com.bumptech.glide.request.RequestOptions r1 = (com.bumptech.glide.request.RequestOptions) r1
        L_0x0200:
            java.lang.String r2 = "if (cornerRadius > 0) {\n…m(CenterCrop())\n        }"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            com.bumptech.glide.RequestManager r2 = com.bumptech.glide.Glide.with((android.view.View) r33)
            com.bumptech.glide.RequestBuilder r0 = r2.load((java.lang.String) r0)
            com.bumptech.glide.load.engine.DiskCacheStrategy r2 = com.bumptech.glide.load.engine.DiskCacheStrategy.ALL
            com.bumptech.glide.request.BaseRequestOptions r0 = r0.diskCacheStrategy(r2)
            com.bumptech.glide.RequestBuilder r0 = (com.bumptech.glide.RequestBuilder) r0
            com.bumptech.glide.RequestBuilder r0 = r0.apply((com.bumptech.glide.request.BaseRequestOptions<?>) r1)
            androidx.appcompat.widget.AppCompatImageView r1 = r33.getImageView()
            r0.into((android.widget.ImageView) r1)
            goto L_0x0231
        L_0x0221:
            r15 = r2
            r32 = r5
            r28 = r9
            r30 = r11
            r31 = r18
            r11 = r1
            r18 = r14
            r14 = r17
            r17 = r19
        L_0x0231:
            androidx.appcompat.widget.AppCompatTextView r0 = r33.getTitleTextView()
            java.lang.Class r1 = java.lang.Integer.TYPE
            java.lang.Class[] r2 = new java.lang.Class[]{r1, r1}
            java.lang.reflect.Constructor r2 = r15.getConstructor(r2)
            r3 = -2
            java.lang.Integer r4 = java.lang.Integer.valueOf(r3)
            java.lang.Integer r5 = java.lang.Integer.valueOf(r13)
            java.lang.Object[] r4 = new java.lang.Object[]{r4, r5}
            java.lang.Object r2 = r2.newInstance(r4)
            android.view.ViewGroup$LayoutParams r2 = (android.view.ViewGroup.LayoutParams) r2
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r11)
            r4 = r2
            android.widget.RelativeLayout$LayoutParams r4 = (android.widget.RelativeLayout.LayoutParams) r4
            r5 = 17
            if (r25 == 0) goto L_0x0268
            android.widget.FrameLayout r8 = r33.getImageContainer()
            int r8 = r8.getId()
            r4.addRule(r5, r8)
            goto L_0x0272
        L_0x0268:
            r8 = 20
            r4.addRule(r8)
            r4.setMarginStart(r12)
            r4.topMargin = r12
        L_0x0272:
            android.widget.FrameLayout r8 = r33.getImageContainer()
            int r8 = r8.getId()
            r9 = 6
            r4.addRule(r9, r8)
            r8 = 11
            r4.addRule(r8)
            r4.setMarginStart(r12)
            r4.setMarginEnd(r12)
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            r6.addView(r0, r2)
            androidx.appcompat.widget.AppCompatButton r0 = r33.getActionButton()
            java.lang.Class[] r2 = new java.lang.Class[]{r1, r1}
            java.lang.reflect.Constructor r2 = r15.getConstructor(r2)
            java.lang.Integer r4 = java.lang.Integer.valueOf(r3)
            java.lang.Integer r8 = java.lang.Integer.valueOf(r17)
            java.lang.Object[] r4 = new java.lang.Object[]{r4, r8}
            java.lang.Object r2 = r2.newInstance(r4)
            android.view.ViewGroup$LayoutParams r2 = (android.view.ViewGroup.LayoutParams) r2
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r11)
            r4 = r2
            android.widget.RelativeLayout$LayoutParams r4 = (android.widget.RelativeLayout.LayoutParams) r4
            r8 = 12
            r4.addRule(r8)
            r10 = 21
            r4.addRule(r10)
            r4.bottomMargin = r12
            r4.setMarginEnd(r12)
            r6.addView(r0, r2)
            android.widget.LinearLayout r0 = r33.getPriceContainer()
            java.lang.Class[] r2 = new java.lang.Class[]{r1, r1}
            java.lang.reflect.Constructor r2 = r15.getConstructor(r2)
            java.lang.Integer r4 = java.lang.Integer.valueOf(r3)
            java.lang.Integer r13 = java.lang.Integer.valueOf(r3)
            java.lang.Object[] r4 = new java.lang.Object[]{r4, r13}
            java.lang.Object r2 = r2.newInstance(r4)
            android.view.ViewGroup$LayoutParams r2 = (android.view.ViewGroup.LayoutParams) r2
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r11)
            r4 = r2
            android.widget.RelativeLayout$LayoutParams r4 = (android.widget.RelativeLayout.LayoutParams) r4
            java.lang.String r13 = r7.f6052f
            r15 = 8
            if (r13 == 0) goto L_0x0324
            int r13 = r13.length()
            if (r13 != 0) goto L_0x02f5
            goto L_0x0324
        L_0x02f5:
            androidx.appcompat.widget.AppCompatButton r8 = r33.getActionButton()
            int r8 = r8.getId()
            r4.addRule(r9, r8)
            android.widget.FrameLayout r8 = r33.getImageContainer()
            int r8 = r8.getId()
            r4.addRule(r5, r8)
            androidx.appcompat.widget.AppCompatButton r8 = r33.getActionButton()
            int r8 = r8.getId()
            r9 = 16
            r4.addRule(r9, r8)
            androidx.appcompat.widget.AppCompatButton r8 = r33.getActionButton()
            int r8 = r8.getId()
            r4.addRule(r15, r8)
            goto L_0x032c
        L_0x0324:
            r4.addRule(r8)
            r4.addRule(r10)
            r4.bottomMargin = r14
        L_0x032c:
            r4.leftMargin = r14
            r4.rightMargin = r14
            r6.addView(r0, r2)
            android.widget.LinearLayout r0 = r33.getPriceContainer()
            java.lang.Boolean r2 = r7.f6053g
            java.lang.Boolean r4 = java.lang.Boolean.TRUE
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r4)
            java.lang.Class<android.view.ViewGroup$LayoutParams> r8 = android.view.ViewGroup.LayoutParams.class
            if (r2 == 0) goto L_0x0367
            androidx.appcompat.widget.AppCompatTextView r2 = r33.getPriceTextView()
            java.lang.Class[] r9 = new java.lang.Class[]{r1, r1}
            java.lang.reflect.Constructor r9 = r8.getConstructor(r9)
            java.lang.Integer r10 = java.lang.Integer.valueOf(r3)
            java.lang.Integer r13 = java.lang.Integer.valueOf(r3)
            java.lang.Object[] r10 = new java.lang.Object[]{r10, r13}
            java.lang.Object r9 = r9.newInstance(r10)
            android.view.ViewGroup$LayoutParams r9 = (android.view.ViewGroup.LayoutParams) r9
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r11)
            r0.addView(r2, r9)
        L_0x0367:
            boolean r2 = r35.hasSpecialPrice$storyly_release()
            if (r2 == 0) goto L_0x0399
            java.lang.Boolean r2 = r7.f6054h
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r4)
            if (r2 == 0) goto L_0x0399
            androidx.appcompat.widget.AppCompatTextView r2 = r33.getOldPriceTextView()
            java.lang.Class[] r1 = new java.lang.Class[]{r1, r1}
            java.lang.reflect.Constructor r1 = r8.getConstructor(r1)
            java.lang.Integer r4 = java.lang.Integer.valueOf(r3)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            java.lang.Object[] r3 = new java.lang.Object[]{r4, r3}
            java.lang.Object r1 = r1.newInstance(r3)
            android.view.ViewGroup$LayoutParams r1 = (android.view.ViewGroup.LayoutParams) r1
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r11)
            r0.addView(r2, r1)
        L_0x0399:
            androidx.appcompat.widget.AppCompatTextView r0 = r33.getTitleTextView()
            com.appsamurai.storyly.config.StorylyConfig r1 = r33.getConfig()
            com.appsamurai.storyly.config.styling.story.StorylyStoryStyling r1 = r1.getStory$storyly_release()
            android.graphics.Typeface r1 = r1.getInteractiveTypeface$storyly_release()
            r0.setTypeface(r1)
            java.lang.String r1 = r35.getTitle()
            r0.setText(r1)
            java.lang.Integer r1 = r7.f6049c
            r2 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            if (r1 != 0) goto L_0x03bb
            r1 = r2
            goto L_0x03bf
        L_0x03bb:
            int r1 = r1.intValue()
        L_0x03bf:
            r0.setTextColor(r1)
            r1 = r18
            r0.setLineHeight(r1)
            r3 = r20
            float r3 = (float) r3
            r4 = 0
            r0.setTextSize(r4, r3)
            java.lang.String r4 = r35.getTitle()
            int r4 = r4.length()
            if (r4 != 0) goto L_0x03d9
            goto L_0x03e3
        L_0x03d9:
            java.lang.Boolean r4 = r7.f6055i
            java.lang.Boolean r8 = java.lang.Boolean.FALSE
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r8)
            if (r4 == 0) goto L_0x03e5
        L_0x03e3:
            r4 = r15
            goto L_0x03e6
        L_0x03e5:
            r4 = 0
        L_0x03e6:
            r0.setVisibility(r4)
            androidx.appcompat.widget.AppCompatButton r0 = r33.getActionButton()
            java.lang.String r4 = r7.f6052f
            if (r4 == 0) goto L_0x03f9
            int r4 = r4.length()
            if (r4 != 0) goto L_0x03f8
            goto L_0x03f9
        L_0x03f8:
            r15 = 0
        L_0x03f9:
            r0.setVisibility(r15)
            r4 = 0
            r0.setPadding(r12, r4, r12, r4)
            com.appsamurai.storyly.config.StorylyConfig r8 = r33.getConfig()
            com.appsamurai.storyly.config.styling.story.StorylyStoryStyling r8 = r8.getStory$storyly_release()
            android.graphics.Typeface r8 = r8.getInteractiveTypeface$storyly_release()
            r0.setTypeface(r8)
            java.lang.String r8 = r7.f6052f
            r0.setText(r8)
            r9 = r22
            float r8 = (float) r9
            r0.setTextSize(r4, r8)
            r4 = -1
            r0.setTextColor(r4)
            r4 = 1067450368(0x3fa00000, float:1.25)
            float r11 = r30 * r4
            int r4 = (int) r11
            r0.setMaxWidth(r4)
            r0.setGravity(r5)
            java.lang.Integer r4 = r7.f6050d
            if (r4 != 0) goto L_0x042f
            r4 = r2
            goto L_0x0433
        L_0x042f:
            int r4 = r4.intValue()
        L_0x0433:
            java.lang.Integer r5 = r7.f6050d
            if (r5 != 0) goto L_0x043a
            r5 = r2
        L_0x0438:
            r8 = 2
            goto L_0x043f
        L_0x043a:
            int r5 = r5.intValue()
            goto L_0x0438
        L_0x043f:
            int r8 = r17 / 2
            float r8 = (float) r8
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r9 = 0
            android.graphics.drawable.GradientDrawable r4 = com.appsamurai.storyly.util.ui.b.a(r0, r4, r8, r5, r9)
            r0.setBackground(r4)
            androidx.appcompat.widget.AppCompatTextView r0 = r33.getPriceTextView()
            com.appsamurai.storyly.config.StorylyConfig r4 = r33.getConfig()
            com.appsamurai.storyly.config.styling.story.StorylyStoryStyling r4 = r4.getStory$storyly_release()
            android.graphics.Typeface r4 = r4.getInteractiveTypeface$storyly_release()
            r0.setTypeface(r4)
            if (r24 != 0) goto L_0x0466
            r4 = r32
            goto L_0x0468
        L_0x0466:
            r4 = r24
        L_0x0468:
            r0.setText(r4)
            r0.setLineHeight(r1)
            java.lang.Integer r1 = r7.f6049c
            if (r1 != 0) goto L_0x0474
            r1 = r2
            goto L_0x0478
        L_0x0474:
            int r1 = r1.intValue()
        L_0x0478:
            r0.setTextColor(r1)
            r1 = 0
            r0.setTextSize(r1, r3)
            androidx.appcompat.widget.AppCompatTextView r0 = r33.getOldPriceTextView()
            com.appsamurai.storyly.config.StorylyConfig r1 = r33.getConfig()
            com.appsamurai.storyly.config.styling.story.StorylyStoryStyling r1 = r1.getStory$storyly_release()
            android.graphics.Typeface r1 = r1.getInteractiveTypeface$storyly_release()
            r0.setTypeface(r1)
            r1 = r32
            r0.setText(r1)
            r1 = r31
            r0.setLineHeight(r1)
            java.lang.Integer r1 = r7.f6051e
            if (r1 != 0) goto L_0x04a1
            goto L_0x04a5
        L_0x04a1:
            int r2 = r1.intValue()
        L_0x04a5:
            r0.setTextColor(r2)
            r9 = r28
            float r1 = (float) r9
            r2 = 0
            r0.setTextSize(r2, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.storylypresenter.storylylayer.storylyProductList.f.a(int, com.appsamurai.storyly.data.managers.product.STRProductItem, com.appsamurai.storyly.storylypresenter.storylylayer.storylyProductList.a):void");
    }

    @NotNull
    public final StorylyConfig getConfig() {
        return this.f6071a;
    }

    public final void a() {
        Glide.with((View) this).clear((View) getImageView());
        removeAllViews();
        getPriceContainer().removeAllViews();
        getImageContainer().removeAllViews();
    }
}
