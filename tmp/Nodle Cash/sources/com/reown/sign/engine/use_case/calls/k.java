package com.reown.sign.engine.use_case.calls;

import kotlin.jvm.functions.Function1;

public final /* synthetic */ class k implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7525a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Function1 f7526b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f7527c;

    public /* synthetic */ k(Object obj, Function1 function1, int i3) {
        this.f7525a = i3;
        this.f7527c = obj;
        this.f7526b = function1;
    }

    public final Object invoke(Object obj) {
        Throwable th = (Throwable) obj;
        switch (this.f7525a) {
            case 0:
                return ProposeSessionUseCase$proposeSession$2.invokeSuspend$lambda$3$lambda$2((ProposeSessionUseCase) this.f7527c, this.f7526b, th);
            default:
                return SessionRequestUseCase.triggerRelayRequest$lambda$4((SessionRequestUseCase) this.f7527c, this.f7526b, th);
        }
    }
}
