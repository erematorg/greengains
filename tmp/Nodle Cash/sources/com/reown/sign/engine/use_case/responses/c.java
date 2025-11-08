package com.reown.sign.engine.use_case.responses;

import com.reown.foundation.common.model.Topic;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.CoroutineScope;

public final /* synthetic */ class c implements Function0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ CoroutineScope f7564a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ OnSessionSettleResponseUseCase f7565b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Topic f7566c;

    public /* synthetic */ c(CoroutineScope coroutineScope, OnSessionSettleResponseUseCase onSessionSettleResponseUseCase, Topic topic) {
        this.f7564a = coroutineScope;
        this.f7565b = onSessionSettleResponseUseCase;
        this.f7566c = topic;
    }

    public final Object invoke() {
        return OnSessionSettleResponseUseCase$invoke$2.invokeSuspend$lambda$3(this.f7564a, this.f7565b, this.f7566c);
    }
}
