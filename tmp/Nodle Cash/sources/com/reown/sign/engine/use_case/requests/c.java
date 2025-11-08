package com.reown.sign.engine.use_case.requests;

import com.reown.android.internal.common.model.WCRequest;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class c implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ OnSessionDeleteUseCase f7554a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ WCRequest f7555b;

    public /* synthetic */ c(OnSessionDeleteUseCase onSessionDeleteUseCase, WCRequest wCRequest) {
        this.f7554a = onSessionDeleteUseCase;
        this.f7555b = wCRequest;
    }

    public final Object invoke(Object obj) {
        return OnSessionDeleteUseCase$invoke$2.invokeSuspend$lambda$1(this.f7554a, this.f7555b, (Throwable) obj);
    }
}
