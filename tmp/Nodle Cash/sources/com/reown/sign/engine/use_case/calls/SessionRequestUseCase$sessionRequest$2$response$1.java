package com.reown.sign.engine.use_case.calls;

import com.reown.sign.common.model.vo.clientsync.session.SignRpc;
import com.reown.sign.engine.domain.wallet_service.WalletServiceRequester;
import java.net.URL;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.calls.SessionRequestUseCase$sessionRequest$2$response$1", f = "SessionRequestUseCase.kt", i = {}, l = {111}, m = "invokeSuspend", n = {}, s = {})
public final class SessionRequestUseCase$sessionRequest$2$response$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
    final /* synthetic */ SignRpc.SessionRequest $sessionPayload;
    final /* synthetic */ URL $walletServiceUrl;
    int label;
    final /* synthetic */ SessionRequestUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SessionRequestUseCase$sessionRequest$2$response$1(SessionRequestUseCase sessionRequestUseCase, SignRpc.SessionRequest sessionRequest, URL url, Continuation<? super SessionRequestUseCase$sessionRequest$2$response$1> continuation) {
        super(2, continuation);
        this.this$0 = sessionRequestUseCase;
        this.$sessionPayload = sessionRequest;
        this.$walletServiceUrl = url;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SessionRequestUseCase$sessionRequest$2$response$1(this.this$0, this.$sessionPayload, this.$walletServiceUrl, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            WalletServiceRequester access$getWalletServiceRequester$p = this.this$0.walletServiceRequester;
            SignRpc.SessionRequest sessionRequest = this.$sessionPayload;
            String url = this.$walletServiceUrl.toString();
            Intrinsics.checkNotNullExpressionValue(url, "toString(...)");
            this.label = 1;
            obj = access$getWalletServiceRequester$p.request(sessionRequest, url, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i3 == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return obj;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
        return ((SessionRequestUseCase$sessionRequest$2$response$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
