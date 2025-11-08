package com.apollographql.apollo3.network.ws;

import com.apollographql.apollo3.api.ApolloRequest;
import com.apollographql.apollo3.network.ws.internal.ConnectionReEstablished;
import com.apollographql.apollo3.network.ws.internal.Event;
import com.apollographql.apollo3.network.ws.internal.GeneralError;
import com.apollographql.apollo3.network.ws.internal.NetworkError;
import com.apollographql.apollo3.network.ws.internal.OperationComplete;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0005H@"}, d2 = {"<anonymous>", "", "D", "Lcom/apollographql/apollo3/api/Operation$Data;", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/apollographql/apollo3/network/ws/internal/Event;", "it"}, k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$execute$3", f = "WebSocketNetworkTransport.kt", i = {}, l = {287, 298}, m = "invokeSuspend", n = {}, s = {})
public final class WebSocketNetworkTransport$execute$3 extends SuspendLambda implements Function3<FlowCollector<? super Event>, Event, Continuation<? super Boolean>, Object> {
    final /* synthetic */ ApolloRequest<D> $request;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WebSocketNetworkTransport$execute$3(ApolloRequest<D> apolloRequest, Continuation<? super WebSocketNetworkTransport$execute$3> continuation) {
        super(3, continuation);
        this.$request = apolloRequest;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        boolean z2 = false;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            Event event = (Event) this.L$1;
            if (!(event instanceof OperationComplete) && !(event instanceof ConnectionReEstablished)) {
                if (event instanceof NetworkError) {
                    this.L$0 = null;
                    this.label = 1;
                    if (flowCollector.emit(event, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (event instanceof GeneralError) {
                    System.out.println("Received general error while executing operation " + this.$request.getOperation().name() + ": " + ((GeneralError) event).getPayload());
                } else {
                    this.L$0 = null;
                    this.label = 2;
                    if (flowCollector.emit(event, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            }
            return Boxing.boxBoolean(z2);
        } else if (i3 == 1) {
            ResultKt.throwOnFailure(obj);
            return Boxing.boxBoolean(z2);
        } else if (i3 == 2) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        z2 = true;
        return Boxing.boxBoolean(z2);
    }

    @Nullable
    public final Object invoke(@NotNull FlowCollector<? super Event> flowCollector, @NotNull Event event, @Nullable Continuation<? super Boolean> continuation) {
        WebSocketNetworkTransport$execute$3 webSocketNetworkTransport$execute$3 = new WebSocketNetworkTransport$execute$3(this.$request, continuation);
        webSocketNetworkTransport$execute$3.L$0 = flowCollector;
        webSocketNetworkTransport$execute$3.L$1 = event;
        return webSocketNetworkTransport$execute$3.invokeSuspend(Unit.INSTANCE);
    }
}
