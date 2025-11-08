package com.appsamurai.storyly.storylypresenter;

import W.d;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import androidx.core.view.ViewGroupKt;
import com.appsamurai.storyly.StoryComponent;
import com.appsamurai.storyly.analytics.e;
import com.appsamurai.storyly.data.b0;
import com.appsamurai.storyly.data.managers.product.STRCart;
import com.appsamurai.storyly.data.managers.product.STRCartItem;
import com.appsamurai.storyly.data.v;
import com.appsamurai.storyly.data.z;
import com.appsamurai.storyly.util.l;
import java.util.Arrays;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlinx.serialization.json.JsonObject;
import org.jetbrains.annotations.NotNull;

public final class f extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ d f4989a;

    public static final class a implements Animator.AnimatorListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ d f4990a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ v f4991b;

        public a(d dVar, v vVar) {
            this.f4990a = dVar;
            this.f4991b = vVar;
        }

        public void onAnimationCancel(@NotNull Animator animator) {
        }

        public void onAnimationEnd(@NotNull Animator animator) {
            for (View a2 : ViewGroupKt.getChildren(this.f4990a)) {
                com.appsamurai.storyly.util.ui.a.a(a2);
            }
            d dVar = this.f4990a;
            dVar.f4973r = false;
            e storylyTracker = dVar.getStorylyTracker();
            com.appsamurai.storyly.analytics.a aVar = com.appsamurai.storyly.analytics.a.GroupComplete;
            v vVar = this.f4991b;
            e.a(storylyTracker, aVar, vVar, vVar == null ? null : vVar.f4240t, (b0) null, (StoryComponent) null, (JsonObject) null, (Function1) null, (String) null, (Function1) null, (Function1) null, (STRCart) null, (STRCartItem) null, 4088);
            d dVar2 = this.f4990a;
            dVar2.setSelectedStorylyGroupIndex(dVar2.c());
            this.f4990a.b();
        }

        public void onAnimationRepeat(@NotNull Animator animator) {
        }

        public void onAnimationStart(@NotNull Animator animator) {
        }
    }

    public static final class b implements Animator.AnimatorListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ d f4992a;

        public b(d dVar) {
            this.f4992a = dVar;
        }

        public void onAnimationCancel(@NotNull Animator animator) {
        }

        public void onAnimationEnd(@NotNull Animator animator) {
        }

        public void onAnimationRepeat(@NotNull Animator animator) {
        }

        public void onAnimationStart(@NotNull Animator animator) {
            this.f4992a.d();
            this.f4992a.a();
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public f(d dVar) {
        super(0);
        this.f4989a = dVar;
    }

    public final void a() {
        z zVar;
        z zVar2;
        v vVar = (v) com.appsamurai.storyly.util.e.a(this.f4989a.getStorylyGroupItems(), this.f4989a.getSelectedStorylyGroupIndex());
        int i3 = 0;
        if (this.f4989a.getLinearLayoutManager().findLastVisibleItemPosition() == this.f4989a.getStorylyGroupItems().size() - 1) {
            View childAt = this.f4989a.getChildAt(0);
            o oVar = childAt instanceof o ? (o) childAt : null;
            if (oVar != null) {
                oVar.m();
            }
            e storylyTracker = this.f4989a.getStorylyTracker();
            com.appsamurai.storyly.analytics.a aVar = com.appsamurai.storyly.analytics.a.GroupComplete;
            if (vVar == null) {
                zVar = null;
            } else {
                zVar = vVar.f4240t;
            }
            e.a(storylyTracker, aVar, vVar, zVar, (b0) null, (StoryComponent) null, (JsonObject) null, (Function1) null, (String) null, (Function1) null, (Function1) null, (STRCart) null, (STRCartItem) null, 4088);
            this.f4989a.getOnCompleted$storyly_release().invoke();
            e storylyTracker2 = this.f4989a.getStorylyTracker();
            com.appsamurai.storyly.analytics.a aVar2 = com.appsamurai.storyly.analytics.a.LastGroupCompleted;
            if (vVar == null) {
                zVar2 = null;
            } else {
                zVar2 = vVar.f4240t;
            }
            e.a(storylyTracker2, aVar2, vVar, zVar2, (b0) null, (StoryComponent) null, (JsonObject) null, (Function1) null, (String) null, (Function1) null, (Function1) null, (STRCart) null, (STRCartItem) null, 4088);
            return;
        }
        d dVar = this.f4989a;
        dVar.f4973r = true;
        int measuredWidth = dVar.getMeasuredWidth();
        int[] iArr = new int[measuredWidth];
        int i4 = 0;
        while (i4 < measuredWidth) {
            int i5 = i4 + 1;
            iArr[i4] = i5;
            i4 = i5;
        }
        if (!l.a(this.f4989a)) {
            ArraysKt.reverse(iArr);
        }
        ValueAnimator ofInt = ValueAnimator.ofInt(Arrays.copyOf(iArr, measuredWidth));
        d dVar2 = this.f4989a;
        ofInt.setInterpolator(new AccelerateDecelerateInterpolator());
        ofInt.setDuration(200);
        Ref.IntRef intRef = new Ref.IntRef();
        if (!l.a(dVar2)) {
            i3 = dVar2.getMeasuredWidth();
        }
        intRef.element = i3;
        ofInt.addUpdateListener(new d(intRef, dVar2, 0));
        Intrinsics.checkNotNullExpressionValue(ofInt, "");
        ofInt.addListener(new a(dVar2, vVar));
        ofInt.addListener(new b(dVar2));
        ofInt.start();
    }

    public /* bridge */ /* synthetic */ Object invoke() {
        a();
        return Unit.INSTANCE;
    }

    public static final void a(Ref.IntRef intRef, d dVar, ValueAnimator valueAnimator) {
        Intrinsics.checkNotNullParameter(intRef, "$previousValue");
        Intrinsics.checkNotNullParameter(dVar, "this$0");
        Object animatedValue = valueAnimator.getAnimatedValue();
        if (animatedValue == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
        } else if (((Integer) animatedValue).intValue() - intRef.element != 0) {
            Object animatedValue2 = valueAnimator.getAnimatedValue();
            if (animatedValue2 != null) {
                dVar.scrollBy(((Integer) animatedValue2).intValue() - intRef.element, 0);
                Object animatedValue3 = valueAnimator.getAnimatedValue();
                if (animatedValue3 != null) {
                    intRef.element = ((Integer) animatedValue3).intValue();
                    return;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
        }
    }
}
