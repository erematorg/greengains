package com.tinder.scarlet.websocket.okhttp;

import com.tinder.scarlet.websocket.okhttp.OkHttpWebSocket;
import com.tinder.scarlet.websocket.okhttp.request.RequestFactory;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;
import okhttp3.WebSocketListener;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/tinder/scarlet/websocket/okhttp/OkHttpClientWebSocketConnectionEstablisher;", "Lcom/tinder/scarlet/websocket/okhttp/OkHttpWebSocket$ConnectionEstablisher;", "okHttpClient", "Lokhttp3/OkHttpClient;", "requestFactory", "Lcom/tinder/scarlet/websocket/okhttp/request/RequestFactory;", "(Lokhttp3/OkHttpClient;Lcom/tinder/scarlet/websocket/okhttp/request/RequestFactory;)V", "establishConnection", "", "webSocketListener", "Lokhttp3/WebSocketListener;", "scarlet-websocket-okhttp"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class OkHttpClientWebSocketConnectionEstablisher implements OkHttpWebSocket.ConnectionEstablisher {
    @NotNull
    private final OkHttpClient okHttpClient;
    @NotNull
    private final RequestFactory requestFactory;

    public OkHttpClientWebSocketConnectionEstablisher(@NotNull OkHttpClient okHttpClient2, @NotNull RequestFactory requestFactory2) {
        Intrinsics.checkNotNullParameter(okHttpClient2, "okHttpClient");
        Intrinsics.checkNotNullParameter(requestFactory2, "requestFactory");
        this.okHttpClient = okHttpClient2;
        this.requestFactory = requestFactory2;
    }

    public void establishConnection(@NotNull WebSocketListener webSocketListener) {
        Intrinsics.checkNotNullParameter(webSocketListener, "webSocketListener");
        this.okHttpClient.newWebSocket(this.requestFactory.createRequest(), webSocketListener);
    }
}
