package com.appsamurai.storyly.storylypresenter.product.productdetail;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

public final class d extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ h f5200a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public d(h hVar) {
        super(0);
        this.f5200a = hVar;
    }

    public Object invoke() {
        this.f5200a.getMessageContainer().setVisibility(8);
        return Unit.INSTANCE;
    }
}
