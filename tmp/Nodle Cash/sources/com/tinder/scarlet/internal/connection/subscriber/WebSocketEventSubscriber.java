package com.tinder.scarlet.internal.connection.subscriber;

import com.tinder.scarlet.Event;
import com.tinder.scarlet.WebSocket;
import com.tinder.scarlet.internal.connection.Connection;
import io.reactivex.subscribers.DisposableSubscriber;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0002H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/tinder/scarlet/internal/connection/subscriber/WebSocketEventSubscriber;", "Lio/reactivex/subscribers/DisposableSubscriber;", "Lcom/tinder/scarlet/WebSocket$Event;", "stateManager", "Lcom/tinder/scarlet/internal/connection/Connection$StateManager;", "(Lcom/tinder/scarlet/internal/connection/Connection$StateManager;)V", "onComplete", "", "onError", "", "throwable", "", "onNext", "webSocketEvent", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class WebSocketEventSubscriber extends DisposableSubscriber<WebSocket.Event> {
    @NotNull
    private final Connection.StateManager stateManager;

    public WebSocketEventSubscriber(@NotNull Connection.StateManager stateManager2) {
        Intrinsics.checkNotNullParameter(stateManager2, "stateManager");
        this.stateManager = stateManager2;
    }

    public void onComplete() {
        this.stateManager.handleEvent(Event.OnWebSocket.Terminate.INSTANCE);
    }

    @NotNull
    public Void onError(@NotNull Throwable th) {
        Intrinsics.checkNotNullParameter(th, "throwable");
        throw th;
    }

    public void onNext(@NotNull WebSocket.Event event) {
        Intrinsics.checkNotNullParameter(event, "webSocketEvent");
        this.stateManager.handleEvent(new Event.OnWebSocket.C0066Event(event));
    }
}
