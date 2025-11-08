package com.reown.foundation.network;

import com.reown.foundation.network.ConnectionState;
import com.tinder.scarlet.WebSocket;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "event", "Lcom/tinder/scarlet/WebSocket$Event;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.foundation.network.BaseRelayClient$eventsFlow$2$1", f = "BaseRelayClient.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class BaseRelayClient$eventsFlow$2$1 extends SuspendLambda implements Function2<WebSocket.Event, Continuation<? super Unit>, Object> {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ BaseRelayClient this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BaseRelayClient$eventsFlow$2$1(BaseRelayClient baseRelayClient, Continuation<? super BaseRelayClient$eventsFlow$2$1> continuation) {
        super(2, continuation);
        this.this$0 = baseRelayClient;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        BaseRelayClient$eventsFlow$2$1 baseRelayClient$eventsFlow$2$1 = new BaseRelayClient$eventsFlow$2$1(this.this$0, continuation);
        baseRelayClient$eventsFlow$2$1.L$0 = obj;
        return baseRelayClient$eventsFlow$2$1;
    }

    public final Object invoke(WebSocket.Event event, Continuation<? super Unit> continuation) {
        return ((BaseRelayClient$eventsFlow$2$1) create(event, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        WebSocket.Event event = (WebSocket.Event) this.L$0;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (event instanceof WebSocket.Event.OnConnectionOpened) {
                this.this$0.getConnectionState$foundation().setValue(ConnectionState.Open.INSTANCE);
            } else if ((event instanceof WebSocket.Event.OnConnectionClosed) || (event instanceof WebSocket.Event.OnConnectionFailed)) {
                this.this$0.getAckedTopics$foundation().clear();
                this.this$0.getConnectionState$foundation().setValue(new ConnectionState.Closed(this.this$0.getError(event)));
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
