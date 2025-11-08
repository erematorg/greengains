package com.appsamurai.storyly.storylypresenter.storylylayer;

import G0.p;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import com.appsamurai.storyly.R;
import com.appsamurai.storyly.StoryComponent;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.data.a0;
import com.appsamurai.storyly.data.b0;
import com.appsamurai.storyly.data.f0;
import com.appsamurai.storyly.data.managers.product.STRProductItem;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.math.MathKt;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.JsonObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressLint({"ViewConstructor"})
public final class s1 extends o1 {
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public final StorylyConfig f6027g;

    /* renamed from: h  reason: collision with root package name */
    public f0 f6028h;

    /* renamed from: i  reason: collision with root package name */
    public Function5<? super com.appsamurai.storyly.analytics.a, ? super b0, ? super StoryComponent, ? super JsonObject, ? super Function1<? super Boolean, Unit>, Unit> f6029i;

    /* renamed from: j  reason: collision with root package name */
    public Function1<? super b0, Unit> f6030j;

    /* renamed from: k  reason: collision with root package name */
    public Function0<Unit> f6031k;

    /* renamed from: l  reason: collision with root package name */
    public float f6032l;
    @NotNull

    /* renamed from: m  reason: collision with root package name */
    public final Lazy f6033m;
    @NotNull

    /* renamed from: n  reason: collision with root package name */
    public final Lazy f6034n;
    @NotNull

    /* renamed from: o  reason: collision with root package name */
    public final Lazy f6035o;
    @NotNull

    /* renamed from: p  reason: collision with root package name */
    public final Lazy f6036p;
    @NotNull

    /* renamed from: q  reason: collision with root package name */
    public final Lazy f6037q;
    @NotNull

    /* renamed from: r  reason: collision with root package name */
    public final Lazy f6038r;

    public static final class a extends Lambda implements Function0<AppCompatImageView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f6039a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(Context context) {
            super(0);
            this.f6039a = context;
        }

