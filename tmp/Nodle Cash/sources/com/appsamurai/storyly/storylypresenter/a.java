package com.appsamurai.storyly.storylypresenter;

import A0.C0201b;
import android.os.Handler;
import android.os.Looper;
import com.appsamurai.storyly.StoryType;
import com.appsamurai.storyly.data.z;
import com.appsamurai.storyly.storylypresenter.j1;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.properties.Delegates;
import kotlin.properties.ObservableProperty;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class a {

    /* renamed from: s  reason: collision with root package name */
    public static final /* synthetic */ KProperty<Object>[] f4719s = {androidx.compose.ui.autofill.a.m(a.class, "storylyItem", "getStorylyItem$storyly_release()Lcom/appsamurai/storyly/data/StorylyItem;", 0)};
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public Pair<Integer, Integer> f4720a = new Pair<>(0, 0);
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final ReadWriteProperty f4721b;

    /* renamed from: c  reason: collision with root package name */
    public C0015a f4722c;
    @Nullable

    /* renamed from: d  reason: collision with root package name */
    public j1 f4723d;

    /* renamed from: e  reason: collision with root package name */
    public Function0<Unit> f4724e;

    /* renamed from: f  reason: collision with root package name */
    public Function0<Unit> f4725f;

    /* renamed from: g  reason: collision with root package name */
    public Function0<Unit> f4726g;

    /* renamed from: h  reason: collision with root package name */
    public Function1<? super Float, Unit> f4727h;

    /* renamed from: i  reason: collision with root package name */
    public Function1<? super Pair<Float, Float>, Unit> f4728i;

    /* renamed from: j  reason: collision with root package name */
    public Function1<? super Boolean, Unit> f4729j;

    /* renamed from: k  reason: collision with root package name */
    public Function0<Unit> f4730k;

    /* renamed from: l  reason: collision with root package name */
    public Function1<? super Long, Unit> f4731l;

    /* renamed from: m  reason: collision with root package name */
    public Function0<Unit> f4732m;

    /* renamed from: n  reason: collision with root package name */
    public Function1<? super Boolean, Unit> f4733n;

    /* renamed from: o  reason: collision with root package name */
    public Function0<Unit> f4734o;

    /* renamed from: p  reason: collision with root package name */
    public Function0<Unit> f4735p;

    /* renamed from: q  reason: collision with root package name */
    public Function0<Unit> f4736q;

    /* renamed from: r  reason: collision with root package name */
    public Function0<Boolean> f4737r;

    /* renamed from: com.appsamurai.storyly.storylypresenter.a$a  reason: collision with other inner class name */
    public class C0015a {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ a f4738a;

        public C0015a(a aVar) {
            Intrinsics.checkNotNullParameter(aVar, "this$0");
            this.f4738a = aVar;
        }

        public void a(@NotNull Pair<Float, Float> pair) {
            Intrinsics.checkNotNullParameter(pair, "clickCoordinates");
        }

        public void b(@NotNull Pair<Float, Float> pair) {
            Intrinsics.checkNotNullParameter(pair, "clickCoordinates");
        }
    }

    public final class b extends C0015a {

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ a f4739b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public b(a aVar) {
            super(aVar);
            Intrinsics.checkNotNullParameter(aVar, "this$0");
            this.f4739b = aVar;
        }

        public void a(@NotNull Pair<Float, Float> pair) {
            Intrinsics.checkNotNullParameter(pair, "clickCoordinates");
            Intrinsics.checkNotNullParameter(pair, "clickCoordinates");
            if (pair.getFirst().floatValue() < ((float) (this.f4739b.f4720a.getFirst().intValue() / 2))) {
                boolean booleanValue = this.f4739b.d().invoke().booleanValue();
                a aVar = this.f4739b;
                if (booleanValue) {
                    aVar.c().invoke();
                } else {
                    aVar.a().invoke(Boolean.TRUE);
                }
            } else {
                boolean booleanValue2 = this.f4739b.d().invoke().booleanValue();
                a aVar2 = this.f4739b;
                if (booleanValue2) {
                    aVar2.a().invoke(Boolean.TRUE);
                } else {
                    aVar2.c().invoke();
                }
            }
        }

        public void b(@NotNull Pair<Float, Float> pair) {
            Intrinsics.checkNotNullParameter(pair, "clickCoordinates");
            Intrinsics.checkNotNullParameter(pair, "clickCoordinates");
            if (pair.getFirst().floatValue() < ((float) (this.f4739b.f4720a.getFirst().intValue() / 2))) {
                boolean booleanValue = this.f4739b.d().invoke().booleanValue();
                a aVar = this.f4739b;
                if (booleanValue) {
                    aVar.c().invoke();
                } else {
                    aVar.a().invoke(Boolean.TRUE);
                }
            } else {
                boolean booleanValue2 = this.f4739b.d().invoke().booleanValue();
                a aVar2 = this.f4739b;
                if (booleanValue2) {
                    aVar2.a().invoke(Boolean.TRUE);
                } else {
                    aVar2.c().invoke();
                }
            }
        }
    }

    public /* synthetic */ class d {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[StoryType.values().length];
            iArr[StoryType.LongVideo.ordinal()] = 1;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final class e extends ObservableProperty<z> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ a f4743a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public e(Object obj, Object obj2, a aVar) {
            super(null);
            this.f4743a = aVar;
        }

        public void afterChange(@NotNull KProperty<?> kProperty, z zVar, z zVar2) {
            Intrinsics.checkNotNullParameter(kProperty, "property");
            z zVar3 = zVar2;
            z zVar4 = zVar;
            if (zVar3 != null) {
                a aVar = this.f4743a;
                aVar.getClass();
                aVar.f4722c = d.$EnumSwitchMapping$0[zVar3.f4307f.ordinal()] == 1 ? new c(aVar) : new b(aVar);
                a aVar2 = this.f4743a;
                j1 j1Var = new j1();
                C0015a aVar3 = this.f4743a.f4722c;
                C0015a aVar4 = null;
                if (aVar3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("actionHandler");
                    aVar3 = null;
                }
                j1Var.f5006a = new f(aVar3);
                C0015a aVar5 = this.f4743a.f4722c;
                if (aVar5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("actionHandler");
                    aVar5 = null;
                }
                j1Var.f5007b = new g(aVar5);
                C0015a aVar6 = this.f4743a.f4722c;
                if (aVar6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("actionHandler");
                    aVar6 = null;
                }
                j1Var.f5010e = new h(aVar6);
                C0015a aVar7 = this.f4743a.f4722c;
                if (aVar7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("actionHandler");
                    aVar7 = null;
                }
                j1Var.f5008c = new i(aVar7);
                C0015a aVar8 = this.f4743a.f4722c;
                if (aVar8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("actionHandler");
                } else {
                    aVar4 = aVar8;
                }
                j1Var.f5009d = new j(aVar4);
                Unit unit = Unit.INSTANCE;
                aVar2.f4723d = j1Var;
            }
        }
    }

    public /* synthetic */ class f extends FunctionReferenceImpl implements Function1<Pair<? extends Float, ? extends Float>, Unit> {
        public f(Object obj) {
            super(1, obj, C0015a.class, "onClick", "onClick(Lkotlin/Pair;)V", 0);
        }

        public Object invoke(Object obj) {
            Pair pair = (Pair) obj;
            Intrinsics.checkNotNullParameter(pair, "p0");
            ((C0015a) this.receiver).a(pair);
            return Unit.INSTANCE;
        }
    }

    public /* synthetic */ class g extends FunctionReferenceImpl implements Function1<Pair<? extends Float, ? extends Float>, Unit> {
        public g(Object obj) {
            super(1, obj, C0015a.class, "onDoubleClick", "onDoubleClick(Lkotlin/Pair;)V", 0);
        }

        public Object invoke(Object obj) {
            Pair pair = (Pair) obj;
            Intrinsics.checkNotNullParameter(pair, "p0");
            ((C0015a) this.receiver).b(pair);
            return Unit.INSTANCE;
        }
    }

    public /* synthetic */ class h extends FunctionReferenceImpl implements Function4<j1.a, Pair<? extends Float, ? extends Float>, Pair<? extends Float, ? extends Float>, Float, Unit> {
        public h(Object obj) {
            super(4, obj, C0015a.class, "onSwipe", "onSwipe(Lcom/appsamurai/storyly/storylypresenter/TouchHandler$Action;Lkotlin/Pair;Lkotlin/Pair;F)V", 0);
        }

        /* JADX WARNING: type inference failed for: r1v0 */
        /* JADX WARNING: type inference failed for: r1v1, types: [kotlin.jvm.functions.Function0] */
        /* JADX WARNING: type inference failed for: r1v2 */
        /* JADX WARNING: type inference failed for: r1v3, types: [kotlin.jvm.functions.Function1] */
        /* JADX WARNING: type inference failed for: r1v4 */
        /* JADX WARNING: type inference failed for: r1v5, types: [kotlin.jvm.functions.Function1] */
        /* JADX WARNING: type inference failed for: r1v6 */
        /* JADX WARNING: type inference failed for: r1v7, types: [kotlin.jvm.functions.Function0] */
        /* JADX WARNING: type inference failed for: r1v8 */
        /* JADX WARNING: type inference failed for: r1v9, types: [kotlin.jvm.functions.Function0] */
        /* JADX WARNING: type inference failed for: r1v10 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object invoke(java.lang.Object r3, java.lang.Object r4, java.lang.Object r5, java.lang.Object r6) {
            /*
                r2 = this;
                com.appsamurai.storyly.storylypresenter.j1$a r3 = (com.appsamurai.storyly.storylypresenter.j1.a) r3
                kotlin.Pair r4 = (kotlin.Pair) r4
                kotlin.Pair r5 = (kotlin.Pair) r5
                java.lang.Number r6 = (java.lang.Number) r6
                float r6 = r6.floatValue()
                java.lang.String r0 = "p0"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
                java.lang.String r0 = "p1"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
                java.lang.String r0 = "p2"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
                java.lang.Object r2 = r2.receiver
                com.appsamurai.storyly.storylypresenter.a$a r2 = (com.appsamurai.storyly.storylypresenter.a.C0015a) r2
                r2.getClass()
                java.lang.String r0 = "action"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
                java.lang.String r0 = "initialTouchCoordinates"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
                java.lang.String r0 = "currentTouchCoordinates"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
                int r3 = r3.ordinal()
                r0 = 2
                r1 = 0
                if (r3 == r0) goto L_0x00c2
                r0 = 3
                if (r3 == r0) goto L_0x00c2
                r0 = 4
                if (r3 == r0) goto L_0x00b1
                r0 = 5
                if (r3 == r0) goto L_0x0044
                goto L_0x00d2
            L_0x0044:
                boolean r3 = java.lang.Float.isNaN(r6)
                if (r3 == 0) goto L_0x0087
                java.lang.Object r3 = r5.getSecond()
                java.lang.Number r3 = (java.lang.Number) r3
                float r3 = r3.floatValue()
                java.lang.Object r4 = r4.getSecond()
                java.lang.Number r4 = (java.lang.Number) r4
                float r4 = r4.floatValue()
                float r3 = r3 - r4
                r4 = 1135542272(0x43af0000, float:350.0)
                int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
                if (r3 <= 0) goto L_0x0076
                com.appsamurai.storyly.storylypresenter.a r2 = r2.f4738a
                kotlin.jvm.functions.Function0<kotlin.Unit> r2 = r2.f4725f
                if (r2 == 0) goto L_0x006d
                r1 = r2
                goto L_0x0072
            L_0x006d:
                java.lang.String r2 = "onSwipeDownComplete"
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            L_0x0072:
                r1.invoke()
                goto L_0x00d2
            L_0x0076:
                com.appsamurai.storyly.storylypresenter.a r2 = r2.f4738a
                kotlin.jvm.functions.Function0<kotlin.Unit> r2 = r2.f4726g
                if (r2 == 0) goto L_0x007e
                r1 = r2
                goto L_0x0083
            L_0x007e:
                java.lang.String r2 = "onSwipeDownCancel"
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            L_0x0083:
                r1.invoke()
                goto L_0x00d2
            L_0x0087:
                com.appsamurai.storyly.storylypresenter.a r2 = r2.f4738a
                kotlin.jvm.functions.Function1<? super java.lang.Float, kotlin.Unit> r2 = r2.f4727h
                if (r2 == 0) goto L_0x008f
                r1 = r2
                goto L_0x0094
            L_0x008f:
                java.lang.String r2 = "onSwipeDownMove"
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            L_0x0094:
                java.lang.Object r2 = r5.getSecond()
                java.lang.Number r2 = (java.lang.Number) r2
                float r2 = r2.floatValue()
                java.lang.Object r3 = r4.getSecond()
                java.lang.Number r3 = (java.lang.Number) r3
                float r3 = r3.floatValue()
                float r2 = r2 - r3
                java.lang.Float r2 = java.lang.Float.valueOf(r2)
                r1.invoke(r2)
                goto L_0x00d2
            L_0x00b1:
                com.appsamurai.storyly.storylypresenter.a r2 = r2.f4738a
                kotlin.jvm.functions.Function1<? super kotlin.Pair<java.lang.Float, java.lang.Float>, kotlin.Unit> r2 = r2.f4728i
                if (r2 == 0) goto L_0x00b9
                r1 = r2
                goto L_0x00be
            L_0x00b9:
                java.lang.String r2 = "onSwipeUp"
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            L_0x00be:
                r1.invoke(r4)
                goto L_0x00d2
            L_0x00c2:
                com.appsamurai.storyly.storylypresenter.a r2 = r2.f4738a
                kotlin.jvm.functions.Function0<kotlin.Unit> r2 = r2.f4724e
                if (r2 == 0) goto L_0x00ca
                r1 = r2
                goto L_0x00cf
            L_0x00ca:
                java.lang.String r2 = "onSwipeHorizontal"
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            L_0x00cf:
                r1.invoke()
            L_0x00d2:
                kotlin.Unit r2 = kotlin.Unit.INSTANCE
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.storylypresenter.a.h.invoke(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object):java.lang.Object");
        }
    }

    public /* synthetic */ class i extends FunctionReferenceImpl implements Function0<Unit> {
        public i(Object obj) {
            super(0, obj, C0015a.class, "onLongPress", "onLongPress()V", 0);
        }

        public Object invoke() {
            C0015a aVar = (C0015a) this.receiver;
            Function0<Unit> function0 = aVar.f4738a.f4734o;
            if (function0 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("onPause");
                function0 = null;
            }
            function0.invoke();
            aVar.f4738a.b().invoke(Boolean.FALSE);
            return Unit.INSTANCE;
        }
    }

    public /* synthetic */ class j extends FunctionReferenceImpl implements Function0<Unit> {
        public j(Object obj) {
            super(0, obj, C0015a.class, "onTouchUp", "onTouchUp()V", 0);
        }

        public Object invoke() {
            C0015a aVar = (C0015a) this.receiver;
            Function0<Unit> function0 = aVar.f4738a.f4736q;
            Function0<Unit> function02 = null;
            if (function0 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("onUserTouchEnded");
                function0 = null;
            }
            function0.invoke();
            Function0<Unit> function03 = aVar.f4738a.f4735p;
            if (function03 != null) {
                function02 = function03;
            } else {
                Intrinsics.throwUninitializedPropertyAccessException("onResume");
            }
            function02.invoke();
            aVar.f4738a.b().invoke(Boolean.TRUE);
            return Unit.INSTANCE;
        }
    }

    public a() {
        Delegates delegates = Delegates.INSTANCE;
        this.f4721b = new e((Object) null, (Object) null, this);
    }

    @NotNull
    public final Function1<Boolean, Unit> a() {
        Function1<? super Boolean, Unit> function1 = this.f4729j;
        if (function1 != null) {
            return function1;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onNextClick");
        return null;
    }

    @NotNull
    public final Function1<Boolean, Unit> b() {
        Function1<? super Boolean, Unit> function1 = this.f4733n;
        if (function1 != null) {
            return function1;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onOverlayVisibilityChange");
        return null;
    }

    @NotNull
    public final Function0<Unit> c() {
        Function0<Unit> function0 = this.f4730k;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onPreviousClick");
        return null;
    }

    @NotNull
    public final Function0<Boolean> d() {
        Function0<Boolean> function0 = this.f4737r;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("isLayoutDirectionLtr");
        return null;
    }

    public final void e() {
        j1 j1Var = this.f4723d;
        if (j1Var != null) {
            j1Var.a().removeCallbacksAndMessages((Object) null);
            j1Var.f5014i = null;
            j1Var.f5011f = null;
        }
    }

    public final class c extends C0015a {
        @NotNull

        /* renamed from: b  reason: collision with root package name */
        public final Lazy f4740b = LazyKt.lazy(C0016a.f4742a);

        /* renamed from: c  reason: collision with root package name */
        public final /* synthetic */ a f4741c;

        /* renamed from: com.appsamurai.storyly.storylypresenter.a$c$a  reason: collision with other inner class name */
        public static final class C0016a extends Lambda implements Function0<Handler> {

            /* renamed from: a  reason: collision with root package name */
            public static final C0016a f4742a = new C0016a();

            public C0016a() {
                super(0);
            }

            public Object invoke() {
                return new Handler(Looper.getMainLooper());
            }
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public c(a aVar) {
            super(aVar);
            Intrinsics.checkNotNullParameter(aVar, "this$0");
            this.f4741c = aVar;
        }

        public void a(@NotNull Pair<Float, Float> pair) {
            Intrinsics.checkNotNullParameter(pair, "clickCoordinates");
            Intrinsics.checkNotNullParameter(pair, "clickCoordinates");
            ((Handler) this.f4740b.getValue()).postDelayed(new C0201b(pair, this.f4741c, 7), 350);
        }

        public void b(@NotNull Pair<Float, Float> pair) {
            Intrinsics.checkNotNullParameter(pair, "clickCoordinates");
            Intrinsics.checkNotNullParameter(pair, "clickCoordinates");
            Function1<? super Long, Unit> function1 = null;
            ((Handler) this.f4740b.getValue()).removeCallbacksAndMessages((Object) null);
            this.f4741c.b().invoke(Boolean.TRUE);
            long j2 = 10000;
            if (pair.getFirst().floatValue() < ((float) (this.f4741c.f4720a.getFirst().intValue() / 2))) {
                Function1<? super Long, Unit> function12 = this.f4741c.f4731l;
                if (function12 != null) {
                    function1 = function12;
                } else {
                    Intrinsics.throwUninitializedPropertyAccessException("onSeek");
                }
                if (this.f4741c.d().invoke().booleanValue()) {
                    j2 = -10000;
                }
                function1.invoke(Long.valueOf(j2));
                return;
            }
            Function1<? super Long, Unit> function13 = this.f4741c.f4731l;
            if (function13 != null) {
                function1 = function13;
            } else {
                Intrinsics.throwUninitializedPropertyAccessException("onSeek");
            }
            if (!this.f4741c.d().invoke().booleanValue()) {
                j2 = -10000;
            }
            function1.invoke(Long.valueOf(j2));
        }

        public static final void a(Pair pair, a aVar) {
            Intrinsics.checkNotNullParameter(pair, "$clickCoordinates");
            Intrinsics.checkNotNullParameter(aVar, "this$0");
            if (((Number) pair.getFirst()).floatValue() < ((float) (aVar.f4720a.getFirst().intValue() / 4))) {
                if (aVar.d().invoke().booleanValue()) {
                    aVar.c().invoke();
                } else {
                    aVar.a().invoke(Boolean.TRUE);
                }
            } else if (((Number) pair.getFirst()).floatValue() <= ((float) ((aVar.f4720a.getFirst().intValue() * 3) / 4))) {
                Function0<Unit> function0 = aVar.f4732m;
                if (function0 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("onControlsVisibilityToggle");
                    function0 = null;
                }
                function0.invoke();
            } else if (aVar.d().invoke().booleanValue()) {
                aVar.a().invoke(Boolean.TRUE);
            } else {
                aVar.c().invoke();
            }
        }
    }
}
