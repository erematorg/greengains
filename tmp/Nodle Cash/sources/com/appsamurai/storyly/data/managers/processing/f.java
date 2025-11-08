package com.appsamurai.storyly.data.managers.processing;

import android.content.Context;
import com.appsamurai.storyly.StoryGroupType;
import com.appsamurai.storyly.StorylyDataSource;
import com.appsamurai.storyly.StorylyInit;
import com.appsamurai.storyly.data.p0;
import com.appsamurai.storyly.data.s;
import com.appsamurai.storyly.data.v;
import com.fasterxml.jackson.core.JsonPointer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.concurrent.ThreadsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.properties.Delegates;
import kotlin.properties.ObservableProperty;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.signature.SignatureVisitor;

public final class f {

    /* renamed from: y  reason: collision with root package name */
    public static final /* synthetic */ KProperty<Object>[] f3970y = {androidx.compose.ui.autofill.a.m(f.class, "storylyInit", "getStorylyInit$storyly_release()Lcom/appsamurai/storyly/StorylyInit;", 0)};
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3971a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public com.appsamurai.storyly.analytics.e f3972b;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public final ReadWriteProperty f3973c;
    @NotNull

    /* renamed from: d  reason: collision with root package name */
    public final Lazy f3974d = LazyKt.lazy(j.f4003a);
    @Nullable

    /* renamed from: e  reason: collision with root package name */
    public b f3975e;
    @Nullable

    /* renamed from: f  reason: collision with root package name */
    public Function1<? super String, Unit> f3976f;
    @Nullable

    /* renamed from: g  reason: collision with root package name */
    public Function2<? super List<v>, ? super StorylyDataSource, Unit> f3977g;
    @Nullable

    /* renamed from: h  reason: collision with root package name */
    public Function1<? super List<v>, Unit> f3978h;
    @Nullable

    /* renamed from: i  reason: collision with root package name */
    public a f3979i;
    @Nullable

    /* renamed from: j  reason: collision with root package name */
    public s f3980j;
    @Nullable

    /* renamed from: k  reason: collision with root package name */
    public s f3981k;
    @Nullable

    /* renamed from: l  reason: collision with root package name */
    public s f3982l;
    @Nullable

    /* renamed from: m  reason: collision with root package name */
    public String f3983m;
    @NotNull

    /* renamed from: n  reason: collision with root package name */
    public final Lazy f3984n = LazyKt.lazy(new C0011f(this));
    @NotNull

    /* renamed from: o  reason: collision with root package name */
    public final Lazy f3985o = LazyKt.lazy(new k(this));
    @NotNull

    /* renamed from: p  reason: collision with root package name */
    public final Lazy f3986p = LazyKt.lazy(new l(this));
    @NotNull

    /* renamed from: q  reason: collision with root package name */
    public final Lazy f3987q = LazyKt.lazy(new c(this));
    @NotNull

    /* renamed from: r  reason: collision with root package name */
    public final Lazy f3988r = LazyKt.lazy(i.f4002a);
    @NotNull

    /* renamed from: s  reason: collision with root package name */
    public final Lazy f3989s = LazyKt.lazy(h.f4001a);
    @NotNull

    /* renamed from: t  reason: collision with root package name */
    public final Lazy f3990t = LazyKt.lazy(new g(this));
    @NotNull

    /* renamed from: u  reason: collision with root package name */
    public final Lazy f3991u = LazyKt.lazy(o.f4009a);
    @NotNull

    /* renamed from: v  reason: collision with root package name */
    public final Lazy f3992v = LazyKt.lazy(new n(this));
    @NotNull

    /* renamed from: w  reason: collision with root package name */
    public final Lazy f3993w = LazyKt.lazy(new d(this));
    @NotNull

    /* renamed from: x  reason: collision with root package name */
    public final Lazy f3994x = LazyKt.lazy(e.f3998a);

    public static final class a extends Lambda implements Function0<Unit> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ f f3995a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(f fVar) {
            super(0);
            this.f3995a = fVar;
        }

