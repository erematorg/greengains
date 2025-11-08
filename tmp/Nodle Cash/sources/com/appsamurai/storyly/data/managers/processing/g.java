package com.appsamurai.storyly.data.managers.processing;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.json.JsonBuilder;

public final class g extends Lambda implements Function1<JsonBuilder, Unit> {

    /* renamed from: a  reason: collision with root package name */
    public static final g f4010a = new g();

    public g() {
        super(1);
    }

    public Object invoke(Object obj) {
        JsonBuilder jsonBuilder = (JsonBuilder) obj;
        Intrinsics.checkNotNullParameter(jsonBuilder, "$this$Json");
        jsonBuilder.setIgnoreUnknownKeys(true);
        return Unit.INSTANCE;
    }
}
