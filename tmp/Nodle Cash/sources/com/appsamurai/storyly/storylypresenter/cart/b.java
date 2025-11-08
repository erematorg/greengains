package com.appsamurai.storyly.storylypresenter.cart;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.ViewCompat;
import androidx.core.widget.ImageViewCompat;
import com.appsamurai.storyly.R;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.util.m;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Lazy;
import kotlin.LazyKt;
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

@SuppressLint({"ViewConstructor"})
public final class b {

    /* renamed from: i  reason: collision with root package name */
    public static final /* synthetic */ KProperty<Object>[] f4775i = {androidx.compose.ui.autofill.a.m(b.class, FirebaseAnalytics.Param.QUANTITY, "getQuantity$storyly_release()Ljava/lang/Integer;", 0)};
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final ViewGroup f4776a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final StorylyConfig f4777b;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public final ReadWriteProperty f4778c = new f((Object) null, (Object) null, this);
    @NotNull

    /* renamed from: d  reason: collision with root package name */
    public final Lazy f4779d = LazyKt.lazy(new a(this));
    @NotNull

    /* renamed from: e  reason: collision with root package name */
    public final Lazy f4780e = LazyKt.lazy(new C0018b(this));
    @NotNull

    /* renamed from: f  reason: collision with root package name */
    public final Lazy f4781f = LazyKt.lazy(new e(this));
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public final Lazy f4782g = LazyKt.lazy(new d(this));
    @NotNull

    /* renamed from: h  reason: collision with root package name */
    public final Lazy f4783h = LazyKt.lazy(new c(this));

    public static final class a extends Lambda implements Function0<LinearLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ b f4784a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(b bVar) {
            super(0);
            this.f4784a = bVar;
        }

