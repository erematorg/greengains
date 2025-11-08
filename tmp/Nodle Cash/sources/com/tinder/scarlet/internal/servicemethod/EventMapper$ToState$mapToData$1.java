package com.tinder.scarlet.internal.servicemethod;

import com.tinder.scarlet.Event;
import com.tinder.scarlet.State;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001\"\b\b\u0000\u0010\u0003*\u00020\u00042\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "Lcom/tinder/scarlet/State;", "kotlin.jvm.PlatformType", "T", "", "it", "Lcom/tinder/scarlet/Event$OnStateChange;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class EventMapper$ToState$mapToData$1 extends Lambda implements Function1<Event.OnStateChange<?>, State> {
    public static final EventMapper$ToState$mapToData$1 INSTANCE = new EventMapper$ToState$mapToData$1();

    public EventMapper$ToState$mapToData$1() {
        super(1);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.tinder.scarlet.Event$OnStateChange, com.tinder.scarlet.Event$OnStateChange<?>, java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.tinder.scarlet.State invoke(@org.jetbrains.annotations.NotNull com.tinder.scarlet.Event.OnStateChange<?> r1) {
        /*
            r0 = this;
            java.lang.String r0 = "it"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
            com.tinder.scarlet.State r0 = r1.getState()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tinder.scarlet.internal.servicemethod.EventMapper$ToState$mapToData$1.invoke(com.tinder.scarlet.Event$OnStateChange):com.tinder.scarlet.State");
    }
}
