package com.appsamurai.storyly.storylypresenter.storylylayer.storylyProductList;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import androidx.core.view.ViewCompat;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.data.a0;
import com.appsamurai.storyly.data.b0;
import com.appsamurai.storyly.data.c;
import com.appsamurai.storyly.data.h0;
import com.appsamurai.storyly.data.j;
import com.appsamurai.storyly.data.m;
import com.appsamurai.storyly.data.managers.product.STRProductItem;
import com.appsamurai.storyly.storylypresenter.storylylayer.d;
import com.appsamurai.storyly.storylypresenter.storylylayer.o1;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.math.MathKt;
import org.jetbrains.annotations.NotNull;

public final class i extends o1 {
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public final StorylyConfig f6096g;

    /* renamed from: h  reason: collision with root package name */
    public h0 f6097h;

    /* renamed from: i  reason: collision with root package name */
    public Function0<Unit> f6098i;

    /* renamed from: j  reason: collision with root package name */
    public Function0<Unit> f6099j;

    /* renamed from: k  reason: collision with root package name */
    public Function1<? super b0, Unit> f6100k;
    @NotNull

    /* renamed from: l  reason: collision with root package name */
    public final Lazy f6101l;

    public static final class a extends Lambda implements Function0<g> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f6102a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ i f6103b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(Context context, i iVar) {
            super(0);
            this.f6102a = context;
            this.f6103b = iVar;
        }

        public Object invoke() {
            g gVar = new g(this.f6102a, (AttributeSet) null, 0, this.f6103b.f6096g);
            i iVar = this.f6103b;
            gVar.setOnUserInteractionStarted$storyly_release(iVar.getOnUserInteractionStarted$storyly_release());
            gVar.setOnUserInteractionEnded$storyly_release(iVar.getOnUserInteractionEnded$storyly_release());
            gVar.setOnProductClick$storyly_release(new h(iVar));
            return gVar;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public i(@NotNull Context context, @NotNull StorylyConfig storylyConfig) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        this.f6096g = storylyConfig;
        this.f6101l = LazyKt.lazy(new a(context, this));
    }

    private final g getRecyclerView() {
        return (g) this.f6101l.getValue();
    }

    public void a(@NotNull b0 b0Var) {
        Map<j, List<STRProductItem>> map;
        b0 b0Var2 = b0Var;
        Intrinsics.checkNotNullParameter(b0Var2, "storylyLayerItem");
        setAlpha(0.0f);
        a0 a0Var = b0Var2.f3615j;
        List list = null;
        h0 h0Var = a0Var instanceof h0 ? (h0) a0Var : null;
        if (h0Var != null) {
            this.f6097h = h0Var;
            setStorylyLayerItem$storyly_release(b0Var);
            g recyclerView = getRecyclerView();
            h0 h0Var2 = this.f6097h;
            if (h0Var2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                h0Var2 = null;
            }
            c cVar = h0Var2.f3726a;
            if (cVar == null) {
                cVar = new c(-1);
            }
            int i3 = cVar.f3624a;
            h0 h0Var3 = this.f6097h;
            if (h0Var3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                h0Var3 = null;
            }
            c cVar2 = h0Var3.f3727b;
            if (cVar2 == null) {
                cVar2 = com.appsamurai.storyly.config.styling.a.COLOR_EEEEEE.b();
            }
            int i4 = cVar2.f3624a;
            h0 h0Var4 = this.f6097h;
            if (h0Var4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                h0Var4 = null;
            }
            c cVar3 = h0Var4.f3729d;
            if (cVar3 == null) {
                cVar3 = new c(ViewCompat.MEASURED_STATE_MASK);
            }
            int i5 = cVar3.f3624a;
            h0 h0Var5 = this.f6097h;
            if (h0Var5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                h0Var5 = null;
            }
            c cVar4 = h0Var5.f3730e;
            if (cVar4 == null) {
                cVar4 = com.appsamurai.storyly.config.styling.a.COLOR_9E9E9E.b();
            }
            int i6 = cVar4.f3624a;
            h0 h0Var6 = this.f6097h;
            if (h0Var6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                h0Var6 = null;
            }
            c cVar5 = h0Var6.f3728c;
            if (cVar5 == null) {
                cVar5 = new c(ViewCompat.MEASURED_STATE_MASK);
            }
            int i7 = cVar5.f3624a;
            h0 h0Var7 = this.f6097h;
            if (h0Var7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                h0Var7 = null;
            }
            boolean z2 = h0Var7.f3735j;
            h0 h0Var8 = this.f6097h;
            if (h0Var8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                h0Var8 = null;
            }
            boolean z3 = h0Var8.f3734i;
            h0 h0Var9 = this.f6097h;
            if (h0Var9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                h0Var9 = null;
            }
            boolean z4 = h0Var9.f3732g;
            h0 h0Var10 = this.f6097h;
            if (h0Var10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                h0Var10 = null;
            }
            String str = h0Var10.f3731f;
            Integer valueOf = Integer.valueOf(i4);
            a aVar = r13;
            a aVar2 = new a(valueOf, Integer.valueOf(i3), Integer.valueOf(i7), Integer.valueOf(i5), Integer.valueOf(i6), str, Boolean.valueOf(z3), Boolean.valueOf(z2), Boolean.valueOf(z4));
            recyclerView.setupEntity(aVar);
            g recyclerView2 = getRecyclerView();
            h0 h0Var11 = this.f6097h;
            if (h0Var11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                h0Var11 = null;
            }
            m mVar = h0Var11.f3733h;
            if (!(mVar == null || (map = mVar.f3859a) == null)) {
                ArrayList arrayList = new ArrayList();
                for (Map.Entry<j, List<STRProductItem>> value : map.entrySet()) {
                    List list2 = (List) value.getValue();
                    if (list2 != null) {
                        arrayList.add(list2);
                    }
                }
                list = CollectionsKt.toList(arrayList);
            }
            if (list == null) {
                list = CollectionsKt.emptyList();
            }
            recyclerView2.setup(list);
            getOnLayerLoad$storyly_release().invoke();
        }
    }

