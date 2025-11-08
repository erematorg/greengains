package com.appsamurai.storyly.storylypresenter.storylylayer;

import A.a;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import com.appsamurai.storyly.ad.StorylyAdView;
import com.appsamurai.storyly.data.b0;
import com.appsamurai.storyly.data.o;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class g extends o1 {

    /* renamed from: g  reason: collision with root package name */
    public o f5734g;
    @Nullable

    /* renamed from: h  reason: collision with root package name */
    public StorylyAdView f5735h;

    /* renamed from: i  reason: collision with root package name */
    public Function1<? super Integer, Unit> f5736i;

    /* renamed from: j  reason: collision with root package name */
    public Function1<? super b0, Unit> f5737j;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public g(@NotNull Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public void a(@NotNull d dVar) {
        Intrinsics.checkNotNullParameter(dVar, "safeFrame");
        float f2 = (float) 100;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(a.a(getStorylyLayerItem$storyly_release().f3609d, f2, dVar.b()), a.a(getStorylyLayerItem$storyly_release().f3610e, f2, dVar.a()));
        layoutParams.setMarginStart(getStorylyLayerItem$storyly_release().b().x);
        layoutParams.topMargin = getStorylyLayerItem$storyly_release().b().y;
        setLayoutParams(layoutParams);
        measure(0, 0);
        addView(this.f5735h, new FrameLayout.LayoutParams(-1, -1));
    }

    public void b() {
        StorylyAdView storylyAdView = this.f5735h;
        if (storylyAdView != null) {
            storylyAdView.pause();
        }
    }

    public void c() {
        super.c();
        removeAllViews();
        StorylyAdView storylyAdView = this.f5735h;
        if (storylyAdView != null) {
            storylyAdView.setOnActionClicked((Function0<Unit>) null);
        }
        StorylyAdView storylyAdView2 = this.f5735h;
        if (storylyAdView2 != null) {
            storylyAdView2.reset();
        }
        this.f5735h = null;
    }

    public void e() {
        StorylyAdView storylyAdView = this.f5735h;
        if (storylyAdView != null) {
            storylyAdView.resume();
        }
    }

    @NotNull
    public final Function1<Integer, Unit> getOnAdReady$storyly_release() {
        Function1<? super Integer, Unit> function1 = this.f5736i;
        if (function1 != null) {
            return function1;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onAdReady");
        return null;
    }

    @NotNull
    public final Function1<b0, Unit> getOnUserActionClick$storyly_release() {
        Function1<? super b0, Unit> function1 = this.f5737j;
        if (function1 != null) {
            return function1;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onUserActionClick");
        return null;
    }

    public final void setLayers(@NotNull Map<String, ? extends View> map) {
        Intrinsics.checkNotNullParameter(map, "layers");
        StorylyAdView storylyAdView = this.f5735h;
        if (storylyAdView != null) {
            storylyAdView.setLayers(map);
        }
    }

    public final void setOnAdReady$storyly_release(@NotNull Function1<? super Integer, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.f5736i = function1;
    }

    public final void setOnUserActionClick$storyly_release(@NotNull Function1<? super b0, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.f5737j = function1;
    }
}
