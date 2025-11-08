package com.appsamurai.storyly.storylypresenter;

import W.e;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class j1 {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Function1<? super Pair<Float, Float>, Unit> f5006a;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    public Function1<? super Pair<Float, Float>, Unit> f5007b;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    public Function0<Unit> f5008c;
    @Nullable

    /* renamed from: d  reason: collision with root package name */
    public Function0<Unit> f5009d;
    @Nullable

    /* renamed from: e  reason: collision with root package name */
    public Function4<? super a, ? super Pair<Float, Float>, ? super Pair<Float, Float>, ? super Float, Unit> f5010e;
    @Nullable

    /* renamed from: f  reason: collision with root package name */
    public Pair<Float, Float> f5011f;
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public final Lazy f5012g = LazyKt.lazy(c.f5025a);
    @NotNull

    /* renamed from: h  reason: collision with root package name */
    public final Lazy f5013h = LazyKt.lazy(b.f5024a);
    @Nullable

    /* renamed from: i  reason: collision with root package name */
    public a f5014i;
    @Nullable

    /* renamed from: j  reason: collision with root package name */
    public a f5015j;

    public enum a {
        Click,
        DoubleClick,
        SwipeLeft,
        SwipeRight,
        SwipeUp,
        SwipeDown,
        LongPress
    }

    public static final class b extends Lambda implements Function0<Handler> {

        /* renamed from: a  reason: collision with root package name */
        public static final b f5024a = new b();

        public b() {
            super(0);
        }

        public Object invoke() {
            return new Handler(Looper.getMainLooper());
        }
    }

    public static final class c extends Lambda implements Function0<Handler> {

        /* renamed from: a  reason: collision with root package name */
        public static final c f5025a = new c();

        public c() {
            super(0);
        }

        public Object invoke() {
            return new Handler(Looper.getMainLooper());
        }
    }

    public static final void b(j1 j1Var) {
        Intrinsics.checkNotNullParameter(j1Var, "this$0");
        j1Var.f5015j = null;
    }

    public final Handler a() {
        return (Handler) this.f5012g.getValue();
    }

    public final void a(@Nullable MotionEvent motionEvent) {
        Function0<Unit> function0;
        Function4<? super a, ? super Pair<Float, Float>, ? super Pair<Float, Float>, ? super Float, Unit> function4;
        if (motionEvent != null) {
            Pair pair = new Pair(Float.valueOf(motionEvent.getRawX()), Float.valueOf(motionEvent.getRawY()));
            int action = motionEvent.getAction();
            if (action == 0) {
                this.f5011f = new Pair<>(Float.valueOf(motionEvent.getRawX()), Float.valueOf(motionEvent.getRawY()));
                a().postDelayed(new e(this, 1), 200);
            } else if (action == 1) {
                a().removeCallbacksAndMessages((Object) null);
                Pair<Float, Float> pair2 = this.f5011f;
                if (pair2 != null) {
                    a aVar = this.f5014i;
                    if (aVar == null) {
                        ((Handler) this.f5013h.getValue()).removeCallbacksAndMessages((Object) null);
                        ((Handler) this.f5013h.getValue()).postDelayed(new e(this, 0), 350);
                        a aVar2 = this.f5015j;
                        a aVar3 = a.Click;
                        if (aVar2 == aVar3 || aVar2 == a.DoubleClick) {
                            Function1<? super Pair<Float, Float>, Unit> function1 = this.f5007b;
                            if (function1 != null) {
                                function1.invoke(pair2);
                            }
                            aVar3 = a.DoubleClick;
                        } else {
                            Function1<? super Pair<Float, Float>, Unit> function12 = this.f5006a;
                            if (function12 != null) {
                                function12.invoke(pair2);
                            }
                        }
                        this.f5014i = aVar3;
                    } else {
                        a aVar4 = a.SwipeDown;
                        if (aVar == aVar4) {
                            Function4<? super a, ? super Pair<Float, Float>, ? super Pair<Float, Float>, ? super Float, Unit> function42 = this.f5010e;
                            if (function42 != null) {
                                function42.invoke(aVar4, pair2, pair, Float.valueOf(Float.NaN));
                            }
                        } else if (!(aVar == a.SwipeUp || (function0 = this.f5009d) == null)) {
                            function0.invoke();
                        }
                    }
                    this.f5015j = this.f5014i;
                    this.f5014i = null;
                    this.f5011f = null;
                }
            } else if (action == 2) {
                if (this.f5011f == null) {
                    this.f5011f = new Pair<>(Float.valueOf(motionEvent.getRawX()), Float.valueOf(motionEvent.getRawY()));
                }
                Pair<Float, Float> pair3 = this.f5011f;
                if (pair3 != null) {
                    float rawX = motionEvent.getRawX();
                    float rawY = motionEvent.getRawY();
                    double d2 = (double) 2;
                    float sqrt = (float) Math.sqrt((double) (((float) Math.pow((double) (rawX - pair3.getFirst().floatValue()), d2)) + ((float) Math.pow((double) (rawY - pair3.getSecond().floatValue()), d2))));
                    a aVar5 = this.f5014i;
                    a aVar6 = a.SwipeDown;
                    if (aVar5 == aVar6 && ((Number) pair.getSecond()).floatValue() > pair3.getSecond().floatValue() && (function4 = this.f5010e) != null) {
                        function4.invoke(aVar6, pair3, pair, Float.valueOf(sqrt));
                    }
                    if (this.f5014i == null && sqrt > 30.0f) {
                        a().removeCallbacksAndMessages((Object) null);
                        float floatValue = pair3.getFirst().floatValue();
                        int abs = (int) Math.abs(Math.toDegrees((double) ((float) Math.atan2((double) (((Number) pair.getSecond()).floatValue() - pair3.getSecond().floatValue()), (double) (((Number) pair.getFirst()).floatValue() - floatValue)))));
                        if ((abs < 0 || abs > 45) && (135 > abs || abs > 180)) {
                            if (((Number) pair.getSecond()).floatValue() > pair3.getSecond().floatValue()) {
                                this.f5014i = aVar6;
                                Function4<? super a, ? super Pair<Float, Float>, ? super Pair<Float, Float>, ? super Float, Unit> function43 = this.f5010e;
                                if (function43 != null) {
                                    function43.invoke(aVar6, pair3, pair, Float.valueOf(sqrt));
                                }
                            } else if (((Number) pair.getSecond()).floatValue() < pair3.getSecond().floatValue()) {
                                a aVar7 = a.SwipeUp;
                                this.f5014i = aVar7;
                                Function4<? super a, ? super Pair<Float, Float>, ? super Pair<Float, Float>, ? super Float, Unit> function44 = this.f5010e;
                                if (function44 != null) {
                                    function44.invoke(aVar7, pair3, pair, Float.valueOf(sqrt));
                                }
                            }
                        } else if (((Number) pair.getFirst()).floatValue() > pair3.getFirst().floatValue()) {
                            a aVar8 = a.SwipeRight;
                            this.f5014i = aVar8;
                            Function4<? super a, ? super Pair<Float, Float>, ? super Pair<Float, Float>, ? super Float, Unit> function45 = this.f5010e;
                            if (function45 != null) {
                                function45.invoke(aVar8, pair3, pair, Float.valueOf(sqrt));
                            }
                        } else if (((Number) pair.getFirst()).floatValue() < pair3.getFirst().floatValue()) {
                            a aVar9 = a.SwipeLeft;
                            this.f5014i = aVar9;
                            Function4<? super a, ? super Pair<Float, Float>, ? super Pair<Float, Float>, ? super Float, Unit> function46 = this.f5010e;
                            if (function46 != null) {
                                function46.invoke(aVar9, pair3, pair, Float.valueOf(sqrt));
                            }
                        }
                    }
                }
            } else if (action == 3) {
                a().removeCallbacksAndMessages((Object) null);
                this.f5014i = null;
                this.f5011f = null;
            }
        }
    }

    public static final void a(j1 j1Var) {
        Intrinsics.checkNotNullParameter(j1Var, "this$0");
        if (j1Var.f5014i == null) {
            j1Var.f5014i = a.LongPress;
            Function0<Unit> function0 = j1Var.f5008c;
            if (function0 != null) {
                function0.invoke();
            }
        }
    }
}
