package com.reown.sign.engine.use_case.calls;

import com.reown.android.internal.common.JsonRpcResponse;
import com.reown.android.internal.common.model.WCResponse;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
public final class SessionRequestUseCase$collectResponse$4<T> implements FlowCollector {
    final /* synthetic */ Function1<Result<JsonRpcResponse.JsonRpcResult>, Unit> $onResponse;

    public SessionRequestUseCase$collectResponse$4(Function1<? super Result<JsonRpcResponse.JsonRpcResult>, Unit> function1) {
        this.$onResponse = function1;
    }

    public final Object emit(WCResponse wCResponse, Continuation<? super Unit> continuation) {
        JsonRpcResponse response = wCResponse.getResponse();
        if (response instanceof JsonRpcResponse.JsonRpcResult) {
            this.$onResponse.invoke(Result.m8978boximpl(Result.m8979constructorimpl(response)));
        } else if (response instanceof JsonRpcResponse.JsonRpcError) {
            Function1<Result<JsonRpcResponse.JsonRpcResult>, Unit> function1 = this.$onResponse;
            Result.Companion companion = Result.Companion;
            function1.invoke(Result.m8978boximpl(Result.m8979constructorimpl(ResultKt.createFailure(new Throwable(((JsonRpcResponse.JsonRpcError) response).getErrorMessage())))));
        } else {
            throw new NoWhenBranchMatchedException();
        }
        return Unit.INSTANCE;
    }
}
