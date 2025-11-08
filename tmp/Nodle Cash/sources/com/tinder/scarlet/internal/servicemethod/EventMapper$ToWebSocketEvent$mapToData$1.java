package com.tinder.scarlet.internal.servicemethod;

import com.tinder.scarlet.Event;
import com.tinder.scarlet.WebSocket;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001\"\b\b\u0000\u0010\u0003*\u00020\u00042\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "Lcom/tinder/scarlet/WebSocket$Event;", "kotlin.jvm.PlatformType", "T", "", "it", "Lcom/tinder/scarlet/Event$OnWebSocket$Event;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class EventMapper$ToWebSocketEvent$mapToData$1 extends Lambda implements Function1<Event.OnWebSocket.C0066Event<?>, WebSocket.Event> {
    public static final EventMapper$ToWebSocketEvent$mapToData$1 INSTANCE = new EventMapper$ToWebSocketEvent$mapToData$1();

    public EventMapper$ToWebSocketEvent$mapToData$1() {
        super(1);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.tinder.scarlet.Event$OnWebSocket$Event<?>, com.tinder.scarlet.Event$OnWebSocket$Event, java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.tinder.scarlet.WebSocket.Event invoke(@org.jetbrains.annotations.NotNull com.tinder.scarlet.Event.OnWebSocket.C0066Event<?> r1) {
        /*
            r0 = this;
            java.lang.String r0 = "it"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
            com.tinder.scarlet.WebSocket$Event r0 = r1.getEvent()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tinder.scarlet.internal.servicemethod.EventMapper$ToWebSocketEvent$mapToData$1.invoke(com.tinder.scarlet.Event$OnWebSocket$Event):com.tinder.scarlet.WebSocket$Event");
    }
}
