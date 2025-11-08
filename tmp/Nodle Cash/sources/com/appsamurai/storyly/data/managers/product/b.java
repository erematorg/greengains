package com.appsamurai.storyly.data.managers.product;

import com.appsamurai.storyly.data.a0;
import com.appsamurai.storyly.data.b0;
import com.appsamurai.storyly.data.g0;
import com.appsamurai.storyly.data.j;
import com.appsamurai.storyly.data.m;
import com.appsamurai.storyly.data.v;
import com.appsamurai.storyly.data.z;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;

public final class b {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public a f4041a = new a();
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public Set<STRProductItem> f4042b = SetsKt.emptySet();
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public Set<j> f4043c = new LinkedHashSet();

    public final void a(@NotNull List<v> list) {
        Map<j, List<STRProductItem>> map;
        Collection<List<STRProductItem>> values;
        Map map2;
        Map<j, List<STRProductItem>> map3;
        Set<j> keySet;
        Map<j, List<STRProductItem>> map4;
        Intrinsics.checkNotNullParameter(list, "storyGroups");
        for (v vVar : list) {
            for (z zVar : vVar.f4226f) {
                List<b0> list2 = zVar.f4303b.f3639a;
                if (list2 != null) {
                    for (b0 b0Var : list2) {
                        a0 a0Var = b0Var == null ? null : b0Var.f3615j;
                        g0 g0Var = a0Var instanceof g0 ? (g0) a0Var : null;
                        if (g0Var != null) {
                            List<T> list3 = CollectionsKt.toList(this.f4042b);
                            Intrinsics.checkNotNullParameter(list3, FirebaseAnalytics.Param.ITEMS);
                            m c3 = g0Var.c();
                            if (!(c3 == null || (map3 = c3.f3859a) == null || (keySet = map3.keySet()) == null)) {
                                for (j jVar : keySet) {
                                    ArrayList arrayList = new ArrayList();
                                    for (T next : list3) {
                                        if (Intrinsics.areEqual((Object) ((STRProductItem) next).getProductGroupId(), (Object) jVar.f3790c)) {
                                            arrayList.add(next);
                                        }
                                    }
                                    List asMutableList = TypeIntrinsics.asMutableList(arrayList);
                                    Iterator it = asMutableList.iterator();
                                    int i3 = 0;
                                    while (true) {
                                        if (!it.hasNext()) {
                                            i3 = -1;
                                            break;
                                        } else if (Intrinsics.areEqual((Object) ((STRProductItem) it.next()).getProductId(), (Object) jVar.f3789b)) {
                                            break;
                                        } else {
                                            i3++;
                                        }
                                    }
                                    if (i3 != -1) {
                                        asMutableList.add(0, (STRProductItem) asMutableList.remove(i3));
                                    }
                                    m c4 = g0Var.c();
                                    if (!(c4 == null || (map4 = c4.f3859a) == null)) {
                                        List put = map4.put(jVar, asMutableList);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        this.f4043c.clear();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (v vVar2 : list) {
            for (z zVar2 : vVar2.f4226f) {
                List<b0> list4 = zVar2.f4303b.f3639a;
                if (list4 != null) {
                    for (b0 b0Var2 : list4) {
                        a0 a0Var2 = b0Var2 == null ? null : b0Var2.f3615j;
                        g0 g0Var2 = a0Var2 instanceof g0 ? (g0) a0Var2 : null;
                        if (g0Var2 != null) {
                            Set<j> set = this.f4043c;
                            m c5 = g0Var2.c();
                            Collection keySet2 = (c5 == null || (map2 = c5.f3859a) == null) ? null : map2.keySet();
                            if (keySet2 == null) {
                                keySet2 = CollectionsKt.emptyList();
                            }
                            set.addAll(keySet2);
                            m c6 = g0Var2.c();
                            if (c6 != null && (map = c6.f3859a) != null && (values = map.values()) != null && !values.isEmpty()) {
                                Iterator<T> it2 = values.iterator();
                                while (true) {
                                    if (it2.hasNext()) {
                                        if (((List) it2.next()).isEmpty()) {
                                            linkedHashSet.add(vVar2.f4221a);
                                            break;
                                        }
                                    } else {
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        for (v vVar3 : list) {
            vVar3.f4244x = linkedHashSet.contains(vVar3.f4221a);
        }
    }
}
