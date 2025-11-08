package com.reown.sign.engine.use_case.calls;

import com.reown.android.internal.common.model.Expiry;
import com.reown.sign.engine.model.EngineDO;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.calls.SessionAuthenticateUseCase", f = "SessionAuthenticateUseCase.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {108, 111}, m = "authenticate", n = {"authenticate", "methods", "pairingTopic", "expiry", "walletAppLink", "onSuccess", "onFailure", "requestExpiry", "optionalNamespaces", "externalReCapsJson", "signReCapsJson", "reCaps", "requesterPublicKey", "responseTopic", "authParams", "authRequest", "authenticate", "methods", "pairingTopic", "expiry", "walletAppLink", "onSuccess", "onFailure", "requestExpiry", "optionalNamespaces", "externalReCapsJson", "signReCapsJson", "reCaps", "requesterPublicKey", "responseTopic", "authParams", "authRequest"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$15", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$15"})
public final class SessionAuthenticateUseCase$authenticate$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$10;
    Object L$11;
    Object L$12;
    Object L$13;
    Object L$14;
    Object L$15;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SessionAuthenticateUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SessionAuthenticateUseCase$authenticate$1(SessionAuthenticateUseCase sessionAuthenticateUseCase, Continuation<? super SessionAuthenticateUseCase$authenticate$1> continuation) {
        super(continuation);
        this.this$0 = sessionAuthenticateUseCase;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.authenticate((EngineDO.Authenticate) null, (List<String>) null, (String) null, (Expiry) null, (String) null, (Function1<? super String, Unit>) null, (Function1<? super Throwable, Unit>) null, this);
    }
}
