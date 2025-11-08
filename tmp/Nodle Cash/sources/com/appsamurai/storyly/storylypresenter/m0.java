package com.appsamurai.storyly.storylypresenter;

import android.support.v4.media.session.a;
import com.appsamurai.storyly.data.b0;
import com.appsamurai.storyly.data.z;
import com.appsamurai.storyly.storylypresenter.storylyfooter.a;
import com.appsamurai.storyly.storylypresenter.storylylayer.c;
import com.appsamurai.storyly.storylypresenter.storylylayer.x;
import java.util.ArrayList;
import java.util.List;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class m0 extends Lambda implements Function2<Long, Long, Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ o f5031a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public m0(o oVar) {
        super(2);
        this.f5031a = oVar;
    }

    public Object invoke(Object obj, Object obj2) {
        List<b0> list;
        int i3;
        long longValue = ((Number) obj).longValue();
        long longValue2 = ((Number) obj2).longValue();
        z h3 = this.f5031a.getStorylyItem();
        if (h3 != null) {
            h3.f4317p = longValue;
        }
        z h4 = this.f5031a.getStorylyItem();
        if (h4 != null) {
            h4.f4304c = longValue2;
        }
        x i4 = this.f5031a.getStorylyLayerContainerView();
        i4.getClass();
        c a2 = i4.a();
        if (!a2.f5662f && (list = a2.f5658b) != null) {
            ArrayList arrayList = new ArrayList(100);
            int i5 = 0;
            for (int i6 = 0; i6 < 100; i6 = a.e(0, arrayList, i6, 1)) {
            }
            for (b0 b0Var : list) {
                Long l2 = b0Var.f3616k;
                if (l2 != null && b0Var.f3617l != null) {
                    long j2 = (long) 100;
                    int longValue3 = (int) ((l2.longValue() * j2) / longValue2);
                    int min = Math.min(99, (int) ((b0Var.f3617l.longValue() * j2) / longValue2)) + 1;
                    if (longValue3 < min) {
                        while (true) {
                            int i7 = longValue3 + 1;
                            arrayList.set(longValue3, 1);
                            if (i7 >= min) {
                                break;
                            }
                            longValue3 = i7;
                        }
                    }
                }
            }
            ArrayList arrayList2 = new ArrayList();
            float f2 = 0.0f;
            List list2 = arrayList;
            while (list2.size() > 0) {
                ArrayList arrayList3 = new ArrayList();
                for (Object next : list2) {
                    if (((Number) next).intValue() != ((Number) list2.get(i3)).intValue()) {
                        break;
                    }
                    arrayList3.add(next);
                }
                ArrayList arrayList4 = new ArrayList();
                int i8 = i3;
                for (Object next2 : list2) {
                    if (i8 != 0) {
                        arrayList4.add(next2);
                    } else if (((Number) next2).intValue() != ((Number) list2.get(i3)).intValue()) {
                        arrayList4.add(next2);
                        i3 = 0;
                        i8 = 1;
                    }
                    i3 = 0;
                }
                List mutableList = CollectionsKt.toMutableList(arrayList4);
                i5 = 0;
                arrayList2.add(new Pair(arrayList3.get(0), Float.valueOf((((float) arrayList3.size()) / 100.0f) + f2)));
                f2 += ((float) arrayList3.size()) / 100.0f;
                list2 = mutableList;
            }
            Function1<? super List<Pair<Integer, Float>>, Unit> function1 = a2.f5660d;
            if (function1 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("onMetadataPartsReady");
                function1 = null;
            }
            function1.invoke(arrayList2);
            a2.f5662f = true;
        }
        a2.a(longValue);
        com.appsamurai.storyly.storylypresenter.storylyfooter.a f3 = this.f5031a.getStorylyFooterView();
        Long valueOf = Long.valueOf(longValue);
        Long valueOf2 = Long.valueOf(longValue2);
        a.C0032a aVar = f3.f5346c;
        if (aVar != null) {
            aVar.a(valueOf, valueOf2);
            Unit unit = Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }
}
