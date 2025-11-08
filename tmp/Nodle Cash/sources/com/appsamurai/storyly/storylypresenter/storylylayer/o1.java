package com.appsamurai.storyly.storylypresenter.storylylayer;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import androidx.constraintlayout.motion.widget.Key;
import androidx.core.view.animation.PathInterpolatorCompat;
import com.appsamurai.storyly.data.b;
import com.appsamurai.storyly.data.b0;
import com.appsamurai.storyly.data.i;
import com.appsamurai.storyly.util.g;
import com.appsamurai.storyly.util.m;
import com.appsamurai.storyly.util.ui.e;
import com.appsamurai.storyly.util.ui.l;
import java.util.ArrayList;
import java.util.List;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.properties.Delegates;
import kotlin.properties.ObservableProperty;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class o1 extends FrameLayout {

    /* renamed from: f  reason: collision with root package name */
    public static final /* synthetic */ KProperty<Object>[] f5893f = {androidx.compose.ui.autofill.a.m(o1.class, "safeFrame", "getSafeFrame$storyly_release()Lcom/appsamurai/storyly/storylypresenter/storylylayer/SafeFrame;", 0)};

    /* renamed from: a  reason: collision with root package name */
    public b0 f5894a;

    /* renamed from: b  reason: collision with root package name */
    public Function0<Unit> f5895b;

    /* renamed from: c  reason: collision with root package name */
    public Function0<Unit> f5896c;
    @NotNull

    /* renamed from: d  reason: collision with root package name */
    public final ReadWriteProperty f5897d;
    @Nullable

    /* renamed from: e  reason: collision with root package name */
    public FrameLayout f5898e;

    public static final class a extends ObservableProperty<d> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Object f5899a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ o1 f5900b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(Object obj, Object obj2, o1 o1Var) {
            super(obj2);
            this.f5899a = obj;
            this.f5900b = o1Var;
        }

        public void afterChange(@NotNull KProperty<?> kProperty, d dVar, d dVar2) {
            Interpolator create;
            List list;
            b bVar;
            int i3;
            Intrinsics.checkNotNullParameter(kProperty, "property");
            d dVar3 = dVar;
            this.f5900b.a(dVar2);
            o1 o1Var = this.f5900b;
            b bVar2 = o1Var.getStorylyLayerItem$storyly_release().f3618m;
            if (bVar2 != null) {
                AnimatorSet animatorSet = new AnimatorSet();
                List<Float> list2 = bVar2.f3601c;
                if (list2 == null || list2.size() < 4) {
                    create = PathInterpolatorCompat.create(0.5f, -0.1f, 0.5f, 1.1f);
                    Intrinsics.checkNotNullExpressionValue(create, "create(0.5f, -0.1f, 0.5f, 1.1f)");
                } else {
                    create = PathInterpolatorCompat.create(list2.get(0).floatValue(), list2.get(1).floatValue(), list2.get(2).floatValue(), list2.get(3).floatValue());
                    Intrinsics.checkNotNullExpressionValue(create, "create(cubicBezier[0], c…ezier[2], cubicBezier[3])");
                }
                Interpolator interpolator = create;
                List<i> list3 = bVar2.f3599a;
                if (list3 == null) {
                    list = null;
                } else {
                    ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list3, 10));
                    for (i iVar : list3) {
                        Long l2 = bVar2.f3602d;
                        long longValue = l2 == null ? 600 : l2.longValue();
                        ArrayList arrayList2 = new ArrayList();
                        Float f2 = iVar.f3749d;
                        if (f2 != null) {
                            arrayList2.add(o1Var.a("alpha", CollectionsKt.listOf(Float.valueOf(f2.floatValue()), Float.valueOf(o1Var.getAlpha())), longValue, interpolator));
                        }
                        Float f3 = iVar.f3746a;
                        if (f3 == null) {
                            bVar = bVar2;
                            i3 = 100;
                        } else {
                            bVar = bVar2;
                            i3 = 100;
                            arrayList2.add(o1Var.a("translationX", CollectionsKt.listOf(Float.valueOf(((((float) m.c().width()) * f3.floatValue()) / ((float) 100)) + o1Var.getX()), Float.valueOf(o1Var.getX())), longValue, interpolator));
                        }
                        Float f4 = iVar.f3747b;
                        if (f4 != null) {
                            arrayList2.add(o1Var.a("translationY", CollectionsKt.listOf(Float.valueOf(((((float) m.c().height()) * f4.floatValue()) / ((float) i3)) + o1Var.getY()), Float.valueOf(o1Var.getY())), longValue, interpolator));
                        }
                        Float f5 = iVar.f3750e;
                        if (f5 != null) {
                            float floatValue = f5.floatValue();
                            long j2 = longValue;
                            arrayList2.addAll(CollectionsKt.listOf(o1Var.a("scaleX", CollectionsKt.listOf(Float.valueOf(floatValue), Float.valueOf(1.0f)), j2, interpolator), o1Var.a("scaleY", CollectionsKt.listOf(Float.valueOf(floatValue), Float.valueOf(1.0f)), j2, interpolator)));
                        }
                        Float f6 = iVar.f3748c;
                        if (f6 != null) {
                            arrayList2.add(o1Var.a(Key.ROTATION, CollectionsKt.listOf(Float.valueOf(o1Var.getRotation() + f6.floatValue()), Float.valueOf(o1Var.getRotation())), longValue, interpolator));
                        }
                        arrayList.add(arrayList2);
                        bVar2 = bVar;
                    }
                    list = CollectionsKt.flatten(arrayList);
                }
                animatorSet.playTogether(list);
                animatorSet.start();
            }
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public o1(@NotNull Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Delegates delegates = Delegates.INSTANCE;
        Float valueOf = Float.valueOf(0.0f);
        d dVar = new d(new Pair(valueOf, valueOf), new Pair(valueOf, valueOf));
        this.f5897d = new a(dVar, dVar, this);
        setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
    }

    public void a(long j2) {
    }

    public abstract void a(@NotNull d dVar);

    public void b() {
    }

    public void c() {
        FrameLayout frameLayout = this.f5898e;
        if (frameLayout != null) {
            ViewParent parent = frameLayout.getParent();
            ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
            if (viewGroup != null) {
                viewGroup.removeView(frameLayout);
            }
        }
        this.f5898e = null;
    }

    public void d() {
    }

    public void e() {
    }

    @Nullable
    public final FrameLayout getAccessibilityLayerView$storyly_release() {
        return this.f5898e;
    }

    @Nullable
    public Bitmap getCurrentBitmap$storyly_release() {
        return null;
    }

    @NotNull
    public final Function0<Unit> getOnLayerLoad$storyly_release() {
        Function0<Unit> function0 = this.f5895b;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onLayerLoad");
        return null;
    }

    @NotNull
    public final Function0<Unit> getOnLayerLoadFail$storyly_release() {
        Function0<Unit> function0 = this.f5896c;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onLayerLoadFail");
        return null;
    }

    @NotNull
    public final d getSafeFrame$storyly_release() {
        return (d) this.f5897d.getValue(this, f5893f[0]);
    }

    @NotNull
    public final b0 getStorylyLayerItem$storyly_release() {
        b0 b0Var = this.f5894a;
        if (b0Var != null) {
            return b0Var;
        }
        Intrinsics.throwUninitializedPropertyAccessException("storylyLayerItem");
        return null;
    }

    public final void setAccessibilityLayerView$storyly_release(@Nullable FrameLayout frameLayout) {
        this.f5898e = frameLayout;
    }

    public final void setOnLayerLoad$storyly_release(@NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.f5895b = function0;
    }

    public final void setOnLayerLoadFail$storyly_release(@NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.f5896c = function0;
    }

    public final void setSafeFrame$storyly_release(@NotNull d dVar) {
        Intrinsics.checkNotNullParameter(dVar, "<set-?>");
        this.f5897d.setValue(this, f5893f[0], dVar);
    }

    public final void setStorylyLayerItem$storyly_release(@NotNull b0 b0Var) {
        Intrinsics.checkNotNullParameter(b0Var, "<set-?>");
        this.f5894a = b0Var;
    }

    public final void a(@NotNull String str) {
        FrameLayout frameLayout;
        Intrinsics.checkNotNullParameter(str, "description");
        Intrinsics.checkNotNullParameter(this, "parent");
        Intrinsics.checkNotNullParameter(str, "description");
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "parent.context");
        if (!g.b(context)) {
            frameLayout = null;
        } else {
            FrameLayout frameLayout2 = new FrameLayout(getContext());
            addView(frameLayout2, new FrameLayout.LayoutParams(-1, -1));
            l.a(frameLayout2, new e());
            frameLayout2.setImportantForAccessibility(1);
            frameLayout2.setContentDescription(str);
            frameLayout = frameLayout2;
        }
        this.f5898e = frameLayout;
    }

    public void b(long j2) {
    }

    public static /* synthetic */ FrameLayout.LayoutParams a(o1 o1Var, FrameLayout.LayoutParams layoutParams, int i3, int i4, float f2, float f3, int i5, Object obj) {
        return o1Var.a(layoutParams, (i5 & 2) != 0 ? 0 : i3, (i5 & 4) != 0 ? 0 : i4, (i5 & 8) != 0 ? 0.0f : f2, (i5 & 16) != 0 ? 0.0f : f3);
    }

    @NotNull
    @SuppressLint({"RtlHardcoded"})
    public final FrameLayout.LayoutParams a(@NotNull FrameLayout.LayoutParams layoutParams, int i3, int i4, float f2, float f3) {
        Intrinsics.checkNotNullParameter(layoutParams, "updateLayoutParams");
        layoutParams.gravity = 0;
        layoutParams.leftMargin = MathKt.roundToInt(((float) i3) + f2);
        layoutParams.topMargin = MathKt.roundToInt(((float) i4) + f3);
        return layoutParams;
    }

    public final boolean a() {
        ViewParent parent = getParent();
        View view = parent instanceof View ? (View) parent : null;
        if (view == null) {
            return true;
        }
        return com.appsamurai.storyly.util.l.a(view);
    }

    public final ObjectAnimator a(String str, List<Float> list, long j2, Interpolator interpolator) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, str, new float[]{list.get(0).floatValue(), list.get(1).floatValue()});
        Intrinsics.checkNotNullExpressionValue(ofFloat, "ofFloat(this, keyPath, values[0], values[1])");
        ofFloat.setDuration(j2);
        ofFloat.setInterpolator(interpolator);
        return ofFloat;
    }
}
