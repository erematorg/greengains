package com.reown.sign.engine.use_case.calls;

import com.reown.android.internal.common.JsonRpcResponse;
import kotlin.jvm.functions.Function0;

public final /* synthetic */ class n implements Function0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Function0 f7536a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ RespondSessionRequestUseCase f7537b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ String f7538c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ JsonRpcResponse f7539d;

    public /* synthetic */ n(Function0 function0, RespondSessionRequestUseCase respondSessionRequestUseCase, String str, JsonRpcResponse jsonRpcResponse) {
        this.f7536a = function0;
        this.f7537b = respondSessionRequestUseCase;
        this.f7538c = str;
        this.f7539d = jsonRpcResponse;
    }

    public final Object invoke() {
        return RespondSessionRequestUseCase$respondSessionRequest$2.invokeSuspend$lambda$3(this.f7536a, this.f7537b, this.f7538c, this.f7539d);
    }
}
