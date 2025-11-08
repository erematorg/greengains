package com.appsamurai.storyly.storylypresenter;

import com.appsamurai.storyly.data.a0;
import com.appsamurai.storyly.data.b0;
import com.appsamurai.storyly.data.c0;
import com.appsamurai.storyly.data.f0;
import com.appsamurai.storyly.data.g0;
import com.appsamurai.storyly.data.h0;
import com.appsamurai.storyly.data.i0;
import com.appsamurai.storyly.data.j;
import com.appsamurai.storyly.data.m;
import com.appsamurai.storyly.data.managers.product.STRProductItem;
import com.appsamurai.storyly.data.n0;
import com.appsamurai.storyly.data.o;
import com.appsamurai.storyly.data.p;
import com.appsamurai.storyly.data.x;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KProperty;

public final class v0 extends Lambda implements Function1<b0, Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ o f6246a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public v0(o oVar) {
        super(1);
        this.f6246a = oVar;
    }

    public Object invoke(Object obj) {
        m c3;
        Map<j, List<STRProductItem>> map;
        Collection<List<STRProductItem>> values;
        Collection<List<STRProductItem>> values2;
        b0 b0Var = (b0) obj;
        Intrinsics.checkNotNullParameter(b0Var, "storylyLayerItem");
        a0 a0Var = b0Var.f3615j;
        T t2 = null;
        if (!(a0Var instanceof g0) || (c3 = ((g0) a0Var).c()) == null || (map = c3.f3859a) == null || (values = map.values()) == null) {
            a0 a0Var2 = b0Var.f3615j;
            if (a0Var2 instanceof p) {
                o oVar = this.f6246a;
                String str = ((p) a0Var2).f4102i;
                KProperty<Object>[] kPropertyArr = o.f5037L;
                oVar.a(str, b0Var);
            } else if (a0Var2 instanceof i0) {
                o oVar2 = this.f6246a;
                String str2 = ((i0) a0Var2).f3762c;
                KProperty<Object>[] kPropertyArr2 = o.f5037L;
                oVar2.a(str2, b0Var);
            } else if (a0Var2 instanceof o) {
                o oVar3 = this.f6246a;
                KProperty<Object>[] kPropertyArr3 = o.f5037L;
                oVar3.a((String) null, b0Var);
            } else if (a0Var2 instanceof n0) {
                o oVar4 = this.f6246a;
                String str3 = ((n0) a0Var2).f4063e;
                KProperty<Object>[] kPropertyArr4 = o.f5037L;
                oVar4.a(str3, b0Var);
            } else if (a0Var2 instanceof x) {
                o oVar5 = this.f6246a;
                String str4 = ((x) a0Var2).f4258g;
                KProperty<Object>[] kPropertyArr5 = o.f5037L;
                oVar5.a(str4, b0Var);
            } else if (a0Var2 instanceof c0) {
                o oVar6 = this.f6246a;
                String str5 = ((c0) a0Var2).f3626b;
                KProperty<Object>[] kPropertyArr6 = o.f5037L;
                oVar6.a(str5, b0Var);
            } else if (a0Var2 instanceof f0) {
                o oVar7 = this.f6246a;
                String str6 = ((f0) a0Var2).f3711u;
                KProperty<Object>[] kPropertyArr7 = o.f5037L;
                oVar7.a(str6, b0Var);
            }
        } else {
            if (!values.isEmpty()) {
                Iterator<T> it = values.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (((List) it.next()).isEmpty()) {
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            a0 a0Var3 = b0Var.f3615j;
            if (a0Var3 instanceof h0) {
                List<STRProductItem> list = ((h0) a0Var3).f3743r;
                KProperty<Object>[] kPropertyArr8 = o.f5037L;
                this.f6246a.a((g0) a0Var3, list);
            } else {
                m c4 = ((g0) a0Var3).c();
                if (c4 != null) {
                    o oVar8 = this.f6246a;
                    g0 g0Var = (g0) b0Var.f3615j;
                    Map<j, List<STRProductItem>> map2 = c4.f3859a;
                    if (!(map2 == null || (values2 = map2.values()) == null)) {
                        Iterator<T> it2 = values2.iterator();
                        while (true) {
                            if (!it2.hasNext()) {
                                break;
                            }
                            T next = it2.next();
                            if (!((List) next).isEmpty()) {
                                t2 = next;
                                break;
                            }
                        }
                        t2 = (List) t2;
                    }
                    KProperty<Object>[] kPropertyArr9 = o.f5037L;
                    oVar8.a(g0Var, (List<STRProductItem>) t2);
                }
            }
        }
        return Unit.INSTANCE;
    }
}
