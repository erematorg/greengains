package com.tinder.scarlet.websocket.okhttp;

import com.tinder.scarlet.Message;
import com.tinder.scarlet.ShutdownReason;
import com.tinder.scarlet.WebSocket;
import io.reactivex.Flowable;
import io.reactivex.processors.BehaviorProcessor;
import io.reactivex.processors.FlowableProcessor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Response;
import okhttp3.WebSocketListener;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\bJ \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J \u0010\u0011\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\"\u0010\u0012\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\u0018\u0010\u0017\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u0010H\u0016J\u0018\u0010\u0017\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0018\u0010\u001b\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0006\u0010\u001c\u001a\u00020\nR\u001c\u0010\u0003\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/tinder/scarlet/websocket/okhttp/OkHttpWebSocketEventObserver;", "Lokhttp3/WebSocketListener;", "()V", "processor", "Lio/reactivex/processors/FlowableProcessor;", "Lcom/tinder/scarlet/WebSocket$Event;", "kotlin.jvm.PlatformType", "observe", "Lio/reactivex/Flowable;", "onClosed", "", "webSocket", "Lokhttp3/WebSocket;", "code", "", "reason", "", "onClosing", "onFailure", "t", "", "response", "Lokhttp3/Response;", "onMessage", "text", "bytes", "Lokio/ByteString;", "onOpen", "terminate", "scarlet-websocket-okhttp"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class OkHttpWebSocketEventObserver extends WebSocketListener {
    @NotNull
    private final FlowableProcessor<WebSocket.Event> processor;

    public OkHttpWebSocketEventObserver() {
        FlowableProcessor<WebSocket.Event> serialized = BehaviorProcessor.create().toSerialized();
        Intrinsics.checkNotNullExpressionValue(serialized, "create<WebSocket.Event>().toSerialized()");
        this.processor = serialized;
    }

    @NotNull
    public final Flowable<WebSocket.Event> observe() {
        Flowable<WebSocket.Event> onBackpressureBuffer = this.processor.onBackpressureBuffer();
        Intrinsics.checkNotNullExpressionValue(onBackpressureBuffer, "processor.onBackpressureBuffer()");
        return onBackpressureBuffer;
    }

    public void onClosed(@NotNull okhttp3.WebSocket webSocket, int i3, @NotNull String str) {
        Intrinsics.checkNotNullParameter(webSocket, "webSocket");
        Intrinsics.checkNotNullParameter(str, "reason");
        this.processor.onNext(new WebSocket.Event.OnConnectionClosed(new ShutdownReason(i3, str)));
    }

    public void onClosing(@NotNull okhttp3.WebSocket webSocket, int i3, @NotNull String str) {
        Intrinsics.checkNotNullParameter(webSocket, "webSocket");
        Intrinsics.checkNotNullParameter(str, "reason");
        this.processor.onNext(new WebSocket.Event.OnConnectionClosing(new ShutdownReason(i3, str)));
    }

    public void onFailure(@NotNull okhttp3.WebSocket webSocket, @NotNull Throwable th, @Nullable Response response) {
        Intrinsics.checkNotNullParameter(webSocket, "webSocket");
        Intrinsics.checkNotNullParameter(th, "t");
        this.processor.onNext(new WebSocket.Event.OnConnectionFailed(th));
    }

    public void onMessage(@NotNull okhttp3.WebSocket webSocket, @NotNull ByteString byteString) {
        Intrinsics.checkNotNullParameter(webSocket, "webSocket");
        Intrinsics.checkNotNullParameter(byteString, "bytes");
        this.processor.onNext(new WebSocket.Event.OnMessageReceived(new Message.Bytes(byteString.toByteArray())));
    }

    public void onOpen(@NotNull okhttp3.WebSocket webSocket, @NotNull Response response) {
        Intrinsics.checkNotNullParameter(webSocket, "webSocket");
        Intrinsics.checkNotNullParameter(response, "response");
        this.processor.onNext(new WebSocket.Event.OnConnectionOpened(webSocket));
    }

    public final void terminate() {
        this.processor.onComplete();
    }

    public void onMessage(@NotNull okhttp3.WebSocket webSocket, @NotNull String str) {
        Intrinsics.checkNotNullParameter(webSocket, "webSocket");
        Intrinsics.checkNotNullParameter(str, "text");
        this.processor.onNext(new WebSocket.Event.OnMessageReceived(new Message.Text(str)));
    }
}
