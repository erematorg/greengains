package com.reown.android.relay;

import com.reown.foundation.network.model.Relay;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "event", "Lcom/reown/foundation/network/model/Relay$Model$Event;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.relay.RelayClient$monitorConnectionState$1", f = "RelayClient.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class RelayClient$monitorConnectionState$1 extends SuspendLambda implements Function2<Relay.Model.Event, Continuation<? super Unit>, Object> {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ RelayClient this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RelayClient$monitorConnectionState$1(RelayClient relayClient, Continuation<? super RelayClient$monitorConnectionState$1> continuation) {
        super(2, continuation);
        this.this$0 = relayClient;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        RelayClient$monitorConnectionState$1 relayClient$monitorConnectionState$1 = new RelayClient$monitorConnectionState$1(this.this$0, continuation);
        relayClient$monitorConnectionState$1.L$0 = obj;
        return relayClient$monitorConnectionState$1;
    }

    public final Object invoke(Relay.Model.Event event, Continuation<? super Unit> continuation) {
        return ((RelayClient$monitorConnectionState$1) create(event, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Relay.Model.Event event = (Relay.Model.Event) this.L$0;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            this.this$0.setIsWSSConnectionOpened(event);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
