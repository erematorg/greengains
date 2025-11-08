package com.appsamurai.storyly.storylypresenter.product.imagelist;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.appsamurai.storyly.util.m;
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

public final class f extends RelativeLayout {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Lazy f5150a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final Lazy f5151b;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public final Lazy f5152c;

    public static final class a extends Lambda implements Function0<View> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5153a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(Context context) {
            super(0);
            this.f5153a = context;
        }

        public Object invoke() {
            return new View(this.f5153a);
        }
    }

    public static final class b extends Lambda implements Function0<FrameLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5154a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ f f5155b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public b(Context context, f fVar) {
            super(0);
            this.f5154a = context;
            this.f5155b = fVar;
        }

        public Object invoke() {
            FrameLayout frameLayout = new FrameLayout(this.f5154a);
            this.f5155b.setGravity(13);
            return frameLayout;
        }
    }

    public static final class c extends Lambda implements Function0<ImageView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5156a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public c(Context context) {
            super(0);
            this.f5156a = context;
        }

        public Object invoke() {
            ImageView imageView = new ImageView(this.f5156a);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public f(@NotNull Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5150a = LazyKt.lazy(new b(context, this));
        this.f5151b = LazyKt.lazy(new c(context));
        this.f5152c = LazyKt.lazy(new a(context));
    }

    private final View getImageBorder() {
        return (View) this.f5152c.getValue();
    }

    private final FrameLayout getImageContainer() {
        return (FrameLayout) this.f5150a.getValue();
    }

    private final ImageView getImageView() {
        return (ImageView) this.f5151b.getValue();
    }

    public final void a() {
        Glide.with(getContext().getApplicationContext()).clear((View) getImageView());
        getImageContainer().removeAllViews();
        removeAllViews();
    }

    public final void setupView(@NotNull String str) {
        RequestOptions requestOptions;
        Intrinsics.checkNotNullParameter(str, "resource");
        int width = (int) (((double) m.b().width()) * 0.435d);
        float f2 = (float) width;
        int i3 = (int) (0.065f * f2);
        int i4 = (int) (f2 * 0.01f);
        getImageBorder().setBackground(com.appsamurai.storyly.util.ui.b.a(this, 0, (float) i3, Integer.valueOf(Color.parseColor("#EEEEEE")), i4, 1));
        if (i3 > 0) {
            requestOptions = (RequestOptions) new RequestOptions().transform((Transformation<Bitmap>[]) new Transformation[]{new CenterCrop(), new RoundedCorners(i3)});
        } else {
            requestOptions = (RequestOptions) new RequestOptions().transform((Transformation<Bitmap>) new CenterCrop());
        }
        Intrinsics.checkNotNullExpressionValue(requestOptions, "if (cornerRadius > 0) {\n…m(CenterCrop())\n        }");
        ((RequestBuilder) Glide.with(getContext().getApplicationContext()).load(str).diskCacheStrategy(DiskCacheStrategy.ALL)).apply((BaseRequestOptions<?>) requestOptions).into(getImageView());
        FrameLayout imageContainer = getImageContainer();
        Class cls = Integer.TYPE;
        Class<RelativeLayout.LayoutParams> cls2 = RelativeLayout.LayoutParams.class;
        ViewGroup.LayoutParams newInstance = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{Integer.valueOf(width), Integer.valueOf(width)});
        Intrinsics.checkNotNullExpressionValue(newInstance, "layoutParams");
        ((RelativeLayout.LayoutParams) newInstance).addRule(13);
        Unit unit = Unit.INSTANCE;
        addView(imageContainer, newInstance);
        FrameLayout imageContainer2 = getImageContainer();
        ImageView imageView = getImageView();
        ViewGroup.LayoutParams newInstance2 = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{Integer.valueOf(width), Integer.valueOf(width)});
        Intrinsics.checkNotNullExpressionValue(newInstance2, "layoutParams");
        imageContainer2.addView(imageView, newInstance2);
        FrameLayout imageContainer3 = getImageContainer();
        View imageBorder = getImageBorder();
        ViewGroup.LayoutParams newInstance3 = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{Integer.valueOf(width), Integer.valueOf(width)});
        Intrinsics.checkNotNullExpressionValue(newInstance3, "layoutParams");
        imageContainer3.addView(imageBorder, newInstance3);
    }
}
