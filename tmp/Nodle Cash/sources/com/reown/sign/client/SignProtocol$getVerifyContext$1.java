package com.reown.sign.client;

import com.reown.sign.client.Sign;
import com.reown.sign.engine.domain.SignEngine;
import com.reown.sign.engine.model.EngineDO;
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

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/reown/sign/client/Sign$Model$VerifyContext;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.client.SignProtocol$getVerifyContext$1", f = "SignProtocol.kt", i = {}, l = {481}, m = "invokeSuspend", n = {}, s = {})
public final class SignProtocol$getVerifyContext$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Sign.Model.VerifyContext>, Object> {
    final /* synthetic */ long $id;
    int label;
    final /* synthetic */ SignProtocol this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SignProtocol$getVerifyContext$1(SignProtocol signProtocol, long j2, Continuation<? super SignProtocol$getVerifyContext$1> continuation) {
        super(2, continuation);
        this.this$0 = signProtocol;
        this.$id = j2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SignProtocol$getVerifyContext$1(this.this$0, this.$id, continuation);
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
            long j2 = this.$id;
            this.label = 1;
            obj = access$getSignEngine$p.getVerifyContext(j2, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i3 == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        EngineDO.VerifyContext verifyContext = (EngineDO.VerifyContext) obj;
        if (verifyContext != null) {
            return Intrinsics.checkNotNullParameter(verifyContext, "<this>");
        }
        return null;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Sign.Model.VerifyContext> continuation) {
        return ((SignProtocol$getVerifyContext$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
