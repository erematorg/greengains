package com.apollographql.apollo3.network.ws;

import com.apollographql.apollo3.api.ApolloRequest;
import com.apollographql.apollo3.network.ws.internal.Event;
import com.apollographql.apollo3.network.ws.internal.StartOperation;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u00020\u00050\u0004H@"}, d2 = {"<anonymous>", "", "D", "Lcom/apollographql/apollo3/api/Operation$Data;", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/apollographql/apollo3/network/ws/internal/Event;"}, k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$execute$1", f = "WebSocketNetworkTransport.kt", i = {}, l = {272}, m = "invokeSuspend", n = {}, s = {})
public final class WebSocketNetworkTransport$execute$1 extends SuspendLambda implements Function2<FlowCollector<? super Event>, Continuation<? super Unit>, Object> {
    final /* synthetic */ ApolloRequest<D> $request;
    int label;
    final /* synthetic */ WebSocketNetworkTransport this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WebSocketNetworkTransport$execute$1(WebSocketNetworkTransport webSocketNetworkTransport, ApolloRequest<D> apolloRequest, Continuation<? super WebSocketNetworkTransport$execute$1> continuation) {
        super(2, continuation);
        this.this$0 = webSocketNetworkTransport;
        this.$request = apolloRequest;
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new WebSocketNetworkTransport$execute$1(this.this$0, this.$request, continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            Channel access$getMessages$p = this.this$0.messages;
            StartOperation startOperation = new StartOperation(this.$request);
            this.label = 1;
            if (access$getMessages$p.send(startOperation, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i3 == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }

    @Nullable
    public final Object invoke(@NotNull FlowCollector<? super Event> flowCollector, @Nullable Continuation<? super Unit> continuation) {
        return ((WebSocketNetworkTransport$execute$1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
