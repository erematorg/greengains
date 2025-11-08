package com.reown.sign.client;

import com.reown.sign.client.Sign;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class f implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Function1 f7473a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Sign.Params.Request f7474b;

    public /* synthetic */ f(Function1 function1, Sign.Params.Request request) {
        this.f7473a = function1;
        this.f7474b = request;
    }

    public final Object invoke(Object obj) {
        return SignProtocol$request$1.invokeSuspend$lambda$0(this.f7473a, this.f7474b, ((Long) obj).longValue());
    }
}
