package com.tinder.scarlet.internal.servicemethod;

import com.tinder.scarlet.Deserialization;
import com.tinder.scarlet.WebSocket;
import com.tinder.scarlet.internal.servicemethod.EventMapper;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0016\u0012\u0004\u0012\u0002H\u0002 \u0003*\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0004\"\b\b\u0001\u0010\u0002*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "Lcom/tinder/scarlet/Deserialization;", "T", "kotlin.jvm.PlatformType", "", "it", "Lcom/tinder/scarlet/WebSocket$Event;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class EventMapper$ToDeserialization$mapToData$2 extends Lambda implements Function1<WebSocket.Event, Deserialization<T>> {
    final /* synthetic */ EventMapper.ToDeserialization<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public EventMapper$ToDeserialization$mapToData$2(EventMapper.ToDeserialization<T> toDeserialization) {
        super(1);
        this.this$0 = toDeserialization;
    }

    public final Deserialization<T> invoke(@NotNull WebSocket.Event event) {
        Intrinsics.checkNotNullParameter(event, "it");
        return this.this$0.deserialize(((WebSocket.Event.OnMessageReceived) event).getMessage());
    }
}
