package com.reown.sign.engine.use_case.requests;

import com.reown.android.internal.common.model.WCRequest;
import kotlin.jvm.functions.Function0;

public final /* synthetic */ class b implements Function0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ OnSessionDeleteUseCase f7552a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ WCRequest f7553b;

    public /* synthetic */ b(OnSessionDeleteUseCase onSessionDeleteUseCase, WCRequest wCRequest) {
        this.f7552a = onSessionDeleteUseCase;
        this.f7553b = wCRequest;
    }

    public final Object invoke() {
        return OnSessionDeleteUseCase$invoke$2.invokeSuspend$lambda$0(this.f7552a, this.f7553b);
    }
}
