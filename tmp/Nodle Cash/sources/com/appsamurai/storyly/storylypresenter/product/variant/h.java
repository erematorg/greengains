package com.appsamurai.storyly.storylypresenter.product.variant;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.ViewCompat;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.data.managers.product.STRProductVariant;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class h extends LinearLayout {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final StorylyConfig f5290a;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    public STRProductVariant f5291b;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public final Lazy f5292c;
    @NotNull

    /* renamed from: d  reason: collision with root package name */
    public final Lazy f5293d;
    @NotNull

    /* renamed from: e  reason: collision with root package name */
    public final Lazy f5294e;
    @NotNull

    /* renamed from: f  reason: collision with root package name */
    public final Lazy f5295f;

    public static final class a extends Lambda implements Function0<ImageView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5296a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(Context context) {
            super(0);
            this.f5296a = context;
        }

        public Object invoke() {
            ImageView imageView = new ImageView(this.f5296a);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;
        }
    }

    public static final class b extends Lambda implements Function0<View> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5297a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public b(Context context) {
            super(0);
            this.f5297a = context;
        }

        public Object invoke() {
            View view = new View(this.f5297a);
            view.setVisibility(8);
            view.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
            return view;
        }
    }

    public static final class c extends Lambda implements Function0<AppCompatTextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5298a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public c(Context context) {
            super(0);
            this.f5298a = context;
        }

        public Object invoke() {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f5298a);
            appCompatTextView.setSingleLine(true);
            appCompatTextView.setEllipsize(TextUtils.TruncateAt.END);
            appCompatTextView.setGravity(17);
            appCompatTextView.setTextAlignment(4);
            appCompatTextView.setTextColor(Color.parseColor("#484848"));
            appCompatTextView.setIncludeFontPadding(false);
            appCompatTextView.setHorizontallyScrolling(false);
            appCompatTextView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            return appCompatTextView;
        }
    }

    public static final class d extends Lambda implements Function0<LinearLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5299a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public d(Context context) {
            super(0);
            this.f5299a = context;
        }

        public Object invoke() {
            LinearLayout linearLayout = new LinearLayout(this.f5299a);
            linearLayout.setGravity(17);
            return linearLayout;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public h(@NotNull Context context, @NotNull StorylyConfig storylyConfig) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        this.f5290a = storylyConfig;
        this.f5292c = LazyKt.lazy(new a(context));
        this.f5293d = LazyKt.lazy(new d(context));
        this.f5294e = LazyKt.lazy(new c(context));
        this.f5295f = LazyKt.lazy(new b(context));
        setOrientation(1);
        setGravity(17);
    }

    private final ImageView getImageView() {
        return (ImageView) this.f5292c.getValue();
    }

    private final View getSelectedIndicator() {
        return (View) this.f5295f.getValue();
    }

    private final AppCompatTextView getVariantLabel() {
        return (AppCompatTextView) this.f5294e.getValue();
    }

    private final LinearLayout getVariantLabelContainer() {
        return (LinearLayout) this.f5293d.getValue();
    }

    public final void a(@NotNull STRProductVariant sTRProductVariant, int i3, boolean z2) {
        LayerDrawable layerDrawable;
        RequestOptions requestOptions;
        Intrinsics.checkNotNullParameter(sTRProductVariant, "variantItem");
        this.f5291b = sTRProductVariant;
        boolean isEnabled$storyly_release = sTRProductVariant.isEnabled$storyly_release();
        setClickable(isEnabled$storyly_release);
        setEnabled(isEnabled$storyly_release);
        getImageView().setAlpha(isEnabled$storyly_release ? 1.0f : 0.1f);
        if (sTRProductVariant.isEnabled$storyly_release()) {
            setEnabled(sTRProductVariant.isEnabled$storyly_release());
        }
        float f2 = (float) i3;
        int i4 = (int) (0.1f * f2);
        int i5 = (int) (f2 * 0.04f);
        getSelectedIndicator().setVisibility(z2 ? 0 : 8);
        ImageView imageView = getImageView();
        Class cls = Integer.TYPE;
        ViewGroup.LayoutParams newInstance = ViewGroup.LayoutParams.class.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{Integer.valueOf(i3), Integer.valueOf(i3)});
        Intrinsics.checkNotNullExpressionValue(newInstance, "layoutParams");
        Unit unit = Unit.INSTANCE;
        addView(imageView, newInstance);
        View selectedIndicator = getSelectedIndicator();
        ViewGroup.LayoutParams newInstance2 = LinearLayout.LayoutParams.class.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{Integer.valueOf(i4), Integer.valueOf(i4)});
        Intrinsics.checkNotNullExpressionValue(newInstance2, "layoutParams");
        ((LinearLayout.LayoutParams) newInstance2).topMargin = i4;
        addView(selectedIndicator, newInstance2);
        ImageView imageView2 = getImageView();
        int ordinal = sTRProductVariant.getSourceType$storyly_release().ordinal();
        if (ordinal == 0) {
            if (z2) {
                layerDrawable = a(Integer.valueOf(Color.parseColor(sTRProductVariant.getValue())), i5, Integer.valueOf(ViewCompat.MEASURED_STATE_MASK), i5 * 2, -1, (float) (i3 / 2));
            } else {
                layerDrawable = a(Integer.valueOf(Color.parseColor(sTRProductVariant.getValue())), i5, Integer.valueOf(Color.parseColor("#EEEEEE")), i5 * 2, Integer.valueOf(Color.parseColor(sTRProductVariant.getValue())), (float) (i3 / 2));
            }
            imageView2.setBackground(layerDrawable);
        } else if (ordinal == 1) {
            String value = sTRProductVariant.getValue();
            int i6 = i3 / 2;
            if (i6 > 0) {
                requestOptions = (RequestOptions) new RequestOptions().transform((Transformation<Bitmap>[]) new Transformation[]{new CenterCrop(), new RoundedCorners(i6)});
            } else {
                requestOptions = (RequestOptions) new RequestOptions().transform((Transformation<Bitmap>) new CenterCrop());
            }
            Intrinsics.checkNotNullExpressionValue(requestOptions, "if (cornerRadius > 0) {\n…m(CenterCrop())\n        }");
            ((RequestBuilder) Glide.with(getContext().getApplicationContext()).load(value).diskCacheStrategy(DiskCacheStrategy.ALL)).apply((BaseRequestOptions<?>) requestOptions).into(getImageView());
        }
        View selectedIndicator2 = getSelectedIndicator();
        selectedIndicator2.setBackground(com.appsamurai.storyly.util.ui.b.a(selectedIndicator2, ViewCompat.MEASURED_STATE_MASK, (float) (i4 / 2), (Integer) null, 0, 4));
    }

    public final void b(@NotNull STRProductVariant sTRProductVariant, int i3, boolean z2) {
        STRProductVariant sTRProductVariant2 = sTRProductVariant;
        int i4 = i3;
        Intrinsics.checkNotNullParameter(sTRProductVariant2, "variantItem");
        this.f5291b = sTRProductVariant2;
        float f2 = (float) i4;
        int i5 = (int) (0.4f * f2);
        int i6 = (int) (0.3f * f2);
        int i7 = (int) (0.1f * f2);
        int i8 = 0;
        int i9 = z2 ? (int) (f2 * 0.04f) : 0;
        boolean isEnabled$storyly_release = sTRProductVariant.isEnabled$storyly_release();
        setClickable(isEnabled$storyly_release);
        setEnabled(isEnabled$storyly_release);
        getVariantLabelContainer().setAlpha(isEnabled$storyly_release ? 1.0f : 0.5f);
        LinearLayout variantLabelContainer = getVariantLabelContainer();
        Class cls = Integer.TYPE;
        ViewGroup.LayoutParams newInstance = ViewGroup.LayoutParams.class.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-2, Integer.valueOf(i3)});
        Intrinsics.checkNotNullExpressionValue(newInstance, "layoutParams");
        Unit unit = Unit.INSTANCE;
        addView(variantLabelContainer, newInstance);
        View selectedIndicator = getSelectedIndicator();
        Class<LinearLayout.LayoutParams> cls2 = LinearLayout.LayoutParams.class;
        ViewGroup.LayoutParams newInstance2 = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{Integer.valueOf(i7), Integer.valueOf(i7)});
        Intrinsics.checkNotNullExpressionValue(newInstance2, "layoutParams");
        ((LinearLayout.LayoutParams) newInstance2).topMargin = i7;
        addView(selectedIndicator, newInstance2);
        LinearLayout variantLabelContainer2 = getVariantLabelContainer();
        AppCompatTextView variantLabel = getVariantLabel();
        ViewGroup.LayoutParams newInstance3 = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-2, Integer.valueOf(i3)});
        Intrinsics.checkNotNullExpressionValue(newInstance3, "layoutParams");
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) newInstance3;
        layoutParams.leftMargin = i6;
        layoutParams.rightMargin = i6;
        variantLabelContainer2.addView(variantLabel, newInstance3);
        getVariantLabelContainer().setBackground(a(Integer.valueOf(Color.parseColor("#EEEEEE")), i9, Integer.valueOf(ViewCompat.MEASURED_STATE_MASK), i9 * 2, -1, (float) (i4 / 2)));
        getVariantLabelContainer().setMinimumWidth(i4);
        AppCompatTextView variantLabel2 = getVariantLabel();
        variantLabel2.setText(sTRProductVariant.getValue());
        variantLabel2.setTypeface(getConfig().getStory$storyly_release().getInteractiveTypeface$storyly_release());
        variantLabel2.setTextSize(0, ((float) i5) * 0.75f);
        View selectedIndicator2 = getSelectedIndicator();
        if (!z2) {
            i8 = 8;
        }
        selectedIndicator2.setVisibility(i8);
        selectedIndicator2.setBackground(com.appsamurai.storyly.util.ui.b.a(selectedIndicator2, ViewCompat.MEASURED_STATE_MASK, (float) (i7 / 2), (Integer) null, 0, 4));
    }

    @NotNull
    public final StorylyConfig getConfig() {
        return this.f5290a;
    }

    @Nullable
    public final STRProductVariant getVariantItem() {
        return this.f5291b;
    }

    public final void setVariantItem(@Nullable STRProductVariant sTRProductVariant) {
        this.f5291b = sTRProductVariant;
    }

    public final void a() {
        removeAllViews();
        getVariantLabelContainer().removeAllViews();
        Glide.with(getContext().getApplicationContext()).clear((View) getImageView());
    }

    public final LayerDrawable a(Integer num, int i3, Integer num2, int i4, Integer num3, float f2) {
        return new LayerDrawable(new GradientDrawable[]{com.appsamurai.storyly.util.ui.b.a(this, num == null ? 0 : num.intValue(), f2, num3, i4), com.appsamurai.storyly.util.ui.b.a(this, 0, f2, num2, i3, 1)});
    }
}
