package com.appsamurai.storyly.data.managers.cache;

import com.appsamurai.storyly.data.z;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class b extends Lambda implements Function1<z, Boolean> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ z f3885a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public b(z zVar) {
        super(1);
        this.f3885a = zVar;
    }

    public Object invoke(Object obj) {
        z zVar = (z) obj;
        Intrinsics.checkNotNullParameter(zVar, "it");
        return Boolean.valueOf(Intrinsics.areEqual((Object) zVar.f4302a, (Object) this.f3885a.f4302a));
    }
}
