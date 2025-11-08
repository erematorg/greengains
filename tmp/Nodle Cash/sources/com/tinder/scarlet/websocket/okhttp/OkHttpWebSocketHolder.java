package com.tinder.scarlet.websocket.okhttp;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.WebSocket;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u001a\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\u000e\u0010\f\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0001J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0016J\u0010\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u000bH\u0016J\u0010\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0006\u0010\u0014\u001a\u00020\u0005R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0001X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/tinder/scarlet/websocket/okhttp/OkHttpWebSocketHolder;", "Lokhttp3/WebSocket;", "()V", "webSocket", "cancel", "", "close", "", "code", "", "reason", "", "initiate", "queueSize", "", "request", "send", "text", "bytes", "Lokio/ByteString;", "shutdown", "scarlet-websocket-okhttp"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class OkHttpWebSocketHolder implements WebSocket {
    @Nullable
    private WebSocket webSocket;

    public void cancel() {
        WebSocket webSocket2 = this.webSocket;
        if (webSocket2 != null) {
            webSocket2.cancel();
            Unit unit = Unit.INSTANCE;
        }
    }

    public boolean close(int i3, @Nullable String str) {
        WebSocket webSocket2 = this.webSocket;
        if (webSocket2 != null) {
            return webSocket2.close(i3, str);
        }
        return false;
    }

    public final void initiate(@NotNull WebSocket webSocket2) {
        Intrinsics.checkNotNullParameter(webSocket2, "webSocket");
        this.webSocket = webSocket2;
    }

    public boolean send(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "text");
        WebSocket webSocket2 = this.webSocket;
        if (webSocket2 != null) {
            return webSocket2.send(str);
        }
        return false;
    }

    public final void shutdown() {
        this.webSocket = null;
    }

    @NotNull
    public Void queueSize() {
        throw new UnsupportedOperationException();
    }

    @NotNull
    public Void request() {
        throw new UnsupportedOperationException();
    }

    public boolean send(@NotNull ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "bytes");
        WebSocket webSocket2 = this.webSocket;
        if (webSocket2 != null) {
            return webSocket2.send(byteString);
        }
        return false;
    }
}
