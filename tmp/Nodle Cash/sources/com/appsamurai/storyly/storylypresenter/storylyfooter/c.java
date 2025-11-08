package com.appsamurai.storyly.storylypresenter.storylyfooter;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatImageView;
import com.appsamurai.storyly.R;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.config.styling.moments.StorylyMomentsIconStyling;
import com.appsamurai.storyly.util.m;
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

public final class c extends FrameLayout {

    /* renamed from: e  reason: collision with root package name */
    public static final /* synthetic */ KProperty<Object>[] f5419e = {androidx.compose.ui.autofill.a.m(c.class, "viewStats", "getViewStats$storyly_release()Ljava/lang/Integer;", 0)};
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final ReadWriteProperty f5420a = new b((Object) null, (Object) null, this);
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final Lazy f5421b;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public final Lazy f5422c;
    @NotNull

    /* renamed from: d  reason: collision with root package name */
    public final Lazy f5423d;

    public static final class a extends Lambda implements Function0<LinearLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5424a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(Context context) {
            super(0);
            this.f5424a = context;
        }

        public Object invoke() {
            LinearLayout linearLayout = new LinearLayout(this.f5424a);
            linearLayout.setGravity(16);
            linearLayout.setOrientation(0);
            return linearLayout;
        }
    }

    public static final class b extends ObservableProperty<Integer> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ c f5425a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public b(Object obj, Object obj2, c cVar) {
            super(null);
            this.f5425a = cVar;
        }

        public void afterChange(@NotNull KProperty<?> kProperty, Integer num, Integer num2) {
            Intrinsics.checkNotNullParameter(kProperty, "property");
            Integer num3 = num2;
            Integer num4 = num;
            TextView a2 = this.f5425a.getViewCountText();
            Integer viewStats$storyly_release = this.f5425a.getViewStats$storyly_release();
            a2.setText(String.valueOf(viewStats$storyly_release == null ? null : Integer.valueOf(viewStats$storyly_release.intValue() + 1)));
        }
    }

    /* renamed from: com.appsamurai.storyly.storylypresenter.storylyfooter.c$c  reason: collision with other inner class name */
    public static final class C0038c extends Lambda implements Function0<TextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5426a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public C0038c(Context context) {
            super(0);
            this.f5426a = context;
        }

        public Object invoke() {
            TextView textView = new TextView(this.f5426a);
            textView.setMaxLines(1);
            textView.setTextColor(-1);
            textView.setTextSize(1, 14.0f);
            textView.setTypeface(Typeface.DEFAULT);
            textView.setLineSpacing((float) m.a((Number) Double.valueOf(4.83d)), 1.0f);
            return textView;
        }
    }

    public static final class d extends Lambda implements Function0<AppCompatImageView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5427a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ StorylyConfig f5428b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public d(Context context, StorylyConfig storylyConfig) {
            super(0);
            this.f5427a = context;
            this.f5428b = storylyConfig;
        }

        public Object invoke() {
            AppCompatImageView appCompatImageView = new AppCompatImageView(this.f5427a);
            StorylyConfig storylyConfig = this.f5428b;
            Context context = this.f5427a;
            appCompatImageView.setMaxHeight(m.a((Number) 20));
            appCompatImageView.setMaxWidth(m.a((Number) 20));
            appCompatImageView.setAdjustViewBounds(true);
            StorylyMomentsIconStyling iconStyling$storyly_release = storylyConfig.getMoments$storyly_release().getIconStyling$storyly_release();
            Drawable storyViewCountIcon$storyly_release = iconStyling$storyly_release == null ? null : iconStyling$storyly_release.getStoryViewCountIcon$storyly_release();
            if (storyViewCountIcon$storyly_release == null) {
                storyViewCountIcon$storyly_release = AppCompatResources.getDrawable(context, R.drawable.st_moments_analytics_eye);
            }
            appCompatImageView.setImageDrawable(storyViewCountIcon$storyly_release);
            return appCompatImageView;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public c(@NotNull Context context, @NotNull StorylyConfig storylyConfig) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        Delegates delegates = Delegates.INSTANCE;
        this.f5421b = LazyKt.lazy(new a(context));
        this.f5422c = LazyKt.lazy(new d(context, storylyConfig));
        this.f5423d = LazyKt.lazy(new C0038c(context));
        setClickable(false);
        addView(getLayerView(), new FrameLayout.LayoutParams(-2, -2));
        LinearLayout layerView = getLayerView();
        AppCompatImageView viewIcon = getViewIcon();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
        layoutParams.setMarginEnd(m.a((Number) 5));
        Unit unit = Unit.INSTANCE;
        layerView.addView(viewIcon, layoutParams);
        getLayerView().addView(getViewCountText(), new LinearLayout.LayoutParams(-2, -2));
        setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
    }

    private final LinearLayout getLayerView() {
        return (LinearLayout) this.f5421b.getValue();
    }

    /* access modifiers changed from: private */
    public final TextView getViewCountText() {
        return (TextView) this.f5423d.getValue();
    }

    private final AppCompatImageView getViewIcon() {
        return (AppCompatImageView) this.f5422c.getValue();
    }

    @Nullable
    public final Integer getViewStats$storyly_release() {
        return (Integer) this.f5420a.getValue(this, f5419e[0]);
    }

    public final void setViewStats$storyly_release(@Nullable Integer num) {
        this.f5420a.setValue(this, f5419e[0], num);
    }
}
