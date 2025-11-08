package com.tinder.scarlet.websocket.okhttp;

import com.tinder.scarlet.WebSocket;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public /* synthetic */ class OkHttpWebSocket$open$2 extends FunctionReferenceImpl implements Function1<WebSocket.Event, Unit> {
    public OkHttpWebSocket$open$2(Object obj) {
        super(1, obj, OkHttpWebSocket.class, "handleWebSocketEvent", "handleWebSocketEvent(Lcom/tinder/scarlet/WebSocket$Event;)V", 0);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((WebSocket.Event) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull WebSocket.Event event) {
        Intrinsics.checkNotNullParameter(event, "p0");
        ((OkHttpWebSocket) this.receiver).handleWebSocketEvent(event);
    }
}
