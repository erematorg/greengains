package com.appsamurai.storyly.storylypresenter.storylylayer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import androidx.appcompat.widget.AppCompatImageView;
import com.appsamurai.storyly.R;
import com.appsamurai.storyly.StoryGroupType;
import com.appsamurai.storyly.data.a0;
import com.appsamurai.storyly.data.b0;
import com.appsamurai.storyly.data.v;
import com.appsamurai.storyly.data.x;
import com.appsamurai.storyly.data.z;
import com.appsamurai.storyly.util.ui.l;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CenterInside;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressLint({"ViewConstructor"})
public final class w extends o1 {
    @Nullable

    /* renamed from: g  reason: collision with root package name */
    public final v f6186g;
    @NotNull

    /* renamed from: h  reason: collision with root package name */
    public final List<BitmapTransformation> f6187h = CollectionsKt.listOf(new CenterCrop(), new CenterInside(), new FitCenter());

    /* renamed from: i  reason: collision with root package name */
    public x f6188i;

    /* renamed from: j  reason: collision with root package name */
    public Function0<Unit> f6189j;

    /* renamed from: k  reason: collision with root package name */
    public Function1<? super b0, Unit> f6190k;
    @Nullable

    /* renamed from: l  reason: collision with root package name */
    public z f6191l;
    @NotNull

    /* renamed from: m  reason: collision with root package name */
    public final Lazy f6192m;
    @Nullable

    /* renamed from: n  reason: collision with root package name */
    public Target<?> f6193n;
    @Nullable

    /* renamed from: o  reason: collision with root package name */
    public Pair<Integer, Integer> f6194o;

    public /* synthetic */ class a {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[x.b.values().length];
            iArr[0] = 1;
            iArr[1] = 2;
            iArr[3] = 3;
            iArr[2] = 4;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final class b extends Lambda implements Function0<AppCompatImageView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f6195a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public b(Context context) {
            super(0);
            this.f6195a = context;
        }

        public Object invoke() {
            AppCompatImageView appCompatImageView = new AppCompatImageView(this.f6195a);
            appCompatImageView.setClickable(false);
            return appCompatImageView;
        }
    }

    public static final class c implements RequestListener<Bitmap> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ w f6196a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ v f6197b;

        public c(w wVar, v vVar) {
            this.f6196a = wVar;
            this.f6197b = vVar;
        }

        public static final void a(w wVar) {
            Intrinsics.checkNotNullParameter(wVar, "this$0");
            wVar.getOnLayerLoadFail$storyly_release().invoke();
        }

        public boolean onLoadFailed(@Nullable GlideException glideException, @Nullable Object obj, @Nullable Target<Bitmap> target, boolean z2) {
            new Handler(Looper.getMainLooper()).post(new G0.w(this.f6196a, 0));
            return false;
        }

