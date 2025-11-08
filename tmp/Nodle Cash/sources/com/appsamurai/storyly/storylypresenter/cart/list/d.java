package com.appsamurai.storyly.storylypresenter.cart.list;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import com.appsamurai.storyly.R;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.data.managers.product.STRCartItem;
import com.appsamurai.storyly.data.managers.product.STRProductItem;
import com.appsamurai.storyly.data.managers.product.STRProductVariant;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.properties.Delegates;
import kotlin.properties.ObservableProperty;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressLint({"ViewConstructor"})
public final class d extends RelativeLayout {

    /* renamed from: r  reason: collision with root package name */
    public static final /* synthetic */ KProperty<Object>[] f4802r = {androidx.compose.ui.autofill.a.m(d.class, FirebaseAnalytics.Param.QUANTITY, "getQuantity()I", 0)};
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final StorylyConfig f4803a;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    public Function3<? super STRCartItem, ? super Integer, ? super Function0<Unit>, Unit> f4804b;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    public STRCartItem f4805c;
    @Nullable

    /* renamed from: d  reason: collision with root package name */
    public Target<?> f4806d;
    @NotNull

    /* renamed from: e  reason: collision with root package name */
    public final ReadWriteProperty f4807e = new n(1, 1, this);
    @NotNull

    /* renamed from: f  reason: collision with root package name */
    public final Lazy f4808f;
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public final Lazy f4809g;
    @NotNull

    /* renamed from: h  reason: collision with root package name */
    public final Lazy f4810h;
    @NotNull

    /* renamed from: i  reason: collision with root package name */
    public final Lazy f4811i;
    @NotNull

    /* renamed from: j  reason: collision with root package name */
    public final Lazy f4812j;
    @NotNull

    /* renamed from: k  reason: collision with root package name */
    public final Lazy f4813k;
    @NotNull

    /* renamed from: l  reason: collision with root package name */
    public final Lazy f4814l;
    @NotNull

    /* renamed from: m  reason: collision with root package name */
    public final Lazy f4815m;
    @NotNull

    /* renamed from: n  reason: collision with root package name */
    public final Lazy f4816n;
    @NotNull

    /* renamed from: o  reason: collision with root package name */
    public final Lazy f4817o;
    @NotNull

    /* renamed from: p  reason: collision with root package name */
    public final Lazy f4818p;
    @NotNull

    /* renamed from: q  reason: collision with root package name */
    public final Lazy f4819q;

    public /* synthetic */ class a {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[com.appsamurai.storyly.data.managers.product.c.values().length];
            iArr[2] = 1;
            iArr[0] = 2;
            iArr[1] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final class c extends Lambda implements Function1<Drawable, Unit> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ int f4823a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ List<STRProductVariant> f4824b;

        /* renamed from: c  reason: collision with root package name */
        public final /* synthetic */ SpannableStringBuilder f4825c;

        /* renamed from: d  reason: collision with root package name */
        public final /* synthetic */ d f4826d;

        /* renamed from: e  reason: collision with root package name */
        public final /* synthetic */ float f4827e;

        /* renamed from: f  reason: collision with root package name */
        public final /* synthetic */ Function1<SpannableStringBuilder, Unit> f4828f;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public c(int i3, List<STRProductVariant> list, SpannableStringBuilder spannableStringBuilder, d dVar, float f2, Function1<? super SpannableStringBuilder, Unit> function1) {
            super(1);
            this.f4823a = i3;
            this.f4824b = list;
            this.f4825c = spannableStringBuilder;
            this.f4826d = dVar;
            this.f4827e = f2;
            this.f4828f = function1;
        }

        public Object invoke(Object obj) {
            Drawable drawable = (Drawable) obj;
            if (drawable != null) {
                d dVar = this.f4826d;
                int i3 = this.f4823a;
                dVar.a(Intrinsics.stringPlus("PLACE_HOLDER", Integer.valueOf(i3)), this.f4825c, this.f4827e, drawable);
            }
            if (this.f4823a != CollectionsKt.getLastIndex(this.f4824b)) {
                this.f4825c.append(", ");
            }
            this.f4826d.a(this.f4823a + 1, this.f4825c, this.f4827e, this.f4824b, this.f4828f);
            return Unit.INSTANCE;
        }
    }