        public Object invoke() {
            f fVar = this.f3995a;
            fVar.h().b();
            fVar.g().b();
            ((com.appsamurai.storyly.data.managers.storage.b) fVar.f3984n.getValue()).b();
            return Unit.INSTANCE;
        }
    }

    public /* synthetic */ class b {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[e.values().length];
            iArr[1] = 1;
            iArr[2] = 2;
            iArr[0] = 3;
            iArr[3] = 4;
            iArr[5] = 5;
            iArr[4] = 6;
            iArr[6] = 7;
            iArr[7] = 8;
            iArr[8] = 9;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[StoryGroupType.values().length];
            iArr2[StoryGroupType.Default.ordinal()] = 1;
            iArr2[StoryGroupType.Live.ordinal()] = 2;
            iArr2[StoryGroupType.MomentsBlock.ordinal()] = 3;
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public static final class c extends Lambda implements Function0<com.appsamurai.storyly.data.managers.storage.a> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ f f3996a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public c(f fVar) {
            super(0);
            this.f3996a = fVar;
        }

        public Object invoke() {
            return new com.appsamurai.storyly.data.managers.storage.a(this.f3996a.f3971a, "stryly-ab-sets");
        }
    }

    public static final class d extends Lambda implements Function0<com.appsamurai.storyly.data.managers.conditional.b> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ f f3997a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public d(f fVar) {
            super(0);
            this.f3997a = fVar;
        }

        public Object invoke() {
            return new com.appsamurai.storyly.data.managers.conditional.b(this.f3997a.f3971a);
        }
    }

    public static final class e extends Lambda implements Function0<Json> {

        /* renamed from: a  reason: collision with root package name */
        public static final e f3998a = new e();

        public e() {
            super(0);
        }

        public Object invoke() {
            return JsonKt.Json$default((Json) null, g.f4010a, 1, (Object) null);
        }
    }

    /* renamed from: com.appsamurai.storyly.data.managers.processing.f$f  reason: collision with other inner class name */
    public static final class C0011f extends Lambda implements Function0<com.appsamurai.storyly.data.managers.storage.b> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ f f3999a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public C0011f(f fVar) {
            super(0);
            this.f3999a = fVar;
        }

        public Object invoke() {
            return new com.appsamurai.storyly.data.managers.storage.b(this.f3999a.f3971a, "stryly-moments-like-status");
        }
    }

    public static final class g extends Lambda implements Function0<n> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ f f4000a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public g(f fVar) {
            super(0);
            this.f4000a = fVar;
        }

        public Object invoke() {
            return new n(this.f4000a.f3971a);
        }
    }

    public static final class h extends Lambda implements Function0<com.appsamurai.storyly.data.managers.pagination.c> {

        /* renamed from: a  reason: collision with root package name */
        public static final h f4001a = new h();

        public h() {
            super(0);
        }

        public Object invoke() {
            return new com.appsamurai.storyly.data.managers.pagination.c();
        }
    }

    public static final class i extends Lambda implements Function0<com.appsamurai.storyly.data.managers.product.b> {

        /* renamed from: a  reason: collision with root package name */
        public static final i f4002a = new i();

        public i() {
            super(0);
        }

        public Object invoke() {
            return new com.appsamurai.storyly.data.managers.product.b();
        }
    }

    public static final class j extends Lambda implements Function0<d> {

        /* renamed from: a  reason: collision with root package name */
        public static final j f4003a = new j();

        public j() {
            super(0);
        }

        public Object invoke() {
            return new d();
        }
    }

    public static final class k extends Lambda implements Function0<com.appsamurai.storyly.data.managers.storage.c> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ f f4004a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public k(f fVar) {
            super(0);
            this.f4004a = fVar;
        }

        public Object invoke() {
            return new com.appsamurai.storyly.data.managers.storage.c(this.f4004a.f3971a, "stryly-moments-report-status");
        }
    }

    public static final class l extends Lambda implements Function0<com.appsamurai.storyly.data.managers.storage.d> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ f f4005a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public l(f fVar) {
            super(0);
            this.f4005a = fVar;
        }

        public Object invoke() {
            return new com.appsamurai.storyly.data.managers.storage.d(this.f4005a.f3971a, "stryly-seen-state");
        }
    }

    public static final class m extends ObservableProperty<StorylyInit> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Object f4006a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ f f4007b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public m(Object obj, Object obj2, f fVar) {
            super(obj2);
            this.f4006a = obj;
            this.f4007b = fVar;
        }

        public void afterChange(@NotNull KProperty<?> kProperty, StorylyInit storylyInit, StorylyInit storylyInit2) {
            Intrinsics.checkNotNullParameter(kProperty, "property");
            StorylyInit storylyInit3 = storylyInit2;
            StorylyInit storylyInit4 = storylyInit;
            com.appsamurai.storyly.data.managers.product.b e3 = this.f4007b.e();
            e3.getClass();
            e3.f4042b = SetsKt.emptySet();
            e3.f4043c = new LinkedHashSet();
            f fVar = this.f4007b;
            fVar.f3979i = null;
            fVar.l();
        }
    }

    public static final class n extends Lambda implements Function0<com.appsamurai.storyly.data.managers.network.h> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ f f4008a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public n(f fVar) {
            super(0);
            this.f4008a = fVar;
        }

        public Object invoke() {
            f fVar = this.f4008a;
            com.appsamurai.storyly.data.managers.network.h hVar = new com.appsamurai.storyly.data.managers.network.h(fVar.f3971a, fVar.f3972b);
            f fVar2 = this.f4008a;
            hVar.f3926d = new i(fVar2);
            hVar.f3925c = new j(fVar2);
            return hVar;
        }
    }

    public static final class o extends Lambda implements Function0<q> {

        /* renamed from: a  reason: collision with root package name */
        public static final o f4009a = new o();

        public o() {
            super(0);
        }

        public Object invoke() {
            return new q();
        }
    }

    public f(@NotNull Context context, @NotNull StorylyInit storylyInit, @NotNull com.appsamurai.storyly.analytics.e eVar) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(storylyInit, "storylyInit");
        Intrinsics.checkNotNullParameter(eVar, "storylyTracker");
        this.f3971a = context;
        this.f3972b = eVar;
        Delegates delegates = Delegates.INSTANCE;
        this.f3973c = new m(storylyInit, storylyInit, this);
        ThreadsKt.thread$default(false, false, (ClassLoader) null, "tll_thread", 0, new a(this), 23, (Object) null);
    }

    public static void a(f fVar, String str, boolean z2, boolean z3, int i3) {
        Function1<? super String, Unit> function1;
        if ((i3 & 1) != 0) {
            str = null;
        }
        if ((i3 & 2) != 0) {
            z2 = false;
        }
        if ((i3 & 4) != 0) {
            z3 = false;
        }
        if (!(str == null || (function1 = fVar.f3976f) == null)) {
            function1.invoke(str);
        }
        d f2 = fVar.f();
        synchronized (f2) {
            f2.a((b) CollectionsKt.firstOrNull(f2.f3957a));
        }
        fVar.a(z2, z3);
    }

    @NotNull
    public final com.appsamurai.storyly.data.managers.conditional.b b() {
        return (com.appsamurai.storyly.data.managers.conditional.b) this.f3993w.getValue();
    }

    @NotNull
    public final Json c() {
        return (Json) this.f3994x.getValue();
    }

    public final com.appsamurai.storyly.data.managers.pagination.c d() {
        return (com.appsamurai.storyly.data.managers.pagination.c) this.f3989s.getValue();
    }

    @NotNull
    public final com.appsamurai.storyly.data.managers.product.b e() {
        return (com.appsamurai.storyly.data.managers.product.b) this.f3988r.getValue();
    }

    public final d f() {
        return (d) this.f3974d.getValue();
    }

    public final com.appsamurai.storyly.data.managers.storage.c g() {
        return (com.appsamurai.storyly.data.managers.storage.c) this.f3985o.getValue();
    }

    public final com.appsamurai.storyly.data.managers.storage.d h() {
        return (com.appsamurai.storyly.data.managers.storage.d) this.f3986p.getValue();
    }

    @NotNull
    public final StorylyInit i() {
        return (StorylyInit) this.f3973c.getValue(this, f3970y[0]);
    }

    public final com.appsamurai.storyly.data.managers.network.h j() {
        return (com.appsamurai.storyly.data.managers.network.h) this.f3992v.getValue();
    }

    public final q k() {
        return (q) this.f3991u.getValue();
    }

    public final void l() {
        this.f3980j = null;
        this.f3982l = null;
        d().a();
        com.appsamurai.storyly.data.managers.conditional.b b3 = b();
        b3.getClass();
        b3.f3901d = new LinkedHashMap();
        b3.f3902e = new LinkedHashMap();
        this.f3981k = null;
    }

    public static /* synthetic */ void a(f fVar, e eVar, Function0 function0, Function0 function02, int i3) {
        if ((i3 & 2) != 0) {
            function0 = null;
        }
        fVar.a(eVar, function0, (Function0<Unit>) null);
    }

    public final void a(@NotNull e eVar, @Nullable Function0<Unit> function0, @Nullable Function0<Unit> function02) {
        List list;
        b bVar;
        Intrinsics.checkNotNullParameter(eVar, "type");
        switch (eVar.ordinal()) {
            case 0:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                list = CollectionsKt.listOf(new b(c.InQueue, eVar, function0, function02, (b) null, 16));
                break;
            case 1:
                c cVar = c.InQueue;
                list = CollectionsKt.listOf(new b(cVar, e.StorylyData, function0, (Function0) null, (b) null, 16), new b(cVar, e.MomentsIDsData, (Function0) null, function02, (b) null, 16));
                break;
            case 2:
                c cVar2 = c.InQueue;
                list = CollectionsKt.listOf(new b(cVar2, e.ProductFallbackData, (Function0) null, function02, (b) null, 16), new b(cVar2, e.ProductDataUpdate, function0, (Function0) null, (b) null, 16));
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        d f2 = f();
        synchronized (f2) {
            try {
                Intrinsics.checkNotNullParameter(list, "queueItems");
                Iterator it = list.iterator();
                int i3 = 0;
                while (true) {
                    bVar = null;
                    if (it.hasNext()) {
                        Object next = it.next();
                        int i4 = i3 + 1;
                        if (i3 < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        b bVar2 = (b) next;
                        if (i3 < list.size() - 1) {
                            bVar2.f3953e = (b) list.get(i4);
                        }
                        int ordinal = bVar2.f3950b.ordinal();
                        if (ordinal == 0) {
                            f2.a(e.StorylyLocalData);
                        } else if (ordinal == 1) {
                            f2.a((e) null);
                        } else if (ordinal == 2) {
                            f2.a(e.ProductFallbackData);
                        } else if (ordinal == 4) {
                            f2.a(e.MomentsIDsData);
                            f2.a(e.PageData);
                        } else if (ordinal == 5) {
                            f2.a(e.UserDataUpdate);
                        } else if (ordinal == 7) {
                            f2.a(e.ProductDataUpdate);
                        }
                        f2.f3957a.add(bVar2);
                        i3 = i4;
                    } else if (f2.f3957a.size() == list.size()) {
                        bVar = f2.a();
                    }
                }
            } finally {
            }
        }
        if (bVar != null) {
            this.f3975e = bVar;
            a(bVar);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: java.util.List<com.appsamurai.storyly.data.v>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v29, resolved type: com.appsamurai.storyly.data.s} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: java.util.List<com.appsamurai.storyly.data.v>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: java.util.List<com.appsamurai.storyly.data.v>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: java.util.List<com.appsamurai.storyly.data.v>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: java.util.List<com.appsamurai.storyly.data.v>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: java.util.List<com.appsamurai.storyly.data.v>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: java.util.List<com.appsamurai.storyly.data.v>} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(com.appsamurai.storyly.data.managers.processing.b r14) {
        /*
            r13 = this;
            com.appsamurai.storyly.data.managers.processing.e r14 = r14.f3950b
            int r14 = r14.ordinal()
            r0 = 2
            r1 = 7
            r2 = 1
            r3 = 0
            r4 = 0
            switch(r14) {
                case 0: goto L_0x020f;
                case 1: goto L_0x01fa;
                case 2: goto L_0x01b3;
                case 3: goto L_0x00c6;
                case 4: goto L_0x00a7;
                case 5: goto L_0x0065;
                case 6: goto L_0x002e;
                case 7: goto L_0x0015;
                case 8: goto L_0x0010;
                default: goto L_0x000e;
            }
        L_0x000e:
            goto L_0x0255
        L_0x0010:
            r13.a(r2, r2)
            goto L_0x0255
        L_0x0015:
            com.appsamurai.storyly.data.managers.product.b r14 = r13.e()
            com.appsamurai.storyly.data.s r0 = r13.f3980j
            if (r0 != 0) goto L_0x001e
            goto L_0x0020
        L_0x001e:
            java.util.List<com.appsamurai.storyly.data.v> r3 = r0.f4197a
        L_0x0020:
            if (r3 != 0) goto L_0x0026
            java.util.List r3 = kotlin.collections.CollectionsKt.emptyList()
        L_0x0026:
            r14.a(r3)
            r13.a(r2, r2)
            goto L_0x0255
        L_0x002e:
            com.appsamurai.storyly.data.s r14 = r13.f3982l
            if (r14 != 0) goto L_0x0034
            r14 = r3
            goto L_0x005e
        L_0x0034:
            com.appsamurai.storyly.data.managers.storage.d r5 = r13.h()
            java.util.List<com.appsamurai.storyly.data.v> r6 = r14.f4197a
            java.util.List r5 = r5.a(r6)
            r14.a(r5)
            com.appsamurai.storyly.data.managers.storage.c r5 = r13.g()
            java.util.List<com.appsamurai.storyly.data.v> r6 = r14.f4197a
            java.util.List r5 = r5.a(r6)
            r14.a(r5)
            com.appsamurai.storyly.data.managers.processing.q r5 = r13.k()
            java.util.List<com.appsamurai.storyly.data.v> r6 = r14.f4197a
            java.util.List r5 = r5.a(r6)
            r14.a(r5)
            a(r13, r2, r4, r0)
        L_0x005e:
            if (r14 != 0) goto L_0x0255
            a((com.appsamurai.storyly.data.managers.processing.f) r13, (java.lang.String) r3, (boolean) r4, (boolean) r4, (int) r1)
            goto L_0x0255
        L_0x0065:
            com.appsamurai.storyly.data.managers.processing.a r14 = r13.f3979i
            if (r14 != 0) goto L_0x006b
            r14 = r3
            goto L_0x006d
        L_0x006b:
            java.lang.String r14 = r14.f3947a
        L_0x006d:
            com.appsamurai.storyly.data.s r14 = r13.a((java.lang.String) r14)
            if (r14 != 0) goto L_0x0074
            goto L_0x009d
        L_0x0074:
            r13.f3980j = r14
            com.appsamurai.storyly.data.managers.pagination.c r0 = r13.d()
            r0.getClass()
            java.util.UUID r1 = java.util.UUID.randomUUID()
            java.lang.String r1 = r1.toString()
            r0.f3942a = r1
            com.appsamurai.storyly.data.managers.product.b r0 = r13.e()
            java.util.List<com.appsamurai.storyly.data.v> r1 = r14.f4197a
            r0.a(r1)
            com.appsamurai.storyly.data.managers.conditional.b r0 = r13.b()
            java.util.List<com.appsamurai.storyly.data.v> r1 = r14.f4197a
            r0.a((java.util.List<com.appsamurai.storyly.data.v>) r1)
            r13.a(r2, r2)
            r3 = r14
        L_0x009d:
            if (r3 != 0) goto L_0x0255
            java.lang.String r14 = "Storyly data parse failed for user data failed!"
            r0 = 6
            a((com.appsamurai.storyly.data.managers.processing.f) r13, (java.lang.String) r14, (boolean) r4, (boolean) r4, (int) r0)
            goto L_0x0255
        L_0x00a7:
            java.lang.String r14 = r13.f3983m
            if (r14 != 0) goto L_0x00ad
            r14 = r3
            goto L_0x00bf
        L_0x00ad:
            com.appsamurai.storyly.data.managers.network.c r0 = new com.appsamurai.storyly.data.managers.network.c
            android.content.Context r2 = r13.f3971a
            com.appsamurai.storyly.StorylyInit r5 = r13.i()
            r0.<init>(r2, r5, r14)
            com.appsamurai.storyly.data.managers.network.h r2 = r13.j()
            r2.b(r0)
        L_0x00bf:
            if (r14 != 0) goto L_0x0255
            a((com.appsamurai.storyly.data.managers.processing.f) r13, (java.lang.String) r3, (boolean) r4, (boolean) r4, (int) r1)
            goto L_0x0255
        L_0x00c6:
            com.appsamurai.storyly.data.managers.pagination.c r14 = r13.d()
            java.util.List<com.appsamurai.storyly.data.managers.pagination.a> r14 = r14.f3944c
            boolean r14 = r14.isEmpty()
            if (r14 == 0) goto L_0x00d6
            a(r13, r2, r4, r0)
            return
        L_0x00d6:
            java.lang.String r14 = r13.f3983m
            com.appsamurai.storyly.data.managers.pagination.c r5 = r13.d()
            java.util.List<com.appsamurai.storyly.data.managers.pagination.a> r6 = r5.f3944c
            int r6 = r6.size()
            int r7 = r5.f3945d
            if (r6 > r7) goto L_0x00ed
            java.util.List r6 = kotlin.collections.CollectionsKt.emptyList()
            r5.f3946e = r6
            goto L_0x010d
        L_0x00ed:
            java.util.List<com.appsamurai.storyly.data.managers.pagination.a> r6 = r5.f3944c
            int r8 = r7 + 8
            int r9 = r6.size()
            if (r9 >= r8) goto L_0x00fd
            java.util.List<com.appsamurai.storyly.data.managers.pagination.a> r8 = r5.f3944c
            int r8 = r8.size()
        L_0x00fd:
            kotlin.ranges.IntRange r7 = kotlin.ranges.RangesKt.until((int) r7, (int) r8)
            java.util.List r6 = kotlin.collections.CollectionsKt.slice(r6, (kotlin.ranges.IntRange) r7)
            r5.f3946e = r6
            int r7 = r5.f3945d
            int r7 = r7 + 8
            r5.f3945d = r7
        L_0x010d:
            com.appsamurai.storyly.data.s r5 = r13.f3981k
            if (r5 != 0) goto L_0x0112
            goto L_0x0116
        L_0x0112:
            java.util.List<com.appsamurai.storyly.data.v> r5 = r5.f4197a
            if (r5 != 0) goto L_0x0118
        L_0x0116:
            r5 = r3
            goto L_0x013b
        L_0x0118:
            java.util.ArrayList r7 = new java.util.ArrayList
            int r8 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r5, 10)
            r7.<init>(r8)
            java.util.Iterator r5 = r5.iterator()
        L_0x0125:
            boolean r8 = r5.hasNext()
            if (r8 == 0) goto L_0x0137
            java.lang.Object r8 = r5.next()
            com.appsamurai.storyly.data.v r8 = (com.appsamurai.storyly.data.v) r8
            java.lang.String r8 = r8.f4221a
            r7.add(r8)
            goto L_0x0125
        L_0x0137:
            java.util.Set r5 = kotlin.collections.CollectionsKt.toSet(r7)
        L_0x013b:
            if (r5 != 0) goto L_0x0141
            java.util.Set r5 = kotlin.collections.SetsKt.emptySet()
        L_0x0141:
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            java.util.Iterator r8 = r6.iterator()
        L_0x014a:
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto L_0x0169
            java.lang.Object r9 = r8.next()
            r10 = r9
            com.appsamurai.storyly.data.managers.pagination.a r10 = (com.appsamurai.storyly.data.managers.pagination.a) r10
            com.appsamurai.storyly.StoryGroupType r11 = r10.f3933c
            com.appsamurai.storyly.StoryGroupType r12 = com.appsamurai.storyly.StoryGroupType.MomentsDefault
            if (r11 != r12) goto L_0x014a
            java.lang.String r10 = r10.f3931a
            boolean r10 = r5.contains(r10)
            if (r10 != 0) goto L_0x014a
            r7.add(r9)
            goto L_0x014a
        L_0x0169:
            java.util.ArrayList r5 = new java.util.ArrayList
            int r8 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r7, 10)
            r5.<init>(r8)
            java.util.Iterator r7 = r7.iterator()
        L_0x0176:
            boolean r8 = r7.hasNext()
            if (r8 == 0) goto L_0x0188
            java.lang.Object r8 = r7.next()
            com.appsamurai.storyly.data.managers.pagination.a r8 = (com.appsamurai.storyly.data.managers.pagination.a) r8
            java.lang.String r8 = r8.f3931a
            r5.add(r8)
            goto L_0x0176
        L_0x0188:
            if (r14 == 0) goto L_0x01a5
            boolean r7 = r5.isEmpty()
            if (r7 == 0) goto L_0x0191
            goto L_0x01a5
        L_0x0191:
            com.appsamurai.storyly.data.managers.network.d r0 = new com.appsamurai.storyly.data.managers.network.d
            android.content.Context r1 = r13.f3971a
            com.appsamurai.storyly.StorylyInit r2 = r13.i()
            r0.<init>(r1, r2, r14, r5)
            com.appsamurai.storyly.data.managers.network.h r13 = r13.j()
            r13.b(r0)
            goto L_0x0255
        L_0x01a5:
            boolean r14 = r6.isEmpty()
            if (r14 != 0) goto L_0x01af
            a(r13, r2, r4, r0)
            goto L_0x01b2
        L_0x01af:
            a((com.appsamurai.storyly.data.managers.processing.f) r13, (java.lang.String) r3, (boolean) r4, (boolean) r4, (int) r1)
        L_0x01b2:
            return
        L_0x01b3:
            com.appsamurai.storyly.StorylyInit r14 = r13.i()
            com.appsamurai.storyly.config.StorylyConfig r14 = r14.getConfig()
            com.appsamurai.storyly.config.StorylyProductConfig r14 = r14.getProduct$storyly_release()
            boolean r14 = r14.isFallbackEnabled$storyly_release()
            if (r14 == 0) goto L_0x01f6
            com.appsamurai.storyly.data.managers.product.b r14 = r13.e()
            java.util.Set<com.appsamurai.storyly.data.j> r0 = r14.f4043c
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x01f6
            java.util.Set<com.appsamurai.storyly.data.managers.product.STRProductItem> r14 = r14.f4042b
            boolean r14 = r14.isEmpty()
            if (r14 == 0) goto L_0x01f6
            com.appsamurai.storyly.data.managers.network.j r14 = new com.appsamurai.storyly.data.managers.network.j
            android.content.Context r0 = r13.f3971a
            com.appsamurai.storyly.StorylyInit r1 = r13.i()
            com.appsamurai.storyly.data.managers.product.b r2 = r13.e()
            java.util.Set<com.appsamurai.storyly.data.j> r2 = r2.f4043c
            java.util.List r2 = kotlin.collections.CollectionsKt.toList(r2)
            r14.<init>(r0, r1, r2)
            com.appsamurai.storyly.data.managers.network.h r13 = r13.j()
            r13.b(r14)
            goto L_0x0255
        L_0x01f6:
            a((com.appsamurai.storyly.data.managers.processing.f) r13, (java.lang.String) r3, (boolean) r4, (boolean) r4, (int) r1)
            return
        L_0x01fa:
            com.appsamurai.storyly.data.managers.network.i r14 = new com.appsamurai.storyly.data.managers.network.i
            android.content.Context r0 = r13.f3971a
            com.appsamurai.storyly.StorylyInit r1 = r13.i()
            com.appsamurai.storyly.data.managers.processing.a r2 = r13.f3979i
            r14.<init>(r0, r1, r2)
            com.appsamurai.storyly.data.managers.network.h r13 = r13.j()
            r13.b(r14)
            goto L_0x0255
        L_0x020f:
            com.appsamurai.storyly.data.s r14 = r13.f3982l
            if (r14 != 0) goto L_0x0214
            goto L_0x0223
        L_0x0214:
            java.util.List<com.appsamurai.storyly.data.v> r14 = r14.f4197a
            if (r14 != 0) goto L_0x0219
            goto L_0x0223
        L_0x0219:
            boolean r14 = r14.isEmpty()
            r14 = r14 ^ r2
            if (r14 != r2) goto L_0x0223
            a((com.appsamurai.storyly.data.managers.processing.f) r13, (java.lang.String) r3, (boolean) r4, (boolean) r4, (int) r1)
        L_0x0223:
            kotlin.Lazy r14 = r13.f3990t
            java.lang.Object r14 = r14.getValue()
            com.appsamurai.storyly.data.managers.processing.n r14 = (com.appsamurai.storyly.data.managers.processing.n) r14
            com.appsamurai.storyly.StorylyInit r0 = r13.i()
            java.lang.String r0 = r0.getStorylyId()
            com.appsamurai.storyly.data.managers.processing.h r1 = new com.appsamurai.storyly.data.managers.processing.h
            r1.<init>(r13)
            r14.getClass()
            java.lang.String r13 = "storylyId"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r13)
            kotlinx.coroutines.CoroutineDispatcher r13 = kotlinx.coroutines.Dispatchers.getIO()
            kotlinx.coroutines.CoroutineScope r4 = kotlinx.coroutines.CoroutineScopeKt.CoroutineScope(r13)
            com.appsamurai.storyly.data.managers.processing.l r7 = new com.appsamurai.storyly.data.managers.processing.l
            r7.<init>(r14, r0, r1, r3)
            r5 = 0
            r6 = 0
            r8 = 3
            r9 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r4, r5, r6, r7, r8, r9)
        L_0x0255:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.managers.processing.f.a(com.appsamurai.storyly.data.managers.processing.b):void");
    }

    public static /* synthetic */ void a(f fVar, boolean z2, boolean z3, int i3) {
        if ((i3 & 1) != 0) {
            z2 = false;
        }
        if ((i3 & 2) != 0) {
            z3 = false;
        }
        fVar.a(z2, z3);
    }

    /* JADX WARNING: Failed to insert additional move for type inference */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(boolean r12, boolean r13) {
        /*
            r11 = this;
            com.appsamurai.storyly.data.managers.processing.b r0 = r11.f3975e
            if (r0 != 0) goto L_0x0006
            goto L_0x0291
        L_0x0006:
            com.appsamurai.storyly.data.managers.processing.e r0 = r0.f3950b
            if (r0 != 0) goto L_0x000c
            goto L_0x0291
        L_0x000c:
            r1 = 0
            if (r12 == 0) goto L_0x01a1
            int r12 = r0.ordinal()
            switch(r12) {
                case 0: goto L_0x018b;
                case 1: goto L_0x018b;
                case 2: goto L_0x018b;
                case 3: goto L_0x001c;
                case 4: goto L_0x018b;
                case 5: goto L_0x001c;
                case 6: goto L_0x018b;
                case 7: goto L_0x001c;
                case 8: goto L_0x001c;
                default: goto L_0x0016;
            }
        L_0x0016:
            kotlin.NoWhenBranchMatchedException r11 = new kotlin.NoWhenBranchMatchedException
            r11.<init>()
            throw r11
        L_0x001c:
            com.appsamurai.storyly.data.managers.storage.d r12 = r11.h()
            com.appsamurai.storyly.data.s r2 = r11.f3982l
            if (r2 != 0) goto L_0x0026
            r2 = r1
            goto L_0x0028
        L_0x0026:
            java.util.List<com.appsamurai.storyly.data.v> r2 = r2.f4197a
        L_0x0028:
            r12.b(r2)
            com.appsamurai.storyly.data.s r12 = r11.f3980j
            if (r12 != 0) goto L_0x0035
            java.util.List r12 = kotlin.collections.CollectionsKt.emptyList()
            goto L_0x0193
        L_0x0035:
            java.util.List<com.appsamurai.storyly.data.v> r2 = r12.f4197a
            com.appsamurai.storyly.data.s r3 = r11.f3981k
            if (r3 != 0) goto L_0x003d
            r3 = r1
            goto L_0x003f
        L_0x003d:
            java.util.List<com.appsamurai.storyly.data.v> r3 = r3.f4197a
        L_0x003f:
            if (r3 != 0) goto L_0x0045
            java.util.List r3 = kotlin.collections.CollectionsKt.emptyList()
        L_0x0045:
            java.util.List r2 = kotlin.collections.CollectionsKt.plus(r2, r3)
            int r3 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r2, 10)
            int r3 = kotlin.collections.MapsKt.mapCapacity(r3)
            r4 = 16
            int r3 = kotlin.ranges.RangesKt.coerceAtLeast((int) r3, (int) r4)
            java.util.LinkedHashMap r4 = new java.util.LinkedHashMap
            r4.<init>(r3)
            java.util.Iterator r2 = r2.iterator()
        L_0x0060:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x007e
            java.lang.Object r3 = r2.next()
            com.appsamurai.storyly.data.v r3 = (com.appsamurai.storyly.data.v) r3
            java.lang.String r5 = r3.f4221a
            kotlin.Pair r3 = kotlin.TuplesKt.to(r5, r3)
            java.lang.Object r5 = r3.getFirst()
            java.lang.Object r3 = r3.getSecond()
            r4.put(r5, r3)
            goto L_0x0060
        L_0x007e:
            com.appsamurai.storyly.data.managers.pagination.c r2 = r11.d()
            int r3 = r2.f3945d
            java.util.List<com.appsamurai.storyly.data.managers.pagination.a> r5 = r2.f3944c
            int r5 = r5.size()
            if (r3 < r5) goto L_0x0093
            java.util.List<com.appsamurai.storyly.data.managers.pagination.a> r3 = r2.f3944c
            int r3 = r3.size()
            goto L_0x0095
        L_0x0093:
            int r3 = r2.f3945d
        L_0x0095:
            java.util.List<com.appsamurai.storyly.data.managers.pagination.a> r2 = r2.f3944c
            r5 = 0
            kotlin.ranges.IntRange r3 = kotlin.ranges.RangesKt.until((int) r5, (int) r3)
            java.util.List r2 = kotlin.collections.CollectionsKt.slice(r2, (kotlin.ranges.IntRange) r3)
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            java.util.Iterator r2 = r2.iterator()
        L_0x00a9:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x00d6
            java.lang.Object r3 = r2.next()
            com.appsamurai.storyly.data.managers.pagination.a r3 = (com.appsamurai.storyly.data.managers.pagination.a) r3
            java.lang.String r5 = r3.f3931a
            java.lang.Object r5 = r4.get(r5)
            com.appsamurai.storyly.data.v r5 = (com.appsamurai.storyly.data.v) r5
            if (r5 != 0) goto L_0x00c0
            goto L_0x00c7
        L_0x00c0:
            boolean r7 = r5.c()
            if (r7 != 0) goto L_0x00c7
            goto L_0x00c8
        L_0x00c7:
            r5 = r1
        L_0x00c8:
            if (r5 != 0) goto L_0x00cc
            r5 = r1
            goto L_0x00d0
        L_0x00cc:
            int r3 = r3.f3935e
            r5.f4225e = r3
        L_0x00d0:
            if (r5 == 0) goto L_0x00a9
            r6.add(r5)
            goto L_0x00a9
        L_0x00d6:
            com.appsamurai.storyly.data.s r2 = new com.appsamurai.storyly.data.s
            com.appsamurai.storyly.data.a r7 = r12.f4198b
            com.appsamurai.storyly.data.p0 r8 = r12.f4199c
            com.appsamurai.storyly.data.m0 r10 = r12.f4201e
            r9 = 0
            r5 = r2
            r5.<init>(r6, r7, r8, r9, r10)
            java.util.List<com.appsamurai.storyly.data.v> r12 = r2.f4197a
            java.util.Iterator r12 = r12.iterator()
        L_0x00e9:
            boolean r3 = r12.hasNext()
            if (r3 == 0) goto L_0x0128
            java.lang.Object r3 = r12.next()
            com.appsamurai.storyly.data.v r3 = (com.appsamurai.storyly.data.v) r3
            java.util.List<com.appsamurai.storyly.data.z> r4 = r3.f4226f
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.util.Iterator r4 = r4.iterator()
        L_0x0100:
            boolean r6 = r4.hasNext()
            if (r6 == 0) goto L_0x0121
            java.lang.Object r6 = r4.next()
            r7 = r6
            com.appsamurai.storyly.data.z r7 = (com.appsamurai.storyly.data.z) r7
            java.lang.Long r7 = r7.f4321t
            if (r7 == 0) goto L_0x011d
            long r7 = r7.longValue()
            long r9 = java.lang.System.currentTimeMillis()
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 <= 0) goto L_0x0100
        L_0x011d:
            r5.add(r6)
            goto L_0x0100
        L_0x0121:
            java.util.List r4 = kotlin.collections.CollectionsKt.toMutableList(r5)
            r3.f4226f = r4
            goto L_0x00e9
        L_0x0128:
            java.util.List<com.appsamurai.storyly.data.v> r12 = r2.f4197a
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Iterator r12 = r12.iterator()
        L_0x0133:
            boolean r4 = r12.hasNext()
            if (r4 == 0) goto L_0x015c
            java.lang.Object r4 = r12.next()
            r5 = r4
            com.appsamurai.storyly.data.v r5 = (com.appsamurai.storyly.data.v) r5
            java.lang.Long r6 = r5.f4239s
            if (r6 == 0) goto L_0x0150
            long r6 = r6.longValue()
            long r8 = java.lang.System.currentTimeMillis()
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 <= 0) goto L_0x0133
        L_0x0150:
            java.util.List<com.appsamurai.storyly.data.z> r5 = r5.f4226f
            boolean r5 = r5.isEmpty()
            if (r5 != 0) goto L_0x0133
            r3.add(r4)
            goto L_0x0133
        L_0x015c:
            r2.a(r3)
            com.appsamurai.storyly.data.managers.storage.d r12 = r11.h()
            java.util.List<com.appsamurai.storyly.data.v> r3 = r2.f4197a
            java.util.List r12 = r12.a(r3)
            r2.a(r12)
            com.appsamurai.storyly.data.managers.storage.c r12 = r11.g()
            java.util.List<com.appsamurai.storyly.data.v> r3 = r2.f4197a
            java.util.List r12 = r12.a(r3)
            r2.a(r12)
            com.appsamurai.storyly.data.managers.processing.q r12 = r11.k()
            java.util.List<com.appsamurai.storyly.data.v> r3 = r2.f4197a
            java.util.List r12 = r12.a(r3)
            r2.a(r12)
            r11.f3982l = r2
            java.util.List<com.appsamurai.storyly.data.v> r12 = r2.f4197a
            goto L_0x0193
        L_0x018b:
            com.appsamurai.storyly.data.s r12 = r11.f3982l
            if (r12 != 0) goto L_0x0191
            r12 = r1
            goto L_0x0193
        L_0x0191:
            java.util.List<com.appsamurai.storyly.data.v> r12 = r12.f4197a
        L_0x0193:
            kotlin.jvm.functions.Function1<? super java.util.List<com.appsamurai.storyly.data.v>, kotlin.Unit> r2 = r11.f3978h
            if (r2 != 0) goto L_0x0198
            goto L_0x01a1
        L_0x0198:
            if (r12 != 0) goto L_0x019e
            java.util.List r12 = kotlin.collections.CollectionsKt.emptyList()
        L_0x019e:
            r2.invoke(r12)
        L_0x01a1:
            if (r13 == 0) goto L_0x0291
            com.appsamurai.storyly.data.s r12 = r11.f3982l
            if (r12 != 0) goto L_0x01a9
            r12 = r1
            goto L_0x01ab
        L_0x01a9:
            java.util.List<com.appsamurai.storyly.data.v> r12 = r12.f4197a
        L_0x01ab:
            if (r12 != 0) goto L_0x01b1
            java.util.List r12 = kotlin.collections.CollectionsKt.emptyList()
        L_0x01b1:
            com.appsamurai.storyly.data.s r13 = r11.f3982l
            if (r13 != 0) goto L_0x01b6
            goto L_0x01ba
        L_0x01b6:
            java.util.List<com.appsamurai.storyly.data.v> r13 = r13.f4197a
            if (r13 != 0) goto L_0x01bc
        L_0x01ba:
            r13 = r1
            goto L_0x01df
        L_0x01bc:
            java.util.ArrayList r2 = new java.util.ArrayList
            int r3 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r13, 10)
            r2.<init>(r3)
            java.util.Iterator r13 = r13.iterator()
        L_0x01c9:
            boolean r3 = r13.hasNext()
            if (r3 == 0) goto L_0x01db
            java.lang.Object r3 = r13.next()
            com.appsamurai.storyly.data.v r3 = (com.appsamurai.storyly.data.v) r3
            java.lang.String r3 = r3.f4221a
            r2.add(r3)
            goto L_0x01c9
        L_0x01db:
            java.util.Set r13 = kotlin.collections.CollectionsKt.toSet(r2)
        L_0x01df:
            if (r13 != 0) goto L_0x01e5
            java.util.Set r13 = kotlin.collections.SetsKt.emptySet()
        L_0x01e5:
            java.util.List r2 = r11.a()
            if (r2 != 0) goto L_0x01ec
            goto L_0x020e
        L_0x01ec:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.Iterator r2 = r2.iterator()
        L_0x01f5:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x020e
            java.lang.Object r3 = r2.next()
            r4 = r3
            com.appsamurai.storyly.data.v r4 = (com.appsamurai.storyly.data.v) r4
            java.lang.String r4 = r4.f4221a
            boolean r4 = r13.contains(r4)
            if (r4 != 0) goto L_0x01f5
            r1.add(r3)
            goto L_0x01f5
        L_0x020e:
            if (r1 != 0) goto L_0x0214
            java.util.List r1 = kotlin.collections.CollectionsKt.emptyList()
        L_0x0214:
            com.appsamurai.storyly.data.managers.storage.d r13 = r11.h()
            java.util.List r13 = r13.a(r1)
            com.appsamurai.storyly.data.managers.processing.q r1 = r11.k()
            java.util.List r13 = r1.a(r13)
            kotlin.jvm.functions.Function2<? super java.util.List<com.appsamurai.storyly.data.v>, ? super com.appsamurai.storyly.StorylyDataSource, kotlin.Unit> r1 = r11.f3977g
            if (r1 != 0) goto L_0x0229
            goto L_0x024e
        L_0x0229:
            java.util.List r12 = kotlin.collections.CollectionsKt.plus(r12, r13)
            int r13 = r0.ordinal()
            switch(r13) {
                case 0: goto L_0x0249;
                case 1: goto L_0x0246;
                case 2: goto L_0x0243;
                case 3: goto L_0x0240;
                case 4: goto L_0x0240;
                case 5: goto L_0x023d;
                case 6: goto L_0x0249;
                case 7: goto L_0x0243;
                case 8: goto L_0x023a;
                default: goto L_0x0234;
            }
        L_0x0234:
            kotlin.NoWhenBranchMatchedException r11 = new kotlin.NoWhenBranchMatchedException
            r11.<init>()
            throw r11
        L_0x023a:
            com.appsamurai.storyly.StorylyDataSource r13 = com.appsamurai.storyly.StorylyDataSource.ConditionData
            goto L_0x024b
        L_0x023d:
            com.appsamurai.storyly.StorylyDataSource r13 = com.appsamurai.storyly.StorylyDataSource.UserData
            goto L_0x024b
        L_0x0240:
            com.appsamurai.storyly.StorylyDataSource r13 = com.appsamurai.storyly.StorylyDataSource.MomentsAPI
            goto L_0x024b
        L_0x0243:
            com.appsamurai.storyly.StorylyDataSource r13 = com.appsamurai.storyly.StorylyDataSource.ProductData
            goto L_0x024b
        L_0x0246:
            com.appsamurai.storyly.StorylyDataSource r13 = com.appsamurai.storyly.StorylyDataSource.API
            goto L_0x024b
        L_0x0249:
            com.appsamurai.storyly.StorylyDataSource r13 = com.appsamurai.storyly.StorylyDataSource.Local
        L_0x024b:
            r1.invoke(r12, r13)
        L_0x024e:
            com.appsamurai.storyly.data.managers.processing.e r12 = com.appsamurai.storyly.data.managers.processing.e.StorylyData
            if (r0 == r12) goto L_0x0256
            com.appsamurai.storyly.data.managers.processing.e r12 = com.appsamurai.storyly.data.managers.processing.e.StorylyLocalData
            if (r0 != r12) goto L_0x0291
        L_0x0256:
            com.appsamurai.storyly.data.managers.processing.a r4 = r11.f3979i
            if (r4 != 0) goto L_0x025b
            goto L_0x0291
        L_0x025b:
            kotlin.Lazy r12 = r11.f3990t
            java.lang.Object r12 = r12.getValue()
            r2 = r12
            com.appsamurai.storyly.data.managers.processing.n r2 = (com.appsamurai.storyly.data.managers.processing.n) r2
            com.appsamurai.storyly.StorylyInit r12 = r11.i()
            java.lang.String r3 = r12.getStorylyId()
            r2.getClass()
            java.lang.String r12 = "storylyId"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r12)
            java.lang.String r12 = "localData"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r12)
            kotlinx.coroutines.CoroutineDispatcher r12 = kotlinx.coroutines.Dispatchers.getIO()
            kotlinx.coroutines.CoroutineScope r12 = kotlinx.coroutines.CoroutineScopeKt.CoroutineScope(r12)
            com.appsamurai.storyly.data.managers.processing.m r8 = new com.appsamurai.storyly.data.managers.processing.m
            r5 = 0
            r6 = 0
            r1 = r8
            r1.<init>(r2, r3, r4, r5, r6)
            r7 = 0
            r9 = 3
            r10 = 0
            r5 = r12
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r5, r6, r7, r8, r9, r10)
        L_0x0291:
            com.appsamurai.storyly.data.managers.processing.d r12 = r11.f()
            monitor-enter(r12)
            java.util.List<com.appsamurai.storyly.data.managers.processing.b> r13 = r12.f3957a     // Catch:{ all -> 0x02bc }
            java.lang.Object r13 = kotlin.collections.CollectionsKt.firstOrNull(r13)     // Catch:{ all -> 0x02bc }
            com.appsamurai.storyly.data.managers.processing.b r13 = (com.appsamurai.storyly.data.managers.processing.b) r13     // Catch:{ all -> 0x02bc }
            if (r13 != 0) goto L_0x02a1
            goto L_0x02ae
        L_0x02a1:
            kotlin.jvm.functions.Function0<kotlin.Unit> r0 = r13.f3952d     // Catch:{ all -> 0x02bc }
            if (r0 != 0) goto L_0x02a6
            goto L_0x02a9
        L_0x02a6:
            r0.invoke()     // Catch:{ all -> 0x02bc }
        L_0x02a9:
            java.util.List<com.appsamurai.storyly.data.managers.processing.b> r0 = r12.f3957a     // Catch:{ all -> 0x02bc }
            r0.remove(r13)     // Catch:{ all -> 0x02bc }
        L_0x02ae:
            com.appsamurai.storyly.data.managers.processing.b r13 = r12.a()     // Catch:{ all -> 0x02bc }
            monitor-exit(r12)
            r11.f3975e = r13
            if (r13 != 0) goto L_0x02b8
            goto L_0x02bb
        L_0x02b8:
            r11.a((com.appsamurai.storyly.data.managers.processing.b) r13)
        L_0x02bb:
            return
        L_0x02bc:
            r11 = move-exception
            monitor-exit(r12)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.managers.processing.f.a(boolean, boolean):void");
    }

    public final void a(a aVar) {
        Map<String, String> map;
        T t2;
        l();
        if (i().getStorylyId().length() == 0) {
            a(this, Intrinsics.stringPlus("Please set storylyId to a valid value. storylyId is ", i().getStorylyId()), false, false, 6);
            return;
        }
        s a2 = a(aVar.f3947a);
        String str = null;
        if (a2 == null) {
            a2 = null;
        } else {
            this.f3979i = aVar;
            this.f3980j = a2;
            com.appsamurai.storyly.data.managers.storage.a aVar2 = (com.appsamurai.storyly.data.managers.storage.a) this.f3987q.getValue();
            String storylyId = i().getStorylyId();
            aVar2.getClass();
            Intrinsics.checkNotNullParameter(storylyId, "storylyId");
            p0 p0Var = a2.f4199c;
            if (p0Var == null) {
                map = null;
            } else {
                map = p0Var.f4117a;
            }
            String str2 = "";
            if (map != null) {
                for (Map.Entry next : map.entrySet()) {
                    String str3 = (String) next.getKey();
                    String str4 = (String) next.getValue();
                    if (str3.length() > 0 && str4.length() > 0) {
                        str2 = str2 + str3 + SignatureVisitor.SUPER + str4 + JsonPointer.SEPARATOR;
                    }
                }
            }
            aVar2.a(storylyId, (Object) str2);
            Iterator<T> it = a2.f4197a.iterator();
            while (true) {
                if (!it.hasNext()) {
                    t2 = null;
                    break;
                }
                t2 = it.next();
                if (((v) t2).f4228h == StoryGroupType.MomentsBlock) {
                    break;
                }
            }
            v vVar = (v) t2;
            if (vVar != null) {
                str = vVar.f4233m;
            }
            this.f3983m = str;
            e().a(a2.f4197a);
            b().a(a2.f4197a);
            i().getConfig().setStorylyStyle$storyly_release(a2.f4201e);
            a((List<com.appsamurai.storyly.data.managers.pagination.a>) CollectionsKt.emptyList());
            a(this, false, true, 1);
        }
        if (a2 == null) {
            a(this, Intrinsics.stringPlus("Data parse failed, storylyId is ", i().getStorylyId()), false, false, 6);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0056, code lost:
        if (r9 >= r2) goto L_0x0059;
     */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x015f A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(java.util.List<com.appsamurai.storyly.data.managers.pagination.a> r21) {
        /*
            r20 = this;
            r0 = 2
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            int r2 = r21.size()
            r3 = r20
            com.appsamurai.storyly.data.s r4 = r3.f3980j
            r5 = 0
            r6 = 1
            r7 = 0
            if (r4 != 0) goto L_0x0015
            goto L_0x00c2
        L_0x0015:
            java.util.List<com.appsamurai.storyly.data.v> r4 = r4.f4197a
            if (r4 != 0) goto L_0x001b
            goto L_0x00c2
        L_0x001b:
            java.util.Iterator r4 = r4.iterator()
            r8 = r7
        L_0x0020:
            boolean r9 = r4.hasNext()
            if (r9 == 0) goto L_0x00c2
            java.lang.Object r9 = r4.next()
            com.appsamurai.storyly.data.v r9 = (com.appsamurai.storyly.data.v) r9
            com.appsamurai.storyly.StoryGroupType r10 = r9.f4228h
            int[] r11 = com.appsamurai.storyly.data.managers.processing.f.b.$EnumSwitchMapping$1
            int r10 = r10.ordinal()
            r10 = r11[r10]
            if (r10 == r6) goto L_0x007d
            if (r10 == r0) goto L_0x007d
            r11 = 3
            if (r10 == r11) goto L_0x0040
        L_0x003d:
            r12 = r21
            goto L_0x0020
        L_0x0040:
            java.lang.Integer r9 = r9.f4235o
            if (r9 != 0) goto L_0x0045
            goto L_0x0059
        L_0x0045:
            int r10 = r9.intValue()
            r11 = -1
            if (r10 == r11) goto L_0x004d
            goto L_0x004e
        L_0x004d:
            r9 = r5
        L_0x004e:
            if (r9 != 0) goto L_0x0051
            goto L_0x0059
        L_0x0051:
            int r9 = r9.intValue()
            int r9 = r9 + r8
            if (r9 >= r2) goto L_0x0059
            goto L_0x005a
        L_0x0059:
            r9 = r2
        L_0x005a:
            if (r8 <= r9) goto L_0x005d
            goto L_0x003d
        L_0x005d:
            if (r8 >= r9) goto L_0x0078
            r10 = r8
        L_0x0060:
            int r11 = r10 + 1
            r12 = r21
            java.lang.Object r10 = r12.get(r10)
            com.appsamurai.storyly.data.managers.pagination.a r10 = (com.appsamurai.storyly.data.managers.pagination.a) r10
            int r13 = r1.size()
            r10.f3935e = r13
            r1.add(r10)
            if (r11 < r9) goto L_0x0076
            goto L_0x007a
        L_0x0076:
            r10 = r11
            goto L_0x0060
        L_0x0078:
            r12 = r21
        L_0x007a:
            int r9 = r9 - r8
            int r8 = r8 + r9
            goto L_0x0020
        L_0x007d:
            r12 = r21
            int r10 = r1.size()
            r9.f4225e = r10
            java.lang.String r14 = r9.f4221a
            java.util.List<com.appsamurai.storyly.data.z> r10 = r9.f4226f
            java.util.ArrayList r15 = new java.util.ArrayList
            int r11 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r10, 10)
            r15.<init>(r11)
            java.util.Iterator r10 = r10.iterator()
        L_0x0096:
            boolean r11 = r10.hasNext()
            if (r11 == 0) goto L_0x00a8
            java.lang.Object r11 = r10.next()
            com.appsamurai.storyly.data.z r11 = (com.appsamurai.storyly.data.z) r11
            java.lang.String r11 = r11.f4302a
            r15.add(r11)
            goto L_0x0096
        L_0x00a8:
            com.appsamurai.storyly.StoryGroupType r10 = r9.f4228h
            boolean r11 = r9.f4230j
            int r9 = r9.f4225e
            com.appsamurai.storyly.data.managers.pagination.a r13 = new com.appsamurai.storyly.data.managers.pagination.a
            r19 = r13
            r16 = r10
            r17 = r11
            r18 = r9
            r13.<init>(r14, r15, r16, r17, r18)
            r9 = r19
            r1.add(r9)
            goto L_0x0020
        L_0x00c2:
            com.appsamurai.storyly.data.managers.storage.d r2 = r20.h()
            java.util.List r1 = kotlin.collections.CollectionsKt.toList(r1)
            r2.getClass()
            java.lang.String r4 = "groupMetaDataList"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r4)
            android.content.SharedPreferences r2 = r2.a()
            java.util.Map r2 = r2.getAll()
            java.util.Iterator r4 = r1.iterator()
        L_0x00de:
            boolean r8 = r4.hasNext()
            if (r8 == 0) goto L_0x0166
            java.lang.Object r8 = r4.next()
            com.appsamurai.storyly.data.managers.pagination.a r8 = (com.appsamurai.storyly.data.managers.pagination.a) r8
            java.util.List<java.lang.String> r9 = r8.f3932b
            boolean r10 = r9 instanceof java.util.Collection
            if (r10 == 0) goto L_0x00f8
            boolean r10 = r9.isEmpty()
            if (r10 == 0) goto L_0x00f8
            goto L_0x0161
        L_0x00f8:
            java.util.Iterator r9 = r9.iterator()
        L_0x00fc:
            boolean r10 = r9.hasNext()
            if (r10 == 0) goto L_0x0161
            java.lang.Object r10 = r9.next()
            java.lang.String r10 = (java.lang.String) r10
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = r8.f3931a
            r11.append(r12)
            r12 = 95
            r11.append(r12)
            r11.append(r10)
            java.lang.String r11 = r11.toString()
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            java.lang.String r14 = "ttl_"
            r13.<init>(r14)
            java.lang.String r14 = r8.f3931a
            r13.append(r14)
            r13.append(r12)
            r13.append(r10)
            java.lang.String r10 = r13.toString()
            java.lang.Object r10 = r2.get(r10)
            boolean r12 = r10 instanceof java.lang.Long
            if (r12 == 0) goto L_0x0140
            java.lang.Long r10 = (java.lang.Long) r10
            goto L_0x0141
        L_0x0140:
            r10 = r5
        L_0x0141:
            if (r10 != 0) goto L_0x015c
            java.lang.Object r10 = r2.get(r11)
            boolean r11 = r10 instanceof java.lang.Boolean
            if (r11 == 0) goto L_0x014e
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            goto L_0x014f
        L_0x014e:
            r10 = r5
        L_0x014f:
            if (r10 != 0) goto L_0x0153
            r10 = r7
            goto L_0x0157
        L_0x0153:
            boolean r10 = r10.booleanValue()
        L_0x0157:
            if (r10 == 0) goto L_0x015a
            goto L_0x015c
        L_0x015a:
            r10 = r7
            goto L_0x015d
        L_0x015c:
            r10 = r6
        L_0x015d:
            if (r10 != 0) goto L_0x00fc
            r9 = r7
            goto L_0x0162
        L_0x0161:
            r9 = r6
        L_0x0162:
            r8.f3936f = r9
            goto L_0x00de
        L_0x0166:
            com.appsamurai.storyly.data.managers.processing.q r2 = r20.k()
            r2.getClass()
            java.lang.String r2 = "groupMetaData"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r2)
            kotlin.jvm.functions.Function1[] r0 = new kotlin.jvm.functions.Function1[r0]
            com.appsamurai.storyly.data.managers.processing.o r2 = com.appsamurai.storyly.data.managers.processing.o.f4029a
            r0[r7] = r2
            com.appsamurai.storyly.data.managers.processing.p r2 = com.appsamurai.storyly.data.managers.processing.p.f4030a
            r0[r6] = r2
            java.util.Comparator r0 = kotlin.comparisons.ComparisonsKt.compareBy((kotlin.jvm.functions.Function1<? super T, ? extends java.lang.Comparable<?>>[]) r0)
            java.util.List r0 = kotlin.collections.CollectionsKt.sortedWith(r1, r0)
            com.appsamurai.storyly.data.managers.pagination.c r1 = r20.d()
            r1.getClass()
            java.lang.String r2 = "orderMetaData"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r2)
            r1.a()
            r1.f3944c = r0
            java.util.UUID r2 = java.util.UUID.randomUUID()
            java.lang.String r2 = r2.toString()
            r1.f3942a = r2
            boolean r2 = r0 instanceof java.util.Collection
            if (r2 == 0) goto L_0x01aa
            boolean r2 = r0.isEmpty()
            if (r2 == 0) goto L_0x01aa
            goto L_0x01c5
        L_0x01aa:
            java.util.Iterator r0 = r0.iterator()
        L_0x01ae:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x01c5
            java.lang.Object r2 = r0.next()
            com.appsamurai.storyly.data.managers.pagination.a r2 = (com.appsamurai.storyly.data.managers.pagination.a) r2
            boolean r2 = r2.f3936f
            if (r2 != 0) goto L_0x01ae
            int r7 = r7 + r6
            if (r7 >= 0) goto L_0x01ae
            kotlin.collections.CollectionsKt.throwCountOverflow()
            goto L_0x01ae
        L_0x01c5:
            r1.f3943b = r7
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.managers.processing.f.a(java.util.List):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v4, resolved type: com.appsamurai.storyly.data.s} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.appsamurai.storyly.data.s a(java.lang.String r23) {
        /*
            r22 = this;
            r1 = r22
            r0 = r23
            java.lang.String r2 = "error"
            r3 = 0
            if (r0 != 0) goto L_0x000a
            return r3
        L_0x000a:
            com.appsamurai.storyly.data.s r4 = com.appsamurai.storyly.data.managers.processing.r.a((com.appsamurai.storyly.data.managers.processing.f) r22, (java.lang.String) r23)     // Catch:{ Exception -> 0x001e }
            if (r4 != 0) goto L_0x0021
            kotlinx.serialization.json.Json r4 = r22.c()     // Catch:{ Exception -> 0x001e }
            com.appsamurai.storyly.data.s$a r5 = com.appsamurai.storyly.data.s.f4195f     // Catch:{ Exception -> 0x001e }
            java.lang.Object r0 = r4.decodeFromString(r5, r0)     // Catch:{ Exception -> 0x001e }
            r4 = r0
            com.appsamurai.storyly.data.s r4 = (com.appsamurai.storyly.data.s) r4     // Catch:{ Exception -> 0x001e }
            goto L_0x0021
        L_0x001e:
            r0 = move-exception
            goto L_0x00ae
        L_0x0021:
            java.util.Map<java.lang.Integer, java.lang.Exception> r0 = r4.f4200d     // Catch:{ Exception -> 0x001e }
            if (r0 != 0) goto L_0x0027
            goto L_0x00ad
        L_0x0027:
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ Exception -> 0x001e }
            int r6 = r0.size()     // Catch:{ Exception -> 0x001e }
            r5.<init>(r6)     // Catch:{ Exception -> 0x001e }
            java.util.Set r0 = r0.entrySet()     // Catch:{ Exception -> 0x001e }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ Exception -> 0x001e }
        L_0x0038:
            boolean r6 = r0.hasNext()     // Catch:{ Exception -> 0x001e }
            if (r6 == 0) goto L_0x0070
            java.lang.Object r6 = r0.next()     // Catch:{ Exception -> 0x001e }
            java.util.Map$Entry r6 = (java.util.Map.Entry) r6     // Catch:{ Exception -> 0x001e }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x001e }
            r7.<init>()     // Catch:{ Exception -> 0x001e }
            java.lang.Object r8 = r6.getKey()     // Catch:{ Exception -> 0x001e }
            java.lang.Number r8 = (java.lang.Number) r8     // Catch:{ Exception -> 0x001e }
            int r8 = r8.intValue()     // Catch:{ Exception -> 0x001e }
            r7.append(r8)     // Catch:{ Exception -> 0x001e }
            java.lang.String r8 = ": "
            r7.append(r8)     // Catch:{ Exception -> 0x001e }
            java.lang.Object r6 = r6.getValue()     // Catch:{ Exception -> 0x001e }
            java.lang.Exception r6 = (java.lang.Exception) r6     // Catch:{ Exception -> 0x001e }
            java.lang.String r6 = r6.getMessage()     // Catch:{ Exception -> 0x001e }
            r7.append(r6)     // Catch:{ Exception -> 0x001e }
            java.lang.String r6 = r7.toString()     // Catch:{ Exception -> 0x001e }
            r5.add(r6)     // Catch:{ Exception -> 0x001e }
            goto L_0x0038
        L_0x0070:
            java.lang.String r6 = ", "
            r10 = 62
            r7 = 0
            r8 = 0
            r9 = 0
            java.lang.String r0 = kotlin.collections.CollectionsKt___CollectionsKt.joinToString$default(r5, r6, r7, r8, 0, (java.lang.CharSequence) null, r9, r10, (java.lang.Object) null)     // Catch:{ Exception -> 0x001e }
            com.appsamurai.storyly.util.a$a r5 = com.appsamurai.storyly.util.a.f6249a     // Catch:{ Exception -> 0x001e }
            java.lang.String r6 = "ParseFailed StorylyGroupItems: "
            java.lang.String r6 = kotlin.jvm.internal.Intrinsics.stringPlus(r6, r0)     // Catch:{ Exception -> 0x001e }
            r7 = 2
            com.appsamurai.storyly.util.a.C0055a.a(r5, r6, r3, r7)     // Catch:{ Exception -> 0x001e }
            com.appsamurai.storyly.analytics.e r8 = r1.f3972b     // Catch:{ Exception -> 0x001e }
            com.appsamurai.storyly.analytics.a r9 = com.appsamurai.storyly.analytics.a.ParseFailed     // Catch:{ Exception -> 0x001e }
            kotlinx.serialization.json.JsonObjectBuilder r5 = new kotlinx.serialization.json.JsonObjectBuilder     // Catch:{ Exception -> 0x001e }
            r5.<init>()     // Catch:{ Exception -> 0x001e }
            kotlinx.serialization.json.JsonElementBuildersKt.put((kotlinx.serialization.json.JsonObjectBuilder) r5, (java.lang.String) r2, (java.lang.String) r0)     // Catch:{ Exception -> 0x001e }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x001e }
            kotlinx.serialization.json.JsonObject r14 = r5.build()     // Catch:{ Exception -> 0x001e }
            r18 = 0
            r19 = 0
            r20 = 0
            r21 = 4056(0xfd8, float:5.684E-42)
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            com.appsamurai.storyly.analytics.e.a(r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21)     // Catch:{ Exception -> 0x001e }
        L_0x00ad:
            return r4
        L_0x00ae:
            r0.printStackTrace()
            com.appsamurai.storyly.analytics.e r4 = r1.f3972b
            com.appsamurai.storyly.analytics.a r5 = com.appsamurai.storyly.analytics.a.ParseFailed
            kotlinx.serialization.json.JsonObjectBuilder r1 = new kotlinx.serialization.json.JsonObjectBuilder
            r1.<init>()
            java.lang.String r0 = r0.getLocalizedMessage()
            kotlinx.serialization.json.JsonElementBuildersKt.put((kotlinx.serialization.json.JsonObjectBuilder) r1, (java.lang.String) r2, (java.lang.String) r0)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            kotlinx.serialization.json.JsonObject r10 = r1.build()
            r14 = 0
            r15 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r16 = 0
            r17 = 4056(0xfd8, float:5.684E-42)
            com.appsamurai.storyly.analytics.e.a(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.managers.processing.f.a(java.lang.String):com.appsamurai.storyly.data.s");
    }

    public final List<v> a() {
        List<v> list;
        s sVar = this.f3980j;
        if (sVar == null || (list = sVar.f4197a) == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (T next : list) {
            v vVar = (v) next;
            if (vVar.f4228h != StoryGroupType.MomentsBlock && !vVar.c()) {
                arrayList.add(next);
            }
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(((v) it.next()).a());
        }
        return arrayList2;
    }
}
