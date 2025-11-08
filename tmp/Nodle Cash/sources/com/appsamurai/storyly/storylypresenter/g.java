package com.appsamurai.storyly.storylypresenter;

import W.d;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import androidx.core.view.ViewGroupKt;
import com.appsamurai.storyly.util.l;
import java.util.Arrays;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;

public final class g extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ d f4994a;

    public static final class a implements Animator.AnimatorListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ d f4995a;

        public a(d dVar) {
            this.f4995a = dVar;
        }

        public void onAnimationCancel(@NotNull Animator animator) {
        }

        public void onAnimationEnd(@NotNull Animator animator) {
            for (View a2 : ViewGroupKt.getChildren(this.f4995a)) {
                com.appsamurai.storyly.util.ui.a.a(a2);
            }
            d dVar = this.f4995a;
            dVar.f4973r = false;
            dVar.setSelectedStorylyGroupIndex(dVar.c());
            this.f4995a.b();
        }

        public void onAnimationRepeat(@NotNull Animator animator) {
        }

        public void onAnimationStart(@NotNull Animator animator) {
        }
    }

    public static final class b implements Animator.AnimatorListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ d f4996a;

        public b(d dVar) {
            this.f4996a = dVar;
        }

        public void onAnimationCancel(@NotNull Animator animator) {
        }

        public void onAnimationEnd(@NotNull Animator animator) {
        }

        public void onAnimationRepeat(@NotNull Animator animator) {
        }

        public void onAnimationStart(@NotNull Animator animator) {
            this.f4996a.d();
            this.f4996a.a();
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public g(d dVar) {
        super(0);
        this.f4994a = dVar;
    }

    public final void a() {
        int i3 = 0;
        if (this.f4994a.getLinearLayoutManager().findLastVisibleItemPosition() == 0) {
            View childAt = this.f4994a.getChildAt(0);
            o oVar = childAt instanceof o ? (o) childAt : null;
            if (oVar != null) {
                oVar.m();
            }
            this.f4994a.getOnCompleted$storyly_release().invoke();
            return;
        }
        d dVar = this.f4994a;
        dVar.f4973r = true;
        int measuredWidth = dVar.getMeasuredWidth();
        int[] iArr = new int[measuredWidth];
        int i4 = 0;
        while (i4 < measuredWidth) {
            int i5 = i4 + 1;
            iArr[i4] = i5;
            i4 = i5;
        }
        if (l.a(this.f4994a)) {
            ArraysKt.reverse(iArr);
        }
        ValueAnimator ofInt = ValueAnimator.ofInt(Arrays.copyOf(iArr, measuredWidth));
        d dVar2 = this.f4994a;
        ofInt.setInterpolator(new AccelerateDecelerateInterpolator());
        ofInt.setDuration(200);
        Ref.IntRef intRef = new Ref.IntRef();
        if (l.a(dVar2)) {
            i3 = dVar2.getMeasuredWidth();
        }
        intRef.element = i3;
        ofInt.addUpdateListener(new d(intRef, dVar2, 1));
        Intrinsics.checkNotNullExpressionValue(ofInt, "");
        ofInt.addListener(new a(dVar2));
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
