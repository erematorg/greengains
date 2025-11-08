package com.apollographql.apollo3.network.ws;

import com.apollographql.apollo3.network.ws.WsProtocol;
import com.apollographql.apollo3.network.ws.internal.GeneralError;
import com.apollographql.apollo3.network.ws.internal.NetworkError;
import com.apollographql.apollo3.network.ws.internal.OperationComplete;
import com.apollographql.apollo3.network.ws.internal.OperationError;
import com.apollographql.apollo3.network.ws.internal.OperationResponse;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0016\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0018\u00010\u0005H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0006H\u0016J(\u0010\r\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00062\u0016\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0018\u00010\u0005H\u0016J&\u0010\u000e\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00062\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005H\u0016¨\u0006\u000f"}, d2 = {"com/apollographql/apollo3/network/ws/WebSocketNetworkTransport$listener$1", "Lcom/apollographql/apollo3/network/ws/WsProtocol$Listener;", "generalError", "", "payload", "", "", "", "networkError", "cause", "", "operationComplete", "id", "operationError", "operationResponse", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class WebSocketNetworkTransport$listener$1 implements WsProtocol.Listener {
    final /* synthetic */ WebSocketNetworkTransport this$0;

    public WebSocketNetworkTransport$listener$1(WebSocketNetworkTransport webSocketNetworkTransport) {
        this.this$0 = webSocketNetworkTransport;
    }

    public void generalError(@Nullable Map<String, ? extends Object> map) {
        this.this$0.messages.m10510trySendJP2dKIU(new GeneralError(map));
    }

    public void networkError(@NotNull Throwable th) {
        Intrinsics.checkNotNullParameter(th, "cause");
        this.this$0.messages.m10510trySendJP2dKIU(new NetworkError(th));
    }

    public void operationComplete(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        this.this$0.messages.m10510trySendJP2dKIU(new OperationComplete(str));
    }

    public void operationError(@NotNull String str, @Nullable Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        this.this$0.messages.m10510trySendJP2dKIU(new OperationError(str, map));
    }

    public void operationResponse(@NotNull String str, @NotNull Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        Intrinsics.checkNotNullParameter(map, "payload");
        this.this$0.messages.m10510trySendJP2dKIU(new OperationResponse(str, map));
    }
}
