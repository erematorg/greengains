package com.reown.sign.client;

import com.reown.sign.client.Sign;
import com.reown.sign.engine.domain.SignEngine;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.client.SignProtocol$rejectSession$1", f = "SignProtocol.kt", i = {}, l = {240}, m = "invokeSuspend", n = {}, s = {})
public final class SignProtocol$rejectSession$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1<Sign.Model.Error, Unit> $onError;
    final /* synthetic */ Function1<Sign.Params.Reject, Unit> $onSuccess;
    final /* synthetic */ Sign.Params.Reject $reject;
    int label;
    final /* synthetic */ SignProtocol this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SignProtocol$rejectSession$1(SignProtocol signProtocol, Sign.Params.Reject reject, Function1<? super Sign.Model.Error, Unit> function1, Function1<? super Sign.Params.Reject, Unit> function12, Continuation<? super SignProtocol$rejectSession$1> continuation) {
        super(2, continuation);
        this.this$0 = signProtocol;
        this.$reject = reject;
        this.$onError = function1;
        this.$onSuccess = function12;
    }

    /* access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$0(Function1 function1, Sign.Params.Reject reject) {
        function1.invoke(reject);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$1(Function1 function1, Throwable th) {
        function1.invoke(new Sign.Model.Error(th));
        return Unit.INSTANCE;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SignProtocol$rejectSession$1(this.this$0, this.$reject, this.$onError, this.$onSuccess, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            SignEngine access$getSignEngine$p = this.this$0.signEngine;
            if (access$getSignEngine$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("signEngine");
                access$getSignEngine$p = null;
            }
            SignEngine signEngine = access$getSignEngine$p;
            String proposerPublicKey = this.$reject.getProposerPublicKey();
            String reason = this.$reject.getReason();
            c cVar = new c(this.$onSuccess, this.$reject, 8);
            d dVar = new d(this.$onError, 12);
            this.label = 1;
            if (signEngine.reject(proposerPublicKey, reason, cVar, dVar, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i3 == 1) {
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Exception e3) {
                this.$onError.invoke(new Sign.Model.Error(e3));
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SignProtocol$rejectSession$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
