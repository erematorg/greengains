package com.appsamurai.storyly.storylypresenter.storylylayer;

import java.util.Collection;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class l1 extends Lambda implements Function1<Map<String, o1>, Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ long f5846a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public l1(long j2) {
        super(1);
        this.f5846a = j2;
    }

    public Object invoke(Object obj) {
        Map map = (Map) obj;
        Intrinsics.checkNotNullParameter(map, "it");
        Collection<o1> values = map.values();
        long j2 = this.f5846a;
        for (o1 b3 : values) {
            b3.b(j2);
        }
        return Unit.INSTANCE;
    }
}
