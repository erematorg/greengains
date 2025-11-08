package com.reown.sign.engine.use_case.calls;

import com.reown.android.Core;
import com.reown.android.internal.common.model.Expiry;
import com.reown.foundation.common.model.Topic;
import com.reown.sign.common.model.vo.clientsync.session.SignRpc;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.calls.SessionAuthenticateUseCase", f = "SessionAuthenticateUseCase.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {202}, m = "publishSessionAuthenticateDeferred-yxL6bBk", n = {"pairing", "authRequest", "responseTopic", "requestExpiry", "irnParamsTtl", "irnParams", "sessionAuthenticateDeferred"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6"})
public final class SessionAuthenticateUseCase$publishSessionAuthenticateDeferred$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SessionAuthenticateUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SessionAuthenticateUseCase$publishSessionAuthenticateDeferred$1(SessionAuthenticateUseCase sessionAuthenticateUseCase, Continuation<? super SessionAuthenticateUseCase$publishSessionAuthenticateDeferred$1> continuation) {
        super(continuation);
        this.this$0 = sessionAuthenticateUseCase;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object r6 = this.this$0.m8893publishSessionAuthenticateDeferredyxL6bBk((Core.Model.Pairing) null, (SignRpc.SessionAuthenticate) null, (Topic) null, (Expiry) null, this);
        return r6 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? r6 : Result.m8978boximpl(r6);
    }
}
