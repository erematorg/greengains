package com.appsamurai.storyly.storylypresenter;

import com.appsamurai.storyly.data.z;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class b0 extends Lambda implements Function1<z, Boolean> {

    /* renamed from: a  reason: collision with root package name */
    public static final b0 f4767a = new b0();

    public b0() {
        super(1);
    }

    public Object invoke(Object obj) {
        z zVar = (z) obj;
        Intrinsics.checkNotNullParameter(zVar, "it");
        return Boolean.valueOf(!zVar.f4318q);
    }
}
