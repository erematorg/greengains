package com.apollographql.apollo3.network.ws;

import com.apollographql.apollo3.network.NetworkTransport;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"closeConnection", "", "Lcom/apollographql/apollo3/network/NetworkTransport;", "reason", "", "apollo-runtime"}, k = 2, mv = {1, 5, 1}, xi = 48)
public final class WebSocketNetworkTransportKt {
    public static final void closeConnection(@NotNull NetworkTransport networkTransport, @NotNull Throwable th) {
        Intrinsics.checkNotNullParameter(networkTransport, "<this>");
        Intrinsics.checkNotNullParameter(th, "reason");
        WebSocketNetworkTransport webSocketNetworkTransport = networkTransport instanceof WebSocketNetworkTransport ? (WebSocketNetworkTransport) networkTransport : null;
        if (webSocketNetworkTransport != null) {
            webSocketNetworkTransport.closeConnection(th);
            return;
        }
        throw new NotImplementedError("closeConnection is only for WebSocketNetworkTransport");
    }
}
