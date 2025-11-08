package com.appsamurai.storyly.storylypresenter.cart.sheet;

import android.animation.StateListAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.ViewCompat;
import com.appsamurai.storyly.R;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.util.m;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class j extends BottomSheetDialog {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final StorylyConfig f4941a;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    public final String f4942b;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public Function0<Unit> f4943c;
    @NotNull

    /* renamed from: d  reason: collision with root package name */
    public final Lazy f4944d;
    @NotNull

    /* renamed from: e  reason: collision with root package name */
    public final Lazy f4945e;
    @NotNull

    /* renamed from: f  reason: collision with root package name */
    public final Lazy f4946f;
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public final Lazy f4947g;
    @NotNull

    /* renamed from: h  reason: collision with root package name */
    public final Lazy f4948h;

    public static final class b extends Lambda implements Function0<LinearLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f4951a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public b(Context context) {
            super(0);
            this.f4951a = context;
        }

        public Object invoke() {
            LinearLayout linearLayout = new LinearLayout(this.f4951a);
            linearLayout.setOrientation(1);
            return linearLayout;
        }
    }

    public static final class c extends Lambda implements Function0<LinearLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f4952a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public c(Context context) {
            super(0);
            this.f4952a = context;
        }

        public Object invoke() {
            LinearLayout linearLayout = new LinearLayout(this.f4952a);
            linearLayout.setOrientation(1);
            linearLayout.setGravity(17);
            return linearLayout;
        }
    }

    public static final class d extends Lambda implements Function0<AppCompatImageView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f4953a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public d(Context context) {
            super(0);
            this.f4953a = context;
        }

        public Object invoke() {
            AppCompatImageView appCompatImageView = new AppCompatImageView(this.f4953a);
            appCompatImageView.setImageResource(R.drawable.st_basket);
            appCompatImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            appCompatImageView.setColorFilter(ViewCompat.MEASURED_STATE_MASK, PorterDuff.Mode.SRC_IN);
            return appCompatImageView;
        }
    }

    public static final class e extends Lambda implements Function0<AppCompatTextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f4954a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public e(Context context) {
            super(0);
            this.f4954a = context;
        }

        public Object invoke() {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f4954a);
            appCompatTextView.setEllipsize(TextUtils.TruncateAt.END);
            appCompatTextView.setMaxLines(3);
            appCompatTextView.setMinLines(1);
            appCompatTextView.setIncludeFontPadding(false);
            appCompatTextView.setHorizontallyScrolling(false);
            appCompatTextView.setGravity(17);
            appCompatTextView.setTextColor(Color.parseColor("#212121"));
            appCompatTextView.setTextAlignment(1);
            com.appsamurai.storyly.util.d.a(appCompatTextView);
            return appCompatTextView;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public j(@NotNull Context context, @NotNull StorylyConfig storylyConfig, @Nullable String str, @NotNull Function0<Unit> function0) {
        super(context, R.style.StorylySuccessSheetTheme);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        Intrinsics.checkNotNullParameter(function0, "onDismiss");
        this.f4941a = storylyConfig;
        this.f4942b = str;
        this.f4943c = function0;
        this.f4944d = LazyKt.lazy(new b(context));
        this.f4945e = LazyKt.lazy(new c(context));
        this.f4946f = LazyKt.lazy(new d(context));
        this.f4947g = LazyKt.lazy(new e(context));
        this.f4948h = LazyKt.lazy(new a(context, this));
        c();
        LinearLayout a2 = a();
        Class cls = Integer.TYPE;
        ViewGroup.LayoutParams newInstance = LinearLayout.LayoutParams.class.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-1, -2});
        Intrinsics.checkNotNullExpressionValue(newInstance, "layoutParams");
        ((LinearLayout.LayoutParams) newInstance).gravity = 80;
        Unit unit = Unit.INSTANCE;
        setContentView(a2, newInstance);
    }

    public final LinearLayout a() {
        return (LinearLayout) this.f4944d.getValue();
    }

    public final LinearLayout b() {
        return (LinearLayout) this.f4945e.getValue();
    }

    public final void c() {
        int width = (int) (((double) m.b().width()) * 0.388d);
        int width2 = (int) (((double) m.b().width()) * 0.0445d);
        int width3 = (int) (((double) m.b().width()) * 0.07d);
        double d2 = (double) width;
        double d3 = 0.135d * d2;
        int i3 = (int) (d2 * 0.28d);
        int width4 = (int) (((double) m.b().width()) * 0.07d);
        int width5 = (int) (((double) m.b().width()) * 0.0445d);
        int width6 = (int) (((double) m.b().width()) * 0.11388888888d);
        int width7 = (int) (((double) m.b().width()) * 0.027265d);
        double d4 = (double) width6;
        double d5 = d3;
        LinearLayout a2 = a();
        LinearLayout b3 = b();
        Class cls = Integer.TYPE;
        Class<LinearLayout.LayoutParams> cls2 = LinearLayout.LayoutParams.class;
        ViewGroup.LayoutParams newInstance = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-1, Integer.valueOf(width)});
        Intrinsics.checkNotNullExpressionValue(newInstance, "layoutParams");
        int i4 = (int) (((double) i3) * 0.3d);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) newInstance;
        layoutParams.topMargin = width3;
        layoutParams.bottomMargin = width3;
        layoutParams.leftMargin = width2;
        layoutParams.rightMargin = width2;
        Unit unit = Unit.INSTANCE;
        a2.addView(b3, newInstance);
        ViewGroup.LayoutParams newInstance2 = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-1, Integer.valueOf(width6)});
        Intrinsics.checkNotNullExpressionValue(newInstance2, "layoutParams");
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) newInstance2;
        layoutParams2.bottomMargin = width4;
        layoutParams2.topMargin = width7;
        layoutParams2.leftMargin = width5;
        layoutParams2.rightMargin = width5;
        a().addView((AppCompatButton) this.f4948h.getValue(), newInstance2);
        LinearLayout b4 = b();
        ViewGroup.LayoutParams newInstance3 = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{Integer.valueOf(i3), Integer.valueOf(i3)});
        Intrinsics.checkNotNullExpressionValue(newInstance3, "layoutParams");
        ((LinearLayout.LayoutParams) newInstance3).topMargin = i4;
        b4.addView((AppCompatImageView) this.f4946f.getValue(), newInstance3);
        ViewGroup.LayoutParams newInstance4 = cls2.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-1, -2});
        Intrinsics.checkNotNullExpressionValue(newInstance4, "layoutParams");
        LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) newInstance4;
        layoutParams3.weight = 1.0f;
        int i5 = width2 * 2;
        layoutParams3.leftMargin = i5;
        layoutParams3.rightMargin = i5;
        b4.addView((AppCompatTextView) this.f4947g.getValue(), newInstance4);
        LinearLayout b5 = b();
        b5.setBackground(com.appsamurai.storyly.util.ui.b.a(b5, Color.parseColor("#F5F5F5"), (float) (0.1d * d2), Integer.valueOf(Color.parseColor("#F5F5F5")), 0));
        AppCompatTextView appCompatTextView = (AppCompatTextView) this.f4947g.getValue();
        appCompatTextView.setText(appCompatTextView.getContext().getString(R.string.st_empty_sheet_desc));
        appCompatTextView.setTypeface(this.f4941a.getStory$storyly_release().getInteractiveTypeface$storyly_release());
        double d6 = d5;
        appCompatTextView.setLineHeight((int) d6);
        appCompatTextView.setTextSize(0, (float) (d6 * 0.85d));
        AppCompatButton appCompatButton = (AppCompatButton) this.f4948h.getValue();
        appCompatButton.setText(this.f4942b);
        appCompatButton.setTypeface(this.f4941a.getStory$storyly_release().getInteractiveTypeface$storyly_release());
        appCompatButton.setTextSize(0, (float) (d4 * 0.414d * 0.85d));
        appCompatButton.setBackground(com.appsamurai.storyly.util.ui.b.a(appCompatButton, ViewCompat.MEASURED_STATE_MASK, (float) (width6 / 2), Integer.valueOf(Color.parseColor("#E0E0E0")), (int) (d4 * 0.024d)));
    }

    public void dismiss() {
        super.dismiss();
        this.f4943c.invoke();
        b().removeAllViews();
        a().removeAllViews();
    }

    public static final class a extends Lambda implements Function0<AppCompatButton> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f4949a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ j f4950b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(Context context, j jVar) {
            super(0);
            this.f4949a = context;
            this.f4950b = jVar;
        }

        @NotNull
        /* renamed from: a */
        public final AppCompatButton invoke() {
            AppCompatButton appCompatButton = new AppCompatButton(this.f4949a);
            j jVar = this.f4950b;
            appCompatButton.setAllCaps(false);
            appCompatButton.setMaxLines(1);
            appCompatButton.setMinLines(1);
            appCompatButton.setEllipsize(TextUtils.TruncateAt.END);
            appCompatButton.setIncludeFontPadding(false);
            appCompatButton.setHorizontallyScrolling(false);
            appCompatButton.setGravity(17);
            appCompatButton.setTextColor(-1);
            appCompatButton.setTextAlignment(1);
            com.appsamurai.storyly.util.d.a(appCompatButton);
            appCompatButton.setStateListAnimator((StateListAnimator) null);
            appCompatButton.setOnClickListener(new E0.c(jVar, 9));
            return appCompatButton;
        }

        public static final void a(j jVar, View view) {
            Intrinsics.checkNotNullParameter(jVar, "this$0");
            jVar.dismiss();
        }
    }
}
