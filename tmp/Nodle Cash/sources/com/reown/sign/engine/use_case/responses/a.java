package com.reown.sign.engine.use_case.responses;

import kotlin.jvm.functions.Function1;

public final /* synthetic */ class a implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ OnSessionAuthenticateResponseUseCase f7560a;

    public /* synthetic */ a(OnSessionAuthenticateResponseUseCase onSessionAuthenticateResponseUseCase) {
        this.f7560a = onSessionAuthenticateResponseUseCase;
    }

    public final Object invoke(Object obj) {
        return OnSessionAuthenticateResponseUseCase$invoke$2.invokeSuspend$lambda$9$lambda$8(this.f7560a, (Throwable) obj);
    }
}
