package com.tinder.scarlet.websocket.okhttp.request;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/tinder/scarlet/websocket/okhttp/request/StaticUrlRequestFactory;", "Lcom/tinder/scarlet/websocket/okhttp/request/RequestFactory;", "url", "", "(Ljava/lang/String;)V", "createRequest", "Lokhttp3/Request;", "scarlet-websocket-okhttp"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class StaticUrlRequestFactory implements RequestFactory {
    @NotNull
    private final String url;

    public StaticUrlRequestFactory(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "url");
        this.url = str;
    }

    @NotNull
    public Request createRequest() {
        return new Request.Builder().url(this.url).build();
    }
}