        public boolean onResourceReady(Object obj, Object obj2, Target target, DataSource dataSource, boolean z2) {
            Bitmap bitmap = (Bitmap) obj;
            if (bitmap != null && this.f6197b.f4228h == StoryGroupType.MomentsDefault) {
                this.f6196a.f6194o = new Pair<>(Integer.valueOf(bitmap.getWidth()), Integer.valueOf(bitmap.getHeight()));
            }
            if (!z2) {
                return false;
            }
            this.f6196a.getOnLayerLoad$storyly_release().invoke();
            return false;
        }
    }

    public static final class d implements RequestListener<Drawable> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ w f6198a;

        public d(w wVar) {
            this.f6198a = wVar;
        }

        public static final void a(w wVar) {
            Intrinsics.checkNotNullParameter(wVar, "this$0");
            wVar.getOnLayerLoadFail$storyly_release().invoke();
        }

        public boolean onLoadFailed(@Nullable GlideException glideException, @Nullable Object obj, @Nullable Target<Drawable> target, boolean z2) {
            new Handler(Looper.getMainLooper()).post(new G0.w(this.f6198a, 1));
            return false;
        }

        public boolean onResourceReady(Object obj, Object obj2, Target target, DataSource dataSource, boolean z2) {
            Drawable drawable = (Drawable) obj;
            if (!z2) {
                return false;
            }
            this.f6198a.getOnImageReady$storyly_release().invoke();
            return false;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public w(@NotNull Context context, @Nullable v vVar) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.f6186g = vVar;
        this.f6192m = LazyKt.lazy(new b(context));
        l.b(this);
    }

    private final AppCompatImageView getImageView() {
        return (AppCompatImageView) this.f6192m.getValue();
    }

    private final void setImageFromSource(x xVar) {
        int[] iArr;
        v vVar;
        int ordinal = xVar.f4271t.ordinal();
        int i3 = 0;
        if (ordinal == 0) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            com.appsamurai.storyly.data.c cVar = xVar.f4255d;
            if (cVar != null) {
                i3 = cVar.f3624a;
            }
            gradientDrawable.setColor(i3);
            getImageView().setBackground(gradientDrawable);
            getOnLayerLoad$storyly_release().invoke();
        } else if (ordinal == 1) {
            GradientDrawable gradientDrawable2 = new GradientDrawable();
            gradientDrawable2.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
            List<com.appsamurai.storyly.data.c> list = xVar.f4256e;
            if (list == null) {
                iArr = null;
            } else {
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
                for (com.appsamurai.storyly.data.c cVar2 : list) {
                    arrayList.add(Integer.valueOf(cVar2.f3624a));
                }
                iArr = CollectionsKt.toIntArray(arrayList);
            }
            if (iArr == null) {
                iArr = new int[]{0};
            }
            gradientDrawable2.setColors(iArr);
            getImageView().setBackground(gradientDrawable2);
            getOnLayerLoad$storyly_release().invoke();
        } else if ((ordinal == 2 || ordinal == 3) && (vVar = this.f6186g) != null) {
            this.f6193n = Glide.with(getContext().getApplicationContext()).asBitmap().load(a.$EnumSwitchMapping$0[xVar.f4271t.ordinal()] == 3 ? Intrinsics.stringPlus(vVar.f4223c, xVar.f4254c) : xVar.f4253b).listener(new c(this, vVar)).preload();
        }
    }

    public void a(@NotNull b0 b0Var) {
        String str;
        Intrinsics.checkNotNullParameter(b0Var, "storylyLayerItem");
        a0 a0Var = b0Var.f3615j;
        x xVar = a0Var instanceof x ? (x) a0Var : null;
        if (xVar != null) {
            setStorylyLayer$storyly_release(xVar);
            setStorylyLayerItem$storyly_release(b0Var);
            Glide.with(getContext().getApplicationContext()).clear((View) getImageView());
            setImageFromSource(getStorylyLayer$storyly_release());
            f();
            setRotation(b0Var.f3613h);
            if (getStorylyLayer$storyly_release().f4259h) {
                setImportantForAccessibility(1);
                z zVar = this.f6191l;
                if (zVar == null || (str = zVar.f4309h) == null) {
                    str = "";
                }
                setContentDescription(str);
            }
        }
    }

    public void c() {
        Target<?> target = this.f6193n;
        if (target != null) {
            Glide.with(getContext().getApplicationContext()).clear(target);
        }
        this.f6193n = null;
        Glide.with(getContext().getApplicationContext()).clear((View) getImageView());
        removeAllViews();
        l.b(this);
    }

    public final void f() {
        if (Intrinsics.areEqual((Object) getStorylyLayerItem$storyly_release().f3606a, (Object) "image_cta")) {
            getImageView().setOnClickListener(new E0.c(this, 6));
            setImportantForAccessibility(1);
            AppCompatImageView imageView = getImageView();
            String str = getStorylyLayer$storyly_release().f4260i;
            if (str == null) {
                str = getResources().getString(R.string.st_desc_imagecta);
            }
            imageView.setContentDescription(str);
        }
    }

    @NotNull
    public final Function0<Unit> getOnImageReady$storyly_release() {
        Function0<Unit> function0 = this.f6189j;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onImageReady");
        return null;
    }

    @NotNull
    public final Function1<b0, Unit> getOnUserActionClick$storyly_release() {
        Function1<? super b0, Unit> function1 = this.f6190k;
        if (function1 != null) {
            return function1;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onUserActionClick");
        return null;
    }

    @Nullable
    public final z getStorylyItem$storyly_release() {
        return this.f6191l;
    }

    @NotNull
    public final x getStorylyLayer$storyly_release() {
        x xVar = this.f6188i;
        if (xVar != null) {
            return xVar;
        }
        Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
        return null;
    }

    public final void setOnImageReady$storyly_release(@NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.f6189j = function0;
    }

    public final void setOnUserActionClick$storyly_release(@NotNull Function1<? super b0, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.f6190k = function1;
    }

    public final void setStorylyItem$storyly_release(@Nullable z zVar) {
        this.f6191l = zVar;
    }

    public final void setStorylyLayer$storyly_release(@NotNull x xVar) {
        Intrinsics.checkNotNullParameter(xVar, "<set-?>");
        this.f6188i = xVar;
    }

    public static final void a(w wVar, View view) {
        Intrinsics.checkNotNullParameter(wVar, "this$0");
        wVar.getOnUserActionClick$storyly_release().invoke(wVar.getStorylyLayerItem$storyly_release());
    }

    /* JADX WARNING: type inference failed for: r0v6, types: [android.graphics.drawable.Drawable] */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0063, code lost:
        r4 = r4.getSecond();
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(@org.jetbrains.annotations.NotNull com.appsamurai.storyly.storylypresenter.storylylayer.d r16) {
        /*
            r15 = this;
            r6 = r15
            r0 = 1
            java.lang.String r1 = "safeFrame"
            r2 = r16
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r1)
            float r1 = r16.b()
            float r7 = r16.a()
            com.appsamurai.storyly.data.x r3 = r15.getStorylyLayer$storyly_release()
            com.appsamurai.storyly.data.x$b r3 = r3.f4271t
            com.appsamurai.storyly.data.x$b r4 = com.appsamurai.storyly.data.x.b.ImagePath
            r8 = 1120403456(0x42c80000, float:100.0)
            r9 = 0
            r10 = 100
            r11 = 2
            if (r3 == r4) goto L_0x0035
            com.appsamurai.storyly.data.x r3 = r15.getStorylyLayer$storyly_release()
            com.appsamurai.storyly.data.x$b r3 = r3.f4271t
            com.appsamurai.storyly.data.x$b r4 = com.appsamurai.storyly.data.x.b.ImageUrl
            if (r3 != r4) goto L_0x002c
            goto L_0x0035
        L_0x002c:
            kotlin.jvm.functions.Function0 r0 = r15.getOnImageReady$storyly_release()
            r0.invoke()
            goto L_0x0113
        L_0x0035:
            com.appsamurai.storyly.data.v r3 = r6.f6186g
            if (r3 != 0) goto L_0x003a
            return
        L_0x003a:
            com.appsamurai.storyly.data.x r4 = r15.getStorylyLayer$storyly_release()
            com.appsamurai.storyly.data.x$b r4 = r4.f4271t
            int[] r5 = com.appsamurai.storyly.storylypresenter.storylylayer.w.a.$EnumSwitchMapping$0
            int r4 = r4.ordinal()
            r4 = r5[r4]
            r5 = 3
            if (r4 != r5) goto L_0x0058
            java.lang.String r3 = r3.f4223c
            com.appsamurai.storyly.data.x r4 = r15.getStorylyLayer$storyly_release()
            java.lang.String r4 = r4.f4254c
            java.lang.String r3 = kotlin.jvm.internal.Intrinsics.stringPlus(r3, r4)
            goto L_0x005e
        L_0x0058:
            com.appsamurai.storyly.data.x r3 = r15.getStorylyLayer$storyly_release()
            java.lang.String r3 = r3.f4253b
        L_0x005e:
            kotlin.Pair<java.lang.Integer, java.lang.Integer> r4 = r6.f6194o
            if (r4 != 0) goto L_0x0063
            goto L_0x006b
        L_0x0063:
            java.lang.Object r4 = r4.getSecond()
            java.lang.Integer r4 = (java.lang.Integer) r4
            if (r4 != 0) goto L_0x006d
        L_0x006b:
            r4 = r9
            goto L_0x0078
        L_0x006d:
            int r4 = r4.intValue()
            float r4 = (float) r4
            float r5 = (float) r11
            float r4 = r4 / r5
            java.lang.Float r4 = java.lang.Float.valueOf(r4)
        L_0x0078:
            if (r4 != 0) goto L_0x0086
            com.appsamurai.storyly.data.b0 r4 = r15.getStorylyLayerItem$storyly_release()
            float r4 = r4.f3610e
            float r5 = (float) r10
            float r4 = r4 / r5
            float r4 = r4 * r7
            float r5 = (float) r11
            float r4 = r4 / r5
            goto L_0x008a
        L_0x0086:
            float r4 = r4.floatValue()
        L_0x008a:
            com.appsamurai.storyly.data.x r5 = r15.getStorylyLayer$storyly_release()
            float r5 = r5.f4257f
            float r5 = r5 / r8
            float r5 = r5 * r4
            int r4 = (int) r5
            android.content.Context r5 = r15.getContext()
            android.content.Context r5 = r5.getApplicationContext()
            com.bumptech.glide.RequestManager r5 = com.bumptech.glide.Glide.with((android.content.Context) r5)
            com.bumptech.glide.RequestBuilder r3 = r5.load((java.lang.String) r3)
            com.bumptech.glide.request.RequestOptions r5 = new com.bumptech.glide.request.RequestOptions
            r5.<init>()
            java.util.List<com.bumptech.glide.load.resource.bitmap.BitmapTransformation> r12 = r6.f6187h
            com.appsamurai.storyly.data.x r13 = r15.getStorylyLayer$storyly_release()
            int r13 = r13.f4252a
            java.lang.Object r12 = r12.get(r13)
            com.bumptech.glide.load.Transformation r12 = (com.bumptech.glide.load.Transformation) r12
            com.bumptech.glide.load.resource.bitmap.RoundedCorners r13 = new com.bumptech.glide.load.resource.bitmap.RoundedCorners
            int r4 = java.lang.Math.max(r0, r4)
            r13.<init>(r4)
            com.bumptech.glide.load.Transformation[] r4 = new com.bumptech.glide.load.Transformation[r11]
            r14 = 0
            r4[r14] = r12
            r4[r0] = r13
            r5.transform((com.bumptech.glide.load.Transformation<android.graphics.Bitmap>[]) r4)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            com.bumptech.glide.RequestBuilder r0 = r3.apply((com.bumptech.glide.request.BaseRequestOptions<?>) r5)
            com.bumptech.glide.load.engine.DiskCacheStrategy r3 = com.bumptech.glide.load.engine.DiskCacheStrategy.ALL
            com.bumptech.glide.request.BaseRequestOptions r0 = r0.diskCacheStrategy(r3)
            com.bumptech.glide.RequestBuilder r0 = (com.bumptech.glide.RequestBuilder) r0
            com.appsamurai.storyly.storylypresenter.storylylayer.w$d r3 = new com.appsamurai.storyly.storylypresenter.storylylayer.w$d
            r3.<init>(r15)
            com.bumptech.glide.RequestBuilder r0 = r0.listener(r3)
            java.lang.String r3 = "override fun updateLayou…derRadius / 100.0f)\n    }"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r3)
            kotlin.Pair<java.lang.Integer, java.lang.Integer> r3 = r6.f6194o
            if (r3 != 0) goto L_0x00ea
            goto L_0x010c
        L_0x00ea:
            java.lang.Object r4 = r3.getFirst()
            java.lang.Integer r4 = (java.lang.Integer) r4
            if (r4 != 0) goto L_0x00f4
            int r4 = (int) r1
            goto L_0x00f8
        L_0x00f4:
            int r4 = r4.intValue()
        L_0x00f8:
            java.lang.Object r3 = r3.getSecond()
            java.lang.Integer r3 = (java.lang.Integer) r3
            if (r3 != 0) goto L_0x0102
            int r3 = (int) r7
            goto L_0x0106
        L_0x0102:
            int r3 = r3.intValue()
        L_0x0106:
            com.bumptech.glide.request.BaseRequestOptions r3 = r0.override(r4, r3)
            com.bumptech.glide.RequestBuilder r3 = (com.bumptech.glide.RequestBuilder) r3
        L_0x010c:
            androidx.appcompat.widget.AppCompatImageView r3 = r15.getImageView()
            r0.into((android.widget.ImageView) r3)
        L_0x0113:
            androidx.appcompat.widget.AppCompatImageView r0 = r15.getImageView()
            android.widget.FrameLayout$LayoutParams r3 = new android.widget.FrameLayout$LayoutParams
            r4 = -1
            r3.<init>(r4, r4)
            r15.addView(r0, r3)
            com.appsamurai.storyly.data.x r0 = r15.getStorylyLayer$storyly_release()
            boolean r0 = r0.f4259h
            if (r0 == 0) goto L_0x012e
            android.widget.FrameLayout$LayoutParams r0 = new android.widget.FrameLayout$LayoutParams
            r0.<init>(r4, r4)
            goto L_0x016e
        L_0x012e:
            android.widget.FrameLayout$LayoutParams r3 = new android.widget.FrameLayout$LayoutParams
            com.appsamurai.storyly.data.b0 r0 = r15.getStorylyLayerItem$storyly_release()
            float r0 = r0.f3609d
            float r4 = (float) r10
            int r0 = A.a.a(r0, r4, r1)
            com.appsamurai.storyly.data.b0 r1 = r15.getStorylyLayerItem$storyly_release()
            float r1 = r1.f3610e
            int r1 = A.a.a(r1, r4, r7)
            r3.<init>(r0, r1)
            com.appsamurai.storyly.data.b0 r0 = r15.getStorylyLayerItem$storyly_release()
            android.graphics.Point r0 = r0.b()
            int r4 = r0.x
            com.appsamurai.storyly.data.b0 r0 = r15.getStorylyLayerItem$storyly_release()
            android.graphics.Point r0 = r0.b()
            int r5 = r0.y
            float r12 = r16.c()
            float r13 = r16.d()
            r0 = r15
            r1 = r3
            r2 = r4
            r3 = r5
            r4 = r12
            r5 = r13
            android.widget.FrameLayout$LayoutParams r0 = r0.a(r1, r2, r3, r4, r5)
        L_0x016e:
            r15.setLayoutParams(r0)
            androidx.appcompat.widget.AppCompatImageView r0 = r15.getImageView()
            android.graphics.drawable.Drawable r0 = r0.getBackground()
            boolean r1 = r0 instanceof android.graphics.drawable.GradientDrawable
            if (r1 == 0) goto L_0x0180
            r9 = r0
            android.graphics.drawable.GradientDrawable r9 = (android.graphics.drawable.GradientDrawable) r9
        L_0x0180:
            if (r9 != 0) goto L_0x0183
            goto L_0x0199
        L_0x0183:
            com.appsamurai.storyly.data.b0 r0 = r15.getStorylyLayerItem$storyly_release()
            float r0 = r0.f3610e
            float r1 = (float) r10
            float r0 = r0 / r1
            float r0 = r0 * r7
            float r1 = (float) r11
            float r0 = r0 / r1
            com.appsamurai.storyly.data.x r1 = r15.getStorylyLayer$storyly_release()
            float r1 = r1.f4257f
            float r1 = r1 / r8
            float r1 = r1 * r0
            r9.setCornerRadius(r1)
        L_0x0199:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.storylypresenter.storylylayer.w.a(com.appsamurai.storyly.storylypresenter.storylylayer.d):void");
    }
}