    public void c() {
        super.c();
        removeAllViews();
    }

    @NotNull
    public final Function1<b0, Unit> getOnProductClick$storyly_release() {
        Function1<? super b0, Unit> function1 = this.f6100k;
        if (function1 != null) {
            return function1;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onProductClick");
        return null;
    }

    @NotNull
    public final Function0<Unit> getOnUserInteractionEnded$storyly_release() {
        Function0<Unit> function0 = this.f6099j;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onUserInteractionEnded");
        return null;
    }

    @NotNull
    public final Function0<Unit> getOnUserInteractionStarted$storyly_release() {
        Function0<Unit> function0 = this.f6098i;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onUserInteractionStarted");
        return null;
    }

    public final void setOnProductClick$storyly_release(@NotNull Function1<? super b0, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.f6100k = function1;
    }

    public final void setOnUserInteractionEnded$storyly_release(@NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.f6099j = function0;
    }

    public final void setOnUserInteractionStarted$storyly_release(@NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.f6098i = function0;
    }

    public void a(@NotNull d dVar) {
        float f2;
        Intrinsics.checkNotNullParameter(dVar, "safeFrame");
        float b3 = dVar.b();
        float a2 = dVar.a();
        if (getStorylyLayerItem$storyly_release().f3610e == 100.0f) {
            f2 = 14.0f;
        } else {
            f2 = getStorylyLayerItem$storyly_release().f3610e;
        }
        int roundToInt = MathKt.roundToInt((((double) getStorylyLayerItem$storyly_release().f3609d) / 100.0d) * ((double) b3));
        int roundToInt2 = MathKt.roundToInt((((double) f2) / 100.0d) * ((double) a2));
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(roundToInt, roundToInt2);
        layoutParams.leftMargin = getStorylyLayerItem$storyly_release().b().x;
        layoutParams.gravity = 0;
        layoutParams.topMargin = getStorylyLayerItem$storyly_release().b().y != 0 ? getStorylyLayerItem$storyly_release().b().y : (int) (((double) (com.appsamurai.storyly.util.m.b().height() - roundToInt2)) - (((double) com.appsamurai.storyly.util.m.b().height()) * 0.025d));
        Unit unit = Unit.INSTANCE;
        setLayoutParams(layoutParams);
        getRecyclerView().setComponentHeight$storyly_release(roundToInt2);
        g recyclerView = getRecyclerView();
        Class cls = Integer.TYPE;
        ViewGroup.LayoutParams newInstance = ViewGroup.LayoutParams.class.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-1, -1});
        Intrinsics.checkNotNullExpressionValue(newInstance, "layoutParams");
        addView(recyclerView, newInstance);
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 100.0f, 0.0f);
        translateAnimation.setDuration(500);
        translateAnimation.setStartOffset(500);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(750);
        alphaAnimation.setStartOffset(500);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(alphaAnimation);
        startAnimation(animationSet);
    }
}
