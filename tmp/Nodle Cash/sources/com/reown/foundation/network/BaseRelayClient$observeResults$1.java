package com.reown.foundation.network;

import com.reown.foundation.network.model.RelayDTO;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

@SourceDebugExtension({"SMAP\nBaseRelayClient.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BaseRelayClient.kt\ncom/reown/foundation/network/BaseRelayClient$observeResults$1\n+ 2 Collect.kt\nkotlinx/coroutines/flow/FlowKt__CollectKt\n*L\n1#1,533:1\n72#2,3:534\n*S KotlinDebug\n*F\n+ 1 BaseRelayClient.kt\ncom/reown/foundation/network/BaseRelayClient$observeResults$1\n*L\n85#1:534,3\n*E\n"})
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.foundation.network.BaseRelayClient$observeResults$1", f = "BaseRelayClient.kt", i = {0, 0}, l = {534}, m = "invokeSuspend", n = {"$this$collect$iv", "$i$f$collect"}, s = {"L$0", "I$0"})
public final class BaseRelayClient$observeResults$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int I$0;
    Object L$0;
    int label;
    final /* synthetic */ BaseRelayClient this$0;

    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/reown/foundation/network/model/RelayDTO;", "exception", ""}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.reown.foundation.network.BaseRelayClient$observeResults$1$1", f = "BaseRelayClient.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.reown.foundation.network.BaseRelayClient$observeResults$1$1  reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function3<FlowCollector<? super RelayDTO>, Throwable, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;

        public final Object invokeSuspend(Object obj) {
            Throwable th = (Throwable) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                baseRelayClient.getLogger().error(th);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }

        public final Object invoke(FlowCollector<? super RelayDTO> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            AnonymousClass1 r12 = new AnonymousClass1(baseRelayClient, continuation);
            r12.L$0 = th;
            return r12.invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BaseRelayClient$observeResults$1(BaseRelayClient baseRelayClient, Continuation<? super BaseRelayClient$observeResults$1> continuation) {
        super(2, continuation);
        this.this$0 = baseRelayClient;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BaseRelayClient$observeResults$1(this.this$0, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            Flow merge = FlowKt.merge((Flow<? extends T>[]) new Flow[]{this.this$0.getRelayService().observePublishAcknowledgement(), this.this$0.getRelayService().observePublishError(), this.this$0.getRelayService().observeProposeSessionAcknowledgement(), this.this$0.getRelayService().observeProposeSessionError(), this.this$0.getRelayService().observeApproveSessionAcknowledgement(), this.this$0.getRelayService().observeApproveSessionError(), this.this$0.getRelayService().observeBatchSubscribeAcknowledgement(), this.this$0.getRelayService().observeBatchSubscribeError(), this.this$0.getRelayService().observeSubscribeAcknowledgement(), this.this$0.getRelayService().observeSubscribeError(), this.this$0.getRelayService().observeUnsubscribeAcknowledgement(), this.this$0.getRelayService().observeUnsubscribeError()});
            final BaseRelayClient baseRelayClient = this.this$0;
            Flow flow = FlowKt.m10511catch(merge, new AnonymousClass1((Continuation<? super AnonymousClass1>) null));
            BaseRelayClient$observeResults$1$invokeSuspend$$inlined$collect$1 baseRelayClient$observeResults$1$invokeSuspend$$inlined$collect$1 = new BaseRelayClient$observeResults$1$invokeSuspend$$inlined$collect$1(this.this$0);
            this.L$0 = SpillingKt.nullOutSpilledVariable(flow);
            this.I$0 = 0;
            this.label = 1;
            if (flow.collect(baseRelayClient$observeResults$1$invokeSuspend$$inlined$collect$1, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i3 == 1) {
            Flow flow2 = (Flow) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BaseRelayClient$observeResults$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
