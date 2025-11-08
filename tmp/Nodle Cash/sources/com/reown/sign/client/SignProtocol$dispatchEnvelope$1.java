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
@DebugMetadata(c = "com.reown.sign.client.SignProtocol$dispatchEnvelope$1", f = "SignProtocol.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class SignProtocol$dispatchEnvelope$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1<Sign.Model.Error, Unit> $onError;
    final /* synthetic */ String $urlWithEnvelope;
    int label;
    final /* synthetic */ SignProtocol this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SignProtocol$dispatchEnvelope$1(SignProtocol signProtocol, String str, Function1<? super Sign.Model.Error, Unit> function1, Continuation<? super SignProtocol$dispatchEnvelope$1> continuation) {
        super(2, continuation);
        this.this$0 = signProtocol;
        this.$urlWithEnvelope = str;
        this.$onError = function1;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SignProtocol$dispatchEnvelope$1(this.this$0, this.$urlWithEnvelope, this.$onError, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            try {
                SignEngine access$getSignEngine$p = this.this$0.signEngine;
                if (access$getSignEngine$p == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("signEngine");
                    access$getSignEngine$p = null;
                }
                access$getSignEngine$p.dispatchEnvelope(this.$urlWithEnvelope);
            } catch (Exception e3) {
                this.$onError.invoke(new Sign.Model.Error(e3));
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SignProtocol$dispatchEnvelope$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
