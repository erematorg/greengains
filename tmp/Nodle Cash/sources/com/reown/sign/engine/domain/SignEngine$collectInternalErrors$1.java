package com.reown.sign.engine.domain;

import com.reown.android.internal.common.model.SDKError;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.MutableSharedFlow;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "exception", "Lcom/reown/android/internal/common/model/SDKError;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.domain.SignEngine$collectInternalErrors$1", f = "SignEngine.kt", i = {0}, l = {302}, m = "invokeSuspend", n = {"exception"}, s = {"L$0"})
public final class SignEngine$collectInternalErrors$1 extends SuspendLambda implements Function2<SDKError, Continuation<? super Unit>, Object> {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ SignEngine this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SignEngine$collectInternalErrors$1(SignEngine signEngine, Continuation<? super SignEngine$collectInternalErrors$1> continuation) {
        super(2, continuation);
        this.this$0 = signEngine;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SignEngine$collectInternalErrors$1 signEngine$collectInternalErrors$1 = new SignEngine$collectInternalErrors$1(this.this$0, continuation);
        signEngine$collectInternalErrors$1.L$0 = obj;
        return signEngine$collectInternalErrors$1;
    }

    public final Object invoke(SDKError sDKError, Continuation<? super Unit> continuation) {
        return ((SignEngine$collectInternalErrors$1) create(sDKError, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        SDKError sDKError = (SDKError) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            MutableSharedFlow access$get_engineEvent$p = this.this$0._engineEvent;
            this.L$0 = SpillingKt.nullOutSpilledVariable(sDKError);
            this.label = 1;
            if (access$get_engineEvent$p.emit(sDKError, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i3 == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
