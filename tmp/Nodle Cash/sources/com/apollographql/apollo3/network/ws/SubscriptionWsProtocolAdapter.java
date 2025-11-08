package com.apollographql.apollo3.network.ws;

import com.apollographql.apollo3.api.ApolloRequest;
import com.apollographql.apollo3.api.Operation;
import com.apollographql.apollo3.network.ws.WsProtocol;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\t\u001a\u00020\nH@¢\u0006\u0002\u0010\u000bJ\u001e\u0010\f\u001a\u00020\n2\u0014\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u000eH\u0016J \u0010\u0011\u001a\u00020\n\"\b\b\u0000\u0010\u0012*\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0015H\u0016J \u0010\u0016\u001a\u00020\n\"\b\b\u0000\u0010\u0012*\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0015H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/apollographql/apollo3/network/ws/SubscriptionWsProtocolAdapter;", "Lcom/apollographql/apollo3/network/ws/WsProtocol;", "webSocketConnection", "Lcom/apollographql/apollo3/network/ws/WebSocketConnection;", "listener", "Lcom/apollographql/apollo3/network/ws/WsProtocol$Listener;", "(Lcom/apollographql/apollo3/network/ws/WebSocketConnection;Lcom/apollographql/apollo3/network/ws/WsProtocol$Listener;)V", "delegate", "Lcom/apollographql/apollo3/network/ws/SubscriptionWsProtocol;", "connectionInit", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "handleServerMessage", "messageMap", "", "", "", "startOperation", "D", "Lcom/apollographql/apollo3/api/Operation$Data;", "request", "Lcom/apollographql/apollo3/api/ApolloRequest;", "stopOperation", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
public class SubscriptionWsProtocolAdapter extends WsProtocol {
    @NotNull
    private final SubscriptionWsProtocol delegate;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SubscriptionWsProtocolAdapter(@NotNull WebSocketConnection webSocketConnection, @NotNull WsProtocol.Listener listener) {
        super(webSocketConnection, listener);
        Intrinsics.checkNotNullParameter(webSocketConnection, "webSocketConnection");
        Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.delegate = new SubscriptionWsProtocol(webSocketConnection, listener, 0, (Function1) null, (WsFrameType) null, 28, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ Object connectionInit$suspendImpl(SubscriptionWsProtocolAdapter subscriptionWsProtocolAdapter, Continuation<? super Unit> continuation) {
        Object connectionInit = subscriptionWsProtocolAdapter.delegate.connectionInit(continuation);
        return connectionInit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? connectionInit : Unit.INSTANCE;
    }

    @Nullable
    public Object connectionInit(@NotNull Continuation<? super Unit> continuation) {
        return connectionInit$suspendImpl(this, continuation);
    }

    public void handleServerMessage(@NotNull Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(map, "messageMap");
        this.delegate.handleServerMessage(map);
    }

    public <D extends Operation.Data> void startOperation(@NotNull ApolloRequest<D> apolloRequest) {
        Intrinsics.checkNotNullParameter(apolloRequest, "request");
        this.delegate.startOperation(apolloRequest);
    }

    public <D extends Operation.Data> void stopOperation(@NotNull ApolloRequest<D> apolloRequest) {
        Intrinsics.checkNotNullParameter(apolloRequest, "request");
        this.delegate.stopOperation(apolloRequest);
    }
}
