package com.reown.foundation.network;

import com.reown.foundation.network.model.Relay;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.SupervisorKt;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "relayRequest", "Lcom/reown/foundation/network/model/Relay$Model$Call$Subscription$Request;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.foundation.network.BaseRelayClient$subscriptionRequest$2$2", f = "BaseRelayClient.kt", i = {0}, l = {116}, m = "invokeSuspend", n = {"relayRequest"}, s = {"L$0"})
public final class BaseRelayClient$subscriptionRequest$2$2 extends SuspendLambda implements Function2<Relay.Model.Call.Subscription.Request, Continuation<? super Unit>, Object> {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ BaseRelayClient this$0;

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.reown.foundation.network.BaseRelayClient$subscriptionRequest$2$2$1", f = "BaseRelayClient.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.reown.foundation.network.BaseRelayClient$subscriptionRequest$2$2$1  reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(baseRelayClient, request, continuation);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                baseRelayClient.publishSubscriptionAcknowledgement(request.getId());
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BaseRelayClient$subscriptionRequest$2$2(BaseRelayClient baseRelayClient, Continuation<? super BaseRelayClient$subscriptionRequest$2$2> continuation) {
        super(2, continuation);
        this.this$0 = baseRelayClient;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        BaseRelayClient$subscriptionRequest$2$2 baseRelayClient$subscriptionRequest$2$2 = new BaseRelayClient$subscriptionRequest$2$2(this.this$0, continuation);
        baseRelayClient$subscriptionRequest$2$2.L$0 = obj;
        return baseRelayClient$subscriptionRequest$2$2;
    }

    public final Object invoke(Relay.Model.Call.Subscription.Request request, Continuation<? super Unit> continuation) {
        return ((BaseRelayClient$subscriptionRequest$2$2) create(request, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        final Relay.Model.Call.Subscription.Request request = (Relay.Model.Call.Subscription.Request) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            final BaseRelayClient baseRelayClient = this.this$0;
            AnonymousClass1 r6 = new AnonymousClass1((Continuation<? super AnonymousClass1>) null);
            this.L$0 = SpillingKt.nullOutSpilledVariable(request);
            this.label = 1;
            if (SupervisorKt.supervisorScope(r6, this) == coroutine_suspended) {
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
