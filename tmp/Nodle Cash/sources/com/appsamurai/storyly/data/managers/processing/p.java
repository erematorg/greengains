package com.appsamurai.storyly.data.managers.processing;

import com.appsamurai.storyly.data.managers.pagination.a;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class p extends Lambda implements Function1<a, Comparable<?>> {

    /* renamed from: a  reason: collision with root package name */
    public static final p f4030a = new p();

    public p() {
        super(1);
    }

    public Object invoke(Object obj) {
        a aVar = (a) obj;
        Intrinsics.checkNotNullParameter(aVar, "it");
        return Boolean.valueOf(!aVar.f3934d && aVar.f3936f);
    }
}
