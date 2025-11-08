package com.appsamurai.storyly.storylypresenter.storylyfooter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import com.appsamurai.storyly.R;
import com.appsamurai.storyly.data.v;
import com.appsamurai.storyly.data.z;
import com.appsamurai.storyly.storylypresenter.j1;
import com.appsamurai.storyly.util.m;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.properties.Delegates;
import kotlin.properties.ObservableProperty;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class d {

    /* renamed from: r  reason: collision with root package name */
    public static final /* synthetic */ KProperty<Object>[] f5429r = {androidx.compose.ui.autofill.a.m(d.class, "storylyItem", "getStorylyItem$storyly_release()Lcom/appsamurai/storyly/data/StorylyItem;", 0)};
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final ViewGroup f5430a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final ViewGroup f5431b;

    /* renamed from: c  reason: collision with root package name */
    public Function0<Unit> f5432c;

    /* renamed from: d  reason: collision with root package name */
    public Function0<Unit> f5433d;

    /* renamed from: e  reason: collision with root package name */
    public Function0<Unit> f5434e;

    /* renamed from: f  reason: collision with root package name */
    public com.appsamurai.storyly.storylypresenter.a f5435f;
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public final RequestManager f5436g;
    @Nullable

    /* renamed from: h  reason: collision with root package name */
    public v f5437h;
    @NotNull

    /* renamed from: i  reason: collision with root package name */
    public final ReadWriteProperty f5438i = new e((Object) null, (Object) null, this);
    @NotNull

    /* renamed from: j  reason: collision with root package name */
    public final Lazy f5439j = LazyKt.lazy(new i(this));
    @NotNull

    /* renamed from: k  reason: collision with root package name */
    public final Lazy f5440k = LazyKt.lazy(new h(this));
    @NotNull

    /* renamed from: l  reason: collision with root package name */
    public final Lazy f5441l = LazyKt.lazy(new f(this));
    @NotNull

    /* renamed from: m  reason: collision with root package name */
    public final Lazy f5442m = LazyKt.lazy(new g(this));
    @NotNull

    /* renamed from: n  reason: collision with root package name */
    public final Lazy f5443n = LazyKt.lazy(new a(this));
    @NotNull

    /* renamed from: o  reason: collision with root package name */
    public final Lazy f5444o = LazyKt.lazy(new b(this));
    @NotNull

    /* renamed from: p  reason: collision with root package name */
    public final Lazy f5445p = LazyKt.lazy(new c(this));
    @NotNull

    /* renamed from: q  reason: collision with root package name */
    public final Lazy f5446q = LazyKt.lazy(new C0039d(this));

    public static final class a extends Lambda implements Function0<FrameLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ d f5447a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(d dVar) {
            super(0);
            this.f5447a = dVar;
        }

        public Object invoke() {
            View view = new View(this.f5447a.f5430a.getContext());
            view.setBackgroundColor(Color.parseColor("#212121"));
            view.setAlpha(0.6f);
            view.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
            Context context = this.f5447a.f5430a.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "holder.context");
            com.appsamurai.storyly.util.ui.blur.c cVar = new com.appsamurai.storyly.util.ui.blur.c(context, (AttributeSet) null, 0, 0, 14);
            d dVar = this.f5447a;
            cVar.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
            com.appsamurai.storyly.util.ui.blur.a aVar = (com.appsamurai.storyly.util.ui.blur.a) cVar.a(dVar.f5431b);
            aVar.f6362e = 20.0f;
            aVar.a(Color.parseColor("#1e1e1e66"));
            aVar.b(false);
            FrameLayout frameLayout = new FrameLayout(this.f5447a.f5430a.getContext());
            frameLayout.setVisibility(4);
            frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
            frameLayout.addView(cVar);
            frameLayout.addView(view);
            return frameLayout;
        }
    }

    public static final class e extends ObservableProperty<z> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ d f5451a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public e(Object obj, Object obj2, d dVar) {
            super(null);
            this.f5451a = dVar;
        }

        public void afterChange(@NotNull KProperty<?> kProperty, z zVar, z zVar2) {
            Intrinsics.checkNotNullParameter(kProperty, "property");
            z zVar3 = zVar2;
            z zVar4 = zVar;
            this.f5451a.h();
            if (zVar3 != null && zVar3.f4316o) {
                d dVar = this.f5451a;
                dVar.f5430a.setVisibility(0);
                dVar.a().setVisibility(0);
                dVar.c().setVisibility(0);
                dVar.i();
            }
        }
    }

    public static final class f extends Lambda implements Function0<View> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ d f5452a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public f(d dVar) {
            super(0);
            this.f5452a = dVar;
        }

        public Object invoke() {
            View view = new View(this.f5452a.f().getContext());
            view.setBackgroundColor(Color.parseColor("#212121"));
            view.setAlpha(1.0f);
            view.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
            return view;
        }
    }

    public static final class g extends Lambda implements Function0<FrameLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ d f5453a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public g(d dVar) {
            super(0);
            this.f5453a = dVar;
        }

        public Object invoke() {
            Context context = this.f5453a.f().getContext();
            Intrinsics.checkNotNullExpressionValue(context, "videoThumbnailImage.context");
            com.appsamurai.storyly.util.ui.blur.c cVar = new com.appsamurai.storyly.util.ui.blur.c(context, (AttributeSet) null, 0, 0, 14);
            d dVar = this.f5453a;
            cVar.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
            com.appsamurai.storyly.util.ui.blur.a aVar = (com.appsamurai.storyly.util.ui.blur.a) cVar.a(dVar.g());
            aVar.f6362e = 20.0f;
            aVar.a(Color.parseColor("#1e1e1e66"));
            aVar.b(false);
            FrameLayout frameLayout = new FrameLayout(this.f5453a.f().getContext());
            frameLayout.setVisibility(0);
            frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
            frameLayout.addView(cVar);
            return frameLayout;
        }
    }

    public static final class h extends Lambda implements Function0<ImageView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ d f5454a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public h(d dVar) {
            super(0);
            this.f5454a = dVar;
        }

        public Object invoke() {
            ImageView imageView = new ImageView(this.f5454a.f5430a.getContext());
            imageView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;
        }
    }

    public static final class i extends Lambda implements Function0<FrameLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ d f5455a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public i(d dVar) {
            super(0);
            this.f5455a = dVar;
        }

        public Object invoke() {
            FrameLayout frameLayout = new FrameLayout(this.f5455a.f5430a.getContext());
            frameLayout.setVisibility(4);
            frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
            return frameLayout;
        }
    }

    public d(@NotNull ViewGroup viewGroup, @NotNull ViewGroup viewGroup2) {
        Intrinsics.checkNotNullParameter(viewGroup, "holder");
        Intrinsics.checkNotNullParameter(viewGroup2, "layerView");
        this.f5430a = viewGroup;
        this.f5431b = viewGroup2;
        RequestManager with = Glide.with(viewGroup.getContext().getApplicationContext());
        Intrinsics.checkNotNullExpressionValue(with, "with(holder.context.applicationContext)");
        this.f5436g = with;
        Delegates delegates = Delegates.INSTANCE;
        viewGroup.setVisibility(4);
        viewGroup.addView(g());
        g().addView(f());
        g().addView(e());
        g().addView(d());
        viewGroup.addView(a());
        viewGroup.addView(b());
        viewGroup.addView(c());
        a().setOnTouchListener(new E0.e(this, 0));
    }

    public static final boolean a(d dVar, View view, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(dVar, "this$0");
        com.appsamurai.storyly.storylypresenter.a aVar = dVar.f5435f;
        if (aVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("actionManager");
            aVar = null;
        }
        Pair<Integer, Integer> pair = new Pair<>(Integer.valueOf(dVar.a().getWidth()), Integer.valueOf(dVar.a().getHeight()));
        aVar.getClass();
        Intrinsics.checkNotNullParameter(pair, "parentArea");
        aVar.f4720a = pair;
        j1 j1Var = aVar.f4723d;
        if (j1Var == null) {
            return true;
        }
        j1Var.a(motionEvent);
        return true;
    }

    public final LinearLayout b() {
        return (LinearLayout) this.f5445p.getValue();
    }

    public final LinearLayout c() {
        return (LinearLayout) this.f5446q.getValue();
    }

    public final View d() {
        return (View) this.f5441l.getValue();
    }

    public final FrameLayout e() {
        return (FrameLayout) this.f5442m.getValue();
    }

    public final ImageView f() {
        return (ImageView) this.f5440k.getValue();
    }

    public final FrameLayout g() {
        return (FrameLayout) this.f5439j.getValue();
    }

    public final void h() {
        this.f5436g.clear((View) f());
        this.f5430a.setVisibility(4);
        g().setVisibility(4);
        b().setVisibility(4);
        a().setVisibility(4);
        c().setVisibility(4);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0011, code lost:
        r0 = r0.a().getMedia();
     */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:50:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void i() {
        /*
            r6 = this;
            kotlin.properties.ReadWriteProperty r0 = r6.f5438i
            kotlin.reflect.KProperty<java.lang.Object>[] r1 = f5429r
            r2 = 0
            r3 = r1[r2]
            java.lang.Object r0 = r0.getValue(r6, r3)
            com.appsamurai.storyly.data.z r0 = (com.appsamurai.storyly.data.z) r0
            r3 = 0
            if (r0 != 0) goto L_0x0011
            goto L_0x001b
        L_0x0011:
            com.appsamurai.storyly.Story r0 = r0.a()
            com.appsamurai.storyly.StoryMedia r0 = r0.getMedia()
            if (r0 != 0) goto L_0x001d
        L_0x001b:
            r0 = r3
            goto L_0x0021
        L_0x001d:
            com.appsamurai.storyly.StoryType r0 = r0.getType()
        L_0x0021:
            com.appsamurai.storyly.StoryType r4 = com.appsamurai.storyly.StoryType.Video
            r5 = 4
            if (r0 != r4) goto L_0x00be
            android.widget.FrameLayout r0 = r6.g()
            r0.setVisibility(r2)
            android.widget.FrameLayout r0 = r6.a()
            r0.setVisibility(r5)
            kotlin.properties.ReadWriteProperty r0 = r6.f5438i
            r1 = r1[r2]
            java.lang.Object r0 = r0.getValue(r6, r1)
            com.appsamurai.storyly.data.z r0 = (com.appsamurai.storyly.data.z) r0
            if (r0 != 0) goto L_0x0041
            goto L_0x0071
        L_0x0041:
            com.appsamurai.storyly.data.d0 r0 = r0.f4303b
            if (r0 != 0) goto L_0x0046
            goto L_0x0071
        L_0x0046:
            java.util.List<com.appsamurai.storyly.data.b0> r0 = r0.f3639a
            if (r0 != 0) goto L_0x004b
            goto L_0x0071
        L_0x004b:
            java.util.Iterator r0 = r0.iterator()
        L_0x004f:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x006c
            java.lang.Object r1 = r0.next()
            r2 = r1
            com.appsamurai.storyly.data.b0 r2 = (com.appsamurai.storyly.data.b0) r2
            if (r2 != 0) goto L_0x0060
            r2 = r3
            goto L_0x0062
        L_0x0060:
            java.lang.String r2 = r2.f3606a
        L_0x0062:
            java.lang.String r4 = "video"
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r4)
            if (r2 == 0) goto L_0x004f
            goto L_0x006d
        L_0x006c:
            r1 = r3
        L_0x006d:
            com.appsamurai.storyly.data.b0 r1 = (com.appsamurai.storyly.data.b0) r1
            if (r1 != 0) goto L_0x0073
        L_0x0071:
            r0 = r3
            goto L_0x0075
        L_0x0073:
            com.appsamurai.storyly.data.a0 r0 = r1.f3615j
        L_0x0075:
            boolean r1 = r0 instanceof com.appsamurai.storyly.data.q0
            if (r1 == 0) goto L_0x007c
            com.appsamurai.storyly.data.q0 r0 = (com.appsamurai.storyly.data.q0) r0
            goto L_0x007d
        L_0x007c:
            r0 = r3
        L_0x007d:
            if (r0 != 0) goto L_0x0080
            goto L_0x00d5
        L_0x0080:
            com.appsamurai.storyly.data.q0$b r1 = r0.f4152g
            int r1 = r1.ordinal()
            if (r1 == 0) goto L_0x00a5
            r2 = 1
            if (r1 == r2) goto L_0x0095
            android.view.View r6 = r6.d()
            r0 = 1065353216(0x3f800000, float:1.0)
            r6.setAlpha(r0)
            goto L_0x00d5
        L_0x0095:
            com.appsamurai.storyly.data.v r1 = r6.f5437h
            if (r1 != 0) goto L_0x009a
            goto L_0x00a2
        L_0x009a:
            java.lang.String r1 = r1.f4223c
            java.lang.String r0 = r0.f4149d
            java.lang.String r3 = kotlin.jvm.internal.Intrinsics.stringPlus(r1, r0)
        L_0x00a2:
            if (r3 != 0) goto L_0x00a7
            goto L_0x00d5
        L_0x00a5:
            java.lang.String r3 = r0.f4148c
        L_0x00a7:
            com.bumptech.glide.RequestManager r0 = r6.f5436g
            com.bumptech.glide.RequestBuilder r0 = r0.load((java.lang.String) r3)
            com.appsamurai.storyly.storylypresenter.storylyfooter.f r1 = new com.appsamurai.storyly.storylypresenter.storylyfooter.f
            r1.<init>(r6)
            com.bumptech.glide.RequestBuilder r0 = r0.listener(r1)
            android.widget.ImageView r6 = r6.f()
            r0.into((android.widget.ImageView) r6)
            goto L_0x00d5
        L_0x00be:
            com.bumptech.glide.RequestManager r0 = r6.f5436g
            android.widget.ImageView r1 = r6.f()
            r0.clear((android.view.View) r1)
            android.widget.FrameLayout r0 = r6.g()
            r0.setVisibility(r5)
            android.widget.FrameLayout r6 = r6.a()
            r6.setVisibility(r2)
        L_0x00d5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.storylypresenter.storylyfooter.d.i():void");
    }

    public static final class b extends Lambda implements Function0<BottomSheetDialog> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ d f5448a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public b(d dVar) {
            super(0);
            this.f5448a = dVar;
        }

        public static final void a(d dVar, View view) {
            Intrinsics.checkNotNullParameter(dVar, "this$0");
            Function0<Unit> function0 = dVar.f5433d;
            if (function0 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("onReport");
                function0 = null;
            }
            function0.invoke();
        }

        @NotNull
        /* renamed from: a */
        public final BottomSheetDialog invoke() {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this.f5448a.f5430a.getContext(), R.style.StorylyBottomSheetTheme);
            d dVar = this.f5448a;
            AppCompatImageView appCompatImageView = new AppCompatImageView(dVar.f5430a.getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(m.b(30.0f, 0.0f, 2), m.a(4.0f, 0.0f, 2));
            layoutParams.setMargins(0, m.a(12.0f, 0.0f, 2), 0, m.a(23.0f, 0.0f, 2));
            layoutParams.gravity = 1;
            Unit unit = Unit.INSTANCE;
            appCompatImageView.setLayoutParams(layoutParams);
            ImageView.ScaleType scaleType = ImageView.ScaleType.FIT_CENTER;
            appCompatImageView.setScaleType(scaleType);
            appCompatImageView.setImageResource(R.drawable.st_lid);
            ImageView imageView = new ImageView(dVar.f5430a.getContext());
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(m.b(18.0f, 0.0f, 2), m.b(18.0f, 0.0f, 2));
            layoutParams2.gravity = 16;
            layoutParams2.setMargins(m.b(19.0f, 0.0f, 2), 0, m.b(11.0f, 0.0f, 2), 0);
            imageView.setLayoutParams(layoutParams2);
            imageView.setScaleType(scaleType);
            imageView.setImageResource(R.drawable.st_report_alert_icon);
            TextView textView = new TextView(bottomSheetDialog.getContext());
            textView.setText(bottomSheetDialog.getContext().getString(R.string.stm_report_text));
            textView.setMaxLines(1);
            textView.setTextColor(-1);
            textView.setTextSize(2, 14.0f);
            textView.setTextAlignment(4);
            textView.setTypeface(Typeface.DEFAULT);
            textView.setLineSpacing((float) m.a((Number) Double.valueOf(4.23d)), 1.0f);
            LinearLayout linearLayout = new LinearLayout(dVar.f5430a.getContext());
            linearLayout.setOrientation(0);
            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -1);
            layoutParams3.setMargins(0, 0, 0, m.a(20.0f, 0.0f, 2));
            linearLayout.setLayoutParams(layoutParams3);
            linearLayout.addView(imageView);
            linearLayout.addView(textView);
            linearLayout.setOnClickListener(new E0.f(dVar, 0));
            LinearLayout linearLayout2 = new LinearLayout(bottomSheetDialog.getContext());
            linearLayout2.setOrientation(1);
            linearLayout2.addView(appCompatImageView);
            linearLayout2.addView(linearLayout);
            LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-1, -2);
            layoutParams4.gravity = 80;
            bottomSheetDialog.setContentView(linearLayout2, layoutParams4);
            return bottomSheetDialog;
        }
    }

    public final FrameLayout a() {
        return (FrameLayout) this.f5443n.getValue();
    }

    /* renamed from: com.appsamurai.storyly.storylypresenter.storylyfooter.d$d  reason: collision with other inner class name */
    public static final class C0039d extends Lambda implements Function0<LinearLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ d f5450a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public C0039d(d dVar) {
            super(0);
            this.f5450a = dVar;
        }

        @NotNull
        /* renamed from: a */
        public final LinearLayout invoke() {
            ImageView imageView = new ImageView(this.f5450a.f5430a.getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(m.b(69.0f, 0.0f, 2), m.b(69.0f, 0.0f, 2));
            layoutParams.gravity = 1;
            layoutParams.setMargins(0, 0, 0, m.a(22.0f, 0.0f, 2));
            Unit unit = Unit.INSTANCE;
            imageView.setLayoutParams(layoutParams);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setImageResource(R.drawable.st_moments_reported_icon);
            TextView textView = new TextView(this.f5450a.f5430a.getContext());
            d dVar = this.f5450a;
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
            layoutParams2.gravity = 1;
            layoutParams2.setMargins(0, 0, 0, m.a(16.0f, 0.0f, 2));
            textView.setLayoutParams(layoutParams2);
            textView.setGravity(1);
            textView.setText(dVar.f5430a.getContext().getString(R.string.stm_reported_info));
            textView.setTextColor(-1);
            textView.setTextSize(1, 18.0f);
            AppCompatButton appCompatButton = new AppCompatButton(this.f5450a.f5430a.getContext());
            d dVar2 = this.f5450a;
            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
            layoutParams3.setMargins(0, 0, 0, m.b(16.0f, 0.0f, 2));
            layoutParams3.gravity = 1;
            appCompatButton.setLayoutParams(layoutParams3);
            appCompatButton.setBackgroundColor(0);
            appCompatButton.setText(dVar2.f5430a.getContext().getString(R.string.stm_show_reported_view));
            appCompatButton.setAllCaps(false);
            appCompatButton.setTextColor(-1);
            appCompatButton.setTextSize(1, 14.0f);
            appCompatButton.setTypeface(appCompatButton.getTypeface(), 1);
            appCompatButton.setOnClickListener(new E0.f(dVar2, 2));
            LinearLayout linearLayout = new LinearLayout(this.f5450a.f5430a.getContext());
            linearLayout.setVisibility(4);
            linearLayout.setOrientation(1);
            linearLayout.setBackgroundColor(0);
            FrameLayout.LayoutParams layoutParams4 = new FrameLayout.LayoutParams(-2, -2);
            layoutParams4.gravity = 17;
            linearLayout.setLayoutParams(layoutParams4);
            linearLayout.addView(imageView);
            linearLayout.addView(textView);
            linearLayout.addView(appCompatButton);
            return linearLayout;
        }

        public static final void a(d dVar, View view) {
            Intrinsics.checkNotNullParameter(dVar, "this$0");
            KProperty<Object>[] kPropertyArr = d.f5429r;
            dVar.h();
        }
    }

    public static final class c extends Lambda implements Function0<LinearLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ d f5449a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public c(d dVar) {
            super(0);
            this.f5449a = dVar;
        }

        @NotNull
        /* renamed from: a */
        public final LinearLayout invoke() {
            AppCompatImageView appCompatImageView = new AppCompatImageView(this.f5449a.f5430a.getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(m.b(50.0f, 0.0f, 2), m.a(4.0f, 0.0f, 2));
            layoutParams.setMargins(0, m.a(12.0f, 0.0f, 2), 0, m.a(20.0f, 0.0f, 2));
            layoutParams.gravity = 1;
            Unit unit = Unit.INSTANCE;
            appCompatImageView.setLayoutParams(layoutParams);
            ImageView.ScaleType scaleType = ImageView.ScaleType.FIT_CENTER;
            appCompatImageView.setScaleType(scaleType);
            appCompatImageView.setImageResource(R.drawable.st_lid_big);
            ImageView imageView = new ImageView(this.f5449a.f5430a.getContext());
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(m.b(69.0f, 0.0f, 2), m.b(69.0f, 0.0f, 2));
            layoutParams2.gravity = 1;
            layoutParams2.setMargins(0, 0, 0, m.a(16.0f, 0.0f, 2));
            imageView.setLayoutParams(layoutParams2);
            imageView.setScaleType(scaleType);
            imageView.setImageResource(R.drawable.st_moments_reported_icon);
            TextView textView = new TextView(this.f5449a.f5430a.getContext());
            d dVar = this.f5449a;
            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
            layoutParams3.gravity = 1;
            layoutParams3.setMargins(0, 0, 0, m.a(10.0f, 0.0f, 2));
            textView.setLayoutParams(layoutParams3);
            textView.setGravity(1);
            textView.setText(dVar.f5430a.getContext().getString(R.string.stm_reported_feedback));
            textView.setTextColor(-1);
            textView.setTextSize(1, 18.0f);
            textView.setTypeface(textView.getTypeface(), 0);
            TextView textView2 = new TextView(this.f5449a.f5430a.getContext());
            d dVar2 = this.f5449a;
            LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(m.b(301.0f, 0.0f, 2), -2);
            layoutParams4.gravity = 1;
            layoutParams4.setMargins(0, 0, 0, m.a(36.0f, 0.0f, 2));
            textView2.setLayoutParams(layoutParams4);
            textView2.setGravity(1);
            textView2.setText(dVar2.f5430a.getContext().getString(R.string.stm_report_detail_text));
            textView2.setLines(2);
            textView2.setTextColor(-1);
            textView2.setTextSize(1, 14.0f);
            AppCompatButton appCompatButton = new AppCompatButton(this.f5449a.f5430a.getContext());
            d dVar3 = this.f5449a;
            LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-1, (int) m.a(44));
            layoutParams5.setMargins((int) m.a(16), 0, (int) m.a(16), m.b(16.0f, 0.0f, 2));
            layoutParams5.gravity = 1;
            appCompatButton.setLayoutParams(layoutParams5);
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(Color.parseColor("#33FFFFFF"));
            float b3 = (float) m.b(8.0f, 0.0f, 2);
            gradientDrawable.setCornerRadii(new float[]{b3, b3, b3, b3, b3, b3, b3, b3});
            appCompatButton.setBackground(gradientDrawable);
            appCompatButton.setAllCaps(false);
            appCompatButton.setText(dVar3.f5430a.getContext().getString(R.string.stm_report_feedback_close));
            appCompatButton.setTextColor(-1);
            appCompatButton.setTextSize(1, 16.0f);
            appCompatButton.setOnClickListener(new E0.f(dVar3, 1));
            LinearLayout linearLayout = new LinearLayout(this.f5449a.f5430a.getContext());
            linearLayout.setVisibility(4);
            linearLayout.setOrientation(1);
            FrameLayout.LayoutParams layoutParams6 = new FrameLayout.LayoutParams(-1, -2);
            layoutParams6.gravity = 80;
            linearLayout.setLayoutParams(layoutParams6);
            Drawable drawable = AppCompatResources.getDrawable(linearLayout.getContext(), R.drawable.st_rectangle_shape_drawable);
            if (drawable != null) {
                GradientDrawable gradientDrawable2 = (GradientDrawable) ((GradientDrawable) drawable).mutate();
                gradientDrawable2.setColor(Color.parseColor("#212121"));
                float applyDimension = TypedValue.applyDimension(0, 16.0f, linearLayout.getContext().getResources().getDisplayMetrics());
                gradientDrawable2.setCornerRadii(new float[]{applyDimension, applyDimension, applyDimension, applyDimension, 0.0f, 0.0f, 0.0f, 0.0f});
                linearLayout.setBackground(gradientDrawable2);
                linearLayout.addView(appCompatImageView);
                linearLayout.addView(imageView);
                linearLayout.addView(textView);
                linearLayout.addView(textView2);
                linearLayout.addView(appCompatButton);
                return linearLayout;
            }
            throw new NullPointerException("null cannot be cast to non-null type android.graphics.drawable.GradientDrawable");
        }

        public static final void a(d dVar, View view) {
            Intrinsics.checkNotNullParameter(dVar, "this$0");
            KProperty<Object>[] kPropertyArr = d.f5429r;
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(dVar.b(), "y", new float[]{(float) dVar.f5430a.getHeight()});
            ofFloat.setDuration(300);
            Intrinsics.checkNotNullExpressionValue(ofFloat, "");
            ofFloat.addListener(new e(dVar));
            ofFloat.start();
        }
    }
}
