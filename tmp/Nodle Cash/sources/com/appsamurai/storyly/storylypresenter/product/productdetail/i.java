package com.appsamurai.storyly.storylypresenter.product.productdetail;

import com.appsamurai.storyly.data.managers.product.STRProductVariant;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class i extends Lambda implements Function1<STRProductVariant, Boolean> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ STRProductVariant f5259a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public i(STRProductVariant sTRProductVariant) {
        super(1);
        this.f5259a = sTRProductVariant;
    }

    public Object invoke(Object obj) {
        STRProductVariant sTRProductVariant = (STRProductVariant) obj;
        Intrinsics.checkNotNullParameter(sTRProductVariant, "it");
        return Boolean.valueOf(Intrinsics.areEqual((Object) sTRProductVariant.getName(), (Object) this.f5259a.getName()));
    }
}
