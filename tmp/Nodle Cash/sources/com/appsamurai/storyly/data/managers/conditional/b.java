package com.appsamurai.storyly.data.managers.conditional;

import android.content.Context;
import com.appsamurai.storyly.data.a0;
import com.appsamurai.storyly.data.b0;
import com.appsamurai.storyly.data.e0;
import com.appsamurai.storyly.data.k0;
import com.appsamurai.storyly.data.managers.storage.e;
import com.appsamurai.storyly.data.n;
import com.appsamurai.storyly.data.v;
import com.appsamurai.storyly.data.y;
import com.appsamurai.storyly.data.z;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class b {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Lazy f3898a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final Lazy f3899b;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public final Lazy f3900c;
    @NotNull

    /* renamed from: d  reason: collision with root package name */
    public Map<String, a> f3901d = new LinkedHashMap();
    @NotNull

    /* renamed from: e  reason: collision with root package name */
    public Map<String, String> f3902e = new LinkedHashMap();

    public static final class a extends Lambda implements Function0<e> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f3903a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(Context context) {
            super(0);
            this.f3903a = context;
        }

        public Object invoke() {
            return new e(this.f3903a, "stryly-image-quiz-results", 0, 4);
        }
    }

    /* renamed from: com.appsamurai.storyly.data.managers.conditional.b$b  reason: collision with other inner class name */
    public static final class C0009b extends Lambda implements Function0<e> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f3904a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public C0009b(Context context) {
            super(0);
            this.f3904a = context;
        }

        public Object invoke() {
            return new e(this.f3904a, "stryly-poll-results", 0, 4);
        }
    }

    public static final class c extends Lambda implements Function0<e> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f3905a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public c(Context context) {
            super(0);
            this.f3905a = context;
        }

        public Object invoke() {
            return new e(this.f3905a, "stryly-quiz-results", 0, 4);
        }
    }

    public b(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f3898a = LazyKt.lazy(new C0009b(context));
        this.f3899b = LazyKt.lazy(new c(context));
        this.f3900c = LazyKt.lazy(new a(context));
    }

    public final boolean a(@NotNull z zVar) {
        boolean z2;
        Integer num;
        Intrinsics.checkNotNullParameter(zVar, "story");
        zVar.f4318q = true;
        List<? extends List<n>> list = zVar.f4315n;
        if (list != null) {
            for (List list2 : list) {
                Iterator it = list2.iterator();
                while (true) {
                    z2 = false;
                    if (!it.hasNext()) {
                        break;
                    }
                    n nVar = (n) it.next();
                    int i3 = nVar.f4055d.f3806b;
                    a aVar = this.f3901d.get(nVar.f4054c);
                    if (!(aVar == null || (num = aVar.f3896a) == null || i3 != num.intValue())) {
                        z2 = true;
                    }
                    nVar.f4056e = z2;
                }
                if (zVar.f4318q && !list2.isEmpty()) {
                    Iterator it2 = list2.iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            if (((n) it2.next()).f4056e) {
                                z2 = true;
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
                zVar.f4318q = z2;
            }
        }
        return zVar.f4318q;
    }

    public final void a(@NotNull List<v> list, @Nullable String str) {
        a aVar;
        Set<c> set;
        T t2;
        T t3;
        List<z> list2;
        Intrinsics.checkNotNullParameter(list, "storyGroups");
        if (str != null && (aVar = this.f3901d.get(str)) != null && (set = aVar.f3897b) != null) {
            for (c cVar : set) {
                Iterator<T> it = list.iterator();
                while (true) {
                    t2 = null;
                    if (!it.hasNext()) {
                        t3 = null;
                        break;
                    }
                    t3 = it.next();
                    if (Intrinsics.areEqual((Object) cVar.f3906a, (Object) ((v) t3).f4221a)) {
                        break;
                    }
                }
                v vVar = (v) t3;
                if (!(vVar == null || (list2 = vVar.f4226f) == null)) {
                    Iterator<T> it2 = list2.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        T next = it2.next();
                        if (Intrinsics.areEqual((Object) ((z) next).f4302a, (Object) cVar.f3907b)) {
                            t2 = next;
                            break;
                        }
                    }
                    z zVar = (z) t2;
                    if (zVar != null) {
                        a(zVar);
                    }
                }
            }
        }
    }

    public final void a(@NotNull List<v> list) {
        a0 a0Var;
        a0 a0Var2;
        a0 a0Var3;
        Set<c> set;
        Intrinsics.checkNotNullParameter(list, "storyGroups");
        for (v vVar : list) {
            for (z zVar : vVar.f4226f) {
                List<? extends List<n>> list2 = zVar.f4315n;
                if (list2 != null) {
                    for (List<n> it : list2) {
                        for (n nVar : it) {
                            if (!this.f3901d.containsKey(nVar.f4054c)) {
                                this.f3901d.put(nVar.f4054c, new a((Integer) null, (Set) null, 3));
                            }
                            a aVar = this.f3901d.get(nVar.f4054c);
                            if (!(aVar == null || (set = aVar.f3897b) == null)) {
                                set.add(new c(vVar.f4221a, zVar.f4302a));
                            }
                        }
                    }
                }
                List<b0> list3 = zVar.f4303b.f3639a;
                if (list3 != null) {
                    ArrayList arrayList = new ArrayList();
                    for (T next : list3) {
                        b0 b0Var = (b0) next;
                        if (b0Var == null) {
                            a0Var = null;
                        } else {
                            a0Var = b0Var.f3615j;
                        }
                        if (!(a0Var instanceof e0)) {
                            if (b0Var == null) {
                                a0Var2 = null;
                            } else {
                                a0Var2 = b0Var.f3615j;
                            }
                            if (!(a0Var2 instanceof k0)) {
                                if (b0Var == null) {
                                    a0Var3 = null;
                                } else {
                                    a0Var3 = b0Var.f3615j;
                                }
                                if (!(a0Var3 instanceof y)) {
                                }
                            }
                        }
                        arrayList.add(next);
                    }
                    Iterator it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        b0 b0Var2 = (b0) it2.next();
                        if (b0Var2 != null) {
                            this.f3902e.put(b0Var2.f3614i, b0Var2.f3606a);
                        }
                    }
                }
            }
        }
        Map<String, String> map = this.f3902e;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry next2 : map.entrySet()) {
            if (this.f3901d.containsKey((String) next2.getKey())) {
                linkedHashMap.put(next2.getKey(), next2.getValue());
            }
        }
        this.f3902e = MapsKt.toMutableMap(linkedHashMap);
        Map<String, a> map2 = this.f3901d;
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (Map.Entry next3 : map2.entrySet()) {
            if (this.f3902e.containsKey((String) next3.getKey())) {
                linkedHashMap2.put(next3.getKey(), next3.getValue());
            }
        }
        Map<String, a> mutableMap = MapsKt.toMutableMap(linkedHashMap2);
        this.f3901d = mutableMap;
        for (Map.Entry next4 : mutableMap.entrySet()) {
            String str = this.f3902e.get(next4.getKey());
            if (Intrinsics.areEqual((Object) str, (Object) "poll")) {
                a aVar2 = (a) next4.getValue();
                Object a2 = ((e) this.f3898a.getValue()).a((String) next4.getKey());
                Boolean bool = a2 instanceof Boolean ? (Boolean) a2 : null;
                aVar2.f3896a = bool == null ? null : Integer.valueOf(Intrinsics.compare(bool.booleanValue() ? 1 : 0, 1));
            } else if (Intrinsics.areEqual((Object) str, (Object) "quiz")) {
                a aVar3 = (a) next4.getValue();
                Object a3 = ((e) this.f3899b.getValue()).a((String) next4.getKey());
                aVar3.f3896a = a3 instanceof Integer ? (Integer) a3 : null;
            } else if (Intrinsics.areEqual((Object) str, (Object) "image_quiz")) {
                a aVar4 = (a) next4.getValue();
                Object a4 = ((e) this.f3900c.getValue()).a((String) next4.getKey());
                aVar4.f3896a = a4 instanceof Integer ? (Integer) a4 : null;
            }
        }
        if (!this.f3901d.isEmpty()) {
            for (v vVar2 : list) {
                for (z a5 : vVar2.f4226f) {
                    a(a5);
                }
            }
        }
    }
}
