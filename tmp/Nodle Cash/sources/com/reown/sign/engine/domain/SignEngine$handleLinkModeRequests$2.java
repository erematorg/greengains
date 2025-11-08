package com.reown.sign.engine.domain;

import com.reown.android.internal.common.model.WCRequest;
import com.reown.android.internal.common.model.type.ClientParams;
import com.reown.sign.common.model.vo.clientsync.session.params.SignParams;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "request", "Lcom/reown/android/internal/common/model/WCRequest;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.domain.SignEngine$handleLinkModeRequests$2", f = "SignEngine.kt", i = {0, 0, 1, 1}, l = {258, 259}, m = "invokeSuspend", n = {"request", "requestParams", "request", "requestParams"}, s = {"L$0", "L$1", "L$0", "L$1"})
public final class SignEngine$handleLinkModeRequests$2 extends SuspendLambda implements Function2<WCRequest, Continuation<? super Unit>, Object> {
    /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ SignEngine this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SignEngine$handleLinkModeRequests$2(SignEngine signEngine, Continuation<? super SignEngine$handleLinkModeRequests$2> continuation) {
        super(2, continuation);
        this.this$0 = signEngine;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SignEngine$handleLinkModeRequests$2 signEngine$handleLinkModeRequests$2 = new SignEngine$handleLinkModeRequests$2(this.this$0, continuation);
        signEngine$handleLinkModeRequests$2.L$0 = obj;
        return signEngine$handleLinkModeRequests$2;
    }

    public final Object invoke(WCRequest wCRequest, Continuation<? super Unit> continuation) {
        return ((SignEngine$handleLinkModeRequests$2) create(wCRequest, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        WCRequest wCRequest = (WCRequest) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            ClientParams params = wCRequest.getParams();
            if (params instanceof SignParams.SessionAuthenticateParams) {
                this.L$0 = SpillingKt.nullOutSpilledVariable(wCRequest);
                this.L$1 = SpillingKt.nullOutSpilledVariable(params);
                this.label = 1;
                if (this.this$0.onAuthenticateSessionUseCase.invoke(wCRequest, (SignParams.SessionAuthenticateParams) params, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (params instanceof SignParams.SessionRequestParams) {
                this.L$0 = SpillingKt.nullOutSpilledVariable(wCRequest);
                this.L$1 = SpillingKt.nullOutSpilledVariable(params);
                this.label = 2;
                if (this.this$0.onSessionRequestUseCase.invoke(wCRequest, (SignParams.SessionRequestParams) params, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else if (i3 == 1 || i3 == 2) {
            ClientParams clientParams = (ClientParams) this.L$1;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
