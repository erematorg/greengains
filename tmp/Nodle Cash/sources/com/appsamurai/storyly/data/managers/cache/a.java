package com.appsamurai.storyly.data.managers.cache;

import com.appsamurai.storyly.data.v;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class a extends Lambda implements Function1<v, Boolean> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ v f3884a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public a(v vVar) {
        super(1);
        this.f3884a = vVar;
    }

    public Object invoke(Object obj) {
        v vVar = (v) obj;
        Intrinsics.checkNotNullParameter(vVar, "it");
        return Boolean.valueOf(Intrinsics.areEqual((Object) vVar.f4221a, (Object) this.f3884a.f4221a));
    }
}
