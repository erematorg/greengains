package com.reown.sign.engine.use_case.calls;

import com.reown.android.Core;
import com.reown.foundation.common.model.Topic;
import com.reown.sign.storage.authenticate.AuthenticateResponseTopicRepository;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.calls.SessionAuthenticateUseCase$authenticate$2$1", f = "SessionAuthenticateUseCase.kt", i = {}, l = {130}, m = "invokeSuspend", n = {}, s = {})
public final class SessionAuthenticateUseCase$authenticate$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Core.Model.Pairing $pairing;
    final /* synthetic */ Topic $responseTopic;
    int label;
    final /* synthetic */ SessionAuthenticateUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SessionAuthenticateUseCase$authenticate$2$1(SessionAuthenticateUseCase sessionAuthenticateUseCase, Core.Model.Pairing pairing, Topic topic, Continuation<? super SessionAuthenticateUseCase$authenticate$2$1> continuation) {
        super(2, continuation);
        this.this$0 = sessionAuthenticateUseCase;
        this.$pairing = pairing;
        this.$responseTopic = topic;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SessionAuthenticateUseCase$authenticate$2$1(this.this$0, this.$pairing, this.$responseTopic, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            AuthenticateResponseTopicRepository access$getAuthenticateResponseTopicRepository$p = this.this$0.authenticateResponseTopicRepository;
            String topic = this.$pairing.getTopic();
            String value = this.$responseTopic.getValue();
            this.label = 1;
            if (access$getAuthenticateResponseTopicRepository$p.insertOrAbort(topic, value, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i3 == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SessionAuthenticateUseCase$authenticate$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
