package com.reown.sign.engine.use_case.calls;

import com.reown.android.Core;
import com.reown.foundation.common.model.Topic;
import com.reown.sign.engine.model.EngineDO;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.calls.SessionAuthenticateUseCase", f = "SessionAuthenticateUseCase.kt", i = {0, 0, 0, 0, 1, 1, 1, 1}, l = {212, 228}, m = "publishSessionProposeDeferred-BWLJW6A", n = {"pairing", "optionalNamespaces", "responseTopic", "sessionProposeDeferred", "pairing", "optionalNamespaces", "responseTopic", "sessionProposeDeferred"}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3"})
public final class SessionAuthenticateUseCase$publishSessionProposeDeferred$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SessionAuthenticateUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SessionAuthenticateUseCase$publishSessionProposeDeferred$1(SessionAuthenticateUseCase sessionAuthenticateUseCase, Continuation<? super SessionAuthenticateUseCase$publishSessionProposeDeferred$1> continuation) {
        super(continuation);
        this.this$0 = sessionAuthenticateUseCase;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object r12 = this.this$0.m8894publishSessionProposeDeferredBWLJW6A((Core.Model.Pairing) null, (Map<String, EngineDO.Namespace.Proposal>) null, (Topic) null, this);
        return r12 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? r12 : Result.m8978boximpl(r12);
    }
}