        public Object invoke() {
            LinearLayout linearLayout = new LinearLayout(this.f4784a.f4776a.getContext());
            linearLayout.setOrientation(0);
            linearLayout.setGravity(16);
            return linearLayout;
        }
    }

    /* renamed from: com.appsamurai.storyly.storylypresenter.cart.b$b  reason: collision with other inner class name */
    public static final class C0018b extends Lambda implements Function0<AppCompatImageView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ b f4785a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public C0018b(b bVar) {
            super(0);
            this.f4785a = bVar;
        }

        public Object invoke() {
            AppCompatImageView appCompatImageView = new AppCompatImageView(this.f4785a.f4776a.getContext());
            appCompatImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            appCompatImageView.setImageResource(R.drawable.st_basket);
            ImageViewCompat.setImageTintList(appCompatImageView, ColorStateList.valueOf(ViewCompat.MEASURED_STATE_MASK));
            return appCompatImageView;
        }
    }

    public static final class c extends Lambda implements Function0<AppCompatTextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ b f4786a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public c(b bVar) {
            super(0);
            this.f4786a = bVar;
        }

        public Object invoke() {
            return b.a(this.f4786a);
        }
    }

    public static final class d extends Lambda implements Function0<AppCompatTextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ b f4787a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public d(b bVar) {
            super(0);
            this.f4787a = bVar;
        }

        public Object invoke() {
            return b.a(this.f4787a);
        }
    }

    public static final class e extends Lambda implements Function0<FrameLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ b f4788a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public e(b bVar) {
            super(0);
            this.f4788a = bVar;
        }

        public Object invoke() {
            FrameLayout frameLayout = new FrameLayout(this.f4788a.f4776a.getContext());
            frameLayout.setVisibility(8);
            return frameLayout;
        }
    }

    public static final class f extends ObservableProperty<Integer> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ b f4789a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public f(Object obj, Object obj2, b bVar) {
            super(null);
            this.f4789a = bVar;
        }

        public void afterChange(@NotNull KProperty<?> kProperty, Integer num, Integer num2) {
            Intrinsics.checkNotNullParameter(kProperty, "property");
            Integer num3 = num2;
            Integer num4 = num;
            FrameLayout e3 = this.f4789a.e();
            int i3 = 0;
            if ((num3 == null ? 0 : num3.intValue()) <= 0) {
                i3 = 8;
            }
            e3.setVisibility(i3);
            if (!Intrinsics.areEqual((Object) num3, (Object) num4)) {
                if (num4 == null || num3 == null) {
                    this.f4789a.c().setText(String.valueOf(num3));
                    this.f4789a.b().setText(String.valueOf(num3));
                    return;
                }
                b.a(this.f4789a, num4.intValue(), num3.intValue());
            }
        }
    }

    public b(@NotNull ViewGroup viewGroup, @NotNull StorylyConfig storylyConfig) {
        Intrinsics.checkNotNullParameter(viewGroup, "holder");
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        this.f4776a = viewGroup;
        this.f4777b = storylyConfig;
        Delegates delegates = Delegates.INSTANCE;
        f();
    }

    public static final AppCompatTextView a(b bVar) {
        bVar.getClass();
        AppCompatTextView appCompatTextView = new AppCompatTextView(bVar.f4776a.getContext());
        appCompatTextView.setEllipsize(TextUtils.TruncateAt.END);
        appCompatTextView.setSingleLine(true);
        appCompatTextView.setTextColor(-1);
        appCompatTextView.setIncludeFontPadding(false);
        appCompatTextView.setText(String.valueOf(bVar.d()));
        appCompatTextView.setHorizontallyScrolling(false);
        appCompatTextView.setTextAlignment(4);
        appCompatTextView.setGravity(17);
        com.appsamurai.storyly.util.d.a(appCompatTextView);
        return appCompatTextView;
    }

    public final AppCompatTextView b() {
        return (AppCompatTextView) this.f4783h.getValue();
    }

    public final AppCompatTextView c() {
        return (AppCompatTextView) this.f4782g.getValue();
    }

    @Nullable
    public final Integer d() {
        return (Integer) this.f4778c.getValue(this, f4775i[0]);
    }

    public final FrameLayout e() {
        return (FrameLayout) this.f4781f.getValue();
    }

    public final void f() {
        int height = (int) (((double) m.c().height()) * 0.0625d);
        int height2 = (int) (((double) m.c().height()) * 0.12d);
        double d2 = (double) height;
        int i3 = (int) (0.025d * d2);
        float f2 = (float) (height / 2);
        int i4 = (int) (0.5d * d2);
        int i5 = (int) (0.275d * d2);
        int i6 = (int) (0.6d * d2);
        int i7 = (int) (0.2d * d2);
        int i8 = (int) (0.225d * d2);
        double d3 = d2 * 0.325d;
        float f3 = (float) (i6 / 2);
        int i9 = i8;
        int i10 = i7;
        a().setBackground(com.appsamurai.storyly.util.ui.b.a(this.f4776a, -1, 0.0f, f2, f2, 0.0f, Integer.valueOf(Color.parseColor("#EEEEEE")), i3));
        e().setBackground(com.appsamurai.storyly.util.ui.b.a(this.f4776a, ViewCompat.MEASURED_STATE_MASK, f3, f3, f3, f3, (Integer) null, 0, 96));
        ViewGroup.LayoutParams layoutParams = this.f4776a.getLayoutParams();
        if (layoutParams != null) {
            ((ViewGroup.MarginLayoutParams) layoutParams).setMargins(0, height2, 0, 0);
            ViewGroup viewGroup = this.f4776a;
            LinearLayout a2 = a();
            Class cls = Integer.TYPE;
            ViewGroup.LayoutParams newInstance = ViewGroup.LayoutParams.class.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-2, Integer.valueOf(height)});
            Intrinsics.checkNotNullExpressionValue(newInstance, "layoutParams");
            Unit unit = Unit.INSTANCE;
            viewGroup.addView(a2, newInstance);
            Class<LinearLayout.LayoutParams> cls2 = LinearLayout.LayoutParams.class;
            ViewGroup.LayoutParams newInstance2 = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{Integer.valueOf(i4), Integer.valueOf(i4)});
            Intrinsics.checkNotNullExpressionValue(newInstance2, "layoutParams");
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) newInstance2;
            layoutParams2.leftMargin = i5;
            layoutParams2.rightMargin = i5;
            a().addView((AppCompatImageView) this.f4780e.getValue(), newInstance2);
            LinearLayout a3 = a();
            FrameLayout e3 = e();
            ViewGroup.LayoutParams newInstance3 = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-2, Integer.valueOf(i6)});
            Intrinsics.checkNotNullExpressionValue(newInstance3, "layoutParams");
            ((LinearLayout.LayoutParams) newInstance3).rightMargin = i10;
            a3.addView(e3, newInstance3);
            FrameLayout e4 = e();
            AppCompatTextView c3 = c();
            ViewGroup.LayoutParams newInstance4 = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-2, Integer.valueOf(i6)});
            Intrinsics.checkNotNullExpressionValue(newInstance4, "layoutParams");
            e4.addView(c3, newInstance4);
            AppCompatTextView b3 = b();
            ViewGroup.LayoutParams newInstance5 = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-2, Integer.valueOf(i6)});
            Intrinsics.checkNotNullExpressionValue(newInstance5, "layoutParams");
            e4.addView(b3, newInstance5);
            AppCompatTextView c4 = c();
            c4.setTypeface(this.f4777b.getStory$storyly_release().getInteractiveTypeface$storyly_release());
            int i11 = i9;
            c4.setPadding(i11, 0, i11, 0);
            float f4 = (float) d3;
            c4.setTextSize(0, f4);
            AppCompatTextView b4 = b();
            b4.setTypeface(this.f4777b.getStory$storyly_release().getInteractiveTypeface$storyly_release());
            b4.setPadding(i11, 0, i11, 0);
            b4.setTextSize(0, f4);
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
    }

    public final LinearLayout a() {
        return (LinearLayout) this.f4779d.getValue();
    }

    public static final void a(b bVar, int i3, int i4) {
        int i5 = i4 > i3 ? i4 - i3 > 3 ? i4 - 3 : i3 + 1 : i3 - i4 > 3 ? i4 + 3 : i3 - 1;
        bVar.b().setText(String.valueOf(i5));
        float f2 = 100.0f;
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, i4 >= i3 ? -100.0f : 100.0f);
        translateAnimation.setDuration(400);
        if (i4 < i3) {
            f2 = -100.0f;
        }
        TranslateAnimation translateAnimation2 = new TranslateAnimation(0.0f, 0.0f, f2, 0.0f);
        translateAnimation2.setDuration(400);
        translateAnimation2.setAnimationListener(new a(bVar, i5, i4));
        bVar.c().startAnimation(translateAnimation);
        bVar.b().startAnimation(translateAnimation2);
    }
}