    /* renamed from: com.appsamurai.storyly.storylypresenter.cart.list.d$d  reason: collision with other inner class name */
    public static final class C0019d extends Lambda implements Function0<View> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f4829a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public C0019d(Context context) {
            super(0);
            this.f4829a = context;
        }

        public Object invoke() {
            return new View(this.f4829a);
        }
    }

    public static final class e extends Lambda implements Function0<FrameLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f4830a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public e(Context context) {
            super(0);
            this.f4830a = context;
        }

        public Object invoke() {
            FrameLayout frameLayout = new FrameLayout(this.f4830a);
            frameLayout.setId(View.generateViewId());
            return frameLayout;
        }
    }

    public static final class f extends Lambda implements Function0<ImageView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f4831a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public f(Context context) {
            super(0);
            this.f4831a = context;
        }

        public Object invoke() {
            ImageView imageView = new ImageView(this.f4831a);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;
        }
    }

    public static final class h extends Lambda implements Function0<LinearLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f4835a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public h(Context context) {
            super(0);
            this.f4835a = context;
        }

        public Object invoke() {
            LinearLayout linearLayout = new LinearLayout(this.f4835a);
            linearLayout.setId(View.generateViewId());
            linearLayout.setGravity(17);
            linearLayout.setOrientation(0);
            return linearLayout;
        }
    }

    public static final class i extends Lambda implements Function0<AppCompatTextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f4836a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ d f4837b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public i(Context context, d dVar) {
            super(0);
            this.f4836a = context;
            this.f4837b = dVar;
        }

        public Object invoke() {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f4836a);
            d dVar = this.f4837b;
            appCompatTextView.setSingleLine(true);
            appCompatTextView.setText(String.valueOf(dVar.getQuantity()));
            appCompatTextView.setEllipsize(TextUtils.TruncateAt.END);
            appCompatTextView.setGravity(GravityCompat.END);
            appCompatTextView.setIncludeFontPadding(false);
            appCompatTextView.setHorizontallyScrolling(false);
            appCompatTextView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            return appCompatTextView;
        }
    }

    public static final class j extends Lambda implements Function0<AppCompatTextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f4838a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public j(Context context) {
            super(0);
            this.f4838a = context;
        }

        public Object invoke() {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f4838a);
            appCompatTextView.setId(View.generateViewId());
            appCompatTextView.setSingleLine(true);
            appCompatTextView.setEllipsize(TextUtils.TruncateAt.END);
            appCompatTextView.setIncludeFontPadding(false);
            appCompatTextView.setLineSpacing(0.0f, 0.0f);
            appCompatTextView.setPaintFlags(appCompatTextView.getPaintFlags() | 16);
            appCompatTextView.setTextColor(Color.parseColor("#757575"));
            appCompatTextView.setHorizontallyScrolling(false);
            return appCompatTextView;
        }
    }

    public static final class k extends Lambda implements Function0<LinearLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f4839a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public k(Context context) {
            super(0);
            this.f4839a = context;
        }

        public Object invoke() {
            LinearLayout linearLayout = new LinearLayout(this.f4839a);
            linearLayout.setId(View.generateViewId());
            linearLayout.setOrientation(1);
            linearLayout.setGravity(16);
            return linearLayout;
        }
    }

    public static final class l extends Lambda implements Function0<AppCompatTextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f4840a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public l(Context context) {
            super(0);
            this.f4840a = context;
        }

        public Object invoke() {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f4840a);
            appCompatTextView.setId(View.generateViewId());
            appCompatTextView.setSingleLine(true);
            appCompatTextView.setEllipsize(TextUtils.TruncateAt.END);
            appCompatTextView.setIncludeFontPadding(false);
            appCompatTextView.setTextColor(Color.parseColor("#212121"));
            appCompatTextView.setHorizontallyScrolling(false);
            return appCompatTextView;
        }
    }

    public static final class m extends Lambda implements Function1<SpannableStringBuilder, Unit> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ AppCompatTextView f4841a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public m(AppCompatTextView appCompatTextView) {
            super(1);
            this.f4841a = appCompatTextView;
        }

        public Object invoke(Object obj) {
            SpannableStringBuilder spannableStringBuilder = (SpannableStringBuilder) obj;
            Intrinsics.checkNotNullParameter(spannableStringBuilder, "it");
            this.f4841a.setText(spannableStringBuilder);
            return Unit.INSTANCE;
        }
    }

    public static final class n extends ObservableProperty<Integer> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Object f4842a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ d f4843b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public n(Object obj, Object obj2, d dVar) {
            super(obj2);
            this.f4842a = obj;
            this.f4843b = dVar;
        }

        public void afterChange(@NotNull KProperty<?> kProperty, Integer num, Integer num2) {
            Intrinsics.checkNotNullParameter(kProperty, "property");
            int intValue = num2.intValue();
            num.intValue();
            this.f4843b.getIndicatorLabel().setText(String.valueOf(intValue));
            this.f4843b.getDecreaseIcon().setEnabled(intValue > 0);
            this.f4843b.getDecreaseIcon().setAlpha(intValue > 0 ? 1.0f : 0.3f);
        }
    }

    public static final class o extends Lambda implements Function0<AppCompatTextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f4844a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public o(Context context) {
            super(0);
            this.f4844a = context;
        }

        public Object invoke() {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f4844a);
            appCompatTextView.setId(View.generateViewId());
            appCompatTextView.setEllipsize(TextUtils.TruncateAt.END);
            appCompatTextView.setSingleLine(true);
            appCompatTextView.setIncludeFontPadding(false);
            appCompatTextView.setHorizontallyScrolling(false);
            appCompatTextView.setTextColor(Color.parseColor("#212121"));
            appCompatTextView.setTextAlignment(5);
            return appCompatTextView;
        }
    }

    public static final class p extends Lambda implements Function0<AppCompatTextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f4845a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public p(Context context) {
            super(0);
            this.f4845a = context;
        }

        public Object invoke() {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f4845a);
            appCompatTextView.setId(View.generateViewId());
            appCompatTextView.setEllipsize(TextUtils.TruncateAt.END);
            appCompatTextView.setSingleLine(true);
            appCompatTextView.setIncludeFontPadding(false);
            appCompatTextView.setTextColor(Color.parseColor("#616161"));
            appCompatTextView.setHorizontallyScrolling(false);
            appCompatTextView.setTextAlignment(5);
            return appCompatTextView;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public d(@NotNull Context context, @NotNull StorylyConfig storylyConfig) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        this.f4803a = storylyConfig;
        Delegates delegates = Delegates.INSTANCE;
        this.f4808f = LazyKt.lazy(new e(context));
        this.f4809g = LazyKt.lazy(new f(context));
        this.f4810h = LazyKt.lazy(new C0019d(context));
        this.f4811i = LazyKt.lazy(new o(context));
        this.f4812j = LazyKt.lazy(new p(context));
        this.f4813k = LazyKt.lazy(new h(context));
        this.f4814l = LazyKt.lazy(new b(context, this));
        this.f4815m = LazyKt.lazy(new g(context, this));
        this.f4816n = LazyKt.lazy(new i(context, this));
        this.f4817o = LazyKt.lazy(new k(context));
        this.f4818p = LazyKt.lazy(new j(context));
        this.f4819q = LazyKt.lazy(new l(context));
        setId(View.generateViewId());
    }

    /* access modifiers changed from: private */
    public final AppCompatImageView getDecreaseIcon() {
        return (AppCompatImageView) this.f4814l.getValue();
    }

    private final View getImageBorder() {
        return (View) this.f4810h.getValue();
    }

    private final FrameLayout getImageContainer() {
        return (FrameLayout) this.f4808f.getValue();
    }

    private final ImageView getImageView() {
        return (ImageView) this.f4809g.getValue();
    }

    private final AppCompatImageView getIncreaseIcon() {
        return (AppCompatImageView) this.f4815m.getValue();
    }

    private final LinearLayout getIndicatorContainer() {
        return (LinearLayout) this.f4813k.getValue();
    }

    /* access modifiers changed from: private */
    public final AppCompatTextView getIndicatorLabel() {
        return (AppCompatTextView) this.f4816n.getValue();
    }

    private final AppCompatTextView getOldPriceTextView() {
        return (AppCompatTextView) this.f4818p.getValue();
    }

    private final LinearLayout getPriceContainer() {
        return (LinearLayout) this.f4817o.getValue();
    }

    private final AppCompatTextView getPriceTextView() {
        return (AppCompatTextView) this.f4819q.getValue();
    }

    /* access modifiers changed from: private */
    public final int getQuantity() {
        return ((Number) this.f4807e.getValue(this, f4802r[0])).intValue();
    }

    private final AppCompatTextView getTitleTextView() {
        return (AppCompatTextView) this.f4811i.getValue();
    }

    private final AppCompatTextView getVariantTextView() {
        return (AppCompatTextView) this.f4812j.getValue();
    }

    private final void setQuantity(int i3) {
        this.f4807e.setValue(this, f4802r[0], Integer.valueOf(i3));
    }

    @NotNull
    public final StorylyConfig getConfig() {
        return this.f4803a;
    }

    @Nullable
    public final Function3<STRCartItem, Integer, Function0<Unit>, Unit> getOnUpdateCart$storyly_release() {
        return this.f4804b;
    }

    public final void setOnUpdateCart$storyly_release(@Nullable Function3<? super STRCartItem, ? super Integer, ? super Function0<Unit>, Unit> function3) {
        this.f4804b = function3;
    }

    public final void setupView$storyly_release(@NotNull STRCartItem sTRCartItem) {
        String str;
        String str2;
        STRProductItem item;
        STRProductItem item2;
        STRCartItem sTRCartItem2 = sTRCartItem;
        Intrinsics.checkNotNullParameter(sTRCartItem2, "cartItem");
        this.f4805c = sTRCartItem2;
        double height = ((double) com.appsamurai.storyly.util.m.b().height()) * 0.189d;
        double d2 = 0.132d * height;
        int i3 = (int) (0.8d * height);
        int i4 = (int) (0.082d * height);
        double d3 = 0.1d * height;
        int i5 = (int) d3;
        int i6 = (int) (height * 0.33d);
        int i7 = (int) (0.01d * height);
        int i8 = (int) (height * 0.198d);
        double d4 = d3;
        int i9 = (int) (height * 0.066d);
        int i10 = (int) d2;
        float f2 = (float) (height * 0.115d);
        int i11 = (int) (height * 0.033d);
        FrameLayout imageContainer = getImageContainer();
        Class cls = Integer.TYPE;
        float f3 = (float) d4;
        Class<RelativeLayout.LayoutParams> cls2 = RelativeLayout.LayoutParams.class;
        float f4 = (float) (height * 0.107d);
        float f5 = f2;
        ViewGroup.LayoutParams newInstance = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{Integer.valueOf(i3), Integer.valueOf(i3)});
        Intrinsics.checkNotNullExpressionValue(newInstance, "layoutParams");
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) newInstance;
        double d5 = d2;
        layoutParams.addRule(15);
        layoutParams.addRule(18, getId());
        layoutParams.setMarginStart(i5);
        layoutParams.setMarginEnd(i5);
        Unit unit = Unit.INSTANCE;
        addView(imageContainer, newInstance);
        FrameLayout imageContainer2 = getImageContainer();
        ImageView imageView = getImageView();
        ViewGroup.LayoutParams newInstance2 = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{Integer.valueOf(i3), Integer.valueOf(i3)});
        Intrinsics.checkNotNullExpressionValue(newInstance2, "layoutParams");
        imageContainer2.addView(imageView, newInstance2);
        View imageBorder = getImageBorder();
        ViewGroup.LayoutParams newInstance3 = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{Integer.valueOf(i3), Integer.valueOf(i3)});
        Intrinsics.checkNotNullExpressionValue(newInstance3, "layoutParams");
        imageContainer2.addView(imageBorder, newInstance3);
        AppCompatTextView titleTextView = getTitleTextView();
        ViewGroup.LayoutParams newInstance4 = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-2, -2});
        Intrinsics.checkNotNullExpressionValue(newInstance4, "layoutParams");
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) newInstance4;
        layoutParams2.addRule(10);
        layoutParams2.addRule(17, getImageContainer().getId());
        layoutParams2.addRule(21);
        layoutParams2.topMargin = i10;
        addView(titleTextView, newInstance4);
        AppCompatTextView variantTextView = getVariantTextView();
        ViewGroup.LayoutParams newInstance5 = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-2, -2});
        Intrinsics.checkNotNullExpressionValue(newInstance5, "layoutParams");
        RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) newInstance5;
        layoutParams3.addRule(3, getTitleTextView().getId());
        layoutParams3.addRule(17, getImageContainer().getId());
        layoutParams3.addRule(21);
        layoutParams3.topMargin = i11;
        addView(variantTextView, newInstance5);
        LinearLayout indicatorContainer = getIndicatorContainer();
        ViewGroup.LayoutParams newInstance6 = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-2, Integer.valueOf(i6)});
        Intrinsics.checkNotNullExpressionValue(newInstance6, "layoutParams");
        RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) newInstance6;
        layoutParams4.addRule(21);
        layoutParams4.addRule(12);
        layoutParams4.bottomMargin = i5;
        layoutParams4.rightMargin = i5;
        addView(indicatorContainer, newInstance6);
        LinearLayout indicatorContainer2 = getIndicatorContainer();
        AppCompatImageView decreaseIcon = getDecreaseIcon();
        Class<ConstraintLayout.LayoutParams> cls3 = ConstraintLayout.LayoutParams.class;
        ViewGroup.LayoutParams newInstance7 = cls3.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{Integer.valueOf(i8), Integer.valueOf(i8)});
        Intrinsics.checkNotNullExpressionValue(newInstance7, "layoutParams");
        ConstraintLayout.LayoutParams layoutParams5 = (ConstraintLayout.LayoutParams) newInstance7;
        layoutParams5.setMarginStart(i9);
        layoutParams5.setMarginEnd(i9);
        indicatorContainer2.addView(decreaseIcon, newInstance7);
        indicatorContainer2.addView(getIndicatorLabel());
        AppCompatImageView increaseIcon = getIncreaseIcon();
        ViewGroup.LayoutParams newInstance8 = cls3.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{Integer.valueOf(i8), Integer.valueOf(i8)});
        Intrinsics.checkNotNullExpressionValue(newInstance8, "layoutParams");
        ConstraintLayout.LayoutParams layoutParams6 = (ConstraintLayout.LayoutParams) newInstance8;
        layoutParams6.setMarginStart(i9);
        layoutParams6.setMarginEnd(i9);
        indicatorContainer2.addView(increaseIcon, newInstance8);
        LinearLayout priceContainer = getPriceContainer();
        ViewGroup.LayoutParams newInstance9 = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-2, -2});
        Intrinsics.checkNotNullExpressionValue(newInstance9, "layoutParams");
        RelativeLayout.LayoutParams layoutParams7 = (RelativeLayout.LayoutParams) newInstance9;
        layoutParams7.addRule(16, getIndicatorContainer().getId());
        layoutParams7.addRule(17, getImageContainer().getId());
        layoutParams7.addRule(6, getIndicatorContainer().getId());
        layoutParams7.addRule(8, getIndicatorContainer().getId());
        addView(priceContainer, newInstance9);
        LinearLayout priceContainer2 = getPriceContainer();
        AppCompatTextView oldPriceTextView = getOldPriceTextView();
        ViewGroup.LayoutParams newInstance10 = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-2, -2});
        Intrinsics.checkNotNullExpressionValue(newInstance10, "layoutParams");
        ((RelativeLayout.LayoutParams) newInstance10).bottomMargin = i11;
        priceContainer2.addView(oldPriceTextView, newInstance10);
        AppCompatTextView priceTextView = getPriceTextView();
        ViewGroup.LayoutParams newInstance11 = ViewGroup.LayoutParams.class.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-2, -2});
        Intrinsics.checkNotNullExpressionValue(newInstance11, "layoutParams");
        priceContainer2.addView(priceTextView, newInstance11);
        setQuantity(sTRCartItem.getQuantity());
        List<String> imageUrls = sTRCartItem.getItem().getImageUrls();
        Float f6 = null;
        String str3 = imageUrls == null ? null : (String) CollectionsKt.firstOrNull(imageUrls);
        RequestOptions requestOptions = i4 > 0 ? (RequestOptions) new RequestOptions().transform((Transformation<Bitmap>[]) new Transformation[]{new CenterCrop(), new RoundedCorners(i4)}) : (RequestOptions) new RequestOptions().transform((Transformation<Bitmap>) new CenterCrop());
        Intrinsics.checkNotNullExpressionValue(requestOptions, "if (cornerRadius > 0) {\n…m(CenterCrop())\n        }");
        ((RequestBuilder) Glide.with(getContext()).load(str3).diskCacheStrategy(DiskCacheStrategy.ALL)).apply((BaseRequestOptions<?>) requestOptions).into(getImageView());
        int i12 = i7;
        setBackground(com.appsamurai.storyly.util.ui.b.a(this, -1, (float) d5, Integer.valueOf(Color.parseColor("#EEEEEE")), i12));
        getImageBorder().setBackground(com.appsamurai.storyly.util.ui.b.a(this, 0, (float) i4, Integer.valueOf(Color.parseColor("#EEEEEE")), i12, 1));
        getIndicatorContainer().setBackground(com.appsamurai.storyly.util.ui.b.a(this, -1, (float) (i6 / 2), Integer.valueOf(Color.parseColor("#E0E0E0")), i12));
        AppCompatTextView titleTextView2 = getTitleTextView();
        titleTextView2.setText(sTRCartItem.getItem().getTitle());
        titleTextView2.setTypeface(getConfig().getStory$storyly_release().getInteractiveTypeface$storyly_release());
        float f7 = f5;
        titleTextView2.setTextSize(0, f7);
        AppCompatTextView variantTextView2 = getVariantTextView();
        a(0, new SpannableStringBuilder(), f4, sTRCartItem.getItem().getVariants(), new m(variantTextView2));
        variantTextView2.setTypeface(getConfig().getStory$storyly_release().getInteractiveTypeface$storyly_release());
        variantTextView2.setTextSize(0, f4);
        AppCompatTextView indicatorLabel = getIndicatorLabel();
        indicatorLabel.setText(String.valueOf(getQuantity()));
        indicatorLabel.setTypeface(getConfig().getStory$storyly_release().getInteractiveTypeface$storyly_release());
        indicatorLabel.setTextSize(0, f7);
        com.appsamurai.storyly.util.formatter.b priceFormatter$storyly_release = this.f4803a.getProduct$storyly_release().getPriceFormatter$storyly_release();
        if (priceFormatter$storyly_release == null) {
            str = null;
        } else {
            STRCartItem sTRCartItem3 = this.f4805c;
            Float totalPrice = sTRCartItem3 == null ? null : sTRCartItem3.getTotalPrice();
            STRCartItem sTRCartItem4 = this.f4805c;
            str = priceFormatter$storyly_release.a(totalPrice, (sTRCartItem4 == null || (item2 = sTRCartItem4.getItem()) == null) ? null : item2.getCurrency());
        }
        AppCompatTextView priceTextView2 = getPriceTextView();
        priceTextView2.setTypeface(getConfig().getStory$storyly_release().getInteractiveTypeface$storyly_release());
        com.appsamurai.storyly.util.c.a(priceTextView2, true, false);
        priceTextView2.setTextSize(0, f7);
        priceTextView2.setText(str);
        priceTextView2.setVisibility(str != null ? 0 : 8);
        com.appsamurai.storyly.util.formatter.b priceFormatter$storyly_release2 = this.f4803a.getProduct$storyly_release().getPriceFormatter$storyly_release();
        if (priceFormatter$storyly_release2 == null) {
            str2 = null;
        } else {
            STRCartItem sTRCartItem5 = this.f4805c;
            Float oldTotalPrice = sTRCartItem5 == null ? null : sTRCartItem5.getOldTotalPrice();
            STRCartItem sTRCartItem6 = this.f4805c;
            str2 = priceFormatter$storyly_release2.a(oldTotalPrice, (sTRCartItem6 == null || (item = sTRCartItem6.getItem()) == null) ? null : item.getCurrency());
        }
        AppCompatTextView oldPriceTextView2 = getOldPriceTextView();
        oldPriceTextView2.setTypeface(getConfig().getStory$storyly_release().getInteractiveTypeface$storyly_release());
        oldPriceTextView2.setTextSize(0, f3);
        oldPriceTextView2.setText(str2);
        STRCartItem sTRCartItem7 = this.f4805c;
        Float totalPrice2 = sTRCartItem7 == null ? null : sTRCartItem7.getTotalPrice();
        STRCartItem sTRCartItem8 = this.f4805c;
        if (sTRCartItem8 != null) {
            f6 = sTRCartItem8.getOldTotalPrice();
        }
        oldPriceTextView2.setVisibility((Intrinsics.areEqual(totalPrice2, f6) || str2 == null) ? 8 : 0);
    }

    public static final void a(d dVar, a aVar, boolean z2) {
        boolean z3 = false;
        dVar.getDecreaseIcon().setEnabled(false);
        dVar.getIncreaseIcon().setEnabled(false);
        boolean z4 = aVar == a.Default;
        AppCompatImageView increaseIcon = z2 ? dVar.getIncreaseIcon() : dVar.getDecreaseIcon();
        int i3 = z2 ? R.drawable.st_incrase_icon : R.drawable.st_decrease_icon;
        if (!z4) {
            i3 = R.drawable.st_load_icon;
        }
        increaseIcon.setImageResource(i3);
        if (z4) {
            dVar.getDecreaseIcon().setAlpha(dVar.getQuantity() > 0 ? 1.0f : 0.3f);
            AppCompatImageView decreaseIcon = dVar.getDecreaseIcon();
            if (dVar.getQuantity() > 0) {
                z3 = true;
            }
            decreaseIcon.setEnabled(z3);
            dVar.getIncreaseIcon().setEnabled(true);
            dVar.getIncreaseIcon().clearAnimation();
            dVar.getDecreaseIcon().clearAnimation();
            return;
        }
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setRepeatCount(-1);
        increaseIcon.startAnimation(rotateAnimation);
    }

    public static final class b extends Lambda implements Function0<AppCompatImageView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f4820a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ d f4821b;

        public static final class a extends Lambda implements Function0<Unit> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ d f4822a;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public a(d dVar) {
                super(0);
                this.f4822a = dVar;
            }

            public Object invoke() {
                d.a(this.f4822a, a.Default, false);
                return Unit.INSTANCE;
            }
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public b(Context context, d dVar) {
            super(0);
            this.f4820a = context;
            this.f4821b = dVar;
        }

        public static final void a(d dVar, View view) {
            Intrinsics.checkNotNullParameter(dVar, "this$0");
            STRCartItem sTRCartItem = dVar.f4805c;
            if (sTRCartItem != null) {
                d.a(dVar, a.Loading, false);
                Function3<STRCartItem, Integer, Function0<Unit>, Unit> onUpdateCart$storyly_release = dVar.getOnUpdateCart$storyly_release();
                if (onUpdateCart$storyly_release != null) {
                    onUpdateCart$storyly_release.invoke(STRCartItem.copy$default(sTRCartItem, (STRProductItem) null, sTRCartItem.getQuantity() - 1, (Float) null, (Float) null, 13, (Object) null), -1, new a(dVar));
                }
            }
        }

        @NotNull
        /* renamed from: a */
        public final AppCompatImageView invoke() {
            AppCompatImageView appCompatImageView = new AppCompatImageView(this.f4820a);
            d dVar = this.f4821b;
            appCompatImageView.setImageResource(R.drawable.st_decrease_icon);
            appCompatImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            appCompatImageView.setEnabled(false);
            appCompatImageView.setAlpha(0.3f);
            appCompatImageView.setOnClickListener(new X.a(dVar, 0));
            return appCompatImageView;
        }
    }

    public static final class g extends Lambda implements Function0<AppCompatImageView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f4832a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ d f4833b;

        public static final class a extends Lambda implements Function0<Unit> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ d f4834a;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public a(d dVar) {
                super(0);
                this.f4834a = dVar;
            }

            public Object invoke() {
                d.a(this.f4834a, a.Default, true);
                return Unit.INSTANCE;
            }
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public g(Context context, d dVar) {
            super(0);
            this.f4832a = context;
            this.f4833b = dVar;
        }

        public static final void a(d dVar, View view) {
            Intrinsics.checkNotNullParameter(dVar, "this$0");
            STRCartItem sTRCartItem = dVar.f4805c;
            if (sTRCartItem != null) {
                d.a(dVar, a.Loading, true);
                Function3<STRCartItem, Integer, Function0<Unit>, Unit> onUpdateCart$storyly_release = dVar.getOnUpdateCart$storyly_release();
                if (onUpdateCart$storyly_release != null) {
                    onUpdateCart$storyly_release.invoke(STRCartItem.copy$default(sTRCartItem, (STRProductItem) null, sTRCartItem.getQuantity() + 1, (Float) null, (Float) null, 13, (Object) null), 1, new a(dVar));
                }
            }
        }

        @NotNull
        /* renamed from: a */
        public final AppCompatImageView invoke() {
            AppCompatImageView appCompatImageView = new AppCompatImageView(this.f4832a);
            d dVar = this.f4833b;
            appCompatImageView.setImageResource(R.drawable.st_incrase_icon);
            appCompatImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            appCompatImageView.setOnClickListener(new X.a(dVar, 1));
            return appCompatImageView;
        }
    }

    public final void a(int i3, SpannableStringBuilder spannableStringBuilder, float f2, List<STRProductVariant> list, Function1<? super SpannableStringBuilder, Unit> function1) {
        com.appsamurai.storyly.data.managers.product.c cVar;
        RequestOptions requestOptions;
        int i4 = i3;
        SpannableStringBuilder spannableStringBuilder2 = spannableStringBuilder;
        float f3 = f2;
        STRProductVariant sTRProductVariant = (STRProductVariant) com.appsamurai.storyly.util.e.a(list, Integer.valueOf(i3));
        Function1<? super SpannableStringBuilder, Unit> function12 = function1;
        if (sTRProductVariant == null) {
            function12.invoke(spannableStringBuilder2);
        }
        if (sTRProductVariant == null) {
            cVar = null;
        } else {
            cVar = sTRProductVariant.getSourceType$storyly_release();
        }
        int i5 = cVar == null ? -1 : a.$EnumSwitchMapping$0[cVar.ordinal()];
        if (i5 == 1) {
            spannableStringBuilder2.append(sTRProductVariant.getValue());
            if (i4 != CollectionsKt.getLastIndex(list)) {
                spannableStringBuilder2.append(", ");
            }
            a(i4 + 1, spannableStringBuilder, f2, list, function1);
        } else if (i5 == 2) {
            a(Intrinsics.stringPlus("PLACE_HOLDER", Integer.valueOf(i3)), spannableStringBuilder2, f3, com.appsamurai.storyly.util.ui.b.a(this, Color.parseColor(sTRProductVariant.getValue()), f3 / ((float) 2), (Integer) null, 0, 12));
            if (i4 != CollectionsKt.getLastIndex(list)) {
                spannableStringBuilder2.append(", ");
            }
            a(i4 + 1, spannableStringBuilder, f2, list, function1);
        } else if (i5 == 3) {
            String value = sTRProductVariant.getValue();
            float f4 = f3 / ((float) 2);
            c cVar2 = r0;
            c cVar3 = new c(i3, list, spannableStringBuilder, this, f2, function1);
            if (f4 > 0.0f) {
                requestOptions = (RequestOptions) new RequestOptions().transform((Transformation<Bitmap>[]) new Transformation[]{new CenterCrop(), new RoundedCorners((int) f4)});
            } else {
                requestOptions = (RequestOptions) new RequestOptions().transform((Transformation<Bitmap>) new CenterCrop());
            }
            Intrinsics.checkNotNullExpressionValue(requestOptions, "if (cornerRadius > 0) {\n…m(CenterCrop())\n        }");
            this.f4806d = ((RequestBuilder) Glide.with(getContext()).load(value).override((int) f3)).apply((BaseRequestOptions<?>) requestOptions).listener(new e(cVar2)).preload();
        }
    }

    public final void a(String str, SpannableStringBuilder spannableStringBuilder, float f2, Drawable drawable) {
        spannableStringBuilder.append(str);
        int B2 = StringsKt__StringsKt.indexOf$default((CharSequence) spannableStringBuilder, str, 0, false, 6, (Object) null);
        int i3 = (int) f2;
        drawable.setBounds(0, 0, i3, i3);
        spannableStringBuilder.setSpan(new ImageSpan(drawable, 2), B2, str.length() + B2, 17);
    }

    public final void a() {
        Target<?> target = this.f4806d;
        if (target != null) {
            Glide.with(getContext()).clear(target);
        }
        this.f4806d = null;
        Glide.with(getContext()).clear((View) getImageView());
        getImageContainer().removeAllViews();
        getIndicatorContainer().removeAllViews();
        getPriceContainer().removeAllViews();
        removeAllViews();
    }
}
