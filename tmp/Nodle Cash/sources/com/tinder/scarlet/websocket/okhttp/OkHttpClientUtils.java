package com.tinder.scarlet.websocket.okhttp;

import com.tinder.scarlet.WebSocket;
import com.tinder.scarlet.websocket.okhttp.OkHttpWebSocket;
import com.tinder.scarlet.websocket.okhttp.request.RequestFactory;
import com.tinder.scarlet.websocket.okhttp.request.StaticUrlRequestFactory;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"newWebSocketFactory", "Lcom/tinder/scarlet/WebSocket$Factory;", "Lokhttp3/OkHttpClient;", "requestFactory", "Lcom/tinder/scarlet/websocket/okhttp/request/RequestFactory;", "url", "", "scarlet-websocket-okhttp"}, k = 2, mv = {1, 8, 0}, xi = 48)
@JvmName(name = "OkHttpClientUtils")
public final class OkHttpClientUtils {
    @NotNull
    public static final WebSocket.Factory newWebSocketFactory(@NotNull OkHttpClient okHttpClient, @NotNull RequestFactory requestFactory) {
        Intrinsics.checkNotNullParameter(okHttpClient, "<this>");
        Intrinsics.checkNotNullParameter(requestFactory, "requestFactory");
        return new OkHttpWebSocket.Factory(new OkHttpClientWebSocketConnectionEstablisher(okHttpClient, requestFactory));
    }

    @NotNull
    public static final WebSocket.Factory newWebSocketFactory(@NotNull OkHttpClient okHttpClient, @NotNull String str) {
        Intrinsics.checkNotNullParameter(okHttpClient, "<this>");
        Intrinsics.checkNotNullParameter(str, "url");
        return newWebSocketFactory(okHttpClient, (RequestFactory) new StaticUrlRequestFactory(str));
    }
}
