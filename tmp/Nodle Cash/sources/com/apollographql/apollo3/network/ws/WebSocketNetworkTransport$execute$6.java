package com.apollographql.apollo3.network.ws;

import com.apollographql.apollo3.api.ApolloRequest;
import com.apollographql.apollo3.api.ApolloResponse;
import com.apollographql.apollo3.network.ws.internal.StopOperation;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H@"}, d2 = {"<anonymous>", "", "D", "Lcom/apollographql/apollo3/api/Operation$Data;", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/apollographql/apollo3/api/ApolloResponse;", "it", ""}, k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$execute$6", f = "WebSocketNetworkTransport.kt", i = {}, l = {333}, m = "invokeSuspend", n = {}, s = {})
public final class WebSocketNetworkTransport$execute$6 extends SuspendLambda implements Function3<FlowCollector<? super ApolloResponse<D>>, Throwable, Continuation<? super Unit>, Object> {
    final /* synthetic */ ApolloRequest<D> $request;
    int label;
    final /* synthetic */ WebSocketNetworkTransport this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WebSocketNetworkTransport$execute$6(WebSocketNetworkTransport webSocketNetworkTransport, ApolloRequest<D> apolloRequest, Continuation<? super WebSocketNetworkTransport$execute$6> continuation) {
        super(3, continuation);
        this.this$0 = webSocketNetworkTransport;
        this.$request = apolloRequest;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            Channel access$getMessages$p = this.this$0.messages;
            StopOperation stopOperation = new StopOperation(this.$request);
            this.label = 1;
            if (access$getMessages$p.send(stopOperation, this) == coroutine_suspended) {
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
    public final Object invoke(@NotNull FlowCollector<? super ApolloResponse<D>> flowCollector, @Nullable Throwable th, @Nullable Continuation<? super Unit> continuation) {
        return new WebSocketNetworkTransport$execute$6(this.this$0, this.$request, continuation).invokeSuspend(Unit.INSTANCE);
    }
}
