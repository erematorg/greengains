package com.tinder.scarlet.internal.connection;

import com.tinder.scarlet.Event;
import com.tinder.scarlet.WebSocket;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0002H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lcom/tinder/scarlet/Event$OnWebSocket$Event;", "invoke", "(Lcom/tinder/scarlet/Event$OnWebSocket$Event;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class Connection$StateManager$webSocketOpen$1 extends Lambda implements Function1<Event.OnWebSocket.C0066Event<?>, Boolean> {
    public static final Connection$StateManager$webSocketOpen$1 INSTANCE = new Connection$StateManager$webSocketOpen$1();

    public Connection$StateManager$webSocketOpen$1() {
        super(1);
    }

    @NotNull
    public final Boolean invoke(@NotNull Event.OnWebSocket.C0066Event<?> event) {
        Intrinsics.checkNotNullParameter(event, "$this$where");
        return Boolean.valueOf(event.getEvent() instanceof WebSocket.Event.OnConnectionOpened);
    }
}
