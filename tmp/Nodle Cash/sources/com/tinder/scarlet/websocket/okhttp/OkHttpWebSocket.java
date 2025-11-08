package com.tinder.scarlet.websocket.okhttp;

import com.reown.android.push.notifications.PushMessagingService;
import com.tinder.scarlet.Message;
import com.tinder.scarlet.ShutdownReason;
import com.tinder.scarlet.Stream;
import com.tinder.scarlet.WebSocket;
import com.tinder.scarlet.utils.FlowableUtils;
import com.tinder.scarlet.utils.a;
import io.reactivex.Flowable;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.WebSocketListener;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0002\u0018\u0019B\u001f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\nH\u0002J\u0010\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u000e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u0017H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/tinder/scarlet/websocket/okhttp/OkHttpWebSocket;", "Lcom/tinder/scarlet/WebSocket;", "okHttpWebSocketHolder", "Lcom/tinder/scarlet/websocket/okhttp/OkHttpWebSocketHolder;", "okHttpWebSocketEventObserver", "Lcom/tinder/scarlet/websocket/okhttp/OkHttpWebSocketEventObserver;", "connectionEstablisher", "Lcom/tinder/scarlet/websocket/okhttp/OkHttpWebSocket$ConnectionEstablisher;", "(Lcom/tinder/scarlet/websocket/okhttp/OkHttpWebSocketHolder;Lcom/tinder/scarlet/websocket/okhttp/OkHttpWebSocketEventObserver;Lcom/tinder/scarlet/websocket/okhttp/OkHttpWebSocket$ConnectionEstablisher;)V", "cancel", "", "close", "", "shutdownReason", "Lcom/tinder/scarlet/ShutdownReason;", "handleConnectionShutdown", "handleWebSocketEvent", "event", "Lcom/tinder/scarlet/WebSocket$Event;", "open", "Lcom/tinder/scarlet/Stream;", "send", "message", "Lcom/tinder/scarlet/Message;", "ConnectionEstablisher", "Factory", "scarlet-websocket-okhttp"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class OkHttpWebSocket implements WebSocket {
    /* access modifiers changed from: private */
    @NotNull
    public final ConnectionEstablisher connectionEstablisher;
    /* access modifiers changed from: private */
    @NotNull
    public final OkHttpWebSocketEventObserver okHttpWebSocketEventObserver;
    @NotNull
    private final OkHttpWebSocketHolder okHttpWebSocketHolder;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/tinder/scarlet/websocket/okhttp/OkHttpWebSocket$ConnectionEstablisher;", "", "establishConnection", "", "webSocketListener", "Lokhttp3/WebSocketListener;", "scarlet-websocket-okhttp"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public interface ConnectionEstablisher {
        void establishConnection(@NotNull WebSocketListener webSocketListener);
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/tinder/scarlet/websocket/okhttp/OkHttpWebSocket$Factory;", "Lcom/tinder/scarlet/WebSocket$Factory;", "connectionEstablisher", "Lcom/tinder/scarlet/websocket/okhttp/OkHttpWebSocket$ConnectionEstablisher;", "(Lcom/tinder/scarlet/websocket/okhttp/OkHttpWebSocket$ConnectionEstablisher;)V", "create", "Lcom/tinder/scarlet/WebSocket;", "scarlet-websocket-okhttp"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Factory implements WebSocket.Factory {
        @NotNull
        private final ConnectionEstablisher connectionEstablisher;

        public Factory(@NotNull ConnectionEstablisher connectionEstablisher2) {
            Intrinsics.checkNotNullParameter(connectionEstablisher2, "connectionEstablisher");
            this.connectionEstablisher = connectionEstablisher2;
        }

        @NotNull
        public WebSocket create() {
            return new OkHttpWebSocket(new OkHttpWebSocketHolder(), new OkHttpWebSocketEventObserver(), this.connectionEstablisher);
        }
    }

    public OkHttpWebSocket(@NotNull OkHttpWebSocketHolder okHttpWebSocketHolder2, @NotNull OkHttpWebSocketEventObserver okHttpWebSocketEventObserver2, @NotNull ConnectionEstablisher connectionEstablisher2) {
        Intrinsics.checkNotNullParameter(okHttpWebSocketHolder2, "okHttpWebSocketHolder");
        Intrinsics.checkNotNullParameter(okHttpWebSocketEventObserver2, "okHttpWebSocketEventObserver");
        Intrinsics.checkNotNullParameter(connectionEstablisher2, "connectionEstablisher");
        this.okHttpWebSocketHolder = okHttpWebSocketHolder2;
        this.okHttpWebSocketEventObserver = okHttpWebSocketEventObserver2;
        this.connectionEstablisher = connectionEstablisher2;
    }

    private final synchronized void handleConnectionShutdown() {
        this.okHttpWebSocketHolder.shutdown();
        this.okHttpWebSocketEventObserver.terminate();
    }

    /* access modifiers changed from: private */
    public final void handleWebSocketEvent(WebSocket.Event event) {
        if (event instanceof WebSocket.Event.OnConnectionOpened) {
            OkHttpWebSocketHolder okHttpWebSocketHolder2 = this.okHttpWebSocketHolder;
            Object webSocket = ((WebSocket.Event.OnConnectionOpened) event).getWebSocket();
            Intrinsics.checkNotNull(webSocket, "null cannot be cast to non-null type okhttp3.WebSocket");
            okHttpWebSocketHolder2.initiate((okhttp3.WebSocket) webSocket);
        } else if (event instanceof WebSocket.Event.OnConnectionClosing) {
            close(ShutdownReason.GRACEFUL);
        } else {
            if (event instanceof WebSocket.Event.OnConnectionClosed ? true : event instanceof WebSocket.Event.OnConnectionFailed) {
                handleConnectionShutdown();
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void open$lambda$0(Function1 function1, Object obj) {
        Intrinsics.checkNotNullParameter(function1, "$tmp0");
        function1.invoke(obj);
    }

    /* access modifiers changed from: private */
    public static final void open$lambda$1(Function1 function1, Object obj) {
        Intrinsics.checkNotNullParameter(function1, "$tmp0");
        function1.invoke(obj);
    }

    public synchronized void cancel() {
        this.okHttpWebSocketHolder.cancel();
    }

    public synchronized boolean close(@NotNull ShutdownReason shutdownReason) {
        Intrinsics.checkNotNullParameter(shutdownReason, "shutdownReason");
        return this.okHttpWebSocketHolder.close(shutdownReason.component1(), shutdownReason.component2());
    }

    @NotNull
    public Stream<WebSocket.Event> open() {
        Flowable<WebSocket.Event> doOnNext = this.okHttpWebSocketEventObserver.observe().doOnSubscribe(new a(new OkHttpWebSocket$open$1(this), 2)).doOnNext(new a(new OkHttpWebSocket$open$2(this), 3));
        Intrinsics.checkNotNullExpressionValue(doOnNext, "override fun open(): Str…vent)\n        .toStream()");
        return FlowableUtils.toStream(doOnNext);
    }

    public synchronized boolean send(@NotNull Message message) {
        boolean z2;
        try {
            Intrinsics.checkNotNullParameter(message, PushMessagingService.KEY_MESSAGE);
            if (message instanceof Message.Text) {
                z2 = this.okHttpWebSocketHolder.send(((Message.Text) message).getValue());
            } else if (message instanceof Message.Bytes) {
                byte[] value = ((Message.Bytes) message).getValue();
                z2 = this.okHttpWebSocketHolder.send(ByteString.Companion.of(value, 0, value.length));
            } else {
                throw new NoWhenBranchMatchedException();
            }
        } catch (Throwable th) {
            throw th;
        }
        return z2;
    }
}
