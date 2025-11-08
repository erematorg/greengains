package com.appsamurai.storyly.storylypresenter.storylylayer;

import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class n1 extends Lambda implements Function1<Map<String, o1>, Unit> {

    /* renamed from: a  reason: collision with root package name */
    public static final n1 f5889a = new n1();

    public n1() {
        super(1);
    }

    public Object invoke(Object obj) {
        Map map = (Map) obj;
        Intrinsics.checkNotNullParameter(map, "it");
        for (o1 c3 : map.values()) {
            c3.c();
        }
        return Unit.INSTANCE;
    }
}