        public Object invoke() {
            AppCompatImageView appCompatImageView = new AppCompatImageView(this.f6039a);
            appCompatImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            appCompatImageView.setId(View.generateViewId());
            return appCompatImageView;
        }
    }

    public static final class b extends Lambda implements Function0<AppCompatTextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f6040a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public b(Context context) {
            super(0);
            this.f6040a = context;
        }

        public Object invoke() {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f6040a);
            appCompatTextView.setId(View.generateViewId());
            appCompatTextView.setMaxLines(1);
            appCompatTextView.setMinLines(1);
            appCompatTextView.setEllipsize(TextUtils.TruncateAt.END);
            appCompatTextView.setIncludeFontPadding(false);
            appCompatTextView.setTextAlignment(1);
            appCompatTextView.setLineSpacing(0.0f, 0.0f);
            appCompatTextView.setPaintFlags(appCompatTextView.getPaintFlags() | 16);
            appCompatTextView.setHorizontallyScrolling(false);
            return appCompatTextView;
        }
    }

    public static final class c extends Lambda implements Function0<ImageView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f6041a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public c(Context context) {
            super(0);
            this.f6041a = context;
        }

        public Object invoke() {
            ImageView imageView = new ImageView(this.f6041a);
            imageView.setId(View.generateViewId());
            return imageView;
        }
    }

    public static final class d extends Lambda implements Function0<AppCompatTextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f6042a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public d(Context context) {
            super(0);
            this.f6042a = context;
        }

        public Object invoke() {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f6042a);
            appCompatTextView.setId(View.generateViewId());
            appCompatTextView.setMaxLines(1);
            appCompatTextView.setMinLines(1);
            appCompatTextView.setEllipsize(TextUtils.TruncateAt.END);
            appCompatTextView.setTextAlignment(1);
            appCompatTextView.setIncludeFontPadding(false);
            appCompatTextView.setHorizontallyScrolling(false);
            return appCompatTextView;
        }
    }

    public static final class f implements RequestListener<Drawable> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ s1 f6045a;

        public f(s1 s1Var) {
            this.f6045a = s1Var;
        }

        public static final void a(s1 s1Var) {
            Intrinsics.checkNotNullParameter(s1Var, "this$0");
            s1Var.getOnLayerLoadFail$storyly_release().invoke();
        }

        public boolean onLoadFailed(@Nullable GlideException glideException, @Nullable Object obj, @Nullable Target<Drawable> target, boolean z2) {
            new Handler(Looper.getMainLooper()).post(new p(this.f6045a, 0));
            return false;
        }

        public boolean onResourceReady(Object obj, Object obj2, Target target, DataSource dataSource, boolean z2) {
            Drawable drawable = (Drawable) obj;
            this.f6045a.getOnLayerLoad$storyly_release().invoke();
            return false;
        }
    }

    public static final class g extends Lambda implements Function0<AppCompatTextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f6046a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public g(Context context) {
            super(0);
            this.f6046a = context;
        }

        public Object invoke() {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f6046a);
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
    public s1(@NotNull Context context, @NotNull StorylyConfig storylyConfig) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        this.f6027g = storylyConfig;
        this.f6033m = LazyKt.lazy(new e(context, this));
        this.f6034n = LazyKt.lazy(new a(context));
        this.f6035o = LazyKt.lazy(new g(context));
        this.f6036p = LazyKt.lazy(new b(context));
        this.f6037q = LazyKt.lazy(new d(context));
        this.f6038r = LazyKt.lazy(new c(context));
    }

    private final AppCompatImageView getImageView() {
        return (AppCompatImageView) this.f6034n.getValue();
    }

    private final AppCompatTextView getOldPriceTextView() {
        return (AppCompatTextView) this.f6036p.getValue();
    }

    private final ImageView getPointButton() {
        return (ImageView) this.f6038r.getValue();
    }

    private final AppCompatTextView getPriceTextView() {
        return (AppCompatTextView) this.f6037q.getValue();
    }

    private final RelativeLayout getProductCardView() {
        return (RelativeLayout) this.f6033m.getValue();
    }

    private final AppCompatTextView getTitleTextView() {
        return (AppCompatTextView) this.f6035o.getValue();
    }

    private final void setImageFromSource(String str) {
        Glide.with(getContext().getApplicationContext()).load(str).listener(new f(this)).preload();
    }

    public void a(@NotNull b0 b0Var) {
        Intrinsics.checkNotNullParameter(b0Var, "storylyLayerItem");
        a0 a0Var = b0Var.f3615j;
        f0 f0Var = null;
        f0 f0Var2 = a0Var instanceof f0 ? (f0) a0Var : null;
        if (f0Var2 != null) {
            this.f6028h = f0Var2;
            setStorylyLayerItem$storyly_release(b0Var);
            setRotation(b0Var.f3613h);
            f0 f0Var3 = this.f6028h;
            if (f0Var3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            } else {
                f0Var = f0Var3;
            }
            setImageFromSource(f0Var.f3691a);
        }
    }

    public void c() {
        super.c();
        removeAllViews();
        getProductCardView().removeAllViews();
    }

    @NotNull
    public final Function0<Unit> getOnImageReady$storyly_release() {
        Function0<Unit> function0 = this.f6031k;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onImageReady");
        return null;
    }

    @NotNull
    public final Function1<b0, Unit> getOnUserActionClick$storyly_release() {
        Function1<? super b0, Unit> function1 = this.f6030j;
        if (function1 != null) {
            return function1;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onUserActionClick");
        return null;
    }

    @NotNull
    public final Function5<com.appsamurai.storyly.analytics.a, b0, StoryComponent, JsonObject, Function1<? super Boolean, Unit>, Unit> getOnUserReaction$storyly_release() {
        Function5<? super com.appsamurai.storyly.analytics.a, ? super b0, ? super StoryComponent, ? super JsonObject, ? super Function1<? super Boolean, Unit>, Unit> function5 = this.f6029i;
        if (function5 != null) {
            return function5;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onUserReaction");
        return null;
    }

    public final void setOnImageReady$storyly_release(@NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.f6031k = function0;
    }

    public final void setOnUserActionClick$storyly_release(@NotNull Function1<? super b0, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.f6030j = function1;
    }

    public final void setOnUserReaction$storyly_release(@NotNull Function5<? super com.appsamurai.storyly.analytics.a, ? super b0, ? super StoryComponent, ? super JsonObject, ? super Function1<? super Boolean, Unit>, Unit> function5) {
        Intrinsics.checkNotNullParameter(function5, "<set-?>");
        this.f6029i = function5;
    }

    public static final class e extends Lambda implements Function0<RelativeLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f6043a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ s1 f6044b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public e(Context context, s1 s1Var) {
            super(0);
            this.f6043a = context;
            this.f6044b = s1Var;
        }

        @NotNull
        /* renamed from: a */
        public final RelativeLayout invoke() {
            RelativeLayout relativeLayout = new RelativeLayout(this.f6043a);
            relativeLayout.setOnClickListener(new E0.c(this.f6044b, 5));
            return relativeLayout;
        }

        public static final void a(s1 s1Var, View view) {
            Intrinsics.checkNotNullParameter(s1Var, "this$0");
            s1Var.getOnUserReaction$storyly_release().invoke(com.appsamurai.storyly.analytics.a.ProductCardClicked, s1Var.getStorylyLayerItem$storyly_release(), null, null, null);
            s1Var.getOnUserActionClick$storyly_release().invoke(s1Var.getStorylyLayerItem$storyly_release());
        }
    }

    public void a(@NotNull d dVar) {
        Integer num;
        float f2;
        int i3;
        RequestOptions requestOptions;
        Intrinsics.checkNotNullParameter(dVar, "safeFrame");
        float b3 = dVar.b();
        float a2 = dVar.a();
        int roundToInt = MathKt.roundToInt((((double) getStorylyLayerItem$storyly_release().f3609d) / 100.0d) * ((double) b3));
        int roundToInt2 = MathKt.roundToInt((((double) getStorylyLayerItem$storyly_release().f3610e) / 100.0d) * ((double) a2));
        float f3 = (float) roundToInt;
        f0 f0Var = this.f6028h;
        if (f0Var == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            f0Var = null;
        }
        float f4 = (float) 100;
        float f5 = (f0Var.f3692b / f4) * f3;
        int i4 = (int) (0.01f * f3);
        float f6 = (float) ((int) (0.14f * f3));
        int i5 = (int) (0.071f * f3);
        int i6 = roundToInt - (i5 * 2);
        float f7 = (float) i6;
        float f8 = f7 * 0.0104f;
        f0 f0Var2 = this.f6028h;
        if (f0Var2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            f0Var2 = null;
        }
        this.f6032l = ((f0Var2.f3692b - ((float) 1)) / f4) * f7;
        f0 f0Var3 = this.f6028h;
        if (f0Var3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            f0Var3 = null;
        }
        float f9 = (f0Var3.f3692b / f4) * f7;
        int i7 = (int) (0.08f * f3);
        float f10 = f6 * 0.85f;
        int i8 = (int) (((float) roundToInt2) * 0.025f);
        int i9 = (int) (((float) 2) * f6);
        float f11 = 0.85f * f10;
        float f12 = f10;
        int i10 = (int) (f6 * 0.7f);
        int i11 = (int) f6;
        int i12 = (int) (f3 * 0.211f);
        int i13 = (int) (f3 * 0.55f);
        f0 f0Var4 = this.f6028h;
        if (f0Var4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            f0Var4 = null;
        }
        int i14 = i7;
        float f13 = f0Var4.f3692b > 0.0f ? ((float) i12) * 0.3f : 0.0f;
        AppCompatImageView imageView = getImageView();
        float f14 = f13;
        f0 f0Var5 = this.f6028h;
        if (f0Var5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            f0Var5 = null;
        }
        com.appsamurai.storyly.data.c cVar = f0Var5.f3709s;
        if (cVar == null) {
            num = null;
        } else {
            num = Integer.valueOf(cVar.f3624a);
        }
        int i15 = (int) f8;
        int i16 = i12;
        f0 f0Var6 = this.f6028h;
        if (f0Var6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            f0Var6 = null;
        }
        com.appsamurai.storyly.data.c cVar2 = f0Var6.f3710t;
        if (cVar2 == null) {
            cVar2 = com.appsamurai.storyly.config.styling.a.COLOR_EEEEEE.b();
        }
        imageView.setBackground(a(num, i15, Integer.valueOf(cVar2.f3624a), f9));
        f0 f0Var7 = this.f6028h;
        if (f0Var7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            f0Var7 = null;
        }
        String str = f0Var7.f3691a;
        if (this.f6032l > 0.0f) {
            i3 = i11;
            f2 = f11;
            requestOptions = (RequestOptions) new RequestOptions().transform((Transformation<Bitmap>[]) new Transformation[]{new CenterCrop(), new RoundedCorners((int) this.f6032l)});
        } else {
            i3 = i11;
            f2 = f11;
            requestOptions = (RequestOptions) new RequestOptions().transform((Transformation<Bitmap>) new CenterCrop());
        }
        Intrinsics.checkNotNullExpressionValue(requestOptions, "if (cornerRadius > 0) {\n…m(CenterCrop())\n        }");
        ((RequestBuilder) Glide.with(getContext().getApplicationContext()).load(str).diskCacheStrategy(DiskCacheStrategy.ALL)).listener(new t1(this)).apply((BaseRequestOptions<?>) requestOptions).into((ImageView) getImageView());
        getImageView().setPadding(i15, i15, i15, i15);
        int i17 = roundToInt;
        int i18 = i13;
        float f15 = f12;
        float f16 = f14;
        int i19 = i9;
        int i20 = i10;
        int i21 = i16;
        int i22 = i5;
        int i23 = i3;
        int i24 = i6;
        float f17 = f2;
        setLayoutParams(a(new FrameLayout.LayoutParams(roundToInt, roundToInt2), getStorylyLayerItem$storyly_release().b().x, getStorylyLayerItem$storyly_release().b().y, dVar.c(), dVar.d()));
        RelativeLayout productCardView = getProductCardView();
        f0 f0Var8 = this.f6028h;
        if (f0Var8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            f0Var8 = null;
        }
        com.appsamurai.storyly.data.c cVar3 = f0Var8.f3709s;
        if (cVar3 == null && (cVar3 = f0Var8.f3708r) == null) {
            cVar3 = new com.appsamurai.storyly.data.c(-1);
        }
        Integer valueOf = Integer.valueOf(cVar3.f3624a);
        f0 f0Var9 = this.f6028h;
        if (f0Var9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            f0Var9 = null;
        }
        com.appsamurai.storyly.data.c cVar4 = f0Var9.f3710t;
        if (cVar4 == null) {
            cVar4 = com.appsamurai.storyly.config.styling.a.COLOR_EEEEEE.b();
        }
        productCardView.setBackground(a(valueOf, i4, Integer.valueOf(cVar4.f3624a), f5));
        Unit unit = Unit.INSTANCE;
        f0 f0Var10 = this.f6028h;
        if (f0Var10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            f0Var10 = null;
        }
        f0 f0Var11 = this.f6028h;
        if (f0Var11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            f0Var11 = null;
        }
        STRProductItem b4 = f0Var11.b();
        f0 f0Var12 = this.f6028h;
        if (f0Var12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            f0Var12 = null;
        }
        String a3 = f0Var10.a(b4, f0Var12.f3693c);
        AppCompatTextView titleTextView = getTitleTextView();
        titleTextView.setVisibility(StringsKt.isBlank(a3) ? 8 : 0);
        titleTextView.setLineHeight(i23);
        titleTextView.setTextSize(0, f15);
        titleTextView.setText(a3);
        f0 f0Var13 = this.f6028h;
        if (f0Var13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            f0Var13 = null;
        }
        com.appsamurai.storyly.data.c cVar5 = f0Var13.f3694d;
        if (cVar5 == null && (cVar5 = f0Var13.f3707q) == null) {
            cVar5 = com.appsamurai.storyly.config.styling.a.COLOR_212121.b();
        }
        titleTextView.setTextColor(cVar5.f3624a);
        titleTextView.setTypeface(this.f6027g.getStory$storyly_release().getInteractiveTypeface$storyly_release());
        titleTextView.setPadding(0, 0, 0, 0);
        titleTextView.setLineSpacing(0.0f, 1.0f);
        f0 f0Var14 = this.f6028h;
        if (f0Var14 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            f0Var14 = null;
        }
        f0 f0Var15 = this.f6028h;
        if (f0Var15 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            f0Var15 = null;
        }
        STRProductItem b5 = f0Var15.b();
        com.appsamurai.storyly.util.formatter.b priceFormatter$storyly_release = this.f6027g.getProduct$storyly_release().getPriceFormatter$storyly_release();
        f0 f0Var16 = this.f6028h;
        if (f0Var16 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            f0Var16 = null;
        }
        String a4 = f0Var14.a(b5, priceFormatter$storyly_release, f0Var16.f3701k);
        AppCompatTextView oldPriceTextView = getOldPriceTextView();
        oldPriceTextView.setVisibility(StringsKt.isBlank(a4) ? 8 : 0);
        oldPriceTextView.setText(a4);
        oldPriceTextView.setLineHeight(i20);
        oldPriceTextView.setTextSize(0, f17);
        f0 f0Var17 = this.f6028h;
        if (f0Var17 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            f0Var17 = null;
        }
        com.appsamurai.storyly.data.c cVar6 = f0Var17.f3704n;
        if (cVar6 == null) {
            cVar6 = com.appsamurai.storyly.config.styling.a.COLOR_757575.b();
        }
        oldPriceTextView.setTextColor(cVar6.f3624a);
        oldPriceTextView.setTypeface(this.f6027g.getStory$storyly_release().getInteractiveTypeface$storyly_release());
        f0 f0Var18 = this.f6028h;
        if (f0Var18 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            f0Var18 = null;
        }
        boolean z2 = f0Var18.f3702l;
        f0 f0Var19 = this.f6028h;
        if (f0Var19 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            f0Var19 = null;
        }
        com.appsamurai.storyly.util.c.a(oldPriceTextView, z2, f0Var19.f3703m);
        oldPriceTextView.setPadding(0, 0, 0, 0);
        oldPriceTextView.setLineSpacing(0.0f, 1.0f);
        f0 f0Var20 = this.f6028h;
        if (f0Var20 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            f0Var20 = null;
        }
        f0 f0Var21 = this.f6028h;
        if (f0Var21 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            f0Var21 = null;
        }
        STRProductItem b6 = f0Var21.b();
        com.appsamurai.storyly.util.formatter.b priceFormatter$storyly_release2 = this.f6027g.getProduct$storyly_release().getPriceFormatter$storyly_release();
        f0 f0Var22 = this.f6028h;
        if (f0Var22 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            f0Var22 = null;
        }
        String b7 = f0Var20.b(b6, priceFormatter$storyly_release2, f0Var22.f3697g);
        AppCompatTextView priceTextView = getPriceTextView();
        priceTextView.setVisibility(StringsKt.isBlank(b7) ? 4 : 0);
        priceTextView.setGravity(16);
        priceTextView.setLineHeight(i23);
        priceTextView.setText(b7);
        priceTextView.setTextSize(0, f15);
        f0 f0Var23 = this.f6028h;
        if (f0Var23 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            f0Var23 = null;
        }
        com.appsamurai.storyly.data.c cVar7 = f0Var23.f3700j;
        if (cVar7 == null && (cVar7 = f0Var23.f3707q) == null) {
            cVar7 = com.appsamurai.storyly.config.styling.a.COLOR_212121.b();
        }
        priceTextView.setTextColor(cVar7.f3624a);
        priceTextView.setTypeface(this.f6027g.getStory$storyly_release().getInteractiveTypeface$storyly_release());
        f0 f0Var24 = this.f6028h;
        if (f0Var24 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            f0Var24 = null;
        }
        boolean z3 = f0Var24.f3698h;
        f0 f0Var25 = this.f6028h;
        if (f0Var25 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            f0Var25 = null;
        }
        com.appsamurai.storyly.util.c.a(priceTextView, z3, f0Var25.f3699i);
        priceTextView.setPadding(0, 0, 0, 0);
        priceTextView.setLineSpacing(0.0f, 1.0f);
        ImageView pointButton = getPointButton();
        int i25 = (int) (((float) i23) * 0.3f);
        pointButton.setPadding(i25, 0, i25, 0);
        Context context = pointButton.getContext();
        f0 f0Var26 = this.f6028h;
        if (f0Var26 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            f0Var26 = null;
        }
        f0Var26.getClass();
        pointButton.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.st_right_arrow_icon));
        f0 f0Var27 = this.f6028h;
        if (f0Var27 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            f0Var27 = null;
        }
        com.appsamurai.storyly.data.c cVar8 = f0Var27.f3706p;
        if (cVar8 == null) {
            cVar8 = com.appsamurai.storyly.config.styling.a.COLOR_F5F5F5.b();
        }
        pointButton.setBackground(a(Integer.valueOf(cVar8.f3624a), 0, (Integer) null, f16));
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        int i26 = i24;
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(i26, i26);
        int i27 = i22;
        layoutParams2.setMargins(i27, i27, i27, 0);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(i17, i19);
        layoutParams3.addRule(3, getImageView().getId());
        layoutParams3.addRule(20);
        int i28 = i14;
        layoutParams3.setMarginStart(i28);
        layoutParams3.topMargin = i8;
        layoutParams3.setMarginEnd(i28);
        int i29 = i18;
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(i29, i20);
        layoutParams4.addRule(2, getPriceTextView().getId());
        layoutParams4.addRule(20);
        layoutParams4.setMarginStart(i28);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(i29, i23);
        layoutParams5.addRule(8, getPointButton().getId());
        layoutParams5.addRule(6, getPointButton().getId());
        layoutParams5.addRule(20);
        layoutParams5.setMarginStart(i28);
        int i30 = i21;
        RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(i30, i30);
        layoutParams6.addRule(21);
        layoutParams6.addRule(12);
        layoutParams6.setMarginEnd(i28);
        layoutParams6.bottomMargin = i28;
        addView(getProductCardView(), layoutParams);
        getProductCardView().addView(getImageView(), layoutParams2);
        getProductCardView().addView(getTitleTextView(), layoutParams3);
        getProductCardView().addView(getPriceTextView(), layoutParams5);
        getProductCardView().addView(getOldPriceTextView(), layoutParams4);
        getProductCardView().addView(getPointButton(), layoutParams6);
    }

    public final GradientDrawable a(Integer num, int i3, Integer num2, float f2) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.st_rectangle_shape_drawable);
        if (drawable != null) {
            GradientDrawable gradientDrawable = (GradientDrawable) ((GradientDrawable) drawable).mutate();
            if (num != null) {
                gradientDrawable.setColor(num.intValue());
            }
            if (num2 != null) {
                gradientDrawable.setStroke(i3, num2.intValue());
            }
            gradientDrawable.setCornerRadius(f2);
            return gradientDrawable;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.graphics.drawable.GradientDrawable");
    }
}
